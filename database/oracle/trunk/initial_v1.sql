-- Sequence
CREATE SEQUENCE dqa_cvx_group_id_sequence INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE dqa_batch_action_sequence INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE dqa_batch_issue_sequence INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE dqa_batch_code_rec_sequence INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE dqa_batch_vaccine_cvx_sequence INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE dqa_organization_id_sequence INCREMENT BY 1 START WITH 100;
CREATE SEQUENCE dqa_profile_id_sequence INCREMENT BY 1 START WITH 1200;
CREATE SEQUENCE dqa_message_received_sequence INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE dqa_code_id_sequence INCREMENT BY 1 START WITH 1000;
CREATE SEQUENCE dqa_patient_id_sequence INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE dqa_next_of_kin_id_sequence INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE dqa_vaccination_id_sequence INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE dqa_keyed_id_sequence INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE dqa_code_master_id_sequence INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE dqa_issue_found_id_sequence INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE dqa_batch_id_sequence INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE dqa_receive_queue_id_sequence INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE dqa_batch_report_id_sequence INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE dqa_template_id_sequence INCREMENT BY 1 START WITH 1200;
CREATE SEQUENCE dqa_report_vac_group_id_seq INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE dqa_potential_status_id_seq INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE dqa_header_id_sequence INCREMENT BY 1 START WITH 1200;

-- Tables and data

CREATE TABLE dqa_user_account
(
  username           VARCHAR2(30) NOT NULL PRIMARY KEY,
  password           VARCHAR2(50) NOT NULL,
  account_type       VARCHAR2(10) NOT NULL, -- Admin, Submitter
  org_id             INTEGER DEFAULT 0,
  email              VARCHAR2(120) NOT NULL
);

INSERT INTO dqa_user_account (username, password, account_type, org_id, email) VALUES ('dqa_admin', 'changeme', 'Admin', 1, 'email@gmail.com');

CREATE TABLE dqa_organization
(
  org_id             INTEGER NOT NULL PRIMARY KEY,
  org_label          VARCHAR2(60) NOT NULL,
  org_parent_id      INTEGER,
  primary_profile_id INTEGER,
  org_local_code     VARCHAR2(60)
);

INSERT INTO dqa_organization (org_id, org_label, org_parent_id, primary_profile_id) VALUES (1, 'IIS', 1, 1);

