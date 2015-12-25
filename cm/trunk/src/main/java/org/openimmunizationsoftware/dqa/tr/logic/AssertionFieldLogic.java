package org.openimmunizationsoftware.dqa.tr.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.tr.model.Assertion;
import org.openimmunizationsoftware.dqa.tr.model.AssertionField;
import org.openimmunizationsoftware.dqa.tr.model.TestConducted;
import org.openimmunizationsoftware.dqa.tr.model.TestMessage;

public class AssertionFieldLogic
{
  public static List<AssertionField> getAssertionFieldListForErrors(Session dataSession, TestConducted testConducted)
  {
    Query query = dataSession
        .createQuery("select a.assertionField, count(a.assertionField) " + "from Assertion a " + "where a.testMessage.testSection.testConducted = ? "
            + "and a.testMessage.testType = 'update' " + "and a.testMessage.prepNotExpectedToConform = 'N' " + "and a.assertionResult = 'error' "
            + "group by a.assertionField " + "order by count(a.assertionField) desc ");
    query.setParameter(0, testConducted);
    List<Object[]> objectsList = query.list();
    List<AssertionField> assertionFieldList = new ArrayList<AssertionField>();
    for (Object[] objects : objectsList)
    {
      if (objects.length == 2)
      {
        AssertionField assertionField = (AssertionField) objects[0];
        long count = (long) objects[1];
        assertionField.setCount((int) count);
        assertionFieldList.add(assertionField);
      }
    }
    return assertionFieldList;
  }

  public static Map<AssertionField, TestMessage> getAssertionFieldToTestMessageMap(Session dataSession, TestConducted testConducted,
      List<AssertionField> assertionFieldList)
  {
    Map<AssertionField, TestMessage> assertionFieldToTestMessageMap = new HashMap<>();
    for (AssertionField assertionField : assertionFieldList)
    {
      Query query = dataSession
          .createQuery("from Assertion where assertionField = ? and testMessage.testSection.testConducted = ? order by assertionId");
      query.setParameter(0, assertionField);
      query.setParameter(1, testConducted);
      List<Assertion> testMessageAssertionList = query.list();
      if (testMessageAssertionList.size() > 0)
      {
        TestMessage testMessage = testMessageAssertionList.get(0).getTestMessage();
        query = dataSession.createQuery("from Assertion where testMessage = ? and assertionResult = 'error'");
        query.setParameter(0, testMessage);
        List<Assertion> assertionList = query.list();
        for (Assertion assertion : assertionList)
        {
          assertionFieldToTestMessageMap.put(assertion.getAssertionField(), testMessage);
        }
      }
    }
    return assertionFieldToTestMessageMap;
  }
}
