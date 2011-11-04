package org.openimmunizationsoftware.dqa.manager;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.openimmunizationsoftware.dqa.InitializationException;
import org.openimmunizationsoftware.dqa.db.model.Application;
import org.openimmunizationsoftware.dqa.db.model.KeyedSetting;

public class KeyedSettingManager
{
  private static KeyedSettingManager singleton = null;
  
  public static KeyedSettingManager getKeyedSettingManager()
  {
    if (singleton == null)
    {
      singleton = new KeyedSettingManager();
    }
    return singleton;
  }
  
  public static Application getApplication()
  {
    return getKeyedSettingManager().application;
  }
  
  private Map<String, KeyedSetting> keyedSettingsMap = new HashMap<String, KeyedSetting>();
  private Application application = null;
  private KeyedSettingManager parent = null;
  
  private KeyedSettingManager()
  {
    SessionFactory factory = OrganizationManager.getSessionFactory();
    Session session = factory.openSession();
    findApplicationToRun(session);
    Query query = session.createQuery("from KeyedSetting where objectCode = ? and objectId = ?");
    query.setParameter(0, "Application");
    query.setParameter(1, application.getApplicationId());
    List<KeyedSetting> keyedSettings = query.list();
    for (KeyedSetting keyedSetting: keyedSettings)
    {
     keyedSettingsMap.put(keyedSetting.getKeyedCode(), keyedSetting);
    }
    session.close();
  }
  
  public int getKeyedValueInt(String keyedCode, int defaultValue)
  {
    String value = getKeyedValue(keyedCode, String.valueOf(defaultValue));
    try
    {
      return Integer.parseInt(value);
    }
    catch (NumberFormatException nfe)
    {
      return defaultValue;
    }
  }
  
  public boolean getKeyedValueBoolean(String keyedCode, boolean defaultValue)
  {
    String value = getKeyedValue(keyedCode, defaultValue ? "Y" : "N");
    if (value.toUpperCase().startsWith("Y") || value.toUpperCase().startsWith("T"))
    {
      return true;
    }
    return false;
  }
  
  public String getKeyedValue(String keyedCode, String defaultValue)
  {
    KeyedSetting keyedSetting = keyedSettingsMap.get(keyedCode);
    if (keyedSetting == null)
    {
      if (parent != null)
      {
        return parent.getKeyedValue(keyedCode, defaultValue);
      }
      else
      {
        return defaultValue;
      }
    }
    return keyedSetting.getKeyedValue();
  }

  private void findApplicationToRun(Session session)
  {
    Query query = session.createQuery("from Application where runThis = true");
    List<Application> applications = query.list();
    if (applications.size() > 0)
    {
      application = applications.get(0);
    }
    else
    {
      throw new InitializationException("Unable to start application, no application is currently defined to run");
    }
  }
  
}
