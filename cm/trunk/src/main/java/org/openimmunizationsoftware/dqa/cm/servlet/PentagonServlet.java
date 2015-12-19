package org.openimmunizationsoftware.dqa.cm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.tr.RecordServletInterface;
import org.openimmunizationsoftware.dqa.tr.logic.PentagonReportLogic;
import org.openimmunizationsoftware.dqa.tr.model.PentagonReport;
import org.openimmunizationsoftware.dqa.tr.model.TestConducted;
import org.openimmunizationsoftware.dqa.tr.model.TestMessage;
import org.openimmunizationsoftware.dqa.tr.model.TestParticipant;
import org.openimmunizationsoftware.dqa.tr.model.Transform;
import org.openimmunizationsoftware.dqa.tr.model.TransportAnalysis;
import org.openimmunizationsoftware.dqa.tr.model.TransportOther;
import org.openimmunizationsoftware.dqa.tr.model.TransportWsdlCdc;

public class PentagonServlet extends HomeServlet
{

  public static final String SHOW_DETAIL_CLOSE_BUTTON = "<a style=\"float: right; display:inline-block; padding: 2px 5px; background:white; \" onclick=\"hideReport('details')\">Close</a>";
  public static final String PARAM_TEST_CONDUCTED_ID = "testConductedId";
  public static final String PARAM_CONNECTION_LABEL = "connectionLabel";
  public static final String PARAM_TEST_MESSAGE_ID = "testMessageId";
  public static final String PARAM_TEST_PARTICIPANT_ID = "testParticipantId";
  public static final String PARAM_COMPARISON_FIELD_ID = "comparisonFieldId";

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

    PentagonBox[] pentagonBoxesConfidence = createPentagonBoxesOfConfidence(pentagonReport);
    PentagonBox[] pentagonBoxesUpdateFunctionality = createPentagonBoxesOfUpdateFunctionality(pentagonReport);
    PentagonBox[] pentagonBoxesUpdateConformance = createPentagonBoxesOfUpdateConformance(pentagonReport);
    PentagonBox[] pentagonBoxesQueryFunctionality = createPentagonBoxesOfQueryFunctionality(pentagonReport);
    PentagonBox[] pentagonBoxesQueryConformance = createPentagonBoxesOfQueryConformance(pentagonReport);

    prepare(pentagonBoxesConfidence, 500);
    prepare(pentagonBoxesUpdateFunctionality, 500);
    prepare(pentagonBoxesUpdateConformance, 488);
    prepare(pentagonBoxesQueryFunctionality, 500);
    prepare(pentagonBoxesQueryConformance, 292);

    {
      int posX = 175;
      int posY = 62;
      int i = 0;
      for (PentagonBox pentagonBox : pentagonBoxesConfidence)
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
      for (PentagonBox pentagonBox : pentagonBoxesUpdateFunctionality)
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
      for (PentagonBox pentagonBox : pentagonBoxesUpdateConformance)
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
      for (PentagonBox pentagonBox : pentagonBoxesQueryConformance)
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
      for (PentagonBox pentagonBox : pentagonBoxesQueryFunctionality)
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
    out.println("    xhttp.open('GET', 'pentagonContent?" + PARAM_TEST_MESSAGE_ID + "=' + testMessageId, true); ");
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
      for (PentagonBox pentagonBox : pentagonBoxesConfidence)
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
      for (PentagonBox pentagonBox : pentagonBoxesUpdateFunctionality)
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
      for (PentagonBox pentagonBox : pentagonBoxesUpdateConformance)
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
      for (PentagonBox pentagonBox : pentagonBoxesQueryConformance)
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
      for (PentagonBox pentagonBox : pentagonBoxesQueryFunctionality)
      {
        out.println("  <span style=\"font-size: 12px; text-align: center; position: absolute; top: " + (posY + offsetY) + "px; left: "
            + (posX + offsetX) + "px; height: " + pentagonBox.getSize() + "px; width: 100px; \" onmouseout=\"flashOffOrange('r_qf" + i
            + "')\" onmouseover=\"flashOnOrange('r_qf" + i + "')\" onClick=\"showReport('qf" + i + "')\">" + pentagonBox.getLabel() + "</span>");
        posY += pentagonBox.getSize();
        i++;
      }
    }

