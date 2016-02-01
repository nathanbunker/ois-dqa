package org.openimmunizationsoftware.dqa.tr.model;

import java.io.Serializable;
import java.util.Date;

import org.openimmunizationsoftware.dqa.cm.model.User;

public class TestComment implements Serializable
{
  private int testCommentId = 0;
  private TestParticipant testParticipant = null;
  private TestComment reply = null;
  private User user = null;
  private TestMessage testMessage = null;
  private String testCaseCategory = "";
  private String commentText = "";
  private Date commentdate = null;
  private CommentMood commentMood = null;

  public String getCommentMoodString()
  {
    return commentMood == null ? "" : commentMood.toString();
  }

  public void setCommentMoodString(String commentMoodString)
  {
    if (commentMoodString == null || commentMoodString.equals(""))
    {
      this.commentMood = null;
    } else
    {
      this.commentMood = CommentMood.valueOf(commentMoodString);
    }
  }

  public int getTestCommentId()
  {
    return testCommentId;
  }

  public void setTestCommentId(int testCommentId)
  {
    this.testCommentId = testCommentId;
  }

  public TestParticipant getTestParticipant()
  {
    return testParticipant;
  }

  public void setTestParticipant(TestParticipant testParticipant)
  {
    this.testParticipant = testParticipant;
  }

  public TestComment getReply()
  {
    return reply;
  }

  public void setReply(TestComment reply)
  {
    this.reply = reply;
  }

  public User getUser()
  {
    return user;
  }

  public void setUser(User user)
  {
    this.user = user;
  }

  public TestMessage getTestMessage()
  {
    return testMessage;
  }

  public void setTestMessage(TestMessage testMessage)
  {
    this.testMessage = testMessage;
  }

  public String getTestCaseCategory()
  {
    return testCaseCategory;
  }

  public void setTestCaseCategory(String testCaseCategory)
  {
    this.testCaseCategory = testCaseCategory;
  }

  public String getCommentText()
  {
    return commentText;
  }

  public void setCommentText(String commentText)
  {
    this.commentText = commentText;
  }

  public Date getCommentdate()
  {
    return commentdate;
  }

  public void setCommentdate(Date commentdate)
  {
    this.commentdate = commentdate;
  }

  public CommentMood getCommentMood()
  {
    return commentMood;
  }

  public void setCommentMood(CommentMood commentMood)
  {
    this.commentMood = commentMood;
  }
}
