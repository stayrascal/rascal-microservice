CREATE TABLE `ROLE` (
  `id` varchar(64) not null,
  `name` varchar(64) not null,
  `time_created` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `ROLE_PERMISSION` (
  `id` bigint(22) NOT NULL AUTO_INCREMENT,
  `role_id` varchar(64) not null,
  `resource` varchar(32) not null,
  `type` varchar(32) not null,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
