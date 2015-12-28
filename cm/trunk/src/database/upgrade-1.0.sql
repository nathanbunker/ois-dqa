delete from pentagon_report;

ALTER TABLE profile_usage_value ADD COLUMN link_definition VARCHAR(250);
ALTER TABLE profile_usage_value ADD COLUMN link_detail VARCHAR(250);
ALTER TABLE profile_usage_value ADD COLUMN link_clarification VARCHAR(250);
ALTER TABLE profile_usage_value ADD COLUMN link_supplement VARCHAR(250);

ALTER TABLE test_message ADD COLUMN result_query_type VARCHAR(250);
ALTER TABLE test_message ADD COLUMN result_forecast_status VARCHAR(250);

CREATE TABLE evaluation_field (
    evaluation_field_id     INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    component_code          VARCHAR(250) NOT NULL,
    vaccine_code            VARCHAR(250) NOT NULL,
    vaccine_date            VARCHAR(250) NOT NULL
);

CREATE TABLE evaluation (
    evaluation_id           INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    evaluation_field_id     INTEGER NOT NULL,
    test_message_id         INTEGER NOT NULL,
    evaluation_type         VARCHAR(250) NOT NULL,
    schedule_name           VARCHAR(250),
    dose_number             VARCHAR(250),
    dose_validity           VARCHAR(250),
    series_name             VARCHAR(250),
    series_dose_count       VARCHAR(250),
    series_status           VARCHAR(250),
    reason_code             VARCHAR(250)
);

CREATE TABLE forecast_field (
    forecast_field_id       INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    vaccine_code            VARCHAR(250) NOT NULL
);

CREATE TABLE forecast (
    forecast_id             INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    forecast_field_id       INTEGER NOT NULL,
    test_message_id         INTEGER NOT NULL,
    forecast_type           VARCHAR(250) NOT NULL,
    schedule_name           VARCHAR(250),
    series_name             VARCHAR(250),
    series_dose_count       VARCHAR(250),
    dose_number             VARCHAR(250),
    date_earliest           DATE,
    date_due                DATE,
    date_overdue            DATE,
    date_latest             DATE,
    series_status           VARCHAR(250),
    reason_code             VARCHAR(250)
);