INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 10, 'MIA04', 'Public vaccine - MI-VRP', 'MIA04', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 10, 'MIA05', 'Medicare (parts A, B and D)', 'MIA05', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 10, 'MIA08', 'Private Stock Vaccine - Other Public Purchase', 'MIA08', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 10, 'MIA10', 'All Hazard eligible - Public Purchase', 'MIA10', 'V');
INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 10, 'MIA14', 'Medicaid-Non VFC', 'MIA14', 'V');

UPDATE dqa_code_received SET code_status = 'V' WHERE table_id = 10 AND received_value = 'MIA04';
UPDATE dqa_code_received SET code_status = 'V' WHERE table_id = 10 AND received_value = 'MIA05';
UPDATE dqa_code_received SET code_status = 'V' WHERE table_id = 10 AND received_value = 'MIA08';
UPDATE dqa_code_received SET code_status = 'V' WHERE table_id = 10 AND received_value = 'MIA10';
UPDATE dqa_code_received SET code_status = 'V' WHERE table_id = 10 AND received_value = 'MIA14';
