package org.openimmunizationsoftware.dqa.quality;

import org.openimmunizationsoftware.dqa.db.model.PotentialIssue;

public class CompletenessRow
{
  private int count = 0;
  private int denominator = 0;
  private boolean invert = true;
  private int okayHigh = 100;
  private int okayLow = 0;
  private PotentialIssue potentialIssue = null;
  private String reportDenominator = "";
  private double score = 0;
  private int scoreWeight = 0;
  private ToolTip toolTip;

  public CompletenessRow() {
    // default
  }

  public CompletenessRow(CompletenessRow template, double score) {
    this.potentialIssue = template.potentialIssue;
    this.reportDenominator = template.reportDenominator;
    this.scoreWeight = template.scoreWeight;
    this.score = score;
    this.toolTip = new ToolTip(toolTip.getLabel(), "");
  }

  public CompletenessRow(String label, PotentialIssue potentialIssue, String reportDenominator, int scoreWeight) {
    this.potentialIssue = potentialIssue;
    this.reportDenominator = reportDenominator;
    this.scoreWeight = scoreWeight;
    this.toolTip = new ToolTip(label, "");
  }
  

  public int getCount()
  {
    return count;
  }

  public int getDenominator()
  {
    return denominator;
  }

  public int getOkayHigh()
  {
    return okayHigh;
  }

  public int getOkayLow()
  {
    return okayLow;
  }

  public PotentialIssue getPotentialIssue()
  {
    return potentialIssue;
  }

  public String getReportDenominator()
  {
    return reportDenominator;
  }

  public double getScore()
  {
    return score;
  }

  public int getScoreWeight()
  {
    return scoreWeight;
  }

  public ToolTip getToolTip()
  {
    return toolTip;
  }

  public boolean isInvert()
  {
    return invert;
  }

  public void setCount(int count)
  {
    this.count = count;
  }

  public void setDenominator(int denominator)
  {
    this.denominator = denominator;
  }

  public CompletenessRow setIndent(boolean indent)
  {
    this.toolTip.setIndent(indent);
    return this;
  }
  
  public CompletenessRow setDemerit()
  {
    this.invert = false;
    this.toolTip.setIndent(true);
    return this;
  }

  public CompletenessRow setInvert(boolean invert)
  {
    this.invert = invert;
    return this;
  }

  public void setOkayHigh(int okayHigh)
  {
    this.okayHigh = okayHigh;
  }

  public void setOkayLow(int okayLow)
  {
    this.okayLow = okayLow;
  }

  public void setPotentialIssue(PotentialIssue potentialIssue)
  {
    this.potentialIssue = potentialIssue;
  }

  public void setReportDenominator(String reportDenominator)
  {
    this.reportDenominator = reportDenominator;
  }

  public void setScore(double score)
  {
    this.score = score;
  }

  public void setScoreWeight(int scoreWeight)
  {
    this.scoreWeight = scoreWeight;
  }

  public void setToolTip(ToolTip toolTip)
  {
    this.toolTip = toolTip;
  }

}
