DROP TABLE IF EXISTS `ADDRESS`;

CREATE TABLE `ADDRESS` (
  `id` bigint(22) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(22),
  `path` varchar(64) NOT NULL,
  `name` varchar(32) NOT NULL,
  `name_en` varchar(32) NOT NULL,
  `language` varchar(16) NOT NULL,
  `status` varchar(16) NOT NULL,
  `grade` INT NOT NULL,
  `time_created` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
