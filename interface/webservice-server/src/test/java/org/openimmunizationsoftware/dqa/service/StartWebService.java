package org.openimmunizationsoftware.dqa.service;

import javax.xml.namespace.QName;

import org.apache.axis.encoding.ser.BeanSerializer;
import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.bio.SocketConnector;
import org.mortbay.jetty.webapp.WebAppContext;

public class StartWebService {

	public static void main(String[] args) throws Exception {
		Server server = new Server();
		SocketConnector connector = new SocketConnector();
		
		// Set some timeout options to make debugging easier.
		connector.setMaxIdleTime(1000 * 60 * 60);
		connector.setSoLingerTime(-1);
		connector.setPort(8285);
		server.setConnectors(new Connector[] { connector });

		WebAppContext bb = new WebAppContext();
		bb.setServer(server);
		bb.setContextPath("/");
		bb.setWar("src/main/webapp");
		
		// START JMX SERVER
		// MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
		// MBeanContainer mBeanContainer = new MBeanContainer(mBeanServer);
		// server.getContainer().addEventListener(mBeanContainer);
		// mBeanContainer.start();
		
		server.addHandler(bb);
		
		
		org.apache.axis.encoding.ser.BeanSerializer beanSerializer = new org.apache.axis.encoding.ser.BeanSerializer(String.class, new QName("name"));
		org.apache.axis.encoding.Serializer serializer = (org.apache.axis.encoding.Serializer) beanSerializer;
		
		System.out.println("Starting!");
		org.openimmunizationsoftware.dqa.service.DqaServiceSOAPSkeleton.getOperationDescByName("DQA");
		
		try {
			System.out.println(">>> STARTING EMBEDDED JETTY SERVER, PRESS ANY KEY TO STOP");
			server.start();
			System.in.read();
			System.out.println(">>> STOPPING EMBEDDED JETTY SERVER"); 
            // while (System.in.available() == 0) {
			//   Thread.sleep(5000);
			// }
			server.stop();
			server.join();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(100);
		}
	}
}
