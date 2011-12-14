ALTER TABLE dqa_submitter_profile DROP CONSTRAINT dqa_fk_submitter_profile1;

delete from  dqa_organization where primary_profile_id >= 1200;

delete from  dqa_submitter_profile where profile_id >= 1200;

ALTER TABLE dqa_submitter_profile ADD CONSTRAINT dqa_fk_submitter_profile1 FOREIGN KEY(org_id) REFERENCES dqa_organization(org_id);
