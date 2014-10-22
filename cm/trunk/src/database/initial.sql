CREATE TABLE release_version (
  release_id             INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  major_version_num      INTEGER NOT NULL,
  minor_version_num      INTEGER NOT NULL,
  started_date           DATETIME NOT NULL,
  released_date          DATETIME,
  retired_date           DATETIME,
  release_status         VARCHAR(1) NOT NULL
);

CREATE TABLE attribute_assigned (
  attribute_assigned_id  INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  attribute_type_id      INTEGER NOT NULL,
  table_id               INTEGER NOT NULL,
  display_order          INTEGER NOT NULL DEFAULT 0,
  allow_multiple         VARCHAR(1) NOT NULL DEFAULT 'N',
  attribute_status       VARCHAR(1) NOT NULL
);

CREATE TABLE attribute_type (
  attribute_type_id      INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  attribute_label        VARCHAR(120) NOT NULL,
  attribute_help         VARCHAR(1200),
  ref_table_id           INTEGER,
  attribute_format       VARCHAR(1) NOT NULL
);

CREATE TABLE allowed_value (
  allowed_value_id       INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  attribute_type_id      INTEGER NOT NULL,
  saved_value            VARCHAR(1200) NOT NULL,
  display_text           VARCHAR(120) NOT NULL,
  display_order          INTEGER NOT NULL DEFAULT 0
);

CREATE TABLE code_table (
  table_id              INTEGER NOT NULL PRIMARY KEY,
  context_table_id      INTEGER
);

CREATE TABLE code_master (
  code_id               INTEGER NOT NULL PRIMARY KEY,
  table_id              INTEGER NOT NULL, 
  code_value            VARCHAR(120) NOT NULL
);

CREATE TABLE attribute_value (
  value_id              INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  attribute_type_id     INTEGER NOT NULL,
  code_id               INTEGER NOT NULL,
  attribute_value       VARCHAR(5000) NOT NULL
);

CREATE TABLE attribute_comment (
  attribute_comment_id  INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  value_id              INTEGER NOT NULL,
  user_id               INTEGER NOT NULL,
  comment_text          VARCHAR(1200),
  entry_date            DATETIME NOT NULL,
  position_status       VARCHAR(1) NOT NULL
);

CREATE TABLE code_table_instance (
  table_instance_id     INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  table_id              INTEGER NOT NULL,
  release_id            INTEGER NOT NULL,
  table_label           VARCHAR(120) NOT NULL,
  issue_count           INTEGER NOT NULL DEFAULT 0,
  inclusion_status      VARCHAR(1) NOT NULL,
  enforce_unique        VARCHAR(1) NOT NULL DEFAULT 'N'
);

CREATE TABLE code_instance (
  code_instance_id      INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  code_id               INTEGER NOT NULL,
  context_id            INTEGER,
  indicates_table_id    INTEGER,
  code_label            VARCHAR(1200),
  use_value             VARCHAR(1200),
  code_status           VARCHAR(1200),
  hl7_code_table        VARCHAR(1200),
  table_instance_id     INTEGER NOT NULL,
  issue_count           INTEGER NOT NULL DEFAULT 0,
  inclusion_status      VARCHAR(1),
  temp_values           VARCHAR(1200)
);

CREATE TABLE attribute_instance (
  attribute_instance_id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  value_id              INTEGER NOT NULL,
  code_instance_id      INTEGER NOT NULL,
  accept_status         VARCHAR(1) NOT NULL
);
CREATE UNIQUE INDEX attribute_instance_index ON attribute_instance (value_id, code_instance_id);

CREATE TABLE user (
  user_id               INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  user_name             VARCHAR(120) NOT NULL,
  password              VARCHAR(120) NOT NULL,
  email_address         VARCHAR(120) NOT NULL,
  user_type             VARCHAR(1) NOT NULL
);

INSERT INTO release_version (major_version_num, minor_version_num, started_date, release_status) VALUES (1, 0, '2014-01-01', 'P'); 


