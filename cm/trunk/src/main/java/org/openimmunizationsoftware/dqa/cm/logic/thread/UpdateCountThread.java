package org.openimmunizationsoftware.dqa.cm.logic.thread;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.openimmunizationsoftware.dqa.cm.logic.CodeInstanceLogic;
import org.openimmunizationsoftware.dqa.cm.logic.CodeTableInstanceLogic;
import org.openimmunizationsoftware.dqa.cm.model.CodeInstance;
import org.openimmunizationsoftware.dqa.cm.model.User;

public class UpdateCountThread extends LogicThread
{
  public UpdateCountThread(User user) {
    super(user.getUserId());
  }

  private Set<Integer> set = new HashSet<Integer>();
  private Queue<Integer> queue = new LinkedList<Integer>();
  private Lock lock = new ReentrantLock();
  private Condition notEmpty = lock.newCondition();

  public void addItem(int codeInstanceId)
  {
    lock.lock();
    try
    {
      if (!set.contains(codeInstanceId))
      {
        queue.add(codeInstanceId);
        notEmpty.signal();
      }
    } finally
    {
      lock.unlock();
    }
  }

  private int getNextCodeInstanceId() throws InterruptedException
  {
    lock.lock();
    try
    {
      while (queue.isEmpty())
      {
        notEmpty.await();
      }
      int codeInstanceId = queue.remove();
      set.remove(codeInstanceId);
      return codeInstanceId;
    } finally
    {
      lock.unlock();
    }
  }

  @Override
  public void run()
  {
    while (true)
    {
      // System.out.println("--> looking to update issue count ");
      try
      {
        int codeInstanceId = getNextCodeInstanceId();
        dataSession.clear();
        CodeInstance codeInstance = CodeInstanceLogic.getCodeInstance(codeInstanceId, dataSession);
        // System.out.println("--> Updating " + codeInstance.getCodeLabel());
        boolean changed = CodeInstanceLogic.updateIssueCount(codeInstance, dataSession);
        // System.out.println("-->  + changed = " + changed);
        if (changed)
        {
          CodeTableInstanceLogic.updateIssueCount(codeInstance.getTableInstance(), false, dataSession);
        }
      } catch (Exception e)
      {
        e.printStackTrace();
      }
    }
  }
}
