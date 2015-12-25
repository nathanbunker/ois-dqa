package org.openimmunizationsoftware.dqa.cm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.cm.servlet.pentagon.PentagonBox;
import org.openimmunizationsoftware.dqa.cm.servlet.pentagon.PentagonRow;
import org.openimmunizationsoftware.dqa.tr.logic.PentagonReportLogic;
import org.openimmunizationsoftware.dqa.tr.model.PentagonReport;
import org.openimmunizationsoftware.dqa.tr.model.TestConducted;
import org.openimmunizationsoftware.dqa.tr.model.TestMessage;
import org.openimmunizationsoftware.dqa.tr.model.TestParticipant;

public class PentagonServlet extends HomeServlet
{

  public static final String SHOW_DETAIL_CLOSE_BUTTON = "<a style=\"float: right; display:inline-block; padding: 2px 5px; background:white; \" onclick=\"hideReport('details')\">Close</a>";
  public static final String PARAM_TEST_CONDUCTED_ID = "testConductedId";
  public static final String PARAM_CONNECTION_LABEL = "connectionLabel";
  public static final String PARAM_TEST_MESSAGE_ID = "testMessageId";
  public static final String PARAM_TEST_PARTICIPANT_ID = "testParticipantId";
  public static final String PARAM_COMPARISON_FIELD_ID = "comparisonFieldId";
  public static final String PARAM_PROFILE_USAGE_ID = "profileUsageId";

