-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.1.21-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for lääkedb
DROP DATABASE IF EXISTS `lääkedb`;
CREATE DATABASE IF NOT EXISTS `lääkedb` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `lääkedb`;

-- Dumping structure for table lääkedb.allergeeni
DROP TABLE IF EXISTS `allergeeni`;
CREATE TABLE IF NOT EXISTS `allergeeni` (
  `id` int(11) NOT NULL,
  `nimi` varchar(40) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table lääkedb.allergeeni: ~0 rows (approximately)
DELETE FROM `allergeeni`;
/*!40000 ALTER TABLE `allergeeni` DISABLE KEYS */;
INSERT INTO `allergeeni` (`id`, `nimi`) VALUES
	(1, 'Kala');
/*!40000 ALTER TABLE `allergeeni` ENABLE KEYS */;

-- Dumping structure for table lääkedb.haittavaikutus
DROP TABLE IF EXISTS `haittavaikutus`;
CREATE TABLE IF NOT EXISTS `haittavaikutus` (
  `id` int(11) NOT NULL,
  `sairaus_id` int(11) NOT NULL,
  `nimi` varchar(40) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table lääkedb.haittavaikutus: ~2 rows (approximately)
DELETE FROM `haittavaikutus`;
/*!40000 ALTER TABLE `haittavaikutus` DISABLE KEYS */;
INSERT INTO `haittavaikutus` (`id`, `sairaus_id`, `nimi`) VALUES
	(1, 1, 'Äkkikuolema'),
	(2, 2, 'Päänsärky');
/*!40000 ALTER TABLE `haittavaikutus` ENABLE KEYS */;

-- Dumping structure for table lääkedb.harvinainen
DROP TABLE IF EXISTS `harvinainen`;
CREATE TABLE IF NOT EXISTS `harvinainen` (
  `haittavaikutus_id` int(11) NOT NULL,
  `lääke_tuotenumero` int(11) NOT NULL,
  PRIMARY KEY (`haittavaikutus_id`,`lääke_tuotenumero`),
  KEY `tuotenumero` (`lääke_tuotenumero`),
  CONSTRAINT `harvinainen_ibfk_1` FOREIGN KEY (`haittavaikutus_id`) REFERENCES `haittavaikutus` (`id`),
  CONSTRAINT `harvinainen_ibfk_2` FOREIGN KEY (`lääke_tuotenumero`) REFERENCES `lääke` (`tuotenumero`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table lääkedb.harvinainen: ~0 rows (approximately)
DELETE FROM `harvinainen`;
/*!40000 ALTER TABLE `harvinainen` DISABLE KEYS */;
INSERT INTO `harvinainen` (`haittavaikutus_id`, `lääke_tuotenumero`) VALUES
	(1, 123456);
/*!40000 ALTER TABLE `harvinainen` ENABLE KEYS */;

-- Dumping structure for table lääkedb.lääke
DROP TABLE IF EXISTS `lääke`;
CREATE TABLE IF NOT EXISTS `lääke` (
  `tuotenumero` int(11) NOT NULL,
  `nimi` varchar(40) NOT NULL,
  `yksikkö` varchar(40) NOT NULL,
  PRIMARY KEY (`tuotenumero`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table lääkedb.lääke: ~3 rows (approximately)
DELETE FROM `lääke`;
/*!40000 ALTER TABLE `lääke` DISABLE KEYS */;
INSERT INTO `lääke` (`tuotenumero`, `nimi`, `yksikkö`) VALUES
	(4455, 'Faverin', 'kpl'),
	(98767, 'Eldepryl', 'ml'),
	(123456, 'Burana', 'kpl'),
	(654321, 'Zyrtec', 'kpl');
/*!40000 ALTER TABLE `lääke` ENABLE KEYS */;

-- Dumping structure for table lääkedb.lääkkeen_allergeeni
DROP TABLE IF EXISTS `lääkkeen_allergeeni`;
CREATE TABLE IF NOT EXISTS `lääkkeen_allergeeni` (
  `lääke_tuotenumero` int(11) NOT NULL,
  `allergeeni_id` int(11) NOT NULL,
  PRIMARY KEY (`lääke_tuotenumero`,`allergeeni_id`),
  KEY `allergeeniID` (`allergeeni_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table lääkedb.lääkkeen_allergeeni: ~0 rows (approximately)
DELETE FROM `lääkkeen_allergeeni`;
/*!40000 ALTER TABLE `lääkkeen_allergeeni` DISABLE KEYS */;
INSERT INTO `lääkkeen_allergeeni` (`lääke_tuotenumero`, `allergeeni_id`) VALUES
	(123456, 1);
/*!40000 ALTER TABLE `lääkkeen_allergeeni` ENABLE KEYS */;

-- Dumping structure for table lääkedb.lääkkeen_vaikuttava_aine
DROP TABLE IF EXISTS `lääkkeen_vaikuttava_aine`;
CREATE TABLE IF NOT EXISTS `lääkkeen_vaikuttava_aine` (
  `lääke_tuotenumero` int(11) NOT NULL,
  `vaikuttava_aine_id` int(11) NOT NULL,
  `pitoisuus` double NOT NULL,
  `lääkkeen_vaikuttava_aine_id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`lääkkeen_vaikuttava_aine_id`),
  KEY `fk_lääke` (`lääke_tuotenumero`),
  KEY `fk_vaikuttava_aine` (`vaikuttava_aine_id`),
  CONSTRAINT `fk_lääke` FOREIGN KEY (`lääke_tuotenumero`) REFERENCES `lääke` (`tuotenumero`),
  CONSTRAINT `fk_vaikuttava_aine` FOREIGN KEY (`vaikuttava_aine_id`) REFERENCES `vaikuttava_aine` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Dumping data for table lääkedb.lääkkeen_vaikuttava_aine: ~3 rows (approximately)
DELETE FROM `lääkkeen_vaikuttava_aine`;
/*!40000 ALTER TABLE `lääkkeen_vaikuttava_aine` DISABLE KEYS */;
INSERT INTO `lääkkeen_vaikuttava_aine` (`lääke_tuotenumero`, `vaikuttava_aine_id`, `pitoisuus`, `lääkkeen_vaikuttava_aine_id`) VALUES
	(123456, 4312, 400, 1),
	(654321, 1243, 6, 2),
	(4455, 5533, 500, 3),
	(98767, 2314, 600, 4);
/*!40000 ALTER TABLE `lääkkeen_vaikuttava_aine` ENABLE KEYS */;

-- Dumping structure for table lääkedb.vaikuttava_aine
DROP TABLE IF EXISTS `vaikuttava_aine`;
CREATE TABLE IF NOT EXISTS `vaikuttava_aine` (
  `id` int(11) NOT NULL,
  `nimi` varchar(40) NOT NULL,
  `maxannos` double NOT NULL,
  `suositeltuannos` double NOT NULL,
  `puoliintumisaika` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table lääkedb.vaikuttava_aine: ~3 rows (approximately)
DELETE FROM `vaikuttava_aine`;
/*!40000 ALTER TABLE `vaikuttava_aine` DISABLE KEYS */;
INSERT INTO `vaikuttava_aine` (`id`, `nimi`, `maxannos`, `suositeltuannos`, `puoliintumisaika`) VALUES
	(1243, 'Cetirizine', 4, 2, 6),
	(2314, 'Selegiline', 10, 5, 8),
	(4312, 'Ibuprofeeni', 7, 3, 4),
	(5533, 'Fluvoxamine', 2, 1, 10);
/*!40000 ALTER TABLE `vaikuttava_aine` ENABLE KEYS */;

-- Dumping structure for table lääkedb.yhteisvaikutus
DROP TABLE IF EXISTS `yhteisvaikutus`;
CREATE TABLE IF NOT EXISTS `yhteisvaikutus` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `aine1` int(11) NOT NULL,
  `aine2` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `aine1_FK` (`aine1`),
  KEY `aine2_FK` (`aine2`),
  CONSTRAINT `aine1_FK` FOREIGN KEY (`aine1`) REFERENCES `lääke` (`tuotenumero`),
  CONSTRAINT `aine2_FK` FOREIGN KEY (`aine2`) REFERENCES `lääke` (`tuotenumero`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Dumping data for table lääkedb.yhteisvaikutus: ~2 rows (approximately)
DELETE FROM `yhteisvaikutus`;
/*!40000 ALTER TABLE `yhteisvaikutus` DISABLE KEYS */;
INSERT INTO `yhteisvaikutus` (`id`, `aine1`, `aine2`) VALUES
	(1, 123456, 4455),
	(2, 123456, 98767);
/*!40000 ALTER TABLE `yhteisvaikutus` ENABLE KEYS */;

-- Dumping structure for table lääkedb.yleinen
DROP TABLE IF EXISTS `yleinen`;
CREATE TABLE IF NOT EXISTS `yleinen` (
  `haittavaikutus_id` int(11) NOT NULL,
  `lääke_tuotenumero` int(11) NOT NULL,
  PRIMARY KEY (`haittavaikutus_id`,`lääke_tuotenumero`),
  KEY `tuotenumero` (`lääke_tuotenumero`),
  CONSTRAINT `yleinen_ibfk_1` FOREIGN KEY (`haittavaikutus_id`) REFERENCES `haittavaikutus` (`id`),
  CONSTRAINT `yleinen_ibfk_2` FOREIGN KEY (`lääke_tuotenumero`) REFERENCES `lääke` (`tuotenumero`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table lääkedb.yleinen: ~0 rows (approximately)
DELETE FROM `yleinen`;
/*!40000 ALTER TABLE `yleinen` DISABLE KEYS */;
INSERT INTO `yleinen` (`haittavaikutus_id`, `lääke_tuotenumero`) VALUES
	(2, 123456);
/*!40000 ALTER TABLE `yleinen` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
