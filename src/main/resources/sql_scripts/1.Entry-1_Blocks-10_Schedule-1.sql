USE mumsched;

-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: mumsched
-- ------------------------------------------------------
-- Server version	5.7.20-log

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
-- Table structure for table `block`
--

DROP TABLE IF EXISTS `block`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `block` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `enddate` date DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `serial` int(11) DEFAULT NULL,
  `startdate` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `block`
--

LOCK TABLES `block` WRITE;
/*!40000 ALTER TABLE `block` DISABLE KEYS */;
INSERT INTO `block` VALUES (1,'2018-01-31','JAN',1,'2018-01-01'),(2,'2018-02-28','FEB',2,'2018-02-01'),(3,'2018-03-31','MAR',3,'2018-03-01'),(4,'2018-04-30','APR',4,'2018-04-01'),(5,'2018-05-31','MAY',5,'2018-05-01'),(6,'2018-06-30','JUNE',6,'2018-06-01'),(7,'2018-07-31','JUL',7,'2018-07-01'),(8,'2018-08-31','AUG',8,'2018-08-01'),(9,'2018-09-30','SEP',9,'2018-09-01'),(10,'2018-10-31','OCT',10,'2018-10-01');
/*!40000 ALTER TABLE `block` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `block_sections`
--

DROP TABLE IF EXISTS `block_sections`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `block_sections` (
  `block_id` bigint(20) NOT NULL,
  `section_id` bigint(20) NOT NULL,
  PRIMARY KEY (`block_id`,`section_id`),
  UNIQUE KEY `UK_r63xlxvr762wv0uk5nou20uyw` (`section_id`),
  CONSTRAINT `FK2f46wegoonjk13k9smc5tlvtd` FOREIGN KEY (`block_id`) REFERENCES `block` (`id`),
  CONSTRAINT `FKaocq49br601mbm027ymhqxlpq` FOREIGN KEY (`section_id`) REFERENCES `section` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `block_sections`
--

LOCK TABLES `block_sections` WRITE;
/*!40000 ALTER TABLE `block_sections` DISABLE KEYS */;
/*!40000 ALTER TABLE `block_sections` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `capacity` int(11) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_prerequisites`
--

DROP TABLE IF EXISTS `course_prerequisites`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course_prerequisites` (
  `course_id` bigint(20) NOT NULL,
  `prerequisites_id` bigint(20) NOT NULL,
  PRIMARY KEY (`course_id`,`prerequisites_id`),
  KEY `FK23d7t0rautc42mi7uhk2dp6q8` (`prerequisites_id`),
  CONSTRAINT `FK23d7t0rautc42mi7uhk2dp6q8` FOREIGN KEY (`prerequisites_id`) REFERENCES `course` (`id`),
  CONSTRAINT `FKemh7nrkoatej939evaclhippn` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_prerequisites`
--

LOCK TABLES `course_prerequisites` WRITE;
/*!40000 ALTER TABLE `course_prerequisites` DISABLE KEYS */;
/*!40000 ALTER TABLE `course_prerequisites` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entry`
--

DROP TABLE IF EXISTS `entry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entry` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `end_date` date DEFAULT NULL,
  `entry_name` varchar(255) DEFAULT NULL,
  `fpp_num` int(11) NOT NULL,
  `mpp_num` int(11) NOT NULL,
  `start_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entry`
--

LOCK TABLES `entry` WRITE;
/*!40000 ALTER TABLE `entry` DISABLE KEYS */;
INSERT INTO `entry` VALUES (2,'2018-01-01','Jan 2018',40,60,'2022-01-07');
/*!40000 ALTER TABLE `entry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entry_blocks`
--

DROP TABLE IF EXISTS `entry_blocks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entry_blocks` (
  `entry_id` bigint(20) NOT NULL,
  `blocks_id` bigint(20) NOT NULL,
  PRIMARY KEY (`entry_id`,`blocks_id`),
  KEY `FK8cawobnq9twjrb4vdvge2slwx` (`blocks_id`),
  CONSTRAINT `FK8cawobnq9twjrb4vdvge2slwx` FOREIGN KEY (`blocks_id`) REFERENCES `block` (`id`),
  CONSTRAINT `FKijeh38potcct1cy5e64qgrns9` FOREIGN KEY (`entry_id`) REFERENCES `entry` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entry_blocks`
--

LOCK TABLES `entry_blocks` WRITE;
/*!40000 ALTER TABLE `entry_blocks` DISABLE KEYS */;
INSERT INTO `entry_blocks` VALUES (2,1),(2,2),(2,3),(2,4),(2,5),(2,6),(2,7),(2,8),(2,9),(2,10);
/*!40000 ALTER TABLE `entry_blocks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faculty`
--

DROP TABLE IF EXISTS `faculty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `faculty` (
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `FKkud8s1mxu2g4v014nb8mvnr9w` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faculty`
--

LOCK TABLES `faculty` WRITE;
/*!40000 ALTER TABLE `faculty` DISABLE KEYS */;
/*!40000 ALTER TABLE `faculty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faculty_blocks`
--

DROP TABLE IF EXISTS `faculty_blocks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `faculty_blocks` (
  `faculty_user_id` bigint(20) NOT NULL,
  `blocks_id` bigint(20) NOT NULL,
  PRIMARY KEY (`faculty_user_id`,`blocks_id`),
  KEY `FK3fa8ymy2bi9w0ap8g8rvs526` (`blocks_id`),
  CONSTRAINT `FK3fa8ymy2bi9w0ap8g8rvs526` FOREIGN KEY (`blocks_id`) REFERENCES `block` (`id`),
  CONSTRAINT `FK9ylsyoeh8buypb365j5rxrlvc` FOREIGN KEY (`faculty_user_id`) REFERENCES `faculty` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faculty_blocks`
--

LOCK TABLES `faculty_blocks` WRITE;
/*!40000 ALTER TABLE `faculty_blocks` DISABLE KEYS */;
/*!40000 ALTER TABLE `faculty_blocks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faculty_courses`
--

DROP TABLE IF EXISTS `faculty_courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `faculty_courses` (
  `faculty_user_id` bigint(20) NOT NULL,
  `courses_id` bigint(20) NOT NULL,
  PRIMARY KEY (`faculty_user_id`,`courses_id`),
  KEY `FKfs13rvapa7jcggc46njhelvus` (`courses_id`),
  CONSTRAINT `FK1j1731aiv1s23ewve9m8qx292` FOREIGN KEY (`faculty_user_id`) REFERENCES `faculty` (`user_id`),
  CONSTRAINT `FKfs13rvapa7jcggc46njhelvus` FOREIGN KEY (`courses_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faculty_courses`
--

LOCK TABLES `faculty_courses` WRITE;
/*!40000 ALTER TABLE `faculty_courses` DISABLE KEYS */;
/*!40000 ALTER TABLE `faculty_courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ADMIN'),(2,'STUDENT'),(3,'FACULTY');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedule`
--

DROP TABLE IF EXISTS `schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `schedule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `entry_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKf9q6vu5fno1tif21p1x8fjgso` (`entry_id`),
  CONSTRAINT `FKf9q6vu5fno1tif21p1x8fjgso` FOREIGN KEY (`entry_id`) REFERENCES `entry` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule`
--

LOCK TABLES `schedule` WRITE;
/*!40000 ALTER TABLE `schedule` DISABLE KEYS */;
INSERT INTO `schedule` VALUES (1,'Schedule for Jan 2018 Entry','DRAFT',2);
/*!40000 ALTER TABLE `schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `section`
--

DROP TABLE IF EXISTS `section`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `section` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `capacity` int(11) NOT NULL,
  `course_id` bigint(20) NOT NULL,
  `faculty_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKae8e5y9octi2vgtfj6ksueboc` (`course_id`,`faculty_user_id`),
  KEY `FKrwvv5o1y3qsknxbhlnp196l6` (`faculty_user_id`),
  CONSTRAINT `FKoy8uc0ftpivwopwf5ptwdtar0` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`),
  CONSTRAINT `FKrwvv5o1y3qsknxbhlnp196l6` FOREIGN KEY (`faculty_user_id`) REFERENCES `faculty` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `section`
--

LOCK TABLES `section` WRITE;
/*!40000 ALTER TABLE `section` DISABLE KEYS */;
/*!40000 ALTER TABLE `section` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `FKk5m148xqefonqw7bgnpm0snwj` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `active` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `FKn82ha3ccdebhokx3a8fgdqeyy` (`role_id`),
  CONSTRAINT `FKn82ha3ccdebhokx3a8fgdqeyy` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-07 12:15:16
