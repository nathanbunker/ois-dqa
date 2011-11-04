-- DQA_ORGANIZATION;
-- Name                                      
-- ----------------------------------------- 
-- ORG_ID                                    PK
-- ORG_LABEL                                 --
-- ORG_PARENT_ID                             FK ORG_ID
-- PRIMARY_PROFILE_ID                        FK PROFILE_ID
-- ORG_LOCAL_CODE                            --

UPDATE dqa_organization SET org_parent_id = 1 WHERE org_parent_id = 0;
COMMIT;
ALTER TABLE dqa_organization ADD CONSTRAINT dqa_fk_organization1 FOREIGN KEY(org_parent_id) REFERENCES dqa_organization(org_id);
-- TODO You may need to delete duplicate organizations
UPDATE dqa_organization SET primary_profile_id = NULL WHERE primary_profile_id NOT IN (1, 300, 301, 320);
COMMIT;
ALTER TABLE dqa_organization ADD CONSTRAINT dqa_fk_organization2 FOREIGN KEY(primary_profile_id) REFERENCES dqa_submitter_profile(profile_id);

--DQA_USER_ACCOUNT;
-- Name                                      
-- ----------------------------------------- 
-- USERNAME                                  --
-- PASSWORD                                  --
-- ACCOUNT_TYPE                              --
-- ORG_ID                                    FK ORG_ID
-- EMAIL                                     --
ALTER TABLE dqa_user_account ADD CONSTRAINT dqa_fk_user_account1 FOREIGN KEY(org_id) REFERENCES dqa_organization(org_id);


--DQA_POTENTIAL_ISSUE_STATUS;
-- Name                                      
-- ----------------------------------------- 
-- POTENTIAL_ISSUE_STATUS_ID                 PK
-- ISSUE_ID                                  FK ISSUE_ID
-- PROFILE_ID                                FK PROFILE_ID
-- ACTION_CODE                               FK ACTION_CODE
-- EXPECT_MIN                                --
-- EXPECT_MAX                                --

ALTER TABLE dqa_potential_issue_status ADD CONSTRAINT dqa_fk_potential_issue_status1 FOREIGN KEY(issue_id) REFERENCES dqa_potential_issue(issue_id);
ALTER TABLE dqa_potential_issue_status ADD CONSTRAINT dqa_fk_potential_issue_status2 FOREIGN KEY(profile_id) REFERENCES dqa_submitter_profile(profile_id);
ALTER TABLE dqa_potential_issue_status ADD CONSTRAINT dqa_fk_potential_issue_status3 FOREIGN KEY(action_code) REFERENCES dqa_issue_action(action_code);

--DQA_ISSUE_ACTION;
-- Name                                      
-- ----------------------------------------- 
-- ACTION_CODE                               PK
-- ACTION_LABEL                              --

--DQA_MESSAGE_BATCH;
-- Name                                      
-- ----------------------------------------- 
-- BATCH_ID                                  PK
-- BATCH_TITLE                               --
-- TYPE_CODE                                 FK TYPE_CODE
-- START_DATE                                --
-- END_DATE                                  --
-- SUBMIT_CODE                               FK SUBMIT_CODE
-- PROFILE_ID                                FK PROFILE_ID
-- ...

ALTER TABLE dqa_message_batch ADD CONSTRAINT dqa_fk_message_batch1 FOREIGN KEY(type_code) REFERENCES dqa_batch_type(type_code);
ALTER TABLE dqa_message_batch ADD CONSTRAINT dqa_fk_message_batch2 FOREIGN KEY(submit_code) REFERENCES dqa_submit_status(submit_code);
ALTER TABLE dqa_message_batch ADD CONSTRAINT dqa_fk_message_batch3 FOREIGN KEY(profile_id) REFERENCES dqa_submitter_profile(profile_id);


--DQA_RECEIVE_QUEUE;
-- Name                                      
-- ----------------------------------------- 
-- RECEIVE_QUEUE_ID                          PK
-- BATCH_ID                                  FK BATCH_ID
-- RECEIVED_ID                               FK RECEIVED_ID
-- SUBMIT_CODE                               FK SUBMIT_CODE

ALTER TABLE dqa_receive_queue ADD CONSTRAINT dqa_fk_receive_queue1 FOREIGN KEY(batch_id) REFERENCES dqa_message_batch(batch_id);
ALTER TABLE dqa_receive_queue ADD CONSTRAINT dqa_fk_receive_queue2 FOREIGN KEY(received_id) REFERENCES dqa_message_received(received_id);
ALTER TABLE dqa_receive_queue ADD CONSTRAINT dqa_fk_receive_queue3 FOREIGN KEY(submit_code) REFERENCES dqa_submit_status(submit_code);

