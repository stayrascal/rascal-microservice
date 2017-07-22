DROP TABLE IF EXISTS `CLOUD_ORDER`;
DROP TABLE IF EXISTS `ORDER_ITEM`;

CREATE TABLE `ORDER` (
  `id` varchar(64) NOT NULL,
  `user_id` varchar(64) NOT NULL,
  `store_id` varchar(64) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `payment_id` VARCHAR(64) DEFAULT NULL,
  `delivery_method` VARCHAR(32) DEFAULT NULL,
  `status` VARCHAR(32) NOT NULL,
  `time_created` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `ORDER_ITEM` (
  `id` bigint(22) NOT NULL AUTO_INCREMENT,
  `order_id` varchar(64) NOT NULL,
  `store_item_id` varchar(64) NOT NULL,
  `product_item_name` varchar(64) NOT NULL,
  `price` NUMERIC(15, 2) NOT NULL,
  `quantity` INT NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
