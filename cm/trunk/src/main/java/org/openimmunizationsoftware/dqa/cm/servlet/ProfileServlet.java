package org.openimmunizationsoftware.dqa.cm.servlet;

import static org.immunizationsoftware.dqa.transform.ScenarioManager.SCENARIO_FULL_RECORD_FOR_PROFILING;
import static org.immunizationsoftware.dqa.transform.ScenarioManager.createTestCaseMessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.immunizationsoftware.dqa.transform.TestCaseMessage;
import org.immunizationsoftware.dqa.transform.Transformer;
import org.openimmunizationsoftware.dqa.tr.model.ProfileField;
import org.openimmunizationsoftware.dqa.tr.model.ProfileUsage;
import org.openimmunizationsoftware.dqa.tr.model.ProfileUsageValue;
import org.openimmunizationsoftware.dqa.tr.profile.CompatibilityConformance;
import org.openimmunizationsoftware.dqa.tr.profile.CompatibilityInteroperability;
import org.openimmunizationsoftware.dqa.tr.profile.Enforcement;
import org.openimmunizationsoftware.dqa.tr.profile.Implementation;
import org.openimmunizationsoftware.dqa.tr.profile.MessageAcceptStatus;
import org.openimmunizationsoftware.dqa.tr.profile.ProfileFieldType;
import org.openimmunizationsoftware.dqa.tr.profile.ProfileManager;
import org.openimmunizationsoftware.dqa.tr.profile.Usage;

@SuppressWarnings("serial")
public class ProfileServlet extends HomeServlet
{

  public static final String PARAM_PROFILE_USAGE_ID = "profileUsageId";
  public static final String PARAM_PROFILE_USAGE_ID_COMPARE = "profileUsageIdCompare";
  public static final String PARAM_COMPARISON_TYPE = "comparisonType";
  public static final String PARAM_SHOW = "show";
  public static final String PARAM_PROFILE_FIELD_ID = "profileFieldId";
  public static final String PARAM_PROFILE_FIELD_ID_COPY_FROM = "profileFieldIdCopyFrom";
  public static final String PARAM_PROFILE_USAGE_VALUE_ID = "profileUsageValueId";
  public static final String PARAM_ = "";

  public static final String SHOW_COMPARE = "Compare";
  public static final String SHOW_VIEW = "View";
  public static final String SHOW_EDIT = "Edit";
  public static final String SHOW_TRANSFORMS = "transforms";

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
    if (userSession.getUser() == null || userSession.getUser().getApplicationUser() == null
        || !userSession.getUser().getApplicationUser().getApplication().isApplicationAart())
    {
      sendToHome(req, resp);
      return;
    }