    printContents(webSession, userSession, dataSession, out, testConducted, offsetY, offsetX, "c", pentagonBoxesConfidence);
    printContents(webSession, userSession, dataSession, out, testConducted, offsetY, offsetX, "uf", pentagonBoxesUpdateFunctionality);
    printContents(webSession, userSession, dataSession, out, testConducted, offsetY, offsetX, "uc", pentagonBoxesUpdateConformance);
    printContents(webSession, userSession, dataSession, out, testConducted, offsetY, offsetX, "qf", pentagonBoxesQueryFunctionality);
    printContents(webSession, userSession, dataSession, out, testConducted, offsetY, offsetX, "qc", pentagonBoxesQueryConformance);

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
      int offsetY, int offsetX, String id, PentagonBox[] pbs)
  {
    int i = 0;
    for (PentagonBox pentagonBox : pbs)
    {
      startDiv(out, id + i, offsetX, offsetY);
      out.println("<h2 class=\"pentagon\">" + pentagonBox.getTitle() + "</h2>");
      out.println("<span style=\"margin-left: 10px; margin-right: 10px; float: left; padding: 0px; width: 100px; height: 115px; \">");
      printScoreChart(out, pentagonBox.getScore());
      out.println("</span>");
      pentagonBox.printDescription(out, dataSession, testConducted, webSession, userSession);
      pentagonBox.printContents(out, dataSession, testConducted, webSession, userSession);
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

  private void prepare(PentagonBox[] pentagonBoxes, int totalSize)
  {
    int amountLeft = totalSize;
    double total = 0;
    for (PentagonBox pentagonBox : pentagonBoxes)
    {
      total += pentagonBox.getWeight();
    }

    for (PentagonBox pentagonBox : pentagonBoxes)
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

  private abstract class PentagonBox
  {
    String label = "";
    String title = "";
    int weight = 0;
    int posX = 0;
    int poxY = 0;
    int size = 0;
    int score = 0;

    public int getScore()
    {
      return score;
    }

    public void setScore(int score)
    {
      this.score = score;
    }

    public String getTitle()
    {
      return title;
    }

    public void setTitle(String title)
    {
      this.title = title;
    }

    public int getSize()
    {
      return size;
    }

    public void setSize(int size)
    {
      this.size = size;
    }

    public int getPosX()
    {
      return posX;
    }

    public void setPosX(int posX)
    {
      this.posX = posX;
    }

    public int getPoxY()
    {
      return poxY;
    }

    public void setPoxY(int poxY)
    {
      this.poxY = poxY;
    }

    public int getWeight()
    {
      return weight;
    }

    public void setWeight(int weight)
    {
      this.weight = weight;
    }

    public String getLabel()
    {
      return label;
    }

    public void setLabel(String label)
    {
      this.label = label;
    }

    public abstract void printContents(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession,
        UserSession userSession);

    public abstract void printDescription(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession,
        UserSession userSession);

    public void printTestMessageListFail(PrintWriter out, List<TestMessage> testMessageList)
    {
      out.println("<ul>");
      for (TestMessage testMessage : testMessageList)
      {
        out.println("<li><a class=\"pentagonTestMessageFail\" onclick=\"loadDetails('" + testMessage.getTestMessageId() + "');\">"
            + testMessage.getTestCaseDescription() + "</a></li>");
      }
      out.println("</ul>");
    }

    public void printTestMessageListPass(PrintWriter out, List<TestMessage> testMessageList)
    {
      out.println("<ul>");
      for (TestMessage testMessage : testMessageList)
      {
        out.println("<li><a class=\"pentagonTestMessagePass\" onclick=\"loadDetails('" + testMessage.getTestMessageId() + "');\">"
            + testMessage.getTestCaseDescription() + "</a></li>");
      }
      out.println("</ul>");
    }

  }

  private PentagonBox[] createPentagonBoxesOfConfidence(PentagonReport pentagonReport)
  {

    PentagonBox[] pb = new PentagonBox[5];
    pb[0] = new PentagonBox() {
      @Override
      public void printDescription(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
      {
        out.println("<p class=\"pentagon\">Acknowledgment messages returned should conform to the CDC Implementation Guide and HL7 requirements. "
            + "Non-conformant messages may be incorrectly read by the testing system. "
            + "Confidence in the results of this report increases when acknowledgments are properly formatted. "
            + "Conformance is verified by NIST software.</p> ");
      }

      @Override
      public void printContents(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
      {
        // TODO Auto-generated method stub
      }

    };

    pb[1] = new PentagonBox() {
      @Override
      public void printContents(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
      {
        if (score < 100)
        {
          out.println("<h3 class=\"pentagon\">Fail - Good Message Was Not Accepted</h3>");
          Query query = dataSession.createQuery(
              "from TestMessage where testSection.testSectionType = ? and testSection.testConducted = ? and resultStatus = 'FAIL' and testType = 'update' order by testCaseCategory");
          query.setParameter(0, RecordServletInterface.VALUE_TEST_SECTION_TYPE_BASIC);
          query.setParameter(1, testConducted);
          List<TestMessage> testMessageList = query.list();
          printTestMessageListFail(out, testMessageList);
        }
        if (score > 0)
        {
          out.println("<h3 class=\"pentagon\">Pass - Good Message Was Accepted</h3>");
          Query query = dataSession.createQuery(
              "from TestMessage where testSection.testSectionType = ? and testSection.testConducted = ? and resultStatus = 'PASS' and testType = 'update' order by testCaseCategory");
          query.setParameter(0, RecordServletInterface.VALUE_TEST_SECTION_TYPE_BASIC);
          query.setParameter(1, testConducted);
          List<TestMessage> testMessageList = query.list();
          printTestMessageListPass(out, testMessageList);
        }
      }

      @Override
      public void printDescription(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
      {
        if (score == 100)
        {
          out.println("<p class=\"pentagon\">All NIST 2014 test messages were accepted, as was expected. "
              + "Accepting all of these standard IIS messages increase confidence when reading the results of the rest "
              + "of the testing process. </p> <br/><br/><br/>");
        } else
        {
          out.println("<p class=\"pentagon\">Properly formatted and complete messages should be accepted by the IIS. "
              + "This measurement starts with the assumption that the IIS can accept good messages "
              + "and considers success when the IIS indicates the message was accepted.  Criteria for "
              + "determining whether a messages was accepted or not is determined by the configuration " + "used to connect to the IIS. </p> ");
        }

      }
    };

    pb[2] = new PentagonBox() {
      @Override
      public void printContents(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
      {
        if (score < 100)
        {
          out.println("<h3 class=\"pentagon\">Fail - Bad Message Was Accepted</h3>");
          Query query = dataSession.createQuery(
              "from TestMessage where testSection.testSectionType = ? and testSection.testConducted = ? and resultStatus = 'FAIL' and testType = 'update' order by testCaseCategory");
          query.setParameter(0, RecordServletInterface.VALUE_TEST_SECTION_TYPE_NOT_ACCEPTED);
          query.setParameter(1, testConducted);
          List<TestMessage> testMessageList = query.list();
          printTestMessageListFail(out, testMessageList);
        }
        if (score > 0)
        {
          out.println("<h3 class=\"pentagon\">Pass - Bad Message Was Not Accepted</h3>");
          Query query = dataSession.createQuery(
              "from TestMessage where testSection.testSectionType = ? and testSection.testConducted = ? and resultStatus = 'PASS' and testType = 'update' order by testCaseCategory");
          query.setParameter(0, RecordServletInterface.VALUE_TEST_SECTION_TYPE_NOT_ACCEPTED);
          query.setParameter(1, testConducted);
          List<TestMessage> testMessageList = query.list();
          printTestMessageListPass(out, testMessageList);
        }
      }

      @Override
      public void printDescription(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
      {
        if (score == 100)
        {
          out.println(
              "<p class=\"pentagon\">All clearly bad messages were not accepted by the IIS. This means that the IIS identified the issue, messaged it back "
                  + "for this report, and this report was able to understand that there was a problem. This means that this report can place "
                  + "higher confidence on the status of data submitted based on the acknowledgment message. </p>");
        } else
        {
          out.println(
              "<p class=\"pentagon\">Messages that have severe data quality or format issues that should prevent them from being read and properly saved by most IIS "
                  + "are considered to be bad messages. While there may be some exceptions in certain cases most IIS should be identifying and "
                  + "acknowledging the problems in these messages when replying. This score reflects the ability of the IIS to identify and communicate "
                  + "issues that are present in bad messages or the ability of this report to understanda the not accept message from the IIS.</p> ");
        }
      }
    };

    pb[3] = new PentagonBox() {
      @Override
      public void printDescription(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
      {
        out.println(
            "<p class=\"pentagon\">Messages that were accepted by the IIS should return that data when queried. This test verifies that patient "
                + "was accepted and that all the vaccinations submitted are returned. It does not check every field submitted, rather "
                + "it verifies basic patient information is returned and that basic vaccination information was returned. "
                + "This measure helps to determine if the positive acknowledgement is accurate or not. </p> ");
      }

      @Override
      public void printContents(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
      {
        if (score < 100)
        {
          out.println("<h3 class=\"pentagon\">Fail - Data NOT Returned for Accepted Message</h3>");
          Query query = dataSession.createQuery(
              "from TestMessage where (testSection.testSectionType = ? or testSection.testSectionType = ?) and testSection.testConducted = ? "
                  + "and resultStoreStatus = 'a-nr' and testType = 'query' order by testCaseCategory");
          query.setParameter(0, RecordServletInterface.VALUE_TEST_SECTION_TYPE_BASIC);
          query.setParameter(1, RecordServletInterface.VALUE_TEST_SECTION_TYPE_NOT_ACCEPTED);
          query.setParameter(2, testConducted);
          List<TestMessage> testMessageList = query.list();
          printTestMessageListFail(out, testMessageList);
        }
        if (score > 0)
        {
          out.println("<h3 class=\"pentagon\">Pass - Data Returned for Accepted Message</h3>");
          Query query = dataSession.createQuery(
              "from TestMessage where (testSection.testSectionType = ? or testSection.testSectionType = ?) and testSection.testConducted = ? "
                  + "and resultStoreStatus = 'a-r' and testType = 'query' order by testCaseCategory");
          query.setParameter(0, RecordServletInterface.VALUE_TEST_SECTION_TYPE_BASIC);
          query.setParameter(1, RecordServletInterface.VALUE_TEST_SECTION_TYPE_NOT_ACCEPTED);
          query.setParameter(2, testConducted);
          List<TestMessage> testMessageList = query.list();
          printTestMessageListPass(out, testMessageList);
        }
      }
    };

    pb[4] = new PentagonBox() {
      @Override
      public void printDescription(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
      {
        out.println("<p class=\"pentagon\">Messages that were NOT accepted by the IIS are not expected to return that all data when queried. "
            + " This test expects that something important, like the a vaccine or patient information is not stored in the IIS. "
            + "This test has practical limitations as an IIS may indicate a message was not accepted, but accept it anyways. "
            + "However, doing this reduces the confidence in the meaning of an acknowledgement. Ideally an IIS "
            + "should use the ACK to provide a view of what data was really accept.  </p> ");
      }

      @Override
      public void printContents(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
      {
        if (score < 100)
        {
          out.println("<h3 class=\"pentagon\">Fail - Data Returned for NOT Accepted Message</h3>");
          Query query = dataSession.createQuery(
              "from TestMessage where (testSection.testSectionType = ? or testSection.testSectionType = ?) and testSection.testConducted = ? "
                  + "and resultStoreStatus = 'na-r' and testType = 'query' order by testCaseCategory");
          query.setParameter(0, RecordServletInterface.VALUE_TEST_SECTION_TYPE_BASIC);
          query.setParameter(1, RecordServletInterface.VALUE_TEST_SECTION_TYPE_NOT_ACCEPTED);
          query.setParameter(2, testConducted);
          List<TestMessage> testMessageList = query.list();
          printTestMessageListFail(out, testMessageList);
        }
        if (score > 0)
        {
          out.println("<h3 class=\"pentagon\">Pass - Data NOT Returned for NOT Accepted Message</h3>");
          Query query = dataSession.createQuery(
              "from TestMessage where (testSection.testSectionType = ? or testSection.testSectionType = ?) and testSection.testConducted = ? "
                  + "and resultStoreStatus = 'na-nr' and testType = 'query' order by testCaseCategory");
          query.setParameter(0, RecordServletInterface.VALUE_TEST_SECTION_TYPE_BASIC);
          query.setParameter(1, RecordServletInterface.VALUE_TEST_SECTION_TYPE_NOT_ACCEPTED);
          query.setParameter(2, testConducted);
          List<TestMessage> testMessageList = query.list();
          printTestMessageListPass(out, testMessageList);
        }
      }
    };

    pb[0].setTitle("ACKs Conform to NIST 2015");
    pb[1].setTitle("Good Messages Accepted");
    pb[2].setTitle("Bad Messages Not Accepted");
    pb[3].setTitle("Good Data Stored");
    pb[4].setTitle("Bad Data Not Stored");

    pb[0].setLabel("ACKs Conform to NIST 2015");
    pb[1].setLabel("Good Messages <br/>Accepted");
    pb[2].setLabel("Bad Messages <br/>Not Accepted");
    pb[3].setLabel("Good Data <br/>Stored");
    pb[4].setLabel("Bad Data <br/>Not Stored");

    pb[0].setWeight(20);
    pb[1].setWeight(20);
    pb[2].setWeight(20);
    pb[3].setWeight(20);
    pb[4].setWeight(20);

    pb[0].setScore(pentagonReport.getScoreCAckConform());
    pb[1].setScore(pentagonReport.getScoreCGoodMessages());
    pb[2].setScore(pentagonReport.getScoreCBadMessages());
    pb[3].setScore(pentagonReport.getScoreCGoodData());
    pb[4].setScore(pentagonReport.getScoreCBadData());

    return pb;
  }

  private PentagonBox[] createPentagonBoxesOfUpdateFunctionality(PentagonReport pentagonReport)
  {

    PentagonBox[] pb = new PentagonBox[7];

    pb[0] = new PentagonBox() {
      @Override
      public void printDescription(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
      {
        if (score == 0)
        {
          out.println("<p class=\"pentagon\">NIST 2014 certified systems must be able to create seven test messages in order to pass certification. "
              + "NIST created these messages by carefully reading the requirements in the CDC Implementation Guide release 1.4. IIS should "
              + "be prepared to receive all of these messages. <br/><br/> </p>");
        } else if (score == 100)
        {
          out.println("<p class=\"pentagon\">NIST 2014 certified systems must be able to create seven test messages in order to pass certification. "
              + "NIST created these messages by carefully reading the requirements in the CDC Implementation Guide release 1.4. IIS can"
              + "process these messages. <br/><br/> </p>");
        } else
        {
          out.println("<p class=\"pentagon\">NIST 2014 certified systems must be able to create seven test messages in order to pass certification. "
              + "NIST created these messages by carefully reading the requirements in the CDC Implementation Guide release 1.4. IIS should "
              + "be prepared to receive all of these messages. Some IIS may not be able to process all the information (e.g. Varicella "
              + "history-of-disease, refusuals) but should at the very least accept the other data in the message and not return a hard"
              + "error. </p>");
        }

      }

      @Override
      public void printContents(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
      {
        if (score < 100)
        {
          out.println("<h3 class=\"pentagon\">Fail - NIST 2014 Example Was Not Accepted</h3>");
          Query query = dataSession.createQuery(
              "from TestMessage where testSection.testSectionType = ? and testSection.testConducted = ? and resultStatus = 'FAIL' and testType = 'update' order by testCaseCategory");
          query.setParameter(0, RecordServletInterface.VALUE_TEST_SECTION_TYPE_BASIC);
          query.setParameter(1, testConducted);
          List<TestMessage> testMessageList = query.list();
          printTestMessageListFail(out, testMessageList);
        }
        if (score > 0)
        {
          out.println("<h3 class=\"pentagon\">Pass - NIST 2014 Example Was Accepted</h3>");
          Query query = dataSession.createQuery(
              "from TestMessage where testSection.testSectionType = ? and testSection.testConducted = ? and resultStatus = 'PASS' and testType = 'update' order by testCaseCategory");
          query.setParameter(0, RecordServletInterface.VALUE_TEST_SECTION_TYPE_BASIC);
          query.setParameter(1, testConducted);
          List<TestMessage> testMessageList = query.list();
          printTestMessageListPass(out, testMessageList);
        }
      }
    };

    pb[1] = new PentagonBox() {
      @Override
      public void printDescription(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
      {
        if (score == 0)
        {
          out.println("<p class=\"pentagon\">NIST 2015 certified systems must be able to create seven test messages in order to pass certification. "
              + "NIST created these messages by carefully reading the requirements in the CDC Implementation Guide release 1.4. IIS should "
              + "be prepared to receive all of these messages. These messages are new and not all IIS yet support them. They are included "
              + "here in order for IIS to see what changes may need to be made to support these new messages. <br/><br/> </p>");
        } else if (score == 100)
        {
          out.println("<p class=\"pentagon\">NIST 2015 certified systems must be able to create seven test messages in order to pass certification. "
              + "NIST created these messages by carefully reading the requirements in the CDC Implementation Guide release 1.4. IIS can"
              + "process these messages. <br/><br/> </p>");
        } else
        {
          out.println("<p class=\"pentagon\">NIST 2015 certified systems must be able to create seven test messages in order to pass certification. "
              + "NIST created these messages by carefully reading the requirements in the CDC Implementation Guide release 1.4. IIS should "
              + "be prepared to receive all of these messages. Some IIS may not be able to process all the information (e.g. Varicella "
              + "history-of-disease, refusuals) but should at the very least accept the other data in the message and not return a hard"
              + "error. </p>");
        }

      }

      @Override
      public void printContents(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
      {
        if (score < 100)
        {
          out.println("<h3 class=\"pentagon\">Fail - NIST 2015 VXU Example Was Not Accepted</h3>");
          Query query = dataSession.createQuery(
              "from TestMessage where testSection.testSectionType = ? and testSection.testConducted = ? and resultStatus = 'FAIL' and testType = 'update' order by testCaseCategory");
          query.setParameter(0, RecordServletInterface.VALUE_TEST_SECTION_TYPE_ONC_2015);
          query.setParameter(1, testConducted);
          List<TestMessage> testMessageList = query.list();
          printTestMessageListFail(out, testMessageList);
        }
        if (score > 0)
        {
          out.println("<h3 class=\"pentagon\">Pass - NIST 2015 VXU Example Was Accepted</h3>");
          Query query = dataSession.createQuery(
              "from TestMessage where testSection.testSectionType = ? and testSection.testConducted = ? and resultStatus = 'PASS' and testType = 'update' order by testCaseCategory");
          query.setParameter(0, RecordServletInterface.VALUE_TEST_SECTION_TYPE_ONC_2015);
          query.setParameter(1, testConducted);
          List<TestMessage> testMessageList = query.list();
          printTestMessageListPass(out, testMessageList);
        }
      }
    };

    pb[2] = new PentagonBox() {
      @Override
      public void printDescription(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
      {
        out.println("<p class=\"pentagon\">IIS must be ensuring that received messages meet basic standards by performing basic checks before "
            + "accepting incoming data. Problems that are found should be messaged back to the sender in the acknowledgement message. "
            + "Doing so gives sending systems the opportunity to correct mistakes and resend. This test can also help confirm that the IIS"
            + "is not vulnerable to the receipt of bad data. Success is measured by noting if the introduction of the issue results"
            + "in a substantive change in the acknowledgment message. Due to the different ways IIS message back issues, this measure is "
            + "somewhat crude and should not be depended on to absolutely establish that an IIS is sensitive to any particular issue.  </p>");
      }

      @Override
      public void printContents(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
      {
        // TODO Auto-generated method stub
      }
    };

    pb[3] = new PentagonBox() {
      @Override
      public void printDescription(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
      {
        out.println("<p class=\"pentagon\">The CDC Implementation Guide defines coded values that must be supported by conformant systems. "
            + "This section tests whether these coded values can be placed in messages without causing messages to NOT be accepted. "
            + "It is important to note that the IIS is not being tested to see if the actual coded value is stored and supported by the IIS "
            + "but rather that a sending system may use this code when submitting data. </p>");
      }

      @Override
      public void printContents(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
      {
        if (score < 100)
        {
          out.println("<h3 class=\"pentagon\">Fail - Message Was Not Accepted</h3>");
          Query query = dataSession.createQuery(
              "from TestMessage where testSection.testSectionType = ? and testSection.testConducted = ? and resultStatus = 'FAIL' order by testCaseCategory");
          query.setParameter(0, RecordServletInterface.VALUE_TEST_SECTION_TYPE_INTERMEDIATE);
          query.setParameter(1, testConducted);
          List<TestMessage> testMessageList = query.list();
          printTestMessageListFail(out, testMessageList);
        }
        if (score > 0)
        {
          out.println("<h3 class=\"pentagon\">Pass - Message Was Accepted</h3>");
          Query query = dataSession.createQuery(
              "from TestMessage where testSection.testSectionType = ? and testSection.testConducted = ? and resultStatus = 'PASS' order by testCaseCategory");
          query.setParameter(0, RecordServletInterface.VALUE_TEST_SECTION_TYPE_INTERMEDIATE);
          query.setParameter(1, testConducted);
          List<TestMessage> testMessageList = query.list();
          printTestMessageListPass(out, testMessageList);
        }
      }
    };

    pb[4] = new PentagonBox() {
      @Override
      public void printDescription(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
      {
        out.println("<p class=\"pentagon\">HL7 requires that receiving systems, such as IIS, give a certain amount of tolerance to received message. "
            + "Ideally IIS would always receive perfect and consistent messages, but when it does not, it should still try its best to process"
            + "messages with minor issues. The test examples in section all have minor issues that do not directly affect data quality and which "
            + "IIS would be expected to cope with. Still this test does not assert that IIS ought to accept every tolerant message proposed here. "
            + "A few of these messages may not be accepted by IIS for good reasons. IIS should review closely the ones they do not accept and "
            + "determine if is possible to be tolerant of these issues. </p>");
      }

      @Override
      public void printContents(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
      {
        if (score < 100)
        {
          out.println("<h3 class=\"pentagon\">Fail - Message Was Not Accepted</h3>");
          Query query = dataSession.createQuery(
              "from TestMessage where testSection.testSectionType = ? and testSection.testConducted = ? and resultStatus = 'FAIL' and testCaseDescription like ? order by testCaseCategory");
          query.setParameter(0, RecordServletInterface.VALUE_TEST_SECTION_TYPE_EXCEPTIONAL);
          query.setParameter(1, testConducted);
          query.setParameter(2, RecordServletInterface.VALUE_EXCEPTIONAL_PREFIX_TOLERANCE_CHECK + "%");
          List<TestMessage> testMessageList = query.list();
          printTestMessageListFail(out, testMessageList);
        }
        if (score > 0)
        {
          out.println("<h3 class=\"pentagon\">Pass - Message Was Accepted</h3>");
          Query query = dataSession.createQuery(
              "from TestMessage where testSection.testSectionType = ? and testSection.testConducted = ? and resultStatus = 'PASS' and testCaseDescription like ?  order by testCaseCategory");
          query.setParameter(0, RecordServletInterface.VALUE_TEST_SECTION_TYPE_EXCEPTIONAL);
          query.setParameter(1, testConducted);
          query.setParameter(2, RecordServletInterface.VALUE_EXCEPTIONAL_PREFIX_TOLERANCE_CHECK + "%");
          List<TestMessage> testMessageList = query.list();
          printTestMessageListPass(out, testMessageList);
        }
      }
    };

    pb[5] = new PentagonBox() {
      @Override
      public void printDescription(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
      {
        out.println(
            "<p class=\"pentagon\">Certified EHR systems are able to produce messages that meet NIST standards, but may contain minor variations "
                + "that are not seen in the NIST examples. This section contains examples from EHR systems. While an attempt has been made to ensure that "
                + "these are good messages, this test does not assert that an IIS ought to be able accept all of these. Rather an IIS should use "
                + "this test to help find issues that were not identified in any of the other testing scenarios. In addition this test list will "
                + "grow as additional examples are found.  </p>");

      }

      @Override
      public void printContents(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
      {
        if (score < 100)
        {
          out.println("<h3 class=\"pentagon\">Fail - EHR Example Was Not Accepted</h3>");
          Query query = dataSession.createQuery(
              "from TestMessage where testSection.testSectionType = ? and testSection.testConducted = ? and resultStatus = 'FAIL' and testCaseDescription like ? order by testCaseCategory");
          query.setParameter(0, RecordServletInterface.VALUE_TEST_SECTION_TYPE_EXCEPTIONAL);
          query.setParameter(1, testConducted);
          query.setParameter(2, RecordServletInterface.VALUE_EXCEPTIONAL_PREFIX_CERTIFIED_MESSAGE + "%");
          List<TestMessage> testMessageList = query.list();
          printTestMessageListFail(out, testMessageList);
        }
        if (score > 0)
        {
          out.println("<h3 class=\"pentagon\">Pass - EHR Example Was Accepted</h3>");
          Query query = dataSession.createQuery(
              "from TestMessage where testSection.testSectionType = ? and testSection.testConducted = ? and resultStatus = 'PASS' and testCaseDescription like ?  order by testCaseCategory");
          query.setParameter(0, RecordServletInterface.VALUE_TEST_SECTION_TYPE_EXCEPTIONAL);
          query.setParameter(1, testConducted);
          query.setParameter(2, RecordServletInterface.VALUE_EXCEPTIONAL_PREFIX_CERTIFIED_MESSAGE + "%");
          List<TestMessage> testMessageList = query.list();
          printTestMessageListPass(out, testMessageList);
        }
      }
    };

    pb[6] = new PentagonBox() {
      @Override
      public void printDescription(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
      {
        out.println(
            "<p class=\"pentagon\">Ideally IIS should respond quickly to updates received. Rapid response reduces transmission times for large amounts"
                + "of data and provides good support for systems as they increase their integration with IIS. "
                + "This report includes a performance measure mostly because the information is available when testing and it has some "
                + "impact on the perception of how well the IIS is working. However, please note that performance makes a very small "
                + "contribution to the overall score.   </p>");
      }

      @Override
      public void printContents(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
      {
        int average = (int) (((double) testConducted.getPerUpdateTotal()) / testConducted.getPerUpdateCount() + 0.5);
        out.println("<h3>Response Time</h3>");
        out.println("<ul>");
        out.println("  <li>Average: " + TestReportServlet.createTime(average) + "</li>");
        out.println("  <li>Minimum: " + TestReportServlet.createTime(testConducted.getPerUpdateMin()) + "</li>");
        out.println("  <li>Maximum: " + TestReportServlet.createTime(testConducted.getPerUpdateMax()) + "</li>");
        out.println("  <li>Std Dev: " + TestReportServlet.createTime(testConducted.getPerUpdateStd()) + "</li>");
        out.println("</ul>");
      }
    };

    pb[0].setTitle("NIST 2014 VXU Accepted");
    pb[1].setTitle("NIST 2015 VXU Accepted");
    pb[2].setTitle("Sensitive");
    pb[3].setTitle("Coded Values");
    pb[4].setTitle("Tolerant");
    pb[5].setTitle("EHR Examples");
    pb[6].setTitle("Performance");

    pb[0].setLabel("NIST 2014<br/>VXU Accepted");
    pb[1].setLabel("NIST 2015<br/>VXU Accepted");
    pb[2].setLabel("Sensitive");
    pb[3].setLabel("Coded Values");
    pb[4].setLabel("Tolerant");
    pb[5].setLabel("EHR Examples");
    pb[6].setLabel("Performance");

    pb[0].setWeight(20);
    pb[1].setWeight(20);
    pb[2].setWeight(12);
    pb[3].setWeight(12);
    pb[4].setWeight(12);
    pb[5].setWeight(12);
    pb[6].setWeight(12);

    pb[0].setScore(pentagonReport.getScoreUFVxu2014());
    pb[1].setScore(pentagonReport.getScoreUFVxu2015());
    pb[2].setScore(pentagonReport.getScoreUFSensitive());
    pb[3].setScore(pentagonReport.getScoreUFCodedValues());
    pb[4].setScore(pentagonReport.getScoreUFTolerant());
    pb[5].setScore(pentagonReport.getScoreUFEhrExamples());
    pb[6].setScore(pentagonReport.getScoreUFPerformance());

    return pb;
  }

  private PentagonBox[] createPentagonBoxesOfUpdateConformance(PentagonReport pentagonReport)
  {

    PentagonBox[] pb = new PentagonBox[4];

    pb[0] = new PentagonBox() {
      @Override
      public void printDescription(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
      {
        out.println("<p class=\"pentagon\">Modifications are changes made to each test message to meet local IIS standards. Some of these"
            + "modifications are for fields that would be expected to be changed, such as identification parameters in the MSH segment. "
            + "Others are to meet local requirements for form or content. Thus these modifications are identified as either"
            + "expected or unexpected modifications. IIS should work to reduce the unexpected modifications as much as possible as "
            + "these are likely to be off-standard or at the very least a difference from the national implementation guide.   </p>");
      }

      @Override
      public void printContents(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
      {
        List<Transform> transformExpected = null;
        {
          Query query = dataSession
              .createQuery("from Transform where testConducted = ? and transformField.transformExpected = ? order by transformField.transformText");
          query.setParameter(0, testConducted);
          query.setParameter(1, true);
          transformExpected = query.list();
        }
        List<Transform> transformCustom = null;
        {
          Query query = dataSession
              .createQuery("from Transform where testConducted = ? and transformField.transformExpected = ? order by transformField.transformText");
          query.setParameter(0, testConducted);
          query.setParameter(1, false);
          transformCustom = query.list();
        }
        if (transformExpected.size() == 0 && transformCustom.size() == 0)
        {
          out.println("<h3 class=\"pentagon\">Custom Modifications</h3>");
          out.println("<p class=\"pentagon\">This interface did not require any modifications to the message in order for it to be accepted. </p>");
        } else
        {
          out.println("<h3 class=\"pentagon\">Custom Modifications</h3>");
          out.println("<p class=\"pentagon\">This interface requires customized Transformations to modify each message "
              + "before transmitting them to the IIS. These transformations can range from setting "
              + "the correct submitter facility in the message header to modifying the structure of "
              + "the HL7 message to meet local requirements. </p>");
          if (transformExpected.size() > 0)
          {
            out.println("<h4 class=\"pentagon\">Expected Modifications</h4>");
            out.println("<p class=\"pentagon\">" + "Changes to certain fields such as MSH-4 and RXA-11.4 are expected "
                + "as IIS may request specific values in these fields.  </p>");
            TestReportServlet.printTransforms(out, transformExpected);
          }
          if (transformCustom.size() > 0)
          {
            out.println("<h4 class=\"pentagon\">Unexpected Modifications</h4>");
            out.println("<p class=\"pentagon\">These changes were not anticipated in the national standard or in "
                + "NIST testing. Please examine the need for these changes carefully as they are "
                + "likely to result in significant effort by EHR-s and other trading partners to achieve interoperability.</p>");
            TestReportServlet.printTransforms(out, transformCustom);
          }
        }
      }
    };

    pb[1] = new PentagonBox() {
      @Override
      public void printDescription(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
      {
        // TODO Auto-generated method stub

      }

      @Override
      public void printContents(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
      {
        // TODO Auto-generated method stub
      }
    };

    pb[2] = new PentagonBox() {
      @Override
      public void printDescription(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
      {
        // TODO Auto-generated method stub

      }

      @Override
      public void printContents(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
      {
        // TODO Auto-generated method stub
      }
    };

    pb[3] = new PentagonBox() {
      @Override
      public void printDescription(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
      {
        // TODO Auto-generated method stub

      }

      @Override
      public void printContents(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
      {
        // TODO Auto-generated method stub
      }
    };

    pb[0].setTitle("No Modifications");
    pb[1].setTitle("No Conflicts");
    pb[2].setTitle("No Constraints");
    pb[3].setTitle("Acks Conform");

    pb[0].setLabel("No<br/>Modifications");
    pb[1].setLabel("No<br/>Conflicts");
    pb[2].setLabel("No<br/>Constraints");
    pb[3].setLabel("Acks<br/>Conform");

    pb[0].setWeight(20);
    pb[1].setWeight(20);
    pb[2].setWeight(20);
    pb[3].setWeight(20);

    pb[0].setScore(pentagonReport.getScoreUCModifications());
    pb[1].setScore(pentagonReport.getScoreUCConflicts());
    pb[2].setScore(pentagonReport.getScoreUCConstraints());
    pb[3].setScore(pentagonReport.getScoreUCAcksConform());

    return pb;
  }

  private PentagonBox[] createPentagonBoxesOfQueryFunctionality(PentagonReport pentagonReport)
  {

    PentagonBox[] pb = new PentagonBox[6];

    pb[0] = new PentagonBox() {
      @Override
      public void printDescription(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
      {
        // TODO Auto-generated method stub

      }

      @Override
      public void printContents(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
      {
        // TODO Auto-generated method stub
      }
    };

    pb[1] = new PentagonBox() {
      @Override
      public void printDescription(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
      {
        // TODO Auto-generated method stub

      }

      @Override
      public void printContents(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
      {
        // TODO Auto-generated method stub
      }
    };

    pb[2] = new PentagonBox() {
      @Override
      public void printDescription(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
      {
        // TODO Auto-generated method stub

      }

      @Override
      public void printContents(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
      {
        // TODO Auto-generated method stub
      }
    };

    pb[3] = new PentagonBox() {
      @Override
      public void printDescription(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
      {
        // TODO Auto-generated method stub

      }

      @Override
      public void printContents(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
      {
        // TODO Auto-generated method stub
      }
    };

    pb[4] = new PentagonBox() {
      @Override
      public void printDescription(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
      {
        out.println("<p class=\"pentagon\">Ideally IIS should respond quickly to queries received. External systems depend on "
            + "the IIS to return data to support clinical decisions. The longer that a clinician has to wait for this "
            + "information the less useful this information will be and the less it may be used. Many IIS "
            + "are able to return query responses within 1 second. Full score is given to average responses less than "
            + "or equal to 1 second, and partial score is given to average responses less than 2 seconds. While performance is "
            + "very important this measure can only begin to measure performance. A complete performance and load test is beyond "
            + "the scope of this testing and should conducted separately. </p>");
      }

      @Override
      public void printContents(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
      {
        int average = (int) (((double) testConducted.getPerQueryTotal()) / testConducted.getPerQueryCount() + 0.5);
        out.println("<h3 class=\"pentagon\">Response Time</h3>");
        out.println("<ul>");
        out.println("  <li>Average: " + TestReportServlet.createTime(average) + "</li>");
        out.println("  <li>Minimum: " + TestReportServlet.createTime(testConducted.getPerQueryMin()) + "</li>");
        out.println("  <li>Maximum: " + TestReportServlet.createTime(testConducted.getPerQueryMax()) + "</li>");
        out.println("  <li>Std Dev: " + TestReportServlet.createTime(testConducted.getPerQueryStd()) + "</li>");
        out.println("</ul>");
      }
    };

    pb[5] = new PentagonBox() {
      @Override
      public void printDescription(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
      {
        // TODO Auto-generated method stub

      }

      @Override
      public void printContents(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
      {
        // TODO Auto-generated method stub
      }
    };

    pb[0].setTitle("NIST 2015 QBP Supported");
    pb[1].setTitle("Data is Available");
    pb[2].setTitle("Deduplication Engaged");
    pb[3].setTitle("Forecaster Engaged");
    pb[4].setTitle("Performance");
    pb[5].setTitle("Query Check");

    pb[0].setLabel("NIST 2015<br/>QBP Supported");
    pb[1].setLabel("Data is Available");
    pb[2].setLabel("Deduplication Engaged");
    pb[3].setLabel("Forecaster Engaged");
    pb[4].setLabel("Performance");
    pb[5].setLabel("Query Check");

    pb[0].setWeight(20);
    pb[1].setWeight(20);
    pb[2].setWeight(15);
    pb[3].setWeight(15);
    pb[4].setWeight(15);
    pb[5].setWeight(15);

    pb[0].setScore(pentagonReport.getScoreQFQbp2015());
    pb[1].setScore(pentagonReport.getScoreQFDataAvailable());
    pb[2].setScore(pentagonReport.getScoreQFDeduplication());
    pb[3].setScore(pentagonReport.getScoreQFForecaster());
    pb[4].setScore(pentagonReport.getScoreQFPerformance());
    pb[5].setScore(pentagonReport.getScoreQFMinimumQuery());

    return pb;
  }

  private PentagonBox[] createPentagonBoxesOfQueryConformance(PentagonReport pentagonReport)
  {

    PentagonBox[] pb = new PentagonBox[2];

    pb[0] = new PentagonBox() {
      @Override
      public void printDescription(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
      {
        // TODO Auto-generated method stub

      }

      @Override
      public void printContents(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
      {
        // TODO Auto-generated method stub
      }
    };

    pb[1] = new PentagonBox() {
      @Override
      public void printDescription(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
      {
        out.println("<p class=\"pentagon\">In 2015, AIRA launched an Interoperability Testing Project to determine the level of alignment between "
            + "current Immunization Information Systems (IIS) and the community’s interoperability standards. The testing process connects with "
            + "IIS directly and submits sample messages to IIS development platforms. When any two systems connect to exchange data, they must "
            + "use an agreed upon transport layer to connect. To this end, a CDC-led expert panel was tasked with selecting transport layer and "
            + "defining a technical specification. In 2011, the panel selected Soap  and defined a formal specification known as a \"CDC WSDL.\" </p>");

      }

      @Override
      public void printContents(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
      {
        TransportWsdlCdc transportWsdlCdc = null;
        {
          Query query = dataSession.createQuery("from TransportWsdlCdc where transportAnalysis.connectionLabel = ?");
          query.setParameter(0, testConducted.getConnectionLabel());
          List<TransportWsdlCdc> transportWsdlCdcList = query.list();
          if (transportWsdlCdcList.size() > 0)
          {
            transportWsdlCdc = transportWsdlCdcList.get(0);
          }
        }
        TransportOther transportOther = null;
        {
          Query query = dataSession.createQuery("from TransportOther where transportAnalysis.connectionLabel = ?");
          query.setParameter(0, testConducted.getConnectionLabel());
          List<TransportOther> transportOtherList = query.list();
          if (transportOtherList.size() > 0)
          {
            transportOther = transportOtherList.get(0);
          }
        }
        TransportAnalysis transportAnalysis = null;
        if (transportWsdlCdc != null)
        {
          transportAnalysis = transportWsdlCdc.getTransportAnalysis();
        } else if (transportOther != null)
        {
          transportAnalysis = transportOther.getTransportAnalysis();
        }
        if (transportAnalysis == null)
        {
          out.println("<h3 class=\"pentagon\">Transport Analysis</h3>");
          out.println("<p class=\"pentagon\">Not completed</p>");
        } else if (transportWsdlCdc != null)
        {
          out.println("<h3 class=\"pentagon\">CDC WSDL Analysis</h3>");
          if (!transportAnalysis.getReportComplete().equalsIgnoreCase("Yes"))
          {
            out.println("<p class=\"pentagon\">Report is not complete yet. </p>");
          } else if (transportAnalysis.getTransportType().equalsIgnoreCase("Non-participator CDC WSDL"))
          {
            out.println("<p class=\"pentagon\">Did not participate in status check project.</p>");
          } else if (transportAnalysis.getTransportType().equalsIgnoreCase("CDC WSDL"))
          {
            if (!transportWsdlCdc.getTransportStatus().equalsIgnoreCase("Done"))
            {
              out.println("<p class=\"pentagon\">Report is not done yet.</p>");
            } else if (transportWsdlCdc.getPassEyeTest().equalsIgnoreCase("No"))
            {
              out.println("<p class=\"pentagon\">WSDL does not look like CDC WSDL. No further analysis could be performed. </p>");
            } else
            {
              out.println("<table class=\"pentagon\">");
              out.println("  <tr>");
              out.println("    <th>Connectivity Test</th>");
              out.println("    <th>Submit Single Message</th>");
              out.println("    <th>Security Fault</th>");
              out.println("  </tr>");
              out.println("  <tr>");
              if (transportWsdlCdc.getCtConforms().equalsIgnoreCase("Yes"))
              {
                out.println("    <td class=\"pass\" style=\"text-align: center;\">Pass</td>");
              } else
              {
                out.println("    <td class=\"fail\" style=\"text-align: center;\">Fail</td>");
              }
              if (transportWsdlCdc.getSsmConforms().equalsIgnoreCase("Yes"))
              {
                out.println("    <td class=\"pass\" style=\"text-align: center;\">Pass</td>");
              } else
              {
                out.println("    <td class=\"fail\" style=\"text-align: center;\">Fail</td>");
              }
              if (transportWsdlCdc.getSfConforms().equalsIgnoreCase("Yes"))
              {
                out.println("    <td class=\"pass\" style=\"text-align: center;\">Pass</td>");
              } else
              {
                out.println("    <td class=\"fail\" style=\"text-align: center;\">Fail</td>");
              }
              out.println("  </tr>");
              out.println("</table>");
            }
          }
        } else if (transportOther != null)
        {
          out.println("<h3 class=\"pentagon\">Other Transport Analysis</h3>");
        }
        // TODO Auto-generated method stub
      }
    };

    pb[0].setTitle("Responses Conform");
    pb[1].setTitle("CDC WSDL Conforms");

    pb[0].setLabel("Responses<br/>Conform");
    pb[1].setLabel("CDC WSDL<br/>Conforms");

    pb[0].setWeight(50);
    pb[1].setWeight(50);

    pb[0].setScore(pentagonReport.getScoreQCResponsesConform());
    pb[1].setScore(pentagonReport.getScoreQCSoapConforms());

    return pb;
  }

}
