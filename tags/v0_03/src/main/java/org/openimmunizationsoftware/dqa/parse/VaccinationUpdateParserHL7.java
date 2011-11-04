package org.openimmunizationsoftware.dqa.parse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openimmunizationsoftware.dqa.db.model.IssueFound;
import org.openimmunizationsoftware.dqa.db.model.MessageReceived;
import org.openimmunizationsoftware.dqa.db.model.SubmitterProfile;
import org.openimmunizationsoftware.dqa.db.model.received.NextOfKin;
import org.openimmunizationsoftware.dqa.db.model.received.Patient;
import org.openimmunizationsoftware.dqa.db.model.received.Vaccination;
import org.openimmunizationsoftware.dqa.db.model.received.types.Address;
import org.openimmunizationsoftware.dqa.db.model.received.types.CodedEntity;
import org.openimmunizationsoftware.dqa.db.model.received.types.Name;
import org.openimmunizationsoftware.dqa.db.model.received.types.PhoneNumber;
import org.openimmunizationsoftware.dqa.manager.PotentialIssues;


public class VaccinationUpdateParserHL7 extends VaccinationUpdateParser
{
  private char[] separators = new char[5];
  private static final int BAR = 0;
  private static final int CAR = 1;
  private static final int TIL = 2;
  private static final int SLA = 3;
  private static final int AMP = 4;

  public VaccinationUpdateParserHL7(SubmitterProfile profile) {
    super(profile);
  }

  private int startField = 0;
  private int currentSegmentPos = -1;
  private String segmentName = "";
  private List<List<String>> segments;
  private List<String> currentSegment;
  private int vaccinationCount = 0;
  private int nextOfKinCount = 0;
  
  private void setup()
  {
    startField = 0;
    currentSegmentPos = -1;
    segmentName = "";
    segments = new ArrayList<List<String>>();
    currentSegment = new ArrayList<String>();

    patient = null;
    vaccination = null;
    nextOfKin = null;

    vaccinationCount = 0;
    nextOfKinCount = 0;

    pi = PotentialIssues.getPotentialIssues();
  }

  @Override
  public void createVaccinationUpdateMessage(MessageReceived messageReceived)
  {
    String messageText = messageReceived.getRequestText(); 
    message = messageReceived;
    issuesFound = message.getIssuesFound();
    setup();
    readSeparators(messageText);
    readFields(messageText);

    patient = message.getPatient();
    List<String> orcSegment = null;
    while (moveNext())
    {
      if (segmentName.equals("PID"))
      {
        populatePID(message);
      } else if (segmentName.equals("PV1"))
      {
        populatePV1(message);
      } else if (segmentName.equals("PD1"))
      {
        populatePD1(message);
      } else if (segmentName.equals("NK1"))
      {
        nextOfKinCount++;
        nextOfKin = new NextOfKin();
        nextOfKin.setPositionId(nextOfKinCount);
        message.getNextOfKins().add(nextOfKin);
        nextOfKin.setReceivedId(message.getNextOfKins().size());
        populateNK1(message);
      } else if (segmentName.equals("ORC"))
      {
        orcSegment = currentSegment;
      } else if (segmentName.equals("RXA"))
      {
        if (vaccination == null)
        {
          positionId = 0;
        }
        positionId ++;
        vaccinationCount++;
        vaccination = new Vaccination();
        vaccination.setPositionId(vaccinationCount);
        message.getVaccinations().add(vaccination);
        populateRXA(message);
        if (orcSegment != null)
        {
          currentSegment = orcSegment;
          populateORC(message);
          orcSegment = null;
        }
      } else if (segmentName.equals("RXR"))
      {
        if (vaccination == null)
        {
          registerIssue(pi.Hl7RxaSegmentIsMissing);
          continue;
        }
        populateRXR(message);
      }
      
    }
  }

  private boolean moveNext()
  {
    currentSegmentPos++;
    while (currentSegmentPos < segments.size())
    {
      currentSegment = segments.get(currentSegmentPos);
      if (currentSegment.size() > 0)
      {
        segmentName = currentSegment.get(0);
        return true;
      }
      currentSegmentPos++;
    }
    return false;
  }

