package org.immunizationsoftware.dqa.mover.install;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.immunizationsoftware.dqa.mover.install.configurations.ConnectionConfiguration;
import org.immunizationsoftware.dqa.tester.connectors.Connector;
import org.immunizationsoftware.dqa.tester.connectors.ConnectorFactory;

public class ConfigureServlet extends ClientServlet
{

  public static final String TEMPLATE_DEFAULT_SOAP = "Default SOAP";
  public static final String TEMPLATE_DEFAULT_POST = "Default POST";
  public static final String TEMPLATE_ASIIS_PROD = "ASIIS Production";
  public static final String TEMPLATE_ASIIS_TEST = "ASIIS Test";

  public static final String[] TEMPLATES = { TEMPLATE_DEFAULT_SOAP, TEMPLATE_DEFAULT_POST, TEMPLATE_ASIIS_PROD, TEMPLATE_ASIIS_TEST };

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {
    HttpSession session = req.getSession(true);
    String folderName = req.getParameter(PrepareServlet.FOLDER_NAME);
    if (folderName != null)
    {
      if (folderName.startsWith("\\") || folderName.startsWith("/"))
      {
        folderName = folderName.substring(1);
      }
      session.setAttribute(PrepareServlet.FOLDER_NAME, folderName);
    }
    String baseDir = req.getParameter(PrepareServlet.BASE_DIR);
    if (baseDir != null)
    {
      if (!baseDir.endsWith("\\") && !baseDir.endsWith("/"))
      {
        if (baseDir.indexOf("/") == -1)
        {
          baseDir = baseDir + "\\";
        } else
        {
          baseDir = baseDir + "/";
        }
      }
      session.setAttribute(PrepareServlet.BASE_DIR, baseDir);
    }
    String templateName = req.getParameter(PrepareServlet.TEMPLATE_NAME);
    if (templateName != null)
    {
      session.setAttribute(PrepareServlet.TEMPLATE_NAME, templateName);
    } else
    {
      templateName = (String) session.getAttribute(PrepareServlet.TEMPLATE_NAME);
    }
    ConnectionConfiguration cc = new ConnectionConfiguration();
    setupConfiguration(templateName, cc);
    String action = req.getParameter("action");
    if (action != null)
    {
      if (action.equals("Step 2: Download and Save"))
      {
        cc.setType(req.getParameter(ConnectionConfiguration.FIELD_TYPE));
        cc.setLabel(req.getParameter(ConnectionConfiguration.FIELD_LABEL));
        cc.setUrl(req.getParameter(ConnectionConfiguration.FIELD_URL));
        String message = null;
        if (cc.getType().equals(""))
        {
          message = "You must select a transport method type in order to create a connection";
        } else if (cc.getLabel().equals(""))
        {
          message = "You must give a label for this connection";
        } else if (cc.getUrl().equals(""))
        {
          message = "You must indicate a " + cc.getUrlLabel() + " for this connection";
        }

        if (message == null)
        {
          try
          {
            Connector connector = ConnectorFactory.getConnector(cc.getType(), cc.getLabel(), cc.getUrl());
            if (connector == null)
            {
              message = "Unrecognized connection type: " + cc.getType();
            } else
            {
              cc.setFacilityid(req.getParameter(ConnectionConfiguration.FIELD_FACILITYID));
              cc.setPassword(req.getParameter(ConnectionConfiguration.FIELD_PASSWORD));
              cc.setUserid(req.getParameter(ConnectionConfiguration.FIELD_USERID));

              connector.setUserid(cc.getUserid());
              connector.setPassword(cc.getPassword());
              connector.setFacilityid(cc.getFacilityid());
              if (cc.isUseridRequired() && connector.getUserid().equals(""))
              {
                message = "You must indicate a " + cc.getUseridLabel();
              } else if (cc.isPasswordRequired() && connector.getPassword().equals(""))
              {
                message = "You must indicate a " + cc.getPasswordLabel();
              } else if (cc.isFacilityidRequired() && connector.getFacilityid().equals(""))
              {
                message = "You must indicate a " + cc.getFacilityidLabel();
              }

              if (message == null)
              {
                resp.setContentType("text/plain;charset=UTF-8");
                resp.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode("smm.config.txt", "UTF-8") + "\"");
                PrintWriter out = new PrintWriter(resp.getOutputStream());
                out.print(connector.getScript());
                out.close();
                return;
              }
            }
          } catch (Exception e)
          {
            message = "Unable to configure connector: " + e.getMessage();
            e.printStackTrace();
          }
        }
        if (message != null)
        {
          req.setAttribute("message", message);
        }
      }
    }
    resp.setContentType("text/html;charset=UTF-8");
    PrintWriter out = new PrintWriter(resp.getOutputStream());
    try
    {
      printHtmlHead(out, "2. Configure", req);

      if (folderName != null)
      {
        cc.setFolderName(folderName);
      }
      if (baseDir != null)
      {
        cc.setBaseDir(baseDir);
      }

      out.println("<h2>Step 2: Configure</h2>");
      cc.printForm(out);
      out.println("<form method=\"InstallServlet\">");
      out.println("    <p>After you have saved <code>smm.config.txt</code> you are ready for <input type=\"submit\" value=\"Step 3: Install\" name=\"action\"></p>");
      out.println("</form>");
      out.println("<h2>Comments</h2>");
      out.println("<p>The information on the form above has been filled out as much as possible and has been simplified to match the requirements of "
          + cc.getReceiverName() + ". To complete this step you will need to:</p>");
      out.println("<ul>");
      out.println("  <li>Contact " + cc.getReceiverName() + " and request access to the system. </li>");
      out.println("  <li>You may be asked to provide your information, complete authorization paperwork, or complete other steps. </li>");
      out.println("  <li>At the end of the process you should be supplied with credentials that you can use in this step.</li>");
      out.println("  <li>Please direct questions about credentials and authentication requirements directly to " + cc.getReceiverName() + ".</li>");
      out.println("</ul>");

      printHtmlFoot(out);
    } catch (Exception e)
    {
      e.printStackTrace();
      out.println("<p>Problem encountered: </p><pre>");
      e.printStackTrace(out);
      out.println("</pre>");
    } finally
    {
      out.close();
    }
  }

  private void setupConfiguration(String templateName, ConnectionConfiguration cc)
  {
    if (templateName != null)
    {
      cc.setLabel(templateName);
      if (templateName.equals(TEMPLATE_DEFAULT_SOAP))
      {
        cc.setType(ConnectorFactory.TYPE_SOAP);
      } else if (templateName.equals(TEMPLATE_DEFAULT_POST))
      {
        cc.setType(ConnectorFactory.TYPE_POST);
      } else if (templateName.equals(TEMPLATE_ASIIS_PROD))
      {
        cc.setType(ConnectorFactory.TYPE_POST);
        cc.setUrl("https://test-asiis.azdhs.gov/HL7Server");
        cc.setFacilityidShow(false);
        cc.setTypeShow(false);
        cc.setInstructions("In order to connect to ASIIS Production you will need to request a Username and Password from the <a href=\"https://www.asiis.state.az.us/\" target=\"_blank\">ASIIS User Support help desk</a>. Please provide the User Id and Password before continuing. ");
        cc.setReceiverName("ASIIS");
        cc.setUseridRequired(true);
        cc.setPasswordRequired(true);
      } else if (templateName.equals(TEMPLATE_ASIIS_TEST))
      {
        cc.setType(ConnectorFactory.TYPE_POST);
        cc.setUrl("https://test-asiis.azdhs.gov/HL7Server");
        cc.setFacilityidShow(false);
        cc.setTypeShow(false);
        cc.setInstructions("In order to connect to ASIIS Test you will need to request a Username and Password from the <a href=\"https://test-asiis.azdhs.gov/\" target=\"_blank\">ASIIS User Support help desk</a>. Please provide the User Id and Password before continuing.");
        cc.setReceiverName("ASIIS");
        cc.setUseridRequired(true);
        cc.setPasswordRequired(true);
      }
    } else
    {
      cc.setInstructions("This is the default configuration with no preset values. If you would like specific instruction based on the system you are working to connect with, please return to Step 1: Prepare and follow the instructions. ");
    }
  }
}
