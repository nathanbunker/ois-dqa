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

public class PentagonServlet extends HomeServlet
{

  private static final String PARAM_TEST_CONDUCTED_ID = "testConductedId";
  private static final String PARAM_CONNECTION_LABEL = "connectionLabel";
  private static final String PARAM_TEST_MESSAGE_ID = "testMessageId";
  private static final String PARAM_TEST_PARTICIPANT_ID = "testParticipantId";
  private static final String PARAM_COMPARISON_FIELD_ID = "comparisonFieldId";

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
      int posX = 700;
      int posY = 80;
      out.println("  <span style=\"font-size: 16px; text-align: center; position: absolute; top: " + (posY + offsetY) + "px; left: "
          + (posX + offsetX) + "px; width: 100px; height: 50px; \">" + sdf.format(testConducted.getTestStartedTime()) + "</span>");
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
      out.println("<span style=\"margin-left: 10px; margin-right: 10px; float: left; padding: 0px; width: 100px; height: 115px;\">");
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
    int posX = 134;
    int posY = 133;

    out.println("<div id=\"" + id + "\" style=\" position: absolute; top: " + (posY + offsetY) + "px; left: " + (posX + offsetX)
        + "px; height: 553px; width: 577px; background-color: #eeeeee; border-size: 2px; display:none; border-style: solid; border-color: #9b0d28; \">");
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
    out.println("  <polygon fill=\"" + color + "\" stroke=\"black\" stroke-width=\"" + strokeWidth + "\" points=\"" + makePoints + "\" onClick=\"showReport('')\"/>");
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
        out.println("<li><span class=\"pentagonTestMessageFail\">" + testMessage.getTestCaseDescription() + "</span></li>");
      }
      out.println("</ul>");
    }

    public void printTestMessageListPass(PrintWriter out, List<TestMessage> testMessageList)
    {
      out.println("<ul>");
      for (TestMessage testMessage : testMessageList)
      {
        out.println("<li><span class=\"pentagonTestMessagePass\">" + testMessage.getTestCaseDescription() + "</span></li>");
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
              "from TestMessage where testSection.testSectionType = ? and testSection.testConducted = ? and resultStatus = 'FAIL' order by testCaseCategory");
          query.setParameter(0, RecordServletInterface.VALUE_TEST_SECTION_TYPE_BASIC);
          query.setParameter(1, testConducted);
          List<TestMessage> testMessageList = query.list();
          printTestMessageListFail(out, testMessageList);
        }
        if (score > 0)
        {
          out.println("<h3 class=\"pentagon\">Pass - Good Message Was Accepted</h3>");
          Query query = dataSession.createQuery(
              "from TestMessage where testSection.testSectionType = ? and testSection.testConducted = ? and resultStatus = 'PASS' order by testCaseCategory");
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
              "from TestMessage where testSection.testSectionType = ? and testSection.testConducted = ? and resultStatus = 'FAIL' order by testCaseCategory");
          query.setParameter(0, RecordServletInterface.VALUE_TEST_SECTION_TYPE_NOT_ACCEPTED);
          query.setParameter(1, testConducted);
          List<TestMessage> testMessageList = query.list();
          printTestMessageListFail(out, testMessageList);
        }
        if (score > 0)
        {
          out.println("<h3 class=\"pentagon\">Pass - Bad Message Was Not Accepted</h3>");
          Query query = dataSession.createQuery(
              "from TestMessage where testSection.testSectionType = ? and testSection.testConducted = ? and resultStatus = 'PASS' order by testCaseCategory");
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
        // TODO Auto-generated method stub

      }

      @Override
      public void printContents(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
      {
        // TODO Auto-generated method stub
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
              "from TestMessage where testSection.testSectionType = ? and testSection.testConducted = ? and resultStatus = 'FAIL' order by testCaseCategory");
          query.setParameter(0, RecordServletInterface.VALUE_TEST_SECTION_TYPE_BASIC);
          query.setParameter(1, testConducted);
          List<TestMessage> testMessageList = query.list();
          printTestMessageListFail(out, testMessageList);
        }
        if (score > 0)
        {
          out.println("<h3 class=\"pentagon\">Pass - NIST 2014 Example Was Accepted</h3>");
          Query query = dataSession.createQuery(
              "from TestMessage where testSection.testSectionType = ? and testSection.testConducted = ? and resultStatus = 'PASS' order by testCaseCategory");
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
              + "be prepared to receive all of these messages. <br/><br/> </p>");
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
              "from TestMessage where testSection.testSectionType = ? and testSection.testConducted = ? and resultStatus = 'FAIL' order by testCaseCategory");
          query.setParameter(0, RecordServletInterface.VALUE_TEST_SECTION_TYPE_ONC_2015);
          query.setParameter(1, testConducted);
          List<TestMessage> testMessageList = query.list();
          printTestMessageListFail(out, testMessageList);
        }
        if (score > 0)
        {
          out.println("<h3 class=\"pentagon\">Pass - NIST 2015 VXU Example Was Accepted</h3>");
          Query query = dataSession.createQuery(
              "from TestMessage where testSection.testSectionType = ? and testSection.testConducted = ? and resultStatus = 'PASS' order by testCaseCategory");
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
        // TODO Auto-generated method stub

      }

      @Override
      public void printContents(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
      {
        // TODO Auto-generated method stub
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

    pb[6] = new PentagonBox() {
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
        // TODO Auto-generated method stub

      }

      @Override
      public void printContents(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
      {
        // TODO Auto-generated method stub
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
        // TODO Auto-generated method stub

      }

      @Override
      public void printContents(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession, UserSession userSession)
      {
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
