ALTER TABLE `ORDER` ADD COLUMN `pickup_code` VARCHAR(32) DEFAULT NULL AFTER `note`;

CREATE TABLE `PICKUP_CODE_SEED` (
  `id` bigint(22) NOT NULL AUTO_INCREMENT,
  `order_id` varchar(64) NOT NULL,
  `store_id` varchar(64) NOT NULL,
  `next_code` INTEGER NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `PICKUP_CODE_SEED` ADD UNIQUE `store_code_unique_constraint`(`order_id`, `store_id`);
