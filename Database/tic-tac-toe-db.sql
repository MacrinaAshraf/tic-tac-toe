CREATE DATABASE  IF NOT EXISTS `tictactoe_db`;



CREATE TABLE `player` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `score` varchar(45) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  UNIQUE KEY `email_UNIQUE` (`email`)
);

DROP TABLE IF EXISTS `game`;

CREATE TABLE `game` (
  `payerX` int(11) NOT NULL,
  `playerO` int(11) NOT NULL,
  `cell11` tinyint(4) NOT NULL,
  `cell12` tinyint(4) NOT NULL,
  `cell13` tinyint(4) NOT NULL,
  `cell21` tinyint(4) NOT NULL,
  `cell22` tinyint(4) NOT NULL,
  `cell23` tinyint(4) NOT NULL,
  `cell31` tinyint(4) NOT NULL,
  `cell32` tinyint(4) NOT NULL,
  `cell33` tinyint(4) NOT NULL,
  `turn` char(4) DEFAULT NULL,
  PRIMARY KEY (`payerX`,`playerO`),
  KEY `playerO` (`playerO`),
  CONSTRAINT `game_ibfk_1` FOREIGN KEY (`payerX`) REFERENCES `player` (`id`),
  CONSTRAINT `playerO` FOREIGN KEY (`playerO`) REFERENCES `player` (`id`)
);
