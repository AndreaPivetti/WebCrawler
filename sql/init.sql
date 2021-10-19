CREATE DATABASE  IF NOT EXISTS `web_crawler` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `web_crawler`;

DROP TABLE IF EXISTS `download`;

CREATE TABLE `download` (
  `id` int NOT NULL AUTO_INCREMENT,
  `pagina_web` varchar(255) NOT NULL,
  `numero_immagini` int DEFAULT NULL,
  `ora_download` varchar(45) NOT NULL,
  `durata_download` bigint DEFAULT NULL,
  `esito_download` varchar(45) NOT NULL,
  `utente_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `utente_id_FK` (`utente_id`),
  CONSTRAINT `utente_id_FK` FOREIGN KEY (`utente_id`) REFERENCES `utenti` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `notifiche_limite_admin`;

CREATE TABLE `notifiche_limite_admin` (
  `id` int NOT NULL AUTO_INCREMENT,
  `utente_id` int NOT NULL,
  `data` varchar(100) DEFAULT NULL,
  `mostra` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `utenti`;

CREATE TABLE `utenti` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `ultimo_accesso` varchar(100) DEFAULT NULL,
  `max_downloads` int NOT NULL DEFAULT '5',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


