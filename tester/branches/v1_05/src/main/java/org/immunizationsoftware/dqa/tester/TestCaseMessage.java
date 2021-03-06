/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.immunizationsoftware.dqa.tester;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.immunizationsoftware.dqa.tester.Transformer.PatientType;

/**
 * 
 * @author nathan
 */
public class TestCaseMessage
{

  private static final String TEST_CASE_SET = "Test Case Set:";
  private static final String TEST_CASE_NUMBER = "Test Case Number:";
  private static final String DESCRIPTION = "Description:";
  private static final String EXPECTED_RESULT = "Expected Result:";
  private static final String ASSERT_RESULT = "Assert Result:";
  private static final String ORIGINAL_MESSAGE = "Original Message:";
  private static final String QUICK_TRANSFORMATIONS = "Quick Transformations:";
  private static final String CUSTOM_TRANSFORMATIONS = "Custom Transformations:";
  private static final String CAUSE_ISSUES = "Cause Issues:";
  private static final String COMMENT = "Comment:";
  private static final String PATIENT_TYPE = "Patient Type:";

  protected static int createTestCase(TestCaseMessage testCaseMessage, StringBuffer message, int number, List<TestCaseMessage> testCaseMessageList)
  {
    String messageText = message.toString();
    if (messageText.length() > 0)
    {
      testCaseMessage.setMessageText(messageText);
      if (testCaseMessage.getTestCaseNumber().equals(""))
      {
        // try to find in message
        int pos = messageText.indexOf("|");
        int count = 1;
        while (pos != -1 && count < 9)
        {
          pos = messageText.indexOf("|", pos + 1);
          count++;
        }
        if (pos != -1)
        {
          int endPos = messageText.indexOf("|", pos + 1);
          if (endPos != -1)
          {
            String messageId = messageText.substring(pos + 1, endPos);
            endPos = messageId.indexOf("~");
            if (endPos != -1)
            {
              messageId = messageId.substring(0, endPos);
            }
            endPos = messageId.indexOf("^");
            if (endPos != -1)
            {
              messageId = messageId.substring(0, endPos);
            }
            testCaseMessage.setTestCaseNumber(messageId);
          }
        }
      }
      // couldn't find it htere, now looking in PID-3
      if (testCaseMessage.getTestCaseNumber().equals(""))
      {
        // try to find in message
        int pos = messageText.indexOf("PID|");
        pos = pos + 3;
        int count = 1;
        while (pos != -1 && count < 3)
        {
          pos = messageText.indexOf("|", pos + 1);
          count++;
        }
        if (pos != -1)
        {
          int endPos = messageText.indexOf("|", pos + 1);
          if (endPos != -1)
          {
            String messageId = messageText.substring(pos + 1, endPos);
            endPos = messageId.indexOf("~");
            if (endPos != -1)
            {
              messageId = messageId.substring(0, endPos);
            }
            endPos = messageId.indexOf("^");
            if (endPos != -1)
            {
              messageId = messageId.substring(0, endPos);
            }
            testCaseMessage.setTestCaseNumber(messageId);
          }
        }
      }
      number++;
      if (testCaseMessage.getTestCaseNumber().equals(""))
      {
        testCaseMessage.setTestCaseNumber("{" + number + "}");
      }
      boolean hasRange = false;
      String tcn = testCaseMessage.getTestCaseNumber();
      int openBracket = tcn.indexOf("[");
      if (openBracket != -1)
      {
        int closeBracket = tcn.indexOf("]", openBracket);
        if (closeBracket != -1)
        {
          String part1 = tcn.substring(0, openBracket);
          String range = tcn.substring(openBracket + 1, closeBracket);
          closeBracket++;
          String part2 = "";
          if (closeBracket < tcn.length())
          {
            part2 = tcn.substring(closeBracket);
          }
          {
            int dotdot = range.indexOf("..");
            if (dotdot != -1)
            {
              hasRange = true;
              String startNum = range.substring(0, dotdot).trim();
              if (startNum.equals(""))
              {
                startNum = "1";
              }
              String endNum = range.substring(dotdot + 2).trim();
              if (endNum.equals(""))
              {
                endNum = "100";
              }
              int count = 1;
              number--;
              while (count < 1000 && !startNum.equals(endNum))
              {
                number++;
                TestCaseMessage copy = new TestCaseMessage(testCaseMessage);
                copy.setTestCaseNumber(part1 + startNum + part2);
                addTestMessageToList(testCaseMessageList, copy);
                startNum = addOne(startNum);
                count++;
              }
              TestCaseMessage copy = new TestCaseMessage(testCaseMessage);
              copy.setTestCaseNumber(part1 + startNum + part2);
              addTestMessageToList(testCaseMessageList, copy);
            }
          }
        }
      }
      if (!hasRange)
      {
        addTestMessageToList(testCaseMessageList, testCaseMessage);
      }
      message.setLength(0);
    }
    return number;
  }

