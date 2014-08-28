USE dqa;
INSERT INTO dqa_database_log VALUES (dqa_change_id_sequence.NEXTVAL, NOW(), '1.08', 'Starting upgrade process');

INSERT INTO dqa_database_log VALUES (dqa_change_id_sequence.NEXTVAL, NOW(), '1.08', 'Adding fields to patient table for AIRA ASC report');
ALTER TABLE dqa_patient ADD COLUMN (system_creation_date DATE);

INSERT INTO dqa_database_log VALUES (dqa_change_id_sequence.NEXTVAL, NOW(), '1.08', 'Adding fields to vaccination table for AIRA ASC report');
ALTER TABLE dqa_vaccination ADD COLUMN (facility_type_code VARCHAR2(250));
ALTER TABLE dqa_vaccination ADD COLUMN (trade_name_code VARCHAR2(250));
ALTER TABLE dqa_vaccination ADD COLUMN (vaccine_validity_code VARCHAR2(250));

INSERT INTO dqa_database_log VALUES (dqa_change_id_sequence.NEXTVAL, NOW(), '1.08', 'Adding new tables to support new values for AIRA ASC report');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES(61, 'Potential Issue', '');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES(62, 'Vaccine Group', '');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES(63, 'Facility Type', '');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES(64, 'Vaccination Trade Name', '');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES(65, 'Vaccination Validity', '');

INSERT INTO dqa_database_log VALUES (dqa_change_id_sequence.NEXTVAL, NOW(), '1.08', 'Adding new potential issues');
REPLACE INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description) VALUES (584, 'Patient', 'system creation date', 'is missing', '', 'A', 'May', 'Patient Count', '');
REPLACE INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description) VALUES (585, 'Patient', 'system creation date', 'is invalid', '', 'W', 'May', 'Patient Count', '');
REPLACE INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description) VALUES (586, 'Patient', 'system creation date', 'is before birth', '', 'W', 'May', 'Patient Count', '');
REPLACE INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description) VALUES (587, 'Patient', 'system creation date', 'is in future', '', 'W', 'May', 'Patient Count', '');
REPLACE INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description) VALUES (588, 'Vaccination', 'facility type', 'is deprecated', '', 'W', 'May', '', '');
REPLACE INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description) VALUES (589, 'Vaccination', 'facility type', 'is ignored', '', 'S', 'May', '', '');
REPLACE INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description) VALUES (590, 'Vaccination', 'facility type', 'is invalid', '', 'W', 'May', '', '');
REPLACE INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description) VALUES (591, 'Vaccination', 'facility type', 'is missing', '', 'A', 'May', '', '');
REPLACE INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description) VALUES (592, 'Vaccination', 'facility type', 'is unrecognized', '', 'W', 'May', '', '');
REPLACE INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description) VALUES (593, 'Vaccination', 'facility type', 'is valued as', 'public', 'A', 'May', '', '');
REPLACE INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description) VALUES (594, 'Vaccination', 'facility type', 'is valued as', 'private', 'A', 'May', '', '');
REPLACE INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description) VALUES (595, 'Vaccination', 'trade name', 'is deprecated', '', 'W', '', '', '');
REPLACE INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description) VALUES (596, 'Vaccination', 'trade name', 'is ignored', '', 'S', '', '', '');
REPLACE INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description) VALUES (597, 'Vaccination', 'trade name', 'is invalid', '', 'W', '', '', '');
REPLACE INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description) VALUES (598, 'Vaccination', 'trade name', 'is missing', '', 'A', '', '', '');
REPLACE INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description) VALUES (599, 'Vaccination', 'trade name', 'is unrecognized', '', 'W', '', '', '');
REPLACE INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description) VALUES (600, 'Vaccination', 'trade name and vaccine', 'are inconsistent', '', 'W', '', '', '');
REPLACE INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description) VALUES (601, 'Vaccination', 'trade name and manufacturer', 'are inconsistent', '', 'W', '', '', '');
REPLACE INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description) VALUES (603, 'Vaccination', 'validity code', 'is invalid', '', 'W', '', '', '');
REPLACE INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description) VALUES (604, 'Vaccination', 'validity code', 'is deprecated', '', 'W', '', '', '');
REPLACE INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description) VALUES (605, 'Vaccination', 'validity code', 'is ignored', '', 'S', '', '', '');
REPLACE INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description) VALUES (606, 'Vaccination', 'validity code', 'is missing', '', 'A', '', '', '');
REPLACE INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description) VALUES (607, 'Vaccination', 'validity code', 'is unrecognized', '', 'W', '', '', '');
REPLACE INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description) VALUES (608, 'Vaccination', 'validity code', 'is valued as', 'valid', 'A', '', '', '');
REPLACE INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description) VALUES (609, 'Vaccination', 'validity code', 'is valued as', 'invalid', 'A', '', '', '');
REPLACE INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description) VALUES (610, 'Vaccination', 'body route', 'is invalid for body site indicated', '', 'W', '', '', '');

INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 584, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 585, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 586, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 587, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 588, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 589, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 590, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 591, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 592, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 593, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 594, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 595, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 596, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 597, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 598, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 599, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 600, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 601, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 603, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 604, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 605, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 606, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 607, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 608, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 609, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 610, 251, 'W', 0, 100);

INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 584, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 585, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 586, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 587, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 588, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 589, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 590, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 591, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 592, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 593, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 594, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 595, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 596, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 597, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 598, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 599, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 600, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 601, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 603, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 604, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 605, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 606, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 607, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 608, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 609, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 610, 252, 'W', 0, 100);

INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 584, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 585, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 586, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 587, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 588, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 589, 253, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 590, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 591, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 592, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 593, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 594, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 595, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 596, 253, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 597, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 598, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 599, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 600, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 601, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 603, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 604, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 605, 253, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 606, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 607, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 608, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 609, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 610, 253, 'W', 0, 100);

INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 584, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 585, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 586, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 587, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 588, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 589, 254, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 590, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 591, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 592, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 593, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 594, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 595, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 596, 254, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 597, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 598, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 599, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 600, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 601, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 603, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 604, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 605, 254, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 606, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 607, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 608, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 609, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 610, 254, 'W', 0, 100);

INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 584, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 585, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 586, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 587, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 588, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 589, 255, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 590, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 591, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 592, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 593, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 594, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 595, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 596, 255, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 597, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 598, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 599, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 600, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 601, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 603, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 604, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 605, 255, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 606, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 607, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 608, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 609, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 610, 255, 'W', 0, 100);

INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 584, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 585, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 586, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 587, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 588, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 589, 256, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 590, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 591, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 592, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 593, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 594, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 595, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 596, 256, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 597, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 598, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 599, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 600, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 601, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 603, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 604, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 605, 256, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 606, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 607, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 608, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 609, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 610, 256, 'W', 0, 100);

INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 584, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 585, 257, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 586, 257, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 587, 257, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 588, 257, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 589, 257, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 590, 257, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 591, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 592, 257, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 593, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 594, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 595, 257, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 596, 257, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 597, 257, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 598, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 599, 257, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 600, 257, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 601, 257, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 603, 257, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 604, 257, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 605, 257, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 606, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 607, 257, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 608, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 609, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 610, 257, 'W', 0, 100);

INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 584, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 585, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 586, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 587, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 588, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 589, 258, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 590, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 591, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 592, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 593, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 594, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 595, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 596, 258, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 597, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 598, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 599, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 600, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 601, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 603, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 604, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 605, 258, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 606, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 607, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 608, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 609, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 610, 258, 'W', 0, 100);

INSERT INTO dqa_database_log VALUES (dqa_change_id_sequence.NEXTVAL, NOW(), '1.08', 'Adding new coded values');

INSERT INTO dqa_code_master(code_master_id, table_id, context_id, code_value, code_label, use_value, code_status, indicates_id) VALUES (630001, 63, NULL, 'PRI', 'Private', 'PRI', 'V', NULL);
INSERT INTO dqa_code_master(code_master_id, table_id, context_id, code_value, code_label, use_value, code_status, indicates_id) VALUES (630002, 63, NULL, 'PUB', 'Public', 'PUB', 'V', NULL);
INSERT INTO dqa_code_master(code_master_id, table_id, context_id, code_value, code_label, use_value, code_status, indicates_id) VALUES (630003, 63, NULL, 'PRIVATE', 'Private', 'PRI', 'D', NULL);
INSERT INTO dqa_code_master(code_master_id, table_id, context_id, code_value, code_label, use_value, code_status, indicates_id) VALUES (630004, 63, NULL, 'PUBLIC', 'Public', 'PUB', 'D', NULL);
INSERT INTO dqa_code_master(code_master_id, table_id, context_id, code_value, code_label, use_value, code_status, indicates_id) VALUES (650001, 65, NULL, 'V', 'Valid', 'V', 'V', NULL);
INSERT INTO dqa_code_master(code_master_id, table_id, context_id, code_value, code_label, use_value, code_status, indicates_id) VALUES (650002, 65, NULL, 'I', 'Invalid', 'I', 'V', NULL);
INSERT INTO dqa_code_master(code_master_id, table_id, context_id, code_value, code_label, use_value, code_status, indicates_id) VALUES (650003, 65, NULL, 'VALID', 'Valid', 'V', 'D', NULL);
INSERT INTO dqa_code_master(code_master_id, table_id, context_id, code_value, code_label, use_value, code_status, indicates_id) VALUES (650004, 65, NULL, 'INVALID', 'Invalid', 'I', 'D', NULL);

