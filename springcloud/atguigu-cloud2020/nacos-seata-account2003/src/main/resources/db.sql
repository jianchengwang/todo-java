CREATE DATABASE seata_account;
CREATE TABLE `seata_account`.`t_account` ( `id` BIGINT(11) NOT NULL AUTO_INCREMENT COMMENT 'id', `user_id` BIGINT(11) COMMENT '用户id', `total` DECIMAL(10,0) COMMENT '总额度', `used` DECIMAL(10,0) COMMENT '已用余额', `residue` DECIMAL(10,0) COMMENT '剩余可用额度', PRIMARY KEY (`id`) ) ENGINE=INNODB CHARSET=utf8 COLLATE=utf8_unicode_ci;
INSERT INTO `seata_account`.`t_account` (`user_id`, `total`, `used`, `residue`) VALUES ('1', '1000', '0', '1000');
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