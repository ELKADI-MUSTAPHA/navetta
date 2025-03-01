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
-- Table structure for table `demande`
--

DROP TABLE IF EXISTS `demande`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `demande` (
  `id` int NOT NULL AUTO_INCREMENT,
  `utilisateur_id` int DEFAULT NULL,
  `ville_depart` varchar(100) DEFAULT NULL,
  `ville_arrivee` varchar(100) DEFAULT NULL,
  `heure_depart` time DEFAULT NULL,
  `heure_arrivee` time DEFAULT NULL,
  `periode_debut` date DEFAULT NULL,
  `periode_fin` date DEFAULT NULL,
  `description` text,
  `prix` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `utilisateur_id` (`utilisateur_id`),
  CONSTRAINT `demande_ibfk_1` FOREIGN KEY (`utilisateur_id`) REFERENCES `utilisateur` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `demande`
--

LOCK TABLES `demande` WRITE;
/*!40000 ALTER TABLE `demande` DISABLE KEYS */;
INSERT INTO `demande` VALUES (1,1,'Sidi bennour','Casablanca','07:25:00','08:25:00','2025-01-15','2025-01-16','clima',100.00),(5,1,'Sidi bennour','Casablanca','06:39:00','08:39:00','2025-01-15','2025-01-16','wifi, clima',90.00),(6,1,'Sidi bennour','Casablanca','07:56:00','09:56:00','2025-01-18','2025-01-19','clima w dak chy',110.00),(7,1,'mohamedia','rabat','21:44:00','22:44:00','2025-01-18','2025-01-21','everyday',30.00),(8,2,'temara','sale','12:20:00','13:20:00','2025-01-18','2025-01-31','everyday',30.00),(9,2,'paris','sale','08:03:00','11:03:00','2025-01-20','2025-01-29','300 Dollars',300.00),(10,1,'Casablanca','Agadir','21:53:00','12:53:00','2025-02-28','2025-03-31','clima',150.00);
/*!40000 ALTER TABLE `demande` ENABLE KEYS */;
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