  public static void main(String[] args)
  {
    for (int i = 0; i < args.length; i++)
    {
      System.out.println((i + 1) + ". Add one: " + addOne(args[1]));
    }
  }

  protected static String addOne(String s)
  {
    String result = "";
    if (s.equals(""))
    {
      return "1";
    }
    int carry = 1;
    for (int i = s.length() - 1; i >= 0; i--)
    {
      char c = s.charAt(i);
      if (c < '0' || c > '9')
      {
        result = s.substring(0, i) + carry + result;
        carry = 0;
        break;
      }
      int num = (c - '0') + carry;
      if (num >= 10)
      {
        carry = num / 10;
        num = num - (carry * 10);
      } else
      {
        carry = 0;
      }
      result = num + result;
    }
    if (carry > 0)
    {
      result = carry + result;
    }
    return result;
  }

  protected static void addTestMessageToList(List<TestCaseMessage> testCaseMessageList, TestCaseMessage testCaseMessage)
  {
    if (testCaseMessage.getMessageText().startsWith("MSH|TRANSFORM"))
    {
      Transformer transformer = new Transformer();
      transformer.transform(testCaseMessage);
    }
    if (testCaseMessage.getOriginalMessage().equals(""))
    {
      testCaseMessage.setOriginalMessage(testCaseMessage.getMessageText());
    }

    for (int i = 0; i < testCaseMessageList.size(); i++)
    {
      if (testCaseMessage.getTestCaseNumber().equals(testCaseMessageList.get(i).getTestCaseNumber()))
      {
        testCaseMessageList.get(i).merge(testCaseMessage);
        testCaseMessage = null;
        break;
      }
    }
    if (testCaseMessage != null)
    {
      testCaseMessageList.add(testCaseMessage);
    }
  }

  private String testCaseSet = "";
  private String testCaseNumber = "";
  private String description = "";
  private String expectedResult = "";
  private String messageText = "";
  private String assertResult = "";
  private String assertResultStatus = "";
  private String assertResultText = "";
  private String originalMessage = "";
  private String preparedMessage = null;
  private String[] quickTransformations = new String[] {};
  private String quickTransformationsConverted = "";
  private String customTransformations = "";
  private String causeIssues = "";
  private List<Comment> comments = new ArrayList<Comment>();
  private String actualResultStatus = "";
  private String actualResultAckType = "";
  private String actualResultAckMessage = "";
  private Transformer.PatientType patientType = Transformer.PatientType.ANY_CHILD;
  private boolean hasIssue = false;

  public Transformer.PatientType getPatientType()
  {
    return patientType;
  }

  public void setPatientType(Transformer.PatientType patientType)
  {
    this.patientType = patientType;
  }

  public void setHasIssue(boolean hasIssue)
  {
    this.hasIssue = hasIssue;
  }

  public boolean hasIssue()
  {
    return hasIssue;
  }

  public TestCaseMessage() {
    // default;
  }

