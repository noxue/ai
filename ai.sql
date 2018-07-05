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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='机器人客户端表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app`
--

LOCK TABLES `app` WRITE;
/*!40000 ALTER TABLE `app` DISABLE KEYS */;
INSERT INTO `app` VALUES (1,'pwrziKSUAQnRyy01','阿士大夫撒111','阿斯顿发斯蒂芬沙发');
/*!40000 ALTER TABLE `app` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=195 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='登录注册登出记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_account_log`
--

LOCK TABLES `auth_account_log` WRITE;
/*!40000 ALTER TABLE `auth_account_log` DISABLE KEYS */;
INSERT INTO `auth_account_log` VALUES (17,'用户登录日志','tom','2018-04-22 13:22:39',1,NULL,'10.0.75.2'),(18,'用户登录日志','admin','2018-06-02 17:42:59',1,'登录成功','127.0.0.1'),(19,'用户登录日志','admin','2018-06-06 17:08:38',1,'登录成功','127.0.0.1'),(20,'用户登录日志','admin','2018-06-06 17:10:50',1,'登录成功','127.0.0.1'),(21,'用户登录日志','admin','2018-06-06 20:14:28',1,'登录成功','127.0.0.1'),(22,'用户登录日志','admin','2018-06-06 20:29:18',1,'登录成功','127.0.0.1'),(23,'用户登录日志','admin','2018-06-06 20:32:04',1,'登录成功','127.0.0.1'),(24,'用户登录日志','admin','2018-06-06 20:38:04',1,'登录成功','127.0.0.1'),(25,'用户登录日志','admin','2018-06-06 20:42:16',1,'登录成功','127.0.0.1'),(26,'用户登录日志','admin','2018-06-06 20:43:36',1,'登录成功','127.0.0.1'),(27,'用户登录日志','admin','2018-06-06 20:56:02',1,'登录成功','127.0.0.1'),(28,'用户登录日志','admin','2018-06-06 21:02:37',1,'登录成功','127.0.0.1'),(29,'用户登录日志','admin','2018-06-06 21:04:09',1,'登录成功','127.0.0.1'),(30,'用户登录日志','admin','2018-06-06 21:04:51',1,'登录成功','127.0.0.1'),(31,'用户登录日志','admin','2018-06-06 21:08:31',1,'登录成功','127.0.0.1'),(32,'用户登录日志','admin','2018-06-06 21:10:26',1,'登录成功','127.0.0.1'),(33,'用户登录日志','admin','2018-06-06 21:16:36',1,'登录成功','127.0.0.1'),(34,'用户登录日志','admin','2018-06-06 21:16:46',1,'登录成功','127.0.0.1'),(35,'用户登录日志','admin','2018-06-07 08:27:25',1,'登录成功','127.0.0.1'),(36,'用户登录日志','admin','2018-06-07 09:10:01',1,'登录成功','127.0.0.1'),(37,'用户登录日志','admin','2018-06-07 09:11:05',1,'登录成功','127.0.0.1'),(38,'用户登录日志','admin','2018-06-14 11:23:02',1,'登录成功','127.0.0.1'),(39,'用户登录日志','admin','2018-06-14 11:24:06',1,'登录成功','127.0.0.1'),(40,'用户登录日志','admin','2018-06-15 10:02:28',1,'登录成功','127.0.0.1'),(41,'用户登录日志','admin','2018-06-22 15:57:22',1,'登录成功','127.0.0.1'),(42,'用户登录日志','admin','2018-06-22 16:23:18',1,'登录成功','127.0.0.1'),(43,'用户登录日志','admin','2018-06-22 16:37:09',1,'登录成功','127.0.0.1'),(44,'用户登录日志','admin','2018-06-22 16:37:28',1,'登录成功','127.0.0.1'),(45,'用户登录日志','admin','2018-06-22 16:37:28',1,'登录成功','127.0.0.1'),(46,'用户登录日志','admin','2018-06-22 16:37:29',1,'登录成功','127.0.0.1'),(47,'用户登录日志','admin','2018-06-22 16:37:29',1,'登录成功','127.0.0.1'),(48,'用户登录日志','admin','2018-06-22 16:37:29',1,'登录成功','127.0.0.1'),(49,'用户登录日志','admin','2018-06-22 16:37:29',1,'登录成功','127.0.0.1'),(50,'用户登录日志','admin','2018-06-22 16:37:29',1,'登录成功','127.0.0.1'),(51,'用户登录日志','admin','2018-06-22 16:37:30',1,'登录成功','127.0.0.1'),(52,'用户登录日志','admin','2018-06-22 16:37:30',1,'登录成功','127.0.0.1'),(53,'用户登录日志','admin','2018-06-22 16:37:30',1,'登录成功','127.0.0.1'),(54,'用户登录日志','admin','2018-06-22 16:37:30',1,'登录成功','127.0.0.1'),(55,'用户登录日志','admin','2018-06-22 16:37:31',1,'登录成功','127.0.0.1'),(56,'用户登录日志','admin','2018-06-22 16:37:32',1,'登录成功','127.0.0.1'),(57,'用户登录日志','admin','2018-06-22 16:37:32',1,'登录成功','127.0.0.1'),(58,'用户登录日志','admin','2018-06-22 16:37:32',1,'登录成功','127.0.0.1'),(59,'用户登录日志','admin','2018-06-22 16:37:32',1,'登录成功','127.0.0.1'),(60,'用户登录日志','admin','2018-06-22 16:37:32',1,'登录成功','127.0.0.1'),(61,'用户登录日志','admin','2018-06-22 16:37:33',1,'登录成功','127.0.0.1'),(62,'用户登录日志','admin','2018-06-22 16:37:33',1,'登录成功','127.0.0.1'),(63,'用户登录日志','admin','2018-06-22 16:37:33',1,'登录成功','127.0.0.1'),(64,'用户登录日志','admin','2018-06-22 16:37:33',1,'登录成功','127.0.0.1'),(65,'用户登录日志','admin','2018-06-22 16:37:38',1,'登录成功','127.0.0.1'),(66,'用户登录日志','admin','2018-06-22 16:39:40',1,'登录成功','127.0.0.1'),(67,'用户登录日志','admin','2018-06-22 16:39:40',1,'登录成功','127.0.0.1'),(68,'用户登录日志','admin','2018-06-22 16:39:41',1,'登录成功','127.0.0.1'),(69,'用户登录日志','admin','2018-06-22 16:39:41',1,'登录成功','127.0.0.1'),(70,'用户登录日志','admin','2018-06-22 16:39:46',1,'登录成功','127.0.0.1'),(71,'用户登录日志','admin','2018-06-22 16:39:46',1,'登录成功','127.0.0.1'),(72,'用户登录日志','admin','2018-06-22 16:39:47',1,'登录成功','127.0.0.1'),(73,'用户登录日志','admin','2018-06-22 16:39:48',1,'登录成功','127.0.0.1'),(74,'用户登录日志','admin','2018-06-22 16:39:48',1,'登录成功','127.0.0.1'),(75,'用户登录日志','admin','2018-06-22 16:39:48',1,'登录成功','127.0.0.1'),(76,'用户登录日志','admin','2018-06-22 16:39:49',1,'登录成功','127.0.0.1'),(77,'用户登录日志','admin','2018-06-22 16:39:49',1,'登录成功','127.0.0.1'),(78,'用户登录日志','admin','2018-06-22 16:39:49',1,'登录成功','127.0.0.1'),(79,'用户登录日志','admin','2018-06-22 16:39:50',1,'登录成功','127.0.0.1'),(80,'用户登录日志','admin','2018-06-22 16:39:54',1,'登录成功','127.0.0.1'),(81,'用户登录日志','admin','2018-06-22 16:39:56',1,'登录成功','127.0.0.1'),(82,'用户登录日志','admin','2018-06-22 16:39:57',1,'登录成功','127.0.0.1'),(83,'用户登录日志','admin','2018-06-22 16:39:57',1,'登录成功','127.0.0.1'),(84,'用户登录日志','admin','2018-06-22 16:39:58',1,'登录成功','127.0.0.1'),(85,'用户登录日志','admin','2018-06-22 16:39:58',1,'登录成功','127.0.0.1'),(86,'用户登录日志','admin','2018-06-22 16:39:58',1,'登录成功','127.0.0.1'),(87,'用户登录日志','admin','2018-06-22 16:39:58',1,'登录成功','127.0.0.1'),(88,'用户登录日志','admin','2018-06-22 16:39:59',1,'登录成功','127.0.0.1'),(89,'用户登录日志','admin','2018-06-22 16:39:59',1,'登录成功','127.0.0.1'),(90,'用户登录日志','admin','2018-06-22 16:39:59',1,'登录成功','127.0.0.1'),(91,'用户登录日志','admin','2018-06-22 16:39:59',1,'登录成功','127.0.0.1'),(92,'用户登录日志','admin','2018-06-22 16:39:59',1,'登录成功','127.0.0.1'),(93,'用户登录日志','admin','2018-06-22 16:40:00',1,'登录成功','127.0.0.1'),(94,'用户登录日志','admin','2018-06-22 16:40:00',1,'登录成功','127.0.0.1'),(95,'用户登录日志','admin','2018-06-22 16:40:00',1,'登录成功','127.0.0.1'),(96,'用户登录日志','admin','2018-06-22 16:40:00',1,'登录成功','127.0.0.1'),(97,'用户登录日志','admin','2018-06-22 16:40:00',1,'登录成功','127.0.0.1'),(98,'用户登录日志','admin','2018-06-22 16:40:01',1,'登录成功','127.0.0.1'),(99,'用户登录日志','admin','2018-06-22 16:40:01',1,'登录成功','127.0.0.1'),(100,'用户登录日志','admin','2018-06-22 16:40:01',1,'登录成功','127.0.0.1'),(101,'用户登录日志','admin','2018-06-22 16:40:01',1,'登录成功','127.0.0.1'),(102,'用户登录日志','admin','2018-06-22 16:40:01',1,'登录成功','127.0.0.1'),(103,'用户登录日志','admin','2018-06-22 16:40:01',1,'登录成功','127.0.0.1'),(104,'用户登录日志','admin','2018-06-22 16:40:02',1,'登录成功','127.0.0.1'),(105,'用户登录日志','admin','2018-06-22 16:40:02',1,'登录成功','127.0.0.1'),(106,'用户登录日志','admin','2018-06-22 16:40:02',1,'登录成功','127.0.0.1'),(107,'用户登录日志','admin','2018-06-22 16:40:02',1,'登录成功','127.0.0.1'),(108,'用户登录日志','admin','2018-06-22 16:40:03',1,'登录成功','127.0.0.1'),(109,'用户登录日志','admin','2018-06-22 16:40:03',1,'登录成功','127.0.0.1'),(110,'用户登录日志','admin','2018-06-22 16:40:03',1,'登录成功','127.0.0.1'),(111,'用户登录日志','admin','2018-06-22 16:40:03',1,'登录成功','127.0.0.1'),(112,'用户登录日志','admin','2018-06-22 16:40:04',1,'登录成功','127.0.0.1'),(113,'用户登录日志','admin','2018-06-22 16:40:04',1,'登录成功','127.0.0.1'),(114,'用户登录日志','admin','2018-06-22 16:40:04',1,'登录成功','127.0.0.1'),(115,'用户登录日志','admin','2018-06-22 16:40:05',1,'登录成功','127.0.0.1'),(116,'用户登录日志','admin','2018-06-22 16:40:05',1,'登录成功','127.0.0.1'),(117,'用户登录日志','admin','2018-06-22 16:40:05',1,'登录成功','127.0.0.1'),(118,'用户登录日志','admin','2018-06-22 16:40:05',1,'登录成功','127.0.0.1'),(119,'用户登录日志','admin','2018-06-22 16:40:05',1,'登录成功','127.0.0.1'),(120,'用户登录日志','admin','2018-06-22 16:40:06',1,'登录成功','127.0.0.1'),(121,'用户登录日志','admin','2018-06-22 16:40:06',1,'登录成功','127.0.0.1'),(122,'用户登录日志','admin','2018-06-22 16:40:06',1,'登录成功','127.0.0.1'),(123,'用户登录日志','admin','2018-06-22 16:40:06',1,'登录成功','127.0.0.1'),(124,'用户登录日志','admin','2018-06-22 16:40:07',1,'登录成功','127.0.0.1'),(125,'用户登录日志','admin','2018-06-22 16:40:07',1,'登录成功','127.0.0.1'),(126,'用户登录日志','admin','2018-06-22 16:40:07',1,'登录成功','127.0.0.1'),(127,'用户登录日志','admin','2018-06-22 16:40:07',1,'登录成功','127.0.0.1'),(128,'用户登录日志','admin','2018-06-22 16:40:07',1,'登录成功','127.0.0.1'),(129,'用户登录日志','admin','2018-06-22 16:40:08',1,'登录成功','127.0.0.1'),(130,'用户登录日志','admin','2018-06-22 16:40:08',1,'登录成功','127.0.0.1'),(131,'用户登录日志','admin','2018-06-22 16:40:08',1,'登录成功','127.0.0.1'),(132,'用户登录日志','admin','2018-06-22 16:40:08',1,'登录成功','127.0.0.1'),(133,'用户登录日志','admin','2018-06-22 16:40:08',1,'登录成功','127.0.0.1'),(134,'用户登录日志','admin','2018-06-22 16:40:09',1,'登录成功','127.0.0.1'),(135,'用户登录日志','admin','2018-06-22 16:40:09',1,'登录成功','127.0.0.1'),(136,'用户登录日志','admin','2018-06-22 16:40:09',1,'登录成功','127.0.0.1'),(137,'用户登录日志','admin','2018-06-22 16:40:09',1,'登录成功','127.0.0.1'),(138,'用户登录日志','admin','2018-06-22 16:40:09',1,'登录成功','127.0.0.1'),(139,'用户登录日志','admin','2018-06-22 16:43:52',1,'登录成功','127.0.0.1'),(140,'用户登录日志','admin','2018-06-22 16:43:54',1,'登录成功','127.0.0.1'),(141,'用户登录日志','admin','2018-06-22 16:48:28',1,'登录成功','127.0.0.1'),(142,'用户登录日志','admin','2018-06-22 16:49:20',1,'登录成功','127.0.0.1'),(143,'用户登录日志','admin','2018-06-22 16:49:53',1,'登录成功','127.0.0.1'),(144,'用户登录日志','admin','2018-06-22 16:51:48',1,'登录成功','127.0.0.1'),(145,'用户登录日志','admin','2018-06-22 16:51:54',1,'登录成功','127.0.0.1'),(146,'用户登录日志','admin','2018-06-22 16:52:32',1,'登录成功','127.0.0.1'),(147,'用户登录日志','admin','2018-06-22 16:58:43',1,'登录成功','127.0.0.1'),(148,'用户登录日志','admin','2018-06-22 17:01:41',1,'登录成功','127.0.0.1'),(149,'用户登录日志','admin','2018-06-22 17:03:50',1,'登录成功','127.0.0.1'),(150,'用户登录日志','admin','2018-06-22 17:04:11',1,'登录成功','127.0.0.1'),(151,'用户登录日志','admin','2018-06-22 17:16:29',1,'登录成功','127.0.0.1'),(152,'用户登录日志','admin','2018-06-22 17:17:08',1,'登录成功','127.0.0.1'),(153,'用户登录日志','admin','2018-06-22 17:18:03',1,'登录成功','127.0.0.1'),(154,'用户登录日志','admin','2018-06-22 17:25:08',1,'登录成功','127.0.0.1'),(155,'用户登录日志','admin','2018-06-22 17:26:46',1,'登录成功','127.0.0.1'),(156,'用户登录日志','admin','2018-06-22 17:27:19',1,'登录成功','127.0.0.1'),(157,'用户登录日志','admin','2018-06-22 17:31:10',1,'登录成功','127.0.0.1'),(158,'用户登录日志','admin','2018-06-22 17:52:54',1,'登录成功','127.0.0.1'),(159,'用户登录日志','admin','2018-06-22 17:53:32',1,'登录成功','127.0.0.1'),(160,'用户登录日志','admin','2018-06-22 17:53:46',1,'登录成功','127.0.0.1'),(161,'用户登录日志','admin','2018-06-22 17:56:43',1,'登录成功','127.0.0.1'),(162,'用户登录日志','admin','2018-06-22 17:56:48',1,'登录成功','127.0.0.1'),(163,'用户登录日志','admin','2018-06-22 18:08:25',1,'登录成功','127.0.0.1'),(164,'用户登录日志','admin','2018-06-22 18:08:34',1,'登录成功','127.0.0.1'),(165,'用户登录日志','admin','2018-06-22 18:09:03',1,'登录成功','127.0.0.1'),(166,'用户登录日志','admin','2018-06-22 18:09:54',1,'登录成功','127.0.0.1'),(167,'用户登录日志','admin','2018-06-22 18:11:33',1,'登录成功','127.0.0.1'),(168,'用户登录日志','admin','2018-06-22 18:14:14',1,'登录成功','127.0.0.1'),(169,'用户登录日志','admin','2018-06-22 18:17:10',1,'登录成功','127.0.0.1'),(170,'用户登录日志','admin','2018-06-22 18:18:08',1,'登录成功','127.0.0.1'),(171,'用户登录日志','admin','2018-06-22 18:19:40',1,'登录成功','127.0.0.1'),(172,'用户登录日志','admin','2018-06-22 18:20:59',1,'登录成功','127.0.0.1'),(173,'用户登录日志','admin','2018-06-22 18:21:20',1,'登录成功','127.0.0.1'),(174,'用户登录日志','admin','2018-06-22 18:21:22',1,'登录成功','127.0.0.1'),(175,'用户登录日志','admin','2018-06-22 18:21:36',1,'登录成功','127.0.0.1'),(176,'用户登录日志','admin','2018-06-22 18:21:37',1,'登录成功','127.0.0.1'),(177,'用户登录日志','admin','2018-06-22 18:21:38',1,'登录成功','127.0.0.1'),(178,'用户登录日志','admin','2018-06-22 18:30:30',1,'登录成功','127.0.0.1'),(179,'用户登录日志','admin','2018-06-22 18:31:08',1,'登录成功','127.0.0.1'),(180,'用户登录日志','admin','2018-06-22 18:34:03',1,'登录成功','127.0.0.1'),(181,'用户登录日志','admin','2018-06-22 18:41:49',1,'登录成功','127.0.0.1'),(182,'用户登录日志','admin','2018-06-22 18:51:47',1,'登录成功','127.0.0.1'),(183,'用户登录日志','admin','2018-06-22 18:52:22',1,'登录成功','127.0.0.1'),(184,'用户登录日志','admin','2018-06-22 18:58:17',1,'登录成功','127.0.0.1'),(185,'用户登录日志','admin','2018-06-22 18:59:28',1,'登录成功','127.0.0.1'),(186,'用户登录日志','admin','2018-06-22 19:10:51',1,'登录成功','127.0.0.1'),(187,'用户登录日志','admin','2018-06-22 19:11:39',1,'登录成功','127.0.0.1'),(188,'用户登录日志','admin','2018-06-22 19:12:34',1,'登录成功','127.0.0.1'),(189,'用户登录日志','admin','2018-06-22 19:12:59',1,'登录成功','127.0.0.1'),(190,'用户登录日志','admin','2018-06-22 19:13:30',1,'登录成功','127.0.0.1'),(191,'用户登录日志','admin','2018-06-22 19:14:26',1,'登录成功','127.0.0.1'),(192,'用户登录日志','admin','2018-06-22 19:15:02',1,'登录成功','127.0.0.1'),(193,'用户登录日志','admin','2018-06-22 19:54:36',1,'登录成功','127.0.0.1'),(194,'用户登录日志','admin','2018-07-05 15:36:45',1,'登录成功','127.0.0.1');
/*!40000 ALTER TABLE `auth_account_log` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='操作日志';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_operation_log`
--

LOCK TABLES `auth_operation_log` WRITE;
/*!40000 ALTER TABLE `auth_operation_log` DISABLE KEYS */;
INSERT INTO `auth_operation_log` VALUES (17,'业务操作日志','tom','/resource/menus','GET','2018-04-22 16:05:05',1,NULL),(18,'业务操作日志','tom','/resource/menus','GET','2018-04-22 16:05:09',1,NULL),(19,'业务操作日志','tom','/resource/api/-1/1/10','GET','2018-04-22 16:08:15',1,NULL),(20,'业务操作日志',NULL,'/resource/authorityMenu','GET','2018-06-06 11:46:18',1,NULL);
/*!40000 ALTER TABLE `auth_operation_log` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=147 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='资源信息表(菜单,资源)';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_resource`
--

LOCK TABLES `auth_resource` WRITE;
/*!40000 ALTER TABLE `auth_resource` DISABLE KEYS */;
INSERT INTO `auth_resource` VALUES (101,'ACCOUNT_LOGIN','用户登录',103,'/account/login',2,'POST',NULL,1,NULL,NULL),(103,'GROUP_ACCOUNT','账户系列',110,'',3,'POST',NULL,1,NULL,NULL),(104,'USER_MAGE','用户管理',-1,'/index/user',1,'POST','fa fa-user',1,NULL,NULL),(106,'RESOURCE_MAGE','资源配置',-1,'',1,'POST','fa fa-pie-chart',1,NULL,NULL),(107,'MENU_MANAGE','菜单管理',106,'/index/menu',1,'POST','fa fa-th',1,NULL,NULL),(109,'API_MANAGE','API管理',106,'/index/api',1,NULL,'fa fa-share',1,'2018-04-07 09:40:24','2018-04-07 09:40:24'),(110,'CATEGORY_GROUP','分类集合(API类别请放入此集合)',-1,NULL,3,NULL,NULL,1,'2018-04-07 14:27:58','2018-04-07 14:27:58'),(112,'ACCOUNT_REGISTER','用户注册',103,'/account/register',2,'POST',NULL,1,'2018-04-07 16:21:45','2018-04-07 16:21:45'),(115,'GROUP_USER','用户系列',110,'',3,'GET',NULL,1,'2018-04-07 16:31:01','2018-04-07 16:31:01'),(117,'ROLE_MANAGE','角色管理',106,'/index/role',1,NULL,'fa fa-adjust',1,'2018-04-08 05:36:31','2018-04-08 05:36:31'),(118,'GROUP_RESOURCE','资源系列',110,NULL,3,NULL,NULL,1,'2018-04-09 02:29:14','2018-04-09 02:29:14'),(119,'USER_ROLE_APPID','获取对应用户角色',115,'/user/role/*/*/*',2,'GET',NULL,1,'2018-04-12 03:07:22','2018-04-12 03:07:22'),(120,'USER_LIST','获取用户列表',115,'/user/list',2,'GET',NULL,1,'2018-04-12 03:08:30','2018-04-12 03:08:30'),(121,'USER_AUTHORITY_ROLE','给用户授权添加角色',115,'/user/authority/role',2,'POST',NULL,1,'2018-04-12 03:15:56','2018-04-12 03:15:56'),(122,'USER_AUTHORITY_ROLE','删除已经授权的用户角色',115,'/user/authority/role',2,'DELETE',NULL,1,'2018-04-12 03:29:03','2018-04-12 03:29:03'),(123,'RESOURCE_AUTORITYMENU','获取用户被授权菜单',118,'/resource/authorityMenu',2,'GET',NULL,1,'2018-04-12 05:30:03','2018-04-12 05:30:03'),(124,'RESOURCE_MENUS','获取全部菜单列',118,'/resource/menus',2,'GET',NULL,1,'2018-04-12 05:42:46','2018-04-12 05:42:46'),(125,'RESOURCE_MENU','增加菜单',118,'/resource/menu',2,'POST',NULL,1,'2018-04-12 06:15:39','2018-04-12 06:15:39'),(126,'RESOURCE_MENU','修改菜单',118,'/resource/menu',2,'PUT',NULL,1,'2018-04-12 06:16:35','2018-04-12 06:16:35'),(127,'RESOURCE_MENU','删除菜单',118,'/resource/menu',2,'DELETE',NULL,1,'2018-04-12 06:17:18','2018-04-12 06:17:18'),(128,'RESOURCE_API','获取API list',118,'/resource/api/*/*/*',2,'GET',NULL,1,'2018-04-12 06:18:02','2018-04-12 06:18:02'),(129,'RESOURCE_API','增加API',118,'/resource/api',2,'POST',NULL,1,'2018-04-12 06:18:42','2018-04-12 06:18:42'),(130,'RESOURCE_API','修改API',118,'/resource/api',2,'PUT',NULL,1,'2018-04-12 06:19:32','2018-04-12 06:19:32'),(131,'RESOURCE_API','删除API',118,'/resource/api',2,'DELETE',NULL,1,'2018-04-12 06:20:03','2018-04-12 06:20:03'),(132,'GROUP_ROLE','角色系列',110,NULL,3,NULL,NULL,1,'2018-04-12 06:22:02','2018-04-12 06:22:02'),(133,'ROLE_USER','获取角色关联用户列表',132,'/role/user/*/*/*',2,'GET',NULL,1,'2018-04-12 06:22:59','2018-04-12 06:22:59'),(134,'ROLE_USER','获取角色未关联用户列表',132,'/role/user/-/*/*/*',2,'GET',NULL,1,'2018-04-12 06:24:09','2018-04-12 06:24:09'),(135,'ROLE_API','获取角色关联API资源',132,'/role/api/*/*/*',2,'GET',NULL,1,'2018-04-12 06:25:32','2018-04-12 06:25:32'),(136,'ROLE_API','获取角色未关联API资源',132,'/role/api/-/*/*/*',2,'GET',NULL,1,'2018-04-12 06:26:12','2018-04-12 06:26:12'),(137,'ROLE_MENU','获取角色关联菜单资源',132,'role/menu/*/*/*',2,'GET',NULL,1,'2018-04-12 06:27:20','2018-04-12 06:27:20'),(138,'ROLE_MENU','获取角色未关联菜单资源',132,'/role/menu/-/*/*/*',2,'GET',NULL,1,'2018-04-12 06:27:56','2018-04-12 06:27:56'),(139,'ROLE_AUTHORITY_RESOURCE','授权资源给角色',132,'/role/authority/resource',2,'POST',NULL,1,'2018-04-12 06:29:45','2018-04-12 06:29:45'),(140,'ROLE_AUTHORITY_RESOURCE','删除角色的授权资源',132,'/role/authority/resource',2,'DELETE',NULL,1,'2018-04-12 06:31:12','2018-04-12 06:31:12'),(141,'ROLE','获取角色LIST',132,'role/*/*',2,'GET',NULL,1,'2018-04-12 06:32:34','2018-04-12 06:32:34'),(142,'ROLE','添加角色',132,'role',2,'POST',NULL,1,'2018-04-12 06:33:25','2018-04-12 06:33:25'),(143,'USER','更新角色',132,'role',2,'PUT',NULL,1,'2018-04-12 06:34:27','2018-04-12 06:34:27'),(144,'ROLE','删除角色',132,'role',2,'DELETE',NULL,1,'2018-04-12 06:35:15','2018-04-12 06:35:15'),(145,'LOG_WATCH','日志记录',104,'/index/log',1,NULL,'fa fa-rss-square',1,'2018-04-22 08:12:24','2018-04-22 08:12:24'),(146,'asf_adf','刘荣飞',-1,'/index/user11',1,NULL,'fa fa-folder',1,'2018-06-06 20:37:19','2018-06-06 20:37:19');
/*!40000 ALTER TABLE `auth_resource` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_role`
--

LOCK TABLES `auth_role` WRITE;
/*!40000 ALTER TABLE `auth_role` DISABLE KEYS */;
INSERT INTO `auth_role` VALUES (100,'role_admin','管理员角色',1,'2018-06-06 20:41:55','2018-06-06 20:41:55'),(102,'role_agent','代理角色',1,'2018-06-06 20:41:55','2018-06-06 20:41:55'),(103,'role_company','企业角色',1,'2018-06-06 20:41:55','2018-06-06 20:41:55'),(104,'role_user','企业内部角色',1,'2018-06-06 20:41:55','2018-06-06 20:41:55');
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
) ENGINE=InnoDB AUTO_INCREMENT=119 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='资源角色关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_role_resource`
--

