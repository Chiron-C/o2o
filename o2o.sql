/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : o2o

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-04-29 11:17:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_area
-- ----------------------------
DROP TABLE IF EXISTS `tb_area`;
CREATE TABLE `tb_area` (
  `area_id` int(2) NOT NULL AUTO_INCREMENT,
  `area_name` varchar(200) NOT NULL,
  `priority` int(2) NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  PRIMARY KEY (`area_id`),
  UNIQUE KEY `UK_AREA` (`area_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_area
-- ----------------------------
INSERT INTO `tb_area` VALUES ('1', '杭电', '1', null, null);
INSERT INTO `tb_area` VALUES ('2', '理工', '2', null, null);

-- ----------------------------
-- Table structure for tb_head_line
-- ----------------------------
DROP TABLE IF EXISTS `tb_head_line`;
CREATE TABLE `tb_head_line` (
  `line_id` int(100) NOT NULL AUTO_INCREMENT,
  `line_name` varchar(1000) DEFAULT NULL,
  `line_link` varchar(2000) NOT NULL,
  `line_img` varchar(2000) NOT NULL,
  `priority` int(2) DEFAULT NULL,
  `enable_status` int(2) NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  PRIMARY KEY (`line_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_head_line
-- ----------------------------

-- ----------------------------
-- Table structure for tb_local_auth
-- ----------------------------
DROP TABLE IF EXISTS `tb_local_auth`;
CREATE TABLE `tb_local_auth` (
  `local_auth_id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL,
  `username` varchar(128) DEFAULT NULL,
  `password` varchar(128) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  PRIMARY KEY (`local_auth_id`),
  KEY `user_id1` (`user_id`),
  CONSTRAINT `user_id1` FOREIGN KEY (`user_id`) REFERENCES `tb_person_info` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_local_auth
-- ----------------------------

-- ----------------------------
-- Table structure for tb_person_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_person_info`;
CREATE TABLE `tb_person_info` (
  `user_id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `profile_img` varchar(1024) DEFAULT NULL,
  `email` varchar(1024) DEFAULT NULL,
  `gender` varchar(2) DEFAULT NULL,
  `enable_status` int(2) DEFAULT '0' COMMENT '0禁止登录,1可以登录',
  `user_type` int(2) DEFAULT '1' COMMENT '1顾客,2商家,3管理员',
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_person_info
-- ----------------------------
INSERT INTO `tb_person_info` VALUES ('1', 'test', 'test', 'test', '1', '1', '2', null, null);

-- ----------------------------
-- Table structure for tb_product
-- ----------------------------
DROP TABLE IF EXISTS `tb_product`;
CREATE TABLE `tb_product` (
  `product_id` int(100) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(255) NOT NULL,
  `product_desc` varchar(1000) DEFAULT NULL,
  `img_addr` varchar(2000) DEFAULT '',
  `normal_price` varchar(100) DEFAULT NULL,
  `promotion_price` varchar(100) DEFAULT NULL,
  `priority` int(2) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  `enable_status` int(2) NOT NULL DEFAULT '0',
  `product_category_id` int(11) DEFAULT NULL,
  `shop_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  KEY `product_category_id` (`product_category_id`),
  KEY `shop_id_for_product` (`shop_id`),
  CONSTRAINT `product_category_id` FOREIGN KEY (`product_category_id`) REFERENCES `tb_product_category` (`product_category_id`),
  CONSTRAINT `shop_id_for_product` FOREIGN KEY (`shop_id`) REFERENCES `tb_shop` (`shop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_product
-- ----------------------------

-- ----------------------------
-- Table structure for tb_product_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_product_category`;
CREATE TABLE `tb_product_category` (
  `product_category_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_category_name` varchar(255) NOT NULL DEFAULT '',
  `priority` int(2) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  `shop_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`product_category_id`),
  KEY `shop_id_for_product_category` (`shop_id`),
  CONSTRAINT `shop_id_for_product_category` FOREIGN KEY (`shop_id`) REFERENCES `tb_shop` (`shop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_product_category
-- ----------------------------

-- ----------------------------
-- Table structure for tb_product_img
-- ----------------------------
DROP TABLE IF EXISTS `tb_product_img`;
CREATE TABLE `tb_product_img` (
  `product_img_id` int(20) NOT NULL AUTO_INCREMENT,
  `img_addr` varchar(2000) NOT NULL,
  `img_desc` varchar(1000) DEFAULT NULL,
  `priority` int(2) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  `product_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`product_img_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `product_id` FOREIGN KEY (`product_id`) REFERENCES `tb_product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_product_img
-- ----------------------------

-- ----------------------------
-- Table structure for tb_shop
-- ----------------------------
DROP TABLE IF EXISTS `tb_shop`;
CREATE TABLE `tb_shop` (
  `shop_id` int(10) NOT NULL AUTO_INCREMENT,
  `owner_id` int(2) DEFAULT NULL,
  `area_id` int(2) DEFAULT NULL,
  `shop_category_id` int(2) DEFAULT NULL,
  `shop_name` varchar(255) NOT NULL,
  `shop_desc` varchar(1024) DEFAULT NULL,
  `shop_addr` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `shop_img` varchar(1024) DEFAULT NULL,
  `priority` int(2) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  `enable_status` int(2) DEFAULT '-1',
  `advice` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`shop_id`),
  KEY `owner_id` (`owner_id`),
  KEY `area_id` (`area_id`),
  KEY `shop_category_id` (`shop_category_id`),
  CONSTRAINT `area_id` FOREIGN KEY (`area_id`) REFERENCES `tb_area` (`area_id`),
  CONSTRAINT `owner_id` FOREIGN KEY (`owner_id`) REFERENCES `tb_person_info` (`user_id`),
  CONSTRAINT `shop_category_id` FOREIGN KEY (`shop_category_id`) REFERENCES `tb_shop_category` (`shop_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_shop
-- ----------------------------
INSERT INTO `tb_shop` VALUES ('24', '1', '2', '1', '测试的店铺1', 'test1', 'test1', '120', '\\upload\\item\\shop\\24\\2018042015002764557.jpg', null, '2018-04-20 15:00:27', '2018-04-20 15:00:27', '0', '审核中');
INSERT INTO `tb_shop` VALUES ('25', '1', '2', '1', '测试的店铺1', 'test1', 'test1', '120', '\\upload\\item\\shop\\25\\2018042015080567513.jpg', null, '2018-04-20 15:08:05', '2018-04-20 15:08:05', '0', '审核中');
INSERT INTO `tb_shop` VALUES ('26', '1', '2', '1', '测试的店铺1', 'test1', 'test1', '120', '\\upload\\item\\shop\\26\\2018042015093219387.jpg', null, '2018-04-20 15:09:33', '2018-04-20 15:09:33', '0', '审核中');
INSERT INTO `tb_shop` VALUES ('27', '1', '2', '1', '测试的店铺1', 'test1', 'test1', '120', '\\upload\\item\\shop\\27\\2018042015100321446.jpg', null, '2018-04-20 15:10:03', '2018-04-20 15:10:03', '0', '审核中');
INSERT INTO `tb_shop` VALUES ('28', '1', '2', '1', '测试的店铺1', 'test1', 'test1', '120', '\\upload\\item\\shop\\28\\2018042015104552345.jpg', null, '2018-04-20 15:10:45', '2018-04-20 15:10:45', '0', '审核中');
INSERT INTO `tb_shop` VALUES ('29', '1', '2', '1', '测试的店铺1', 'test1', 'test1', '120', '\\upload\\item\\shop\\29\\2018042015112745521.jpg', null, '2018-04-20 15:11:28', '2018-04-20 15:11:28', '0', '审核中');
INSERT INTO `tb_shop` VALUES ('30', '1', '2', '1', '测试的店铺1', 'test1', 'test1', '120', '\\upload\\item\\shop\\30\\2018042015125931826.jpg', null, '2018-04-20 15:12:59', '2018-04-20 15:12:59', '0', '审核中');
INSERT INTO `tb_shop` VALUES ('31', '1', '2', '1', '测试的店铺1', 'test1', 'test1', '120', '\\upload\\item\\shop\\31\\2018042015144625353.jpg', null, '2018-04-20 15:14:47', '2018-04-20 15:14:47', '0', '审核中');
INSERT INTO `tb_shop` VALUES ('32', '1', '2', '1', '测试的店铺1', 'test1', 'test1', '120', '\\upload\\item\\shop\\32\\2018042015164183605.jpg', null, '2018-04-20 15:16:41', '2018-04-20 15:16:41', '0', '审核中');

-- ----------------------------
-- Table structure for tb_shop_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_shop_category`;
CREATE TABLE `tb_shop_category` (
  `shop_category_id` int(11) NOT NULL AUTO_INCREMENT,
  `shop_category_name` varchar(100) NOT NULL DEFAULT '',
  `shop_category_desc` varchar(1000) DEFAULT '',
  `shop_category_img` varchar(2000) DEFAULT NULL,
  `priority` int(2) NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`shop_category_id`),
  KEY `parent_id` (`parent_id`),
  CONSTRAINT `parent_id` FOREIGN KEY (`parent_id`) REFERENCES `tb_shop_category` (`shop_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_shop_category
-- ----------------------------
INSERT INTO `tb_shop_category` VALUES ('1', '咖啡', '咖啡', null, '1', null, null, null);
INSERT INTO `tb_shop_category` VALUES ('2', '牛奶', '咖啡牛奶', null, '2', null, null, '1');

-- ----------------------------
-- Table structure for tb_wechat_auth
-- ----------------------------
DROP TABLE IF EXISTS `tb_wechat_auth`;
CREATE TABLE `tb_wechat_auth` (
  `wechat_auth_id` int(10) NOT NULL AUTO_INCREMENT,
  `open_id` varchar(1024) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `user_id` int(10) NOT NULL,
  PRIMARY KEY (`wechat_auth_id`),
  UNIQUE KEY `open_id` (`open_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `tb_person_info` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_wechat_auth
-- ----------------------------
