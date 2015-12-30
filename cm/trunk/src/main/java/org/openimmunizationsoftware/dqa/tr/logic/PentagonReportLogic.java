package org.openimmunizationsoftware.dqa.tr.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.openimmunizationsoftware.dqa.cm.servlet.pentagon.PentagonRow;
import org.openimmunizationsoftware.dqa.tr.RecordServletInterface;
import org.openimmunizationsoftware.dqa.tr.model.PentagonReport;
import org.openimmunizationsoftware.dqa.tr.model.TestConducted;
import org.openimmunizationsoftware.dqa.tr.model.TestSection;

public class PentagonReportLogic
{

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

    ArrayList<PentagonRow> pentagonRowList = PentagonRow.createPentagonRowList(pentagonReport);
    for (PentagonRow pentagonRow : pentagonRowList)
    {
      pentagonRow.calculateScores(testConducted, dataSession, pentagonReport, testSectionMap);
    }

    Transaction transaction = dataSession.beginTransaction();
    dataSession.save(pentagonReport);
    transaction.commit();
    return pentagonReport;
  }

  public static ConformanceCount getConformanceCounts(TestConducted testConducted, Session dataSession, String testType)
  {
    ConformanceCount conformanceCount = new ConformanceCount();
    {
      long countTotal = 0;
      int countNotRun = 0;
      long countOk = 0;
      int countError = 0;
      Query query = dataSession.createQuery("select tm.resultAckConformance, count(tm.resultAckConformance) from TestMessage tm "
          + "where tm.testSection.testConducted = ? and tm.testType = ? group by tm.resultAckConformance");
      query.setParameter(0, testConducted);
      query.setParameter(1, testType);
      List<Object[]> objectsList = query.list();
      for (Object[] object : objectsList)
      {
        String resultAckConformance = (String) object[0];
        Long count = (Long) object[1];
        if (resultAckConformance != null)
        {
          if (resultAckConformance.equals(RecordServletInterface.VALUE_RESULT_ACK_CONFORMANCE_OK))
          {
            countOk += (long) count;
            countTotal += (long) count;
          } else if (resultAckConformance.equals(RecordServletInterface.VALUE_RESULT_ACK_CONFORMANCE_ERROR))
          {
            countError += (long) count;
            countTotal += (long) count;
          } else if (resultAckConformance.equals(RecordServletInterface.VALUE_RESULT_ACK_CONFORMANCE_NOT_RUN))
          {
            countTotal += (long) count;
            countNotRun += (long) count;
          }
        }
      }
      conformanceCount.setCountTotal((int) countTotal);
      conformanceCount.setCountNotRun(countNotRun);
      conformanceCount.setCountError(countError);
      conformanceCount.setCountOk((int) countOk);
    }
    return conformanceCount;
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
