package org.openimmunizationsoftware.dqa.cm.logic;

import java.util.List;
import java.util.Random;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.openimmunizationsoftware.dqa.cm.model.User;
import org.openimmunizationsoftware.dqa.cm.model.UserType;

public class UserLogic
{
  
  public static final String DQA_INITIAL = "DQA Initial";
  public static final String CDC_IISSB = "CDC IISSB";
  public static final String DQACM = "DQAcm";
  
  
  public static User getUser(int userId, Session dataSession)
  {
    User user = (User) dataSession.get(User.class, userId);
    return user;
  }

  public static List<User> getUserList(Session dataSession)
  {
    List<User> userList;
    Query query = dataSession.createQuery("from User order by userName");
    userList = query.list();
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
    }
    return user;
  }

  public static void createUser(User user, Session dataSession)
  {
    Transaction transaction = dataSession.beginTransaction();
    user.setPassword(generatePassword());
    if (user.getUserType() == null)
    {
      user.setUserType(UserType.EXPERT);
    }
    dataSession.save(user);
    transaction.commit();
  }

  public static void updateUser(User user, Session dataSession)
  {
    Transaction transaction = dataSession.beginTransaction();
    dataSession.update(user);
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
