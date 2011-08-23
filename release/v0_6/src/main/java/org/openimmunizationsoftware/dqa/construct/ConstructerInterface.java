package org.openimmunizationsoftware.dqa.construct;

import org.openimmunizationsoftware.dqa.db.model.MessageReceived;

public interface ConstructerInterface
{
  public String constructMessage(MessageReceived messageReceived);
}