INSERT INTO code_table (table_id, context_table_id) VALUES(40, NULL);
INSERT INTO code_table (table_id, context_table_id) VALUES(2, NULL);
INSERT INTO code_table (table_id, context_table_id) VALUES(3, 4);
INSERT INTO code_table (table_id, context_table_id) VALUES(4, 2);
INSERT INTO code_table (table_id, context_table_id) VALUES(5, NULL);
INSERT INTO code_table (table_id, context_table_id) VALUES(6, NULL);
INSERT INTO code_table (table_id, context_table_id) VALUES(7, NULL);
INSERT INTO code_table (table_id, context_table_id) VALUES(8, NULL);
INSERT INTO code_table (table_id, context_table_id) VALUES(9, NULL);
INSERT INTO code_table (table_id, context_table_id) VALUES(59, NULL);
INSERT INTO code_table (table_id, context_table_id) VALUES(36, NULL);
INSERT INTO code_table (table_id, context_table_id) VALUES(49, NULL);
INSERT INTO code_table (table_id, context_table_id) VALUES(51, NULL);
INSERT INTO code_table (table_id, context_table_id) VALUES(10, NULL);
INSERT INTO code_table (table_id, context_table_id) VALUES(57, NULL);
INSERT INTO code_table (table_id, context_table_id) VALUES(55, NULL);
INSERT INTO code_table (table_id, context_table_id) VALUES(54, NULL);
INSERT INTO code_table (table_id, context_table_id) VALUES(56, NULL);
INSERT INTO code_table (table_id, context_table_id) VALUES(46, NULL);
INSERT INTO code_table (table_id, context_table_id) VALUES(45, NULL);
INSERT INTO code_table (table_id, context_table_id) VALUES(11, NULL);
INSERT INTO code_table (table_id, context_table_id) VALUES(12, NULL);
INSERT INTO code_table (table_id, context_table_id) VALUES(39, NULL);
INSERT INTO code_table (table_id, context_table_id) VALUES(37, NULL);
INSERT INTO code_table (table_id, context_table_id) VALUES(43, NULL);
INSERT INTO code_table (table_id, context_table_id) VALUES(13, NULL);
INSERT INTO code_table (table_id, context_table_id) VALUES(38, NULL);
INSERT INTO code_table (table_id, context_table_id) VALUES(14, NULL);
INSERT INTO code_table (table_id, context_table_id) VALUES(15, NULL);
INSERT INTO code_table (table_id, context_table_id) VALUES(16, NULL);
INSERT INTO code_table (table_id, context_table_id) VALUES(17, NULL);
INSERT INTO code_table (table_id, context_table_id) VALUES(18, NULL);
INSERT INTO code_table (table_id, context_table_id) VALUES(19, NULL);
INSERT INTO code_table (table_id, context_table_id) VALUES(20, NULL);
INSERT INTO code_table (table_id, context_table_id) VALUES(21, NULL);
INSERT INTO code_table (table_id, context_table_id) VALUES(22, NULL);
INSERT INTO code_table (table_id, context_table_id) VALUES(23, NULL);
INSERT INTO code_table (table_id, context_table_id) VALUES(24, NULL);
INSERT INTO code_table (table_id, context_table_id) VALUES(42, NULL);
INSERT INTO code_table (table_id, context_table_id) VALUES(41, NULL);
INSERT INTO code_table (table_id, context_table_id) VALUES(25, NULL);
INSERT INTO code_table (table_id, context_table_id) VALUES(26, NULL);
INSERT INTO code_table (table_id, context_table_id) VALUES(27, NULL);
INSERT INTO code_table (table_id, context_table_id) VALUES(28, NULL);
INSERT INTO code_table (table_id, context_table_id) VALUES(29, NULL);
INSERT INTO code_table (table_id, context_table_id) VALUES(47, NULL);
INSERT INTO code_table (table_id, context_table_id) VALUES(30, NULL);
INSERT INTO code_table (table_id, context_table_id) VALUES(31, NULL);
INSERT INTO code_table (table_id, context_table_id) VALUES(34, NULL);
INSERT INTO code_table (table_id, context_table_id) VALUES(50, NULL);
INSERT INTO code_table (table_id, context_table_id) VALUES(32, NULL);
INSERT INTO code_table (table_id, context_table_id) VALUES(52, NULL);
INSERT INTO code_table (table_id, context_table_id) VALUES(60, NULL);
INSERT INTO code_table (table_id, context_table_id) VALUES(53, NULL);
INSERT INTO code_table (table_id, context_table_id) VALUES(58, NULL);
INSERT INTO code_table (table_id, context_table_id) VALUES(33, NULL);
INSERT INTO code_table (table_id, context_table_id) VALUES(48, NULL);
INSERT INTO code_table (table_id, context_table_id) VALUES(61, NULL);
INSERT INTO code_table (table_id, context_table_id) VALUES(62, NULL);
INSERT INTO code_table (table_id, context_table_id) VALUES(63, NULL);
INSERT INTO code_table (table_id, context_table_id) VALUES(64, NULL);
INSERT INTO code_table (table_id, context_table_id) VALUES(65, NULL);
INSERT INTO code_table (table_id, context_table_id) VALUES(66, NULL);


