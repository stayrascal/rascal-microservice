CREATE TABLE `TRANSACTION` (
  `id` bigint(22) NOT NULL AUTO_INCREMENT,
  `order_id` varchar(64) NOT NULL,
  `third_transaction_id` varchar(64) NOT NULL,
  `transaction_provider` varchar(32) NOT NULL,
  `we_chat_trade_type` varchar(32) DEFAULT NULL,
  `price` NUMERIC(15,2) NOT NULL,
  `status` varchar(32) NOT NULL,
  `time_created` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
