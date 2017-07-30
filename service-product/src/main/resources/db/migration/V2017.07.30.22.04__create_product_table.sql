DROP TABLE IF EXISTS `PRODUCT`;
DROP TABLE IF EXISTS `PRODUCT_ITEM`;
DROP TABLE IF EXISTS `OPTION_PAIR`;

CREATE TABLE `PRODUCT` (
  `id` varchar(64) not null,
  `name` varchar(32) not null,
  `description` varchar(64),
  `thumbnail` varchar(32),
  `create_time_from` bigint(22),
  `create_time_to` bigint(22),
  `filter_included` BIT,
  `status` varchar(16) NOT NULL ,
  `time_created` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `PRODUCT_ITEM` (
  `id` varchar(64) not null,
  `product_id` varchar(64) not null,
  `price` DECIMAL not null,
  `time_created` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `OPTION_PAIR` (
  `id` varchar(64) not null,
  `name` varchar(32) not null,
  `value` varchar(32) NOT NULL ,
  `time_created` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;