--DQA_MESSAGE_RECEIVED;
-- Name                                      
-- ----------------------------------------- 
-- RECEIVED_ID                               PK
-- PROFILE_ID                                FK PROFILE_ID
-- RECEIVED_DATE                             --
-- REQUEST_TEXT                              --
-- RESPONSE_TEXT                             --
-- ACTION_CODE                               FK ACTION_CODE
-- SUBMIT_CODE                               FK SUBMIT_CODE
-- PATIENT_ID                                --

ALTER TABLE dqa_message_received ADD CONSTRAINT dqa_fk_message_received1 FOREIGN KEY(profile_id) REFERENCES dqa_submitter_profile(profile_id);
ALTER TABLE dqa_message_received ADD CONSTRAINT dqa_fk_message_received2 FOREIGN KEY(action_code) REFERENCES dqa_issue_action(action_code);
ALTER TABLE dqa_message_received ADD CONSTRAINT dqa_fk_message_received3 FOREIGN KEY(submit_code) REFERENCES dqa_submit_status(submit_code);

--DQA_NEXT_OF_KIN;
-- Name                                      
-- ----------------------------------------- 
-- NEXT_OF_KIN_ID                            PK
-- RECEIVED_ID                               FK RECEIVED_ID
-- POSITION_ID                               --
-- ADDRESS_CITY                              --
-- ADDRESS_COUNTRY                           --
-- ADDRESS_COUNTY_PARISH                     --
-- ADDRESS_STATE                             --
-- ADDRESS_STREET                            --
...
-- SKIPPED                                   --

ALTER TABLE dqa_next_of_kin ADD CONSTRAINT dqa_fk_next_of_kin1 FOREIGN KEY(received_id) REFERENCES dqa_message_received(received_id);

--DQA_ISSUE_FOUND;
-- Name                                      
-- ----------------------------------------- 
-- ISSUE_FOUND_ID                            PK
-- RECEIVED_ID                               FK RECEIVED_ID
-- ISSUE_ID                                  FK ISSUE_ID
-- POSITION_ID                               --
-- ISSUE_NEGATE                              --
-- ACTION_CODE                               FK ACTION_CODE
-- CODE_ID                                   FK CODE_ID

ALTER TABLE dqa_issue_found ADD CONSTRAINT dqa_fk_issue_found1 FOREIGN KEY(received_id) REFERENCES dqa_message_received(received_id);
ALTER TABLE dqa_issue_found ADD CONSTRAINT dqa_fk_issue_found2 FOREIGN KEY(issue_id) REFERENCES dqa_potential_issue(issue_id);
ALTER TABLE dqa_issue_found ADD CONSTRAINT dqa_fk_issue_found3 FOREIGN KEY(action_code) REFERENCES dqa_issue_action(action_code);
ALTER TABLE dqa_issue_found ADD CONSTRAINT dqa_fk_issue_found4 FOREIGN KEY(code_id) REFERENCES dqa_code_received(code_id);

--DQA_CODE_STATUS;
-- Name                                      
-- ----------------------------------------- 
-- CODE_STATUS                               PK
-- CODE_LABEL                                

--DQA_BATCH_TYPE;
-- Name                                      
-- ----------------------------------------- 
-- TYPE_CODE                                 PK
-- TYPE_LABEL                                

--DQA_BATCH_ISSUES;
-- Name                                      
-- ----------------------------------------- 
-- BATCH_ISSUES_ID                           PK
-- BATCH_ID                                  FK BATCH_ID
-- ISSUE_ID                                  FK ISSUE_ID
-- ISSUE_COUNT_POS                           --
-- ISSUE_COUNT_NEG                           --

ALTER TABLE dqa_batch_issues ADD CONSTRAINT dqa_fk_batch_issues1 FOREIGN KEY(batch_id) REFERENCES dqa_message_batch(batch_id);
ALTER TABLE dqa_batch_issues ADD CONSTRAINT dqa_fk_batch_issues2 FOREIGN KEY(issue_id) REFERENCES dqa_potential_issue(issue_id);

--DQA_BATCH_ACTIONS;
-- Name                                      
-- ----------------------------------------- 
-- BATCH_ACTIONS_ID                          PK
-- BATCH_ID                                  FK BATCH_ID
-- ACTION_CODE                               FK ACTION_CODE
-- ACTION_COUNT                              --

ALTER TABLE dqa_batch_actions ADD CONSTRAINT dqa_fk_batch_actions1 FOREIGN KEY(batch_id) REFERENCES dqa_message_batch(batch_id);
ALTER TABLE dqa_batch_actions ADD CONSTRAINT dqa_fk_batch_actions2 FOREIGN KEY(action_code) REFERENCES dqa_issue_action(action_code);

--DQA_SUBMIT_STATUS;
-- Name                                      
-- ----------------------------------------- 
-- SUBMIT_CODE                               PK
-- SUBMIT_LABEL                              --

