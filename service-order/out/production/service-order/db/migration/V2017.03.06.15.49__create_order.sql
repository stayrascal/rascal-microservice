DROP TABLE IF EXISTS `CLOUD_ORDER`;
DROP TABLE IF EXISTS `ORDER_ITEM`;

CREATE TABLE `CLOUD_ORDER` (
  `id` varchar(64) NOT NULL,
  `user_id` varchar(64) NOT NULL,
  `store_id` varchar(64) NOT NULL,
  `user_mobile` varchar(16) NOT NULL,
  `user_name` varchar(32) NOT NULL,
  `store_name` varchar(64) NOT NULL,
  `order_note` varchar(255) DEFAULT NULL,
  `invoice_receiver_name` varchar(64) DEFAULT NULL,
  `invoice_content` VARCHAR(64) DEFAULT NULL,
  `receiver_name` VARCHAR(64) DEFAULT NULL,
  `receiver_mobile` VARCHAR(16) DEFAULT NULL,
  `receiver_province` VARCHAR(32) DEFAULT NULL,
  `receiver_city` varchar(32) DEFAULT NULL,
  `receiver_district` varchar(32) DEFAULT NULL,
  `receiver_address` VARCHAR(255) DEFAULT NULL,
  `receiver_zip_code` VARCHAR(18) DEFAULT NULL,
  `payment_id` VARCHAR(64) DEFAULT NULL,
  `ship_method` VARCHAR(32) DEFAULT NULL,
  `status` VARCHAR(32) DEFAULT NULL,
  `time_modified` datetime NOT NULL,
  `price` NUMERIC(15, 2) NOT NULL,
  `time_created` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `ORDER_ITEM` (
  `id` bigint(22) NOT NULL AUTO_INCREMENT,
  `order_id` varchar(64) NOT NULL,
  `store_item_id` varchar(64) NOT NULL,
  `store_product_id` varchar(64) NOT NULL,
  `quantity` INT NOT NULL,
  `price` NUMERIC(15, 2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;