-- --------------------------------------------------------
-- Verkkotietokone:              127.0.0.1
-- Palvelinversio:               10.1.18-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Versio:              9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for applicationdb
DROP DATABASE IF EXISTS `applicationdb`;
CREATE DATABASE IF NOT EXISTS `applicationdb` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `applicationdb`;


-- Dumping structure for taulu applicationdb.message
DROP TABLE IF EXISTS `message`;
CREATE TABLE IF NOT EXISTS `message` (
  `MessageID` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `message` varchar(1000) DEFAULT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `sender` varchar(50) NOT NULL,
  `receiver` varchar(50) NOT NULL,
  PRIMARY KEY (`MessageID`),
  KEY `FK_message_sender` (`sender`),
  KEY `FK_message_receiver` (`receiver`),
  CONSTRAINT `FK_message_receiver` FOREIGN KEY (`receiver`) REFERENCES `user` (`username`),
  CONSTRAINT `FK_message_sender` FOREIGN KEY (`sender`) REFERENCES `user` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- Dumping data for table applicationdb.message: ~5 rows (suunnilleen)
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` (`MessageID`, `title`, `message`, `date`, `sender`, `receiver`) VALUES
	(1, 'Moi', 'Joko toimii', '2017-03-13 13:30:23', 'doctor1', 'nurse1'),
	(2, 'Testi', 'moro', '2017-03-13 14:41:13', 'doctor1', 'admin'),
	(3, 'Potilaalla hätä', 'Tuleeko viestit läpi', '2017-03-13 15:11:25', 'admin', 'nurse1'),
	(4, 'Ei toimi lääkkeet', 'moi', '2017-03-13 19:27:49', 'doctor1', 'nurse1'),
	(5, 'Viestin testaus', 'doctor1 on vastaanottaja', '2017-03-13 19:28:36', 'nurse1', 'doctor1'),
	(6, 'Palaveri', 'Muistahan osallistua palaveriin illemmalla klo 20.00', '2017-03-14 12:37:50', 'doctormad', 'doctor1');
/*!40000 ALTER TABLE `message` ENABLE KEYS */;


-- Dumping structure for taulu applicationdb.prescription
DROP TABLE IF EXISTS `prescription`;
CREATE TABLE IF NOT EXISTS `prescription` (
  `prescriptionID` int(11) NOT NULL AUTO_INCREMENT,
  `patientID` varchar(50) NOT NULL,
  `doctorID` int(11) NOT NULL,
  `diagnoseID` int(11) NOT NULL,
  `dose` double NOT NULL,
  `timesADay` int(11) NOT NULL,
  `info` varchar(1000) NOT NULL,
  `startDate` timestamp NULL DEFAULT NULL,
  `endDate` timestamp NULL DEFAULT NULL,
  `creationDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `drugID` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  PRIMARY KEY (`prescriptionID`),
  KEY `username` (`username`),
  CONSTRAINT `prescription_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Dumping data for table applicationdb.prescription: ~2 rows (suunnilleen)
/*!40000 ALTER TABLE `prescription` DISABLE KEYS */;
INSERT INTO `prescription` (`prescriptionID`, `patientID`, `doctorID`, `diagnoseID`, `dose`, `timesADay`, `info`, `startDate`, `endDate`, `creationDate`, `drugID`, `username`) VALUES
	(1, '123456-789a', 3, 1, 2, 2, 'Syö yksinäs', '2017-03-08 18:11:51', '2017-02-13 10:46:50', '2017-02-13 10:46:50', 123456, 'doctor1'),
	(3, '123456-789a', 3, 1, 1, 1, 'Tehokuurin aikana potilaan ei tule syödä mitään.\nVettä voi mahdollisesti juoda.', '2017-04-01 00:00:00', '2017-04-30 00:00:00', '2017-03-12 00:00:00', 123456, 'doctor1');
/*!40000 ALTER TABLE `prescription` ENABLE KEYS */;


-- Dumping structure for taulu applicationdb.user
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `userID` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `usertype` int(11) NOT NULL,
  `firstname` varchar(50) NOT NULL,
  `lastname` varchar(50) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table applicationdb.user: ~4 rows (suunnilleen)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`userID`, `username`, `password`, `email`, `usertype`, `firstname`, `lastname`) VALUES
	(1, 'admin', 'admin', 'admin@sairaala.com', 3, 'admin', 'admin'),
	(3, 'doctor1', 'doctor1', 'doctor@sairaala.com', 2, 'doctor', 'amazing'),
	(4, 'doctormad', 'doctormad', 'imsocrazy@sairaala.com', 2, 'mr', 'wonderful'),
	(2, 'nurse1', 'nurse1', 'nurse1@sairaala.com', 1, 'ivanka', 'trump');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;


-- Dumping database structure for lääkedb
DROP DATABASE IF EXISTS `lääkedb`;
CREATE DATABASE IF NOT EXISTS `lääkedb` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `lääkedb`;


-- Dumping structure for taulu lääkedb.allergeeni
DROP TABLE IF EXISTS `allergeeni`;
CREATE TABLE IF NOT EXISTS `allergeeni` (
  `id` int(11) NOT NULL,
  `nimi` varchar(40) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table lääkedb.allergeeni: ~0 rows (suunnilleen)
/*!40000 ALTER TABLE `allergeeni` DISABLE KEYS */;
INSERT INTO `allergeeni` (`id`, `nimi`) VALUES
	(1, 'Kala');
/*!40000 ALTER TABLE `allergeeni` ENABLE KEYS */;


-- Dumping structure for taulu lääkedb.haittavaikutus
DROP TABLE IF EXISTS `haittavaikutus`;
CREATE TABLE IF NOT EXISTS `haittavaikutus` (
  `id` int(11) NOT NULL,
  `sairaus_id` int(11) NOT NULL,
  `nimi` varchar(40) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table lääkedb.haittavaikutus: ~2 rows (suunnilleen)
/*!40000 ALTER TABLE `haittavaikutus` DISABLE KEYS */;
INSERT INTO `haittavaikutus` (`id`, `sairaus_id`, `nimi`) VALUES
	(1, 1, 'Äkkikuolema'),
	(2, 2, 'Päänsärky');
/*!40000 ALTER TABLE `haittavaikutus` ENABLE KEYS */;


-- Dumping structure for taulu lääkedb.harvinainen
DROP TABLE IF EXISTS `harvinainen`;
CREATE TABLE IF NOT EXISTS `harvinainen` (
  `haittavaikutus_id` int(11) NOT NULL,
  `lääke_tuotenumero` int(11) NOT NULL,
  PRIMARY KEY (`haittavaikutus_id`,`lääke_tuotenumero`),
  KEY `tuotenumero` (`lääke_tuotenumero`),
  CONSTRAINT `harvinainen_ibfk_1` FOREIGN KEY (`haittavaikutus_id`) REFERENCES `haittavaikutus` (`id`),
  CONSTRAINT `harvinainen_ibfk_2` FOREIGN KEY (`lääke_tuotenumero`) REFERENCES `lääke` (`tuotenumero`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table lääkedb.harvinainen: ~0 rows (suunnilleen)
/*!40000 ALTER TABLE `harvinainen` DISABLE KEYS */;
INSERT INTO `harvinainen` (`haittavaikutus_id`, `lääke_tuotenumero`) VALUES
	(1, 123456);
/*!40000 ALTER TABLE `harvinainen` ENABLE KEYS */;


-- Dumping structure for taulu lääkedb.lääke
DROP TABLE IF EXISTS `lääke`;
CREATE TABLE IF NOT EXISTS `lääke` (
  `tuotenumero` int(11) NOT NULL,
  `nimi` varchar(40) NOT NULL,
  `yksikkö` varchar(40) NOT NULL,
  PRIMARY KEY (`tuotenumero`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table lääkedb.lääke: ~0 rows (suunnilleen)
/*!40000 ALTER TABLE `lääke` DISABLE KEYS */;
INSERT INTO `lääke` (`tuotenumero`, `nimi`, `yksikkö`) VALUES
	(123456, 'Burana', 'kpl');
/*!40000 ALTER TABLE `lääke` ENABLE KEYS */;


-- Dumping structure for taulu lääkedb.lääkkeen_allergeeni
DROP TABLE IF EXISTS `lääkkeen_allergeeni`;
CREATE TABLE IF NOT EXISTS `lääkkeen_allergeeni` (
  `lääke_tuotenumero` int(11) NOT NULL,
  `allergeeni_id` int(11) NOT NULL,
  PRIMARY KEY (`lääke_tuotenumero`,`allergeeni_id`),
  KEY `allergeeniID` (`allergeeni_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table lääkedb.lääkkeen_allergeeni: ~0 rows (suunnilleen)
/*!40000 ALTER TABLE `lääkkeen_allergeeni` DISABLE KEYS */;
INSERT INTO `lääkkeen_allergeeni` (`lääke_tuotenumero`, `allergeeni_id`) VALUES
	(123456, 1);
/*!40000 ALTER TABLE `lääkkeen_allergeeni` ENABLE KEYS */;


-- Dumping structure for taulu lääkedb.lääkkeen_vaikuttava_aine
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Dumping data for table lääkedb.lääkkeen_vaikuttava_aine: ~0 rows (suunnilleen)
/*!40000 ALTER TABLE `lääkkeen_vaikuttava_aine` DISABLE KEYS */;
INSERT INTO `lääkkeen_vaikuttava_aine` (`lääke_tuotenumero`, `vaikuttava_aine_id`, `pitoisuus`, `lääkkeen_vaikuttava_aine_id`) VALUES
	(123456, 4312, 400, 1);
/*!40000 ALTER TABLE `lääkkeen_vaikuttava_aine` ENABLE KEYS */;


-- Dumping structure for taulu lääkedb.vaikuttava_aine
DROP TABLE IF EXISTS `vaikuttava_aine`;
CREATE TABLE IF NOT EXISTS `vaikuttava_aine` (
  `id` int(11) NOT NULL,
  `nimi` varchar(40) NOT NULL,
  `maxannos` double NOT NULL,
  `suositeltuannos` double NOT NULL,
  `puoliintumisaika` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table lääkedb.vaikuttava_aine: ~0 rows (suunnilleen)
/*!40000 ALTER TABLE `vaikuttava_aine` DISABLE KEYS */;
INSERT INTO `vaikuttava_aine` (`id`, `nimi`, `maxannos`, `suositeltuannos`, `puoliintumisaika`) VALUES
	(4312, 'Ibuprofeeni', 7, 3, 4);
/*!40000 ALTER TABLE `vaikuttava_aine` ENABLE KEYS */;


-- Dumping structure for taulu lääkedb.yleinen
DROP TABLE IF EXISTS `yleinen`;
CREATE TABLE IF NOT EXISTS `yleinen` (
  `haittavaikutus_id` int(11) NOT NULL,
  `lääke_tuotenumero` int(11) NOT NULL,
  PRIMARY KEY (`haittavaikutus_id`,`lääke_tuotenumero`),
  KEY `tuotenumero` (`lääke_tuotenumero`),
  CONSTRAINT `yleinen_ibfk_1` FOREIGN KEY (`haittavaikutus_id`) REFERENCES `haittavaikutus` (`id`),
  CONSTRAINT `yleinen_ibfk_2` FOREIGN KEY (`lääke_tuotenumero`) REFERENCES `lääke` (`tuotenumero`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table lääkedb.yleinen: ~0 rows (suunnilleen)
/*!40000 ALTER TABLE `yleinen` DISABLE KEYS */;
INSERT INTO `yleinen` (`haittavaikutus_id`, `lääke_tuotenumero`) VALUES
	(2, 123456);
/*!40000 ALTER TABLE `yleinen` ENABLE KEYS */;


-- Dumping database structure for sairaaladb
DROP DATABASE IF EXISTS `sairaaladb`;
CREATE DATABASE IF NOT EXISTS `sairaaladb` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `sairaaladb`;


-- Dumping structure for taulu sairaaladb.diagnoosi
DROP TABLE IF EXISTS `diagnoosi`;
CREATE TABLE IF NOT EXISTS `diagnoosi` (
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

-- Dumping data for table sairaaladb.diagnoosi: ~0 rows (suunnilleen)
/*!40000 ALTER TABLE `diagnoosi` DISABLE KEYS */;
INSERT INTO `diagnoosi` (`diagnoosiID`, `sairaustunniste`, `epikriisi`, `luontipäivä`, `päättymispäivä`, `hetu`, `työntekijänumero`) VALUES
	(1, 1, 'Potilaalla havaittu äkillinen hengen menetys', '2017-02-10 12:40:38', '2017-02-10 12:40:40', '123456-789a', 1);
/*!40000 ALTER TABLE `diagnoosi` ENABLE KEYS */;


-- Dumping structure for taulu sairaaladb.hoitosuhde
DROP TABLE IF EXISTS `hoitosuhde`;
CREATE TABLE IF NOT EXISTS `hoitosuhde` (
  `työntekijänumero` int(11) NOT NULL,
  `hetu` varchar(15) NOT NULL,
  PRIMARY KEY (`työntekijänumero`,`hetu`),
  KEY `hetu` (`hetu`),
  CONSTRAINT `hoitosuhde_ibfk_1` FOREIGN KEY (`työntekijänumero`) REFERENCES `työntekijä` (`työntekijänumero`),
  CONSTRAINT `hoitosuhde_ibfk_2` FOREIGN KEY (`hetu`) REFERENCES `potilas` (`hetu`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table sairaaladb.hoitosuhde: ~0 rows (suunnilleen)
/*!40000 ALTER TABLE `hoitosuhde` DISABLE KEYS */;
INSERT INTO `hoitosuhde` (`työntekijänumero`, `hetu`) VALUES
	(1, '123456-789a');
/*!40000 ALTER TABLE `hoitosuhde` ENABLE KEYS */;


-- Dumping structure for taulu sairaaladb.potilas
DROP TABLE IF EXISTS `potilas`;
CREATE TABLE IF NOT EXISTS `potilas` (
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

-- Dumping data for table sairaaladb.potilas: ~2 rows (suunnilleen)
/*!40000 ALTER TABLE `potilas` DISABLE KEYS */;
INSERT INTO `potilas` (`hetu`, `etunimi`, `sukunimi`, `pituus`, `paino`, `sukupuoli`, `osoite`, `postinumero`, `kaupunki`, `maa`, `puhelinnumero`) VALUES
	('123456-789a', 'Potilas', 'Saarinen', 160, 65, 'Mies', 'Bule 29', 420, 'Helsinki', 'Suomi', '040-12344556'),
	('987654-321a', 'Patient', 'Zero', 180, 50, 'Mies', 'Bule 31', 420, 'Helsinki', 'Suomi', '050-11122333');
/*!40000 ALTER TABLE `potilas` ENABLE KEYS */;


-- Dumping structure for taulu sairaaladb.työntekijä
DROP TABLE IF EXISTS `työntekijä`;
CREATE TABLE IF NOT EXISTS `työntekijä` (
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

-- Dumping data for table sairaaladb.työntekijä: ~4 rows (suunnilleen)
/*!40000 ALTER TABLE `työntekijä` DISABLE KEYS */;
INSERT INTO `työntekijä` (`työntekijänumero`, `etunimi`, `sukunimi`, `osoite`, `postinumero`, `kaupunki`, `maa`, `puhelinnumero`, `pankkiyhteys`, `titteli`, `sposti`) VALUES
	(1, 'Irvi', 'Kaikkonen', 'Bule 31', 180, 'Helsinki', 'Suomi', '112', '123543-123456', 'Ylläpitäjä', 'admin@sairaala.com'),
	(2, 'Marja', 'Makkonen', 'Bule 31', 180, 'Helsinki', 'Suomi', '112', '632918-382901', 'Hoitaja', 'nurse1@sairaala.com'),
	(3, 'Leku', 'Nordqvist', 'Bule 31', 180, 'Helsinki', 'Suomi', '112', '12720921-68921', 'Lääkäri', 'doctor1@sairaala.com'),
	(4, 'Frank E.', 'nStein', 'Jakomäentie 13', 666, 'Helsinki', 'Suomi', '911', '672169-62891', 'Lääkäri', 'doctormad@sairaala.com');
/*!40000 ALTER TABLE `työntekijä` ENABLE KEYS */;


-- Dumping database structure for sairausdb
DROP DATABASE IF EXISTS `sairausdb`;
CREATE DATABASE IF NOT EXISTS `sairausdb` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `sairausdb`;


-- Dumping structure for taulu sairausdb.allergeenit
DROP TABLE IF EXISTS `allergeenit`;
CREATE TABLE IF NOT EXISTS `allergeenit` (
  `allergeeniID` int(11) NOT NULL,
  `nimi` varchar(40) NOT NULL,
  PRIMARY KEY (`allergeeniID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table sairausdb.allergeenit: ~0 rows (suunnilleen)
/*!40000 ALTER TABLE `allergeenit` DISABLE KEYS */;
INSERT INTO `allergeenit` (`allergeeniID`, `nimi`) VALUES
	(1, 'Kala');
/*!40000 ALTER TABLE `allergeenit` ENABLE KEYS */;


-- Dumping structure for taulu sairausdb.rajoitteet
DROP TABLE IF EXISTS `rajoitteet`;
CREATE TABLE IF NOT EXISTS `rajoitteet` (
  `sairausid` int(11) NOT NULL,
  `allergeeniID` int(11) NOT NULL,
  PRIMARY KEY (`sairausid`,`allergeeniID`),
  KEY `allergeeniID` (`allergeeniID`),
  CONSTRAINT `rajoitteet_ibfk_1` FOREIGN KEY (`sairausid`) REFERENCES `sairaus` (`sairausid`),
  CONSTRAINT `rajoitteet_ibfk_2` FOREIGN KEY (`allergeeniID`) REFERENCES `allergeenit` (`allergeeniID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table sairausdb.rajoitteet: ~0 rows (suunnilleen)
/*!40000 ALTER TABLE `rajoitteet` DISABLE KEYS */;
INSERT INTO `rajoitteet` (`sairausid`, `allergeeniID`) VALUES
	(1, 1);
/*!40000 ALTER TABLE `rajoitteet` ENABLE KEYS */;


-- Dumping structure for taulu sairausdb.sairaus
DROP TABLE IF EXISTS `sairaus`;
CREATE TABLE IF NOT EXISTS `sairaus` (
  `sairausid` int(11) NOT NULL,
  `nimi` varchar(40) NOT NULL,
  `kuvaus` varchar(1000) NOT NULL,
  PRIMARY KEY (`sairausid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table sairausdb.sairaus: ~0 rows (suunnilleen)
/*!40000 ALTER TABLE `sairaus` DISABLE KEYS */;
INSERT INTO `sairaus` (`sairausid`, `nimi`, `kuvaus`) VALUES
	(1, 'Äkkikuolema', 'Saattaa potilas kupsahtaa');
/*!40000 ALTER TABLE `sairaus` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
