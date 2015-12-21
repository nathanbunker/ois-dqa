delete from pentagon_report;

ALTER TABLE profile_usage_value ADD COLUMN link_definition VARCHAR(250);
ALTER TABLE profile_usage_value ADD COLUMN link_detail VARCHAR(250);
ALTER TABLE profile_usage_value ADD COLUMN link_clarification VARCHAR(250);
ALTER TABLE profile_usage_value ADD COLUMN link_supplement VARCHAR(250);

