/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50716
Source Host           : 127.0.0.1:3306
Source Database       : hibernate_db

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2020-09-20 23:55:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for employee02
-- ----------------------------
DROP TABLE IF EXISTS `employee02`;
CREATE TABLE `employee02` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `salary` int(11) DEFAULT NULL,
  `address` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_4afygo6734cxxru54vo8ak5iy` (`address`),
  CONSTRAINT `FK_4afygo6734cxxru54vo8ak5iy` FOREIGN KEY (`address`) REFERENCES `address` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee02
-- ----------------------------
INSERT INTO `employee02` VALUES ('1', 'Manoj', 'Kumar', '5000', '1');