LOCK TABLES `auth_role_resource` WRITE;
/*!40000 ALTER TABLE `auth_role_resource` DISABLE KEYS */;
INSERT INTO `auth_role_resource` VALUES (10,103,105,'2018-04-01 12:50:19','2018-04-01 12:50:19'),(21,102,102,'2018-04-09 21:09:09','2018-04-09 21:09:09'),(23,103,101,'2018-04-09 21:51:39','2018-04-09 21:51:39'),(24,103,102,'2018-04-09 21:51:43','2018-04-09 21:51:43'),(25,103,103,'2018-04-09 21:51:46','2018-04-09 21:51:46'),(26,103,112,'2018-04-09 21:51:49','2018-04-09 21:51:49'),(27,101,112,'2018-04-09 22:21:02','2018-04-09 22:21:02'),(28,101,103,'2018-04-09 22:21:06','2018-04-09 22:21:06'),(29,100,102,'2018-04-09 22:25:09','2018-04-09 22:25:09'),(30,101,101,'2018-04-09 22:39:28','2018-04-09 22:39:28'),(31,103,100,'2018-04-09 22:45:00','2018-04-09 22:45:00'),(32,103,104,'2018-04-09 22:46:26','2018-04-09 22:46:26'),(33,103,106,'2018-04-09 22:46:28','2018-04-09 22:46:28'),(34,103,107,'2018-04-09 22:46:31','2018-04-09 22:46:31'),(35,103,109,'2018-04-09 22:46:34','2018-04-09 22:46:34'),(36,103,116,'2018-04-09 22:46:37','2018-04-09 22:46:37'),(37,103,117,'2018-04-09 22:46:43','2018-04-09 22:46:43'),(38,104,101,'2018-04-09 22:49:46','2018-04-09 22:49:46'),(39,104,102,'2018-04-09 22:49:52','2018-04-09 22:49:52'),(40,104,103,'2018-04-09 22:49:55','2018-04-09 22:49:55'),(41,100,103,'2018-04-09 22:51:56','2018-04-09 22:51:56'),(42,102,101,'2018-04-11 09:35:37','2018-04-11 09:35:37'),(43,103,123,'2018-04-20 09:08:52','2018-04-20 09:08:52'),(44,103,119,'2018-04-20 09:23:51','2018-04-20 09:23:51'),(45,103,129,'2018-04-20 09:24:21','2018-04-20 09:24:21'),(46,103,128,'2018-04-20 09:25:58','2018-04-20 09:25:58'),(47,102,104,'2018-04-20 23:26:00','2018-04-20 23:26:00'),(48,102,107,'2018-04-20 23:26:04','2018-04-20 23:26:04'),(49,102,117,'2018-04-20 23:26:07','2018-04-20 23:26:07'),(50,102,109,'2018-04-20 23:26:10','2018-04-20 23:26:10'),(51,102,106,'2018-04-20 23:26:13','2018-04-20 23:26:13'),(52,102,123,'2018-04-21 00:24:38','2018-04-21 00:24:38'),(54,102,128,'2018-04-21 09:27:08','2018-04-21 09:27:08'),(58,102,134,'2018-04-22 13:24:55','2018-04-22 13:24:55'),(59,102,135,'2018-04-22 13:25:00','2018-04-22 13:25:00'),(60,102,145,'2018-04-22 17:23:30','2018-04-22 17:23:30'),(83,100,124,'2018-04-25 16:05:11','2018-04-25 16:05:11'),(84,103,122,'2018-05-03 23:00:19','2018-05-03 23:00:19'),(85,100,120,'2018-05-03 23:02:14','2018-05-03 23:02:14'),(87,100,107,'2018-05-09 10:10:13','2018-05-09 10:10:13'),(88,102,112,'2018-05-09 10:10:40','2018-05-09 10:10:40'),(89,102,119,'2018-05-09 10:10:45','2018-05-09 10:10:45'),(90,102,120,'2018-05-09 10:10:50','2018-05-09 10:10:50'),(91,102,121,'2018-05-09 10:10:55','2018-05-09 10:10:55'),(92,102,122,'2018-05-09 10:11:00','2018-05-09 10:11:00'),(93,102,124,'2018-05-09 10:11:05','2018-05-09 10:11:05'),(94,102,125,'2018-05-09 10:11:10','2018-05-09 10:11:10'),(95,102,126,'2018-05-09 10:11:15','2018-05-09 10:11:15'),(96,102,127,'2018-05-09 10:11:19','2018-05-09 10:11:19'),(97,102,129,'2018-05-09 10:11:24','2018-05-09 10:11:24'),(98,102,130,'2018-05-09 10:11:29','2018-05-09 10:11:29'),(99,102,131,'2018-05-09 10:11:34','2018-05-09 10:11:34'),(100,102,133,'2018-05-09 10:11:39','2018-05-09 10:11:39'),(101,102,136,'2018-05-09 10:11:44','2018-05-09 10:11:44'),(102,102,137,'2018-05-09 10:11:49','2018-05-09 10:11:49'),(103,102,138,'2018-05-09 10:11:54','2018-05-09 10:11:54'),(104,102,139,'2018-05-09 10:11:59','2018-05-09 10:11:59'),(105,102,140,'2018-05-09 10:12:04','2018-05-09 10:12:04'),(106,102,141,'2018-05-09 10:12:08','2018-05-09 10:12:08'),(107,102,142,'2018-05-09 10:12:13','2018-05-09 10:12:13'),(108,102,143,'2018-05-09 10:12:17','2018-05-09 10:12:17'),(109,102,144,'2018-05-09 10:12:21','2018-05-09 10:12:21'),(110,100,106,'2018-06-06 20:27:14','2018-06-06 20:27:14'),(111,100,109,'2018-06-06 20:27:19','2018-06-06 20:27:19'),(112,100,117,'2018-06-06 20:27:22','2018-06-06 20:27:22'),(113,100,145,'2018-06-06 20:27:25','2018-06-06 20:27:25'),(114,100,101,'2018-06-06 20:28:19','2018-06-06 20:28:19'),(115,100,121,'2018-06-06 20:28:23','2018-06-06 20:28:23'),(116,100,125,'2018-06-06 20:28:27','2018-06-06 20:28:27'),(117,104,107,'2018-06-06 20:36:17','2018-06-06 20:36:17'),(118,100,104,'2018-06-06 21:14:33','2018-06-06 21:14:33');
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
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户角色关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_user_role`
--

LOCK TABLES `auth_user_role` WRITE;
/*!40000 ALTER TABLE `auth_user_role` DISABLE KEYS */;
INSERT INTO `auth_user_role` VALUES (16,'admin',100,'2018-06-06 18:44:21','2018-06-06 18:44:21'),(17,'admin',102,'2018-06-22 18:11:11','2018-06-22 18:11:11'),(18,'admin',103,'2018-06-22 18:11:11','2018-06-22 18:11:11'),(19,'admin',104,'2018-06-22 18:11:11','2018-06-22 18:11:11');
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
  `user_id` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `gateway_id_uindex` (`id`),
  UNIQUE KEY `gateway_name_uindex` (`name`),
  KEY `gateway_app_id_fk` (`app_id`),
  KEY `gateway_auth_user_uid_fk` (`user_id`),
  CONSTRAINT `gateway_app_id_fk` FOREIGN KEY (`app_id`) REFERENCES `app` (`id`) ON DELETE CASCADE,
  CONSTRAINT `gateway_auth_user_uid_fk` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`uid`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='网关信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gateway`
