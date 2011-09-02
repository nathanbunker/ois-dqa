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