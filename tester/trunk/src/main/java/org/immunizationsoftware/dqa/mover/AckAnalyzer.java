package org.immunizationsoftware.dqa.mover;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.immunizationsoftware.dqa.tester.manager.HL7Reader;

public class AckAnalyzer {

  public static enum ErrorType {
    UNKNOWN, AUTHENTICATION, SENDER_PROBLEM, RECEIVER_PROBLEM
  };

  public static enum AckType {
    DEFAULT, NMSIIS, ALERT, WEBIZ, MIIC, IRIS, VIIS, NJSIIS;
    
    private boolean inHL7Format = true;
    
    public boolean isInHL7Format()
    {
      return inHL7Format;
    }
  };
  
  static
  {
    AckType.NJSIIS.inHL7Format = false;
  }
  
  public static HL7Reader getMessageReader(String ackMessageText, AckType ackType)
  {
    if (ackMessageText == null || ackMessageText.length() == 0 || !ackType.inHL7Format)
    {
      return null;
    }
    HL7Reader ackMessageReader = new HL7Reader(ackMessageText);
    if (!ackMessageReader.advanceToSegment("MSH") || !ackMessageReader.getValue(9).equals("ACK")) {
      return null;
    }
    return ackMessageReader;
  }

  private ErrorType errorType = null;
  private boolean ackMessage = false;
  private boolean positive = false;
  private boolean temporarilyUnavailable = false;
  private boolean setupProblem = false;
  private String setupProblemDescription = "";
  private AckType ackType = null;
  private List<String> errorMessages = new ArrayList<String>();
  private List<String> segments;
  private FileOut errorFileOut = null;

  private void log(String s) {
    if (errorFileOut != null) {
      try {
        errorFileOut.printCommentLn(s);
      } catch (IOException ioe) {
        ioe.printStackTrace();
      }
    }
  }

  public String getSetupProblemDescription() {
    return setupProblemDescription;
  }

  public void setSetupProblemDescription(String setupProblemDescription) {
    this.setupProblemDescription = setupProblemDescription;
  }

  public boolean isTemporarilyUnavailable() {
    return temporarilyUnavailable;
  }

  public void setTemporarilyUnavailable(boolean temporarilyUnavailable) {
    this.temporarilyUnavailable = temporarilyUnavailable;
  }

  public boolean hasSetupProblem() {
    return setupProblem;
  }

  public void setSetupProblem(boolean setupProblem) {
    this.setupProblem = setupProblem;
  }

  public ErrorType getErrorType() {
    return errorType;
  }

  public void setErrorType(ErrorType errorType) {
    this.errorType = errorType;
  }

  private String ackCode = "";

  public boolean isAckMessage() {
    return ackMessage;
  }

  public boolean isPositive() {
    return positive;
  }

  public void setPositive(boolean positive) {
    this.positive = positive;
  }

  public String getAckCode() {
    return ackCode;
  }

  public void setAckCode(String ackCode) {
    this.ackCode = ackCode;
  }

  public AckAnalyzer(String ack) {
    this(ack, AckType.DEFAULT, null);
  }

  public AckAnalyzer(String ack, AckType ackType) {
    this(ack, ackType, null);
  }

