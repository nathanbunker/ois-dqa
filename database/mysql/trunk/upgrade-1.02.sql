
ALTER TABLE dqa_message_received ADD COLUMN
(
    message_key  VARCHAR(100)
);

INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id, hl7_reference) VALUES (485, 'Next-of-kin', 'relationship', 'is unexpected', '', 'W', 'May', 'NextOfKin Count', 'Next-of-kin relationship is not expected considering the age of the patient. For example, a 5 year old is not expected to have a child.', 22, 'NK1-3');


INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(485, 251, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(485, 252, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(485, 253, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(485, 254, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(485, 255, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(485, 256, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(485, 257, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(485, 258, 'A', 0, 100);

INSERT INTO dqa_code_master(table_id, code_value, code_label, use_value, code_status) VALUES  (22, 'CHD', 'Child', 'CHD', 'V');

INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (148, '148', 'Meningococcal C/Y-HIB PRP ', '2012-01-01', '2012-06-14', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (149, '149', 'influenza, live, intranasal, quadrivalent', '2012-01-01', '2012-02-29', '2100-01-01', '2100-01-01', 1, 1440, 'vaccine');

INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (100, '90644', 'Meningococcal C/Y-HIB PRP', '2012-01-01', '2012-06-14', '2100-01-01', '2100-01-01', '148');

INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('JNJ', 'Johnson and Johnson', '2012-07-11', '2012-07-11', '2100-01-01', '2100-01-01');

INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (83, 'Tenivac', 'Tenivac', '113', 'PMC', '1984-01-01', '1984-01-01', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (84, 'MENHIBRIX', 'Meningococcal C/Y-HIB PRP', '148', 'SKB', '2012-01-01', '2012-06-14', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (85, 'Flumist quadrivalent ', 'influenza, live, intranasal, quadrivalent', '149', 'MED', '2012-01-01', '2012-02-29', '2100-01-01', '2100-01-01');
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (86, 'Adenovirus types 4 and 7 ', 'Adenovirus types 4 and 7', '143', 'BRR', '2011-01-01', '2011-03-16', '2100-01-01', '2100-01-01');
