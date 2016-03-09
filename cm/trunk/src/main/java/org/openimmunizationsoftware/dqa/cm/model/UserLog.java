package org.openimmunizationsoftware.dqa.cm.model;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

public class UserLog
{
  private int userLogId = 0;
  private User user = null;
  private String servletName = "";
  private StringBuilder requestParameters = new StringBuilder();
  private String exceptionText = null;
  private Throwable exception = null;
  private Date startTime = null;
  private Date endTime = null;
  private int responseMs = 0;

  public int getUserLogId()
  {
    return userLogId;
  }

  public void setUserLogId(int userLogId)
  {
    this.userLogId = userLogId;
  }

  public User getUser()
  {
    return user;
  }

  public void setUser(User user)
  {
    this.user = user;
  }

  public String getServletName()
  {
    return servletName;
  }

  public void setServletName(String servletName)
  {
    this.servletName = servletName;
  }

  public String getRequestParameters()
  {
    return requestParameters.toString();
  }

  public void addRequestParameters(String name, String value)
  {
    if (requestParameters.length() > 0)
    {
      requestParameters.append("&");
    }
    try
    {
      requestParameters.append(URLEncoder.encode(name, "UTF-8"));
      requestParameters.append("=");
      requestParameters.append(value);
    } catch (UnsupportedEncodingException uee)
    {
      uee.printStackTrace();
    }
  }

  public void setRequestParameters(String requestParameters)
  {
    this.requestParameters = new StringBuilder(requestParameters);
  }

  public String getExceptionText()
  {
    if (exception != null && exceptionText == null)
    {
      StringWriter sw = new StringWriter();
      PrintWriter pw = new PrintWriter(sw);
      exception.printStackTrace(pw);
      exceptionText = sw.toString();
    }
    return exceptionText;
  }

  public void setExceptionText(String exceptionText)
  {
    this.exceptionText = exceptionText;
  }

  public Throwable getException()
  {
    return exception;
  }

  public void setException(Throwable exception)
  {
    this.exception = exception;
  }

  public Date getStartTime()
  {
    return startTime;
  }

  public void setStartTime(Date startTime)
  {
    this.startTime = startTime;
  }

  public Date getEndTime()
  {
    return endTime;
  }

  public void setEndTime(Date endTime)
  {
    this.endTime = endTime;
  }

  public int getResponseMs()
  {
    return responseMs;
  }

  public void setResponseMs(int responseMs)
  {
    this.responseMs = responseMs;
  }
}
