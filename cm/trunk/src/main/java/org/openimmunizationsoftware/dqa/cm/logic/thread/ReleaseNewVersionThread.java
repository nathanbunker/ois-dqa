package org.openimmunizationsoftware.dqa.cm.logic.thread;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.openimmunizationsoftware.dqa.cm.logic.ReleaseVersionLogic;
import org.openimmunizationsoftware.dqa.cm.model.AcceptStatus;
import org.openimmunizationsoftware.dqa.cm.model.AttributeInstance;
import org.openimmunizationsoftware.dqa.cm.model.CodeInstance;
import org.openimmunizationsoftware.dqa.cm.model.CodeTableInstance;
import org.openimmunizationsoftware.dqa.cm.model.InclusionStatus;
import org.openimmunizationsoftware.dqa.cm.model.ReleaseStatus;
import org.openimmunizationsoftware.dqa.cm.model.ReleaseVersion;
import org.openimmunizationsoftware.dqa.cm.model.User;

public class ReleaseNewVersionThread extends LogicThread
{
  private ReleaseVersion releaseVersion;

  public ReleaseNewVersionThread(ReleaseVersion releaseVersion, User user) {
    super(user.getUserId());
    this.releaseVersion = releaseVersion;
  }

  @Override
  public void run()
  {
    try
    {
      int majorVersionNum = releaseVersion.getMajorVersionNum();
      int minorVersionNum = releaseVersion.getMinorVersionNum() + 1;
      out.println("<h1>Release New Version</h1>");
      out.println("<p>Moving proposed version " + releaseVersion.getVersion() + " to current, and creating new proposed version " + majorVersionNum
          + "." + minorVersionNum + " as a copy of current. </p> ");
      out.println("<h3>Step 1</h3> <p>Verify that new proposed version number does not currently exist. </p>");
      boolean proposedVersionExists = ReleaseVersionLogic.checkIfProposedVersionExists(majorVersionNum, minorVersionNum, dataSession);
      if (proposedVersionExists)
      {
        out.println("        Problem! Proposed version " + majorVersionNum + "." + minorVersionNum + " already exists, process is being terminated. ");
        return;
      }
      Transaction transaction = dataSession.beginTransaction();
      out.println("<h3>Step 2</h3> <p>Marking any current releases with the same major version number as deprecated.");
      {
        Query query = dataSession.createQuery("from ReleaseVersion where majorVersionNum = ? and releaseStatusString = ?");
        query.setParameter(0, releaseVersion.getMajorVersionNum());
        query.setParameter(1, ReleaseStatus.CURRENT.getId());
        @SuppressWarnings("unchecked")
        List<ReleaseVersion> releaseVersionList = query.list();
        for (ReleaseVersion rv : releaseVersionList)
        {
          out.println("        + Deprecating release version " + rv.getVersion() + ".");
          rv.setReleaseStatus(ReleaseStatus.DEPRECATED);
          rv.setRetiredDate(new Date());
          dataSession.update(rv);
        }
      }

      out.println("<h3>Step 3</h3> ");
      out.println("<p>Marking previously proposed release as current");
      releaseVersion.setReleasedDate(new Date());
      releaseVersion.setReleaseStatus(ReleaseStatus.CURRENT);
      dataSession.update(releaseVersion);

      ReleaseVersion proposedReleaseVersion = new ReleaseVersion();
      proposedReleaseVersion.setMajorVersionNum(majorVersionNum);
      proposedReleaseVersion.setMinorVersionNum(minorVersionNum);
      proposedReleaseVersion.setStartedDate(new Date());
      proposedReleaseVersion.setReleaseStatus(ReleaseStatus.PROPOSED);
      out.println("<h3>Step 4</h3> ");
      out.println("<p>Creating new proposed release " + proposedReleaseVersion.getVersion());
      dataSession.save(proposedReleaseVersion);

      {
        Query query = dataSession.createQuery("from CodeTableInstance where release = ? order by tableLabel");
        query.setParameter(0, releaseVersion);
        @SuppressWarnings("unchecked")
        List<CodeTableInstance> codeTableInstanceList = query.list();

        out.println("<h3>Step 5</h3> ");
        out.println("<p>Copying Instances");
        out.println("<ul>");
        outULTagOpen = true;
        for (CodeTableInstance cti : codeTableInstanceList)
        {
          if (cti.getInclusionStatus() == InclusionStatus.REMOVE)
          {
            out.println("<li>Skipping table " + cti.getTableLabel() + " becuase it is marked for removal in the next version</li>");
            continue;
          }
          out.println("<li>Copying table " + cti.getTableLabel() + "</li>");
          CodeTableInstance codeTableInstance = new CodeTableInstance();
          codeTableInstance.setTable(cti.getTable());
          codeTableInstance.setRelease(proposedReleaseVersion);
          codeTableInstance.setTableLabel(cti.getTableLabel());
          codeTableInstance.setInclusionStatus(cti.getInclusionStatus());
          codeTableInstance.setEnforceUniqe(cti.isEnforceUniqe());
          codeTableInstance.setIssueCount(cti.getIssueCount());
          dataSession.save(codeTableInstance);

          query = dataSession.createQuery("from CodeInstance where tableInstance = ?");
          query.setParameter(0, cti);
          @SuppressWarnings("unchecked")
          List<CodeInstance> codeInstanceList = query.list();
          for (CodeInstance ci : codeInstanceList)
          {
            if (ci.getInclusionStatus() == InclusionStatus.REMOVE)
            {
              continue;
            }
            CodeInstance codeInstance = new CodeInstance();
            codeInstance.setCode(ci.getCode());
            codeInstance.setContext(ci.getContext());
            codeInstance.setIndicatesTable(ci.getIndicatesTable());
            codeInstance.setCodeLabel(ci.getCodeLabel());
            codeInstance.setUseValue(ci.getUseValue());
            codeInstance.setCodeStatus(ci.getCodeStatus());
            codeInstance.setTableInstance(codeTableInstance);
            codeInstance.setInclusionStatus(ci.getInclusionStatus());
            codeInstance.setIssueCount(ci.getIssueCount());
            dataSession.save(codeInstance);

            query = dataSession.createQuery("from AttributeInstance where codeInstance = ?");
            query.setParameter(0, ci);
            @SuppressWarnings("unchecked")
            List<AttributeInstance> attributeInstanceList = query.list();
            for (AttributeInstance ai : attributeInstanceList)
            {
              if (ai.getAcceptStatus() == AcceptStatus.REJECTED)
              {
                continue;
              }
              AttributeInstance attributeInstance = new AttributeInstance();
              attributeInstance.setValue(ai.getValue());
              attributeInstance.setCodeInstance(codeInstance);
              attributeInstance.setAcceptStatus(ai.getAcceptStatus());
              dataSession.save(attributeInstance);
            }
          }
        }
        out.println("</ol>");
        outULTagOpen = false;
      }

      transaction.commit();
      out.println("<p>Process complete, changes committed</p>");
    } catch (Exception e)
    {
      out.println("<p>Unable to complete because of exception " + e.getMessage() + "</p>");
      out.println("<pre>");
      e.printStackTrace(out);
      out.println("</pre>");
    } finally
    {
      out.close();
      complete = true;
    }
  }
}
