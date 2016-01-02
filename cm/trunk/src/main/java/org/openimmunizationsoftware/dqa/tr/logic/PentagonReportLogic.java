package org.openimmunizationsoftware.dqa.tr.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.openimmunizationsoftware.dqa.cm.servlet.pentagon.PentagonBoxHelper;
import org.openimmunizationsoftware.dqa.cm.servlet.pentagon.PentagonRowHelper;
import org.openimmunizationsoftware.dqa.tr.RecordServletInterface;
import org.openimmunizationsoftware.dqa.tr.model.PentagonBox;
import org.openimmunizationsoftware.dqa.tr.model.PentagonReport;
import org.openimmunizationsoftware.dqa.tr.model.TestConducted;
import org.openimmunizationsoftware.dqa.tr.model.TestSection;

public class PentagonReportLogic
{

  public static PentagonReport createOrReturnPentagonReport(TestConducted testConducted, Session dataSession)
  {
    PentagonReport pentagonReport = null;
    {
      Query query = dataSession.createQuery("from PentagonReport where testConducted = ?");
      query.setParameter(0, testConducted);
      List<PentagonReport> pentagonReportList = query.list();
      if (pentagonReportList.size() > 0)
      {
        pentagonReport = pentagonReportList.get(0);
        loadTestSectionMap(dataSession, pentagonReport);
        loadPentagonBox(dataSession, pentagonReport);
      }
    }
    if (pentagonReport == null)
    {
      pentagonReport = new PentagonReport();
      pentagonReport.setTestConducted(testConducted);
      {
        Transaction transaction = dataSession.beginTransaction();
        dataSession.save(pentagonReport);
        transaction.commit();
      }

      AssertionFieldLogic.createAssertionIdentifiedListForErrors(dataSession, testConducted, pentagonReport, "update");
      AssertionFieldLogic.createAssertionIdentifiedListForErrors(dataSession, testConducted, pentagonReport, "query");

      loadTestSectionMap(dataSession, pentagonReport);
      Map<String, TestSection> testSectionMap = pentagonReport.getTestSectionMap();

      ArrayList<PentagonRowHelper> pentagonRowList = PentagonRowHelper.createPentagonRowList(pentagonReport);
      for (PentagonRowHelper pentagonRow : pentagonRowList)
      {
        pentagonRow.calculateScores(testConducted, dataSession, pentagonReport, testSectionMap);
      }

      calculatePriorities(pentagonRowList);

      {
        Transaction transaction = dataSession.beginTransaction();
        dataSession.update(pentagonReport);
        for (PentagonBox pentagonBox : pentagonReport.getPentagonBoxMap().values())
        {
          dataSession.save(pentagonBox);
        }
        transaction.commit();
      }
    }
    return pentagonReport;
  }

  private static final String[][] combine = { { PentagonBox.BOX_NAME_C_ACK_CONFORM, PentagonBox.BOX_NAME_UC_ACKS_CONFORM },
      { PentagonBox.BOX_NAME_C_GOOD_MESSAGE, PentagonBox.BOX_NAME_UF_VXU2014 } };