--

LOCK TABLES `gateway` WRITE;
/*!40000 ALTER TABLE `gateway` DISABLE KEYS */;
INSERT INTO `gateway` VALUES (1,'阿斯蒂芬安抚','123.123.123.123',2131,1,'','admin');
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
  KEY `sim_gateway_id_fk` (`gateway_id`),
  KEY `sim_user_id_fk` (`user_id`),
  CONSTRAINT `sim_auth_user_uid_fk` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`uid`) ON DELETE CASCADE,
  CONSTRAINT `sim_gateway_id_fk` FOREIGN KEY (`gateway_id`) REFERENCES `gateway` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='sim卡信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sim`
--

LOCK TABLES `sim` WRITE;
/*!40000 ALTER TABLE `sim` DISABLE KEYS */;
INSERT INTO `sim` VALUES (1,'asdfasd fsafsadfasf',1,'asdfasdfasf','admin');
/*!40000 ALTER TABLE `sim` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sim_user`
--

DROP TABLE IF EXISTS `sim_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sim_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(30) NOT NULL,
  `sim_id` bigint(20) NOT NULL COMMENT '表示user_id编号指定的用户可以使用的卡的编号',
  PRIMARY KEY (`id`),
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
  `name` varchar(100) NOT NULL,
  `thread` int(11) DEFAULT NULL COMMENT '任务并发数，即最多同时多少张卡执行任务',
  `total` int(11) NOT NULL COMMENT '该任务的电话号码总数',
  `called` int(11) DEFAULT '0' COMMENT '已拨打的电话数目，每次提交任务报告的时候更新这个字段',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '床建任务的时间',
  `start_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '任务开始的时间',
  `finish_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '任务结束的时间',
  `status` tinyint(4) DEFAULT '1' COMMENT '0 已结束。 1 未开始 2 已准备好等待开始  3 执行中  4 暂停中  5 执行失败，被客户端获取，但是客户端很久（半小时）没有更新拨打状态的情况',
  `test` tinyint(1) DEFAULT '0' COMMENT '记录是否是测试任务，用于实现单呼叫，单呼的优先级最高，优先拨打测试客户',
  PRIMARY KEY (`id`),
  UNIQUE KEY `task_id_uindex` (`id`),
  KEY `task_user_id_fk` (`user_id`),
  CONSTRAINT `task_auth_user_uid_fk` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`uid`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='任务表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task`
--

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
INSERT INTO `task` VALUES (1,'admin',1,'',1,111,0,'2018-07-03 18:15:25','2018-07-03 18:15:25','2018-07-03 18:15:25',1,0);
/*!40000 ALTER TABLE `task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task_user`
--

DROP TABLE IF EXISTS `task_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `task_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `task_id` bigint(20) NOT NULL COMMENT '任务编号',
  `content` text COMMENT '用于描述任务执行结果的json字符串',
  `mobile` char(15) NOT NULL COMMENT '要拨打的电话号码',
  `name` varchar(100) DEFAULT NULL COMMENT '客户姓名',
  `remark` varchar(255) DEFAULT NULL COMMENT '客户备注',
  `voice` varchar(100) DEFAULT NULL COMMENT '从头到尾整段录音的存放路径',
  `status` tinyint(4) DEFAULT '1' COMMENT '任务状态  0 通话完毕。 1 任务未执行。 2 任务被客户端获取。',
  `called_at` datetime NOT NULL COMMENT '给该客户拨打电话的时间',
  `time` int(11) DEFAULT '0' COMMENT '通话时长，单位（秒）',
  `type` tinyint(4) DEFAULT '0' COMMENT '什么类型的客户，具体根据模板中定义的分类索引来比较。',
  `share` tinyint(1) DEFAULT '0' COMMENT '是否公开该客户，默认不公开，只有自己和公司账号能看到',
  PRIMARY KEY (`id`),
  UNIQUE KEY `task_user_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务电话用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task_user`
--

LOCK TABLES `task_user` WRITE;
/*!40000 ALTER TABLE `task_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `task_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task_user_report`
--

DROP TABLE IF EXISTS `task_user_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `task_user_report` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `task_user_id` bigint(20) NOT NULL COMMENT '任务客户编号',
  `content` text NOT NULL COMMENT '每一个客户的执行记录，用json保存',
  PRIMARY KEY (`id`),
  UNIQUE KEY `task_user_report_id_uindex` (`id`),
  KEY `task_user_report_task_user_id_fk` (`task_user_id`),
  CONSTRAINT `task_user_report_task_user_id_fk` FOREIGN KEY (`task_user_id`) REFERENCES `task_user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task_user_report`
--

LOCK TABLES `task_user_report` WRITE;
/*!40000 ALTER TABLE `task_user_report` DISABLE KEYS */;
/*!40000 ALTER TABLE `task_user_report` ENABLE KEYS */;
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
-- Table structure for table `user_order`
--

