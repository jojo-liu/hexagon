-- MySQL dump 10.13  Distrib 5.7.18, for macos10.12 (x86_64)
--
-- Host: 127.0.0.1    Database: hexagon
-- ------------------------------------------------------
-- Server version	5.7.18

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
-- Table structure for table `consultant`
--

DROP TABLE IF EXISTS `consultant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `consultant` (
  `consultantID` bigint(20) NOT NULL,
  `tagID` bigint(20) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(32) NOT NULL,
  `realName` varchar(45) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `avatar` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`consultantID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consultant`
--

LOCK TABLES `consultant` WRITE;
/*!40000 ALTER TABLE `consultant` DISABLE KEYS */;
/*!40000 ALTER TABLE `consultant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order` (
  `orderID` bigint(20) NOT NULL,
  `orderNo` varchar(45) NOT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `price` bigint(20) NOT NULL,
  `postID` bigint(20) NOT NULL,
  `consultantID` bigint(20) NOT NULL,
  `status` char(1) DEFAULT NULL,
  `payChannel` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`orderID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payChannel`
--

DROP TABLE IF EXISTS `payChannel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payChannel` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `payChannel` varchar(50) DEFAULT NULL COMMENT '支付渠道编号',
  `payChannelName` varchar(50) DEFAULT NULL COMMENT '支付渠道名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='支付渠道';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payChannel`
--

LOCK TABLES `payChannel` WRITE;
/*!40000 ALTER TABLE `payChannel` DISABLE KEYS */;
INSERT INTO `payChannel` VALUES (0,'0000000000','银联'),(1,'0000000001','支付宝'),(2,'0000000002','微信');
/*!40000 ALTER TABLE `payChannel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payInfo`
--

DROP TABLE IF EXISTS `payInfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payInfo` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `payOrderNo` varchar(50) DEFAULT NULL COMMENT '支付订单编号',
  `orderNo` varchar(50) DEFAULT NULL COMMENT '业务订单编号',
  `payChannel` varchar(50) DEFAULT NULL COMMENT '支付渠道编号',
  `status` char(1) DEFAULT NULL COMMENT '订单状态：I：初始，P：处理中，S：成功，F：失败',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='支付信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payInfo`
--

LOCK TABLES `payInfo` WRITE;
/*!40000 ALTER TABLE `payInfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `payInfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `post` (
  `postID` bigint(20) NOT NULL,
  `userID` bigint(20) NOT NULL,
  `tagID` bigint(20) NOT NULL,
  `title` varchar(128) NOT NULL,
  `summary` varchar(256) NOT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `price` int(11) NOT NULL,
  PRIMARY KEY (`postID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `session`
--

DROP TABLE IF EXISTS `session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `session` (
  `sessionID` bigint(20) NOT NULL,
  `accessToken` varchar(128) NOT NULL,
  `refreshToken` varchar(128) NOT NULL,
  `accessTokenValidTime` varchar(128) NOT NULL,
  `refreshTokenTime` varchar(128) NOT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `userID` bigint(20) NOT NULL,
  PRIMARY KEY (`sessionID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `session`
--

LOCK TABLES `session` WRITE;
/*!40000 ALTER TABLE `session` DISABLE KEYS */;
/*!40000 ALTER TABLE `session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tag` (
  `tagID` bigint(20) NOT NULL,
  `createrID` bigint(20) NOT NULL,
  `name` varchar(45) NOT NULL,
  `code` varchar(45) NOT NULL,
  `parent` bigint(20) DEFAULT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`tagID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test`
--

DROP TABLE IF EXISTS `test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test`
--

LOCK TABLES `test` WRITE;
/*!40000 ALTER TABLE `test` DISABLE KEYS */;
INSERT INTO `test` VALUES (1,'lqq','woodheaven');
/*!40000 ALTER TABLE `test` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `userID` bigint(20) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(32) NOT NULL,
  `realName` varchar(45) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `avatar` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-31 22:46:00