  public AckAnalyzer(String ack, AckType ackType, FileOut errorFileOut) {
    System.out.println("--> ack = " + ack);
    while (ack != null && ack.length() > 0 && ack.charAt(0) <= ' ') {
      ack = ack.substring(1);
    }
    this.ackType = ackType;
    this.errorFileOut = errorFileOut;

    ack = convertToSegments(ack);
    String ackUpperCase = ack.toUpperCase();

    boolean isNotAck = false;
    if (ack.length() == 0) {
      isNotAck = true;
      log("Returned result is not an acknowledgement message: no acknowledgement message returned");
    } else if (!ack.startsWith("MSH|")) {
      isNotAck = true;
      log("Returned result is not an acknowledgement message: first line does not start with MSH|");
    } else if (!getFieldValue("MSH", 9).equals("ACK")) {
      isNotAck = true;
      log("Returned result is not an acknowledgement message: MSH-9 is not ACK, it is '" + getFieldValue("MSH", 9) + "'");
    }
    if (isNotAck) {
      System.out.println("--> ackType = " + ackType);
      if (ackType.equals(AckType.NJSIIS)) {
        isNotAck = false;
        ackMessage = true;
        positive = ackUpperCase.equals("SUCCESS");
        ackCode = positive ? "AA" : "AE";
        System.out.println("--> positive = " + positive);
      } else {
        ackMessage = false;
        positive = false;
        setupProblem = true;
        if (ack != null && ackType == AckType.NMSIIS) {
          if (ack.length() < 240) {
            setupProblemDescription = ack;
          } else {
            setupProblemDescription = "Unexpected non-HL7 response, please verify connection URL";
          }
        } else {
          setupProblemDescription = "Unexpected non-HL7 response, please verify connection URL";
        }
      }
    } else {
      ackMessage = true;
      ackCode = getFieldValue("MSA", 1);

      if (ackType.equals(AckType.NMSIIS)) {
        setupProblem = ackUpperCase.indexOf("|BAD MESSAGE|") != -1 || ackUpperCase.indexOf("FILE REJECTED") != -1;
        if (setupProblem) {
          log("Setup problem found, message contains phrase |BAD MESSAGE| or File Rejected.");
        }
        int recordRejectedPos = ackUpperCase.indexOf("RECORD REJECTED");
        int messageRejectedPos = ackUpperCase.indexOf("MESSAGE REJECTED");
        int warningRxaPos1 = ackUpperCase.indexOf("WARNING:  RXA #");
        int warningRxaPos2 = -1;
        if (warningRxaPos1 > -1) {
          warningRxaPos2 = ackUpperCase.indexOf(" IGNORED - REQUIRED FIELD RXA-");
        }
        boolean recordNotRejected = recordRejectedPos == -1 && messageRejectedPos == -1 && warningRxaPos2 == -1;
        if (!recordNotRejected) {
          log("Record was rejected, message contains phrase Record Rejected");
        }
        positive = !setupProblem && recordNotRejected;
      } else if (ackType.equals(AckType.MIIC)) {
        int recordRejectedPos = ackUpperCase.indexOf("REJECTED");
        int pidRejectedPos = ackUpperCase.indexOf("PID #1 IGNORED");
        positive = ackUpperCase.startsWith("MSH|^~\\&|") && recordRejectedPos == -1 && pidRejectedPos == -1;
        if (positive) {
          int rxaRejectedPos = ackUpperCase.indexOf("RXA #");
          if (rxaRejectedPos != -1) {
            rxaRejectedPos = ackUpperCase.indexOf(" ", rxaRejectedPos + 5);
            if (rxaRejectedPos != -1) {
              positive = !ackUpperCase.substring(rxaRejectedPos + +1).startsWith("IGNORED");
            }
          }
        }
        if (!positive) {
          log("The word rejected appeared in the message so the message was rejected");
        }
      } else if (ackType.equals(AckType.VIIS)) {
        String[] rejectPhrases = { "Unsupported HL7 version or trigger".toUpperCase(), "REJECTED", "PID #1 IGNORED", "BAD MESSAGE" };

        positive = ackUpperCase.startsWith("MSH|^~\\&|");
        for (String rejectPhrase : rejectPhrases) {
          int pos = ackUpperCase.indexOf(rejectPhrase);
          if (pos > 0) {
            positive = false;
            break;
          }
        }
        if (positive) {
          int rxaRejectedPos = ackUpperCase.indexOf("RXA #");
          if (rxaRejectedPos != -1) {
            rxaRejectedPos = ackUpperCase.indexOf(" ", rxaRejectedPos + 5);
            if (rxaRejectedPos != -1) {
              positive = !ackUpperCase.substring(rxaRejectedPos + +1).startsWith("IGNORED");
            }
          }
        }
        if (!positive) {
          log("The word rejected appeared in the message so the message was rejected");
        }
      } else if (ackType.equals(AckType.IRIS)) {
        // IA defines 5 levels of errors: None, Low, Moderate, High, and
        // Critical
        int recordRejectedPos = ackUpperCase.indexOf("REJECTED");
        int pidRejectedPos = ackUpperCase.indexOf("PID #1 IGNORED");
        positive = ackUpperCase.startsWith("MSH|^~\\&|") && recordRejectedPos == -1 && pidRejectedPos == -1;
        if (positive) {
          int rxaRejectedPos = ackUpperCase.indexOf("RXA #");
          if (rxaRejectedPos != -1) {
            rxaRejectedPos = ackUpperCase.indexOf(" ", rxaRejectedPos + 5);
            if (rxaRejectedPos != -1) {
              positive = !ackUpperCase.substring(rxaRejectedPos + +1).startsWith("IGNORED");
            }
          }
        }
        if (!positive) {
          log("The word rejected appeared in the message so the message was rejected");
        }
      } else if (ackType.equals(AckType.ALERT)) {
        positive = true;
        int pos = 1;
        String[] values = null;
        while ((values = getFieldValues("MSA", pos, 1)) != null) {
          if (values.length > 0) {
            if (values[0].equals("AE")) {
              positive = false;
              break;
            }
          }
          pos++;
        }
        if (!positive) {
          log("At least one MSA-1 field was found with a value of AE so message was rejected");
        }
      } else if (ackType.equals(AckType.WEBIZ)) {
        if (ackCode.equals("AA")) {
          positive = true;
        } else if (ackCode.equals("AE")) {
          positive = true;
          if (ack.indexOf("Not processing order group") != -1) {
            positive = false;
          } else if (ack.indexOf("Application internal error") != -1) {
            positive = false;
          }
        } else {
          positive = false;
        }
      } else {
        if (ackCode.equals("AA")) {
          positive = true;
        } else if (ackCode.equals("AR")) {
          positive = false;
        } else {
          positive = true;
          boolean noSeverity = true;
          int pos = 1;
          String[] values = null;
          while ((values = getFieldValues("ERR", pos, 4)) != null) {
            if (values.length > 0) {
              if (!values[0].equals("")) {
                noSeverity = false;
              }
              if (values[0].equals("E")) {
                positive = false;
                break;
              }
            }
            pos++;
          }
          if (noSeverity) {
            positive = false;
          }
        }

      }
    }
  }

