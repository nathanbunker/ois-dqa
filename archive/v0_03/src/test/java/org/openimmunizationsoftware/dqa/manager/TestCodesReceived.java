package org.openimmunizationsoftware.dqa.manager;

import junit.framework.TestCase;

import org.openimmunizationsoftware.dqa.db.model.CodeReceived;

public class TestCodesReceived extends TestCase
{
  
  public void testGetCodesReceived()
  {
    CodesReceived codesReceived = CodesReceived.getCodesReceived();
    assertEquals(20, codesReceived.getCodeTables().size());
    CodeReceived cr = codesReceived.getCodeReceived("90700", CodesReceived.getCodeTable(CodesReceived.CPT));
    assertNotNull(cr);
    cr = codesReceived.getCodeReceived("03", CodesReceived.getCodeTable(CodesReceived.CVX));
    assertNotNull(cr);
    cr = codesReceived.getCodeReceived("00", CodesReceived.getCodeTable(CodesReceived.INFO_SOURCE));
    assertNotNull(cr);
    cr = codesReceived.getCodeReceived("IM", CodesReceived.getCodeTable(CodesReceived.BODY_ROUTE));
    assertNotNull(cr);
    assertEquals("V", cr.getCodeStatus().getCodeStatus());
  }

}
