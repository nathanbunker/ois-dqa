CREATE TABLE profile_field_value
(
    profile_field_value_id  INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    profile_field_id        INTEGER NOT NULL,
    test_message_id         INTEGER NOT NULL,
    field_value             VARCHAR(2500) NOT NULL
);

ALTER TABLE profile_field ADD COLUMN profile_name VARCHAR(250) NOT NULL DEFAULT 'Z22';