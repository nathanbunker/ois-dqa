
-- Tables and data

CREATE TABLE dqa_user_account
(
  username           VARCHAR(30) NOT NULL PRIMARY KEY,
  password           VARCHAR(50) NOT NULL,
  account_type       VARCHAR(10) NOT NULL, -- Admin, Submitter
  org_id             INTEGER DEFAULT 0,
  email              VARCHAR(120) NOT NULL
);

INSERT INTO dqa_user_account (username, password, account_type, org_id, email) VALUES ('dqa_admin', 'changeme', 'Admin', 1, 'email@gmail.com');

CREATE TABLE dqa_organization
(
  org_id             INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  org_label          VARCHAR(60) NOT NULL,
  org_parent_id      INTEGER,
  primary_profile_id INTEGER,
  org_local_code     VARCHAR(60)
);

INSERT INTO dqa_organization (org_id, org_label, org_parent_id, primary_profile_id) VALUES (1, 'IIS', 1, 1);

CREATE TABLE dqa_submitter_profile
(
  profile_id         INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  profile_code       VARCHAR(50) NOT NULL,
  profile_label      VARCHAR(50) NOT NULL,
  profile_status     VARCHAR(10) DEFAULT 'Setup' NOT NULL, -- Setup, Test, Prod, Hold, Clos, Template
  org_id             INTEGER NOT NULL,
  data_format        VARCHAR(5) DEFAULT 'HL7v2' NOT NULL, -- HL7v2
  transfer_priority  VARCHAR(7) DEFAULT 'Normal' NOT NULL, -- Normal, High, Highest, Low, Lowest
  access_key         VARCHAR(50),
  template_id        INTEGER DEFAULT 1
);

INSERT INTO dqa_submitter_profile (profile_id, profile_code, profile_label, profile_status, org_id, data_format, transfer_priority, access_key, template_id) VALUES (1, 'MASTER:HL7', 'HL7', 'Template', 1, 'HL7v2', 'Normal', 'hl7', 0);
INSERT INTO dqa_submitter_profile (profile_id, profile_code, profile_label, profile_status, org_id, data_format, transfer_priority, access_key, template_id) VALUES (101, 'TEST:HL7', 'Test HL7', 'Test HL7', 1, 'HL7v2', 'Normal', 'test', 0);
INSERT INTO dqa_submitter_profile (profile_id, profile_code, profile_label, profile_status, org_id, data_format, transfer_priority, access_key, template_id) VALUES (251, 'TEMPLATE:CDC Vacc Admin', 'CDC Vacc Admin Template', 'Template', 1, 'HL7v2', 'Normal', 'template', 1);
INSERT INTO dqa_submitter_profile (profile_id, profile_code, profile_label, profile_status, org_id, data_format, transfer_priority, access_key, template_id) VALUES (252, 'TEMPLATE:CDC Vacc Inventory', 'CDC Vacc Inventory Template', 'Template', 1, 'HL7v2', 'Normal', 'template', 2);
INSERT INTO dqa_submitter_profile (profile_id, profile_code, profile_label, profile_status, org_id, data_format, transfer_priority, access_key, template_id) VALUES (253, 'TEMPLATE:ImmTrac Vacc Admin', 'ImmTrac Vacc Admin Template', 'Template', 1, 'HL7v2', 'Normal', 'template', 3);
INSERT INTO dqa_submitter_profile (profile_id, profile_code, profile_label, profile_status, org_id, data_format, transfer_priority, access_key, template_id) VALUES (254, 'TEMPLATE:ImmTrac Vacc Inventory', 'ImmTrac Vacc Inventory Template', 'Template', 1, 'HL7v2', 'Normal', 'template', 4);
INSERT INTO dqa_submitter_profile (profile_id, profile_code, profile_label, profile_status, org_id, data_format, transfer_priority, access_key, template_id) VALUES (255, 'TEMPLATE:MCIR Vacc Admin', 'MCIR Vacc Admin Template', 'Template', 1, 'HL7v2', 'Normal', 'template', 5);
INSERT INTO dqa_submitter_profile (profile_id, profile_code, profile_label, profile_status, org_id, data_format, transfer_priority, access_key, template_id) VALUES (256, 'TEMPLATE:MCIR Vacc Inventory', 'MCIR Vacc Inventory Template', 'Template', 1, 'HL7v2', 'Normal', 'template', 6);

-- Insert a row just before 1200 to force all new profile id's higher in MySQL
INSERT INTO dqa_submitter_profile 
  (profile_id, profile_code, profile_label, profile_status, org_id, data_format, transfer_priority, access_key)
VALUES
  (1199, 'TEST:Placeholder', 'Placeholder', 'Closed', 1, 'HL7v2', 'Normal', 'placeholder');


CREATE TABLE dqa_potential_issue
(
  issue_id            INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  target_object       VARCHAR(30) NOT NULL,
  target_field        VARCHAR(50) NOT NULL,
  issue_type          VARCHAR(50) NOT NULL,
  field_value         VARCHAR(50),
  default_action_code VARCHAR(1) NOT NULL,
  change_priority     VARCHAR(7) NOT NULL, -- Must, Should, Can, May, Blocked
  report_denominator  VARCHAR(30) NOT NULL,
  issue_description   VARCHAR(4000),
  table_id            INTEGER
);

INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (2, 'General', 'authorization', 'exception', '', 'E', 'Blocked', 'Message Count', 'Submitter is not authorized to submit messages', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (3, 'General', 'configuration', 'exception', '', 'E', 'Blocked', 'Message Count', 'Profile is not configured properly, unable to accept messages', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (4, 'General', 'parse', 'exception', '', 'E', 'Blocked', 'Message Count', 'Message can not be parsed', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (5, 'General', 'processing', 'exception', '', 'E', 'Blocked', 'Message Count', 'General unexpected exception while processing message', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (463, 'HL7', 'segment', 'is unrecognized', '', 'W', 'May', 'Message Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (464, 'HL7', 'segment', 'is invalid', '', 'W', 'May', 'Message Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (452, 'HL7', 'segments', 'out of order', '', 'W', 'May', 'Message Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (415, 'HL7 MSH', 'accept ack type', 'is deprecated', '', 'W', 'May', 'Message Count', '', 40);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (417, 'HL7 MSH', 'accept ack type', 'is ignored', '', 'S', 'May', 'Message Count', '', 40);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (416, 'HL7 MSH', 'accept ack type', 'is invalid', '', 'W', 'May', 'Message Count', '', 40);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (6, 'HL7 MSH', 'accept ack type', 'is missing', '', 'A', 'May', 'Message Count', '', 40);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (7, 'HL7 MSH', 'accept ack type', 'is unrecognized', '', 'W', 'May', 'Message Count', '', 40);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (8, 'HL7 MSH', 'accept ack type', 'is valued as', 'always', 'A', 'May', 'Message Count', '', 40);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (9, 'HL7 MSH', 'accept ack type', 'is valued as', 'never', 'A', 'May', 'Message Count', '', 40);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (10, 'HL7 MSH', 'accept ack type', 'is valued as', 'only on errors', 'A', 'May', 'Message Count', '', 40);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (431, 'HL7 MSH', 'alt character set', 'is deprecated', '', 'W', 'May', 'Message Count', '', 36);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (432, 'HL7 MSH', 'alt character set', 'is ignored', '', 'S', 'May', 'Message Count', '', 36);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (433, 'HL7 MSH', 'alt character set', 'is invalid', '', 'W', 'May', 'Message Count', '', 36);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (434, 'HL7 MSH', 'alt character set', 'is missing', '', 'A', 'May', 'Message Count', '', 36);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (435, 'HL7 MSH', 'alt character set', 'is unrecognized', '', 'W', 'May', 'Message Count', '', 36);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (418, 'HL7 MSH', 'app ack type', 'is deprecated', '', 'W', 'May', 'Message Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (420, 'HL7 MSH', 'app ack type', 'is ignored', '', 'S', 'May', 'Message Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (419, 'HL7 MSH', 'app ack type', 'is invalid', '', 'W', 'May', 'Message Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (410, 'HL7 MSH', 'app ack type', 'is missing', '', 'A', 'May', 'Message Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (411, 'HL7 MSH', 'app ack type', 'is unrecognized', '', 'W', 'May', 'Message Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (412, 'HL7 MSH', 'app ack type', 'is valued as', 'always', 'A', 'May', 'Message Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (413, 'HL7 MSH', 'app ack type', 'is valued as', 'never', 'A', 'May', 'Message Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (414, 'HL7 MSH', 'app ack type', 'is valued as', 'only on errors', 'A', 'May', 'Message Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (426, 'HL7 MSH', 'character set', 'is deprecated', '', 'W', 'May', 'Message Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (427, 'HL7 MSH', 'character set', 'is ignored', '', 'S', 'May', 'Message Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (428, 'HL7 MSH', 'character set', 'is invalid', '', 'W', 'May', 'Message Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (429, 'HL7 MSH', 'character set', 'is missing', '', 'A', 'May', 'Message Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (430, 'HL7 MSH', 'character set', 'is unrecognized', '', 'W', 'May', 'Message Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (421, 'HL7 MSH', 'country code', 'is deprecated', '', 'W', 'May', 'Message Count', '', 35);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (422, 'HL7 MSH', 'country code', 'is ignored', '', 'S', 'May', 'Message Count', '', 35);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (423, 'HL7 MSH', 'country code', 'is invalid', '', 'W', 'May', 'Message Count', '', 35);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (424, 'HL7 MSH', 'country code', 'is missing', '', 'A', 'May', 'Message Count', '', 35);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (425, 'HL7 MSH', 'country code', 'is unrecognized', '', 'W', 'May', 'Message Count', '', 35);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (11, 'HL7 MSH', 'encoding character', 'is invalid', '', 'E', 'Blocked', 'Message Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (12, 'HL7 MSH', 'encoding character', 'is missing', '', 'E', 'Blocked', 'Message Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (13, 'HL7 MSH', 'encoding character', 'is non-standard', '', 'E', 'May', 'Message Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (14, 'HL7 MSH', 'message control id', 'is missing', '', 'W', 'May', 'Message Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (15, 'HL7 MSH', 'message date', 'is in future', '', 'E', 'May', 'Message Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (16, 'HL7 MSH', 'message date', 'is invalid', '', 'E', 'May', 'Message Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (17, 'HL7 MSH', 'message date', 'is missing', '', 'E', 'May', 'Message Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (436, 'HL7 MSH', 'message profile id', 'is deprecated', '', 'W', 'May', 'Message Count', '', 37);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (437, 'HL7 MSH', 'message profile id', 'is ignored', '', 'S', 'May', 'Message Count', '', 37);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (438, 'HL7 MSH', 'message profile id', 'is invalid', '', 'W', 'May', 'Message Count', '', 37);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (439, 'HL7 MSH', 'message profile id', 'is missing', '', 'A', 'May', 'Message Count', '', 37);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (440, 'HL7 MSH', 'message profile id', 'is unrecognized', '', 'W', 'May', 'Message Count', '', 37);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (391, 'HL7 MSH', 'message structure', 'is missing', '', 'W', 'May', 'Message Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (392, 'HL7 MSH', 'message structure', 'is unrecognized', '', 'W', 'May', 'Message Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (18, 'HL7 MSH', 'message trigger', 'is missing', '', 'W', 'May', 'Message Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (19, 'HL7 MSH', 'message trigger', 'is unrecognized', '', 'E', 'May', 'Message Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (20, 'HL7 MSH', 'message type', 'is missing', '', 'W', 'May', 'Message Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (21, 'HL7 MSH', 'message type', 'is unrecognized', '', 'E', 'Blocked', 'Message Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (22, 'HL7 MSH', 'message type', 'is unsupported', '', 'E', 'Blocked', 'Message Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (403, 'HL7 MSH', 'processing id', 'is deprecated', '', 'W', 'May', 'Message Count', '', 39);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (404, 'HL7 MSH', 'processing id', 'is ignored', '', 'S', 'May', 'Message Count', '', 39);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (402, 'HL7 MSH', 'processing id', 'is invalid', '', 'W', 'May', 'Message Count', '', 39);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (23, 'HL7 MSH', 'processing id', 'is missing', '', 'A', 'May', 'Message Count', '', 39);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (401, 'HL7 MSH', 'processing id', 'is unrecognized', '', 'W', 'May', 'Message Count', '', 39);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (24, 'HL7 MSH', 'processing id', 'is valued as', 'debug', 'A', 'May', 'Message Count', '', 39);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (25, 'HL7 MSH', 'processing id', 'is valued as', 'production', 'A', 'May', 'Message Count', '', 39);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (26, 'HL7 MSH', 'processing id', 'is valued as', 'training', 'A', 'May', 'Message Count', '', 39);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (29, 'HL7 MSH', 'receiving application', 'is invalid', '', 'W', 'May', 'Message Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (30, 'HL7 MSH', 'receiving application', 'is missing', '', 'A', 'May', 'Message Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (31, 'HL7 MSH', 'receiving facility', 'is invalid', '', 'W', 'May', 'Message Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (32, 'HL7 MSH', 'receiving facility', 'is missing', '', 'A', 'May', 'Message Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (33, 'HL7 MSH', 'segment', 'is missing', '', 'E', 'Blocked', 'Message Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (34, 'HL7 MSH', 'sending application', 'is invalid', '', 'W', 'May', 'Message Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (35, 'HL7 MSH', 'sending application', 'is missing', '', 'A', 'May', 'Message Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (36, 'HL7 MSH', 'sending facility', 'is invalid', '', 'A', 'May', 'Message Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (37, 'HL7 MSH', 'sending facility', 'is missing', '', 'W', 'May', 'Message Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (38, 'HL7 MSH', 'version', 'is missing', '', 'A', 'May', 'Message Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (39, 'HL7 MSH', 'version', 'is unrecognized', '', 'W', 'May', 'Message Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (40, 'HL7 MSH', 'version', 'is valued as', '2.3.1', 'A', 'May', 'Message Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (41, 'HL7 MSH', 'version', 'is valued as', '2.4', 'A', 'May', 'Message Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (42, 'HL7 MSH', 'version', 'is valued as', '2.5', 'A', 'May', 'Message Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (43, 'HL7 NK1', 'segment', 'is missing', '', 'W', 'Should', 'NextOfKin Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (44, 'HL7 NK1', 'segment', 'is repeated', '', 'A', 'May', 'NextOfKin Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (368, 'HL7 NK1', 'set id', 'is missing', '', 'A', 'May', 'NextOfKin Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (45, 'HL7 OBX', 'segment', 'is missing', '', 'A', 'May', 'Observation Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (46, 'HL7 ORC', 'segment', 'is missing', '', 'A', 'May', 'Vaccination Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (47, 'HL7 ORC', 'segment', 'is repeated', '', 'E', 'May', 'Message Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (48, 'HL7 PD1', 'segment', 'is missing', '', 'A', 'May', 'Message Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (49, 'HL7 PID', 'segment', 'is missing', '', 'E', 'Blocked', 'Message Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (50, 'HL7 PID', 'segment', 'is repeated', '', 'E', 'May', 'Message Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (51, 'HL7 PV1', 'segment', 'is missing', '', 'A', 'May', 'Message Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (400, 'HL7 PV1', 'segment', 'is repeated', '', 'E', 'May', 'Message Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (390, 'HL7 RXA', 'admin sub id counter', 'is missing', '', 'A', 'May', 'Vaccination Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (389, 'HL7 RXA', 'give sub id', 'is missing', '', 'A', 'May', 'Vaccination Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (52, 'HL7 RXA', 'segment', 'is missing', '', 'S', 'Can', 'Message Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (53, 'HL7 RXA', 'segment', 'is repeated', '', 'A', 'Can', 'Message Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (54, 'HL7 RXR', 'segment', 'is missing', '', 'A', 'May', 'Vaccination Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (55, 'HL7 RXR', 'segment', 'is repeated', '', 'A', 'May', 'Vaccination Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (56, 'Next-of-kin', 'address', 'is different from patient address', '', 'A', 'May', 'NextOfKin Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (57, 'Next-of-kin', 'address', 'is missing', '', 'A', 'May', 'NextOfKin Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (58, 'Next-of-kin', 'address city', 'is invalid', '', 'W', 'May', 'NextOfKin Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (59, 'Next-of-kin', 'address city', 'is missing', '', 'A', 'May', 'NextOfKin Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (60, 'Next-of-kin', 'address country', 'is deprecated', '', 'W', 'May', 'NextOfKin Count', '', 2);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (61, 'Next-of-kin', 'address country', 'is ignored', '', 'S', 'May', 'NextOfKin Count', '', 2);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (62, 'Next-of-kin', 'address country', 'is invalid', '', 'W', 'May', 'NextOfKin Count', '', 2);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (63, 'Next-of-kin', 'address country', 'is missing', '', 'A', 'May', 'NextOfKin Count', '', 2);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (64, 'Next-of-kin', 'address country', 'is unrecognized', '', 'W', 'May', 'NextOfKin Count', '', 2);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (65, 'Next-of-kin', 'address county', 'is deprecated', '', 'W', 'May', 'NextOfKin Count', '', 3);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (66, 'Next-of-kin', 'address county', 'is ignored', '', 'S', 'May', 'NextOfKin Count', '', 3);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (67, 'Next-of-kin', 'address county', 'is invalid', '', 'W', 'May', 'NextOfKin Count', '', 3);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (68, 'Next-of-kin', 'address county', 'is missing', '', 'A', 'May', 'NextOfKin Count', '', 3);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (69, 'Next-of-kin', 'address county', 'is unrecognized', '', 'A', 'May', 'NextOfKin Count', '', 3);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (70, 'Next-of-kin', 'address state', 'is deprecated', '', 'W', 'May', 'NextOfKin Count', '', 4);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (71, 'Next-of-kin', 'address state', 'is ignored', '', 'S', 'May', 'NextOfKin Count', '', 4);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (72, 'Next-of-kin', 'address state', 'is invalid', '', 'W', 'May', 'NextOfKin Count', '', 4);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (73, 'Next-of-kin', 'address state', 'is missing', '', 'A', 'May', 'NextOfKin Count', '', 4);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (74, 'Next-of-kin', 'address state', 'is unrecognized', '', 'W', 'May', 'NextOfKin Count', '', 4);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (75, 'Next-of-kin', 'address street', 'is missing', '', 'A', 'May', 'NextOfKin Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (76, 'Next-of-kin', 'address street2', 'is missing', '', 'A', 'May', 'NextOfKin Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (395, 'Next-of-kin', 'address type', 'is deprecated', '', 'W', 'May', 'NextOfKin Count', '', 5);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (396, 'Next-of-kin', 'address type', 'is ignored', '', 'S', 'May', 'NextOfKin Count', '', 5);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (397, 'Next-of-kin', 'address type', 'is invalid', '', 'W', 'May', 'NextOfKin Count', '', 5);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (398, 'Next-of-kin', 'address type', 'is missing', '', 'A', 'May', 'NextOfKin Count', '', 5);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (399, 'Next-of-kin', 'address type', 'is unrecognized', '', 'W', 'May', 'NextOfKin Count', '', 5);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (77, 'Next-of-kin', 'address zip', 'is invalid', '', 'W', 'May', 'NextOfKin Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (78, 'Next-of-kin', 'address zip', 'is missing', '', 'A', 'May', 'NextOfKin Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (79, 'Next-of-kin', 'name', 'is missing', '', 'W', 'Can', 'NextOfKin Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (80, 'Next-of-kin', 'name first', 'is missing', '', 'S', 'Can', 'NextOfKin Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (81, 'Next-of-kin', 'name last', 'is missing', '', 'S', 'Can', 'NextOfKin Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (82, 'Next-of-kin', 'phone number', 'is incomplete', '', 'W', 'May', 'NextOfKin Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (83, 'Next-of-kin', 'phone number', 'is invalid', '', 'W', 'May', 'NextOfKin Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (84, 'Next-of-kin', 'phone number', 'is missing', '', 'A', 'Can', 'NextOfKin Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (85, 'Next-of-kin', 'relationship', 'is deprecated', '', 'W', 'May', 'NextOfKin Count', '', 22);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (86, 'Next-of-kin', 'relationship', 'is ignored', '', 'S', 'May', 'NextOfKin Count', '', 22);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (87, 'Next-of-kin', 'relationship', 'is invalid', '', 'W', 'May', 'NextOfKin Count', '', 22);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (88, 'Next-of-kin', 'relationship', 'is missing', '', 'S', 'Should', 'NextOfKin Count', '', 22);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (89, 'Next-of-kin', 'relationship', 'is not responsible party', '', 'A', 'May', 'NextOfKin Count', '', 22);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (90, 'Next-of-kin', 'relationship', 'is unrecognized', '', 'W', 'May', 'NextOfKin Count', '', 22);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (91, 'Next-of-kin', 'SSN', 'is missing', '', 'A', 'May', 'NextOfKin Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (470, 'Observation', 'value type', 'is deprecated', '', 'W', 'May', 'Vaccination Count', '', 45);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (471, 'Observation', 'value type', 'is ignored', '', 'S', 'May', 'Vaccination Count', '', 45);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (472, 'Observation', 'value type', 'is invalid', '', 'W', 'May', 'Vaccination Count', '', 45);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (473, 'Observation', 'value type', 'is missing', '', 'W', 'May', 'Vaccination Count', '', 45);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (474, 'Observation', 'value type', 'is unrecognized', '', 'W', 'May', 'Vaccination Count', '', 45);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (475, 'Observation', 'observation identifier code', 'is deprecated', '', 'W', 'May', 'Vaccination Count', '', 43);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (476, 'Observation', 'observation identifier code', 'is ignored', '', 'S', 'May', 'Vaccination Count', '', 43);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (477, 'Observation', 'observation identifier code', 'is invalid', '', 'W', 'May', 'Vaccination Count', '', 43);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (478, 'Observation', 'observation identifier code', 'is missing', '', 'W', 'May', 'Vaccination Count', '', 43);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (479, 'Observation', 'observation identifier code', 'is unrecognized', '', 'W', 'May', 'Vaccination Count', '', 43);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (480, 'Observation', 'observation value', 'is missing', '', 'W', 'May', 'Vaccination Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (481, 'Observation', 'date time of observation', 'is missing', '', 'W', 'May', 'Vaccination Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (482, 'Observation', 'date time of observation', 'is invalid', '', 'W', 'May', 'Vaccination Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (92, 'Patient', 'address', 'is missing', '', 'E', 'Should', 'Patient Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (93, 'Patient', 'address city', 'is invalid', '', 'E', 'May', 'Patient Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (94, 'Patient', 'address city', 'is missing', '', 'E', 'May', 'Patient Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (95, 'Patient', 'address country', 'is deprecated', '', 'W', 'May', 'Patient Count', '', 2);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (96, 'Patient', 'address country', 'is ignored', '', 'S', 'May', 'Patient Count', '', 2);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (97, 'Patient', 'address country', 'is invalid', '', 'W', 'May', 'Patient Count', '', 2);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (98, 'Patient', 'address country', 'is missing', '', 'A', 'May', 'Patient Count', '', 2);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (99, 'Patient', 'address country', 'is unrecognized', '', 'W', 'May', 'Patient Count', '', 2);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (100, 'Patient', 'address county', 'is deprecated', '', 'W', 'May', 'Patient Count', '', 3);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (101, 'Patient', 'address county', 'is ignored', '', 'S', 'May', 'Patient Count', '', 3);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (102, 'Patient', 'address county', 'is invalid', '', 'W', 'May', 'Patient Count', '', 3);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (103, 'Patient', 'address county', 'is missing', '', 'A', 'May', 'Patient Count', '', 3);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (104, 'Patient', 'address county', 'is unrecognized', '', 'A', 'May', 'Patient Count', '', 3);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (105, 'Patient', 'address state', 'is deprecated', '', 'W', 'May', 'Patient Count', '', 4);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (106, 'Patient', 'address state', 'is ignored', '', 'S', 'May', 'Patient Count', '', 4);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (107, 'Patient', 'address state', 'is invalid', '', 'E', 'May', 'Patient Count', '', 4);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (108, 'Patient', 'address state', 'is missing', '', 'E', 'May', 'Patient Count', '', 4);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (109, 'Patient', 'address state', 'is unrecognized', '', 'E', 'May', 'Patient Count', '', 4);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (110, 'Patient', 'address street', 'is missing', '', 'E', 'May', 'Patient Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (111, 'Patient', 'address street2', 'is missing', '', 'A', 'Can', 'Patient Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (451, 'Patient', 'address type', 'is missing', '', 'A', 'Can', 'Patient Count', '', 5);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (112, 'Patient', 'address zip', 'is invalid', '', 'W', 'May', 'Patient Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (113, 'Patient', 'address zip', 'is missing', '', 'E', 'May', 'Patient Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (114, 'Patient', 'alias', 'is missing', '', 'A', 'May', 'Patient Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (115, 'Patient', 'birth date', 'is after submission', '', 'E', 'Blocked', 'Patient Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (116, 'Patient', 'birth date', 'is in future', '', 'E', 'Blocked', 'Patient Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (117, 'Patient', 'birth date', 'is invalid', '', 'E', 'Blocked', 'Patient Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (118, 'Patient', 'birth date', 'is missing', '', 'E', 'Blocked', 'Patient Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (119, 'Patient', 'birth date', 'is underage', '', 'A', 'May', 'Patient Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (120, 'Patient', 'birth date', 'is very long ago', '', 'E', 'Blocked', 'Patient Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (121, 'Patient', 'birth indicator', 'is invalid', '', 'W', 'May', 'Patient Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (122, 'Patient', 'birth indicator', 'is missing', '', 'A', 'Should', 'Patient Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (123, 'Patient', 'birth order', 'is invalid', '', 'W', 'May', 'Patient Count', '', 7);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (124, 'Patient', 'birth order', 'is missing', '', 'A', 'Should', 'Patient Count', '', 7);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (125, 'Patient', 'birth order', 'is missing and multiple birth indicated', '', 'W', 'May', 'Patient Count', '', 7);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (126, 'Patient', 'birth place', 'is missing', '', 'A', 'May', 'Patient Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (127, 'Patient', 'birth registry id', 'is invalid', '', 'W', 'May', 'Patient Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (128, 'Patient', 'birth registry id', 'is missing', '', 'A', 'May', 'Patient Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (374, 'Patient', 'class', 'is deprecated', '', 'W', 'May', 'Patient Count', '', 38);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (375, 'Patient', 'class', 'is ignored', '', 'S', 'May', 'Patient Count', '', 38);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (376, 'Patient', 'class', 'is invalid', '', 'A', 'May', 'Patient Count', '', 38);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (377, 'Patient', 'class', 'is missing', '', 'W', 'May', 'Patient Count', '', 38);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (378, 'Patient', 'class', 'is unrecognized', '', 'A', 'May', 'Patient Count', '', 38);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (129, 'Patient', 'death date', 'is before birth', '', 'E', 'Blocked', 'Patient Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (130, 'Patient', 'death date', 'is in future', '', 'E', 'Blocked', 'Patient Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (131, 'Patient', 'death date', 'is invalid', '', 'E', 'Blocked', 'Patient Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (132, 'Patient', 'death date', 'is missing', '', 'W', 'May', 'Patient Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (133, 'Patient', 'death indicator', 'is inconsistent', '', 'W', 'May', 'Patient Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (134, 'Patient', 'death indicator', 'is missing', '', 'A', 'May', 'Patient Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (135, 'Patient', 'ethnicity', 'is deprecated', '', 'W', 'May', 'Patient Count', '', 14);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (136, 'Patient', 'ethnicity', 'is ignored', '', 'S', 'May', 'Patient Count', '', 14);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (137, 'Patient', 'ethnicity', 'is invalid', '', 'W', 'May', 'Patient Count', '', 14);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (138, 'Patient', 'ethnicity', 'is missing', '', 'A', 'Should', 'Patient Count', '', 14);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (139, 'Patient', 'ethnicity', 'is unrecognized', '', 'W', 'May', 'Patient Count', '', 14);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (143, 'Patient', 'gender', 'is deprecated', '', 'W', 'May', 'Patient Count', '', 19);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (144, 'Patient', 'gender', 'is ignored', '', 'S', 'May', 'Patient Count', '', 19);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (145, 'Patient', 'gender', 'is invalid', '', 'E', 'May', 'Patient Count', '', 19);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (146, 'Patient', 'gender', 'is missing', '', 'E', 'Blocked', 'Patient Count', '', 19);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (147, 'Patient', 'gender', 'is unrecognized', '', 'E', 'May', 'Patient Count', '', 19);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (148, 'Patient', 'guardian address', 'is missing', '', 'A', 'May', 'Patient Underage Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (149, 'Patient', 'guardian address city', 'is missing', '', 'A', 'May', 'Patient Underage Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (150, 'Patient', 'guardian address state', 'is missing', '', 'A', 'May', 'Patient Underage Count', '', 4);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (151, 'Patient', 'guardian address street', 'is missing', '', 'A', 'May', 'Patient Underage Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (152, 'Patient', 'guardian address zip', 'is missing', '', 'A', 'May', 'Patient Underage Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (155, 'Patient', 'guardian name', 'is missing', '', 'W', 'May', 'Patient Underage Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (156, 'Patient', 'guardian name', 'is same as underage patient', '', 'A', 'May', 'Patient Underage Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (153, 'Patient', 'guardian name first', 'is missing', '', 'W', 'May', 'Patient Underage Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (154, 'Patient', 'guardian name last', 'is missing', '', 'W', 'May', 'Patient Underage Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (157, 'Patient', 'guardian responsible party', 'is missing', '', 'W', 'May', 'Patient Underage Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (158, 'Patient', 'guardian phone', 'is missing', '', 'A', 'May', 'Patient Underage Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (159, 'Patient', 'guardian relationship', 'is missing', '', 'W', 'May', 'Patient Underage Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (160, 'Patient', 'immunization registry status', 'is deprecated', '', 'W', 'May', 'Patient Count', '', 24);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (161, 'Patient', 'immunization registry status', 'is ignored', '', 'S', 'May', 'Patient Count', '', 24);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (162, 'Patient', 'immunization registry status', 'is invalid', '', 'W', 'May', 'Patient Count', '', 24);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (163, 'Patient', 'immunization registry status', 'is missing', '', 'A', 'May', 'Patient Count', '', 24);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (164, 'Patient', 'immunization registry status', 'is unrecognized', '', 'W', 'May', 'Patient Count', '', 24);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (167, 'Patient', 'Medicaid number', 'is invalid', '', 'W', 'Can', 'Patient Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (168, 'Patient', 'Medicaid number', 'is missing', '', 'A', 'Can', 'Patient Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (169, 'Patient', 'middle name', 'is missing', '', 'A', 'May', 'Patient Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (170, 'Patient', 'middle name', 'may be initial', '', 'A', 'May', 'Patient Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (171, 'Patient', 'mother''s maiden name', 'is missing', '', 'A', 'Should', 'Patient Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (172, 'Patient', 'name', 'may be temporary newborn name', '', 'A', 'Should', 'Patient Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (173, 'Patient', 'name', 'may be test name', '', 'A', 'Should', 'Patient Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (140, 'Patient', 'name first', 'is invalid', '', 'E', 'May', 'Patient Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (141, 'Patient', 'name first', 'is missing', '', 'E', 'May', 'Patient Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (142, 'Patient', 'name first', 'may include middle initial', '', 'A', 'May', 'Patient Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (165, 'Patient', 'name last', 'is invalid', '', 'E', 'May', 'Patient Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (166, 'Patient', 'name last', 'is missing', '', 'E', 'May', 'Patient Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (405, 'Patient', 'name type code', 'is deprecated', '', 'W', 'May', 'Patient Count', '', 21);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (406, 'Patient', 'name type code', 'is ignored', '', 'S', 'May', 'Patient Count', '', 21);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (407, 'Patient', 'name type code', 'is invalid', '', 'W', 'May', 'Patient Count', '', 21);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (408, 'Patient', 'name type code', 'is missing', '', 'A', 'May', 'Patient Count', '', 21);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (409, 'Patient', 'name type code', 'is unrecognized', '', 'W', 'May', 'Patient Count', '', 21);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (174, 'Patient', 'phone', 'is incomplete', '', 'W', 'May', 'Patient Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (175, 'Patient', 'phone', 'is invalid', '', 'W', 'May', 'Patient Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (176, 'Patient', 'phone', 'is missing', '', 'A', 'May', 'Patient Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (453, 'Patient', 'phone tel use code', 'is deprecated', '', 'W', 'May', 'Patient Count', '', 41);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (454, 'Patient', 'phone tel use code', 'is ignored', '', 'S', 'May', 'Patient Count', '', 41);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (455, 'Patient', 'phone tel use code', 'is invalid', '', 'W', 'May', 'Patient Count', '', 41);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (456, 'Patient', 'phone tel use code', 'is missing', '', 'A', 'May', 'Patient Count', '', 41);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (457, 'Patient', 'phone tel use code', 'is unrecognized', '', 'W', 'May', 'Patient Count', '', 41);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (458, 'Patient', 'phone tel equip code', 'is deprecated', '', 'W', 'May', 'Patient Count', '', 42);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (459, 'Patient', 'phone tel equip code', 'is ignored', '', 'S', 'May', 'Patient Count', '', 42);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (460, 'Patient', 'phone tel equip code', 'is invalid', '', 'W', 'May', 'Patient Count', '', 42);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (461, 'Patient', 'phone tel equip code', 'is missing', '', 'A', 'May', 'Patient Count', '', 42);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (462, 'Patient', 'phone tel equip code', 'is unrecognized', '', 'W', 'May', 'Patient Count', '', 42);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (177, 'Patient', 'primary facility id', 'is deprecated', '', 'W', 'May', 'Patient Count', '', 13);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (178, 'Patient', 'primary facility id', 'is ignored', '', 'S', 'May', 'Patient Count', '', 13);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (179, 'Patient', 'primary facility id', 'is invalid', '', 'E', 'May', 'Patient Count', '', 13);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (180, 'Patient', 'primary facility id', 'is missing', '', 'A', 'May', 'Patient Count', '', 13);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (181, 'Patient', 'primary facility id', 'is unrecognized', '', 'W', 'May', 'Patient Count', '', 13);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (182, 'Patient', 'primary facility name', 'is missing', '', 'A', 'May', 'Patient Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (183, 'Patient', 'primary language', 'is deprecated', '', 'A', 'May', 'Patient Count', '', 20);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (184, 'Patient', 'primary language', 'is ignored', '', 'S', 'May', 'Patient Count', '', 20);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (185, 'Patient', 'primary language', 'is invalid', '', 'W', 'May', 'Patient Count', '', 20);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (186, 'Patient', 'primary language', 'is missing', '', 'A', 'May', 'Patient Count', '', 20);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (187, 'Patient', 'primary language', 'is unrecognized', '', 'W', 'May', 'Patient Count', '', 20);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (188, 'Patient', 'primary physician id', 'is deprecated', '', 'W', 'May', 'Patient Count', '', 23);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (189, 'Patient', 'primary physician id', 'is ignored', '', 'S', 'May', 'Patient Count', '', 23);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (190, 'Patient', 'primary physician id', 'is invalid', '', 'W', 'May', 'Patient Count', '', 23);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (191, 'Patient', 'primary physician id', 'is missing', '', 'A', 'May', 'Patient Count', '', 23);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (192, 'Patient', 'primary physician id', 'is unrecognized', '', 'W', 'May', 'Patient Count', '', 23);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (193, 'Patient', 'primary physician name', 'is missing', '', 'A', 'May', 'Patient Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (194, 'Patient', 'protection indicator', 'is deprecated', '', 'W', 'May', 'Patient Count', '', 16);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (195, 'Patient', 'protection indicator', 'is ignored', '', 'S', 'May', 'Patient Count', '', 16);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (196, 'Patient', 'protection indicator', 'is invalid', '', 'W', 'May', 'Patient Count', '', 16);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (197, 'Patient', 'protection indicator', 'is missing', '', 'A', 'May', 'Patient Count', '', 16);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (198, 'Patient', 'protection indicator', 'is unrecognized', '', 'W', 'May', 'Patient Count', '', 16);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (199, 'Patient', 'protection indicator', 'is valued as', 'no', 'A', 'May', 'Patient Count', '', 16);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (200, 'Patient', 'protection indicator', 'is valued as', 'yes', 'W', 'May', 'Patient Count', '', 16);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (201, 'Patient', 'publicity code', 'is deprecated', '', 'W', 'May', 'Patient Count', '', 17);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (202, 'Patient', 'publicity code', 'is ignored', '', 'S', 'May', 'Patient Count', '', 17);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (203, 'Patient', 'publicity code', 'is invalid', '', 'W', 'May', 'Patient Count', '', 17);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (204, 'Patient', 'publicity code', 'is missing', '', 'A', 'May', 'Patient Count', '', 17);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (205, 'Patient', 'publicity code', 'is unrecognized', '', 'W', 'May', 'Patient Count', '', 17);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (206, 'Patient', 'race', 'is deprecated', '', 'W', 'May', 'Patient Count', '', 18);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (207, 'Patient', 'race', 'is ignored', '', 'S', 'May', 'Patient Count', '', 18);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (208, 'Patient', 'race', 'is invalid', '', 'W', 'May', 'Patient Count', '', 18);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (209, 'Patient', 'race', 'is missing', '', 'A', 'May', 'Patient Count', '', 18);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (210, 'Patient', 'race', 'is unrecognized', '', 'W', 'May', 'Patient Count', '', 18);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (211, 'Patient', 'registry id', 'is missing', '', 'A', 'May', 'Patient Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (212, 'Patient', 'registry id', 'is unrecognized', '', 'A', 'May', 'Patient Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (213, 'Patient', 'registry status', 'is deprecated', '', 'W', 'May', 'Patient Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (214, 'Patient', 'registry status', 'is ignored', '', 'S', 'May', 'Patient Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (215, 'Patient', 'registry status', 'is invalid', '', 'W', 'May', 'Patient Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (216, 'Patient', 'registry status', 'is missing', '', 'A', 'May', 'Patient Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (217, 'Patient', 'registry status', 'is unrecognized', '', 'W', 'May', 'Patient Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (218, 'Patient', 'SSN', 'is invalid', '', 'W', 'May', 'Patient Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (219, 'Patient', 'SSN', 'is missing', '', 'A', 'May', 'Patient Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (220, 'Patient', 'submitter id', 'is missing', '', 'E', 'Blocked', 'Patient Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (393, 'Patient', 'submitter id authority', 'is missing', '', 'W', 'May', 'Patient Count', '', 11);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (394, 'Patient', 'submitter id type code', 'is missing', '', 'W', 'May', 'Patient Count', '', 12);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (221, 'Patient', 'VFC effective date', 'is before birth', '', 'E', 'May', 'Patient Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (222, 'Patient', 'VFC effective date', 'is in future', '', 'E', 'May', 'Patient Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (223, 'Patient', 'VFC effective date', 'is invalid', '', 'E', 'May', 'Patient Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (224, 'Patient', 'VFC effective date', 'is missing', '', 'A', 'May', 'Patient Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (225, 'Patient', 'VFC status', 'is deprecated', '', 'W', 'May', 'Patient Count', '', 10);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (226, 'Patient', 'VFC status', 'is ignored', '', 'S', 'May', 'Patient Count', '', 10);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (227, 'Patient', 'VFC status', 'is invalid', '', 'W', 'May', 'Patient Count', '', 10);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (228, 'Patient', 'VFC status', 'is missing', '', 'A', 'May', 'Patient Count', '', 10);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (229, 'Patient', 'VFC status', 'is unrecognized', '', 'W', 'May', 'Patient Count', '', 10);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (230, 'Patient', 'WIC id', 'is invalid', '', 'A', 'May', 'Patient Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (231, 'Patient', 'WIC id', 'is missing', '', 'A', 'May', 'Patient Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (232, 'Vaccination', 'action code', 'is deprecated', '', 'W', 'May', 'Vaccination Count', '', 25);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (233, 'Vaccination', 'action code', 'is ignored', '', 'S', 'May', 'Vaccination Count', '', 25);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (234, 'Vaccination', 'action code', 'is invalid', '', 'E', 'May', 'Vaccination Count', '', 25);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (235, 'Vaccination', 'action code', 'is missing', '', 'A', 'May', 'Vaccination Count', '', 25);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (236, 'Vaccination', 'action code', 'is unrecognized', '', 'W', 'May', 'Vaccination Count', '', 25);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (237, 'Vaccination', 'action code', 'is valued as', 'add', 'A', 'May', 'Vaccination Count', '', 25);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (238, 'Vaccination', 'action code', 'is valued as', 'add or update', 'A', 'May', 'Vaccination Count', '', 25);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (239, 'Vaccination', 'action code', 'is valued as', 'delete', 'A', 'May', 'Vaccination Count', '', 25);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (240, 'Vaccination', 'action code', 'is valued as', 'update', 'A', 'May', 'Vaccination Count', '', 25);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (241, 'Vaccination', 'admin code', 'is deprecated', '', 'W', 'May', 'Vaccination Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (242, 'Vaccination', 'admin code', 'is ignored', '', 'S', 'May', 'Vaccination Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (243, 'Vaccination', 'admin code', 'is invalid', '', 'E', 'May', 'Vaccination Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (244, 'Vaccination', 'admin code', 'is missing', '', 'E', 'May', 'Vaccination Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (245, 'Vaccination', 'admin code', 'is not specific', '', 'W', 'May', 'Vaccination Admin Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (246, 'Vaccination', 'admin code', 'is not vaccine', '', 'W', 'May', 'Vaccination Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (247, 'Vaccination', 'admin code', 'is unrecognized', '', 'E', 'May', 'Vaccination Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (248, 'Vaccination', 'admin code', 'is valued as', 'not administered', 'S', 'May', 'Vaccination Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (249, 'Vaccination', 'admin code', 'is valued as', 'unknown', 'W', 'May', 'Vaccination Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (483, 'Vaccination', 'admin code table', 'is missing', '', 'W', 'May', 'Vaccination Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (484, 'Vaccination', 'admin code table', 'is invalid', '', 'W', 'May', 'Vaccination Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (250, 'Vaccination', 'admin code', 'may be variation of previously reported codes', '', 'W', 'May', 'Vaccination Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (251, 'Vaccination', 'admin date', 'is after lot expiration date', '', 'W', 'May', 'Vaccination Admin Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (252, 'Vaccination', 'admin date', 'is after message submitted', '', 'E', 'May', 'Vaccination Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (253, 'Vaccination', 'admin date', 'is after patient death date', '', 'E', 'May', 'Vaccination Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (254, 'Vaccination', 'admin date', 'is after system entry date', '', 'E', 'May', 'Vaccination Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (255, 'Vaccination', 'admin date', 'is before birth', '', 'E', 'May', 'Vaccination Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (256, 'Vaccination', 'admin date', 'is before or after expected vaccine usage range', '', 'W', 'May', 'Vaccination Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (257, 'Vaccination', 'admin date', 'is before or after licensed vaccine range', '', 'E', 'May', 'Vaccination Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (258, 'Vaccination', 'admin date', 'is before or after when expected for patient age', '', 'W', 'May', 'Vaccination Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (259, 'Vaccination', 'admin date', 'is before or after when valid for patient age', '', 'E', 'May', 'Vaccination Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (260, 'Vaccination', 'admin date', 'is invalid', '', 'E', 'May', 'Vaccination Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (261, 'Vaccination', 'admin date', 'is missing', '', 'E', 'May', 'Vaccination Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (262, 'Vaccination', 'admin date', 'is on 15th day of month', '', 'A', 'May', 'Vaccination Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (263, 'Vaccination', 'admin date', 'is on first day of month', '', 'A', 'May', 'Vaccination Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (264, 'Vaccination', 'admin date', 'is on last day of month', '', 'A', 'May', 'Vaccination Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (265, 'Vaccination', 'admin date', 'is reported late', '', 'W', 'May', 'Vaccination Admin Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (266, 'Vaccination', 'admin date end', 'is different from start date', '', 'W', 'May', 'Vaccination Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (267, 'Vaccination', 'admin date end', 'is missing', '', 'A', 'May', 'Vaccination Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (268, 'Vaccination', 'administered amount', 'is invalid', '', 'W', 'May', 'Vaccination Admin Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (269, 'Vaccination', 'administered amount', 'is missing', '', 'A', 'May', 'Vaccination Admin Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (270, 'Vaccination', 'administered amount', 'is valued as', 'zero', 'A', 'May', 'Vaccination Admin Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (271, 'Vaccination', 'administered amount', 'is valued as', 'unknown', 'A', 'May', 'Vaccination Admin Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (447, 'Vaccination', 'administered unit', 'is deprecated', '', 'W', 'May', 'Vaccination Admin Count', '', 6);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (448, 'Vaccination', 'administered unit', 'is ignored', '', 'S', 'May', 'Vaccination Admin Count', '', 6);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (449, 'Vaccination', 'administered unit', 'is invalid', '', 'W', 'May', 'Vaccination Admin Count', '', 6);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (272, 'Vaccination', 'administered unit', 'is missing', '', 'A', 'May', 'Vaccination Admin Count', '', 6);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (450, 'Vaccination', 'administered unit', 'is unrecognized', '', 'W', 'May', 'Vaccination Admin Count', '', 6);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (273, 'Vaccination', 'body route', 'is deprecated', '', 'W', 'May', 'Vaccination Admin Count', '', 8);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (274, 'Vaccination', 'body route', 'is ignored', '', 'S', 'May', 'Vaccination Admin Count', '', 8);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (275, 'Vaccination', 'body route', 'is invalid', '', 'W', 'May', 'Vaccination Admin Count', '', 8);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (276, 'Vaccination', 'body route', 'is invalid for vaccine indicated', '', 'W', 'May', 'Vaccination Admin Count', '', 8);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (277, 'Vaccination', 'body route', 'is missing', '', 'A', 'May', 'Vaccination Admin Count', '', 8);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (278, 'Vaccination', 'body route', 'is unrecognized', '', 'W', 'May', 'Vaccination Admin Count', '', 8);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (279, 'Vaccination', 'body site', 'is deprecated', '', 'W', 'May', 'Vaccination Admin Count', '', 9);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (280, 'Vaccination', 'body site', 'is ignored', '', 'S', 'May', 'Vaccination Admin Count', '', 9);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (281, 'Vaccination', 'body site', 'is invalid', '', 'W', 'May', 'Vaccination Admin Count', '', 9);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (282, 'Vaccination', 'body site', 'is invalid for vaccine indicated', '', 'W', 'May', 'Vaccination Admin Count', '', 9);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (283, 'Vaccination', 'body site', 'is missing', '', 'A', 'May', 'Vaccination Admin Count', '', 9);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (284, 'Vaccination', 'body site', 'is unrecognized', '', 'W', 'May', 'Vaccination Admin Count', '', 9);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (285, 'Vaccination', 'completion status', 'is deprecated', '', 'W', 'May', 'Vaccination Count', '', 26);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (286, 'Vaccination', 'completion status', 'is ignored', '', 'S', 'May', 'Vaccination Count', '', 26);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (287, 'Vaccination', 'completion status', 'is invalid', '', 'E', 'May', 'Vaccination Count', '', 26);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (288, 'Vaccination', 'completion status', 'is missing', '', 'A', 'May', 'Vaccination Count', '', 26);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (289, 'Vaccination', 'completion status', 'is unrecognized', '', 'E', 'May', 'Vaccination Count', '', 26);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (290, 'Vaccination', 'completion status', 'is valued as', 'completed', 'A', 'May', 'Vaccination Count', '', 26);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (291, 'Vaccination', 'completion status', 'is valued as', 'not administered', 'S', 'May', 'Vaccination Count', '', 26);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (292, 'Vaccination', 'completion status', 'is valued as', 'partially administered', 'S', 'May', 'Vaccination Count', '', 26);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (293, 'Vaccination', 'completion status', 'is valued as', 'refused', 'S', 'May', 'Vaccination Count', '', 26);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (294, 'Vaccination', 'confidentiality code', 'is deprecated', '', 'W', 'May', 'Vaccination Count', '', 27);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (295, 'Vaccination', 'confidentiality code', 'is ignored', '', 'S', 'May', 'Vaccination Count', '', 27);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (296, 'Vaccination', 'confidentiality code', 'is invalid', '', 'W', 'May', 'Vaccination Count', '', 27);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (297, 'Vaccination', 'confidentiality code', 'is missing', '', 'A', 'May', 'Vaccination Count', '', 27);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (298, 'Vaccination', 'confidentiality code', 'is unrecognized', '', 'W', 'May', 'Vaccination Count', '', 27);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (299, 'Vaccination', 'confidentiality code', 'is valued as', 'restricted', 'W', 'May', 'Vaccination Count', '', 27);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (300, 'Vaccination', 'CPT code', 'is deprecated', '', 'W', 'May', 'Vaccination Count', '', 28);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (301, 'Vaccination', 'CPT code', 'is ignored', '', 'S', 'May', 'Vaccination Count', '', 28);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (302, 'Vaccination', 'CPT code', 'is invalid', '', 'W', 'May', 'Vaccination Count', '', 28);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (303, 'Vaccination', 'CPT code', 'is missing', '', 'A', 'May', 'Vaccination Count', '', 28);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (304, 'Vaccination', 'CPT code', 'is unrecognized', '', 'W', 'May', 'Vaccination Count', '', 28);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (305, 'Vaccination', 'CVX code', 'is deprecated', '', 'W', 'May', 'Vaccination Count', '', 29);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (306, 'Vaccination', 'CVX code', 'is ignored', '', 'S', 'May', 'Vaccination Count', '', 29);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (307, 'Vaccination', 'CVX code', 'is invalid', '', 'W', 'May', 'Vaccination Count', '', 29);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (308, 'Vaccination', 'CVX code', 'is missing', '', 'A', 'May', 'Vaccination Count', '', 29);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (309, 'Vaccination', 'CVX code', 'is unrecognized', '', 'W', 'May', 'Vaccination Count', '', 29);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (310, 'Vaccination', 'CVX code and CPT code', 'are inconsistent', '', 'W', 'May', 'Vaccination Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (311, 'Vaccination', 'facility id', 'is deprecated', '', 'W', 'May', 'Vaccination Admin Count', '', 13);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (312, 'Vaccination', 'facility id', 'is ignored', '', 'S', 'May', 'Vaccination Admin Count', '', 13);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (313, 'Vaccination', 'facility id', 'is invalid', '', 'A', 'May', 'Vaccination Admin Count', '', 13);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (314, 'Vaccination', 'facility id', 'is missing', '', 'A', 'May', 'Vaccination Admin Count', '', 13);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (315, 'Vaccination', 'facility id', 'is unrecognized', '', 'A', 'May', 'Vaccination Admin Count', '', 13);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (316, 'Vaccination', 'facility name', 'is missing', '', 'A', 'May', 'Vaccination Admin Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (379, 'Vaccination', 'filler order number', 'is deprecated', '', 'W', 'May', 'Vaccination Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (380, 'Vaccination', 'filler order number', 'is ignored', '', 'S', 'May', 'Vaccination Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (381, 'Vaccination', 'filler order number', 'is invalid', '', 'W', 'May', 'Vaccination Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (382, 'Vaccination', 'filler order number', 'is missing', '', 'A', 'May', 'Vaccination Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (383, 'Vaccination', 'filler order number', 'is unrecognized', '', 'W', 'May', 'Vaccination Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (465, 'Vaccination', 'financial eligibility code', 'is deprecated', '', 'W', 'May', 'Vaccination Admin Count', '', 10);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (466, 'Vaccination', 'financial eligibility code', 'is ignored', '', 'S', 'May', 'Vaccination Admin Count', '', 10);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (467, 'Vaccination', 'financial eligibility code', 'is invalid', '', 'E', 'May', 'Vaccination Admin Count', '', 10);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (468, 'Vaccination', 'financial eligibility code', 'is missing', '', 'E', 'May', 'Vaccination Admin Count', '', 10);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (469, 'Vaccination', 'financial eligibility code', 'is unrecognized', '', 'E', 'May', 'Vaccination Admin Count', '', 10);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (317, 'Vaccination', 'given by', 'is deprecated', '', 'W', 'May', 'Vaccination Admin Count', '', 23);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (318, 'Vaccination', 'given by', 'is ignored', '', 'S', 'May', 'Vaccination Admin Count', '', 23);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (319, 'Vaccination', 'given by', 'is invalid', '', 'W', 'May', 'Vaccination Admin Count', '', 23);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (320, 'Vaccination', 'given by', 'is missing', '', 'A', 'May', 'Vaccination Admin Count', '', 23);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (321, 'Vaccination', 'given by', 'is unrecognized', '', 'W', 'May', 'Vaccination Admin Count', '', 23);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (322, 'Vaccination', 'id', 'is missing', '', 'A', 'May', 'Vaccination Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (323, 'Vaccination', 'id of receiver', 'is missing', '', 'A', 'May', 'Vaccination Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (324, 'Vaccination', 'id of receiver', 'is unrecognized', '', 'W', 'May', 'Vaccination Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (325, 'Vaccination', 'id of sender', 'is missing', '', 'A', 'May', 'Vaccination Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (326, 'Vaccination', 'id of sender', 'is unrecognized', '', 'W', 'May', 'Vaccination Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (327, 'Vaccination', 'information source', 'is administered but appears to historical', '', 'W', 'May', 'Vaccination Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (328, 'Vaccination', 'information source', 'is deprecated', '', 'W', 'May', 'Vaccination Count', '', 30);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (329, 'Vaccination', 'information source', 'is historical but appears to be administered', '', 'W', 'May', 'Vaccination Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (330, 'Vaccination', 'information source', 'is ignored', '', 'S', 'May', 'Vaccination Count', '', 30);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (331, 'Vaccination', 'information source', 'is invalid', '', 'E', 'May', 'Vaccination Count', '', 30);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (332, 'Vaccination', 'information source', 'is missing', '', 'E', 'May', 'Vaccination Count', '', 30);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (333, 'Vaccination', 'information source', 'is unrecognized', '', 'E', 'May', 'Vaccination Count', '', 30);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (334, 'Vaccination', 'information source', 'is valued as', 'administered', 'A', 'May', 'Vaccination Count', '', 30);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (335, 'Vaccination', 'information source', 'is valued as', 'historical', 'A', 'May', 'Vaccination Count', '', 30);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (336, 'Vaccination', 'lot expiration date', 'is invalid', '', 'W', 'May', 'Vaccination Admin Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (337, 'Vaccination', 'lot expiration date', 'is missing', '', 'A', 'May', 'Vaccination Admin Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (338, 'Vaccination', 'lot number', 'is invalid', '', 'W', 'May', 'Vaccination Admin Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (339, 'Vaccination', 'lot number', 'is missing', '', 'W', 'May', 'Vaccination Admin Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (340, 'Vaccination', 'manufacturer code', 'is deprecated', '', 'W', 'May', 'Vaccination Admin Count', '', 31);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (341, 'Vaccination', 'manufacturer code', 'is ignored', '', 'S', 'May', 'Vaccination Admin Count', '', 31);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (342, 'Vaccination', 'manufacturer code', 'is invalid', '', 'W', 'May', 'Vaccination Admin Count', '', 31);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (343, 'Vaccination', 'manufacturer code', 'is missing', '', 'W', 'May', 'Vaccination Admin Count', '', 31);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (344, 'Vaccination', 'manufacturer code', 'is unrecognized', '', 'W', 'May', 'Vaccination Admin Count', '', 31);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (373, 'Vaccination', 'order control code', 'is deprecated', '', 'W', 'May', 'Vaccination Count', '', 34);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (369, 'Vaccination', 'order control code', 'is ignored', '', 'S', 'May', 'Vaccination Count', '', 34);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (370, 'Vaccination', 'order control code', 'is invalid', '', 'W', 'May', 'Vaccination Count', '', 34);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (371, 'Vaccination', 'order control code', 'is missing', '', 'A', 'May', 'Vaccination Count', '', 34);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (372, 'Vaccination', 'order control code', 'is unrecognized', '', 'W', 'May', 'Vaccination Count', '', 34);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (442, 'Vaccination', 'order facility id', 'is deprecated', '', 'A', 'May', 'Vaccination Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (443, 'Vaccination', 'order facility id', 'is ignored', '', 'S', 'May', 'Vaccination Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (444, 'Vaccination', 'order facility id', 'is invalid', '', 'W', 'May', 'Vaccination Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (445, 'Vaccination', 'order facility id', 'is missing', '', 'A', 'May', 'Vaccination Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (446, 'Vaccination', 'order facility id', 'is unrecognized', '', 'W', 'May', 'Vaccination Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (441, 'Vaccination', 'order facility name', 'is missing', '', 'A', 'May', 'Vaccination Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (345, 'Vaccination', 'ordered by', 'is deprecated', '', 'W', 'May', 'Vaccination Admin Count', '', 23);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (346, 'Vaccination', 'ordered by', 'is ignored', '', 'S', 'May', 'Vaccination Admin Count', '', 23);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (347, 'Vaccination', 'ordered by', 'is invalid', '', 'W', 'May', 'Vaccination Admin Count', '', 23);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (348, 'Vaccination', 'ordered by', 'is missing', '', 'A', 'May', 'Vaccination Admin Count', '', 23);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (349, 'Vaccination', 'ordered by', 'is unrecognized', '', 'W', 'May', 'Vaccination Admin Count', '', 23);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (384, 'Vaccination', 'placer order number', 'is deprecated', '', 'W', 'May', 'Vaccination Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (385, 'Vaccination', 'placer order number', 'is ignored', '', 'S', 'May', 'Vaccination Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (386, 'Vaccination', 'placer order number', 'is invalid', '', 'W', 'May', 'Vaccination Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (387, 'Vaccination', 'placer order number', 'is missing', '', 'A', 'May', 'Vaccination Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (388, 'Vaccination', 'placer order number', 'is unrecognized', '', 'W', 'May', 'Vaccination Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (350, 'Vaccination', 'product', 'is deprecated', '', 'W', 'May', 'Vaccination Admin Count', '', 33);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (351, 'Vaccination', 'product', 'is invalid', '', 'E', 'May', 'Vaccination Admin Count', '', 33);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (352, 'Vaccination', 'product', 'is missing', '', 'A', 'May', 'Vaccination Admin Count', '', 33);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (353, 'Vaccination', 'product', 'is unrecognized', '', 'W', 'May', 'Vaccination Admin Count', '', 33);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (354, 'Vaccination', 'recorded by', 'is deprecated', '', 'W', 'May', 'Vaccination Admin Count', '', 23);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (355, 'Vaccination', 'recorded by', 'is ignored', '', 'S', 'May', 'Vaccination Admin Count', '', 23);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (356, 'Vaccination', 'recorded by', 'is invalid', '', 'W', 'May', 'Vaccination Admin Count', '', 23);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (357, 'Vaccination', 'recorded by', 'is missing', '', 'A', 'May', 'Vaccination Admin Count', '', 23);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (358, 'Vaccination', 'recorded by', 'is unrecognized', '', 'W', 'May', 'Vaccination Admin Count', '', 23);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (359, 'Vaccination', 'refusal reason', 'conflicts completion status', '', 'E', 'May', 'Vaccination Admin Count', '', 32);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (360, 'Vaccination', 'refusal reason', 'is deprecated', '', 'W', 'May', 'Vaccination Admin Count', '', 32);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (361, 'Vaccination', 'refusal reason', 'is ignored', '', 'S', 'May', 'Vaccination Admin Count', '', 32);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (362, 'Vaccination', 'refusal reason', 'is invalid', '', 'E', 'May', 'Vaccination Admin Count', '', 32);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (363, 'Vaccination', 'refusal reason', 'is missing', '', 'A', 'May', 'Vaccination Admin Count', '', 32);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (364, 'Vaccination', 'refusal reason', 'is unrecognized', '', 'A', 'May', 'Vaccination Admin Count', '', 32);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (365, 'Vaccination', 'system entry time', 'is in future', '', 'E', 'May', 'Vaccination Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (366, 'Vaccination', 'system entry time', 'is invalid', '', 'E', 'May', 'Vaccination Count', '', NULL);
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id) VALUES (367, 'Vaccination', 'system entry time', 'is missing', '', 'A', 'May', 'Vaccination Count', '', NULL);

CREATE TABLE dqa_potential_issue_status
(
  potential_issue_status_id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  issue_id           INTEGER NOT NULL,
  profile_id         INTEGER NOT NULL,
  action_code        VARCHAR(1) NOT NULL,
  expect_min         INTEGER DEFAULT 0 NOT NULL,
  expect_max         INTEGER DEFAULT 100 NOT NULL
);

INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(2, 251, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(3, 251, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(4, 251, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(5, 251, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(463, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(464, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(452, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(415, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(417, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(416, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(6, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(7, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(8, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(9, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(10, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(431, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(432, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(433, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(434, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(435, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(418, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(420, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(419, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(410, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(411, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(412, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(413, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(414, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(426, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(427, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(428, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(429, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(430, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(421, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(422, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(423, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(424, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(425, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(11, 251, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(12, 251, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(13, 251, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(14, 251, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(15, 251, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(16, 251, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(17, 251, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(436, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(437, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(438, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(439, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(440, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(391, 251, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(392, 251, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(18, 251, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(19, 251, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(20, 251, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(21, 251, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(22, 251, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(403, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(404, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(402, 251, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(23, 251, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(401, 251, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(24, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(25, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(26, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(29, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(30, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(31, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(32, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(33, 251, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(34, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(35, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(36, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(37, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(38, 251, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(39, 251, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(40, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(41, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(42, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(43, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(44, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(368, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(45, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(46, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(47, 251, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(48, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(49, 251, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(50, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(51, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(400, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(390, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(389, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(52, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(53, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(54, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(55, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(56, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(57, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(58, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(59, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(60, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(61, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(62, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(63, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(64, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(65, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(66, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(67, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(68, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(69, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(70, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(71, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(72, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(73, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(74, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(75, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(76, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(395, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(396, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(397, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(398, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(399, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(77, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(78, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(79, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(80, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(81, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(82, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(83, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(84, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(85, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(86, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(87, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(88, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(89, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(90, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(91, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(470, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(471, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(472, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(473, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(474, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(475, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(476, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(477, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(478, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(479, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(480, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(481, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(482, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(92, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(93, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(94, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(95, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(96, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(97, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(98, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(99, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(100, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(101, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(102, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(103, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(104, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(105, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(106, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(107, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(108, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(109, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(110, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(111, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(451, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(112, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(113, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(114, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(115, 251, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(116, 251, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(117, 251, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(118, 251, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(119, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(120, 251, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(121, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(122, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(123, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(124, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(125, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(126, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(127, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(128, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(374, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(375, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(376, 251, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(377, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(378, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(129, 251, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(130, 251, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(131, 251, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(132, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(133, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(134, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(135, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(136, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(137, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(138, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(139, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(143, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(144, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(145, 251, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(146, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(147, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(148, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(149, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(150, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(151, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(152, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(155, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(156, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(153, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(154, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(157, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(158, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(159, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(160, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(161, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(162, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(163, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(164, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(167, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(168, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(169, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(170, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(171, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(172, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(173, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(140, 251, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(141, 251, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(142, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(165, 251, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(166, 251, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(405, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(406, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(407, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(408, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(409, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(174, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(175, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(176, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(453, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(454, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(455, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(456, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(457, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(458, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(459, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(460, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(461, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(462, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(177, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(178, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(179, 251, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(180, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(181, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(182, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(183, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(184, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(185, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(186, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(187, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(188, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(189, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(190, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(191, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(192, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(193, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(194, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(195, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(196, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(197, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(198, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(199, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(200, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(201, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(202, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(203, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(204, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(205, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(206, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(207, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(208, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(209, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(210, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(211, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(212, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(213, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(214, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(215, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(216, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(217, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(218, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(219, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(220, 251, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(393, 251, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(394, 251, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(221, 251, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(222, 251, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(223, 251, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(224, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(225, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(226, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(227, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(228, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(229, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(230, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(231, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(232, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(233, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(234, 251, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(235, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(236, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(237, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(238, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(239, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(240, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(241, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(242, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(243, 251, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(244, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(245, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(246, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(247, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(248, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(249, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(483, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(484, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(250, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(251, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(252, 251, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(253, 251, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(254, 251, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(255, 251, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(256, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(257, 251, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(258, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(259, 251, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(260, 251, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(261, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(262, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(263, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(264, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(265, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(266, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(267, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(268, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(269, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(270, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(271, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(447, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(448, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(449, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(272, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(450, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(273, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(274, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(275, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(276, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(277, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(278, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(279, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(280, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(281, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(282, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(283, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(284, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(285, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(286, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(287, 251, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(288, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(289, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(290, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(291, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(292, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(293, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(294, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(295, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(296, 251, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(297, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(298, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(299, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(300, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(301, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(302, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(303, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(304, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(305, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(306, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(307, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(308, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(309, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(310, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(311, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(312, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(313, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(314, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(315, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(316, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(379, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(380, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(381, 251, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(382, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(383, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(465, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(466, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(467, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(468, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(469, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(317, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(318, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(319, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(320, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(321, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(322, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(323, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(324, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(325, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(326, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(327, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(328, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(329, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(330, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(331, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(332, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(333, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(334, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(335, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(336, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(337, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(338, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(339, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(340, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(341, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(342, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(343, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(344, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(373, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(369, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(370, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(371, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(372, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(442, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(443, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(444, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(445, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(446, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(441, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(345, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(346, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(347, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(348, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(349, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(384, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(385, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(386, 251, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(387, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(388, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(350, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(351, 251, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(352, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(353, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(354, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(355, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(356, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(357, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(358, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(359, 251, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(360, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(361, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(362, 251, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(363, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(364, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(365, 251, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(366, 251, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(367, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(2, 252, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(3, 252, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(4, 252, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(5, 252, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(463, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(464, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(452, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(415, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(417, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(416, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(6, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(7, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(8, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(9, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(10, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(431, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(432, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(433, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(434, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(435, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(418, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(420, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(419, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(410, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(411, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(412, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(413, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(414, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(426, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(427, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(428, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(429, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(430, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(421, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(422, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(423, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(424, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(425, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(11, 252, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(12, 252, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(13, 252, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(14, 252, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(15, 252, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(16, 252, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(17, 252, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(436, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(437, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(438, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(439, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(440, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(391, 252, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(392, 252, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(18, 252, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(19, 252, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(20, 252, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(21, 252, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(22, 252, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(403, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(404, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(402, 252, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(23, 252, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(401, 252, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(24, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(25, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(26, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(29, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(30, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(31, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(32, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(33, 252, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(34, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(35, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(36, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(37, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(38, 252, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(39, 252, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(40, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(41, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(42, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(43, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(44, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(368, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(45, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(46, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(47, 252, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(48, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(49, 252, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(50, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(51, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(400, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(390, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(389, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(52, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(53, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(54, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(55, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(56, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(57, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(58, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(59, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(60, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(61, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(62, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(63, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(64, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(65, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(66, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(67, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(68, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(69, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(70, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(71, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(72, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(73, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(74, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(75, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(76, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(395, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(396, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(397, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(398, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(399, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(77, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(78, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(79, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(80, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(81, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(82, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(83, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(84, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(85, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(86, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(87, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(88, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(89, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(90, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(91, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(470, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(471, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(472, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(473, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(474, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(475, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(476, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(477, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(478, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(479, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(480, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(481, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(482, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(92, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(93, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(94, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(95, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(96, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(97, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(98, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(99, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(100, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(101, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(102, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(103, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(104, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(105, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(106, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(107, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(108, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(109, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(110, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(111, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(451, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(112, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(113, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(114, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(115, 252, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(116, 252, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(117, 252, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(118, 252, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(119, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(120, 252, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(121, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(122, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(123, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(124, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(125, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(126, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(127, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(128, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(374, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(375, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(376, 252, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(377, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(378, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(129, 252, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(130, 252, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(131, 252, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(132, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(133, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(134, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(135, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(136, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(137, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(138, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(139, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(143, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(144, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(145, 252, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(146, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(147, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(148, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(149, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(150, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(151, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(152, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(155, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(156, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(153, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(154, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(157, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(158, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(159, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(160, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(161, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(162, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(163, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(164, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(167, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(168, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(169, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(170, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(171, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(172, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(173, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(140, 252, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(141, 252, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(142, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(165, 252, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(166, 252, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(405, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(406, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(407, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(408, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(409, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(174, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(175, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(176, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(453, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(454, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(455, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(456, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(457, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(458, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(459, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(460, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(461, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(462, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(177, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(178, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(179, 252, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(180, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(181, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(182, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(183, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(184, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(185, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(186, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(187, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(188, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(189, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(190, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(191, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(192, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(193, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(194, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(195, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(196, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(197, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(198, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(199, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(200, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(201, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(202, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(203, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(204, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(205, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(206, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(207, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(208, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(209, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(210, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(211, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(212, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(213, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(214, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(215, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(216, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(217, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(218, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(219, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(220, 252, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(393, 252, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(394, 252, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(221, 252, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(222, 252, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(223, 252, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(224, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(225, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(226, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(227, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(228, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(229, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(230, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(231, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(232, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(233, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(234, 252, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(235, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(236, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(237, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(238, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(239, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(240, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(241, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(242, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(243, 252, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(244, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(245, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(246, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(247, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(248, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(249, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(483, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(484, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(250, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(251, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(252, 252, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(253, 252, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(254, 252, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(255, 252, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(256, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(257, 252, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(258, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(259, 252, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(260, 252, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(261, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(262, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(263, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(264, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(265, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(266, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(267, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(268, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(269, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(270, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(271, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(447, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(448, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(449, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(272, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(450, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(273, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(274, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(275, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(276, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(277, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(278, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(279, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(280, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(281, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(282, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(283, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(284, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(285, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(286, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(287, 252, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(288, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(289, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(290, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(291, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(292, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(293, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(294, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(295, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(296, 252, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(297, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(298, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(299, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(300, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(301, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(302, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(303, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(304, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(305, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(306, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(307, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(308, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(309, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(310, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(311, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(312, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(313, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(314, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(315, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(316, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(379, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(380, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(381, 252, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(382, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(383, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(465, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(466, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(467, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(468, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(469, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(317, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(318, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(319, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(320, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(321, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(322, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(323, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(324, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(325, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(326, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(327, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(328, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(329, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(330, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(331, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(332, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(333, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(334, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(335, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(336, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(337, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(338, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(339, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(340, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(341, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(342, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(343, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(344, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(373, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(369, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(370, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(371, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(372, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(442, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(443, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(444, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(445, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(446, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(441, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(345, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(346, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(347, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(348, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(349, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(384, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(385, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(386, 252, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(387, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(388, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(350, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(351, 252, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(352, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(353, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(354, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(355, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(356, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(357, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(358, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(359, 252, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(360, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(361, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(362, 252, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(363, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(364, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(365, 252, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(366, 252, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(367, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(2, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(3, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(4, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(5, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(463, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(464, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(452, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(415, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(417, 253, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(416, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(6, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(7, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(8, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(9, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(10, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(431, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(432, 253, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(433, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(434, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(435, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(418, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(420, 253, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(419, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(410, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(411, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(412, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(413, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(414, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(426, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(427, 253, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(428, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(429, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(430, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(421, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(422, 253, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(423, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(424, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(425, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(11, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(12, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(13, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(14, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(15, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(16, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(17, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(436, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(437, 253, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(438, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(439, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(440, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(391, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(392, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(18, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(19, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(20, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(21, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(22, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(403, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(404, 253, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(402, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(23, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(401, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(24, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(25, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(26, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(29, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(30, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(31, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(32, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(33, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(34, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(35, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(36, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(37, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(38, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(39, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(40, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(41, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(42, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(43, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(44, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(368, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(45, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(46, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(47, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(48, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(49, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(50, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(51, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(400, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(390, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(389, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(52, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(53, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(54, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(55, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(56, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(57, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(58, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(59, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(60, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(61, 253, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(62, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(63, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(64, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(65, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(66, 253, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(67, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(68, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(69, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(70, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(71, 253, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(72, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(73, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(74, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(75, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(76, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(395, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(396, 253, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(397, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(398, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(399, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(77, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(78, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(79, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(80, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(81, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(82, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(83, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(84, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(85, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(86, 253, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(87, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(88, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(89, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(90, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(91, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(470, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(471, 253, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(472, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(473, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(474, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(475, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(476, 253, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(477, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(478, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(479, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(480, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(481, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(482, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(92, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(93, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(94, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(95, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(96, 253, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(97, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(98, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(99, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(100, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(101, 253, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(102, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(103, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(104, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(105, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(106, 253, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(107, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(108, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(109, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(110, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(111, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(451, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(112, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(113, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(114, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(115, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(116, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(117, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(118, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(119, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(120, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(121, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(122, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(123, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(124, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(125, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(126, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(127, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(128, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(374, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(375, 253, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(376, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(377, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(378, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(129, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(130, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(131, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(132, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(133, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(134, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(135, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(136, 253, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(137, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(138, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(139, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(143, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(144, 253, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(145, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(146, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(147, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(148, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(149, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(150, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(151, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(152, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(155, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(156, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(153, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(154, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(157, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(158, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(159, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(160, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(161, 253, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(162, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(163, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(164, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(167, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(168, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(169, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(170, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(171, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(172, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(173, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(140, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(141, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(142, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(165, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(166, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(405, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(406, 253, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(407, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(408, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(409, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(174, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(175, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(176, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(453, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(454, 253, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(455, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(456, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(457, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(458, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(459, 253, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(460, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(461, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(462, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(177, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(178, 253, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(179, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(180, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(181, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(182, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(183, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(184, 253, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(185, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(186, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(187, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(188, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(189, 253, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(190, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(191, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(192, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(193, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(194, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(195, 253, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(196, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(197, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(198, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(199, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(200, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(201, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(202, 253, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(203, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(204, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(205, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(206, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(207, 253, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(208, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(209, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(210, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(211, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(212, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(213, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(214, 253, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(215, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(216, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(217, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(218, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(219, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(220, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(393, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(394, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(221, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(222, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(223, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(224, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(225, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(226, 253, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(227, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(228, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(229, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(230, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(231, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(232, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(233, 253, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(234, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(235, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(236, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(237, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(238, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(239, 253, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(240, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(241, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(242, 253, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(243, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(244, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(245, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(246, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(247, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(248, 253, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(249, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(483, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(484, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(250, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(251, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(252, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(253, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(254, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(255, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(256, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(257, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(258, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(259, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(260, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(261, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(262, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(263, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(264, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(265, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(266, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(267, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(268, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(269, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(270, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(271, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(447, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(448, 253, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(449, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(272, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(450, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(273, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(274, 253, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(275, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(276, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(277, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(278, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(279, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(280, 253, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(281, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(282, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(283, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(284, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(285, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(286, 253, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(287, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(288, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(289, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(290, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(291, 253, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(292, 253, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(293, 253, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(294, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(295, 253, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(296, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(297, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(298, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(299, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(300, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(301, 253, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(302, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(303, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(304, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(305, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(306, 253, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(307, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(308, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(309, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(310, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(311, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(312, 253, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(313, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(314, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(315, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(316, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(379, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(380, 253, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(381, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(382, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(383, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(465, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(466, 253, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(467, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(468, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(469, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(317, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(318, 253, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(319, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(320, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(321, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(322, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(323, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(324, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(325, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(326, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(327, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(328, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(329, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(330, 253, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(331, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(332, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(333, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(334, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(335, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(336, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(337, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(338, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(339, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(340, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(341, 253, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(342, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(343, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(344, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(373, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(369, 253, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(370, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(371, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(372, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(442, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(443, 253, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(444, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(445, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(446, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(441, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(345, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(346, 253, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(347, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(348, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(349, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(384, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(385, 253, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(386, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(387, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(388, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(350, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(351, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(352, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(353, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(354, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(355, 253, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(356, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(357, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(358, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(359, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(360, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(361, 253, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(362, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(363, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(364, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(365, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(366, 253, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(367, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(2, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(3, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(4, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(5, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(463, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(464, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(452, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(415, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(417, 254, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(416, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(6, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(7, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(8, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(9, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(10, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(431, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(432, 254, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(433, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(434, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(435, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(418, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(420, 254, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(419, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(410, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(411, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(412, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(413, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(414, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(426, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(427, 254, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(428, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(429, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(430, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(421, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(422, 254, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(423, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(424, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(425, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(11, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(12, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(13, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(14, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(15, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(16, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(17, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(436, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(437, 254, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(438, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(439, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(440, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(391, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(392, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(18, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(19, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(20, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(21, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(22, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(403, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(404, 254, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(402, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(23, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(401, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(24, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(25, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(26, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(29, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(30, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(31, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(32, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(33, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(34, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(35, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(36, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(37, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(38, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(39, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(40, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(41, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(42, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(43, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(44, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(368, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(45, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(46, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(47, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(48, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(49, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(50, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(51, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(400, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(390, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(389, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(52, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(53, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(54, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(55, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(56, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(57, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(58, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(59, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(60, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(61, 254, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(62, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(63, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(64, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(65, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(66, 254, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(67, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(68, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(69, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(70, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(71, 254, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(72, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(73, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(74, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(75, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(76, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(395, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(396, 254, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(397, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(398, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(399, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(77, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(78, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(79, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(80, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(81, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(82, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(83, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(84, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(85, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(86, 254, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(87, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(88, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(89, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(90, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(91, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(470, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(471, 254, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(472, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(473, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(474, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(475, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(476, 254, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(477, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(478, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(479, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(480, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(481, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(482, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(92, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(93, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(94, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(95, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(96, 254, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(97, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(98, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(99, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(100, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(101, 254, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(102, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(103, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(104, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(105, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(106, 254, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(107, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(108, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(109, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(110, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(111, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(451, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(112, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(113, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(114, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(115, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(116, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(117, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(118, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(119, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(120, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(121, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(122, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(123, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(124, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(125, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(126, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(127, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(128, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(374, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(375, 254, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(376, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(377, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(378, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(129, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(130, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(131, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(132, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(133, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(134, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(135, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(136, 254, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(137, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(138, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(139, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(143, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(144, 254, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(145, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(146, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(147, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(148, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(149, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(150, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(151, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(152, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(155, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(156, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(153, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(154, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(157, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(158, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(159, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(160, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(161, 254, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(162, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(163, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(164, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(167, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(168, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(169, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(170, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(171, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(172, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(173, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(140, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(141, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(142, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(165, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(166, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(405, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(406, 254, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(407, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(408, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(409, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(174, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(175, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(176, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(453, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(454, 254, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(455, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(456, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(457, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(458, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(459, 254, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(460, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(461, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(462, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(177, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(178, 254, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(179, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(180, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(181, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(182, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(183, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(184, 254, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(185, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(186, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(187, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(188, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(189, 254, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(190, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(191, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(192, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(193, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(194, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(195, 254, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(196, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(197, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(198, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(199, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(200, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(201, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(202, 254, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(203, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(204, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(205, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(206, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(207, 254, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(208, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(209, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(210, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(211, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(212, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(213, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(214, 254, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(215, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(216, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(217, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(218, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(219, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(220, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(393, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(394, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(221, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(222, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(223, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(224, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(225, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(226, 254, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(227, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(228, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(229, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(230, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(231, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(232, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(233, 254, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(234, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(235, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(236, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(237, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(238, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(239, 254, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(240, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(241, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(242, 254, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(243, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(244, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(245, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(246, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(247, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(248, 254, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(249, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(483, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(484, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(250, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(251, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(252, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(253, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(254, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(255, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(256, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(257, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(258, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(259, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(260, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(261, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(262, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(263, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(264, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(265, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(266, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(267, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(268, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(269, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(270, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(271, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(447, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(448, 254, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(449, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(272, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(450, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(273, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(274, 254, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(275, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(276, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(277, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(278, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(279, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(280, 254, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(281, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(282, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(283, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(284, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(285, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(286, 254, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(287, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(288, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(289, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(290, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(291, 254, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(292, 254, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(293, 254, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(294, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(295, 254, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(296, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(297, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(298, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(299, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(300, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(301, 254, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(302, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(303, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(304, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(305, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(306, 254, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(307, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(308, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(309, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(310, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(311, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(312, 254, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(313, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(314, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(315, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(316, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(379, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(380, 254, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(381, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(382, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(383, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(465, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(466, 254, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(467, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(468, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(469, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(317, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(318, 254, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(319, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(320, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(321, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(322, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(323, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(324, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(325, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(326, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(327, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(328, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(329, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(330, 254, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(331, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(332, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(333, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(334, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(335, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(336, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(337, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(338, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(339, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(340, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(341, 254, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(342, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(343, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(344, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(373, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(369, 254, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(370, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(371, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(372, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(442, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(443, 254, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(444, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(445, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(446, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(441, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(345, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(346, 254, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(347, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(348, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(349, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(384, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(385, 254, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(386, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(387, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(388, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(350, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(351, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(352, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(353, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(354, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(355, 254, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(356, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(357, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(358, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(359, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(360, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(361, 254, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(362, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(363, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(364, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(365, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(366, 254, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(367, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(2, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(3, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(4, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(5, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(463, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(464, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(452, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(415, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(417, 255, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(416, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(6, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(7, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(8, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(9, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(10, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(431, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(432, 255, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(433, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(434, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(435, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(418, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(420, 255, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(419, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(410, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(411, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(412, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(413, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(414, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(426, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(427, 255, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(428, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(429, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(430, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(421, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(422, 255, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(423, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(424, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(425, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(11, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(12, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(13, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(14, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(15, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(16, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(17, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(436, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(437, 255, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(438, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(439, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(440, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(391, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(392, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(18, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(19, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(20, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(21, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(22, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(403, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(404, 255, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(402, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(23, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(401, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(24, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(25, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(26, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(29, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(30, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(31, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(32, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(33, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(34, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(35, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(36, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(37, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(38, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(39, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(40, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(41, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(42, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(43, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(44, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(368, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(45, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(46, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(47, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(48, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(49, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(50, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(51, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(400, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(390, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(389, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(52, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(53, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(54, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(55, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(56, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(57, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(58, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(59, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(60, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(61, 255, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(62, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(63, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(64, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(65, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(66, 255, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(67, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(68, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(69, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(70, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(71, 255, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(72, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(73, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(74, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(75, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(76, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(395, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(396, 255, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(397, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(398, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(399, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(77, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(78, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(79, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(80, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(81, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(82, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(83, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(84, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(85, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(86, 255, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(87, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(88, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(89, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(90, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(91, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(470, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(471, 255, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(472, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(473, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(474, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(475, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(476, 255, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(477, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(478, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(479, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(480, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(481, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(482, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(92, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(93, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(94, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(95, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(96, 255, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(97, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(98, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(99, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(100, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(101, 255, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(102, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(103, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(104, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(105, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(106, 255, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(107, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(108, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(109, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(110, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(111, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(451, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(112, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(113, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(114, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(115, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(116, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(117, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(118, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(119, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(120, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(121, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(122, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(123, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(124, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(125, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(126, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(127, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(128, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(374, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(375, 255, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(376, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(377, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(378, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(129, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(130, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(131, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(132, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(133, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(134, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(135, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(136, 255, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(137, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(138, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(139, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(143, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(144, 255, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(145, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(146, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(147, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(148, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(149, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(150, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(151, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(152, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(155, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(156, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(153, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(154, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(157, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(158, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(159, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(160, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(161, 255, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(162, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(163, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(164, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(167, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(168, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(169, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(170, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(171, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(172, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(173, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(140, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(141, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(142, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(165, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(166, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(405, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(406, 255, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(407, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(408, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(409, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(174, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(175, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(176, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(453, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(454, 255, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(455, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(456, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(457, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(458, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(459, 255, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(460, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(461, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(462, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(177, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(178, 255, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(179, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(180, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(181, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(182, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(183, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(184, 255, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(185, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(186, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(187, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(188, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(189, 255, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(190, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(191, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(192, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(193, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(194, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(195, 255, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(196, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(197, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(198, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(199, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(200, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(201, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(202, 255, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(203, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(204, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(205, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(206, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(207, 255, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(208, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(209, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(210, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(211, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(212, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(213, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(214, 255, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(215, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(216, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(217, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(218, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(219, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(220, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(393, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(394, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(221, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(222, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(223, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(224, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(225, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(226, 255, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(227, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(228, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(229, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(230, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(231, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(232, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(233, 255, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(234, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(235, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(236, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(237, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(238, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(239, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(240, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(241, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(242, 255, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(243, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(244, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(245, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(246, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(247, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(248, 255, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(249, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(483, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(484, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(250, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(251, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(252, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(253, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(254, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(255, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(256, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(257, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(258, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(259, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(260, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(261, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(262, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(263, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(264, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(265, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(266, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(267, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(268, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(269, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(270, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(271, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(447, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(448, 255, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(449, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(272, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(450, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(273, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(274, 255, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(275, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(276, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(277, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(278, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(279, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(280, 255, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(281, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(282, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(283, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(284, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(285, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(286, 255, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(287, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(288, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(289, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(290, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(291, 255, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(292, 255, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(293, 255, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(294, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(295, 255, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(296, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(297, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(298, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(299, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(300, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(301, 255, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(302, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(303, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(304, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(305, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(306, 255, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(307, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(308, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(309, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(310, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(311, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(312, 255, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(313, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(314, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(315, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(316, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(379, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(380, 255, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(381, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(382, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(383, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(465, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(466, 255, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(467, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(468, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(469, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(317, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(318, 255, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(319, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(320, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(321, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(322, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(323, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(324, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(325, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(326, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(327, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(328, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(329, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(330, 255, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(331, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(332, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(333, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(334, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(335, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(336, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(337, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(338, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(339, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(340, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(341, 255, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(342, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(343, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(344, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(373, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(369, 255, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(370, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(371, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(372, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(442, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(443, 255, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(444, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(445, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(446, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(441, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(345, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(346, 255, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(347, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(348, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(349, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(384, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(385, 255, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(386, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(387, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(388, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(350, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(351, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(352, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(353, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(354, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(355, 255, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(356, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(357, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(358, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(359, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(360, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(361, 255, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(362, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(363, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(364, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(365, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(366, 255, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(367, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(2, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(3, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(4, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(5, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(463, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(464, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(452, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(415, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(417, 256, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(416, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(6, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(7, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(8, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(9, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(10, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(431, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(432, 256, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(433, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(434, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(435, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(418, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(420, 256, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(419, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(410, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(411, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(412, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(413, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(414, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(426, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(427, 256, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(428, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(429, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(430, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(421, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(422, 256, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(423, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(424, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(425, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(11, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(12, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(13, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(14, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(15, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(16, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(17, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(436, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(437, 256, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(438, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(439, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(440, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(391, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(392, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(18, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(19, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(20, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(21, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(22, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(403, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(404, 256, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(402, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(23, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(401, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(24, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(25, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(26, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(29, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(30, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(31, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(32, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(33, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(34, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(35, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(36, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(37, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(38, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(39, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(40, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(41, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(42, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(43, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(44, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(368, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(45, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(46, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(47, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(48, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(49, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(50, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(51, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(400, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(390, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(389, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(52, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(53, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(54, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(55, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(56, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(57, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(58, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(59, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(60, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(61, 256, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(62, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(63, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(64, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(65, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(66, 256, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(67, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(68, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(69, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(70, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(71, 256, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(72, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(73, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(74, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(75, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(76, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(395, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(396, 256, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(397, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(398, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(399, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(77, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(78, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(79, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(80, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(81, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(82, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(83, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(84, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(85, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(86, 256, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(87, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(88, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(89, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(90, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(91, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(470, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(471, 256, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(472, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(473, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(474, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(475, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(476, 256, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(477, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(478, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(479, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(480, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(481, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(482, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(92, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(93, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(94, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(95, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(96, 256, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(97, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(98, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(99, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(100, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(101, 256, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(102, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(103, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(104, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(105, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(106, 256, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(107, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(108, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(109, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(110, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(111, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(451, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(112, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(113, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(114, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(115, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(116, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(117, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(118, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(119, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(120, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(121, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(122, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(123, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(124, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(125, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(126, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(127, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(128, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(374, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(375, 256, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(376, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(377, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(378, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(129, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(130, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(131, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(132, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(133, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(134, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(135, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(136, 256, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(137, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(138, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(139, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(143, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(144, 256, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(145, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(146, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(147, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(148, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(149, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(150, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(151, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(152, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(155, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(156, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(153, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(154, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(157, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(158, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(159, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(160, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(161, 256, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(162, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(163, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(164, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(167, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(168, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(169, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(170, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(171, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(172, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(173, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(140, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(141, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(142, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(165, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(166, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(405, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(406, 256, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(407, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(408, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(409, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(174, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(175, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(176, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(453, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(454, 256, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(455, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(456, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(457, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(458, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(459, 256, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(460, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(461, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(462, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(177, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(178, 256, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(179, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(180, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(181, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(182, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(183, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(184, 256, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(185, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(186, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(187, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(188, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(189, 256, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(190, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(191, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(192, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(193, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(194, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(195, 256, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(196, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(197, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(198, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(199, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(200, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(201, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(202, 256, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(203, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(204, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(205, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(206, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(207, 256, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(208, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(209, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(210, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(211, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(212, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(213, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(214, 256, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(215, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(216, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(217, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(218, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(219, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(220, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(393, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(394, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(221, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(222, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(223, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(224, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(225, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(226, 256, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(227, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(228, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(229, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(230, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(231, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(232, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(233, 256, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(234, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(235, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(236, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(237, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(238, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(239, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(240, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(241, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(242, 256, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(243, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(244, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(245, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(246, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(247, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(248, 256, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(249, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(483, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(484, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(250, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(251, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(252, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(253, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(254, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(255, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(256, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(257, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(258, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(259, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(260, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(261, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(262, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(263, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(264, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(265, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(266, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(267, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(268, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(269, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(270, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(271, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(447, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(448, 256, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(449, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(272, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(450, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(273, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(274, 256, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(275, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(276, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(277, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(278, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(279, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(280, 256, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(281, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(282, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(283, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(284, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(285, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(286, 256, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(287, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(288, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(289, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(290, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(291, 256, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(292, 256, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(293, 256, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(294, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(295, 256, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(296, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(297, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(298, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(299, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(300, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(301, 256, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(302, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(303, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(304, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(305, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(306, 256, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(307, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(308, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(309, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(310, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(311, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(312, 256, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(313, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(314, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(315, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(316, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(379, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(380, 256, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(381, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(382, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(383, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(465, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(466, 256, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(467, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(468, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(469, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(317, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(318, 256, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(319, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(320, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(321, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(322, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(323, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(324, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(325, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(326, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(327, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(328, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(329, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(330, 256, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(331, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(332, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(333, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(334, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(335, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(336, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(337, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(338, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(339, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(340, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(341, 256, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(342, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(343, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(344, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(373, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(369, 256, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(370, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(371, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(372, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(442, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(443, 256, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(444, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(445, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(446, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(441, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(345, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(346, 256, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(347, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(348, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(349, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(384, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(385, 256, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(386, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(387, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(388, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(350, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(351, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(352, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(353, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(354, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(355, 256, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(356, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(357, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(358, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(359, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(360, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(361, 256, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(362, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(363, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(364, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(365, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(366, 256, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(367, 256, 'A', 0, 100);


CREATE TABLE dqa_issue_action
(
  action_code        VARCHAR(1) NOT NULL PRIMARY KEY,
  action_label       VARCHAR(30) NOT NULL
);

INSERT INTO dqa_issue_action (action_code, action_label) VALUES ('E', 'Error');
INSERT INTO dqa_issue_action (action_code, action_label) VALUES ('W', 'Warn');
INSERT INTO dqa_issue_action (action_code, action_label) VALUES ('A', 'Accept');
INSERT INTO dqa_issue_action (action_code, action_label) VALUES ('S', 'Skip');

CREATE TABLE dqa_message_batch
(
  batch_id            INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  batch_title         VARCHAR(60),
  type_code           VARCHAR(1),
  start_date          DATETIME,
  end_date            DATETIME,
  submit_code         VARCHAR(1),
  profile_id         INTEGER
);

CREATE TABLE dqa_receive_queue
(
  receive_queue_id    INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  batch_id            INTEGER,
  received_id         INTEGER,
  submit_code         VARCHAR(1)
);

CREATE TABLE dqa_message_received
(
  received_id         INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  profile_id          INTEGER NOT NULL,
  received_date       DATETIME NOT NULL,
  request_text        TEXT,
  response_text       TEXT,
  action_code         VARCHAR(1),
  submit_code         VARCHAR(1)
);

CREATE TABLE dqa_issue_found 
(
  issue_found_id      INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  received_id         INTEGER NOT NULL,
  issue_id            INTEGER NOT NULL,
  position_id         INTEGER NOT NULL,
  action_code         VARCHAR(1),
  code_id             INTEGER
);

CREATE TABLE dqa_code_received
(
  code_id             INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  code_label          VARCHAR(30),
  profile_id          INTEGER NOT NULL,
  table_id            INTEGER NOT NULL,
  received_value      VARCHAR(50) NOT NULL,
  code_value          VARCHAR(50),
  code_status         VARCHAR(1) NOT NULL,
  received_count      INTEGER
);

CREATE TABLE dqa_code_status
(
  code_status        VARCHAR(1) NOT NULL PRIMARY KEY,
  code_label         VARCHAR(30) NOT NULL
);

INSERT INTO dqa_code_status (code_status, code_label) VALUES ('V', 'Valid');
INSERT INTO dqa_code_status (code_status, code_label) VALUES ('I', 'Invalid');
INSERT INTO dqa_code_status (code_status, code_label) VALUES ('U', 'Unrecognized');
INSERT INTO dqa_code_status (code_status, code_label) VALUES ('D', 'Deprecated');
INSERT INTO dqa_code_status (code_status, code_label) VALUES ('G', 'Ignored');

CREATE TABLE dqa_code_table
(
  table_id             INTEGER NOT NULL PRIMARY KEY,
  table_label          VARCHAR(50) NOT NULL, 
  default_code_value   VARCHAR(50)
);

INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES(2, 'Address Country', '');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES(3, 'Address County', '');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES(4, 'Address State', '');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES(5, 'Address Type', '');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES(6, 'Administration Unit', '');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES(7, 'Birth Order', '');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES(8, 'Body Route', '');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES(9, 'Body Site', '');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES(35, 'Country Code', '');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES(36, 'Character Set', '');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES(10, 'Financial Status Code', '');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES(45, 'HL7 Value Type', '');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES(11, 'Id Assigning Authority', '');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES(12, 'Id Type Code', '');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES(37, 'Message Profile Id', '');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES(39, 'Message Processing Id', '');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES(43, 'Observation Identifier', '');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES(13, 'Organization', '');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES(38, 'Patient Class', '');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES(14, 'Patient Ethnicity', '');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES(15, 'Patient Id', '');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES(16, 'Patient Protection', '');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES(17, 'Patient Publicity', '');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES(18, 'Patient Race', '');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES(19, 'Patient Sex', '');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES(20, 'Person Language', '');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES(21, 'Person Name Type', '');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES(22, 'Person Relationship', '');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES(23, 'Physician Number', '');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES(24, 'Registry Status', '');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES(41, 'Telecommunication Use', '');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES(42, 'Telecommunication Equipment', '');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES(25, 'Vaccination Action Code', 'A');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES(26, 'Vaccination Completion', '');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES(27, 'Vaccination Confidentiality', '');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES(28, 'Vaccination CPT Code', '');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES(29, 'Vaccination CVX Code', '');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES(30, 'Vaccination Information Source', '');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES(31, 'Vaccination Manufacturer Code', '');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES(34, 'Vaccination Order Control Code', '');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES(32, 'Vaccination Refusal', '');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES(33, 'Vaccine Product', '');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES(40, 'Acknowledgement Type', '');

CREATE TABLE dqa_batch_type
(
  type_code           VARCHAR(1) NOT NULL PRIMARY KEY,
  type_label          VARCHAR(30) NOT NULL
);

INSERT INTO dqa_batch_type (type_code, type_label) VALUES ('D', 'Daily');
INSERT INTO dqa_batch_type (type_code, type_label) VALUES ('W', 'Weekly');
INSERT INTO dqa_batch_type (type_code, type_label) VALUES ('M', 'Monthly');
INSERT INTO dqa_batch_type (type_code, type_label) VALUES ('O', 'Other');
INSERT INTO dqa_batch_type (type_code, type_label) VALUES ('S', 'Submission');

CREATE TABLE dqa_batch_issues
(
  batch_issues_id     INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  batch_id            INTEGER NOT NULL,
  issue_id            INTEGER NOT NULL,
  issue_count         INTEGER
);

CREATE TABLE dqa_batch_actions
(
  batch_actions_id    INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  batch_id            INTEGER NOT NULL,
  action_code         VARCHAR(1) NOT NULL,
  action_count        INTEGER
);

CREATE TABLE dqa_submit_status
(
  submit_code         VARCHAR(1) NOT NULL PRIMARY KEY,
  submit_label        VARCHAR(30) NOT NULL
);

INSERT INTO dqa_submit_status (submit_code, submit_label) VALUES ('E', 'Excluded');
INSERT INTO dqa_submit_status (submit_code, submit_label) VALUES ('Q', 'Queued');
INSERT INTO dqa_submit_status (submit_code, submit_label) VALUES ('H', 'Hold');
INSERT INTO dqa_submit_status (submit_code, submit_label) VALUES ('P', 'Prepared');
INSERT INTO dqa_submit_status (submit_code, submit_label) VALUES ('S', 'Submitted');

CREATE TABLE dqa_message_header
(
  header_id             INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  received_id           INTEGER NOT NULL,
  ack_type_accept       VARCHAR(250),
  ack_type_application  VARCHAR(250),
  character_set         VARCHAR(250),
  country_code          VARCHAR(250),
  message_control       VARCHAR(250),
  message_date          DATE,
  message_profile       VARCHAR(250),
  message_structure     VARCHAR(250),
  message_trigger       VARCHAR(250),
  message_type          VARCHAR(250),
  message_version       VARCHAR(250),
  processing_status     VARCHAR(250),
  receiving_application VARCHAR(250),
  receiving_facility    VARCHAR(250),
  sending_application   VARCHAR(250),
  sending_facility      VARCHAR(250)
);

CREATE TABLE dqa_patient 
(
  patient_id               INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  received_id              INTEGER NOT NULL,
  address_city             VARCHAR(250),
  address_country          VARCHAR(250),
  address_county_parish    VARCHAR(250),
  address_state            VARCHAR(250),
  address_street           VARCHAR(250),
  address_street2          VARCHAR(250),
  address_type             VARCHAR(250),
  address_zip              VARCHAR(250),
  alias_first              VARCHAR(250),
  alias_last               VARCHAR(250),
  alias_middle             VARCHAR(250),
  alias_prefix             VARCHAR(250),
  alias_suffix             VARCHAR(250),
  alias_type_code          VARCHAR(250),
  birth_date               DATE,
  birth_muliple            VARCHAR(250),
  birth_order              VARCHAR(250),
  birth_place              VARCHAR(250),
  death_date               DATE,
  death_indicator          VARCHAR(250),
  ethnicity_code           VARCHAR(250),
  facility_id              VARCHAR(250),
  facility_name            VARCHAR(250),
  financial_eligibility    VARCHAR(250),
  id_medicaid              VARCHAR(250),
  id_ssn                   VARCHAR(250),
  id_submitter_assign_auth VARCHAR(250),
  id_submitter_number      VARCHAR(250),
  id_submitter_type_code   VARCHAR(250),
  mother_maiden_name       VARCHAR(250),
  name_first               VARCHAR(250),
  name_last                VARCHAR(250),
  name_middle              VARCHAR(250),
  name_prefix              VARCHAR(250),
  name_suffix              VARCHAR(250),
  name_type_code           VARCHAR(250),
  phone_number             VARCHAR(250),
  physician_name_first     VARCHAR(250),
  physician_name_last      VARCHAR(250),
  physician_number         VARCHAR(250),
  primary_language_code    VARCHAR(250),
  protection_code          VARCHAR(250),
  publicity_code           VARCHAR(250),
  race_code                VARCHAR(250),
  registry_status          VARCHAR(250),
  sex_code                 VARCHAR(250),
  skipped                  VARCHAR(1),
  financial_eligibility_date  DATE
);

CREATE TABLE dqa_next_of_kin
(
  next_of_kin_id           INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  received_id              INTEGER NOT NULL,
  position_id              INTEGER NOT NULL,
  address_city             VARCHAR(250),
  address_country          VARCHAR(250),
  address_county_parish    VARCHAR(250),
  address_state            VARCHAR(250),
  address_street           VARCHAR(250),
  address_street2          VARCHAR(250),
  address_type             VARCHAR(250),
  address_zip              VARCHAR(250),
  name_first               VARCHAR(250),
  name_last                VARCHAR(250),
  name_middle              VARCHAR(250),
  name_prefix              VARCHAR(250),
  name_suffix              VARCHAR(250),
  name_type_code           VARCHAR(250),
  phone_number             VARCHAR(250),
  relationship_code        VARCHAR(250),
  skipped                  VARCHAR(1)
);

CREATE TABLE dqa_vaccination 
(
  vaccination_id             INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  received_id                INTEGER NOT NULL,
  position_id                INTEGER NOT NULL,
  admin_code_cpt             VARCHAR(250),
  admin_code_cvx             VARCHAR(250),
  admin_date                 DATE,
  amount                     VARCHAR(250),
  amount_unit_code           VARCHAR(250),
  body_route_code            VARCHAR(250),
  body_site_code             VARCHAR(250),
  completion_status_code     VARCHAR(250),
  confidentiality_code       VARCHAR(250),
  entered_by_number          VARCHAR(250),
  entered_by_name_first      VARCHAR(250),
  entered_by_name_last       VARCHAR(250),
  expiration_date            DATE,
  facility_id                VARCHAR(250),
  facility_name              VARCHAR(250),
  financial_eligibility_code VARCHAR(250),
  given_by_number            VARCHAR(250),
  given_by_name_last         VARCHAR(250),
  given_by_name_first        VARCHAR(250),
  id_submitter               VARCHAR(250),
  information_source_code    VARCHAR(250),
  lot_number                 VARCHAR(250),
  manufacturer_code          VARCHAR(250),
  ordered_by_number          VARCHAR(250),
  refusal_code               VARCHAR(250),
  system_entry_date          DATE,
  vis_publication_date       VARCHAR(250),
  skipped                    VARCHAR(1),
  order_control_code         VARCHAR(250)
);

CREATE TABLE dqa_vaccine_product
(
  product_id          INTEGER NOT NULL PRIMARY KEY,
  product_name        VARCHAR(250) NOT NULL,
  product_label       VARCHAR(250) NOT NULL,
  cvx_id              INTEGER NOT NULL,
  mvx_code            VARCHAR(10) NOT NULL,
  valid_start_date    DATE NOT NULL,
  use_start_date      DATE NOT NULL,
  use_end_date        DATE NOT NULL,
  valid_end_date      DATE NOT NULL
);

INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (2, 'ORIMUNE', 'OPV', '02', 'WAL', '1970-01-01', '1970-01-01', '2010-09-01', '2010-09-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (3, 'M-M-R II', 'MMR', '03', 'MSD', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (4, 'ATTENUVAX', 'measles', '05', 'MSD', '1970-01-01', '1970-01-01', '2010-05-28', '2010-05-28');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (5, 'MERUVAX II', 'rubella', '06', 'MSD', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (6, 'MUMPSVAX', 'mumps', '07', 'MSD', '1970-01-01', '1970-01-01', '2010-09-01', '2010-09-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (7, 'ENGERIX B-PEDS', 'Hep B, adolescent or pediatric', '08', 'SKB', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (8, 'RECOMBIVAX-PEDS', 'Hep B, adolescent or pediatric', '08', 'MSD', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (9, 'TD(GENERIC)', 'Td (adult), adsorbed', '09', 'MBL', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (10, 'IPOL', 'IPV', '10', 'PMC', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (11, 'PREVNAR 7', 'pneumococcal conjugate PCV 7', '100', 'WAL', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (12, 'TYPHIM VI', 'typhoid, ViCPs', '101', 'PMC', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (13, 'TWINRIX', 'Hep A-Hep B', '104', 'SKB', '2001-01-01', '2001-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (14, 'DAPTACEL', 'DTaP, 5 pertussis antigens', '106', 'PMC', '2002-01-01', '2002-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (15, 'PEDIARIX', 'DTaP-Hep B-IPV', '110', 'SKB', '2002-01-01', '2002-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (16, 'FLUMIST', 'influenza, live, intranasal', '111', 'MED', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (17, 'DECAVAC', 'Td (adult) preservative free', '113', 'PMC', '2003-01-01', '2003-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (18, 'MENACTRA', 'meningococcal MCV4P', '114', 'PMC', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (19, 'ADACEL', 'Tdap', '115', 'PMC', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (20, 'BOOSTRIX', 'Tdap', '115', 'SKB', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (21, 'ROTATEQ', 'rotavirus, pentavalent', '116', 'MSD', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (22, 'CERVARIX', 'HPV, bivalent', '118', 'SKB', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (23, 'ROTARIX', 'rotavirus, monovalent', '119', 'SKB', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (24, 'PENTACEL', 'DTaP-Hib-IPV', '120', 'PMC', '2008-01-01', '2008-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (25, 'ZOSTAVAX', 'zoster', '121', 'MSD', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (26, 'KINRIX', 'DTaP-IPV', '130', 'SKB', '2008-01-01', '2008-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (27, 'PREVNAR 13', 'Pneumococcal conjugate PCV 13', '133', 'PFR', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (28, 'PREVNAR 13', 'Pneumococcal conjugate PCV 13', '133', 'WAL', '1970-01-01', '1970-01-01', '2010-05-28', '2010-05-28');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (29, 'IXIARO', 'Japanese Encephalitis IM', '134', 'INT', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (30, 'FLUZONE-HIGH DOSE', 'Influenza, high dose seasonal', '135', 'PMC', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (31, 'MENVEO', 'Meningococcal MCV4O', '136', 'NOV', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (32, 'Afluria, preservative free', 'Influenza, seasonal, injectable, preservative free', '140', 'CSL', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (33, 'AGRIFLU', 'Influenza, seasonal, injectable, preservative free', '140', 'NOV', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (34, 'FLUARIX', 'Influenza, seasonal, injectable, preservative free', '140', 'SKB', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (35, 'FLUVIRIN-PRESERVATIVE FREE', 'Influenza, seasonal, injectable, preservative free', '140', 'NOV', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (36, 'FLUZONE-PRESERVATIVE FREE', 'Influenza, seasonal, injectable, preservative free', '140', 'PMC', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (37, 'AFLURIA', 'Influenza, seasonal, injectable', '141', 'CSL', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (38, 'FLULAVAL', 'Influenza, seasonal, injectable', '141', 'SKB', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (39, 'FLUVIRIN', 'Influenza, seasonal, injectable', '141', 'NOV', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (40, 'FLUZONE', 'Influenza, seasonal, injectable', '141', 'PMC', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (41, 'IMOVAX', 'rabies, intramuscular injection', '18', 'PMC', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (42, 'RABAVERT', 'rabies, intramuscular injection', '18', 'NOV', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (43, 'MYCOBAX', 'BCG', '19', 'PMC', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (44, 'TICE BCG', 'BCG', '19', 'OTC', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (45, 'ACEL-IMUNE', 'DTaP', '20', 'WAL', '1991-01-01', '1991-01-01', '2002-01-01', '2002-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (46, 'CERTIVA', 'DTaP', '20', 'NAV', '1998-01-01', '1998-01-01', '2001-01-01', '2001-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (47, 'INFANRIX', 'DTaP', '20', 'SKB', '1997-01-01', '1997-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (48, 'TRIPEDIA', 'DTaP', '20', 'PMC', '1996-01-01', '1996-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (49, 'VARIVAX', 'varicella', '21', 'MSD', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (50, 'TETRAMUNE', 'DTP-Hib', '22', 'WAL', '1970-01-01', '1970-01-01', '2010-09-01', '2010-09-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (51, 'BIOTHRAX', 'anthrax', '24', 'MIP', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (52, 'VIVOTIF BERNA', 'typhoid, oral', '25', 'BPC', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (53, 'DT(GENERIC)', 'DT (pediatric)', '28', 'PMC', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (54, 'MENOMUNE', 'meningococcal MPSV4', '32', 'PMC', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (55, 'PNEUMOVAX 23', 'pneumococcal polysaccharide PPV23', '33', 'MSD', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (56, 'TETANUS TOXOID (GENERIC)', 'tetanus toxoid, adsorbed', '35', 'PMC', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (57, 'YF-VAX', 'yellow fever', '37', 'PMC', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (58, 'BIAVAX II', 'rubella/mumps', '38', 'MSD', '1970-01-01', '1970-01-01', '2010-09-01', '2010-09-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (59, 'JE-VAX', 'Japanese encephalitis SC', '39', 'JPN', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (60, 'IMOVAX ID', 'rabies, intradermal injection', '40', 'PMC', '1970-01-01', '1970-01-01', '2010-08-18', '2010-08-18');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (61, 'RabAvert', 'rabies, intradermal injection', '40', 'CHI', '1970-01-01', '1970-01-01', '2010-05-28', '2010-05-28');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (62, 'ENGERIX-B-ADULT', 'Hep B, adult', '43', 'SKB', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (63, 'RECOMBIVAX-ADULT', 'Hep B, adult', '43', 'MSD', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (64, 'RECOMBIVAX-DIALYSIS', 'Hep B, dialysis', '44', 'MSD', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (65, 'PROHIBIT', 'Hib (PRP-D)', '46', 'PMC', '1970-01-01', '1970-01-01', '2010-09-01', '2010-09-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (66, 'HIBTITER', 'Hib (HbOC)', '47', 'WAL', '1970-01-01', '1970-01-01', '2010-08-18', '2010-08-18');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (67, 'ACTHIB', 'Hib (PRP-T)', '48', 'PMC', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (68, 'HIBERIX', 'Hib (PRP-T)', '48', 'SKB', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (69, 'OMNIHIB', 'Hib (PRP-T)', '48', 'SKB', '1970-01-01', '1970-01-01', '2010-09-01', '2010-09-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (70, 'PEDVAXHIB', 'Hib (PRP-OMP)', '49', 'MSD', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (71, 'TRIHIBIT', 'DTaP-Hib', '50', 'PMC', '1996-01-01', '1996-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (72, 'COMVAX', 'Hib-Hep B', '51', 'MSD', '1996-01-01', '1996-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (73, 'HAVRIX-ADULT', 'Hep A, adult', '52', 'SKB', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (74, 'VAQTA-ADULT', 'Hep A, adult', '52', 'MSD', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (75, 'TYPHOID-AKD', 'typhoid, parenteral, AKD (U.S. military)', '53', 'USA', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (76, 'GARDASIL', 'HPV, quadrivalent', '62', 'MSD', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (77, 'ACAM2000', 'vaccinia (smallpox)', '75', 'ACA', '1970-01-01', '1970-01-01', '2010-05-28', '2010-05-28');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (78, 'ACAM2000', 'vaccinia (smallpox)', '75', 'PMC', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (79, 'DRYVAX', 'vaccinia (smallpox)', '75', 'WAL', '1970-01-01', '1970-01-01', '2010-09-01', '2010-09-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (80, 'HAVRIX-PEDS', 'Hep A, ped/adol, 2 dose', '83', 'SKB', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (81, 'VAQTA-PEDS', 'Hep A, ped/adol, 2 dose', '83', 'MSD', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (82, 'PROQUAD', 'MMRV', '94', 'MSD', '2005-01-01', '2005-01-01', '2100-01-01', '2100-01-01');

CREATE TABLE dqa_vaccine_mvx
(
  mvx_code            VARCHAR(10) NOT NULL PRIMARY KEY,
  mvx_label           VARCHAR(250) NOT NULL,
  valid_start_date    DATE NOT NULL,
  use_start_date      DATE NOT NULL,
  use_end_date        DATE NOT NULL,
  valid_end_date      DATE NOT NULL
);

INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('AB', 'Abbott Laboratories', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('ACA', 'Acambis, Inc', '1970-01-01', '1970-01-01', '2010-05-28', '2010-05-28');
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('AD', 'Adams Laboratories, Inc.', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('ALP', 'Alpha Therapeutic Corporation', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('AR', 'Armour', '1970-01-01', '1970-01-01', '2010-05-28', '2010-05-28');
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('AVB', 'Aventis Behring L.L.C.', '1970-01-01', '1970-01-01', '2010-05-28', '2010-05-28');
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('AVI', 'Aviron', '1970-01-01', '1970-01-01', '2010-05-28', '2010-05-28');
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('BA', 'Baxter Healthcare Corporation-inactive', '1970-01-01', '1970-01-01', '2010-05-28', '2010-05-28');
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('BAH', 'Baxter Healthcare Corporation', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('BAY', 'Bayer Corporation', '1970-01-01', '1970-01-01', '2010-05-28', '2010-05-28');
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('BP', 'Berna Products', '1970-01-01', '1970-01-01', '2010-05-28', '2010-05-28');
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('BPC', 'Berna Products Corporation', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('BTP', 'Biotest Pharmaceuticals Corporation', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('MIP', 'Emergent BioDefense Operations Lansing', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('CSL', 'CSL Behring, Inc', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('CNJ', 'Cangene Corporation', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('CMP', 'Celltech Medeva Pharmaceuticals', '1970-01-01', '1970-01-01', '2010-05-28', '2010-05-28');
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('CEN', 'Centeon L.L.C.', '1970-01-01', '1970-01-01', '2010-05-28', '2010-05-28');
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('CHI', 'Chiron Corporation', '1970-01-01', '1970-01-01', '2010-05-28', '2010-05-28');
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('CON', 'Connaught', '1970-01-01', '1970-01-01', '2010-05-28', '2010-05-28');
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('DVC', 'DynPort Vaccine Company, LLC', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('EVN', 'Evans Medical Limited', '1970-01-01', '1970-01-01', '2010-05-28', '2010-05-28');
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('GEO', 'GeoVax Labs, Inc.', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('SKB', 'GlaxoSmithKline', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('GRE', 'Greer Laboratories, Inc.', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('IAG', 'Immuno International AG', '1970-01-01', '1970-01-01', '2010-05-28', '2010-05-28');
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('IUS', 'Immuno-U.S., Inc.', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('INT', 'Intercell Biomedical', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('KGC', 'Korea Green Cross Corporation', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('LED', 'Lederle', '1970-01-01', '1970-01-01', '2010-05-28', '2010-05-28');
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('MBL', 'Massachusetts Biologic Laboratories', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('MA', 'Massachusetts Public Health Biologic Laboratories', '1970-01-01', '1970-01-01', '2010-05-28', '2010-05-28');
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('MED', 'MedImmune, Inc.', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('MSD', 'Merck and Co., Inc.', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('IM', 'Merieux', '1970-01-01', '1970-01-01', '2010-05-28', '2010-05-28');
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('MIL', 'Miles', '1970-01-01', '1970-01-01', '2010-05-28', '2010-05-28');
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('NAB', 'NABI', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('NYB', 'New York Blood Center', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('NAV', 'North American Vaccine, Inc.', '1970-01-01', '1970-01-01', '2010-05-28', '2010-05-28');
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('NOV', 'Novartis Pharmaceutical Corporation', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('NVX', 'Novavax, Inc.', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('OTC', 'Organon Teknika Corporation', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('ORT', 'Ortho-clinical Diagnostics', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('PD', 'Parkedale Pharmaceuticals', '1970-01-01', '1970-01-01', '2010-05-28', '2010-05-28');
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('PWJ', 'PowderJect Pharmaceuticals', '1970-01-01', '1970-01-01', '2010-05-28', '2010-05-28');
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('PRX', 'Praxis Biologics', '1970-01-01', '1970-01-01', '2010-05-28', '2010-05-28');
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('JPN', 'The Research Foundation for Microbial Diseases of Osaka University (BIKEN)', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('PMC', 'sanofi pasteur', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('SCL', 'Sclavo, Inc.', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('SOL', 'Solvay Pharmaceuticals', '1970-01-01', '1970-01-01', '2010-05-28', '2010-05-28');
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('SI', 'Swiss Serum and Vaccine Inst.', '1970-01-01', '1970-01-01', '2010-05-28', '2010-05-28');
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('TAL', 'Talecris Biotherapeutics', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('USA', 'United States Army Medical Research and Material Command', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('VXG', 'VaxGen', '1970-01-01', '1970-01-01', '2010-05-28', '2010-05-28');
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('WA', 'Wyeth-Ayerst', '1970-01-01', '1970-01-01', '2010-05-28', '2010-05-28');
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('WAL', 'Wyeth', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('ZLB', 'ZLB Behring', '1970-01-01', '1970-01-01', '2010-05-28', '2010-05-28');
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('OTH', 'Other manufacturer', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('UNK', 'Unknown manufacturer', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('AKR', 'Akorn, Inc', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('PFR', 'Pfizer, Inc', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('BRR', 'Barr Laboratories', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01');

CREATE TABLE dqa_vaccine_cvx
(
  cvx_id              INTEGER NOT NULL PRIMARY KEY,
  cvx_code            VARCHAR(10) NOT NULL,
  cvx_label           VARCHAR(250) NOT NULL,
  valid_start_date    DATE NOT NULL,
  use_start_date      DATE NOT NULL,
  use_end_date        DATE NOT NULL,
  valid_end_date      DATE NOT NULL,
  use_month_start     INTEGER NOT NULL,
  use_month_end       INTEGER NOT NULL,
  concept_type        VARCHAR(30) NOT NULL
);

INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (998, '998', 'no vaccine administered', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'non vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (99, '99', 'RESERVED - do not use', '1970-01-01', '1970-01-01', '2010-05-28', '2010-05-28', 1, 1440, 'non vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (999, '999', 'unknown', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'non vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (143, '143', 'Adenovirus types 4 and 7', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (54, '54', 'adenovirus, type 4', '1970-01-01', '1970-01-01', '2010-05-28', '2010-05-28', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (55, '55', 'adenovirus, type 7', '1970-01-01', '1970-01-01', '2010-05-28', '2010-05-28', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (82, '82', 'adenovirus, unspecified formulation', '1970-01-01', '1970-01-01', '2010-09-30', '2010-09-30', 1, 1440, 'unspecified');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (24, '24', 'anthrax', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (19, '19', 'BCG', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (27, '27', 'botulinum antitoxin', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (26, '26', 'cholera', '1970-01-01', '1970-01-01', '2010-05-28', '2010-05-28', 1, 1440, 'unspecified');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (29, '29', 'CMVIG', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (56, '56', 'dengue fever', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (12, '12', 'diphtheria antitoxin', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (28, '28', 'DT (pediatric)', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (20, '20', 'DTaP', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (106, '106', 'DTaP, 5 pertussis antigens', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (107, '107', 'DTaP, unspecified formulation', '1970-01-01', '1970-01-01', '2010-09-30', '2010-09-30', 1, 1440, 'unspecified');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (110, '110', 'DTaP-Hep B-IPV', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (50, '50', 'DTaP-Hib', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (120, '120', 'DTaP-Hib-IPV', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (130, '130', 'DTaP-IPV', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (132, '132', 'DTaP-IPV-HIB-HEP B, historical', '1970-01-01', '1970-01-01', '2010-05-28', '2010-05-28', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (146, '146', 'DTaP,IPV,Hib,HepB', '2011-08-31', '2011-08-31', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (01, '01', 'DTP', '1970-01-01', '1970-01-01', '2010-05-28', '2010-05-28', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (22, '22', 'DTP-Hib', '1970-01-01', '1970-01-01', '2010-05-28', '2010-05-28', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (102, '102', 'DTP-Hib-Hep B', '1970-01-01', '1970-01-01', '2010-05-28', '2010-05-28', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (57, '57', 'hantavirus', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (30, '30', 'HBIG', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (52, '52', 'Hep A, adult', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (83, '83', 'Hep A, ped/adol, 2 dose', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (84, '84', 'Hep A, ped/adol, 3 dose', '1970-01-01', '1970-01-01', '2010-05-28', '2010-05-28', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (31, '31', 'Hep A, pediatric, unspecified formulation', '1970-01-01', '1970-01-01', '2010-09-30', '2010-09-30', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (85, '85', 'Hep A, unspecified formulation', '1970-01-01', '1970-01-01', '2010-09-30', '2010-09-30', 1, 1440, 'unspecified');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (104, '104', 'Hep A-Hep B', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (08, '08', 'Hep B, adolescent or pediatric', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 0, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (42, '42', 'Hep B, adolescent/high risk infant', '1970-01-01', '1970-01-01', '2010-05-28', '2010-05-28', 0, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (43, '43', 'Hep B, adult', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (44, '44', 'Hep B, dialysis', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (45, '45', 'Hep B, unspecified formulation', '1970-01-01', '1970-01-01', '2010-09-30', '2010-09-30', 0, 1440, 'unspecified');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (58, '58', 'Hep C', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (59, '59', 'Hep E', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (60, '60', 'herpes simplex 2', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (47, '47', 'Hib (HbOC)', '1970-01-01', '1970-01-01', '2010-05-28', '2010-05-28', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (46, '46', 'Hib (PRP-D)', '1970-01-01', '1970-01-01', '2010-05-28', '2010-05-28', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (49, '49', 'Hib (PRP-OMP)', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (48, '48', 'Hib (PRP-T)', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (17, '17', 'Hib, unspecified formulation', '1970-01-01', '1970-01-01', '2010-09-30', '2010-09-30', 1, 1440, 'unspecified');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (51, '51', 'Hib-Hep B', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (61, '61', 'HIV', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (118, '118', 'HPV, bivalent', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (62, '62', 'HPV, quadrivalent', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (137, '137', 'HPV, unspecified formulation', '1970-01-01', '1970-01-01', '2010-09-30', '2010-09-30', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (86, '86', 'IG', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (14, '14', 'IG, unspecified formulation', '1970-01-01', '1970-01-01', '2010-09-30', '2010-09-30', 1, 1440, 'unspecified');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (87, '87', 'IGIV', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (123, '123', 'influenza, H5N1-1203', '1970-01-01', '1970-01-01', '2010-05-28', '2010-05-28', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (135, '135', 'Influenza, high dose seasonal', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (111, '111', 'influenza, live, intranasal', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (141, '141', 'Influenza, seasonal, injectable', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (140, '140', 'Influenza, seasonal, injectable, preservative free', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (144, '144', 'influenza, seasonal, intradermal, preservative free', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (15, '15', 'influenza, split (incl. purified surface antigen)', '1970-01-01', '1970-01-01', '2010-09-30', '2010-09-30', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (88, '88', 'influenza, unspecified formulation', '1970-01-01', '1970-01-01', '2010-09-30', '2010-09-30', 1, 1440, 'unspecified');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (16, '16', 'influenza, whole', '1970-01-01', '1970-01-01', '2010-05-28', '2010-05-28', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (10, '10', 'IPV', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (134, '134', 'Japanese Encephalitis IM', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (39, '39', 'Japanese encephalitis SC', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (129, '129', 'Japanese Encephalitis, unspecified formulation', '1970-01-01', '1970-01-01', '2010-09-30', '2010-09-30', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (63, '63', 'Junin virus', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (64, '64', 'leishmaniasis', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (65, '65', 'leprosy', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (66, '66', 'Lyme disease', '1970-01-01', '1970-01-01', '2010-05-28', '2010-05-28', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (04, '04', 'M/R', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (67, '67', 'malaria', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (05, '05', 'measles', '1970-01-01', '1970-01-01', '2010-08-31', '2010-08-31', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (68, '68', 'melanoma', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (103, '103', 'meningococcal C conjugate', '1970-01-01', '1970-01-01', '2010-05-28', '2010-05-28', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (136, '136', 'Meningococcal MCV4O', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (114, '114', 'meningococcal MCV4P', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (32, '32', 'meningococcal MPSV4', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (108, '108', 'meningococcal, unspecified formulation', '1970-01-01', '1970-01-01', '2010-09-30', '2010-09-30', 1, 1440, 'unspecified');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (03, '03', 'MMR', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (94, '94', 'MMRV', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (07, '07', 'mumps', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (127, '127', 'Novel influenza-H1N1-09', '1970-01-01', '1970-01-01', '2010-08-28', '2010-08-28', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (128, '128', 'Novel Influenza-H1N1-09, all formulations', '1970-01-01', '1970-01-01', '2010-08-28', '2010-08-28', 1, 1440, 'unspecified');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (125, '125', 'Novel Influenza-H1N1-09, nasal', '1970-01-01', '1970-01-01', '2010-08-28', '2010-08-28', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (126, '126', 'Novel influenza-H1N1-09, preservative-free', '1970-01-01', '1970-01-01', '2010-08-28', '2010-08-28', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (02, '02', 'OPV', '1970-01-01', '1970-01-01', '2010-05-28', '2010-05-28', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (69, '69', 'parainfluenza-3', '1970-01-01', '1970-01-01', '2010-05-28', '2010-05-28', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (11, '11', 'pertussis', '1970-01-01', '1970-01-01', '2010-05-28', '2010-05-28', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (23, '23', 'plague', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (133, '133', 'Pneumococcal conjugate PCV 13', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (100, '100', 'pneumococcal conjugate PCV 7', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (33, '33', 'pneumococcal polysaccharide PPV23', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (109, '109', 'pneumococcal, unspecified formulation', '1970-01-01', '1970-01-01', '2010-09-30', '2010-09-30', 1, 1440, 'unspecified');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (89, '89', 'polio, unspecified formulation', '1970-01-01', '1970-01-01', '2010-09-30', '2010-09-30', 1, 1440, 'unspecified');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (70, '70', 'Q fever', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (40, '40', 'rabies, intradermal injection', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (18, '18', 'rabies, intramuscular injection', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (90, '90', 'rabies, unspecified formulation', '1970-01-01', '1970-01-01', '2010-09-30', '2010-09-30', 1, 1440, 'unspecified');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (72, '72', 'rheumatic fever', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (73, '73', 'Rift Valley fever', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (34, '34', 'RIG', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (119, '119', 'rotavirus, monovalent', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (116, '116', 'rotavirus, pentavalent', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (74, '74', 'rotavirus, tetravalent', '1970-01-01', '1970-01-01', '2010-05-28', '2010-05-28', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (122, '122', 'rotavirus, unspecified formulation', '1970-01-01', '1970-01-01', '2010-09-30', '2010-09-30', 1, 1440, 'unspecified');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (71, '71', 'RSV-IGIV', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (93, '93', 'RSV-MAb', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (145, '145', 'RSV-Mab (new)', '2011-08-31', '2011-08-31', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (06, '06', 'rubella', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (38, '38', 'rubella/mumps', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (76, '76', 'Staphylococcus bacterio lysate', '1970-01-01', '1970-01-01', '2010-05-28', '2010-05-28', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (138, '138', 'Td (adult)', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (113, '113', 'Td (adult) preservative free', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (09, '09', 'Td (adult), adsorbed', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (139, '139', 'Td(adult) unspecified formulation', '1970-01-01', '1970-01-01', '2010-09-30', '2010-09-30', 1, 1440, 'unspecified');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (115, '115', 'Tdap', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (35, '35', 'tetanus toxoid, adsorbed', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (142, '142', 'tetanus toxoid, not adsorbed', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (112, '112', 'tetanus toxoid, unspecified formulation', '1970-01-01', '1970-01-01', '2010-09-30', '2010-09-30', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (77, '77', 'tick-borne encephalitis', '1970-01-01', '1970-01-01', '2010-05-28', '2010-05-28', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (13, '13', 'TIG', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (98, '98', 'TST, unspecified formulation', '1970-01-01', '1970-01-01', '2010-09-30', '2010-09-30', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (95, '95', 'TST-OT tine test', '1970-01-01', '1970-01-01', '2010-05-28', '2010-05-28', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (96, '96', 'TST-PPD intradermal', '1970-01-01', '1970-01-01', '2010-05-28', '2010-05-28', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (97, '97', 'TST-PPD tine test', '1970-01-01', '1970-01-01', '2010-05-28', '2010-05-28', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (78, '78', 'tularemia vaccine', '1970-01-01', '1970-01-01', '2010-05-28', '2010-05-28', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (25, '25', 'typhoid, oral', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (41, '41', 'typhoid, parenteral', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (53, '53', 'typhoid, parenteral, AKD (U.S. military)', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (91, '91', 'typhoid, unspecified formulation', '1970-01-01', '1970-01-01', '2010-09-30', '2010-09-30', 1, 1440, 'unspecified');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (101, '101', 'typhoid, ViCPs', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (131, '131', 'typhus, historical', '1970-01-01', '1970-01-01', '2010-05-28', '2010-05-28', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (75, '75', 'vaccinia (smallpox)', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (105, '105', 'vaccinia (smallpox) diluted', '1970-01-01', '1970-01-01', '2010-05-28', '2010-05-28', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (79, '79', 'vaccinia immune globulin', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (21, '21', 'varicella', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (81, '81', 'VEE, inactivated', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (80, '80', 'VEE, live', '1970-01-01', '1970-01-01', '2010-05-28', '2010-05-28', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (92, '92', 'VEE, unspecified formulation', '1970-01-01', '1970-01-01', '2010-09-30', '2010-09-30', 1, 1440, 'unspecified');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (36, '36', 'VZIG', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (117, '117', 'VZIG (IND)', '1970-01-01', '1970-01-01', '2010-05-28', '2010-05-28', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (37, '37', 'yellow fever', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (121, '121', 'zoster', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (147, '147', 'meningococcal MCV4, unspecified formulation', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', 1, 1440, 'unspecified');

CREATE TABLE dqa_vaccine_cpt
(
  cpt_id              INTEGER NOT NULL PRIMARY KEY,
  cpt_code            VARCHAR(10) NOT NULL,
  cpt_label           VARCHAR(250) NOT NULL,
  valid_start_date    DATE NOT NULL,
  use_start_date      DATE NOT NULL,
  use_end_date        DATE NOT NULL,
  valid_end_date      DATE NOT NULL,
  cvx_id              INTEGER NOT NULL
);

INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (2, '90476', 'adenovirus, type 4', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '54');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (3, '90477', 'adenovirus, type 7', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '55');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (4, '90581', 'anthrax', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '24');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (5, '90585', 'BCG', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '19');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (6, '90728', 'BCG', '1970-01-01', '1970-01-01', '2011-03-17', '2011-03-17', '19');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (7, '90287', 'botulinum antitoxin', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '27');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (8, '90725', 'cholera', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '26');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (9, '90291', 'CMVIG', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '29');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (10, '90296', 'diphtheria antitoxin', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '12');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (11, '90702', 'DT (pediatric)', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '28');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (12, '90700', 'DTaP', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '20');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (13, '90700', 'DTaP, 5 pertussis antigens', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '106');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (14, '90723', 'DTaP-Hep B-IPV', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '110');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (15, '90721', 'DTaP-Hib', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '50');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (16, '90698', 'DTaP-Hib-IPV', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '120');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (17, '90696', 'DTaP-IPV', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '130');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (18, '90701', 'DTP', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '01');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (19, '90720', 'DTP-Hib', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '22');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (20, '90371', 'HBIG', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '30');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (21, '90632', 'Hep A, adult', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '52');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (22, '90633', 'Hep A, ped/adol, 2 dose', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '83');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (23, '90634', 'Hep A, ped/adol, 3 dose', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '84');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (24, '90730', 'Hep A, unspecified formulation', '1970-01-01', '1970-01-01', '2011-03-17', '2011-03-17', '85');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (25, '90636', 'Hep A-Hep B', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '104');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (26, '90744', 'Hep B, adolescent or pediatric', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '08');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (27, '90745', 'Hep B, adolescent/high risk infant', '1970-01-01', '1970-01-01', '2011-03-17', '2011-03-17', '42');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (28, '90743', 'Hep B, adult', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '43');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (29, '90746', 'Hep B, adult', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '43');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (30, '90740', 'Hep B, dialysis', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '44');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (31, '90747', 'Hep B, dialysis', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '44');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (32, '90731', 'Hep B, unspecified formulation', '1970-01-01', '1970-01-01', '2011-03-17', '2011-03-17', '45');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (33, '90645', 'Hib (HbOC)', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '47');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (34, '90646', 'Hib (PRP-D)', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '46');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (35, '90647', 'Hib (PRP-OMP)', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '49');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (36, '90648', 'Hib (PRP-T)', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '48');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (37, '90737', 'Hib, unspecified formulation', '1970-01-01', '1970-01-01', '2011-03-17', '2011-03-17', '17');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (38, '90748', 'Hib-Hep B', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '51');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (39, '90650', 'HPV, bivalent', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '118');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (40, '90649', 'HPV, quadrivalent', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '62');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (41, '90281', 'IG', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '86');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (42, '90741', 'IG, unspecified formulation', '1970-01-01', '1970-01-01', '2011-03-17', '2011-03-17', '14');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (43, '90283', 'IGIV', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '87');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (44, '90662', 'Influenza, high dose seasonal', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '135');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (45, '90660', 'influenza, live, intranasal', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '111');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (46, '90657', 'Influenza, seasonal, injectable', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '141');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (47, '90658', 'Influenza, seasonal, injectable', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '141');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (48, '90656', 'Influenza, seasonal, injectable, preservative free', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '140');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (49, '90654', 'Influenza, seasonal, injectable, preservative free', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '144');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (50, '90655', 'Influenza, seasonal, injectable, preservative free', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '140');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (51, '90724', 'influenza, unspecified formulation', '1970-01-01', '1970-01-01', '2011-03-17', '2011-03-17', '88');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (52, '90659', 'influenza, whole', '1970-01-01', '1970-01-01', '2011-03-17', '2011-03-17', '16');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (53, '90713', 'IPV', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '10');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (54, '90738', 'Japanese Encephalitis IM', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '134');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (55, '90735', 'Japanese encephalitis SC', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '39');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (56, '90665', 'Lyme disease', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '66');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (57, '90708', 'M/R', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '04');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (58, '90705', 'measles', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '05');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (59, '90734', 'Meningococcal MCV4O', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '136');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (60, '90734', 'meningococcal MCV4P', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '114');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (61, '90733', 'meningococcal MPSV4', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '32');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (62, '90707', 'MMR', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '03');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (63, '90710', 'MMRV', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '94');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (64, '90704', 'mumps', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '07');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (65, '90668', 'Novel influenza-H1N1-09', '1970-01-01', '1970-01-01', '2010-08-31', '2010-08-31', '127');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (66, '90470', 'Novel Influenza-H1N1-09, all formulations', '1970-01-01', '1970-01-01', '2010-08-31', '2010-08-31', '128');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (67, '90663', 'Novel Influenza-H1N1-09, all formulations', '1970-01-01', '1970-01-01', '2010-08-31', '2010-08-31', '128');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (68, '90664', 'Novel Influenza-H1N1-09, nasal', '1970-01-01', '1970-01-01', '2010-08-31', '2010-08-31', '125');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (69, '90666', 'Novel influenza-H1N1-09, preservative-free', '1970-01-01', '1970-01-01', '2010-08-31', '2010-08-31', '126');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (70, '90712', 'OPV', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '02');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (71, '90727', 'plague', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '23');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (72, '90670', 'Pneumococcal conjugate PCV 13', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '133');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (73, '90669', 'pneumococcal conjugate PCV 7', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '100');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (74, '90732', 'pneumococcal polysaccharide PPV23', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '33');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (75, '90676', 'rabies, intradermal injection', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '40');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (76, '90675', 'rabies, intramuscular injection', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '18');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (77, '90726', 'rabies, unspecified formulation', '1970-01-01', '1970-01-01', '2011-03-17', '2011-03-17', '90');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (78, '90375', 'RIG', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '34');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (79, '90376', 'RIG', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '34');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (80, '90681', 'rotavirus, monovalent', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '119');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (81, '90680', 'rotavirus, pentavalent', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '116');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (82, '90379', 'RSV-IGIV', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '71');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (83, '90378', 'RSV-MAb', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '93');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (84, '90706', 'rubella', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '06');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (85, '90714', 'Td (adult) preservative free', '2005-01-01', '2005-01-01', '2100-01-01', '2100-01-01', '113');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (86, '90718', 'Td (adult), adsorbed', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '09');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (87, '90715', 'Tdap', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '115');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (88, '90703', 'tetanus toxoid, adsorbed', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '35');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (89, '90389', 'TIG', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '13');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (90, '90690', 'typhoid, oral', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '25');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (91, '90692', 'typhoid, parenteral', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '41');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (92, '90693', 'typhoid, parenteral, AKD (U.S. military)', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '53');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (93, '90714', 'typhoid, unspecified formulation', '1970-01-01', '1970-01-01', '2011-03-17', '2011-03-17', '91');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (94, '90691', 'typhoid, ViCPs', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '101');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (95, '90393', 'vaccinia immune globulin', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '79');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (96, '90716', 'varicella', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '21');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (97, '90396', 'VZIG', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '36');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (98, '90717', 'yellow fever', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '37');
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (99, '90736', 'zoster', '1970-01-01', '1970-01-01', '2100-01-01', '2100-01-01', '121');

CREATE TABLE dqa_code_master
(
  code_master_id      INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  table_id            INTEGER NOT NULL,
  code_value          VARCHAR(50) NOT NULL,
  code_label          VARCHAR(250) NOT NULL,
  use_value           VARCHAR(50) NOT NULL,
  code_status         VARCHAR(1) NOT NULL
);

INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (25, 'A', 'Add', 'A', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (25, 'D', 'Delete', 'D', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (25, 'U', 'Update', 'U', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (40, 'AL', 'Always', 'AL', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (40, 'NE', 'Never', 'NE', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (40, 'ER', 'Error only', 'ER', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (40, 'SU', 'SU', 'SU', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (41, 'PRN', 'Primary residence number', 'PRN', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (41, 'ORN', 'Other residence number', 'ORN', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (41, 'WPN', 'Work number', 'WPN', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (41, 'VHN', 'Vacation home number', 'VHN', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (41, 'ASN', 'Answering service number', 'ASN', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (41, 'EMR', 'Emergency number', 'EMR', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (41, 'NET', 'Network (email) address', 'NET', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (41, 'BPN', 'Beeper number', 'BPN', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (42, 'PH', 'Telephone', 'PH', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (42, 'FX', 'Fax', 'FX', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (42, 'MD', 'Modem', 'MD', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (42, 'CP', 'Cellular phone', 'CP', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (42, 'BP', 'Beeper', 'BP', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (42, 'Internet', 'Internet address', 'Internet', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (42, 'X.400', 'X.400 email address', 'X.400', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (42, 'TDD', 'Telecommunications Device for the Deaf', 'TDD', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (42, 'TTY', 'Teletypewriter', 'TTY', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (5, 'C', 'Current or temporary', 'C', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (5, 'P', 'Permanent', 'P', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (5, 'M', 'Mailing', 'M', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (5, 'B', 'Firm/Business', 'B', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (5, 'O', 'Office', 'O', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (5, 'H', 'Home', 'H', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (5, 'N', 'Birth (nee)', 'N', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (5, 'F', 'Country of origin', 'F', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (5, 'L', 'Legal address', 'L', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (5, 'BDL', 'Birth delivery location', 'BDL', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (5, 'BR', 'Residence at birth', 'BR', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (5, 'RH', 'Registry home', 'RH', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (5, 'BA', 'Bad address', 'BA', 'I');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (7, '1', 'First', '1', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (7, '2', 'Second', '2', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (7, '3', 'Third', '3', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (7, '4', 'Fourth', '4', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (7, '5', 'Fifth', '5', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (8, 'ID', 'Intradermal', 'ID', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (8, 'IM', 'Intramuscular', 'IM', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (8, 'NS', 'Nasal', 'NS', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (8, 'IN', 'Intranasal', 'NS', 'D');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (8, 'IV', 'Intravenous', 'IV', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (8, 'PO', 'Oral', 'PO', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (8, 'OTH', 'Other/Miscellaneous', 'OTH', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (8, 'SC', 'Subcutaneous', 'SC', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (8, 'TD', 'Transdermal', 'TD', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (8, 'C38238', 'Intradermal', 'C38238', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (8, 'C28161', 'Intramuscular', 'C28161', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (8, 'C38284', 'Nasal', 'C38284', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (8, 'C38276', 'Intravenous', 'C38276', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (8, 'C38288', 'Oral', 'C38288', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (8, 'C38676', 'Percutaneous', 'C38676', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (8, 'C38299', 'Subcutaneous', 'C38299', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (8, 'C38305', 'Transdermal', 'C38305', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (9, 'LT', 'Left Thigh', 'LT', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (9, 'LA', 'Left Upper Arm', 'LA', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (9, 'LD', 'Left Deltoid', 'LD', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (9, 'LG', 'Left Gluteous Medius', 'LG', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (9, 'LVL', 'Left Vastus Lateralis', 'LVL', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (9, 'LLFA', 'Left Lower Forearm', 'LLFA', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (9, 'RA', 'Right Upper Arm', 'RA', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (9, 'RT', 'Right Thigh', 'RT', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (9, 'RVL', 'Right Vastus Lateralis', 'RVL', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (9, 'RG', 'Right Gluteous Medius', 'RG', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (9, 'RD', 'Right Deltoid', 'RD', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (9, 'RLFA', 'Right Lower Forearm', 'RLFA', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (2, 'USA', 'United States', 'USA', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (2, 'US', 'United States', 'USA', 'D');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (2, 'MX', 'Mexico', 'MEX', 'D');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (2, 'CA', 'Canada', 'CAN', 'D');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (2, 'MEX', 'Mexico', 'MEX', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (2, 'CAN', 'Canada', 'CAN', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (38, 'R', 'Recurring', 'R', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (39, 'P', 'Production', 'P', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (39, 'T', 'Training', 'T', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (39, 'D', 'Debug', 'D', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (30, '00', 'New immunization record', '00', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (30, '01', 'Historical information - source unspecified', '01', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (30, '02', 'Historical information - from other provider', '01', 'D');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (30, '03', 'Historical information - from parents written record', '01', 'D');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (30, '04', 'Historical information - from parents recall', '01', 'D');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (30, '05', 'Historical information - from other registry', '01', 'D');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (30, '06', 'Historical information - from birth certificate', '01', 'D');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (30, '07', 'Historical information - from school record', '01', 'D');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (30, '08', 'Historical information - from public agency', '01', 'D');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (20, 'ara', 'Arabic', 'ara', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (20, 'arm', 'Armenian', 'arm', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (20, 'cat', 'Catalan; Valencian', 'cat', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (20, 'chi', 'Chinese', 'chi', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (20, 'dan', 'Danish', 'dan', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (20, 'eng', 'English', 'eng', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (20, 'En', 'English', 'eng', 'D');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (20, 'en', 'English', 'eng', 'D');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (20, 'fre', 'French', 'fre', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (20, 'ger', 'German', 'ger', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (20, 'hat', 'Haitian; Haitian Creole', 'hat', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (20, 'heb', 'Hebrew', 'heb', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (20, 'hin', 'Hindi', 'hin', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (20, 'hmn', 'Hmong', 'hmn', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (20, 'jpn', 'Japanese', 'jpn', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (20, 'kor', 'Korean', 'kor', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (20, 'rus', 'Russian', 'rus', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (20, 'som', 'Somali', 'som', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (20, 'spa', 'Spanish; Castilian', 'spa', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (20, 'vie', 'Vietnamese', 'vie', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (21, 'A', 'Alias name', 'A', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (21, 'L', 'Legal name', 'L', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (21, 'D', 'Display name', 'D', 'D');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (21, 'M', 'Maiden name', 'M', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (21, 'C', 'Adopted name', 'C', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (21, 'B', 'Name at birth', 'B', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (21, 'P', 'Name of partner/spouse', 'P', 'I');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (21, 'U', 'Unspecified', 'U', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (19, 'F', 'Female', 'F', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (19, 'M', 'Male', 'M', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (19, 'U', 'Unknown/undifferentiated', 'U', 'I');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (10, 'V01', 'Not VFC eligible', 'V01', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (10, 'V02', 'VFC eligible-Medicaid/Medicaid managed care', 'V02', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (10, 'V03', 'VFC eligible-Uninsured', 'V03', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (10, 'V04', 'VFC eligible- American Indian/Alaskan Native', 'V04', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (10, 'V05', 'VFC eligible-Federally Qualified Health Center Patient (under-insured)', 'V05', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (10, 'V06', 'VFC eligible- State specific eligibility (e.g. S-CHIP plan)', 'V06', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (10, 'V07', 'VFC eligibility- Local-specific eligibility', 'V07', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (10, 'V08', 'Not VFC eligible-Under-insured', 'V08', 'I');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (4, 'AL', 'Alabama', 'AL', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (4, 'AK', 'Alaska', 'AK', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (4, 'AZ', 'Arizona', 'AZ', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (4, 'AR', 'Arkansas', 'AR', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (4, 'CA', 'California', 'CA', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (4, 'CO', 'Colorado', 'CO', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (4, 'CT', 'Connecticut', 'CT', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (4, 'DE', 'Delaware', 'DE', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (4, 'DC', 'District of Columbia', 'DC', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (4, 'FL', 'Florida', 'FL', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (4, 'GA', 'Georgia', 'GA', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (4, 'HI', 'Hawaii', 'HI', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (4, 'ID', 'Idaho', 'ID', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (4, 'IL', 'Illinois', 'IL', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (4, 'IN', 'Indiana', 'IN', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (4, 'IA', 'Iowa', 'IA', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (4, 'KS', 'Kansas', 'KS', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (4, 'KY', 'Kentucky', 'KY', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (4, 'LA', 'Louisiana', 'LA', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (4, 'ME', 'Maine', 'ME', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (4, 'MD', 'Maryland', 'MD', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (4, 'MA', 'Massachusetts', 'MA', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (4, 'MI', 'Michigan', 'MI', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (4, 'MN', 'Minnesota', 'MN', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (4, 'MS', 'Mississippi', 'MS', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (4, 'MO', 'Missouri', 'MO', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (4, 'MT', 'Montana', 'MT', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (4, 'NE', 'Nebraska', 'NE', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (4, 'NV', 'Nevada', 'NV', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (4, 'NH', 'New Hampshire', 'NH', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (4, 'NJ', 'New Jersey', 'NJ', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (4, 'NM', 'New Mexico', 'NM', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (4, 'NY', 'New York', 'NY', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (4, 'NC', 'North Carolina', 'NC', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (4, 'ND', 'North Dakota', 'ND', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (4, 'OH', 'Ohio', 'OH', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (4, 'OK', 'Oklahoma', 'OK', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (4, 'OR', 'Oregon', 'OR', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (4, 'PA', 'Pennsylvania', 'PA', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (4, 'RI', 'Rhode Island', 'RI', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (4, 'SC', 'South Carolina', 'SC', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (4, 'SD', 'South Dakota', 'SD', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (4, 'TN', 'Tennessee', 'TN', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (4, 'TX', 'Texas', 'TX', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (4, 'UT', 'Utah', 'UT', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (4, 'VT', 'Vermont', 'VT', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (4, 'VA', 'Virginia', 'VA', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (4, 'WA', 'Washington', 'WA', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (4, 'WV', 'West Virginia', 'WV', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (4, 'WI', 'Wisconsin', 'WI', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (4, 'WY', 'Wyoming', 'WY', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (4, 'AS', 'American Samoa', 'AS', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (4, 'GU', 'Guam', 'GU', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (4, 'MP', 'Northern Mariana Islands', 'MP', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (4, 'PR', 'Puerto Rico', 'PR', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (4, 'VI', 'Virgin Islands', 'VI', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (4, 'FM', 'Federated States of Micronesia', 'FM', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (4, 'MH', 'Marshall Islands', 'MH', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (4, 'PW', 'Palau', 'PW', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (4, 'AA', 'Armed Forces Americas (except Canada)', 'AA', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (4, 'AE', 'Armed Forces (Europe, Canada, Middle East, Africa)', 'AE', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (4, 'AP', 'Armed Forces Pacific', 'AP', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (4, 'CZ', 'Canal Zone', 'CZ', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (4, 'PI', 'Philippine Islands', 'PI', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (4, 'TT', 'Trust Territory of the Pacific Islands', 'TT', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (4, 'CM', 'Commonwealth of the Northern Mariana Islands', 'CM', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (26, 'CP', 'Complete', 'CP', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (26, 'RE', 'Refused', 'RE', 'G');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (26, 'NA', 'Not Administered', 'NA', 'G');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (26, 'PA', 'Partially Administered', 'PA', 'G');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (22, 'BRO', 'Brother', 'BRO', 'G');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (22, 'CGV', 'Care Giver', 'CGV', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (22, 'FTH', 'Father', 'FTH', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (22, 'FCH', 'Foster Child', 'FCH', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (22, 'GRP', 'Grand Parent', 'GRP', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (22, 'GRD', 'Guardian', 'GRD', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (22, 'MTH', 'Mother', 'MTH', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (22, 'OTH', 'Other', 'OTH', 'G');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (22, 'PAR', 'Parent', 'PAR', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (22, 'SEL', 'Self', 'SEL', 'G');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (22, 'SIB', 'Sibling', 'SIB', 'G');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (22, 'SIS', 'Sister', 'SIS', 'G');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (22, 'SPO', 'Spouse', 'SPO', 'G');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (22, 'SCH', 'Step Child', 'SCH', 'G');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (34, 'RE', 'Observations to follow', 'RE', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (34, 'OK', 'Order accepted & OK', 'OK', 'I');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (18, '1002-5', 'American Indian or Alaska Native', '1002-5', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (18, '2028-9', 'Asian', '2028-9', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (18, '2076-8', 'Native Hawaiian or Other Pacific Islander', '2076-8', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (18, '2054-5', 'Black or African-American', '2054-5', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (18, '2106-3', 'White', '2106-3', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (18, '2131-1', 'Other Race', '2131-1', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (18, 'I', 'American Indian or Alaska Native', '1002-5', 'D');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (18, 'A', 'Asian', '2028-9', 'D');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (18, 'A', 'Native Hawaiian or Other Pacific Islander', '2076-8', 'D');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (18, 'B', 'Black or African-American', '2054-5', 'D');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (18, 'W', 'White', '2106-3', 'D');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (18, 'O', 'Other Race', '2131-1', 'D');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (18, 'U', 'Unknown', '2131-1', 'D');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (14, '2135-2', 'Hispanic or Latino', '2135-2', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (14, '2186-5', 'not Hispanic or Latino', '2186-5', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (14, 'H', 'Hispanic or Latino', '2135-2', 'D');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (14, 'N', 'not Hispanic or Latino', '2186-5', 'D');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (14, 'U', 'Unknown', 'U', 'D');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (6, 'ML', 'Milliliter', 'ML', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (6, 'ml', 'Milliliter', 'ML', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (6, 'mL', 'Milliliter', 'ML', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (6, 'CC', 'Cubic centimeter (ml)', 'ML', 'D');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (43, '64994-7', 'Vaccine funding program eligibility category', '64994-7', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (45, 'AD', 'Address', 'AD', 'G');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (45, 'CE', 'Coded Entry', 'CE', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (45, 'CF', 'Coded Element With Formatted Values', 'CF', 'G');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (45, 'CK', 'Composite ID With Check Digit', 'CK', 'G');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (45, 'CN', 'Composite ID And Name', 'CN', 'G');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (45, 'CP', 'Composite Price', 'CP', 'G');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (45, 'CX', 'Extended Composite ID With Check Digit', 'CX', 'G');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (45, 'DT', 'Date', 'DT', 'G');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (45, 'ED', 'Encapsulated Data', 'ED', 'G');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (45, 'FT', 'Formatted Text (Display)', 'FT', 'G');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (45, 'MO', 'Money', 'MO', 'G');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (45, 'NM', 'Numeric', 'NM', 'G');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (45, 'PN', 'Person Name', 'PN', 'G');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (45, 'RP', 'Reference Pointer', 'RP', 'G');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (45, 'SN', 'Structured Numeric', 'SN', 'G');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (45, 'ST', 'String Data.', 'ST', 'G');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (45, 'TM', 'Time', 'TM', 'G');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (45, 'TN', 'Telephone Number', 'TN', 'G');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (45, 'TS', 'Time Stamp (Date & Time)', 'TS', 'G');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (45, 'TX', 'Text Data (Display)', 'TX', 'G');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (45, 'XAD', 'Extended Address', 'XAD', 'G');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (45, 'XCN', 'Extended Composite Name And Number For Persons', 'XCN', 'G');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (45, 'XON', 'Extended Composite Name And Number For Organizations', 'XON', 'G');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (45, 'XPN', 'Extended Person Name', 'XPN', 'G');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (45, 'XTN', 'Extended Telecommunications Number', 'XTN', 'G');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (24, 'A', 'Active', 'A', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (24, 'I', 'Inactive – Unspecified', 'I', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (24, 'L', 'Inactive – Lost to follow-up', 'L', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (24, 'M', 'Inactive – Moved or gone elsewhere', 'M', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (24, 'P', 'Inactive – Permanently inactive', 'P', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (24, 'U', 'Unknown', 'U', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (17, '01', 'No reminder/recall', '01', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (17, '02', 'Reminder/recall – any method', '02', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (17, '03', 'Reminder/recall – no calls', '03', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (17, '04', 'Reminder only – any method', '04', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (17, '05', 'Reminder only – no calls', '05', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (17, '06', 'Recall only – any method', '06', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (17, '07', 'Recall only – no calls', '07', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (17, '08', 'Reminder/recall – to provider', '08', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (17, '09', 'Reminder to provider', '09', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (17, '10', 'Only reminder to provider, no recall', '10', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (17, '11', 'Recall to provider', '11', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (17, '12', 'Only recall to provider, no reminder', '12', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (16, 'Y', 'Yes', 'Y', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (16, 'N', 'No', 'N', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (27, 'R', 'Restricted', 'R', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (27, 'U', 'Usual control', 'U', 'V');
INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (27, 'V', 'Very restricted', 'V', 'V');

CREATE TABLE dqa_application
(
  application_id      INTEGER NOT NULL PRIMARY KEY,
  application_label   VARCHAR(30) NOT NULL,
  application_type    VARCHAR(30) NOT NULL, -- Dev, Test, Prod
  run_this            VARCHAR(1) DEFAULT 'N',
  primary_template_id INTEGER
);

INSERT INTO dqa_application (application_id, application_label, application_type, run_this) VALUES (1, 'DQA Dev', 'Dev', 'Y');
INSERT INTO dqa_application (application_id, application_label, application_type, run_this) VALUES (2, 'ImmTrac', 'Test', 'N');
INSERT INTO dqa_application (application_id, application_label, application_type, run_this) VALUES (3, 'ImmTrac', 'Prod', 'N');
INSERT INTO dqa_application (application_id, application_label, application_type, run_this) VALUES (4, 'MCIR', 'Dev', 'N');
INSERT INTO dqa_application (application_id, application_label, application_type, run_this) VALUES (5, 'MCIR', 'Test', 'N');
INSERT INTO dqa_application (application_id, application_label, application_type, run_this) VALUES (6, 'MCIR', 'Prod', 'N');
INSERT INTO dqa_application (application_id, application_label, application_type, run_this) VALUES (7, 'IHS', 'Dev', 'N');
INSERT INTO dqa_application (application_id, application_label, application_type, run_this) VALUES (8, 'IHS', 'Test', 'N');
INSERT INTO dqa_application (application_id, application_label, application_type, run_this) VALUES (9, 'IHS', 'Prod', 'N');
INSERT INTO dqa_application (application_id, application_label, application_type, run_this) VALUES (10, 'ImmTrac', 'Dev', 'N');
INSERT INTO dqa_application (application_id, application_label, application_type, run_this) VALUES (11, 'OIS', 'Dev', 'N');
INSERT INTO dqa_application (application_id, application_label, application_type, run_this) VALUES (12, 'OIS', 'Test', 'N');
INSERT INTO dqa_application (application_id, application_label, application_type, run_this) VALUES (13, 'OIS', 'Prod', 'N');

UPDATE dqa_application SET primary_template_id = 1 WHERE application_id = 1;
UPDATE dqa_application SET primary_template_id = 3 WHERE application_id = 2;
UPDATE dqa_application SET primary_template_id = 3 WHERE application_id = 3;
UPDATE dqa_application SET primary_template_id = 5 WHERE application_id = 4;
UPDATE dqa_application SET primary_template_id = 5 WHERE application_id = 5;
UPDATE dqa_application SET primary_template_id = 5 WHERE application_id = 6;
UPDATE dqa_application SET primary_template_id = 1 WHERE application_id = 7;
UPDATE dqa_application SET primary_template_id = 1 WHERE application_id = 8;
UPDATE dqa_application SET primary_template_id = 1 WHERE application_id = 9;
UPDATE dqa_application SET primary_template_id = 3 WHERE application_id = 10;
UPDATE dqa_application SET primary_template_id = 1 WHERE application_id = 11;
UPDATE dqa_application SET primary_template_id = 1 WHERE application_id = 12;
UPDATE dqa_application SET primary_template_id = 1 WHERE application_id = 13;

CREATE TABLE dqa_keyed_setting
(
  keyed_id            INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  keyed_code          VARCHAR(50) NOT NULL,
  object_code         VARCHAR(50) NOT NULL,
  object_id           INTEGER NOT NULL,
  keyed_value         VARCHAR(250) NOT NULL
);

INSERT INTO dqa_keyed_setting (keyed_code, object_code, object_id, keyed_value) VALUES ('validate.header.sending_facility.pfs', 'Application', 2, 'Y');
INSERT INTO dqa_keyed_setting (keyed_code, object_code, object_id, keyed_value) VALUES ('validate.header.sending_facility.pfs', 'Application', 3, 'Y');
INSERT INTO dqa_keyed_setting (keyed_code, object_code, object_id, keyed_value) VALUES ('validate.header.sending_facility.pfs', 'Application', 10, 'Y');

INSERT INTO dqa_keyed_setting (keyed_code, object_code, object_id, keyed_value) VALUES ('validate.vaccination.facility.pfs', 'Application', 2, 'Y');
INSERT INTO dqa_keyed_setting (keyed_code, object_code, object_id, keyed_value) VALUES ('validate.vaccination.facility.pfs', 'Application', 3, 'Y');
INSERT INTO dqa_keyed_setting (keyed_code, object_code, object_id, keyed_value) VALUES ('validate.vaccination.facility.pfs', 'Application', 10, 'Y');

CREATE TABLE dqa_vaccine_group
(
  group_id            INTEGER NOT NULL PRIMARY KEY,   
  group_code          VARCHAR(30) NOT NULL,
  group_label         VARCHAR(250) NOT NULL,
  group_status        VARCHAR(30) NOT NULL 
);

INSERT INTO dqa_vaccine_group(group_id, group_code, group_label, group_status) VALUES (2, 'ADENO', 'Adnovirus', 'Not Expected');
INSERT INTO dqa_vaccine_group(group_id, group_code, group_label, group_status) VALUES (3, 'ANTHRAX', 'Anthrax', 'Not Expected');
INSERT INTO dqa_vaccine_group(group_id, group_code, group_label, group_status) VALUES (4, 'BCG', 'BCG', 'Not Expected');
INSERT INTO dqa_vaccine_group(group_id, group_code, group_label, group_status) VALUES (5, 'cholera', 'Cholera', 'Not Expected');
INSERT INTO dqa_vaccine_group(group_id, group_code, group_label, group_status) VALUES (6, 'DTAP', 'DTaP', 'Expected');
INSERT INTO dqa_vaccine_group(group_id, group_code, group_label, group_status) VALUES (7, 'FLU', 'Influenza', 'Expected');
INSERT INTO dqa_vaccine_group(group_id, group_code, group_label, group_status) VALUES (8, 'H1N1 flu', 'H1N1-09', 'Not Expected');
INSERT INTO dqa_vaccine_group(group_id, group_code, group_label, group_status) VALUES (9, 'H5N1 flu', 'Influenza, H5N1-1203', 'Not Expected');
INSERT INTO dqa_vaccine_group(group_id, group_code, group_label, group_status) VALUES (10, 'HBIG', 'HBIG', 'Not Expected');
INSERT INTO dqa_vaccine_group(group_id, group_code, group_label, group_status) VALUES (11, 'Hep E', 'Hep E', 'Not Expected');
INSERT INTO dqa_vaccine_group(group_id, group_code, group_label, group_status) VALUES (12, 'HepA', 'Hep A', 'Expected');
INSERT INTO dqa_vaccine_group(group_id, group_code, group_label, group_status) VALUES (13, 'HepB', 'Hep B', 'Expected');
INSERT INTO dqa_vaccine_group(group_id, group_code, group_label, group_status) VALUES (14, 'HepC', 'Hep C', 'Not Expected');
INSERT INTO dqa_vaccine_group(group_id, group_code, group_label, group_status) VALUES (15, 'HIB', 'Hib', 'Expected');
INSERT INTO dqa_vaccine_group(group_id, group_code, group_label, group_status) VALUES (16, 'HPV', 'HPV', 'Recommended');
INSERT INTO dqa_vaccine_group(group_id, group_code, group_label, group_status) VALUES (17, 'IG', 'IG', 'Not Expected');
INSERT INTO dqa_vaccine_group(group_id, group_code, group_label, group_status) VALUES (18, 'Japanese encephalitis', 'Japanese Encephalitis', 'Not Expected');
INSERT INTO dqa_vaccine_group(group_id, group_code, group_label, group_status) VALUES (19, 'Lyme disease', 'Lyme Disease', 'Not Expected');
INSERT INTO dqa_vaccine_group(group_id, group_code, group_label, group_status) VALUES (20, 'MEASLES', 'Measles', 'Optional');
INSERT INTO dqa_vaccine_group(group_id, group_code, group_label, group_status) VALUES (21, 'MENING', 'Meningococcal', 'Recommended');
INSERT INTO dqa_vaccine_group(group_id, group_code, group_label, group_status) VALUES (22, 'MMR', 'MMR', 'Expected');
INSERT INTO dqa_vaccine_group(group_id, group_code, group_label, group_status) VALUES (23, 'MUMPS', 'Mumps', 'Optional');
INSERT INTO dqa_vaccine_group(group_id, group_code, group_label, group_status) VALUES (24, 'PneumoPCV', 'Pneumococcal', 'Expected');
INSERT INTO dqa_vaccine_group(group_id, group_code, group_label, group_status) VALUES (25, 'PneumoPPV', 'Pneumococcal', 'Optional');
INSERT INTO dqa_vaccine_group(group_id, group_code, group_label, group_status) VALUES (26, 'POLIO', 'Polio', 'Expected');
INSERT INTO dqa_vaccine_group(group_id, group_code, group_label, group_status) VALUES (27, 'RABIES', 'Rabies', 'Optional');
INSERT INTO dqa_vaccine_group(group_id, group_code, group_label, group_status) VALUES (28, 'ROTAVIRUS', 'Rotavirus', 'Recommended');
INSERT INTO dqa_vaccine_group(group_id, group_code, group_label, group_status) VALUES (29, 'RUBELLA', 'Rubella', 'Optional');
INSERT INTO dqa_vaccine_group(group_id, group_code, group_label, group_status) VALUES (30, 'Td', 'Tdap', 'Expected');
INSERT INTO dqa_vaccine_group(group_id, group_code, group_label, group_status) VALUES (31, 'TYPHOID', 'Typhoid', 'Optional');
INSERT INTO dqa_vaccine_group(group_id, group_code, group_label, group_status) VALUES (32, 'VACCINIA', 'Vaccinia (smallpox)', 'Optional');
INSERT INTO dqa_vaccine_group(group_id, group_code, group_label, group_status) VALUES (33, 'VARICELLA', 'Varicella', 'Expected');
INSERT INTO dqa_vaccine_group(group_id, group_code, group_label, group_status) VALUES (34, 'VEE', 'VEE', 'Optional');
INSERT INTO dqa_vaccine_group(group_id, group_code, group_label, group_status) VALUES (35, 'YELLOWFEVER', 'Yellow Fever', 'Optional');
INSERT INTO dqa_vaccine_group(group_id, group_code, group_label, group_status) VALUES (36, 'ZOSTER', 'Zoster', 'Optional');

CREATE TABLE dqa_vaccine_cvx_group
(
  cvx_group_id        INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  group_id            INTEGER NOT NULL,
  cvx_id              INTEGER NOT NULL
);

INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (2, '54');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (2, '55');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (2, '82');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (3, '24');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (4, '19');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (5, '26');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (17, '29');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (6, '28');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (6, '20');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (6, '106');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (6, '107');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (6, '110');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (13, '110');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (26, '110');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (6, '50');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (15, '50');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (6, '120');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (15, '120');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (26, '120');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (6, '130');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (26, '130');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (6, '132');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (13, '132');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (15, '132');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (26, '132');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (6, '01');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (6, '22');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (15, '22');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (6, '102');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (13, '102');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (15, '102');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (10, '30');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (12, '52');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (12, '83');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (12, '84');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (12, '31');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (12, '85');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (12, '104');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (13, '104');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (13, '08');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (13, '42');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (13, '43');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (13, '44');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (13, '45');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (14, '58');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (11, '59');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (15, '47');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (15, '46');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (15, '49');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (15, '48');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (15, '17');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (13, '51');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (15, '51');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (16, '118');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (16, '62');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (16, '137');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (17, '86');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (17, '14');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (17, '87');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (9, '123');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (7, '135');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (7, '111');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (7, '141');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (7, '140');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (7, '15');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (7, '88');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (7, '16');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (26, '10');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (18, '134');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (18, '39');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (19, '66');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (20, '04');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (29, '04');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (20, '05');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (21, '103');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (21, '136');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (21, '114');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (21, '32');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (21, '108');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (22, '03');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (22, '94');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (33, '94');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (23, '07');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (8, '127');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (8, '128');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (8, '125');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (8, '126');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (26, '02');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (24, '133');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (24, '100');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (25, '33');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (26, '89');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (27, '40');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (27, '18');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (27, '90');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (17, '34');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (28, '119');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (28, '116');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (28, '74');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (28, '122');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (17, '71');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (29, '06');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (23, '38');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (29, '38');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (30, '138');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (30, '113');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (30, '09');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (30, '115');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (31, '25');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (31, '41');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (31, '53');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (31, '91');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (31, '101');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (32, '75');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (32, '105');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (33, '21');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (34, '81');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (34, '80');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (34, '92');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (35, '37');
INSERT INTO dqa_vaccine_cvx_group (group_id, cvx_id) VALUES (36, '121');

CREATE TABLE dqa_batch_code_received
(
  batch_code_received_id    INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  batch_id                  INTEGER,
  coded_id                  INTEGER,
  received_count            INTEGER
);

CREATE TABLE dqa_batch_vaccine_cvx
(
  batch_vaccine_cvx_id    INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  batch_id                INTEGER,
  cvx_id                  INTEGER,
  received_count          INTEGER
);

CREATE TABLE dqa_batch_report 
(
  batch_report_id            INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  batch_id                   INTEGER NOT NULL,
  comp_patient_score         INTEGER,
  comp_score                 INTEGER,
  comp_vaccination_score     INTEGER,
  comp_vaccine_group_score   INTEGER,
  message_count              INTEGER,
  message_with_admin_count   INTEGER,
  next_of_kin_count          INTEGER,
  overall_score              INTEGER,
  patient_count              INTEGER,
  patient_underage_count     INTEGER,
  qual_error_score           INTEGER,
  qual_score                 INTEGER,
  qual_warn_score            INTEGER,
  time_average               FLOAT,
  time_date_first            DATE,
  time_date_last             DATE,
  time_score                 INTEGER,
  vacc_admin_count           INTEGER,
  vacc_delete_count          INTEGER,
  vacc_historical_count      INTEGER,
  vacc_not_admin_count       INTEGER,
  time_score_early           INTEGER,
  time_score_on_time         INTEGER,
  time_score_late            INTEGER,
  time_score_very_late       INTEGER,
  time_count_early           INTEGER,
  time_count_on_time         INTEGER,
  time_count_late            INTEGER,
  time_count_very_late       INTEGER,
  time_count_old_data        INTEGER
);

CREATE TABLE dqa_report_template
(
  template_id        INTEGER NOT NULL PRIMARY KEY,
  template_label     VARCHAR(120) NOT NULL,
  report_type_id     INTEGER NOT NULL,
  report_definition  TEXT,
  base_profile_id    INTEGER
);

INSERT INTO dqa_report_template(template_id, template_label, report_type_id, report_definition, base_profile_id) VALUES(1, 'CDC Vacc Admin', 1, '<dqa-scoring>
  <section name="completeness" weight="50">
    <section name="patient" weight="45">
      <section name="required" weight="14">
        <score label="Patient Id" hl7Reference="PID-3" denominator="patient count" numerator="Patient submitter id" weight="10" />
        <score label="First Name" hl7Reference="PID-5.2" denominator="patient count" numerator="Patient name first" weight="5" />
        <score label="Last Name" hl7Reference="PID-5.1" denominator="patient count" numerator="Patient name last" weight="5" />
        <score label="Possible Test Name" hl7Reference="" denominator="patient count" numerator="Patient name may be test name" weight="-5" />
        <score label="Possible Baby Name" hl7Reference="" denominator="patient count" numerator="Patient name may be temporary newborn name" weight="-10" />
        <score label="Birth Date" hl7Reference="PID-7" denominator="patient count" numerator="Patient birth date" weight="10" />
        <score label="Sex" hl7Reference="PID-8" denominator="patient count" numerator="Patient gender" weight="5" />
        <score label="Address" hl7Reference="PID-11" denominator="patient count" numerator="Patient address" weight="2">
          <score label="Street" hl7Reference="PID-1" denominator="patient count" numerator="Patient address street" weight="5" />
          <score label="City" hl7Reference="PID-3" denominator="patient count" numerator="Patient address city" weight="1" />
          <score label="State" hl7Reference="PID-4" denominator="patient count" numerator="Patient address state" weight="1" />
          <score label="Zip" hl7Reference="PID-5" denominator="patient count" numerator="Patient address zip" weight="1" />
        </score>
      </section>
      <section name="expected" weight="4">
        <score label="Middle Name" hl7Reference="PID-5.3" denominator="patient count" numerator="Patient middle name" weight="10" />
        <score label="Phone" hl7Reference="PID-13" denominator="patient count" numerator="Patient phone" weight="10" />
        <score label="Mother''s Maiden" hl7Reference="PID-6" denominator="patient count" numerator="Patient mother''s maiden name" weight="10" />
      </section>
      <section name="recommended" weight="2">
        <score label="Ethnicity" hl7Reference="PID-22" denominator="patient count" numerator="Patient ethnicity" weight="10" />
        <score label="Race" hl7Reference="PID-10" denominator="patient count" numerator="Patient race" weight="10" />
        <score label="Responsible Party" hl7Reference="NK1" denominator="patient count" numerator="Patient guardian responsible party" weight="1">
          <score label="First Name" hl7Reference="NK1-2.2" denominator="patient count" numerator="Patient guardian name first" weight="4" />
          <score label="Last Name" hl7Reference="NK1-2.1" denominator="patient count" numerator="Patient guardian name last" weight="4" />
          <score label="Same as Patient" hl7Reference="NK1-3" denominator="patient count" numerator="Patient guardian name is same as underage patient" weight="-8" />
          <score label="Relationship" hl7Reference="NK1-3" denominator="patient count" numerator="Patient guardian relationship" weight="1" />
        </score>
      </section>
      <section name="optional" weight="0">
        <score label="Birth Indicator" hl7Reference="PID-24" denominator="patient count" numerator="Patient birth indicator" weight="0" />
        <score label="Medicaid Id" hl7Reference="PID-3" denominator="patient count" numerator="Patient Medicaid number" weight="0" />
        <score label="SSN" hl7Reference="PID-3" denominator="patient count" numerator="Patient SSN" weight="0" />
        <score label="Alias" hl7Reference="PID-5" denominator="patient count" numerator="Patient alias" weight="0" />
        <score label="Primary Language" hl7Reference="PID-15" denominator="patient count" numerator="Patient primary language" weight="0" />
        <score label="Resp Party Address" hl7Reference="NK1-4" denominator="patient count" numerator="Patient guardian address" weight="0">
          <score label="Street" hl7Reference="NK1-4.1" denominator="patient count" numerator="Patient guardian address street" weight="0" />
          <score label="City" hl7Reference="NK1-4.2" denominator="patient count" numerator="Patient guardian address city" weight="0" />
          <score label="State" hl7Reference="NK1-4.3" denominator="patient count" numerator="Patient guardian address state" weight="0" />
          <score label="Zip" hl7Reference="NK1-4.4" denominator="patient count" numerator="Patient guardian address zip" weight="0" />
        </score>
        <score label="Resp Party Phone" hl7Reference="NK1-5" denominator="patient count" numerator="Patient guardian phone" weight="0" />
        <score label="Address County" hl7Reference="PID-11.6" denominator="patient count" numerator="Patient address county" weight="0" />
        <score label="Financial Class" hl7Reference="PV1-20" denominator="patient count" numerator="Patient VFC status" weight="0" />
        <score label="Registry Status" hl7Reference="PD1-16" denominator="patient count" numerator="Patient registry status" weight="0" />
        <score label="Primary Physician" hl7Reference="PD1-4" denominator="patient count" numerator="Patient primary physician id" weight="0">
          <score label="Name" hl7Reference="" denominator="patient count" numerator="Patient primary physician name" weight="0" />
        </score>
        <score label="Primary Facility" hl7Reference="PD1-3" denominator="patient count" numerator="Patient primary facility id" weight="0">
          <score label="Name" hl7Reference="" denominator="patient count" numerator="Patient primary facility name" weight="0" />
        </score>
        <score label="Patient Registry Id" hl7Reference="PID-3" denominator="patient count" numerator="Patient registry id" weight="0" />
        <score label="Protection Indicator" hl7Reference="PD1-12" denominator="patient count" numerator="Patient protection indicator" weight="0" />
        <score label="Publicity Indicator" hl7Reference="PD1-11" denominator="patient count" numerator="Patient publicity code" weight="0" />
      </section>
    </section>
    <section name="vaccination" weight="45">
      <section name="required" weight="14">
        <score label="Vaccination Date" hl7Reference="RXA-3" denominator="vaccination count" numerator="Vaccination admin date" weight="40" />
        <score label="Vaccination Code" hl7Reference="RXA-5" denominator="vaccination count" numerator="Vaccination admin code" weight="40">
          <score label="Not Specific" hl7Reference="" denominator="vaccination admin count" numerator="Vaccination admin code is not specific" weight="-10" />
          <score label="Not Vaccine" hl7Reference="" denominator="vaccination count" numerator="Vaccination admin code is not vaccine" weight="-40" />
          <score label="Valued as Unknown" hl7Reference="" denominator="vaccination count" numerator="Vaccination admin code is valued as unknown" weight="-40" />
        </score>
        <score label="Information Source" hl7Reference="RXA-9" denominator="vaccination count" numerator="Vaccination information source" weight="40">
          <score label="May be Historical" hl7Reference="" denominator="vaccination count" numerator="Vaccination information source is administered but appears to historical"
            weight="-10" />
          <score label="May be Administered" hl7Reference="" denominator="vaccination count" numerator="Vaccination information source is historical but appears to be administered"
            weight="-10" />
        </score>
        <score label="VFC Status" hl7Reference="OBX-5" denominator="vaccination admin count" numerator="Vaccination financial eligibility code" weight="20" />
      </section>
      <section name="expected" weight="4">
        <score label="CVX Code" hl7Reference="RXA-5" denominator="vaccination count" numerator="Vaccination CVX code" weight="20" />
        <score label="Lot Number" hl7Reference="RXA-15" denominator="vaccination admin count" numerator="Vaccination lot number" weight="20" />
        <score label="Manufacturer" hl7Reference="RXA-17" denominator="vaccination admin count" numerator="Vaccination manufacturer code" weight="20" />
      </section>
      <section name="recommended" weight="2">
        <score label="Admin Amount" hl7Reference="RXA-6" denominator="vaccination admin count" numerator="Vaccination administered amount" weight="14">
          <score label="Missing Units" hl7Reference="RXA-7" denominator="vaccination admin count" numerator="Vaccination administered unit is missing" weight="-3" />
        </score>
        <score label="Completion Status" hl7Reference="RXA-20" denominator="vaccination admin count" numerator="Vaccination completion status" weight="6" />
      </section>
      <section name="optional" weight="0">
        <score label="Facility Id" hl7Reference="RXA-11" denominator="vaccination admin count" numerator="Vaccination facility id" weight="0" />
        <score label="Given By Id" hl7Reference="RXA-10" denominator="vaccination admin count" numerator="Vaccination given by" weight="0" />
        <score label="Action Code" hl7Reference="RXA-21" denominator="vaccination count" numerator="Vaccination action code" weight="0" />
        <score label="Ordered By" hl7Reference="ORC-12" denominator="vaccination admin count" numerator="Vaccination ordered by" weight="0" />
        <score label="Entered By" hl7Reference="ORC-10" denominator="vaccination count" numerator="Vaccination recorded by" weight="0" />
        <score label="Refusal Reason" hl7Reference="RXA-18" denominator="vaccination count" numerator="Vaccination refusal reason" weight="0" />
        <score label="Lot Expiration Date" hl7Reference="RXA-16" denominator="vaccination admin count" numerator="Vaccination lot expiration date" weight="0" />
        <score label="CPT Code" hl7Reference="RXA-5" denominator="vaccination count" numerator="Vaccination CPT code" weight="0" />
        <score label="System Entry Date" hl7Reference="RXA-22" denominator="vaccination count" numerator="Vaccination system entry time" weight="0">
          <score label="In Future" hl7Reference="" denominator="vaccination count" numerator="Vaccination system entry time is in future" weight="-5" />
        </score>
        <score label="Vaccination Id" hl7Reference="ORC-3" denominator="vaccination count" numerator="Vaccination id" weight="0" />
      </section>
    </section>
    <section name="vaccineGroup" weight="10">
      <section name="expected" weight="2">
        <section name="DTAP" weight="1" />
        <section name="HepB" weight="1" />
        <section name="POLIO" weight="1" />
        <section name="HIB" weight="1" />
        <section name="FLU" weight="1" />
        <section name="MMR" weight="1" />
        <section name="VARICELLA" weight="1" />
        <section name="PneumoPCV" weight="1" />
      </section>
      <section name="recommended" weight="1">
        <section name="HPV" weight="1" />
        <section name="ROTAVIRUS" weight="1" />
        <section name="Td" weight="1" />
        <section name="HepA" weight="1" />
      </section>
      <section name="optional" weight="0">
        <section name="RABIES" weight="0" />
      </section>
      <section name="unexpected" weight="-2">
        <section name="ANTHRAX" weight="1" />
      </section>
    </section>
  </section>
  <section name="quality" weight="40">
    <section name="errors" weight="28">
    </section>
    <section name="warnings" weight="12">
    </section>
  </section>
  <section name="timeliness" weight="10">
    <section name="early" weight="10" days="1">
    </section>
    <section name="onTime" weight="7" days="3">
    </section>
    <section name="late" weight="4" days="7">
    </section>
    <section name="veryLate" weight="1" days="30">
    </section>
    <section name="oldData" weight="0">
    </section>
  </section>
</dqa-scoring>', 251);
INSERT INTO dqa_report_template(template_id, template_label, report_type_id, report_definition, base_profile_id) VALUES(2, 'CDC Vacc Inventory', 2, '<dqa-scoring>
  <section name="completeness" weight="50">
    <section name="patient" weight="45">
      <section name="required" weight="14">
        <score label="Patient Id" hl7Reference="PID-3" denominator="patient count" numerator="Patient submitter id" weight="10" />
        <score label="First Name" hl7Reference="PID-5.2" denominator="patient count" numerator="Patient name first" weight="5" />
        <score label="Last Name" hl7Reference="PID-5.1" denominator="patient count" numerator="Patient name last" weight="5" />
        <score label="Possible Test Name" hl7Reference="" denominator="patient count" numerator="Patient name may be test name" weight="-5" />
        <score label="Possible Baby Name" hl7Reference="" denominator="patient count" numerator="Patient name may be temporary newborn name" weight="-10" />
        <score label="Birth Date" hl7Reference="PID-7" denominator="patient count" numerator="Patient birth date" weight="10" />
        <score label="Sex" hl7Reference="PID-8" denominator="patient count" numerator="Patient gender" weight="5" />
        <score label="Address" hl7Reference="PID-11" denominator="patient count" numerator="Patient address" weight="2">
          <score label="Street" hl7Reference="PID-1" denominator="patient count" numerator="Patient address street" weight="5" />
          <score label="City" hl7Reference="PID-3" denominator="patient count" numerator="Patient address city" weight="1" />
          <score label="State" hl7Reference="PID-4" denominator="patient count" numerator="Patient address state" weight="1" />
          <score label="Zip" hl7Reference="PID-5" denominator="patient count" numerator="Patient address zip" weight="1" />
        </score>
      </section>
      <section name="expected" weight="4">
        <score label="Middle Name" hl7Reference="PID-5.3" denominator="patient count" numerator="Patient middle name" weight="10" />
        <score label="Phone" hl7Reference="PID-13" denominator="patient count" numerator="Patient phone" weight="10" />
        <score label="Mother''s Maiden" hl7Reference="PID-6" denominator="patient count" numerator="Patient mother''s maiden name" weight="10" />
      </section>
      <section name="recommended" weight="2">
        <score label="Ethnicity" hl7Reference="PID-22" denominator="patient count" numerator="Patient ethnicity" weight="10" />
        <score label="Race" hl7Reference="PID-10" denominator="patient count" numerator="Patient race" weight="10" />
        <score label="Responsible Party" hl7Reference="NK1" denominator="patient count" numerator="Patient guardian responsible party" weight="1">
          <score label="First Name" hl7Reference="NK1-2.2" denominator="patient count" numerator="Patient guardian name first" weight="4" />
          <score label="Last Name" hl7Reference="NK1-2.1" denominator="patient count" numerator="Patient guardian name last" weight="4" />
          <score label="Same as Patient" hl7Reference="NK1-3" denominator="patient count" numerator="Patient guardian name is same as underage patient" weight="-8" />
          <score label="Relationship" hl7Reference="NK1-3" denominator="patient count" numerator="Patient guardian relationship" weight="1" />
        </score>
      </section>
      <section name="optional" weight="0">
        <score label="Birth Indicator" hl7Reference="PID-24" denominator="patient count" numerator="Patient birth indicator" weight="0" />
        <score label="Medicaid Id" hl7Reference="PID-3" denominator="patient count" numerator="Patient Medicaid number" weight="0" />
        <score label="SSN" hl7Reference="PID-3" denominator="patient count" numerator="Patient SSN" weight="0" />
        <score label="Alias" hl7Reference="PID-5" denominator="patient count" numerator="Patient alias" weight="0" />
        <score label="Primary Language" hl7Reference="PID-15" denominator="patient count" numerator="Patient primary language" weight="0" />
        <score label="Resp Party Address" hl7Reference="NK1-4" denominator="patient count" numerator="Patient guardian address" weight="0">
          <score label="Street" hl7Reference="NK1-4.1" denominator="patient count" numerator="Patient guardian address street" weight="0" />
          <score label="City" hl7Reference="NK1-4.2" denominator="patient count" numerator="Patient guardian address city" weight="0" />
          <score label="State" hl7Reference="NK1-4.3" denominator="patient count" numerator="Patient guardian address state" weight="0" />
          <score label="Zip" hl7Reference="NK1-4.4" denominator="patient count" numerator="Patient guardian address zip" weight="0" />
        </score>
        <score label="Resp Party Phone" hl7Reference="NK1-5" denominator="patient count" numerator="Patient guardian phone" weight="0" />
        <score label="Address County" hl7Reference="PID-11.6" denominator="patient count" numerator="Patient address county" weight="0" />
        <score label="Financial Class" hl7Reference="PV1-20" denominator="patient count" numerator="Patient VFC status" weight="0" />
        <score label="Registry Status" hl7Reference="PD1-16" denominator="patient count" numerator="Patient registry status" weight="0" />
        <score label="Primary Physician" hl7Reference="PD1-4" denominator="patient count" numerator="Patient primary physician id" weight="0">
          <score label="Name" hl7Reference="" denominator="patient count" numerator="Patient primary physician name" weight="0" />
        </score>
        <score label="Primary Facility" hl7Reference="PD1-3" denominator="patient count" numerator="Patient primary facility id" weight="0">
          <score label="Name" hl7Reference="" denominator="patient count" numerator="Patient primary facility name" weight="0" />
        </score>
        <score label="Patient Registry Id" hl7Reference="PID-3" denominator="patient count" numerator="Patient registry id" weight="0" />
        <score label="Protection Indicator" hl7Reference="PD1-12" denominator="patient count" numerator="Patient protection indicator" weight="0" />
        <score label="Publicity Indicator" hl7Reference="PD1-11" denominator="patient count" numerator="Patient publicity code" weight="0" />
      </section>
    </section>
    <section name="vaccination" weight="45">
      <section name="required" weight="14">
        <score label="Vaccination Date" hl7Reference="RXA-3" denominator="vaccination count" numerator="Vaccination admin date" weight="40" />
        <score label="Vaccination Code" hl7Reference="RXA-5" denominator="vaccination count" numerator="Vaccination admin code" weight="40">
          <score label="Not Specific" hl7Reference="" denominator="vaccination admin count" numerator="Vaccination admin code is not specific" weight="-10" />
          <score label="Not Vaccine" hl7Reference="" denominator="vaccination count" numerator="Vaccination admin code is not vaccine" weight="-40" />
          <score label="Valued as Unknown" hl7Reference="" denominator="vaccination count" numerator="Vaccination admin code is valued as unknown" weight="-40" />
        </score>
        <score label="Information Source" hl7Reference="RXA-9" denominator="vaccination count" numerator="Vaccination information source" weight="40">
          <score label="May be Historical" hl7Reference="" denominator="vaccination count" numerator="Vaccination information source is administered but appears to historical"
            weight="-10" />
          <score label="May be Administered" hl7Reference="" denominator="vaccination count" numerator="Vaccination information source is historical but appears to be administered"
            weight="-10" />
        </score>
        <score label="VFC Status" hl7Reference="OBX-5" denominator="vaccination admin count" numerator="Vaccination financial eligibility code" weight="20" />
      </section>
      <section name="expected" weight="4">
        <score label="CVX Code" hl7Reference="RXA-5" denominator="vaccination count" numerator="Vaccination CVX code" weight="20" />
        <score label="Lot Number" hl7Reference="RXA-15" denominator="vaccination admin count" numerator="Vaccination lot number" weight="20" />
        <score label="Manufacturer" hl7Reference="RXA-17" denominator="vaccination admin count" numerator="Vaccination manufacturer code" weight="20" />
      </section>
      <section name="recommended" weight="2">
        <score label="Admin Amount" hl7Reference="RXA-6" denominator="vaccination admin count" numerator="Vaccination administered amount" weight="14">
          <score label="Missing Units" hl7Reference="RXA-7" denominator="vaccination admin count" numerator="Vaccination administered unit is missing" weight="-3" />
        </score>
        <score label="Completion Status" hl7Reference="RXA-20" denominator="vaccination admin count" numerator="Vaccination completion status" weight="6" />
      </section>
      <section name="optional" weight="0">
        <score label="Facility Id" hl7Reference="RXA-11" denominator="vaccination admin count" numerator="Vaccination facility id" weight="0" />
        <score label="Given By Id" hl7Reference="RXA-10" denominator="vaccination admin count" numerator="Vaccination given by" weight="0" />
        <score label="Action Code" hl7Reference="RXA-21" denominator="vaccination count" numerator="Vaccination action code" weight="0" />
        <score label="Ordered By" hl7Reference="ORC-12" denominator="vaccination admin count" numerator="Vaccination ordered by" weight="0" />
        <score label="Entered By" hl7Reference="ORC-10" denominator="vaccination count" numerator="Vaccination recorded by" weight="0" />
        <score label="Refusal Reason" hl7Reference="RXA-18" denominator="vaccination count" numerator="Vaccination refusal reason" weight="0" />
        <score label="Lot Expiration Date" hl7Reference="RXA-16" denominator="vaccination admin count" numerator="Vaccination lot expiration date" weight="0" />
        <score label="CPT Code" hl7Reference="RXA-5" denominator="vaccination count" numerator="Vaccination CPT code" weight="0" />
        <score label="System Entry Date" hl7Reference="RXA-22" denominator="vaccination count" numerator="Vaccination system entry time" weight="0">
          <score label="In Future" hl7Reference="" denominator="vaccination count" numerator="Vaccination system entry time is in future" weight="-5" />
        </score>
        <score label="Vaccination Id" hl7Reference="ORC-3" denominator="vaccination count" numerator="Vaccination id" weight="0" />
      </section>
    </section>
    <section name="vaccineGroup" weight="10">
      <section name="expected" weight="2">
        <section name="DTAP" weight="1" />
        <section name="HepB" weight="1" />
        <section name="POLIO" weight="1" />
        <section name="HIB" weight="1" />
        <section name="FLU" weight="1" />
        <section name="MMR" weight="1" />
        <section name="VARICELLA" weight="1" />
        <section name="PneumoPCV" weight="1" />
      </section>
      <section name="recommended" weight="1">
        <section name="HPV" weight="1" />
        <section name="ROTAVIRUS" weight="1" />
        <section name="Td" weight="1" />
        <section name="HepA" weight="1" />
      </section>
      <section name="optional" weight="0">
        <section name="RABIES" weight="0" />
      </section>
      <section name="unexpected" weight="-2">
        <section name="ANTHRAX" weight="1" />
      </section>
    </section>
  </section>
  <section name="quality" weight="40">
    <section name="errors" weight="28">
    </section>
    <section name="warnings" weight="12">
    </section>
  </section>
  <section name="timeliness" weight="10">
    <section name="early" weight="10" days="1">
    </section>
    <section name="onTime" weight="7" days="3">
    </section>
    <section name="late" weight="4" days="7">
    </section>
    <section name="veryLate" weight="1" days="30">
    </section>
    <section name="oldData" weight="0">
    </section>
  </section>
</dqa-scoring>', 252);
INSERT INTO dqa_report_template(template_id, template_label, report_type_id, report_definition, base_profile_id) VALUES(3, 'ImmTrac Vacc Admin', 1, '<dqa-scoring>
  <section name="completeness" weight="50">
    <section name="patient" weight="20">
      <section name="required" weight="10">
        <score label="Patient Id" denominator="patient count" numerator="Patient submitter id" weight="10" />
        <score label="First Name" denominator="patient count" numerator="Patient name first" weight="5" />
        <score label="Last Name" denominator="patient count" numerator="Patient name last" weight="5" />
        <score label="Possible Test Name" denominator="patient count" numerator="Patient name may be test name" weight="-5" />
        <score label="Possible Baby Name" denominator="patient count" numerator="Patient name may be temporary newborn name" weight="-10" />
        <score label="Birth Date" denominator="patient count" numerator="Patient birth date" weight="10" />
        <score label="Sex" denominator="patient count" numerator="Patient gender" weight="5" />
        <score label="Address" denominator="patient count" numerator="Patient address" weight="2">
          <score label="Street" denominator="patient count" numerator="Patient address street" weight="5" />
          <score label="City" denominator="patient count" numerator="Patient address city" weight="1" />
          <score label="State" denominator="patient count" numerator="Patient address state" weight="1" />
          <score label="Zip" denominator="patient count" numerator="Patient address zip" weight="1" />
        </score>
      </section>
      <section name="expected" weight="6">
        <score label="Middle Name" denominator="patient count" numerator="Patient middle name" weight="10" />
        <score label="Phone" denominator="patient count" numerator="Patient phone" weight="10" />
        <score label="Mother''s Maiden" denominator="patient count" numerator="Patient mother''s maiden name" weight="10" />
        <score label="SSN" denominator="patient count" numerator="Patient SSN" weight="10" />
        <score label="Responsible Party" denominator="patient count" numerator="Patient guardian responsible party" weight="1">
          <score label="First Name" denominator="patient count" numerator="Patient guardian name first" weight="4" />
          <score label="Last Name" denominator="patient count" numerator="Patient guardian name last" weight="4" />
          <score label="Same as Patient" denominator="patient count" numerator="Patient guardian name is same as underage patient" weight="-8" />
          <score label="Relationship" denominator="patient count" numerator="Patient guardian relationship" weight="1" />
        </score>
      </section>
      <section name="recommended" weight="4">
        <score label="Birth Indicator" denominator="patient count" numerator="Patient birth indicator" weight="10" />
        <score label="Ethnicity" denominator="patient count" numerator="Patient ethnicity" weight="10" />
        <score label="Race" denominator="patient count" numerator="Patient race" weight="10" />
        <score label="Medicaid Id" denominator="patient count" numerator="Patient Medicaid number" weight="5" />
      </section>
      <section name="optional" weight="0">
        <score label="Alias" denominator="patient count" numerator="Patient alias" weight="0" />
        <score label="Primary Language" denominator="patient count" numerator="Patient primary language" weight="0" />
        <score label="Resp Party Address" denominator="patient count" numerator="Patient guardian address is missing" weight="0">
          <score label="Street" denominator="patient count" numerator="Patient guardian address street" weight="0" />
          <score label="City" denominator="patient count" numerator="Patient guardian address city" weight="0" />
          <score label="State" denominator="patient count" numerator="Patient guardian address state" weight="0" />
          <score label="Zip" denominator="patient count" numerator="Patient guardian address zip" weight="0" />
        </score>
        <score label="Resp Party Phone" denominator="patient count" numerator="Patient guardian phone" weight="0" />
        <score label="Address County" denominator="patient count" numerator="Patient address county" weight="0" />
        <score label="Financial Class" denominator="patient count" numerator="Patient VFC status" weight="0" />
        <score label="Registry Status" denominator="patient count" numerator="Patient registry status" weight="0" />
        <score label="Primary Physician" denominator="patient count" numerator="Patient primary physician id" weight="0">
          <score label="Name" denominator="patient count" numerator="Patient primary physician name" weight="0" />
        </score>
        <score label="Primary Facility" denominator="patient count" numerator="Patient primary facility id" weight="0">
          <score label="Name" denominator="patient count" numerator="Patient primary facility name" weight="0" />
        </score>
        <score label="Patient Registry Id" denominator="patient count" numerator="Patient registry id" weight="0" />
        <score label="Protection Indicator" denominator="patient count" numerator="Patient protection indicator" weight="0" />
        <score label="Publicity Indicator" denominator="patient count" numerator="Patient publicity code" weight="0" />
      </section>
    </section>
    <section name="vaccination" weight="20">
      <section name="required" weight="10">
        <score label="Vaccination Date" denominator="vaccination count" numerator="Vaccination admin date" weight="40" />
        <score label="Vaccination Code" denominator="vaccination count" numerator="Vaccination admin code" weight="40">
          <score label="Not Specific" denominator="vaccination admin count" numerator="Vaccination admin code is not specific" weight="-10" />
          <score label="Not Vaccine" denominator="vaccination count" numerator="Vaccination admin code is not vaccine" weight="-40" />
          <score label="Valued as Unknown" denominator="vaccination count" numerator="Vaccination admin code is valued as unknown" weight="-40" />
        </score>
        <score label="Information Source" denominator="vaccination count" numerator="Vaccination information source" weight="40">
          <score label="May be Historical" denominator="vaccination count" numerator="Vaccination information source is administered but appears to historical"
            weight="-10" />
          <score label="May be Administered" denominator="vaccination count" numerator="Vaccination information source is historical but appears to be administered"
            weight="-10" />
        </score>
      </section>
      <section name="expected" weight="6">
        <score label="CVX Code" denominator="vaccination count" numerator="Vaccination CVX code" weight="20" />
        <score label="Lot Number" denominator="vaccination admin count" numerator="Vaccination lot number" weight="20" />
        <score label="Manufacturer" denominator="vaccination admin count" numerator="Vaccination manufacturer code" weight="20" />
        <score label="Admin Amount" denominator="vaccination admin count" numerator="Vaccination administered amount" weight="10">
          <score label="Missing Units" denominator="vaccination admin count" numerator="Vaccination administered unit is missing" weight="-2" />
        </score>
        <score label="Facility Id" denominator="vaccination admin count" numerator="Vaccination facility id" weight="20" />
      </section>
      <section name="recommended" weight="4">
        <score label="Action Code" denominator="vaccination count" numerator="Vaccination action code" weight="10" />
        <score label="Given By Id" denominator="vaccination admin count" numerator="Vaccination given by" weight="10" />
        <score label="Vaccination Id" denominator="vaccination count" numerator="Vaccination id" weight="10" />
        <score label="Completion Status" denominator="vaccination admin count" numerator="Vaccination completion status" weight="5" />
        <score label="System Entry Date" denominator="vaccination count" numerator="Vaccination system entry time" weight="5">
          <score label="In Future" denominator="vaccination count" numerator="Vaccination system entry time is in future" weight="-5" />
        </score>
      </section>
      <section name="optional" weight="0">
        <score label="Ordered By" denominator="vaccination admin count" numerator="Vaccination ordered by" weight="0" />
        <score label="Entered By" denominator="vaccination count" numerator="Vaccination recorded by" weight="0" />
        <score label="Refusal Reason" denominator="vaccination count" numerator="Vaccination refusal reason" weight="0" />
        <score label="Lot Expiration Date" denominator="vaccination admin count" numerator="Vaccination lot expiration date" weight="0" />
        <score label="CPT Code" denominator="vaccination count" numerator="Vaccination CPT code" weight="0" />
      </section>
    </section>
    <section name="vaccineGroup" weight="10">
      <section name="expected" weight="2">
        <section name="DTAP" weight="1" />
        <section name="HepB" weight="1" />
        <section name="POLIO" weight="1" />
        <section name="HIB" weight="1" />
        <section name="HepA" weight="1" />
        <section name="FLU" weight="1" />
        <section name="MMR" weight="1" />
        <section name="VARICELLA" weight="1" />
        <section name="PneumoPCV" weight="1" />
        <section name="Td" weight="1" />
      </section>
      <section name="recommended" weight="1">
      </section>
      <section name="optional" weight="0">
      </section>
      <section name="unexpected" weight="-2">
      </section>
    </section>
  </section>
  <section name="quality" weight="40">
    <section name="errors" weight="28">
    </section>
    <section name="warnings" weight="12">
    </section>
  </section>
  <section name="timeliness" weight="10">
    <section name="early" weight="10" days="2">
    </section>
    <section name="onTime" weight="9" days="7">
    </section>
    <section name="late" weight="7" days="30">
    </section>
    <section name="veryLate" weight="5" days="60">
    </section>
    <section name="oldData" weight="0">
    </section>
  </section>
</dqa-scoring>', 253);
INSERT INTO dqa_report_template(template_id, template_label, report_type_id, report_definition, base_profile_id) VALUES(4, 'ImmTrac Vacc Inventory', 2, '<dqa-scoring>
  <section name="completeness" weight="50">
    <section name="patient" weight="20">
      <section name="required" weight="10">
        <score label="Patient Id" denominator="patient count" numerator="Patient submitter id" weight="10" />
        <score label="First Name" denominator="patient count" numerator="Patient name first" weight="5" />
        <score label="Last Name" denominator="patient count" numerator="Patient name last" weight="5" />
        <score label="Possible Test Name" denominator="patient count" numerator="Patient name may be test name" weight="-5" />
        <score label="Possible Baby Name" denominator="patient count" numerator="Patient name may be temporary newborn name" weight="-10" />
        <score label="Birth Date" denominator="patient count" numerator="Patient birth date" weight="10" />
        <score label="Sex" denominator="patient count" numerator="Patient gender" weight="5" />
        <score label="Address" denominator="patient count" numerator="Patient address" weight="2">
          <score label="Street" denominator="patient count" numerator="Patient address street" weight="5" />
          <score label="City" denominator="patient count" numerator="Patient address city" weight="1" />
          <score label="State" denominator="patient count" numerator="Patient address state" weight="1" />
          <score label="Zip" denominator="patient count" numerator="Patient address zip" weight="1" />
        </score>
      </section>
      <section name="expected" weight="6">
        <score label="Middle Name" denominator="patient count" numerator="Patient middle name" weight="10" />
        <score label="Phone" denominator="patient count" numerator="Patient phone" weight="10" />
        <score label="Mother''s Maiden" denominator="patient count" numerator="Patient mother''s maiden name" weight="10" />
        <score label="SSN" denominator="patient count" numerator="Patient SSN" weight="10" />
        <score label="Responsible Party" denominator="patient count" numerator="Patient guardian responsible party" weight="1">
          <score label="First Name" denominator="patient count" numerator="Patient guardian name first" weight="4" />
          <score label="Last Name" denominator="patient count" numerator="Patient guardian name last" weight="4" />
          <score label="Same as Patient" denominator="patient count" numerator="Patient guardian name is same as underage patient" weight="-8" />
          <score label="Relationship" denominator="patient count" numerator="Patient guardian relationship" weight="1" />
        </score>
      </section>
      <section name="recommended" weight="4">
        <score label="Birth Indicator" denominator="patient count" numerator="Patient birth indicator" weight="10" />
        <score label="Ethnicity" denominator="patient count" numerator="Patient ethnicity" weight="10" />
        <score label="Race" denominator="patient count" numerator="Patient race" weight="10" />
        <score label="Medicaid Id" denominator="patient count" numerator="Patient Medicaid number" weight="5" />
      </section>
      <section name="optional" weight="0">
        <score label="Alias" denominator="patient count" numerator="Patient alias" weight="0" />
        <score label="Primary Language" denominator="patient count" numerator="Patient primary language" weight="0" />
        <score label="Resp Party Address" denominator="patient count" numerator="Patient guardian address is missing" weight="0">
          <score label="Street" denominator="patient count" numerator="Patient guardian address street" weight="0" />
          <score label="City" denominator="patient count" numerator="Patient guardian address city" weight="0" />
          <score label="State" denominator="patient count" numerator="Patient guardian address state" weight="0" />
          <score label="Zip" denominator="patient count" numerator="Patient guardian address zip" weight="0" />
        </score>
        <score label="Resp Party Phone" denominator="patient count" numerator="Patient guardian phone" weight="0" />
        <score label="Address County" denominator="patient count" numerator="Patient address county" weight="0" />
        <score label="Financial Class" denominator="patient count" numerator="Patient VFC status" weight="0" />
        <score label="Registry Status" denominator="patient count" numerator="Patient registry status" weight="0" />
        <score label="Primary Physician" denominator="patient count" numerator="Patient primary physician id" weight="0">
          <score label="Name" denominator="patient count" numerator="Patient primary physician name" weight="0" />
        </score>
        <score label="Primary Facility" denominator="patient count" numerator="Patient primary facility id" weight="0">
          <score label="Name" denominator="patient count" numerator="Patient primary facility name" weight="0" />
        </score>
        <score label="Patient Registry Id" denominator="patient count" numerator="Patient registry id" weight="0" />
        <score label="Protection Indicator" denominator="patient count" numerator="Patient protection indicator" weight="0" />
        <score label="Publicity Indicator" denominator="patient count" numerator="Patient publicity code" weight="0" />
      </section>
    </section>
    <section name="vaccination" weight="20">
      <section name="required" weight="10">
        <score label="Vaccination Date" denominator="vaccination count" numerator="Vaccination admin date" weight="40" />
        <score label="Vaccination Code" denominator="vaccination count" numerator="Vaccination admin code" weight="40">
          <score label="Not Specific" denominator="vaccination admin count" numerator="Vaccination admin code is not specific" weight="-10" />
          <score label="Not Vaccine" denominator="vaccination count" numerator="Vaccination admin code is not vaccine" weight="-40" />
          <score label="Valued as Unknown" denominator="vaccination count" numerator="Vaccination admin code is valued as unknown" weight="-40" />
        </score>
        <score label="Information Source" denominator="vaccination count" numerator="Vaccination information source" weight="40">
          <score label="May be Historical" denominator="vaccination count" numerator="Vaccination information source is administered but appears to historical"
            weight="-10" />
          <score label="May be Administered" denominator="vaccination count" numerator="Vaccination information source is historical but appears to be administered"
            weight="-10" />
        </score>
      </section>
      <section name="expected" weight="6">
        <score label="CVX Code" denominator="vaccination count" numerator="Vaccination CVX code" weight="20" />
        <score label="Lot Number" denominator="vaccination admin count" numerator="Vaccination lot number" weight="20" />
        <score label="Manufacturer" denominator="vaccination admin count" numerator="Vaccination manufacturer code" weight="20" />
        <score label="Admin Amount" denominator="vaccination admin count" numerator="Vaccination administered amount" weight="10">
          <score label="Missing Units" denominator="vaccination admin count" numerator="Vaccination administered unit is missing" weight="-2" />
        </score>
        <score label="Facility Id" denominator="vaccination admin count" numerator="Vaccination facility id" weight="20" />
      </section>
      <section name="recommended" weight="4">
        <score label="Action Code" denominator="vaccination count" numerator="Vaccination action code" weight="10" />
        <score label="Given By Id" denominator="vaccination admin count" numerator="Vaccination given by" weight="10" />
        <score label="Vaccination Id" denominator="vaccination count" numerator="Vaccination id" weight="10" />
        <score label="Completion Status" denominator="vaccination admin count" numerator="Vaccination completion status" weight="5" />
        <score label="System Entry Date" denominator="vaccination count" numerator="Vaccination system entry time" weight="5">
          <score label="In Future" denominator="vaccination count" numerator="Vaccination system entry time is in future" weight="-5" />
        </score>
      </section>
      <section name="optional" weight="0">
        <score label="Ordered By" denominator="vaccination admin count" numerator="Vaccination ordered by" weight="0" />
        <score label="Entered By" denominator="vaccination count" numerator="Vaccination recorded by" weight="0" />
        <score label="Refusal Reason" denominator="vaccination count" numerator="Vaccination refusal reason" weight="0" />
        <score label="Lot Expiration Date" denominator="vaccination admin count" numerator="Vaccination lot expiration date" weight="0" />
        <score label="CPT Code" denominator="vaccination count" numerator="Vaccination CPT code" weight="0" />
      </section>
    </section>
    <section name="vaccineGroup" weight="10">
      <section name="expected" weight="2">
        <section name="DTAP" weight="1" />
        <section name="HepB" weight="1" />
        <section name="POLIO" weight="1" />
        <section name="HIB" weight="1" />
        <section name="HepA" weight="1" />
        <section name="FLU" weight="1" />
        <section name="MMR" weight="1" />
        <section name="VARICELLA" weight="1" />
        <section name="PneumoPCV" weight="1" />
        <section name="Td" weight="1" />
      </section>
      <section name="recommended" weight="1">
      </section>
      <section name="optional" weight="0">
      </section>
      <section name="unexpected" weight="-2">
      </section>
    </section>
  </section>
  <section name="quality" weight="40">
    <section name="errors" weight="28">
    </section>
    <section name="warnings" weight="12">
    </section>
  </section>
  <section name="timeliness" weight="10">
    <section name="early" weight="10" days="2">
    </section>
    <section name="onTime" weight="9" days="7">
    </section>
    <section name="late" weight="7" days="30">
    </section>
    <section name="veryLate" weight="5" days="60">
    </section>
    <section name="oldData" weight="0">
    </section>
  </section>
</dqa-scoring>', 254);
INSERT INTO dqa_report_template(template_id, template_label, report_type_id, report_definition, base_profile_id) VALUES(5, 'MCIR Vacc Admin', 1, '<dqa-scoring>
  <section name="completeness" weight="50">
    <section name="patient" weight="45">
      <section name="required" weight="14">
        <score label="Patient Id" hl7Reference="PID-3" denominator="patient count" numerator="Patient submitter id" weight="10" />
        <score label="First Name" hl7Reference="PID-5.2" denominator="patient count" numerator="Patient name first" weight="5" />
        <score label="Last Name" hl7Reference="PID-5.1" denominator="patient count" numerator="Patient name last" weight="5" />
        <score label="Possible Test Name" hl7Reference="" denominator="patient count" numerator="Patient name may be test name" weight="-5" />
        <score label="Possible Baby Name" hl7Reference="" denominator="patient count" numerator="Patient name may be temporary newborn name" weight="-10" />
        <score label="Birth Date" hl7Reference="PID-7" denominator="patient count" numerator="Patient birth date" weight="10" />
        <score label="Sex" hl7Reference="PID-8" denominator="patient count" numerator="Patient gender" weight="5" />
        <score label="Address" hl7Reference="PID-11" denominator="patient count" numerator="Patient address" weight="2">
          <score label="Street" hl7Reference="PID-1" denominator="patient count" numerator="Patient address street" weight="5" />
          <score label="City" hl7Reference="PID-3" denominator="patient count" numerator="Patient address city" weight="1" />
          <score label="State" hl7Reference="PID-4" denominator="patient count" numerator="Patient address state" weight="1" />
          <score label="Zip" hl7Reference="PID-5" denominator="patient count" numerator="Patient address zip" weight="1" />
        </score>
      </section>
      <section name="expected" weight="4">
        <score label="Middle Name" hl7Reference="PID-5.3" denominator="patient count" numerator="Patient middle name" weight="10" />
        <score label="Phone" hl7Reference="PID-13" denominator="patient count" numerator="Patient phone" weight="10" />
        <score label="Mother''s Maiden" hl7Reference="PID-6" denominator="patient count" numerator="Patient mother''s maiden name" weight="10" />
      </section>
      <section name="recommended" weight="2">
        <score label="Ethnicity" hl7Reference="PID-22" denominator="patient count" numerator="Patient ethnicity" weight="10" />
        <score label="Race" hl7Reference="PID-10" denominator="patient count" numerator="Patient race" weight="10" />
        <score label="Responsible Party" hl7Reference="NK1" denominator="patient count" numerator="Patient guardian responsible party" weight="1">
          <score label="First Name" hl7Reference="NK1-2.2" denominator="patient count" numerator="Patient guardian name first" weight="4" />
          <score label="Last Name" hl7Reference="NK1-2.1" denominator="patient count" numerator="Patient guardian name last" weight="4" />
          <score label="Same as Patient" hl7Reference="NK1-3" denominator="patient count" numerator="Patient guardian name is same as underage patient" weight="-8" />
          <score label="Relationship" hl7Reference="NK1-3" denominator="patient count" numerator="Patient guardian relationship" weight="1" />
        </score>
      </section>
      <section name="optional" weight="0">
        <score label="Birth Indicator" hl7Reference="PID-24" denominator="patient count" numerator="Patient birth indicator" weight="0" />
        <score label="Medicaid Id" hl7Reference="PID-3" denominator="patient count" numerator="Patient Medicaid number" weight="0" />
        <score label="SSN" hl7Reference="PID-3" denominator="patient count" numerator="Patient SSN" weight="0" />
        <score label="Alias" hl7Reference="PID-5" denominator="patient count" numerator="Patient alias" weight="0" />
        <score label="Primary Language" hl7Reference="PID-15" denominator="patient count" numerator="Patient primary language" weight="0" />
        <score label="Resp Party Address" hl7Reference="NK1-4" denominator="patient count" numerator="Patient guardian address" weight="0">
          <score label="Street" hl7Reference="NK1-4.1" denominator="patient count" numerator="Patient guardian address street" weight="0" />
          <score label="City" hl7Reference="NK1-4.2" denominator="patient count" numerator="Patient guardian address city" weight="0" />
          <score label="State" hl7Reference="NK1-4.3" denominator="patient count" numerator="Patient guardian address state" weight="0" />
          <score label="Zip" hl7Reference="NK1-4.4" denominator="patient count" numerator="Patient guardian address zip" weight="0" />
        </score>
        <score label="Resp Party Phone" hl7Reference="NK1-5" denominator="patient count" numerator="Patient guardian phone" weight="0" />
        <score label="Address County" hl7Reference="PID-11.6" denominator="patient count" numerator="Patient address county" weight="0" />
        <score label="Financial Class" hl7Reference="PV1-20" denominator="patient count" numerator="Patient VFC status" weight="0" />
        <score label="Registry Status" hl7Reference="PD1-16" denominator="patient count" numerator="Patient registry status" weight="0" />
        <score label="Primary Physician" hl7Reference="PD1-4" denominator="patient count" numerator="Patient primary physician id" weight="0">
          <score label="Name" hl7Reference="" denominator="patient count" numerator="Patient primary physician name" weight="0" />
        </score>
        <score label="Primary Facility" hl7Reference="PD1-3" denominator="patient count" numerator="Patient primary facility id" weight="0">
          <score label="Name" hl7Reference="" denominator="patient count" numerator="Patient primary facility name" weight="0" />
        </score>
        <score label="Patient Registry Id" hl7Reference="PID-3" denominator="patient count" numerator="Patient registry id" weight="0" />
        <score label="Protection Indicator" hl7Reference="PD1-12" denominator="patient count" numerator="Patient protection indicator" weight="0" />
        <score label="Publicity Indicator" hl7Reference="PD1-11" denominator="patient count" numerator="Patient publicity code" weight="0" />
      </section>
    </section>
    <section name="vaccination" weight="45">
      <section name="required" weight="14">
        <score label="Vaccination Date" hl7Reference="RXA-3" denominator="vaccination count" numerator="Vaccination admin date" weight="40" />
        <score label="Vaccination Code" hl7Reference="RXA-5" denominator="vaccination count" numerator="Vaccination admin code" weight="40">
          <score label="Not Specific" hl7Reference="" denominator="vaccination admin count" numerator="Vaccination admin code is not specific" weight="-10" />
          <score label="Not Vaccine" hl7Reference="" denominator="vaccination count" numerator="Vaccination admin code is not vaccine" weight="-40" />
          <score label="Valued as Unknown" hl7Reference="" denominator="vaccination count" numerator="Vaccination admin code is valued as unknown" weight="-40" />
        </score>
        <score label="Information Source" hl7Reference="RXA-9" denominator="vaccination count" numerator="Vaccination information source" weight="40">
          <score label="May be Historical" hl7Reference="" denominator="vaccination count" numerator="Vaccination information source is administered but appears to historical"
            weight="-10" />
          <score label="May be Administered" hl7Reference="" denominator="vaccination count" numerator="Vaccination information source is historical but appears to be administered"
            weight="-10" />
        </score>
        <score label="VFC Status" hl7Reference="OBX-5" denominator="vaccination admin count" numerator="Vaccination financial eligibility code" weight="20" />
      </section>
      <section name="expected" weight="4">
        <score label="CVX Code" hl7Reference="RXA-5" denominator="vaccination count" numerator="Vaccination CVX code" weight="20" />
        <score label="Lot Number" hl7Reference="RXA-15" denominator="vaccination admin count" numerator="Vaccination lot number" weight="20" />
        <score label="Manufacturer" hl7Reference="RXA-17" denominator="vaccination admin count" numerator="Vaccination manufacturer code" weight="20" />
      </section>
      <section name="recommended" weight="2">
        <score label="Admin Amount" hl7Reference="RXA-6" denominator="vaccination admin count" numerator="Vaccination administered amount" weight="14">
          <score label="Missing Units" hl7Reference="RXA-7" denominator="vaccination admin count" numerator="Vaccination administered unit is missing" weight="-3" />
        </score>
        <score label="Completion Status" hl7Reference="RXA-20" denominator="vaccination admin count" numerator="Vaccination completion status" weight="6" />
      </section>
      <section name="optional" weight="0">
        <score label="Facility Id" hl7Reference="RXA-11" denominator="vaccination admin count" numerator="Vaccination facility id" weight="0" />
        <score label="Given By Id" hl7Reference="RXA-10" denominator="vaccination admin count" numerator="Vaccination given by" weight="0" />
        <score label="Action Code" hl7Reference="RXA-21" denominator="vaccination count" numerator="Vaccination action code" weight="0" />
        <score label="Ordered By" hl7Reference="ORC-12" denominator="vaccination admin count" numerator="Vaccination ordered by" weight="0" />
        <score label="Entered By" hl7Reference="ORC-10" denominator="vaccination count" numerator="Vaccination recorded by" weight="0" />
        <score label="Refusal Reason" hl7Reference="RXA-18" denominator="vaccination count" numerator="Vaccination refusal reason" weight="0" />
        <score label="Lot Expiration Date" hl7Reference="RXA-16" denominator="vaccination admin count" numerator="Vaccination lot expiration date" weight="0" />
        <score label="CPT Code" hl7Reference="RXA-5" denominator="vaccination count" numerator="Vaccination CPT code" weight="0" />
        <score label="System Entry Date" hl7Reference="RXA-22" denominator="vaccination count" numerator="Vaccination system entry time" weight="0">
          <score label="In Future" hl7Reference="" denominator="vaccination count" numerator="Vaccination system entry time is in future" weight="-5" />
        </score>
        <score label="Vaccination Id" hl7Reference="ORC-3" denominator="vaccination count" numerator="Vaccination id" weight="0" />
      </section>
    </section>
    <section name="vaccineGroup" weight="10">
      <section name="expected" weight="2">
        <section name="DTAP" weight="1" />
        <section name="HepB" weight="1" />
        <section name="POLIO" weight="1" />
        <section name="HIB" weight="1" />
        <section name="FLU" weight="1" />
        <section name="MMR" weight="1" />
        <section name="VARICELLA" weight="1" />
        <section name="PneumoPCV" weight="1" />
      </section>
      <section name="recommended" weight="1">
        <section name="HPV" weight="1" />
        <section name="ROTAVIRUS" weight="1" />
        <section name="Td" weight="1" />
        <section name="HepA" weight="1" />
      </section>
      <section name="optional" weight="0">
        <section name="RABIES" weight="0" />
      </section>
      <section name="unexpected" weight="-2">
        <section name="ANTHRAX" weight="1" />
      </section>
    </section>
  </section>
  <section name="quality" weight="40">
    <section name="errors" weight="28">
    </section>
    <section name="warnings" weight="12">
    </section>
  </section>
  <section name="timeliness" weight="10">
    <section name="early" weight="10" days="1">
    </section>
    <section name="onTime" weight="7" days="3">
    </section>
    <section name="late" weight="4" days="7">
    </section>
    <section name="veryLate" weight="1" days="30">
    </section>
    <section name="oldData" weight="0">
    </section>
  </section>
</dqa-scoring>', 255);
INSERT INTO dqa_report_template(template_id, template_label, report_type_id, report_definition, base_profile_id) VALUES(6, 'MCIR Vacc Inventory', 2, '<dqa-scoring>
  <section name="completeness" weight="50">
    <section name="patient" weight="45">
      <section name="required" weight="14">
        <score label="Patient Id" hl7Reference="PID-3" denominator="patient count" numerator="Patient submitter id" weight="10" />
        <score label="First Name" hl7Reference="PID-5.2" denominator="patient count" numerator="Patient name first" weight="5" />
        <score label="Last Name" hl7Reference="PID-5.1" denominator="patient count" numerator="Patient name last" weight="5" />
        <score label="Possible Test Name" hl7Reference="" denominator="patient count" numerator="Patient name may be test name" weight="-5" />
        <score label="Possible Baby Name" hl7Reference="" denominator="patient count" numerator="Patient name may be temporary newborn name" weight="-10" />
        <score label="Birth Date" hl7Reference="PID-7" denominator="patient count" numerator="Patient birth date" weight="10" />
        <score label="Sex" hl7Reference="PID-8" denominator="patient count" numerator="Patient gender" weight="5" />
        <score label="Address" hl7Reference="PID-11" denominator="patient count" numerator="Patient address" weight="2">
          <score label="Street" hl7Reference="PID-1" denominator="patient count" numerator="Patient address street" weight="5" />
          <score label="City" hl7Reference="PID-3" denominator="patient count" numerator="Patient address city" weight="1" />
          <score label="State" hl7Reference="PID-4" denominator="patient count" numerator="Patient address state" weight="1" />
          <score label="Zip" hl7Reference="PID-5" denominator="patient count" numerator="Patient address zip" weight="1" />
        </score>
      </section>
      <section name="expected" weight="4">
        <score label="Middle Name" hl7Reference="PID-5.3" denominator="patient count" numerator="Patient middle name" weight="10" />
        <score label="Phone" hl7Reference="PID-13" denominator="patient count" numerator="Patient phone" weight="10" />
        <score label="Mother''s Maiden" hl7Reference="PID-6" denominator="patient count" numerator="Patient mother''s maiden name" weight="10" />
      </section>
      <section name="recommended" weight="2">
        <score label="Ethnicity" hl7Reference="PID-22" denominator="patient count" numerator="Patient ethnicity" weight="10" />
        <score label="Race" hl7Reference="PID-10" denominator="patient count" numerator="Patient race" weight="10" />
        <score label="Responsible Party" hl7Reference="NK1" denominator="patient count" numerator="Patient guardian responsible party" weight="1">
          <score label="First Name" hl7Reference="NK1-2.2" denominator="patient count" numerator="Patient guardian name first" weight="4" />
          <score label="Last Name" hl7Reference="NK1-2.1" denominator="patient count" numerator="Patient guardian name last" weight="4" />
          <score label="Same as Patient" hl7Reference="NK1-3" denominator="patient count" numerator="Patient guardian name is same as underage patient" weight="-8" />
          <score label="Relationship" hl7Reference="NK1-3" denominator="patient count" numerator="Patient guardian relationship" weight="1" />
        </score>
      </section>
      <section name="optional" weight="0">
        <score label="Birth Indicator" hl7Reference="PID-24" denominator="patient count" numerator="Patient birth indicator" weight="0" />
        <score label="Medicaid Id" hl7Reference="PID-3" denominator="patient count" numerator="Patient Medicaid number" weight="0" />
        <score label="SSN" hl7Reference="PID-3" denominator="patient count" numerator="Patient SSN" weight="0" />
        <score label="Alias" hl7Reference="PID-5" denominator="patient count" numerator="Patient alias" weight="0" />
        <score label="Primary Language" hl7Reference="PID-15" denominator="patient count" numerator="Patient primary language" weight="0" />
        <score label="Resp Party Address" hl7Reference="NK1-4" denominator="patient count" numerator="Patient guardian address" weight="0">
          <score label="Street" hl7Reference="NK1-4.1" denominator="patient count" numerator="Patient guardian address street" weight="0" />
          <score label="City" hl7Reference="NK1-4.2" denominator="patient count" numerator="Patient guardian address city" weight="0" />
          <score label="State" hl7Reference="NK1-4.3" denominator="patient count" numerator="Patient guardian address state" weight="0" />
          <score label="Zip" hl7Reference="NK1-4.4" denominator="patient count" numerator="Patient guardian address zip" weight="0" />
        </score>
        <score label="Resp Party Phone" hl7Reference="NK1-5" denominator="patient count" numerator="Patient guardian phone" weight="0" />
        <score label="Address County" hl7Reference="PID-11.6" denominator="patient count" numerator="Patient address county" weight="0" />
        <score label="Financial Class" hl7Reference="PV1-20" denominator="patient count" numerator="Patient VFC status" weight="0" />
        <score label="Registry Status" hl7Reference="PD1-16" denominator="patient count" numerator="Patient registry status" weight="0" />
        <score label="Primary Physician" hl7Reference="PD1-4" denominator="patient count" numerator="Patient primary physician id" weight="0">
          <score label="Name" hl7Reference="" denominator="patient count" numerator="Patient primary physician name" weight="0" />
        </score>
        <score label="Primary Facility" hl7Reference="PD1-3" denominator="patient count" numerator="Patient primary facility id" weight="0">
          <score label="Name" hl7Reference="" denominator="patient count" numerator="Patient primary facility name" weight="0" />
        </score>
        <score label="Patient Registry Id" hl7Reference="PID-3" denominator="patient count" numerator="Patient registry id" weight="0" />
        <score label="Protection Indicator" hl7Reference="PD1-12" denominator="patient count" numerator="Patient protection indicator" weight="0" />
        <score label="Publicity Indicator" hl7Reference="PD1-11" denominator="patient count" numerator="Patient publicity code" weight="0" />
      </section>
    </section>
    <section name="vaccination" weight="45">
      <section name="required" weight="14">
        <score label="Vaccination Date" hl7Reference="RXA-3" denominator="vaccination count" numerator="Vaccination admin date" weight="40" />
        <score label="Vaccination Code" hl7Reference="RXA-5" denominator="vaccination count" numerator="Vaccination admin code" weight="40">
          <score label="Not Specific" hl7Reference="" denominator="vaccination admin count" numerator="Vaccination admin code is not specific" weight="-10" />
          <score label="Not Vaccine" hl7Reference="" denominator="vaccination count" numerator="Vaccination admin code is not vaccine" weight="-40" />
          <score label="Valued as Unknown" hl7Reference="" denominator="vaccination count" numerator="Vaccination admin code is valued as unknown" weight="-40" />
        </score>
        <score label="Information Source" hl7Reference="RXA-9" denominator="vaccination count" numerator="Vaccination information source" weight="40">
          <score label="May be Historical" hl7Reference="" denominator="vaccination count" numerator="Vaccination information source is administered but appears to historical"
            weight="-10" />
          <score label="May be Administered" hl7Reference="" denominator="vaccination count" numerator="Vaccination information source is historical but appears to be administered"
            weight="-10" />
        </score>
        <score label="VFC Status" hl7Reference="OBX-5" denominator="vaccination admin count" numerator="Vaccination financial eligibility code" weight="20" />
      </section>
      <section name="expected" weight="4">
        <score label="CVX Code" hl7Reference="RXA-5" denominator="vaccination count" numerator="Vaccination CVX code" weight="20" />
        <score label="Lot Number" hl7Reference="RXA-15" denominator="vaccination admin count" numerator="Vaccination lot number" weight="20" />
        <score label="Manufacturer" hl7Reference="RXA-17" denominator="vaccination admin count" numerator="Vaccination manufacturer code" weight="20" />
      </section>
      <section name="recommended" weight="2">
        <score label="Admin Amount" hl7Reference="RXA-6" denominator="vaccination admin count" numerator="Vaccination administered amount" weight="14">
          <score label="Missing Units" hl7Reference="RXA-7" denominator="vaccination admin count" numerator="Vaccination administered unit is missing" weight="-3" />
        </score>
        <score label="Completion Status" hl7Reference="RXA-20" denominator="vaccination admin count" numerator="Vaccination completion status" weight="6" />
      </section>
      <section name="optional" weight="0">
        <score label="Facility Id" hl7Reference="RXA-11" denominator="vaccination admin count" numerator="Vaccination facility id" weight="0" />
        <score label="Given By Id" hl7Reference="RXA-10" denominator="vaccination admin count" numerator="Vaccination given by" weight="0" />
        <score label="Action Code" hl7Reference="RXA-21" denominator="vaccination count" numerator="Vaccination action code" weight="0" />
        <score label="Ordered By" hl7Reference="ORC-12" denominator="vaccination admin count" numerator="Vaccination ordered by" weight="0" />
        <score label="Entered By" hl7Reference="ORC-10" denominator="vaccination count" numerator="Vaccination recorded by" weight="0" />
        <score label="Refusal Reason" hl7Reference="RXA-18" denominator="vaccination count" numerator="Vaccination refusal reason" weight="0" />
        <score label="Lot Expiration Date" hl7Reference="RXA-16" denominator="vaccination admin count" numerator="Vaccination lot expiration date" weight="0" />
        <score label="CPT Code" hl7Reference="RXA-5" denominator="vaccination count" numerator="Vaccination CPT code" weight="0" />
        <score label="System Entry Date" hl7Reference="RXA-22" denominator="vaccination count" numerator="Vaccination system entry time" weight="0">
          <score label="In Future" hl7Reference="" denominator="vaccination count" numerator="Vaccination system entry time is in future" weight="-5" />
        </score>
        <score label="Vaccination Id" hl7Reference="ORC-3" denominator="vaccination count" numerator="Vaccination id" weight="0" />
      </section>
    </section>
    <section name="vaccineGroup" weight="10">
      <section name="expected" weight="2">
        <section name="DTAP" weight="1" />
        <section name="HepB" weight="1" />
        <section name="POLIO" weight="1" />
        <section name="HIB" weight="1" />
        <section name="FLU" weight="1" />
        <section name="MMR" weight="1" />
        <section name="VARICELLA" weight="1" />
        <section name="PneumoPCV" weight="1" />
      </section>
      <section name="recommended" weight="1">
        <section name="HPV" weight="1" />
        <section name="ROTAVIRUS" weight="1" />
        <section name="Td" weight="1" />
        <section name="HepA" weight="1" />
      </section>
      <section name="optional" weight="0">
        <section name="RABIES" weight="0" />
      </section>
      <section name="unexpected" weight="-2">
        <section name="ANTHRAX" weight="1" />
      </section>
    </section>
  </section>
  <section name="quality" weight="40">
    <section name="errors" weight="28">
    </section>
    <section name="warnings" weight="12">
    </section>
  </section>
  <section name="timeliness" weight="10">
    <section name="early" weight="10" days="1">
    </section>
    <section name="onTime" weight="7" days="3">
    </section>
    <section name="late" weight="4" days="7">
    </section>
    <section name="veryLate" weight="1" days="30">
    </section>
    <section name="oldData" weight="0">
    </section>
  </section>
</dqa-scoring>', 256);


CREATE TABLE dqa_report_type
(
  report_type_id    INTEGER NOT NULL PRIMARY KEY,
  report_type_label VARCHAR(120) NOT NULL
);

INSERT INTO dqa_report_type(report_type_id, report_type_label) VALUES (1, 'Vacc Admin');
INSERT INTO dqa_report_type(report_type_id, report_type_label) VALUES (2, 'Vacc Inventory');

CREATE TABLE dqa_report_vaccine_group
(
  report_vaccine_group_id  INTEGER NOT NULL PRIMARY KEY,
  group_id                 INTEGER NOT NULL,
  profile_id               INTEGER NOT NULL,
  group_status             VARCHAR(30) NOT NULL
);

COMMIT;

-- Constraints and Indexes
CREATE INDEX dqa_in_cm_table_id  ON dqa_code_master(table_id);
CREATE INDEX dqa_in_cr_profile_table ON dqa_code_received(profile_id, table_id);
CREATE UNIQUE INDEX dqa_in_pi ON dqa_potential_issue(target_object, target_field, issue_type, field_value);
CREATE UNIQUE INDEX dqa_in_sp ON dqa_submitter_profile(profile_code);
CREATE INDEX dqa_potential_issue_status_key ON dqa_potential_issue_status (issue_id, profile_id);
CREATE INDEX dqa_receive_queue_key ON dqa_receive_queue (batch_id, received_id);
CREATE INDEX dqa_issue_found_key ON dqa_issue_found (received_id, issue_id);
CREATE INDEX dqa_code_received_key ON dqa_code_received (profile_id, table_id, received_value);
CREATE INDEX dqa_batch_issues_key ON dqa_batch_issues (batch_id, issue_id);
CREATE INDEX dqa_batch_actions_key ON dqa_batch_actions (batch_id, action_code);

COMMIT;

ALTER TABLE dqa_organization ADD CONSTRAINT dqa_fk_organization1 FOREIGN KEY(org_parent_id) REFERENCES dqa_organization(org_id);
ALTER TABLE dqa_organization ADD CONSTRAINT dqa_fk_organization2 FOREIGN KEY(primary_profile_id) REFERENCES dqa_submitter_profile(profile_id);
ALTER TABLE dqa_user_account ADD CONSTRAINT dqa_fk_user_account1 FOREIGN KEY(org_id) REFERENCES dqa_organization(org_id);
ALTER TABLE dqa_potential_issue_status ADD CONSTRAINT dqa_fk_potential_issue_status1 FOREIGN KEY(issue_id) REFERENCES dqa_potential_issue(issue_id);
ALTER TABLE dqa_potential_issue_status ADD CONSTRAINT dqa_fk_potential_issue_status2 FOREIGN KEY(profile_id) REFERENCES dqa_submitter_profile(profile_id);
ALTER TABLE dqa_potential_issue_status ADD CONSTRAINT dqa_fk_potential_issue_status3 FOREIGN KEY(action_code) REFERENCES dqa_issue_action(action_code);
ALTER TABLE dqa_message_batch ADD CONSTRAINT dqa_fk_message_batch1 FOREIGN KEY(type_code) REFERENCES dqa_batch_type(type_code);
ALTER TABLE dqa_message_batch ADD CONSTRAINT dqa_fk_message_batch2 FOREIGN KEY(submit_code) REFERENCES dqa_submit_status(submit_code);
ALTER TABLE dqa_message_batch ADD CONSTRAINT dqa_fk_message_batch3 FOREIGN KEY(profile_id) REFERENCES dqa_submitter_profile(profile_id);
ALTER TABLE dqa_receive_queue ADD CONSTRAINT dqa_fk_receive_queue1 FOREIGN KEY(batch_id) REFERENCES dqa_message_batch(batch_id);
ALTER TABLE dqa_receive_queue ADD CONSTRAINT dqa_fk_receive_queue2 FOREIGN KEY(received_id) REFERENCES dqa_message_received(received_id);
ALTER TABLE dqa_receive_queue ADD CONSTRAINT dqa_fk_receive_queue3 FOREIGN KEY(submit_code) REFERENCES dqa_submit_status(submit_code);
ALTER TABLE dqa_message_received ADD CONSTRAINT dqa_fk_message_received1 FOREIGN KEY(profile_id) REFERENCES dqa_submitter_profile(profile_id);
ALTER TABLE dqa_message_received ADD CONSTRAINT dqa_fk_message_received2 FOREIGN KEY(action_code) REFERENCES dqa_issue_action(action_code);
ALTER TABLE dqa_message_received ADD CONSTRAINT dqa_fk_message_received3 FOREIGN KEY(submit_code) REFERENCES dqa_submit_status(submit_code);
ALTER TABLE dqa_next_of_kin ADD CONSTRAINT dqa_fk_next_of_kin1 FOREIGN KEY(received_id) REFERENCES dqa_message_received(received_id);
ALTER TABLE dqa_issue_found ADD CONSTRAINT dqa_fk_issue_found1 FOREIGN KEY(received_id) REFERENCES dqa_message_received(received_id);
ALTER TABLE dqa_issue_found ADD CONSTRAINT dqa_fk_issue_found2 FOREIGN KEY(issue_id) REFERENCES dqa_potential_issue(issue_id);
ALTER TABLE dqa_issue_found ADD CONSTRAINT dqa_fk_issue_found3 FOREIGN KEY(action_code) REFERENCES dqa_issue_action(action_code);
ALTER TABLE dqa_issue_found ADD CONSTRAINT dqa_fk_issue_found4 FOREIGN KEY(code_id) REFERENCES dqa_code_received(code_id);
ALTER TABLE dqa_batch_issues ADD CONSTRAINT dqa_fk_batch_issues1 FOREIGN KEY(batch_id) REFERENCES dqa_message_batch(batch_id);
ALTER TABLE dqa_batch_issues ADD CONSTRAINT dqa_fk_batch_issues2 FOREIGN KEY(issue_id) REFERENCES dqa_potential_issue(issue_id);
ALTER TABLE dqa_batch_actions ADD CONSTRAINT dqa_fk_batch_actions1 FOREIGN KEY(batch_id) REFERENCES dqa_message_batch(batch_id);
ALTER TABLE dqa_batch_actions ADD CONSTRAINT dqa_fk_batch_actions2 FOREIGN KEY(action_code) REFERENCES dqa_issue_action(action_code);
ALTER TABLE dqa_potential_issue ADD CONSTRAINT dqa_fk_potential_issue1 FOREIGN KEY(default_action_code) REFERENCES dqa_issue_action(action_code);
ALTER TABLE dqa_vaccination ADD CONSTRAINT dqa_fk_vaccination2 FOREIGN KEY(received_id) REFERENCES dqa_message_received(received_id);
ALTER TABLE dqa_code_master ADD CONSTRAINT dqa_fk_code_master1 FOREIGN KEY(table_id) REFERENCES dqa_code_table(table_id);
ALTER TABLE dqa_code_master ADD CONSTRAINT dqa_fk_code_master2 FOREIGN KEY(code_status) REFERENCES dqa_code_status(code_status);
ALTER TABLE dqa_batch_code_received ADD CONSTRAINT dqa_fk_batch_code_received1 FOREIGN KEY(batch_id) REFERENCES dqa_message_batch(batch_id);
ALTER TABLE dqa_batch_code_received ADD CONSTRAINT dqa_fk_batch_code_received2 FOREIGN KEY(coded_id) REFERENCES dqa_code_received(code_id);
ALTER TABLE dqa_code_received ADD CONSTRAINT dqa_fk_code_received1 FOREIGN KEY(profile_id) REFERENCES dqa_submitter_profile(profile_id);
ALTER TABLE dqa_code_received ADD CONSTRAINT dqa_fk_code_received2 FOREIGN KEY(table_id) REFERENCES dqa_code_table(table_id);
ALTER TABLE dqa_code_received ADD CONSTRAINT dqa_fk_code_received3 FOREIGN KEY(code_status) REFERENCES dqa_code_status(code_status);
ALTER TABLE dqa_code_received ADD CONSTRAINT dqa_uk_code_received1 UNIQUE(profile_id, table_id, received_value);
ALTER TABLE dqa_vaccine_cvx_group ADD CONSTRAINT dqa_fk_cvx_vaccine_group1 FOREIGN KEY(group_id) REFERENCES dqa_vaccine_group(group_id);
ALTER TABLE dqa_vaccine_cvx_group ADD CONSTRAINT dqa_fk_cvx_vaccine_group2 FOREIGN KEY(cvx_id) REFERENCES dqa_vaccine_cvx(cvx_id);
ALTER TABLE dqa_vaccine_product ADD CONSTRAINT dqa_fk_cvx_vaccine_product1 FOREIGN KEY(cvx_id) REFERENCES dqa_vaccine_cvx(cvx_id);
ALTER TABLE dqa_vaccine_product ADD CONSTRAINT dqa_fk_cvx_vaccine_product2 FOREIGN KEY(mvx_code) REFERENCES dqa_vaccine_mvx(mvx_code);
ALTER TABLE dqa_vaccine_cpt ADD CONSTRAINT dqa_fk_cvx_vaccine_cpt1 FOREIGN KEY(cvx_id) REFERENCES dqa_vaccine_cvx(cvx_id);
ALTER TABLE dqa_batch_vaccine_cvx ADD CONSTRAINT dqa_fk_batch_vaccine_cvx1 FOREIGN KEY(batch_id) REFERENCES dqa_message_batch(batch_id);
ALTER TABLE dqa_batch_vaccine_cvx ADD CONSTRAINT dqa_fk_batch_vaccine_cvx2 FOREIGN KEY(cvx_id) REFERENCES dqa_vaccine_cvx(cvx_id);
ALTER TABLE dqa_submitter_profile ADD CONSTRAINT dqa_fk_submitter_profile1 FOREIGN KEY(org_id) REFERENCES dqa_organization(org_id);

COMMIT;