package org.openimmunizationsoftware.dqa.service.client;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.4.6-redhat-1
 * 2013-10-23T16:32:43.582-04:00
 * Generated source version: 2.4.6-redhat-1
 * 
 */
@WebServiceClient(name = "DqaService", 
                  wsdlLocation = "http://10.8.11.246:8080/dqa-webservice/DqaService?wsdl",
                  targetNamespace = "http://dqaws.openimmunizationsoftware.org/dqa/") 
public class DqaService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://dqaws.openimmunizationsoftware.org/dqa/", "DqaService");
    public final static QName DqaServiceSOAPImplPort = new QName("http://dqaws.openimmunizationsoftware.org/dqa/", "DqaServiceSOAPImplPort");
    static {
        URL url = null;
        try {
            url = new URL("http://10.8.11.246:8080/dqa-webservice/DqaService?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(DqaService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://10.8.11.246:8080/dqa-webservice/DqaService?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public DqaService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public DqaService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public DqaService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public DqaService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public DqaService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public DqaService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns DqaServiceSOAPImpl
     */
    @WebEndpoint(name = "DqaServiceSOAPImplPort")
    public DqaServiceSOAPImpl getDqaServiceSOAPImplPort() {
        return super.getPort(DqaServiceSOAPImplPort, DqaServiceSOAPImpl.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns DqaServiceSOAPImpl
     */
    @WebEndpoint(name = "DqaServiceSOAPImplPort")
    public DqaServiceSOAPImpl getDqaServiceSOAPImplPort(WebServiceFeature... features) {
        return super.getPort(DqaServiceSOAPImplPort, DqaServiceSOAPImpl.class, features);
    }

}