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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='机器人客户端表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app`
--

LOCK TABLES `app` WRITE;
/*!40000 ALTER TABLE `app` DISABLE KEYS */;
INSERT INTO `app` VALUES (1,'pwrziKSUAQnRyy01','阿士大夫撒111','阿斯顿发斯蒂芬沙发'),(2,'YclkZAe7DFQ7wOhv','测试','测试');
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
) ENGINE=InnoDB AUTO_INCREMENT=209 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='登录注册登出记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_account_log`
--

LOCK TABLES `auth_account_log` WRITE;
/*!40000 ALTER TABLE `auth_account_log` DISABLE KEYS */;
INSERT INTO `auth_account_log` VALUES (17,'用户登录日志','tom','2018-04-22 13:22:39',1,NULL,'10.0.75.2'),(18,'用户登录日志','admin','2018-06-02 17:42:59',1,'登录成功','127.0.0.1'),(19,'用户登录日志','admin','2018-06-06 17:08:38',1,'登录成功','127.0.0.1'),(20,'用户登录日志','admin','2018-06-06 17:10:50',1,'登录成功','127.0.0.1'),(21,'用户登录日志','admin','2018-06-06 20:14:28',1,'登录成功','127.0.0.1'),(22,'用户登录日志','admin','2018-06-06 20:29:18',1,'登录成功','127.0.0.1'),(23,'用户登录日志','admin','2018-06-06 20:32:04',1,'登录成功','127.0.0.1'),(24,'用户登录日志','admin','2018-06-06 20:38:04',1,'登录成功','127.0.0.1'),(25,'用户登录日志','admin','2018-06-06 20:42:16',1,'登录成功','127.0.0.1'),(26,'用户登录日志','admin','2018-06-06 20:43:36',1,'登录成功','127.0.0.1'),(27,'用户登录日志','admin','2018-06-06 20:56:02',1,'登录成功','127.0.0.1'),(28,'用户登录日志','admin','2018-06-06 21:02:37',1,'登录成功','127.0.0.1'),(29,'用户登录日志','admin','2018-06-06 21:04:09',1,'登录成功','127.0.0.1'),(30,'用户登录日志','admin','2018-06-06 21:04:51',1,'登录成功','127.0.0.1'),(31,'用户登录日志','admin','2018-06-06 21:08:31',1,'登录成功','127.0.0.1'),(32,'用户登录日志','admin','2018-06-06 21:10:26',1,'登录成功','127.0.0.1'),(33,'用户登录日志','admin','2018-06-06 21:16:36',1,'登录成功','127.0.0.1'),(34,'用户登录日志','admin','2018-06-06 21:16:46',1,'登录成功','127.0.0.1'),(35,'用户登录日志','admin','2018-06-07 08:27:25',1,'登录成功','127.0.0.1'),(36,'用户登录日志','admin','2018-06-07 09:10:01',1,'登录成功','127.0.0.1'),(37,'用户登录日志','admin','2018-06-07 09:11:05',1,'登录成功','127.0.0.1'),(38,'用户登录日志','admin','2018-06-14 11:23:02',1,'登录成功','127.0.0.1'),(39,'用户登录日志','admin','2018-06-14 11:24:06',1,'登录成功','127.0.0.1'),(40,'用户登录日志','admin','2018-06-15 10:02:28',1,'登录成功','127.0.0.1'),(41,'用户登录日志','admin','2018-06-22 15:57:22',1,'登录成功','127.0.0.1'),(42,'用户登录日志','admin','2018-06-22 16:23:18',1,'登录成功','127.0.0.1'),(43,'用户登录日志','admin','2018-06-22 16:37:09',1,'登录成功','127.0.0.1'),(44,'用户登录日志','admin','2018-06-22 16:37:28',1,'登录成功','127.0.0.1'),(45,'用户登录日志','admin','2018-06-22 16:37:28',1,'登录成功','127.0.0.1'),(46,'用户登录日志','admin','2018-06-22 16:37:29',1,'登录成功','127.0.0.1'),(47,'用户登录日志','admin','2018-06-22 16:37:29',1,'登录成功','127.0.0.1'),(48,'用户登录日志','admin','2018-06-22 16:37:29',1,'登录成功','127.0.0.1'),(49,'用户登录日志','admin','2018-06-22 16:37:29',1,'登录成功','127.0.0.1'),(50,'用户登录日志','admin','2018-06-22 16:37:29',1,'登录成功','127.0.0.1'),(51,'用户登录日志','admin','2018-06-22 16:37:30',1,'登录成功','127.0.0.1'),(52,'用户登录日志','admin','2018-06-22 16:37:30',1,'登录成功','127.0.0.1'),(53,'用户登录日志','admin','2018-06-22 16:37:30',1,'登录成功','127.0.0.1'),(54,'用户登录日志','admin','2018-06-22 16:37:30',1,'登录成功','127.0.0.1'),(55,'用户登录日志','admin','2018-06-22 16:37:31',1,'登录成功','127.0.0.1'),(56,'用户登录日志','admin','2018-06-22 16:37:32',1,'登录成功','127.0.0.1'),(57,'用户登录日志','admin','2018-06-22 16:37:32',1,'登录成功','127.0.0.1'),(58,'用户登录日志','admin','2018-06-22 16:37:32',1,'登录成功','127.0.0.1'),(59,'用户登录日志','admin','2018-06-22 16:37:32',1,'登录成功','127.0.0.1'),(60,'用户登录日志','admin','2018-06-22 16:37:32',1,'登录成功','127.0.0.1'),(61,'用户登录日志','admin','2018-06-22 16:37:33',1,'登录成功','127.0.0.1'),(62,'用户登录日志','admin','2018-06-22 16:37:33',1,'登录成功','127.0.0.1'),(63,'用户登录日志','admin','2018-06-22 16:37:33',1,'登录成功','127.0.0.1'),(64,'用户登录日志','admin','2018-06-22 16:37:33',1,'登录成功','127.0.0.1'),(65,'用户登录日志','admin','2018-06-22 16:37:38',1,'登录成功','127.0.0.1'),(66,'用户登录日志','admin','2018-06-22 16:39:40',1,'登录成功','127.0.0.1'),(67,'用户登录日志','admin','2018-06-22 16:39:40',1,'登录成功','127.0.0.1'),(68,'用户登录日志','admin','2018-06-22 16:39:41',1,'登录成功','127.0.0.1'),(69,'用户登录日志','admin','2018-06-22 16:39:41',1,'登录成功','127.0.0.1'),(70,'用户登录日志','admin','2018-06-22 16:39:46',1,'登录成功','127.0.0.1'),(71,'用户登录日志','admin','2018-06-22 16:39:46',1,'登录成功','127.0.0.1'),(72,'用户登录日志','admin','2018-06-22 16:39:47',1,'登录成功','127.0.0.1'),(73,'用户登录日志','admin','2018-06-22 16:39:48',1,'登录成功','127.0.0.1'),(74,'用户登录日志','admin','2018-06-22 16:39:48',1,'登录成功','127.0.0.1'),(75,'用户登录日志','admin','2018-06-22 16:39:48',1,'登录成功','127.0.0.1'),(76,'用户登录日志','admin','2018-06-22 16:39:49',1,'登录成功','127.0.0.1'),(77,'用户登录日志','admin','2018-06-22 16:39:49',1,'登录成功','127.0.0.1'),(78,'用户登录日志','admin','2018-06-22 16:39:49',1,'登录成功','127.0.0.1'),(79,'用户登录日志','admin','2018-06-22 16:39:50',1,'登录成功','127.0.0.1'),(80,'用户登录日志','admin','2018-06-22 16:39:54',1,'登录成功','127.0.0.1'),(81,'用户登录日志','admin','2018-06-22 16:39:56',1,'登录成功','127.0.0.1'),(82,'用户登录日志','admin','2018-06-22 16:39:57',1,'登录成功','127.0.0.1'),(83,'用户登录日志','admin','2018-06-22 16:39:57',1,'登录成功','127.0.0.1'),(84,'用户登录日志','admin','2018-06-22 16:39:58',1,'登录成功','127.0.0.1'),(85,'用户登录日志','admin','2018-06-22 16:39:58',1,'登录成功','127.0.0.1'),(86,'用户登录日志','admin','2018-06-22 16:39:58',1,'登录成功','127.0.0.1'),(87,'用户登录日志','admin','2018-06-22 16:39:58',1,'登录成功','127.0.0.1'),(88,'用户登录日志','admin','2018-06-22 16:39:59',1,'登录成功','127.0.0.1'),(89,'用户登录日志','admin','2018-06-22 16:39:59',1,'登录成功','127.0.0.1'),(90,'用户登录日志','admin','2018-06-22 16:39:59',1,'登录成功','127.0.0.1'),(91,'用户登录日志','admin','2018-06-22 16:39:59',1,'登录成功','127.0.0.1'),(92,'用户登录日志','admin','2018-06-22 16:39:59',1,'登录成功','127.0.0.1'),(93,'用户登录日志','admin','2018-06-22 16:40:00',1,'登录成功','127.0.0.1'),(94,'用户登录日志','admin','2018-06-22 16:40:00',1,'登录成功','127.0.0.1'),(95,'用户登录日志','admin','2018-06-22 16:40:00',1,'登录成功','127.0.0.1'),(96,'用户登录日志','admin','2018-06-22 16:40:00',1,'登录成功','127.0.0.1'),(97,'用户登录日志','admin','2018-06-22 16:40:00',1,'登录成功','127.0.0.1'),(98,'用户登录日志','admin','2018-06-22 16:40:01',1,'登录成功','127.0.0.1'),(99,'用户登录日志','admin','2018-06-22 16:40:01',1,'登录成功','127.0.0.1'),(100,'用户登录日志','admin','2018-06-22 16:40:01',1,'登录成功','127.0.0.1'),(101,'用户登录日志','admin','2018-06-22 16:40:01',1,'登录成功','127.0.0.1'),(102,'用户登录日志','admin','2018-06-22 16:40:01',1,'登录成功','127.0.0.1'),(103,'用户登录日志','admin','2018-06-22 16:40:01',1,'登录成功','127.0.0.1'),(104,'用户登录日志','admin','2018-06-22 16:40:02',1,'登录成功','127.0.0.1'),(105,'用户登录日志','admin','2018-06-22 16:40:02',1,'登录成功','127.0.0.1'),(106,'用户登录日志','admin','2018-06-22 16:40:02',1,'登录成功','127.0.0.1'),(107,'用户登录日志','admin','2018-06-22 16:40:02',1,'登录成功','127.0.0.1'),(108,'用户登录日志','admin','2018-06-22 16:40:03',1,'登录成功','127.0.0.1'),(109,'用户登录日志','admin','2018-06-22 16:40:03',1,'登录成功','127.0.0.1'),(110,'用户登录日志','admin','2018-06-22 16:40:03',1,'登录成功','127.0.0.1'),(111,'用户登录日志','admin','2018-06-22 16:40:03',1,'登录成功','127.0.0.1'),(112,'用户登录日志','admin','2018-06-22 16:40:04',1,'登录成功','127.0.0.1'),(113,'用户登录日志','admin','2018-06-22 16:40:04',1,'登录成功','127.0.0.1'),(114,'用户登录日志','admin','2018-06-22 16:40:04',1,'登录成功','127.0.0.1'),(115,'用户登录日志','admin','2018-06-22 16:40:05',1,'登录成功','127.0.0.1'),(116,'用户登录日志','admin','2018-06-22 16:40:05',1,'登录成功','127.0.0.1'),(117,'用户登录日志','admin','2018-06-22 16:40:05',1,'登录成功','127.0.0.1'),(118,'用户登录日志','admin','2018-06-22 16:40:05',1,'登录成功','127.0.0.1'),(119,'用户登录日志','admin','2018-06-22 16:40:05',1,'登录成功','127.0.0.1'),(120,'用户登录日志','admin','2018-06-22 16:40:06',1,'登录成功','127.0.0.1'),(121,'用户登录日志','admin','2018-06-22 16:40:06',1,'登录成功','127.0.0.1'),(122,'用户登录日志','admin','2018-06-22 16:40:06',1,'登录成功','127.0.0.1'),(123,'用户登录日志','admin','2018-06-22 16:40:06',1,'登录成功','127.0.0.1'),(124,'用户登录日志','admin','2018-06-22 16:40:07',1,'登录成功','127.0.0.1'),(125,'用户登录日志','admin','2018-06-22 16:40:07',1,'登录成功','127.0.0.1'),(126,'用户登录日志','admin','2018-06-22 16:40:07',1,'登录成功','127.0.0.1'),(127,'用户登录日志','admin','2018-06-22 16:40:07',1,'登录成功','127.0.0.1'),(128,'用户登录日志','admin','2018-06-22 16:40:07',1,'登录成功','127.0.0.1'),(129,'用户登录日志','admin','2018-06-22 16:40:08',1,'登录成功','127.0.0.1'),(130,'用户登录日志','admin','2018-06-22 16:40:08',1,'登录成功','127.0.0.1'),(131,'用户登录日志','admin','2018-06-22 16:40:08',1,'登录成功','127.0.0.1'),(132,'用户登录日志','admin','2018-06-22 16:40:08',1,'登录成功','127.0.0.1'),(133,'用户登录日志','admin','2018-06-22 16:40:08',1,'登录成功','127.0.0.1'),(134,'用户登录日志','admin','2018-06-22 16:40:09',1,'登录成功','127.0.0.1'),(135,'用户登录日志','admin','2018-06-22 16:40:09',1,'登录成功','127.0.0.1'),(136,'用户登录日志','admin','2018-06-22 16:40:09',1,'登录成功','127.0.0.1'),(137,'用户登录日志','admin','2018-06-22 16:40:09',1,'登录成功','127.0.0.1'),(138,'用户登录日志','admin','2018-06-22 16:40:09',1,'登录成功','127.0.0.1'),(139,'用户登录日志','admin','2018-06-22 16:43:52',1,'登录成功','127.0.0.1'),(140,'用户登录日志','admin','2018-06-22 16:43:54',1,'登录成功','127.0.0.1'),(141,'用户登录日志','admin','2018-06-22 16:48:28',1,'登录成功','127.0.0.1'),(142,'用户登录日志','admin','2018-06-22 16:49:20',1,'登录成功','127.0.0.1'),(143,'用户登录日志','admin','2018-06-22 16:49:53',1,'登录成功','127.0.0.1'),(144,'用户登录日志','admin','2018-06-22 16:51:48',1,'登录成功','127.0.0.1'),(145,'用户登录日志','admin','2018-06-22 16:51:54',1,'登录成功','127.0.0.1'),(146,'用户登录日志','admin','2018-06-22 16:52:32',1,'登录成功','127.0.0.1'),(147,'用户登录日志','admin','2018-06-22 16:58:43',1,'登录成功','127.0.0.1'),(148,'用户登录日志','admin','2018-06-22 17:01:41',1,'登录成功','127.0.0.1'),(149,'用户登录日志','admin','2018-06-22 17:03:50',1,'登录成功','127.0.0.1'),(150,'用户登录日志','admin','2018-06-22 17:04:11',1,'登录成功','127.0.0.1'),(151,'用户登录日志','admin','2018-06-22 17:16:29',1,'登录成功','127.0.0.1'),(152,'用户登录日志','admin','2018-06-22 17:17:08',1,'登录成功','127.0.0.1'),(153,'用户登录日志','admin','2018-06-22 17:18:03',1,'登录成功','127.0.0.1'),(154,'用户登录日志','admin','2018-06-22 17:25:08',1,'登录成功','127.0.0.1'),(155,'用户登录日志','admin','2018-06-22 17:26:46',1,'登录成功','127.0.0.1'),(156,'用户登录日志','admin','2018-06-22 17:27:19',1,'登录成功','127.0.0.1'),(157,'用户登录日志','admin','2018-06-22 17:31:10',1,'登录成功','127.0.0.1'),(158,'用户登录日志','admin','2018-06-22 17:52:54',1,'登录成功','127.0.0.1'),(159,'用户登录日志','admin','2018-06-22 17:53:32',1,'登录成功','127.0.0.1'),(160,'用户登录日志','admin','2018-06-22 17:53:46',1,'登录成功','127.0.0.1'),(161,'用户登录日志','admin','2018-06-22 17:56:43',1,'登录成功','127.0.0.1'),(162,'用户登录日志','admin','2018-06-22 17:56:48',1,'登录成功','127.0.0.1'),(163,'用户登录日志','admin','2018-06-22 18:08:25',1,'登录成功','127.0.0.1'),(164,'用户登录日志','admin','2018-06-22 18:08:34',1,'登录成功','127.0.0.1'),(165,'用户登录日志','admin','2018-06-22 18:09:03',1,'登录成功','127.0.0.1'),(166,'用户登录日志','admin','2018-06-22 18:09:54',1,'登录成功','127.0.0.1'),(167,'用户登录日志','admin','2018-06-22 18:11:33',1,'登录成功','127.0.0.1'),(168,'用户登录日志','admin','2018-06-22 18:14:14',1,'登录成功','127.0.0.1'),(169,'用户登录日志','admin','2018-06-22 18:17:10',1,'登录成功','127.0.0.1'),(170,'用户登录日志','admin','2018-06-22 18:18:08',1,'登录成功','127.0.0.1'),(171,'用户登录日志','admin','2018-06-22 18:19:40',1,'登录成功','127.0.0.1'),(172,'用户登录日志','admin','2018-06-22 18:20:59',1,'登录成功','127.0.0.1'),(173,'用户登录日志','admin','2018-06-22 18:21:20',1,'登录成功','127.0.0.1'),(174,'用户登录日志','admin','2018-06-22 18:21:22',1,'登录成功','127.0.0.1'),(175,'用户登录日志','admin','2018-06-22 18:21:36',1,'登录成功','127.0.0.1'),(176,'用户登录日志','admin','2018-06-22 18:21:37',1,'登录成功','127.0.0.1'),(177,'用户登录日志','admin','2018-06-22 18:21:38',1,'登录成功','127.0.0.1'),(178,'用户登录日志','admin','2018-06-22 18:30:30',1,'登录成功','127.0.0.1'),(179,'用户登录日志','admin','2018-06-22 18:31:08',1,'登录成功','127.0.0.1'),(180,'用户登录日志','admin','2018-06-22 18:34:03',1,'登录成功','127.0.0.1'),(181,'用户登录日志','admin','2018-06-22 18:41:49',1,'登录成功','127.0.0.1'),(182,'用户登录日志','admin','2018-06-22 18:51:47',1,'登录成功','127.0.0.1'),(183,'用户登录日志','admin','2018-06-22 18:52:22',1,'登录成功','127.0.0.1'),(184,'用户登录日志','admin','2018-06-22 18:58:17',1,'登录成功','127.0.0.1'),(185,'用户登录日志','admin','2018-06-22 18:59:28',1,'登录成功','127.0.0.1'),(186,'用户登录日志','admin','2018-06-22 19:10:51',1,'登录成功','127.0.0.1'),(187,'用户登录日志','admin','2018-06-22 19:11:39',1,'登录成功','127.0.0.1'),(188,'用户登录日志','admin','2018-06-22 19:12:34',1,'登录成功','127.0.0.1'),(189,'用户登录日志','admin','2018-06-22 19:12:59',1,'登录成功','127.0.0.1'),(190,'用户登录日志','admin','2018-06-22 19:13:30',1,'登录成功','127.0.0.1'),(191,'用户登录日志','admin','2018-06-22 19:14:26',1,'登录成功','127.0.0.1'),(192,'用户登录日志','admin','2018-06-22 19:15:02',1,'登录成功','127.0.0.1'),(193,'用户登录日志','admin','2018-06-22 19:54:36',1,'登录成功','127.0.0.1'),(194,'用户登录日志','admin','2018-07-05 15:36:45',1,'登录成功','127.0.0.1'),(195,'用户登录日志','admin','2018-07-06 11:06:56',1,'登录成功','127.0.0.1'),(196,'用户登录日志','admin','2018-07-06 11:09:50',1,'登录成功','192.168.3.195'),(197,'用户登录日志','admin','2018-07-06 11:28:20',1,'登录成功','192.168.3.135'),(198,'用户登录日志','admin','2018-07-06 11:28:20',1,'登录成功','192.168.3.135'),(199,'用户登录日志','admin','2018-07-06 11:33:37',1,'登录成功','192.168.3.192'),(200,'用户登录日志','admin','2018-07-08 18:20:26',1,'登录成功','192.168.3.236'),(201,'用户登录日志','admin','2018-07-09 17:15:38',1,'登录成功','192.168.3.236'),(202,'用户登录日志','admin','2018-07-10 19:07:02',1,'登录成功','0:0:0:0:0:0:0:1'),(203,'用户登录日志','admin','2018-07-10 19:45:48',1,'登录成功','192.168.3.236'),(204,'用户登录日志','admin','2018-07-10 19:56:50',1,'登录成功','192.168.3.195'),(205,'用户登录日志','admin','2018-07-10 19:56:50',1,'登录成功','192.168.3.195'),(206,'用户登录日志','admin','2018-07-10 20:26:16',1,'登录成功','192.168.3.195'),(207,'用户登录日志','admin','2018-07-10 20:33:13',1,'登录成功','192.168.3.152'),(208,'用户登录日志','admin','2018-07-10 22:21:31',1,'登录成功','192.168.3.236');
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
INSERT INTO `gateway` VALUES (1,'第二个网关','123.123.123.123',5060,1,'放在第二个房间第一个机柜，第一层左边第二个','admin');
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='sim卡信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sim`
--

