package org.openimmunizationsoftware.dqa.tr.logic;

import org.openimmunizationsoftware.dqa.tr.model.ProfileUsageValue;
import org.openimmunizationsoftware.dqa.tr.profile.CompatibilityConformance;
import org.openimmunizationsoftware.dqa.tr.profile.Enforcement;
import org.openimmunizationsoftware.dqa.tr.profile.Implementation;
import org.openimmunizationsoftware.dqa.tr.profile.Usage;

public class ProfileUsageValueLogic
{

  /**
   * In this function the usage value is converted to it's basic * form. This form is used
   * to summarize the affects of the enforcement and implementation on the usage value. 
   * @param profileUsageValue
   * @return
   */
  public static Usage rectifyUsageForDetection(ProfileUsageValue profileUsageValue)
  {
    Usage usageExpected = profileUsageValue.getUsage();
    // R, RE, O, X, RE_OR_O, R_OR_X, RE_OR_X, R_OR_RE, R_SPECIAL, R_NOT_ENFORCED,
    // RE_NOT_USED, O_NOT_USED, X_NOT_ENFORCED, NOT_DEFINED;
    Enforcement enforcement = profileUsageValue.getEnforcement();
    // ENFORCED, WARNING, NOT_ENFORCED, NOT_DEFINED;
    Implementation implementation = profileUsageValue.getImplementation();
    // INDIFFERENT, SUPPORTED, DEPRECATED, FUTURE, NOT_DEFINED;

    switch (usageExpected) {
    case RE_OR_O:
    case R_OR_RE:
    case RE_OR_X:
    case R_OR_X:
    case R_SPECIAL:
    case R_NOT_ENFORCED:
    case RE_NOT_USED:
    case O_NOT_USED:
    case X_NOT_ENFORCED:
    case NOT_DEFINED:
      return usageExpected;
    case R:
      if (enforcement == Enforcement.NOT_ENFORCED || enforcement == Enforcement.WARNING)
      {
        return Usage.R_NOT_ENFORCED;
      }
      if (enforcement == Enforcement.NOT_DEFINED && implementation == Implementation.INDIFFERENT)
      {
        return Usage.R_NOT_ENFORCED;
      }
    case RE:
      if (implementation == Implementation.INDIFFERENT || implementation == Implementation.FUTURE)
      {
        return Usage.RE_NOT_USED;
      }
    case O:
      if (implementation == Implementation.INDIFFERENT || implementation == Implementation.FUTURE)
      {
        return Usage.O_NOT_USED;
      }
    case X:
      if (enforcement == Enforcement.NOT_ENFORCED || enforcement == Enforcement.WARNING)
      {
        return Usage.X_NOT_ENFORCED;
      }
      if (enforcement == Enforcement.NOT_DEFINED && implementation == Implementation.INDIFFERENT)
      {
        return Usage.X_NOT_ENFORCED;
      }
    }

    return usageExpected;
  }
  
  public static CompatibilityConformance getCompatibilityConformance(Usage profileUsage, Usage profileUsageConformance) {

    switch (profileUsageConformance) {
    case R:
      switch (profileUsage) {
      case R:
        return CompatibilityConformance.COMPATIBLE;
      case R_SPECIAL:
        return CompatibilityConformance.CONFLICT;
      case RE:
        return CompatibilityConformance.ALLOWANCE;
      case O:
      case NOT_DEFINED:
        return CompatibilityConformance.NOT_DEFINED;
      case X:
        return CompatibilityConformance.MAJOR_CONFLICT;
      case R_NOT_ENFORCED:
        return CompatibilityConformance.ALLOWANCE;
      case RE_NOT_USED:
        return CompatibilityConformance.ALLOWANCE;
      case O_NOT_USED:
        return CompatibilityConformance.ALLOWANCE;
      case X_NOT_ENFORCED:
        return CompatibilityConformance.CONFLICT;
      default:
        return CompatibilityConformance.UNABLE_TO_DETERMINE;
      }
    case RE:
      switch (profileUsage) {
      case R:
      case R_SPECIAL:
        return CompatibilityConformance.CONSTRAINT;
      case RE:
        return CompatibilityConformance.COMPATIBLE;
      case O:
      case NOT_DEFINED:
        return CompatibilityConformance.NOT_DEFINED;
      case X:
        return CompatibilityConformance.MAJOR_CONFLICT;
      case R_NOT_ENFORCED:
        return CompatibilityConformance.CONSTRAINT;
      case RE_NOT_USED:
        return CompatibilityConformance.ALLOWANCE;
      case O_NOT_USED:
        return CompatibilityConformance.ALLOWANCE;
      case X_NOT_ENFORCED:
        return CompatibilityConformance.CONFLICT;
      default:
        return CompatibilityConformance.UNABLE_TO_DETERMINE;
      }
    case NOT_DEFINED:
      switch (profileUsage) {
      case R:
      case R_SPECIAL:
        return CompatibilityConformance.MAJOR_CONSTRAINT;
      case RE:
        return CompatibilityConformance.CONSTRAINT;
      case O:
      case NOT_DEFINED:
        return CompatibilityConformance.COMPATIBLE;
      case X:
        return CompatibilityConformance.MAJOR_CONSTRAINT;
      case R_NOT_ENFORCED:
        return CompatibilityConformance.CONSTRAINT;
      case RE_NOT_USED:
        return CompatibilityConformance.CONSTRAINT;
      case O_NOT_USED:
        return CompatibilityConformance.COMPATIBLE;
      case X_NOT_ENFORCED:
        return CompatibilityConformance.COMPATIBLE;
      default:
        return CompatibilityConformance.UNABLE_TO_DETERMINE;
      }
    case O:
      switch (profileUsage) {
      case R:
      case R_SPECIAL:
        return CompatibilityConformance.MAJOR_CONSTRAINT;
      case RE:
        return CompatibilityConformance.CONSTRAINT;
      case O:
      case NOT_DEFINED:
        return CompatibilityConformance.COMPATIBLE;
      case X:
        return CompatibilityConformance.MAJOR_CONSTRAINT;
      case R_NOT_ENFORCED:
        return CompatibilityConformance.CONSTRAINT;
      case RE_NOT_USED:
        return CompatibilityConformance.CONSTRAINT;
      case O_NOT_USED:
        return CompatibilityConformance.COMPATIBLE;
      case X_NOT_ENFORCED:
        return CompatibilityConformance.COMPATIBLE;
      default:
        return CompatibilityConformance.UNABLE_TO_DETERMINE;
      }
    case X:
      switch (profileUsage) {
      case R:
      case R_SPECIAL:
        return CompatibilityConformance.MAJOR_CONFLICT;
      case RE:
        return CompatibilityConformance.MAJOR_CONFLICT;
      case O:
        return CompatibilityConformance.CONFLICT;
      case NOT_DEFINED:
        return CompatibilityConformance.COMPATIBLE;
      case X:
        return CompatibilityConformance.COMPATIBLE;
      case R_NOT_ENFORCED:
        return CompatibilityConformance.CONFLICT;
      case RE_NOT_USED:
        return CompatibilityConformance.CONFLICT;
      case O_NOT_USED:
        return CompatibilityConformance.COMPATIBLE;
      case X_NOT_ENFORCED:
        return CompatibilityConformance.COMPATIBLE;
      default:
        return CompatibilityConformance.UNABLE_TO_DETERMINE;
      }
    default:
      return CompatibilityConformance.UNABLE_TO_DETERMINE;
    }
  }

}
