/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50610
Source Host           : localhost:3306
Source Database       : niuxin

Target Server Type    : MYSQL
Target Server Version : 50610
File Encoding         : 65001

Date: 2015-11-23 19:47:44
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
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COMMENT='聊天记录表，记录消息的发送者，接受者（或者群组）';

-- ----------------------------
-- Records of chat_record
-- ----------------------------
INSERT INTO `chat_record` VALUES ('1', '25', '你好', '-1', '24', null, null, null, '2015-09-06 16:47:44');
INSERT INTO `chat_record` VALUES ('2', '25', '饿饿', '-1', '25', null, null, null, '2015-09-06 16:54:58');
INSERT INTO `chat_record` VALUES ('3', '22', '和和和', '-1', '5', null, null, null, '2015-09-06 17:21:41');
INSERT INTO `chat_record` VALUES ('4', '22', '个个怪怪', '-1', '1', null, null, null, '2015-09-06 18:08:38');
INSERT INTO `chat_record` VALUES ('5', '21', '在吗？', '22', '-1', null, null, null, '2015-09-06 21:54:45');
INSERT INTO `chat_record` VALUES ('6', '21', '你是？', '23', '-1', null, null, null, '2015-09-06 22:07:18');
INSERT INTO `chat_record` VALUES ('7', '21', '你好', '23', '-1', null, null, null, '2015-09-18 23:00:06');
INSERT INTO `chat_record` VALUES ('8', '21', '哈哈哈哈哈哈哈哈好啊', '22', '-1', null, null, null, '2015-09-19 09:37:13');
INSERT INTO `chat_record` VALUES ('9', '23', 'q', '22', '-1', null, null, null, '2015-09-20 22:33:02');
INSERT INTO `chat_record` VALUES ('10', '21', '你在哪里？？？', '-1', '1', null, null, null, '2015-09-21 16:24:45');
INSERT INTO `chat_record` VALUES ('11', '22', '你信', '-1', '2', null, null, null, '2015-09-22 10:47:11');
INSERT INTO `chat_record` VALUES ('12', '22', '刚刚', '-1', '1', null, null, null, '2015-09-22 11:11:03');
INSERT INTO `chat_record` VALUES ('13', '22', '你在吗？', '24', '-1', null, null, null, '2015-09-23 09:31:16');
INSERT INTO `chat_record` VALUES ('14', '21', 'hello', '23', '-1', null, null, null, '2015-09-23 15:56:27');
INSERT INTO `chat_record` VALUES ('15', '21', 'hello', '22', '-1', null, null, null, '2015-09-23 16:19:54');
INSERT INTO `chat_record` VALUES ('16', '21', 'test', '22', '-1', null, null, null, '2015-09-23 16:20:01');
INSERT INTO `chat_record` VALUES ('17', '21', '百大集团', '22', '-1', null, null, null, '2015-09-23 16:20:10');
INSERT INTO `chat_record` VALUES ('18', '21', '11', '-1', '1', null, null, null, '2015-09-23 16:51:06');
INSERT INTO `chat_record` VALUES ('19', '21', 'test', '22', '-1', null, null, null, '2015-09-23 16:51:16');
INSERT INTO `chat_record` VALUES ('20', '22', '和和和', '-1', '1', null, null, null, '2015-09-23 22:44:50');
INSERT INTO `chat_record` VALUES ('21', '22', '共和国', '-1', '1', null, null, null, '2015-09-23 22:44:53');
INSERT INTO `chat_record` VALUES ('22', '22', '和和', '-1', '1', null, null, null, '2015-09-23 22:44:57');
INSERT INTO `chat_record` VALUES ('23', '22', '和和和', '-1', '1', null, null, null, '2015-09-23 22:45:03');
INSERT INTO `chat_record` VALUES ('24', '22', '和和和', '-1', '1', null, null, null, '2015-09-24 22:22:20');

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
-- Table structure for `contract`
-- ----------------------------
DROP TABLE IF EXISTS `contract`;
CREATE TABLE `contract` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(1024) DEFAULT NULL COMMENT '合约名称',
  `type` varchar(1024) DEFAULT NULL,
  `exchange` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of contract
