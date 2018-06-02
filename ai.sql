-- MySQL dump 10.13  Distrib 5.7.22, for Win64 (x86_64)
--
-- Host: localhost    Database: ai
-- ------------------------------------------------------
-- Server version	5.7.22-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `app`
--

DROP TABLE IF EXISTS `app`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `app` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `key` char(32) NOT NULL,
  `name` varchar(50) NOT NULL COMMENT '机器人名字，比如：总部机器人1',
  `description` varchar(256) DEFAULT NULL COMMENT '机器人描述或备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `app_id_uindex` (`id`),
  UNIQUE KEY `app_name_uindex` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='机器人客户端表';
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `auth_account_log`
--

DROP TABLE IF EXISTS `auth_account_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_account_log` (
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户账户操作日志主键',
  `LOG_NAME` varchar(255) DEFAULT NULL COMMENT '日志名称(login,register,logout)',
  `USER_ID` varchar(30) DEFAULT NULL COMMENT '用户id',
  `CREATE_TIME` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `SUCCEED` tinyint(4) DEFAULT NULL COMMENT '是否执行成功(0失败1成功)',
  `MESSAGE` varchar(255) DEFAULT NULL COMMENT '具体消息',
  `IP` varchar(255) DEFAULT NULL COMMENT '登录ip',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='登录注册登出记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `auth_operation_log`
--

DROP TABLE IF EXISTS `auth_operation_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_operation_log` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户操作日志主键',
  `LOG_NAME` varchar(255) DEFAULT NULL COMMENT '日志名称',
  `USER_ID` varchar(30) DEFAULT NULL COMMENT '用户id',
  `API` varchar(255) DEFAULT NULL COMMENT 'api名称',
  `METHOD` varchar(255) DEFAULT NULL COMMENT '方法名称',
  `CREATE_TIME` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `SUCCEED` tinyint(4) DEFAULT NULL COMMENT '是否执行成功(0失败1成功)',
  `MESSAGE` varchar(255) DEFAULT NULL COMMENT '具体消息备注',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='操作日志';
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `auth_resource`
--

DROP TABLE IF EXISTS `auth_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_resource` (
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '资源ID',
  `CODE` varchar(30) DEFAULT NULL COMMENT '资源名称',
  `NAME` varchar(30) DEFAULT NULL COMMENT '资源描述',
  `PARENT_ID` int(11) DEFAULT NULL COMMENT '父资源编码->菜单',
  `URI` varchar(100) DEFAULT NULL COMMENT '访问地址URL',
  `TYPE` smallint(4) DEFAULT NULL COMMENT '类型 1:菜单menu 2:资源element(rest-api) 3:资源分类',
  `METHOD` varchar(10) DEFAULT NULL COMMENT '访问方式 GET POST PUT DELETE PATCH',
  `ICON` varchar(100) DEFAULT NULL COMMENT '图标',
  `STATUS` smallint(4) DEFAULT '1' COMMENT '状态   1:正常、9：禁用',
  `CREATE_TIME` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='资源信息表(菜单,资源)';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_resource`
--

--
-- Table structure for table `auth_role`
--

DROP TABLE IF EXISTS `auth_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_role` (
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `CODE` varchar(30) NOT NULL COMMENT '角色编码',
  `NAME` varchar(30) DEFAULT NULL COMMENT '角色名称',
  `STATUS` smallint(4) DEFAULT '1' COMMENT '状态   1:正常、9：禁用',
  `CREATE_TIME` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_role`
--

LOCK TABLES `auth_role` WRITE;
/*!40000 ALTER TABLE `auth_role` DISABLE KEYS */;
INSERT INTO `auth_role` VALUES (1,'role_admin','管理员角色',1,NULL,NULL),(2,'role_agent','代理角色',1,NULL,NULL),(3,'role_company','企业角色',1,NULL,NULL),(4,'role_user','企业内部角色',1,NULL,NULL);
/*!40000 ALTER TABLE `auth_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_role_resource`
--

DROP TABLE IF EXISTS `auth_role_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_role_resource` (
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `ROLE_ID` int(11) NOT NULL COMMENT '角色ID',
  `RESOURCE_ID` int(11) NOT NULL COMMENT '资源ID',
  `CREATE_TIME` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE KEY `ROLE_ID` (`ROLE_ID`,`RESOURCE_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='资源角色关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_role_resource`
--

LOCK TABLES `auth_role_resource` WRITE;
/*!40000 ALTER TABLE `auth_role_resource` DISABLE KEYS */;
INSERT INTO `auth_role_resource` VALUES (10,103,105,'2018-04-01 12:50:19','2018-04-01 12:50:19'),(21,102,102,'2018-04-09 21:09:09','2018-04-09 21:09:09'),(23,103,101,'2018-04-09 21:51:39','2018-04-09 21:51:39'),(24,103,102,'2018-04-09 21:51:43','2018-04-09 21:51:43'),(25,103,103,'2018-04-09 21:51:46','2018-04-09 21:51:46'),(26,103,112,'2018-04-09 21:51:49','2018-04-09 21:51:49'),(27,101,112,'2018-04-09 22:21:02','2018-04-09 22:21:02'),(28,101,103,'2018-04-09 22:21:06','2018-04-09 22:21:06'),(29,100,102,'2018-04-09 22:25:09','2018-04-09 22:25:09'),(30,101,101,'2018-04-09 22:39:28','2018-04-09 22:39:28'),(31,103,100,'2018-04-09 22:45:00','2018-04-09 22:45:00'),(32,103,104,'2018-04-09 22:46:26','2018-04-09 22:46:26'),(33,103,106,'2018-04-09 22:46:28','2018-04-09 22:46:28'),(34,103,107,'2018-04-09 22:46:31','2018-04-09 22:46:31'),(35,103,109,'2018-04-09 22:46:34','2018-04-09 22:46:34'),(36,103,116,'2018-04-09 22:46:37','2018-04-09 22:46:37'),(37,103,117,'2018-04-09 22:46:43','2018-04-09 22:46:43'),(38,104,101,'2018-04-09 22:49:46','2018-04-09 22:49:46'),(39,104,102,'2018-04-09 22:49:52','2018-04-09 22:49:52'),(40,104,103,'2018-04-09 22:49:55','2018-04-09 22:49:55'),(41,100,103,'2018-04-09 22:51:56','2018-04-09 22:51:56'),(42,102,101,'2018-04-11 09:35:37','2018-04-11 09:35:37'),(43,103,123,'2018-04-20 09:08:52','2018-04-20 09:08:52'),(44,103,119,'2018-04-20 09:23:51','2018-04-20 09:23:51'),(45,103,129,'2018-04-20 09:24:21','2018-04-20 09:24:21'),(46,103,128,'2018-04-20 09:25:58','2018-04-20 09:25:58'),(47,102,104,'2018-04-20 23:26:00','2018-04-20 23:26:00'),(48,102,107,'2018-04-20 23:26:04','2018-04-20 23:26:04'),(49,102,117,'2018-04-20 23:26:07','2018-04-20 23:26:07'),(50,102,109,'2018-04-20 23:26:10','2018-04-20 23:26:10'),(51,102,106,'2018-04-20 23:26:13','2018-04-20 23:26:13'),(52,102,123,'2018-04-21 00:24:38','2018-04-21 00:24:38'),(53,104,112,'2018-04-21 09:03:24','2018-04-21 09:03:24'),(54,102,128,'2018-04-21 09:27:08','2018-04-21 09:27:08'),(58,102,134,'2018-04-22 13:24:55','2018-04-22 13:24:55'),(59,102,135,'2018-04-22 13:25:00','2018-04-22 13:25:00'),(60,102,145,'2018-04-22 17:23:30','2018-04-22 17:23:30'),(83,100,124,'2018-04-25 16:05:11','2018-04-25 16:05:11'),(84,103,122,'2018-05-03 23:00:19','2018-05-03 23:00:19'),(85,100,120,'2018-05-03 23:02:14','2018-05-03 23:02:14'),(86,100,104,'2018-05-09 10:10:06','2018-05-09 10:10:06'),(87,100,107,'2018-05-09 10:10:13','2018-05-09 10:10:13'),(88,102,112,'2018-05-09 10:10:40','2018-05-09 10:10:40'),(89,102,119,'2018-05-09 10:10:45','2018-05-09 10:10:45'),(90,102,120,'2018-05-09 10:10:50','2018-05-09 10:10:50'),(91,102,121,'2018-05-09 10:10:55','2018-05-09 10:10:55'),(92,102,122,'2018-05-09 10:11:00','2018-05-09 10:11:00'),(93,102,124,'2018-05-09 10:11:05','2018-05-09 10:11:05'),(94,102,125,'2018-05-09 10:11:10','2018-05-09 10:11:10'),(95,102,126,'2018-05-09 10:11:15','2018-05-09 10:11:15'),(96,102,127,'2018-05-09 10:11:19','2018-05-09 10:11:19'),(97,102,129,'2018-05-09 10:11:24','2018-05-09 10:11:24'),(98,102,130,'2018-05-09 10:11:29','2018-05-09 10:11:29'),(99,102,131,'2018-05-09 10:11:34','2018-05-09 10:11:34'),(100,102,133,'2018-05-09 10:11:39','2018-05-09 10:11:39'),(101,102,136,'2018-05-09 10:11:44','2018-05-09 10:11:44'),(102,102,137,'2018-05-09 10:11:49','2018-05-09 10:11:49'),(103,102,138,'2018-05-09 10:11:54','2018-05-09 10:11:54'),(104,102,139,'2018-05-09 10:11:59','2018-05-09 10:11:59'),(105,102,140,'2018-05-09 10:12:04','2018-05-09 10:12:04'),(106,102,141,'2018-05-09 10:12:08','2018-05-09 10:12:08'),(107,102,142,'2018-05-09 10:12:13','2018-05-09 10:12:13'),(108,102,143,'2018-05-09 10:12:17','2018-05-09 10:12:17'),(109,102,144,'2018-05-09 10:12:21','2018-05-09 10:12:21');
/*!40000 ALTER TABLE `auth_role_resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_user`
--

DROP TABLE IF EXISTS `auth_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_user` (
  `uid` varchar(30) NOT NULL COMMENT 'uid,用户账号,主键',
  `username` varchar(30) NOT NULL COMMENT '用户名(nick_name)',
  `password` varchar(50) NOT NULL COMMENT '密码(MD5(密码+盐))',
  `salt` varchar(20) DEFAULT NULL COMMENT '盐',
  `real_name` varchar(30) DEFAULT NULL COMMENT '用户真名',
  `avatar` varchar(100) DEFAULT NULL COMMENT '头像',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话号码(唯一)',
  `email` varchar(50) DEFAULT NULL COMMENT '邮件地址(唯一)',
  `sex` tinyint(4) DEFAULT NULL COMMENT '性别(1.男 2.女)',
  `status` tinyint(4) DEFAULT NULL COMMENT '账户状态(1.正常 2.锁定 3.删除 4.非法)',
  `CREATE_TIME` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `CREATE_WHERE` tinyint(4) DEFAULT NULL COMMENT '创建来源(1.web 2.android 3.ios 4.win 5.macos 6.ubuntu)',
  `pid` varchar(30) DEFAULT NULL COMMENT '属于该账号属于某个账号',
  PRIMARY KEY (`uid`) USING BTREE,
  UNIQUE KEY `phone` (`phone`) USING BTREE,
  UNIQUE KEY `email` (`email`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_user`
--

LOCK TABLES `auth_user` WRITE;
/*!40000 ALTER TABLE `auth_user` DISABLE KEYS */;
INSERT INTO `auth_user` VALUES ('admin','管理员','F72375083F06C3C73D2DD525E54782A8','79sz6j',NULL,NULL,NULL,NULL,NULL,1,'2018-04-26 19:21:04','2018-04-26 11:21:04',NULL,NULL);
/*!40000 ALTER TABLE `auth_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_user_role`
--

DROP TABLE IF EXISTS `auth_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_user_role` (
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户角色关联表主键',
  `USER_ID` varchar(30) NOT NULL COMMENT '用户UID',
  `ROLE_ID` int(11) NOT NULL COMMENT '角色ID',
  `CREATE_TIME` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE KEY `USER_ID` (`USER_ID`,`ROLE_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户角色关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_user_role`
--

LOCK TABLES `auth_user_role` WRITE;
/*!40000 ALTER TABLE `auth_user_role` DISABLE KEYS */;
INSERT INTO `auth_user_role` VALUES (15,'282870345',103,'2018-04-09 22:44:47','2018-04-09 22:44:47');
/*!40000 ALTER TABLE `auth_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gateway`
--

DROP TABLE IF EXISTS `gateway`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gateway` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '网关名字，用于后台直观的区分不同的网关',
  `ip` varchar(40) NOT NULL COMMENT 'ip地址，考虑到以后可能用ipv6地址 所以设定40个长度\n此外ip地址是内网的，所以可能重复，不能唯一',
  `port` int(11) NOT NULL COMMENT '网关端口',
  `app_id` bigint(20) NOT NULL COMMENT '客户端编号，表示该网关属于哪个app管理。不允许一个网关多个app管理的情况',
  `description` varchar(512) DEFAULT NULL COMMENT '备注网关信息，方便后期管理。',
  PRIMARY KEY (`id`),
  UNIQUE KEY `gateway_id_uindex` (`id`),
  UNIQUE KEY `gateway_name_uindex` (`name`),
  KEY `gateway_app_id_fk` (`app_id`),
  CONSTRAINT `gateway_app_id_fk` FOREIGN KEY (`app_id`) REFERENCES `app` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='网关信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gateway`
--

LOCK TABLES `gateway` WRITE;
/*!40000 ALTER TABLE `gateway` DISABLE KEYS */;
/*!40000 ALTER TABLE `gateway` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gateway_report`
--

DROP TABLE IF EXISTS `gateway_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gateway_report` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `gateway_id` bigint(20) NOT NULL,
  `description` varchar(256) NOT NULL COMMENT '简单描述网关出错信息',
  `detail` text COMMENT '详细的错误信息，带上客户端报错信息，方便排查。',
  PRIMARY KEY (`id`),
  UNIQUE KEY `gateway_report_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='网关错误信息报告';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gateway_report`
--

LOCK TABLES `gateway_report` WRITE;
/*!40000 ALTER TABLE `gateway_report` DISABLE KEYS */;
/*!40000 ALTER TABLE `gateway_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sim`
--

DROP TABLE IF EXISTS `sim`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sim` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `number` varchar(20) NOT NULL COMMENT '手机号，包括座机号',
  `gateway_id` bigint(20) NOT NULL COMMENT '卡所属的网关的编号',
  `description` varchar(500) NOT NULL COMMENT '人工备注在第几个卡槽，方便以后管理',
  `user_id` varchar(30) NOT NULL COMMENT '属于哪个账号，限制只有属于该账号创建的账号才能使用该卡。',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sim_id_uindex` (`id`),
  UNIQUE KEY `sim_number_uindex` (`number`),
  KEY `sim_gateway_id_fk` (`gateway_id`),
  KEY `sim_user_id_fk` (`user_id`),
  CONSTRAINT `sim_auth_user_uid_fk` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`uid`) ON DELETE CASCADE,
  CONSTRAINT `sim_gateway_id_fk` FOREIGN KEY (`gateway_id`) REFERENCES `gateway` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='sim卡信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sim`
--

LOCK TABLES `sim` WRITE;
/*!40000 ALTER TABLE `sim` DISABLE KEYS */;
/*!40000 ALTER TABLE `sim` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sim_user`
--

DROP TABLE IF EXISTS `sim_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sim_user` (
  `user_id` varchar(30) NOT NULL,
  `sim_id` bigint(20) NOT NULL COMMENT '表示user_id编号指定的用户可以使用的卡的编号',
  KEY `sim_user_sim_id_fk` (`sim_id`),
  KEY `sim_user_user_id_fk` (`user_id`),
  CONSTRAINT `sim_user_auth_user_uid_fk` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`uid`) ON DELETE CASCADE,
  CONSTRAINT `sim_user_sim_id_fk` FOREIGN KEY (`sim_id`) REFERENCES `sim` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='sim卡使用记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sim_user`
--

LOCK TABLES `sim_user` WRITE;
/*!40000 ALTER TABLE `sim_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `sim_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `task` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(30) NOT NULL COMMENT '用户编号，表示任务是哪个用户的',
  `template_id` bigint(20) NOT NULL COMMENT '表示该任务使用哪个模板',
  `thread` int(11) DEFAULT NULL COMMENT '任务并发数，即最多同时多少张卡执行任务',
  `total` int(11) NOT NULL COMMENT '该任务的电话号码总数',
  `called` int(11) DEFAULT '0' COMMENT '已拨打的电话数目，每次提交任务报告的时候更新这个字段',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '床建任务的时间',
  `start_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '任务开始的时间',
  `finish_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '任务结束的时间',
  `status` tinyint(4) DEFAULT '1' COMMENT '0 已结束。 1 未开始  2 执行中  3 暂停中  4 执行失败，被客户端获取，但是客户端很久（半小时）没有更新拨打状态的情况',
  `test` tinyint(1) DEFAULT '0' COMMENT '记录是否是测试任务，用于实现单呼叫，单呼的优先级最高，优先拨打测试客户',
  PRIMARY KEY (`id`),
  UNIQUE KEY `task_id_uindex` (`id`),
  KEY `task_user_id_fk` (`user_id`),
  CONSTRAINT `task_auth_user_uid_fk` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`uid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task`
--

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
/*!40000 ALTER TABLE `task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task_list`
--

DROP TABLE IF EXISTS `task_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `task_list` (
  `id` bigint(20) DEFAULT NULL,
  `task_id` bigint(20) NOT NULL COMMENT '任务编号',
  `content` text COMMENT '用于描述任务执行结果的json字符串',
  `mobile` char(15) NOT NULL COMMENT '要拨打的电话号码',
  `voice` varchar(100) DEFAULT NULL COMMENT '从头到尾整段录音的存放路径',
  `status` tinyint(4) DEFAULT '1' COMMENT '任务状态  0 通话完毕。 1 任务未执行。 2 任务被客户端获取。',
  `time` int(11) DEFAULT '0' COMMENT '通话时长，单位（秒）',
  `type` tinyint(4) DEFAULT '0' COMMENT '什么类型的客户，具体根据模板中定义的分类索引来比较。',
  `share` tinyint(1) DEFAULT '0' COMMENT '是否公开该客户，默认不公开，只有自己和公司账号能看到'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务报告结果';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task_list`
--

LOCK TABLES `task_list` WRITE;
/*!40000 ALTER TABLE `task_list` DISABLE KEYS */;
/*!40000 ALTER TABLE `task_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `template`
--

DROP TABLE IF EXISTS `template`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `template` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '模板名称',
  `user_id` varchar(30) NOT NULL COMMENT '模板属于哪个用户',
  `content` text NOT NULL COMMENT '模板内容，一段json字符串',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '模板状态：0表示已上线，1表示制作中，2表示测试中，3表示审核中。',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `template_user_id_fk` (`user_id`),
  CONSTRAINT `template_auth_user_uid_fk` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`uid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='话术模板';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `template`
--

LOCK TABLES `template` WRITE;
/*!40000 ALTER TABLE `template` DISABLE KEYS */;
/*!40000 ALTER TABLE `template` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_schedule`
--

DROP TABLE IF EXISTS `user_schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_schedule` (
  `user_id` varchar(30) NOT NULL,
  `start_at` datetime NOT NULL COMMENT '开始工作的时间，包括开始时间',
  `end_at` datetime NOT NULL COMMENT '结束工作的时间，不包括结束时间',
  `repeat` tinyint(4) NOT NULL COMMENT '记录重复规则，通过7位2进制表示一周7天，从左到右依次为，周一到周日。如果对应的位为1，表示这一天是需要工作。',
  KEY `user_schedule_auth_user_uid_fk` (`user_id`),
  CONSTRAINT `user_schedule_auth_user_uid_fk` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`uid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工作时间设定，支持多段工作时间设定';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_schedule`
--

LOCK TABLES `user_schedule` WRITE;
/*!40000 ALTER TABLE `user_schedule` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `voice`
--

DROP TABLE IF EXISTS `voice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `voice` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `path` varchar(100) NOT NULL COMMENT '原始wav文件的路径',
  `pcm` varchar(100) DEFAULT NULL COMMENT 'pcm文件路径，客户录音不需要保存pcm，所以可以为null',
  PRIMARY KEY (`id`),
  UNIQUE KEY `voice_id_uindex` (`id`),
  UNIQUE KEY `voice_path_uindex` (`path`),
  UNIQUE KEY `voice_pcm_uindex` (`pcm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='机器人音频文件，以及通话客户录音文件';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `voice`
--

LOCK TABLES `voice` WRITE;
/*!40000 ALTER TABLE `voice` DISABLE KEYS */;
/*!40000 ALTER TABLE `voice` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-02 17:49:53
