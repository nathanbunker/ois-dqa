

/**
 * RhapWSUAT_EHR_IIS.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package org.immunizationsoftware.dqa.tester.connectors.nm;

    /*
     *  RhapWSUAT_EHR_IIS java interface
     */

    public interface RhapWSUAT_EHR_IIS {
          

        /**
          * Auto generated method signature
          * submit single message
                    * @param submitSingleMessage0
                
             * @throws org.immunizationsoftware.dqa.tester.connectors.nm.SubmitSingleMessageFault : 
         */

         
                     public org.immunizationsoftware.dqa.tester.connectors.tlep.SubmitSingleMessageResponse submitSingleMessage(

                        org.immunizationsoftware.dqa.tester.connectors.tlep.SubmitSingleMessage submitSingleMessage0)
                        throws java.rmi.RemoteException
             
          ,org.immunizationsoftware.dqa.tester.connectors.nm.SubmitSingleMessageFault;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * submit single message
                * @param submitSingleMessage0
            
          */
        public void startsubmitSingleMessage(

            org.immunizationsoftware.dqa.tester.connectors.tlep.SubmitSingleMessage submitSingleMessage0,

            final org.immunizationsoftware.dqa.tester.connectors.nm.RhapWSUAT_EHR_IISCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        /**
          * Auto generated method signature
          * the connectivity test
                    * @param connectivityTest2
                
             * @throws org.immunizationsoftware.dqa.tester.connectors.nm.ConnectivityTestFault : 
         */

         
                     public org.immunizationsoftware.dqa.tester.connectors.tlep.ConnectivityTestResponse connectivityTest(

                        org.immunizationsoftware.dqa.tester.connectors.tlep.ConnectivityTest connectivityTest2)
                        throws java.rmi.RemoteException
             
          ,org.immunizationsoftware.dqa.tester.connectors.nm.ConnectivityTestFault;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * the connectivity test
                * @param connectivityTest2
            
          */
        public void startconnectivityTest(

            org.immunizationsoftware.dqa.tester.connectors.tlep.ConnectivityTest connectivityTest2,

            final org.immunizationsoftware.dqa.tester.connectors.nm.RhapWSUAT_EHR_IISCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        
       //
       }
    