-- ----------------------------
INSERT INTO `contract` VALUES ('1', '沪铜', 'CU1511', '上交所');
INSERT INTO `contract` VALUES ('2', '沪铝', 'AL1511', '上交所');
INSERT INTO `contract` VALUES ('3', '沪锌', 'ZN1511', '上交所');
INSERT INTO `contract` VALUES ('4', '沪铅', 'PB1511', '上交所');
INSERT INTO `contract` VALUES ('5', '橡胶', 'RU1511', '上交所');
INSERT INTO `contract` VALUES ('6', '螺纹钢', 'RB1511', '上交所');
INSERT INTO `contract` VALUES ('7', '线材', 'WR1511', '上交所');
INSERT INTO `contract` VALUES ('8', '黄金', 'AU1511', '上交所');
INSERT INTO `contract` VALUES ('9', '白银', 'AG1511', '上交所');
INSERT INTO `contract` VALUES ('10', '沥青', 'BU1511', '上交所');
INSERT INTO `contract` VALUES ('11', '热卷', 'HC1511', '上交所');
INSERT INTO `contract` VALUES ('12', '沪镍', 'NI1511', '上交所');
INSERT INTO `contract` VALUES ('13', '黄豆一号\r\n黄豆一号\r\n黄豆一号', 'A1511', '大商所');
INSERT INTO `contract` VALUES ('14', '黄豆二号', 'B1511', '大商所');
INSERT INTO `contract` VALUES ('15', '豆粕', 'M1511', '大商所');
INSERT INTO `contract` VALUES ('16', '豆油', 'Y1511', '大商所');
INSERT INTO `contract` VALUES ('17', '棕榈油', 'P1511', '大商所');
INSERT INTO `contract` VALUES ('18', '玉米', 'C1511', '大商所');
INSERT INTO `contract` VALUES ('19', 'pvc', '1511', '大商所');
INSERT INTO `contract` VALUES ('20', '焦炭', 'J1511', '大商所');
INSERT INTO `contract` VALUES ('21', '焦煤', 'JM1511', '大商所');
INSERT INTO `contract` VALUES ('22', '铁矿石', 'I1511', '大商所');
INSERT INTO `contract` VALUES ('23', '鸡蛋', 'JD1511', '大商所');
INSERT INTO `contract` VALUES ('24', '纤维板', 'FB1511', '大商所');
INSERT INTO `contract` VALUES ('25', '胶合板', 'BB1511', '大商所');
INSERT INTO `contract` VALUES ('26', '玉米淀粉', 'CS1511', '大商所');
INSERT INTO `contract` VALUES ('27', '强麦', 'WH1511', '郑商所');
INSERT INTO `contract` VALUES ('28', '白糖', 'SR1511', '郑商所');
INSERT INTO `contract` VALUES ('29', '棉花', 'CF1511', '郑商所');
INSERT INTO `contract` VALUES ('30', 'PTA', 'TA1511', '郑商所');
INSERT INTO `contract` VALUES ('31', '菜油', 'OL1511', '郑商所');
INSERT INTO `contract` VALUES ('32', '早籼', 'RL1511', '郑商所');
INSERT INTO `contract` VALUES ('33', '甲醇', 'MA1511', '郑商所');
INSERT INTO `contract` VALUES ('34', '玻璃', 'FG1511', '郑商所');
INSERT INTO `contract` VALUES ('35', '菜粕', 'FG1511', '郑商所');
INSERT INTO `contract` VALUES ('36', '动力煤', 'TC1511', '郑商所');
INSERT INTO `contract` VALUES ('37', '沪深300指数', 'IF1511', '中金所');
INSERT INTO `contract` VALUES ('38', '中证500指数', 'IC1511', '中金所');
INSERT INTO `contract` VALUES ('39', '上证50指数', 'IH1511', '中金所');

-- ----------------------------
-- Table structure for `follow`
-- ----------------------------
DROP TABLE IF EXISTS `follow`;
CREATE TABLE `follow` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `follow_userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `follow_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of follow
-- ----------------------------