  public TestCaseMessage(TestCaseMessage copy) {
    this.testCaseSet = copy.testCaseSet;
    this.testCaseNumber = copy.testCaseNumber;
    this.description = copy.description;
    this.expectedResult = copy.expectedResult;
    this.messageText = copy.messageText;
    this.assertResult = copy.assertResult;
    this.assertResultStatus = copy.assertResultStatus;
    this.assertResultText = copy.assertResultText;
    this.originalMessage = copy.originalMessage;
    this.quickTransformations = new String[copy.quickTransformations.length];
    System.arraycopy(copy.quickTransformations, 0, this.quickTransformations, 0, copy.quickTransformations.length);
    this.quickTransformationsConverted = copy.quickTransformationsConverted;
    this.customTransformations = copy.customTransformations;
    this.causeIssues = copy.causeIssues;
    this.comments = new ArrayList<Comment>(copy.comments);
    this.actualResultStatus = copy.actualResultStatus;
    this.actualResultAckType = copy.actualResultAckType;
    this.actualResultAckMessage = copy.actualResultAckMessage;
    this.patientType = copy.patientType;
  }

  public void merge(TestCaseMessage updated)
  {
    if (!updated.getMessageText().equals(""))
    {
      messageText = updated.getMessageText();
    }
    if (!updated.getTestCaseSet().equals(""))
    {
      testCaseSet = updated.getTestCaseSet();
    }
    if (!updated.getDescription().equals(""))
    {
      description = updated.getDescription();
    }
    if (updated.getPatientType() != PatientType.ANY_CHILD)
    {
      patientType = updated.getPatientType();
    }
    if (!updated.getExpectedResult().equals(""))
    {
      expectedResult = updated.getExpectedResult();
    }
    if (!updated.getAssertResultStatus().equals(""))
    {
      assertResultStatus = updated.getAssertResultStatus();
    }
    if (!updated.getAssertResultText().equals(""))
    {
      assertResultText = updated.getAssertResultText();
    }
    if (!updated.getOriginalMessage().equals(""))
    {
      originalMessage = updated.getOriginalMessage();
    }
    if (updated.getQuickTransformations().length > 0 && !updated.getQuickTransformations()[0].equals(""))
    {
      quickTransformations = updated.getQuickTransformations();
    }
    if (!updated.getQuickTransformationsConverted().equals(""))
    {
      quickTransformationsConverted = updated.getQuickTransformationsConverted();
    }
    if (!updated.getCustomTransformations().equals(""))
    {
      customTransformations = updated.getCustomTransformations();
    }
    if (!updated.getCauseIssues().equals(""))
    {
      causeIssues = updated.getCauseIssues();
    }
    if (updated.getComments().size() > 0)
    {
      comments = updated.getComments();
    }
    if (!updated.getActualResultStatus().equals(""))
    {
      actualResultStatus = updated.getActualResultStatus();
    }
    if (!updated.getActualResultAckMessage().equals(""))
    {
      actualResultAckMessage = updated.getActualResultAckMessage();
    }
    if (!updated.getActualResultAckType().equals(""))
    {
      actualResultAckType = updated.getActualResultAckType();
    }
  }

  public String getActualResultStatus()
  {
    return actualResultStatus;
  }

  public void setActualResultStatus(String actualResultStatus)
  {
    this.actualResultStatus = actualResultStatus;
  }

  public String getActualResultAckMessage()
  {
    return actualResultAckMessage;
  }

  public void setActualResultAckMessage(String actualResultAckMessage)
  {
    this.actualResultAckMessage = actualResultAckMessage;
  }

  public String getActualResultAckType()
  {
    return actualResultAckType;
  }

  public void setActualResultAckType(String actualResultAckType)
  {
    this.actualResultAckType = actualResultAckType;
  }

  public List<Comment> getComments()
  {
    return comments;
  }

  public void setComment(String name, String text)
  {
    Comment comment = new Comment();
    comment.setName(name);
    comment.setText(text);
    comments.add(comment);
  }

  public class Comment
  {

    private String name = "";
    private String text = "";

    public String getName()
    {
      return name;
    }

    public String getText()
    {
      return text;
    }

    public void setName(String name)
    {
      this.name = name;
    }

    public void setText(String text)
    {
      this.text = text;
    }
  }

  public String getAssertResultStatus()
  {
    return assertResultStatus;
  }

  public void setAssertResultStatus(String assertResultStatus)
  {
    this.assertResultStatus = assertResultStatus;
  }

  public String getAssertResultText()
  {
    return assertResultText;
  }

