package org.openimmunizationsoftware.dqa.tr.logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.tr.model.ProfileField;
import org.openimmunizationsoftware.dqa.tr.model.ProfileFieldValue;
import org.openimmunizationsoftware.dqa.tr.model.TestConducted;
import org.openimmunizationsoftware.dqa.tr.model.TestMessage;

public class ProfileFieldValueSetup extends Thread
{

  public ProfileFieldValueSetup(Session dataSession) {
    this.dataSession = dataSession;
  }

  private Session dataSession = null;
  private String status = "";

  public String getStatus()
  {
    return status;
  }

  private Map<String, ProfileField> profileFieldMap = new HashMap<String, ProfileField>();
  private Set<String> unsupportedProfileFieldNameSet = new HashSet<String>();

  @Override
  public void run()
  {
    Query query = dataSession.createQuery("from TestConducted");
    List<TestConducted> testConductedList = query.list();
    int totalSize = testConductedList.size();
    int pos = 0;
    for (TestConducted testConducted : testConductedList)
    {
      pos++;
      status = "processing " + pos + " of " + totalSize;
      query = dataSession.createQuery("from TestMessage where testSection.testConducted = ?");
      query.setParameter(0, testConducted);
      List<TestMessage> testMessageList = query.list();
      for (TestMessage testMessage : testMessageList)
      {

        if (testMessage.getResultMessageActual() != null && !testMessage.getResultMessageActual().equals(""))
        {
          Map<String, ProfileFieldValue> profileFieldValueMap = new HashMap<String, ProfileFieldValue>();
          {
            query = dataSession.createQuery("from ProfileFieldValue where testMessage = ?");
            query.setParameter(0, testMessage);
            List<ProfileFieldValue> profileFieldValueList = query.list();
            for (ProfileFieldValue profileFieldValue : profileFieldValueList)
            {
              profileFieldValueMap.put(profileFieldValue.getFieldValue(), profileFieldValue);
            }
          }

          try
          {
            BufferedReader lineReader = new BufferedReader(new StringReader(testMessage.getResultMessageActual()));
            String line = lineReader.readLine();
            String peakAhead;
            String rxaPrepend = "";
            while (line != null)
            {
              peakAhead = lineReader.readLine();
              if (line.length() > 3)
              {
                String segmentName = line.substring(0, 3);
                String[] fields = line.split("\\|");
                {
                  String prepend = null;
                  if (segmentName.equals("OBX"))
                  {
                    if (fields.length > 3)
                    {
                      String obsCode = fields[3];
                      if (obsCode != null && obsCode.length() > 0)
                      {
                        prepend = strip(obsCode);
                      }
                    }
                  } else if (segmentName.equals("ORC"))
                  {
                    prepend = "Admin";
                    if (peakAhead.startsWith("RXA|"))
                    {
                      prepend = determinePrepend(peakAhead.split("\\|"));
                    }
                  } else if (segmentName.equals("RXA"))
                  {
                    prepend = determinePrepend(fields);
                    rxaPrepend = prepend;
                  } else if (segmentName.equals("NTE") || segmentName.equals(""))
                  {
                    prepend = determinePrepend(fields);
                    rxaPrepend = prepend;
                  }
                  if (prepend != null)
                  {
                    segmentName = prepend + " " + segmentName;
                  }
                }
                for (int fieldPos = 1; fieldPos < fields.length; fieldPos++)
                {
                  if (fields[fieldPos] != null && fields[fieldPos].length() > 0)
                  {

                  }
                }
              }
              line = peakAhead;
            }
          } catch (IOException ioe)
          {
            // shouldn't happen when reading a string
            ioe.printStackTrace();
          }
        }
      }
    }
    status = "complete";
  }

  public String determinePrepend(String[] fields)
  {
    String prepend;
    String[] fieldsToExamine = fields;
    String cvxCode = strip(fieldsToExamine[5]);
    String completionStatus = strip(fieldsToExamine[20]);
    String informationSource = strip(fieldsToExamine[9]);
    prepend = informationSource.equals("00") ? "Admin" : "Hist";
    if (cvxCode.equals("998"))
    {
      prepend = "Non-Admin";
    }
    if (completionStatus.equals("RE"))
    {
      prepend = "Refusal";
    }
    return prepend;
  }

  public String strip(String obsCode)
  {
    if (obsCode == null || obsCode.length() == 0)
    {
      return "";
    }
    String value = obsCode;
    {
      int i = obsCode.indexOf("^");
      if (i > 0)
      {
        value = value.substring(0, i);
      }
    }
    obsCode = value;
    return obsCode;
  }
}
