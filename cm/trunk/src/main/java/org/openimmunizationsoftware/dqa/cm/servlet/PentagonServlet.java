package org.openimmunizationsoftware.dqa.cm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.cm.servlet.pentagon.PentagonBoxHelper;
import org.openimmunizationsoftware.dqa.cm.servlet.pentagon.PentagonRowHelper;
import org.openimmunizationsoftware.dqa.tr.logic.PentagonReportLogic;
import org.openimmunizationsoftware.dqa.tr.model.PentagonBox;
import org.openimmunizationsoftware.dqa.tr.model.PentagonReport;
import org.openimmunizationsoftware.dqa.tr.model.ProfileUsage;
import org.openimmunizationsoftware.dqa.tr.model.TestConducted;
import org.openimmunizationsoftware.dqa.tr.model.TestMessage;
import org.openimmunizationsoftware.dqa.tr.model.TestParticipant;

@SuppressWarnings("serial")
public class PentagonServlet extends HomeServlet
{

  public static final String PARAM_TEST_MESSAGE_ID = "testMessageId";
  public static final String PARAM_COMPARISON_FIELD_ID = "comparisonFieldId";
  public static final String PARAM_PROFILE_USAGE_ID = "profileUsageId";
  public static final String PARAM_BOX_NAME = "boxName";
  public static final String PARAM_SELECTOR = "selector";

  public static final String BOX_NAME_HOW_TO_READ = "_howToRead";
  public static final String BOX_NAME_REPORT_SELECT = "_reportSelect";
  public static final String BOX_NAME_TEST_SECTIONS = "_testSections";

  protected static final String[] DETAILS_SECTIONS = { "Overview", "Data", "HL7", "Conformance", "Preparation", "History", "Comparison" };
  protected static final String[] BOX_DETAILS_SECTIONS = { "Overview", "Details", "Improve", "Calculation", "History", "Comparison" };

  public PentagonServlet() {
    super("Report");
  }

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