LOCK TABLES `sim` WRITE;
/*!40000 ALTER TABLE `sim` DISABLE KEYS */;
INSERT INTO `sim` VALUES (1,'13777777777',1,'第三个卡槽','admin'),(2,'13888888888',1,'备注一些信息，比如给某个公司的，购买了多久等等','admin');
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='任务表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task`
--

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
INSERT INTO `task` VALUES (2,'admin',1,'江苏省第一批数据',NULL,0,NULL,'2018-07-09 12:40:15',NULL,NULL,1,0),(3,'admin',1,'江苏省第一批数据',NULL,0,NULL,'2018-07-09 12:40:34',NULL,NULL,1,0),(4,'admin',1,'江苏省第一批数据',NULL,0,NULL,'2018-07-09 12:40:35',NULL,NULL,1,0),(5,'admin',1,'江苏省第一批数据',NULL,0,NULL,'2018-07-09 12:40:35',NULL,NULL,1,0);
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='任务电话用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task_user`
--

LOCK TABLES `task_user` WRITE;
/*!40000 ALTER TABLE `task_user` DISABLE KEYS */;
INSERT INTO `task_user` VALUES (1,1,NULL,'13777777777','李四','来自xxx的客户',NULL,1,'2018-07-09 12:48:49',0,0,0),(2,1,NULL,'13888888888','张三','来自yyy的客户',NULL,1,'2018-07-09 12:48:49',0,0,0),(3,1,NULL,'13999999999','王五','来自zzz的客户',NULL,1,'2018-07-09 12:48:49',0,0,0),(4,1,NULL,'13777777777','李四','来自xxx的客户',NULL,1,'2018-07-09 12:49:40',0,0,0),(5,1,NULL,'13888888888','张三','来自yyy的客户',NULL,1,'2018-07-09 12:49:40',0,0,0),(6,1,NULL,'13999999999','王五','来自zzz的客户',NULL,1,'2018-07-09 12:49:40',0,0,0);
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='话术模板';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `template`
--

