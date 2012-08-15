describe dqa_application;
describe dqa_batch_actions;
describe dqa_batch_code_received;
describe dqa_batch_issues;
describe dqa_batch_report;
describe dqa_batch_type;
describe dqa_batch_vaccine_cvx;
describe dqa_code_master;
describe dqa_code_received;
describe dqa_code_status;
describe dqa_code_table;
describe dqa_issue_action;
describe dqa_issue_found;
describe dqa_keyed_setting;
describe dqa_message_batch;
describe dqa_message_header;
describe dqa_message_received;
describe dqa_next_of_kin;
describe dqa_organization;
describe dqa_patient;
describe dqa_potential_issue;
describe dqa_potential_issue_status;
describe dqa_receive_queue;
describe dqa_report_template;
describe dqa_report_type;
describe dqa_report_vaccine_group;
describe dqa_submit_status;
describe dqa_submitter_profile;
describe dqa_user_account;
describe dqa_vaccination;
describe dqa_vaccine_cpt;
describe dqa_vaccine_cvx;
describe dqa_vaccine_cvx_group;
describe dqa_vaccine_group;
describe dqa_vaccine_mvx;
describe dqa_vaccine_product;



select * from dqa_application order by application_label;
select * from dqa_batch_type order by type_label;
select * from dqa_code_master order by table_id, code_value;
select * from dqa_code_status order by code_label;
select * from dqa_code_table order by table_label;
select * from dqa_issue_action order by action_label;
select * from dqa_potential_issue order by target_object, target_field, issue_type, field_value;
select * from dqa_report_template order by template_label;
select * from dqa_report_type order by report_type_label;
select * from dqa_submit_status order by submit_label;
select * from dqa_submitter_profile where profile_id < 1200 order by profile_id;
select * from dqa_vaccine_cpt order by cpt_id;
select * from dqa_vaccine_cvx order by cvx_id;
select * from dqa_vaccine_cvx_group order by cvx_group_id;
select * from dqa_vaccine_group order by group_label;
select * from dqa_vaccine_mvx order by mvx_code;
select * from dqa_vaccine_product order by product_name;

 