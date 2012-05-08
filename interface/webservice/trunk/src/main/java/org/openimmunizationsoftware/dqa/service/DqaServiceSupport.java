package org.openimmunizationsoftware.dqa.service;

import java.rmi.RemoteException;

public class DqaServiceSupport {
  
  public static SubmitMessageResultType submitMessage(SubmitMessageType request) throws RemoteException, FaultType {
    IssueType[] errorList = new IssueType[] {};
    IssueType[] warningList = new IssueType[] {};
    return new SubmitMessageResultType("messageResponse", "responseStatus", "responseText", 0L, 0L, "hashId", errorList, warningList, "processReport");
  }
  
}