  public void setAssertResultText(String assertResultText)
  {
    this.assertResultText = assertResultText;
  }

  public String getDescription()
  {
    return description;
  }

  public void setDescription(String description)
  {
    this.description = description;
  }

  public String getExpectedResult()
  {
    return expectedResult;
  }

  public void setExpectedResult(String expectedResult)
  {
    this.expectedResult = expectedResult;
  }

  public String getMessageText()
  {
    return messageText;
  }

  public void setMessageText(String messageText)
  {
    this.messageText = messageText;
  }

  public String getTestCaseNumber()
  {
    return testCaseNumber;
  }

  public void setTestCaseNumber(String testCaseNumber)
  {
    this.testCaseNumber = testCaseNumber;
  }

  public void setTestCaseSet(String testCaseSet)
  {
    this.testCaseSet = testCaseSet;
  }

  public String getTestCaseSet()
  {
    return testCaseSet;
  }

  public String getAssertResult()
  {
    return assertResult;
  }

  public void setAssertResult(String assertResult)
  {
    this.assertResult = assertResult;
    if (assertResult != null)
    {
      int pos = assertResult.indexOf("-");
      if (pos != -1)
      {
        assertResultStatus = assertResult.substring(0, pos).trim();
        pos++;
        if (pos < assertResult.length())
        {
          assertResultText = assertResult.substring(pos).trim();
        }
      } else
      {
        assertResultStatus = assertResult;
      }
    }
  }

  public String getCustomTransformations()
  {
    return customTransformations;
  }

  public void setCustomTransformations(String customTransformations)
  {
    this.customTransformations = customTransformations;
  }

  public String getCauseIssues()
  {
    return causeIssues;
  }

  public void setCauseIssues(String causeIssues)
  {
    this.causeIssues = causeIssues;
  }

  public void addCauseIssues(String causeIssues)
  {
    this.causeIssues += causeIssues + "\n";
  }

  private void appendCustomTransformation(String customTransformation)
  {
    if (this.customTransformations == null)
    {
      this.customTransformations = "";
    }
    this.customTransformations += customTransformation + "\n";
  }

  private void appendCauseIssue(String causeIssue)
  {
    if (this.causeIssues == null)
    {
      this.causeIssues = "";
    }
    this.causeIssues += causeIssue + "\n";
  }

  public String getOriginalMessage()
  {
    return originalMessage;
  }

  public void setOriginalMessage(String originalMessage)
  {
    this.originalMessage = originalMessage;
  }

  public String[] getQuickTransformations()
  {
    return quickTransformations;
  }

  public void setQuickTransformations(String[] quickTransformations)
  {
    this.quickTransformations = quickTransformations;
  }

