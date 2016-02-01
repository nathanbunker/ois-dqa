delete from pentagon_report;

ALTER TABLE profile_usage_value ADD COLUMN link_definition VARCHAR(250);
ALTER TABLE profile_usage_value ADD COLUMN link_detail VARCHAR(250);
ALTER TABLE profile_usage_value ADD COLUMN link_clarification VARCHAR(250);
ALTER TABLE profile_usage_value ADD COLUMN link_supplement VARCHAR(250);

ALTER TABLE test_message ADD COLUMN result_query_type VARCHAR(250);
ALTER TABLE test_message ADD COLUMN result_forecast_status VARCHAR(250);

CREATE TABLE evaluation_field (
    evaluation_field_id     INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    component_code          VARCHAR(250) NOT NULL,
    vaccine_code            VARCHAR(250) NOT NULL,
    vaccine_date            VARCHAR(250) NOT NULL
);

CREATE TABLE evaluation (
    evaluation_id           INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    evaluation_field_id     INTEGER NOT NULL,
    test_message_id         INTEGER NOT NULL,
    evaluation_type         VARCHAR(250) NOT NULL,
    schedule_name           VARCHAR(250),
    dose_number             VARCHAR(250),
    dose_validity           VARCHAR(250),
    series_name             VARCHAR(250),
    series_dose_count       VARCHAR(250),
    series_status           VARCHAR(250),
    reason_code             VARCHAR(250)
);

CREATE TABLE forecast_field (
    forecast_field_id       INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    vaccine_code            VARCHAR(250) NOT NULL
);

CREATE TABLE forecast (
    forecast_id             INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    forecast_field_id       INTEGER NOT NULL,
    test_message_id         INTEGER NOT NULL,
    forecast_type           VARCHAR(250) NOT NULL,
    schedule_name           VARCHAR(250),
    series_name             VARCHAR(250),
    series_dose_count       VARCHAR(250),
    dose_number             VARCHAR(250),
    date_earliest           DATE,
    date_due                DATE,
    date_overdue            DATE,
    date_latest             DATE,
    series_status           VARCHAR(250),
    reason_code             VARCHAR(250)
);

CREATE INDEX test_message_test_section_id ON test_message(test_section_id);
CREATE INDEX test_message_test_type ON test_message(test_type);
CREATE INDEX assertion_test_message_id ON assertion (test_message_id);
CREATE INDEX assertion_assertion_field_id ON assertion (assertion_field_id);
CREATE INDEX assertion_identified_report_type_id ON assertion_identified (pentagon_report_id, test_type);
CREATE INDEX forecast_test_message_id ON forecast (test_message_id);
CREATE INDEX evaluation_test_message_id ON evaluation (test_message_id);
CREATE INDEX comparison_test_message_id ON comparison (test_message_id);



CREATE TABLE assertion_identified (
  assertion_identified_id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  assertion_field_id      INTEGER NOT NULL,
  pentagon_report_id      INTEGER NOT NULL,
  test_message_id         INTEGER NOT NULL,
  assertion_count         INTEGER NOT NULL,
  test_type               VARCHAR(250) NOT NULL,
  assertion_result        VARCHAR(250)
);


DROP TABLE pentagon_report;

CREATE TABLE pentagon_report (
  pentagon_report_id          INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  test_conducted_id           INTEGER NOT NULL
);

CREATE TABLE pentagon_box (
  pentagon_box_id             INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  pentagon_report_id          INTEGER NOT NULL,
  box_row                     VARCHAR(250) NOT NULL,
  box_name                    VARCHAR(250),
  report_weight               INTEGER NOT NULL,
  report_score                INTEGER NOT NULL,
  report_score_gap            INTEGER NOT NULL,
  priority_row                INTEGER NOT NULL,
  priority_overall            INTEGER NOT NULL
);

ALTER TABLE profile_usage_value ADD COLUMN link_table VARCHAR(250);
ALTER TABLE profile_usage ADD COLUMN link_guide VARCHAR(250);

