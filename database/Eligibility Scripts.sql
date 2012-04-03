-- Update the status on the master list

UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', target_object='Vaccination', target_field='financial eligibility code', issue_type='is invalid', field_value='', report_denominator='Vaccination Admin Count', table_id = 10 WHERE issue_id='467';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', target_object='Vaccination', target_field='financial eligibility code', issue_type='is missing', field_value='', report_denominator='Vaccination Admin Count', table_id = 10 WHERE issue_id='468';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', target_object='Vaccination', target_field='financial eligibility code', issue_type='is unrecognized', field_value='', report_denominator='Vaccination Admin Count', table_id = 10 WHERE issue_id='469';

-- Update the status on all templates and current profiles
UPDATE dqa_potential_issue_status SET action_code = 'E' WHERE issue_id = 467;
UPDATE dqa_potential_issue_status SET action_code = 'E' WHERE issue_id = 468;
UPDATE dqa_potential_issue_status SET action_code = 'E' WHERE issue_id = 469;


-- Update the master table with the latest codes
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 10, 'V00', 'VFC eligibility not determined/unknown', 'V00', 'I');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 10, 'MIA04', 'MCIR: Public vaccine – MI-VRP', 'MIA04', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 10, 'MIA05', 'MCIR: Medicare (parts A, B, and D)', 'MIA05', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 10, 'MIA08', 'MCIR: Other Public Purchase', 'MIA08', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 10, 'MIA10', 'MCIR: All Hazard - Public Purpose', 'MIA10', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 10, 'MIA14', 'MCIR: Medicaid Non-VFC', 'MIA14', 'V');

-- Update all of profile tables with the latest code settings
UPDATE dqa_code_received SET code_status = 'I' WHERE table_id = 10 AND received_value = 'V00';
UPDATE dqa_code_received SET code_status = 'V' WHERE table_id = 10 AND received_value = 'MIA04';
UPDATE dqa_code_received SET code_status = 'V' WHERE table_id = 10 AND received_value = 'MIA05';
UPDATE dqa_code_received SET code_status = 'V' WHERE table_id = 10 AND received_value = 'MIA08';
UPDATE dqa_code_received SET code_status = 'V' WHERE table_id = 10 AND received_value = 'MIA10';
UPDATE dqa_code_received SET code_status = 'V' WHERE table_id = 10 AND received_value = 'MIA14';
