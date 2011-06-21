-- password reydegatos


CREATE USER dqa_web IDENTIFIED BY SharkBaitHooHaHa;
GRANT connect to dqa_web;
GRANT dba TO dqa_web;

CREATE TABLE dqa_user_account
(
  username           VARCHAR2(30) NOT NULL PRIMARY KEY,
  password           VARCHAR2(50) NOT NULL,
  account_type       VARCHAR2(10) NOT NULL, -- Admin, Submitter
  org_id             INTEGER DEFAULT 0,
  email              VARCHAR2(120) NOT NULL
);

INSERT INTO dqa_user_account (username, password, account_type, org_id, email) VALUES ('iisadmin', 'changeme', 'Admin', 1, 'Nathan.Bunker@gmail.com');

CREATE TABLE dqa_organization
(
  org_id             INTEGER NOT NULL PRIMARY KEY,
  org_label          VARCHAR2(60) NOT NULL,
  org_parent_id      INTEGER,
  primary_profile_id INTEGER
);

INSERT INTO dqa_organization (org_id, org_label, org_parent_id, primary_profile_id) VALUES (1, 'IIS', 0, 1);

CREATE TABLE dqa_submitter_profile
(
  profile_id         INTEGER NOT NULL PRIMARY KEY,
  profile_label      VARCHAR2(30) NOT NULL,
  profile_status     VARCHAR2(5) DEFAULT 'Setup' NOT NULL, -- Setup, Test, Production, Hold
  org_id             INTEGER NOT NULL,
  data_format        VARCHAR2(5) DEFAULT 'HL7v2' NOT NULL, -- HL7v2
  transfer_priority  VARCHAR2(7) DEFAULT 'Normal' NOT NULL, -- Normal, High, Highest, Low, Lowest
  access_key         VARCHAR2(50)
);

INSERT INTO dqa_submitter_profile(profile_id, profile_label, profile_status, org_id, data_format, transfer_priority, access_key) VALUES (1, 'HL7', 'Test', 1, 'HL7v2', 'Normal', 'hl7');

CREATE TABLE dqa_potential_issue_status
(
  potential_issue_status_id INTEGER NOT NULL PRIMARY KEY,
  issue_id           INTEGER NOT NULL,
  profile_id         INTEGER NOT NULL,
  action_code        VARCHAR2(1) NOT NULL,
  expect_min         INTEGER DEFAULT 0 NOT NULL,
  expect_max         INTEGER DEFAULT 100 NOT NULL
);

CREATE INDEX dqa_potential_issue_status_key ON dqa_potential_issue_status (issue_id, profile_id);

CREATE TABLE dqa_issue_action
(
  action_code        VARCHAR2(1) NOT NULL PRIMARY KEY,
  action_label       VARCHAR2(30) NOT NULL
);

INSERT INTO dqa_issue_action (action_code, action_label) VALUES ('E', 'Error');
INSERT INTO dqa_issue_action (action_code, action_label) VALUES ('W', 'Warn');
INSERT INTO dqa_issue_action (action_code, action_label) VALUES ('A', 'Accept');
INSERT INTO dqa_issue_action (action_code, action_label) VALUES ('S', 'Skip');

CREATE TABLE dqa_potential_issue
(
  issue_id            INTEGER NOT NULL PRIMARY KEY,
  target_object       VARCHAR2(30) NOT NULL,
  target_field        VARCHAR2(50) NOT NULL,
  issue_type          VARCHAR2(50) NOT NULL,
  field_value         VARCHAR2(50),
  default_action_code VARCHAR2(1) NOT NULL,
  change_priority     VARCHAR2(7) NOT NULL -- Must, Should, Can, May, Blocked
);

CREATE TABLE dqa_message_batch
(
  batch_id            INTEGER NOT NULL PRIMARY KEY,
  batch_title         VARCHAR2(60),
  type_code           VARCHAR2(1),
  start_date          DATE,
  end_date            DATE,
  submit_code         VARCHAR2(1)
);

CREATE TABLE dqa_receive_queue
(
  receive_queue_id    INTEGER NOT NULL PRIMARY KEY,
  batch_id            INTEGER,
  received_id         INTEGER,
  submit_code         VARCHAR2(1)
);

