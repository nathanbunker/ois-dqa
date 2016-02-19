package org.openimmunizationsoftware.dqa.cm.logic;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.cm.model.User;

public class MailManager
{
  public static final int DQA_INITIAL_USER_ID = 2;

  private String reply = "";
  private String smtpsPassword = "";
  private int smtpsPort = 0;
  private String smtpsUsername = "";
  private boolean useSmtps = false;
  private String address = "";
  private String host = "";

  public MailManager(Session dataSession) {
    User u = UserLogic.getUser(DQA_INITIAL_USER_ID, dataSession);
    reply = u.getUserSetting(User.USER_SETTING_EMAIL_MANAGER_REPLY, "");
    smtpsPassword = u.getUserSetting(User.USER_SETTING_EMAIL_MANAGER_SMTPS_PASSWORD, "");
    smtpsPort = u.getUserSetting(User.USER_SETTING_EMAIL_MANAGER_SMTPS_PORT, 0);
    smtpsUsername = u.getUserSetting(User.USER_SETTING_EMAIL_MANAGER_SMTPS_USERNAME, "");
    useSmtps = u.getUserSetting(User.USER_SETTING_EMAIL_MANAGER_USE_SMTPS, false);
    address = u.getUserSetting(User.USER_SETTING_EMAIL_MANAGER_ADDRESS, "");
    host = u.getUserSetting(User.USER_SETTING_EMAIL_MANAGER_SMTPS_HOST, "");
  }

  public void sendEmail(String subject, String body, String to) throws Exception
  {
    if (useSmtps)
    {
      Properties props = System.getProperties();

      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", host);
      props.put("mail.smtp.port", "587");

      javax.mail.Session session = javax.mail.Session.getInstance(props, new javax.mail.Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication()
        {
          return new PasswordAuthentication(smtpsUsername, smtpsPassword);
        }
      });

      try
      {

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(reply));
        String[] t = to.split("\\,");
        InternetAddress[] addresses = new InternetAddress[t.length];
        for (int i = 0; i < t.length; i++)
        {
          addresses[i] = new InternetAddress(t[i].trim());
        }
        message.setRecipients(Message.RecipientType.TO, addresses);
        message.setSubject(subject);
        message.setText(body);
        message.setContent(body, "text/html; charset=UTF-8");
        message.setHeader("X-Mailer", "Tracker");
        Transport.send(message);
      } catch (MessagingException e)
      {
        throw new RuntimeException(e);
      }

    } else
    {
      Properties props = new Properties();
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "false");
      javax.mail.Session session = javax.mail.Session.getInstance(props, null);
      MimeMessage msg = new MimeMessage(session);
      msg.setFrom(new InternetAddress(reply));
      String[] t = to.split("\\,");
      InternetAddress[] addresses = new InternetAddress[t.length];
      for (int i = 0; i < t.length; i++)
      {
        addresses[i] = new InternetAddress(t[i].trim());
      }
      msg.setRecipients(Message.RecipientType.TO, addresses);
      msg.setSubject(subject);
      msg.setSentDate(new Date());
      msg.setContent(body, "text/html; charset=UTF-8");
      msg.setHeader("X-Mailer", "Tracker");
      Transport tr = session.getTransport("smtp");
      tr.connect(address, smtpsUsername, smtpsPassword);
      msg.saveChanges();
      tr.sendMessage(msg, addresses);
      tr.close();
    }
  }
}
