DROP  TABLE IF EXISTS `ORGANIZATION`;

CREATE TABLE `ORGANIZATION` (
  `id` varchar(64) NOT NULL,
  `superior_id` varchar(64) NOT NULL,
  `name` varchar(32) NOT NULL,
  `type` varchar(32) NOT NULL,
  `time_created` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;