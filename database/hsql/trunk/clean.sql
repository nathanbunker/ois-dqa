delete from dqa_batch_issues;
delete from dqa_batch_actions;
delete from dqa_batch_vaccine_cvx;
delete from dqa_batch_code_received;
delete from dqa_receive_queue;
delete from dqa_message_batch;
delete from dqa_vaccination;
delete from dqa_next_of_kin;
delete from dqa_patient;
delete from dqa_issue_found;
delete from dqa_code_received;
delete from dqa_message_received;
delete from dqa_potential_issue_status where profile_id >= 1200;

