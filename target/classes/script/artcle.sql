SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `artcles`
-- ----------------------------
DROP TABLE IF EXISTS `artcles`;
CREATE TABLE `artcles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `created` TIMESTAMP COMMENT '创建日期',
  `title` varchar(32) DEFAULT NULL COMMENT '标题',
  `content` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
