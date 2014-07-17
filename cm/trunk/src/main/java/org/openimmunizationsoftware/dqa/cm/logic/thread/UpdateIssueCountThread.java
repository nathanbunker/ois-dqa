package org.openimmunizationsoftware.dqa.cm.logic.thread;

import java.util.List;

import org.hibernate.Query;
import org.openimmunizationsoftware.dqa.cm.logic.CodeTableInstanceLogic;
import org.openimmunizationsoftware.dqa.cm.model.CodeTableInstance;
import org.openimmunizationsoftware.dqa.cm.model.ReleaseVersion;
import org.openimmunizationsoftware.dqa.cm.model.User;

public class UpdateIssueCountThread extends LogicThread
{
  private ReleaseVersion release = null;

  public UpdateIssueCountThread(ReleaseVersion release, User user) {
    super(user.getUserId());
    this.release = release;
  }

  @Override
  public void run()
  {
    try
    {
      int issueCount = 0;
      out.println("<h1>Updating Issue Counts</h1>");
      Query query = dataSession.createQuery("from CodeTableInstance where release = ?");
      query.setParameter(0, release);
      List<CodeTableInstance> codeTableInstanceList = query.list();
      for (CodeTableInstance codeTableInstance : codeTableInstanceList)
      {
        out.println("<h2>" + codeTableInstance.getTableLabel() + "</h2>");
        CodeTableInstanceLogic.updateIssueCount(codeTableInstance, true, dataSession);
        out.println("<p>Issues found: " + codeTableInstance.getIssueCount() + "</p>");
        issueCount += codeTableInstance.getIssueCount();
      }
      out.println("<h2>Issue Counting Complete</h2>");
      out.println("<p>Total issues found: " + issueCount + "</p>");

    } catch (Exception e)
    {
      out.println("<p>Unable to complete because of exception " + e.getMessage() + "</p>");
      out.println("<pre>");
      e.printStackTrace(out);
      out.println("</pre>");
    } finally
    {
      out.close();
      complete = true;
    }

  }
}
