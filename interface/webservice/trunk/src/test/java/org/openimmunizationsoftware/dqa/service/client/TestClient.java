package org.openimmunizationsoftware.dqa.service.client;

import java.rmi.RemoteException;

import org.openimmunizationsoftware.dqa.service.client.DqaServiceProxy;
import org.openimmunizationsoftware.dqa.service.client.SubmitMessageResultType;
import org.openimmunizationsoftware.dqa.service.client.SubmitMessageType;


public class TestClient {

  private static final String WS_ENDPOINT_TOMCAT = "http://localhost:8080/dqa/services/DqaServiceSOAP";

  /**
   * @param args
   */
  public static void main(String[] args) {
    System.out.println("***** BEGIN web service test *****");
    try {
      SubmitMessageType request = new SubmitMessageType("profileCode", "profileLabel", 0, "messageRequest", "processMode", "batchInstruction");
      SubmitMessageResultType results = getProxy().submitMessage(request);
      System.out.println("Batch ID: " + results.getBatchId());
      System.out.println("Hash ID: " + results.getHashId());
      System.out.println("Message Response: " + results.getMessageResponse());
      System.out.println("Process Report: " + results.getProcessReport());
      System.out.println("Received ID: " + results.getReceivedId());
      System.out.println("Response Status: " + results.getResponseStatus());
      System.out.println("Response Text: " + results.getResponseText());
      System.out.println("Error List: " + results.getErrorList().length);
      System.out.println("Warning List: " + results.getWarningList().length);

    } catch (RemoteException e) {
      e.printStackTrace();
    }
    System.out.println("***** END web service test *****");
  }

  private static DqaServiceProxy getProxy() {
    String wsEndpoint = WS_ENDPOINT_TOMCAT; 
    DqaServiceProxy proxy = new DqaServiceProxy(wsEndpoint);
    return proxy;
  }

}
