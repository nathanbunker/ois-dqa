package org.openimmunizationsoftware.dqa;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.Test;
import org.openimmunizationsoftware.dqa.service.DqaService;
import org.openimmunizationsoftware.dqa.service.schema.FaultType;
import org.openimmunizationsoftware.dqa.service.schema.SubmitMessageResultType;
import org.openimmunizationsoftware.dqa.service.schema.SubmitMessageType;


public class DqaServiceTest {

  private static final String TEST_MESSAGE = "MSH|^~\\&|||||20120518171320||VXU^V04^VXU_V04|NONE|P|2.5.1|\r"
      + "PID|1||NONE^^^OIS-TEST^MR||Durbin^Enda^A^Jr^^^L|Clatsop|19990516|M|||178 Ingham Ave^^Simplicity Pattern^MI^49121^USA||(269)029-6779^PRN^PH^^^269^029-6779|\r"
      + "NK1|1|Durbin^Alfreda|MTH^Mother^HL70063|\r" + "PV1|1|R|\r" + "ORC|RE||NONE.1^OIS|\r"
      + "RXA|0|1|20080516||115^Tdap^CVX|999|||01^Historical^NIP0001||||||||||||A|\r" + "ORC|RE||NONE.2^OIS|\r"
      + "RXA|0|1|20100513||118^HPV^CVX|999|||01^Historical^NIP0001||||||||||||A|\r" + "ORC|RE||NONE.3^OIS|\r"
      + "RXA|0|1|20120518||83^Hep A^CVX|0.5|ML||00^Administered^NIP0001||||||W0253JI||SKB^GlaxoSmithKline^MVX||||A|\r";

      
  @Test
  public void testSubmitMessage() {
    SubmitMessageType request = new SubmitMessageType();
    request.setProfileCode("test123");
    request.setProfileLabel("Test 123");
    request.setOrgLocalCode(123);
    request.setMessageRequest(TEST_MESSAGE);
    request.setProcessMode("Submit");
    System.out.println(request.toString());
    SubmitMessageResultType results;
    try {
      results = DqaService.getInstance().submitMessage(request);
      System.out.println("-- REQUEST  ----------------------------------------------");
      System.out.println(request.getMessageRequest().replace('\r', '\n'));
      System.out.println("-- RESPONSE ----------------------------------------------");
      System.out.println(results.getMessageResponse().replace('\r', '\n'));

      System.out.println("  + Response Text:   " + results.getResponseText());
      System.out.println("  + Response Status: " + results.getResponseStatus());
      System.out.println("  + Batch ID:        " + results.getBatchId());
      System.out.println("  + Hash ID:         " + results.getHashId());
      System.out.println("  + Received ID:     " + results.getReceivedId());

    } catch (FaultType e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      fail("FaultType thrown");
    
    } catch (RemoteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      fail("RemoteException thrown");
    }
  }

//  @Test
//  public void testGetBatches() {
//    fail("Not yet implemented");
//  }

  @Test
  public void testGetInstance() {
    assertTrue(null != DqaService.getInstance());
  }

//  @Test
//  public void testGetDateFormatter() {
//    fail("Not yet implemented");
//  }

}
