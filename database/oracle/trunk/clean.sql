ALTER TABLE dqa_issue_found DROP CONSTRAINT dqa_fk_issue_found4;
ALTER TABLE dqa_code_received DROP CONSTRAINT dqa_fk_code_received1;
ALTER TABLE dqa_code_received DROP CONSTRAINT dqa_fk_code_received2;
ALTER TABLE dqa_code_received DROP CONSTRAINT dqa_fk_code_received3;
ALTER TABLE dqa_batch_code_received DROP CONSTRAINT dqa_fk_batch_code_received2;
ALTER TABLE dqa_receive_queue DROP CONSTRAINT dqa_fk_receive_queue2;
ALTER TABLE dqa_next_of_kin DROP CONSTRAINT dqa_fk_next_of_kin1;
ALTER TABLE dqa_issue_found DROP CONSTRAINT dqa_fk_issue_found1;
ALTER TABLE dqa_vaccination DROP CONSTRAINT dqa_fk_vaccination2;
ALTER TABLE dqa_receive_queue DROP CONSTRAINT dqa_fk_receive_queue1;
ALTER TABLE dqa_batch_issues DROP CONSTRAINT dqa_fk_batch_issues1;
ALTER TABLE dqa_batch_actions DROP CONSTRAINT dqa_fk_batch_actions1;
ALTER TABLE dqa_batch_code_received DROP CONSTRAINT dqa_fk_batch_code_received1;
ALTER TABLE dqa_batch_vaccine_cvx DROP CONSTRAINT dqa_fk_batch_vaccine_cvx1;

truncate table dqa_batch_issues;
truncate table dqa_batch_actions;
truncate table dqa_batch_vaccine_cvx;
truncate table dqa_batch_code_received;
truncate table dqa_receive_queue;
truncate table dqa_message_batch;
truncate table dqa_vaccination;
truncate table dqa_next_of_kin;
truncate table dqa_patient;
truncate table dqa_issue_found;
truncate table dqa_code_received;
truncate table dqa_message_received;

delete from dqa_potential_issue_status where profile_id >= 1200;

ALTER TABLE dqa_issue_found ADD CONSTRAINT dqa_fk_issue_found4 FOREIGN KEY(code_id) REFERENCES dqa_code_received(code_id);
ALTER TABLE dqa_code_received ADD CONSTRAINT dqa_fk_code_received1 FOREIGN KEY(profile_id) REFERENCES dqa_submitter_profile(profile_id);
ALTER TABLE dqa_code_received ADD CONSTRAINT dqa_fk_code_received2 FOREIGN KEY(table_id) REFERENCES dqa_code_table(table_id);
ALTER TABLE dqa_code_received ADD CONSTRAINT dqa_fk_code_received3 FOREIGN KEY(code_status) REFERENCES dqa_code_status(code_status);
ALTER TABLE dqa_batch_code_received ADD CONSTRAINT dqa_fk_batch_code_received2 FOREIGN KEY(coded_id) REFERENCES dqa_code_received(code_id);
ALTER TABLE dqa_receive_queue ADD CONSTRAINT dqa_fk_receive_queue2 FOREIGN KEY(received_id) REFERENCES dqa_message_received(received_id);
ALTER TABLE dqa_next_of_kin ADD CONSTRAINT dqa_fk_next_of_kin1 FOREIGN KEY(received_id) REFERENCES dqa_message_received(received_id);
ALTER TABLE dqa_issue_found ADD CONSTRAINT dqa_fk_issue_found1 FOREIGN KEY(received_id) REFERENCES dqa_message_received(received_id);
ALTER TABLE dqa_vaccination ADD CONSTRAINT dqa_fk_vaccination2 FOREIGN KEY(received_id) REFERENCES dqa_message_received(received_id);
ALTER TABLE dqa_receive_queue ADD CONSTRAINT dqa_fk_receive_queue1 FOREIGN KEY(batch_id) REFERENCES dqa_message_batch(batch_id);
ALTER TABLE dqa_batch_issues ADD CONSTRAINT dqa_fk_batch_issues1 FOREIGN KEY(batch_id) REFERENCES dqa_message_batch(batch_id);
ALTER TABLE dqa_batch_actions ADD CONSTRAINT dqa_fk_batch_actions1 FOREIGN KEY(batch_id) REFERENCES dqa_message_batch(batch_id);
ALTER TABLE dqa_batch_code_received ADD CONSTRAINT dqa_fk_batch_code_received1 FOREIGN KEY(batch_id) REFERENCES dqa_message_batch(batch_id);
ALTER TABLE dqa_batch_vaccine_cvx ADD CONSTRAINT dqa_fk_batch_vaccine_cvx1 FOREIGN KEY(batch_id) REFERENCES dqa_message_batch(batch_id);