CREATE TABLE dqa_submitter_profile
(
  profile_id         INTEGER NOT NULL PRIMARY KEY,
  profile_code       VARCHAR2(50) NOT NULL,
  profile_label      VARCHAR2(50) NOT NULL,
  profile_status     VARCHAR2(10) DEFAULT 'Setup' NOT NULL, -- Setup, Test, Prod, Hold, Clos, Template
  org_id             INTEGER NOT NULL,
  data_format        VARCHAR2(5) DEFAULT 'HL7v2' NOT NULL, -- HL7v2
  transfer_priority  VARCHAR2(7) DEFAULT 'Normal' NOT NULL, -- Normal, High, Highest, Low, Lowest
  access_key         VARCHAR2(50),
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

CREATE TABLE dqa_potential_issue
(
  issue_id            INTEGER NOT NULL PRIMARY KEY,
  target_object       VARCHAR2(30) NOT NULL,
  target_field        VARCHAR2(50) NOT NULL,
  issue_type          VARCHAR2(50) NOT NULL,
  field_value         VARCHAR2(50),
  default_action_code VARCHAR2(1) NOT NULL,
  change_priority     VARCHAR2(7) NOT NULL, -- Must, Should, Can, May, Blocked
  report_denominator  VARCHAR2(30) NOT NULL,
  issue_description   VARCHAR2(4000),
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
  potential_issue_status_id INTEGER NOT NULL PRIMARY KEY,
  issue_id           INTEGER NOT NULL,
  profile_id         INTEGER NOT NULL,
  action_code        VARCHAR2(1) NOT NULL,
  expect_min         INTEGER DEFAULT 0 NOT NULL,
  expect_max         INTEGER DEFAULT 100 NOT NULL
);

CREATE TABLE dqa_issue_action
(
  action_code        VARCHAR2(1) NOT NULL PRIMARY KEY,
  action_label       VARCHAR2(30) NOT NULL
);

INSERT INTO dqa_issue_action (action_code, action_label) VALUES ('E', 'Error');
INSERT INTO dqa_issue_action (action_code, action_label) VALUES ('W', 'Warn');
INSERT INTO dqa_issue_action (action_code, action_label) VALUES ('A', 'Accept');
INSERT INTO dqa_issue_action (action_code, action_label) VALUES ('S', 'Skip');

CREATE TABLE dqa_message_batch
(
  batch_id                   INTEGER NOT NULL PRIMARY KEY,
  batch_title                VARCHAR2(60),
  type_code                  VARCHAR2(1),
  start_date                 DATE,
  end_date                   DATE,
  submit_code                VARCHAR2(1),
  profile_id                 INTEGER
);

CREATE TABLE dqa_receive_queue
(
  receive_queue_id    INTEGER NOT NULL PRIMARY KEY,
  batch_id            INTEGER,
  received_id         INTEGER,
  submit_code         VARCHAR2(1)
);

CREATE TABLE dqa_message_received
(
  received_id         INTEGER NOT NULL PRIMARY KEY,
  profile_id          INTEGER NOT NULL,
  received_date       DATE NOT NULL,
  request_text        CLOB,
  response_text       CLOB,
  action_code         VARCHAR2(1),
  submit_code         VARCHAR2(1)
);

CREATE TABLE dqa_issue_found 
(
  issue_found_id      INTEGER NOT NULL PRIMARY KEY,
  received_id         INTEGER NOT NULL,
  issue_id            INTEGER NOT NULL,
  position_id         INTEGER NOT NULL,
  action_code         VARCHAR2(1),
  code_id             INTEGER
);

CREATE TABLE dqa_code_received
(
  code_id             INTEGER NOT NULL PRIMARY KEY,
  code_label          VARCHAR2(30),
  profile_id          INTEGER NOT NULL,
  table_id            INTEGER NOT NULL,
  received_value      VARCHAR2(50) NOT NULL,
  code_value          VARCHAR2(50),
  code_status         VARCHAR2(1) NOT NULL,
  received_count      INTEGER
);

CREATE TABLE dqa_code_status
(
  code_status        VARCHAR2(1) NOT NULL PRIMARY KEY,
  code_label         VARCHAR2(30) NOT NULL
);

INSERT INTO dqa_code_status (code_status, code_label) VALUES ('V', 'Valid');
INSERT INTO dqa_code_status (code_status, code_label) VALUES ('I', 'Invalid');
INSERT INTO dqa_code_status (code_status, code_label) VALUES ('U', 'Unrecognized');
INSERT INTO dqa_code_status (code_status, code_label) VALUES ('D', 'Deprecated');
INSERT INTO dqa_code_status (code_status, code_label) VALUES ('G', 'Ignored');

CREATE TABLE dqa_code_table
(
  table_id             INTEGER NOT NULL PRIMARY KEY,
  table_label          VARCHAR2(50) NOT NULL, 
  default_code_value   VARCHAR2(50)
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
  type_code           VARCHAR2(1) NOT NULL PRIMARY KEY,
  type_label          VARCHAR2(30) NOT NULL
);

INSERT INTO dqa_batch_type (type_code, type_label) VALUES ('D', 'Daily');
INSERT INTO dqa_batch_type (type_code, type_label) VALUES ('W', 'Weekly');
INSERT INTO dqa_batch_type (type_code, type_label) VALUES ('M', 'Monthly');
INSERT INTO dqa_batch_type (type_code, type_label) VALUES ('O', 'Other');
INSERT INTO dqa_batch_type (type_code, type_label) VALUES ('S', 'Submission');

CREATE TABLE dqa_batch_issues
(
  batch_issues_id     INTEGER NOT NULL PRIMARY KEY,
  batch_id            INTEGER NOT NULL,
  issue_id            INTEGER NOT NULL,
  issue_count         INTEGER
);

CREATE TABLE dqa_batch_actions
(
  batch_actions_id    INTEGER NOT NULL PRIMARY KEY,
  batch_id            INTEGER NOT NULL,
  action_code         VARCHAR2(1) NOT NULL,
  action_count        INTEGER
);

CREATE TABLE dqa_submit_status
(
  submit_code         VARCHAR2(1) NOT NULL PRIMARY KEY,
  submit_label        VARCHAR2(30) NOT NULL
);

INSERT INTO dqa_submit_status (submit_code, submit_label) VALUES ('E', 'Excluded');
INSERT INTO dqa_submit_status (submit_code, submit_label) VALUES ('Q', 'Queued');
INSERT INTO dqa_submit_status (submit_code, submit_label) VALUES ('H', 'Hold');
INSERT INTO dqa_submit_status (submit_code, submit_label) VALUES ('P', 'Prepared');
INSERT INTO dqa_submit_status (submit_code, submit_label) VALUES ('S', 'Submitted');

CREATE TABLE dqa_message_header
(
  header_id             INTEGER NOT NULL PRIMARY KEY,
  received_id           INTEGER NOT NULL,
  ack_type_accept       VARCHAR2(250),
  ack_type_application  VARCHAR2(250),
  character_set         VARCHAR2(250),
  country_code          VARCHAR2(250),
  message_control       VARCHAR2(250),
  message_date          DATE,
  message_profile       VARCHAR2(250),
  message_structure     VARCHAR2(250),
  message_trigger       VARCHAR2(250),
  message_type          VARCHAR2(250),
  message_version       VARCHAR2(250),
  processing_status     VARCHAR2(250),
  receiving_application VARCHAR2(250),
  receiving_facility    VARCHAR2(250),
  sending_application   VARCHAR2(250),
  sending_facility      VARCHAR2(250)
);


CREATE TABLE dqa_patient 
(
  patient_id               INTEGER NOT NULL PRIMARY KEY,
  received_id              INTEGER NOT NULL,
  address_city             VARCHAR2(250),
  address_country          VARCHAR2(250),
  address_county_parish    VARCHAR2(250),
  address_state            VARCHAR2(250),
  address_street           VARCHAR2(250),
  address_street2          VARCHAR2(250),
  address_type             VARCHAR2(250),
  address_zip              VARCHAR2(250),
  alias_first              VARCHAR2(250),
  alias_last               VARCHAR2(250),
  alias_middle             VARCHAR2(250),
  alias_prefix             VARCHAR2(250),
  alias_suffix             VARCHAR2(250),
  alias_type_code          VARCHAR2(250),
  birth_date               DATE,
  birth_muliple            VARCHAR2(250),
  birth_order              VARCHAR2(250),
  birth_place              VARCHAR2(250),
  death_date               DATE,
  death_indicator          VARCHAR2(250),
  ethnicity_code           VARCHAR2(250),
  facility_id              VARCHAR2(250),
  facility_name            VARCHAR2(250),
  financial_eligibility    VARCHAR2(250),
  id_medicaid              VARCHAR2(250),
  id_ssn                   VARCHAR2(250),
  id_submitter_assign_auth VARCHAR2(250),
  id_submitter_number      VARCHAR2(250),
  id_submitter_type_code   VARCHAR2(250),
  mother_maiden_name       VARCHAR2(250),
  name_first               VARCHAR2(250),
  name_last                VARCHAR2(250),
  name_middle              VARCHAR2(250),
  name_prefix              VARCHAR2(250),
  name_suffix              VARCHAR2(250),
  name_type_code           VARCHAR2(250),
  phone_number             VARCHAR2(250),
  physician_name_first     VARCHAR2(250),
  physician_name_last      VARCHAR2(250),
  physician_number         VARCHAR2(250),
  primary_language_code    VARCHAR2(250),
  protection_code          VARCHAR2(250),
  publicity_code           VARCHAR2(250),
  race_code                VARCHAR2(250),
  registry_status          VARCHAR2(250),
  sex_code                 VARCHAR2(250),
  skipped                  VARCHAR2(1),
  financial_eligibility_date  DATE
);

CREATE TABLE dqa_next_of_kin
(
  next_of_kin_id           INTEGER NOT NULL PRIMARY KEY,
  received_id              INTEGER NOT NULL,
  position_id              INTEGER NOT NULL,
  address_city             VARCHAR2(250),
  address_country          VARCHAR2(250),
  address_county_parish    VARCHAR2(250),
  address_state            VARCHAR2(250),
  address_street           VARCHAR2(250),
  address_street2          VARCHAR2(250),
  address_type             VARCHAR2(250),
  address_zip              VARCHAR2(250),
  name_first               VARCHAR2(250),
  name_last                VARCHAR2(250),
  name_middle              VARCHAR2(250),
  name_prefix              VARCHAR2(250),
  name_suffix              VARCHAR2(250),
  name_type_code           VARCHAR2(250),
  phone_number             VARCHAR2(250),
  relationship_code        VARCHAR2(250),
  skipped                  VARCHAR2(1)
);

CREATE TABLE dqa_vaccination 
(
  vaccination_id             INTEGER NOT NULL PRIMARY KEY,
  received_id                INTEGER NOT NULL,
  position_id                INTEGER NOT NULL,
  admin_code_cpt             VARCHAR2(250),
  admin_code_cvx             VARCHAR2(250),
  admin_date                 DATE,
  amount                     VARCHAR2(250),
  amount_unit_code           VARCHAR2(250),
  body_route_code            VARCHAR2(250),
  body_site_code             VARCHAR2(250),
  completion_status_code     VARCHAR2(250),
  confidentiality_code       VARCHAR2(250),
  entered_by_number          VARCHAR2(250),
  entered_by_name_first      VARCHAR2(250),
  entered_by_name_last       VARCHAR2(250),
  expiration_date            DATE,
  facility_id                VARCHAR2(250),
  facility_name              VARCHAR2(250),
  financial_eligibility_code VARCHAR2(250),
  given_by_number            VARCHAR2(250),
  given_by_name_last         VARCHAR2(250),
  given_by_name_first        VARCHAR2(250),
  id_submitter               VARCHAR2(250),
  information_source_code    VARCHAR2(250),
  lot_number                 VARCHAR2(250),
  manufacturer_code          VARCHAR2(250),
  ordered_by_number          VARCHAR2(250),
  refusal_code               VARCHAR2(250),
  system_entry_date          DATE,
  vis_publication_date       VARCHAR2(250),
  skipped                    VARCHAR2(1),
  order_control_code         VARCHAR2(250)
);

CREATE TABLE dqa_vaccine_product
(
  product_id          INTEGER NOT NULL PRIMARY KEY,
  product_name        VARCHAR2(250) NOT NULL,
  product_label       VARCHAR2(250) NOT NULL,
  cvx_id              INTEGER NOT NULL,
  mvx_code            VARCHAR2(10) NOT NULL,
  valid_start_date    DATE NOT NULL,
  use_start_date      DATE NOT NULL,
  use_end_date        DATE NOT NULL,
  valid_end_date      DATE NOT NULL
);

INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (2, 'ORIMUNE', 'OPV', 2, 'WAL', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-09-01', 'YYYY-MM-DD'), TO_DATE('2010-09-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (3, 'M-M-R II', 'MMR', 3, 'MSD', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (4, 'ATTENUVAX', 'measles', 5, 'MSD', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (5, 'MERUVAX II', 'rubella', 6, 'MSD', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (6, 'MUMPSVAX', 'mumps', 7, 'MSD', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-09-01', 'YYYY-MM-DD'), TO_DATE('2010-09-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (7, 'ENGERIX B-PEDS', 'Hep B, adolescent or pediatric', 8, 'SKB', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (8, 'RECOMBIVAX-PEDS', 'Hep B, adolescent or pediatric', 8, 'MSD', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (9, 'TD(GENERIC)', 'Td (adult), adsorbed', 9, 'MBL', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (10, 'IPOL', 'IPV', 10, 'PMC', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (11, 'PREVNAR 7', 'pneumococcal conjugate PCV 7', 100, 'WAL', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (12, 'TYPHIM VI', 'typhoid, ViCPs', 101, 'PMC', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (13, 'TWINRIX', 'Hep A-Hep B', 104, 'SKB', TO_DATE('2001-01-01', 'YYYY-MM-DD'), TO_DATE('2001-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (14, 'DAPTACEL', 'DTaP, 5 pertussis antigens', 106, 'PMC', TO_DATE('2002-01-01', 'YYYY-MM-DD'), TO_DATE('2002-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (15, 'PEDIARIX', 'DTaP-Hep B-IPV', 110, 'SKB', TO_DATE('2002-01-01', 'YYYY-MM-DD'), TO_DATE('2002-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (16, 'FLUMIST', 'influenza, live, intranasal', 111, 'MED', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (17, 'DECAVAC', 'Td (adult) preservative free', 113, 'PMC', TO_DATE('2003-01-01', 'YYYY-MM-DD'), TO_DATE('2003-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (18, 'MENACTRA', 'meningococcal MCV4P', 114, 'PMC', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (19, 'ADACEL', 'Tdap', 115, 'PMC', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (20, 'BOOSTRIX', 'Tdap', 115, 'SKB', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (21, 'ROTATEQ', 'rotavirus, pentavalent', 116, 'MSD', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (22, 'CERVARIX', 'HPV, bivalent', 118, 'SKB', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (23, 'ROTARIX', 'rotavirus, monovalent', 119, 'SKB', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (24, 'PENTACEL', 'DTaP-Hib-IPV', 120, 'PMC', TO_DATE('2008-01-01', 'YYYY-MM-DD'), TO_DATE('2008-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (25, 'ZOSTAVAX', 'zoster', 121, 'MSD', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (26, 'KINRIX', 'DTaP-IPV', 130, 'SKB', TO_DATE('2008-01-01', 'YYYY-MM-DD'), TO_DATE('2008-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (27, 'PREVNAR 13', 'Pneumococcal conjugate PCV 13', 133, 'PFR', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (28, 'PREVNAR 13', 'Pneumococcal conjugate PCV 13', 133, 'WAL', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (29, 'IXIARO', 'Japanese Encephalitis IM', 134, 'INT', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (30, 'FLUZONE-HIGH DOSE', 'Influenza, high dose seasonal', 135, 'PMC', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (31, 'MENVEO', 'Meningococcal MCV4O', 136, 'NOV', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (32, 'Afluria, preservative free', 'Influenza, seasonal, injectable, preservative free', 140, 'CSL', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (33, 'AGRIFLU', 'Influenza, seasonal, injectable, preservative free', 140, 'NOV', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (34, 'FLUARIX', 'Influenza, seasonal, injectable, preservative free', 140, 'SKB', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (35, 'FLUVIRIN-PRESERVATIVE FREE', 'Influenza, seasonal, injectable, preservative free', 140, 'NOV', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (36, 'FLUZONE-PRESERVATIVE FREE', 'Influenza, seasonal, injectable, preservative free', 140, 'PMC', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (37, 'AFLURIA', 'Influenza, seasonal, injectable', 141, 'CSL', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (38, 'FLULAVAL', 'Influenza, seasonal, injectable', 141, 'SKB', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (39, 'FLUVIRIN', 'Influenza, seasonal, injectable', 141, 'NOV', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (40, 'FLUZONE', 'Influenza, seasonal, injectable', 141, 'PMC', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (41, 'IMOVAX', 'rabies, intramuscular injection', 18, 'PMC', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (42, 'RABAVERT', 'rabies, intramuscular injection', 18, 'NOV', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (43, 'MYCOBAX', 'BCG', 19, 'PMC', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (44, 'TICE BCG', 'BCG', 19, 'OTC', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (45, 'ACEL-IMUNE', 'DTaP', 20, 'WAL', TO_DATE('1991-01-01', 'YYYY-MM-DD'), TO_DATE('1991-01-01', 'YYYY-MM-DD'), TO_DATE('2002-01-01', 'YYYY-MM-DD'), TO_DATE('2002-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (46, 'CERTIVA', 'DTaP', 20, 'NAV', TO_DATE('1998-01-01', 'YYYY-MM-DD'), TO_DATE('1998-01-01', 'YYYY-MM-DD'), TO_DATE('2001-01-01', 'YYYY-MM-DD'), TO_DATE('2001-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (47, 'INFANRIX', 'DTaP', 20, 'SKB', TO_DATE('1997-01-01', 'YYYY-MM-DD'), TO_DATE('1997-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (48, 'TRIPEDIA', 'DTaP', 20, 'PMC', TO_DATE('1996-01-01', 'YYYY-MM-DD'), TO_DATE('1996-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (49, 'VARIVAX', 'varicella', 21, 'MSD', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (50, 'TETRAMUNE', 'DTP-Hib', 22, 'WAL', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-09-01', 'YYYY-MM-DD'), TO_DATE('2010-09-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (51, 'BIOTHRAX', 'anthrax', 24, 'MIP', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (52, 'VIVOTIF BERNA', 'typhoid, oral', 25, 'BPC', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (53, 'DT(GENERIC)', 'DT (pediatric)', 28, 'PMC', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (54, 'MENOMUNE', 'meningococcal MPSV4', 32, 'PMC', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (55, 'PNEUMOVAX 23', 'pneumococcal polysaccharide PPV23', 33, 'MSD', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (56, 'TETANUS TOXOID (GENERIC)', 'tetanus toxoid, adsorbed', 35, 'PMC', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (57, 'YF-VAX', 'yellow fever', 37, 'PMC', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (58, 'BIAVAX II', 'rubella/mumps', 38, 'MSD', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-09-01', 'YYYY-MM-DD'), TO_DATE('2010-09-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (59, 'JE-VAX', 'Japanese encephalitis SC', 39, 'JPN', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (60, 'IMOVAX ID', 'rabies, intradermal injection', 40, 'PMC', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-08-18', 'YYYY-MM-DD'), TO_DATE('2010-08-18', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (61, 'RabAvert', 'rabies, intradermal injection', 40, 'CHI', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (62, 'ENGERIX-B-ADULT', 'Hep B, adult', 43, 'SKB', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (63, 'RECOMBIVAX-ADULT', 'Hep B, adult', 43, 'MSD', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (64, 'RECOMBIVAX-DIALYSIS', 'Hep B, dialysis', 44, 'MSD', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (65, 'PROHIBIT', 'Hib (PRP-D)', 46, 'PMC', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-09-01', 'YYYY-MM-DD'), TO_DATE('2010-09-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (66, 'HIBTITER', 'Hib (HbOC)', 47, 'WAL', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-08-18', 'YYYY-MM-DD'), TO_DATE('2010-08-18', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (67, 'ACTHIB', 'Hib (PRP-T)', 48, 'PMC', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (68, 'HIBERIX', 'Hib (PRP-T)', 48, 'SKB', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (69, 'OMNIHIB', 'Hib (PRP-T)', 48, 'SKB', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-09-01', 'YYYY-MM-DD'), TO_DATE('2010-09-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (70, 'PEDVAXHIB', 'Hib (PRP-OMP)', 49, 'MSD', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (71, 'TRIHIBIT', 'DTaP-Hib', 50, 'PMC', TO_DATE('1996-01-01', 'YYYY-MM-DD'), TO_DATE('1996-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (72, 'COMVAX', 'Hib-Hep B', 51, 'MSD', TO_DATE('1996-01-01', 'YYYY-MM-DD'), TO_DATE('1996-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (73, 'HAVRIX-ADULT', 'Hep A, adult', 52, 'SKB', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (74, 'VAQTA-ADULT', 'Hep A, adult', 52, 'MSD', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (75, 'TYPHOID-AKD', 'typhoid, parenteral, AKD (U.S. military)', 53, 'USA', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (76, 'GARDASIL', 'HPV, quadrivalent', 62, 'MSD', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (77, 'ACAM2000', 'vaccinia (smallpox)', 75, 'ACA', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (78, 'ACAM2000', 'vaccinia (smallpox)', 75, 'PMC', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (79, 'DRYVAX', 'vaccinia (smallpox)', 75, 'WAL', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-09-01', 'YYYY-MM-DD'), TO_DATE('2010-09-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (80, 'HAVRIX-PEDS', 'Hep A, ped/adol, 2 dose', 83, 'SKB', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (81, 'VAQTA-PEDS', 'Hep A, ped/adol, 2 dose', 83, 'MSD', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (82, 'PROQUAD', 'MMRV', 94, 'MSD', TO_DATE('2005-01-01', 'YYYY-MM-DD'), TO_DATE('2005-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));

CREATE TABLE dqa_vaccine_mvx
(
  mvx_code            VARCHAR2(10) NOT NULL PRIMARY KEY,
  mvx_label           VARCHAR2(250) NOT NULL,
  valid_start_date    DATE NOT NULL,
  use_start_date      DATE NOT NULL,
  use_end_date        DATE NOT NULL,
  valid_end_date      DATE NOT NULL
);

INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('AB', 'Abbott Laboratories', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('ACA', 'Acambis, Inc', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('AD', 'Adams Laboratories, Inc.', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('ALP', 'Alpha Therapeutic Corporation', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('AR', 'Armour', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('AVB', 'Aventis Behring L.L.C.', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('AVI', 'Aviron', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('BA', 'Baxter Healthcare Corporation-inactive', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('BAH', 'Baxter Healthcare Corporation', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('BAY', 'Bayer Corporation', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('BP', 'Berna Products', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('BPC', 'Berna Products Corporation', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('BTP', 'Biotest Pharmaceuticals Corporation', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('MIP', 'Emergent BioDefense Operations Lansing', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('CSL', 'CSL Behring, Inc', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('CNJ', 'Cangene Corporation', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('CMP', 'Celltech Medeva Pharmaceuticals', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('CEN', 'Centeon L.L.C.', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('CHI', 'Chiron Corporation', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('CON', 'Connaught', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('DVC', 'DynPort Vaccine Company, LLC', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('EVN', 'Evans Medical Limited', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('GEO', 'GeoVax Labs, Inc.', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('SKB', 'GlaxoSmithKline', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('GRE', 'Greer Laboratories, Inc.', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('IAG', 'Immuno International AG', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('IUS', 'Immuno-U.S., Inc.', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('INT', 'Intercell Biomedical', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('KGC', 'Korea Green Cross Corporation', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('LED', 'Lederle', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('MBL', 'Massachusetts Biologic Laboratories', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('MA', 'Massachusetts Public Health Biologic Laboratories', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('MED', 'MedImmune, Inc.', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('MSD', 'Merck and Co., Inc.', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('IM', 'Merieux', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('MIL', 'Miles', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('NAB', 'NABI', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('NYB', 'New York Blood Center', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('NAV', 'North American Vaccine, Inc.', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('NOV', 'Novartis Pharmaceutical Corporation', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('NVX', 'Novavax, Inc.', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('OTC', 'Organon Teknika Corporation', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('ORT', 'Ortho-clinical Diagnostics', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('PD', 'Parkedale Pharmaceuticals', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('PWJ', 'PowderJect Pharmaceuticals', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('PRX', 'Praxis Biologics', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('JPN', 'The Research Foundation for Microbial Diseases of Osaka University (BIKEN)', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('PMC', 'sanofi pasteur', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('SCL', 'Sclavo, Inc.', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('SOL', 'Solvay Pharmaceuticals', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('SI', 'Swiss Serum and Vaccine Inst.', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('TAL', 'Talecris Biotherapeutics', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('USA', 'United States Army Medical Research and Material Command', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('VXG', 'VaxGen', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('WA', 'Wyeth-Ayerst', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('WAL', 'Wyeth', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('ZLB', 'ZLB Behring', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('OTH', 'Other manufacturer', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('UNK', 'Unknown manufacturer', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('AKR', 'Akorn, Inc', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('PFR', 'Pfizer, Inc', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('BRR', 'Barr Laboratories', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));

CREATE TABLE dqa_vaccine_cvx
(
  cvx_id              INTEGER NOT NULL PRIMARY KEY,
  cvx_code            VARCHAR2(10) NOT NULL,
  cvx_label           VARCHAR2(250) NOT NULL,
  valid_start_date    DATE NOT NULL,
  use_start_date      DATE NOT NULL,
  use_end_date        DATE NOT NULL,
  valid_end_date      DATE NOT NULL,
  use_month_start     INTEGER NOT NULL,
  use_month_end       INTEGER NOT NULL,
  concept_type        VARCHAR2(30) NOT NULL
);

INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (998,'998', 'no vaccine administered', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'non vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (99,'99', 'RESERVED - do not use', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), 1, 1440, 'non vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (999,'999', 'unknown', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'non vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (143,'143', 'Adenovirus types 4 and 7', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (54,'54', 'adenovirus, type 4', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (55,'55', 'adenovirus, type 7', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (82,'82', 'adenovirus, unspecified formulation', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-09-30', 'YYYY-MM-DD'), TO_DATE('2010-09-30', 'YYYY-MM-DD'), 1, 1440, 'unspecified');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (24,'24', 'anthrax', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (19,'19', 'BCG', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (27,'27', 'botulinum antitoxin', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (26,'26', 'cholera', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), 1, 1440, 'unspecified');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (29,'29', 'CMVIG', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (56,'56', 'dengue fever', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (12,'12', 'diphtheria antitoxin', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (28,'28', 'DT (pediatric)', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (20,'20', 'DTaP', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (106,'106', 'DTaP, 5 pertussis antigens', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (107,'107', 'DTaP, unspecified formulation', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-09-30', 'YYYY-MM-DD'), TO_DATE('2010-09-30', 'YYYY-MM-DD'), 1, 1440, 'unspecified');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (110,'110', 'DTaP-Hep B-IPV', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (50,'50', 'DTaP-Hib', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (120,'120', 'DTaP-Hib-IPV', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (130,'130', 'DTaP-IPV', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (132,'132', 'DTaP-IPV-HIB-HEP B, historical', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (146,'146', 'DTaP,IPV,Hib,HepB', TO_DATE('2011-08-31', 'YYYY-MM-DD'), TO_DATE('2011-08-31', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (1,'01', 'DTP', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (22,'22', 'DTP-Hib', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (102,'102', 'DTP-Hib-Hep B', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (57,'57', 'hantavirus', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (30,'30', 'HBIG', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (52,'52', 'Hep A, adult', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (83,'83', 'Hep A, ped/adol, 2 dose', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (84,'84', 'Hep A, ped/adol, 3 dose', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (31,'31', 'Hep A, pediatric, unspecified formulation', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-09-30', 'YYYY-MM-DD'), TO_DATE('2010-09-30', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (85,'85', 'Hep A, unspecified formulation', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-09-30', 'YYYY-MM-DD'), TO_DATE('2010-09-30', 'YYYY-MM-DD'), 1, 1440, 'unspecified');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (104,'104', 'Hep A-Hep B', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (8,'08', 'Hep B, adolescent or pediatric', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 0, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (42,'42', 'Hep B, adolescent/high risk infant', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), 0, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (43,'43', 'Hep B, adult', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (44,'44', 'Hep B, dialysis', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (45,'45', 'Hep B, unspecified formulation', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-09-30', 'YYYY-MM-DD'), TO_DATE('2010-09-30', 'YYYY-MM-DD'), 0, 1440, 'unspecified');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (58,'58', 'Hep C', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (59,'59', 'Hep E', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (60,'60', 'herpes simplex 2', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (47,'47', 'Hib (HbOC)', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (46,'46', 'Hib (PRP-D)', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (49,'49', 'Hib (PRP-OMP)', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (48,'48', 'Hib (PRP-T)', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (17,'17', 'Hib, unspecified formulation', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-09-30', 'YYYY-MM-DD'), TO_DATE('2010-09-30', 'YYYY-MM-DD'), 1, 1440, 'unspecified');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (51,'51', 'Hib-Hep B', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (61,'61', 'HIV', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (118,'118', 'HPV, bivalent', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (62,'62', 'HPV, quadrivalent', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (137,'137', 'HPV, unspecified formulation', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-09-30', 'YYYY-MM-DD'), TO_DATE('2010-09-30', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (86,'86', 'IG', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (14,'14', 'IG, unspecified formulation', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-09-30', 'YYYY-MM-DD'), TO_DATE('2010-09-30', 'YYYY-MM-DD'), 1, 1440, 'unspecified');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (87,'87', 'IGIV', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (123,'123', 'influenza, H5N1-1203', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (135,'135', 'Influenza, high dose seasonal', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (111,'111', 'influenza, live, intranasal', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (141,'141', 'Influenza, seasonal, injectable', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (140,'140', 'Influenza, seasonal, injectable, preservative free', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (144,'144', 'influenza, seasonal, intradermal, preservative free', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (15,'15', 'influenza, split (incl. purified surface antigen)', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-09-30', 'YYYY-MM-DD'), TO_DATE('2010-09-30', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (88,'88', 'influenza, unspecified formulation', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-09-30', 'YYYY-MM-DD'), TO_DATE('2010-09-30', 'YYYY-MM-DD'), 1, 1440, 'unspecified');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (16,'16', 'influenza, whole', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (10,'10', 'IPV', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (134,'134', 'Japanese Encephalitis IM', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (39,'39', 'Japanese encephalitis SC', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (129,'129', 'Japanese Encephalitis, unspecified formulation', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-09-30', 'YYYY-MM-DD'), TO_DATE('2010-09-30', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (63,'63', 'Junin virus', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (64,'64', 'leishmaniasis', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (65,'65', 'leprosy', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (66,'66', 'Lyme disease', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (4,'04', 'M/R', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (67,'67', 'malaria', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (5,'05', 'measles', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-08-31', 'YYYY-MM-DD'), TO_DATE('2010-08-31', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (68,'68', 'melanoma', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (103,'103', 'meningococcal C conjugate', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (136,'136', 'Meningococcal MCV4O', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (114,'114', 'meningococcal MCV4P', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (32,'32', 'meningococcal MPSV4', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (108,'108', 'meningococcal, unspecified formulation', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-09-30', 'YYYY-MM-DD'), TO_DATE('2010-09-30', 'YYYY-MM-DD'), 1, 1440, 'unspecified');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (3,'03', 'MMR', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (94,'94', 'MMRV', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (7,'07', 'mumps', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (127,'127', 'Novel influenza-H1N1-09', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-08-28', 'YYYY-MM-DD'), TO_DATE('2010-08-28', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (128,'128', 'Novel Influenza-H1N1-09, all formulations', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-08-28', 'YYYY-MM-DD'), TO_DATE('2010-08-28', 'YYYY-MM-DD'), 1, 1440, 'unspecified');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (125,'125', 'Novel Influenza-H1N1-09, nasal', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-08-28', 'YYYY-MM-DD'), TO_DATE('2010-08-28', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (126,'126', 'Novel influenza-H1N1-09, preservative-free', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-08-28', 'YYYY-MM-DD'), TO_DATE('2010-08-28', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (2,'02', 'OPV', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (69,'69', 'parainfluenza-3', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (11,'11', 'pertussis', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (23,'23', 'plague', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (133,'133', 'Pneumococcal conjugate PCV 13', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (100,'100', 'pneumococcal conjugate PCV 7', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (33,'33', 'pneumococcal polysaccharide PPV23', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (109,'109', 'pneumococcal, unspecified formulation', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-09-30', 'YYYY-MM-DD'), TO_DATE('2010-09-30', 'YYYY-MM-DD'), 1, 1440, 'unspecified');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (89,'89', 'polio, unspecified formulation', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-09-30', 'YYYY-MM-DD'), TO_DATE('2010-09-30', 'YYYY-MM-DD'), 1, 1440, 'unspecified');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (70,'70', 'Q fever', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (40,'40', 'rabies, intradermal injection', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (18,'18', 'rabies, intramuscular injection', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (90,'90', 'rabies, unspecified formulation', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-09-30', 'YYYY-MM-DD'), TO_DATE('2010-09-30', 'YYYY-MM-DD'), 1, 1440, 'unspecified');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (72,'72', 'rheumatic fever', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (73,'73', 'Rift Valley fever', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (34,'34', 'RIG', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (119,'119', 'rotavirus, monovalent', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (116,'116', 'rotavirus, pentavalent', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (74,'74', 'rotavirus, tetravalent', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (122,'122', 'rotavirus, unspecified formulation', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-09-30', 'YYYY-MM-DD'), TO_DATE('2010-09-30', 'YYYY-MM-DD'), 1, 1440, 'unspecified');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (71,'71', 'RSV-IGIV', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (93,'93', 'RSV-MAb', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (145,'145', 'RSV-Mab (new)', TO_DATE('2011-08-31', 'YYYY-MM-DD'), TO_DATE('2011-08-31', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (6,'06', 'rubella', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (38,'38', 'rubella/mumps', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (76,'76', 'Staphylococcus bacterio lysate', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (138,'138', 'Td (adult)', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (113,'113', 'Td (adult) preservative free', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (9,'09', 'Td (adult), adsorbed', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (139,'139', 'Td(adult) unspecified formulation', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-09-30', 'YYYY-MM-DD'), TO_DATE('2010-09-30', 'YYYY-MM-DD'), 1, 1440, 'unspecified');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (115,'115', 'Tdap', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (35,'35', 'tetanus toxoid, adsorbed', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (142,'142', 'tetanus toxoid, not adsorbed', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (112,'112', 'tetanus toxoid, unspecified formulation', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-09-30', 'YYYY-MM-DD'), TO_DATE('2010-09-30', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (77,'77', 'tick-borne encephalitis', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (13,'13', 'TIG', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (98,'98', 'TST, unspecified formulation', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-09-30', 'YYYY-MM-DD'), TO_DATE('2010-09-30', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (95,'95', 'TST-OT tine test', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (96,'96', 'TST-PPD intradermal', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (97,'97', 'TST-PPD tine test', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (78,'78', 'tularemia vaccine', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (25,'25', 'typhoid, oral', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (41,'41', 'typhoid, parenteral', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (53,'53', 'typhoid, parenteral, AKD (U.S. military)', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (91,'91', 'typhoid, unspecified formulation', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-09-30', 'YYYY-MM-DD'), TO_DATE('2010-09-30', 'YYYY-MM-DD'), 1, 1440, 'unspecified');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (101,'101', 'typhoid, ViCPs', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (131,'131', 'typhus, historical', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (75,'75', 'vaccinia (smallpox)', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (105,'105', 'vaccinia (smallpox) diluted', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (79,'79', 'vaccinia immune globulin', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (21,'21', 'varicella', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (81,'81', 'VEE, inactivated', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (80,'80', 'VEE, live', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (92,'92', 'VEE, unspecified formulation', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-09-30', 'YYYY-MM-DD'), TO_DATE('2010-09-30', 'YYYY-MM-DD'), 1, 1440, 'unspecified');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (36,'36', 'VZIG', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (117,'117', 'VZIG (IND)', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (37,'37', 'yellow fever', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (121,'121', 'zoster', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (147,'147', 'meningococcal MCV4, unspecified formulation', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'unspecified');


CREATE TABLE dqa_vaccine_cpt
(
  cpt_id              INTEGER NOT NULL PRIMARY KEY,
  cpt_code            VARCHAR2(10) NOT NULL,
  cpt_label           VARCHAR2(250) NOT NULL,
  valid_start_date    DATE NOT NULL,
  use_start_date      DATE NOT NULL,
  use_end_date        DATE NOT NULL,
  valid_end_date      DATE NOT NULL,
  cvx_id              INTEGER NOT NULL
);

INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (2, '90476', 'adenovirus, type 4', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 54);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (3, '90477', 'adenovirus, type 7', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 55);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (4, '90581', 'anthrax', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 24);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (5, '90585', 'BCG', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 19);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (6, '90728', 'BCG', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2011-03-17', 'YYYY-MM-DD'), TO_DATE('2011-03-17', 'YYYY-MM-DD'), 19);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (7, '90287', 'botulinum antitoxin', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 27);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (8, '90725', 'cholera', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 26);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (9, '90291', 'CMVIG', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 29);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (10, '90296', 'diphtheria antitoxin', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 12);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (11, '90702', 'DT (pediatric)', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 28);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (12, '90700', 'DTaP', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 20);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (13, '90700', 'DTaP, 5 pertussis antigens', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 106);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (14, '90723', 'DTaP-Hep B-IPV', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 110);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (15, '90721', 'DTaP-Hib', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 50);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (16, '90698', 'DTaP-Hib-IPV', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 120);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (17, '90696', 'DTaP-IPV', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 130);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (18, '90701', 'DTP', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (19, '90720', 'DTP-Hib', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 22);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (20, '90371', 'HBIG', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 30);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (21, '90632', 'Hep A, adult', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 52);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (22, '90633', 'Hep A, ped/adol, 2 dose', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 83);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (23, '90634', 'Hep A, ped/adol, 3 dose', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 84);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (24, '90730', 'Hep A, unspecified formulation', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2011-03-17', 'YYYY-MM-DD'), TO_DATE('2011-03-17', 'YYYY-MM-DD'), 85);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (25, '90636', 'Hep A-Hep B', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 104);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (26, '90744', 'Hep B, adolescent or pediatric', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 8);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (27, '90745', 'Hep B, adolescent/high risk infant', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2011-03-17', 'YYYY-MM-DD'), TO_DATE('2011-03-17', 'YYYY-MM-DD'), 42);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (28, '90743', 'Hep B, adult', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 43);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (29, '90746', 'Hep B, adult', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 43);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (30, '90740', 'Hep B, dialysis', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 44);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (31, '90747', 'Hep B, dialysis', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 44);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (32, '90731', 'Hep B, unspecified formulation', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2011-03-17', 'YYYY-MM-DD'), TO_DATE('2011-03-17', 'YYYY-MM-DD'), 45);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (33, '90645', 'Hib (HbOC)', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 47);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (34, '90646', 'Hib (PRP-D)', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 46);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (35, '90647', 'Hib (PRP-OMP)', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 49);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (36, '90648', 'Hib (PRP-T)', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 48);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (37, '90737', 'Hib, unspecified formulation', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2011-03-17', 'YYYY-MM-DD'), TO_DATE('2011-03-17', 'YYYY-MM-DD'), 17);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (38, '90748', 'Hib-Hep B', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 51);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (39, '90650', 'HPV, bivalent', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 118);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (40, '90649', 'HPV, quadrivalent', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 62);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (41, '90281', 'IG', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 86);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (42, '90741', 'IG, unspecified formulation', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2011-03-17', 'YYYY-MM-DD'), TO_DATE('2011-03-17', 'YYYY-MM-DD'), 14);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (43, '90283', 'IGIV', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 87);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (44, '90662', 'Influenza, high dose seasonal', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 135);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (45, '90660', 'influenza, live, intranasal', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 111);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (46, '90657', 'Influenza, seasonal, injectable', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 141);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (47, '90658', 'Influenza, seasonal, injectable', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 141);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (48, '90656', 'Influenza, seasonal, injectable, preservative free', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 140);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (49, '90654', 'Influenza, seasonal, injectable, preservative free', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 144);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (50, '90655', 'Influenza, seasonal, injectable, preservative free', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 140);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (51, '90724', 'influenza, unspecified formulation', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2011-03-17', 'YYYY-MM-DD'), TO_DATE('2011-03-17', 'YYYY-MM-DD'), 88);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (52, '90659', 'influenza, whole', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2011-03-17', 'YYYY-MM-DD'), TO_DATE('2011-03-17', 'YYYY-MM-DD'), 16);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (53, '90713', 'IPV', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 10);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (54, '90738', 'Japanese Encephalitis IM', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 134);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (55, '90735', 'Japanese encephalitis SC', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 39);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (56, '90665', 'Lyme disease', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 66);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (57, '90708', 'M/R', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 4);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (58, '90705', 'measles', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 5);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (59, '90734', 'Meningococcal MCV4O', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 136);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (60, '90734', 'meningococcal MCV4P', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 114);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (61, '90733', 'meningococcal MPSV4', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 32);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (62, '90707', 'MMR', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 3);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (63, '90710', 'MMRV', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 94);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (64, '90704', 'mumps', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 7);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (65, '90668', 'Novel influenza-H1N1-09', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-08-31', 'YYYY-MM-DD'), TO_DATE('2010-08-31', 'YYYY-MM-DD'), 127);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (66, '90470', 'Novel Influenza-H1N1-09, all formulations', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-08-31', 'YYYY-MM-DD'), TO_DATE('2010-08-31', 'YYYY-MM-DD'), 128);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (67, '90663', 'Novel Influenza-H1N1-09, all formulations', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-08-31', 'YYYY-MM-DD'), TO_DATE('2010-08-31', 'YYYY-MM-DD'), 128);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (68, '90664', 'Novel Influenza-H1N1-09, nasal', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-08-31', 'YYYY-MM-DD'), TO_DATE('2010-08-31', 'YYYY-MM-DD'), 125);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (69, '90666', 'Novel influenza-H1N1-09, preservative-free', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-08-31', 'YYYY-MM-DD'), TO_DATE('2010-08-31', 'YYYY-MM-DD'), 126);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (70, '90712', 'OPV', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 2);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (71, '90727', 'plague', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 23);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (72, '90670', 'Pneumococcal conjugate PCV 13', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 133);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (73, '90669', 'pneumococcal conjugate PCV 7', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 100);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (74, '90732', 'pneumococcal polysaccharide PPV23', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 33);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (75, '90676', 'rabies, intradermal injection', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 40);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (76, '90675', 'rabies, intramuscular injection', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 18);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (77, '90726', 'rabies, unspecified formulation', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2011-03-17', 'YYYY-MM-DD'), TO_DATE('2011-03-17', 'YYYY-MM-DD'), 90);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (78, '90375', 'RIG', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 34);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (79, '90376', 'RIG', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 34);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (80, '90681', 'rotavirus, monovalent', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 119);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (81, '90680', 'rotavirus, pentavalent', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 116);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (82, '90379', 'RSV-IGIV', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 71);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (83, '90378', 'RSV-MAb', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 93);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (84, '90706', 'rubella', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 6);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (85, '90714', 'Td (adult) preservative free', TO_DATE('2005-01-01', 'YYYY-MM-DD'), TO_DATE('2005-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 113);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (86, '90718', 'Td (adult), adsorbed', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 9);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (87, '90715', 'Tdap', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 115);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (88, '90703', 'tetanus toxoid, adsorbed', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 35);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (89, '90389', 'TIG', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 13);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (90, '90690', 'typhoid, oral', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 25);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (91, '90692', 'typhoid, parenteral', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 41);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (92, '90693', 'typhoid, parenteral, AKD (U.S. military)', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 53);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (93, '90714', 'typhoid, unspecified formulation', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2011-03-17', 'YYYY-MM-DD'), TO_DATE('2011-03-17', 'YYYY-MM-DD'), 91);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (94, '90691', 'typhoid, ViCPs', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 101);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (95, '90393', 'vaccinia immune globulin', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 79);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (96, '90716', 'varicella', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 21);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (97, '90396', 'VZIG', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 36);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (98, '90717', 'yellow fever', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 37);
INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (99, '90736', 'zoster', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 121);

CREATE TABLE dqa_code_master
(
  code_master_id      INTEGER NOT NULL PRIMARY KEY,
  table_id            INTEGER NOT NULL,
  code_value          VARCHAR2(50) NOT NULL,
  code_label          VARCHAR2(250) NOT NULL,
  use_value           VARCHAR2(50) NOT NULL,
  code_status         VARCHAR2(1) NOT NULL
);

INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 25, 'A', 'Add', 'A', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 25, 'D', 'Delete', 'D', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 25, 'U', 'Update', 'U', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 40, 'AL', 'Always', 'AL', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 40, 'NE', 'Never', 'NE', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 40, 'ER', 'Error only', 'ER', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 40, 'SU', 'SU', 'SU', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 41, 'PRN', 'Primary residence number', 'PRN', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 41, 'ORN', 'Other residence number', 'ORN', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 41, 'WPN', 'Work number', 'WPN', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 41, 'VHN', 'Vacation home number', 'VHN', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 41, 'ASN', 'Answering service number', 'ASN', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 41, 'EMR', 'Emergency number', 'EMR', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 41, 'NET', 'Network (email) address', 'NET', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 41, 'BPN', 'Beeper number', 'BPN', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 42, 'PH', 'Telephone', 'PH', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 42, 'FX', 'Fax', 'FX', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 42, 'MD', 'Modem', 'MD', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 42, 'CP', 'Cellular phone', 'CP', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 42, 'BP', 'Beeper', 'BP', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 42, 'Internet', 'Internet address', 'Internet', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 42, 'X.400', 'X.400 email address', 'X.400', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 42, 'TDD', 'Telecommunications Device for the Deaf', 'TDD', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 42, 'TTY', 'Teletypewriter', 'TTY', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 5, 'C', 'Current or temporary', 'C', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 5, 'P', 'Permanent', 'P', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 5, 'M', 'Mailing', 'M', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 5, 'B', 'Firm/Business', 'B', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 5, 'O', 'Office', 'O', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 5, 'H', 'Home', 'H', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 5, 'N', 'Birth (nee) ', 'N', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 5, 'F', 'Country of origin ', 'F', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 5, 'L', 'Legal address ', 'L', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 5, 'BDL', 'Birth delivery location', 'BDL', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 5, 'BR', 'Residence at birth', 'BR', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 5, 'RH', 'Registry home ', 'RH', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 5, 'BA', 'Bad address ', 'BA', 'I');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 7, '1', 'First', '1', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 7, '2', 'Second', '2', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 7, '3', 'Third', '3', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 7, '4', 'Fourth', '4', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 7, '5', 'Fifth', '5', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 8, 'ID', 'Intradermal ', 'ID', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 8, 'IM', 'Intramuscular ', 'IM', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 8, 'NS', 'Nasal ', 'NS', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 8, 'IN', 'Intranasal', 'NS', 'D');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 8, 'IV', 'Intravenous ', 'IV', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 8, 'PO', 'Oral ', 'PO', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 8, 'OTH', 'Other/Miscellaneous ', 'OTH', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 8, 'SC', 'Subcutaneous ', 'SC', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 8, 'TD', 'Transdermal ', 'TD', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 8, 'C38238', 'Intradermal ', 'C38238', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 8, 'C28161', 'Intramuscular ', 'C28161', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 8, 'C38284', 'Nasal ', 'C38284', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 8, 'C38276', 'Intravenous ', 'C38276', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 8, 'C38288', 'Oral ', 'C38288', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 8, 'C38676', 'Percutaneous ', 'C38676', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 8, 'C38299', 'Subcutaneous ', 'C38299', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 8, 'C38305', 'Transdermal ', 'C38305', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 9, 'LT', 'Left Thigh ', 'LT', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 9, 'LA', 'Left Upper Arm ', 'LA', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 9, 'LD', 'Left Deltoid ', 'LD', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 9, 'LG', 'Left Gluteous Medius ', 'LG', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 9, 'LVL', 'Left Vastus Lateralis ', 'LVL', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 9, 'LLFA', 'Left Lower Forearm ', 'LLFA', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 9, 'RA', 'Right Upper Arm ', 'RA', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 9, 'RT', 'Right Thigh ', 'RT', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 9, 'RVL', 'Right Vastus Lateralis ', 'RVL', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 9, 'RG', 'Right Gluteous Medius ', 'RG', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 9, 'RD', 'Right Deltoid ', 'RD', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 9, 'RLFA', 'Right Lower Forearm ', 'RLFA', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 2, 'USA', 'United States', 'USA', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 2, 'US', 'United States', 'USA', 'D');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 2, 'MX', 'Mexico', 'MEX', 'D');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 2, 'CA', 'Canada', 'CAN', 'D');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 2, 'MEX', 'Mexico', 'MEX', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 2, 'CAN', 'Canada', 'CAN', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 38, 'R', 'Recurring', 'R', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 39, 'P', 'Production', 'P', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 39, 'T', 'Training', 'T', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 39, 'D', 'Debug', 'D', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 30, '00', 'New immunization record ', '00', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 30, '01', 'Historical information - source unspecified ', '01', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 30, '02', 'Historical information - from other provider ', '01', 'D');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 30, '03', 'Historical information - from parents written record ', '01', 'D');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 30, '04', 'Historical information - from parents recall ', '01', 'D');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 30, '05', 'Historical information - from other registry ', '01', 'D');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 30, '06', 'Historical information - from birth certificate ', '01', 'D');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 30, '07', 'Historical information - from school record ', '01', 'D');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 30, '08', 'Historical information - from public agency ', '01', 'D');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 20, 'ara', 'Arabic ', 'ara', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 20, 'arm', 'Armenian ', 'arm', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 20, 'cat', 'Catalan; Valencian ', 'cat', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 20, 'chi', 'Chinese ', 'chi', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 20, 'dan', 'Danish ', 'dan', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 20, 'eng', 'English ', 'eng', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 20, 'En', 'English ', 'eng', 'D');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 20, 'en', 'English ', 'eng', 'D');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 20, 'fre', 'French ', 'fre', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 20, 'ger', 'German ', 'ger', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 20, 'hat', 'Haitian; Haitian Creole ', 'hat', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 20, 'heb', 'Hebrew ', 'heb', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 20, 'hin', 'Hindi ', 'hin', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 20, 'hmn', 'Hmong ', 'hmn', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 20, 'jpn', 'Japanese ', 'jpn', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 20, 'kor', 'Korean ', 'kor', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 20, 'rus', 'Russian ', 'rus', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 20, 'som', 'Somali ', 'som', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 20, 'spa', 'Spanish; Castilian ', 'spa', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 20, 'vie', 'Vietnamese ', 'vie', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 21, 'A', 'Alias name ', 'A', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 21, 'L', 'Legal name ', 'L', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 21, 'D', 'Display name ', 'D', 'D');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 21, 'M', 'Maiden name ', 'M', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 21, 'C', 'Adopted name ', 'C', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 21, 'B', 'Name at birth ', 'B', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 21, 'P', 'Name of partner/spouse ', 'P', 'I');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 21, 'U', 'Unspecified ', 'U', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 19, 'F', 'Female', 'F', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 19, 'M', 'Male', 'M', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 19, 'U', 'Unknown/undifferentiated', 'U', 'I');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 10, 'V01', 'Not VFC eligible ', 'V01', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 10, 'V02', 'VFC eligible-Medicaid/Medicaid managed care', 'V02', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 10, 'V03', 'VFC eligible-Uninsured ', 'V03', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 10, 'V04', 'VFC eligible- American Indian/Alaskan Native ', 'V04', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 10, 'V05', 'VFC eligible-Federally Qualified Health Center Patient (under-insured) ', 'V05', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 10, 'V06', 'VFC eligible- State specific eligibility (e.g. S-CHIP plan) ', 'V06', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 10, 'V07', 'VFC eligibility- Local-specific eligibility ', 'V07', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 10, 'V08', 'Not VFC eligible-Under-insured ', 'V08', 'I');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 4, 'AL', 'Alabama', 'AL', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 4, 'AK', 'Alaska', 'AK', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 4, 'AZ', 'Arizona', 'AZ', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 4, 'AR', 'Arkansas', 'AR', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 4, 'CA', 'California', 'CA', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 4, 'CO', 'Colorado', 'CO', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 4, 'CT', 'Connecticut', 'CT', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 4, 'DE', 'Delaware', 'DE', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 4, 'DC', 'District of Columbia', 'DC', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 4, 'FL', 'Florida', 'FL', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 4, 'GA', 'Georgia', 'GA', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 4, 'HI', 'Hawaii', 'HI', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 4, 'ID', 'Idaho', 'ID', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 4, 'IL', 'Illinois', 'IL', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 4, 'IN', 'Indiana', 'IN', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 4, 'IA', 'Iowa', 'IA', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 4, 'KS', 'Kansas', 'KS', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 4, 'KY', 'Kentucky', 'KY', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 4, 'LA', 'Louisiana', 'LA', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 4, 'ME', 'Maine', 'ME', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 4, 'MD', 'Maryland', 'MD', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 4, 'MA', 'Massachusetts', 'MA', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 4, 'MI', 'Michigan', 'MI', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 4, 'MN', 'Minnesota', 'MN', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 4, 'MS', 'Mississippi', 'MS', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 4, 'MO', 'Missouri', 'MO', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 4, 'MT', 'Montana', 'MT', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 4, 'NE', 'Nebraska', 'NE', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 4, 'NV', 'Nevada', 'NV', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 4, 'NH', 'New Hampshire', 'NH', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 4, 'NJ', 'New Jersey', 'NJ', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 4, 'NM', 'New Mexico', 'NM', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 4, 'NY', 'New York', 'NY', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 4, 'NC', 'North Carolina', 'NC', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 4, 'ND', 'North Dakota', 'ND', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 4, 'OH', 'Ohio', 'OH', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 4, 'OK', 'Oklahoma', 'OK', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 4, 'OR', 'Oregon', 'OR', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 4, 'PA', 'Pennsylvania', 'PA', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 4, 'RI', 'Rhode Island', 'RI', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 4, 'SC', 'South Carolina', 'SC', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 4, 'SD', 'South Dakota', 'SD', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 4, 'TN', 'Tennessee', 'TN', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 4, 'TX', 'Texas', 'TX', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 4, 'UT', 'Utah', 'UT', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 4, 'VT', 'Vermont', 'VT', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 4, 'VA', 'Virginia', 'VA', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 4, 'WA', 'Washington', 'WA', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 4, 'WV', 'West Virginia', 'WV', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 4, 'WI', 'Wisconsin', 'WI', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 4, 'WY', 'Wyoming', 'WY', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 4, 'AS', 'American Samoa', 'AS', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 4, 'GU', 'Guam', 'GU', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 4, 'MP', 'Northern Mariana Islands', 'MP', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 4, 'PR', 'Puerto Rico', 'PR', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 4, 'VI', 'Virgin Islands', 'VI', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 4, 'FM', 'Federated States of Micronesia', 'FM', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 4, 'MH', 'Marshall Islands', 'MH', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 4, 'PW', 'Palau', 'PW', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 4, 'AA', 'Armed Forces Americas (except Canada)', 'AA', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 4, 'AE', 'Armed Forces (Europe, Canada, Middle East, Africa)', 'AE', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 4, 'AP', 'Armed Forces Pacific', 'AP', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 4, 'CZ', 'Canal Zone', 'CZ', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 4, 'PI', 'Philippine Islands', 'PI', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 4, 'TT', 'Trust Territory of the Pacific Islands', 'TT', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 4, 'CM', 'Commonwealth of the Northern Mariana Islands', 'CM', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 26, 'CP', 'Complete ', 'CP', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 26, 'RE', 'Refused ', 'RE', 'G');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 26, 'NA', 'Not Administered ', 'NA', 'G');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 26, 'PA', 'Partially Administered ', 'PA', 'G');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 22, 'BRO', 'Brother', 'BRO', 'G');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 22, 'CGV', 'Care Giver', 'CGV', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 22, 'FTH', 'Father', 'FTH', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 22, 'FCH', 'Foster Child', 'FCH', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 22, 'GRP', 'Grand Parent', 'GRP', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 22, 'GRD', 'Guardian', 'GRD', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 22, 'MTH', 'Mother', 'MTH', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 22, 'OTH', 'Other', 'OTH', 'G');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 22, 'PAR', 'Parent', 'PAR', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 22, 'SEL', 'Self', 'SEL', 'G');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 22, 'SIB', 'Sibling', 'SIB', 'G');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 22, 'SIS', 'Sister', 'SIS', 'G');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 22, 'SPO', 'Spouse', 'SPO', 'G');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 22, 'SCH', 'Step Child', 'SCH', 'G');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 34, 'RE', 'Observations to follow', 'RE', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 34, 'OK', 'Order accepted &'||' OK', 'OK', 'I');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 18, '1002-5', 'American Indian or Alaska Native ', '1002-5', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 18, '2028-9', 'Asian ', '2028-9', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 18, '2076-8', 'Native Hawaiian or Other Pacific Islander ', '2076-8', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 18, '2054-5', 'Black or African-American ', '2054-5', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 18, '2106-3', 'White ', '2106-3', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 18, '2131-1', 'Other Race ', '2131-1', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 18, 'I', 'American Indian or Alaska Native ', '1002-5', 'D');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 18, 'A', 'Asian ', '2028-9', 'D');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 18, 'A', 'Native Hawaiian or Other Pacific Islander ', '2076-8', 'D');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 18, 'B', 'Black or African-American ', '2054-5', 'D');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 18, 'W', 'White ', '2106-3', 'D');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 18, 'O', 'Other Race ', '2131-1', 'D');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 18, 'U', 'Unknown ', '2131-1', 'D');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 14, '2135-2', 'Hispanic or Latino', '2135-2', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 14, '2186-5', 'not Hispanic or Latino', '2186-5', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 14, 'H', 'Hispanic or Latino', '2135-2', 'D');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 14, 'N', 'not Hispanic or Latino', '2186-5', 'D');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 14, 'U', 'Unknown', 'U', 'D');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 6, 'ML', 'Milliliter', 'ML', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 6, 'ml', 'Milliliter', 'ML', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 6, 'mL', 'Milliliter', 'ML', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 6, 'CC', 'Cubic centimeter (ml)', 'ML', 'D');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 43, '64994-7', 'Vaccine funding program eligibility category', '64994-7', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 45, 'AD', 'Address', 'AD', 'G');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 45, 'CE', 'Coded Entry', 'CE', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 45, 'CF', 'Coded Element With Formatted Values', 'CF', 'G');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 45, 'CK', 'Composite ID With Check Digit', 'CK', 'G');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 45, 'CN', 'Composite ID And Name', 'CN', 'G');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 45, 'CP', 'Composite Price', 'CP', 'G');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 45, 'CX', 'Extended Composite ID With Check Digit', 'CX', 'G');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 45, 'DT', 'Date', 'DT', 'G');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 45, 'ED', 'Encapsulated Data', 'ED', 'G');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 45, 'FT', 'Formatted Text (Display)', 'FT', 'G');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 45, 'MO', 'Money', 'MO', 'G');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 45, 'NM', 'Numeric', 'NM', 'G');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 45, 'PN', 'Person Name', 'PN', 'G');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 45, 'RP', 'Reference Pointer', 'RP', 'G');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 45, 'SN', 'Structured Numeric', 'SN', 'G');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 45, 'ST', 'String Data.', 'ST', 'G');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 45, 'TM', 'Time', 'TM', 'G');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 45, 'TN', 'Telephone Number', 'TN', 'G');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 45, 'TS', 'Time Stamp (Date &'||' Time)', 'TS', 'G');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 45, 'TX', 'Text Data (Display)', 'TX', 'G');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 45, 'XAD', 'Extended Address', 'XAD', 'G');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 45, 'XCN', 'Extended Composite Name And Number For Persons', 'XCN', 'G');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 45, 'XON', 'Extended Composite Name And Number For Organizations', 'XON', 'G');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 45, 'XPN', 'Extended Person Name', 'XPN', 'G');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 45, 'XTN', 'Extended Telecommunications Number', 'XTN', 'G');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 24, 'A', 'Active', 'A', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 24, 'I', 'Inactive – Unspecified', 'I', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 24, 'L', 'Inactive – Lost to follow-up', 'L', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 24, 'M', 'Inactive – Moved or gone elsewhere', 'M', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 24, 'P', 'Inactive – Permanently inactive', 'P', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 24, 'U', 'Unknown', 'U', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 17, '01', 'No reminder/recall', '01', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 17, '02', 'Reminder/recall – any method', '02', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 17, '03', 'Reminder/recall – no calls', '03', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 17, '04', 'Reminder only – any method', '04', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 17, '05', 'Reminder only – no calls', '05', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 17, '06', 'Recall only – any method', '06', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 17, '07', 'Recall only – no calls', '07', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 17, '08', 'Reminder/recall – to provider', '08', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 17, '09', 'Reminder to provider', '09', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 17, '10', 'Only reminder to provider, no recall', '10', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 17, '11', 'Recall to provider', '11', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 17, '12', 'Only recall to provider, no reminder', '12', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 16, 'Y', 'Yes', 'Y', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 16, 'N', 'No', 'N', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 27, 'R', 'Restricted', 'R', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 27, 'U', 'Usual control', 'U', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 27, 'V', 'Very restricted', 'V', 'V');


CREATE TABLE dqa_application
(
  application_id      INTEGER NOT NULL PRIMARY KEY,
  application_label   VARCHAR2(30) NOT NULL,
  application_type    VARCHAR2(30) NOT NULL, -- Dev, Test, Prod
  run_this            VARCHAR2(1) DEFAULT 'N',
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
  keyed_id            INTEGER NOT NULL PRIMARY KEY,
  keyed_code          VARCHAR2(50) NOT NULL,
  object_code         VARCHAR2(50) NOT NULL,
  object_id           INTEGER NOT NULL,
  keyed_value         VARCHAR2(250) NOT NULL
);

INSERT INTO dqa_keyed_setting (keyed_id, keyed_code, object_code, object_id, keyed_value) VALUES (dqa_keyed_id_sequence.NEXTVAL, 'validate.header.sending_facility.pfs', 'Application', 2, 'Y');
INSERT INTO dqa_keyed_setting (keyed_id, keyed_code, object_code, object_id, keyed_value) VALUES (dqa_keyed_id_sequence.NEXTVAL, 'validate.header.sending_facility.pfs', 'Application', 3, 'Y');
INSERT INTO dqa_keyed_setting (keyed_id, keyed_code, object_code, object_id, keyed_value) VALUES (dqa_keyed_id_sequence.NEXTVAL, 'validate.header.sending_facility.pfs', 'Application', 10, 'Y');

INSERT INTO dqa_keyed_setting (keyed_id, keyed_code, object_code, object_id, keyed_value) VALUES (dqa_keyed_id_sequence.NEXTVAL, 'validate.vaccination.facility.pfs', 'Application', 2, 'Y');
INSERT INTO dqa_keyed_setting (keyed_id, keyed_code, object_code, object_id, keyed_value) VALUES (dqa_keyed_id_sequence.NEXTVAL, 'validate.vaccination.facility.pfs', 'Application', 3, 'Y');
INSERT INTO dqa_keyed_setting (keyed_id, keyed_code, object_code, object_id, keyed_value) VALUES (dqa_keyed_id_sequence.NEXTVAL, 'validate.vaccination.facility.pfs', 'Application', 10, 'Y');

CREATE TABLE dqa_vaccine_group
(
  group_id            INTEGER NOT NULL PRIMARY KEY,   
  group_code          VARCHAR2(30) NOT NULL,
  group_label         VARCHAR2(250) NOT NULL,
  group_status        VARCHAR2(30) NOT NULL 
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
  cvx_group_id        INTEGER NOT NULL PRIMARY KEY,
  group_id            INTEGER NOT NULL,
  cvx_id              INTEGER NOT NULL
);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 2, 54);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 2, 55);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 2, 82);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 3, 24);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 4, 19);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 5, 26);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 17, 29);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 6, 28);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 6, 20);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 6, 106);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 6, 107);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 6, 110);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 13, 110);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 26, 110);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 6, 50);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 15, 50);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 6, 120);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 15, 120);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 26, 120);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 6, 130);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 26, 130);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 6, 132);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 13, 132);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 15, 132);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 26, 132);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 6, 1);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 6, 22);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 15, 22);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 6, 102);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 13, 102);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 15, 102);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 10, 30);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 12, 52);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 12, 83);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 12, 84);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 12, 31);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 12, 85);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 12, 104);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 13, 104);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 13, 8);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 13, 42);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 13, 43);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 13, 44);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 13, 45);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 14, 58);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 11, 59);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 15, 47);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 15, 46);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 15, 49);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 15, 48);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 15, 17);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 13, 51);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 15, 51);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 16, 118);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 16, 62);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 16, 137);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 17, 86);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 17, 14);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 17, 87);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 9, 123);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 7, 135);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 7, 111);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 7, 141);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 7, 140);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 7, 15);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 7, 88);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 7, 16);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 26, 10);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 18, 134);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 18, 39);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 19, 66);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 20, 4);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 29, 4);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 20, 5);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 21, 103);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 21, 136);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 21, 114);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 21, 32);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 21, 108);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 22, 3);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 22, 94);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 33, 94);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 23, 7);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 8, 127);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 8, 128);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 8, 125);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 8, 126);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 26, 2);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 24, 133);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 24, 100);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 25, 33);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 26, 89);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 27, 40);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 27, 18);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 27, 90);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 17, 34);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 28, 119);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 28, 116);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 28, 74);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 28, 122);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 17, 71);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 29, 6);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 23, 38);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 29, 38);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 30, 138);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 30, 113);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 30, 9);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 30, 115);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 31, 25);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 31, 41);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 31, 53);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 31, 91);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 31, 101);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 32, 75);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 32, 105);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 33, 21);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 34, 81);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 34, 80);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 34, 92);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 35, 37);
INSERT INTO dqa_vaccine_cvx_group (cvx_group_id, group_id, cvx_id) VALUES (dqa_cvx_group_id_sequence.NEXTVAL, 36, 121);

