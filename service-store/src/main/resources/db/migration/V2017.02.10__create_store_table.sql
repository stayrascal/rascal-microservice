CREATE TABLE `STORE` (
  `id` varchar(64) NOT NULL,
  `name` varchar(64) NOT NULL,
  `shop_time` varchar(32) NOT NULL,
  `closing_time` varchar(32) NOT NULL,
  `province_id` bigint(22) NOT NULL,
  `city_id` bigint(22) NOT NULL,
  `district_id` bigint(22) NOT NULL,
  `address` varchar(256) NOT NULL,
  `longitude` double NOT NULL,
  `latitude` double NOT NULL,
  `contact_number` varchar(16) not null,
  `status` varchar(32) NOT NULL,
  `time_created` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
