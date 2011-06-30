package org.openimmunizationsoftware.dqa.manager;

import java.util.Date;

import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.db.model.IssueFound;
import org.openimmunizationsoftware.dqa.db.model.MessageReceived;
import org.openimmunizationsoftware.dqa.db.model.SubmitStatus;
import org.openimmunizationsoftware.dqa.db.model.SubmitterProfile;
import org.openimmunizationsoftware.dqa.db.model.received.NextOfKin;
import org.openimmunizationsoftware.dqa.db.model.received.Patient;
import org.openimmunizationsoftware.dqa.db.model.received.Vaccination;

public class MessageReceivedManager
{
  public static void saveMessageReceived(SubmitterProfile profile, MessageReceived messageReceived, Session session)
  {
    messageReceived.setProfile(profile);
    messageReceived.setReceivedDate(new Date());
    messageReceived.setSubmitStatus(SubmitStatus.HOLD);
    session.saveOrUpdate(messageReceived);
    Patient patient = messageReceived.getPatient();
    if (!patient.isSkipped())
    {
      patient.setMessageReceived(messageReceived);
      session.save(patient);
      for (Vaccination vaccination : messageReceived.getVaccinations())
      {
        if (!vaccination.isSkipped())
        {
          vaccination.setMessageReceived(messageReceived);
          session.saveOrUpdate(vaccination);
        }
      }
      for (NextOfKin nextOfKin : messageReceived.getNextOfKins())
      {
        if (!nextOfKin.isSkipped())
        {
          nextOfKin.setMessageReceived(messageReceived);
          session.saveOrUpdate(nextOfKin);
        }
      }
    }
    for (IssueFound issueFound : messageReceived.getIssuesFound())
    {
      issueFound.setMessageReceived(messageReceived);
      session.saveOrUpdate(issueFound);
    }
  }
}