    String action = getAction(req);
    try
    {

      ProfileUsage profileUsageSelected = getProfileUsageSelected(req, userSession, dataSession);
      ProfileUsageValue profileUsageValueSelected = null;
      ProfileField profileFieldSelected = null;
      if (req.getParameter(PARAM_PROFILE_USAGE_VALUE_ID) != null)
      {
        int profileUsageValueId = Integer.parseInt(req.getParameter(PARAM_PROFILE_USAGE_VALUE_ID));
        profileUsageValueSelected = (ProfileUsageValue) dataSession.get(ProfileUsageValue.class, profileUsageValueId);
        profileFieldSelected = profileUsageValueSelected.getProfileField();
        profileUsageSelected = profileUsageValueSelected.getProfileUsage();
      }
      profileFieldSelected = getPorfileFieldSelected(req, dataSession, profileFieldSelected);
      setProfileUsageSelected(userSession, profileUsageSelected);
      ProfileUsage profileUsageCompare = getProfileUsageCompare(req, dataSession);

      createHeader(webSession);
      String comparisonType = getComparisonType(req);
      printCompareProfilesForm(userSession, dataSession, out, profileUsageSelected, profileUsageCompare, comparisonType);
      String show = getShow(req);
      if (show.equals(SHOW_COMPARE))
      {
        printShow(dataSession, out, profileUsageSelected, profileUsageCompare, comparisonType);
      } else if (show.equals(SHOW_VIEW))
      {
        printView(dataSession, out, profileUsageSelected);
      } else if (show.equals(SHOW_EDIT))
      {
        printEdit(req, dataSession, out, action, profileUsageSelected, profileUsageValueSelected);
      } else if (show.equals(SHOW_TRANSFORMS))
      {
        printTransforms(req, dataSession, out, action, profileFieldSelected);
      }
    }
    catch (Exception e)
    {
      handleError(e, webSession);
    } finally
    {
      createFooter(webSession);
    }

  }

  public void printTransforms(HttpServletRequest req, Session dataSession, PrintWriter out, String action, ProfileField profileFieldSelected)
      throws IOException
  {
    if (profileFieldSelected != null)
    {
      TestCaseMessage tcmFull = createTestCaseMessage(SCENARIO_FULL_RECORD_FOR_PROFILING);
      Transformer transformer = new Transformer();
      transformer.transform(tcmFull);
      TestCaseMessage testCaseMessagePresent = ProfileManager.getPresentTestCase(profileFieldSelected, tcmFull);
      TestCaseMessage testCaseMessageAbsent = ProfileManager.getAbsentTestCase(profileFieldSelected, tcmFull);
      out.println("<h2>" + profileFieldSelected.getFieldName() + "</h2>");
      String edit = req.getParameter("edit");
      if (edit == null)
      {
        edit = "";
      }
      out.println("<h3>Present Test Case</h3>");
      if (edit.equals("present"))
      {
        if (action.equals("Refresh") || action.equals("Save"))
        {
          boolean useTcmFull = req.getParameter("tcmFull") != null;
          if (useTcmFull)
          {
            testCaseMessagePresent = tcmFull;
            profileFieldSelected.setTransformsPresent(ProfileManager.USE_FULL_TEST_CASE);
          } else
          {
            String additionalTransformations = req.getParameter("additionalTransformations");
            profileFieldSelected.setTransformsPresent(additionalTransformations);
            if (testCaseMessagePresent == tcmFull)
            {
              testCaseMessagePresent = ProfileManager.getPresentTestCase(profileFieldSelected, tcmFull);
            } else
            {
              testCaseMessagePresent.setAdditionalTransformations(additionalTransformations);
            }
          }
          if (action.equals("Save"))
          {
            Transaction transaction = dataSession.beginTransaction();
            dataSession.update(profileFieldSelected);
            transaction.commit();
          }
        }
        String transformedMessage = null;
        String originalMessage = formatMessage(tcmFull.getMessageText());
        if (testCaseMessagePresent != tcmFull)
        {
          transformedMessage = formatMessage(transformer.transformAddition(tcmFull, testCaseMessagePresent.getAdditionalTransformations()));
        }
        out.println("<p>Starting Message</p>");
        if (transformedMessage != null)
        {
          out.println("<pre>" + addHovers(showDiff(originalMessage, transformedMessage)) + "</pre>");
        } else
        {
          out.println("<pre>" + originalMessage + "</pre>");
        }
        out.println("<p>Transforms</p>");
        out.println("<form action=\"profile\" method=\"POST\">");
        out.println("  <textarea name=\"additionalTransformations\" cols=\"70\" rows=\"10\" wrap=\"off\">"
            + testCaseMessagePresent.getAdditionalTransformations() + "</textarea></td>");
        out.println("  <br/>");
        out.println("  <input type=\"checkbox\" name=\"tcmFull\" value=\"true\"" + (testCaseMessagePresent == tcmFull ? " checked" : "")
            + "/> Use Full Message");
        out.println("  <input type=\"hidden\" name=\"" + PARAM_SHOW + "\" value=\"" + SHOW_TRANSFORMS + "\"/>");
        out.println(
            "  <input type=\"hidden\" name=\"" + PARAM_PROFILE_FIELD_ID + "\" value=\"" + profileFieldSelected.getProfileFieldId() + "\"/>");
        out.println("  <input type=\"hidden\" name=\"edit\" value=\"present\"/>");
        out.println("  <br/>");
        out.println("  <input type=\"submit\" name=\"action\" value=\"Refresh\"/>");
        out.println("  <input type=\"submit\" name=\"action\" value=\"Save\"/>");
        out.println("</form>");
        if (testCaseMessagePresent != tcmFull)
        {
          out.println("<p>Final Message</p>");
          out.println("<pre>" + addHovers(showDiff(transformedMessage, originalMessage)) + "</pre>");
        }
      } else
      {
        if (testCaseMessagePresent == tcmFull)
        {
          out.println("<p>Full Test Case</p>");
        } else if (!testCaseMessagePresent.hasIssue())
        {
          out.println("<p>Not Defined</p>");
        } else
        {
          out.println("<pre>" + testCaseMessagePresent.getAdditionalTransformations() + "</pre>");
        }
        String link = "profile?" + PARAM_SHOW + "=" + SHOW_TRANSFORMS + "&" + PARAM_PROFILE_FIELD_ID + "="
            + profileFieldSelected.getProfileFieldId() + "&edit=present";
        out.println("<p><a href=\"" + link + "\">Edit</a></p>");
      }
      out.println("<h3>Absent Test Case</h3>");
      if (edit.equals("absent"))
      {
        if (action.equals("Refresh") || action.equals("Save"))
        {
          boolean useTcmFull = req.getParameter("tcmFull") != null;
          if (useTcmFull)
          {
            testCaseMessagePresent = tcmFull;
            profileFieldSelected.setTransformsAbsent(ProfileManager.USE_FULL_TEST_CASE);
          } else
          {
            String additionalTransformations = req.getParameter("additionalTransformations");
            profileFieldSelected.setTransformsAbsent(additionalTransformations);
            if (testCaseMessageAbsent == tcmFull)
            {
              testCaseMessageAbsent = ProfileManager.getAbsentTestCase(profileFieldSelected, tcmFull);
            } else
            {
              testCaseMessageAbsent.setAdditionalTransformations(additionalTransformations);
            }
          }
          if (action.equals("Save"))
          {
            Transaction transaction = dataSession.beginTransaction();
            dataSession.update(profileFieldSelected);
            transaction.commit();
          }
        }
        String transformedMessage = null;
        String originalMessage = formatMessage(tcmFull.getMessageText());
        if (testCaseMessageAbsent != tcmFull)
        {
          transformedMessage = formatMessage(transformer.transformAddition(tcmFull, testCaseMessageAbsent.getAdditionalTransformations()));
        }
        out.println("<p>Starting Message</p>");
        if (transformedMessage != null)
        {
          out.println("<pre>" + addHovers(showDiff(originalMessage, transformedMessage)) + "</pre>");
        } else
        {
          out.println("<pre>" + originalMessage + "</pre>");
        }
        out.println("<p>Transforms</p>");
        out.println("<form action=\"profile\" method=\"POST\">");
        out.println("  <textarea name=\"additionalTransformations\" cols=\"70\" rows=\"10\" wrap=\"off\">"
            + testCaseMessageAbsent.getAdditionalTransformations() + "</textarea></td>");
        out.println("  <input type=\"hidden\" name=\"" + PARAM_SHOW + "\" value=\"" + SHOW_TRANSFORMS + "\"/>");
        out.println(
            "  <input type=\"hidden\" name=\"" + PARAM_PROFILE_FIELD_ID + "\" value=\"" + profileFieldSelected.getProfileFieldId() + "\"/>");
        out.println("  <input type=\"hidden\" name=\"edit\" value=\"absent\"/>");
        out.println("  <br/>");
        out.println("  <input type=\"checkbox\" name=\"tcmFull\" value=\"true\"" + (testCaseMessageAbsent == tcmFull ? " checked" : "")
            + "/> Use Full Message");
        out.println("  <br/>");
        out.println("  <input type=\"submit\" name=\"action\" value=\"Refresh\"/>");
        out.println("  <input type=\"submit\" name=\"action\" value=\"Save\"/>");
        out.println("</form>");
        if (testCaseMessageAbsent != tcmFull)
        {
          out.println("<p>Final Message</p>");
          out.println("<pre>" + addHovers(showDiff(transformedMessage, originalMessage)) + "</pre>");
        }
      } else
      {
        if (testCaseMessageAbsent == tcmFull)
        {
          out.println("<p>Full Test Case</p>");
        } else if (!testCaseMessageAbsent.hasIssue())
        {
          out.println("<p>Not Defined</p>");
        } else
        {
          out.println("<pre>" + testCaseMessageAbsent.getAdditionalTransformations() + "</pre>");
        }
        String link = "profile?" + PARAM_SHOW + "=" + SHOW_TRANSFORMS + "&" + PARAM_PROFILE_FIELD_ID + "="
            + profileFieldSelected.getProfileFieldId() + "&edit=absent";
        out.println("<p><a href=\"" + link + "\">Edit</a></p>");
      }
    }
  }

  public void printEdit(HttpServletRequest req, Session dataSession, PrintWriter out, String action, ProfileUsage profileUsageSelected,
      ProfileUsageValue profileUsageValueSelected) throws UnsupportedEncodingException
  {
    ProfileField copyFrom = null;
    if (action.equals("Save") || action.equals("Return"))
    {
      int i = 1;
      while (req.getParameter(PARAM_PROFILE_FIELD_ID + i) != null)
      {
        int profileFieldId = Integer.parseInt(req.getParameter(PARAM_PROFILE_FIELD_ID + i));
        ProfileField profileField = (ProfileField) dataSession.get(ProfileField.class, profileFieldId);
        String usage = req.getParameter("usage" + i);
        if (usage != null && !usage.equals(""))
        {
          ProfileUsageValue profileUsageValue = ProfileManager.getProfileUsageValue(dataSession, profileUsageSelected, profileField);
          if (profileUsageValue == null)
          {
            profileUsageValue = new ProfileUsageValue();
            profileUsageValue.setProfileField(profileField);
            profileUsageValue.setProfileUsage(profileUsageSelected);
          }
          String enforcement = req.getParameter("enforcement" + i);
          String implementation = req.getParameter("implementation" + i);
          String value = req.getParameter("value" + i);
          String comments = req.getParameter("comments" + i);
          String notes = req.getParameter("notes" + i);
          String linkDefinition = req.getParameter("linkDefinition" + i);
          String linkDetail = req.getParameter("linkDetail" + i);
          String linkClarification = req.getParameter("linkClarification" + i);
          String linkSupplement = req.getParameter("linkSupplement" + i);
          profileUsageValue.setUsage(Usage.readUsage(usage));
          profileUsageValue.setEnforcement(Enforcement.readEnforcement(enforcement));
          profileUsageValue.setImplementation(Implementation.readImplementation(implementation));
          profileUsageValue.setValue(value);
          profileUsageValue.setComments(comments);
          profileUsageValue.setNotes(notes);
          profileUsageValue.setLinkDefinition(linkDefinition);
          profileUsageValue.setLinkDetail(linkDetail);
          profileUsageValue.setLinkClarification(linkClarification);
          profileUsageValue.setLinkSupplement(linkSupplement);
          String debug = ProfileManager.determineMessageAcceptStatus(profileUsageValue, dataSession);
          profileUsageValue.setMessageAcceptStatusDebug(debug);
          Transaction transaction = dataSession.beginTransaction();
          dataSession.saveOrUpdate(profileUsageValue);
          transaction.commit();
        }
        i++;
      }
      if (action.equals("Return"))
      {
        ProfileField parent = profileUsageValueSelected.getProfileField().getParent();
        if (parent == null)
        {
          profileUsageValueSelected = null;
        } else
        {
          Query query = dataSession.createQuery("from ProfileUsageValue where profileUsage = ? and profileField = ?");
          query.setParameter(0, profileUsageSelected);
          query.setParameter(1, parent);
          @SuppressWarnings("unchecked")
          List<ProfileUsageValue> profileUsageValueList = query.list();
          if (profileUsageValueList.size() > 0)
          {
            profileUsageValueSelected = profileUsageValueList.get(0);
          }
        }
      }
    } else if (action.equals("Copy"))
    {
      if (!req.getParameter(PARAM_PROFILE_FIELD_ID_COPY_FROM).equals(""))
      {
        int profileFieldIdCopyFrom = Integer.parseInt(req.getParameter(PARAM_PROFILE_FIELD_ID_COPY_FROM));
        copyFrom = (ProfileField) dataSession.get(ProfileField.class, profileFieldIdCopyFrom);
      }
    }
    out.println("<h2>Edit Profile for " + profileUsageSelected + "</h2>");
    if (profileUsageValueSelected == null)
    {
      printEditTable(out, dataSession, profileUsageSelected, ProfileFieldType.SEGMENT, ProfileFieldType.SEGMENT_GROUP);
    } else
    {
      printEditTable(out, dataSession, profileUsageValueSelected, copyFrom);
    }
  }

  public void printView(Session dataSession, PrintWriter out, ProfileUsage profileUsageSelected) throws UnsupportedEncodingException
  {
    out.println("<h2>Profile for " + profileUsageSelected + "</h2>");
    {
      Query query = dataSession.createQuery("from ProfileUsageValue "
          + "where profileUsage = ? and (profileField.profileFieldType = ? or profileField.profileFieldType = ?) order by profileField.pos");
      query.setParameter(0, profileUsageSelected);
      query.setParameter(1, ProfileFieldType.DATA_TYPE.toString());
      query.setParameter(2, ProfileFieldType.DATA_TYPE_FIELD.toString());
      @SuppressWarnings("unchecked")
      List<ProfileUsageValue> profileUsageValueList = query.list();
      String lastDataTypeDef = "";
      boolean skip = false;
      for (ProfileUsageValue profileUsageValue : profileUsageValueList)
      {
        ProfileField profileField = profileUsageValue.getProfileField();
        if (!lastDataTypeDef.equals(profileField.getDataTypeDef()))
        {
          if (!lastDataTypeDef.equals("") && !skip)
          {
            out.println("</table>");
          }
          out.println("<h3>" + profileField.getDataTypeDef() + " Data Type</h3>");
          out.println("<table border=\"1\" cellspacing=\"0\">");
          out.println("  <tr>");
          out.println("    <th>Field Name</th>");
          out.println("    <th>Description</th>");
          out.println("    <th>Type</th>");
          out.println("    <th>Usage</th>");
          out.println("    <th>Links</th>");
          out.println("  </tr>");
        }
        out.println("  <tr>");
        out.println("    <td>" + profileField.getFieldName() + "</td>");
        if (profileField.getType() == ProfileFieldType.DATA_TYPE_FIELD)
        {
          out.println("    <td>&nbsp;" + profileField.getDescription() + "</td>");
        } else
        {
          out.println("    <td>" + profileField.getDescription() + "</td>");
        }
        out.println("    <td>" + profileField.getType() + "</td>");
        out.println("    <td>" + profileUsageValue.getUsage() + "</td>");
        printLink(out, profileUsageValue);
        out.println("  </tr>");
        lastDataTypeDef = profileField.getDataTypeDef();
      }
      if (!lastDataTypeDef.equals(""))
      {
        out.println("</table>");
      }
    }

    {
      Query query = dataSession.createQuery("from ProfileUsageValue "
          + "where profileUsage = ? and profileField.profileFieldType <> ? and profileField.profileFieldType <> ? order by profileField.pos");
      query.setParameter(0, profileUsageSelected);
      query.setParameter(1, ProfileFieldType.DATA_TYPE.toString());
      query.setParameter(2, ProfileFieldType.DATA_TYPE_FIELD.toString());
      @SuppressWarnings("unchecked")
      List<ProfileUsageValue> profileUsageValueList = query.list();

      ProfileManager.updateMessageAcceptStatus(profileUsageValueList);

      boolean skip = false;
      String lastSegmentName = "";
      for (ProfileUsageValue profileUsageValue : profileUsageValueList)
      {
        ProfileField profileField = profileUsageValue.getProfileField();
        {
          if (!lastSegmentName.equals(profileField.getSegmentName()))
          {
            if (!lastSegmentName.equals("") && !skip)
            {
              out.println("</table>");
            }
            out.println("<h3>" + profileField.getSegmentName() + " Segment</h3>");
            out.println("<table border=\"1\" cellspacing=\"0\">");
            out.println("  <tr>");
            out.println("    <th>Field</th>");
            out.println("    <th>Description</th>");
            out.println("    <th>Type</th>");
            out.println("    <th>Usage</th>");
            out.println("    <th>Links</th>");
            out.println("    <th>Expect Error</th>");
            out.println("    <th>Test Cases</th>");
            out.println("  </tr>");
          }
          out.println("  <tr>");
          out.println("    <td>" + profileField.getFieldName() + "</td>");
          if (profileField.getType() == ProfileFieldType.FIELD_PART || profileField.getType() == ProfileFieldType.FIELD_SUB_PART
              || profileField.getType() == ProfileFieldType.FIELD_VALUE || profileField.getType() == ProfileFieldType.FIELD_PART_VALUE
              || profileField.getType() == ProfileFieldType.FIELD_SUB_PART_VALUE)
          {
            out.println("    <td>&nbsp;" + profileField.getDescription() + "</td>");
          } else
          {
            out.println("    <td>" + profileField.getDescription() + "</td>");
          }
          out.println("    <td>" + profileField.getType() + "</td>");
          out.println("    <td>" + profileUsageValue.getUsage() + "</td>");
          printLink(out, profileUsageValue);
          if (profileUsageValue.getMessageAcceptStatus() == MessageAcceptStatus.ONLY_IF_PRESENT)
          {
            out.println("    <td>If Empty</td>");
          } else if (profileUsageValue.getMessageAcceptStatus() == MessageAcceptStatus.ONLY_IF_ABSENT)
          {
            out.println("    <td>If Valued</td>");
          } else
          {
            out.println("    <td>-</td>");
          }
          String link = "profile?" + PARAM_SHOW + "=transforms&" + PARAM_PROFILE_USAGE_VALUE_ID + "="
              + profileUsageValue.getProfileUsageValueId();
          if (!profileField.isTransformPresentDefined() || !profileField.isTransformAbsentDefined())
          {
            out.println("    <td><a href=\"" + link + "\">-</a></td>");
          } else
          {
            out.println("    <td><a href=\"" + link + "\">Defined</a></td>");
          }
          out.println("  </tr>");
          lastSegmentName = profileField.getSegmentName();
        }
      }
      if (!lastSegmentName.equals(""))
      {
        out.println("</table>");
      }

      out.println("<h2>Notes</h2>");
      out.println("<table border=\"1\" cellspacing=\"0\">");
      out.println("  <tr>");
      out.println("    <th>Field</th>");
      out.println("    <th>Description</th>");
      out.println("    <th>Type</th>");
      out.println("    <th>Usage</th>");
      out.println("    <th>Value</th>");
      out.println("    <th>Comments</th>");
      out.println("    <th>Notes</th>");
      out.println("  </tr>");
      for (ProfileUsageValue profileUsageValue : profileUsageValueList)
      {
        ProfileField profileField = profileUsageValue.getProfileField();
        if (!profileUsageValue.getNotes().equals(""))
        {
          out.println("  <tr>");
          out.println("    <td>" + profileField.getFieldName() + "</td>");
          if (profileField.getType() == ProfileFieldType.FIELD_PART || profileField.getType() == ProfileFieldType.FIELD_SUB_PART
              || profileField.getType() == ProfileFieldType.FIELD_VALUE || profileField.getType() == ProfileFieldType.FIELD_PART_VALUE
              || profileField.getType() == ProfileFieldType.FIELD_SUB_PART_VALUE)
          {
            out.println("    <td>&nbsp;" + profileField.getDescription() + "</td>");
          } else
          {
            out.println("    <td>" + profileField.getDescription() + "</td>");
          }
          out.println("    <td>" + profileField.getType() + "</td>");
          out.println("    <td>" + profileUsageValue.getUsage() + "</td>");
          out.println("    <td>" + profileUsageValue.getValue() + "</td>");
          out.println("    <td>" + profileUsageValue.getComments() + "</td>");
          out.println("    <td>" + profileUsageValue.getNotes() + "</td>");
          out.println("  </tr>");

        }
      }
      out.println("</table>");
    }
  }

  @SuppressWarnings("unchecked")
  public void printShow(Session dataSession, PrintWriter out, ProfileUsage profileUsageSelected, ProfileUsage profileUsageCompare,
      String comparisonType)
  {
    if (profileUsageCompare != null)
    {
      boolean compareConformance = comparisonType.equals("C");

      if (compareConformance)
      {
        Map<CompatibilityConformance, List<ProfileUsageValue>> compatibilityMap = new HashMap<CompatibilityConformance, List<ProfileUsageValue>>();
        List<ProfileUsageValue> profileUsageValueList;
        {
          Query query = dataSession.createQuery("from ProfileUsageValue where profileUsage = ?");
          query.setParameter(0, profileUsageSelected);
          profileUsageValueList = query.list();
        }
        for (ProfileUsageValue profileUsageValue : profileUsageValueList)
        {
          ProfileUsageValue profileUsageValueConformance = null;
          Query query = dataSession.createQuery("from ProfileUsageValue where profileUsage = ? and profileField = ?");
          query.setParameter(0, profileUsageCompare);
          query.setParameter(1, profileUsageValue.getProfileField());
          List<ProfileUsageValue> profileUsageValueConformanceList = query.list();
          if (profileUsageValueConformanceList.size() > 0)
          {
            profileUsageValueConformance = profileUsageValueConformanceList.get(0);
          }
          profileUsageValue.setProfileUsageValueCompare(profileUsageValueConformance);
          if (profileUsageValueConformance != null)
          {

            CompatibilityConformance compatibility;
            compatibility = ProfileManager.getCompatibilityConformance(profileUsageValue.getUsage(), profileUsageValueConformance.getUsage());
            List<ProfileUsageValue> pll = compatibilityMap.get(compatibility);
            if (pll == null)
            {
              pll = new ArrayList<ProfileUsageValue>();
              compatibilityMap.put(compatibility, pll);
            }
            pll.add(profileUsageValue);
          }
        }
        out.println("<h2>Conformance of " + profileUsageSelected + " in regards to " + profileUsageCompare + "</h2>");
        out.println("<table border=\"1\" cellspacing=\"0\">");
        out.println("  <tr>");
        out.println("    <th>Conformance</th>");
        out.println("    <th>Count</th>");
        out.println("  </tr>");
        printTotalRow(out, compatibilityMap, CompatibilityConformance.COMPATIBLE);
        printTotalRow(out, compatibilityMap, CompatibilityConformance.ALLOWANCE);
        printTotalRow(out, compatibilityMap, CompatibilityConformance.MAJOR_CONSTRAINT);
        printTotalRow(out, compatibilityMap, CompatibilityConformance.CONFLICT);
        printTotalRow(out, compatibilityMap, CompatibilityConformance.MAJOR_CONFLICT);
        printTotalRow(out, compatibilityMap, CompatibilityConformance.UNABLE_TO_DETERMINE);
        printTotalRow(out, compatibilityMap, CompatibilityConformance.NOT_DEFINED);
        out.println("</table>");

        printConformanceCompatibility(out, compatibilityMap, CompatibilityConformance.MAJOR_CONFLICT, profileUsageSelected, profileUsageCompare);
        printConformanceCompatibility(out, compatibilityMap, CompatibilityConformance.CONFLICT, profileUsageSelected, profileUsageCompare);
        printConformanceCompatibility(out, compatibilityMap, CompatibilityConformance.MAJOR_CONSTRAINT, profileUsageSelected,
            profileUsageCompare);
        printConformanceCompatibility(out, compatibilityMap, CompatibilityConformance.CONSTRAINT, profileUsageSelected, profileUsageCompare);
        printConformanceCompatibility(out, compatibilityMap, CompatibilityConformance.ALLOWANCE, profileUsageSelected, profileUsageCompare);
        printConformanceCompatibility(out, compatibilityMap, CompatibilityConformance.UNABLE_TO_DETERMINE, profileUsageSelected,
            profileUsageCompare);
      } else
      {
        Map<CompatibilityInteroperability, List<ProfileUsageValue>> compatibilityMap = new HashMap<CompatibilityInteroperability, List<ProfileUsageValue>>();
        List<ProfileUsageValue> profileUsageValueList;
        {
          Query query = dataSession.createQuery("from ProfileUsageValue where profileUsage = ?");
          query.setParameter(0, profileUsageSelected);
          profileUsageValueList = query.list();
        }
        for (ProfileUsageValue profileUsageValue : profileUsageValueList)
        {
          ProfileUsageValue profileUsageValueConformance = null;
          Query query = dataSession.createQuery("from ProfileUsageValue where profileUsage = ? and profileField = ?");
          query.setParameter(0, profileUsageCompare);
          query.setParameter(1, profileUsageValue.getProfileField());
          List<ProfileUsageValue> profileUsageValueConformanceList = query.list();
          if (profileUsageValueConformanceList.size() > 0)
          {
            profileUsageValueConformance = profileUsageValueConformanceList.get(0);
          }
          profileUsageValue.setProfileUsageValueCompare(profileUsageValueConformance);
          if (profileUsageValueConformance != null)
          {
            CompatibilityInteroperability compatibility;
            compatibility = ProfileManager.getCompatibilityInteroperability(profileUsageValue, profileUsageValueConformance);
            List<ProfileUsageValue> pll = compatibilityMap.get(compatibility);
            if (pll == null)
            {
              pll = new ArrayList<ProfileUsageValue>();
              compatibilityMap.put(compatibility, pll);
            }
            pll.add(profileUsageValue);
          }
        }

        out.println("<h2>Interoperability of " + profileUsageSelected + " with " + profileUsageCompare + "</h2>");
        out.println("<table border=\"1\" cellspacing=\"0\">");
        out.println("  <tr>");
        out.println("    <th>Conformance</th>");
        out.println("    <th>Count</th>");
        out.println("  </tr>");
        printTotalRow(out, compatibilityMap, CompatibilityInteroperability.COMPATIBLE);
        printTotalRow(out, compatibilityMap, CompatibilityInteroperability.DATA_LOSS);
        printTotalRow(out, compatibilityMap, CompatibilityInteroperability.IF_CONFIGURED);
        printTotalRow(out, compatibilityMap, CompatibilityInteroperability.IF_POPULATED);
        printTotalRow(out, compatibilityMap, CompatibilityInteroperability.NO_PROBLEM);
        printTotalRow(out, compatibilityMap, CompatibilityInteroperability.PROBLEM);
        printTotalRow(out, compatibilityMap, CompatibilityInteroperability.MAJOR_PROBLEM);
        out.println("</table>");

        printConformanceInteroperability(out, compatibilityMap, CompatibilityInteroperability.MAJOR_PROBLEM, profileUsageSelected,
            profileUsageCompare);
        printConformanceInteroperability(out, compatibilityMap, CompatibilityInteroperability.PROBLEM, profileUsageSelected, profileUsageCompare);
        printConformanceInteroperability(out, compatibilityMap, CompatibilityInteroperability.IF_CONFIGURED, profileUsageSelected,
            profileUsageCompare);
        printConformanceInteroperability(out, compatibilityMap, CompatibilityInteroperability.IF_POPULATED, profileUsageSelected,
            profileUsageCompare);
        printConformanceInteroperability(out, compatibilityMap, CompatibilityInteroperability.DATA_LOSS, profileUsageSelected,
            profileUsageCompare);
      }
    }
  }

  public String getShow(HttpServletRequest req)
  {
    String show = req.getParameter(PARAM_SHOW);
    if (show == null)
    {
      show = "";
    }
    return show;
  }

  public String getComparisonType(HttpServletRequest req)
  {
    String comparisonType = req.getParameter(PARAM_COMPARISON_TYPE);
    if (comparisonType == null)
    {
      comparisonType = "C";
    }
    return comparisonType;
  }

  @SuppressWarnings("unchecked")
  public void printCompareProfilesForm(UserSession userSession, Session dataSession, PrintWriter out, ProfileUsage profileUsageSelected,
      ProfileUsage profileUsageCompare, String comparisonType)
  {
    List<ProfileUsage> profileUsageList;
    {
      Query query = dataSession.createQuery("from ProfileUsage order by category, label, version");
      profileUsageList = query.list();
    }
    out.println("    <h2>Compare Profiles</h2>");
    out.println("    <form action=\"profile\" method=\"GET\">");
    out.println("      <table border=\"1\">");
    out.println("        <tr>");
    out.println("          <td>Usage Profile</td>");
    out.println("          <td>");
    out.println("            <select name=\"" + PARAM_PROFILE_USAGE_ID + "\">");
    out.println("              <option value=\"\">select</option>");
    {
      for (ProfileUsage profileUsage : profileUsageList)
      {
        if (profileUsageSelected != null && profileUsageSelected.getProfileUsageId() == profileUsage.getProfileUsageId())
        {
          out.println("              <option value=\"" + profileUsage.getProfileUsageId() + "\" selected=\"true\">" + profileUsage + "</option>");
        } else
        {
          out.println("              <option value=\"" + profileUsage.getProfileUsageId() + "\">" + profileUsage + "</option>");
        }
      }
    }
    out.println("            </select>");
    out.println("          </td>");
    out.println("          <td>");
    out.println("            <input type=\"submit\" name=\"" + PARAM_SHOW + "\" value=\"View\"/>");
    if (userSession.isAdmin())
    {
      out.println("            <input type=\"submit\" name=\"" + PARAM_SHOW + "\" value=\"Edit\"/>");
    }
    out.println("          </td>");
    out.println("        </tr>");
    out.println("        <tr>");
    out.println("          <td>Compare with</td>");
    out.println("          <td>");
    out.println("            <select name=\"" + PARAM_PROFILE_USAGE_ID_COMPARE + "\">");
    out.println("              <option value=\"\">select</option>");
    {
      for (ProfileUsage profileUsage : profileUsageList)
      {
        if (profileUsageCompare.getProfileUsageId() == profileUsageCompare.getProfileUsageId())
        {
          out.println("              <option value=\"" + profileUsage.getProfileUsageId() + "\" selected=\"true\">" + profileUsage + "</option>");
        } else
        {
          out.println("              <option value=\"" + profileUsage.getProfileUsageId() + "\">" + profileUsage + "</option>");
        }
      }
    }
    out.println("            </select>");
    out.println("          </td>");
    out.println("          <td>");
    String checked = "";
    checked = comparisonType.equals("C") ? " checked=\"checked\"" : "";
    out.println("            <input type=\"radio\" name=\"" + PARAM_COMPARISON_TYPE + "\" value=\"C\"" + checked + "> Conformance");
    checked = comparisonType.equals("I") ? " checked=\"checked\"" : "";
    out.println("            <input type=\"radio\" name=\"" + PARAM_COMPARISON_TYPE + "\" value=\"I\"" + checked + "> Interoperability");
    out.println("            <input type=\"submit\" name=\"" + PARAM_SHOW + "\" value=\"" + SHOW_COMPARE + "\"/>");
    out.println("          </td>");
    out.println("        </tr>");
    out.println("      </table>");
    out.println("    </form>");
  }

  public ProfileUsage getProfileUsageCompare(HttpServletRequest req, Session dataSession)
  {
    ProfileUsage profileUsageCompare = null;
    int profileUsageIdCompare = 1;
    String profileUsageIdCompareString = req.getParameter(PARAM_PROFILE_USAGE_ID_COMPARE);
    if (profileUsageIdCompareString != null && !profileUsageIdCompareString.equals(""))
    {
      profileUsageIdCompare = Integer.parseInt(profileUsageIdCompareString);
    }
    profileUsageCompare = (ProfileUsage) dataSession.get(ProfileUsage.class, profileUsageIdCompare);
    return profileUsageCompare;
  }

  public void setProfileUsageSelected(UserSession userSession, ProfileUsage profileUsageSelected)
  {
    if (profileUsageSelected != null)
    {
      userSession.setProfileUsageSelected(profileUsageSelected);
    }
  }

  public ProfileField getPorfileFieldSelected(HttpServletRequest req, Session dataSession, ProfileField profileFieldSelected)
  {
    if (req.getParameter(PARAM_PROFILE_FIELD_ID) != null)
    {
      int profileFieldId = Integer.parseInt(req.getParameter(PARAM_PROFILE_FIELD_ID));
      profileFieldSelected = (ProfileField) dataSession.get(ProfileField.class, profileFieldId);
    }
    return profileFieldSelected;
  }

  public ProfileUsage getProfileUsageSelected(HttpServletRequest req, UserSession userSession, Session dataSession)
  {
    ProfileUsage profileUsageSelected = null;
    {
      int profileUsageId = 0;
      if (req.getParameter(PARAM_PROFILE_USAGE_ID) != null && !req.getParameter(PARAM_PROFILE_USAGE_ID).equals(""))
      {
        profileUsageId = Integer.parseInt(req.getParameter(PARAM_PROFILE_USAGE_ID));
        profileUsageSelected = (ProfileUsage) dataSession.get(ProfileUsage.class, profileUsageId);
      } else if (userSession.getProfileUsageSelected() != null)
      {
        profileUsageSelected = userSession.getProfileUsageSelected();
        profileUsageId = profileUsageSelected.getProfileUsageId();
      }
    }
    return profileUsageSelected;
  }

  public String getAction(HttpServletRequest req)
  {
    String action = req.getParameter(PARAM_ACTION);
    if (action == null)
    {
      action = "";
    }
    return action;
  }

  public void printLink(PrintWriter out, ProfileUsageValue profileUsageValue) throws UnsupportedEncodingException
  {
    String link = "guide?" + GuideServlet.PARAM_PROFILE_USAGE_ID + "=" + profileUsageValue.getProfileUsage().getProfileUsageId() + "&"
        + GuideServlet.PARAM_PROFILE_USAGE_VALUE_ID + "=" + profileUsageValue.getProfileUsageValueId() + "&" + GuideServlet.PARAM_LOCATION + "=";
    out.println("    <td>");
    printLink(out, link, "Definition", profileUsageValue.getLinkDefinition());
    printLink(out, link, "Detail", profileUsageValue.getLinkDetail());
    printLink(out, link, "Clarification", profileUsageValue.getLinkClarification());
    printLink(out, link, "Supplement", profileUsageValue.getLinkSupplement());
    out.println("    </td>");
  }

  public void printLink(PrintWriter out, String link, String label, String location) throws UnsupportedEncodingException
  {
    if (location != null && !location.equals(""))
    {
      out.println("<a href=\"" + link + URLEncoder.encode(location, "UTF-8") + "\" target=\"guide\">" + label + "</a> ");
    }
  }

  @SuppressWarnings("unchecked")
  private void printEditTable(PrintWriter out, Session dataSession, ProfileUsageValue profileUsageValueSelected, ProfileField profileFieldCopyFrom)
      throws UnsupportedEncodingException
  {
    ProfileUsage profileUsage = profileUsageValueSelected.getProfileUsage();
    out.println("<h3>" + profileUsageValueSelected.getProfileField().getDescription() + " Table</h3>");
    out.println("<form action=\"profile\" method=\"POST\">");
    out.println("<table border=\"1\" cellspacing=\"0\">");
    out.println("  <tr>");
    out.println("    <th colspan=\"3\">US Base Standard</th>");
    out.println("    <th colspan=\"5\">Local Standard</th>");
    out.println("    <td><input type=\"submit\" name=\"action\" value=\"Save\"/><input type=\"submit\" name=\"action\" value=\"Return\"/></td>");
    out.println("    <th colspan=\"4\">Links</th>");
    out.println("  </tr>");
    out.println("  <tr>");
    out.println("    <th>Field Name</th>");
    out.println("    <th>Description</th>");
    out.println("    <th>Usage</th>");
    out.println("    <th>Usage</th>");
    out.println("    <th>Enforcement</th>");
    out.println("    <th>Implementation</th>");
    out.println("    <th>Value</th>");
    out.println("    <th>Comments</th>");
    out.println("    <th>Testing Notes</th>");
    out.println("    <th>Definition</th>");
    out.println("    <th>Detail</th>");
    out.println("    <th>Clarification</th>");
    out.println("    <th>Supplement</th>");
    out.println("  </tr>");
    List<ProfileField> profileFieldSelectedList;
    {
      Query query = dataSession.createQuery("from ProfileField where parent = ? order by pos");
      query.setParameter(0, profileUsageValueSelected.getProfileField());
      profileFieldSelectedList = query.list();
    }

    int i = 0;
    for (ProfileField profileField : profileFieldSelectedList)
    {
      i++;
      ProfileUsageValue profileUsageValue = null;
      {
        Query query = dataSession.createQuery("from ProfileUsageValue where profileField = ? and profileUsage = ? ");
        query.setParameter(0, profileField);
        query.setParameter(1, profileUsage);
        List<ProfileUsageValue> profileUsageValueList = query.list();
        if (profileUsageValueList.size() > 0)
        {
          profileUsageValue = profileUsageValueList.get(0);
        }
      }
      ProfileUsageValue profileUsageValueCopyFrom = null;
      if (profileFieldCopyFrom != null)
      {
        List<ProfileUsageValue> profileUsageValueCopyList = null;
        if (profileFieldCopyFrom.getType() == ProfileFieldType.SEGMENT)
        {
          Query query = dataSession
              .createQuery("from ProfileUsageValue where profileUsage = ? and profileField.parent = ? and profileField.posInSegment = ?");
          query.setParameter(0, profileUsage);
          query.setParameter(1, profileFieldCopyFrom);
          query.setParameter(2, profileField.getPosInSegment());
          profileUsageValueCopyList = query.list();
        } else if (profileFieldCopyFrom.getType() == ProfileFieldType.FIELD)
        {
          Query query = dataSession
              .createQuery("from ProfileUsageValue where profileUsage = ? and profileField.parent = ? and profileField.posInField = ?");
          query.setParameter(0, profileUsage);
          query.setParameter(1, profileFieldCopyFrom);
          query.setParameter(2, profileField.getPosInField());
          profileUsageValueCopyList = query.list();
        }
        if (profileUsageValueCopyList != null && profileUsageValueCopyList.size() > 0)
        {
          profileUsageValueCopyFrom = profileUsageValueCopyList.get(0);
        }
      }

      out.println("  <tr>");
      if (profileUsageValue == null || profileUsageValue.getUsage() == Usage.NOT_DEFINED
          || (profileField.getType() != ProfileFieldType.FIELD && profileField.getType() != ProfileFieldType.FIELD_PART))
      {
        out.println("    <td>" + profileField.getFieldName() + "</td>");
        out.println("    <td>" + profileField.getDescription() + "</td>");
      } else
      {
        String link = "profile?" + PARAM_SHOW + "=" + SHOW_EDIT + "&" + PARAM_PROFILE_USAGE_VALUE_ID + "="
            + profileUsageValue.getProfileUsageValueId();
        out.println("    <td><a href=\"" + link + "\">" + profileField.getFieldName() + "</a></td>");
        out.println("    <td><a href=\"" + link + "\">" + profileField.getDescription() + "</a></td>");
      }
      Usage selectedUsage = Usage.NOT_DEFINED;
      String usageValue = "";
      Enforcement selectedEnforcement = Enforcement.NOT_DEFINED;
      Implementation selectedImplementation = Implementation.NOT_DEFINED;
      String comments = "";
      String notes = "";
      String linkDefinition = "";
      String linkDetail = "";
      String linkClarification = "";
      String linkSupplement = "";
      if (profileUsageValue != null)
      {
        selectedUsage = profileUsageValue.getUsage();
        usageValue = profileUsageValue.getValue();
        selectedEnforcement = profileUsageValue.getEnforcement();
        selectedImplementation = profileUsageValue.getImplementation();
        comments = profileUsageValue.getComments();
        notes = profileUsageValue.getNotes();
        linkDefinition = n(profileUsageValue.getLinkDefinition());
        linkDetail = n(profileUsageValue.getLinkDetail());
        linkClarification = n(profileUsageValue.getLinkClarification());
        linkSupplement = n(profileUsageValue.getLinkSupplement());
      }
      if (profileUsageValueCopyFrom != null)
      {
        selectedUsage = profileUsageValueCopyFrom.getUsage();
        selectedEnforcement = profileUsageValueCopyFrom.getEnforcement();
        selectedImplementation = profileUsageValueCopyFrom.getImplementation();
        usageValue = profileUsageValueCopyFrom.getValue();
        comments = profileUsageValueCopyFrom.getComments();
        notes = profileUsageValueCopyFrom.getNotes();
        linkDefinition = n(profileUsageValueCopyFrom.getLinkDefinition());
        linkDetail = n(profileUsageValueCopyFrom.getLinkDetail());
        linkClarification = n(profileUsageValueCopyFrom.getLinkClarification());
        linkSupplement = n(profileUsageValueCopyFrom.getLinkSupplement());
      }
      String classType = "";
      if (selectedUsage != Usage.NOT_DEFINED)
      {
        if (verifyCompatible(selectedUsage, profileField.getTestUsage()))
        {
          classType = "pass";
        } else
        {
          classType = "fail";
        }
      }
      out.println("    <td class=\"" + classType + "\">" + profileField.getTestUsage() + "</td>");
      out.println("    <td>");
      out.println("      <select name=\"usage" + i + "\">");
      out.println("        <option value=\"\">select</option>");
      for (Usage usage : Usage.values())
      {
        if (usage == selectedUsage)
        {
          out.println("        <option value=\"" + usage + "\" selected=\"true\">" + usage + "</option>");
        } else
        {
          out.println("        <option value=\"" + usage + "\">" + usage + " " + "</option>");
        }
      }
      out.println("      </select>");
      out.println("    </td>");
      out.println("    <td>");
      out.println("      <select name=\"enforcement" + i + "\">");
      for (Enforcement enforcement : Enforcement.values())
      {
        if (enforcement == selectedEnforcement)
        {
          out.println("        <option value=\"" + enforcement + "\" selected=\"true\">" + enforcement.getDescription() + "</option>");
        } else
        {
          out.println("        <option value=\"" + enforcement + "\">" + enforcement.getDescription() + " " + "</option>");
        }
      }
      out.println("      </select>");
      out.println("    </td>");
      out.println("    <td>");
      out.println("      <select name=\"implementation" + i + "\">");
      for (Implementation implementation : Implementation.values())
      {
        if (implementation == selectedImplementation)
        {
          out.println("        <option value=\"" + implementation + "\" selected=\"true\">" + implementation.getDescription() + "</option>");
        } else
        {
          out.println("        <option value=\"" + implementation + "\">" + implementation.getDescription() + " " + "</option>");
        }
      }
      out.println("      </select>");
      out.println("    </td>");
      out.println("    <td><input type=\"text\" size=\"5\" name=\"value" + i + "\" value=\"" + usageValue + "\"/></td>");
      out.println("    <td><input type=\"text\" size=\"20\" name=\"comments" + i + "\" value=\"" + comments + "\"/></td>");
      out.println("    <td><input type=\"text\" size=\"20\" name=\"notes" + i + "\" value=\"" + notes + "\"/>");
      out.println("    <td><input type=\"text\" size=\"15\" name=\"linkDefinition" + i + "\" value=\"" + linkDefinition + "\"/>");
      out.println("    <td><input type=\"text\" size=\"15\" name=\"linkDetail" + i + "\" value=\"" + linkDetail + "\"/>");
      out.println("    <td><input type=\"text\" size=\"15\" name=\"linkClarification" + i + "\" value=\"" + linkClarification + "\"/>");
      out.println("    <td><input type=\"text\" size=\"15\" name=\"linkSupplement" + i + "\" value=\"" + linkSupplement + "\"/>");
      out.println("    <input type=\"hidden\" name=\"" + PARAM_PROFILE_FIELD_ID + i + "\" value=\"" + profileField.getProfileFieldId() + "\"/></td>");

      out.println("  </tr>");

    }
    out.println("</table>");
    List<ProfileUsageValue> copyFromList = null;
    ProfileField profileField = profileUsageValueSelected.getProfileField();
    if (profileField.getType() == ProfileFieldType.SEGMENT)
    {
      Query query = dataSession.createQuery(
          "from ProfileUsageValue where profileUsage = ? and profileField.profileFieldType = ? and profileField.segmentName = ? and profileField <> ? order by pos");
      query.setParameter(0, profileUsage);
      query.setParameter(1, ProfileFieldType.SEGMENT.toString());
      query.setParameter(2, profileField.getSegmentName());
      query.setParameter(3, profileField);
      copyFromList = query.list();
    } else if (profileField.getType() == ProfileFieldType.FIELD)
    {
      Query query = dataSession.createQuery(
          "from ProfileUsageValue where profileUsage = ? and profileField.profileFieldType = ? and profileField.dataType = ? and profileField <> ? order by pos");
      query.setParameter(0, profileUsage);
      query.setParameter(1, ProfileFieldType.FIELD.toString());
      query.setParameter(2, profileField.getDataType());
      query.setParameter(3, profileField);
      copyFromList = query.list();
    }

    out.println("<input type=\"hidden\" name=\"" + PARAM_SHOW + "\" value=\"" + SHOW_EDIT + "\"/>");
    out.println("<input type=\"submit\" name=\"action\" value=\"Save\"/>");
    out.println("<input type=\"submit\" name=\"action\" value=\"Return\"/>");
    out.println("<input type=\"hidden\" name=\"" + PARAM_PROFILE_USAGE_VALUE_ID + "\" value=\"" + profileUsageValueSelected.getProfileUsageValueId()
        + "\"/>");

    if (copyFromList != null && copyFromList.size() > 0)
    {
      out.println("<select name=\"" + PARAM_PROFILE_FIELD_ID_COPY_FROM + "\">");
      out.println("  <option value=\"\">select</option>");
      for (ProfileUsageValue profileUsageValue : copyFromList)
      {
        out.println("  <option value=\"" + profileUsageValue.getProfileField().getProfileFieldId() + "\">"
            + profileUsageValue.getProfileField().getFieldName() + "</option>");
      }
      out.println("  </select>");
      out.println("<input type=\"submit\" name=\"action\" value=\"Copy\"/>");
    }
    out.println("</form>");
  }

  @SuppressWarnings("unchecked")
  private void printEditTable(PrintWriter out, Session dataSession, ProfileUsage profileUsageSelected, ProfileFieldType profileFieldType1,
      ProfileFieldType profileFieldType2) throws UnsupportedEncodingException
  {
    out.println("<h3>" + profileFieldType1 + " Table</h3>");
    out.println("<form action=\"profile\" method=\"POST\">");
    out.println("<table border=\"1\" cellspacing=\"0\">");
    out.println("  <tr>");
    out.println("    <th colspan=\"3\">US Base Standard</th>");
    out.println("    <th colspan=\"5\">Local Standard</th>");
    out.println("    <td><input type=\"submit\" name=\"action\" value=\"Save\"/></td>");
    out.println("    <th colspan=\"4\">Links</th>");
    out.println("  </tr>");
    out.println("  <tr>");
    out.println("    <th>Field Name</th>");
    out.println("    <th>Description</th>");
    out.println("    <th>Usage</th>");
    out.println("    <th>Usage</th>");
    out.println("    <th>Enforcement</th>");
    out.println("    <th>Implementation</th>");
    out.println("    <th>Value</th>");
    out.println("    <th>Comments</th>");
    out.println("    <th>Testing Notes</th>");
    out.println("    <th>Definition</th>");
    out.println("    <th>Detail</th>");
    out.println("    <th>Clarification</th>");
    out.println("    <th>Supplement</th>");
    out.println("  </tr>");
    List<ProfileField> profileFieldList;
    {
      Query query = dataSession.createQuery("from ProfileField where profileFieldType = ? or profileFieldType = ? order by pos");
      query.setParameter(0, profileFieldType1.toString());
      query.setParameter(1, profileFieldType2.toString());
      profileFieldList = query.list();
    }
    int i = 0;
    for (ProfileField profileField : profileFieldList)
    {
      i++;

      ProfileUsageValue profileUsageValue;
      {
        Query query = dataSession.createQuery("from ProfileUsageValue where profileUsage = ? and profileField = ?");
        query.setParameter(0, profileUsageSelected);
        query.setParameter(1, profileField);
        List<ProfileUsageValue> profileUsageValueList = query.list();
        if (profileUsageValueList.size() > 0)
        {
          profileUsageValue = profileUsageValueList.get(0);
        } else
        {
          profileUsageValue = new ProfileUsageValue();
          profileUsageValue.setUsage(Usage.NOT_DEFINED);

        }
      }

      if (profileField.getType() == profileFieldType1 || profileField.getType() == profileFieldType2)
      {
        String link = "profile?" + PARAM_SHOW + "=" + SHOW_EDIT + "&" + PARAM_PROFILE_USAGE_VALUE_ID + "="
            + profileUsageValue.getProfileUsageValueId();
        out.println("  <tr>");
        if (profileUsageValue.getUsage() == Usage.NOT_DEFINED || profileField.getType() == ProfileFieldType.SEGMENT_GROUP)
        {
          out.println("    <td>" + profileField.getFieldName() + "</td>");
          out.println("    <td>" + profileField.getDescription() + "</td>");
        } else
        {
          out.println("    <td><a href=\"" + link + "\">" + profileField.getFieldName() + "</a></td>");
          out.println("    <td><a href=\"" + link + "\">" + profileField.getDescription() + "</a></td>");
        }
        String classType = "";
        if (profileUsageValue.getUsage() != Usage.NOT_DEFINED)
        {
          if (verifyCompatible(profileUsageValue.getUsage(), profileField.getTestUsage()))
          {
            classType = "pass";
          } else
          {
            classType = "fail";
          }
        }
        out.println("    <td class=\"" + classType + "\">" + profileField.getTestUsage() + "</td>");
        out.println("    <td>");
        out.println("      <select name=\"usage" + i + "\">");
        for (Usage usage : Usage.values())
        {
          if (usage == profileUsageValue.getUsage())
          {
            out.println("        <option value=\"" + usage + "\" selected=\"true\">" + usage + "</option>");
          } else
          {
            out.println("        <option value=\"" + usage + "\">" + usage + "</option>");
          }
        }
        out.println("      </select>");
        out.println("    </td>");
        out.println("    <td>");
        out.println("      <select name=\"enforcement" + i + "\">");
        for (Enforcement enforcement : Enforcement.values())
        {
          if (enforcement == profileUsageValue.getEnforcement())
          {
            out.println("        <option value=\"" + enforcement + "\" selected=\"true\">" + enforcement.getDescription() + "</option>");
          } else
          {
            out.println("        <option value=\"" + enforcement + "\">" + enforcement.getDescription() + " " + "</option>");
          }
        }
        out.println("      </select>");
        out.println("    </td>");
        out.println("    <td>");
        out.println("      <select name=\"implementation" + i + "\">");
        for (Implementation implementation : Implementation.values())
        {
          if (implementation == profileUsageValue.getImplementation())
          {
            out.println("        <option value=\"" + implementation + "\" selected=\"true\">" + implementation.getDescription() + "</option>");
          } else
          {
            out.println("        <option value=\"" + implementation + "\">" + implementation.getDescription() + " " + "</option>");
          }
        }
        out.println("      </select>");
        out.println("    </td>");
        out.println("    <td><input type=\"text\" size=\"5\" name=\"value" + i + "\" value=\"" + profileUsageValue.getValue() + "\"/></td>");
        out.println("    <td><input type=\"text\" size=\"20\" name=\"comments" + i + "\" value=\"" + profileUsageValue.getComments() + "\"/></td>");
        out.println("    <td><input type=\"text\" size=\"20\" name=\"notes" + i + "\" value=\"" + profileUsageValue.getNotes() + "\"/>");
        out.println(
            "    <td><input type=\"text\" size=\"15\" name=\"linkDefinition" + i + "\" value=\"" + n(profileUsageValue.getLinkDefinition()) + "\"/>");
        out.println("    <td><input type=\"text\" size=\"15\" name=\"linkDetail" + i + "\" value=\"" + n(profileUsageValue.getLinkDetail()) + "\"/>");
        out.println("    <td><input type=\"text\" size=\"15\" name=\"linkClarification" + i + "\" value=\""
            + n(profileUsageValue.getLinkClarification()) + "\"/>");
        out.println(
            "    <td><input type=\"text\" size=\"15\" name=\"linkSupplement" + i + "\" value=\"" + n(profileUsageValue.getLinkSupplement()) + "\"/>");
        out.println(
            "    <input type=\"hidden\" name=\"" + PARAM_PROFILE_FIELD_ID + i + "\" value=\"" + profileField.getProfileFieldId() + "\"/></td>");
        out.println("  </tr>");
      }
    }
    out.println("</table>");
    out.println("<input type=\"hidden\" name=\"" + PARAM_PROFILE_USAGE_ID + "\" value=\"" + profileUsageSelected.getProfileUsageId() + "\"/>");
    out.println("<input type=\"hidden\" name=\"" + PARAM_SHOW + "\" value=\"" + SHOW_EDIT + "\"/>");
    out.println("<input type=\"submit\" name=\"action\" value=\"Save\"/>");
    out.println("</form>");
  }

  private boolean verifyCompatible(Usage profileUsage, Usage baseUsage)
  {
    if (profileUsage == baseUsage)
    {
      return true;
    }
    if (baseUsage == Usage.R && (profileUsage == Usage.R_NOT_ENFORCED))
    {
      return true;
    }
    if (baseUsage == Usage.RE && (profileUsage == Usage.RE_NOT_USED))
    {
      return true;
    }
    if (baseUsage == Usage.O && (profileUsage == Usage.O_NOT_USED))
    {
      return true;
    }
    if (baseUsage == Usage.O && (profileUsage == Usage.X_NOT_ENFORCED))
    {
      return true;
    }
    if (baseUsage == Usage.X && (profileUsage == Usage.X_NOT_ENFORCED))
    {
      return true;
    }
    return false;
  }

  public void printTotalRow(PrintWriter out, Map<CompatibilityConformance, List<ProfileUsageValue>> compatibilityMap, CompatibilityConformance c)
  {
    List<ProfileUsageValue> pl = compatibilityMap.get(c);
    if (pl != null && pl.size() > 0)
    {
      out.println("  <tr>");
      out.println("    <td>" + c + "</td>");
      out.println("    <td>" + pl.size() + "</td>");
      out.println("  </tr>");
    }
  }

  public void printTotalRow(PrintWriter out, Map<CompatibilityInteroperability, List<ProfileUsageValue>> compatibilityMap,
      CompatibilityInteroperability c)
  {
    List<ProfileUsageValue> pl = compatibilityMap.get(c);
    if (pl != null && pl.size() > 0)
    {
      out.println("  <tr>");
      out.println("    <td>" + c + "</td>");
      out.println("    <td>" + pl.size() + "</td>");
      out.println("  </tr>");
    }
  }

  private static String showDiff(String message, String compareMessage)
  {
    int firstDiffPos = message.length();
    int lastDiffPos = message.length();
    for (int i = 0; i < message.length() && i < compareMessage.length(); i++)
    {
      if (message.charAt(i) != compareMessage.charAt(i))
      {
        firstDiffPos = i;
        break;
      }
    }
    if (firstDiffPos == message.length())
    {
      return message;
    }
    int i = message.length();
    int j = compareMessage.length();
    while (i > 0 && j > 0)
    {
      i--;
      j--;
      lastDiffPos = i;
      if (message.charAt(i) != compareMessage.charAt(j))
      {
        break;
      }
    }
    if (lastDiffPos < firstDiffPos)
    {
      lastDiffPos = firstDiffPos + 1;
    }
    return message.substring(0, firstDiffPos) + "<b class=\"different\">" + message.substring(firstDiffPos, lastDiffPos) + "</b>"
        + message.substring(lastDiffPos);
  }

  private static String addHovers(String message) throws IOException
  {
    StringBuilder sb = new StringBuilder();
    BufferedReader reader = new BufferedReader(new StringReader(message));
    String line;
    String segmentName = "";
    int fieldCount = 0;
    while ((line = reader.readLine()) != null)
    {
      if (!line.startsWith("   ") && line.length() > 3 && line.charAt(3) == '|')
      {
        segmentName = line.substring(0, 3);
        fieldCount = 0;
      }
      for (int i = 0; i < line.length(); i++)
      {
        @SuppressWarnings("unused")
        char c = line.charAt(i);
        if (line.charAt(i) == '|')
        {
          if (fieldCount > 0)
          {
            sb.append("</a>");
          } else
          {
            if (segmentName.equals("MSH") || segmentName.equals("FHS") || segmentName.equals("BHS"))
            {
              fieldCount++;
            }
          }
          fieldCount++;
          sb.append("<a class=\"hl7\" title=\"" + segmentName + "-" + fieldCount + "\">");
        }
        sb.append(line.charAt(i));
      }
      if (fieldCount > 0)
      {
        sb.append("</a>");
      }
      sb.append("\n");
    }
    reader.close();
    return sb.toString();
  }

  private static String formatMessage(String message) throws IOException
  {
    StringBuilder sb = new StringBuilder();
    BufferedReader reader = new BufferedReader(new StringReader(message));
    String line;
    while ((line = reader.readLine()) != null)
    {
      boolean first = true;
      while (line.length() > 200)
      {
        int i = first ? 200 : 197;
        while (i > 4 && line.charAt(i) != '|')
        {
          i--;
        }
        if (i == 4)
        {
          i = first ? 200 : 197;
        }
        if (!first)
        {
          sb.append("   ");
        }
        sb.append(line.substring(0, i));
        sb.append("\n");
        line = line.substring(i);
        first = false;
      }
      if (!first)
      {
        sb.append("   ");
      }
      sb.append(line);
      sb.append("\n");
    }
    reader.close();
    return sb.toString();
  }

  private void printConformanceCompatibility(PrintWriter out, Map<CompatibilityConformance, List<ProfileUsageValue>> compatibilityMap,
      CompatibilityConformance c, ProfileUsage profileUsageSelected, ProfileUsage profileUsageCompare)
  {
    if (compatibilityMap.get(c) != null)
    {
      out.println("<h3>" + c + "</h3>");
      out.println("<table border=\"1\" cellspacing=\"0\">");
      out.println("  <tr>");
      out.println("    <th>Field</th>");
      out.println("    <th>Description</th>");
      out.println("    <th>" + profileUsageCompare + "</th>");
      out.println("    <th>" + profileUsageSelected + "</th>");
      out.println("  </tr>");
      for (ProfileUsageValue profileUsageValue : compatibilityMap.get(c))
      {
        ProfileUsageValue profileUsageValueConformance = profileUsageValue.getProfileUsageValueCompare();
        if (profileUsageValueConformance != null)
        {
          out.println("  <tr>");
          out.println("    <td>" + profileUsageValue.getProfileField().getFieldName() + "</td>");
          out.println("    <td>" + profileUsageValue.getProfileField().getDescription() + "</td>");
          out.println("    <td>" + profileUsageValueConformance.getUsage() + "</td>");
          out.println("    <td>" + profileUsageValue.getUsage() + "</td>");
          out.println("  </tr>");
        }
      }
      out.println("</table>");
    }
  }

  private void printConformanceInteroperability(PrintWriter out, Map<CompatibilityInteroperability, List<ProfileUsageValue>> compatibilityMap,
      CompatibilityInteroperability c, ProfileUsage profileUsageSelected, ProfileUsage profileUsageCompare)
  {
    if (compatibilityMap.get(c) != null)
    {
      out.println("<h3>" + c + "</h3>");
      out.println("<table border=\"1\" cellspacing=\"0\">");
      out.println("  <tr>");
      out.println("    <th>Field</th>");
      out.println("    <th>Description</th>");
      out.println("    <th>" + profileUsageSelected + "</th>");
      out.println("    <th>" + profileUsageCompare + "</th>");
      out.println("  </tr>");
      for (ProfileUsageValue profileUsageValue : compatibilityMap.get(c))
      {
        ProfileUsageValue profileUsageValueConformance = profileUsageValue.getProfileUsageValueCompare();
        if (profileUsageValueConformance != null)
        {
          out.println("  <tr>");
          out.println("    <td>" + profileUsageValue.getProfileField().getFieldName() + "</td>");
          out.println("    <td>" + profileUsageValue.getProfileField().getDescription() + "</td>");
          out.println("    <td>" + profileUsageValue.getUsage() + "</td>");
          out.println("    <td>" + profileUsageValueConformance.getUsage() + "</td>");
          out.println("  </tr>");
        }
      }
      out.println("</table>");
    }
  }

}