INSERT INTO code_table_instance (table_instance_id, table_id, release_id, table_label, inclusion_status, enforce_unique) VALUES (40, 40, 1, 'Acknowledgement Type', 'I', 'Y');
INSERT INTO code_table_instance (table_instance_id, table_id, release_id, table_label, inclusion_status, enforce_unique) VALUES (2, 2, 1, 'Address Country', 'I', 'Y');
INSERT INTO code_table_instance (table_instance_id, table_id, release_id, table_label, inclusion_status, enforce_unique) VALUES (3, 3, 1, 'Address County', 'I', 'Y');
INSERT INTO code_table_instance (table_instance_id, table_id, release_id, table_label, inclusion_status, enforce_unique) VALUES (4, 4, 1, 'Address State', 'I', 'Y');
INSERT INTO code_table_instance (table_instance_id, table_id, release_id, table_label, inclusion_status, enforce_unique) VALUES (5, 5, 1, 'Address Type', 'I', 'Y');
INSERT INTO code_table_instance (table_instance_id, table_id, release_id, table_label, inclusion_status, enforce_unique) VALUES (6, 6, 1, 'Administration Unit', 'I', 'Y');
INSERT INTO code_table_instance (table_instance_id, table_id, release_id, table_label, inclusion_status, enforce_unique) VALUES (7, 7, 1, 'Birth Order', 'I', 'Y');
INSERT INTO code_table_instance (table_instance_id, table_id, release_id, table_label, inclusion_status, enforce_unique) VALUES (8, 8, 1, 'Body Route', 'I', 'Y');
INSERT INTO code_table_instance (table_instance_id, table_id, release_id, table_label, inclusion_status, enforce_unique) VALUES (9, 9, 1, 'Body Site', 'I', 'Y');
INSERT INTO code_table_instance (table_instance_id, table_id, release_id, table_label, inclusion_status, enforce_unique) VALUES (59, 59, 1, 'CDC PHIN VADS', 'I', 'Y');
INSERT INTO code_table_instance (table_instance_id, table_id, release_id, table_label, inclusion_status, enforce_unique) VALUES (36, 36, 1, 'Character Set', 'I', 'Y');
INSERT INTO code_table_instance (table_instance_id, table_id, release_id, table_label, inclusion_status, enforce_unique) VALUES (49, 49, 1, 'Contraindication or Precaution', 'I', 'Y');
INSERT INTO code_table_instance (table_instance_id, table_id, release_id, table_label, inclusion_status, enforce_unique) VALUES (51, 51, 1, 'Evidence of Immunity', 'I', 'Y');
INSERT INTO code_table_instance (table_instance_id, table_id, release_id, table_label, inclusion_status, enforce_unique) VALUES (10, 10, 1, 'Financial Status Code', 'I', 'Y');
INSERT INTO code_table_instance (table_instance_id, table_id, release_id, table_label, inclusion_status, enforce_unique) VALUES (57, 57, 1, 'Financial Status Obs Method', 'I', 'Y');
INSERT INTO code_table_instance (table_instance_id, table_id, release_id, table_label, inclusion_status, enforce_unique) VALUES (55, 55, 1, 'Forecast Immunization Schedule', 'I', 'Y');
INSERT INTO code_table_instance (table_instance_id, table_id, release_id, table_label, inclusion_status, enforce_unique) VALUES (54, 54, 1, 'Forecast Reason', 'I', 'Y');
INSERT INTO code_table_instance (table_instance_id, table_id, release_id, table_label, inclusion_status, enforce_unique) VALUES (56, 56, 1, 'Forecast Series Status', 'I', 'Y');
INSERT INTO code_table_instance (table_instance_id, table_id, release_id, table_label, inclusion_status, enforce_unique) VALUES (46, 46, 1, 'HL7 Coding System', 'I', 'Y');
INSERT INTO code_table_instance (table_instance_id, table_id, release_id, table_label, inclusion_status, enforce_unique) VALUES (45, 45, 1, 'HL7 Value Type', 'I', 'Y');
INSERT INTO code_table_instance (table_instance_id, table_id, release_id, table_label, inclusion_status, enforce_unique) VALUES (11, 11, 1, 'Id Assigning Authority', 'I', 'Y');
INSERT INTO code_table_instance (table_instance_id, table_id, release_id, table_label, inclusion_status, enforce_unique) VALUES (12, 12, 1, 'Id Type Code', 'I', 'Y');
INSERT INTO code_table_instance (table_instance_id, table_id, release_id, table_label, inclusion_status, enforce_unique) VALUES (39, 39, 1, 'Message Processing Id', 'I', 'Y');
INSERT INTO code_table_instance (table_instance_id, table_id, release_id, table_label, inclusion_status, enforce_unique) VALUES (37, 37, 1, 'Message Profile Id', 'I', 'Y');
INSERT INTO code_table_instance (table_instance_id, table_id, release_id, table_label, inclusion_status, enforce_unique) VALUES (43, 43, 1, 'Observation Identifier', 'I', 'Y');
INSERT INTO code_table_instance (table_instance_id, table_id, release_id, table_label, inclusion_status, enforce_unique) VALUES (13, 13, 1, 'Organization', 'I', 'Y');
INSERT INTO code_table_instance (table_instance_id, table_id, release_id, table_label, inclusion_status, enforce_unique) VALUES (38, 38, 1, 'Patient Class', 'I', 'Y');
INSERT INTO code_table_instance (table_instance_id, table_id, release_id, table_label, inclusion_status, enforce_unique) VALUES (14, 14, 1, 'Patient Ethnicity', 'I', 'Y');
INSERT INTO code_table_instance (table_instance_id, table_id, release_id, table_label, inclusion_status, enforce_unique) VALUES (15, 15, 1, 'Patient Id', 'I', 'Y');
INSERT INTO code_table_instance (table_instance_id, table_id, release_id, table_label, inclusion_status, enforce_unique) VALUES (16, 16, 1, 'Patient Protection', 'I', 'Y');
INSERT INTO code_table_instance (table_instance_id, table_id, release_id, table_label, inclusion_status, enforce_unique) VALUES (17, 17, 1, 'Patient Publicity', 'I', 'Y');
INSERT INTO code_table_instance (table_instance_id, table_id, release_id, table_label, inclusion_status, enforce_unique) VALUES (18, 18, 1, 'Patient Race', 'I', 'Y');
INSERT INTO code_table_instance (table_instance_id, table_id, release_id, table_label, inclusion_status, enforce_unique) VALUES (19, 19, 1, 'Patient Sex', 'I', 'Y');
INSERT INTO code_table_instance (table_instance_id, table_id, release_id, table_label, inclusion_status, enforce_unique) VALUES (20, 20, 1, 'Person Language', 'I', 'Y');
INSERT INTO code_table_instance (table_instance_id, table_id, release_id, table_label, inclusion_status, enforce_unique) VALUES (21, 21, 1, 'Person Name Type', 'I', 'Y');
INSERT INTO code_table_instance (table_instance_id, table_id, release_id, table_label, inclusion_status, enforce_unique) VALUES (22, 22, 1, 'Person Relationship', 'I', 'Y');
INSERT INTO code_table_instance (table_instance_id, table_id, release_id, table_label, inclusion_status, enforce_unique) VALUES (23, 23, 1, 'Physician Number', 'I', 'Y');
INSERT INTO code_table_instance (table_instance_id, table_id, release_id, table_label, inclusion_status, enforce_unique) VALUES (24, 24, 1, 'Registry Status', 'I', 'Y');
INSERT INTO code_table_instance (table_instance_id, table_id, release_id, table_label, inclusion_status, enforce_unique) VALUES (42, 42, 1, 'Telecommunication Equipment', 'I', 'Y');
INSERT INTO code_table_instance (table_instance_id, table_id, release_id, table_label, inclusion_status, enforce_unique) VALUES (41, 41, 1, 'Telecommunication Use', 'I', 'Y');
INSERT INTO code_table_instance (table_instance_id, table_id, release_id, table_label, inclusion_status, enforce_unique) VALUES (25, 25, 1, 'Vaccination Action Code', 'I', 'Y');
INSERT INTO code_table_instance (table_instance_id, table_id, release_id, table_label, inclusion_status, enforce_unique) VALUES (26, 26, 1, 'Vaccination Completion', 'I', 'Y');
INSERT INTO code_table_instance (table_instance_id, table_id, release_id, table_label, inclusion_status, enforce_unique) VALUES (27, 27, 1, 'Vaccination Confidentiality', 'I', 'Y');
INSERT INTO code_table_instance (table_instance_id, table_id, release_id, table_label, inclusion_status, enforce_unique) VALUES (28, 28, 1, 'Vaccination CPT Code', 'I', 'Y');
INSERT INTO code_table_instance (table_instance_id, table_id, release_id, table_label, inclusion_status, enforce_unique) VALUES (29, 29, 1, 'Vaccination CVX Code', 'I', 'Y');
INSERT INTO code_table_instance (table_instance_id, table_id, release_id, table_label, inclusion_status, enforce_unique) VALUES (47, 47, 1, 'Vaccination Funding Source', 'I', 'Y');
INSERT INTO code_table_instance (table_instance_id, table_id, release_id, table_label, inclusion_status, enforce_unique) VALUES (30, 30, 1, 'Vaccination Information Source', 'I', 'Y');
INSERT INTO code_table_instance (table_instance_id, table_id, release_id, table_label, inclusion_status, enforce_unique) VALUES (31, 31, 1, 'Vaccination Manufacturer Code', 'I', 'Y');
INSERT INTO code_table_instance (table_instance_id, table_id, release_id, table_label, inclusion_status, enforce_unique) VALUES (34, 34, 1, 'Vaccination Order Control Code', 'I', 'Y');
INSERT INTO code_table_instance (table_instance_id, table_id, release_id, table_label, inclusion_status, enforce_unique) VALUES (50, 50, 1, 'Vaccination Reaction', 'I', 'Y');
INSERT INTO code_table_instance (table_instance_id, table_id, release_id, table_label, inclusion_status, enforce_unique) VALUES (32, 32, 1, 'Vaccination Refusal', 'I', 'Y');
INSERT INTO code_table_instance (table_instance_id, table_id, release_id, table_label, inclusion_status, enforce_unique) VALUES (52, 52, 1, 'Vaccination Special Indications', 'I', 'Y');
INSERT INTO code_table_instance (table_instance_id, table_id, release_id, table_label, inclusion_status, enforce_unique) VALUES (60, 60, 1, 'Vaccination VIS CVX Code', 'I', 'Y');
INSERT INTO code_table_instance (table_instance_id, table_id, release_id, table_label, inclusion_status, enforce_unique) VALUES (53, 53, 1, 'Vaccination VIS Doc Type', 'I', 'Y');
INSERT INTO code_table_instance (table_instance_id, table_id, release_id, table_label, inclusion_status, enforce_unique) VALUES (58, 58, 1, 'Vaccination VIS Vaccines', 'I', 'Y');
INSERT INTO code_table_instance (table_instance_id, table_id, release_id, table_label, inclusion_status, enforce_unique) VALUES (33, 33, 1, 'Vaccine Product', 'I', 'Y');
INSERT INTO code_table_instance (table_instance_id, table_id, release_id, table_label, inclusion_status, enforce_unique) VALUES (48, 48, 1, 'Vaccine Type', 'I', 'Y');
INSERT INTO code_table_instance (table_instance_id, table_id, release_id, table_label, inclusion_status, enforce_unique) VALUES (61, 61, 1, 'Potential Issue', 'I', 'Y');
INSERT INTO code_table_instance (table_instance_id, table_id, release_id, table_label, inclusion_status, enforce_unique) VALUES (62, 62, 1, 'Vaccine Group', 'I', 'Y');
INSERT INTO code_table_instance (table_instance_id, table_id, release_id, table_label, inclusion_status, enforce_unique) VALUES (63, 63, 1, 'Facility Type', 'I', 'Y');
INSERT INTO code_table_instance (table_instance_id, table_id, release_id, table_label, inclusion_status, enforce_unique) VALUES (64, 64, 1, 'Vaccination Trade Name', 'I', 'Y');
INSERT INTO code_table_instance (table_instance_id, table_id, release_id, table_label, inclusion_status, enforce_unique) VALUES (65, 65, 1, 'Vaccination Validity', 'I', 'Y');
INSERT INTO code_table_instance (table_instance_id, table_id, release_id, table_label, inclusion_status, enforce_unique) VALUES (66, 66, 1, 'HL7 Error Status Code', 'I', 'Y');