--DQA_PATIENT;
-- Name                                      
-- ----------------------------------------- -------- ----------------------------
-- PATIENT_ID                                PK
-- RECEIVED_ID                               FK
--...

--DQA_POTENTIAL_ISSUE;
-- Name                                      
-- ----------------------------------------- 
-- ISSUE_ID                                  PK
-- TARGET_OBJECT                             --
-- TARGET_FIELD                              --
-- ISSUE_TYPE                                --
-- FIELD_VALUE                               --
-- DEFAULT_ACTION_CODE                       FK ACTION_CODE
-- CHANGE_PRIORITY                           --
-- REPORT_DENOMINATOR                        --
-- ISSUE_DESCRIPTION                         --

ALTER TABLE dqa_potential_issue ADD CONSTRAINT dqa_fk_potential_issue1 FOREIGN KEY(default_action_code) REFERENCES dqa_issue_action(action_code);

--DQA_VACCINE_GROUP;
-- Name                                      
-- ----------------------------------------- 
-- GROUP_ID                                  PK
-- GROUP_CODE                                AK
-- GROUP_LABEL                               --
-- GROUP_STATUS                              --

--DQA_VACCINE_MVX;
-- Name                                      
-- ----------------------------------------- 
-- MVX_CODE                                  PK
-- MVX_LABEL                                 --
-- VALID_START_DATE                          --
-- USE_START_DATE                            --
-- USE_END_DATE                              --
-- VALID_END_DATE                            --

--DQA_VACCINATION;
-- Name                                      
-- ----------------------------------------- 
-- VACCINATION_ID                            PK
-- RECEIVED_ID                               FK RECEIVED_ID
-- POSITION_ID                               --
-- ...
-- SKIPPED                                   --

ALTER TABLE dqa_vaccination ADD CONSTRAINT dqa_fk_vaccination2 FOREIGN KEY(received_id) REFERENCES dqa_message_received(received_id);

--DQA_CODE_MASTER;
-- Name                                      
-- ----------------------------------------- 
-- CODE_MASTER_ID                            PK
-- TABLE_ID                                  FK TABLE_ID
-- CODE_VALUE                                --
-- CODE_LABEL                                --
-- USE_VALUE                                 --
-- CODE_STATUS                               FK CODE_STATUS

ALTER TABLE dqa_code_master ADD CONSTRAINT dqa_fk_code_master1 FOREIGN KEY(table_id) REFERENCES dqa_code_table(table_id);
ALTER TABLE dqa_code_master ADD CONSTRAINT dqa_fk_code_master2 FOREIGN KEY(code_status) REFERENCES dqa_code_status(code_status);

--DQA_KEYED_SETTING;
-- Name                                      
-- ----------------------------------------- 
-- KEYED_ID                                  PK
-- KEYED_CODE                                --
-- OBJECT_CODE                               --
-- OBJECT_ID                                 --
-- KEYED_VALUE                               --

--DQA_APPLICATION;
-- Name                                      
-- ----------------------------------------- 
-- APPLICATION_ID                            PK
-- APPLICATION_LABEL                         --
-- APPLICATION_TYPE                          --
-- RUN_THIS                                  --

--DQA_BATCH_CODE_RECEIVED;
-- Name                                      
-- ----------------------------------------- 
-- BATCH_CODE_RECEIVED_ID                    PK
-- BATCH_ID                                  FK BATCH_ID
-- CODED_ID                                  FK CODED_ID
-- RECEIVED_COUNT                            --

ALTER TABLE dqa_batch_code_received ADD CONSTRAINT dqa_fk_batch_code_received1 FOREIGN KEY(batch_id) REFERENCES dqa_message_batch(batch_id);
ALTER TABLE dqa_batch_code_received ADD CONSTRAINT dqa_fk_batch_code_received2 FOREIGN KEY(coded_id) REFERENCES dqa_code_received(code_id);

--DQA_CODE_TABLE;
-- Name                                      
-- ----------------------------------------- 
-- TABLE_ID                                  PK
-- TABLE_LABEL                               --
-- DEFAULT_CODE_VALUE                        --

--DQA_CODE_RECEIVED;
-- Name                                      
-- ----------------------------------------- 
-- CODE_ID                                   PK
-- CODE_LABEL                                --
-- PROFILE_ID                                FK PROFILE_ID
-- TABLE_ID                                  FK TABLE_ID
-- RECEIVED_VALUE                            --
-- CODE_VALUE                                --
-- CODE_STATUS                               FK CODE_STATUS
-- RECEIVED_COUNT                            --

