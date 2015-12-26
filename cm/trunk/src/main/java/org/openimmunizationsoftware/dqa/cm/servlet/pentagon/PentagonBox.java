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

  public void printTestMessageListPass(PrintWriter out, List<TestMessage> testMessageList)
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
        out.println("    <td class=\"pentagon\"><a class=\"pentagonTestMessagePass\" href=\"javascript: void(0);\" onclick=\"loadDetails('"
            + testMessage.getTestMessageId() + "');\">" + testMessage.getTestCaseDescription() + "</a></td>");
        out.println("  </tr>");
      }
      out.println("</table>");
    }
  }

}