INSERT INTO user (user_name, password, email_address, user_type) VALUES ('Nathan Bunker', 'panda', 'Nathan.Bunker@gmail.com', 'A');
INSERT INTO user (user_name, password, email_address, user_type) VALUES ('DQA Initial', 'panda', 'Nathan.Bunker@gmail.com', 'S');
INSERT INTO user (user_name, password, email_address, user_type) VALUES ('CDC IISSB', 'panda', 'Nathan.Bunker@gmail.com', 'S');
INSERT INTO user (user_name, password, email_address, user_type) VALUES ('DQAcm', 'panda', 'Nathan.Bunker@gmail.com', 'S');

-- FREE_TEXT       "T" - "Free Text"
-- LONG_TEXT       "L" - "Long Text"
-- DATE            "D" - "Date"
-- SELECT_TEXT     "S" - "Select Text"
-- INTEGER         "I" - "Integer"
-- CODE_MASTER     "C" - "Code Master"
-- CODE_TABLE      "A" = "Code Table"

-- 

INSERT INTO attribute_type(attribute_type_id, attribute_format, attribute_label, attribute_help ) VALUES (1, 'T', 'Code Label', 'A short human readable description of the code.');
INSERT INTO attribute_type(attribute_type_id, attribute_format, attribute_label, attribute_help ) VALUES (2, 'T', 'Use Value', 'The best value to represent concept, usually the same as the code value.');
INSERT INTO attribute_type(attribute_type_id, attribute_format, attribute_label, attribute_help ) VALUES (3, 'S', 'Code Status', 'The usage status of the code, in relation to whether a sender should use this value or not. ');
INSERT INTO attribute_type(attribute_type_id, attribute_format, attribute_label, attribute_help ) VALUES (4, 'C', 'Context Code', 'The code that provides a scoping context. For example, BC is a valid state code in Mexico and a valid province code in Canada. These codes can be differentiated by having different contexts. ');
INSERT INTO attribute_type(attribute_type_id, attribute_format, attribute_label, attribute_help ) VALUES (5, 'T', 'Indicates Table', 'The code references a particular set of values in a table. For example, a particular LOINC may indicate a particular code table should be used. This is used in OBX segments. ');
INSERT INTO attribute_type(attribute_type_id, attribute_format, attribute_label, attribute_help ) VALUES (6, 'T', 'HL7 Code Table', 'When sending this in HL7, the code table that should be used. ');
INSERT INTO attribute_type(attribute_type_id, attribute_format, attribute_label, attribute_help ) VALUES (7, 'D', 'Licensed Valid Start Date', 'The first date when this concept could be used for vaccination events that occurred in the US.');
INSERT INTO attribute_type(attribute_type_id, attribute_format, attribute_label, attribute_help ) VALUES (8, 'D', 'Licensed Use Start Date', 'The first date when this concept would normally be used for vaccination events that occurred in the US.');
INSERT INTO attribute_type(attribute_type_id, attribute_format, attribute_label, attribute_help ) VALUES (9, 'D', 'Licensed Use End Date', 'The first date when this concept would NOT normally be used for vaccination events that occurred in the US.');
INSERT INTO attribute_type(attribute_type_id, attribute_format, attribute_label, attribute_help ) VALUES (10, 'D', 'Licensed Valid End Date', 'The first date when this concept should NOT be used for vaccination events that occurred in the US.');
INSERT INTO attribute_type(attribute_type_id, attribute_format, attribute_label, attribute_help ) VALUES (11, 'D', 'Reported Valid Start Date', 'The first date when this concept could be used to report vaccinations events that occurred somewhere else (historical).');
INSERT INTO attribute_type(attribute_type_id, attribute_format, attribute_label, attribute_help ) VALUES (12, 'D', 'Reported Use Start Date', 'The first date when this concept would normally be used to report vaccinations events that occurred somewhere else (historical).');
INSERT INTO attribute_type(attribute_type_id, attribute_format, attribute_label, attribute_help ) VALUES (13, 'D', 'Reported Use End Date', 'The first date when this concept would NOT normally be used to report vaccinations events that occurred somewhere else (historical).');
INSERT INTO attribute_type(attribute_type_id, attribute_format, attribute_label, attribute_help ) VALUES (14, 'D', 'Reported Valid End Date', 'The first date when this concept should NOT used to report vaccinations events that occurred somewhere else (historical).');
INSERT INTO attribute_type(attribute_type_id, attribute_format, attribute_label, attribute_help, ref_table_id ) VALUES (15, 'C', 'CVX Code', 'The CDC assigned CVX code that this concept is associated with.', 29);
INSERT INTO attribute_type(attribute_type_id, attribute_format, attribute_label, attribute_help ) VALUES (16, 'I', 'Use Month Start', 'The first month since birth that a patient could possibly be administered this vaccination, whether as part of a routine schedule or one determined by special conditions. ');
INSERT INTO attribute_type(attribute_type_id, attribute_format, attribute_label, attribute_help ) VALUES (17, 'I', 'Use Month End', 'The first month since birth that a patient would not be expected to be administered this vaccination, whether part of a routine schedule or one determined by special conditions. ');
INSERT INTO attribute_type(attribute_type_id, attribute_format, attribute_label, attribute_help ) VALUES (18, 'S', 'Test Age', 'The general category of age that a patient is who normally receives this vaccination. This is used for automated testing purposes in order to create example messages that are sensible. ');
INSERT INTO attribute_type(attribute_type_id, attribute_format, attribute_label, attribute_help ) VALUES (19, 'S', 'Concept Type', 'General classification of how this CVX code is used. ');
INSERT INTO attribute_type(attribute_type_id, attribute_format, attribute_label, attribute_help ) VALUES (20, 'T', 'CDC Description', 'CDC''s description of the concept.');
INSERT INTO attribute_type(attribute_type_id, attribute_format, attribute_label, attribute_help, ref_table_id ) VALUES (21, 'C', 'Vaccine Group', 'Vaccine Group associated with this concept.', 62);
INSERT INTO attribute_type(attribute_type_id, attribute_format, attribute_label, attribute_help, ref_table_id ) VALUES (22, 'C', 'MVX Code', 'The CDC assigned MVC code that this concept is associated with.', 31);
INSERT INTO attribute_type(attribute_type_id, attribute_format, attribute_label, attribute_help ) VALUES (23, 'S', 'Vaccine Group Status', 'A general status of the use of this particular vaccine group. ');
INSERT INTO attribute_type(attribute_type_id, attribute_format, attribute_label, attribute_help ) VALUES (24, 'S', 'Profile Status', 'The status of a DQA profile.');
INSERT INTO attribute_type(attribute_type_id, attribute_format, attribute_label, attribute_help ) VALUES (25, 'T', 'Potential Issue Id', 'The DQA assigned id for a potential issue.');
INSERT INTO attribute_type(attribute_type_id, attribute_format, attribute_label, attribute_help ) VALUES (26, 'S', 'Application Type', 'The purpose for this installation of the DQA. ');
INSERT INTO attribute_type(attribute_type_id, attribute_format, attribute_label, attribute_help ) VALUES (27, 'S', 'Inclusion Status', 'The status of this code within the code table.');
INSERT INTO attribute_type(attribute_type_id, attribute_format, attribute_label, attribute_help ) VALUES (28, 'T', 'Target Object', 'The object this potential issue is associated with.');
INSERT INTO attribute_type(attribute_type_id, attribute_format, attribute_label, attribute_help ) VALUES (29, 'T', 'Target Field', 'The field this potential issue is associated with.');
INSERT INTO attribute_type(attribute_type_id, attribute_format, attribute_label, attribute_help ) VALUES (30, 'T', 'Issue Type', 'The type of potential issue this is.');
INSERT INTO attribute_type(attribute_type_id, attribute_format, attribute_label, attribute_help ) VALUES (31, 'T', 'Field Value', 'The value this potential issue references.');
INSERT INTO attribute_type(attribute_type_id, attribute_format, attribute_label, attribute_help ) VALUES (32, 'A', 'Code Table', 'The table this potential issue is normally associated with.');
INSERT INTO attribute_type(attribute_type_id, attribute_format, attribute_label, attribute_help ) VALUES (33, 'S', 'Change Priority', 'The relative importance to review this field when customizing for a specific interface.');
INSERT INTO attribute_type(attribute_type_id, attribute_format, attribute_label, attribute_help ) VALUES (34, 'S', 'Report Denominator', 'The type of denominator to use when generating the DQA report.');
INSERT INTO attribute_type(attribute_type_id, attribute_format, attribute_label, attribute_help ) VALUES (35, 'T', 'HL7 Reference', 'The HL7 location that is normally associated with this issue.');
INSERT INTO attribute_type(attribute_type_id, attribute_format, attribute_label, attribute_help, ref_table_id ) VALUES (36, 'C', 'HL7 Error Code', 'The HL7 error code that should be placed in ERR-3 for this issue.', 66);
INSERT INTO attribute_type(attribute_type_id, attribute_format, attribute_label, attribute_help ) VALUES (37, 'T', 'Issue Description', 'A human readable description of this issue. ');

