package org.openimmunizationsoftware.dqa.quality;

public class ToolTip
{

  private String indent = "";
  private String label = "";
  private String tip = "";

  public ToolTip(String label, String tip) {
    this.label = label;
    this.tip = tip;
  }

  public ToolTip(String label, String tip, boolean indent) {
    this.label = label;
    this.tip = tip;
    this.indent = indent ? "&nbsp;-&nbsp;" : "";
  }

  public String getHtml()
  {
    if (tip != null && !tip.equals(""))
    {
      return "<a class=\"tooltip\" href=\"#\">" + indent + label + "<span>" + tip + "</span></a>";
    } else
    {
      return indent + label;
    }
  }

  public String getIndent()
  {
    return indent;
  }

  public String getLabel()
  {
    return label;
  }
  public String getTip()
  {
    return tip;
  }

  public void setIndent(String indent)
  {
    this.indent = indent;
  }

  public void setLabel(String label)
  {
    this.label = label;
  }

  public void setTip(String tip)
  {
    this.tip = tip;
  }

}
