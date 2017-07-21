DROP TABLE IF EXISTS `AUTHENTICATION`;

CREATE TABLE `AUTHENTICATION` (
  `id` varchar(64) not null,
  `identity_type` varchar(32) not null,
  `identity_id` varchar(64) not null,
  `authentication_type` varchar(32) not null,
  `authentication_name` varchar(64) not null,
  `time_created` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

TRUNCATE `AUTHENTICATION_KEY`;
