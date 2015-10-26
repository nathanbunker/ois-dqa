CREATE TABLE resource (
  resource_id            INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  display_label          VARCHAR(1024) NOT NULL,
  url                    VARCHAR(1024) NOT NULL
);


INSERT INTO resource(display_label, url) VALUES ('CHOP Vaccine Availability Timeline', 'http://www.chop.edu/centers-programs/vaccine-education-center/vaccine-history/vaccine-availability-timeline#.VdtNAvlViko');

CREATE TABLE supporting_info (
  supporting_info_id     INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  code_id                INTEGER NOT NULL,
  user_id                INTEGER NOT NULL,
  resource_id            DATETIME NOT NULL,
  abstract_text          TEXT,
  comment_text           TEXT,
  effective_date         DATETIME,
  entry_date             DATETIME
);

