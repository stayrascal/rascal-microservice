DROP TABLE IF EXISTS `CATEGORY`;
DROP TABLE IF EXISTS `PRODUCT_OPTION`;
DROP TABLE IF EXISTS `OPTION_VALUE`;


CREATE TABLE `CATEGORY` (
  `id` varchar(64) not null,
  `name` varchar(32) not null,
  `index` INT NOT NULL ,
  `time_created` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `PRODUCT_OPTION` (
  `id` varchar(64) not null,
  `name` varchar(32) not null,
  `time_created` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `OPTION_VALUE` (
  `id` varchar(64) not null,
  `value` varchar(32) not null,
  `time_created` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
