-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.4    Database: lacantinelladelbonsignore
-- ------------------------------------------------------
-- Server version	5.7.19-log

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
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feedback` (
  `idVino` int(11) unsigned DEFAULT NULL,
  `user` varchar(45) NOT NULL,
  KEY `user` (`user`),
  KEY `idVino` (`idVino`),
  CONSTRAINT `feedback_ibfk_1` FOREIGN KEY (`user`) REFERENCES `utenti` (`user`),
  CONSTRAINT `feedback_ibfk_2` FOREIGN KEY (`idVino`) REFERENCES `vino` (`idVino`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
INSERT INTO `feedback` VALUES (2,'daniele'),(3,'daniele');
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordine`
--

DROP TABLE IF EXISTS `ordine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ordine` (
  `idOrdine` int(255) unsigned NOT NULL AUTO_INCREMENT,
  `user` varchar(45) NOT NULL,
  `descrizione` varchar(500) NOT NULL,
  `email` varchar(50) NOT NULL,
  `indirizzo` varchar(50) NOT NULL,
  `zipCode` int(10) NOT NULL,
  `totale` varchar(255) NOT NULL,
  `percentuale` varchar(255) NOT NULL,
  PRIMARY KEY (`idOrdine`),
  KEY `user` (`user`),
  CONSTRAINT `ordine_ibfk_1` FOREIGN KEY (`user`) REFERENCES `utenti` (`user`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordine`
--

LOCK TABLES `ordine` WRITE;
/*!40000 ALTER TABLE `ordine` DISABLE KEYS */;
INSERT INTO `ordine` VALUES (1,'catello','1 MOSCATO  2 24.0 euro\n2 MONTALCINO 1 125 euro\n','passpar@gogle.com','via kennedy',999999,'149.0','14.900001'),(2,'catello','1 MOSCATO  2 24.0 euro\n2 MONTALCINO 1 125 euro\n','passpar@gogle.com','via kennedy',999999,'149.0','14.900001');
/*!40000 ALTER TABLE `ordine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utenti`
--

DROP TABLE IF EXISTS `utenti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `utenti` (
  `user` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `email` varchar(50) NOT NULL,
  `nome` varchar(25) NOT NULL,
  `cognome` varchar(25) NOT NULL,
  `cf` varchar(45) NOT NULL,
  `isSeller` tinyint(1) DEFAULT NULL,
  `nomeAzienda` varchar(45) DEFAULT NULL,
  `piva` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`cf`),
  UNIQUE KEY `user` (`user`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utenti`
--

LOCK TABLES `utenti` WRITE;
/*!40000 ALTER TABLE `utenti` DISABLE KEYS */;
INSERT INTO `utenti` VALUES ('leo77','leoleo','tuo@zepter.it','Leonardo','daVinci','1231232131234567',1,'DaVinciSPA','12345698701'),('gfbfdvcfgnbdv','nbgfdvfgfbfdv','uu@ll.it','fgfbfvfd','fdbdfd','1234567891234567',0,NULL,NULL),('catello','catello','catello@catello.it','catello','graziuso','23221232152562',1,'catello spa','148625156852155'),('daniele','daniele','daniele@daniele.it','daniele','fornaro','55851455215852',1,'daniele spa','9546256356326562'),('Amministratore','Amministratore','admin@admin.it','admin','admin','loekcmdkfrlscfrg',0,NULL,NULL);
/*!40000 ALTER TABLE `utenti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vino`
--

DROP TABLE IF EXISTS `vino`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vino` (
  `idVino` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `cf` varchar(45) NOT NULL,
  `nome` varchar(25) NOT NULL,
  `descrizione` varchar(500) NOT NULL,
  `anno` varchar(45) NOT NULL,
  `prezzo` varchar(45) NOT NULL,
  `immagine` varchar(500) DEFAULT NULL,
  `tipo` varchar(15) DEFAULT NULL,
  `quantita` int(225) NOT NULL,
  `feedback` int(225) NOT NULL,
  PRIMARY KEY (`idVino`),
  KEY `vino_ibfk_1` (`cf`),
  CONSTRAINT `vino_ibfk_1` FOREIGN KEY (`cf`) REFERENCES `utenti` (`cf`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vino`
--

LOCK TABLES `vino` WRITE;
/*!40000 ALTER TABLE `vino` DISABLE KEYS */;
INSERT INTO `vino` VALUES (1,'23221232152562','BARBERA','Un blend unico ottenuto da Merlot e dall’autoctono friulano Refosco, dona complessità a questo spumante avvolgente.','1883','50','https://s.tannico.it/media/catalog/product/cache/1/small_image/300x300/0dc2d03fe217f8c83829496872af24a0/a/v/avvocata1_1.jpg','rosso',249,0),(2,'23221232152562','MOSCATO ','Lo Spumante Rigole Brut è un vino dal colore giallo paglierino chiaro con riflessi verdolini da uve Pinot Bianco e Chardonnay. L’affinamento “sur-lie”  cremoso, dal perlage fine e persistente.','1888','12','https://s.tannico.it/media/catalog/product/cache/1/small_image/300x300/0dc2d03fe217f8c83829496872af24a0/m/o/moscato2_1_1.jpg','bianco',334,1),(3,'23221232152562','MONTALCINO','Abbinamento Il Brunello bene si abbina con piatti molto strutturati quali le carni rosse, la selvaggina da penna e da pelo, eventualmente accompagnate da funghi e tartufi. Trova anche abbinamento ottimale con piatti della cucina internazionale a base di carni o con salse. In tavola si può abbinare anche con formaggi: tome stagionate, pecorino toscano, formaggi strutturati. Inoltre, per le sue caratteristiche, è ottimo anche come vino da meditazione. Vedi Abbinamento vino cibo.','1965','125','https://s.tannico.it/media/catalog/product/cache/1/small_image/300x300/0dc2d03fe217f8c83829496872af24a0/S/h/Sherazade-2015_1_1.jpg','rosso',155,1),(4,'55851455215852','Cesanese del Piglio:','Ha colore rosso rubino tendente al granato; odore delicato e sapore dolce. La gradazione Ã¨ tra i 12 gradi e i 13 e Â½: Eâ indicato con lâabbacchio al forno, lâagnello al tegame con i carciofi e carni di maiale. Si consigliano i tipi âamabiliâ e âdolceâ per il dessert.','2001','29','https://s.tannico.it/media/catalog/product/cache/1/thumbnail/0dc2d03fe217f8c83829496872af24a0/r/o/rossomas_1_1.jpg','rosso',60,0),(5,'1231232131234567','La Fiorita','Denominazione: Brunello di Montalcino Riserva DOCG Vitigni: sangiovese grosso 100% Alcol: 14% Formato: 0.75l Consumo ideale: 2015/2025 Temperatura di servizio: 18/20 Â°C Abbinamenti: secondi di terra Momento per degustarlo: occasioni speciali , ricorrenza da festeggiare Giudizio di Tannico: 9/10','2005','47','https://s.tannico.it/media/catalog/product/cache/1/thumbnail/0dc2d03fe217f8c83829496872af24a0/l/a/lafiorita_brunelloriserva_94_2.jpg','rosso',100,0);
/*!40000 ALTER TABLE `vino` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'lacantinelladelbonsignore'
--

--
-- Dumping routines for database 'lacantinelladelbonsignore'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-15 18:12:26
