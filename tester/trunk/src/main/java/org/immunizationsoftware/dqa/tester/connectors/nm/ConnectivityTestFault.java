
/**
 * ConnectivityTestFault.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package org.immunizationsoftware.dqa.tester.connectors.nm;

public class ConnectivityTestFault extends java.lang.Exception{

    private static final long serialVersionUID = 1352994966042L;
    
    private org.immunizationsoftware.dqa.tester.connectors.tlep.UnsupportedOperationFault faultMessage;

    
        public ConnectivityTestFault() {
            super("ConnectivityTestFault");
        }

        public ConnectivityTestFault(java.lang.String s) {
           super(s);
        }

        public ConnectivityTestFault(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public ConnectivityTestFault(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(org.immunizationsoftware.dqa.tester.connectors.tlep.UnsupportedOperationFault msg){
       faultMessage = msg;
    }
    
    public org.immunizationsoftware.dqa.tester.connectors.tlep.UnsupportedOperationFault getFaultMessage(){
       return faultMessage;
    }
}
    