package org.openimmunizationsoftware.dqa.service;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.openimmunizationsoftware.dqa.IncomingServlet;
import org.openimmunizationsoftware.dqa.ProcessLocker;
import org.openimmunizationsoftware.dqa.IncomingServlet.Results;
import org.openimmunizationsoftware.dqa.db.model.Application;
import org.openimmunizationsoftware.dqa.db.model.BatchType;
import org.openimmunizationsoftware.dqa.db.model.Organization;
import org.openimmunizationsoftware.dqa.db.model.ReportTemplate;
import org.openimmunizationsoftware.dqa.db.model.SubmitterProfile;
import org.openimmunizationsoftware.dqa.manager.OrganizationManager;
import org.openimmunizationsoftware.dqa.quality.QualityCollector;

public class SubmitMessageHandler
{
  public SubmitMessageResultType submitMessage(SubmitMessageType request) throws RemoteException, FaultType
  {
    List<IssueType> errorList = new ArrayList<IssueType>();
    List<IssueType> warningList = new ArrayList<IssueType>();
    SubmitMessageResultType result = new SubmitMessageResultType();
    result.setBatchId(0);
    result.setHashId("hashId");
    result.setMessageResponse("NO RESPONSE");
    result.setReceivedId(0);
    result.setResponseStatus("AR");
    result.setResponseText("UNABLE TO PROCESS");
    result.setErrorList(errorList.toArray(new IssueType[] {}));
    result.setWarningList(warningList.toArray(new IssueType[] {}));
    SessionFactory factory = OrganizationManager.getSessionFactory();
    Session session = factory.openSession();

    try
    {
      String profileCode = request.getProfileCode();
      if (profileCode == null || profileCode.equals(""))
      {
        errorList.add(new IssueType(0, "Profile code not specified"));
      } else
      {
        SubmitterProfile profile = null;
        Query query = session.createQuery("from SubmitterProfile where profileCode = ?");
        query.setParameter(0, profileCode);
        List<SubmitterProfile> profileList = query.list();
        if (profileList.size() == 0)
        {
          warningList.add(new IssueType(0, "Profile code not recognized, creating new profile"));
          profile = createNewProfile(request, session, profileCode);
        } else
        {
          profile = profileList.get(0);
        }

        boolean debug = request.getProcessMode() != null && request.getProcessMode().equals("Debug");
        StringWriter stringWriterResponse = new StringWriter();
        PrintWriter out = new PrintWriter(stringWriterResponse);
        QualityCollector qualityCollector = null;
        Results results = new Results();
        results.setDebug(debug);
        results.setErrorList(errorList);
        results.setWarningList(warningList);
        try
        {
          ProcessLocker.lock(profile);
          qualityCollector = new QualityCollector("Web Service", BatchType.SUBMISSION, profile);
          String messageData = request.getMessageRequest();
          IncomingServlet.processStream(debug, session, profile, messageData, out, qualityCollector, results);
          debug = results.isDebug();
        } catch (Exception e)
        {
          errorList.add(new IssueType(0, "Processing exception: " + e.getMessage()));
          e.printStackTrace();
        } finally
        {
          ProcessLocker.unlock(profile);
        }
        if (debug)
        {
          processDebug(errorList, result, session, profile, stringWriterResponse, qualityCollector);
        }
        result.setBatchId(results.getBatchId());
        result.setHashId("hashId");
        result.setMessageResponse(stringWriterResponse.toString());
        result.setReceivedId(0);
        result.setResponseStatus(results.getResponseStatus());
        result.setResponseText(results.getResponseText());
      }

    } finally
    {
      session.close();
    }
    result.setErrorList(errorList.toArray(new IssueType[] {}));
    result.setWarningList(warningList.toArray(new IssueType[] {}));
    return result;

  }

  private void processDebug(List<IssueType> errorList, SubmitMessageResultType result, Session session, SubmitterProfile profile,
      StringWriter stringWriterResponse, QualityCollector qualityCollector)
  {
    StringWriter stringWriterDebug = new StringWriter();
    PrintWriter outDebug = new PrintWriter(stringWriterResponse);
    try
    {
      IncomingServlet.printDebugOutput(outDebug, session, profile, qualityCollector);
    } catch (Exception e)
    {
      errorList.add(new IssueType(1, "Unable to print debug information: " + e.getMessage()));
    }
    result.setProcessReport(stringWriterDebug.toString());
  }

  private SubmitterProfile createNewProfile(SubmitMessageType request, Session session, String profileCode)
  {
    SubmitterProfile profile;
    Transaction transaction = session.beginTransaction();

    Organization organization = findOrCreateOrganization(request, session);

    String profileLabel = request.getProfileLabel();
    if (profileLabel == null || profileLabel.equals(""))
    {
      profileLabel = profileCode;
    }
    profile = new SubmitterProfile();
    profile.generateAccessKey();
    profile.setDataFormat(SubmitterProfile.DATA_FORMAT_HL7V2);
    profile.setOrganization(organization);
    profile.setProfileCode(profileCode);
    profile.setProfileLabel(profileLabel);
    profile.setProfileStatus(SubmitterProfile.PROFILE_STATUS_TEST);
    profile.setTransferPriority(SubmitterProfile.TRANSFER_PRIORITY_NORMAL);
    Query query = session.createQuery("from Application where runThis = 'Y'");
    List<Application> applicationList = query.list();
    if (applicationList.size() > 0)
    {
      Application application = applicationList.get(0);
      profile.setReportTemplate(application.getPrimaryReportTemplate());
    } else
    {
      profile.setReportTemplate((ReportTemplate) session.get(ReportTemplate.class, 1));
    }

    session.save(profile);
    transaction.commit();
    return profile;
  }

  private Organization findOrCreateOrganization(SubmitMessageType request, Session session)
  {
    Query query;
    Organization organization;
    String orgLocalCode = "" + request.getOrgLocalCode();
    query = session.createQuery("from Organization where orgLocalCode = ?");
    query.setParameter(0, orgLocalCode);
    List<Organization> organizationList = query.list();
    if (organizationList.size() == 0)
    {
      organization = (Organization) session.get(Organization.class, 1);
    } else
    {
      organization = organizationList.get(0);
    }
    return organization;
  }
}
