-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: navette
-- ------------------------------------------------------
-- Server version	8.0.40

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `navette`
--

DROP TABLE IF EXISTS `navette`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `navette` (
  `id` int NOT NULL AUTO_INCREMENT,
  `societe_id` int DEFAULT NULL,
  `ville_depart` varchar(100) DEFAULT NULL,
  `ville_arrivee` varchar(100) DEFAULT NULL,
  `heure_depart` time DEFAULT NULL,
  `heure_arrivee` time DEFAULT NULL,
  `periode_debut` date DEFAULT NULL,
  `periode_fin` date DEFAULT NULL,
  `description` text,
  `nombre_abonnes` int DEFAULT NULL,
  `places_disponibles` int DEFAULT NULL,
  `prix` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `societe_id` (`societe_id`),
  CONSTRAINT `navette_ibfk_1` FOREIGN KEY (`societe_id`) REFERENCES `societe` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `navette`
--

LOCK TABLES `navette` WRITE;
/*!40000 ALTER TABLE `navette` DISABLE KEYS */;
INSERT INTO `navette` VALUES (6,1,'mohamedia','zagor','21:02:00','15:02:00','2025-02-17','2025-02-28','to zagora',30,29,300.00),(8,1,'Casablanca','rabat','07:00:00','07:55:00','2025-02-20','2025-02-28','qlima, wifi, autoroute, moyen de transport confortable',15,14,40.00),(11,1,'Casablanca','rabat','07:42:00','08:42:00','2025-02-20','2025-02-28','professional driver and comfortable transport',10,10,30.00),(12,1,'Casablanca','Rabat','07:45:00','08:30:00','2025-02-28','2025-03-31','Clima, wifi, drop at the desired point within the ',20,18,50.00),(13,1,'temara','Agadir','18:30:00','21:30:00','2025-02-28','2025-03-31','wifi, clima, stop at Essaouira, end stop ',10,8,150.00),(14,2,'temara','rabat','18:00:00','18:25:00','2025-02-28','2025-03-31','comfortable',4,2,20.00);
/*!40000 ALTER TABLE `navette` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-01  0:02:58
