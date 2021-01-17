CREATE DATABASE seata_storage;
CREATE TABLE `seata_storage`.`t_storage` ( `id` BIGINT(11) NOT NULL AUTO_INCREMENT COMMENT 'id', `product_id` BIGINT(11) COMMENT '产品id', `total` INT(11) COMMENT '总库存', `used` INT(11) COMMENT '已用库存', `residue` INT(11) COMMENT '剩余库存', PRIMARY KEY (`id`) ) ENGINE=INNODB CHARSET=utf8 COLLATE=utf8_unicode_ci;
INSERT INTO `seata_storage`.`t_storage` (`product_id`, `total`, `used`, `residue`) VALUES ('1', '100', '0', '100');
-- for AT mode you must to init this sql for you business database. the seata server not need it.
CREATE TABLE IF NOT EXISTS `undo_log`
(
    `branch_id`     BIGINT(20)   NOT NULL COMMENT 'branch transaction id',
    `xid`           VARCHAR(100) NOT NULL COMMENT 'global transaction id',
    `context`       VARCHAR(128) NOT NULL COMMENT 'undo_log context,such as serialization',
    `rollback_info` LONGBLOB     NOT NULL COMMENT 'rollback info',
    `log_status`    INT(11)      NOT NULL COMMENT '0:normal status,1:defense status',
    `log_created`   DATETIME(6)  NOT NULL COMMENT 'create datetime',
    `log_modified`  DATETIME(6)  NOT NULL COMMENT 'modify datetime',
    UNIQUE KEY `ux_undo_log` (`xid`, `branch_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8 COMMENT ='AT transaction mode undo table';