ALTER TABLE dqa_code_received ADD CONSTRAINT dqa_fk_code_received1 FOREIGN KEY(profile_id) REFERENCES dqa_submitter_profile(profile_id);
ALTER TABLE dqa_code_received ADD CONSTRAINT dqa_fk_code_received2 FOREIGN KEY(table_id) REFERENCES dqa_code_table(table_id);
ALTER TABLE dqa_code_received ADD CONSTRAINT dqa_fk_code_received3 FOREIGN KEY(code_status) REFERENCES dqa_code_status(code_status);
ALTER TABLE dqa_code_received ADD CONSTRAINT dqa_uk_code_received1 UNIQUE(profile_id, table_id, received_value);

DROP TABLE dqa_vaccine_cvx;

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

INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (998,'998', 'no vaccine administered', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), 1, 1440, 'non vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (99,'99', 'RESERVED - do not use', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), 1, 1440, 'non vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (999,'999', 'unknown', TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('1970-01-01', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), TO_DATE('2010-05-28', 'YYYY-MM-DD'), 1, 1440, 'non vaccine');
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
COMMIT;

--DQA_VACCINE_CVX_GROUP;
-- Name                                      
-- ----------------------------------------- 
-- CVX_GROUP_ID                              PK
-- GROUP_ID                                  FK GROUP_ID
-- CVX_ID                                    FK CVX_ID

ALTER TABLE dqa_vaccine_cvx_group ADD CONSTRAINT dqa_fk_cvx_vaccine_group1 FOREIGN KEY(group_id) REFERENCES dqa_vaccine_group(group_id);
ALTER TABLE dqa_vaccine_cvx_group ADD CONSTRAINT dqa_fk_cvx_vaccine_group2 FOREIGN KEY(cvx_id) REFERENCES dqa_vaccine_cvx(cvx_id);

--DQA_VACCINE_PRODUCT;
-- Name                                      
-- ----------------------------------------- 
-- PRODUCT_ID                                PK
-- PRODUCT_NAME                              --
-- PRODUCT_LABEL                             --
-- CVX_ID                                    FK CVX_ID
-- MVX_CODE                                  FC MVX_CODE
-- VALID_START_DATE                          --
-- USE_START_DATE                            --
-- USE_END_DATE                              --
-- VALID_END_DATE                            --

ALTER TABLE dqa_vaccine_product ADD CONSTRAINT dqa_fk_cvx_vaccine_product1 FOREIGN KEY(cvx_id) REFERENCES dqa_vaccine_cvx(cvx_id);
ALTER TABLE dqa_vaccine_product ADD CONSTRAINT dqa_fk_cvx_vaccine_product2 FOREIGN KEY(mvx_code) REFERENCES dqa_vaccine_mvx(mvx_code);

--DQA_VACCINE_CVX;
-- Name                                      
-- ----------------------------------------- 
-- CVX_ID                                    PK
-- CVX_CODE                                  AK
-- CVX_LABEL                                 --
-- VALID_START_DATE                          --
-- USE_START_DATE                            --
-- USE_END_DATE                              --
-- VALID_END_DATE                            --
-- USE_MONTH_START                           --
-- USE_MONTH_END                             --
-- CONCEPT_TYPE                              --

--DQA_VACCINE_CPT;
-- Name                                      
-- ----------------------------------------- 
-- CPT_ID                                    PK
-- CPT_CODE                                  
-- CPT_LABEL                                 
-- VALID_START_DATE                          
-- USE_START_DATE                            
-- USE_END_DATE                              
-- VALID_END_DATE                            
-- CVX_ID                                    FK CVX_ID

ALTER TABLE dqa_vaccine_cpt ADD CONSTRAINT dqa_fk_cvx_vaccine_cpt1 FOREIGN KEY(cvx_id) REFERENCES dqa_vaccine_cvx(cvx_id);

--DQA_BATCH_VACCINE_CVX;
-- Name                                      
-- ----------------------------------------- 
-- BATCH_VACCINE_CVX_ID                      PK
-- BATCH_ID                                  FK BATCH_ID
-- CVX_ID                                    FK CVX_ID
-- RECEIVED_COUNT                            --

ALTER TABLE dqa_batch_vaccine_cvx ADD CONSTRAINT dqa_fk_batch_vaccine_cvx1 FOREIGN KEY(batch_id) REFERENCES dqa_message_batch(batch_id);
ALTER TABLE dqa_batch_vaccine_cvx ADD CONSTRAINT dqa_fk_batch_vaccine_cvx2 FOREIGN KEY(cvx_id) REFERENCES dqa_vaccine_cvx(cvx_id);

--DQA_SUBMITTER_PROFILE;
-- Name                                      
-- ----------------------------------------- 
-- PROFILE_ID                                PK
-- PROFILE_CODE                              AK
-- PROFILE_LABEL                             v
-- PROFILE_STATUS                            --
-- ORG_ID                                    FK ORG_ID
-- DATA_FORMAT                               --
-- TRANSFER_PRIORITY                         --
-- ACCESS_KEY                                --

