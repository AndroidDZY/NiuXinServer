/*
Navicat MySQL Data Transfer

Source Server         : 115.28.242.139
Source Server Version : 50544
Source Host           : 115.28.242.139:3306
Source Database       : niuxin

Target Server Type    : MYSQL
Target Server Version : 50544
File Encoding         : 65001

Date: 2015-09-15 10:11:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `article`
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_user` int(11) DEFAULT NULL COMMENT '创建者',
  `content` text COMMENT '内容',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='热门文章';

-- ----------------------------
-- Records of article
-- ----------------------------

-- ----------------------------
-- Table structure for `chat_record`
-- ----------------------------
DROP TABLE IF EXISTS `chat_record`;
CREATE TABLE `chat_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sendUserId` int(11) NOT NULL COMMENT '发送消息的用户编号',
  `message` varchar(1024) DEFAULT NULL COMMENT '发送的消息',
  `receiveUserId` int(11) DEFAULT '-1' COMMENT '接受消息的用户',
  `receiveGroupId` int(11) DEFAULT '-1' COMMENT '接受消息的群组',
  `imageUrl` varchar(1024) DEFAULT NULL COMMENT '发送图片的url',
  `audioUrl` varchar(1024) DEFAULT NULL COMMENT '语音消息的url',
  `videoUrl` varchar(1024) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_7` (`sendUserId`),
  CONSTRAINT `FK_Reference_7` FOREIGN KEY (`sendUserId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='聊天记录表，记录消息的发送者，接受者（或者群组）';

-- ----------------------------
-- Records of chat_record
-- ----------------------------
INSERT INTO `chat_record` VALUES ('1', '25', '你好', '-1', '24', null, null, null, '2015-09-06 16:47:44');
INSERT INTO `chat_record` VALUES ('2', '25', '饿饿', '-1', '25', null, null, null, '2015-09-06 16:54:58');
INSERT INTO `chat_record` VALUES ('3', '22', '和和和', '-1', '5', null, null, null, '2015-09-06 17:21:41');
INSERT INTO `chat_record` VALUES ('4', '22', '个个怪怪', '-1', '1', null, null, null, '2015-09-06 18:08:38');
INSERT INTO `chat_record` VALUES ('5', '21', '在吗？', '22', '-1', null, null, null, '2015-09-06 21:54:45');
INSERT INTO `chat_record` VALUES ('6', '21', '你是？', '23', '-1', null, null, null, '2015-09-06 22:07:18');

-- ----------------------------
-- Table structure for `collection`
-- ----------------------------
DROP TABLE IF EXISTS `collection`;
CREATE TABLE `collection` (
  `id` int(11) NOT NULL,
  `collection_id` int(11) DEFAULT NULL COMMENT '收藏者的用户ID',
  `article_id` int(11) DEFAULT NULL COMMENT '收藏的文章',
  `create_time` datetime DEFAULT NULL,
  `label_id` int(11) DEFAULT NULL COMMENT '标签编号',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_10` (`collection_id`),
  KEY `FK_Reference_8` (`article_id`),
  CONSTRAINT `FK_Reference_10` FOREIGN KEY (`collection_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_Reference_8` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='收藏表';

-- ----------------------------
-- Records of collection
-- ----------------------------

-- ----------------------------
-- Table structure for `collection_form`
-- ----------------------------
DROP TABLE IF EXISTS `collection_form`;
CREATE TABLE `collection_form` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `contract` varchar(1024) DEFAULT NULL COMMENT '合约类型',
  `operation` varchar(1024) DEFAULT NULL COMMENT '操作类型',
  `price` decimal(65,0) DEFAULT NULL COMMENT '价格',
  `handnum` int(11) DEFAULT NULL COMMENT '手数',
  `position` double(5,0) DEFAULT NULL COMMENT '仓位',
  `minnum` double(5,0) DEFAULT NULL COMMENT '止损范围最小值',
  `maxnum` double(5,0) DEFAULT NULL COMMENT '止损范围最大值',
  `remark` varchar(1024) DEFAULT NULL COMMENT '备注',
  `pictureurl` varchar(1024) DEFAULT NULL COMMENT '配图的路径',
  `audiourl` varchar(1024) DEFAULT NULL COMMENT '语音备注的url',
  `sendto` int(11) DEFAULT NULL COMMENT '发送给谁（可能是个人 也可能是群组）',
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  `audioread` tinyint(2) DEFAULT '0' COMMENT '是否听过语音 0 代表没有 1代表听过',
  `sendfrom` int(11) DEFAULT NULL COMMENT '谁发送的',
  `occupy` int(11) DEFAULT NULL COMMENT '备用的字段 暂时没有作用',
  `name` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of collection_form
-- ----------------------------

-- ----------------------------
-- Table structure for `contract`
-- ----------------------------
DROP TABLE IF EXISTS `contract`;
CREATE TABLE `contract` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_userid` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `name` varchar(1024) DEFAULT NULL COMMENT '合约名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of contract
-- ----------------------------

-- ----------------------------
-- Table structure for `form`
-- ----------------------------
DROP TABLE IF EXISTS `form`;
CREATE TABLE `form` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `contract` varchar(1024) DEFAULT NULL COMMENT '合约类型',
  `operation` varchar(1024) DEFAULT NULL COMMENT '操作类型',
  `price` decimal(65,0) DEFAULT NULL COMMENT '价格',
  `handnum` int(11) DEFAULT NULL COMMENT '手数',
  `position` double(5,0) DEFAULT NULL COMMENT '仓位',
  `minnum` double(5,0) DEFAULT NULL COMMENT '止损范围最小值',
  `maxnum` double(5,0) DEFAULT NULL COMMENT '止损范围最大值',
  `remark` varchar(1024) DEFAULT NULL COMMENT '备注',
  `pictureurl` varchar(1024) DEFAULT NULL COMMENT '配图的路径',
  `audiourl` varchar(1024) DEFAULT NULL COMMENT '语音备注的url',
  `sendto` int(11) DEFAULT NULL COMMENT '发送给谁（可能是个人 也可能是群组）',
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  `audioread` tinyint(2) DEFAULT '0' COMMENT '是否听过语音 0 代表没有 1代表听过',
  `sendfrom` int(11) DEFAULT NULL COMMENT '谁发送的',
  `occupy` int(11) DEFAULT NULL COMMENT '备用的字段 暂时没有用',
  `name` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of form
-- ----------------------------

-- ----------------------------
-- Table structure for `form_from`
-- ----------------------------
DROP TABLE IF EXISTS `form_from`;
CREATE TABLE `form_from` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `receive_userid` int(11) DEFAULT '-1',
  `receive_groupid` int(11) DEFAULT '-1',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of form_from
-- ----------------------------

-- ----------------------------
-- Table structure for `form_sendto`
-- ----------------------------
DROP TABLE IF EXISTS `form_sendto`;
CREATE TABLE `form_sendto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT '-1',
  `groupid` int(11) DEFAULT '-1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of form_sendto
-- ----------------------------

-- ----------------------------
-- Table structure for `lab`
-- ----------------------------
DROP TABLE IF EXISTS `lab`;
CREATE TABLE `lab` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(1024) DEFAULT NULL COMMENT '标签名字',
  `create_time` datetime DEFAULT NULL,
  `create_id` int(11) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='标签';

-- ----------------------------
-- Records of lab
-- ----------------------------

-- ----------------------------
-- Table structure for `share`
-- ----------------------------
DROP TABLE IF EXISTS `share`;
CREATE TABLE `share` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(1024) DEFAULT NULL,
  `name` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of share
-- ----------------------------
INSERT INTO `share` VALUES ('1', '600585', '海螺水泥');
INSERT INTO `share` VALUES ('2', '601006', '大秦铁路');
INSERT INTO `share` VALUES ('3', '000825', '太钢不锈');
INSERT INTO `share` VALUES ('4', '399300', '沪深300');
INSERT INTO `share` VALUES ('5', '600008', '首创股份');
INSERT INTO `share` VALUES ('6', '600865', '百大集团');

-- ----------------------------
-- Table structure for `share_group`
-- ----------------------------
DROP TABLE IF EXISTS `share_group`;
CREATE TABLE `share_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '群组编号',
  `type` varchar(1024) NOT NULL COMMENT '群组类型',
  `enter_grade` varchar(256) NOT NULL COMMENT '入群等级',
  `description` varchar(1024) DEFAULT NULL COMMENT '群描述',
  `isfree` varchar(1024) DEFAULT NULL COMMENT '是否免费',
  `total_number` int(11) NOT NULL COMMENT '群总共可以拥有的人数',
  `current_number` int(11) NOT NULL,
  `create_time` date DEFAULT NULL COMMENT '创建群时间',
  `name` varchar(1024) DEFAULT NULL,
  `mark` varchar(1024) DEFAULT NULL,
  `createuserid` int(11) DEFAULT NULL,
  `img` int(11) DEFAULT '0' COMMENT '群图标编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COMMENT='群组表';

-- ----------------------------
-- Records of share_group
-- ----------------------------
INSERT INTO `share_group` VALUES ('1', '个股', '大于等于2', '333', '免费', '50', '1', '2015-08-24', '中信证券聊天组', '222', '21', '2130837509');
INSERT INTO `share_group` VALUES ('2', '教学', '大于等于4', '这是一个群', '免费', '50', '1', '2015-08-29', 'test', '123456', '21', '2130837509');
INSERT INTO `share_group` VALUES ('5', '个股', '大于等于2', '这是一只好股', '免费', '50', '1', '2015-09-01', '海螺水泥讨论组', '海螺水泥', '22', '2130837595');
INSERT INTO `share_group` VALUES ('23', '个股', '大于等于2', '测试描述', '免费', '50', '1', '2015-09-03', '测试群组', '中信证券', '24', '2130837593');
INSERT INTO `share_group` VALUES ('24', '个股', '大于等于2', '的', '免费', '50', '1', '2015-09-06', '被\n', '中信证券', '25', '2130837592');
INSERT INTO `share_group` VALUES ('25', '个股', '大于等于2', '1', '免费', '50', '1', '2015-09-06', '啦啦', '啊', '25', '2130837591');

-- ----------------------------
-- Table structure for `share_select`
-- ----------------------------
DROP TABLE IF EXISTS `share_select`;
CREATE TABLE `share_select` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `shareId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of share_select
-- ----------------------------
INSERT INTO `share_select` VALUES ('5', '21', '6');
INSERT INTO `share_select` VALUES ('11', '22', '1');
INSERT INTO `share_select` VALUES ('13', '22', '1');
INSERT INTO `share_select` VALUES ('16', '22', '3');
INSERT INTO `share_select` VALUES ('17', '22', '4');
INSERT INTO `share_select` VALUES ('32', '25', '1');
INSERT INTO `share_select` VALUES ('33', '25', '2');
INSERT INTO `share_select` VALUES ('34', '25', '3');
INSERT INTO `share_select` VALUES ('35', '25', '4');
INSERT INTO `share_select` VALUES ('36', '25', '5');
INSERT INTO `share_select` VALUES ('37', '25', '6');
INSERT INTO `share_select` VALUES ('38', '22', '5');
INSERT INTO `share_select` VALUES ('39', '22', '5');

-- ----------------------------
-- Table structure for `template`
-- ----------------------------
DROP TABLE IF EXISTS `template`;
CREATE TABLE `template` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `contract` varchar(1024) DEFAULT NULL COMMENT '合约类型',
  `operation` varchar(1024) DEFAULT NULL COMMENT '操作类型',
  `price` decimal(65,0) DEFAULT NULL COMMENT '价格',
  `handnum` int(11) DEFAULT NULL COMMENT '手数',
  `position` double(5,0) DEFAULT NULL COMMENT '仓位',
  `minnum` double(5,0) DEFAULT NULL COMMENT '止损范围最小值',
  `maxnum` double(5,0) DEFAULT NULL COMMENT '止损范围最大值',
  `remark` varchar(1024) DEFAULT NULL COMMENT '备注',
  `pictureurl` varchar(1024) DEFAULT NULL COMMENT '配图的路径',
  `audiourl` varchar(1024) DEFAULT NULL COMMENT '语音备注的url',
  `sendto` int(11) DEFAULT NULL COMMENT '发送给谁（可能是个人 也可能是群组）',
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  `audioread` tinyint(2) DEFAULT '0' COMMENT '是否听过语音 0 代表没有 1代表听过',
  `sendfrom` int(11) DEFAULT NULL COMMENT '谁发送的',
  `occupy` int(11) DEFAULT NULL COMMENT '备用的字段 暂时没有作用',
  `name` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of template
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `username` varchar(256) NOT NULL COMMENT '用户名',
  `password` varchar(256) NOT NULL COMMENT '密码',
  `status` tinyint(1) DEFAULT NULL COMMENT '0用户不再启用 1代表用户正在使用',
  `createtime` datetime DEFAULT NULL COMMENT '用户创建时间',
  `updatetime` datetime DEFAULT NULL COMMENT '用户修改时间',
  `email` varchar(1024) DEFAULT NULL,
  `isOnline` int(11) DEFAULT NULL,
  `img` int(11) DEFAULT NULL,
  `ip` varchar(1024) DEFAULT NULL,
  `port` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COMMENT='用户';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('21', '1', 'c4ca4238a0b923820dcc509a6f75849b', '1', '2015-08-20 19:31:25', null, 'qq@qq.com', '0', '2130837590', null, '0');
INSERT INTO `user` VALUES ('22', '2', 'c81e728d9d4c2f636f067f89cc14862c', '1', '2015-08-25 15:57:25', null, '11', '0', '2130837590', null, '0');
INSERT INTO `user` VALUES ('23', '3', 'eccbc87e4b5ce2fe28308fd9f2a7baf3', '1', '2015-08-29 19:15:01', null, '3', '0', '2130837590', null, '0');
INSERT INTO `user` VALUES ('24', '4', 'a87ff679a2f3e71d9181a67b7542122c', '1', '2015-08-31 15:50:25', null, '4', '0', '2130837590', null, '0');
INSERT INTO `user` VALUES ('25', 'Ragdoll', 'c4ca4238a0b923820dcc509a6f75849b', '1', '2015-09-02 09:55:19', null, 'tears1943', '0', '2130837593', null, '0');

-- ----------------------------
-- Table structure for `user_friend`
-- ----------------------------
DROP TABLE IF EXISTS `user_friend`;
CREATE TABLE `user_friend` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_self_id` int(11) NOT NULL COMMENT '用户自己的编号',
  `user_friend_id` int(11) NOT NULL COMMENT '用户好友的编号',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_4` (`user_self_id`),
  KEY `FK_Reference_5` (`user_friend_id`),
  CONSTRAINT `FK_Reference_4` FOREIGN KEY (`user_self_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_Reference_5` FOREIGN KEY (`user_friend_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='用户好友表，记录每一个用户的好友id';

-- ----------------------------
-- Records of user_friend
-- ----------------------------
INSERT INTO `user_friend` VALUES ('1', '21', '22');
INSERT INTO `user_friend` VALUES ('5', '21', '23');
INSERT INTO `user_friend` VALUES ('6', '21', '24');
INSERT INTO `user_friend` VALUES ('7', '24', '21');
INSERT INTO `user_friend` VALUES ('8', '24', '22');
INSERT INTO `user_friend` VALUES ('9', '22', '24');
INSERT INTO `user_friend` VALUES ('10', '23', '22');
INSERT INTO `user_friend` VALUES ('11', '23', '24');
INSERT INTO `user_friend` VALUES ('12', '21', '21');
INSERT INTO `user_friend` VALUES ('13', '24', '23');

-- ----------------------------
-- Table structure for `user_group`
-- ----------------------------
DROP TABLE IF EXISTS `user_group`;
CREATE TABLE `user_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `group_id` int(11) NOT NULL COMMENT '群组ID',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_2` (`user_id`),
  KEY `FK_Reference_3` (`group_id`),
  CONSTRAINT `FK_Reference_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`group_id`) REFERENCES `share_group` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COMMENT='用户和群组的中间表，记录一个用户属于的群组。';

-- ----------------------------
-- Records of user_group
-- ----------------------------
INSERT INTO `user_group` VALUES ('1', '21', '1');
INSERT INTO `user_group` VALUES ('2', '22', '1');
INSERT INTO `user_group` VALUES ('4', '21', '2');
INSERT INTO `user_group` VALUES ('6', '24', '2');
INSERT INTO `user_group` VALUES ('8', '22', '5');
INSERT INTO `user_group` VALUES ('10', '22', '2');
INSERT INTO `user_group` VALUES ('27', '24', '23');
INSERT INTO `user_group` VALUES ('28', '25', '24');
INSERT INTO `user_group` VALUES ('29', '25', '25');

-- ----------------------------
-- Table structure for `user_information`
-- ----------------------------
DROP TABLE IF EXISTS `user_information`;
CREATE TABLE `user_information` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户编号',
  `email` varchar(1024) DEFAULT NULL COMMENT '邮箱',
  `home_address` varchar(1024) DEFAULT NULL COMMENT '家庭地址',
  `work_address` varchar(1024) DEFAULT NULL COMMENT '单位地址',
  `credit_id` varchar(128) DEFAULT NULL COMMENT '身份证号码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_6` (`user_id`),
  CONSTRAINT `FK_Reference_6` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表，记录用户具体的个人信息。';

-- ----------------------------
-- Records of user_information
-- ----------------------------