-- INSERT INTO attribute_type(attribute_type_id, attribute_format, attribute_label, attribute_help ) VALUES (38, 'T', '', '');
-- INSERT INTO attribute_type(attribute_type_id, attribute_format, attribute_label, attribute_help ) VALUES (39, 'T', '', '');
-- INSERT INTO attribute_type(attribute_type_id, attribute_format, attribute_label, attribute_help ) VALUES (40, 'T', '', '');
-- INSERT INTO attribute_type(attribute_type_id, attribute_format, attribute_label, attribute_help ) VALUES (41, 'T', '', '');
-- INSERT INTO attribute_type(attribute_type_id, attribute_format, attribute_label, attribute_help ) VALUES (42, 'T', '', '');

INSERT INTO allowed_value(attribute_type_id, saved_value, display_text) VALUES (27, 'I', 'Included');
INSERT INTO allowed_value(attribute_type_id, saved_value, display_text) VALUES (27, 'R', 'Removed');
INSERT INTO allowed_value(attribute_type_id, saved_value, display_text) VALUES (27, 'Y', 'Proposed Include');
INSERT INTO allowed_value(attribute_type_id, saved_value, display_text) VALUES (27, 'N', 'Proposed Remove');

INSERT INTO allowed_value(attribute_type_id, saved_value, display_text) VALUES (33, 'Blocked', 'Blocked');
INSERT INTO allowed_value(attribute_type_id, saved_value, display_text) VALUES (33, 'Can', 'Can');
INSERT INTO allowed_value(attribute_type_id, saved_value, display_text) VALUES (33, 'May', 'May');
INSERT INTO allowed_value(attribute_type_id, saved_value, display_text) VALUES (33, 'Should', 'Should');

