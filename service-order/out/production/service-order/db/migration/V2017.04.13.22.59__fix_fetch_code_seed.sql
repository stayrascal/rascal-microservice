DROP TABLE IF EXISTS `PICKUP_CODE_SEED`;

CREATE TABLE `PICKUP_CODE_SEED` (
  `id` bigint(22) NOT NULL AUTO_INCREMENT,
  `code_differentiator` varchar(64) NOT NULL,
  `next_code` INTEGER NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `PICKUP_CODE_SEED` ADD UNIQUE `differentiator_code_unique_constraint`(`code_differentiator`, `next_code`);
