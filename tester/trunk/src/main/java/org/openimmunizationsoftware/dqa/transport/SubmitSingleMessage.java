package org.openimmunizationsoftware.dqa.transport;

import java.util.HashMap;
import java.util.Map;

public class SubmitSingleMessage
{
  protected String username = "";
  protected String password = "";
  protected String facilityID = "";
  protected String hl7Message = "";
  protected String accessDenied = null;
  protected boolean debug = false;
  Map<String, Object> attributeMap = new HashMap<String, Object>();
  
  public Object getAttribute(String key)
  {
    return attributeMap.get(key);
  }
  
  public void setAttribute(String key, Object obj)
  {
    attributeMap.put(key,  obj);
  }
  
  public boolean isAccessDenied()
  {
    return accessDenied != null;
  }
  
  public String getUsername()
  {
    return username;
  }
  public void setUsername(String username)
  {
    this.username = username;
  }
  public String getPassword()
  {
    return password;
  }
  public void setPassword(String password)
  {
    this.password = password;
  }
  public String getFacilityID()
  {
    return facilityID;
  }
  public void setFacilityID(String facilityID)
  {
    this.facilityID = facilityID;
  }
  public String getHl7Message()
  {
    return hl7Message;
  }
  public void setHl7Message(String hl7Message)
  {
    this.hl7Message = hl7Message;
  }
  public String getAccessDenied()
  {
    return accessDenied;
  }
  public void setAccessDenied(String accessDenied)
  {
    this.accessDenied = accessDenied;
  }
  public boolean isDebug()
  {
    return debug;
  }
  public void setDebug(boolean debug)
  {
    this.debug = debug;
  }
}
