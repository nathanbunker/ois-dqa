INSERT INTO dqa_database_log VALUES (dqa_change_id_sequence.NEXTVAL, SYSDATE, '1.05', 'Starting upgrade process');

INSERT INTO dqa_database_log VALUES (dqa_change_id_sequence.NEXTVAL, SYSDATE, '1.05', 'Creating dqa_remote_connection table');
CREATE SEQUENCE dqa_remote_conn_id_sequence INCREMENT BY 1 START WITH 1;
CREATE TABLE dqa_remote_connection
(
  connection_id       INTEGER NOT NULL PRIMARY KEY,
  connection_code     VARCHAR2(128) NOT NULL,
  connection_label    VARCHAR2(128) NOT NULL,
  support_center_code VARCHAR2(128) NOT NULL,
  location_to         VARCHAR2(256),
  location_from       VARCHAR2(256),
  account_name        VARCHAR2(128),
  log_level           INTEGER NOT NULL
);

INSERT INTO dqa_database_log VALUES (dqa_change_id_sequence.NEXTVAL, SYSDATE, '1.05', 'Creating dqa_remote_stat table');
CREATE SEQUENCE dqa_remote_stat_id_sequence INCREMENT BY 1 START WITH 1;
CREATE TABLE dqa_remote_stat
(
  stat_id         INTEGER NOT NULL PRIMARY KEY,
  connection_id   INTEGER NOT NULL,
  reported_date   DATE NOT NULL,
  up_since_date   DATE NOT NULL,
  status_label    VARCHAR2(128) NOT NULL,
  attempt_count   INTEGER NOT NULL,
  sent_count      INTEGER NOT NULL,
  error_count     INTEGER NOT NULL
);

INSERT INTO dqa_database_log VALUES (dqa_change_id_sequence.NEXTVAL, SYSDATE, '1.05', 'Creating dqa_remote_log table');
CREATE SEQUENCE dqa_remote_log_id_sequence INCREMENT BY 1 START WITH 1;
CREATE TABLE dqa_remote_log
(
  log_id          INTEGER NOT NULL PRIMARY KEY,
  stat_id         INTEGER NOT NULL,
  reported_date   DATE NOT NULL,
  log_level       INTEGER NOT NULL,
  issue_text      VARCHAR2(1000) NOT NULL,
  exception_trace VARCHAR2(4000)
);

INSERT INTO dqa_database_log VALUES (dqa_change_id_sequence.NEXTVAL, SYSDATE, '1.05', 'Creating dqa_remote_file table');
CREATE SEQUENCE dqa_remote_file_id_sequence INCREMENT BY 1 START WITH 1;
CREATE TABLE dqa_remote_file
(
  file_id         INTEGER NOT NULL PRIMARY KEY,
  stat_id         INTEGER NOT NULL,
  reported_date   DATE NOT NULL,
  file_name       VARCHAR2(256) NOT NULL,
  status_label    VARCHAR2(128) NOT NULL,
  message_count   INTEGER NOT NULL,
  sent_count      INTEGER NOT NULL,
  error_count     INTEGER NOT NULL
);


INSERT INTO dqa_database_log VALUES (dqa_change_id_sequence.NEXTVAL, SYSDATE, '1.05', 'Finished upgrading');
COMMIT;