  private void populatePID(MessageReceived message)
  {

    readAddress(11, patient.getAddress());
    // private Name alias = new Name();
    patient.setBirthDate(getValueDate(7));
    patient.setBirthMuliple(getValue(24));
    patient.setBirthOrder(getValue(25));
    patient.setBirthPlace(getValue(23));
    patient.setDeathDate(getValueDate(29));
    patient.setDeathIndicator(getValue(30));
    patient.setEthnicityCode(getValue(22));
    // TODO private OrganizationName facility = new OrganizationName();
    // TODO private String financialEligibility = "";
    readPatientId(patient);
    patient.setMotherMaidenName(getValue(21));
    readName(5, patient.getName());
    readPhoneNumber(13, patient.getPhone());
    patient.setPrimaryLanguageCode(getValue(15));
    patient.setRaceCode(getValue(10));
    patient.setSexCode(getValue(8));
  }

  private void populatePD1(MessageReceived message)
  {
    // TODO private OrganizationName facility = new OrganizationName();
    // TODO private Id idSubmitter = new Id();
    // TODO private Id physician = new Id();
    // TODO private CodedEntity protection = new CodedEntity();
    // TODO private CodedEntity publicity = new CodedEntity();
    // TODO private String registryStatus = "";
  }

  private void populateNK1(MessageReceived message)
  {
    readAddress(4, nextOfKin.getAddress());
    readPhoneNumber(5, nextOfKin.getPhone());
    readCodeEntity(3, nextOfKin.getRelationship());
    readName(2, nextOfKin.getName());
  }

  private void populateRXA(MessageReceived message)
  {
    vaccination.setAdminDate(getValueDate(3));
    readCodeEntity(5, vaccination.getAdmin());
    CodedEntity admin = vaccination.getAdmin();
    readCptCvxCodes(admin);
    vaccination.setAmount(getValue(6));
    vaccination.setAmountUnitCode(getValue(7));
    readCodeEntity(9, vaccination.getInformationSource());
    // TODO 10 XCN private Id givenBy = new Id();
    // TODO 11 LA2 private OrganizationName facility = new OrganizationName();
    vaccination.setLotNumber(getValue(15));
    vaccination.setExpirationDate(getValueDate(16));
    readCodeEntity(17, vaccination.getManufacturer());
    readCodeEntity(18, vaccination.getRefusal());
    vaccination.setCompletionStatusCode(getValue(20));
    vaccination.setActionCode(getValue(21));
    vaccination.setSystemEntryDate(getValueDate(22));
  }
  
  private void populateRXR(MessageReceived message)
  {
    readCodeEntity(1, vaccination.getBodyRoute());
    readCodeEntity(2, vaccination.getBodySite());
  }

  private void readCptCvxCodes(CodedEntity admin)
  {
    if (admin.getTable().equals("CVX"))
    {
      vaccination.setAdminCodeCvx(admin.getCode());
    } else if (admin.getAltTable().equals("CVX"))
    {
      vaccination.setAdminCodeCvx(admin.getAltCode());
    }
    if (admin.getTable().equals("CPT") || admin.getTable().equals("C4"))
    {
      vaccination.setAdminCodeCpt(admin.getCode());
    } else if (admin.getAltTable().equals("CPT") || admin.getAltTable().equals("C4"))
    {
      vaccination.setAdminCodeCpt(admin.getAltCode());
    }
  }

  private void populateORC(MessageReceived message)
  {
    // TODO private CodedEntity confidentiality = new CodedEntity();
    // TODO private Id enteredBy = new Id();
    // TODO private String idSubmitter = "";
    // TODO private Id orderedBy = new Id();
  }

  private void populatePV1(MessageReceived message)
  {
    // TODO private String financialEligibility = "";
  }

