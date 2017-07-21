CREATE TABLE `STAFF` (
  `id` varchar(64) NOT NULL,
  `name` varchar(64),
  `mobile` varchar(16),
  `email` varchar(64),
  `role` varchar(32) NOT NULL,
  `group` varchar(32) NOT NULL,
  `status` varchar(32) NOT NULL,
  `assign_ids` varchar(2048),
  `time_created` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;