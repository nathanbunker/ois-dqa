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


INSERT INTO dqa_keyed_setting (keyed_id, keyed_code, object_code, object_id, keyed_value) VALUES (dqa_keyed_id_sequence.NEXTVAL, 'validate.header.sending_facility.pfs', 'Application', 2, 'Y');
INSERT INTO dqa_keyed_setting (keyed_id, keyed_code, object_code, object_id, keyed_value) VALUES (dqa_keyed_id_sequence.NEXTVAL, 'validate.header.sending_facility.pfs', 'Application', 3, 'Y');
INSERT INTO dqa_keyed_setting (keyed_id, keyed_code, object_code, object_id, keyed_value) VALUES (dqa_keyed_id_sequence.NEXTVAL, 'validate.header.sending_facility.pfs', 'Application', 10, 'Y');

DROP SEQUENCE dqa_profile_id_sequence;

CREATE SEQUENCE dqa_profile_id_sequence INCREMENT BY 1 START WITH 1200;

INSERT INTO dqa_submitter_profile 
  (profile_id, profile_code, profile_label, profile_status, org_id, data_format, transfer_priority, access_key)
VALUES
  (101, 'TEST:HL7', 'Test HL7', 'Test', 1, 'HL7v2', 'Normal', 'test');


INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (145,'145', 'RSV-Mab (new)', TO_DATE('2011-08-31', 'YYYY-MM-DD'), TO_DATE('2011-08-31', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');

INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (146,'146', 'DTaP,IPV,Hib,HepB', TO_DATE('2011-08-31', 'YYYY-MM-DD'), TO_DATE('2011-08-31', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');

INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description) VALUES (368, 'HL7 NK1', 'set id', 'is missing', '', 'S', 'May', 'NextOfKin Count', '');
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description) VALUES (373, 'Vaccination', 'order control code', 'is deprecated', '', 'W', 'May', 'Vaccination Count', '');
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description) VALUES (369, 'Vaccination', 'order control code', 'is ignored', '', 'S', 'May', 'Vaccination Count', '');
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description) VALUES (370, 'Vaccination', 'order control code', 'is invalid', '', 'W', 'May', 'Vaccination Count', '');
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description) VALUES (371, 'Vaccination', 'order control code', 'is missing', '', 'A', 'May', 'Vaccination Count', '');
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description) VALUES (372, 'Vaccination', 'order control code', 'is unrecognized', '', 'W', 'May', 'Vaccination Count', '');

INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES(34, 'Vaccination Order Control Code', '');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 34, 'RE', 'Observations to follow', 'RE', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 34, 'OK', 'Order accepted &'||' OK', 'OK', 'I');

ALTER TABLE dqa_vaccination ADD (order_control_code VARCHAR2(250));