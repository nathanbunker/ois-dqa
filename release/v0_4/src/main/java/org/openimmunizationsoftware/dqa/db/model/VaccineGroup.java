package org.openimmunizationsoftware.dqa.db.model;

import java.util.ArrayList;
import java.util.List;

import org.openimmunizationsoftware.dqa.quality.ToolTip;

public class VaccineGroup
{
  public static final String GROUP_STATUS_EXPECTED = "Expected";
  public static final String GROUP_STATUS_NOT_EXPECTED = "Not Expected";
  public static final String GROUP_STATUS_OPTIONAL = "Optional";

  private String groupCode = "";
  private int groupId = 0;
  private String groupLabel = "";
  private String groupStatus = "";
  private ToolTip toolTip = null;
  private List<VaccineCvx> vaccineCvxList = new ArrayList<VaccineCvx>();

  public String getGroupCode()
  {
    return groupCode;
  }

  public int getGroupId()
  {
    return groupId;
  }

  public String getGroupLabel()
  {
    return groupLabel;
  }

  public String getGroupStatus()
  {
    return groupStatus;
  }

  public ToolTip getToolTip()
  {
    if (toolTip == null)
    {
      toolTip = new ToolTip(groupLabel, "");
    }
    return toolTip;
  }

  public List<VaccineCvx> getVaccineCvxList()
  {
    return vaccineCvxList;
  }

  public void setGroupCode(String groupCode)
  {
    this.groupCode = groupCode;
  }

  public void setGroupId(int groupId)
  {
    this.groupId = groupId;
  }

  public void setGroupLabel(String groupLabel)
  {
    this.groupLabel = groupLabel;
  }

  public void setGroupStatus(String groupStatus)
  {
    this.groupStatus = groupStatus;
  }

  public void setToolTip(ToolTip toolTip)
  {
    this.toolTip = toolTip;
  }
}