  private void readPhoneNumber(int fieldNumber, PhoneNumber phoneNumber)
  {
    String[] field = getValues(fieldNumber);
    if (field.length == 0)
    {
      return;
    }
    phoneNumber.setNumber(field.length >= 1 ? field[0] : "");
    phoneNumber.setTelUseCode(field.length >= 2 ? field[1] : "");
    phoneNumber.setTelEquipCode(field.length >= 3 ? field[2] : "");
    phoneNumber.setEmail(field.length >= 4 ? field[3] : "");
    phoneNumber.setCountryCode(field.length >= 5 ? field[4] : "");
    phoneNumber.setAreaCode(field.length >= 6 ? field[5] : "");
    phoneNumber.setLocalNumber(field.length >= 7 ? field[6] : "");
    phoneNumber.setExtension(field.length >= 8 ? field[7] : "");
  }

  private void readAddress(int fieldNumber, Address address)
  {
    String[] field = getValues(fieldNumber);
    address.setStreet(field.length >= 1 ? field[0] : "");
    address.setStreet2(field.length >= 2 ? field[1] : "");
    address.setCity(field.length >= 3 ? field[2] : "");
    address.setState(field.length >= 4 ? field[3] : "");
    address.setZip(field.length >= 5 ? field[4] : "");
    address.setCountry(field.length >= 6 ? field[5] : "");
    address.setType(field.length >= 7 ? field[6] : "");
    address.setCountyParish(field.length >= 8 ? field[7] : "");
  }
  
  private void readPatientId(Patient patient)
  {
    List<String[]> values = getRepeatValues(3);
    if (values.size() > 0)
    {
      String[] fields = values.get(0);
      patient.setIdSubmitterNumber(fields.length >= 1 ? fields[0] : "");
      patient.setIdSubmitterAssigningAuthority(fields.length >= 4 ? fields[3] : "");
      patient.setIdSubmitterTypeCode(fields.length >= 5 ? fields[4] : "");
    }
    for (int i = 1; i < values.size(); i++)
    {
      String[] fields = values.get(i);
      if (fields.length >= 5)
      {
        String typeCode = fields[4];
        if ("SS".equals(typeCode))
        {
          patient.setIdSsn(fields[0]);
        }
        else if ("MA".equals(typeCode))
        {
          patient.setIdMedicaid(fields[0]);
        }
      }
    }
  }

  private void readCodeEntity(int fieldNumber, CodedEntity ce)
  {
    String[] field = getValues(fieldNumber);
    ce.setCode(field.length >= 1 ? field[0] : "");
    ce.setText(field.length >= 2 ? field[1] : "");
    ce.setTable(field.length >= 3 ? field[2] : "");
    ce.setAltCode(field.length >= 4 ? field[3] : "");
    ce.setAltText(field.length >= 5 ? field[4] : "");
    ce.setAltTable(field.length >= 6 ? field[5] : "");
  }

  private void readName(int fieldNumber, Name name)
  {
    String[] field = getValues(fieldNumber);
    name.setLast(field.length >= 1 ? field[0] : "");
    name.setFirst(field.length >= 2 ? field[1] : "");
    name.setMiddle(field.length >= 3 ? field[2] : "");
    name.setSuffix(field.length >= 4 ? field[3] : "");
    name.setPrefix(field.length >= 5 ? field[4] : "");
    name.setTypeCode(field.length >= 7 ? field[6] : "");
  }

  private void readFields(String messageText)
  {
    int totalLength = messageText.length();
    startField = 0;
    for (int i = 0; i < totalLength; i++)
    {
      char c = messageText.charAt(i);
      if (c < ' ')
      {
        // end of segment, break
        currentSegment.add(messageText.substring(startField, i));
        currentSegment = new ArrayList<String>();
        segments.add(currentSegment);
        startField = i + 1;
      } else if (c == separators[BAR])
      {
        String fieldValue = messageText.substring(startField, i);
        currentSegment.add(fieldValue);
        if (currentSegment.size() == 1 && fieldValue.equals("MSH"))
        {
          // MSH is a special case where the first separator is the first
          // field.
          currentSegment.add(String.valueOf(separators[0]));
        }
        startField = i + 1;
      }
    }
  }

