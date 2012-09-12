ALTER TABLE dqa_message_received ADD 
(
    message_key  VARCHAR2(100)
);

INSERT INTO dqa_submitter_profile (profile_id, profile_code, profile_label, profile_status, org_id, data_format, transfer_priority, access_key, template_id) VALUES (257, 'TEMPLATE:IHS Certification', 'IHS Certification', 'Template', 1, 'HL7v2', 'Normal', 'template', 7);
INSERT INTO dqa_submitter_profile (profile_id, profile_code, profile_label, profile_status, org_id, data_format, transfer_priority, access_key, template_id) VALUES (258, 'TEMPLATE:ASIIS Profile', 'ASIIS Profile', 'Template', 1, 'HL7v2', 'Normal', 'template', 8);

INSERT INTO dqa_potential_issue(issue_id, target_object, target_field, issue_type, field_value, default_action_code, change_priority, report_denominator, issue_description, table_id, hl7_reference) VALUES (485, 'Next-of-kin', 'relationship', 'is unexpected', '', 'W', 'May', 'NextOfKin Count', 'Next-of-kin relationship is not expected considering the age of the patient. For example, a 5 year old is not expected to have a child.', 22, 'NK1-3');

INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 485, 251, 'W', 0, 100);	
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 485, 252, 'W', 0, 100);	
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 485, 253, 'A', 0, 100);	
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 485, 254, 'A', 0, 100);	
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 485, 255, 'W', 0, 100);	
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 485, 256, 'W', 0, 100);	
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 485, 257, 'W', 0, 100);	
INSERT INTO dqa_potential_issue_status (potential_issue_status_id, issue_id, profile_id, action_code, expect_min, expect_max) VALUES(dqa_potential_status_id_seq.NEXTVAL, 485, 258, 'A', 0, 100);

INSERT INTO dqa_code_master(code_master_id, table_id, code_value, code_label, use_value, code_status) VALUES  (dqa_code_master_id_sequence.NEXTVAL, 22, 'CHD', 'Child', 'CHD', 'V');

INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (148,'148', 'Meningococcal C/Y-HIB PRP ', TO_DATE('2012-01-01', 'YYYY-MM-DD'), TO_DATE('2012-06-14', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');
INSERT INTO dqa_vaccine_cvx (cvx_id, cvx_code, cvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date, use_month_start, use_month_end, concept_type) VALUES (149,'149', 'influenza, live, intranasal, quadrivalent', TO_DATE('2012-01-01', 'YYYY-MM-DD'), TO_DATE('2012-02-29', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 1, 1440, 'vaccine');

INSERT INTO dqa_vaccine_cpt (cpt_id, cpt_code, cpt_label, valid_start_date, use_start_date, use_end_date, valid_end_date, cvx_id) VALUES (100, '90644', 'Meningococcal C/Y-HIB PRP', TO_DATE('2012-01-01', 'YYYY-MM-DD'), TO_DATE('2012-06-14', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), 148);

INSERT INTO dqa_vaccine_mvx (mvx_code, mvx_label, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES ('JNJ', 'Johnson and Johnson', TO_DATE('2012-07-11', 'YYYY-MM-DD'), TO_DATE('2012-07-11', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));

INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (83, 'Tenivac', 'Tenivac', 113, 'PMC', TO_DATE('1984-01-01', 'YYYY-MM-DD'), TO_DATE('1984-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (84, 'MENHIBRIX', 'Meningococcal C/Y-HIB PRP', 148, 'SKB', TO_DATE('2012-01-01', 'YYYY-MM-DD'), TO_DATE('2012-06-14', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (85, 'Flumist quadrivalent ', 'influenza, live, intranasal, quadrivalent', 149, 'MED', TO_DATE('2012-01-01', 'YYYY-MM-DD'), TO_DATE('2012-02-29', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
INSERT INTO dqa_vaccine_product(product_id, product_name, product_label, cvx_id, mvx_code, valid_start_date, use_start_date, use_end_date, valid_end_date) VALUES (86, 'Adenovirus types 4 and 7 ', 'Adenovirus types 4 and 7', 143, 'BRR', TO_DATE('2011-01-01', 'YYYY-MM-DD'), TO_DATE('2011-03-16', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'), TO_DATE('2100-01-01', 'YYYY-MM-DD'));
