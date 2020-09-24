/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50716
Source Host           : 127.0.0.1:3306
Source Database       : hibernate_db

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2020-09-20 23:55:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for certificate
-- ----------------------------
DROP TABLE IF EXISTS `certificate`;
CREATE TABLE `certificate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `certificate_name` varchar(30) DEFAULT NULL,
  `employee_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_1t5pca769lsff735cnwkxbdq4` (`employee_id`),
  CONSTRAINT `FK_1t5pca769lsff735cnwkxbdq4` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of certificate
-- ----------------------------
INSERT INTO `certificate` VALUES ('1', 'PMP', '1');
INSERT INTO `certificate` VALUES ('2', 'MBA', '1');
INSERT INTO `certificate` VALUES ('3', 'MCA', '1');
INSERT INTO `certificate` VALUES ('6', 'A', '3');