  private static final String VIEW_CONFORMANCE1 = "c1";

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {
    doGet(req, resp);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {
    HttpSession webSession = setup(req, resp);
    UserSession userSession = (UserSession) webSession.getAttribute(USER_SESSION);
    Session dataSession = userSession.getDataSession();
    PrintWriter out = userSession.getOut();
    if (userSession.getUser() == null || userSession.getUser().getApplicationUser() == null
        || !userSession.getUser().getApplicationUser().getApplication().isApplicationAart())
    {
      sendToHome(req, resp);
      return;
    }
    String view = req.getParameter(PARAM_VIEW);
    if (view == null)
    {
      view = "";
    }
    String connectionLabel = req.getParameter(PARAM_CONNECTION_LABEL);
    if (connectionLabel == null)
    {
      connectionLabel = "";
    }
    TestConducted testConducted = (TestConducted) webSession.getAttribute(ATTRIBUTE_TEST_CONDUCTED);

    int testConnectedId = 0;
    {
      String testConnectedIdString = req.getParameter(PARAM_TEST_CONDUCTED_ID);
      if (testConnectedIdString != null)
      {
        testConnectedId = Integer.parseInt(testConnectedIdString);
      }
      if (testConnectedId != 0)
      {
        testConducted = (TestConducted) dataSession.get(TestConducted.class, testConnectedId);
      }
      if (testConducted != null)
      {
        if (connectionLabel.equals(""))
        {
          connectionLabel = testConducted.getConnectionLabel();
        } else
        {
          if (!testConducted.getConnectionLabel().equals(connectionLabel))
          {
            webSession.removeAttribute(ATTRIBUTE_TEST_CONDUCTED);
            testConducted = null;
          }
        }
      }
    }

    TestParticipant testParticipantSelected = (TestParticipant) webSession.getAttribute(ATTRIBUTE_TEST_PARTICIPANT);
    if (req.getParameter(PARAM_TEST_PARTICIPANT_ID) != null)
    {
      int testParticipantId = Integer.parseInt(req.getParameter(PARAM_TEST_PARTICIPANT_ID));
      testParticipantSelected = (TestParticipant) dataSession.get(TestParticipant.class, testParticipantId);
      connectionLabel = testParticipantSelected.getConnectionLabel();
    }
    if (testParticipantSelected != null)
    {
      webSession.setAttribute(ATTRIBUTE_TEST_PARTICIPANT, testParticipantSelected);
    } else
    {
      webSession.removeAttribute(ATTRIBUTE_TEST_PARTICIPANT);
    }

    if (testConducted != null && testParticipantSelected != null)
    {
      if (!testConducted.getConnectionLabel().equals(testParticipantSelected.getConnectionLabel()))
      {
        testConducted = null;
      }
    }

    if (testConducted != null)
    {
      webSession.setAttribute(ATTRIBUTE_TEST_CONDUCTED, testConducted);
    } else
    {
      webSession.removeAttribute(ATTRIBUTE_TEST_CONDUCTED);
    }

    TestMessage testMessage = null;
    {
      String testMessageIdString = req.getParameter(PARAM_TEST_MESSAGE_ID);
      if (testMessageIdString != null)
      {
        if (!testMessageIdString.equals(""))
        {
          testMessage = (TestMessage) dataSession.get(TestMessage.class, Integer.parseInt(testMessageIdString));
        }
        if (testMessage != null)
        {
          webSession.setAttribute(ATTRIBUTE_TEST_MESSAGE, testMessage);
        } else
        {
          webSession.removeAttribute(ATTRIBUTE_TEST_MESSAGE);
        }
      } else
      {
        testMessage = (TestMessage) webSession.getAttribute(ATTRIBUTE_TEST_MESSAGE);
        if (testMessage != null && testConducted != null && !testMessage.getTestSection().getTestConducted().equals(testConducted))
        {
          webSession.removeAttribute(ATTRIBUTE_TEST_MESSAGE);
          testMessage = null;
        }
      }
    }

    if (testConducted != null && connectionLabel.equals(""))
    {
      connectionLabel = testConducted.getConnectionLabel();
    }

    PentagonReport pentagonReport = PentagonReportLogic.createOrReturnPentagonReport(testConducted, dataSession);

    createHeader(webSession);
    out.println("<svg xmlns=\"http://www.w3.org/2000/svg\"  width=\"850\" height=\"850\">");
    int[] centerPointBig = { 425, 438 };
    int[][] pointsBig = new int[5][2];
    setupPointsBig(pointsBig);
    printPentagon(out, pointsBig, "#cccccc", "2");

    {
      int[][] scorePoints = new int[5][2];
      int[] scores = pentagonReport.getScores();
      {
        for (int i = 0; i < scores.length; i++)
        {
          scorePoints[i][0] = pointsBig[i][0] - ((int) ((pointsBig[i][0] - centerPointBig[0]) * ((100 - scores[i]) / 100.0)));
          scorePoints[i][1] = pointsBig[i][1] - ((int) ((pointsBig[i][1] - centerPointBig[1]) * ((100 - scores[i]) / 100.0)));
        }
        printPentagon(out, scorePoints, "#9b0d28", "2");
      }
    }

    int[] centerPointSmall = { 85, 88 };
    int[][] pointsSmall = new int[5][2];
    setupPointsSmall(pointsSmall);
    printPentagon(out, pointsSmall, "#eeeeee", "0.5");

    {
      int[][] scorePoints = new int[5][2];
      int[] scores = pentagonReport.getScores();
      {
        for (int i = 0; i < scores.length; i++)
        {
          scorePoints[i][0] = pointsSmall[i][0] - ((int) ((pointsSmall[i][0] - centerPointSmall[0]) * ((100 - scores[i]) / 100.0)));
          scorePoints[i][1] = pointsSmall[i][1] - ((int) ((pointsSmall[i][1] - centerPointSmall[1]) * ((100 - scores[i]) / 100.0)));
        }
        printPentagon(out, scorePoints, "#cccccc", "0.5");
      }
    }

    out.println("  <rect fill=\"#83bbe5\" stroke=\"black\" stroke-width=\"2\" x=\"175\" y=\"138\" width=\"500\" height=\"27\"/>");
    out.println("  <rect fill=\"#7ab648\" stroke=\"black\" stroke-width=\"2\" x=\"125\" y=\"150\" width=\"40\" height=\"500\"/>");
    out.println("  <rect fill=\"#fcc438\" stroke=\"black\" stroke-width=\"2\" x=\"685\" y=\"150\" width=\"40\" height=\"500\"/>");
    out.println("  <rect fill=\"#7ab648\" stroke=\"black\" stroke-width=\"2\" x=\"25\" y=\"660\" width=\"488\" height=\"27\"/>");
    out.println("  <rect fill=\"#fcc438\" stroke=\"black\" stroke-width=\"2\" x=\"533\" y=\"660\" width=\"292\" height=\"27\"/>");
    // out.println(
    // " <circle fill=\"#83bbe5\" stroke=\"black\" stroke-width=\"2\" cx=\"" +
    // pointsBig[0][0] + "\" cy=\"" + pointsBig[0][1] + "\" r=\"40\"/>");
    // out.println(
    // " <circle fill=\"#fcc438\" stroke=\"black\" stroke-width=\"2\" cx=\"" +
    // pointsBig[1][0] + "\" cy=\"" + pointsBig[1][1] + "\" r=\"40\"/>");
    // out.println(
    // " <circle fill=\"#fcc438\" stroke=\"black\" stroke-width=\"2\" cx=\"" +
    // pointsBig[2][0] + "\" cy=\"" + pointsBig[2][1] + "\" r=\"40\"/>");
    // out.println(
    // " <circle fill=\"#7ab648\" stroke=\"black\" stroke-width=\"2\" cx=\"" +
    // pointsBig[3][0] + "\" cy=\"" + pointsBig[3][1] + "\" r=\"40\"/>");
    // out.println(
    // " <circle fill=\"#7ab648\" stroke=\"black\" stroke-width=\"2\" cx=\"" +
    // pointsBig[4][0] + "\" cy=\"" + pointsBig[4][1] + "\" r=\"40\"/>");

    PentagonRow pentagonRowConfidence = PentagonRow.createConfidenceRow(pentagonReport);
    PentagonRow pentagonRowsUpdateFunctionality = PentagonRow.createUpdateFunctionalityRow(pentagonReport);
    PentagonRow pentagonRowUpdateConformance = PentagonRow.createUpdateConformanceRow(pentagonReport);
    PentagonRow pentagonRowQueryFunctionality = PentagonRow.createQueryFunctionalityRow(pentagonReport);
    PentagonRow pentagonRowQueryConformance = PentagonRow.createQueryConformanceRow(pentagonReport);

    prepare(pentagonRowConfidence, 500);
    prepare(pentagonRowsUpdateFunctionality, 500);
    prepare(pentagonRowUpdateConformance, 488);
    prepare(pentagonRowQueryFunctionality, 500);
    prepare(pentagonRowQueryConformance, 292);

    {
      int posX = 175;
      int posY = 62;
      int i = 0;
      for (PentagonBox pentagonBox : pentagonRowConfidence)
      {
        out.println("  <rect id=\"r_c" + i + "\" fill=\"#99d2f2\" stroke=\"black\" stroke-width=\"2\" x=\"" + posX + "\" y=\"" + posY + "\" width=\""
            + pentagonBox.getSize() + "\" height=\"76\"/>");
        printScoreChartMini(out, pentagonBox.getScore(), ((int) (pentagonBox.getSize() - 38)) / 2 + posX, posY + 36, 18, "#83bbe5", "#99d2f2");
        posX += pentagonBox.getSize();
        i++;
      }
    }
    {
      int posX = 25;
      int posY = 150;
      int i = 0;
      for (PentagonBox pentagonBox : pentagonRowsUpdateFunctionality)
      {
        out.println("  <rect id=\"r_uf" + i + "\" fill=\"#a3d977\" stroke=\"black\" stroke-width=\"2\" x=\"" + posX + "\" y=\"" + posY
            + "\" width=\"100\" height=\"" + pentagonBox.getSize() + "\"/>");
        printScoreChartMini(out, pentagonBox.getScore(), posX + 31, ((pentagonBox.getSize() - 38) - 2) + posY, 18, "#7ab648", "#a3d977");
        posY += pentagonBox.getSize();
        i++;
      }
    }
    {
      int posX = 25;
      int posY = 688;
      int i = 0;
      for (PentagonBox pentagonBox : pentagonRowUpdateConformance)
      {
        out.println("  <rect id=\"r_uc" + i + "\" fill=\"#a3d977\" stroke=\"black\" stroke-width=\"2\" x=\"" + posX + "\" y=\"" + posY + "\" width=\""
            + pentagonBox.getSize() + "\" height=\"82\"/>");
        printScoreChartMini(out, pentagonBox.getScore(), ((int) (pentagonBox.getSize() - 38)) / 2 + posX, posY + 3, 18, "#7ab648", "#a3d977");
        posX += pentagonBox.getSize();
        i++;
      }
    }
    {
      int posX = 533;
      int posY = 688;
      int i = 0;
      for (PentagonBox pentagonBox : pentagonRowQueryConformance)
      {
        out.println("  <rect id=\"r_qc" + i + "\" fill=\"#ffdf71\" stroke=\"black\" stroke-width=\"2\" x=\"" + posX + "\" y=\"" + posY + "\" width=\""
            + pentagonBox.getSize() + "\" height=\"82\"/>");
        printScoreChartMini(out, pentagonBox.getScore(), ((int) (pentagonBox.getSize() - 38)) / 2 + posX, posY + 3, 18, "#fcc438", "#ffdf71");
        posX += pentagonBox.getSize();
        i++;
      }
    }

    {
      int posX = 725;
      int posY = 150;
      int i = 0;
      for (PentagonBox pentagonBox : pentagonRowQueryFunctionality)
      {
        out.println("  <rect id=\"r_qf" + i + "\" fill=\"#ffdf71\" stroke=\"black\" stroke-width=\"2\" x=\"" + posX + "\" y=\"" + posY
            + "\" width=\"100\" height=\"" + pentagonBox.getSize() + "\"/>");
        printScoreChartMini(out, pentagonBox.getScore(), posX + 31, ((pentagonBox.getSize() - 38) - 2) + posY, 18, "#fcc438", "#ffdf71");
        posY += pentagonBox.getSize();
        i++;
      }
    }

    out.println("</svg>");

    out.println("<script>");
    out.println("  function loadDetails(testMessageId) { ");
    out.println("    var xhttp = new XMLHttpRequest(); ");
    out.println("    xhttp.onreadystatechange = function() { ");
    out.println("      if (xhttp.readyState == XMLHttpRequest.DONE) { ");
    out.println("        if (xhttp.status == 200) { ");
    out.println("          var e = document.getElementById('details');");
    out.println("          e.innerHTML = xhttp.responseText; ");
    out.println("          e.style.display = 'block';");
    out.println("        }");
    out.println("      }");
    out.println("    }");
    String link;
    if (testParticipantSelected.getProfileUsage() == null)
    {
      link = "'pentagonContent?" + PARAM_TEST_MESSAGE_ID + "=' + testMessageId";
    } else
    {
      link = "'pentagonContent?" + PARAM_PROFILE_USAGE_ID + "=" + testParticipantSelected.getProfileUsage().getProfileUsageId() + "&"
          + PARAM_TEST_MESSAGE_ID + "=' + testMessageId";
    }

    out.println("    xhttp.open('GET', " + link + ", true); ");
    out.println("    xhttp.send(null);");
    out.println("  }");
    out.println("  function flashOnGreen(id) { ");
    out.println("    var e = document.getElementById(id); ");
    out.println("    e.style.fill = '#7ab648'");
    out.println("  }");
    out.println("  function flashOffGreen(id) { ");
    out.println("    var e = document.getElementById(id); ");
    out.println("    e.style.fill = '#a3d977'");
    out.println("  }");
    out.println("  function flashOnBlue(id) { ");
    out.println("    var e = document.getElementById(id); ");
    out.println("    e.style.fill = '#83bbe5'");
    out.println("  }");
    out.println("  function flashOffBlue(id) { ");
    out.println("    var e = document.getElementById(id); ");
    out.println("    e.style.fill = '#99d2f2'");
    out.println("  }");
    out.println("  function flashOnOrange(id) { ");
    out.println("    var e = document.getElementById(id); ");
    out.println("    e.style.fill = '#fcc438'");
    out.println("  }");
    out.println("  function flashOffOrange(id) { ");
    out.println("    var e = document.getElementById(id); ");
    out.println("    e.style.fill = '#ffdf71'");
    out.println("  }");
    out.println("  function showReport(id) { ");
    out.println("    hideReport('c0'); ");
    out.println("    hideReport('c1'); ");
    out.println("    hideReport('c2'); ");
    out.println("    hideReport('c3'); ");
    out.println("    hideReport('c4'); ");
    out.println("    hideReport('uf0'); ");
    out.println("    hideReport('uf1'); ");
    out.println("    hideReport('uf2'); ");
    out.println("    hideReport('uf3'); ");
    out.println("    hideReport('uf4'); ");
    out.println("    hideReport('uf5'); ");
    out.println("    hideReport('uf6'); ");
    out.println("    hideReport('uc0'); ");
    out.println("    hideReport('uc1'); ");
    out.println("    hideReport('uc2'); ");
    out.println("    hideReport('uc3'); ");
    out.println("    hideReport('qf0'); ");
    out.println("    hideReport('qf1'); ");
    out.println("    hideReport('qf2'); ");
    out.println("    hideReport('qf3'); ");
    out.println("    hideReport('qf4'); ");
    out.println("    hideReport('qf5'); ");
    out.println("    hideReport('qf5'); ");
    out.println("    hideReport('qc0'); ");
    out.println("    hideReport('qc1'); ");
    out.println("    var e = document.getElementById(id)");
    out.println("    e.style.display = 'block'; ");
    out.println("  }");
    out.println("  function hideReport(id) { ");
    out.println("    var e = document.getElementById(id)");
    out.println("    e.style.display = 'none'; ");
    out.println("  }");
    out.println("</script>");

    int offsetY = 38;
    int offsetX = 10;

    {
      int posX = 0;
      int posY = 0;
      out.println("  <span style=\"position: absolute; top: " + (posY + offsetY) + "px; left: " + (posX + offsetX)
          + "px; width: 850px; \"><h1 class=\"pentagon\">" + testConducted.getConnectionLabel() + "</h1></span>");
    }

    {
      SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
      int posX = 720;
      int posY = 50;
      out.println("  <span style=\"font-size: 16px; text-align: center; position: absolute; top: " + (posY + offsetY) + "px; left: "
          + (posX + offsetX) + "px; width: 100px; height: 50px; border-width: 1px; border-style: solid; \">Report Run<br/>"
          + sdf.format(testConducted.getTestStartedTime()) + "</span>");
    }

    {
      int posX = 350;
      int posY = 137;
      out.println("  <span style=\"font-size: 18px; font-weight: bold; text-align: center; position: absolute; top: " + (posY + offsetY)
          + "px; left: " + (posX + offsetX) + "px; width: 150px; height: 50px; color: #1a4982;\">Confidence</span>");
    }

    {
      int posX = -15;
      int posY = 330;
      out.println(
          "  <span style=\"font-size: 18px; font-weight: bold; text-align: center; position: absolute; top: " + (posY + offsetY) + "px; left: "
              + (posX + offsetX) + "px; width: 300px; height: 50px;  color: #00624f; transform: rotate(90deg); \">Update Functionality</span>");
    }

    {
      int posX = 544;
      int posY = 330;
      out.println(
          "  <span style=\"font-size: 18px; font-weight: bold; text-align: center; position: absolute; top: " + (posY + offsetY) + "px; left: "
              + (posX + offsetX) + "px; width: 300px; height: 50px;  color: #9b0d28; transform: rotate(90deg); \">Query Functionality</span>");
    }

    {
      int posX = 135;
      int posY = 660;
      out.println("  <span style=\"font-size: 18px; font-weight: bold; text-align: center; position: absolute; top: " + (posY + offsetY)
          + "px; left: " + (posX + offsetX) + "px; width: 300px; height: 50px;  color: #00624f;\">Update Conformance</span>");
    }

    {
      int posX = 516;
      int posY = 660;
      out.println("  <span style=\"font-size: 18px; font-weight: bold; text-align: center; position: absolute; top: " + (posY + offsetY)
          + "px; left: " + (posX + offsetX) + "px; width: 300px; height: 50px;  color: #9b0d28;\">Query Conformance</span>");
    }

    {
      int posX = 175;
      int posY = 62;
      int i = 0;
      for (PentagonBox pentagonBox : pentagonRowConfidence)
      {
        out.println("  <span style=\"font-size: 12px; text-align: center; position: absolute; top: " + (posY + offsetY) + "px; left: "
            + (posX + offsetX) + "px; width: " + pentagonBox.getSize() + "px; height: 100px;\" onmouseout=\"flashOffBlue('r_c" + i
            + "')\" onmouseover=\"flashOnBlue('r_c" + i + "')\"  onClick=\"showReport('c" + i + "')\">" + pentagonBox.getLabel() + "</span>");
        posX += pentagonBox.getSize();
        i++;
      }
    }
    {
      int posX = 25;
      int posY = 150;
      int i = 0;
      for (PentagonBox pentagonBox : pentagonRowsUpdateFunctionality)
      {
        out.println("  <span style=\"font-size: 12px; text-align: center; position: absolute; top: " + (posY + offsetY) + "px; left: "
            + (posX + offsetX) + "px; height: " + pentagonBox.getSize() + "px; width: 100px; \" onmouseout=\"flashOffGreen('r_uf" + i
            + "')\" onmouseover=\"flashOnGreen('r_uf" + i + "')\" onClick=\"showReport('uf" + i + "')\">" + pentagonBox.getLabel() + "</span>");
        posY += pentagonBox.getSize();
        i++;
      }
    }
    {
      int posX = 25;
      int posY = 700;
      int i = 0;
      for (PentagonBox pentagonBox : pentagonRowUpdateConformance)
      {
        out.println("  <span style=\"font-size: 12px; text-align: center; position: absolute; top: " + (posY + offsetY) + "px; left: "
            + (posX + offsetX) + "px; width: " + pentagonBox.getSize() + "px; height: 70px;\" onmouseout=\"flashOffGreen('r_uc" + i
            + "')\" onmouseover=\"flashOnGreen('r_uc" + i + "')\" onClick=\"showReport('uc" + i + "')\"><br/><br/>" + pentagonBox.getLabel()
            + "</span>");
        posX += pentagonBox.getSize();
        i++;
      }
    }
    {
      int posX = 538;
      int posY = 700;
      int i = 0;
      for (PentagonBox pentagonBox : pentagonRowQueryConformance)
      {
        out.println("  <span style=\"font-size: 12px; text-align: center; position: absolute; top: " + (posY + offsetY) + "px; left: "
            + (posX + offsetX) + "px; width: " + pentagonBox.getSize() + "px; height: 70px;\" onmouseout=\"flashOffOrange('r_qc" + i
            + "')\" onmouseover=\"flashOnOrange('r_qc" + i + "')\" onClick=\"showReport('qc" + i + "')\"><br/><br/>" + pentagonBox.getLabel()
            + "</span>");
        posX += pentagonBox.getSize();
        i++;
      }
    }
    {
      int posX = 725;
      int posY = 150;
      int i = 0;
      for (PentagonBox pentagonBox : pentagonRowQueryFunctionality)
      {
        out.println("  <span style=\"font-size: 12px; text-align: center; position: absolute; top: " + (posY + offsetY) + "px; left: "
            + (posX + offsetX) + "px; height: " + pentagonBox.getSize() + "px; width: 100px; \" onmouseout=\"flashOffOrange('r_qf" + i
            + "')\" onmouseover=\"flashOnOrange('r_qf" + i + "')\" onClick=\"showReport('qf" + i + "')\">" + pentagonBox.getLabel() + "</span>");
        posY += pentagonBox.getSize();
        i++;
      }
    }

    printContents(webSession, userSession, dataSession, out, testConducted, offsetY, offsetX, "c", pentagonRowConfidence);
    printContents(webSession, userSession, dataSession, out, testConducted, offsetY, offsetX, "uf", pentagonRowsUpdateFunctionality);
    printContents(webSession, userSession, dataSession, out, testConducted, offsetY, offsetX, "uc", pentagonRowUpdateConformance);
    printContents(webSession, userSession, dataSession, out, testConducted, offsetY, offsetX, "qf", pentagonRowQueryFunctionality);
    printContents(webSession, userSession, dataSession, out, testConducted, offsetY, offsetX, "qc", pentagonRowQueryConformance);

    {
      int posX = 0;
      int posY = 50;
      out.println("<div id=\"details\" style=\" position: absolute; top: " + (posY + offsetY) + "px; left: " + (posX + offsetX)
          + "px; height: 700px; width: 850px; background-color: #eeeeee; border-size: 2px; display:none; border-style: solid; border-color: #9b0d28; overflow: auto;\">");
      out.println(SHOW_DETAIL_CLOSE_BUTTON);
      out.println("</div>");
    }

    createFooter(webSession);
  }

  public void printContents(HttpSession webSession, UserSession userSession, Session dataSession, PrintWriter out, TestConducted testConducted,
      int offsetY, int offsetX, String id, PentagonRow pentagonRow)
  {
    int i = 0;
    for (PentagonBox pentagonBox : pentagonRow)
    {
      startDiv(out, id + i, offsetX, offsetY);
      out.println("<h2 class=\"pentagon\">" + pentagonBox.getTitle() + "</h2>");
      out.println("<span style=\"margin-left: 10px; margin-right: 10px; float: left; padding: 0px; width: 100px; height: 115px; \">");
      printScoreChart(out, pentagonBox.getScore());
      out.println("</span>");
      try
      {
        pentagonBox.printDescription(out, dataSession, testConducted, webSession, userSession);
        pentagonBox.printContents(out, dataSession, testConducted, webSession, userSession);
        out.println("<h3 class=\"pentagon\">Score Calculation</h3>");
        pentagonBox.printScoreExplanation(out, dataSession, testConducted, webSession, userSession);
      } catch (Exception e)
      {
        out.println("<p><em>Internal Error, unable to load content</em></p>");
        e.printStackTrace();
      }
      out.println("</div>");
      i++;
    }
  }

  private static final int CIRCLE_RADIUS = 45;
  private static final int PADDING_X = 5;
  private static final int PADDING_Y = 2;

  public void printScoreChart(PrintWriter out, int score)
  {
    out.println("<svg xmlns=\"http://www.w3.org/2000/svg\"  width=\"110\" height=\"117\">");
    if (score == 100)
    {
      out.println("  <circle fill=\"#9b0d28\" stroke=\"black\" stroke-width=\"2\" cx=\"50\" cy=\"46\" r=\"45\"/>");
      out.println("  <text x=\"12\" y=\"52\" font-size=\"14\" fill=\"white\" font-weight=\"bold\">COMPLETE</text>");
      out.println("  <text x=\"38\" y=\"107\" font-size=\"12\">100%</text>");
    } else
    {
      out.println("  <circle fill=\"#cccccc\" stroke=\"black\" stroke-width=\"0.5\" cx=\"" + (CIRCLE_RADIUS + PADDING_X) + "\" cy=\""
          + (CIRCLE_RADIUS + PADDING_Y) + "\" r=\"" + (CIRCLE_RADIUS) + "\"/>");
      if (score > 0)
      {
        int posX = 50 + (int) Math.round((CIRCLE_RADIUS + 1) * Math.sin(Math.toRadians(360.0 * score / 100.0)));
        int posY = 47 - (int) Math.round((CIRCLE_RADIUS + 1) * Math.cos(Math.toRadians(360.0 * score / 100.0)));
        if (score <= 50)
        {
          out.println("   <path d=\"M50,47 L50,1 A47,47 0 0,1 " + posX + "," + posY + " z\" fill=\"#9b0d28\" stroke=\"black\" stroke-width=\"2\"/>");
        } else
        {
          out.println("   <path d=\"M50,47 L50,1 A47,47 0 1,1 " + posX + "," + posY + " z\" fill=\"#9b0d28\" stroke=\"black\" stroke-width=\"2\"/>");
        }
      }
      if (score < 0)
      {
        out.println("   <path d=\"M19,16 L81,78 z\" fill=\"#9b0d28\" stroke=\"black\" stroke-width=\"0.5\"/>");
        out.println("  <text x=\"25\" y=\"107\" font-size=\"12\">Not Run</text>");
      } else
      {
        out.println("  <text x=\"40\" y=\"107\" font-size=\"12\">" + score + "%</text>");
      }
    }
    out.println("</svg>");
  }

  public void printScoreChartMini(PrintWriter out, int score, int cornerX, int cornerY, int radius, String colorPass, String colorFail)
  {
    int padding = 1;
    if (score == 100)
    {
      out.println("  <circle fill=\"" + colorPass + "\" stroke=\"black\" stroke-width=\"0.5\" cx=\"" + (cornerX + radius + padding) + "\" cy=\""
          + (cornerY + radius + padding) + "\" r=\"" + radius + "\"/>");
      out.println("  <text x=\"" + (cornerX + 6) + "\" y=\"" + (cornerY + radius + padding + 2) + "\" font-size=\"10\" fill=\"black\">100%</text>");
    } else
    {
      out.println(" <circle fill=\"" + colorFail + "\" stroke=\"black\" stroke-width=\"0.5\" cx=\"" + (cornerX + radius + padding) + "\" cy=\""
          + (cornerY + radius + padding) + "\" r=\"" + radius + "\"/>");
      if (score > 0)
      {
        int posX = (cornerX + radius + padding) + (int) Math.round(radius * Math.sin(Math.toRadians(360.0 * score / 100.0)));
        int posY = (cornerY + radius + padding) - (int) Math.round(radius * Math.cos(Math.toRadians(360.0 * score / 100.0)));
        if (score <= 50)
        {
          out.println("   <path d=\"M" + (cornerX + radius + padding) + "," + (cornerY + radius + padding) + " L" + (cornerX + radius + padding) + ","
              + (padding + cornerY) + " A" + radius + "," + radius + " 0 0,1 " + posX + "," + posY + " z\" fill=\"" + colorPass
              + "\" stroke=\"black\" stroke-width=\"0.5\"/>");
        } else
        {
          out.println("   <path d=\"M" + (cornerX + radius + padding) + "," + (cornerY + radius + padding) + " L" + (cornerX + radius + padding) + ","
              + (padding + cornerY) + " A" + radius + "," + radius + " 0 1,1 " + posX + "," + posY + " z\" fill=\"" + colorPass
              + "\" stroke=\"black\" stroke-width=\"0.5\"/>");
        }
      } else if (score == 0)
      {
        out.println("  <text x=\"" + (cornerX + 13) + "\" y=\"" + (cornerY + radius + padding + 2) + "\" font-size=\"10\" fill=\"black\">0%</text>");

      } else if (score < 0)
      {
        int posX1 = (cornerX + radius + padding) + (int) Math.round(radius * Math.sin(Math.toRadians(315)));
        int posY1 = (cornerY + radius + padding) - (int) Math.round(radius * Math.cos(Math.toRadians(315)));
        int posX2 = (cornerX + radius + padding) + (int) Math.round(radius * Math.sin(Math.toRadians(135)));
        int posY2 = (cornerY + radius + padding) - (int) Math.round(radius * Math.cos(Math.toRadians(135)));
        out.println(
            " <path d=\"M" + posX1 + "," + posY1 + " L" + posX2 + "," + posY2 + " z\" fill=\"#9b0d28\" stroke=\"black\" stroke-width=\"0.5\"/>");
      }
    }
  }

  public void startDiv(PrintWriter out, String id, int offsetX, int offsetY)
  {
    int posX = 124;
    int posY = 135;

    out.println("<div id=\"" + id + "\" style=\" position: absolute; top: " + (posY + offsetY) + "px; left: " + (posX + offsetX)
        + "px; height: 548px; width: 597px; background-color: #eeeeee; border-size: 2px; display:none; border-style: solid; border-color: #9b0d28; overflow: auto;\">");
  }

  public void setupPointsBig(int[][] points)
  {
    points[0][0] = 425;
    points[0][1] = 175;
    points[1][0] = 676;
    points[1][1] = 357;
    points[2][0] = 580;
    points[2][1] = 650;
    points[3][0] = 270;
    points[3][1] = 650;
    points[4][0] = 175;
    points[4][1] = 357;
  }

  public void setupPointsSmall(int[][] points)
  {
    points[0][0] = 85;
    points[0][1] = 35;
    points[1][0] = 135;
    points[1][1] = 71;
    points[2][0] = 116;
    points[2][1] = 130;
    points[3][0] = 54;
    points[3][1] = 130;
    points[4][0] = 35;
    points[4][1] = 71;
  }

  public void printPentagon(PrintWriter out, int[][] points, String color, String strokeWidth)
  {
    String makePoints = "";
    for (int i = 0; i < points.length; i++)
    {
      makePoints += points[i][0] + "," + points[i][1] + " ";
    }
    out.println("  <polygon fill=\"" + color + "\" stroke=\"black\" stroke-width=\"" + strokeWidth + "\" points=\"" + makePoints
        + "\" onClick=\"showReport('')\"/>");
  }

  private void prepare(PentagonRow pentagonRow, int totalSize)
  {
    int amountLeft = totalSize;
    double total = 0;
    for (PentagonBox pentagonBox : pentagonRow)
    {
      total += pentagonBox.getWeight();
    }

    for (PentagonBox pentagonBox : pentagonRow)
    {
      int size = (int) (totalSize * (pentagonBox.getWeight() / total) + 0.5);
      if (size > amountLeft)
      {
        size = amountLeft;
      }
      amountLeft = amountLeft - size;
      pentagonBox.setSize(size);
    }
  }

}
