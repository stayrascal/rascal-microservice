DROP TABLE IF EXISTS `STAFF`;

CREATE TABLE `STAFF` (
  `id` varchar(64) NOT NULL,
  `name` varchar(64),
  `mobile` varchar(16),
  `email` varchar(64),
  `status` varchar(32) NOT NULL,
  `time_created` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `STAFF_AUTHORIZATION` (
  `id` bigint(22) NOT NULL AUTO_INCREMENT,
  `staff_id` varchar(64) not null,
  `organization_id` varchar(64) not null,
  `role_id` varchar(64) not null,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