ALTER TABLE dqa_submitter_profile ADD CONSTRAINT dqa_fk_submitter_profile1 FOREIGN KEY(org_id) REFERENCES dqa_organization(org_id);


update dqa_potential_issue set target_field = 'primary language' where target_field = 'primary lanaguage';
update dqa_potential_issue set report_denominator = 'NextOfKin Count' where report_denominator='Next-of-Kin Count';
update dqa_potential_issue set default_action_code = 'E' where issue_id = 35;


INSERT INTO dqa_keyed_setting (keyed_id, keyed_code, object_code, object_id, keyed_value) VALUES (dqa_keyed_id_sequence.NEXTVAL, 'validate.header.sending_facility.numeric', 'Application', 1, 'Y');
INSERT INTO dqa_keyed_setting (keyed_id, keyed_code, object_code, object_id, keyed_value) VALUES (dqa_keyed_id_sequence.NEXTVAL, 'validate.header.sending_facility.min_len', 'Application', 1, '10');
INSERT INTO dqa_keyed_setting (keyed_id, keyed_code, object_code, object_id, keyed_value) VALUES (dqa_keyed_id_sequence.NEXTVAL, 'validate.header.sending_facility.max_len', 'Application', 1, '10');
INSERT INTO dqa_keyed_setting (keyed_id, keyed_code, object_code, object_id, keyed_value) VALUES (dqa_keyed_id_sequence.NEXTVAL, 'validate.vaccination.facility.numeric', 'Application', 1, 'Y');
INSERT INTO dqa_keyed_setting (keyed_id, keyed_code, object_code, object_id, keyed_value) VALUES (dqa_keyed_id_sequence.NEXTVAL, 'validate.vaccination.facility.min_len', 'Application', 1, '10');
INSERT INTO dqa_keyed_setting (keyed_id, keyed_code, object_code, object_id, keyed_value) VALUES (dqa_keyed_id_sequence.NEXTVAL, 'validate.vaccination.facility.max_len', 'Application', 1, '10');

UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='Blocked', report_denominator='Message Count' WHERE issue_id='2';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='Blocked', report_denominator='Message Count' WHERE issue_id='3';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='Blocked', report_denominator='Message Count' WHERE issue_id='4';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='Blocked', report_denominator='Message Count' WHERE issue_id='5';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Message Count' WHERE issue_id='6';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Message Count' WHERE issue_id='7';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Message Count' WHERE issue_id='8';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Message Count' WHERE issue_id='9';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Message Count' WHERE issue_id='10';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='Blocked', report_denominator='Message Count' WHERE issue_id='11';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='Blocked', report_denominator='Message Count' WHERE issue_id='12';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', report_denominator='Message Count' WHERE issue_id='13';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Message Count' WHERE issue_id='14';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', report_denominator='Message Count' WHERE issue_id='15';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', report_denominator='Message Count' WHERE issue_id='16';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', report_denominator='Message Count' WHERE issue_id='17';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Message Count' WHERE issue_id='18';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', report_denominator='Message Count' WHERE issue_id='19';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Message Count' WHERE issue_id='20';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='Blocked', report_denominator='Message Count' WHERE issue_id='21';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='Blocked', report_denominator='Message Count' WHERE issue_id='22';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Message Count' WHERE issue_id='23';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Message Count' WHERE issue_id='24';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Message Count' WHERE issue_id='25';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Message Count' WHERE issue_id='26';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Message Count' WHERE issue_id='27';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Message Count' WHERE issue_id='28';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Message Count' WHERE issue_id='29';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Message Count' WHERE issue_id='30';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Message Count' WHERE issue_id='31';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Message Count' WHERE issue_id='32';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='Blocked', report_denominator='Message Count' WHERE issue_id='33';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Message Count' WHERE issue_id='34';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Message Count' WHERE issue_id='35';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Message Count' WHERE issue_id='36';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Message Count' WHERE issue_id='37';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Message Count' WHERE issue_id='38';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Message Count' WHERE issue_id='39';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Message Count' WHERE issue_id='40';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Message Count' WHERE issue_id='41';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Message Count' WHERE issue_id='42';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='Should', report_denominator='NextOfKin Count' WHERE issue_id='43';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='NextOfKin Count' WHERE issue_id='44';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Observation Count' WHERE issue_id='45';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='46';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='Blocked', report_denominator='Message Count' WHERE issue_id='47';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Message Count' WHERE issue_id='48';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='Blocked', report_denominator='Message Count' WHERE issue_id='49';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='Blocked', report_denominator='Message Count' WHERE issue_id='50';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Message Count' WHERE issue_id='51';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='Can', report_denominator='Message Count' WHERE issue_id='52';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='Can', report_denominator='Message Count' WHERE issue_id='53';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='54';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='55';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='NextOfKin Count' WHERE issue_id='56';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='NextOfKin Count' WHERE issue_id='57';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='NextOfKin Count' WHERE issue_id='58';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='NextOfKin Count' WHERE issue_id='59';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='NextOfKin Count' WHERE issue_id='60';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', report_denominator='NextOfKin Count' WHERE issue_id='61';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='NextOfKin Count' WHERE issue_id='62';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='NextOfKin Count' WHERE issue_id='63';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='NextOfKin Count' WHERE issue_id='64';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='NextOfKin Count' WHERE issue_id='65';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', report_denominator='NextOfKin Count' WHERE issue_id='66';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='NextOfKin Count' WHERE issue_id='67';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='NextOfKin Count' WHERE issue_id='68';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='NextOfKin Count' WHERE issue_id='69';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='NextOfKin Count' WHERE issue_id='70';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', report_denominator='NextOfKin Count' WHERE issue_id='71';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='NextOfKin Count' WHERE issue_id='72';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='NextOfKin Count' WHERE issue_id='73';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='NextOfKin Count' WHERE issue_id='74';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='NextOfKin Count' WHERE issue_id='75';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='NextOfKin Count' WHERE issue_id='76';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='NextOfKin Count' WHERE issue_id='77';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='NextOfKin Count' WHERE issue_id='78';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='Can', report_denominator='NextOfKin Count' WHERE issue_id='79';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='Can', report_denominator='NextOfKin Count' WHERE issue_id='80';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='Can', report_denominator='NextOfKin Count' WHERE issue_id='81';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='NextOfKin Count' WHERE issue_id='82';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='NextOfKin Count' WHERE issue_id='83';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='Can', report_denominator='NextOfKin Count' WHERE issue_id='84';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='NextOfKin Count' WHERE issue_id='85';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', report_denominator='NextOfKin Count' WHERE issue_id='86';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='NextOfKin Count' WHERE issue_id='87';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='Should', report_denominator='NextOfKin Count' WHERE issue_id='88';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='NextOfKin Count' WHERE issue_id='89';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='NextOfKin Count' WHERE issue_id='90';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='NextOfKin Count' WHERE issue_id='91';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='Should', report_denominator='Patient Count' WHERE issue_id='92';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', report_denominator='Patient Count' WHERE issue_id='93';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', report_denominator='Patient Count' WHERE issue_id='94';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Patient Count' WHERE issue_id='95';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', report_denominator='Patient Count' WHERE issue_id='96';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Patient Count' WHERE issue_id='97';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Patient Count' WHERE issue_id='98';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Patient Count' WHERE issue_id='99';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Patient Count' WHERE issue_id='100';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', report_denominator='Patient Count' WHERE issue_id='101';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Patient Count' WHERE issue_id='102';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Patient Count' WHERE issue_id='103';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Patient Count' WHERE issue_id='104';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Patient Count' WHERE issue_id='105';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', report_denominator='Patient Count' WHERE issue_id='106';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Patient Count' WHERE issue_id='107';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', report_denominator='Patient Count' WHERE issue_id='108';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Patient Count' WHERE issue_id='109';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', report_denominator='Patient Count' WHERE issue_id='110';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='Can', report_denominator='Patient Count' WHERE issue_id='111';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Patient Count' WHERE issue_id='112';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', report_denominator='Patient Count' WHERE issue_id='113';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Patient Count' WHERE issue_id='114';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='Blocked', report_denominator='Patient Count' WHERE issue_id='115';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='Blocked', report_denominator='Patient Count' WHERE issue_id='116';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='Blocked', report_denominator='Patient Count' WHERE issue_id='117';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='Blocked', report_denominator='Patient Count' WHERE issue_id='118';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Patient Count' WHERE issue_id='119';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='Blocked', report_denominator='Patient Count' WHERE issue_id='120';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Patient Count' WHERE issue_id='121';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='Should', report_denominator='Patient Count' WHERE issue_id='122';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Patient Count' WHERE issue_id='123';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='Should', report_denominator='Patient Count' WHERE issue_id='124';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', report_denominator='Patient Count' WHERE issue_id='125';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Patient Count' WHERE issue_id='126';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Patient Count' WHERE issue_id='127';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Patient Count' WHERE issue_id='128';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='Blocked', report_denominator='Patient Count' WHERE issue_id='129';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='Blocked', report_denominator='Patient Count' WHERE issue_id='130';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='Blocked', report_denominator='Patient Count' WHERE issue_id='131';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Patient Count' WHERE issue_id='132';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', report_denominator='Patient Count' WHERE issue_id='133';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Patient Count' WHERE issue_id='134';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Patient Count' WHERE issue_id='135';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', report_denominator='Patient Count' WHERE issue_id='136';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Patient Count' WHERE issue_id='137';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='Should', report_denominator='Patient Count' WHERE issue_id='138';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Patient Count' WHERE issue_id='139';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', report_denominator='Patient Count' WHERE issue_id='140';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', report_denominator='Patient Count' WHERE issue_id='141';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Patient Count' WHERE issue_id='142';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Patient Count' WHERE issue_id='143';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', report_denominator='Patient Count' WHERE issue_id='144';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', report_denominator='Patient Count' WHERE issue_id='145';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='Blocked', report_denominator='Patient Count' WHERE issue_id='146';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', report_denominator='Patient Count' WHERE issue_id='147';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Patient Underage Count' WHERE issue_id='148';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Patient Underage Count' WHERE issue_id='149';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Patient Underage Count' WHERE issue_id='150';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Patient Underage Count' WHERE issue_id='151';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Patient Underage Count' WHERE issue_id='152';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Patient Underage Count' WHERE issue_id='153';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Patient Underage Count' WHERE issue_id='154';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', report_denominator='Patient Underage Count' WHERE issue_id='155';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Patient Underage Count' WHERE issue_id='156';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Patient Underage Count' WHERE issue_id='157';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Patient Underage Count' WHERE issue_id='158';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', report_denominator='Patient Underage Count' WHERE issue_id='159';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Patient Count' WHERE issue_id='160';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', report_denominator='Patient Count' WHERE issue_id='161';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Patient Count' WHERE issue_id='162';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Patient Count' WHERE issue_id='163';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Patient Count' WHERE issue_id='164';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', report_denominator='Patient Count' WHERE issue_id='165';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', report_denominator='Patient Count' WHERE issue_id='166';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='Can', report_denominator='Patient Count' WHERE issue_id='167';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='Can', report_denominator='Patient Count' WHERE issue_id='168';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Patient Count' WHERE issue_id='169';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Patient Count' WHERE issue_id='170';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='Should', report_denominator='Patient Count' WHERE issue_id='171';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='Should', report_denominator='Patient Count' WHERE issue_id='172';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='Should', report_denominator='Patient Count' WHERE issue_id='173';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Patient Count' WHERE issue_id='174';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Patient Count' WHERE issue_id='175';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Patient Count' WHERE issue_id='176';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Patient Count' WHERE issue_id='177';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', report_denominator='Patient Count' WHERE issue_id='178';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', report_denominator='Patient Count' WHERE issue_id='179';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Patient Count' WHERE issue_id='180';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Patient Count' WHERE issue_id='181';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Patient Count' WHERE issue_id='182';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Patient Count' WHERE issue_id='183';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', report_denominator='Patient Count' WHERE issue_id='184';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Patient Count' WHERE issue_id='185';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Patient Count' WHERE issue_id='186';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Patient Count' WHERE issue_id='187';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Patient Count' WHERE issue_id='188';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', report_denominator='Patient Count' WHERE issue_id='189';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Patient Count' WHERE issue_id='190';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Patient Count' WHERE issue_id='191';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Patient Count' WHERE issue_id='192';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Patient Count' WHERE issue_id='193';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Patient Count' WHERE issue_id='194';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', report_denominator='Patient Count' WHERE issue_id='195';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Patient Count' WHERE issue_id='196';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Patient Count' WHERE issue_id='197';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Patient Count' WHERE issue_id='198';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Patient Count' WHERE issue_id='199';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Patient Count' WHERE issue_id='200';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Patient Count' WHERE issue_id='201';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', report_denominator='Patient Count' WHERE issue_id='202';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Patient Count' WHERE issue_id='203';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Patient Count' WHERE issue_id='204';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Patient Count' WHERE issue_id='205';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Patient Count' WHERE issue_id='206';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', report_denominator='Patient Count' WHERE issue_id='207';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Patient Count' WHERE issue_id='208';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Patient Count' WHERE issue_id='209';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Patient Count' WHERE issue_id='210';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Patient Count' WHERE issue_id='211';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Patient Count' WHERE issue_id='212';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Patient Count' WHERE issue_id='213';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', report_denominator='Patient Count' WHERE issue_id='214';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Patient Count' WHERE issue_id='215';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Patient Count' WHERE issue_id='216';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Patient Count' WHERE issue_id='217';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Patient Count' WHERE issue_id='218';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Patient Count' WHERE issue_id='219';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='Blocked', report_denominator='Patient Count' WHERE issue_id='220';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', report_denominator='Patient Count' WHERE issue_id='221';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', report_denominator='Patient Count' WHERE issue_id='222';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', report_denominator='Patient Count' WHERE issue_id='223';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Patient Count' WHERE issue_id='224';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Patient Count' WHERE issue_id='225';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', report_denominator='Patient Count' WHERE issue_id='226';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Patient Count' WHERE issue_id='227';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Patient Count' WHERE issue_id='228';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Patient Count' WHERE issue_id='229';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Patient Count' WHERE issue_id='230';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Patient Count' WHERE issue_id='231';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='232';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='233';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='234';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='235';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='236';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='237';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='238';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='239';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='240';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='241';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='242';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='243';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='244';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Vaccination Admin Count' WHERE issue_id='245';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='246';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='247';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='248';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='249';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='250';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Vaccination Admin Count' WHERE issue_id='251';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='252';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='253';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='254';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='255';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='256';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='257';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='258';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='259';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='260';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='261';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='262';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='263';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='264';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Vaccination Admin Count' WHERE issue_id='265';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='266';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='267';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Vaccination Admin Count' WHERE issue_id='268';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Vaccination Admin Count' WHERE issue_id='269';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Vaccination Admin Count' WHERE issue_id='270';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Vaccination Admin Count' WHERE issue_id='271';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Vaccination Admin Count' WHERE issue_id='272';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Vaccination Admin Count' WHERE issue_id='273';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', report_denominator='Vaccination Admin Count' WHERE issue_id='274';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Vaccination Admin Count' WHERE issue_id='275';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Vaccination Admin Count' WHERE issue_id='276';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Vaccination Admin Count' WHERE issue_id='277';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Vaccination Admin Count' WHERE issue_id='278';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Vaccination Admin Count' WHERE issue_id='279';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', report_denominator='Vaccination Admin Count' WHERE issue_id='280';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Vaccination Admin Count' WHERE issue_id='281';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Vaccination Admin Count' WHERE issue_id='282';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Vaccination Admin Count' WHERE issue_id='283';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Vaccination Admin Count' WHERE issue_id='284';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='285';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='286';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='287';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='288';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='289';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='290';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='291';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='292';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='293';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='294';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='295';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='296';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='297';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='298';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='299';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='300';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='301';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='302';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='303';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='304';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='305';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='306';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='307';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='308';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='309';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='310';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Vaccination Admin Count' WHERE issue_id='311';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', report_denominator='Vaccination Admin Count' WHERE issue_id='312';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', report_denominator='Vaccination Admin Count' WHERE issue_id='313';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', report_denominator='Vaccination Admin Count' WHERE issue_id='314';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Vaccination Admin Count' WHERE issue_id='315';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Vaccination Admin Count' WHERE issue_id='316';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Vaccination Admin Count' WHERE issue_id='317';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', report_denominator='Vaccination Admin Count' WHERE issue_id='318';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Vaccination Admin Count' WHERE issue_id='319';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Vaccination Admin Count' WHERE issue_id='320';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Vaccination Admin Count' WHERE issue_id='321';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='322';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='323';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='324';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='325';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='326';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='327';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='328';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='329';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='330';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='331';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='332';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='333';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='334';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='335';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Vaccination Admin Count' WHERE issue_id='336';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Vaccination Admin Count' WHERE issue_id='337';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Vaccination Admin Count' WHERE issue_id='338';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Vaccination Admin Count' WHERE issue_id='339';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Vaccination Admin Count' WHERE issue_id='340';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', report_denominator='Vaccination Admin Count' WHERE issue_id='341';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Vaccination Admin Count' WHERE issue_id='342';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Vaccination Admin Count' WHERE issue_id='343';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Vaccination Admin Count' WHERE issue_id='344';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Vaccination Admin Count' WHERE issue_id='345';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', report_denominator='Vaccination Admin Count' WHERE issue_id='346';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Vaccination Admin Count' WHERE issue_id='347';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Vaccination Admin Count' WHERE issue_id='348';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Vaccination Admin Count' WHERE issue_id='349';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Vaccination Admin Count' WHERE issue_id='350';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', report_denominator='Vaccination Admin Count' WHERE issue_id='351';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Vaccination Admin Count' WHERE issue_id='352';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Vaccination Admin Count' WHERE issue_id='353';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Vaccination Admin Count' WHERE issue_id='354';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', report_denominator='Vaccination Admin Count' WHERE issue_id='355';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Vaccination Admin Count' WHERE issue_id='356';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Vaccination Admin Count' WHERE issue_id='357';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Vaccination Admin Count' WHERE issue_id='358';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', report_denominator='Vaccination Admin Count' WHERE issue_id='359';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', report_denominator='Vaccination Admin Count' WHERE issue_id='360';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', report_denominator='Vaccination Admin Count' WHERE issue_id='361';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', report_denominator='Vaccination Admin Count' WHERE issue_id='362';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Vaccination Admin Count' WHERE issue_id='363';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', report_denominator='Vaccination Admin Count' WHERE issue_id='364';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='365';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='366';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', report_denominator='Vaccination Count' WHERE issue_id='367';


COMMIT;
