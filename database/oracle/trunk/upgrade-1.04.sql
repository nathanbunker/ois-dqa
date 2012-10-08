CREATE SEQUENCE dqa_change_id_sequence INCREMENT BY 1 START WITH 1;

CREATE TABLE dqa_database_log (
  change_id      INTEGER NOT NULL PRIMARY KEY,
  change_date    DATE,
  change_version VARCHAR2(30),
  change_comment VARCHAR2(4000)
);

INSERT INTO dqa_database_log VALUES (dqa_change_id_sequence.NEXTVAL, SYSDATE, '1.04', 'Starting upgrade process');

INSERT INTO dqa_database_log VALUES (dqa_change_id_sequence.NEXTVAL, SYSDATE, '1.04', 'Add new table to hold vaccination product codes derived from received data');

INSERT INTO dqa_database_log VALUES (dqa_change_id_sequence.NEXTVAL, SYSDATE, '1.04', 'Creating new issues to handle invalid and unexpected codes based on administration date');
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id, hl7_reference) VALUES (489, 'Vaccination', 'CPT code', 'is invalid for date administered', '', 'W', 'May', 'Vaccination Count', 'Vaccination CPT code is a known invalid value and will not be accepted.', 27, 'RXA-5');
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id, hl7_reference) VALUES (488, 'Vaccination', 'CPT code', 'is unexpected for date administered', '', 'A', 'May', 'Vaccination Count', 'Vaccination CPT code is not specified.', 27, 'RXA-5');
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id, hl7_reference) VALUES (490, 'Vaccination', 'admin code', 'is unexpected for date administered', '', 'W', 'May', 'Vaccination Count', '', NULL, 'RXA-5');
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id, hl7_reference) VALUES (487, 'Vaccination', 'CVX code', 'is invalid for date administered', '', 'W', 'May', 'Vaccination Count', 'Vaccination CVX code is a known invalid value and will not be accepted.', 28, 'RXA-5');
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id, hl7_reference) VALUES (486, 'Vaccination', 'CVX code', 'is unexpected for date administered', '', 'A', 'May', 'Vaccination Count', 'Vaccination CVX code is not specified.', 28, 'RXA-5');
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id, hl7_reference) VALUES (491, 'Vaccination', 'admin code', 'is invalid for date administered', '', 'E', 'May', 'Vaccination Count', '', NULL, 'RXA-5');
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id, hl7_reference) VALUES (492, 'Vaccination', 'product', 'is unexpected for date administered', '', 'A', 'May', 'Vaccination Admin Count', 'Vaccination product is not specified.', 33, 'RXA-5/RXA-17');
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id, hl7_reference) VALUES (493, 'Vaccination', 'product', 'is invalid for date administered', '', 'E', 'May', 'Vaccination Admin Count', 'Vaccination product is a known invalid value and will not be accepted.', 33, 'RXA-5/RXA-17');
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id, hl7_reference) VALUES (494, 'Vaccination', 'manufacturer code', 'is unexpected for date administered', '', 'W', 'May', 'Vaccination Admin Count', 'Vaccination manufacturer code is not specified.', 30, 'RXA-17');
INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id, hl7_reference) VALUES (495, 'Vaccination', 'manufacturer code', 'is invalid for date administered', '', 'W', 'May', 'Vaccination Admin Count', 'Vaccination manufacturer code is a known invalid value and will not be accepted.', 30, 'RXA-17');

INSERT INTO dqa_database_log VALUES (dqa_change_id_sequence.NEXTVAL, SYSDATE, '1.04', 'Adding default values to templates for the new product codes');
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(489, 251, 'W', 0, 100);	
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(489, 252, 'W', 0, 100);	
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(489, 253, 'W', 0, 100);	
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(489, 254, 'W', 0, 100);	
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(489, 255, 'W', 0, 100);	
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(489, 256, 'W', 0, 100);	
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(489, 257, 'E', 0, 100);	
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(489, 258, 'W', 0, 100);

INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(488, 251, 'A', 0, 100);	
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(488, 252, 'A', 0, 100);	
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(488, 253, 'A', 0, 100);	
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(488, 254, 'A', 0, 100);	
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(488, 255, 'A', 0, 100);	
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(488, 256, 'A', 0, 100);	
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(488, 257, 'A', 0, 100);	
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(488, 258, 'A', 0, 100);

INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(490, 251, 'W', 0, 100);	
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(490, 252, 'W', 0, 100);	
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(490, 253, 'W', 0, 100);	
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(490, 254, 'W', 0, 100);	
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(490, 255, 'W', 0, 100);	
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(490, 256, 'W', 0, 100);	
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(490, 257, 'E', 0, 100);	
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(490, 258, 'W', 0, 100);

INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(487, 251, 'W', 0, 100);	
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(487, 252, 'W', 0, 100);	
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(487, 253, 'W', 0, 100);	
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(487, 254, 'W', 0, 100);	
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(487, 255, 'W', 0, 100);	
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(487, 256, 'W', 0, 100);	
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(487, 257, 'E', 0, 100);	
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(487, 258, 'W', 0, 100);


INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(486, 251, 'A', 0, 100);	
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(486, 252, 'A', 0, 100);	
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(486, 253, 'A', 0, 100);	
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(486, 254, 'A', 0, 100);	
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(486, 255, 'A', 0, 100);	
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(486, 256, 'A', 0, 100);	
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(486, 257, 'A', 0, 100);	
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(486, 258, 'A', 0, 100);

INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(491, 251, 'E', 0, 100);	
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(491, 252, 'E', 0, 100);	
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(491, 253, 'E', 0, 100);	
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(491, 254, 'E', 0, 100);	
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(491, 255, 'E', 0, 100);	
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(491, 256, 'E', 0, 100);	
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(491, 257, 'E', 0, 100);	
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(491, 258, 'E', 0, 100);

INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL,492, 251, 'A', 0, 100);	
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL,492, 252, 'A', 0, 100);	
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL,492, 253, 'A', 0, 100);	
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL,492, 254, 'A', 0, 100);	
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL,492, 255, 'A', 0, 100);	
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL,492, 256, 'A', 0, 100);	
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL,492, 257, 'A', 0, 100);	
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL,492, 258, 'A', 0, 100);

INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL,493, 251, 'E', 0, 100);	
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL,493, 252, 'E', 0, 100);	
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL,493, 253, 'E', 0, 100);	
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL,493, 254, 'E', 0, 100);	
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL,493, 255, 'E', 0, 100);	
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL,493, 256, 'E', 0, 100);	
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL,493, 257, 'E', 0, 100);	
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL,493, 258, 'E', 0, 100);

INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL,494, 251, 'W', 0, 100);	
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL,494, 252, 'W', 0, 100);	
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL,494, 253, 'W', 0, 100);	
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL,494, 254, 'W', 0, 100);	
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL,494, 255, 'W', 0, 100);	
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL,494, 256, 'W', 0, 100);	
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL,494, 257, 'W', 0, 100);	
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL,494, 258, 'A', 0, 100);

INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL,495, 251, 'W', 0, 100);	
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL,495, 252, 'W', 0, 100);	
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL,495, 253, 'W', 0, 100);	
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL,495, 254, 'W', 0, 100);	
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL,495, 255, 'W', 0, 100);	
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL,495, 256, 'W', 0, 100);	
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL,495, 257, 'E', 0, 100);	
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL,495, 258, 'W', 0, 100);

INSERT INTO dqa_database_log VALUES (dqa_change_id_sequence.NEXTVAL, SYSDATE, '1.04', 'Finished upgrading');
