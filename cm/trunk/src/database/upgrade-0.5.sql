-- alter table to support other application types

CREATE TABLE application (
  application_id        INTEGER NOT NULL PRIMARY KEY,
  application_label     VARCHAR(120) NOT NULL,
  application_acronym   VARCHAR(120) NOT NULL
);

INSERT INTO application(application_id, application_label, application_acronym) VALUES (1, 'Data Quality Assurance - Code Manager', 'DQAcm');
INSERT INTO application(application_id, application_label, application_acronym) VALUES (2, 'Data Quality Assurance - Interoperability Analysis System', 'DQAias');


CREATE TABLE application_user (
  application_user_id   INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  application_id        INTEGER NOT NULL,
  user_id               INTEGER NOT NULL,
  user_type             VARCHAR(1) NOT NULL
);

INSERT INTO application_user(application_id, user_id, user_type) VALUES (1, 1, 'A');
INSERT INTO application_user(application_id, user_id, user_type) VALUES (1, 2, 'S');
INSERT INTO application_user(application_id, user_id, user_type) VALUES (1, 3, 'S');
INSERT INTO application_user(application_id, user_id, user_type) VALUES (1, 4, 'S');
INSERT INTO application_user(application_id, user_id, user_type) VALUES (1, 5, 'E');
INSERT INTO application_user(application_id, user_id, user_type) VALUES (1, 6, 'E');
INSERT INTO application_user(application_id, user_id, user_type) VALUES (1, 7, 'E');
INSERT INTO application_user(application_id, user_id, user_type) VALUES (1, 8, 'E');
INSERT INTO application_user(application_id, user_id, user_type) VALUES (1, 9, 'E');
INSERT INTO application_user(application_id, user_id, user_type) VALUES (1, 10, 'E');
INSERT INTO application_user(application_id, user_id, user_type) VALUES (1, 11, 'E');
INSERT INTO application_user(application_id, user_id, user_type) VALUES (1, 12, 'E');
INSERT INTO application_user(application_id, user_id, user_type) VALUES (1, 13, 'E');
INSERT INTO application_user(application_id, user_id, user_type) VALUES (1, 14, 'E');
INSERT INTO application_user(application_id, user_id, user_type) VALUES (1, 15, 'E');
INSERT INTO application_user(application_id, user_id, user_type) VALUES (1, 16, 'E');
INSERT INTO application_user(application_id, user_id, user_type) VALUES (1, 17, 'E');
INSERT INTO application_user(application_id, user_id, user_type) VALUES (1, 18, 'E');
INSERT INTO application_user(application_id, user_id, user_type) VALUES (1, 19, 'E');
INSERT INTO application_user(application_id, user_id, user_type) VALUES (1, 20, 'E');
INSERT INTO application_user(application_id, user_id, user_type) VALUES (1, 21, 'E');
INSERT INTO application_user(application_id, user_id, user_type) VALUES (1, 22, 'E');

ALTER TABLE user drop column user_type;


CREATE TABLE test_conducted (
  test_conducted_id    INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  connection_label     VARCHAR(250) NOT NULL,
  connection_type      VARCHAR(250),
  connection_url       VARCHAR(250),
  connection_ack_type  VARCHAR(250),
  connection_config    TEXT, 
  query_type           VARCHAR(250),
  query_enabled        VARCHAR(1),
  query_pause          VARCHAR(1),
  test_log             TEXT,
  test_started_time    DATETIME,
  test_finished_time   DATETIME,
  count_update         INTEGER,
  count_query          INTEGER,
  profile_base_name    VARCHAR(250),
  profile_compare_name VARCHAR(250)
);

CREATE TABLE test_section (
  test_section_id    INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  test_conducted_id  INTEGER NOT NULL,
  test_section_type  VARCHAR(250) NOT NULL,
  test_enabled       VARCHAR(1) NOT NULL,
  score_level1       INTEGER,
  score_level2       INTEGER,
  score_level3       INTEGER,
  progress_level1    INTEGER,
  progress_level2    INTEGER,
  progress_level3    INTEGER,
  count_level1       INTEGER,
  count_level2       INTEGER,
  count_level3       INTEGER
);

CREATE TABLE test_message (
  test_message_id                    INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  test_section_id                    INTEGER NOT NULL,
  test_position                      INTEGER NOT NULL,
  test_type                          VARCHAR(250) NOT NULL,
  test_case_set                      VARCHAR(250),
  test_case_number                   VARCHAR(250),
  test_case_category                 VARCHAR(250),
  test_case_description              VARCHAR(250),
  test_case_assert_result            VARCHAR(250),
  prep_patient_type                  VARCHAR(250),
  prep_transforms_quick              TEXT,
  prep_transforms_custom             TEXT,
  prep_transforms_addition           TEXT,
  prep_transforms_cause_issue        TEXT,
  prep_cause_issue_names             TEXT,
  prep_has_issue                     VARCHAR(1),
  prep_major_changes_made            VARCHAR(1),
  prep_not_expected_to_conform       VARCHAR(1),
  prep_message_accept_status_debug   VARCHAR(250),
  prep_scenario_name                 VARCHAR(250),
  prep_message_derived_from          TEXT,
  prep_message_original              TEXT,
  prep_message_actual                TEXT,
  result_message_actual              TEXT,
  result_status                      VARCHAR(250),
  result_exception_text              TEXT,
  result_accepted_message            VARCHAR(250),
  result_response_type               VARCHAR(250),
  result_ack_type                    VARCHAR(250),
  forecast_test_panel_case_id        INTEGER,
  forecast_test_panel_id             INTEGER
);

CREATE TABLE test_profile (
  test_profile_id                    INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  test_section_id                    INTEGER NOT NULL,
  test_profile_status                VARCHAR(250) NOT NULL,
  profile_field_pos                  INTEGER NOT NULL,
  profile_field_name                 VARCHAR(250) NOT NULL,
  usage_expected                     VARCHAR(250),
  usage_detected                     VARCHAR(250),
  accept_expected                    VARCHAR(250),
  accept_detected                    VARCHAR(250),
  test_message_present_id            INTEGER NOT NULL,
  test_message_absent_id             INTEGER NOT NULL,
  message_accept_status_debug        VARCHAR(250),
  compatibility_conformance          VARCHAR(250)
);