INSERT INTO dqa_database_log VALUES (dqa_change_id_sequence.NEXTVAL, NOW(), '1.08', 'Creating dqa_submission table to hold submissions via the database');

CREATE SEQUENCE dqa_submission_id_sequence INCREMENT BY 1 START WITH 1;

CREATE TABLE dqa_submission 
(
  submission_id             INTEGER NOT NULL PRIMARY KEY,
  submitter_name            VARCHAR2(120) NOT NULL,
  request_content           CLOB,
  request_name              VARCHAR2(240),
  profile_id                INTEGER,
  return_response           VARCHAR2(1) DEFAULT 'Y' NOT NULL, 
  return_detail_log         VARCHAR2(1) DEFAULT 'Y' NOT NULL,
  return_detail_error       VARCHAR2(1) DEFAULT 'Y' NOT NULL,
  return_report             VARCHAR2(1) DEFAULT 'Y' NOT NULL,
  return_analysis           VARCHAR2(1) DEFAULT 'Y' NOT NULL,
  response_content          CLOB,
  response_detail_log       CLOB,
  response_detail_error     CLOB,
  response_report           CLOB,
  response_analysis         CLOB,
  batch_id                  INTEGER,
  submission_status         VARCHAR2(1) DEFAULT 'C' NOT NULL,
    -- C  Created 
    -- S  Submitted
    -- P  Processing
    -- F  Finished
    -- D  Delete
    -- E  Error
  submission_status_date    DATE NOT NULL,
  created_date              DATE NOT NULL,
  submitter_defined_value1  VARCHAR2(240),
  submitter_defined_value2  VARCHAR2(240)
);

-- INSERT INTO dqa_submission(submission_id, submitter_name, request_content, request_name, submission_status, submission_status_date, created_date)
-- VALUES (dqa_submission_id_sequence.NEXTVAL, 'ImmTracSubTest', 'MSH|^~\\&|||||20140804133821-0400||VXU^V04^VXU_V04|[MRN].1463|P|2.5.1|\rPID|1||L47I746^^^OIS-TEST^MR||Monroe^Waverly^Leandro^^^^L|Thorne^Suki|20140128|M|||178 Medina Pl^^Felch^MI^49877^USA^P||^PRN^PH^^^906^3507796|\rNK1|1|Thorne^Suki|MTH^Mother^HL70063|\rPV1|1|R|\rORC|RE||L47I746.1^OIS|\rRXA|0|1|20140402||133^PCV 13^CVX|999|||01^Historical^NIP001||||||||||||A|\rORC|RE||L47I746.2^OIS|\rRXA|0|1|20140527||133^PCV 13^CVX|999|||01^Historical^NIP001||||||||||||A|\rORC|RE||L47I746.3^OIS|||||||I-23432^Burden^Donna^A^^^^^NIST-AA-1||57422^RADON^NICHOLAS^^^^^^NIST-AA-1^L|\rRXA|0|1|20140804||49^Hib^CVX|0.5|mL^milliliters^UCUM||00^Administered^NIP001||||||U5086LL||MSD^Merck and Co^MVX||||A|\rOBX|1|CE|64994-7^Vaccine funding program eligibility category^LN|1|V01^Not VFC eligible^HL70064||||||F|||20140804|||VXC40^Eligibility captured at the immunization level^CDCPHINVS|\rOBX|2|CE|30956-7^Vaccine Type^LN|2|17^Hib^CVX||||||F|\rOBX|3|TS|29768-9^Date vaccine information statement published^LN|2|19981216||||||F|\rOBX|4|TS|29769-7^Date vaccine information statement presented^LN|2|20140804||||||F|\r', 'Test1.hl7','C',now(),now());


INSERT INTO dqa_database_log VALUES (dqa_change_id_sequence.NEXTVAL, NOW(), '1.08', 'Creating dqa_submission_analysis table to hold analysis of submissions via the database');

CREATE SEQUENCE dqa_sub_analysis_id_sequence INCREMENT BY 1 START WITH 1;

CREATE TABLE dqa_submission_analysis
(
  submission_analysis_id  INTEGER NOT NULL PRIMARY KEY,
  submission_id           INTEGER NOT NULL,
  analysis_label          VARCHAR2(240) NOT NULL,
  received_id             INTEGER NOT NULL,
  analysis_content        DATE
);

INSERT INTO dqa_database_log VALUES (dqa_change_id_sequence.NEXTVAL, NOW(), '1.08', 'Finished upgrading');




