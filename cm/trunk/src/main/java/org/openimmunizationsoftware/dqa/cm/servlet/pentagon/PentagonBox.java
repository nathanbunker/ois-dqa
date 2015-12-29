package org.openimmunizationsoftware.dqa.cm.servlet.pentagon;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.cm.servlet.UserSession;
import org.openimmunizationsoftware.dqa.tr.model.PentagonReport;
import org.openimmunizationsoftware.dqa.tr.model.TestConducted;
import org.openimmunizationsoftware.dqa.tr.model.TestMessage;
import org.openimmunizationsoftware.dqa.tr.model.TestSection;

public abstract class PentagonBox
{
  public static final String BOX_NAME_C_GOOD_MESSAGE = "CGoodMessage";
  public static final String BOX_NAME_C_GOOD_DATA = "CGoodData";
  public static final String BOX_NAME_C_BAD_MESSAGE = "CBadMessage";
  public static final String BOX_NAME_C_BAD_DATA = "CBadData";
  public static final String BOX_NAME_C_ACK_CONFORM = "CAckConform";
  public static final String BOX_NAME_QC_SOAP_CONFORMS = "QCSoapConforms";
  public static final String BOX_NAME_QC_RESPONSES_CONFORM = "QCResponsesConform";
  public static final String BOX_NAME_QF_QBP2015 = "QFQbp2015";
  public static final String BOX_NAME_QF_PERFORMANCE = "QFPerformance";
  public static final String BOX_NAME_QF_MINIMUM_QUERY = "QFMinimumQuery";
  public static final String BOX_NAME_QF_FORECASTER = "QFForecaster";
  public static final String BOX_NAME_QF_DEDUPLICATION = "QFDeduplication";
  public static final String BOX_NAME_QF_DATA_AVAILABLE = "QFDataAvailable";
  public static final String BOX_NAME_UC_MODIFICATIONS = "UCModifications";
  public static final String BOX_NAME_UC_CONFLICTS = "UCConflicts";
  public static final String BOX_NAME_UC_CONSTRAINTS = "UCConstraints";
  public static final String BOX_NAME_UC_ACKS_CONFORM = "UCAcksConform";
  public static final String BOX_NAME_UF_VXU2015 = "UFVxu2015";
  public static final String BOX_NAME_UF_VXU2014 = "UFVxu2014";
  public static final String BOX_NAME_UF_TOLERANT = "UFTolerant";
  public static final String BOX_NAME_UF_SENSITIVE = "UFSensitive";
  public static final String BOX_NAME_UF_PERFORMANCE = "UFPerformance";
  public static final String BOX_NAME_UF_EHR_EXAMPLES = "UFEhrExamples";
  public static final String BOX_NAME_UF_CODED_VALUES = "UFCodedValues";
  
  private String boxName = "";
  protected String label = "";
  protected String title = "";
  protected int weight = 0;
  protected int posX = 0;
  protected int poxY = 0;
  protected int size = 0;
  protected int score = 0;
  
  public PentagonBox(String boxName)
  {
    this.boxName = boxName;
  }

  public String getBoxName()
  {
    return boxName;
  }

  public void setBoxName(String boxName)
  {
    this.boxName = boxName;
  }

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

  public abstract void printScoreExplanation(PrintWriter out, Session dataSession, TestConducted testConducted, HttpSession webSession,
      UserSession userSession);

  public abstract void calculateScore(TestConducted testConducted, Session dataSession, PentagonReport pentagonReport,
      Map<String, TestSection> testSectionMap);

