package org.openimmunizationsoftware.dqa.cm.servlet;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.tr.model.ProfileUsage;

public class GuideImageServlet extends HomeServlet
{

  public static final String PARAM_PROFILE_USAGE_ID = "profileUsageId";
  public static final String PARAM_LOCATION = "location";

  private static final String DIR1 = "C:/test/guides";
  private static final String DIR2 = "/home/oisptorg/ois-pt.org/data/guides";

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {
    HttpSession webSession = setupSkinny(req, resp);
    UserSession userSession = (UserSession) webSession.getAttribute(USER_SESSION);
    Session dataSession = userSession.getDataSession();

    String location = req.getParameter(PARAM_LOCATION);
    int profileUsageId = Integer.parseInt(req.getParameter(PARAM_PROFILE_USAGE_ID));
    ProfileUsage profileUsage = (ProfileUsage) dataSession.get(ProfileUsage.class, profileUsageId);

    File file;
    file = getImageFile(location, profileUsage);
    resp.setContentType("image/png");

    FileInputStream in = new FileInputStream(file);
    byte[] b = new byte[100];
    int length = 0;
    while ((length = in.read(b)) != -1)
    {
      resp.getOutputStream().write(b, 0, length);
    }
    in.close();
    resp.getOutputStream().close();

  }

  public static File getImageFile(String location, ProfileUsage profileUsage)
  {
    File file;
    File dir = new File(DIR2);
    if (!dir.exists())
    {
      dir = new File(DIR1);
    }
    dir = new File(dir, profileUsage.getCategory().toString());
    dir = new File(dir, profileUsage.getLabel());
    if (!profileUsage.getVersion().equals(""))
    {
      dir = new File(dir, profileUsage.getVersion());
    }
    String filename = location;
    String page = null;
    int slashPos = location.lastIndexOf('/');
    if (slashPos > 0)
    {
      filename = location.substring(0, slashPos);
      page = location.substring(slashPos + 1);
      filename = filename + "-" + page;
    }
    filename = filename + ".png";
    file = new File(dir, filename);
    return file;
  }

  public static int getPage(String location)
  {
    int slashPos = location.lastIndexOf('/');
    if (slashPos > 0)
    {
      try
      {
        return Integer.parseInt(location.substring(slashPos + 1));
      } catch (NumberFormatException nfe)
      {
        return -1;
      }
    }
    return -1;
  }

  public static String getBase(String location)
  {

    int slashPos = location.lastIndexOf('/');
    if (slashPos > 0)
    {
      return location.substring(0, slashPos);
    }
    return location;
  }
}
