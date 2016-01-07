package org.openimmunizationsoftware.dqa.cm.logic;

import java.util.List;
import java.util.Random;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.openimmunizationsoftware.dqa.cm.model.Application;
import org.openimmunizationsoftware.dqa.cm.model.ApplicationUser;
import org.openimmunizationsoftware.dqa.cm.model.ReportUser;
import org.openimmunizationsoftware.dqa.cm.model.User;
import org.openimmunizationsoftware.dqa.cm.model.UserSetting;
import org.openimmunizationsoftware.dqa.cm.model.UserType;

public class UserLogic
{

  public static final String DQA_INITIAL = "DQA Initial";
  public static final String CDC_IISSB = "CDC IISSB";
  public static final String DQACM = "DQAcm";

  public static User getUser(int userId, Session dataSession)
  {
    User user = (User) dataSession.get(User.class, userId);
    user.setApplicationUserList(getApplicationUserList(user, dataSession));
    if (user.getApplicationUserList().size() == 1)
    {
      user.setApplicationUser(user.getApplicationUserList().get(0));
    }
    setupUser(dataSession, user);
    return user;
  }

  public static List<User> getUserList(Session dataSession)
  {
    List<User> userList;
    Query query = dataSession.createQuery("from User order by userName");
    userList = query.list();
    for (User user : userList)
    {
      user.setApplicationUserList(getApplicationUserList(user, dataSession));
      if (user.getApplicationUserList().size() == 1)
      {
        user.setApplicationUser(user.getApplicationUserList().get(0));
      }
      setupUser(dataSession, user);
    }

    return userList;
  }

  public static User getUser(String userName, Session dataSession)
  {
    User user = null;
    Query query = dataSession.createQuery("from User where userName = ?");
    query.setParameter(0, userName);
    List<User> userList = query.list();
    if (userList.size() > 0)
    {
      user = userList.get(0);
      user.setApplicationUserList(getApplicationUserList(user, dataSession));
      if (user.getApplicationUserList().size() == 1)
      {
        user.setApplicationUser(user.getApplicationUserList().get(0));
      }
      setupUser(dataSession, user);
    }
    return user;
  }

  public static void setupUser(Session dataSession, User user)
  {
    {
      user.getUserSettingMap().clear();
      Query query = dataSession.createQuery("from UserSetting where user = ?");
      query.setParameter(0, user);
      List<UserSetting> userSettingList = query.list();
      for (UserSetting userSetting : userSettingList)
      {
        user.getUserSettingMap().put(userSetting.getSettingKey(), userSetting);
      }
    }
    {
      user.getReportUserMap().clear();
      Query query = dataSession.createQuery("from ReportUser where user = ?");
      query.setParameter(0, user);
      List<ReportUser> reportUserList = query.list();
      for (ReportUser reportUser : reportUserList)
      {
        user.getReportUserMap().put(reportUser.getTestParticipant(), reportUser);
      }
    }
  }

  public static List<ApplicationUser> getApplicationUserList(User user, Session dataSession)
  {
    Query query = dataSession.createQuery("from ApplicationUser where user = ?");
    query.setParameter(0, user);
    return query.list();
  }

  public static void createOrUpdateApplicationUser(User user, Application application, UserType userType, Session dataSession)
  {
    ApplicationUser applicationUser = null;
    for (ApplicationUser au : user.getApplicationUserList())
    {
      if (au.getApplication() == application)
      {
        applicationUser = au;
        break;
      }
    }
    if (userType == null && applicationUser != null)
    {
      Transaction transaction = dataSession.beginTransaction();
      dataSession.delete(applicationUser);
      user.getApplicationUserList().remove(applicationUser);
      transaction.commit();
    } else if (userType != null && applicationUser == null)
    {
      Transaction transaction = dataSession.beginTransaction();
      applicationUser = new ApplicationUser();
      applicationUser.setApplication(application);
      applicationUser.setUser(user);
      applicationUser.setUserType(userType);
      dataSession.save(applicationUser);
      user.getApplicationUserList().add(applicationUser);
      transaction.commit();
    } else if (userType != null && applicationUser != null)
    {
      Transaction transaction = dataSession.beginTransaction();
      applicationUser.setUserType(userType);
      dataSession.update(applicationUser);
      transaction.commit();
    }

  }

  public static void createUser(User user, Application application, UserType userType, Session dataSession)
  {
    Transaction transaction = dataSession.beginTransaction();
    user.setPassword(generatePassword());
    dataSession.save(user);
    ApplicationUser applicationUser = new ApplicationUser();
    applicationUser.setApplication(application);
    applicationUser.setUser(user);
    applicationUser.setUserType(userType);
    dataSession.save(applicationUser);
    transaction.commit();
  }

  public static void updateUser(User user, Session dataSession)
  {
    Transaction transaction = dataSession.beginTransaction();
    dataSession.update(user);
    for (ReportUser reportUser : user.getReportUserMap().values())
    {
      dataSession.saveOrUpdate(reportUser);
    }
    for (UserSetting userSetting : user.getUserSettingMap().values())
    {
      dataSession.saveOrUpdate(userSetting);
    }
    transaction.commit();
  }
  
  public static void resetPassword(User user, Session dataSession)
  {
    Transaction transaction = dataSession.beginTransaction();
    user.setPassword(generatePassword());
    dataSession.update(user);
    transaction.commit();
  }

  private static String generatePassword()
  {
    Random random = new Random();
    String s = "";
    for (int i = 0; i < 6; i++)
    {
      s = s + ((char) (65 + random.nextInt(26)));
    }
    return s;
  }
}
