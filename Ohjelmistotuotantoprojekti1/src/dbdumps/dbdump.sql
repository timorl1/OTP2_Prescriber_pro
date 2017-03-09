-- MySQL dump 10.13  Distrib 5.7.9, for osx10.9 (x86_64)
--
-- Host: localhost    Database: applicationdb
-- ------------------------------------------------------
-- Server version	5.7.9

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
-- Current Database: `applicationdb`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `applicationdb` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `applicationdb`;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `MessageID` int(11) NOT NULL,
  `message` varchar(1000) DEFAULT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`MessageID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (1,'Testi viesti','2017-02-10 10:08:53');
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prescription`
--

DROP TABLE IF EXISTS `prescription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prescription` (
  `prescriptionID` int(11) NOT NULL AUTO_INCREMENT,
  `patientID` varchar(50) NOT NULL,
  `doctorID` int(11) NOT NULL,
  `diagnoseID` int(11) NOT NULL,
  `dose` double NOT NULL,
  `timesADay` int(11) NOT NULL,
  `info` varchar(1000) NOT NULL,
  `startDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `endDate` timestamp NULL DEFAULT NULL,
  `creationDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `drugID` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  PRIMARY KEY (`prescriptionID`),
  KEY `username` (`username`),
  CONSTRAINT `prescription_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prescription`
--

LOCK TABLES `prescription` WRITE;
/*!40000 ALTER TABLE `prescription` DISABLE KEYS */;
INSERT INTO `prescription` VALUES (1,'123456-789a',3,1,2,2,'Syö yksinäs','2017-03-08 16:11:51','2017-02-13 08:46:50','2017-02-13 10:46:50',123456,'doctor1');
/*!40000 ALTER TABLE `prescription` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `receiver`
--

DROP TABLE IF EXISTS `receiver`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `receiver` (
  `MessageID` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  PRIMARY KEY (`MessageID`,`username`),
  KEY `username` (`username`),
  CONSTRAINT `receiver_ibfk_1` FOREIGN KEY (`MessageID`) REFERENCES `message` (`MessageID`),
  CONSTRAINT `receiver_ibfk_2` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receiver`
--

LOCK TABLES `receiver` WRITE;
/*!40000 ALTER TABLE `receiver` DISABLE KEYS */;
INSERT INTO `receiver` VALUES (1,'admin');
/*!40000 ALTER TABLE `receiver` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sender`
--

DROP TABLE IF EXISTS `sender`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sender` (
  `MessageID` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  PRIMARY KEY (`MessageID`,`username`),
  KEY `username` (`username`),
  CONSTRAINT `sender_ibfk_1` FOREIGN KEY (`MessageID`) REFERENCES `message` (`MessageID`),
  CONSTRAINT `sender_ibfk_2` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sender`
--

LOCK TABLES `sender` WRITE;
/*!40000 ALTER TABLE `sender` DISABLE KEYS */;
INSERT INTO `sender` VALUES (1,'admin');
/*!40000 ALTER TABLE `sender` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `userID` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `usertype` int(11) NOT NULL,
  `firstname` varchar(50) NOT NULL,
  `lastname` varchar(50) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','admin','admin@sairaala.com',3,'admin','admin'),(3,'doctor1','doctor1','doctor@sairaala.com',2,'doctor','amazing'),(4,'doctormad','doctormad','imsocrazy@sairaala.com',2,'mr','wonderful'),(2,'nurse1','nurse1','nurse1@sairaala.com',1,'ivanka','trump');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Current Database: `sairausdb`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `sairausdb` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `sairausdb`;

--
-- Table structure for table `allergeenit`
--

DROP TABLE IF EXISTS `allergeenit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `allergeenit` (
  `allergeeniID` int(11) NOT NULL,
  `nimi` varchar(40) NOT NULL,
  PRIMARY KEY (`allergeeniID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `allergeenit`
--

LOCK TABLES `allergeenit` WRITE;
/*!40000 ALTER TABLE `allergeenit` DISABLE KEYS */;
INSERT INTO `allergeenit` VALUES (1,'Kala');
/*!40000 ALTER TABLE `allergeenit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rajoitteet`
--

DROP TABLE IF EXISTS `rajoitteet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rajoitteet` (
  `sairausid` int(11) NOT NULL,
  `allergeeniID` int(11) NOT NULL,
  PRIMARY KEY (`sairausid`,`allergeeniID`),
  KEY `allergeeniID` (`allergeeniID`),
  CONSTRAINT `rajoitteet_ibfk_1` FOREIGN KEY (`sairausid`) REFERENCES `sairaus` (`sairausid`),
  CONSTRAINT `rajoitteet_ibfk_2` FOREIGN KEY (`allergeeniID`) REFERENCES `allergeenit` (`allergeeniID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rajoitteet`
--

LOCK TABLES `rajoitteet` WRITE;
/*!40000 ALTER TABLE `rajoitteet` DISABLE KEYS */;
INSERT INTO `rajoitteet` VALUES (1,1);
/*!40000 ALTER TABLE `rajoitteet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sairaus`
--

DROP TABLE IF EXISTS `sairaus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sairaus` (
  `sairausid` int(11) NOT NULL,
  `nimi` varchar(40) NOT NULL,
  `kuvaus` varchar(1000) NOT NULL,
  PRIMARY KEY (`sairausid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sairaus`
--

LOCK TABLES `sairaus` WRITE;
/*!40000 ALTER TABLE `sairaus` DISABLE KEYS */;
INSERT INTO `sairaus` VALUES (1,'Äkkikuolema','Saattaa potilas kupsahtaa');
/*!40000 ALTER TABLE `sairaus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Current Database: `sairaaladb`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `sairaaladb` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `sairaaladb`;

--
-- Table structure for table `diagnoosi`
--

DROP TABLE IF EXISTS `diagnoosi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `diagnoosi` (
  `diagnoosiID` int(11) NOT NULL,
  `sairaustunniste` int(11) NOT NULL,
  `epikriisi` varchar(1000) DEFAULT NULL,
  `luontipäivä` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `päättymispäivä` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `hetu` varchar(15) NOT NULL,
  `työntekijänumero` int(11) NOT NULL,
  PRIMARY KEY (`diagnoosiID`),
  KEY `hetu` (`hetu`),
  KEY `työntekijänumero` (`työntekijänumero`),
  CONSTRAINT `diagnoosi_ibfk_1` FOREIGN KEY (`hetu`) REFERENCES `potilas` (`hetu`),
  CONSTRAINT `diagnoosi_ibfk_2` FOREIGN KEY (`työntekijänumero`) REFERENCES `työntekijä` (`työntekijänumero`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diagnoosi`
--

LOCK TABLES `diagnoosi` WRITE;
/*!40000 ALTER TABLE `diagnoosi` DISABLE KEYS */;
INSERT INTO `diagnoosi` VALUES (1,1,'Potilaalla havaittu äkillinen hengen menetys','2017-02-10 10:40:38','2017-02-10 10:40:40','123456-789a',1);
/*!40000 ALTER TABLE `diagnoosi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hoitosuhde`
--

DROP TABLE IF EXISTS `hoitosuhde`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hoitosuhde` (
  `työntekijänumero` int(11) NOT NULL,
  `hetu` varchar(15) NOT NULL,
  PRIMARY KEY (`työntekijänumero`,`hetu`),
  KEY `hetu` (`hetu`),
  CONSTRAINT `hoitosuhde_ibfk_1` FOREIGN KEY (`työntekijänumero`) REFERENCES `työntekijä` (`työntekijänumero`),
  CONSTRAINT `hoitosuhde_ibfk_2` FOREIGN KEY (`hetu`) REFERENCES `potilas` (`hetu`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoitosuhde`
--

LOCK TABLES `hoitosuhde` WRITE;
/*!40000 ALTER TABLE `hoitosuhde` DISABLE KEYS */;
INSERT INTO `hoitosuhde` VALUES (1,'123456-789a');
/*!40000 ALTER TABLE `hoitosuhde` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `potilas`
--

DROP TABLE IF EXISTS `potilas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `potilas` (
  `hetu` varchar(15) NOT NULL,
  `etunimi` varchar(40) NOT NULL,
  `sukunimi` varchar(40) NOT NULL,
  `pituus` double NOT NULL,
  `paino` double NOT NULL,
  `sukupuoli` varchar(10) NOT NULL,
  `osoite` varchar(50) DEFAULT NULL,
  `postinumero` int(11) DEFAULT NULL,
  `kaupunki` varchar(40) DEFAULT NULL,
  `maa` varchar(40) DEFAULT NULL,
  `puhelinnumero` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`hetu`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `potilas`
--

LOCK TABLES `potilas` WRITE;
/*!40000 ALTER TABLE `potilas` DISABLE KEYS */;
INSERT INTO `potilas` VALUES ('123456-789a','Potilas','Saarinen',160,65,'Mies','Bule 29',420,'Helsinki','Suomi','040-12344556'),('987654-321a','Patient','Zero',180,50,'Mies','Bule 31',420,'Helsinki','Suomi','050-11122333');
/*!40000 ALTER TABLE `potilas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `työntekijä`
--

DROP TABLE IF EXISTS `työntekijä`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `työntekijä` (
  `työntekijänumero` int(11) NOT NULL,
  `etunimi` varchar(40) NOT NULL,
  `sukunimi` varchar(40) NOT NULL,
  `osoite` varchar(50) NOT NULL,
  `postinumero` int(11) NOT NULL,
  `kaupunki` varchar(40) NOT NULL,
  `maa` varchar(40) NOT NULL,
  `puhelinnumero` varchar(40) NOT NULL,
  `pankkiyhteys` varchar(40) NOT NULL,
  `titteli` varchar(40) NOT NULL,
  `sposti` varchar(45) NOT NULL,
  PRIMARY KEY (`työntekijänumero`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `työntekijä`
--

LOCK TABLES `työntekijä` WRITE;
/*!40000 ALTER TABLE `työntekijä` DISABLE KEYS */;
INSERT INTO `työntekijä` VALUES (1,'Irvi','Kaikkonen','Bule 31',180,'Helsinki','Suomi','112','123543-123456','Ylläpitäjä','admin@sairaala.com'),(2,'Marja','Makkonen','Bule 31',180,'Helsinki','Suomi','112','632918-382901','Hoitaja','nurse1@sairaala.com'),(3,'Leku','Nordqvist','Bule 31',180,'Helsinki','Suomi','112','12720921-68921','Lääkäri','doctor1@sairaala.com'),(4,'Frank E.','nStein','Jakomäentie 13',666,'Helsinki','Suomi','911','672169-62891','Lääkäri','doctormad@sairaala.com');
/*!40000 ALTER TABLE `työntekijä` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Current Database: `lääkedb`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `lääkedb` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `lääkedb`;

--
-- Table structure for table `allergeeni`
--

DROP TABLE IF EXISTS `allergeeni`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `allergeeni` (
  `id` int(11) NOT NULL,
  `nimi` varchar(40) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `allergeeni`
--

LOCK TABLES `allergeeni` WRITE;
/*!40000 ALTER TABLE `allergeeni` DISABLE KEYS */;
INSERT INTO `allergeeni` VALUES (1,'Kala');
/*!40000 ALTER TABLE `allergeeni` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `haittavaikutus`
--

DROP TABLE IF EXISTS `haittavaikutus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `haittavaikutus` (
  `id` int(11) NOT NULL,
  `sairaus_id` int(11) NOT NULL,
  `nimi` varchar(40) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `haittavaikutus`
--

LOCK TABLES `haittavaikutus` WRITE;
/*!40000 ALTER TABLE `haittavaikutus` DISABLE KEYS */;
INSERT INTO `haittavaikutus` VALUES (1,1,'Äkkikuolema'),(2,2,'Päänsärky');
/*!40000 ALTER TABLE `haittavaikutus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `harvinainen`
--

DROP TABLE IF EXISTS `harvinainen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `harvinainen` (
  `haittavaikutus_id` int(11) NOT NULL,
  `lääke_tuotenumero` int(11) NOT NULL,
  PRIMARY KEY (`haittavaikutus_id`,`lääke_tuotenumero`),
  KEY `tuotenumero` (`lääke_tuotenumero`),
  CONSTRAINT `harvinainen_ibfk_1` FOREIGN KEY (`haittavaikutus_id`) REFERENCES `haittavaikutus` (`id`),
  CONSTRAINT `harvinainen_ibfk_2` FOREIGN KEY (`lääke_tuotenumero`) REFERENCES `lääke` (`tuotenumero`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `harvinainen`
--

LOCK TABLES `harvinainen` WRITE;
/*!40000 ALTER TABLE `harvinainen` DISABLE KEYS */;
INSERT INTO `harvinainen` VALUES (1,123456);
/*!40000 ALTER TABLE `harvinainen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lääke`
--

DROP TABLE IF EXISTS `lääke`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lääke` (
  `tuotenumero` int(11) NOT NULL,
  `nimi` varchar(40) NOT NULL,
  `yksikkö` varchar(40) NOT NULL,
  PRIMARY KEY (`tuotenumero`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lääke`
--

LOCK TABLES `lääke` WRITE;
/*!40000 ALTER TABLE `lääke` DISABLE KEYS */;
INSERT INTO `lääke` VALUES (123456,'Burana','kpl');
/*!40000 ALTER TABLE `lääke` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lääkkeen_allergeeni`
--

DROP TABLE IF EXISTS `lääkkeen_allergeeni`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lääkkeen_allergeeni` (
  `lääke_tuotenumero` int(11) NOT NULL,
  `allergeeni_id` int(11) NOT NULL,
  PRIMARY KEY (`lääke_tuotenumero`,`allergeeni_id`),
  KEY `allergeeniID` (`allergeeni_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lääkkeen_allergeeni`
--

LOCK TABLES `lääkkeen_allergeeni` WRITE;
/*!40000 ALTER TABLE `lääkkeen_allergeeni` DISABLE KEYS */;
INSERT INTO `lääkkeen_allergeeni` VALUES (123456,1);
/*!40000 ALTER TABLE `lääkkeen_allergeeni` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lääkkeen_vaikuttava_aine`
--

DROP TABLE IF EXISTS `lääkkeen_vaikuttava_aine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lääkkeen_vaikuttava_aine` (
  `lääke_tuotenumero` int(11) NOT NULL,
  `vaikuttava_aine_id` int(11) NOT NULL,
  `pitoisuus` double NOT NULL,
  `lääkkeen_vaikuttava_aine_id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`lääkkeen_vaikuttava_aine_id`),
  KEY `fk_lääke` (`lääke_tuotenumero`),
  KEY `fk_vaikuttava_aine` (`vaikuttava_aine_id`),
  CONSTRAINT `fk_lääke` FOREIGN KEY (`lääke_tuotenumero`) REFERENCES `lääke` (`tuotenumero`),
  CONSTRAINT `fk_vaikuttava_aine` FOREIGN KEY (`vaikuttava_aine_id`) REFERENCES `vaikuttava_aine` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lääkkeen_vaikuttava_aine`
--

LOCK TABLES `lääkkeen_vaikuttava_aine` WRITE;
/*!40000 ALTER TABLE `lääkkeen_vaikuttava_aine` DISABLE KEYS */;
INSERT INTO `lääkkeen_vaikuttava_aine` VALUES (123456,4312,400,1);
/*!40000 ALTER TABLE `lääkkeen_vaikuttava_aine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vaikuttava_aine`
--

DROP TABLE IF EXISTS `vaikuttava_aine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vaikuttava_aine` (
  `id` int(11) NOT NULL,
  `nimi` varchar(40) NOT NULL,
  `maxannos` double NOT NULL,
  `suositeltuannos` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vaikuttava_aine`
--

LOCK TABLES `vaikuttava_aine` WRITE;
/*!40000 ALTER TABLE `vaikuttava_aine` DISABLE KEYS */;
INSERT INTO `vaikuttava_aine` VALUES (4312,'Ibuprofeeni',7,3);
/*!40000 ALTER TABLE `vaikuttava_aine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yleinen`
--

DROP TABLE IF EXISTS `yleinen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `yleinen` (
  `haittavaikutus_id` int(11) NOT NULL,
  `lääke_tuotenumero` int(11) NOT NULL,
  PRIMARY KEY (`haittavaikutus_id`,`lääke_tuotenumero`),
  KEY `tuotenumero` (`lääke_tuotenumero`),
  CONSTRAINT `yleinen_ibfk_1` FOREIGN KEY (`haittavaikutus_id`) REFERENCES `haittavaikutus` (`id`),
  CONSTRAINT `yleinen_ibfk_2` FOREIGN KEY (`lääke_tuotenumero`) REFERENCES `lääke` (`tuotenumero`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yleinen`
--

LOCK TABLES `yleinen` WRITE;
/*!40000 ALTER TABLE `yleinen` DISABLE KEYS */;
INSERT INTO `yleinen` VALUES (2,123456);
/*!40000 ALTER TABLE `yleinen` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-03-09 12:24:08
