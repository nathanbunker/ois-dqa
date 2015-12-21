package org.openimmunizationsoftware.dqa.cm.servlet;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.tr.model.ProfileUsage;
import org.openimmunizationsoftware.dqa.tr.model.ProfileUsageValue;

public class GuideServlet extends HomeServlet
{
  public static final String PARAM_FIELD_NAME = "fieldName";
  public static final String PARAM_LOCATION = "location";
  public static final String PARAM_PROFILE_USAGE_ID = "profileUsageId";
  public static final String PARAM_PROFILE_USAGE_ID_COMPARE = "profileUsageIdCompare";
  public static final String PARAM_COMPARISON_TYPE = "comparisonType";
  public static final String PARAM_SHOW = "show";
  public static final String PARAM_PROFILE_FIELD_ID = "profileFieldId";
  public static final String PARAM_PROFILE_FIELD_ID_COPY_FROM = "profileFieldIdCopyFrom";
  public static final String PARAM_PROFILE_USAGE_VALUE_ID = "profileUsageValueId";
  public static final String PARAM_ = "";

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {
    doGet(req, resp);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {
    HttpSession webSession = setup(req, resp);
    UserSession userSession = (UserSession) webSession.getAttribute(USER_SESSION);
    Session dataSession = userSession.getDataSession();
    PrintWriter out = userSession.getOut();

    String action = req.getParameter(PARAM_ACTION);
    if (action == null)
    {
      action = "";
    }

    try
    {

      ProfileUsage profileUsage = null;
      int profileUsageId = 0;
      if (req.getParameter(PARAM_PROFILE_USAGE_ID) != null)
      {
        profileUsageId = Integer.parseInt(req.getParameter(PARAM_PROFILE_USAGE_ID));
        profileUsage = (ProfileUsage) dataSession.get(ProfileUsage.class, profileUsageId);
      } else if (userSession.getProfileUsageSelected() != null)
      {
        profileUsage = userSession.getProfileUsageSelected();
        profileUsageId = profileUsage.getProfileUsageId();
      }

      ProfileUsageValue profileUsageValue = null;
      if (req.getParameter(PARAM_PROFILE_USAGE_VALUE_ID) != null)
      {
        int profileUsageValueId = Integer.parseInt(req.getParameter(PARAM_PROFILE_USAGE_VALUE_ID));
        profileUsageValue = (ProfileUsageValue) dataSession.get(ProfileUsageValue.class, profileUsageValueId);
        profileUsage = profileUsageValue.getProfileUsage();
      }

      String location = req.getParameter("location");

      String show = req.getParameter(PARAM_SHOW);
      if (show == null)
      {
        show = "";
      }
      createHeader(webSession);

      out.println("<h2>" + profileUsage + "</h2>");
      out.println("<h3>" + profileUsageValue.getProfileField().getFieldName() + "</h3>");
      int page = GuideImageServlet.getPage(location);
      if (page != -1)
      {
        out.println("<h4>Page " + page + "</h4>");
        out.println("<p>");
        String base = GuideImageServlet.getBase(location);
        String link = "guide?" + GuideServlet.PARAM_PROFILE_USAGE_ID + "=" + profileUsage.getProfileUsageId() + "&"
            + GuideServlet.PARAM_PROFILE_USAGE_VALUE_ID + "=" + profileUsageValue.getProfileUsageValueId() + "&" + GuideServlet.PARAM_LOCATION + "="
            + base + "/";
        if (page > 0)
        {
          out.println("<a href=\"" + link + (page - 1) + "\">Previous</a>");
        }
        out.println("<a href=\"" + link + (page + 1) + "\">Next</a>");
        out.println("</p>");
      }
      File file = GuideImageServlet.getImageFile(location, profileUsage);
      if (file != null && file.exists())
      {
        BufferedImage bimg = ImageIO.read(file);
        int width = bimg.getWidth();
        int height = bimg.getHeight();
        if (height > width)
        {
          width = 750;
          height = (int) (((double) height / width) * width);
        } else
        {
          height = 750;
          width = (int) (((double) width / height) * height);
        }

        String imageLink = "guideImage?" + GuideImageServlet.PARAM_PROFILE_USAGE_ID + "=" + profileUsage.getProfileUsageId() + "&"
            + GuideImageServlet.PARAM_LOCATION + "=" + URLEncoder.encode(location, "UTF-8");
        out.println("<img src=\"" + imageLink + "\" height=\"" + height + " width=\"" + width + "\"/>");
      }

      createFooter(webSession);
    } finally
    {
      out.close();
    }

  }

}
