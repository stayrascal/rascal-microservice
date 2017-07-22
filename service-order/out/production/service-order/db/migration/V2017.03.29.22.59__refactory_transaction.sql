DROP TABLE IF EXISTS `TRANSACTION`;

CREATE TABLE `TRANSACTION` (
  `id` varchar(64) NOT NULL,
  `order_id` varchar(64) NOT NULL,
  `provider` varchar(32) NOT NULL,
  `client_type` varchar(32) NOT NULL,
  `total_amount` NUMERIC(15, 2) NOT NULL,
  `third_party_transaction_id` varchar(64) DEFAULT NULL,
  `third_party_transaction_info` varchar(2048) DEFAULT NULL,
  `status` varchar(32) NOT NULL,
  `time_created` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `ORDER` CHANGE COLUMN `transaction_id` `transaction_id` VARCHAR(64) DEFAULT NULL;