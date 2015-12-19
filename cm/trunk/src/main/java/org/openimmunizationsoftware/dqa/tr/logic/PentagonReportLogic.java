package org.openimmunizationsoftware.dqa.tr.logic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.openimmunizationsoftware.dqa.tr.RecordServletInterface;
import org.openimmunizationsoftware.dqa.tr.model.PentagonReport;
import org.openimmunizationsoftware.dqa.tr.model.TestConducted;
import org.openimmunizationsoftware.dqa.tr.model.TestMessage;
import org.openimmunizationsoftware.dqa.tr.model.TestSection;
import org.openimmunizationsoftware.dqa.tr.model.Transform;
import org.openimmunizationsoftware.dqa.tr.model.TransportAnalysis;
import org.openimmunizationsoftware.dqa.tr.model.TransportWsdlCdc;

public class PentagonReportLogic
{

  private static final String[] DATA_AVAILABLE_SECTIONS = { RecordServletInterface.VALUE_TEST_SECTION_TYPE_BASIC,
      RecordServletInterface.VALUE_TEST_SECTION_TYPE_INTERMEDIATE, RecordServletInterface.VALUE_TEST_SECTION_TYPE_ADVANCED,
      RecordServletInterface.VALUE_TEST_SECTION_TYPE_EXCEPTIONAL, RecordServletInterface.VALUE_TEST_SECTION_TYPE_PROFILING,
      RecordServletInterface.VALUE_TEST_SECTION_TYPE_ONC_2015, RecordServletInterface.VALUE_TEST_SECTION_TYPE_NOT_ACCEPTED };

