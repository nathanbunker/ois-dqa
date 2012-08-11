package org.openimmunizationsoftware.dqa.service;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.rmi.RemoteException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.openimmunizationsoftware.dqa.ProcessLocker;
import org.openimmunizationsoftware.dqa.db.model.Application;
import org.openimmunizationsoftware.dqa.db.model.BatchType;
import org.openimmunizationsoftware.dqa.db.model.MessageReceived;
import org.openimmunizationsoftware.dqa.db.model.Organization;
import org.openimmunizationsoftware.dqa.db.model.ReportTemplate;
import org.openimmunizationsoftware.dqa.db.model.SubmitterProfile;
import org.openimmunizationsoftware.dqa.manager.HashManager;
import org.openimmunizationsoftware.dqa.manager.OrganizationManager;
import org.openimmunizationsoftware.dqa.parse.VaccinationUpdateParserHL7;
import org.openimmunizationsoftware.dqa.process.MessageProcessRequest;
import org.openimmunizationsoftware.dqa.process.MessageProcessor;
import org.openimmunizationsoftware.dqa.quality.QualityCollector;
import org.openimmunizationsoftware.dqa.service.schema.FaultType;
import org.openimmunizationsoftware.dqa.service.schema.IssueType;
import org.openimmunizationsoftware.dqa.service.schema.SubmitMessageResultType;
import org.openimmunizationsoftware.dqa.service.schema.SubmitMessageType;

public class SubmitMessageHandler
{

  static Logger logger = Logger.getLogger(SubmitMessageHandler.class.getName());

  private static SubmitMessageHandler submitMessageHandler = null;

  public static SubmitMessageHandler getInstance()
  {
    if (submitMessageHandler == null)
    {
      submitMessageHandler = new SubmitMessageHandler();
    }
    return submitMessageHandler;
  }

  public SubmitMessageResultType submitMessage(SubmitMessageType request) throws RemoteException, FaultType
  {
    List<IssueType> errorList = new ArrayList<IssueType>();
    List<IssueType> warningList = new ArrayList<IssueType>();
    SubmitMessageResultType result = new SubmitMessageResultType();
    result.setBatchId(0);
    String hashId = "";
    String messageResponse = "";
    try
    {
      hashId = HashManager.getMD5Hash(request.getMessageRequest());
    } catch (NoSuchAlgorithmException nse)
    {
      // TODO handle hash problem
      nse.printStackTrace();
    }
    logger.info(hashId);
    result.setHashId(hashId);
    result.setMessageResponse("NO RESPONSE");
    result.setReceivedId(0);
    result.setResponseStatus("AR");
    result.setResponseText("UNABLE TO PROCESS");
    result.setErrorList(errorList.toArray(new IssueType[] {}));
    result.setWarningList(warningList.toArray(new IssueType[] {}));
    logger.info("gettingSessionFactory");
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
          String str = "Profile code not recognized, creating new profile";
          logger.warning(str);
          warningList.add(new IssueType(0, str));
          profile = createNewProfile(request, session, profileCode);
        } else
        {
          profile = profileList.get(0);
        }
        VaccinationUpdateParserHL7 parser = new VaccinationUpdateParserHL7(profile);
        boolean debug = request.getProcessMode() != null && request.getProcessMode().equals("Debug");
        StringWriter stringWriterResponse = new StringWriter();
        PrintWriter out = new PrintWriter(stringWriterResponse);
        QualityCollector qualityCollector = null;

        ProcessLocker.lock(profile);
        qualityCollector = new QualityCollector("Web Service", BatchType.SUBMISSION, profile);
        String messageData = request.getMessageRequest();
        MessageProcessRequest procRequest = new MessageProcessRequest();
        procRequest.setDebugFlag(debug);
        procRequest.setParser(parser);
        procRequest.setMessageText(messageData);
        procRequest.setProfile(profile);
        procRequest.setSession(session);
        procRequest.setQualityCollector(qualityCollector);
        procRequest.setMessageKey(hashId);
        
        MessageReceived msg = MessageProcessor.processMessage(procRequest).getMessageReceived();

        debug = msg.isDebugOn();
        if (!msg.isSuccessful())
        {
          String str = "Processing exception: " + msg.getException().getMessage();
          logger.warning(str);
          errorList.add(new IssueType(0, str));
          msg.getException().printStackTrace();
        }
        result.setBatchId(qualityCollector.getMessageBatch().getBatchId());
        result.setReceivedId(0);
        result.setResponseStatus(msg.getIssueAction().getActionCode());
        result.setMessageResponse(msg.getResponseText());
        result.setResponseText("Message Received"); // TODO need to get actual
                                                    // text out, requires change
                                                    // to core

        if (debug)
        {
          try
          {
            String output = MessageProcessor.processDebugOutput(session, profile, qualityCollector);
            result.setResponseText(msg.getResponseText() + output);
          } catch (Exception e)
          {
            errorList.add(new IssueType(1, "Unable to print debug information: " + e.getMessage()));
          }
        }
        ProcessLocker.unlock(profile);
      }

    } finally
    {
      session.close();
    }
    result.setErrorList(errorList.toArray(new IssueType[] {}));
    result.setWarningList(warningList.toArray(new IssueType[] {}));
    logger.info(result.toString());
    return result;

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
