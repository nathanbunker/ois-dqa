CREATE INDEX dqa_in_cm_table_id  ON dqa_code_master(table_id);
CREATE INDEX dqa_in_cr_profile_table ON dqa_code_received(profile_id, table_id);
CREATE UNIQUE INDEX dqa_in_pi ON dqa_potential_issue(target_object, target_field, issue_type, field_value);
CREATE UNIQUE INDEX dqa_in_sp ON dqa_submitter_profile(profile_code);