INSERT INTO allowed_value(attribute_type_id, saved_value, display_text) VALUES (34, 'Message Count', 'Message');
INSERT INTO allowed_value(attribute_type_id, saved_value, display_text) VALUES (34, 'NextOfKin Count', 'NextOfKin Count');
INSERT INTO allowed_value(attribute_type_id, saved_value, display_text) VALUES (34, 'Observation Count', 'Observation Count');
INSERT INTO allowed_value(attribute_type_id, saved_value, display_text) VALUES (34, 'Patient Count', 'Patient Count');
INSERT INTO allowed_value(attribute_type_id, saved_value, display_text) VALUES (34, 'Patient Underage Count', 'Patient Underage Count');
INSERT INTO allowed_value(attribute_type_id, saved_value, display_text) VALUES (34, 'Vaccination Admin Count', 'Vaccination Admin Count');
INSERT INTO allowed_value(attribute_type_id, saved_value, display_text) VALUES (34, 'Vaccination Count', 'Vaccination Count');
INSERT INTO allowed_value(attribute_type_id, saved_value, display_text) VALUES (34, 'Vaccination VIS Count', 'Vaccination VIS Count');

INSERT INTO allowed_value(attribute_type_id, saved_value, display_text) VALUES (3, 'D', 'Deprecated');
INSERT INTO allowed_value(attribute_type_id, saved_value, display_text) VALUES (3, 'G', 'Ignored');
INSERT INTO allowed_value(attribute_type_id, saved_value, display_text) VALUES (3, 'I', 'Invalid');
INSERT INTO allowed_value(attribute_type_id, saved_value, display_text) VALUES (3, 'U', 'Unrecognized');
INSERT INTO allowed_value(attribute_type_id, saved_value, display_text) VALUES (3, 'V', 'Valid');