LOCK TABLES `template` WRITE;
/*!40000 ALTER TABLE `template` DISABLE KEYS */;
INSERT INTO `template` VALUES (1,'希高机器人模板','admin','{\"main\":\"JUU1JUJDJTgwJUU1JTlDJUJBJUU3JTk5JUJE\",\"flow\":{\"JUU1JUJDJTgwJUU1JTlDJUJBJUU3JTk5JUJE\":{\"section\":{\"type\":\"condition\",\"voice\":[\"535a12eff01cf6deb1176fd848362431f819efc7\"],\"choice\":\"535a12eff01cf6deb1176fd848362431f819efc7\",\"conds\":[{\"keyword\":[\"呦#25\",\"对#25\",\"ok#25\",\"yes#25\",\"要#25\",\"好#25\",\"行#25\",\"可以#25\",\"说#25\",\"你讲#25\",\"你说#25\",\"说吧#25\",\"讲吧#25\",\"好的#25\",\"你介绍#25\",\"介绍吧#25\",\"没问题#25\",\"请讲#25\",\"请说#25\",\"怎么卖#25\",\"怎么买#25\",\"了解下#25\",\"想了解#25\",\"不了解#25\",\"介绍下#25\",\"有兴趣#25\",\"感兴趣#25\",\"说说看#25\",\"好啊#25\",\"知道#25\",\"哦你#25\",\"哦你说#25\",\"做什么#25\",\"什么的#25\",\"说说#25\",\"嗯#25\",\"有#20\"],\"to\":\"JUU1JTg2JTg1JUU1JUFFJUI5JUU0JUJCJThCJUU3JUJCJThEJUU0JUI4JTgw\"},{\"keyword\":[\"不用#25\",\"不要#25\",\"不买#25\",\"没钱#25\",\"很穷#25\",\"没兴趣#25\",\"买不起#25\",\"不考虑#25\",\"没考虑#25\",\"不需要#25\",\"没需求#25\",\"不想要#25\",\"不感兴趣#25\",\"不想了解#25\",\"没有需要#25\",\"不用介绍#25\",\"已经买了#25\",\"已买#25\",\"没有钱#25\",\"不可以#25\",\"挂了#25\",\"年纪大#25\",\"先这样#25\",\"没有兴趣#25\",\"用不着#25\",\"用不上#25\",\"哦不需#25\",\"哦不需要#25\",\"笑挂了#25\",\"没有#30\",\"我没有#25\"],\"to\":\"JUU2JThDJUJEJUU1JTlCJTlFJUU4JUFGJUFEJUU1JThGJUE1\"}]},\"next\":\"JUU1JTg2JTg1JUU1JUFFJUI5JUU0JUJCJThCJUU3JUJCJThEJUU0JUI4JTgw\",\"hook\":false,\"type\":0},\"JUU1JTg2JTg1JUU1JUFFJUI5JUU0JUJCJThCJUU3JUJCJThEJUU0JUI4JTgw\":{\"section\":{\"type\":\"condition\",\"voice\":[\"843343ec0694f1c4c472a001968fc4c8d875f8fc\",\"c0a43eaf793db93ddab5e4c8697cc0bd9f084c31\"],\"choice\":\"random\",\"conds\":[{\"keyword\":[\"不用#25\",\"不要#25\",\"不买#25\",\"没钱#25\",\"很穷#25\",\"没兴趣#25\",\"买不起#25\",\"不考虑#25\",\"没考虑#25\",\"不需要#25\",\"没需求#25\",\"不想要#25\",\"不感兴趣#25\",\"不想了解#25\",\"没有需要#25\",\"不用介绍#25\",\"已经买了#25\",\"已买#25\",\"没有钱#25\",\"不可以#25\",\"挂了#25\",\"年纪大#25\",\"先这样#25\",\"没有兴趣#25\",\"用不着#25\",\"用不上#25\",\"哦不需#25\",\"哦不需要#25\",\"笑挂了#25\",\"谢谢啊#25\"],\"to\":\"\"}]},\"next\":\"\",\"hook\":true,\"type\":0},\"JUU2JThDJUJEJUU1JTlCJTlFJUU4JUFGJUFEJUU1JThGJUE1\":{\"section\":{\"type\":\"condition\",\"voice\":[\"2e225320aa67d3793bcf14edfc9a436547856eee\",\"608df84ba67e12c512fae5002ab9fad2f27714c7\",\"cfde722931c32e58bf739f12a1ddd293f26896b2\"],\"choice\":\"random\",\"conds\":[]},\"next\":\"\",\"hook\":true,\"type\":0},\"JUU5JTgyJTgwJUU3JUJBJUE2JUU4JUFGJUFEJUU1JThGJUE1\":{\"section\":{\"type\":\"condition\",\"voice\":[\"8dd0c34982fa9ea3487be35098517490c59b52ad\",\"d3222453fca753129ce40f9f68ddd945afb898f9\",\"e3bd741c4be852ff6d0932df1194bea862a184fc\"],\"choice\":\"random\",\"conds\":[{\"keyword\":[\"呦#25\",\"对#25\",\"ok#25\",\"yes#25\",\"要#25\",\"好#25\",\"行#25\",\"可以#25\",\"说#25\",\"你讲#25\",\"你说#25\",\"说吧#25\",\"讲吧#25\",\"好的#25\",\"你介绍#25\",\"介绍吧#25\",\"没问题#25\",\"请讲#25\",\"请说#25\",\"怎么卖#25\",\"怎么买#25\",\"了解下#25\",\"想了解#25\",\"不了解#25\",\"介绍下#25\",\"有兴趣#25\",\"感兴趣#25\",\"说说看#25\",\"好啊#25\",\"知道#25\",\"哦你#25\",\"哦你说#25\",\"做什么#25\",\"什么的#25\",\"哈#25\"],\"to\":\"JUU2JTg4JTkwJUU1JThBJTlGJUU3JUJCJTkzJUU2JTlEJTlG\"},{\"keyword\":[\"不用#25\",\"不要#25\",\"不买#25\",\"没钱#25\",\"很穷#25\",\"没兴趣#25\",\"买不起#25\",\"不考虑#25\",\"没考虑#25\",\"不需要#25\",\"没需求#25\",\"不想要#25\",\"不感兴趣#25\",\"不想了解#25\",\"没有需要#25\",\"不用介绍#25\",\"已经买了#25\",\"已买#25\",\"没有钱#25\",\"不可以#25\",\"挂了#25\",\"年纪大#25\",\"先这样#25\",\"没有兴趣#25\",\"用不着#25\",\"用不上#25\",\"哦不需#25\",\"哦不需要#25\",\"笑挂了#25\",\"我没有#25\"],\"to\":\"JUU1JUE0JUIxJUU4JUI0JUE1JUU3JUJCJTkzJUU2JTlEJTlG\"}]},\"next\":\"JUU2JTg4JTkwJUU1JThBJTlGJUU3JUJCJTkzJUU2JTlEJTlG\",\"hook\":true,\"type\":0},\"JUU2JTg4JTkwJUU1JThBJTlGJUU3JUJCJTkzJUU2JTlEJTlG\":{\"section\":{\"type\":\"end\",\"voice\":[\"7cb518e2e44425f4e091489a10722bea0ae4bcf9\"],\"choice\":\"7cb518e2e44425f4e091489a10722bea0ae4bcf9\"},\"next\":\"\",\"hook\":false,\"type\":0},\"JUU1JUE0JUIxJUU4JUI0JUE1JUU3JUJCJTkzJUU2JTlEJTlG\":{\"section\":{\"type\":\"end\",\"voice\":[\"c73a2c158cd59e1687dc6c56cfaa8879ff8363b2\"],\"choice\":\"random\"},\"next\":\"\",\"hook\":false,\"type\":0},\"JUU1JUJDJTgwJUU1JTlDJUJBJUU3JTk5JUJEJUU0JUJBJThD\":{\"section\":{\"type\":\"condition\",\"voice\":[\"2f8e41a86a111deabcd2b83277a70f8871f86c56\"],\"choice\":\"2f8e41a86a111deabcd2b83277a70f8871f86c56\",\"conds\":[{\"keyword\":[\"呦#25\",\"对#25\",\"ok#25\",\"yes#25\",\"要#25\",\"好#25\",\"行#25\",\"可以#25\",\"说#25\",\"你讲#25\",\"你说#25\",\"说吧#25\",\"讲吧#25\",\"好的#25\",\"你介绍#25\",\"介绍吧#25\",\"没问题#25\",\"请讲#25\",\"请说#25\",\"怎么卖#25\",\"怎么买#25\",\"了解下#25\",\"想了解#25\",\"不了解#25\",\"介绍下#25\",\"有兴趣#25\",\"感兴趣#25\",\"说说看#25\",\"好啊#25\",\"知道#25\",\"哦你#25\",\"哦你说#25\",\"做什么#25\",\"什么的#25\",\"嗯嗯#25\",\"嗯#25\",\"有#20\"],\"to\":\"JUU1JTg2JTg1JUU1JUFFJUI5JUU0JUJCJThCJUU3JUJCJThEJUU0JUI4JTgw\"},{\"keyword\":[\"不用#25\",\"不要#25\",\"不买#25\",\"没钱#25\",\"很穷#25\",\"没兴趣#25\",\"买不起#25\",\"不考虑#25\",\"没考虑#25\",\"不需要#25\",\"没需求#25\",\"不想要#25\",\"不感兴趣#25\",\"不想了解#25\",\"没有需要#25\",\"不用介绍#25\",\"已经买了#25\",\"已买#25\",\"没有钱#25\",\"不可以#25\",\"挂了#25\",\"年纪大#25\",\"先这样#25\",\"没有兴趣#25\",\"用不着#25\",\"用不上#25\",\"哦不需#25\",\"哦不需要#25\",\"笑挂了#25\",\"要不#20\"],\"to\":\"JUU2JThDJUJEJUU1JTlCJTlFJUU4JUFGJUFEJUU1JThGJUE1\"}]},\"next\":\"JUU1JTg2JTg1JUU1JUFFJUI5JUU0JUJCJThCJUU3JUJCJThEJUU0JUI4JTgw\",\"hook\":true,\"type\":0}},\"keyword\":{\"quiet1\":{\"type\":0,\"voice\":[\"5738a429e322cce6405ab55874b9265664a94797\"],\"choice\":\"random\",\"conds\":[],\"next\":\"next\"},\"quiet2\":{\"type\":0,\"voice\":[],\"choice\":\"random\",\"conds\":[],\"next\":\"JUU1JUE0JUIxJUU4JUI0JUE1JUU3JUJCJTkzJUU2JTlEJTlG\"},\"noword1\":{\"type\":0,\"voice\":[\"47c88fb61b1c17874ecbc13d5a065cdea4619bf1\"],\"choice\":\"random\",\"conds\":[],\"next\":\"\"},\"noword2\":{\"type\":0,\"voice\":[],\"choice\":\"random\",\"conds\":[],\"next\":\"\"},\"noword3\":{\"type\":0,\"voice\":[],\"choice\":\"random\",\"conds\":[],\"next\":\"JUU1JUE0JUIxJUU4JUI0JUE1JUU3JUJCJTkzJUU2JTlEJTlG\"},\"JUU0JUJEJThEJUU3JUJEJUFFJUU4JUFGJUFEJUU1JUEyJTgz\":{\"type\":0,\"keyword\":[\"哪个单位#25\",\"什么单位#25\",\"什么公司#25\",\"公司#25\"],\"voice\":[\"ed1a07ce20fca27d9ae674750a06ba7701343c40\"],\"choice\":\"random\",\"conds\":[{\"keyword\":[\"不用#25\",\"不要#25\",\"不买#25\",\"没钱#25\",\"很穷#25\",\"没兴趣#25\",\"买不起#25\",\"不考虑#25\",\"没考虑#25\",\"不需要#25\",\"没需求#25\",\"不想要#25\",\"不感兴趣#25\",\"不想了解#25\",\"没有需要#25\",\"不用介绍#25\",\"已经买了#25\",\"已买#25\",\"没有钱#25\",\"不可以#25\",\"挂了#25\",\"年纪大#25\",\"先这样#25\",\"没有兴趣#25\",\"用不着#25\",\"用不上#25\",\"哦不需#25\",\"哦不需要#25\",\"笑挂了#25\",\"还有事#25\",\"我有事#25\"],\"to\":\"JUU1JUE0JUIxJUU4JUI0JUE1JUU3JUJCJTkzJUU2JTlEJTlG\"}],\"next\":\"JUU5JTgyJTgwJUU3JUJBJUE2JUU4JUFGJUFEJUU1JThGJUE1\"},\"JUU0JUJEJThEJUU3JUJEJUFFJUU4JUFGJUFEJUU1JUEyJTgzJUU0JUJBJThD\":{\"type\":0,\"keyword\":[\"哪里#25\",\"那里#25\",\"哪呢#25\",\"在哪#25\",\"位置#25\",\"说一下#25\",\"地方#25\",\"地址#25\",\"地点#25\",\"什么路#25\",\"哪条路#25\",\"不清楚#25\",\"哪的#25\",\"清楚#25\",\"愿不愿#25\",\"远不远#25\",\"冤不冤#25\",\"哪个区#25\",\"哪个方向#25\"],\"voice\":[\"01be40dcdc49605656e20a1fdfdd09dc4f7f32ec\"],\"choice\":\"random\",\"conds\":[{\"keyword\":[\"不用#25\",\"不要#25\",\"不买#25\",\"没钱#25\",\"很穷#25\",\"没兴趣#25\",\"买不起#25\",\"不考虑#25\",\"没考虑#25\",\"不需要#25\",\"没需求#25\",\"不想要#25\",\"不感兴趣#25\",\"不想了解#25\",\"没有需要#25\",\"不用介绍#25\",\"已经买了#25\",\"已买#25\",\"没有钱#25\",\"不可以#25\",\"挂了#25\",\"年纪大#25\",\"先这样#25\",\"没有兴趣#25\",\"用不着#25\",\"用不上#25\",\"哦不需#25\",\"哦不需要#25\",\"笑挂了#25\"],\"to\":\"JUU1JUE0JUIxJUU4JUI0JUE1JUU3JUJCJTkzJUU2JTlEJTlG\"}],\"next\":\"JUU5JTgyJTgwJUU3JUJBJUE2JUU4JUFGJUFEJUU1JThGJUE1\"},\"JUU0JUJCJUI3JUU2JUEwJUJDJUU4JUFGJUFEJUU1JUEyJTgz\":{\"type\":0,\"keyword\":[\"太贵#25\",\"优惠#25\"],\"voice\":[\"8141dbcc261062a04b123a9ce5894dc8acb1fb0c\",\"688fe966d6ffe68ec58b8156f91e4505365c3ab6\"],\"choice\":\"random\",\"conds\":[{\"keyword\":[\"不用#25\",\"不要#25\",\"不买#25\",\"没钱#25\",\"很穷#25\",\"没兴趣#25\",\"买不起#25\",\"不考虑#25\",\"没考虑#25\",\"不需要#25\",\"没需求#25\",\"不想要#25\",\"不感兴趣#25\",\"不想了解#25\",\"没有需要#25\",\"不用介绍#25\",\"已经买了#25\",\"已买#25\",\"没有钱#25\",\"不可以#25\",\"挂了#25\",\"年纪大#25\",\"先这样#25\",\"没有兴趣#25\",\"用不着#25\",\"用不上#25\",\"哦不需#25\",\"哦不需要#25\",\"笑挂了#25\"],\"to\":\"JUU1JUE0JUIxJUU4JUI0JUE1JUU3JUJCJTkzJUU2JTlEJTlG\"}],\"next\":\"JUU5JTgyJTgwJUU3JUJBJUE2JUU4JUFGJUFEJUU1JThGJUE1\"},\"JUU4JUI0JUE4JUU3JTk2JTkxJUU4JUFGJUFEJUU1JThGJUE1\":{\"type\":0,\"keyword\":[\"骗子#25\",\"骗人的#25\"],\"voice\":[\"128da7f0807efe24e147ada0b0dbce376e12aef2\"],\"choice\":\"random\",\"conds\":[{\"keyword\":[\"不用#25\",\"不要#25\",\"不买#25\",\"没钱#25\",\"很穷#25\",\"没兴趣#25\",\"买不起#25\",\"不考虑#25\",\"没考虑#25\",\"不需要#25\",\"没需求#25\",\"不想要#25\",\"不感兴趣#25\",\"不想了解#25\",\"没有需要#25\",\"不用介绍#25\",\"已经买了#25\",\"已买#25\",\"没有钱#25\",\"不可以#25\",\"挂了#25\",\"年纪大#25\",\"先这样#25\",\"没有兴趣#25\",\"用不着#25\",\"用不上#25\",\"哦不需#25\",\"哦不需要#25\",\"笑挂了#25\"],\"to\":\"JUU1JUE0JUIxJUU4JUI0JUE1JUU3JUJCJTkzJUU2JTlEJTlG\"}],\"next\":\"JUU5JTgyJTgwJUU3JUJBJUE2JUU4JUFGJUFEJUU1JThGJUE1\"},\"JUU2JTg0JThGJUU1JTkwJTkxJUU4JUFGJUFEJUU1JThGJUE1\":{\"type\":0,\"keyword\":[],\"voice\":[\"807cf2e89d9818185c138de3a8db527fbcaa75a6\"],\"choice\":\"random\",\"conds\":[],\"next\":\"JUU5JTgyJTgwJUU3JUJBJUE2JUU4JUFGJUFEJUU1JThGJUE1\"},\"JUU2JTk1JTg4JUU2JTlFJTlDJUU4JUFGJUFEJUU1JUEyJTgz\":{\"type\":0,\"keyword\":[],\"voice\":[\"a5f815548ca589be6253efc1c8c562ed3a814780\"],\"choice\":\"random\",\"conds\":[{\"keyword\":[\"不用#25\",\"不要#25\",\"不买#25\",\"没钱#25\",\"很穷#25\",\"没兴趣#25\",\"买不起#25\",\"不考虑#25\",\"没考虑#25\",\"不需要#25\",\"没需求#25\",\"不想要#25\",\"不感兴趣#25\",\"不想了解#25\",\"没有需要#25\",\"不用介绍#25\",\"已经买了#25\",\"已买#25\",\"没有钱#25\",\"不可以#25\",\"挂了#25\",\"年纪大#25\",\"先这样#25\",\"没有兴趣#25\",\"用不着#25\",\"用不上#25\",\"哦不需#25\",\"哦不需要#25\",\"笑挂了#25\",\"哦不用#25\",\"哦不#25\"],\"to\":\"JUU1JUE0JUIxJUU4JUI0JUE1JUU3JUJCJTkzJUU2JTlEJTlG\"}],\"next\":\"JUU5JTgyJTgwJUU3JUJBJUE2JUU4JUFGJUFEJUU1JThGJUE1\"},\"JUU4JUExJThDJUU0JUI4JTlBJUU4JUFGJUFEJUU1JUEyJTgz\":{\"type\":0,\"keyword\":[\"没人管#25\"],\"voice\":[\"5cf624717b30dba7b22b62161732a30005493c11\"],\"choice\":\"random\",\"conds\":[{\"keyword\":[\"不用#25\",\"不要#25\",\"不买#25\",\"没钱#25\",\"很穷#25\",\"没兴趣#25\",\"买不起#25\",\"不考虑#25\",\"没考虑#25\",\"不需要#25\",\"没需求#25\",\"不想要#25\",\"不感兴趣#25\",\"不想了解#25\",\"没有需要#25\",\"不用介绍#25\",\"已经买了#25\",\"已买#25\",\"没有钱#25\",\"不可以#25\",\"挂了#25\",\"年纪大#25\",\"先这样#25\",\"没有兴趣#25\",\"用不着#25\",\"用不上#25\",\"哦不需#25\",\"哦不需要#25\",\"笑挂了#25\"],\"to\":\"JUU1JUE0JUIxJUU4JUI0JUE1JUU3JUJCJTkzJUU2JTlEJTlG\"}],\"next\":\"JUU5JTgyJTgwJUU3JUJBJUE2JUU4JUFGJUFEJUU1JThGJUE1\"},\"JUU3JTk0JUI1JUU4JUFGJTlEJUU1JThEJUEx\":{\"type\":0,\"keyword\":[\"电话卡#25\"],\"voice\":[\"00d8e90c67215f63c853bcf280388f2de2b0b0f4\"],\"choice\":\"random\",\"conds\":[{\"keyword\":[],\"to\":\"JUU1JUE0JUIxJUU4JUI0JUE1JUU3JUJCJTkzJUU2JTlEJTlG\"}],\"next\":\"JUU5JTgyJTgwJUU3JUJBJUE2JUU4JUFGJUFEJUU1JThGJUE1\"},\"JUU1JTkwJThDJUU4JUExJThDJUU4JUFGJUFEJUU1JThGJUE1\":{\"type\":0,\"keyword\":[\"灵生#25\",\"百应#25\",\"小心#25\",\"晓芯#25\"],\"voice\":[\"1cf64885d7f6002e45378543fb9adeecdafa1a49\"],\"choice\":\"random\",\"conds\":[{\"keyword\":[\"不用#25\",\"不要#25\",\"不买#25\",\"没钱#25\",\"很穷#25\",\"没兴趣#25\",\"买不起#25\",\"不考虑#25\",\"没考虑#25\",\"不需要#25\",\"没需求#25\",\"不想要#25\",\"不感兴趣#25\",\"不想了解#25\",\"没有需要#25\",\"不用介绍#25\",\"已经买了#25\",\"已买#25\",\"没有钱#25\",\"不可以#25\",\"挂了#25\",\"年纪大#25\",\"先这样#25\",\"没有兴趣#25\",\"用不着#25\",\"用不上#25\",\"哦不需#25\",\"哦不需要#25\",\"笑挂了#25\"],\"to\":\"JUU1JUE0JUIxJUU4JUI0JUE1JUU3JUJCJTkzJUU2JTlEJTlG\"}],\"next\":\"JUU5JTgyJTgwJUU3JUJBJUE2JUU4JUFGJUFEJUU1JThGJUE1\"}},\"voice\":{\"535a12eff01cf6deb1176fd848362431f819efc7\":{\"pcm\":\"2018\\\\07\\\\10\\\\535a12eff01cf6deb1176fd848362431f819efc7.pcm\",\"path\":\"2018\\\\07\\\\10\\\\535a12eff01cf6deb1176fd848362431f819efc7.wav\",\"filename\":\"1\",\"text\":\"喂您好，您好，我们这边是专业做人工智能电话机器人的，可以帮您帅选意向客户，您这边需要了解一下吗？\"},\"843343ec0694f1c4c472a001968fc4c8d875f8fc\":{\"pcm\":\"2018\\\\07\\\\10\\\\843343ec0694f1c4c472a001968fc4c8d875f8fc.pcm\",\"path\":\"2018\\\\07\\\\10\\\\843343ec0694f1c4c472a001968fc4c8d875f8fc.wav\",\"filename\":\"3\",\"text\":\"我们的产品呢，可以做到像真人一样和客户沟通，每天可以呼出上万个电话，效率是人工的几十倍，像电信和联通都是我们的服务客户，您公司平时有电销的业务吗？\"},\"c0a43eaf793db93ddab5e4c8697cc0bd9f084c31\":{\"pcm\":\"2018\\\\07\\\\10\\\\c0a43eaf793db93ddab5e4c8697cc0bd9f084c31.pcm\",\"path\":\"2018\\\\07\\\\10\\\\c0a43eaf793db93ddab5e4c8697cc0bd9f084c31.wav\",\"filename\":\"4\",\"text\":\"我们的机器人能自动对客户进行意向分类，邀约等等，目前已经有上百家企业在跟我们合作了，您这边要不要先使用一下呢？\"},\"2e225320aa67d3793bcf14edfc9a436547856eee\":{\"pcm\":\"2018\\\\07\\\\10\\\\2e225320aa67d3793bcf14edfc9a436547856eee.pcm\",\"path\":\"2018\\\\07\\\\10\\\\2e225320aa67d3793bcf14edfc9a436547856eee.wav\",\"filename\":\"11\",\"text\":\"您可以了解一下，毕竟人工智能是未来的趋势，您提前了解的话对您也是有帮助的，您说是吧？\"},\"608df84ba67e12c512fae5002ab9fad2f27714c7\":{\"pcm\":\"2018\\\\07\\\\10\\\\608df84ba67e12c512fae5002ab9fad2f27714c7.pcm\",\"path\":\"2018\\\\07\\\\10\\\\608df84ba67e12c512fae5002ab9fad2f27714c7.wav\",\"filename\":\"12\",\"text\":\"我们的智能机器人效率是非常高的，能够节省很多不必要的费用，您要不再考虑一下？\"},\"cfde722931c32e58bf739f12a1ddd293f26896b2\":{\"pcm\":\"2018\\\\07\\\\10\\\\cfde722931c32e58bf739f12a1ddd293f26896b2.pcm\",\"path\":\"2018\\\\07\\\\10\\\\cfde722931c32e58bf739f12a1ddd293f26896b2.wav\",\"filename\":\"13\",\"text\":\"我们的机器人还是非常划算的，一个机器人就可以完成几个人的工作量，为您省去了招聘人力需要话费的支出，您认为呢\"},\"2f8e41a86a111deabcd2b83277a70f8871f86c56\":{\"pcm\":\"2018\\\\07\\\\10\\\\2f8e41a86a111deabcd2b83277a70f8871f86c56.pcm\",\"path\":\"2018\\\\07\\\\10\\\\2f8e41a86a111deabcd2b83277a70f8871f86c56.wav\",\"filename\":\"2\",\"text\":\"是这样，我这边是做人工智能电话机器人的，可以实现用智能机器人代替人工打电话，还能帮您筛选意向客户，您看要不要了解一下呢？\"},\"8dd0c34982fa9ea3487be35098517490c59b52ad\":{\"pcm\":\"2018\\\\07\\\\10\\\\8dd0c34982fa9ea3487be35098517490c59b52ad.pcm\",\"path\":\"2018\\\\07\\\\10\\\\8dd0c34982fa9ea3487be35098517490c59b52ad.wav\",\"filename\":\"5\",\"text\":\"哪稍后我会安排专业的技术顾问和您联系，请问您贵姓呢？\"},\"d3222453fca753129ce40f9f68ddd945afb898f9\":{\"pcm\":\"2018\\\\07\\\\10\\\\d3222453fca753129ce40f9f68ddd945afb898f9.pcm\",\"path\":\"2018\\\\07\\\\10\\\\d3222453fca753129ce40f9f68ddd945afb898f9.wav\",\"filename\":\"6\",\"text\":\"我们的技术顾问在这方面非常专业的，稍后让她给您回个电话，详细的沟通好吗？\"},\"e3bd741c4be852ff6d0932df1194bea862a184fc\":{\"pcm\":\"2018\\\\07\\\\10\\\\e3bd741c4be852ff6d0932df1194bea862a184fc.pcm\",\"path\":\"2018\\\\07\\\\10\\\\e3bd741c4be852ff6d0932df1194bea862a184fc.wav\",\"filename\":\"7\",\"text\":\"我们的产品对贵公司的日常运营能节省一大笔开支，您看要不要详细了解一下呢？\"},\"7cb518e2e44425f4e091489a10722bea0ae4bcf9\":{\"pcm\":\"2018\\\\07\\\\10\\\\7cb518e2e44425f4e091489a10722bea0ae4bcf9.pcm\",\"path\":\"2018\\\\07\\\\10\\\\7cb518e2e44425f4e091489a10722bea0ae4bcf9.wav\",\"filename\":\"9\",\"text\":\"好的，感谢您的接听，祝您生活愉快再见。\"},\"c73a2c158cd59e1687dc6c56cfaa8879ff8363b2\":{\"pcm\":\"2018\\\\07\\\\10\\\\c73a2c158cd59e1687dc6c56cfaa8879ff8363b2.pcm\",\"path\":\"2018\\\\07\\\\10\\\\c73a2c158cd59e1687dc6c56cfaa8879ff8363b2.wav\",\"filename\":\"10\",\"text\":\"那打扰您了，祝您生活愉快，再见\"},\"5738a429e322cce6405ab55874b9265664a94797\":{\"pcm\":\"2018\\\\07\\\\10\\\\5738a429e322cce6405ab55874b9265664a94797.pcm\",\"path\":\"2018\\\\07\\\\10\\\\5738a429e322cce6405ab55874b9265664a94797.wav\",\"filename\":\"14\",\"text\":\"喂，您还在吗，刚才没听清\"},\"47c88fb61b1c17874ecbc13d5a065cdea4619bf1\":{\"pcm\":\"2018\\\\07\\\\10\\\\47c88fb61b1c17874ecbc13d5a065cdea4619bf1.pcm\",\"path\":\"2018\\\\07\\\\10\\\\47c88fb61b1c17874ecbc13d5a065cdea4619bf1.wav\",\"filename\":\"35\",\"text\":\"喂，能听到吗，这边信号有点不好\"},\"ed1a07ce20fca27d9ae674750a06ba7701343c40\":{\"pcm\":\"2018\\\\07\\\\10\\\\ed1a07ce20fca27d9ae674750a06ba7701343c40.pcm\",\"path\":\"2018\\\\07\\\\10\\\\ed1a07ce20fca27d9ae674750a06ba7701343c40.wav\",\"filename\":\"15\",\"text\":\"我们这边是江苏希高智能，主要是做ai智能电话机器人，您看需要了解一下吗？\"},\"01be40dcdc49605656e20a1fdfdd09dc4f7f32ec\":{\"pcm\":\"2018\\\\07\\\\10\\\\01be40dcdc49605656e20a1fdfdd09dc4f7f32ec.pcm\",\"path\":\"2018\\\\07\\\\10\\\\01be40dcdc49605656e20a1fdfdd09dc4f7f32ec.wav\",\"filename\":\"16\",\"text\":\"我们就在江苏省淮安市，稍后我把地址发给您，你有时间可以来我们公司考察一下\"},\"8141dbcc261062a04b123a9ce5894dc8acb1fb0c\":{\"pcm\":\"2018\\\\07\\\\10\\\\8141dbcc261062a04b123a9ce5894dc8acb1fb0c.pcm\",\"path\":\"2018\\\\07\\\\10\\\\8141dbcc261062a04b123a9ce5894dc8acb1fb0c.wav\",\"filename\":\"18\",\"text\":\"您想啊，您雇佣一个员工加上社保公积金，平均每月4000元，而我们的机器人可以剩下这些成本，还可以保质保量完成任务，对您公司发展是非常有帮助的\"},\"688fe966d6ffe68ec58b8156f91e4505365c3ab6\":{\"pcm\":\"2018\\\\07\\\\10\\\\688fe966d6ffe68ec58b8156f91e4505365c3ab6.pcm\",\"path\":\"2018\\\\07\\\\10\\\\688fe966d6ffe68ec58b8156f91e4505365c3ab6.wav\",\"filename\":\"19\",\"text\":\"如果您是确定需要的话，我稍后能让技术顾问跟您详细沟通一下您看可以吧。\"},\"128da7f0807efe24e147ada0b0dbce376e12aef2\":{\"pcm\":\"2018\\\\07\\\\10\\\\128da7f0807efe24e147ada0b0dbce376e12aef2.pcm\",\"path\":\"2018\\\\07\\\\10\\\\128da7f0807efe24e147ada0b0dbce376e12aef2.wav\",\"filename\":\"20\",\"text\":\"你可以网上搜索了解下我们公司的，实力这块您不放心的话也可以来我们公司看下的\"},\"807cf2e89d9818185c138de3a8db527fbcaa75a6\":{\"pcm\":\"2018\\\\07\\\\10\\\\807cf2e89d9818185c138de3a8db527fbcaa75a6.pcm\",\"path\":\"2018\\\\07\\\\10\\\\807cf2e89d9818185c138de3a8db527fbcaa75a6.wav\",\"filename\":\"22\",\"text\":\"合同的话我们肯定是要签的，这也是对我们双方的一种保障啊\"},\"a5f815548ca589be6253efc1c8c562ed3a814780\":{\"pcm\":\"2018\\\\07\\\\10\\\\a5f815548ca589be6253efc1c8c562ed3a814780.pcm\",\"path\":\"2018\\\\07\\\\10\\\\a5f815548ca589be6253efc1c8c562ed3a814780.wav\",\"filename\":\"23\",\"text\":\"我们一条线路能完成4-5个人的工作量，像京东，淘宝网都有合作，效果这块您放心\"},\"5cf624717b30dba7b22b62161732a30005493c11\":{\"pcm\":\"2018\\\\07\\\\10\\\\5cf624717b30dba7b22b62161732a30005493c11.pcm\",\"path\":\"2018\\\\07\\\\10\\\\5cf624717b30dba7b22b62161732a30005493c11.wav\",\"filename\":\"24\",\"text\":\"这个您放心，我们有专业团队全程服务，毕竟呢都是希望大家长期合作\"},\"00d8e90c67215f63c853bcf280388f2de2b0b0f4\":{\"pcm\":\"2018\\\\07\\\\10\\\\00d8e90c67215f63c853bcf280388f2de2b0b0f4.pcm\",\"path\":\"2018\\\\07\\\\10\\\\00d8e90c67215f63c853bcf280388f2de2b0b0f4.wav\",\"filename\":\"25\",\"text\":\"是这样的，因为电话卡实名制，您需要再见办理。。。。\"},\"1cf64885d7f6002e45378543fb9adeecdafa1a49\":{\"pcm\":\"2018\\\\07\\\\10\\\\1cf64885d7f6002e45378543fb9adeecdafa1a49.pcm\",\"path\":\"2018\\\\07\\\\10\\\\1cf64885d7f6002e45378543fb9adeecdafa1a49.wav\",\"filename\":\"27\",\"text\":\"我们在语音识别这一块的市场上有非常大的优势，而且在。。。。\"}},\"type\":[{\"name\":\"未分类\",\"rule\":\"time:0,10;word:0,5;section:0,10\"},{\"name\":\"空号\",\"rule\":\"\"},{\"name\":\"A类\",\"rule\":\"\"},{\"name\":\"B类\",\"rule\":\"\"},{\"name\":\"C类\",\"rule\":\"\"},{\"name\":\"D类\",\"rule\":\"\"},{\"name\":\"E类\",\"rule\":\"\"},{\"name\":\"F类\",\"rule\":\"\"}]}',3,NULL),(2,'希高地产模板','admin',' {\r\n      \'main\': \'JUU0JUJCJThCJUU3JUJCJThEJUU1JUI5JUI2JUU4JUFGJUEyJUU5JTk3JUFFJUU5JTlDJTgwJUU2JUIxJTgy\',\r\n      \'flow\': {\r\n        \'JUU0JUJCJThCJUU3JUJCJThEJUU1JUI5JUI2JUU4JUFGJUEyJUU5JTk3JUFFJUU5JTlDJTgwJUU2JUIxJTgy\': {\r\n          \'section\': {\r\n            \'type\': \'condition\',\r\n            \'voice\': [\r\n              \'f618559afa62c72f65b96d43aa8fad93ac30c835\'\r\n            ],\r\n            \'choice\': \'random\',\r\n            \'conds\': [\r\n              {\r\n                \'keyword\': [\r\n                  \'做什么#25\',\r\n                  \'干嘛#25\',\r\n                  \'做啥#25\',\r\n                  \'什么#10\'\r\n                ],\r\n                \'to\': \'JUU1JTg2JThEJUU2JUFDJUExJUU0JUJCJThCJUU3JUJCJThE\'\r\n              },\r\n              {\r\n                \'keyword\': [\r\n                  \'好的#25\',\r\n                  \'可以#25\',\r\n                  \'你说#25\',\r\n                  \'好#25\',\r\n                  \'需要#25\'\r\n                ],\r\n                \'to\': \'JUU4JUFGJUI3JUU2JUIxJTgyJUU4JUFFJUE5JUU0JUI4JUJCJUU3JUFFJUExJUU4JTgxJTk0JUU3JUIzJUJCJUU0JUJCJTk2\'\r\n              },\r\n              {\r\n                \'keyword\': [\r\n                  \'不用#25\',\r\n                  \'不需要#77\',\r\n                  \'你需要#25\'\r\n                ],\r\n                \'to\': \'JUU2JThDJUJEJUU1JTlCJTlF\'\r\n              }\r\n            ]\r\n          },\r\n          \'next\': \'JUU2JThDJUJEJUU1JTlCJTlF\',\r\n          \'hook\': true,\r\n          \'type\': 0\r\n        },\r\n        \'JUU2JThDJUJEJUU1JTlCJTlF\': {\r\n          \'section\': {\r\n            \'type\': \'condition\',\r\n            \'voice\': [\r\n              \'c2fea2182648516b42446bfa29cbad920315c6b4\',\r\n              \'622dd290810bb00d60c87355ffe6096fad39594f\',\r\n              \'7c2347a0d64ad422f1b3ba5c4ea5af693ddcbf74\'\r\n            ],\r\n            \'choice\': \'random\',\r\n            \'conds\': [\r\n              {\r\n                \'keyword\': [\r\n                  \'asdfasdf#25\'\r\n                ],\r\n                \'to\': \'\'\r\n              },\r\n              {\r\n                \'keyword\': [],\r\n                \'to\': \'\'\r\n              },\r\n              {\r\n                \'keyword\': [],\r\n                \'to\': \'\'\r\n              }\r\n            ]\r\n          },\r\n          \'next\': \'JUU4JUFGJUI3JUU2JUIxJTgyJUU4JUFFJUE5JUU0JUI4JUJCJUU3JUFFJUExJUU4JTgxJTk0JUU3JUIzJUJCJUU0JUJCJTk2\',\r\n          \'hook\': true,\r\n          \'type\': 4\r\n        },\r\n        \'JUU1JTg2JThEJUU2JUFDJUExJUU0JUJCJThCJUU3JUJCJThE\': {\r\n          \'section\': {\r\n            \'type\': \'condition\',\r\n            \'voice\': [\r\n              \'06227df902d4350cadcffe0d17eeb499bd010ca4\'\r\n            ],\r\n            \'choice\': \'random\',\r\n            \'conds\': []\r\n          },\r\n          \'next\': \'JUU4JUFGJUI3JUU2JUIxJTgyJUU4JUFFJUE5JUU0JUI4JUJCJUU3JUFFJUExJUU4JTgxJTk0JUU3JUIzJUJCJUU0JUJCJTk2\',\r\n          \'hook\': true,\r\n          \'type\': 4\r\n        },\r\n        \'JUU4JUFGJUI3JUU2JUIxJTgyJUU4JUFFJUE5JUU0JUI4JUJCJUU3JUFFJUExJUU4JTgxJTk0JUU3JUIzJUJCJUU0JUJCJTk2\': {\r\n          \'section\': {\r\n            \'type\': \'condition\',\r\n            \'voice\': [\r\n              \'c04cdee1a86ad6e3f66135d7d59ae6688c573a8a\',\r\n              \'ca71b2e676b836b30b0622a11f18e86587e688e1\',\r\n              \'d31fdca0d475bdf41d845d1812961b0b707527a1\'\r\n            ],\r\n            \'choice\': \'random\',\r\n            \'conds\': [\r\n              {\r\n                \'keyword\': [\r\n                  \'不用#25\',\r\n                  \'谢谢#25\',\r\n                  \'再见#25\',\r\n                  \'不需要#25\',\r\n                  \'你需要#25\'\r\n                ],\r\n                \'to\': \'JUU5JTgyJTgwJUU3JUJBJUE2JUU1JUE0JUIxJUU4JUI0JUE1JUVGJUJDJThDJUU1JTg2JThEJUU4JUE3JTgx\'\r\n              },\r\n              {\r\n                \'keyword\': [],\r\n                \'to\': \'JUU5JTgyJTgwJUU3JUJBJUE2JUU2JTg4JTkwJUU1JThBJTlG\'\r\n              }\r\n            ]\r\n          },\r\n          \'next\': \'JUU5JTgyJTgwJUU3JUJBJUE2JUU2JTg4JTkwJUU1JThBJTlG\',\r\n          \'hook\': true,\r\n          \'type\': 3\r\n        },\r\n        \'JUU0JUJDJTk4JUU1JThBJUJG\': {\r\n          \'section\': {\r\n            \'type\': \'condition\',\r\n            \'voice\': [\r\n              \'26c31553335a1fd07f7776e2f0b36e942d762b1d\',\r\n              \'520d7b28d0df45cfe302768b58ec1e5ac471ea3f\'\r\n            ],\r\n            \'choice\': \'random\',\r\n            \'conds\': []\r\n          },\r\n          \'next\': \'JUU1JTg2JThEJUU2JUFDJUExJUU0JUJCJThCJUU3JUJCJThE\',\r\n          \'hook\': true,\r\n          \'type\': 3\r\n        },\r\n        \'JUU5JTgyJTgwJUU3JUJBJUE2JUU1JUE0JUIxJUU4JUI0JUE1JUVGJUJDJThDJUU1JTg2JThEJUU4JUE3JTgx\': {\r\n          \'section\': {\r\n            \'type\': \'end\',\r\n            \'voice\': [\r\n              \'41152b9f5842ffc4bc06145b102274f3a4e71d4a\'\r\n            ],\r\n            \'choice\': \'41152b9f5842ffc4bc06145b102274f3a4e71d4a\'\r\n          },\r\n          \'next\': \'\',\r\n          \'hook\': true,\r\n          \'type\': 0\r\n        },\r\n        \'JUU5JTgyJTgwJUU3JUJBJUE2JUU2JTg4JTkwJUU1JThBJTlG\': {\r\n          \'section\': {\r\n            \'type\': \'end\',\r\n            \'voice\': [\r\n              \'f617d1661f1738a59d1cc03733c85a0ad6e56e35\'\r\n            ],\r\n            \'choice\': \'random\'\r\n          },\r\n          \'next\': \'\',\r\n          \'hook\': true,\r\n          \'type\': 2\r\n        }\r\n      },\r\n      \'keyword\': {\r\n        \'quiet1\': {\r\n          \'type\': 0,\r\n          \'voice\': [\r\n            \'cf34fe75dffec7282b54292bc8479dc76681b6c3\'\r\n          ],\r\n          \'choice\': \'random\',\r\n          \'conds\': [],\r\n          \'next\': \'\'\r\n        },\r\n        \'quiet2\': {\r\n          \'type\': 0,\r\n          \'voice\': [\r\n            \'cf34fe75dffec7282b54292bc8479dc76681b6c3\'\r\n          ],\r\n          \'choice\': \'random\',\r\n          \'conds\': [],\r\n          \'next\': \'\'\r\n        },\r\n        \'noword1\': {\r\n          \'type\': 0,\r\n          \'voice\': [\r\n            \'cf34fe75dffec7282b54292bc8479dc76681b6c3\'\r\n          ],\r\n          \'choice\': \'random\',\r\n          \'conds\': [],\r\n          \'next\': \'\'\r\n        },\r\n        \'noword2\': {\r\n          \'type\': 0,\r\n          \'voice\': [\r\n            \'cf34fe75dffec7282b54292bc8479dc76681b6c3\'\r\n          ],\r\n          \'choice\': \'random\',\r\n          \'conds\': [],\r\n          \'next\': \'\'\r\n        },\r\n        \'noword3\': {\r\n          \'type\': 0,\r\n          \'voice\': [\r\n            \'cf34fe75dffec7282b54292bc8479dc76681b6c3\'\r\n          ],\r\n          \'choice\': \'random\',\r\n          \'conds\': [],\r\n          \'next\': \'\'\r\n        },\r\n        \'JUU1JTg1JUFDJUU1JThGJUI4JUU1JTlDJUIwJUU1JTlEJTgw\': {\r\n          \'type\': 4,\r\n          \'keyword\': [\r\n            \'公司#25\',\r\n            \'地址#25\',\r\n            \'哪里#25\',\r\n            \'那里#25\',\r\n            \'地方#25\'\r\n          ],\r\n          \'voice\': [\r\n            \'7bd0b288eba4e14a69f2526f2e728e2afb72457d\',\r\n            \'afb4b8c7cbabaafc18653dcc9bf1e56b956fbe5c\'\r\n          ],\r\n          \'choice\': \'random\',\r\n          \'conds\': [],\r\n          \'next\': \'JUU4JUFGJUI3JUU2JUIxJTgyJUU4JUFFJUE5JUU0JUI4JUJCJUU3JUFFJUExJUU4JTgxJTk0JUU3JUIzJUJCJUU0JUJCJTk2\'\r\n        },\r\n        \'JUU4JUFGJUEyJUU5JTk3JUFFJUU2JTk4JUFGJUU1JTkwJUE2JUU2JUFEJUEzJUU4JUE3JTg0\': {\r\n          \'type\': 5,\r\n          \'keyword\': [\r\n            \'正规#25\'\r\n          ],\r\n          \'voice\': [\r\n            \'51940df26dfdd5d8ab1744fb0280c588d12f0fc0\'\r\n          ],\r\n          \'choice\': \'random\',\r\n          \'conds\': [],\r\n          \'next\': \'wait\'\r\n        },\r\n        \'JUU2JTlDJThEJUU1JThBJUExJUU2JTgwJThFJUU0JUI5JTg4JUU2JUEwJUI3\': {\r\n          \'type\': 4,\r\n          \'keyword\': [\r\n            \'服务#25\'\r\n          ],\r\n          \'voice\': [\r\n            \'b04888054d18c21f7f27ab88adeae6bf289b4786\'\r\n          ],\r\n          \'choice\': \'random\',\r\n          \'conds\': [],\r\n          \'next\': \'JUU4JUFGJUI3JUU2JUIxJTgyJUU4JUFFJUE5JUU0JUI4JUJCJUU3JUFFJUExJUU4JTgxJTk0JUU3JUIzJUJCJUU0JUJCJTk2\'\r\n        },\r\n        \'JUU4JUFGJUEyJUU5JTk3JUFFJUU1JTg1JUI2JUU0JUJCJTk2JUU0JUI4JTlBJUU1JThBJUExJUU1JTgxJTlBJUU0JUI4JThEJUU1JTgxJTlB\': {\r\n          \'type\': 5,\r\n          \'keyword\': [\r\n            \'飞机#25\',\r\n            \'飞船#25\',\r\n            \'大炮#25\',\r\n            \'走私#25\',\r\n            \'枪火#25\',\r\n            \'宇宙#25\',\r\n            \'白粉#25\',\r\n            \'租车#25\',\r\n            \'注销#25\'\r\n          ],\r\n          \'voice\': [],\r\n          \'choice\': \'random\',\r\n          \'conds\': [\r\n            {\r\n              \'keyword\': [\r\n                \'好的#25\',\r\n                \'可以#25\',\r\n                \'没问题#25\'\r\n              ],\r\n              \'to\': \'JUU4JUFGJUI3JUU2JUIxJTgyJUU4JUFFJUE5JUU0JUI4JUJCJUU3JUFFJUExJUU4JTgxJTk0JUU3JUIzJUJCJUU0JUJCJTk2\'\r\n            }\r\n          ],\r\n          \'next\': \'return\'\r\n        },\r\n        \'JUU2JTk0JUI2JUU4JUI0JUI5\': {\r\n          \'type\': 3,\r\n          \'keyword\': [\r\n            \'价格#25\',\r\n            \'价钱#25\',\r\n            \'收费#25\',\r\n            \'费用#25\',\r\n            \'多少钱#25\',\r\n            \'钱#25\'\r\n          ],\r\n          \'voice\': [\r\n            \'d55851338caa81a52c63936029b1e5f630c8927a\'\r\n          ],\r\n          \'choice\': \'d55851338caa81a52c63936029b1e5f630c8927a\',\r\n          \'conds\': [],\r\n          \'next\': \'JUU4JUFGJUI3JUU2JUIxJTgyJUU4JUFFJUE5JUU0JUI4JUJCJUU3JUFFJUExJUU4JTgxJTk0JUU3JUIzJUJCJUU0JUJCJTk2\'\r\n        },\r\n        \'JUU1JUFCJThDJUU4JUI0JUI1\': {\r\n          \'type\': 4,\r\n          \'keyword\': [\r\n            \'太贵#25\',\r\n            \'优惠#25\',\r\n            \'便宜#25\',\r\n            \'打折#25\',\r\n            \'折扣#25\'\r\n          ],\r\n          \'voice\': [\r\n            \'31b9367fddd568b68e5cbf81aee8fa94388b0708\'\r\n          ],\r\n          \'choice\': \'random\',\r\n          \'conds\': [],\r\n          \'next\': \'JUU4JUFGJUI3JUU2JUIxJTgyJUU4JUFFJUE5JUU0JUI4JUJCJUU3JUFFJUExJUU4JTgxJTk0JUU3JUIzJUJCJUU0JUJCJTk2\'\r\n        },\r\n        \'JUU1JUFFJUEyJUU2JTg4JUI3JUU2JTgwJTgwJUU3JTk2JTkxJUU2JTk4JUFGJUU2JTlDJUJBJUU1JTk5JUE4JUU0JUJBJUJB\': {\r\n          \'type\': 4,\r\n          \'keyword\': [\r\n            \'机器#25\',\r\n            \'电脑#25\',\r\n            \'软件#25\',\r\n            \'程序#25\',\r\n            \'自动#25\',\r\n            \'录音#25\'\r\n          ],\r\n          \'voice\': [\r\n            \'398999628876116b3eee7ebada46f72053195f19\'\r\n          ],\r\n          \'choice\': \'random\',\r\n          \'conds\': [],\r\n          \'next\': \'\'\r\n        },\r\n        \'JUU1JUFFJUEyJUU2JTg4JUI3JUU4JUFFJUE5JUU2JTlDJUJBJUU1JTk5JUE4JUU0JUJBJUJBJUU3JUFEJTg5JUU0JUI4JTgwJUU0JUI4JThC\': {\r\n          \'type\': 0,\r\n          \'keyword\': [\r\n            \'听我说#25\',\r\n            \'等下#25\',\r\n            \'等等#25\'\r\n          ],\r\n          \'voice\': [\r\n            \'4986490ca8ad2bb18348219d7c7827d5c0a300a1\',\r\n            \'c1556f29dc49977e38d8311a30857550ee3d5655\'\r\n          ],\r\n          \'choice\': \'random\',\r\n          \'conds\': [],\r\n          \'next\': \'wait\'\r\n        },\r\n        \'JUU1JUFFJUEyJUU2JTg4JUI3JUU4JUFGJUEyJUU5JTk3JUFFJUU2JTgwJThFJUU0JUI5JTg4JUU2JTlDJTg5JUU0JUJCJTk2JUU1JThGJUI3JUU3JUEwJTgx\': {\r\n          \'type\': 0,\r\n          \'keyword\': [\r\n            \'手机号#25\',\r\n            \'联系方式#25\'\r\n          ],\r\n          \'voice\': [\r\n            \'5380f4f7e8ff3bc6f810ecb8766f85618f6b8884\'\r\n          ],\r\n          \'choice\': \'random\',\r\n          \'conds\': [],\r\n          \'next\': \'wait\'\r\n        },\r\n        \'JUU4JUIwJTgzJUU2JTg4JThGJUU1JUFFJUEyJUU2JTlDJThE\': {\r\n          \'type\': 0,\r\n          \'keyword\': [\r\n            \'美女#25\',\r\n            \'几岁#25\',\r\n            \'约你#25\',\r\n            \'约会#25\',\r\n            \'请你#25\',\r\n            \'吃饭#25\',\r\n            \'多大#25\',\r\n            \'多少岁#25\',\r\n            \'声音好听#25\',\r\n            \'好听#25\'\r\n          ],\r\n          \'voice\': [\r\n            \'09caade6139421700add2d7dd2579a399322cff7\',\r\n            \'189509ef54a2cf03c756f026a656f3d93ba6fcd0\'\r\n          ],\r\n          \'choice\': \'random\',\r\n          \'conds\': [\r\n            {\r\n              \'keyword\': [\r\n                \'没有#25\',\r\n                \'不需要#25\',\r\n                \'不用#25\',\r\n                \'没#25\'\r\n              ],\r\n              \'to\': \'JUU4JUFGJUI3JUU2JUIxJTgyJUU4JUFFJUE5JUU0JUI4JUJCJUU3JUFFJUExJUU4JTgxJTk0JUU3JUIzJUJCJUU0JUJCJTk2\'\r\n            }\r\n          ],\r\n          \'next\': \'wait\'\r\n        }\r\n      },\r\n      \'voice\': {\r\n        \'f618559afa62c72f65b96d43aa8fad93ac30c835\': {\r\n          \'pcm\': \'2018\\\\06\\\\21\\\\f618559afa62c72f65b96d43aa8fad93ac30c835.pcm\',\r\n          \'path\': \'2018\\\\06\\\\21\\\\f618559afa62c72f65b96d43aa8fad93ac30c835.wav\',\r\n          \'filename\': \'3.1.1\',\r\n          \'text\': \'介绍并询问需求\'\r\n        },\r\n        \'26c31553335a1fd07f7776e2f0b36e942d762b1d\': {\r\n          \'pcm\': \'2018\\\\06\\\\21\\\\26c31553335a1fd07f7776e2f0b36e942d762b1d.pcm\',\r\n          \'path\': \'2018\\\\06\\\\21\\\\26c31553335a1fd07f7776e2f0b36e942d762b1d.wav\',\r\n          \'filename\': \'3.2.0\',\r\n          \'text\': \'介绍优势11111\'\r\n        },\r\n        \'520d7b28d0df45cfe302768b58ec1e5ac471ea3f\': {\r\n          \'pcm\': \'2018\\\\06\\\\21\\\\520d7b28d0df45cfe302768b58ec1e5ac471ea3f.pcm\',\r\n          \'path\': \'2018\\\\06\\\\21\\\\520d7b28d0df45cfe302768b58ec1e5ac471ea3f.wav\',\r\n          \'filename\': \'3.2.1\',\r\n          \'text\': \'介绍优势22222\'\r\n        },\r\n        \'06227df902d4350cadcffe0d17eeb499bd010ca4\': {\r\n          \'pcm\': \'2018\\\\06\\\\21\\\\06227df902d4350cadcffe0d17eeb499bd010ca4.pcm\',\r\n          \'path\': \'2018\\\\06\\\\21\\\\06227df902d4350cadcffe0d17eeb499bd010ca4.wav\',\r\n          \'filename\': \'3.1.1.1\',\r\n          \'text\': \'再次介绍，询问需要吗\'\r\n        },\r\n        \'41152b9f5842ffc4bc06145b102274f3a4e71d4a\': {\r\n          \'pcm\': \'2018\\\\06\\\\21\\\\41152b9f5842ffc4bc06145b102274f3a4e71d4a.pcm\',\r\n          \'path\': \'2018\\\\06\\\\21\\\\41152b9f5842ffc4bc06145b102274f3a4e71d4a.wav\',\r\n          \'filename\': \'3.4.2\',\r\n          \'text\': \'不好意思打扰了\'\r\n        },\r\n        \'c04cdee1a86ad6e3f66135d7d59ae6688c573a8a\': {\r\n          \'pcm\': \'2018\\\\06\\\\21\\\\c04cdee1a86ad6e3f66135d7d59ae6688c573a8a.pcm\',\r\n          \'path\': \'2018\\\\06\\\\21\\\\c04cdee1a86ad6e3f66135d7d59ae6688c573a8a.wav\',\r\n          \'filename\': \'3.3.1.ai1\',\r\n          \'text\': \'发资料，约时间\'\r\n        },\r\n        \'ca71b2e676b836b30b0622a11f18e86587e688e1\': {\r\n          \'pcm\': \'2018\\\\06\\\\21\\\\ca71b2e676b836b30b0622a11f18e86587e688e1.pcm\',\r\n          \'path\': \'2018\\\\06\\\\21\\\\ca71b2e676b836b30b0622a11f18e86587e688e1.wav\',\r\n          \'filename\': \'3.3.2.ai1\',\r\n          \'text\': \'让会计师去电话\'\r\n        },\r\n        \'d31fdca0d475bdf41d845d1812961b0b707527a1\': {\r\n          \'pcm\': \'2018\\\\06\\\\21\\\\d31fdca0d475bdf41d845d1812961b0b707527a1.pcm\',\r\n          \'path\': \'2018\\\\06\\\\21\\\\d31fdca0d475bdf41d845d1812961b0b707527a1.wav\',\r\n          \'filename\': \'3.3.2.ai2\',\r\n          \'text\': \'让会计师给你介绍\'\r\n        },\r\n        \'c2fea2182648516b42446bfa29cbad920315c6b4\': {\r\n          \'pcm\': \'2018\\\\06\\\\21\\\\c2fea2182648516b42446bfa29cbad920315c6b4.pcm\',\r\n          \'path\': \'2018\\\\06\\\\21\\\\c2fea2182648516b42446bfa29cbad920315c6b4.wav\',\r\n          \'filename\': \'4.2.1ai2\',\r\n          \'text\': \'挽回1\'\r\n        },\r\n        \'622dd290810bb00d60c87355ffe6096fad39594f\': {\r\n          \'pcm\': \'2018\\\\06\\\\21\\\\622dd290810bb00d60c87355ffe6096fad39594f.pcm\',\r\n          \'path\': \'2018\\\\06\\\\21\\\\622dd290810bb00d60c87355ffe6096fad39594f.wav\',\r\n          \'filename\': \'4.2.1ai1\',\r\n          \'text\': \'挽回2\'\r\n        },\r\n        \'7c2347a0d64ad422f1b3ba5c4ea5af693ddcbf74\': {\r\n          \'pcm\': \'2018\\\\06\\\\21\\\\7c2347a0d64ad422f1b3ba5c4ea5af693ddcbf74.pcm\',\r\n          \'path\': \'2018\\\\06\\\\21\\\\7c2347a0d64ad422f1b3ba5c4ea5af693ddcbf74.wav\',\r\n          \'filename\': \'4.2.1ai3\',\r\n          \'text\': \'挽回3\'\r\n        },\r\n        \'f617d1661f1738a59d1cc03733c85a0ad6e56e35\': {\r\n          \'pcm\': \'2018\\\\06\\\\21\\\\f617d1661f1738a59d1cc03733c85a0ad6e56e35.pcm\',\r\n          \'path\': \'2018\\\\06\\\\21\\\\f617d1661f1738a59d1cc03733c85a0ad6e56e35.wav\',\r\n          \'filename\': \'3.4.1\',\r\n          \'text\': \'邀约成功\'\r\n        },\r\n        \'cf34fe75dffec7282b54292bc8479dc76681b6c3\': {\r\n          \'pcm\': \'2018\\\\06\\\\21\\\\cf34fe75dffec7282b54292bc8479dc76681b6c3.pcm\',\r\n          \'path\': \'2018\\\\06\\\\21\\\\cf34fe75dffec7282b54292bc8479dc76681b6c3.wav\',\r\n          \'filename\': \'6.1.2.ai1\',\r\n          \'text\': \'没声音或没信号\'\r\n        },\r\n        \'7bd0b288eba4e14a69f2526f2e728e2afb72457d\': {\r\n          \'pcm\': \'2018\\\\06\\\\21\\\\7bd0b288eba4e14a69f2526f2e728e2afb72457d.pcm\',\r\n          \'path\': \'2018\\\\06\\\\21\\\\7bd0b288eba4e14a69f2526f2e728e2afb72457d.wav\',\r\n          \'filename\': \'5.2.1.ai1\',\r\n          \'text\': \'我们在xxx地方11\'\r\n        },\r\n        \'afb4b8c7cbabaafc18653dcc9bf1e56b956fbe5c\': {\r\n          \'pcm\': \'2018\\\\06\\\\21\\\\afb4b8c7cbabaafc18653dcc9bf1e56b956fbe5c.pcm\',\r\n          \'path\': \'2018\\\\06\\\\21\\\\afb4b8c7cbabaafc18653dcc9bf1e56b956fbe5c.wav\',\r\n          \'filename\': \'5.2.2.ai1\',\r\n          \'text\': \'我们在yyy地方22\'\r\n        },\r\n        \'51940df26dfdd5d8ab1744fb0280c588d12f0fc0\': {\r\n          \'pcm\': \'2018\\\\06\\\\21\\\\51940df26dfdd5d8ab1744fb0280c588d12f0fc0.pcm\',\r\n          \'path\': \'2018\\\\06\\\\21\\\\51940df26dfdd5d8ab1744fb0280c588d12f0fc0.wav\',\r\n          \'filename\': \'5.1.5.ai1\',\r\n          \'text\': \'我们是正规的公司\'\r\n        },\r\n        \'b04888054d18c21f7f27ab88adeae6bf289b4786\': {\r\n          \'pcm\': \'2018\\\\06\\\\21\\\\b04888054d18c21f7f27ab88adeae6bf289b4786.pcm\',\r\n          \'path\': \'2018\\\\06\\\\21\\\\b04888054d18c21f7f27ab88adeae6bf289b4786.wav\',\r\n          \'filename\': \'5.1.8.ai1\',\r\n          \'text\': \'服务一对一，绝对专业\'\r\n        },\r\n        \'d55851338caa81a52c63936029b1e5f630c8927a\': {\r\n          \'pcm\': \'2018\\\\06\\\\21\\\\d55851338caa81a52c63936029b1e5f630c8927a.pcm\',\r\n          \'path\': \'2018\\\\06\\\\21\\\\d55851338caa81a52c63936029b1e5f630c8927a.wav\',\r\n          \'filename\': \'5.3.1ai1\',\r\n          \'text\': \'介绍价格，小规模200\'\r\n        },\r\n        \'31b9367fddd568b68e5cbf81aee8fa94388b0708\': {\r\n          \'pcm\': \'2018\\\\06\\\\21\\\\31b9367fddd568b68e5cbf81aee8fa94388b0708.pcm\',\r\n          \'path\': \'2018\\\\06\\\\21\\\\31b9367fddd568b68e5cbf81aee8fa94388b0708.wav\',\r\n          \'filename\': \'5.3.2.ai1\',\r\n          \'text\': \'服务很厉害，可以体验一下\'\r\n        },\r\n        \'398999628876116b3eee7ebada46f72053195f19\': {\r\n          \'pcm\': \'2018\\\\06\\\\21\\\\398999628876116b3eee7ebada46f72053195f19.pcm\',\r\n          \'path\': \'2018\\\\06\\\\21\\\\398999628876116b3eee7ebada46f72053195f19.wav\',\r\n          \'filename\': \'6.1.3.ai1\',\r\n          \'text\': \'我不是机器人，统一话术而已\'\r\n        },\r\n        \'4986490ca8ad2bb18348219d7c7827d5c0a300a1\': {\r\n          \'pcm\': \'2018\\\\06\\\\21\\\\4986490ca8ad2bb18348219d7c7827d5c0a300a1.pcm\',\r\n          \'path\': \'2018\\\\06\\\\21\\\\4986490ca8ad2bb18348219d7c7827d5c0a300a1.wav\',\r\n          \'filename\': \'6.1.7ai1\',\r\n          \'text\': \'好的，你请讲\'\r\n        },\r\n        \'c1556f29dc49977e38d8311a30857550ee3d5655\': {\r\n          \'pcm\': \'2018\\\\06\\\\21\\\\c1556f29dc49977e38d8311a30857550ee3d5655.pcm\',\r\n          \'path\': \'2018\\\\06\\\\21\\\\c1556f29dc49977e38d8311a30857550ee3d5655.wav\',\r\n          \'filename\': \'6.1.8.ai1\',\r\n          \'text\': \'好的你请说\'\r\n        },\r\n        \'5380f4f7e8ff3bc6f810ecb8766f85618f6b8884\': {\r\n          \'pcm\': \'2018\\\\06\\\\21\\\\5380f4f7e8ff3bc6f810ecb8766f85618f6b8884.pcm\',\r\n          \'path\': \'2018\\\\06\\\\21\\\\5380f4f7e8ff3bc6f810ecb8766f85618f6b8884.wav\',\r\n          \'filename\': \'6.1.10.ai1\',\r\n          \'text\': \'公司分配的号码，可能你在网上留过号码\'\r\n        },\r\n        \'09caade6139421700add2d7dd2579a399322cff7\': {\r\n          \'pcm\': \'2018\\\\06\\\\21\\\\09caade6139421700add2d7dd2579a399322cff7.pcm\',\r\n          \'path\': \'2018\\\\06\\\\21\\\\09caade6139421700add2d7dd2579a399322cff7.wav\',\r\n          \'filename\': \'6.1.11.ai1\',\r\n          \'text\': \'不方便回答这个问题11\'\r\n        },\r\n        \'189509ef54a2cf03c756f026a656f3d93ba6fcd0\': {\r\n          \'pcm\': \'2018\\\\06\\\\21\\\\189509ef54a2cf03c756f026a656f3d93ba6fcd0.pcm\',\r\n          \'path\': \'2018\\\\06\\\\21\\\\189509ef54a2cf03c756f026a656f3d93ba6fcd0.wav\',\r\n          \'filename\': \'6.1.12.ai1\',\r\n          \'text\': \'不方便回答这个问题222\'\r\n        }\r\n      },\r\n      \'type\': [\r\n        {\r\n          \'name\': \'未分类\',\r\n          \'rule\': \'time:0,10;word:0,5;section:0,10\'\r\n        },\r\n        {\r\n          \'name\': \'空号\',\r\n          \'rule\': \'\'\r\n        },\r\n        {\r\n          \'name\': \'A类\',\r\n          \'rule\': \'\'\r\n        },\r\n        {\r\n          \'name\': \'B类\',\r\n          \'rule\': \'\'\r\n        },\r\n        {\r\n          \'name\': \'C类\',\r\n          \'rule\': \'\'\r\n        },\r\n        {\r\n          \'name\': \'D类\',\r\n          \'rule\': \'\'\r\n        },\r\n        {\r\n          \'name\': \'E类\',\r\n          \'rule\': \'\'\r\n        },\r\n        {\r\n          \'name\': \'F类\',\r\n          \'rule\': \'\'\r\n        }\r\n      ]\r\n    }',0,'2018-07-09 12:19:45'),(3,'希高金融模板','admin',' {\r\n      \'main\': \'JUU0JUJCJThCJUU3JUJCJThEJUU1JUI5JUI2JUU4JUFGJUEyJUU5JTk3JUFFJUU5JTlDJTgwJUU2JUIxJTgy\',\r\n      \'flow\': {\r\n        \'JUU0JUJCJThCJUU3JUJCJThEJUU1JUI5JUI2JUU4JUFGJUEyJUU5JTk3JUFFJUU5JTlDJTgwJUU2JUIxJTgy\': {\r\n          \'section\': {\r\n            \'type\': \'condition\',\r\n            \'voice\': [\r\n              \'f618559afa62c72f65b96d43aa8fad93ac30c835\'\r\n            ],\r\n            \'choice\': \'random\',\r\n            \'conds\': [\r\n              {\r\n                \'keyword\': [\r\n                  \'做什么#25\',\r\n                  \'干嘛#25\',\r\n                  \'做啥#25\',\r\n                  \'什么#10\'\r\n                ],\r\n                \'to\': \'JUU1JTg2JThEJUU2JUFDJUExJUU0JUJCJThCJUU3JUJCJThE\'\r\n              },\r\n              {\r\n                \'keyword\': [\r\n                  \'好的#25\',\r\n                  \'可以#25\',\r\n                  \'你说#25\',\r\n                  \'好#25\',\r\n                  \'需要#25\'\r\n                ],\r\n                \'to\': \'JUU4JUFGJUI3JUU2JUIxJTgyJUU4JUFFJUE5JUU0JUI4JUJCJUU3JUFFJUExJUU4JTgxJTk0JUU3JUIzJUJCJUU0JUJCJTk2\'\r\n              },\r\n              {\r\n                \'keyword\': [\r\n                  \'不用#25\',\r\n                  \'不需要#77\',\r\n                  \'你需要#25\'\r\n                ],\r\n                \'to\': \'JUU2JThDJUJEJUU1JTlCJTlF\'\r\n              }\r\n            ]\r\n          },\r\n          \'next\': \'JUU2JThDJUJEJUU1JTlCJTlF\',\r\n          \'hook\': true,\r\n          \'type\': 0\r\n        },\r\n        \'JUU2JThDJUJEJUU1JTlCJTlF\': {\r\n          \'section\': {\r\n            \'type\': \'condition\',\r\n            \'voice\': [\r\n              \'c2fea2182648516b42446bfa29cbad920315c6b4\',\r\n              \'622dd290810bb00d60c87355ffe6096fad39594f\',\r\n              \'7c2347a0d64ad422f1b3ba5c4ea5af693ddcbf74\'\r\n            ],\r\n            \'choice\': \'random\',\r\n            \'conds\': [\r\n              {\r\n                \'keyword\': [\r\n                  \'asdfasdf#25\'\r\n                ],\r\n                \'to\': \'\'\r\n              },\r\n              {\r\n                \'keyword\': [],\r\n                \'to\': \'\'\r\n              },\r\n              {\r\n                \'keyword\': [],\r\n                \'to\': \'\'\r\n              }\r\n            ]\r\n          },\r\n          \'next\': \'JUU4JUFGJUI3JUU2JUIxJTgyJUU4JUFFJUE5JUU0JUI4JUJCJUU3JUFFJUExJUU4JTgxJTk0JUU3JUIzJUJCJUU0JUJCJTk2\',\r\n          \'hook\': true,\r\n          \'type\': 4\r\n        },\r\n        \'JUU1JTg2JThEJUU2JUFDJUExJUU0JUJCJThCJUU3JUJCJThE\': {\r\n          \'section\': {\r\n            \'type\': \'condition\',\r\n            \'voice\': [\r\n              \'06227df902d4350cadcffe0d17eeb499bd010ca4\'\r\n            ],\r\n            \'choice\': \'random\',\r\n            \'conds\': []\r\n          },\r\n          \'next\': \'JUU4JUFGJUI3JUU2JUIxJTgyJUU4JUFFJUE5JUU0JUI4JUJCJUU3JUFFJUExJUU4JTgxJTk0JUU3JUIzJUJCJUU0JUJCJTk2\',\r\n          \'hook\': true,\r\n          \'type\': 4\r\n        },\r\n        \'JUU4JUFGJUI3JUU2JUIxJTgyJUU4JUFFJUE5JUU0JUI4JUJCJUU3JUFFJUExJUU4JTgxJTk0JUU3JUIzJUJCJUU0JUJCJTk2\': {\r\n          \'section\': {\r\n            \'type\': \'condition\',\r\n            \'voice\': [\r\n              \'c04cdee1a86ad6e3f66135d7d59ae6688c573a8a\',\r\n              \'ca71b2e676b836b30b0622a11f18e86587e688e1\',\r\n              \'d31fdca0d475bdf41d845d1812961b0b707527a1\'\r\n            ],\r\n            \'choice\': \'random\',\r\n            \'conds\': [\r\n              {\r\n                \'keyword\': [\r\n                  \'不用#25\',\r\n                  \'谢谢#25\',\r\n                  \'再见#25\',\r\n                  \'不需要#25\',\r\n                  \'你需要#25\'\r\n                ],\r\n                \'to\': \'JUU5JTgyJTgwJUU3JUJBJUE2JUU1JUE0JUIxJUU4JUI0JUE1JUVGJUJDJThDJUU1JTg2JThEJUU4JUE3JTgx\'\r\n              },\r\n              {\r\n                \'keyword\': [],\r\n                \'to\': \'JUU5JTgyJTgwJUU3JUJBJUE2JUU2JTg4JTkwJUU1JThBJTlG\'\r\n              }\r\n            ]\r\n          },\r\n          \'next\': \'JUU5JTgyJTgwJUU3JUJBJUE2JUU2JTg4JTkwJUU1JThBJTlG\',\r\n          \'hook\': true,\r\n          \'type\': 3\r\n        },\r\n        \'JUU0JUJDJTk4JUU1JThBJUJG\': {\r\n          \'section\': {\r\n            \'type\': \'condition\',\r\n            \'voice\': [\r\n              \'26c31553335a1fd07f7776e2f0b36e942d762b1d\',\r\n              \'520d7b28d0df45cfe302768b58ec1e5ac471ea3f\'\r\n            ],\r\n            \'choice\': \'random\',\r\n            \'conds\': []\r\n          },\r\n          \'next\': \'JUU1JTg2JThEJUU2JUFDJUExJUU0JUJCJThCJUU3JUJCJThE\',\r\n          \'hook\': true,\r\n          \'type\': 3\r\n        },\r\n        \'JUU5JTgyJTgwJUU3JUJBJUE2JUU1JUE0JUIxJUU4JUI0JUE1JUVGJUJDJThDJUU1JTg2JThEJUU4JUE3JTgx\': {\r\n          \'section\': {\r\n            \'type\': \'end\',\r\n            \'voice\': [\r\n              \'41152b9f5842ffc4bc06145b102274f3a4e71d4a\'\r\n            ],\r\n            \'choice\': \'41152b9f5842ffc4bc06145b102274f3a4e71d4a\'\r\n          },\r\n          \'next\': \'\',\r\n          \'hook\': true,\r\n          \'type\': 0\r\n        },\r\n        \'JUU5JTgyJTgwJUU3JUJBJUE2JUU2JTg4JTkwJUU1JThBJTlG\': {\r\n          \'section\': {\r\n            \'type\': \'end\',\r\n            \'voice\': [\r\n              \'f617d1661f1738a59d1cc03733c85a0ad6e56e35\'\r\n            ],\r\n            \'choice\': \'random\'\r\n          },\r\n          \'next\': \'\',\r\n          \'hook\': true,\r\n          \'type\': 2\r\n        }\r\n      },\r\n      \'keyword\': {\r\n        \'quiet1\': {\r\n          \'type\': 0,\r\n          \'voice\': [\r\n            \'cf34fe75dffec7282b54292bc8479dc76681b6c3\'\r\n          ],\r\n          \'choice\': \'random\',\r\n          \'conds\': [],\r\n          \'next\': \'\'\r\n        },\r\n        \'quiet2\': {\r\n          \'type\': 0,\r\n          \'voice\': [\r\n            \'cf34fe75dffec7282b54292bc8479dc76681b6c3\'\r\n          ],\r\n          \'choice\': \'random\',\r\n          \'conds\': [],\r\n          \'next\': \'\'\r\n        },\r\n        \'noword1\': {\r\n          \'type\': 0,\r\n          \'voice\': [\r\n            \'cf34fe75dffec7282b54292bc8479dc76681b6c3\'\r\n          ],\r\n          \'choice\': \'random\',\r\n          \'conds\': [],\r\n          \'next\': \'\'\r\n        },\r\n        \'noword2\': {\r\n          \'type\': 0,\r\n          \'voice\': [\r\n            \'cf34fe75dffec7282b54292bc8479dc76681b6c3\'\r\n          ],\r\n          \'choice\': \'random\',\r\n          \'conds\': [],\r\n          \'next\': \'\'\r\n        },\r\n        \'noword3\': {\r\n          \'type\': 0,\r\n          \'voice\': [\r\n            \'cf34fe75dffec7282b54292bc8479dc76681b6c3\'\r\n          ],\r\n          \'choice\': \'random\',\r\n          \'conds\': [],\r\n          \'next\': \'\'\r\n        },\r\n        \'JUU1JTg1JUFDJUU1JThGJUI4JUU1JTlDJUIwJUU1JTlEJTgw\': {\r\n          \'type\': 4,\r\n          \'keyword\': [\r\n            \'公司#25\',\r\n            \'地址#25\',\r\n            \'哪里#25\',\r\n            \'那里#25\',\r\n            \'地方#25\'\r\n          ],\r\n          \'voice\': [\r\n            \'7bd0b288eba4e14a69f2526f2e728e2afb72457d\',\r\n            \'afb4b8c7cbabaafc18653dcc9bf1e56b956fbe5c\'\r\n          ],\r\n          \'choice\': \'random\',\r\n          \'conds\': [],\r\n          \'next\': \'JUU4JUFGJUI3JUU2JUIxJTgyJUU4JUFFJUE5JUU0JUI4JUJCJUU3JUFFJUExJUU4JTgxJTk0JUU3JUIzJUJCJUU0JUJCJTk2\'\r\n        },\r\n        \'JUU4JUFGJUEyJUU5JTk3JUFFJUU2JTk4JUFGJUU1JTkwJUE2JUU2JUFEJUEzJUU4JUE3JTg0\': {\r\n          \'type\': 5,\r\n          \'keyword\': [\r\n            \'正规#25\'\r\n          ],\r\n          \'voice\': [\r\n            \'51940df26dfdd5d8ab1744fb0280c588d12f0fc0\'\r\n          ],\r\n          \'choice\': \'random\',\r\n          \'conds\': [],\r\n          \'next\': \'wait\'\r\n        },\r\n        \'JUU2JTlDJThEJUU1JThBJUExJUU2JTgwJThFJUU0JUI5JTg4JUU2JUEwJUI3\': {\r\n          \'type\': 4,\r\n          \'keyword\': [\r\n            \'服务#25\'\r\n          ],\r\n          \'voice\': [\r\n            \'b04888054d18c21f7f27ab88adeae6bf289b4786\'\r\n          ],\r\n          \'choice\': \'random\',\r\n          \'conds\': [],\r\n          \'next\': \'JUU4JUFGJUI3JUU2JUIxJTgyJUU4JUFFJUE5JUU0JUI4JUJCJUU3JUFFJUExJUU4JTgxJTk0JUU3JUIzJUJCJUU0JUJCJTk2\'\r\n        },\r\n        \'JUU4JUFGJUEyJUU5JTk3JUFFJUU1JTg1JUI2JUU0JUJCJTk2JUU0JUI4JTlBJUU1JThBJUExJUU1JTgxJTlBJUU0JUI4JThEJUU1JTgxJTlB\': {\r\n          \'type\': 5,\r\n          \'keyword\': [\r\n            \'飞机#25\',\r\n            \'飞船#25\',\r\n            \'大炮#25\',\r\n            \'走私#25\',\r\n            \'枪火#25\',\r\n            \'宇宙#25\',\r\n            \'白粉#25\',\r\n            \'租车#25\',\r\n            \'注销#25\'\r\n          ],\r\n          \'voice\': [],\r\n          \'choice\': \'random\',\r\n          \'conds\': [\r\n            {\r\n              \'keyword\': [\r\n                \'好的#25\',\r\n                \'可以#25\',\r\n                \'没问题#25\'\r\n              ],\r\n              \'to\': \'JUU4JUFGJUI3JUU2JUIxJTgyJUU4JUFFJUE5JUU0JUI4JUJCJUU3JUFFJUExJUU4JTgxJTk0JUU3JUIzJUJCJUU0JUJCJTk2\'\r\n            }\r\n          ],\r\n          \'next\': \'return\'\r\n        },\r\n        \'JUU2JTk0JUI2JUU4JUI0JUI5\': {\r\n          \'type\': 3,\r\n          \'keyword\': [\r\n            \'价格#25\',\r\n            \'价钱#25\',\r\n            \'收费#25\',\r\n            \'费用#25\',\r\n            \'多少钱#25\',\r\n            \'钱#25\'\r\n          ],\r\n          \'voice\': [\r\n            \'d55851338caa81a52c63936029b1e5f630c8927a\'\r\n          ],\r\n          \'choice\': \'d55851338caa81a52c63936029b1e5f630c8927a\',\r\n          \'conds\': [],\r\n          \'next\': \'JUU4JUFGJUI3JUU2JUIxJTgyJUU4JUFFJUE5JUU0JUI4JUJCJUU3JUFFJUExJUU4JTgxJTk0JUU3JUIzJUJCJUU0JUJCJTk2\'\r\n        },\r\n        \'JUU1JUFCJThDJUU4JUI0JUI1\': {\r\n          \'type\': 4,\r\n          \'keyword\': [\r\n            \'太贵#25\',\r\n            \'优惠#25\',\r\n            \'便宜#25\',\r\n            \'打折#25\',\r\n            \'折扣#25\'\r\n          ],\r\n          \'voice\': [\r\n            \'31b9367fddd568b68e5cbf81aee8fa94388b0708\'\r\n          ],\r\n          \'choice\': \'random\',\r\n          \'conds\': [],\r\n          \'next\': \'JUU4JUFGJUI3JUU2JUIxJTgyJUU4JUFFJUE5JUU0JUI4JUJCJUU3JUFFJUExJUU4JTgxJTk0JUU3JUIzJUJCJUU0JUJCJTk2\'\r\n        },\r\n        \'JUU1JUFFJUEyJUU2JTg4JUI3JUU2JTgwJTgwJUU3JTk2JTkxJUU2JTk4JUFGJUU2JTlDJUJBJUU1JTk5JUE4JUU0JUJBJUJB\': {\r\n          \'type\': 4,\r\n          \'keyword\': [\r\n            \'机器#25\',\r\n            \'电脑#25\',\r\n            \'软件#25\',\r\n            \'程序#25\',\r\n            \'自动#25\',\r\n            \'录音#25\'\r\n          ],\r\n          \'voice\': [\r\n            \'398999628876116b3eee7ebada46f72053195f19\'\r\n          ],\r\n          \'choice\': \'random\',\r\n          \'conds\': [],\r\n          \'next\': \'\'\r\n        },\r\n        \'JUU1JUFFJUEyJUU2JTg4JUI3JUU4JUFFJUE5JUU2JTlDJUJBJUU1JTk5JUE4JUU0JUJBJUJBJUU3JUFEJTg5JUU0JUI4JTgwJUU0JUI4JThC\': {\r\n          \'type\': 0,\r\n          \'keyword\': [\r\n            \'听我说#25\',\r\n            \'等下#25\',\r\n            \'等等#25\'\r\n          ],\r\n          \'voice\': [\r\n            \'4986490ca8ad2bb18348219d7c7827d5c0a300a1\',\r\n            \'c1556f29dc49977e38d8311a30857550ee3d5655\'\r\n          ],\r\n          \'choice\': \'random\',\r\n          \'conds\': [],\r\n          \'next\': \'wait\'\r\n        },\r\n        \'JUU1JUFFJUEyJUU2JTg4JUI3JUU4JUFGJUEyJUU5JTk3JUFFJUU2JTgwJThFJUU0JUI5JTg4JUU2JTlDJTg5JUU0JUJCJTk2JUU1JThGJUI3JUU3JUEwJTgx\': {\r\n          \'type\': 0,\r\n          \'keyword\': [\r\n            \'手机号#25\',\r\n            \'联系方式#25\'\r\n          ],\r\n          \'voice\': [\r\n            \'5380f4f7e8ff3bc6f810ecb8766f85618f6b8884\'\r\n          ],\r\n          \'choice\': \'random\',\r\n          \'conds\': [],\r\n          \'next\': \'wait\'\r\n        },\r\n        \'JUU4JUIwJTgzJUU2JTg4JThGJUU1JUFFJUEyJUU2JTlDJThE\': {\r\n          \'type\': 0,\r\n          \'keyword\': [\r\n            \'美女#25\',\r\n            \'几岁#25\',\r\n            \'约你#25\',\r\n            \'约会#25\',\r\n            \'请你#25\',\r\n            \'吃饭#25\',\r\n            \'多大#25\',\r\n            \'多少岁#25\',\r\n            \'声音好听#25\',\r\n            \'好听#25\'\r\n          ],\r\n          \'voice\': [\r\n            \'09caade6139421700add2d7dd2579a399322cff7\',\r\n            \'189509ef54a2cf03c756f026a656f3d93ba6fcd0\'\r\n          ],\r\n          \'choice\': \'random\',\r\n          \'conds\': [\r\n            {\r\n              \'keyword\': [\r\n                \'没有#25\',\r\n                \'不需要#25\',\r\n                \'不用#25\',\r\n                \'没#25\'\r\n              ],\r\n              \'to\': \'JUU4JUFGJUI3JUU2JUIxJTgyJUU4JUFFJUE5JUU0JUI4JUJCJUU3JUFFJUExJUU4JTgxJTk0JUU3JUIzJUJCJUU0JUJCJTk2\'\r\n            }\r\n          ],\r\n          \'next\': \'wait\'\r\n        }\r\n      },\r\n      \'voice\': {\r\n        \'f618559afa62c72f65b96d43aa8fad93ac30c835\': {\r\n          \'pcm\': \'2018\\\\06\\\\21\\\\f618559afa62c72f65b96d43aa8fad93ac30c835.pcm\',\r\n          \'path\': \'2018\\\\06\\\\21\\\\f618559afa62c72f65b96d43aa8fad93ac30c835.wav\',\r\n          \'filename\': \'3.1.1\',\r\n          \'text\': \'介绍并询问需求\'\r\n        },\r\n        \'26c31553335a1fd07f7776e2f0b36e942d762b1d\': {\r\n          \'pcm\': \'2018\\\\06\\\\21\\\\26c31553335a1fd07f7776e2f0b36e942d762b1d.pcm\',\r\n          \'path\': \'2018\\\\06\\\\21\\\\26c31553335a1fd07f7776e2f0b36e942d762b1d.wav\',\r\n          \'filename\': \'3.2.0\',\r\n          \'text\': \'介绍优势11111\'\r\n        },\r\n        \'520d7b28d0df45cfe302768b58ec1e5ac471ea3f\': {\r\n          \'pcm\': \'2018\\\\06\\\\21\\\\520d7b28d0df45cfe302768b58ec1e5ac471ea3f.pcm\',\r\n          \'path\': \'2018\\\\06\\\\21\\\\520d7b28d0df45cfe302768b58ec1e5ac471ea3f.wav\',\r\n          \'filename\': \'3.2.1\',\r\n          \'text\': \'介绍优势22222\'\r\n        },\r\n        \'06227df902d4350cadcffe0d17eeb499bd010ca4\': {\r\n          \'pcm\': \'2018\\\\06\\\\21\\\\06227df902d4350cadcffe0d17eeb499bd010ca4.pcm\',\r\n          \'path\': \'2018\\\\06\\\\21\\\\06227df902d4350cadcffe0d17eeb499bd010ca4.wav\',\r\n          \'filename\': \'3.1.1.1\',\r\n          \'text\': \'再次介绍，询问需要吗\'\r\n        },\r\n        \'41152b9f5842ffc4bc06145b102274f3a4e71d4a\': {\r\n          \'pcm\': \'2018\\\\06\\\\21\\\\41152b9f5842ffc4bc06145b102274f3a4e71d4a.pcm\',\r\n          \'path\': \'2018\\\\06\\\\21\\\\41152b9f5842ffc4bc06145b102274f3a4e71d4a.wav\',\r\n          \'filename\': \'3.4.2\',\r\n          \'text\': \'不好意思打扰了\'\r\n        },\r\n        \'c04cdee1a86ad6e3f66135d7d59ae6688c573a8a\': {\r\n          \'pcm\': \'2018\\\\06\\\\21\\\\c04cdee1a86ad6e3f66135d7d59ae6688c573a8a.pcm\',\r\n          \'path\': \'2018\\\\06\\\\21\\\\c04cdee1a86ad6e3f66135d7d59ae6688c573a8a.wav\',\r\n          \'filename\': \'3.3.1.ai1\',\r\n          \'text\': \'发资料，约时间\'\r\n        },\r\n        \'ca71b2e676b836b30b0622a11f18e86587e688e1\': {\r\n          \'pcm\': \'2018\\\\06\\\\21\\\\ca71b2e676b836b30b0622a11f18e86587e688e1.pcm\',\r\n          \'path\': \'2018\\\\06\\\\21\\\\ca71b2e676b836b30b0622a11f18e86587e688e1.wav\',\r\n          \'filename\': \'3.3.2.ai1\',\r\n          \'text\': \'让会计师去电话\'\r\n        },\r\n        \'d31fdca0d475bdf41d845d1812961b0b707527a1\': {\r\n          \'pcm\': \'2018\\\\06\\\\21\\\\d31fdca0d475bdf41d845d1812961b0b707527a1.pcm\',\r\n          \'path\': \'2018\\\\06\\\\21\\\\d31fdca0d475bdf41d845d1812961b0b707527a1.wav\',\r\n          \'filename\': \'3.3.2.ai2\',\r\n          \'text\': \'让会计师给你介绍\'\r\n        },\r\n        \'c2fea2182648516b42446bfa29cbad920315c6b4\': {\r\n          \'pcm\': \'2018\\\\06\\\\21\\\\c2fea2182648516b42446bfa29cbad920315c6b4.pcm\',\r\n          \'path\': \'2018\\\\06\\\\21\\\\c2fea2182648516b42446bfa29cbad920315c6b4.wav\',\r\n          \'filename\': \'4.2.1ai2\',\r\n          \'text\': \'挽回1\'\r\n        },\r\n        \'622dd290810bb00d60c87355ffe6096fad39594f\': {\r\n          \'pcm\': \'2018\\\\06\\\\21\\\\622dd290810bb00d60c87355ffe6096fad39594f.pcm\',\r\n          \'path\': \'2018\\\\06\\\\21\\\\622dd290810bb00d60c87355ffe6096fad39594f.wav\',\r\n          \'filename\': \'4.2.1ai1\',\r\n          \'text\': \'挽回2\'\r\n        },\r\n        \'7c2347a0d64ad422f1b3ba5c4ea5af693ddcbf74\': {\r\n          \'pcm\': \'2018\\\\06\\\\21\\\\7c2347a0d64ad422f1b3ba5c4ea5af693ddcbf74.pcm\',\r\n          \'path\': \'2018\\\\06\\\\21\\\\7c2347a0d64ad422f1b3ba5c4ea5af693ddcbf74.wav\',\r\n          \'filename\': \'4.2.1ai3\',\r\n          \'text\': \'挽回3\'\r\n        },\r\n        \'f617d1661f1738a59d1cc03733c85a0ad6e56e35\': {\r\n          \'pcm\': \'2018\\\\06\\\\21\\\\f617d1661f1738a59d1cc03733c85a0ad6e56e35.pcm\',\r\n          \'path\': \'2018\\\\06\\\\21\\\\f617d1661f1738a59d1cc03733c85a0ad6e56e35.wav\',\r\n          \'filename\': \'3.4.1\',\r\n          \'text\': \'邀约成功\'\r\n        },\r\n        \'cf34fe75dffec7282b54292bc8479dc76681b6c3\': {\r\n          \'pcm\': \'2018\\\\06\\\\21\\\\cf34fe75dffec7282b54292bc8479dc76681b6c3.pcm\',\r\n          \'path\': \'2018\\\\06\\\\21\\\\cf34fe75dffec7282b54292bc8479dc76681b6c3.wav\',\r\n          \'filename\': \'6.1.2.ai1\',\r\n          \'text\': \'没声音或没信号\'\r\n        },\r\n        \'7bd0b288eba4e14a69f2526f2e728e2afb72457d\': {\r\n          \'pcm\': \'2018\\\\06\\\\21\\\\7bd0b288eba4e14a69f2526f2e728e2afb72457d.pcm\',\r\n          \'path\': \'2018\\\\06\\\\21\\\\7bd0b288eba4e14a69f2526f2e728e2afb72457d.wav\',\r\n          \'filename\': \'5.2.1.ai1\',\r\n          \'text\': \'我们在xxx地方11\'\r\n        },\r\n        \'afb4b8c7cbabaafc18653dcc9bf1e56b956fbe5c\': {\r\n          \'pcm\': \'2018\\\\06\\\\21\\\\afb4b8c7cbabaafc18653dcc9bf1e56b956fbe5c.pcm\',\r\n          \'path\': \'2018\\\\06\\\\21\\\\afb4b8c7cbabaafc18653dcc9bf1e56b956fbe5c.wav\',\r\n          \'filename\': \'5.2.2.ai1\',\r\n          \'text\': \'我们在yyy地方22\'\r\n        },\r\n        \'51940df26dfdd5d8ab1744fb0280c588d12f0fc0\': {\r\n          \'pcm\': \'2018\\\\06\\\\21\\\\51940df26dfdd5d8ab1744fb0280c588d12f0fc0.pcm\',\r\n          \'path\': \'2018\\\\06\\\\21\\\\51940df26dfdd5d8ab1744fb0280c588d12f0fc0.wav\',\r\n          \'filename\': \'5.1.5.ai1\',\r\n          \'text\': \'我们是正规的公司\'\r\n        },\r\n        \'b04888054d18c21f7f27ab88adeae6bf289b4786\': {\r\n          \'pcm\': \'2018\\\\06\\\\21\\\\b04888054d18c21f7f27ab88adeae6bf289b4786.pcm\',\r\n          \'path\': \'2018\\\\06\\\\21\\\\b04888054d18c21f7f27ab88adeae6bf289b4786.wav\',\r\n          \'filename\': \'5.1.8.ai1\',\r\n          \'text\': \'服务一对一，绝对专业\'\r\n        },\r\n        \'d55851338caa81a52c63936029b1e5f630c8927a\': {\r\n          \'pcm\': \'2018\\\\06\\\\21\\\\d55851338caa81a52c63936029b1e5f630c8927a.pcm\',\r\n          \'path\': \'2018\\\\06\\\\21\\\\d55851338caa81a52c63936029b1e5f630c8927a.wav\',\r\n          \'filename\': \'5.3.1ai1\',\r\n          \'text\': \'介绍价格，小规模200\'\r\n        },\r\n        \'31b9367fddd568b68e5cbf81aee8fa94388b0708\': {\r\n          \'pcm\': \'2018\\\\06\\\\21\\\\31b9367fddd568b68e5cbf81aee8fa94388b0708.pcm\',\r\n          \'path\': \'2018\\\\06\\\\21\\\\31b9367fddd568b68e5cbf81aee8fa94388b0708.wav\',\r\n          \'filename\': \'5.3.2.ai1\',\r\n          \'text\': \'服务很厉害，可以体验一下\'\r\n        },\r\n        \'398999628876116b3eee7ebada46f72053195f19\': {\r\n          \'pcm\': \'2018\\\\06\\\\21\\\\398999628876116b3eee7ebada46f72053195f19.pcm\',\r\n          \'path\': \'2018\\\\06\\\\21\\\\398999628876116b3eee7ebada46f72053195f19.wav\',\r\n          \'filename\': \'6.1.3.ai1\',\r\n          \'text\': \'我不是机器人，统一话术而已\'\r\n        },\r\n        \'4986490ca8ad2bb18348219d7c7827d5c0a300a1\': {\r\n          \'pcm\': \'2018\\\\06\\\\21\\\\4986490ca8ad2bb18348219d7c7827d5c0a300a1.pcm\',\r\n          \'path\': \'2018\\\\06\\\\21\\\\4986490ca8ad2bb18348219d7c7827d5c0a300a1.wav\',\r\n          \'filename\': \'6.1.7ai1\',\r\n          \'text\': \'好的，你请讲\'\r\n        },\r\n        \'c1556f29dc49977e38d8311a30857550ee3d5655\': {\r\n          \'pcm\': \'2018\\\\06\\\\21\\\\c1556f29dc49977e38d8311a30857550ee3d5655.pcm\',\r\n          \'path\': \'2018\\\\06\\\\21\\\\c1556f29dc49977e38d8311a30857550ee3d5655.wav\',\r\n          \'filename\': \'6.1.8.ai1\',\r\n          \'text\': \'好的你请说\'\r\n        },\r\n        \'5380f4f7e8ff3bc6f810ecb8766f85618f6b8884\': {\r\n          \'pcm\': \'2018\\\\06\\\\21\\\\5380f4f7e8ff3bc6f810ecb8766f85618f6b8884.pcm\',\r\n          \'path\': \'2018\\\\06\\\\21\\\\5380f4f7e8ff3bc6f810ecb8766f85618f6b8884.wav\',\r\n          \'filename\': \'6.1.10.ai1\',\r\n          \'text\': \'公司分配的号码，可能你在网上留过号码\'\r\n        },\r\n        \'09caade6139421700add2d7dd2579a399322cff7\': {\r\n          \'pcm\': \'2018\\\\06\\\\21\\\\09caade6139421700add2d7dd2579a399322cff7.pcm\',\r\n          \'path\': \'2018\\\\06\\\\21\\\\09caade6139421700add2d7dd2579a399322cff7.wav\',\r\n          \'filename\': \'6.1.11.ai1\',\r\n          \'text\': \'不方便回答这个问题11\'\r\n        },\r\n        \'189509ef54a2cf03c756f026a656f3d93ba6fcd0\': {\r\n          \'pcm\': \'2018\\\\06\\\\21\\\\189509ef54a2cf03c756f026a656f3d93ba6fcd0.pcm\',\r\n          \'path\': \'2018\\\\06\\\\21\\\\189509ef54a2cf03c756f026a656f3d93ba6fcd0.wav\',\r\n          \'filename\': \'6.1.12.ai1\',\r\n          \'text\': \'不方便回答这个问题222\'\r\n        }\r\n      },\r\n      \'type\': [\r\n        {\r\n          \'name\': \'未分类\',\r\n          \'rule\': \'time:0,10;word:0,5;section:0,10\'\r\n        },\r\n        {\r\n          \'name\': \'空号\',\r\n          \'rule\': \'\'\r\n        },\r\n        {\r\n          \'name\': \'A类\',\r\n          \'rule\': \'\'\r\n        },\r\n        {\r\n          \'name\': \'B类\',\r\n          \'rule\': \'\'\r\n        },\r\n        {\r\n          \'name\': \'C类\',\r\n          \'rule\': \'\'\r\n        },\r\n        {\r\n          \'name\': \'D类\',\r\n          \'rule\': \'\'\r\n        },\r\n        {\r\n          \'name\': \'E类\',\r\n          \'rule\': \'\'\r\n        },\r\n        {\r\n          \'name\': \'F类\',\r\n          \'rule\': \'\'\r\n        }\r\n      ]\r\n    }',0,'2018-07-09 12:19:45'),(4,'asdfaasdfasdf','admin','{\"main\":\"\",\"flow\":{\"YXNkZmFzZGY=\":{\"section\":{\"type\":\"condition\",\"voice\":[],\"choice\":\"random\",\"conds\":[]},\"next\":\"\",\"hook\":true,\"type\":0}},\"keyword\":{\"quiet1\":{\"type\":0,\"voice\":[],\"choice\":\"random\",\"conds\":[],\"next\":\"\"},\"quiet2\":{\"type\":0,\"voice\":[],\"choice\":\"random\",\"conds\":[],\"next\":\"\"},\"noword1\":{\"type\":0,\"voice\":[],\"choice\":\"random\",\"conds\":[],\"next\":\"\"},\"noword2\":{\"type\":0,\"voice\":[],\"choice\":\"random\",\"conds\":[],\"next\":\"\"},\"noword3\":{\"type\":0,\"voice\":[],\"choice\":\"random\",\"conds\":[],\"next\":\"\"}},\"voice\":{},\"type\":[{\"name\":\"未分类\",\"rule\":\"time:0,10;word:0,5;section:0,10\"},{\"name\":\"空号\",\"rule\":\"\"},{\"name\":\"A类\",\"rule\":\"\"},{\"name\":\"B类\",\"rule\":\"\"},{\"name\":\"C类\",\"rule\":\"\"},{\"name\":\"D类\",\"rule\":\"\"},{\"name\":\"E类\",\"rule\":\"\"},{\"name\":\"F类\",\"rule\":\"\"}]}',3,'2018-07-10 22:01:49');
/*!40000 ALTER TABLE `template` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_config`
--

DROP TABLE IF EXISTS `user_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_config` (
  `user_id` varchar(30) NOT NULL,
  `key` varchar(50) NOT NULL,
  `value` text COMMENT '配置值'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户配置表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_config`
--

LOCK TABLES `user_config` WRITE;
/*!40000 ALTER TABLE `user_config` DISABLE KEYS */;
INSERT INTO `user_config` VALUES ('admin','schedule','{}');
/*!40000 ALTER TABLE `user_config` ENABLE KEYS */;
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
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(30) NOT NULL,
  `start_at` datetime NOT NULL COMMENT '开始工作的时间，包括开始时间',
  `end_at` datetime NOT NULL COMMENT '结束工作的时间，不包括结束时间',
  `repeat` tinyint(4) NOT NULL COMMENT '记录重复规则，通过7位2进制表示一周7天，从左到右依次为，周一到周日。如果对应的位为1，表示这一天是需要工作。',
  PRIMARY KEY (`id`),
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
INSERT INTO `voice` VALUES ('00d8e90c67215f63c853bcf280388f2de2b0b0f4','2018\\07\\10\\00d8e90c67215f63c853bcf280388f2de2b0b0f4.wav','2018\\07\\10\\00d8e90c67215f63c853bcf280388f2de2b0b0f4.pcm','25','2018-07-10 22:54:28'),('01be40dcdc49605656e20a1fdfdd09dc4f7f32ec','2018\\07\\10\\01be40dcdc49605656e20a1fdfdd09dc4f7f32ec.wav','2018\\07\\10\\01be40dcdc49605656e20a1fdfdd09dc4f7f32ec.pcm','16','2018-07-10 22:29:09'),('0ff4ad654d4e5259a2c12cf905675ee0f2500cf5','2018\\07\\10\\0ff4ad654d4e5259a2c12cf905675ee0f2500cf5.wav','2018\\07\\10\\0ff4ad654d4e5259a2c12cf905675ee0f2500cf5.pcm','34','2018-07-10 23:07:18'),('128da7f0807efe24e147ada0b0dbce376e12aef2','2018\\07\\10\\128da7f0807efe24e147ada0b0dbce376e12aef2.wav','2018\\07\\10\\128da7f0807efe24e147ada0b0dbce376e12aef2.pcm','20','2018-07-10 22:44:02'),('1cf64885d7f6002e45378543fb9adeecdafa1a49','2018\\07\\10\\1cf64885d7f6002e45378543fb9adeecdafa1a49.wav','2018\\07\\10\\1cf64885d7f6002e45378543fb9adeecdafa1a49.pcm','27','2018-07-10 22:56:00'),('2e225320aa67d3793bcf14edfc9a436547856eee','2018\\07\\10\\2e225320aa67d3793bcf14edfc9a436547856eee.wav','2018\\07\\10\\2e225320aa67d3793bcf14edfc9a436547856eee.pcm','11','2018-07-10 20:53:10'),('2f8e41a86a111deabcd2b83277a70f8871f86c56','2018\\07\\10\\2f8e41a86a111deabcd2b83277a70f8871f86c56.wav','2018\\07\\10\\2f8e41a86a111deabcd2b83277a70f8871f86c56.pcm','2','2018-07-10 21:11:02'),('3cef980ffc3124f2e35f060b025f75e554914895','2018\\07\\10\\3cef980ffc3124f2e35f060b025f75e554914895.wav','2018\\07\\10\\3cef980ffc3124f2e35f060b025f75e554914895.pcm','5.1.1.ai1','2018-07-10 22:19:05'),('3db1727276984fb31669f751e2631eddbf72497b','2018\\07\\10\\3db1727276984fb31669f751e2631eddbf72497b.wav','2018\\07\\10\\3db1727276984fb31669f751e2631eddbf72497b.pcm','36','2018-07-10 23:08:33'),('41152b9f5842ffc4bc06145b102274f3a4e71d4a','2018\\07\\10\\41152b9f5842ffc4bc06145b102274f3a4e71d4a.wav','2018\\07\\10\\41152b9f5842ffc4bc06145b102274f3a4e71d4a.pcm','3.4.2','2018-07-10 22:20:50'),('4438818f564ed16dfa01844a8033c1c8dee59c68','2018\\07\\10\\4438818f564ed16dfa01844a8033c1c8dee59c68.wav','2018\\07\\10\\4438818f564ed16dfa01844a8033c1c8dee59c68.pcm','31','2018-07-10 23:03:44'),('47c88fb61b1c17874ecbc13d5a065cdea4619bf1','2018\\07\\10\\47c88fb61b1c17874ecbc13d5a065cdea4619bf1.wav','2018\\07\\10\\47c88fb61b1c17874ecbc13d5a065cdea4619bf1.pcm','35','2018-07-10 21:56:00'),('535a12eff01cf6deb1176fd848362431f819efc7','2018\\07\\10\\535a12eff01cf6deb1176fd848362431f819efc7.wav','2018\\07\\10\\535a12eff01cf6deb1176fd848362431f819efc7.pcm','1','2018-07-10 20:08:45'),('5738a429e322cce6405ab55874b9265664a94797','2018\\07\\10\\5738a429e322cce6405ab55874b9265664a94797.wav','2018\\07\\10\\5738a429e322cce6405ab55874b9265664a94797.pcm','14','2018-07-10 21:49:11'),('5cf624717b30dba7b22b62161732a30005493c11','2018\\07\\10\\5cf624717b30dba7b22b62161732a30005493c11.wav','2018\\07\\10\\5cf624717b30dba7b22b62161732a30005493c11.pcm','24','2018-07-10 22:53:08'),('608df84ba67e12c512fae5002ab9fad2f27714c7','2018\\07\\10\\608df84ba67e12c512fae5002ab9fad2f27714c7.wav','2018\\07\\10\\608df84ba67e12c512fae5002ab9fad2f27714c7.pcm','12','2018-07-10 20:53:20'),('688fe966d6ffe68ec58b8156f91e4505365c3ab6','2018\\07\\10\\688fe966d6ffe68ec58b8156f91e4505365c3ab6.wav','2018\\07\\10\\688fe966d6ffe68ec58b8156f91e4505365c3ab6.pcm','19','2018-07-10 22:41:13'),('692e41f2a1f6010b350a5b69b1a64650012409d6','2018\\07\\10\\692e41f2a1f6010b350a5b69b1a64650012409d6.wav','2018\\07\\10\\692e41f2a1f6010b350a5b69b1a64650012409d6.pcm','35','2018-07-10 21:53:42'),('78be12d4d7a4f6bda1867d4427bf95f95f07e8e8','2018\\07\\10\\78be12d4d7a4f6bda1867d4427bf95f95f07e8e8.wav','2018\\07\\10\\78be12d4d7a4f6bda1867d4427bf95f95f07e8e8.pcm','29','2018-07-10 23:01:22'),('7cb518e2e44425f4e091489a10722bea0ae4bcf9','2018\\07\\10\\7cb518e2e44425f4e091489a10722bea0ae4bcf9.wav','2018\\07\\10\\7cb518e2e44425f4e091489a10722bea0ae4bcf9.pcm','9','2018-07-10 20:07:13'),('807cf2e89d9818185c138de3a8db527fbcaa75a6','2018\\07\\10\\807cf2e89d9818185c138de3a8db527fbcaa75a6.wav','2018\\07\\10\\807cf2e89d9818185c138de3a8db527fbcaa75a6.pcm','22','2018-07-10 22:48:31'),('8141dbcc261062a04b123a9ce5894dc8acb1fb0c','2018\\07\\10\\8141dbcc261062a04b123a9ce5894dc8acb1fb0c.wav','2018\\07\\10\\8141dbcc261062a04b123a9ce5894dc8acb1fb0c.pcm','18','2018-07-10 22:39:34'),('843343ec0694f1c4c472a001968fc4c8d875f8fc','2018\\07\\10\\843343ec0694f1c4c472a001968fc4c8d875f8fc.wav','2018\\07\\10\\843343ec0694f1c4c472a001968fc4c8d875f8fc.pcm','3','2018-07-10 20:12:57'),('8801ffcf687b4266914b76e4aa2c5a67b365d094','2018\\07\\10\\8801ffcf687b4266914b76e4aa2c5a67b365d094.wav','2018\\07\\10\\8801ffcf687b4266914b76e4aa2c5a67b365d094.pcm','41','2018-07-10 22:20:02'),('8dd0c34982fa9ea3487be35098517490c59b52ad','2018\\07\\10\\8dd0c34982fa9ea3487be35098517490c59b52ad.wav','2018\\07\\10\\8dd0c34982fa9ea3487be35098517490c59b52ad.pcm','5','2018-07-10 21:20:57'),('a54b8055088c787131ad1bad52504736d8c92ba6','2018\\07\\10\\a54b8055088c787131ad1bad52504736d8c92ba6.wav','2018\\07\\10\\a54b8055088c787131ad1bad52504736d8c92ba6.pcm','17','2018-07-10 22:37:29'),('a5f815548ca589be6253efc1c8c562ed3a814780','2018\\07\\10\\a5f815548ca589be6253efc1c8c562ed3a814780.wav','2018\\07\\10\\a5f815548ca589be6253efc1c8c562ed3a814780.pcm','23','2018-07-10 22:51:37'),('bed4999d9224a780640a4e8d3ff1885b30dd9270','2018\\07\\10\\bed4999d9224a780640a4e8d3ff1885b30dd9270.wav','2018\\07\\10\\bed4999d9224a780640a4e8d3ff1885b30dd9270.pcm','30','2018-07-10 23:02:36'),('bee4e29f6ca190e52321af29cb1dbe14b100f4e5','2018\\07\\10\\bee4e29f6ca190e52321af29cb1dbe14b100f4e5.wav','2018\\07\\10\\bee4e29f6ca190e52321af29cb1dbe14b100f4e5.pcm','32','2018-07-10 22:18:19'),('c04cdee1a86ad6e3f66135d7d59ae6688c573a8a','2018\\07\\10\\c04cdee1a86ad6e3f66135d7d59ae6688c573a8a.wav','2018\\07\\10\\c04cdee1a86ad6e3f66135d7d59ae6688c573a8a.pcm','3.3.1.ai1','2018-07-10 20:06:02'),('c0a43eaf793db93ddab5e4c8697cc0bd9f084c31','2018\\07\\10\\c0a43eaf793db93ddab5e4c8697cc0bd9f084c31.wav','2018\\07\\10\\c0a43eaf793db93ddab5e4c8697cc0bd9f084c31.pcm','4','2018-07-10 20:44:19'),('c2fea2182648516b42446bfa29cbad920315c6b4','2018\\07\\10\\c2fea2182648516b42446bfa29cbad920315c6b4.wav','2018\\07\\10\\c2fea2182648516b42446bfa29cbad920315c6b4.pcm','4.2.1ai2','2018-07-10 22:10:53'),('c73a2c158cd59e1687dc6c56cfaa8879ff8363b2','2018\\07\\10\\c73a2c158cd59e1687dc6c56cfaa8879ff8363b2.wav','2018\\07\\10\\c73a2c158cd59e1687dc6c56cfaa8879ff8363b2.pcm','10','2018-07-10 21:26:58'),('ceb6b50a524759fba8cedd9f51ae09d593be77c3','2018\\07\\10\\ceb6b50a524759fba8cedd9f51ae09d593be77c3.wav','2018\\07\\10\\ceb6b50a524759fba8cedd9f51ae09d593be77c3.pcm','28','2018-07-10 22:59:44'),('cfde722931c32e58bf739f12a1ddd293f26896b2','2018\\07\\10\\cfde722931c32e58bf739f12a1ddd293f26896b2.wav','2018\\07\\10\\cfde722931c32e58bf739f12a1ddd293f26896b2.pcm','13','2018-07-10 20:53:44'),('d3222453fca753129ce40f9f68ddd945afb898f9','2018\\07\\10\\d3222453fca753129ce40f9f68ddd945afb898f9.wav','2018\\07\\10\\d3222453fca753129ce40f9f68ddd945afb898f9.pcm','6','2018-07-10 21:21:39'),('dd6823ae7bb867cb6a8c61ef2b6abca243548bb4','2018\\07\\10\\dd6823ae7bb867cb6a8c61ef2b6abca243548bb4.wav','2018\\07\\10\\dd6823ae7bb867cb6a8c61ef2b6abca243548bb4.pcm','33','2018-07-10 23:06:16'),('e3bd741c4be852ff6d0932df1194bea862a184fc','2018\\07\\10\\e3bd741c4be852ff6d0932df1194bea862a184fc.wav','2018\\07\\10\\e3bd741c4be852ff6d0932df1194bea862a184fc.pcm','7','2018-07-10 21:22:18'),('ed1a07ce20fca27d9ae674750a06ba7701343c40','2018\\07\\10\\ed1a07ce20fca27d9ae674750a06ba7701343c40.wav','2018\\07\\10\\ed1a07ce20fca27d9ae674750a06ba7701343c40.pcm','15','2018-07-10 22:01:05'),('f617d1661f1738a59d1cc03733c85a0ad6e56e35','2018\\07\\10\\f617d1661f1738a59d1cc03733c85a0ad6e56e35.wav','2018\\07\\10\\f617d1661f1738a59d1cc03733c85a0ad6e56e35.pcm','3.4.1','2018-07-10 22:22:19');
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

-- Dump completed on 2018-07-10 23:12:22
