delete from dqa_batch_issues;
delete from dqa_batch_actions;
delete from dqa_batch_vaccine_cvx;
delete from dqa_batch_code_received;
delete from dqa_receive_queue;
delete from dqa_message_batch;
delete from dqa_vaccination;
delete from dqa_next_of_kin;
delete from dqa_patient;
delete from dqa_code_received;
delete from dqa_issue_found;
delete from dqa_message_received;
delete from dqa_potential_issue_status;
commit;



select profile_id, message_count from dqa_message_batch;

CREATE INDEX dqa_in_cm_table_id  ON dqa_code_master(table_id);
CREATE INDEX dqa_in_cr_profile_table ON dqa_code_received(profile_id, table_id);
CREATE UNIQUE INDEX dqa_in_pi ON dqa_potential_issue(target_object, target_field, issue_type, field_value);
CREATE UNIQUE INDEX dqa_in_sp ON dqa_submitter_profile(profile_code);

-- CREATE UNIQUE INDEX dqa_in_vc ON dqa_vaccine_cpt(cpt_code, valid_start_date);-- 
