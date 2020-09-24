/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50716
Source Host           : 127.0.0.1:3306
Source Database       : hibernate_db

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2020-09-20 23:55:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(20) DEFAULT NULL,
  `last_name` varchar(20) DEFAULT NULL,
  `salary` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('1', 'Zara', 'Ali', '3000');
INSERT INTO `employee` VALUES ('3', 'zhang', 'Paul', '10000');
INSERT INTO `employee` VALUES ('7', 'zhang', 'san', '6666');
INSERT INTO `employee` VALUES ('9', 'wang', 'wu', '2000');
INSERT INTO `employee` VALUES ('10', 'Zara', 'Ali', '3000');
INSERT INTO `employee` VALUES ('11', 'zhang', 'Paul', '10000');
INSERT INTO `employee` VALUES ('12', 'zhang', 'san', '6666');
INSERT INTO `employee` VALUES ('13', 'wang', 'wu', '2000');
INSERT INTO `employee` VALUES ('18', 'wang', 'wu', '2000');
INSERT INTO `employee` VALUES ('19', 'li', 'si', '6');
