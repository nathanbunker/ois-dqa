/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.immunizationsoftware.dqa.tester.connectors;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * 
 * @author nathan
 */
public class SimpleSoapConnector extends Connector
{

  public SimpleSoapConnector(String label, String url) {
    super(label, "SIMPLE SOAP");
    this.url = url;
  }

  @Override
  public String submitMessage(String message, boolean debug) throws Exception
  {
    ClientConnection cc = new ClientConnection();
    cc.setUserId(userid);
    cc.setPassword(password);
    cc.setFacilityId(facilityid);
    cc.setUrl(url);
    String result = "";
    try
    {

      result = sendRequest(message, cc);
    } catch (Exception e)
    {
      return "Unable to relay message, received this error: " + e.getMessage();
    }

    StringBuffer sbuf = new StringBuffer(result.length());
    boolean inTag = false;
    for (char c : result.toCharArray())
    {
      if (c == '<')
      {
        inTag = true;
      } else if (c == '>')
      {
        inTag = false;
      } else if (!inTag)
      {
        sbuf.append(c);
      }
    }
    if (sbuf.length() > 0)
    {
      result = sbuf.toString();
    }
    return result;
  }

  public String sendRequest(String request, ClientConnection conn) throws IOException
  {
    URLConnection urlConn;
    DataOutputStream printout;
    InputStreamReader input = null;
    URL url = new URL(conn.getUrl());
    urlConn = url.openConnection();
    urlConn.setDoInput(true);
    urlConn.setDoOutput(true);
    urlConn.setUseCaches(false);
    urlConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
    urlConn.setRequestProperty("SOAPAction", "\"http://tempuri.org/ExecuteHL7Message\"");
    printout = new DataOutputStream(urlConn.getOutputStream());
    StringWriter stringWriter = new StringWriter();
    PrintWriter out = new PrintWriter(stringWriter);
    out.println("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
    out.println("<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">");
    out.println("  <soap:Body>");
    out.println("    <ExecuteHL7Message xmlns=\"http://tempuri.org/\">");
    out.println("      <userName>" + conn.getUserId() + "</userName>");
    out.println("      <password>" + conn.getPassword() + "</password>");
    out.println("      <flatWire>" + request + "</flatWire>");
    out.println("    </ExecuteHL7Message>");
    out.println("  </soap:Body>");
    out.println("</soap:Envelope>");
    printout.writeBytes(stringWriter.toString());
    printout.flush();
    printout.close();
    input = new InputStreamReader(urlConn.getInputStream());
    StringBuilder response = new StringBuilder();
    BufferedReader in = new BufferedReader(input);
    String line;
    while ((line = in.readLine()) != null)
    {
      response.append(line);
      response.append('\r');
    }
    input.close();
    return response.toString();
  }

  @Override
  public String connectivityTest(String message) throws Exception
  {
    return "Connectivity test not supported for HTTPS POST connections";
  }


  @Override
  protected void makeScriptAdditions(StringBuilder sb)
  {
    // do nothing
  }

  @Override
  protected void setupFields(List<String> fields)
  {
    // do nothing
  }
}
