CREATE DATABASE dqa;

USE dqa;

CREATE USER 'dqa_web'@'localhost' IDENTIFIED BY 'SharkBaitHooHaHa';

GRANT ALL PRIVILEGES ON dqa.* TO 'dqa_web'@'localhost' WITH GRANT OPTION;