CREATE INDEX dqa_receive_queue_key ON dqa_receive_queue (batch_id, received_id);

CREATE TABLE dqa_message_received
(
  received_id         INTEGER NOT NULL PRIMARY KEY,
  profile_id          INTEGER NOT NULL,
  received_date       DATE NOT NULL,
  request_text        CLOB,
  response_text       CLOB,
  action_code         VARCHAR2(1),
  submit_code         VARCHAR2(1),
  patient_id          INTEGER
);

CREATE TABLE dqa_issue_found 
(
  issue_found_id      INTEGER NOT NULL PRIMARY KEY,
  received_id         INTEGER NOT NULL,
  issue_id            INTEGER NOT NULL,
  position_id         INTEGER NOT NULL,
  issue_negate        VARCHAR2(1) DEFAULT 'N', -- Y, N
  action_code         VARCHAR2(1),
  code_id             INTEGER
);

CREATE INDEX dqa_issue_found_key ON dqa_issue_found (received_id, issue_id);

CREATE TABLE dqa_code_received
(
  code_id             INTEGER NOT NULL PRIMARY KEY,
  profile_id          INTEGER NOT NULL,
  table_id            INTEGER NOT NULL,
  received_value      VARCHAR2(50) NOT NULL,
  code_value          VARCHAR2(50),
  code_status         VARCHAR2(1) NOT NULL,
  received_count      INTEGER
);

CREATE INDEX dqa_code_received_key ON dqa_code_received (profile_id, table_id, received_value);

CREATE TABLE dqa_code_status
(
  code_status        VARCHAR2(1) NOT NULL PRIMARY KEY,
  code_label         VARCHAR2(30) NOT NULL
);

INSERT INTO dqa_code_status (code_status, code_label) VALUES ('V', 'Valid');
INSERT INTO dqa_code_status (code_status, code_label) VALUES ('I', 'Invalid');
INSERT INTO dqa_code_status (code_status, code_label) VALUES ('U', 'Unrecognized');
INSERT INTO dqa_code_status (code_status, code_label) VALUES ('D', 'Deprecated');

CREATE TABLE dqa_code_table
(
  table_id             INTEGER NOT NULL PRIMARY KEY,
  table_label          VARCHAR2(15) NOT NULL, 
  default_code_value   VARCHAR2(50)
);

INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES (1, 'Action Code', 'A');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES (2, 'Address Type', '');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES (3, 'Birth Order', '');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES (4, 'Body Route', '');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES (5, 'Body Site', '');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES (6, 'County', '');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES (7, 'Country', '');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES (8, 'CPT', '');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES (9, 'CVX', '');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES (10, 'Facility', '');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES (11, 'Info Source', '');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES (12, 'Language', '');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES (13, 'MVX', '');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES (14, 'Name Type', '');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES (15, 'Sex', '');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES (16, 'VFC Status', '');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES (17, 'State', '');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES (18, 'Completion', '');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES (19, 'Confidentiality', '');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES (20, 'Vaccine Product', '');

CREATE TABLE dqa_batch_type
(
  type_code           VARCHAR2(1) NOT NULL PRIMARY KEY,
  type_label          VARCHAR2(30) NOT NULL
);

INSERT INTO dqa_batch_type (type_code, type_label) VALUES ('D', 'Daily');
INSERT INTO dqa_batch_type (type_code, type_label) VALUES ('W', 'Weekly');
INSERT INTO dqa_batch_type (type_code, type_label) VALUES ('M', 'Monthly');
INSERT INTO dqa_batch_type (type_code, type_label) VALUES ('O', 'Other');

CREATE TABLE dqa_batch_issues
(
  batch_issues_id     INTEGER NOT NULL PRIMARY KEY,
  batch_id            INTEGER NOT NULL,
  issue_id            INTEGER NOT NULL,
  issue_count_pos     INTEGER,
  issue_count_neg     INTEGER
);

CREATE INDEX dqa_batch_issues_key ON dqa_batch_issues (batch_id, issue_id);