  public static List<TestCaseMessage> createTestCaseMessageList(String source) throws Exception
  {
    List<TestCaseMessage> testCaseMessageList = new ArrayList<TestCaseMessage>();

    try
    {
      BufferedReader in = new BufferedReader(new StringReader(source));
      String line = null;
      StringBuffer message = new StringBuffer();
      TestCaseMessage testCaseMessage = new TestCaseMessage();
      int number = 0;
      boolean readingHL7 = false;
      String lastList = "";
      while ((line = in.readLine()) != null)
      {
        line = line.trim();
        if (line.length() > 0)
        {
          if (line.startsWith("TC:") || line.startsWith(TEST_CASE_NUMBER))
          {
            number = createTestCase(testCaseMessage, message, number, testCaseMessageList);
            testCaseMessage = new TestCaseMessage();
            readingHL7 = false;
            lastList = "";
          } else if (line.startsWith("MSH|"))
          {
            if (readingHL7)
            {
              // found test case without a proper header
              number = createTestCase(testCaseMessage, message, number, testCaseMessageList);
              testCaseMessage = new TestCaseMessage();
              readingHL7 = false;
              lastList = "";
            }
          }
          if (line.startsWith("TC:") || line.startsWith(TEST_CASE_NUMBER))
          {
            testCaseMessage.setTestCaseNumber(readValue(line));
          } else if (line.startsWith(COMMENT))
          {
            String[] parts = split(line);
            if (parts.length == 2 && parts[0] != null && parts[1] != null)
            {
              testCaseMessage.setComment(parts[0].trim(), parts[1].trim());
            }
          } else if (line.startsWith(DESCRIPTION))
          {
            testCaseMessage.setDescription(readValue(line));
          } else if (line.startsWith(PATIENT_TYPE))
          {
            testCaseMessage.setPatientType(PatientType.valueOf(readValue(line)));
          } else if (line.startsWith(TEST_CASE_SET))
          {
            testCaseMessage.setTestCaseSet(readValue(line));
          } else if (line.startsWith(EXPECTED_RESULT))
          {
            testCaseMessage.setExpectedResult(readValue(line));
          } else if (line.startsWith(ORIGINAL_MESSAGE))
          {
            testCaseMessage.setOriginalMessage(readValue(line).replaceAll("\\Q<CR>\\E", "\r"));
          } else if (line.startsWith(QUICK_TRANSFORMATIONS))
          {
            testCaseMessage.setQuickTransformations(readValues(line));
          } else if (line.startsWith(ASSERT_RESULT))
          {
            testCaseMessage.setAssertResult(readValue(line));
          } else if (line.startsWith(CUSTOM_TRANSFORMATIONS))
          {
            testCaseMessage.setCustomTransformations(readValue(line).replaceAll("\\Q<CR>\\E", "\r"));
            lastList = "CT";
          } else if (line.startsWith(CAUSE_ISSUES))
          {
            testCaseMessage.setCauseIssues(readValue(line).replaceAll("\\Q<CR>\\E", "\r"));
            lastList = "CI";
          } else if (line.startsWith("+") && line.length() > 1)
          {
            if (lastList.equals("CT"))
            {
              testCaseMessage.appendCustomTransformation(line.substring(1).trim());
            } else if (lastList.equals("CI"))
            {
              testCaseMessage.appendCauseIssue(line.substring(1).trim());
            }
          } else if (line.startsWith("--") || line.startsWith("//"))
          {
            // ignore, this line is a comment
          } else if (line.startsWith("MSH|"))
          {
            // Looks like part of an HL7 message
            message.append(line);
            message.append("\r");
            readingHL7 = true;
          } else if (readingHL7)
          {
            if (line.length() > 3 && line.charAt(3) == '|')
            {
              message.append(line);
              message.append("\r");
            }
          } else
          {
            lastList = "";
          }
        }
      }
      if (message.length() > 0)
      {
        number = createTestCase(testCaseMessage, message, number, testCaseMessageList);
      }
    } catch (Exception e)
    {
      throw new Exception("Unable to intantiate test case messages", e);
    }
    return testCaseMessageList;
  }

  private static String readValue(String s)
  {
    int pos = s.indexOf(":");
    if (pos == -1)
    {
      return "";
    }
    pos++;
    if (pos == s.length())
    {
      return "";
    }
    return s.substring(pos).trim();
  }

  private static String[] readValues(String s)
  {
    String[] values = readValue(s).split("\\,");
    if (values == null)
    {
      values = new String[] {};
    }
    for (int i = 0; i < values.length; i++)
    {
      values[i] = values[i].trim();
    }
    return values;
  }

  private static String[] split(String s)
  {
    int pos = s.indexOf(":");
    if (pos == -1 || (++pos == s.length()))
    {
      return new String[] {};
    }
    s = s.substring(pos).trim();
    pos = s.indexOf("-");
    if (pos == -1 || (++pos == s.length()))
    {
      return new String[] {};
    }
    return new String[] { s.substring(0, pos - 1), s.substring(pos) };
  }

  public String createText()
  {
    return createText(false);
  }

