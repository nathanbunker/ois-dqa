package org.openimmunizationsoftware.dqa.quality;

import java.util.ArrayList;
import java.util.List;

import org.openimmunizationsoftware.dqa.db.model.PotentialIssue;
import org.openimmunizationsoftware.dqa.manager.PotentialIssues;

public class ScoringSet
{
  private double weight = 0;
  private List<CompletenessRow> completenessRow = null;
  private double score = 0;
  private double weightedScore = 0;
  private String label = "";
  private int overallWeight = 0;

  public int getOverallWeight()
  {
    return overallWeight;
  }

  public void setOverallWeight(int overallWeight)
  {
    this.overallWeight = overallWeight;
  }

  public String getLabel()
  {
    return label;
  }

  public void setLabel(String label)
  {
    this.label = label;
  }

  public double getWeightedScore()
  {
    return weightedScore;
  }

  public void setWeightedScore(double weightedScore)
  {
    this.weightedScore = weightedScore;
  }

  public ScoringSet() {
    completenessRow = new ArrayList<CompletenessRow>();
  }

  public void add(CompletenessRow row)
  {
    completenessRow.add(row);
  }
  
  public CompletenessRow add(String label, PotentialIssue potentialIssue, String reportDenominator, int scoreWeight)
  {
    CompletenessRow row = new CompletenessRow(label, potentialIssue, reportDenominator, scoreWeight);
    completenessRow.add(row);
    return row;
  }

  public void add(String label, PotentialIssues.Field field, String denominator, int weight)
  {
    PotentialIssues pi = PotentialIssues.getPotentialIssues();
    PotentialIssue missing = pi.getIssue(field, PotentialIssue.ISSUE_TYPE_IS_MISSING);
    if (missing != null)
    {
      add(new CompletenessRow(label, missing, denominator, weight));
      PotentialIssue deprecate = pi.getIssue(field, PotentialIssue.ISSUE_TYPE_IS_DEPRECATE);
      if (deprecate != null)
      {
        add(new CompletenessRow("Deprecate", deprecate, denominator, -weight).setDemerit());
      }
      PotentialIssue invalid = pi.getIssue(field, PotentialIssue.ISSUE_TYPE_IS_INVALID);
      if (invalid != null)
      {
        add(new CompletenessRow("Invalid", invalid, denominator, -weight).setDemerit());
      }
      PotentialIssue unrecognized = pi.getIssue(field, PotentialIssue.ISSUE_TYPE_IS_UNRECOGNIZED);
      if (unrecognized != null)
      {
        add(new CompletenessRow("Unrecognized", unrecognized, denominator, -weight).setDemerit());
      }
    }

  }

  public double getWeight()
  {
    return weight;
  }

  public void setWeight(double weight)
  {
    this.weight = weight;
  }

  public List<CompletenessRow> getCompletenessRow()
  {
    return completenessRow;
  }

  public void setCompletenessRow(List<CompletenessRow> completenessRow)
  {
    this.completenessRow = completenessRow;
  }

  public double getScore()
  {
    return score;
  }

  public void setScore(double score)
  {
    this.score = score;
  }
}
