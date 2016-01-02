package org.openimmunizationsoftware.dqa.cm.servlet;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.tr.model.ProfileField;
import org.openimmunizationsoftware.dqa.tr.model.ProfileUsage;
import org.openimmunizationsoftware.dqa.tr.model.ProfileUsageValue;
import org.openimmunizationsoftware.dqa.tr.profile.Enforcement;
import org.openimmunizationsoftware.dqa.tr.profile.Implementation;
import org.openimmunizationsoftware.dqa.tr.profile.ProfileCategory;
import org.openimmunizationsoftware.dqa.tr.profile.Usage;

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
      String location = req.getParameter("location");

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

      ProfileUsage profileUsageBase = ProfileUsage.getBaseProfileUsage(dataSession);

      ProfileField profileField = null;
      ProfileUsageValue profileUsageValue = null;

      if (req.getParameter(PARAM_PROFILE_FIELD_ID) != null)
      {
        profileField = (ProfileField) dataSession.get(ProfileField.class, Integer.parseInt(req.getParameter(PARAM_PROFILE_FIELD_ID)));
        Query query = dataSession.createQuery("from ProfileUsageValue where profileField = ? and profileUsage = ?");
        query.setParameter(0, profileField);
        query.setParameter(1, profileUsage);
        List<ProfileUsageValue> profileUsageValueList = query.list();
        if (profileUsageValueList.size() > 0)
        {
          profileUsageValue = profileUsageValueList.get(0);
        }
      }

      if (req.getParameter(PARAM_PROFILE_USAGE_VALUE_ID) != null)
      {
        int profileUsageValueId = Integer.parseInt(req.getParameter(PARAM_PROFILE_USAGE_VALUE_ID));
        profileUsageValue = (ProfileUsageValue) dataSession.get(ProfileUsageValue.class, profileUsageValueId);
        profileUsage = profileUsageValue.getProfileUsage();
        profileField = profileUsageValue.getProfileField();
      } else
      {
        String fieldName = req.getParameter(PARAM_FIELD_NAME);
        if (fieldName != null && !fieldName.equals(""))
        {
          if (fieldName.startsWith("RXA") || fieldName.startsWith("ORC") || fieldName.startsWith("TQ1") || fieldName.startsWith("TQ2")
              || fieldName.startsWith("RXR") || fieldName.startsWith("OBX") || fieldName.startsWith("NTE"))
          {
            fieldName = "Admin " + fieldName;
          }
          // must be a segment or data type name
          Query query = dataSession.createQuery("from ProfileField where fieldName = ?");
          query.setParameter(0, fieldName);
          List<ProfileField> profileFieldList = query.list();
          if (profileFieldList.size() > 0)
          {
            profileField = profileFieldList.get(0);
            profileUsageValue = getProfileUsageValue(dataSession, profileUsage, profileField);
          }
        }
      }

      if (profileUsageValue == null && profileField != null)
      {
        profileUsageValue = getProfileUsageValue(dataSession, profileUsageBase, profileField);
      }

      String show = req.getParameter(PARAM_SHOW);
      if (show == null)
      {
        show = "";
      }
      createHeaderForGuide(webSession);

      if (location == null)
      {
        if (profileUsageValue != null && profileUsageValue.getLinkDefinition() != null && !profileUsageValue.getLinkDefinition().equals(""))
        {
          location = profileUsageValue.getLinkDefinition();
        } else if (profileUsage != null && profileUsage.getLinkGuide() != null && !profileUsage.getLinkGuide().equals(""))
        {
          location = profileUsage.getLinkGuide();
        }
      }

      int width = 850;
      int height = 850;
      File file = null;
      if (location != null)
      {
        file = GuideImageServlet.getImageFile(location, profileUsage);
        if (file != null && file.exists())
        {
          BufferedImage bimg = ImageIO.read(file);
          width = bimg.getWidth();
          height = bimg.getHeight();
          if (height > width)
          {
            height = (int) (((double) height / width) * 850);
            width = 850;
          } else
          {
            width = (int) (((double) width / height) * 850);
            height = 850;
          }
        }
      }
      out.println("<div style=\"float: left; width: 325px; margin-right: 10px; \">");
      printInformationPanel(out, location, profileUsage, profileUsageBase, profileField, profileUsageValue);
      out.println("</div>");
      out.println(
          "<div style=\"display: inline-block; width: " + (width + 2) + "px; border-style: solid; border-width: 2px; background-color: #ffffff;\">");
      if (location == null)
      {
        out.println("<h2 style=\"text-align: center; position: fixed;  top: 15px; left: " + ((width - 300) / 2 + 325) + "px; width: 300px;\">"
            + profileUsage + "</h2>");
        out.println("<p>&nbsp;</p>");
        out.println("<p>&nbsp;</p>");
        out.println("<p>&nbsp;</p>");
        out.println("<p>&nbsp;</p>");
        out.println("<p>&nbsp;</p>");
        out.println("<p>&nbsp;</p>");
        out.println("<h2 style=\"text-align: center; color: red; \">Guide Not Available</h2>");
        out.println("<p>&nbsp;</p>");
        out.println("<p>&nbsp;</p>");
        out.println("<p>&nbsp;</p>");
        out.println("<p>&nbsp;</p>");
        out.println("<p>&nbsp;</p>");
        out.println("<p>&nbsp;</p>");
        out.println("<p>&nbsp;</p>");
        out.println("<p>&nbsp;</p>");
        out.println("<p>&nbsp;</p>");
        out.println("<p>&nbsp;</p>");
        out.println("<p>&nbsp;</p>");
        out.println("<p>&nbsp;</p>");
      } else
      {
        if (file != null && file.exists())
        {
          int page = GuideImageServlet.getPage(location);
          String link = "guide?" + GuideServlet.PARAM_PROFILE_USAGE_ID + "=" + profileUsage.getProfileUsageId();
          String base = GuideImageServlet.getBase(location);
          if (profileUsageValue != null)
          {
            link += "&" + GuideServlet.PARAM_PROFILE_USAGE_VALUE_ID + "=" + profileUsageValue.getProfileUsageValueId();
          }
          link += "&" + GuideServlet.PARAM_LOCATION + "=" + base + "/";
          if (page > 0)
          {
            out.println(
                "<a style=\"float: left; width: 75px; border-style: solid; border-width: 1px; text-align: center; margin: 3px; padding: 3px;\" href=\""
                    + link + (page - 1) + "\">Previous</a>");
          }
          if (page != -1)
          {
            out.println(
                "<a style=\"float:right; width: 75px; border-style: solid; border-width: 1px; text-align: center; margin: 3px; padding: 3px;\" href=\""
                    + link + (page + 1) + "\">Next</a>");
          }
          out.println("<h2 style=\"text-align: center; \">" + profileUsage + "</h2>");
          String imageLink = "guideImage?" + GuideImageServlet.PARAM_PROFILE_USAGE_ID + "=" + profileUsage.getProfileUsageId() + "&"
              + GuideImageServlet.PARAM_LOCATION + "=" + URLEncoder.encode(location, "UTF-8");
          out.println("<img src=\"" + imageLink + "\" height=\"" + height + " width=\"" + width + "\"/>");
          out.println("<br/><p style=\"text-align: center;\">" + location + "</p>");
        } else
        {
          out.println("<p class=\"guide\">Unable to find location " + location + "</p>");
        }
      }

      out.println("</div>");
      out.println("<div style=\"float: right; width: 250px;\">");
      out.println("<h4>All IIS Guides</h4>");
      out.println("<ul style=\"font-size: 0.9em; \">");
      Query query = dataSession.createQuery("from ProfileUsage where category = 'IIS' order by label, version");
      List<ProfileUsage> profileUsageList = query.list();
      for (ProfileUsage pu : profileUsageList)
      {
        out.println("<li>");
        printGuideLink(out, profileField, pu);
        out.println("</li>");
      }
      out.println("</ul>");
      out.println("</div>");

      createFooter(webSession);
    } finally
    {
      out.close();
    }

  }

  public void printInformationPanel(PrintWriter out, String location, ProfileUsage profileUsage, ProfileUsage profileUsageBase,
      ProfileField profileField, ProfileUsageValue profileUsageValue) throws UnsupportedEncodingException
  {

    if (profileField != null)
    {
      out.println("<h3>" + profileField.getFieldName() + " " + profileField.getDescription() + "</h3>");
      if (profileUsageValue != null)
      {
        out.println("<table class=\"guide\" width=\"100%\">");
        out.println("  <caption class=\"guide\">Testing " + profileUsage + "</caption>");
        if (profileUsageValue.getProfileUsage().getCategory() != ProfileCategory.US)
        {
          out.println("  <tr class=\"guide\">");
          out.println("    <th class=\"guide\">Test Usage</th>");
          out.println("    <td class=\"guide\">" + profileUsageValue.getUsageString() + "</td>");
          out.println("  </tr>");
          if (profileUsageValue.getUsageDetected() != null && profileUsageValue.getUsageDetected() != Usage.NOT_DEFINED)
          {
            out.println("  <tr class=\"guide\">");
            out.println("    <th class=\"guide\">Detected Usage</th>");
            out.println("    <td class=\"guide\">" + profileUsageValue.getUsageDetected() + "</td>");
            out.println("  </tr>");
          }
          if (profileUsageValue.getEnforcement() != null && profileUsageValue.getEnforcement() != Enforcement.NOT_DEFINED)
          {
            out.println("  <tr class=\"guide\">");
            out.println("    <th class=\"guide\">Enforcement</th>");
            out.println("    <td class=\"guide\">" + profileUsageValue.getEnforcement().getDescription() + "</td>");
            out.println("  </tr>");
          }
          if (profileUsageValue.getImplementation() != null && profileUsageValue.getImplementation() != Implementation.NOT_DEFINED)
          {
            out.println("  <tr class=\"guide\">");
            out.println("    <th class=\"guide\">Implementation</th>");
            out.println("    <td class=\"guide\">" + profileUsageValue.getImplementation().getDescription() + "</td>");
            out.println("  </tr>");
          }
        }
        if (profileUsageValue.getLinkDefinition() != null && !profileUsageValue.getLinkDefinition().equals(""))
        {
          String link = createLink(profileUsage, profileField, profileUsageValue, profileUsageValue.getLinkDefinition());
          out.println("  <tr class=\"guide\">");
          out.println("    <th class=\"guide\">Definition</th>");
          out.println("    <td class=\"guide\"><a href=\"" + link + "\">" + profileUsageValue.getLinkDefinition() + "</a></td>");
          out.println("  </tr>");
        }
        if (profileUsageValue.getLinkDetail() != null && !profileUsageValue.getLinkDetail().equals(""))
        {
          String link = createLink(profileUsage, profileField, profileUsageValue, profileUsageValue.getLinkDetail());
          out.println("  <tr class=\"guide\">");
          out.println("    <th class=\"guide\">Detail</th>");
          out.println("    <td class=\"guide\"><a href=\"" + link + "\">" + profileUsageValue.getLinkDetail() + "</a></td>");
          out.println("  </tr>");
        }
        if (profileUsageValue.getLinkClarification() != null && !profileUsageValue.getLinkClarification().equals(""))
        {
          String link = createLink(profileUsage, profileField, profileUsageValue, profileUsageValue.getLinkClarification());
          out.println("  <tr class=\"guide\">");
          out.println("    <th class=\"guide\">Clarification</th>");
          out.println("    <td class=\"guide\"><a href=\"" + link + "\">" + profileUsageValue.getLinkClarification() + "</a></td>");
          out.println("  </tr>");
        }
        if (profileUsageValue.getLinkSupplement() != null && !profileUsageValue.getLinkSupplement().equals(""))
        {
          String link = createLink(profileUsage, profileField, profileUsageValue, profileUsageValue.getLinkSupplement());
          out.println("  <tr class=\"guide\">");
          out.println("    <th class=\"guide\">Supplement</th>");
          out.println("    <td class=\"guide\"><a href=\"" + link + "\">" + profileUsageValue.getLinkSupplement() + "</a></td>");
          out.println("  </tr>");
        }
      }
      out.println("</table>");
    }
    out.println("<h3>");
    printGuideLink(out, profileField, profileUsageBase);
    out.println("</h3>");
    if (profileField != null)
    {
      out.println("<table class=\"guide\" width=\"100%\">");
      out.println("  <caption class=\"guide\">From CDC Guide</caption>");
      out.println("  <tr class=\"guide\">");
      out.println("    <th class=\"guide\">Data Type</th>");
      out.println("    <td class=\"guide\">" + profileField.getDataType() + "</td>");
      out.println("  </tr>");
      out.println("  <tr class=\"guide\">");
      out.println("    <th class=\"guide\">Usage</th>");
      out.println("    <td class=\"guide\">" + profileField.getBaseUsage() + "</td>");
      out.println("  </tr>");
      if (profileField.getLength() != null && !profileField.getLength().equals(""))
      {
        out.println("  <tr class=\"guide\">");
        out.println("    <th class=\"guide\">Length</th>");
        out.println("    <td class=\"guide\">" + profileField.getLength() + "</td>");
        out.println("  </tr>");
      }
      if (profileField.getConditionalPredicate() != null && !profileField.getConditionalPredicate().equals(""))
      {
        out.println("  <tr class=\"guide\">");
        out.println("    <th class=\"guide\">Cond Pred</th>");
        out.println("    <td class=\"guide\">" + profileField.getConditionalPredicate() + "</td>");
        out.println("  </tr>");
      }
      if (profileField.getTableName() != null && !profileField.getTableName().equals(""))
      {
        out.println("  <tr class=\"guide\">");
        out.println("    <th class=\"guide\">Value Set</th>");
        out.println("    <td class=\"guide\">" + profileField.getTableName() + "</td>");
        out.println("  </tr>");
      }
      if (profileField.getComments() != null && !profileField.getComments().equals(""))
      {
        out.println("  <tr class=\"guide\">");
        out.println("    <th class=\"guide\">Comments</th>");
        out.println("    <td class=\"guide\">" + profileField.getComments() + "</td>");
        out.println("  </tr>");
      }
      out.println("</table>");
    }
  }

  public String createLink(ProfileUsage profileUsage, ProfileField profileField, ProfileUsageValue profileUsageValue, String loc)
      throws UnsupportedEncodingException
  {
    String link = "guide?" + PARAM_PROFILE_USAGE_ID + "=" + profileUsage.getProfileUsageId() + "&" + PARAM_PROFILE_USAGE_ID + "="
        + profileUsageValue.getProfileUsageValueId() + "&" + PARAM_PROFILE_FIELD_ID + "=" + profileField.getProfileFieldId() + "&" + PARAM_LOCATION
        + "=" + URLEncoder.encode(loc, "UTF-8");
    return link;
  }

  public void printGuideLink(PrintWriter out, ProfileField profileField, ProfileUsage pu)
  {
    String link = "guide?" + PARAM_PROFILE_USAGE_ID + "=" + pu.getProfileUsageId();
    if (profileField != null)
    {
      link += "&" + PARAM_PROFILE_FIELD_ID + "=" + profileField.getProfileFieldId();
    }
    if (pu.getCategory() == ProfileCategory.IIS)
    {
      if (pu.getVersion() == null || pu.getVersion().equals(""))
      {
        out.println("<a href=\"" + link + "\">" + pu.getLabel() + "</a>");
      } else
      {
        out.println("<a href=\"" + link + "\">" + pu.getLabel() + " - " + pu.getVersion() + "</a>");
      }
    } else
    {
      out.println("<a href=\"" + link + "\">" + pu + "</a>");
    }
  }

  public ProfileUsageValue getProfileUsageValue(Session dataSession, ProfileUsage profileUsage, ProfileField profileField)
  {
    Query query = dataSession.createQuery("from ProfileUsageValue where profileUsage = ? and profileField = ?");
    query.setParameter(0, profileUsage);
    query.setParameter(1, profileField);
    List<ProfileUsageValue> profileUsageValueList = query.list();
    if (profileUsageValueList.size() > 0)
    {
      return profileUsageValueList.get(0);
    }
    return null;
  }

}
