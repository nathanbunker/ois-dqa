package org.openimmunizationsoftware.dqa.quality;

public class ToolTip
{

  private static final String DEFAULT_INDENT = "&nbsp;-&nbsp;";
  private String indent = "";
  private String label = "";
  private String tip = "";
  private boolean emphasize = false;
  private String link = "#";
  
  public ToolTip setLink(String link)
  {
    this.link = link;
    return this;
  }

  public boolean isEmphasize()
  {
    return emphasize;
  }

  public ToolTip setEmphasize(boolean emphasize)
  {
    this.emphasize = emphasize;
    return this;
  }

  public ToolTip(String label, String tip) {
    this.label = label;
    this.tip = tip;
  }

  public ToolTip(String label, String tip, boolean indent) {
    this.label = label;
    this.tip = tip;
    this.indent = indent ? DEFAULT_INDENT : "";
  }

  public String getHtml()
  {
    if (tip != null && !tip.equals(""))
    {
      return "<a class=\"tooltip\" href=\"" + link + "\">" + indent + label + "<span>" + tip + "</span></a>";
    } else
    {
      return indent + label;
    }
  }

  public String getIndent()
  {
    return indent;
  }
  
  public boolean hasIndent()
  {
    return indent != null && !indent.equals("");
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

  public void setIndent(boolean i)
  {
    this.indent = i ? DEFAULT_INDENT : "";
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
