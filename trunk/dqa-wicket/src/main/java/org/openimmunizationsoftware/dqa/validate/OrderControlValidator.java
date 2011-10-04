package org.openimmunizationsoftware.dqa.validate;

import org.openimmunizationsoftware.dqa.db.model.received.Vaccination;
import org.openimmunizationsoftware.dqa.manager.PotentialIssues;

public class OrderControlValidator extends SectionValidator
{
  public OrderControlValidator()
  {
    super("Order Control");
  }
  
  @Override
  public void validateVaccination(Vaccination vaccination, Validator validator)
  {
    super.validateVaccination(vaccination, validator);
    validator.documentParagraph("The vaccination order control id should always be RE.");
    validator.documentValuesFound("Vaccination Order Control Code", vaccination.getOrderControlCode());
    validator.handleCodeReceived(vaccination.getFacility().getId(), PotentialIssues.Field.VACCINATION_FACILITY_ID,
        vaccination.isAdministered());
  }
}