  public String createText(boolean forHtml)
  {
    StringWriter stringWriter = new StringWriter();
    PrintWriter stringOut = new PrintWriter(stringWriter);
    try
    {
      stringOut.println("--------------------------------------------------------------------------------");
      stringOut.println(TEST_CASE_NUMBER + " " + testCaseNumber);
      stringOut.println(TEST_CASE_SET + " " + testCaseSet);
      stringOut.println(DESCRIPTION + " " + description);
      stringOut.println(EXPECTED_RESULT + " " + expectedResult);
      if (!assertResultText.equals(""))
      {
        stringOut.println(ASSERT_RESULT + " " + assertResultStatus + " - " + assertResultText);
      }
      for (Comment comment : comments)
      {
        stringOut.println(COMMENT + " " + comment.getName() + " - " + comment.getText());
      }
      if (!originalMessage.equals(messageText))
      {
        stringOut.print(ORIGINAL_MESSAGE + " ");
        BufferedReader inBase = new BufferedReader(new StringReader(originalMessage));
        String line;
        while ((line = inBase.readLine()) != null)
        {
          line = line.trim();
          stringOut.print(line + (forHtml ? "&lt;CR&gt;" : "<CR>"));
        }
        stringOut.println();
      }
      if (quickTransformations != null && quickTransformations.length > 0 && quickTransformations[0].trim().length() > 0)
      {
        stringOut.print(QUICK_TRANSFORMATIONS + " ");
        boolean first = true;
        for (String extra : quickTransformations)
        {
          if (first)
          {
            stringOut.print(extra + "");
            first = false;
          } else
          {
            stringOut.print(", " + extra);
          }
        }
        stringOut.println();
      }
      if (customTransformations != null && !customTransformations.equals(""))
      {
        stringOut.println(CUSTOM_TRANSFORMATIONS + " ");
        BufferedReader inTransform = new BufferedReader(new StringReader(customTransformations));
        String line;
        while ((line = inTransform.readLine()) != null)
        {
          line = line.trim();
          stringOut.println(" + " + line);
        }
      }
      if (causeIssues != null && !causeIssues.equals(""))
      {
        stringOut.println(CAUSE_ISSUES + " ");
        BufferedReader inTransform = new BufferedReader(new StringReader(causeIssues));
        String line;
        while ((line = inTransform.readLine()) != null)
        {
          line = line.trim();
          stringOut.println(" + " + line);
        }
      }
      stringOut.println(PATIENT_TYPE + " " + patientType);
      stringOut.println("--------------------------------------------------------------------------------");
      stringOut.print(messageText);
    } catch (Exception ioe)
    {
      stringOut.println("Exception occured: " + ioe);
      ioe.printStackTrace(stringOut);
    } finally
    {
      stringOut.close();
    }
    return stringWriter.toString();
  }

  public String getQuickTransformationsConverted()
  {
    return quickTransformationsConverted;
  }

  public void setQuickTransformationsConverted(String quickTransformationsConverted)
  {
    this.quickTransformationsConverted = quickTransformationsConverted;
  }

  public String getPreparedMessage()
  {
    if (preparedMessage == null)
    {
      preparedMessage = originalMessage;
    }
    return preparedMessage;
  }

  public void setPreparedMessage(String preparedMessage)
  {
    this.preparedMessage = preparedMessage;
  }

  public void prepareMessageAddSegment(String segmentId, String afterSegmentId)
  {
    StringBuilder sb = new StringBuilder();
    try
    {
      BufferedReader reader = new BufferedReader(new StringReader(getPreparedMessage()));
      String line = null;
      boolean inserted = false;
      while ((line = reader.readLine()) != null)
      {
        sb.append(line);
        sb.append("\r");
        if (!inserted && line.startsWith(afterSegmentId))
        {
          sb.append(segmentId);
          sb.append("|\r");
          inserted = true;
        }
      }
    } catch (IOException ioe)
    {
      throw new IllegalArgumentException("Unable to read string", ioe);
    }
    preparedMessage = sb.toString();
  }

  public void prepareMessageRemoveSegment(String segmentId)
  {
    StringBuilder sb = new StringBuilder();
    try
    {
      BufferedReader reader = new BufferedReader(new StringReader(getPreparedMessage()));
      String line = null;
      boolean removed = false;
      while ((line = reader.readLine()) != null)
      {
        if (!removed && line.startsWith(segmentId))
        {
          removed = true;
        } else
        {
          sb.append(line);
          sb.append("\r");
        }
      }
    } catch (IOException ioe)
    {
      throw new IllegalArgumentException("Unable to read string", ioe);
    }
    preparedMessage = sb.toString();
  }
}
