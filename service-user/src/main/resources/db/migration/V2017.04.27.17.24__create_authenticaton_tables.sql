CREATE TABLE `AUTHENTICATION_KEY` (
  `id` bigint(22) not null AUTO_INCREMENT,
  `authentication_id` varchar(64) not null,
  `key_value` varchar(64) not null,
  `expired_time` datetime not null,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `AUTHENTICATION` (
  `id` varchar(64) not null,
  `account_id` varchar(64) not null,
  `authentication_type` varchar(32) not null,
  `authentication_name` varchar(64) not null,
  `account_role` varchar(32) not null,
  `account_group` varchar(32) not null,
  `time_created` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
