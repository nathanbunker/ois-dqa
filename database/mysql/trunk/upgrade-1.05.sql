USE dqa;
INSERT INTO dqa_database_log VALUES (NULL, NOW(), '1.05', 'Starting upgrade process');

INSERT INTO dqa_database_log VALUES (NULL, NOW(), '1.05', 'Creating dqa_remote_connection table');
CREATE TABLE dqa_remote_connection
(
  connection_id       INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  connection_code     VARCHAR(128) NOT NULL,
  connection_label    VARCHAR(128) NOT NULL,
  support_center_code VARCHAR(128) NOT NULL,
  location_to         VARCHAR(256),
  location_from       VARCHAR(256),
  account_name        VARCHAR(128),
  log_level           INTEGER NOT NULL
);

INSERT INTO dqa_database_log VALUES (NULL, NOW(), '1.05', 'Creating dqa_remote_stat table');
CREATE TABLE dqa_remote_stat
(
  stat_id         INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  connection_id   INTEGER NOT NULL,
  reported_date   DATETIME NOT NULL,
  up_since_date   DATETIME NOT NULL,
  status_label    VARCHAR(128) NOT NULL,
  attempt_count   INTEGER NOT NULL,
  sent_count      INTEGER NOT NULL,
  error_count     INTEGER NOT NULL
);

INSERT INTO dqa_database_log VALUES (NULL, NOW(), '1.05', 'Creating dqa_remote_log table');
CREATE TABLE dqa_remote_log
(
  log_id          INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  stat_id         INTEGER NOT NULL,
  reported_date   DATETIME NOT NULL,
  log_level       INTEGER NOT NULL,
  issue_text      VARCHAR(1000) NOT NULL,
  exception_trace VARCHAR(4000)
);

INSERT INTO dqa_database_log VALUES (NULL, NOW(), '1.05', 'Creating dqa_remote_file table');
CREATE TABLE dqa_remote_file
(
  file_id         INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  stat_id         INTEGER NOT NULL,
  reported_date   DATETIME NOT NULL,
  file_name       VARCHAR(256) NOT NULL,
  status_label    VARCHAR(128) NOT NULL,
  message_count   INTEGER NOT NULL,
  sent_count      INTEGER NOT NULL,
  error_count     INTEGER NOT NULL
);


INSERT INTO dqa_database_log VALUES (NULL, NOW(), '1.05', 'Finished upgrading');
COMMIT;