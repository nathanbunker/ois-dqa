package org.openimmunizationsoftware.dqa.tr.profile;

public enum MessageAcceptStatus {
  ONLY_IF_PRESENT, ONLY_IF_ABSENT, IF_PRESENT_OR_ABSENT;

  public String toString()
  {
    if (this == ONLY_IF_PRESENT)
    {
      return "Only if Present";
    }
    if (this == ONLY_IF_ABSENT)
    {
      return "Only if Absent";
    }
    if (this == IF_PRESENT_OR_ABSENT)
    {
      return "If Present or Absent";
    }
    return super.toString();
  };

  public static MessageAcceptStatus readMessageAcceptStatus(String messageAcceptStatusString)
  {
    if (messageAcceptStatusString == null)
    {
      return null;
    } else if (messageAcceptStatusString.equals("Only if Present"))
    {
      return MessageAcceptStatus.ONLY_IF_PRESENT;
    } else if (messageAcceptStatusString.equals("Only if Absent"))
    {
      return MessageAcceptStatus.ONLY_IF_ABSENT;
    } else if (messageAcceptStatusString.equals("If Present or Absent"))
    {
      return MessageAcceptStatus.IF_PRESENT_OR_ABSENT;
    } else
    {
      return null;
    }
  }
}
