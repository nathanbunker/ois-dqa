package org.openimmunizationsoftware.dqa.quality.model;

import java.util.ArrayList;
import java.util.List;

import org.openimmunizationsoftware.dqa.db.model.PotentialIssue;
import org.openimmunizationsoftware.dqa.quality.ReportDenominator;

public class ModelScore
{
  private String label = "";
  private ReportDenominator denominator = null;
  private String numerator = "";
  private float weight = 0;
  private PotentialIssue potentialIssue = null;
  private List<ModelScore> scores = new ArrayList<ModelScore>();

  public List<ModelScore> getScores()
  {
    return scores;
  }

  public String getLabel()
  {
    return label;
  }

  public void setLabel(String label)
  {
    this.label = label;
  }

  public ReportDenominator getDenominator()
  {
    return denominator;
  }

  public void setDenominator(ReportDenominator targetObject)
  {
    this.denominator = targetObject;
  }

  public String getNumerator()
  {
    return numerator;
  }

  public void setNumerator(String targetField)
  {
    this.numerator = targetField;
  }

  public float getWeight()
  {
    return weight;
  }

  public void setWeight(float weight)
  {
    this.weight = weight;
  }

  public PotentialIssue getPotentialIssue()
  {
    return potentialIssue;
  }

  public void setPotentialIssue(PotentialIssue potentialIssue)
  {
    this.potentialIssue = potentialIssue;
  }

}