update profile_usage set link_guide = 'AK_guide_1-4_2015-10/0' where profile_usage_id = 2;
update profile_usage set link_guide = 'AL_guide_0_6_2015-8/0' where profile_usage_id = 3;
update profile_usage set link_guide = 'AR_IG_2013-1/0' where profile_usage_id = 4;
update profile_usage set link_guide = 'CA_CA_IG_2014-12/0' where profile_usage_id = 5;
update profile_usage set link_guide = 'CA_SD_IG_2014-2/0' where profile_usage_id = 6;
update profile_usage set link_guide = 'CO_IG_2015-10/0' where profile_usage_id = 7;
update profile_usage set link_guide = 'CT_IG_2013-8/0' where profile_usage_id = 8;
update profile_usage set link_guide = 'Envision_IG_2015-2/0' where profile_usage_id = 9;
update profile_usage set link_guide = 'FL_IG_2014-7/0' where profile_usage_id = 10;
update profile_usage set link_guide = 'GA_IG_2015-5/0' where profile_usage_id = 11;
update profile_usage set link_guide = 'HI_IG_2013-4/0' where profile_usage_id = 12;
update profile_usage set link_guide = 'IA_IG_2013-2/0' where profile_usage_id = 13;
update profile_usage set link_guide = 'ID_IG_2015-6/0' where profile_usage_id = 14;
update profile_usage set link_guide = 'IL_IG_2015-4/0' where profile_usage_id = 15;
update profile_usage set link_guide = 'MA_IG_2015-4/0' where profile_usage_id = 17;
update profile_usage set link_guide = 'MD_IG_2014-11/0' where profile_usage_id = 18;
update profile_usage set link_guide = 'ME_IG_2009-7/0' where profile_usage_id = 19;
update profile_usage set link_guide = 'MI_IG_2015-5/0' where profile_usage_id = 20;
update profile_usage set link_guide = 'MN_IG_2014-5/0' where profile_usage_id = 21;
update profile_usage set link_guide = 'MO_IG_2012-6/0' where profile_usage_id = 22;
update profile_usage set link_guide = 'MS_IG_2015-8/0' where profile_usage_id = 23;
update profile_usage set link_guide = 'MT_IG_2013-8/0' where profile_usage_id = 24;
update profile_usage set link_guide = 'NC_IG_2015-6/0' where profile_usage_id = 25;
update profile_usage set link_guide = 'ND_IG_2014-11/0' where profile_usage_id = 26;
update profile_usage set link_guide = 'NE_IG_2015-6/0' where profile_usage_id = 28;
update profile_usage set link_guide = 'NJ_IG_2015-6/0' where profile_usage_id = 29;
update profile_usage set link_guide = 'NM_IS_2009-12/0' where profile_usage_id = 30;
update profile_usage set link_guide = 'NY_IG_2013-11/0' where profile_usage_id = 33;
update profile_usage set link_guide = 'OH_IG_2013-7/0' where profile_usage_id = 34;
update profile_usage set link_guide = 'OR_IG_2014-3/0' where profile_usage_id = 35;
update profile_usage set link_guide = 'STC_IG_2012-9/0' where profile_usage_id = 39;
update profile_usage set link_guide = 'TN_IG_2015-3/0' where profile_usage_id = 40;
update profile_usage set link_guide = 'TX_IG_2014-12/0' where profile_usage_id = 41;
update profile_usage set link_guide = 'UT_IG_2013-9/0' where profile_usage_id = 42;
update profile_usage set link_guide = 'VA_IG_2015-7/0' where profile_usage_id = 43;
update profile_usage set link_guide = 'VT_IG_2014-7/0' where profile_usage_id = 44;
update profile_usage set link_guide = 'WA_IG_2014-4/0' where profile_usage_id = 45;
update profile_usage set link_guide = 'hl7guide-1-5-2014-11/0' where profile_usage_id = 46;
update profile_usage set link_guide = 'LA_IG_2014-11/0' where profile_usage_id = 47;
update profile_usage set link_guide = 'WI_IG_2015-9/0' where profile_usage_id = 50;
update profile_usage set link_guide = 'WV_IG/0' where profile_usage_id = 51;
update profile_usage set version = 'v0.0', link_guide = 'WY_IG_2015-11/0' where profile_usage_id = 52;

