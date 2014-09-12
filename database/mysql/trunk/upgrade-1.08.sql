USE dqa;
INSERT INTO dqa_database_log VALUES (NULL, NOW(), '1.08', 'Starting upgrade process');

INSERT INTO dqa_database_log VALUES (NULL, NOW(), '1.08', 'Adding fields to patient table for AIRA ASC report');
ALTER TABLE dqa_patient ADD COLUMN (system_creation_date DATE);

INSERT INTO dqa_database_log VALUES (NULL, NOW(), '1.08', 'Adding fields to vaccination table for AIRA ASC report');
ALTER TABLE dqa_vaccination ADD COLUMN (facility_type_code VARCHAR(250));
ALTER TABLE dqa_vaccination ADD COLUMN (trade_name_code VARCHAR(250));
ALTER TABLE dqa_vaccination ADD COLUMN (vaccine_validity_code VARCHAR(250));

INSERT INTO dqa_database_log VALUES (NULL, NOW(), '1.08', 'Adding new tables to support new values for AIRA ASC report');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES(61, 'Potential Issue', '');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES(62, 'Vaccine Group', '');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES(63, 'Facility Type', '');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES(64, 'Vaccination Trade Name', '');
INSERT INTO dqa_code_table (table_id, table_label, default_code_value) VALUES(65, 'Vaccination Validity', '');

INSERT INTO dqa_database_log VALUES (NULL, NOW(), '1.08', 'Adding new potential issues');
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

INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(584, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(585, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(586, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(587, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(588, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(589, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(590, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(591, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(592, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(593, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(594, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(595, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(596, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(597, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(598, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(599, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(600, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(601, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(603, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(604, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(605, 251, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(606, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(607, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(608, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(609, 251, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(610, 251, 'W', 0, 100);

INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(584, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(585, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(586, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(587, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(588, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(589, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(590, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(591, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(592, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(593, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(594, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(595, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(596, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(597, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(598, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(599, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(600, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(601, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(603, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(604, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(605, 252, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(606, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(607, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(608, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(609, 252, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(610, 252, 'W', 0, 100);

INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(584, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(585, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(586, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(587, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(588, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(589, 253, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(590, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(591, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(592, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(593, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(594, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(595, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(596, 253, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(597, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(598, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(599, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(600, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(601, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(603, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(604, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(605, 253, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(606, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(607, 253, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(608, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(609, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(610, 253, 'W', 0, 100);

INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(584, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(585, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(586, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(587, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(588, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(589, 254, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(590, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(591, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(592, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(593, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(594, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(595, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(596, 254, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(597, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(598, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(599, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(600, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(601, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(603, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(604, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(605, 254, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(606, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(607, 254, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(608, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(609, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(610, 254, 'W', 0, 100);

INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(584, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(585, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(586, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(587, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(588, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(589, 255, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(590, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(591, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(592, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(593, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(594, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(595, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(596, 255, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(597, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(598, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(599, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(600, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(601, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(603, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(604, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(605, 255, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(606, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(607, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(608, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(609, 255, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(610, 255, 'W', 0, 100);

INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(584, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(585, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(586, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(587, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(588, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(589, 256, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(590, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(591, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(592, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(593, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(594, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(595, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(596, 256, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(597, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(598, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(599, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(600, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(601, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(603, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(604, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(605, 256, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(606, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(607, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(608, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(609, 256, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(610, 256, 'W', 0, 100);

INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(584, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(585, 257, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(586, 257, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(587, 257, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(588, 257, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(589, 257, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(590, 257, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(591, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(592, 257, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(593, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(594, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(595, 257, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(596, 257, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(597, 257, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(598, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(599, 257, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(600, 257, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(601, 257, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(603, 257, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(604, 257, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(605, 257, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(606, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(607, 257, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(608, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(609, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(610, 257, 'W', 0, 100);

INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(584, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(585, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(586, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(587, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(588, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(589, 258, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(590, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(591, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(592, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(593, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(594, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(595, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(596, 258, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(597, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(598, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(599, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(600, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(601, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(603, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(604, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(605, 258, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(606, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(607, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(608, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(609, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(610, 258, 'W', 0, 100);

INSERT INTO dqa_database_log VALUES (NULL, NOW(), '1.08', 'Adding new coded values');

INSERT INTO dqa_code_master(code_master_id, table_id, context_id, code_value, code_label, use_value, code_status, indicates_id) VALUES (630001, 63, NULL, 'PRI', 'Private', 'PRI', 'V', NULL);
INSERT INTO dqa_code_master(code_master_id, table_id, context_id, code_value, code_label, use_value, code_status, indicates_id) VALUES (630002, 63, NULL, 'PUB', 'Public', 'PUB', 'V', NULL);
INSERT INTO dqa_code_master(code_master_id, table_id, context_id, code_value, code_label, use_value, code_status, indicates_id) VALUES (630003, 63, NULL, 'PRIVATE', 'Private', 'PRI', 'D', NULL);
INSERT INTO dqa_code_master(code_master_id, table_id, context_id, code_value, code_label, use_value, code_status, indicates_id) VALUES (630004, 63, NULL, 'PUBLIC', 'Public', 'PUB', 'D', NULL);
INSERT INTO dqa_code_master(code_master_id, table_id, context_id, code_value, code_label, use_value, code_status, indicates_id) VALUES (650001, 65, NULL, 'V', 'Valid', 'V', 'V', NULL);
INSERT INTO dqa_code_master(code_master_id, table_id, context_id, code_value, code_label, use_value, code_status, indicates_id) VALUES (650002, 65, NULL, 'I', 'Invalid', 'I', 'V', NULL);
INSERT INTO dqa_code_master(code_master_id, table_id, context_id, code_value, code_label, use_value, code_status, indicates_id) VALUES (650003, 65, NULL, 'VALID', 'Valid', 'V', 'D', NULL);
INSERT INTO dqa_code_master(code_master_id, table_id, context_id, code_value, code_label, use_value, code_status, indicates_id) VALUES (650004, 65, NULL, 'INVALID', 'Invalid', 'I', 'D', NULL);

INSERT INTO dqa_database_log VALUES (NULL, NOW(), '1.08', 'Creating dqa_submission table to hold submissions via the database');

CREATE TABLE dqa_submission 
(
  submission_id             INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  submitter_name            VARCHAR(120) NOT NULL,
  request_content           LONGTEXT,
  request_name              VARCHAR(240),
  profile_id                INTEGER,
  return_response           VARCHAR(1) DEFAULT 'Y' NOT NULL, 
  return_detail_log         VARCHAR(1) DEFAULT 'Y' NOT NULL,
  return_detail_error       VARCHAR(1) DEFAULT 'Y' NOT NULL,
  return_report             VARCHAR(1) DEFAULT 'Y' NOT NULL,
  return_analysis           VARCHAR(1) DEFAULT 'Y' NOT NULL,
  response_content          LONGTEXT,
  response_detail_log       LONGTEXT,
  response_detail_error     LONGTEXT,
  response_report           LONGTEXT,
  response_analysis         LONGTEXT,
  batch_id                  INTEGER,
  submission_status         VARCHAR(1) DEFAULT 'C' NOT NULL,
    -- C  Created 
    -- S  Submitted
    -- P  Processing
    -- F  Finished
    -- D  Delete
    -- E  Error
  submission_status_date    DATETIME NOT NULL,
  created_date              DATETIME NOT NULL,
  submitter_defined_value1  VARCHAR(240),
  submitter_defined_value2  VARCHAR(240)
);

-- INSERT INTO dqa_submission(submitter_name, request_content, request_name, submission_status, submission_status_date, created_date)
-- VALUES ('ImmTracSubTest', 'MSH|^~\\&|||||20140804133821-0400||VXU^V04^VXU_V04|[MRN].1463|P|2.5.1|\rPID|1||L47I746^^^OIS-TEST^MR||Monroe^Waverly^Leandro^^^^L|Thorne^Suki|20140128|M|||178 Medina Pl^^Felch^MI^49877^USA^P||^PRN^PH^^^906^3507796|\rNK1|1|Thorne^Suki|MTH^Mother^HL70063|\rPV1|1|R|\rORC|RE||L47I746.1^OIS|\rRXA|0|1|20140402||133^PCV 13^CVX|999|||01^Historical^NIP001||||||||||||A|\rORC|RE||L47I746.2^OIS|\rRXA|0|1|20140527||133^PCV 13^CVX|999|||01^Historical^NIP001||||||||||||A|\rORC|RE||L47I746.3^OIS|||||||I-23432^Burden^Donna^A^^^^^NIST-AA-1||57422^RADON^NICHOLAS^^^^^^NIST-AA-1^L|\rRXA|0|1|20140804||49^Hib^CVX|0.5|mL^milliliters^UCUM||00^Administered^NIP001||||||U5086LL||MSD^Merck and Co^MVX||||A|\rOBX|1|CE|64994-7^Vaccine funding program eligibility category^LN|1|V01^Not VFC eligible^HL70064||||||F|||20140804|||VXC40^Eligibility captured at the immunization level^CDCPHINVS|\rOBX|2|CE|30956-7^Vaccine Type^LN|2|17^Hib^CVX||||||F|\rOBX|3|TS|29768-9^Date vaccine information statement published^LN|2|19981216||||||F|\rOBX|4|TS|29769-7^Date vaccine information statement presented^LN|2|20140804||||||F|\r', 'Test1.hl7','C',now(),now());


INSERT INTO dqa_database_log VALUES (NULL, NOW(), '1.08', 'Creating dqa_submission_analysis table to hold analysis of submissions via the database');

CREATE TABLE dqa_submission_analysis
(
  submission_analysis_id  INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  submission_id           INTEGER NOT NULL,
  analysis_label          VARCHAR(240) NOT NULL,
  received_id             INTEGER NOT NULL,
  analysis_content        LONGTEXT
);

INSERT INTO dqa_database_log VALUES (NULL, NOW(), '1.08', 'Adding ASC report template');

INSERT INTO dqa_report_type(report_type_id, report_type_label) VALUES (5, 'IIS Data Quality');


INSERT INTO dqa_report_template(template_id, template_label, report_type_id, report_definition, base_profile_id) VALUES(9, 'ASC IIS DQA', 5, '<dqa-scoring>
  <section name="completeness" weight="50">
    <section name="patient" weight="20">
      <section name="required" weight="10">
        <score label="Patient Id" denominator="patient count" numerator="Patient submitter id" weight="10" />
        <score label="Birth Date" denominator="patient count" numerator="Patient birth date" weight="10" />
        <score label="Sex" denominator="patient count" numerator="Patient gender" weight="10" />
      </section>
      <section name="expected" weight="6">
        <score label="Ethnicity" denominator="patient count" numerator="Patient ethnicity" weight="10" />
        <score label="System Creation Date" denominator="patient count" numerator="Patient system creation date" weight="10" />
      </section>
      <section name="recommended" weight="1">
        <score label="Death Date" denominator="patient count" numerator="Patient death date" weight="10" />
      </section>
      <section name="optional" weight="0">
        <score label="First Name" denominator="patient count" numerator="Patient name first" weight="5" />
        <score label="Last Name" denominator="patient count" numerator="Patient name last" weight="5" />
        <score label="Possible Test Name" denominator="patient count" numerator="Patient name may be test name" weight="-5" />
        <score label="Possible Baby Name" denominator="patient count" numerator="Patient name may be temporary newborn name" weight="-10" />
        <score label="Address" denominator="patient count" numerator="Patient address" weight="2">
          <score label="Street" denominator="patient count" numerator="Patient address street" weight="5" />
          <score label="City" denominator="patient count" numerator="Patient address city" weight="1" />
          <score label="State" denominator="patient count" numerator="Patient address state" weight="1" />
          <score label="Zip" denominator="patient count" numerator="Patient address zip" weight="1" />
        </score>
        <score label="Middle Name" denominator="patient count" numerator="Patient middle name" weight="10" />
        <score label="Phone" denominator="patient count" numerator="Patient phone" weight="10" />
        <score label="Mother''s Maiden" denominator="patient count" numerator="Patient mother''s maiden name" weight="10" />
        <score label="Responsible Party" denominator="patient count" numerator="Patient guardian responsible party" weight="1">
          <score label="First Name" denominator="patient count" numerator="Patient guardian name first" weight="4" />
          <score label="Last Name" denominator="patient count" numerator="Patient guardian name last" weight="4" />
          <score label="Same as Patient" denominator="patient count" numerator="Patient guardian name is same as underage patient" weight="-8" />
          <score label="Relationship" denominator="patient count" numerator="Patient guardian relationship" weight="1" />
        </score>
        <score label="Race" denominator="patient count" numerator="Patient race" weight="10" />
        <score label="Birth Indicator" denominator="patient count" numerator="Patient birth indicator" weight="10" />
        <score label="Medicaid Id" denominator="patient count" numerator="Patient Medicaid number" weight="5" />
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
        <score label="Manufacturer" denominator="vaccination admin count" numerator="Vaccination manufacturer code" weight="20" />
        <score label="Vaccine Validity" denominator="vaccination count" numerator="Vaccination validity" weight="20" />
        <score label="Facility Id" denominator="vaccination admin count" numerator="Vaccination facility id" weight="20" />
        <score label="Body Route" denominator="vaccination admin count" numerator="Vaccination body route" weight="20" />
        <score label="Body Site" denominator="vaccination admin count" numerator="Vaccination body site" weight="20" />
        <score label="System Entry Date" denominator="vaccination count" numerator="Vaccination system entry time" weight="5">
          <score label="In Future" denominator="vaccination count" numerator="Vaccination system entry time is in future" weight="-5" />
        </score>
      </section>
      <section name="recommended" weight="4">
        <score label="Trade Name" denominator="vaccination admin count" numerator="Vaccination trade name" weight="20" />
        <score label="Facility Type" denominator="vaccination admin count" numerator="Vaccination facility type" weight="20" />
      </section>
      <section name="optional" weight="0">
        <score label="Lot Number" denominator="vaccination admin count" numerator="Vaccination lot number" weight="20" />
        <score label="Admin Amount" denominator="vaccination admin count" numerator="Vaccination administered amount" weight="10">
          <score label="Missing Units" denominator="vaccination admin count" numerator="Vaccination administered unit is missing" weight="-2" />
        </score>
        <score label="Ordered By" denominator="vaccination admin count" numerator="Vaccination ordered by" weight="0" />
        <score label="Entered By" denominator="vaccination count" numerator="Vaccination recorded by" weight="0" />
        <score label="Refusal Reason" denominator="vaccination count" numerator="Vaccination refusal reason" weight="0" />
        <score label="Lot Expiration Date" denominator="vaccination admin count" numerator="Vaccination lot expiration date" weight="0" />
        <score label="CVX Code" denominator="vaccination count" numerator="Vaccination CVX code" weight="20" />
        <score label="CPT Code" denominator="vaccination count" numerator="Vaccination CPT code" weight="0" />
        <score label="Action Code" denominator="vaccination count" numerator="Vaccination action code" weight="10" />
        <score label="Given By Id" denominator="vaccination admin count" numerator="Vaccination given by" weight="10" />
        <score label="Vaccination Id" denominator="vaccination count" numerator="Vaccination id" weight="10" />
        <score label="Completion Status" denominator="vaccination admin count" numerator="Vaccination completion status" weight="5" />
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
</dqa-scoring>
', 259);

INSERT INTO dqa_submitter_profile (profile_id, profile_code, profile_label, profile_status, org_id, data_format, transfer_priority, access_key, template_id) VALUES (259, 'TEMPLATE:ASC IIS DQA', 'ASC IIS DQA', 'Template', 1, 'HL7v2', 'Normal', 'template', 9);

INSERT INTO dqa_application (application_id, application_label, application_type, run_this) VALUES (15, 'ASC', 'Test', 'N');
UPDATE dqa_application SET primary_template_id = 9 WHERE application_id = 15;


INSERT INTO dqa_database_log VALUES (NULL, NOW(), '1.08', 'Add new counts to the report');
ALTER TABLE dqa_batch_report ADD COLUMN (vacc_refusal_count INT(11) NOT NULL DEFAULT 0);

INSERT INTO dqa_database_log VALUES (NULL, NOW(), '1.08', 'Update the labels for a select set of potential issues');

UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Observation', target_field='identifier code', issue_type='is deprecated', field_value='', report_denominator='Vaccination Count', table_id = 43, hl7_reference = 'OBX-3', hl7_error_code = '102', app_error_code = NULL WHERE issue_id='475';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', target_object='Observation', target_field='identifier code', issue_type='is ignored', field_value='', report_denominator='Vaccination Count', table_id = 43, hl7_reference = 'OBX-3', hl7_error_code = '102', app_error_code = NULL WHERE issue_id='476';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Observation', target_field='identifier code', issue_type='is invalid', field_value='', report_denominator='Vaccination Count', table_id = 43, hl7_reference = 'OBX-3', hl7_error_code = '102', app_error_code = NULL WHERE issue_id='477';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Observation', target_field='identifier code', issue_type='is missing', field_value='', report_denominator='Vaccination Count', table_id = 43, hl7_reference = 'OBX-3', hl7_error_code = '101', app_error_code = NULL WHERE issue_id='478';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Observation', target_field='identifier code', issue_type='is unrecognized', field_value='', report_denominator='Vaccination Count', table_id = 43, hl7_reference = 'OBX-3', hl7_error_code = '103', app_error_code = NULL WHERE issue_id='479';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Observation', target_field='value', issue_type='is missing', field_value='', report_denominator='Vaccination Count', table_id = NULL, hl7_reference = 'OBX-5', hl7_error_code = '101', app_error_code = NULL WHERE issue_id='480';


INSERT INTO dqa_report_template(template_id, template_label, report_type_id, base_profile_id) VALUES(0, 'No Template', 1, 1);

INSERT INTO dqa_database_log VALUES (NULL, NOW(), '1.08', 'Dropping group_status from dqa_database_log, old data not used anymore');

ALTER TABLE dqa_vaccine_group DROP group_status;

INSERT INTO dqa_database_log VALUES (NULL, NOW(), '1.08', 'Increasing password field to accommodate hashed password');

ALTER TABLE dqa_user_account MODIFY password  VARCHAR(250);
-- not run yet

INSERT INTO dqa_database_log VALUES (NULL, NOW(), '1.08', 'Finished upgrading');




