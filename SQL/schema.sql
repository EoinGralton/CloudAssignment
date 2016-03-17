CREATE TABLE `policy` (
  `idpolicy` int(11) NOT NULL,
  `outsideEuUS` tinyint(1) DEFAULT NULL,
  `cover_start` date DEFAULT NULL,
  `cover_finish` date DEFAULT NULL,
  `health_insurance` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`idpolicy`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
CREATE TABLE `person` (
  `idperson` int(11) NOT NULL,
  `idpolicy` int(11) NOT NULL,
  `admin` tinyint(1) DEFAULT NULL,
  `title` varchar(5) DEFAULT NULL,
  `FName` varchar(20) DEFAULT NULL,
  `LName` varchar(30) DEFAULT NULL,
  `D.O.B` date DEFAULT NULL,
  `Email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idperson`),
  KEY `idpolicy_idx` (`idpolicy`),
  CONSTRAINT `idpolicy` FOREIGN KEY (`idpolicy`) REFERENCES `policy` (`idpolicy`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
CREATE TABLE `member` (
  `email` varchar(15) DEFAULT NULL,
  `password` varchar(13) DEFAULT NULL,
  `idperson` int(11) DEFAULT NULL,
  KEY `idperson_idx` (`idperson`),
  CONSTRAINT `idperson` FOREIGN KEY (`idperson`) REFERENCES `person` (`idperson`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
CREATE TABLE `policyperson` (
  `idPolicyPerson` int(11) NOT NULL,
  `idpolicy` int(11) NOT NULL,
  `Title` varchar(5) DEFAULT NULL,
  `FName` varchar(20) DEFAULT NULL,
  `LName` varchar(30) DEFAULT NULL,
  `D.O.B` date DEFAULT NULL,
  PRIMARY KEY (`idPolicyPerson`),
  KEY `idpolicy_idx` (`idpolicy`),
  KEY `idpolicyfk_idx` (`idpolicy`),
  CONSTRAINT `idpolicyfk` FOREIGN KEY (`idpolicy`) REFERENCES `policy` (`idpolicy`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