  public static PentagonReport createOrReturnPentagonReport(TestConducted testConducted, Session dataSession)
  {
    {
      Query query = dataSession.createQuery("from PentagonReport where testConducted = ?");
      query.setParameter(0, testConducted);
      List<PentagonReport> pentagonReportList = query.list();
      if (pentagonReportList.size() > 0)
      {
        return pentagonReportList.get(0);
      }
    }
    PentagonReport pentagonReport = new PentagonReport();
    pentagonReport.setTestConducted(testConducted);

    Map<String, TestSection> testSectionMap = new HashMap<String, TestSection>();
    {
      Query query = dataSession.createQuery("from TestSection where testConducted = ?");
      query.setParameter(0, testConducted);
      List<TestSection> testSectionList = query.list();
      for (TestSection testSection : testSectionList)
      {
        testSectionMap.put(testSection.getTestSectionType(), testSection);
      }
    }

    if (testSectionMap.get(RecordServletInterface.VALUE_TEST_SECTION_TYPE_NOT_ACCEPTED) != null)
    {
      TestSection testSection = testSectionMap.get(RecordServletInterface.VALUE_TEST_SECTION_TYPE_NOT_ACCEPTED);
      pentagonReport.setScoreCBadMessages(testSection.getScoreLevel1());
    }
    if (testSectionMap.get(RecordServletInterface.VALUE_TEST_SECTION_TYPE_BASIC) != null)
    {
      TestSection testSection = testSectionMap.get(RecordServletInterface.VALUE_TEST_SECTION_TYPE_BASIC);
      pentagonReport.setScoreUFVxu2014(testSection.getScoreLevel1());
      pentagonReport.setScoreCGoodMessages(testSection.getScoreLevel1());
    }
    if (testSectionMap.get(RecordServletInterface.VALUE_TEST_SECTION_TYPE_BASIC) != null
        && testSectionMap.get(RecordServletInterface.VALUE_TEST_SECTION_TYPE_NOT_ACCEPTED) != null)
    {
      int countOk = 0;
      int countTotal = 0;
      TestSection testSectionBasic = testSectionMap.get(RecordServletInterface.VALUE_TEST_SECTION_TYPE_BASIC);
      TestSection testSectionNotAccepted = testSectionMap.get(RecordServletInterface.VALUE_TEST_SECTION_TYPE_NOT_ACCEPTED);
      Query query = dataSession.createQuery("from TestMessage where testSection = ? or testSection = ?");
      query.setParameter(0, testSectionBasic);
      query.setParameter(1, testSectionNotAccepted);
      List<TestMessage> testMessageList = query.list();
      for (TestMessage testMessage : testMessageList)
      {
        if (testMessage.getResultAckConformance() != null
            && !testMessage.getResultAckConformance().equals(RecordServletInterface.VALUE_RESULT_ACK_CONFORMANCE_NOT_RUN))
        {
          countTotal++;
          if (testMessage.getResultAckConformance().equals(RecordServletInterface.VALUE_RESULT_ACK_CONFORMANCE_OK))
          {
            countOk++;
          }
        }
      }
      if (countTotal != 0)
      {
        pentagonReport.setScoreCAckConform(((int) 100.0 * countOk / countTotal));
      }
    }

    {
      int count = 0;
      int countPass = 0;
      for (String sectionNames : new String[] { RecordServletInterface.VALUE_TEST_SECTION_TYPE_BASIC,
          RecordServletInterface.VALUE_TEST_SECTION_TYPE_NOT_ACCEPTED })
      {
        TestSection testSection = testSectionMap.get(sectionNames);
        Query query = dataSession.createQuery("from TestMessage where testSection = ? and testType = 'query'");
        query.setParameter(0, testSection);
        List<TestMessage> testMessageList = query.list();
        for (TestMessage testMessage : testMessageList)
        {
          if (testMessage.getResultStoreStatus() != null)
          {
            if (testMessage.getResultStoreStatus().equals(RecordServletInterface.VALUE_RESULT_ACK_STORE_STATUS_ACCEPTED_RETURNED))
            {
              count++;
              countPass++;
            } else if (testMessage.getResultStoreStatus().equals(RecordServletInterface.VALUE_RESULT_ACK_STORE_STATUS_ACCEPTED_NOT_RETURNED))
            {
              count++;
            }
          }
        }
      }
      if (count > 0)
      {
        pentagonReport.setScoreCGoodData((int) (100.0 * countPass / count));
      }
    }

    {
      int count = 0;
      int countPass = 0;
      for (String sectionNames : new String[] { RecordServletInterface.VALUE_TEST_SECTION_TYPE_BASIC,
          RecordServletInterface.VALUE_TEST_SECTION_TYPE_NOT_ACCEPTED })
      {
        TestSection testSection = testSectionMap.get(sectionNames);
        Query query = dataSession.createQuery("from TestMessage where testSection = ? and testType = 'query'");
        query.setParameter(0, testSection);
        List<TestMessage> testMessageList = query.list();
        for (TestMessage testMessage : testMessageList)
        {
          if (testMessage.getResultStoreStatus() != null)
          {
            if (testMessage.getResultStoreStatus().equals(RecordServletInterface.VALUE_RESULT_ACK_STORE_STATUS_NOT_ACCEPTED_RETURNED))
            {
              count++;
            } else if (testMessage.getResultStoreStatus().equals(RecordServletInterface.VALUE_RESULT_ACK_STORE_STATUS_NOT_ACCEPTED_NOT_RETURNED))
            {
              count++;
              countPass++;
            }
          }
        }
      }
      if (count > 0)
      {
        pentagonReport.setScoreCBadData((int) (100.0 * countPass / count));
      }
    }

    if (testSectionMap.get(RecordServletInterface.VALUE_TEST_SECTION_TYPE_ONC_2015) != null)
    {
      TestSection testSection = testSectionMap.get(RecordServletInterface.VALUE_TEST_SECTION_TYPE_ONC_2015);
      pentagonReport.setScoreUFVxu2015(testSection.getScoreLevel1());
    }
    if (testSectionMap.get(RecordServletInterface.VALUE_TEST_SECTION_TYPE_ADVANCED) != null)
    {
      TestSection testSection = testSectionMap.get(RecordServletInterface.VALUE_TEST_SECTION_TYPE_ADVANCED);
      pentagonReport.setScoreUFSensitive(testSection.getScoreLevel1());
    }
    pentagonReport.setScoreUFCodedValues(testConducted.getScoreCoded());
    pentagonReport.setScoreUFTolerant(testConducted.getScoreTolerance());
    pentagonReport.setScoreUFEhrExamples(testConducted.getScoreEhr());

    if (testSectionMap.get(RecordServletInterface.VALUE_TEST_SECTION_TYPE_CONFORMANCE_2015) != null)
    {
      TestSection testSection = testSectionMap.get(RecordServletInterface.VALUE_TEST_SECTION_TYPE_CONFORMANCE_2015);
      pentagonReport.setScoreUCAcksConform(testSection.getScoreLevel1());
    }

    {
      Query query = dataSession.createQuery("from Transform where testConducted = ?");
      query.setParameter(0, testConducted);
      List<Transform> transformList = query.list();
      int countExpected = 0;
      for (Transform transform : transformList)
      {
        if (transform.getTransformField().isTransformExpected())
        {
          countExpected++;
        }
      }
      int countUnexpected = transformList.size() - countExpected;
      int totalScore = 0;
      if (countUnexpected == 0)
      {
        totalScore += 70;
      } else if (countUnexpected == 1)
      {
        totalScore += 50;
      } else if (countUnexpected == 2)
      {
        totalScore += 30;
      }
      if (countExpected <= 5)
      {
        totalScore += 30;
      } else if (countExpected <= 7)
      {
        totalScore += 15;
      }
      pentagonReport.setScoreUCModifications(totalScore);

    }

    {
      int perUpdateCount = testConducted.getPerUpdateCount();
      int perUpdateTotal = testConducted.getPerUpdateTotal();
      if (perUpdateTotal > 0)
      {
        int millisecondsPerUpdate = Math.round(((float) perUpdateTotal) / perUpdateCount);
        if (millisecondsPerUpdate < 1050)
        {
          pentagonReport.setScoreUFPerformance(100);
        } else if (millisecondsPerUpdate > 2000)
        {
          pentagonReport.setScoreUFPerformance(0);
        } else
        {
          pentagonReport.setScoreUFPerformance((int) Math.round(-100 * ((millisecondsPerUpdate - 2000.0) / 1000)));
        }
      }
    }

    {
      int perQueryCount = testConducted.getPerQueryCount();
      int perQueryTotal = testConducted.getPerQueryTotal();
      if (perQueryTotal > 0)
      {
        int millisecondsPerQuery = Math.round(((float) perQueryTotal) / perQueryCount);
        if (millisecondsPerQuery < 1050)
        {
          pentagonReport.setScoreQFPerformance(100);
        } else if (millisecondsPerQuery > 2000)
        {
          pentagonReport.setScoreQFPerformance(0);
        } else
        {
          pentagonReport.setScoreQFPerformance((int) Math.round(-100 * ((millisecondsPerQuery - 2000.0) / 1000)));
        }
      }
    }

    {
      int scoreC = 0;
      scoreC = addWeightToScore(scoreC, 20, pentagonReport.getScoreCAckConform());
      scoreC = addWeightToScore(scoreC, 20, pentagonReport.getScoreCGoodMessages());
      scoreC = addWeightToScore(scoreC, 20, pentagonReport.getScoreCBadMessages());
      scoreC = addWeightToScore(scoreC, 18, pentagonReport.getScoreCGoodData());
      scoreC = addWeightToScore(scoreC, 12, pentagonReport.getScoreCBadData());
      pentagonReport.setScoreC(scoreC / 100);
    }
    {
      int scoreUF = 0;
      scoreUF = addWeightToScore(scoreUF, 35, pentagonReport.getScoreUFVxu2014());
      scoreUF = addWeightToScore(scoreUF, 25, pentagonReport.getScoreUFVxu2015());
      scoreUF = addWeightToScore(scoreUF, 12, pentagonReport.getScoreUFSensitive());
      scoreUF = addWeightToScore(scoreUF, 10, pentagonReport.getScoreUFCodedValues());
      scoreUF = addWeightToScore(scoreUF, 8, pentagonReport.getScoreUFTolerant());
      scoreUF = addWeightToScore(scoreUF, 5, pentagonReport.getScoreUFEhrExamples());
      scoreUF = addWeightToScore(scoreUF, 5, pentagonReport.getScoreUFPerformance());
      pentagonReport.setScoreUF(scoreUF / 100);
    }
    {
      int scoreUC = 0;
      scoreUC = addWeightToScore(scoreUC, 30, pentagonReport.getScoreUCModifications());
      scoreUC = addWeightToScore(scoreUC, 30, pentagonReport.getScoreUCConflicts());
      scoreUC = addWeightToScore(scoreUC, 20, pentagonReport.getScoreUCConstraints());
      scoreUC = addWeightToScore(scoreUC, 20, pentagonReport.getScoreUCAcksConform());
      pentagonReport.setScoreUC(scoreUC / 100);
    }
    if (testSectionMap.get(RecordServletInterface.VALUE_TEST_SECTION_TYPE_QBP_SUPPORT) != null)
    {
      TestSection testSection = testSectionMap.get(RecordServletInterface.VALUE_TEST_SECTION_TYPE_QBP_SUPPORT);
      pentagonReport.setScoreQFQbp2015(testSection.getScoreLevel2());
    }
    {
      int count = 0;
      int countPass = 0;
      for (String sectionNames : DATA_AVAILABLE_SECTIONS)
      {
        TestSection testSection = testSectionMap.get(sectionNames);
        Query query = dataSession.createQuery("from TestMessage where testSection = ? and testType = 'query'");
        query.setParameter(0, testSection);
        List<TestMessage> testMessageList = query.list();
        for (TestMessage testMessage : testMessageList)
        {
          if (testMessage.getResultStoreStatus() != null)
          {
            if (testMessage.getResultStoreStatus().equals(RecordServletInterface.VALUE_RESULT_ACK_STORE_STATUS_ACCEPTED_RETURNED))
            {
              count++;
              countPass++;
            } else if (testMessage.getResultStoreStatus().equals(RecordServletInterface.VALUE_RESULT_ACK_STORE_STATUS_ACCEPTED_NOT_RETURNED))
            {
              count++;
            }
          }
        }
      }
      if (count > 0)
      {
        pentagonReport.setScoreQFDataAvailable((int) (100.0 * countPass / count));
      }
    }
    {
      int scoreQF = 0;
      scoreQF = addWeightToScore(scoreQF, 30, pentagonReport.getScoreQFQbp2015());
      scoreQF = addWeightToScore(scoreQF, 20, pentagonReport.getScoreQFDataAvailable());
      scoreQF = addWeightToScore(scoreQF, 15, pentagonReport.getScoreQFDeduplication());
      scoreQF = addWeightToScore(scoreQF, 15, pentagonReport.getScoreQFForecaster());
      scoreQF = addWeightToScore(scoreQF, 10, pentagonReport.getScoreQFPerformance());
      scoreQF = addWeightToScore(scoreQF, 10, pentagonReport.getScoreQFMinimumQuery());
      pentagonReport.setScoreQF(scoreQF / 100);
    }
    {
      int scoreQC = 0;
      scoreQC = addWeightToScore(scoreQC, 60, pentagonReport.getScoreQCResponsesConform());
      scoreQC = addWeightToScore(scoreQC, 40, pentagonReport.getScoreQCSoapConforms());
      pentagonReport.setScoreQC(scoreQC / 100);
    }

    if (testSectionMap.get(RecordServletInterface.VALUE_TEST_SECTION_TYPE_CONFORMANCE_2015) != null)
    {
      TestSection testSection = testSectionMap.get(RecordServletInterface.VALUE_TEST_SECTION_TYPE_CONFORMANCE_2015);
      pentagonReport.setScoreQCResponsesConform(testSection.getScoreLevel2());
    }

    {
      TransportWsdlCdc transportWsdlCdc = null;
      {
        Query query = dataSession.createQuery("from TransportWsdlCdc where transportAnalysis.connectionLabel = ?");
        query.setParameter(0, testConducted.getConnectionLabel());
        List<TransportWsdlCdc> transportWsdlCdcList = query.list();
        if (transportWsdlCdcList.size() > 0)
        {
          transportWsdlCdc = transportWsdlCdcList.get(0);
        }
      }
      if (transportWsdlCdc != null)
      {
        TransportAnalysis transportAnalysis = transportWsdlCdc.getTransportAnalysis();
        if (transportAnalysis.getTransportType().equalsIgnoreCase("CDC WSDL") && transportAnalysis.getReportComplete().equalsIgnoreCase("Yes"))
        {
          int score = 0;
          if (transportWsdlCdc.getSsmConforms().equalsIgnoreCase("Yes"))
          {
            score += 70;
          }
          if (transportWsdlCdc.getCtConforms().equalsIgnoreCase("Yes"))
          {
            score += 20;
          }
          if (transportWsdlCdc.getSfConforms().equalsIgnoreCase("Yes"))
          {
            score += 10;
          }
          pentagonReport.setScoreQCSoapConforms(score);
        }
      }

    }

    Transaction transaction = dataSession.beginTransaction();
    dataSession.save(pentagonReport);
    transaction.commit();
    return pentagonReport;
  }

  public static int addWeightToScore(int scoreC, int weight, int score)
  {
    if (score >= 0)
    {
      scoreC += weight * score;
    }
    return scoreC;
  }
}