CREATE TABLE dqa_batch_code_received
(
  batch_code_received_id    INTEGER NOT NULL PRIMARY KEY,
  batch_id                  INTEGER,
  coded_id                  INTEGER,
  received_count            INTEGER
);

CREATE TABLE dqa_batch_vaccine_cvx
(
  batch_vaccine_cvx_id    INTEGER NOT NULL PRIMARY KEY,
  batch_id                INTEGER,
  cvx_id                  INTEGER,
  received_count          INTEGER
);

CREATE TABLE dqa_batch_report 
(
  batch_report_id            INTEGER NOT NULL PRIMARY KEY,
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
  template_label     VARCHAR2(120) NOT NULL,
  report_type_id     INTEGER NOT NULL,
  report_definition  CLOB,
  base_profile_id    INTEGER
);

INSERT INTO dqa_report_template(template_id, template_label, report_type_id, base_profile_id) VALUES(1, 'CDC Vacc Admin', 1, 251);
INSERT INTO dqa_report_template(template_id, template_label, report_type_id, base_profile_id) VALUES(2, 'CDC Vacc Inventory', 2, 252);
INSERT INTO dqa_report_template(template_id, template_label, report_type_id, base_profile_id) VALUES(3, 'ImmTrac Vacc Admin', 1, 253);
INSERT INTO dqa_report_template(template_id, template_label, report_type_id, base_profile_id) VALUES(4, 'ImmTrac Vacc Inventory', 2, 254);
INSERT INTO dqa_report_template(template_id, template_label, report_type_id, base_profile_id) VALUES(5, 'MCIR Vacc Admin', 1, 255);
INSERT INTO dqa_report_template(template_id, template_label, report_type_id, base_profile_id) VALUES(6, 'MCIR Vacc Inventory', 2, 256);

CREATE TABLE dqa_report_type
(
  report_type_id    INTEGER NOT NULL PRIMARY KEY,
  report_type_label VARCHAR2(120) NOT NULL
);

INSERT INTO dqa_report_type(report_type_id, report_type_label) VALUES (1, 'Vacc Admin');
INSERT INTO dqa_report_type(report_type_id, report_type_label) VALUES (2, 'Vacc Inventory');

CREATE TABLE dqa_report_vaccine_group
(
  report_vaccine_group_id  INTEGER NOT NULL PRIMARY KEY,
  group_id                 INTEGER NOT NULL,
  profile_id               INTEGER NOT NULL,
  group_status             VARCHAR2(30) NOT NULL
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