-- ----------------------------
-- Table structure for `form`
-- ----------------------------
DROP TABLE IF EXISTS `form`;
CREATE TABLE `form` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `contract` int(11) DEFAULT NULL COMMENT '合约类型',
  `operation` varchar(1024) DEFAULT NULL COMMENT '操作类型',
  `price` decimal(65,0) DEFAULT NULL COMMENT '价格',
  `handnum` int(11) DEFAULT NULL COMMENT '手数',
  `position` double(5,0) DEFAULT NULL COMMENT '仓位',
  `minnum` double(5,0) DEFAULT NULL COMMENT '止损范围最小值',
  `maxnum` double(5,0) DEFAULT NULL COMMENT '止损范围最大值',
  `remark` varchar(1024) DEFAULT NULL COMMENT '备注',
  `pictureurl` varchar(1024) DEFAULT NULL COMMENT '配图的路径',
  `audiourl` varchar(1024) DEFAULT NULL COMMENT '语音备注的url',
  `collection` int(11) DEFAULT '0' COMMENT '0代表没收藏，1代表收藏',
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  `audioread` tinyint(2) DEFAULT '0' COMMENT '是否听过语音 0 代表没有 1代表听过',
  `sendfrom` int(11) DEFAULT NULL COMMENT '谁发送的',
  `occupy` int(11) DEFAULT NULL COMMENT '备用的字段 暂时没有用',
  `name` varchar(1024) DEFAULT NULL,
  `sendto_group` varchar(1024) DEFAULT NULL,
  `sendto_user` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of form
-- ----------------------------
INSERT INTO `form` VALUES ('1', '1', '多开', '1', '1', '1', '1', '1', '1', '1', '1', '0', '2015-11-18 16:43:59', '2015-11-24 16:44:03', '0', '23', '1', '1', '1,2', '21,23');

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
  `img` varchar(1024) DEFAULT '0' COMMENT '群图标编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COMMENT='群组表';

-- ----------------------------
-- Records of share_group
-- ----------------------------
INSERT INTO `share_group` VALUES ('1', '合约', '大于等于2', '333', '免费', '50', '1', '2015-08-24', '豆粕期货群', '222', '21', 'head006.png');
INSERT INTO `share_group` VALUES ('2', '教学', '大于等于4', '这是一个群', '免费', '50', '1', '2015-08-29', '豆粕期货群', '123456', '21', 'head002.png');
INSERT INTO `share_group` VALUES ('5', '合约', '大于等于2', '这是一只好股', '免费', '50', '1', '2015-09-01', '豆粕期货群', '海螺水泥', '22', 'head003.png');
INSERT INTO `share_group` VALUES ('23', '合约', '大于等于2', '测试描述', '免费', '50', '1', '2015-09-03', '豆粕期货群', '中信证券', '24', 'head004.png');
INSERT INTO `share_group` VALUES ('24', '合约', '大于等于2', '的', '免费', '50', '1', '2015-09-06', '豆粕期货群', '中信证券', '25', 'head007.png');
INSERT INTO `share_group` VALUES ('25', '合约', '大于等于2', '1', '免费', '50', '1', '2015-09-06', '豆粕期货群', '啊', '25', 'head003.png');

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
-- Table structure for `shield`
-- ----------------------------
DROP TABLE IF EXISTS `shield`;
CREATE TABLE `shield` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `shield_userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shield
-- ----------------------------

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
  `sendto_user` int(11) DEFAULT NULL,
  `sendto_group` int(11) DEFAULT NULL,
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
  `img` varchar(1024) DEFAULT NULL,
  `ip` varchar(1024) DEFAULT NULL,
  `port` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='用户';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('21', '1', 'c4ca4238a0b923820dcc509a6f75849b', '1', '2015-08-20 19:31:25', null, 'qq@qq.com', '0', 'head006.png', null, '0');
INSERT INTO `user` VALUES ('22', '2', 'c81e728d9d4c2f636f067f89cc14862c', '1', '2015-08-25 15:57:25', null, '11', '0', 'head004.png', null, '0');
INSERT INTO `user` VALUES ('23', '3', 'eccbc87e4b5ce2fe28308fd9f2a7baf3', '1', '2015-08-29 19:15:01', null, '3', '0', 'head003.png', null, '0');
INSERT INTO `user` VALUES ('24', '4', 'a87ff679a2f3e71d9181a67b7542122c', '1', '2015-08-31 15:50:25', null, '4', '0', 'head007.png', null, '0');
INSERT INTO `user` VALUES ('25', 'Ragdoll', 'c4ca4238a0b923820dcc509a6f75849b', '1', '2015-09-02 09:55:19', null, 'tears1943', '0', 'head004.png', null, '0');
INSERT INTO `user` VALUES ('26', '本宫', 'f8b0eb1816ebff5033143405e61f656b', '1', '2015-09-22 10:18:32', null, '1393498885@qq.com', '0', 'head008.png', null, '0');

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