  private Date getValueDate(int fieldNumber)
  {
    String fieldValue = getValue(fieldNumber);
    if (fieldValue.equals("") || fieldValue.length() < 8)
    {
      return null;
    }
    if (fieldValue.length() < 14)
    {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
      try
      {
        return sdf.parse(fieldValue.substring(0, 8));
      } catch (java.text.ParseException e)
      {
        return null;
        // TODO
        // throw new ParseException("Invalid Date", currentSegment.get(0) + "-"
        // + fieldNumber);
      }
    }
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    try
    {
      return sdf.parse(fieldValue.substring(0, 14));
    } catch (java.text.ParseException e)
    {
      return null;
      // TODO
      // throw new ParseException("Invalid DateTime", currentSegment.get(0) +
      // "-" + fieldNumber);
    }
  }

  private String getValue(int fieldNumber)
  {
    String value = null;
    if (currentSegment.size() > fieldNumber)
    {
      value = currentSegment.get(fieldNumber);
    }
    if (value == null)
    {
      return "";
    }
    int valueLength = value.length();
    for (int i = 0; i < valueLength; i++)
    {
      char c = value.charAt(i);
      if (c == separators[TIL] || c == separators[CAR] || c == separators[AMP])
      {
        return value.substring(0, i);
      }
    }
    return value;
  }

  private String[] getValues(int fieldNumber)
  {
    String value = null;
    if (currentSegment.size() > fieldNumber)
    {
      value = currentSegment.get(fieldNumber);
    }
    if (value == null)
    {
      return new String[] { "" };
    }
    int valueLength = value.length();
    for (int i = 0; i < valueLength; i++)
    {
      char c = value.charAt(i);
      if (c == separators[TIL])
      {
        value = value.substring(0, i);
        break;
      }
    }
    return value.split("\\" + separators[CAR]);
  }
  
  private List<String[]> getRepeatValues(int fieldNumber)
  {
    List<String[]> values = new ArrayList<String[]>();
    String value = null;
    if (currentSegment.size() > fieldNumber)
    {
      value = currentSegment.get(fieldNumber);
    }
    if (value == null)
    {
      values.add(new String[] { "" });
      return values;
    }
    int valueLength = value.length();
    int startPos = 0;
    for (int i = 0; i < valueLength; i++)
    {
      char c = value.charAt(i);
      if (c == separators[TIL])
      {
        values.add(value.substring(startPos, i).split("\\" + separators[CAR]));
        startPos = i + 1;
      }
    }
    if (startPos < valueLength)
    {
      values.add(value.substring(startPos).split("\\" + separators[CAR]));
    }
    return values;
  }

  private void readSeparators(String messageText)
  {
    if (messageText.startsWith("MSH") && messageText.length() > 10)
    {
      separators[BAR] = messageText.charAt(BAR + 3);
      separators[CAR] = messageText.charAt(CAR + 3);
      separators[TIL] = messageText.charAt(TIL + 3);
      separators[SLA] = messageText.charAt(SLA + 3);
      separators[AMP] = messageText.charAt(AMP + 3);
    } else
    {
      separators[BAR] = '|';
      separators[CAR] = '^';
      separators[TIL] = '~';
      separators[SLA] = '\\';
      separators[AMP] = '&';
      if (!messageText.startsWith("MSH"))
      {
        registerError(pi.Hl7MshSegmentIsMissing);
      }
      else if (messageText.length() < 10)
      {
        registerError(pi.Hl7MshEncodingCharacterIsMissing);
      }
      else
      {
        registerError(pi.GeneralParseException);
      }
    }

  }

  public String makeAckMessage(MessageReceived messageReceived)
  {
    // TODO Message needs to be in a better format
    if (messageReceived.hasErrors())
    {
      for (IssueFound issueFound : messageReceived.getIssuesFound())
      {
        if (issueFound.isError())
          return "MSH|^~\\&|||||201105231008000||ACK^|201105231008000|P|2.3.1|\r" + "MSA|AE|TODO|"
              + issueFound.getDisplayText() + "|\r";
      }
    }
    return "MSH|^~\\&|||||201105231008000||ACK^|201105231008000|P|2.3.1|\r" + "MSA|AA|TODO|Message received for "
        + messageReceived.getPatient().getNameFirst() + " " + messageReceived.getPatient().getNameLast() + "|\r";

  }

}