CREATE TABLE dqa_batch_actions
(
  batch_actions_id    INTEGER NOT NULL PRIMARY KEY,
  batch_id            INTEGER NOT NULL,
  action_code         VARCHAR2(1) NOT NULL,
  action_count        INTEGER
);

CREATE INDEX dqa_batch_actions_key ON dqa_batch_actions (batch_id, action_code);

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
  sex_code                 VARCHAR2(250)
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
  relationship_code        VARCHAR2(250)
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
  vis_publication_date       VARCHAR2(250)
);

CREATE TABLE dqa_vaccine_product
(
  product_id          INTEGER NOT NULL PRIMARY KEY,
  product_name        VARCHAR2(250) NOT NULL,
  product_label           VARCHAR2(250) NOT NULL,
  cvx_code            VARCHAR2(10) NOT NULL,
  mvx_code            VARCHAR2(10) NOT NULL,
  valid_start_date    DATE NOT NULL,
  use_start_date      DATE NOT NULL,
  use_end_date        DATE NOT NULL,
  valid_end_date      DATE NOT NULL
);


CREATE TABLE dqa_vaccine_mvx
(
  mvx_code            VARCHAR2(10) NOT NULL PRIMARY KEY,
  mvx_label           VARCHAR2(250) NOT NULL,
  valid_start_date    DATE NOT NULL,
  use_start_date      DATE NOT NULL,
  use_end_date        DATE NOT NULL,
  valid_end_date      DATE NOT NULL
);

CREATE TABLE dqa_vaccine_cvx
(
  cvx_code            VARCHAR2(10) NOT NULL PRIMARY KEY,
  cvx_label           VARCHAR2(250) NOT NULL,
  valid_start_date    DATE NOT NULL,
  use_start_date      DATE NOT NULL,
  use_end_date        DATE NOT NULL,
  valid_end_date      DATE NOT NULL,
  use_month_start     INTEGER NOT NULL,
  use_month_end       INTEGER NOT NULL,
  concept_type        VARCHAR2(30) NOT NULL
);

CREATE TABLE dqa_vaccine_cpt
(
  cpt_id              INTEGER NOT NULL PRIMARY KEY,
  cpt_code            VARCHAR2(10) NOT NULL,
  cpt_label           VARCHAR2(250) NOT NULL,
  valid_start_date    DATE NOT NULL,
  use_start_date      DATE NOT NULL,
  use_end_date        DATE NOT NULL,
  valid_end_date      DATE NOT NULL,
  cvx_code            VARCHAR2(10) NOT NULL
);

CREATE TABLE dqa_application
(
  application_id      INTEGER NOT NULL PRIMARY KEY,
  application_label   VARCHAR2(30) NOT NULL,
  application_type    VARCHAR2(30) NOT NULL, -- Dev, Test, Prod
  run_this            VARCHAR2(1) DEFAULT 'N'
);

INSERT INTO dqa_application (application_id, application_label, application_type, run_this) VALUES (1, 'DQA Dev', 'Dev', 'Y');
INSERT INTO dqa_application (application_id, application_label, application_type, run_this) VALUES (2, 'ImmTrac', 'Test', 'N');
INSERT INTO dqa_application (application_id, application_label, application_type, run_this) VALUES (3, 'ImmTrac', 'Prod', 'N');

CREATE TABLE dqa_keyed_setting
(
  keyed_id            INTEGER NOT NULL PRIMARY KEY,
  keyed_code          VARCHAR2(50) NOT NULL,
  object_code         VARCHAR2(50) NOT NULL,
  object_id           INTEGER NOT NULL,
  keyed_value         VARCHAR2(250) NOT NULL
);

CREATE SEQUENCE dqa_organization_id_sequence INCREMENT BY 1 START WITH 100;
CREATE SEQUENCE dqa_profile_id_sequence INCREMENT BY 1 START WITH 100;
CREATE SEQUENCE dqa_message_received_sequence INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE dqa_code_id_sequence INCREMENT BY 1 START WITH 1000;
CREATE SEQUENCE dqa_patient_id_sequence INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE dqa_next_of_kin_id_sequence INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE dqa_vaccination_id_sequence INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE dqa_keyed_id_sequence INCREMENT BY 1 START WITH 1;


