ALTER TABLE `user` ADD `password` VARCHAR(25) NOT NULL AFTER `date_insert`;
ALTER TABLE `user` ADD `role` VARCHAR(25) NOT NULL AFTER `password`;