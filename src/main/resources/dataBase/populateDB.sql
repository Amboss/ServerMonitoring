INSERT INTO `employee_entity`(`id`, `employee_name`, `login`, `password`, `email`, `created`,
`lastLogin`, `active`, `admin`) VALUES (01, 'Admin Adminov', 'admin',
'8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 'admin_mail@mail.com',
'2013-05-19 00:00:01', '2038-01-09 03:14:07', 1, 1);
INSERT INTO `employee_entity`(`id`, `employee_name`, `login`, `password`, `email`, `created`,
`lastLogin`, `active`, `admin`) VALUES (02, 'User Userov', 'user',
'04f8996da763b7a969b1028ee3007569eaf3a635486ddab211d512c85b9df8fb', 'user_mail@mail.com',
'2013-05-19 00:00:01', '2038-01-09 03:14:07', 1, 0);

INSERT INTO `server_entity`(`id`, `server_name`, `address`, `port`, `url`, `state`, `response`,
 `created`, `lastCheck`, `active`) VALUES (09, 'localhost', '255.255.255.0', 8080,
 'http://localhost/', 'OK', 'OK', '2013-05-19 00:00:01', '2038-01-09 03:14:07', 1);

