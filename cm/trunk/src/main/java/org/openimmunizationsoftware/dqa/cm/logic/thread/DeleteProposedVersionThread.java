package org.openimmunizationsoftware.dqa.cm.logic.thread;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.openimmunizationsoftware.dqa.cm.model.AttributeInstance;
import org.openimmunizationsoftware.dqa.cm.model.CodeInstance;
import org.openimmunizationsoftware.dqa.cm.model.CodeTableInstance;
import org.openimmunizationsoftware.dqa.cm.model.ReleaseStatus;
import org.openimmunizationsoftware.dqa.cm.model.ReleaseVersion;
import org.openimmunizationsoftware.dqa.cm.model.User;

public class DeleteProposedVersionThread extends LogicThread
{
  
  private ReleaseVersion proposedReleaseVersion = null;
  public DeleteProposedVersionThread(User user, ReleaseVersion proposedReleaseVersion) {
    super(user.getUserId());
    this.proposedReleaseVersion = proposedReleaseVersion;
  }

  @Override
  public void run()
  {
    try
    {
      out.println("<h2>Delete Proposed Version</h2>");
      out.println("<p>Deleting proposed version, this will remove version " + proposedReleaseVersion.getVersion() + "</p>");
      Transaction transaction = dataSession.beginTransaction();
      Query query = dataSession.createQuery("from CodeTableInstance where release = ?");
      query.setParameter(0, proposedReleaseVersion);
      @SuppressWarnings("unchecked")
      List<CodeTableInstance> codeTableInstanceList = query.list();
      for (CodeTableInstance cti : codeTableInstanceList)
      {
        query = dataSession.createQuery("from CodeInstance where tableInstance = ?");
        query.setParameter(0, cti);
        @SuppressWarnings("unchecked")
        List<CodeInstance> codeInstanceList = query.list();
        for (CodeInstance ci : codeInstanceList)
        {
          query = dataSession.createQuery("from AttributeInstance where codeInstance = ?");
          query.setParameter(0, ci);
          @SuppressWarnings("unchecked")
          List<AttributeInstance> attributeInstanceList = query.list();
          for (AttributeInstance ai : attributeInstanceList)
          {
            dataSession.delete(ai);
          }
          dataSession.delete(ci);
        }
        dataSession.delete(cti);
      }
      int majorVersionNum = proposedReleaseVersion.getMajorVersionNum();
      int minorVersionNum = proposedReleaseVersion.getMinorVersionNum();
      dataSession.delete(proposedReleaseVersion);
      if (minorVersionNum > 0)
      {
        minorVersionNum--;
        out.println("<p>Reverting previously released version " + majorVersionNum + "." + minorVersionNum + " back to proposed.</p>");
        query = dataSession.createQuery("from ReleaseVersion where majorVersionNum = ? and minorVersionNum = ? ");
        query.setParameter(0, majorVersionNum);
        query.setParameter(1, minorVersionNum);
        @SuppressWarnings("unchecked")
        List<ReleaseVersion> releaseVersionList = query.list();
        if (releaseVersionList.size() > 0)
        {
          ReleaseVersion rv = releaseVersionList.get(0);
          out.println("<p>Found release, looking to revert to proposed</p>");
          if (rv.getReleaseStatus() == ReleaseStatus.CURRENT)
          {
            out.println("<p>Release is current, moving back to propsoed</p>");
            rv.setReleaseStatus(ReleaseStatus.PROPOSED);
            rv.setReleasedDate(null);
            rv.setRetiredDate(null);
            dataSession.update(rv);
          }
        }
      }
      transaction.commit();
      out.println("<p>Deleting process completed</p>");
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
