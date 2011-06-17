package org.openimmunizationsoftware.dqa.manager;

import org.openimmunizationsoftware.dqa.db.model.CodeReceived;
import org.openimmunizationsoftware.dqa.manager.CodesReceived;

import junit.framework.TestCase;

public class TestCodesReceived extends TestCase
{
  
  public void testGetCodesReceived()
  {
    CodesReceived codesReceived = CodesReceived.getCodesReceived();
    assertEquals(19, codesReceived.getCodeTables().size());
    CodeReceived cr = codesReceived.getCodeReceived("90700", CodesReceived.getCodeTable(CodesReceived.CPT));
    assertNotNull(cr);
    cr = codesReceived.getCodeReceived("03", CodesReceived.getCodeTable(CodesReceived.CVX));
    assertNotNull(cr);
    
  }

}
