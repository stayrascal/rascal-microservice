DROP TABLE IF EXISTS `STORE_PRODUCT`;
DROP TABLE IF EXISTS `STORE_PRODUCT_ITEM`;


CREATE TABLE `STORE_PRODUCT` (
  `id` varchar(64) not null,
  `product_id` varchar(64) not null,
  `category_id` varchar(64) not null,
  `store_id` varchar(64) not null,
  `name` varchar(32) not null,
  `description` varchar(32),
  `thumbnail` varchar(32),
  `create_time_from` BIGINT(22),
  `create_time_to` BIGINT(22),
  `status` varchar(32) NOT NULL ,
  `time_created` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `STORE_PRODUCT_ITEM` (
  `id` varchar(64) not null,
  `store_product_id` varchar(64) not null,
  `item_id` varchar(64) not null,
  `store_id` varchar(64) not null,
  `quantity` int not null,
  `price` DECIMAL not null,
  `time_created` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
