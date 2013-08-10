CREATE DATABASE IF NOT EXISTS server_monitoring_db CHARACTER SET utf8 COLLATE utf8_bin;
GRANT ALL PRIVILEGES ON server_monitoring_db.* TO 'root'@localhost IDENTIFIED BY '' ;

USE server_monitoring_db;

CREATE TABLE IF NOT EXISTS employee_entity (
id INT(50) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
employee_name VARCHAR(50) NOT NULL,
login VARCHAR(20) NOT NULL,
password VARCHAR(64) NOT NULL,
email VARCHAR(50) NOT NULL,
created TIMESTAMP,
lastLogin TIMESTAMP,
active INT NOT NULL,
admin INT NOT NULL,
INDEX(login),
UNIQUE (id)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS server_entity (
id INT(50) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
server_name VARCHAR(50) NOT NULL,
address VARCHAR(50) NOT NULL,
port INT(16) NOT NULL,
url VARCHAR(255) NOT NULL,
state VARCHAR(255),
response VARCHAR(255),
created TIMESTAMP,
lastCheck TIMESTAMP,
active INT NOT NULL,
responsible INT,
INDEX(id),
UNIQUE (server_name)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS system_settings (
id INT(50) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
settings_name VARCHAR(50) NOT NULL,
scan_interval INT(50),
timeout INT(50),
reload_time INT(50),
smtp_adress VARCHAR(50),
smtp_port INT (16),
INDEX(settings_name),
UNIQUE (settings_name)
) engine=InnoDB;
