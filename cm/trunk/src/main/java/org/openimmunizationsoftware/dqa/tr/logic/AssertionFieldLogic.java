package org.openimmunizationsoftware.dqa.tr.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.openimmunizationsoftware.dqa.tr.model.Assertion;
import org.openimmunizationsoftware.dqa.tr.model.AssertionField;
import org.openimmunizationsoftware.dqa.tr.model.AssertionIdentified;
import org.openimmunizationsoftware.dqa.tr.model.PentagonReport;
import org.openimmunizationsoftware.dqa.tr.model.TestConducted;
import org.openimmunizationsoftware.dqa.tr.model.TestMessage;

public class AssertionFieldLogic
{
  public static List<AssertionIdentified> getAssertionIdentifiedListForErrors(Session dataSession, PentagonReport pentagonReport, String testType)
  {
    Query query = dataSession.createQuery("from AssertionIdentified where pentagonReport = ? and testType = ?");
    query.setParameter(0, pentagonReport);
    query.setParameter(1, testType);
    List<AssertionIdentified> assertionIdentifiedList = query.list();
    return assertionIdentifiedList;
  }

  public static void createAssertionIdentifiedListForErrors(Session dataSession, TestConducted testConducted, PentagonReport pentagonReport,
      String testType)
  {
    Transaction transaction = dataSession.beginTransaction();
    Query query = dataSession.createQuery("select a.assertionField, count(a.assertionField) from Assertion a "
        + "where a.testMessage.testSection.testConducted = ? and a.testMessage.testType = ? "
        + "and a.testMessage.prepNotExpectedToConform = 'N' and a.assertionResult = 'error' "
        + "group by a.assertionField order by count(a.assertionField) desc ");
    query.setParameter(0, testConducted);
    query.setParameter(1, testType);
    List<Object[]> objectsList = query.list();
    for (Object[] objects : objectsList)
    {
      if (objects.length == 2)
      {
        AssertionField assertionField = (AssertionField) objects[0];
        long assertionCount = (Long) objects[1];
        AssertionIdentified assertionIdentified = new AssertionIdentified();
        assertionIdentified.setAssertionField(assertionField);
        assertionIdentified.setPentagonReport(pentagonReport);
        assertionIdentified.setAssertionCount((int) assertionCount);
        assertionIdentified.setTestType(testType);
        assertionIdentified.setAssertionResult("error");
        query = dataSession
            .createQuery("from Assertion where assertionField = ? and testMessage.testSection.testConducted = ?");
        query.setParameter(0, assertionIdentified.getAssertionField());
        query.setParameter(1, testConducted);
        List<Assertion> testMessageAssertionList = query.list();
        if (testMessageAssertionList.size() > 0)
        {
          assertionIdentified.setTestMessage(testMessageAssertionList.get(0).getTestMessage());
          dataSession.save(assertionIdentified);
        }
      }
    }
    transaction.commit();
  }

 
}
