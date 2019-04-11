CREATE DATABASE `inventory` /*!40100 COLLATE 'utf8_general_ci' */

INSERT INTO `user` (`user_id`, `last_name`, `name`, `password`, `username`) VALUES (1, 'last', 'name', '$2a$10$st8wbuXeDLZsJxUTz5WyTOqlYWR4vE8TxxlCNJ.EoJ5BbKuRTu7KC', 'namelast');

INSERT INTO `inventory`.`role` (`role_id`, `role`) VALUES (1, 'ADMIN');

INSERT INTO `inventory`.`role` (`role_id`, `role`) VALUES (2, 'USER');

INSERT INTO `inventory`.`user_role` (`user_id`, `role_id`) VALUES (1, 1);