    try
    {
      TestConducted testConducted = getTestConducted(req, dataSession);
      TestParticipant testParticipantSelected = getTestParticipantSelected(testConducted);
      TestMessage testMessageSelected = getTestMessageSelected(req, dataSession);
      PentagonReport pentagonReport = PentagonReportLogic.createOrReturnPentagonReport(testConducted, dataSession);
      createHeader(webSession);
      printPentagonReport(userSession, dataSession, out, testConducted, testParticipantSelected, testMessageSelected, pentagonReport);
    } catch (Exception e)
    {
      handleError(e, webSession);
    } finally
    {
      createFooter(webSession);
    }
  }

  public void printPentagonReport(UserSession userSession, Session dataSession, PrintWriter out, TestConducted testConducted,
      TestParticipant testParticipantSelected, TestMessage testMessageSelected, PentagonReport pentagonReport) throws IOException
  {
    out.println("<svg xmlns=\"http://www.w3.org/2000/svg\"  width=\"850\" height=\"850\">");
    int[] centerPointBig = { 425, 438 };
    int[][] pointsBig = new int[5][2];
    setupPointsBig(pointsBig);
    printPentagon(out, pointsBig, "#cccccc", "2", null);

    {
      int[][] scorePoints = new int[5][2];
      int[] scores = pentagonReport.getScores();
      {
        for (int i = 0; i < scores.length; i++)
        {
          scorePoints[i][0] = pointsBig[i][0] - ((int) ((pointsBig[i][0] - centerPointBig[0]) * ((100 - scores[i]) / 100.0)));
          scorePoints[i][1] = pointsBig[i][1] - ((int) ((pointsBig[i][1] - centerPointBig[1]) * ((100 - scores[i]) / 100.0)));
        }
        printPentagon(out, scorePoints, "green", "2", null);
      }
    }

    out.println("  <rect fill=\"#83bbe5\" stroke=\"black\" stroke-width=\"2\" x=\"175\" y=\"138\" width=\"500\" height=\"27\"/>");
    out.println("  <rect fill=\"#7ab648\" stroke=\"black\" stroke-width=\"2\" x=\"125\" y=\"150\" width=\"40\" height=\"500\"/>");
    out.println("  <rect fill=\"#fcc438\" stroke=\"black\" stroke-width=\"2\" x=\"685\" y=\"150\" width=\"40\" height=\"500\"/>");
    out.println("  <rect fill=\"#7ab648\" stroke=\"black\" stroke-width=\"2\" x=\"25\" y=\"660\" width=\"488\" height=\"27\"/>");
    out.println("  <rect fill=\"#fcc438\" stroke=\"black\" stroke-width=\"2\" x=\"533\" y=\"660\" width=\"292\" height=\"27\"/>");

    PentagonRowHelper pentagonRowConfidence = PentagonRowHelper.createConfidenceRow(pentagonReport);
    PentagonRowHelper pentagonRowsUpdateFunctionality = PentagonRowHelper.createUpdateFunctionalityRow(pentagonReport);
    PentagonRowHelper pentagonRowUpdateConformance = PentagonRowHelper.createUpdateConformanceRow(pentagonReport);
    PentagonRowHelper pentagonRowQueryFunctionality = PentagonRowHelper.createQueryFunctionalityRow(pentagonReport);
    PentagonRowHelper pentagonRowQueryConformance = PentagonRowHelper.createQueryConformanceRow(pentagonReport);

    prepare(pentagonRowConfidence, 500);
    prepare(pentagonRowsUpdateFunctionality, 500);
    prepare(pentagonRowUpdateConformance, 488);
    prepare(pentagonRowQueryFunctionality, 500);
    prepare(pentagonRowQueryConformance, 292);

    {
      int posX = 27;
      int posY = 52;
      out.println("  <rect id=\"box_help\" fill=\"#eeeeee\" stroke=\"#9b0d28\" stroke-width=\"2\" x=\"" + posX + "\" y=\"" + posY
          + "\" width=\"130\" height=\"25\"/>");
      posY += 25;
      out.println("  <rect id=\"box_cdc\" fill=\"#eeeeee\" stroke=\"#9b0d28\" stroke-width=\"2\" x=\"" + posX + "\" y=\"" + posY
          + "\" width=\"43\" height=\"55\"/>");
      posX += 43;
      out.println("  <rect id=\"box_nist\" fill=\"#eeeeee\" stroke=\"#9b0d28\" stroke-width=\"2\" x=\"" + posX + "\" y=\"" + posY
          + "\" width=\"44\" height=\"55\"/>");
      posX += 44;
      out.println("  <rect id=\"box_local\" fill=\"#eeeeee\" stroke=\"#9b0d28\" stroke-width=\"2\" x=\"" + posX + "\" y=\"" + posY
          + "\" width=\"43\" height=\"55\"/>");
    }

    {
      int posX = 690;
      int posY = 52;
      out.println("  <rect id=\"box_improve\" fill=\"#eeeeee\" stroke=\"#9b0d28\" stroke-width=\"2\" x=\"" + posX + "\" y=\"" + posY
          + "\" width=\"130\" height=\"80\"/>");
    }

    {
      int posX = 175;
      int posY = 62;
      int i = 0;
      for (PentagonBoxHelper pentagonBox : pentagonRowConfidence)
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
      for (PentagonBoxHelper pentagonBox : pentagonRowsUpdateFunctionality)
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
      for (PentagonBoxHelper pentagonBox : pentagonRowUpdateConformance)
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
      for (PentagonBoxHelper pentagonBox : pentagonRowQueryConformance)
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
      for (PentagonBoxHelper pentagonBox : pentagonRowQueryFunctionality)
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
    {
      out.println("  function loadDetails(testMessageId, title) { ");
      out.println("    var eMenu = document.getElementById('detailsMenu');");
      out.println("    eMenu.style.display = 'block';");
      out.println("    var e = document.getElementById('details');");
      out.println("    var eTitle = document.getElementById('detailsTitle');");
      out.println("    var eContent = document.getElementById('detailsContent');");
      out.println("    eTitle.innerHTML = title; ");
      out.println("    eContent.innerHTML = '<p class=\"pentagon\">Loading...</p>"
          + "<p class=\"pentagon\">(Please be patient, this may take a minute.)</p>'; ");
      out.println("    e.style.display = 'block';");
      out.println("    var xhttp = new XMLHttpRequest(); ");
      out.println("    xhttp.onreadystatechange = function() { ");
      out.println("      if (xhttp.readyState == XMLHttpRequest.DONE) { ");
      out.println("        if (xhttp.status == 200) { ");
      out.println("          eContent.innerHTML = xhttp.responseText; ");
      out.println("          showDetailsSection(detailsSelected);");
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
    }
    {
      out.println("  function loadBoxContentsAndSwitch(title, boxName, selector, switchTo) { ");
      out.println("     boxDetailsSelected = switchTo; ");
      out.println("     loadBoxContents(title, boxName, selector, true); ");
      out.println("  }");
      out.println("  function loadBoxContents(title, boxName, selector, showMenu) { ");
      out.println("    var eMenu = document.getElementById('boxDetailsMenu');");
      out.println("    if (showMenu) { ");
      out.println("      eMenu.style.display = 'block';");
      out.println("    } else { ");
      out.println("      eMenu.style.display = 'none';");
      out.println("    }");
      out.println("    var e = document.getElementById('boxDetails');");
      out.println("    var eTitle = document.getElementById('boxDetailsTitle');");
      out.println("    var eContent = document.getElementById('boxDetailsContent');");
      out.println("    eTitle.innerHTML = title; ");
      out.println("    eContent.innerHTML = '<p class=\"pentagon\">Loading...</p>"
          + "<p class=\"pentagon\">(Please be patient, this may take a minute.)</p>'; ");
      out.println("    e.style.display = 'block';");
      out.println("    var xhttp = new XMLHttpRequest(); ");
      out.println("    xhttp.onreadystatechange = function() { ");
      out.println("      if (xhttp.readyState == XMLHttpRequest.DONE) { ");
      out.println("        if (xhttp.status == 200) { ");
      out.println("          eContent.innerHTML = xhttp.responseText; ");
      out.println("          showBoxDetailsSection(boxDetailsSelected);");
      out.println("        }");
      out.println("      }");
      out.println("    }");
      String link = "'pentagonContent?" + PARAM_TEST_CONDUCTED_ID + "=" + testConducted.getTestConductedId() + "&" + PARAM_BOX_NAME
          + "=' + boxName + '&" + PARAM_SELECTOR + "=' + selector";
      out.println("    xhttp.open('GET', " + link + ", true); ");
      out.println("    xhttp.send(null);");
      out.println("  }");
    }
    {
      out.println("  var boxDetailsSelected = 'boxDetailsOverview'; ");
      out.println("  function showBoxDetailsSection(id) { ");
      for (String s : BOX_DETAILS_SECTIONS)
      {
        out.println("    if (id != 'boxDetails" + s + "') { ");
        out.println("      hideReport('boxDetails" + s + "'); ");
        out.println("      var mh = document.getElementById('boxDetails" + s + "TopMenu');");
        out.println("      mh.style.border = '1px solid #eeeeee'; ");
        out.println("      } ");
      }
      out.println("    boxDetailsSelected = id; ");
      out.println("    var e = document.getElementById(id);");
      out.println("    e.style.display = 'block'; ");
      out.println("    var m = document.getElementById(id + 'TopMenu'); ");
      out.println("    m.style.border = '1px solid #9b0d28'; ");
      out.println("  }");
    }
    {
      out.println("  var detailsSelected = 'detailsOverview'; ");
      out.println("  function showDetailsSection(id) { ");
      for (String s : DETAILS_SECTIONS)
      {
        out.println("    if (id != 'details" + s + "') { ");
        out.println("      hideReport('details" + s + "'); ");
        out.println("      var mh = document.getElementById('details" + s + "TopMenu');");
        out.println("      mh.style.border = '1px solid #eeeeee'; ");
        out.println("      } ");
      }
      out.println("    detailsSelected = id; ");
      out.println("    var e = document.getElementById(id);");
      out.println("    e.style.display = 'block'; ");
      out.println("    var m = document.getElementById(id + 'TopMenu'); ");
      out.println("    m.style.border = '1px solid #9b0d28'; ");
      out.println("  }");
    }
    out.println("  function flashOnGrey(id) { ");
    out.println("    var e = document.getElementById(id); ");
    out.println("    e.style.fill = '#eeeeee';");
    out.println("  }");
    out.println("  function flashOffGrey(id) { ");
    out.println("    var e = document.getElementById(id); ");
    out.println("    e.style.fill = '#ffffff';");
    out.println("  }");
    out.println("  function flashOnGreen(id) { ");
    out.println("    var e = document.getElementById(id); ");
    out.println("    e.style.fill = '#7ab648';");
    out.println("  }");
    out.println("  function flashOffGreen(id) { ");
    out.println("    var e = document.getElementById(id); ");
    out.println("    e.style.fill = '#a3d977';");
    out.println("  }");
    out.println("  function flashOnBlue(id) { ");
    out.println("    var e = document.getElementById(id); ");
    out.println("    e.style.fill = '#83bbe5';");
    out.println("  }");
    out.println("  function flashOffBlue(id) { ");
    out.println("    var e = document.getElementById(id); ");
    out.println("    e.style.fill = '#99d2f2';");
    out.println("  }");
    out.println("  function flashOnLinkYellow(id) { ");
    out.println("    var e = document.getElementById(id); ");
    out.println("    e.style.fill = '#FFE1AA';");
    out.println("  }");
    out.println("  function flashOffLinkYellow(id) { ");
    out.println("    var e = document.getElementById(id); ");
    out.println("    e.style.fill = '#eeeee';");
    out.println("  }");
    out.println("  function flashOnOrange(id) { ");
    out.println("    var e = document.getElementById(id); ");
    out.println("    e.style.fill = '#fcc438';");
    out.println("  }");
    out.println("  function flashOffOrange(id) { ");
    out.println("    var e = document.getElementById(id); ");
    out.println("    e.style.fill = '#ffdf71';");
    out.println("  }");
    out.println("  function showReport(id) { ");
    out.println("    if (id != 'boxDetails') { hideReport('boxDetails'); } ");
    out.println("    if (id != 'details') { hideReport('details'); } ");
    out.println("    if (id != '') { ");
    out.println("      var e = document.getElementById(id)");
    out.println("      e.style.display = 'block'; ");
    out.println("    }");
    out.println("  }");
    out.println("  function hideReport(id) { ");
    out.println("    var e = document.getElementById(id)");
    out.println("    if (e != null) {");
    out.println("      e.style.display = 'none'; ");
    out.println("    } ");
    out.println("  }");
    out.println("</script>");

    int offsetY = 38;
    int offsetX = 10;

    {
      int posX = 0;
      int posY = 0;
      out.println("  <span style=\"position: absolute; top: " + (posY + offsetY) + "px; left: " + (posX + offsetX)
          + "px; width: 850px; cursor: pointer; cursor: hand; \"><h1 class=\"pentagon\" onClick=\"loadBoxContents('"
          + "AIRA Interoperability Testing Report', '" + BOX_NAME_REPORT_SELECT + "', '', true)\">"
          + testConducted.getTestParticipant().getConnectionLabel(userSession) + "</h1></span>");
    }

    {
      SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
      int posX = 690;
      int posY = 5;
      out.println(
          "  <span id=\"box_reportSelect\" style=\"color: #9b0d28; font-size: 17px; text-align: center; position: absolute; top: " + (posY + offsetY)
              + "px; left: " + (posX + offsetX) + "px; width: 130px; border-width: 0px; border-style: none;  cursor: pointer; cursor: hand; \" "
              + "onmouseout=\"flashOnLinkYellow('box_reportSelect')\" onmouseover=\"flashOnGrey('box_reportSelect')\" onClick=\"loadBoxContents('"
              + "AIRA Interoperability Testing Report', '" + BOX_NAME_REPORT_SELECT + "', '', true)\">"
              + sdf.format(testConducted.getTestStartedTime()) + "</span>");
    }

    {
      int posX = 27;
      int posY = 52;
      out.println("  <span style=\"color: #9b0d28; font-size: 12px; text-align: center; position: absolute; top: " + (posY + offsetY) + "px; left: "
          + (posX + offsetX)
          + "px; width: 130px; height: 25px; cursor: pointer; cursor: hand; \" onmouseout=\"flashOnGrey('box_help')\" onmouseover=\"flashOnLinkYellow('box_help')\"  "
          + "onClick=\"loadBoxContents('How To Read This Report', '" + BOX_NAME_HOW_TO_READ
          + "', '', true)\"><span style=\"font-size: 11pt; font-weight: bold;\">How To Read</span></span>");
      posY += 25;
      out.println("  <span style=\"color: #9b0d28; font-size: 12px; text-align: center; position: absolute; top: " + (posY + offsetY) + "px; left: "
          + (posX + offsetX)
          + "px; width: 43px; height: 55px; cursor: pointer; cursor: hand; \" onmouseout=\"flashOnGrey('box_cdc')\" onmouseover=\"flashOnLinkYellow('box_cdc')\"  "
          + "onClick=\"window.open('guide?profileUsageId=" + ProfileUsage.getBaseProfileUsage(dataSession).getProfileUsageId()
          + "', '_blank')\"><span style=\"font-size: 10pt; font-weight: bold;\">CDC Guide</span></span>");
      posX += 43;
      out.println("  <span style=\"color: #9b0d28; font-size: 12px; text-align: center; position: absolute; top: " + (posY + offsetY) + "px; left: "
          + (posX + offsetX)
          + "px; width: 44px; height: 55px; cursor: pointer; cursor: hand; \" onmouseout=\"flashOnGrey('box_nist')\" onmouseover=\"flashOnLinkYellow('box_nist')\"  "
          + "onClick=\"window.open('http://hl7v2-iz-r1.5-testing.nist.gov/iztool/#/cf', '_blank')\"><span style=\"font-size: 10pt; font-weight: bold;\">NIST Test Suite</span></span>");
      posX += 44;
      if (testParticipantSelected.getProfileUsage() == null || testParticipantSelected.getProfileUsage().getLinkGuide() == null
          || testParticipantSelected.getProfileUsage().getLinkGuide().equals(""))
      {
        if (testParticipantSelected.getProfileUsage() != null && testParticipantSelected.canViewConnectionLabel(userSession))
        {
          out.println("  <span style=\"color: #9b0d28; font-size: 12px; text-align: center; position: absolute; top: " + (posY + offsetY)
              + "px; left: " + (posX + offsetX)
              + "px; width: 43px; height: 55px; cursor: pointer; cursor: hand; \" onmouseout=\"flashOnGrey('box_local')\" onmouseover=\"flashOnLinkYellow('box_local')\"  "
              + "onClick=\"window.open('guide?profileUsageId=" + testParticipantSelected.getProfileUsage().getProfileUsageId() + "', '_blank')\">"
              + "<span style=\"font-size: 10pt; font-weight: bold; color: #AAAAAA; \">IIS Guide</span></span>");
        } else
        {
          out.println("  <span style=\"color: #9b0d28; font-size: 12px; text-align: center; position: absolute; top: " + (posY + offsetY)
              + "px; left: " + (posX + offsetX) + "px; width: 43px; height: 55px; \">"
              + "<span style=\"font-size: 10pt; font-weight: bold; color: #AAAAAA; \"></span></span>");
        }
      } else
      {
        if (testParticipantSelected.canViewConnectionLabel(userSession))
        {
          out.println("  <span style=\"color: #9b0d28; font-size: 12px; text-align: center; position: absolute; top: " + (posY + offsetY)
              + "px; left: " + (posX + offsetX)
              + "px; width: 43px; height: 55px; cursor: pointer; cursor: hand; \" onmouseout=\"flashOnGrey('box_local')\" onmouseover=\"flashOnLinkYellow('box_local')\"  "
              + "onClick=\"window.open('guide?profileUsageId=" + testParticipantSelected.getProfileUsage().getProfileUsageId()
              + "', '_blank')\"><span style=\"font-size: 10pt; font-weight: bold;\">IIS Guide</span></span>");
        } else
        {
          out.println("  <span style=\"color: #9b0d28; font-size: 12px; text-align: center; position: absolute; top: " + (posY + offsetY)
              + "px; left: " + (posX + offsetX) + "px; width: 43px; height: 55px; \">"
              + "<span style=\"font-size: 10pt; font-weight: bold; color: #AAAAAA; \"></span></span>");
        }
      }
    }

    {
      int posX = 690;
      int posY = 52;
      int count = 0;
      for (PentagonBox pentagonBox : pentagonReport.getPentagonBoxMap().values())
      {
        if (pentagonBox.getPriorityRow() == 1 && pentagonBox.getPriorityOverall() > 0)
        {
          count++;
        }
      }

      out.println("  <span style=\"color: #9b0d28; font-size: 12px; text-align: center; position: absolute; top: " + (posY + offsetY) + "px; left: "
          + (posX + offsetX) + "px; width: 130px; height: 100px; cursor: pointer; cursor: hand; \" onmouseout=\"flashOnGrey('box_improve')\" "
          + "onmouseover=\"flashOnLinkYellow('box_improve')\"  onClick=\"loadBoxContentsAndSwitch('AIRA Interoperability Testing Report', '"
          + BOX_NAME_REPORT_SELECT + "', '', 'boxDetailsImprove')\"><span style=\"font-size: 18pt; font-weight: bold;\">" + count
          + " Ways </span><br/><span style=\"font-size: 12pt; font-weight: bold;\">To Improve<br/>HL7 Interface</span></span>");
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
      for (PentagonBoxHelper pentagonBox : pentagonRowConfidence)
      {
        out.println(
            "  <span style=\"font-size: 12px; text-align: center; position: absolute; top: " + (posY + offsetY) + "px; left: " + (posX + offsetX)
                + "px; width: " + pentagonBox.getSize() + "px; height: 100px; cursor: pointer; cursor: hand; \" onmouseout=\"flashOffBlue('r_c" + i
                + "')\" onmouseover=\"flashOnBlue('r_c" + i + "')\"  onClick=\"loadBoxContents('" + "Confidence: " + pentagonBox.getTitle() + "', '"
                + pentagonBox.getBoxName() + "', '', true)\">" + pentagonBox.getLabel() + "</span>");
        posX += pentagonBox.getSize();
        i++;
      }
    }
    {
      int posX = 25;
      int posY = 150;
      int i = 0;
      for (PentagonBoxHelper pentagonBox : pentagonRowsUpdateFunctionality)
      {
        out.println(
            "  <span style=\"font-size: 12px; text-align: center; position: absolute; top: " + (posY + offsetY) + "px; left: " + (posX + offsetX)
                + "px; height: " + pentagonBox.getSize() + "px; width: 100px; cursor: pointer; cursor: hand; \" onmouseout=\"flashOffGreen('r_uf" + i
                + "')\" onmouseover=\"flashOnGreen('r_uf" + i + "')\" onClick=\"loadBoxContents('" + "Update Functionality: " + pentagonBox.getTitle()
                + "', '" + pentagonBox.getBoxName() + "', '', true)\">" + pentagonBox.getLabel() + "</span>");
        posY += pentagonBox.getSize();
        i++;
      }
    }
    {
      int posX = 25;
      int posY = 700;
      int i = 0;
      for (PentagonBoxHelper pentagonBox : pentagonRowUpdateConformance)
      {
        out.println(
            "  <span style=\"font-size: 12px; text-align: center; position: absolute; top: " + (posY + offsetY) + "px; left: " + (posX + offsetX)
                + "px; width: " + pentagonBox.getSize() + "px; height: 70px; cursor: pointer; cursor: hand; \" onmouseout=\"flashOffGreen('r_uc" + i
                + "')\" onmouseover=\"flashOnGreen('r_uc" + i + "')\" onClick=\"loadBoxContents('" + "Update Conformance: " + pentagonBox.getTitle()
                + "', '" + pentagonBox.getBoxName() + "', '', true)\"><br/><br/>" + pentagonBox.getLabel() + "</span>");
        posX += pentagonBox.getSize();
        i++;
      }
    }
    {
      int posX = 538;
      int posY = 700;
      int i = 0;
      for (PentagonBoxHelper pentagonBox : pentagonRowQueryConformance)
      {
        out.println(
            "  <span style=\"font-size: 12px; text-align: center; position: absolute; top: " + (posY + offsetY) + "px; left: " + (posX + offsetX)
                + "px; width: " + pentagonBox.getSize() + "px; height: 70px; cursor: pointer; cursor: hand; \" onmouseout=\"flashOffOrange('r_qc" + i
                + "')\" onmouseover=\"flashOnOrange('r_qc" + i + "')\" onClick=\"loadBoxContents('" + "Query Conformance: " + pentagonBox.getTitle()
                + "', '" + pentagonBox.getBoxName() + "', '', true)\"><br/><br/>" + pentagonBox.getLabel() + "</span>");
        posX += pentagonBox.getSize();
        i++;
      }
    }
    {
      int posX = 725;
      int posY = 150;
      int i = 0;
      for (PentagonBoxHelper pentagonBox : pentagonRowQueryFunctionality)
      {
        out.println(
            "  <span style=\"font-size: 12px; text-align: center; position: absolute; top: " + (posY + offsetY) + "px; left: " + (posX + offsetX)
                + "px; height: " + pentagonBox.getSize() + "px; width: 100px; cursor: pointer; cursor: hand; \" onmouseout=\"flashOffOrange('r_qf" + i
                + "')\" onmouseover=\"flashOnOrange('r_qf" + i + "')\" onClick=\"loadBoxContents('" + "Query Functionality: " + pentagonBox.getTitle()
                + "', '" + pentagonBox.getBoxName() + "', '', true)\">" + pentagonBox.getLabel() + "</span>");
        posY += pentagonBox.getSize();
        i++;
      }
    }

    {
      int posX = 124;
      int posY = 135;
      out.println("<div id=\"boxDetails\" style=\" position: absolute; top: " + (posY + offsetY) + "px; left: " + (posX + offsetX)
          + "px; width: 597px; background-color: #eeeeee; border-size: 2px; display:none; border-style: solid; border-color: #9b0d28;\">");
      out.println(
          "<a class=\"pentagonMenuLink\" onclick=\"hideReport('boxDetails')\" style=\"float: right;\" href=\"javascript: void(0); \">Close</a>");
      out.println("<h2 class=\"pentagon\" id=\"boxDetailsTitle\" ></h2>");
      out.println("<div id=\"boxDetailsMenu\" style=\"text-align: center; margin-bottom: 10px; \">");
      for (int i = 0; i < BOX_DETAILS_SECTIONS.length; i++)
      {
        String boxTopStyle;
        if (i == 0)
        {
          boxTopStyle = " style=\"border-style: solid; border-color: #9b0d28; \"";
        } else
        {
          boxTopStyle = "";
        }
        String boxDetailsId = "boxDetails" + BOX_DETAILS_SECTIONS[i];
        out.println("<a class=\"pentagonMenuLink\" id=\"" + boxDetailsId + "TopMenu\"" + boxTopStyle + " onClick=\"showBoxDetailsSection('"
            + boxDetailsId + "')\" href=\"javascript: void(0); \">" + BOX_DETAILS_SECTIONS[i] + "</a>");
      }
      out.println("</div>");
      out.println("<div id=\"boxDetailsContent\" style=\"height: 450px; overflow: auto; \"></div>");
      out.println("</div>");
    }

    {
      int posX = 0;
      int posY = 48;
      boolean display = testMessageSelected != null;
      out.println("<div id=\"details\" style=\" position: absolute; top: " + (posY + offsetY) + "px; left: " + (posX + offsetX)
          + "px; width: 850px; background-color: #eeeeee; border-size: 2px;" + (display ? "" : " display:none;")
          + " border-style: solid; border-color: #9b0d28; overflow: auto;\">");
      out.println("<a class=\"pentagonMenuLink\" onclick=\"hideReport('details')\" style=\"float: right;\" href=\"javascript: void(0); \">Close</a>");
      if (testMessageSelected == null)
      {
        out.println("<h2 class=\"pentagon\" id=\"detailsTitle\" ></h2>");
      } else
      {
        out.println("<h2 class=\"pentagon\" id=\"detailsTitle\" >" + testMessageSelected.getTestCaseDescription() + "</h2>");
      }
      out.println("<div id=\"detailsMenu\" style=\"text-align: center; margin-bottom: 10px; \">");
      for (int i = 0; i < DETAILS_SECTIONS.length; i++)
      {
        String boxTopStyle;
        if (i == 0)
        {
          boxTopStyle = "border-style: solid; border-color: #9b0d28; ";
        } else
        {
          boxTopStyle = "";
        }
        String detailsId = "details" + DETAILS_SECTIONS[i];
        out.println("<a class=\"pentagonMenuLink\" id=\"" + detailsId + "TopMenu\" style=\"" + boxTopStyle + "\" onClick=\"showDetailsSection('"
            + detailsId + "')\" href=\"javascript: void(0); \">" + DETAILS_SECTIONS[i] + "</a>");
      }
      out.println("</div>");
      out.println("<div id=\"detailsContent\" style=\"height: 700px; overflow: auto; \">");
      if (testMessageSelected != null)
      {
        ProfileUsage profileUsage = testParticipantSelected.getProfileUsage();
        PentagonContentServlet.printTestMessage(dataSession, out, profileUsage, testMessageSelected, false, userSession);
      }
      out.println("</div>");
      out.println("</div>");
    }

    {
      int posX = 900;
      int posY = 48;
      boolean display = testMessageSelected != null;
      out.println("<div id=\"details\" style=\" position: absolute; top: " + (posY + offsetY) + "px; left: " + (posX + offsetX)
          + "px; width: 850px; background-color: #eeeeee; border-size: 2px; border-style: solid; border-color: #9b0d28; overflow: auto;\">");
      out.println("<a class=\"pentagonMenuLink\" onclick=\"hideReport('details')\" style=\"float: right;\" href=\"javascript: void(0); \">Close</a>");
      out.println("<h2 class=\"pentagon\" id=\"detailsTitle\" >Beta Testing Feedback</h2>");
      out.println("<p class=\"pentagon\">Please note that this report is not complete and needs your feedback so we can make improvements. "
          + "You may use the information in this report, but you must know that it has limitations: </p>");
      out.println("<ul>");
      out.println("  <li>This report shows the results of a testing discovery process which is still evolving and improving. Expect this report to change and improve. </li>");
      out.println("  <li>The scoring and graphics shown in the report are to focus the reader on aspects that are felt to be most critical. They are not defnitive and subject to revision and improvement as the testing process improves. </li>");
      out.println("  <li>We need your help to make this report better. Notice a problem? Please tell us!</li>");
      out.println("</ul>");
      out.println("<h3 class=\"pentagon\" id=\"detailsTitle\" >Your Comments or Questions</h3>");
      out.println("<form>");
      out.println("<textarea name=\"\" cols=\"60\" rows=\"10\"></textarea>");
      out.println("<br/>");
      out.println("<input name=\"submit\" type=\"submit\" value=\"Send\" />");
      out.println("<form>");
      out.println("</div>");
    }
  }

  public TestParticipant getTestParticipantSelected(TestConducted testConducted)
  {
    TestParticipant testParticipantSelected = testConducted.getTestParticipant();
    if (testParticipantSelected == null)
    {
      throw new NullPointerException("Test Conducted did not have Test Participant ");
    }
    return testParticipantSelected;
  }

  public TestMessage getTestMessageSelected(HttpServletRequest req, Session dataSession)
  {
    TestMessage testMessageSelected = null;
    if (req.getParameter(PARAM_TEST_MESSAGE_ID) != null)
    {
      testMessageSelected = (TestMessage) dataSession.get(TestMessage.class, Integer.parseInt(req.getParameter(PARAM_TEST_MESSAGE_ID)));
    }
    return testMessageSelected;
  }

  public TestConducted getTestConducted(HttpServletRequest req, Session dataSession)
  {
    TestConducted testConducted;
    {
      String testConnectedIdString = req.getParameter(PARAM_TEST_CONDUCTED_ID);
      int testConductedId = Integer.parseInt(testConnectedIdString);
      testConducted = (TestConducted) dataSession.get(TestConducted.class, testConductedId);
    }
    return testConducted;
  }

  public static void setupAndPrintSmallPentagon(PrintWriter out, PentagonReport pentagonReport, int offsetY, int offsetX)
  {
    int[] centerPointSmall = { 50 + offsetX, 50 + offsetY };
    int[][] pointsSmall = new int[5][2];
    setupPointsSmall(pointsSmall, offsetX, offsetY);
    printPentagon(out, pointsSmall, "#eeeeee", "0.5", pentagonReport);

    {
      int[][] scorePoints = new int[5][2];
      int[] scores = pentagonReport.getScores();
      {
        for (int i = 0; i < scores.length; i++)
        {
          scorePoints[i][0] = pointsSmall[i][0] - ((int) ((pointsSmall[i][0] - centerPointSmall[0]) * ((100 - scores[i]) / 100.0)));
          scorePoints[i][1] = pointsSmall[i][1] - ((int) ((pointsSmall[i][1] - centerPointSmall[1]) * ((100 - scores[i]) / 100.0)));
        }
        printPentagon(out, scorePoints, "#cccccc", "0.5", pentagonReport);
      }
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
      out.println("  <circle fill=\"green\" stroke=\"black\" stroke-width=\"2\" cx=\"50\" cy=\"46\" r=\"45\"/>");
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
          out.println("   <path d=\"M50,47 L50,1 A47,47 0 0,1 " + posX + "," + posY + " z\" fill=\"green\" stroke=\"black\" stroke-width=\"1\"/>");
        } else
        {
          out.println("   <path d=\"M50,47 L50,1 A47,47 0 1,1 " + posX + "," + posY + " z\" fill=\"green\" stroke=\"black\" stroke-width=\"1\"/>");
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

  public static void setupPointsSmall(int[][] points, int offsetX, int offsetY)
  {
    points[0][0] = 50 + offsetX;
    points[0][1] = 0 + offsetY;

    points[1][0] = 100 + offsetX;
    points[1][1] = 36 + offsetY;

    points[2][0] = 81 + offsetX;
    points[2][1] = 95 + offsetY;

    points[3][0] = 19 + offsetX;
    points[3][1] = 95 + offsetY;

    points[4][0] = 0 + offsetX;
    points[4][1] = 36 + offsetY;
  }

  public static void printPentagon(PrintWriter out, int[][] points, String color, String strokeWidth, PentagonReport pentagonReport)
  {
    String makePoints = "";
    for (int i = 0; i < points.length; i++)
    {
      makePoints += points[i][0] + "," + points[i][1] + " ";
    }
    if (pentagonReport != null)
    {
      TestConducted testConductedDisplay = pentagonReport.getTestConducted();
      String link = "pentagon?" + PARAM_TEST_CONDUCTED_ID + "=" + testConductedDisplay.getTestConductedId();
      out.println("  <polygon fill=\"" + color + "\" stroke=\"black\" stroke-width=\"" + strokeWidth + "\" points=\"" + makePoints
          + "\" onClick=\"window.open('" + link + "', '_self')\"/>");
    } else
    {
      out.println("  <polygon fill=\"" + color + "\" stroke=\"black\" stroke-width=\"" + strokeWidth + "\" points=\"" + makePoints + "\"\"/>");
    }
  }

  private void prepare(PentagonRowHelper pentagonRow, int totalSize)
  {
    int amountLeft = totalSize;
    double total = 0;
    for (PentagonBoxHelper pentagonBox : pentagonRow)
    {
      total += pentagonBox.getWidth();
    }

    for (PentagonBoxHelper pentagonBox : pentagonRow)
    {
      int size = (int) (totalSize * (pentagonBox.getWidth() / total) + 0.5);
      if (size > amountLeft)
      {
        size = amountLeft;
      }
      amountLeft = amountLeft - size;
      pentagonBox.setSize(size);
    }
  }

  public List<PentagonBoxHelper> createImprovePentagonBoxList(PentagonReport pentagonReport)
  {
    List<PentagonBoxHelper> pentagonBoxList = new ArrayList<PentagonBoxHelper>();
    return pentagonBoxList;
  }

}
