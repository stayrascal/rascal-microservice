CREATE TABLE `MEMBER` (
  `id` varchar(64) NOT NULL,
  `nickname` varchar(64) NOT NULL,
  `gender` varchar(32) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `email` varchar(64) DEFAULT NULL,
  `mobile` varchar(16) DEFAULT NULL,
  `status` varchar(32) NOT NULL,
  `time_created` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
