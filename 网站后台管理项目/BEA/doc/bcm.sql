/*
Navicat MySQL Data Transfer

Source Server         : MYSQL
Source Server Version : 50616
Source Host           : localhost:3306
Source Database       : bcm

Target Server Type    : MYSQL
Target Server Version : 50616
File Encoding         : 65001

Date: 2014-11-03 18:51:35
*/

SET FOREIGN_KEY_CHECKS=0;

CREATE DATABASE `bcm`
    CHARACTER SET 'utf8'
    COLLATE 'utf8_general_ci';

USE `bcm`;
-- ----------------------------
-- Table structure for bument
-- ----------------------------
DROP TABLE IF EXISTS `bument`;
CREATE TABLE `bument` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `buMenName` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bument
-- ----------------------------
INSERT INTO `bument` VALUES ('1', '普通部门');

-- ----------------------------
-- Table structure for lanmut
-- ----------------------------
DROP TABLE IF EXISTS `lanmut`;
CREATE TABLE `lanmut` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL,
  `lanMuName` varchar(30) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `moXingID` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lanmut
-- ----------------------------
INSERT INTO `lanmut` VALUES ('1', '0', '关于集团', '1', '1');
INSERT INTO `lanmut` VALUES ('2', '0', '新闻资讯', '2', '1');
INSERT INTO `lanmut` VALUES ('3', '0', '业务领域', '3', '1');
INSERT INTO `lanmut` VALUES ('4', '0', '下属公司', '4', '1');
INSERT INTO `lanmut` VALUES ('5', '0', '企业文化', '5', '1');
INSERT INTO `lanmut` VALUES ('6', '0', '社会责任', '6', '1');
INSERT INTO `lanmut` VALUES ('7', '0', '资源中心', '7', '1');
INSERT INTO `lanmut` VALUES ('8', '1', '集团简介', '8', '1');
INSERT INTO `lanmut` VALUES ('9', '1', '组织架构', null, '1');
INSERT INTO `lanmut` VALUES ('10', '1', '管理团队', null, '1');
INSERT INTO `lanmut` VALUES ('11', '1', '董事长专栏', null, '1');
INSERT INTO `lanmut` VALUES ('12', '1', '发展战略', null, '1');
INSERT INTO `lanmut` VALUES ('13', '1', '经营业绩', null, '1');
INSERT INTO `lanmut` VALUES ('14', '2', '集团新闻', null, '1');
INSERT INTO `lanmut` VALUES ('15', '2', '下属公司动态', null, '1');
INSERT INTO `lanmut` VALUES ('16', '2', '本地新闻', null, '1');
INSERT INTO `lanmut` VALUES ('18', '2', '他山之石', null, '1');
INSERT INTO `lanmut` VALUES ('19', '3', '融资业务', null, '1');
INSERT INTO `lanmut` VALUES ('20', '3', '综合投资业务', null, '1');
INSERT INTO `lanmut` VALUES ('21', '3', '金融业务', null, '1');
INSERT INTO `lanmut` VALUES ('22', '3', '总部经济运营业务', null, '1');
INSERT INTO `lanmut` VALUES ('23', '3', '地产经济运营业务', null, '1');
INSERT INTO `lanmut` VALUES ('24', '3', '地产业务', null, '1');
INSERT INTO `lanmut` VALUES ('25', '4', '全资企业', null, '1');
INSERT INTO `lanmut` VALUES ('26', '4', '控股企业', null, '1');
INSERT INTO `lanmut` VALUES ('27', '4', '参股企业', null, '1');
INSERT INTO `lanmut` VALUES ('28', '5', '党建工作', null, '1');
INSERT INTO `lanmut` VALUES ('29', '5', '发投风采', null, '1');
INSERT INTO `lanmut` VALUES ('30', '5', '员工论坛', null, '1');
INSERT INTO `lanmut` VALUES ('31', '5', '公司规范', null, '1');
INSERT INTO `lanmut` VALUES ('32', '5', '工作动态', null, '1');
INSERT INTO `lanmut` VALUES ('33', '5', '企业精神', null, '1');
INSERT INTO `lanmut` VALUES ('34', '5', '案例解读', null, '1');
INSERT INTO `lanmut` VALUES ('35', '5', '企业杂志', null, '1');
INSERT INTO `lanmut` VALUES ('36', '6', '责任理念', null, '1');
INSERT INTO `lanmut` VALUES ('37', '6', '责任实践', null, '1');
INSERT INTO `lanmut` VALUES ('38', '7', '服务流程', null, '1');
INSERT INTO `lanmut` VALUES ('39', '7', '政策法规', null, '1');
INSERT INTO `lanmut` VALUES ('40', '39', '国家法规', null, '1');
INSERT INTO `lanmut` VALUES ('41', '39', '省市规章', null, '1');
INSERT INTO `lanmut` VALUES ('42', '39', '集团规章', null, '1');
INSERT INTO `lanmut` VALUES ('43', '11', '董事长讲话', null, '1');
INSERT INTO `lanmut` VALUES ('44', '11', '重大活动', null, '1');
INSERT INTO `lanmut` VALUES ('45', '11', '调查研究', null, '1');

