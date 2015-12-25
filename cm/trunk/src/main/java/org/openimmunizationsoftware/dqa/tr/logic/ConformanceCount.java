package org.openimmunizationsoftware.dqa.tr.logic;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class ConformanceCount
{
  private int countTotal = 0;
  private int countNotRun = 0;
  private int countError = 0;
  private int countOk = 0;

  private String makePercent(int count)
  {
    return makePercent(count, countTotal);
  }

  public static String makePercent(int count, int total)
  {
    if (total == 0)
    {
      return "-";
    }
    if (count > total)
    {
      return "100%";
    }
    double percentage = (100.0 *  count) / total;
    NumberFormat formatter = new DecimalFormat("#0.0");
    return formatter.format(percentage) + "%";
  }

  public int getCountTotal()
  {
    return countTotal;
  }

  public void setCountTotal(int countTotal)
  {
    this.countTotal = countTotal;
  }

  public int getCountNotRun()
  {
    return countNotRun;
  }

  public String getCountNotRunPercentage()
  {
    return makePercent(countNotRun);
  }

  public String getCountRunPercentage()
  {
    return makePercent(countTotal - countNotRun);
  }

  public String getCountErrorPercentage()
  {
    return makePercent(countError);
  }

  public String getCountOkPercentage()
  {
    return makePercent(countOk);
  }

  public void setCountNotRun(int countNotRun)
  {
    this.countNotRun = countNotRun;
  }

  public int getCountError()
  {
    return countError;
  }

  public void setCountError(int countError)
  {
    this.countError = countError;
  }

  public int getCountOk()
  {
    return countOk;
  }

  public void setCountOk(int countOk)
  {
    this.countOk = countOk;
  }
}
