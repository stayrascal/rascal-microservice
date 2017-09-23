DROP TABLE IF EXISTS `STAFF_ADDRESS_MAPPING`;
DROP TABLE IF EXISTS `STAFF_ORDER_MAPPING`;

CREATE TABLE `STAFF_ADDRESS_MAPPING` (
  `user_id` varchar(64) NOT NULL,
  `address_id` bigint(64) NOT NULL,
  `time_created` datetime NOT NULL,
  UNIQUE KEY `staff_address` (`user_id`,`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `STAFF_ORDER_MAPPING` (
  `user_id` varchar(64) NOT NULL,
  `order_id` varchar(64) NOT NULL,
  `time_created` datetime NOT NULL,
  UNIQUE KEY `staff_order` (`user_id`,`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;