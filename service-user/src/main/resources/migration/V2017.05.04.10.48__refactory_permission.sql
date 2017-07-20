DROP TABLE IF EXISTS `ROLE_PERMISSION`;

CREATE TABLE `ROLE_PERMISSION` (
  `id` bigint(22) NOT NULL AUTO_INCREMENT,
  `role_id` varchar(64) not null,
  `permission_type` varchar(32) not null,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