-- ----------------------------
-- Table structure for moxingt
-- ----------------------------
DROP TABLE IF EXISTS `moxingt`;
CREATE TABLE `moxingt` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `moXingName` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of moxingt
-- ----------------------------
INSERT INTO `moxingt` VALUES ('1', '文章模型');

-- ----------------------------
-- Table structure for quanxiant
-- ----------------------------
DROP TABLE IF EXISTS `quanxiant`;
CREATE TABLE `quanxiant` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleId` int(11) DEFAULT NULL,
  `QuanxianName` varchar(100) DEFAULT NULL,
  `binary` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of quanxiant
-- ----------------------------
INSERT INTO `quanxiant` VALUES ('1', null, '栏目增删改', '8');
INSERT INTO `quanxiant` VALUES ('2', null, '栏目查看', '4');
INSERT INTO `quanxiant` VALUES ('3', null, '文章增删改', '2');
INSERT INTO `quanxiant` VALUES ('4', null, '文章查看', '1');

-- ----------------------------
-- Table structure for rolet
-- ----------------------------
DROP TABLE IF EXISTS `rolet`;
CREATE TABLE `rolet` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `qian` varchar(30) DEFAULT NULL,
  `describeD` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rolet
-- ----------------------------
INSERT INTO `rolet` VALUES ('1', '超级管理员组', '15', '');
INSERT INTO `rolet` VALUES ('2', '管理员', '15', '');
INSERT INTO `rolet` VALUES ('3', '人力资源部', '15', '');
INSERT INTO `rolet` VALUES ('4', '党群部', '15', '');
INSERT INTO `rolet` VALUES ('5', '投融资部', '15', '');
INSERT INTO `rolet` VALUES ('6', '普通人', '1', '');

-- ----------------------------
-- Table structure for wenzhangt
-- ----------------------------
DROP TABLE IF EXISTS `wenzhangt`;
CREATE TABLE `wenzhangt` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(100) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `content` text,
  `dianJiShu` int(11) DEFAULT NULL,
  `faBuDtae` date DEFAULT NULL,
  `picture` varchar(100) DEFAULT NULL,
  `author` varchar(100) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `shou` bit(1) DEFAULT NULL,
  `zuo` bit(1) DEFAULT NULL,
  `tuiJian` bit(1) DEFAULT NULL,
  `zhiDing` bit(1) DEFAULT NULL,
  `lanMuID` int(11) DEFAULT NULL,
  `shenHeFou` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wenzhangt
-- ----------------------------
INSERT INTO `wenzhangt` VALUES ('26', null, 'w', 'we', '0', '2014-11-05', null, 'we', null, '', '', '\0', '', '8', '');

-- ----------------------------
-- Table structure for yonghulogt
-- ----------------------------
DROP TABLE IF EXISTS `yonghulogt`;
CREATE TABLE `yonghulogt` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `yongHuID` int(11) DEFAULT NULL,
  `loginIP` varchar(30) DEFAULT NULL,
  `loginTime` varchar(20) DEFAULT NULL,
  `lgoutTime` varchar(20) DEFAULT NULL,
  `dengChuFou` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=176 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of yonghulogt
-- ----------------------------
INSERT INTO `yonghulogt` VALUES ('157', '3', '117.170.52.149', '2014-11-02 21:05:36', '2014-11-02 21:05:55', '');
INSERT INTO `yonghulogt` VALUES ('158', '3', '117.170.52.149', '2014-11-02 21:05:59', '2014-11-02 21:19:09', '');
INSERT INTO `yonghulogt` VALUES ('159', '12', '117.170.52.149', '2014-11-02 21:06:33', null, '\0');
INSERT INTO `yonghulogt` VALUES ('160', '3', '117.170.52.149', '2014-11-02 21:19:02', '2014-11-02 21:48:02', '');
INSERT INTO `yonghulogt` VALUES ('161', '3', '117.170.52.149', '2014-11-02 21:19:03', '2014-11-03 18:08:18', '');
INSERT INTO `yonghulogt` VALUES ('162', '3', '117.170.52.149', '2014-11-02 21:19:03', null, '\0');
INSERT INTO `yonghulogt` VALUES ('163', '3', '117.170.52.149', '2014-11-02 21:19:03', null, '\0');
INSERT INTO `yonghulogt` VALUES ('164', '3', '117.170.52.149', '2014-11-02 21:19:03', null, '\0');
INSERT INTO `yonghulogt` VALUES ('165', '3', '117.170.52.149', '2014-11-02 21:19:03', null, '\0');
INSERT INTO `yonghulogt` VALUES ('166', '3', '117.170.57.120', '2014-11-02 21:46:48', null, '\0');
INSERT INTO `yonghulogt` VALUES ('167', '6', '117.170.57.120', '2014-11-02 21:49:29', '2014-11-03 17:53:16', '');
INSERT INTO `yonghulogt` VALUES ('168', '6', '117.170.57.120', '2014-11-02 21:49:37', null, '\0');
INSERT INTO `yonghulogt` VALUES ('169', '6', '192.168.1.103', '2014-11-03 17:52:18', null, '\0');
INSERT INTO `yonghulogt` VALUES ('170', '6', '192.168.1.103', '2014-11-03 17:53:22', null, '\0');
INSERT INTO `yonghulogt` VALUES ('171', '6', '192.168.1.103', '2014-11-03 17:58:03', null, '\0');
INSERT INTO `yonghulogt` VALUES ('172', '6', '192.168.1.103', '2014-11-03 18:00:26', null, '\0');
INSERT INTO `yonghulogt` VALUES ('173', '3', '192.168.1.103', '2014-11-03 18:07:58', null, '\0');
INSERT INTO `yonghulogt` VALUES ('174', '6', '192.168.1.103', '2014-11-03 18:08:27', null, '\0');
INSERT INTO `yonghulogt` VALUES ('175', '13', '192.168.1.103', '2014-11-03 18:11:09', '2014-11-03 18:11:16', '');

-- ----------------------------
-- Table structure for yonghut
-- ----------------------------
DROP TABLE IF EXISTS `yonghut`;
CREATE TABLE `yonghut` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `yongHuMing` varchar(30) DEFAULT NULL,
  `realName` varchar(30) DEFAULT NULL,
  `pwd` varchar(100) DEFAULT NULL,
  `teltPhone` varchar(30) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `createTime` date DEFAULT NULL,
  `bumenID` int(11) DEFAULT NULL,
  `roleID` int(11) DEFAULT NULL,
  `deLuCount` int(11) DEFAULT NULL,
  `zhuTai` bit(1) DEFAULT NULL,
  `duorendenglu` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of yonghut
-- ----------------------------
INSERT INTO `yonghut` VALUES ('2', '123', '123', '202cb962ac59075b964b07152d234b70', '123', '123', '2014-11-01', '1', '6', '33', '', '\0');
INSERT INTO `yonghut` VALUES ('3', '12', '12', 'c20ad4d76fe97759aa27a0c99bff6710', '12', '12', '2014-11-01', '1', '6', '49', '', '');
INSERT INTO `yonghut` VALUES ('6', '56', '56', '9f61408e3afb633e50cdf1b20de6f466', '56', '56', '2014-11-01', '1', '6', '40', '', '');
INSERT INTO `yonghut` VALUES ('13', 'admin', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '6800734', '421346928@qq.com', '2014-11-03', '1', '1', '1', '', null);
