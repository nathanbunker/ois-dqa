package org.openimmunizationsoftware.dqa.service.client;

import java.rmi.RemoteException;

import org.openimmunizationsoftware.dqa.service.client.DqaServiceProxy;
import org.openimmunizationsoftware.dqa.service.client.SubmitMessageResultType;
import org.openimmunizationsoftware.dqa.service.client.SubmitMessageType;

public class TestClient
{

  private static final String WS_ENDPOINT_TOMCAT = "http://localhost:8080/dqa/services/DqaServiceSOAP";
  private static final String WS_ENDPONT_DEV_NATHAN_TOMCAT = "http://localhost:8086/webservice2/services/DqaServiceSOAP";
  private static final String WS_ENDPONT_DEV_NATHAN_JETTY = "http://localhost:8281/services/DqaServiceSOAP";

  private static final String TEST_MESSAGE = "MSH|^~\\&|||||20120518171320||VXU^V04^VXU_V04|NONE|P|2.5.1|\r"
      + "PID|1||NONE^^^OIS-TEST^MR||Durbin^Enda^A^Jr^^^L|Clatsop|19990516|M|||178 Ingham Ave^^Simplicity Pattern^MI^49121^USA||(269)029-6779^PRN^PH^^^269^029-6779|\r"
      + "NK1|1|Durbin^Alfreda|MTH^Mother^HL70063|\r" + "PV1|1|R|\r" + "ORC|RE||NONE.1^OIS|\r"
      + "RXA|0|1|20080516||115^Tdap^CVX|999|||01^Historical^NIP0001||||||||||||A|\r" + "ORC|RE||NONE.2^OIS|\r"
      + "RXA|0|1|20100513||118^HPV^CVX|999|||01^Historical^NIP0001||||||||||||A|\r" + "ORC|RE||NONE.3^OIS|\r"
      + "RXA|0|1|20120518||83^Hep A^CVX|0.5|ML||00^Administered^NIP0001||||||W0253JI||SKB^GlaxoSmithKline^MVX||||A|\r";;

  // mvn exec:java
  // -Dexec.mainClass="org.openimmunizationsoftware.dqa.service.client.TestClient"

  /**
   * @param args
   */
  public static void main(String[] args)
  {
    System.out.println("***** BEGIN web service test *****");
    try
    {
      SubmitMessageType request = new SubmitMessageType();
      request.setProfileCode("test123");
      request.setProfileLabel("Test 123");
      request.setOrgLocalCode(123);
      request.setMessageRequest(TEST_MESSAGE);
      request.setProcessMode("Submit");
      SubmitMessageResultType results = getProxy().submitMessage(request);
      System.out.println("-- REQUEST  ----------------------------------------------");
      System.out.println(request.getMessageRequest().replace('\r', '\n'));
      System.out.println("-- RESPONSE ----------------------------------------------");
      System.out.println(results.getMessageResponse().replace('\r', '\n'));

      System.out.println("  + Response Text:   " + results.getResponseText());
      System.out.println("  + Response Status: " + results.getResponseStatus());
      System.out.println("  + Batch ID:        " + results.getBatchId());
      System.out.println("  + Hash ID:         " + results.getHashId());
      System.out.println("  + Received ID:     " + results.getReceivedId());
      if (results.getErrorList().length > 0)
      {
        System.out.println("Errors ");
        for (IssueType issueType : results.getErrorList())
        {
          System.out.println("  + " + issueType.getIssueLabel() + " (" + issueType.getIssueId() + ")");
        }
      }
      if (results.getErrorList().length > 0)
      {
        System.out.println("Warnings ");
        for (IssueType issueType : results.getWarningList())
        {
          System.out.println("  + " + issueType.getIssueLabel() + " (" + issueType.getIssueId() + ")");
        }
      }
      System.out.println("Process Report: ");
      System.out.println(results.getProcessReport());

    } catch (RemoteException e)
    {
      e.printStackTrace();
    }
    System.out.println("***** END web service test *****");
  }

  private static DqaServiceProxy getProxy()
  {
    String wsEndpoint = WS_ENDPONT_DEV_NATHAN_JETTY;
    DqaServiceProxy proxy = new DqaServiceProxy(wsEndpoint);
    return proxy;
  }

}