  private String convertToSegments(String ack) {
    StringBuilder ackActual = new StringBuilder();
    segments = new ArrayList<String>();
    if (ack != null) {
      BufferedReader in = new BufferedReader(new StringReader(ack));
      String line;
      try {
        while ((line = in.readLine()) != null) {
          if (line.startsWith("FHS") || line.startsWith("BHS") || line.startsWith("BTS") || line.startsWith("FTS")) {
            // ignore these lines
          } else {
            segments.add(line);
            ackActual.append(line);
            ackActual.append("\r");
          }
        }
      } catch (IOException ioe) {
        ioe.printStackTrace();
      }
    }
    return ackActual.toString();
  }

  private boolean hasSegment(String segmentName) {
    for (String segment : segments) {
      if (segment.startsWith(segmentName + "|")) {
        return true;
      }
    }
    return false;
  }

  private String getFieldValue(String segmentName, int pos) {
    String[] values = getFieldValues(segmentName, 1, pos);
    if (values != null && values.length > 0 && values[0] != null) {
      return values[0];
    }
    return "";
  }

  private String[] getFieldValues(String segmentName, int segmentCount, int pos) {
    if (!segmentName.equals("MSH")) {
      pos++;
    }
    for (String segment : segments) {
      if (segment.startsWith(segmentName + "|")) {
        segmentCount--;
        if (segmentCount == 0) {
          int startPos = -1;
          int endPos = -1;
          while (pos > 0) {
            pos--;
            if (endPos < segment.length()) {
              startPos = endPos + 1;
              endPos = segment.indexOf("|", startPos);
              if (endPos == -1) {
                endPos = segment.length();
              }
            }
          }
          String value = segment.substring(startPos, endPos);
          int repeatPos = value.indexOf("~");
          if (repeatPos != -1) {
            value = value.substring(0, repeatPos);
          }
          return value.split("\\^");
        }
      }
    }

    return null;
  }

}
