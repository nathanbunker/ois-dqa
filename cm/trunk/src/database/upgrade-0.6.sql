ALTER TABLE test_conducted ADD COLUMN complete_test VARCHAR(1) DEFAULT 'N';

CREATE TABLE profile_field (
    profile_field_id        INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    pos                     INTEGER NOT NULL,
    profile_field_id_parent INTEGER,
    field_name              VARCHAR(250) NOT NULL,
    profile_field_type      VARCHAR(250) NOT NULL,
    segment_name            VARCHAR(250),
    description             VARCHAR(250) NOT NULL,
    code_value              VARCHAR(250),
    code_label              VARCHAR(250),
    transforms_present      TEXT,
    transforms_absent       TEXT,
    pos_in_segment          INTEGER,
    pos_in_field            INTEGER,
    pos_in_sub_field        INTEGER,
    special_name            VARCHAR(250),
    special_section         VARCHAR(250),
    data_type               VARCHAR(250),
    table_name              VARCHAR(250),
    date_type_def           VARCHAR(250),
    data_type_pos           INTEGER,
    test_usage              VARCHAR(250),
    base_usage              VARCHAR(250),
    conditional_predicate   VARCHAR(250),
    length                  VARCHAR(250),
    value_string            VARCHAR(250),
    comments                VARCHAR(250)
);

INSERT INTO profile_field(profile_field_id, pos, field_name, profile_field_type, description) VALUES (0, 0, 'NULL', 'NULL', 'NULL');

CREATE TABLE profile_usage
(
    profile_usage_id       INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    category               VARCHAR(250) NOT NULL,
    label                  VARCHAR(250) NOT NULL,
    version                VARCHAR(250)
);

CREATE TABLE profile_usage_value
(
    profile_usage_value_id         INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    profile_field_id               INTEGER NOT NULL,
    profile_usage_id               INTEGER NOT NULL,
    field_name                     VARCHAR(250),
    test_usage                     VARCHAR(250),
    value_string                   VARCHAR(250),
    comments                       VARCHAR(1024),
    notes                          VARCHAR(1024),
    usage_detected                 VARCHAR(250),
    enforcement                    VARCHAR(250),
    implementation                 VARCHAR(250),
    message_accept_status          VARCHAR(250),
    message_accept_status_detected VARCHAR(250),
    message_accept_status_debug    TEXT
);

CREATE TABLE transform
(
    transform_id          INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    transform_field_id    INTEGER NOT NULL,
    test_conducted_id     INTEGER NOT NULL,
    scenario_name         VARCHAR(250)
);

CREATE TABLE transform_field
(
    transform_field_id    INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    transform_text        VARCHAR(1024) NOT NULL,
    transform_expected    VARCHAR(1) NOT NULL
);


ALTER TABLE test_participant ADD COLUMN profile_usage_id INTEGER;  

DROP TABLE test_profile;

CREATE TABLE test_profile (
  test_profile_id                    INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  test_section_id                    INTEGER NOT NULL,
  test_profile_status                VARCHAR(250) NOT NULL,
  profile_field_id                   INTEGER NOT NULL,
  profile_usage_value_id             INTEGER,
  usage_expected                     VARCHAR(250),
  usage_detected                     VARCHAR(250),
  accept_expected                    VARCHAR(250),
  accept_detected                    VARCHAR(250),
  test_message_present_id            INTEGER,
  test_message_absent_id             INTEGER,
  message_accept_status_debug        VARCHAR(250),
  compatibility_conformance          VARCHAR(250)
);

