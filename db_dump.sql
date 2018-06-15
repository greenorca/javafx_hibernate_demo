CREATE DATABASE  IF NOT EXISTS `JavaHibernation_EmployeeDb` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `JavaHibernation_EmployeeDb`;
-- MySQL dump 10.13  Distrib 5.7.22, for Linux (x86_64)
--
-- Host: localhost    Database: JavaHibernation_EmployeeDb
-- ------------------------------------------------------
-- Server version	5.7.22

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
-- Table structure for table `Person`
--

DROP TABLE IF EXISTS `Person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Person` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `nachname` varchar(255) DEFAULT NULL,
  `ort` varchar(255) DEFAULT NULL,
  `salary` int(11) DEFAULT NULL,
  `strasse` varchar(255) DEFAULT NULL,
  `vorname` varchar(255) DEFAULT NULL,
  `plz` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Person`
--

LOCK TABLES `Person` WRITE;
/*!40000 ALTER TABLE `Person` DISABLE KEYS */;
INSERT INTO `Person` VALUES (3,'Welt','Würmlingen',0,'Wunderstrasse 123','Walter','9876'),(5,'Jodokus','Entenhausen',0,'Weihergasse 12','Alfred','1234'),(8,'Es eilig','Waldorf',0,'Warp 3','Wer hat','3333'),(9,'Wurst','Saustall',0,'Schlachthof 10','Hans','4433'),(10,'Ernsthaft','Lustighausen',0,'Uhustrasse 99','Ernst','9999'),(11,'Ängstlich','Schreckheim',0,'Argwohn 13','Alfons','6666'),(12,'Ehrlich','Zeugenhofen',0,'','Dorothe',NULL),(13,'Dankbar','Sonnenstadt',0,'Sonnenallee 333','Dagmar','3456'),(14,'Muster','Musterhausen',0,'Musterplatz 2','Markus','12345'),(15,'Lustig','Lachen',0,'Lillienweg 33','Lars','7654');
/*!40000 ALTER TABLE `Person` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-15  9:54:23
