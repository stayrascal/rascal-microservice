DROP TABLE IF EXISTS `ORDER`;

CREATE TABLE `ORDER` (
  `id` varchar(64) NOT NULL,
  `user_id` varchar(64) NOT NULL,
  `store_id` varchar(64) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `transaction_id` VARCHAR(64) DEFAULT NULL,
  `delivery_method` VARCHAR(32) DEFAULT NULL,
  `status` VARCHAR(32) NOT NULL,
  `time_created` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
