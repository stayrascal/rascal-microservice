DROP TABLE IF EXISTS `ORDER_ITEM`;

CREATE TABLE `ORDER_ITEM` (
  `id` bigint(22) NOT NULL AUTO_INCREMENT,
  `order_id` varchar(64) NOT NULL,
  `store_product_id` varchar(64) NOT NULL,
  `store_item_id` varchar(64) NOT NULL,
  `product_item_name` varchar(64) NOT NULL,
  `price` NUMERIC(15, 2) NOT NULL,
  `quantity` INT NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