  public static void calculatePriorities(ArrayList<PentagonRowHelper> pentagonRowList)
  {
    WeightComparator wc = new WeightComparator();
    List<PriorityWeight> priorityWeightListOverall = new ArrayList();
    int swAckConform = 0;  // the ack conform gap needs to be added to the Update Conformance Acks Conform gap
    int swGoodMessages = 0; // needs to be added to the same measurement in a different row. 
    for (PentagonRowHelper pentagonRow : pentagonRowList)
    {
      {
        List<PriorityWeight> priorityWeightList = new ArrayList();
        for (PentagonBoxHelper pb : pentagonRow)
        {
          PriorityWeight priorityWeight = new PriorityWeight();
          priorityWeight.importance = (pb.getWeight() / 100.0) * (100.0 - pb.getScore());
          priorityWeight.pentagonBox = pb.getPentagonBox();
          priorityWeightList.add(priorityWeight);
        }
        Collections.sort(priorityWeightList, wc);
        int position = 0;
        int scoreGap = 0;
        for (PriorityWeight pw : priorityWeightList)
        {
          position++;
          scoreGap = (int) Math.round(pw.importance);
          pw.pentagonBox.setReportScoreGap(scoreGap);
          pw.pentagonBox.setPriorityRow(position);
          if (pw.pentagonBox.getBoxName().equals(PentagonBox.BOX_NAME_C_ACK_CONFORM))
          {
            swAckConform = scoreGap;
            scoreGap = -1;
          } else if (pw.pentagonBox.getBoxName().equals(PentagonBox.BOX_NAME_UC_ACKS_CONFORM))
          {
            scoreGap += swAckConform;
          } else if (pw.pentagonBox.getBoxName().equals(PentagonBox.BOX_NAME_C_GOOD_MESSAGE))
          {
            swGoodMessages = scoreGap;
            scoreGap = -1;
          } else if (pw.pentagonBox.getBoxName().equals(PentagonBox.BOX_NAME_UF_VXU2014))
          {
            scoreGap += swGoodMessages;
          }
          if (scoreGap != -1)
          {
            PriorityWeight pwO = new PriorityWeight();
            pwO.importance = (11 - position) * 1000 + scoreGap;
            pwO.pentagonBox = pw.pentagonBox;
            priorityWeightListOverall.add(pwO);
          }
        }
      }
    }

    Collections.sort(priorityWeightListOverall, wc);
    int position = 0;
    for (PriorityWeight pw : priorityWeightListOverall)
    {
      position++;
      pw.pentagonBox.setPriorityOverall(position);
    }
  }

  public static void loadTestSectionMap(Session dataSession, PentagonReport pentagonReport)
  {
    Map<String, TestSection> testSectionMap = new HashMap<String, TestSection>();
    {
      Query query = dataSession.createQuery("from TestSection where testConducted = ?");
      query.setParameter(0, pentagonReport.getTestConducted());
      List<TestSection> testSectionList = query.list();
      for (TestSection testSection : testSectionList)
      {
        testSectionMap.put(testSection.getTestSectionType(), testSection);
      }
    }
    pentagonReport.setTestSectionMap(testSectionMap);
  }

  public static void loadPentagonBox(Session dataSession, PentagonReport pentagonReport)
  {
    Map<String, PentagonBox> pentagonBoxMap = new HashMap<String, PentagonBox>();
    {
      Query query = dataSession.createQuery("from PentagonBox where pentagonReport = ?");
      query.setParameter(0, pentagonReport);
      List<PentagonBox> pentagonBoxList = query.list();
      for (PentagonBox pentagonBox : pentagonBoxList)
      {
        if (pentagonBox.getBoxName() != null && !pentagonBox.getBoxName().equals(""))
        {
          pentagonBoxMap.put(pentagonBox.getBoxName(), pentagonBox);
        } else
        {
          pentagonBoxMap.put(pentagonBox.getBoxRow(), pentagonBox);
        }
      }
    }
    pentagonReport.setPentagonBoxMap(pentagonBoxMap);
  }

  public static ConformanceCount getConformanceCounts(PentagonReport pentagonReport, Session dataSession, String testType)
  {
    ConformanceCount conformanceCount = new ConformanceCount();
    {
      long countTotal = 0;
      int countNotRun = 0;
      long countOk = 0;
      int countError = 0;
      Query query = dataSession.createQuery("select tm.resultAckConformance, count(tm.resultAckConformance) from TestMessage tm "
          + "where tm.testSection.testConducted = ? and tm.testType = ? group by tm.resultAckConformance");
      query.setParameter(0, pentagonReport.getTestConducted());
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

  private static final class WeightComparator implements Comparator<PriorityWeight>
  {
    @Override
    public int compare(PriorityWeight o1, PriorityWeight o2)
    {
      if (o1.importance > o2.importance)
      {
        return -1;
      } else if (o1.importance < o2.importance)
      {
        return 1;
      }
      return 0;
    }
  }

  private static class PriorityWeight
  {
    double importance = 0;
    PentagonBox pentagonBox = null;
  }
}