INSERT INTO allowed_value(attribute_type_id, saved_value, display_text, display_order) VALUES (18, 'Birth', 'Birth', 1);
INSERT INTO allowed_value(attribute_type_id, saved_value, display_text, display_order) VALUES (18, '2m', '2 Months Old', 2);
INSERT INTO allowed_value(attribute_type_id, saved_value, display_text, display_order) VALUES (18, '2y', '2 Years Old', 3);
INSERT INTO allowed_value(attribute_type_id, saved_value, display_text, display_order) VALUES (18, '4y', '4 Years Old', 4);
INSERT INTO allowed_value(attribute_type_id, saved_value, display_text, display_order) VALUES (18, '12y', '12 Years Old', 5);
INSERT INTO allowed_value(attribute_type_id, saved_value, display_text, display_order) VALUES (18, '16y', '16 Years Old', 6);
INSERT INTO allowed_value(attribute_type_id, saved_value, display_text, display_order) VALUES (18, '65y', '65 Years Old', 7);

INSERT INTO allowed_value(attribute_type_id, saved_value, display_text) VALUES (19, 'foreign_vaccine', 'Foreign Vaccine');
INSERT INTO allowed_value(attribute_type_id, saved_value, display_text) VALUES (19, 'never_active', 'Never Active');
INSERT INTO allowed_value(attribute_type_id, saved_value, display_text) VALUES (19, 'non_vaccine', 'Non Vaccine');
INSERT INTO allowed_value(attribute_type_id, saved_value, display_text) VALUES (19, 'uspecified', 'Unspecified');
INSERT INTO allowed_value(attribute_type_id, saved_value, display_text) VALUES (19, 'vaccine', 'Vaccine');

INSERT INTO allowed_value(attribute_type_id, saved_value, display_text) VALUES (23, 'N', 'Not Expected');
INSERT INTO allowed_value(attribute_type_id, saved_value, display_text) VALUES (23, 'E', 'Expected');
INSERT INTO allowed_value(attribute_type_id, saved_value, display_text) VALUES (23, 'R', 'Recommended');
INSERT INTO allowed_value(attribute_type_id, saved_value, display_text) VALUES (23, 'O', 'Optional');

INSERT INTO allowed_value(attribute_type_id, saved_value, display_text) VALUES (24, 'T', 'Test');
INSERT INTO allowed_value(attribute_type_id, saved_value, display_text) VALUES (24, 'P', 'Template');

INSERT INTO allowed_value(attribute_type_id, saved_value, display_text) VALUES (26, 'D', 'Development');
INSERT INTO allowed_value(attribute_type_id, saved_value, display_text) VALUES (26, 'T', 'Test');
INSERT INTO allowed_value(attribute_type_id, saved_value, display_text) VALUES (26, 'P', 'Production');

