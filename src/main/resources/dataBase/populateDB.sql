INSERT IGNORE INTO `employee_entity`(`id`, `employee_name`, `login`, `password`, `email`, `created`,
`lastLogin`, `active`, `admin`) VALUES (01, 'Admin Adminov', 'admin',
'8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 'admin_mail@mail.com',
'2013-05-19 00:00:01', '2038-01-09 03:14:07', 1, 1);
INSERT IGNORE INTO `employee_entity`(`id`, `employee_name`, `login`, `password`, `email`, `created`,
`lastLogin`, `active`, `admin`) VALUES (02, 'User Userov2', 'user2',
'd74ff0ee8da3b9806b18c877dbf29bbde50b5bd8e4dad7a3a725000feb82e8f1', 'user_mail@mail.com',
'2013-05-19 00:00:01', '2038-01-09 03:14:07', 1, 0);
INSERT IGNORE INTO `employee_entity`(`id`, `employee_name`, `login`, `password`, `email`, `created`,
`lastLogin`, `active`, `admin`) VALUES (03, 'User2 Userov3', 'user3',
'd74ff0ee8da3b9806b18c877dbf29bbde50b5bd8e4dad7a3a725000feb82e8f1', 'user_mail@mail.com',
'2013-05-19 00:00:01', '2038-01-09 03:14:07', 1, 0);

INSERT IGNORE INTO `server_entity`(`id`, `server_name`, `address`, `port`, `url`, `state`, `response`,
 `created`, `lastCheck`, `active`, `responsible`) VALUES (01, 'localhost', '255.255.255.0', 8080,
 'http://localhost/', 'OK', 'OK', '2013-05-19 00:00:01', '2038-01-09 03:14:07', 1, 02);
INSERT IGNORE INTO `server_entity`(`id`, `server_name`, `address`, `port`, `url`, `state`, `response`,
 `created`, `lastCheck`, `active`, `responsible`) VALUES (02, 'localhost2', '255.255.255.0', 8080,
 'http://localhost/', 'OK', 'OK', '2013-05-19 00:00:01', '2038-01-09 03:14:07', 1, 02);
INSERT IGNORE INTO `server_entity`(`id`, `server_name`, `address`, `port`, `url`, `state`, `response`,
 `created`, `lastCheck`, `active`, `responsible`) VALUES (03, 'localhost3', '255.255.255.0', 8080,
 'http://localhost/', 'OK', 'OK', '2013-05-19 00:00:01', '2038-01-09 03:14:07', 1, 02);
INSERT IGNORE INTO `server_entity`(`id`, `server_name`, `address`, `port`, `url`, `state`, `response`,
 `created`, `lastCheck`, `active`, `responsible`) VALUES (04, 'localhost4', '255.255.255.0', 8080,
 'http://localhost/', 'OK', 'OK', '2013-05-19 00:00:01', '2038-01-09 03:14:07', 1, 02);
INSERT IGNORE INTO `server_entity`(`id`, `server_name`, `address`, `port`, `url`, `state`, `response`,
 `created`, `lastCheck`, `active`, `responsible`) VALUES (05, 'localhost5', '255.255.255.0', 8080,
 'http://localhost/', 'OK', 'OK', '2013-05-19 00:00:01', '2038-01-09 03:14:07', 1, 03);
INSERT IGNORE INTO `server_entity`(`id`, `server_name`, `address`, `port`, `url`, `state`, `response`,
 `created`, `lastCheck`, `active`, `responsible`) VALUES (06, 'localhost6', '255.255.255.0', 8080,
 'http://localhost/', 'OK', 'OK', '2013-05-19 00:00:01', '2038-01-09 03:14:07', 1, 03);
INSERT IGNORE INTO `server_entity`(`id`, `server_name`, `address`, `port`, `url`, `state`, `response`,
 `created`, `lastCheck`, `active`, `responsible`) VALUES (07, 'localhost7', '255.255.255.0', 8080,
 'http://localhost/', 'OK', 'OK', '2013-05-19 00:00:01', '2038-01-09 03:14:07', 1, 03);
INSERT IGNORE INTO `server_entity`(`id`, `server_name`, `address`, `port`, `url`, `state`, `response`,
 `created`, `lastCheck`, `active`, `responsible`) VALUES (08, 'localhost8', '255.255.255.0', 8080,
 'http://localhost/', 'OK', 'OK', '2013-05-19 00:00:01', '2038-01-09 03:14:07', 1, 03);

INSERT IGNORE INTO `system_settings`(`id`, `settings_name`, `scan_interval`,`timeout`,`reload_time`,`smtp_adress`,`smtp_port`)
VALUES (01, "default", 5000, 5000, 5000, 'huskyserge@gmail.com', 587);