update profile_usage set label = 'AL ImmPRINT' where profile_usage_id = 3;
update profile_usage set label = 'AR WebIZ' where profile_usage_id = 4;
update profile_usage set version = 'v1.6' where profile_usage_id = 7;
update profile_usage set version = 'v1.2' where profile_usage_id = 12;
update profile_usage set version = 'v1.7.1' where profile_usage_id = 14;
update profile_usage set version = 'v0.0' where profile_usage_id = 15;
update profile_usage set version = 'v2.5' where profile_usage_id = 17;
update profile_usage set version = 'v0.0' where profile_usage_id = 20;
update profile_usage set version = 'v1.4' where profile_usage_id = 21;
update profile_usage set label = 'MS MIIX', version = 'v3.2' where profile_usage_id = 23;
update profile_usage set label = 'ND NDIIS' where profile_usage_id = 26;
update profile_usage set label = 'NE NESIIS' where profile_usage_id = 28;
update profile_usage set label = 'NJ NJIIS' where profile_usage_id = 29;
update profile_usage set version = 'v1.4.4' where profile_usage_id = 30;
update profile_usage set version = 'v4.1' where profile_usage_id = 33;
update profile_usage set version = 'v1.1' where profile_usage_id = 34;
update profile_usage set label = 'TN TIR', version = 'v1.5' where profile_usage_id = 40;
update profile_usage set version = 'v1.4' where profile_usage_id = 45;


delete from pentagon_box;
delete from assertion_identified;
delete from pentagon_report;

 
create table report_user (
    report_user_id          INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id                 INTEGER NOT NULL,
    test_participant_id     INTEGER NOT NULL,
    authorized_by_user_id   INTEGER NOT NULL,
    authorized_date         DATE NOT NULL,
    report_role             VARCHAR(120) NOT NULL
);

create table user_setting (
    user_setting_id         INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id                 INTEGER NOT NULL,
    setting_key             VARCHAR(250) NOT NULL,
    setting_value           VARCHAR(1200) NOT NULL
);

ALTER TABLE test_conducted ADD COLUMN manual_test VARCHAR(1) NOT NULL DEFAULT 'N';

create table tester_status (
    tester_status_id        INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    tester_name             VARCHAR(250) NOT NULL,
    ready_status            VARCHAR(250) NOT NULL,
    status_date             DATETIME NOT NULL,
    test_conducted_id       INTEGER,
    etc_query_date          DATETIME,
    etc_update_date         DATETIME
);

create table tester_command (
    tester_command_id       INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    tester_name             VARCHAR(250) NOT NULL,
    test_participant_id     INTEGER,
    command_text            VARCHAR(250) NOT NULL,
    command_options         VARCHAR(1200),
    run_date                DATETIME NOT NULL
);

ALTER TABLE test_message ADD COLUMN result_manual_test VARCHAR(1) NOT NULL DEFAULT 'N';

CREATE TABLE test_comment (
    test_comment_id       INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    test_participant_id   INTEGER NOT NULL,
    reply_comment_id      INTEGER,
    user_id               INTEGER NOT NULL,
    test_message_id       INTEGER,
    test_case_category    VARCHAR(250),
    comment_text          TEXT NOT NULL,
    comment_date          DATETIME NOT NULL,
    comment_mood          VARCHAR(250) NOT NULL
);

CREATE TABLE test_case (
    test_case_id          INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    test_participant_id   INTEGER NOT NULL,
    author_comment_id     INTEGER NOT NULL,
    reviewed_comment_id   INTEGER,
    test_case_content     TEXT NOT NULL,
    test_section_type     VARCHAR(250) NOT NULL,
    use_status            VARCHAR(250) NOT NULL    
);

ALTER TABLE user ADD COLUMN reset_password VARCHAR(1) NOT NULL DEFAULT 'N';
ALTER TABLE test_participant ADD COLUMN public_id_code VARCHAR(250);