DROP TABLE IF EXISTS `user_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(30) DEFAULT NULL,
  `used` int(11) DEFAULT '0' COMMENT '已使用量',
  `total` int(11) NOT NULL COMMENT '总量',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_order_id_uindex` (`id`),
  KEY `user_order_auth_user_uid_fk` (`user_id`),
  CONSTRAINT `user_order_auth_user_uid_fk` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`uid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='代理商购买订单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_order`
--

LOCK TABLES `user_order` WRITE;
/*!40000 ALTER TABLE `user_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_order` ENABLE KEYS */;
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
  `hash` varchar(50) NOT NULL COMMENT 'wav源文件的sha1摘要信息，用于记录文件是否改变，如果文件改变，才需要下载到客户端，这样可以节省宽带',
  `path` varchar(100) NOT NULL COMMENT '原始wav文件的路径',
  `pcm` varchar(100) DEFAULT NULL COMMENT 'pcm文件路径，客户录音不需要保存pcm，所以可以为null',
  `filename` varchar(100) NOT NULL COMMENT '上传之前的源文件名字',
  `visited_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '记录最后访问的时间，用于后期清理垃圾附件的凭证，超过一定日期，自动删除。',
  PRIMARY KEY (`hash`),
  UNIQUE KEY `voice_hash_uindex` (`hash`),
  UNIQUE KEY `voice_path_uindex` (`path`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='机器人音频文件，以及通话客户录音文件';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `voice`
--

LOCK TABLES `voice` WRITE;
/*!40000 ALTER TABLE `voice` DISABLE KEYS */;
INSERT INTO `voice` VALUES ('0256f64f0c86b9ef1bc114304d4e62be951ee34e','2018\\06\\25\\0256f64f0c86b9ef1bc114304d4e62be951ee34e.wav','2018\\06\\25\\0256f64f0c86b9ef1bc114304d4e62be951ee34e.pcm','6957a64e136f5cf0f3f5eebf9f77bba9ac32c821','2018-06-25 10:25:03'),('02c283d1dc879c939742911e97843a7def9944cf','2018\\06\\21\\02c283d1dc879c939742911e97843a7def9944cf.wav','2018\\06\\21\\02c283d1dc879c939742911e97843a7def9944cf.pcm','5.1.7.ai1',NULL),('06227df902d4350cadcffe0d17eeb499bd010ca4','2018\\06\\21\\06227df902d4350cadcffe0d17eeb499bd010ca4.wav','2018\\06\\21\\06227df902d4350cadcffe0d17eeb499bd010ca4.pcm','3.1.1.1',NULL),('099da22ff9785bca3047af97f5a1f2cdeb548088','2018\\06\\25\\099da22ff9785bca3047af97f5a1f2cdeb548088.png','2018\\06\\25\\099da22ff9785bca3047af97f5a1f2cdeb548088.pcm','QQ截图20180624173542','2018-06-25 11:47:20'),('09caade6139421700add2d7dd2579a399322cff7','2018\\06\\21\\09caade6139421700add2d7dd2579a399322cff7.wav','2018\\06\\21\\09caade6139421700add2d7dd2579a399322cff7.pcm','6.1.11.ai1',NULL),('0d1eba88d4d39bd20ea0ec35718492c6fb588de4','2018\\06\\25\\0d1eba88d4d39bd20ea0ec35718492c6fb588de4.png','2018\\06\\25\\0d1eba88d4d39bd20ea0ec35718492c6fb588de4.pcm','2','2018-06-25 10:25:02'),('0f94e8cf7008c889f39eb3164a708af5d55cac5c','2018\\06\\25\\0f94e8cf7008c889f39eb3164a708af5d55cac5c.pcm','2018\\06\\25\\0f94e8cf7008c889f39eb3164a708af5d55cac5c.pcm','6957a64e136f5cf0f3f5eebf9f77bba9ac32c821.wav','2018-06-25 10:57:11'),('189509ef54a2cf03c756f026a656f3d93ba6fcd0','2018\\06\\21\\189509ef54a2cf03c756f026a656f3d93ba6fcd0.wav','2018\\06\\21\\189509ef54a2cf03c756f026a656f3d93ba6fcd0.pcm','6.1.12.ai1',NULL),('18c758d94d26cd22d1db5308a515ef90f349112c','2018\\06\\25\\18c758d94d26cd22d1db5308a515ef90f349112c.jpg','2018\\06\\25\\18c758d94d26cd22d1db5308a515ef90f349112c.pcm','100528hejbwellm7urhe7j','2018-06-25 11:47:19'),('1e48184853d9dc8adbda5c083ee710a862857e05','2018\\06\\21\\1e48184853d9dc8adbda5c083ee710a862857e05.wav','2018\\06\\21\\1e48184853d9dc8adbda5c083ee710a862857e05.pcm','5.1.6.ai1',NULL),('24a32942724785f97b7a9baab15093ee5cbc5c47','2018\\06\\21\\24a32942724785f97b7a9baab15093ee5cbc5c47.wav','2018\\06\\21\\24a32942724785f97b7a9baab15093ee5cbc5c47.pcm','5.1.3.ai1',NULL),('26c31553335a1fd07f7776e2f0b36e942d762b1d','2018\\06\\21\\26c31553335a1fd07f7776e2f0b36e942d762b1d.wav','2018\\06\\21\\26c31553335a1fd07f7776e2f0b36e942d762b1d.pcm','3.2.0',NULL),('2a42a171a0da3fad9345da750e8adfcf42de8bb3','2018\\06\\25\\2a42a171a0da3fad9345da750e8adfcf42de8bb3.wav','2018\\06\\25\\2a42a171a0da3fad9345da750e8adfcf42de8bb3.pcm','11','2018-06-25 10:25:03'),('2e1c9d99e86680fddd0814ebd278d3132ff4625e','2018\\06\\25\\2e1c9d99e86680fddd0814ebd278d3132ff4625e.pcm','2018\\06\\25\\2e1c9d99e86680fddd0814ebd278d3132ff4625e.pcm','1','2018-06-25 11:47:17'),('31b9367fddd568b68e5cbf81aee8fa94388b0708','2018\\06\\21\\31b9367fddd568b68e5cbf81aee8fa94388b0708.wav','2018\\06\\21\\31b9367fddd568b68e5cbf81aee8fa94388b0708.pcm','5.3.2.ai1',NULL),('398999628876116b3eee7ebada46f72053195f19','2018\\06\\21\\398999628876116b3eee7ebada46f72053195f19.wav','2018\\06\\21\\398999628876116b3eee7ebada46f72053195f19.pcm','6.1.3.ai1',NULL),('3cef980ffc3124f2e35f060b025f75e554914895','2018\\06\\21\\3cef980ffc3124f2e35f060b025f75e554914895.wav','2018\\06\\21\\3cef980ffc3124f2e35f060b025f75e554914895.pcm','5.1.1.ai1',NULL),('3f65571f6b27b52370d4a99f47f33f40a0019a2f','2018\\06\\25\\3f65571f6b27b52370d4a99f47f33f40a0019a2f.pcm','2018\\06\\25\\3f65571f6b27b52370d4a99f47f33f40a0019a2f.pcm','3.0.1','2018-06-25 10:25:01'),('41152b9f5842ffc4bc06145b102274f3a4e71d4a','2018\\06\\21\\41152b9f5842ffc4bc06145b102274f3a4e71d4a.wav','2018\\06\\21\\41152b9f5842ffc4bc06145b102274f3a4e71d4a.pcm','3.4.2',NULL),('4520a9b77e95c6fe50eda684cb212fed6df12987','2018\\06\\25\\4520a9b77e95c6fe50eda684cb212fed6df12987.png','2018\\06\\25\\4520a9b77e95c6fe50eda684cb212fed6df12987.pcm','QQ截图20180624173659','2018-06-25 11:47:20'),('4986490ca8ad2bb18348219d7c7827d5c0a300a1','2018\\06\\21\\4986490ca8ad2bb18348219d7c7827d5c0a300a1.wav','2018\\06\\21\\4986490ca8ad2bb18348219d7c7827d5c0a300a1.pcm','6.1.7ai1',NULL),('4de50084658a0e9ea7600c1e90eb77badd92bc4b','2018\\06\\21\\4de50084658a0e9ea7600c1e90eb77badd92bc4b.wav','2018\\06\\21\\4de50084658a0e9ea7600c1e90eb77badd92bc4b.pcm','6.1.5.ai1',NULL),('4fb556a33bbe5f2bab9d0430f17fa6fd45da7ca9','2018\\06\\21\\4fb556a33bbe5f2bab9d0430f17fa6fd45da7ca9.wav','2018\\06\\21\\4fb556a33bbe5f2bab9d0430f17fa6fd45da7ca9.pcm','5.1.2.ai1',NULL),('51940df26dfdd5d8ab1744fb0280c588d12f0fc0','2018\\06\\21\\51940df26dfdd5d8ab1744fb0280c588d12f0fc0.wav','2018\\06\\21\\51940df26dfdd5d8ab1744fb0280c588d12f0fc0.pcm','5.1.5.ai1',NULL),('520d7b28d0df45cfe302768b58ec1e5ac471ea3f','2018\\06\\21\\520d7b28d0df45cfe302768b58ec1e5ac471ea3f.wav','2018\\06\\21\\520d7b28d0df45cfe302768b58ec1e5ac471ea3f.pcm','3.2.1',NULL),('5380f4f7e8ff3bc6f810ecb8766f85618f6b8884','2018\\06\\21\\5380f4f7e8ff3bc6f810ecb8766f85618f6b8884.wav','2018\\06\\21\\5380f4f7e8ff3bc6f810ecb8766f85618f6b8884.pcm','6.1.10.ai1',NULL),('5c9df906c28d99d8a0776cc4cbe100312877459e','2018\\06\\21\\5c9df906c28d99d8a0776cc4cbe100312877459e.wav','2018\\06\\21\\5c9df906c28d99d8a0776cc4cbe100312877459e.pcm','6.1.1.ai1',NULL),('5e973e44c1db39533b674fae0bca032239c4b42a','2018\\06\\21\\5e973e44c1db39533b674fae0bca032239c4b42a.wav','2018\\06\\21\\5e973e44c1db39533b674fae0bca032239c4b42a.pcm','5.1.9.ai1',NULL),('622dd290810bb00d60c87355ffe6096fad39594f','2018\\06\\21\\622dd290810bb00d60c87355ffe6096fad39594f.wav','2018\\06\\21\\622dd290810bb00d60c87355ffe6096fad39594f.pcm','4.2.1ai1',NULL),('6957a64e136f5cf0f3f5eebf9f77bba9ac32c821','2018\\06\\24\\6957a64e136f5cf0f3f5eebf9f77bba9ac32c821.wav','2018\\06\\24\\6957a64e136f5cf0f3f5eebf9f77bba9ac32c821.pcm','1','2018-06-24 16:53:12'),('6cc02657300a83c017291d448b7b291d7d981e38','2018\\06\\21\\6cc02657300a83c017291d448b7b291d7d981e38.wav','2018\\06\\21\\6cc02657300a83c017291d448b7b291d7d981e38.pcm','6.1.13.ai1',NULL),('77d47bc2c40f3d2a361b9438c78625163ae0c099','2018\\06\\21\\77d47bc2c40f3d2a361b9438c78625163ae0c099.wav','2018\\06\\21\\77d47bc2c40f3d2a361b9438c78625163ae0c099.pcm','5.2.3.ai1',NULL),('7bd0b288eba4e14a69f2526f2e728e2afb72457d','2018\\06\\21\\7bd0b288eba4e14a69f2526f2e728e2afb72457d.wav','2018\\06\\21\\7bd0b288eba4e14a69f2526f2e728e2afb72457d.pcm','5.2.1.ai1',NULL),('7c2347a0d64ad422f1b3ba5c4ea5af693ddcbf74','2018\\06\\21\\7c2347a0d64ad422f1b3ba5c4ea5af693ddcbf74.wav','2018\\06\\21\\7c2347a0d64ad422f1b3ba5c4ea5af693ddcbf74.pcm','4.2.1ai3',NULL),('7d69bcb24b320eccf6db7ee04d6d42e232c95d88','2018\\06\\21\\7d69bcb24b320eccf6db7ee04d6d42e232c95d88.wav','2018\\06\\21\\7d69bcb24b320eccf6db7ee04d6d42e232c95d88.pcm','6.1.9.ai1',NULL),('888467217cafdc7d7aecd2c1f561c906db095d72','2018\\06\\25\\888467217cafdc7d7aecd2c1f561c906db095d72.mp3','2018\\06\\25\\888467217cafdc7d7aecd2c1f561c906db095d72.pcm','1','2018-06-25 11:47:21'),('8bae753b2fe3dbb3708aa4c5dab3e4263346f54f','2018\\06\\24\\8bae753b2fe3dbb3708aa4c5dab3e4263346f54f.pcm','2018\\06\\24\\8bae753b2fe3dbb3708aa4c5dab3e4263346f54f.pcm','1','2018-06-24 17:01:56'),('909f14cc1b90436d7f82b54be750a83b99a2bcf6','2018\\06\\25\\909f14cc1b90436d7f82b54be750a83b99a2bcf6.png','2018\\06\\25\\909f14cc1b90436d7f82b54be750a83b99a2bcf6.pcm','1','2018-06-25 11:47:17'),('91bd64be49de5533b67565904de0f70b7f1c3004','2018\\06\\25\\91bd64be49de5533b67565904de0f70b7f1c3004.png','2018\\06\\25\\91bd64be49de5533b67565904de0f70b7f1c3004.pcm','QQ截图20180619094142','2018-06-25 11:47:20'),('92964dca9a064203ebe4888e852b37a6c9688fdb','2018\\06\\25\\92964dca9a064203ebe4888e852b37a6c9688fdb.m4a','2018\\06\\25\\92964dca9a064203ebe4888e852b37a6c9688fdb.pcm','123','2018-06-25 10:25:03'),('95d865839dbf794248f6f85171f3028cec62577c','2018\\06\\25\\95d865839dbf794248f6f85171f3028cec62577c.mp3','2018\\06\\25\\95d865839dbf794248f6f85171f3028cec62577c.pcm','故梦','2018-06-25 11:47:27'),('9a5bb73f1a250b304c83aac95c1877b08ed57660','2018\\06\\25\\9a5bb73f1a250b304c83aac95c1877b08ed57660.wav','2018\\06\\25\\9a5bb73f1a250b304c83aac95c1877b08ed57660.pcm','test(1)','2018-06-25 11:47:22'),('a1734be6246b9309902a552416f42f6db7423fa8','2018\\06\\25\\a1734be6246b9309902a552416f42f6db7423fa8.png','2018\\06\\25\\a1734be6246b9309902a552416f42f6db7423fa8.pcm','QQ截图20180624173639','2018-06-25 11:47:21'),('a83d0c1b32b0efeebf2fcb2156769157b618f13d','2018\\06\\21\\a83d0c1b32b0efeebf2fcb2156769157b618f13d.wav','2018\\06\\21\\a83d0c1b32b0efeebf2fcb2156769157b618f13d.pcm','6.1.4.ai1',NULL),('afb4b8c7cbabaafc18653dcc9bf1e56b956fbe5c','2018\\06\\21\\afb4b8c7cbabaafc18653dcc9bf1e56b956fbe5c.wav','2018\\06\\21\\afb4b8c7cbabaafc18653dcc9bf1e56b956fbe5c.pcm','5.2.2.ai1',NULL),('b04888054d18c21f7f27ab88adeae6bf289b4786','2018\\06\\21\\b04888054d18c21f7f27ab88adeae6bf289b4786.wav','2018\\06\\21\\b04888054d18c21f7f27ab88adeae6bf289b4786.pcm','5.1.8.ai1',NULL),('c04cdee1a86ad6e3f66135d7d59ae6688c573a8a','2018\\06\\21\\c04cdee1a86ad6e3f66135d7d59ae6688c573a8a.wav','2018\\06\\21\\c04cdee1a86ad6e3f66135d7d59ae6688c573a8a.pcm','3.3.1.ai1',NULL),('c1556f29dc49977e38d8311a30857550ee3d5655','2018\\06\\21\\c1556f29dc49977e38d8311a30857550ee3d5655.wav','2018\\06\\21\\c1556f29dc49977e38d8311a30857550ee3d5655.pcm','6.1.8.ai1',NULL),('c2fea2182648516b42446bfa29cbad920315c6b4','2018\\06\\21\\c2fea2182648516b42446bfa29cbad920315c6b4.wav','2018\\06\\21\\c2fea2182648516b42446bfa29cbad920315c6b4.pcm','4.2.1ai2',NULL),('ca71b2e676b836b30b0622a11f18e86587e688e1','2018\\06\\21\\ca71b2e676b836b30b0622a11f18e86587e688e1.wav','2018\\06\\21\\ca71b2e676b836b30b0622a11f18e86587e688e1.pcm','3.3.2.ai1',NULL),('cf34fe75dffec7282b54292bc8479dc76681b6c3','2018\\06\\21\\cf34fe75dffec7282b54292bc8479dc76681b6c3.wav','2018\\06\\21\\cf34fe75dffec7282b54292bc8479dc76681b6c3.pcm','6.1.2.ai1',NULL),('d02887a741b2d5f88e640b5408a458be4477a70a','2018\\06\\21\\d02887a741b2d5f88e640b5408a458be4477a70a.wav','2018\\06\\21\\d02887a741b2d5f88e640b5408a458be4477a70a.pcm','5.1.4.ai1',NULL),('d31fdca0d475bdf41d845d1812961b0b707527a1','2018\\06\\21\\d31fdca0d475bdf41d845d1812961b0b707527a1.wav','2018\\06\\21\\d31fdca0d475bdf41d845d1812961b0b707527a1.pcm','3.3.2.ai2',NULL),('d55851338caa81a52c63936029b1e5f630c8927a','2018\\06\\21\\d55851338caa81a52c63936029b1e5f630c8927a.wav','2018\\06\\21\\d55851338caa81a52c63936029b1e5f630c8927a.pcm','5.3.1ai1',NULL),('e1de83b76a118cad3f31b961bb4c2319018ca610','2018\\06\\25\\e1de83b76a118cad3f31b961bb4c2319018ca610.txt','2018\\06\\25\\e1de83b76a118cad3f31b961bb4c2319018ca610.pcm','1','2018-06-25 10:52:55'),('e47d80ec3f5c15594646f56a2f7135ba3c58916d','2018\\06\\21\\e47d80ec3f5c15594646f56a2f7135ba3c58916d.wav','2018\\06\\21\\e47d80ec3f5c15594646f56a2f7135ba3c58916d.pcm','6.1.6.ai1',NULL),('ed95ac3eec1b5768caa647029c90b0bfe3860745','2018\\06\\25\\ed95ac3eec1b5768caa647029c90b0bfe3860745.wav','2018\\06\\25\\ed95ac3eec1b5768caa647029c90b0bfe3860745.pcm','3.0.1111','2018-06-25 10:25:03'),('f617d1661f1738a59d1cc03733c85a0ad6e56e35','2018\\06\\21\\f617d1661f1738a59d1cc03733c85a0ad6e56e35.wav','2018\\06\\21\\f617d1661f1738a59d1cc03733c85a0ad6e56e35.pcm','3.4.1',NULL),('f618559afa62c72f65b96d43aa8fad93ac30c835','2018\\06\\21\\f618559afa62c72f65b96d43aa8fad93ac30c835.wav','2018\\06\\21\\f618559afa62c72f65b96d43aa8fad93ac30c835.pcm','3.1.1',NULL);
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

-- Dump completed on 2018-07-05 16:31:44