  public void printTestMessageListFail(PrintWriter out, List<TestMessage> testMessageList)
  {
    if (testMessageList.size() > 0)
    {
      out.println("<table class=\"pentagon\">");
      out.println("  <tr class=\"pentagon\">");
      out.println("    <th class=\"pentagon\">Status</th>");
      out.println("    <th class=\"pentagon\">Accepted</th>");
      out.println("    <th class=\"pentagon\">Description</th>");
      out.println("  </tr>");
      for (TestMessage testMessage : testMessageList)
      {
        out.println("  <tr class=\"pentagon\">");
        out.println("    <td class=\"pentagon\" style=\"text-align: center;\">Fail</td>");
        out.println("    <td class=\"pentagon\" style=\"text-align: center;\">" + (testMessage.isResultAccepted() ? "Yes" : "No") + "</td>");
        out.println("    <td class=\"pentagon\"><a class=\"pentagonTestMessageFail\" href=\"javascript: void(0);\" onclick=\"loadDetails('"
            + testMessage.getTestMessageId() + "');\">" + testMessage.getTestCaseDescription() + "</a></td>");
        out.println("  </tr>");
      }
      out.println("</table>");
    }
  }

  public static void printTestMessageListPass(PrintWriter out, List<TestMessage> testMessageList)
  {
    if (testMessageList.size() > 0)
    {
      out.println("<table class=\"pentagon\">");
      out.println("  <tr class=\"pentagon\">");
      out.println("    <th class=\"pentagon\">Status</th>");
      out.println("    <th class=\"pentagon\">Accepted</th>");
      out.println("    <th class=\"pentagon\">Description</th>");
      out.println("  </tr>");
      for (TestMessage testMessage : testMessageList)
      {
        out.println("  <tr class=\"pentagon\">");
        out.println("    <td class=\"pentagon\" style=\"text-align: center;\">Pass</td>");
        out.println("    <td class=\"pentagon\" style=\"text-align: center;\">" + (testMessage.isResultAccepted() ? "Yes" : "No") + "</td>");
        String testMessageLink = createLink(testMessage);
        out.println("    <td class=\"pentagon\">" + testMessageLink + "</td>");
        out.println("  </tr>");
      }
      out.println("</table>");
    }
  }

  public static String createLink(TestMessage testMessage)
  {
    String testMessageLink = createLink(testMessage, testMessage.getTestCaseDescription());
    return testMessageLink;
  }

  public static String createLink(TestMessage testMessage, String label)
  {
    String testMessageLink = "<a class=\"pentagonTestMessagePass\" href=\"javascript: void(0);\" onclick=\"loadDetails('"
        + testMessage.getTestMessageId() + "');\">" + label + "</a>";
    return testMessageLink;
  }
  
  public void printTestMessageListFailForQuery(PrintWriter out, List<TestMessage> testMessageList)
  {
    String classString = "pentagonTestMessageFail";
    printTestMessageListFailForQuery(out, testMessageList, classString);
  }

  public void printTestMessageListFailForQuery(PrintWriter out, List<TestMessage> testMessageList, String classString)
  {
    if (testMessageList.size() > 0)
    {
      out.println("<table class=\"pentagon\">");
      out.println("  <tr class=\"pentagon\">");
      out.println("    <th class=\"pentagon\">Status</th>");
      out.println("    <th class=\"pentagon\">Response</th>");
      out.println("    <th class=\"pentagon\">Store Status</th>");
      out.println("    <th class=\"pentagon\">Description</th>");
      out.println("  </tr>");
      for (TestMessage testMessage : testMessageList)
      {
        out.println("  <tr class=\"pentagon\">");
        out.println("    <td class=\"pentagon\" style=\"text-align: center;\">" + testMessage.getResultStatus() + "</td>");
        out.println("    <td class=\"pentagon\" style=\"text-align: center;\">" + testMessage.getResultQueryType() + "</td>");
        out.println("    <td class=\"pentagon\">" + testMessage.getResultStoreStatusForDisplay() + "</td>");
        out.println("    <td class=\"pentagon\"><a class=\"" + classString + "\" href=\"javascript: void(0);\" onclick=\"loadDetails('"
            + testMessage.getTestMessageId() + "');\">" + testMessage.getTestCaseDescription() + "</a></td>");
        out.println("  </tr>");
      }
      out.println("</table>");
    }
  }

  public void printTestMessageListPassForQuery(PrintWriter out, List<TestMessage> testMessageList)
  {
    String classString = "pentagonTestMessagePass";
    printTestMessageListFailForQuery(out, testMessageList, classString);
  }

}
