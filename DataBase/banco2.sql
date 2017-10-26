-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: locacao
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
-- Table structure for table `arestricao_recurso`
--

DROP TABLE IF EXISTS `arestricao_recurso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `arestricao_recurso` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_tipo_recurso` int(11) NOT NULL,
  `id_funcao` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `arestricao_recurso`
--

LOCK TABLES `arestricao_recurso` WRITE;
/*!40000 ALTER TABLE `arestricao_recurso` DISABLE KEYS */;
/*!40000 ALTER TABLE `arestricao_recurso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `funcao`
--

DROP TABLE IF EXISTS `funcao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `funcao` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `descricao` varchar(100) DEFAULT NULL,
  `ativo` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcao`
--

LOCK TABLES `funcao` WRITE;
/*!40000 ALTER TABLE `funcao` DISABLE KEYS */;
INSERT INTO `funcao` VALUES (48,'ygkuyg','uygkjgk',NULL),(49,'ttt','hgfhf',NULL);
/*!40000 ALTER TABLE `funcao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `funcao_permissao`
--

DROP TABLE IF EXISTS `funcao_permissao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `funcao_permissao` (
  `id_funcao` int(10) DEFAULT NULL,
  `id_permissao` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcao_permissao`
--

LOCK TABLES `funcao_permissao` WRITE;
/*!40000 ALTER TABLE `funcao_permissao` DISABLE KEYS */;
INSERT INTO `funcao_permissao` VALUES (48,13),(48,16),(48,18),(49,13),(49,16),(49,19);
/*!40000 ALTER TABLE `funcao_permissao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `instituicao`
--

DROP TABLE IF EXISTS `instituicao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `instituicao` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `telefone` varchar(100) DEFAULT NULL,
  `ativo` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `instituicao`
--

LOCK TABLES `instituicao` WRITE;
/*!40000 ALTER TABLE `instituicao` DISABLE KEYS */;
INSERT INTO `instituicao` VALUES (11,'alfa','alfa@gamil.com','12222222','1'),(12,'coac','coac@kg','46343',NULL);
/*!40000 ALTER TABLE `instituicao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permissao`
--

DROP TABLE IF EXISTS `permissao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permissao` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(30) DEFAULT NULL,
  `descricao` varchar(100) DEFAULT NULL,
  `ativo` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permissao`
--

LOCK TABLES `permissao` WRITE;
/*!40000 ALTER TABLE `permissao` DISABLE KEYS */;
INSERT INTO `permissao` VALUES (13,'sala','att',1),(14,'objetos','att2',1),(15,'admin','att4',1),(16,'auditorio','att2',1),(17,'bloco a','att9',1),(18,'cinema','aaa',1),(19,'video show','gfd',1);
/*!40000 ALTER TABLE `permissao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recurso`
--

DROP TABLE IF EXISTS `recurso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recurso` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `etiqueta` varchar(100) DEFAULT NULL,
  `observacao` varchar(100) DEFAULT NULL,
  `id_unidade` int(11) NOT NULL,
  `id_tipo_recurso` int(11) NOT NULL,
  `ativo` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recurso`
--

LOCK TABLES `recurso` WRITE;
/*!40000 ALTER TABLE `recurso` DISABLE KEYS */;
/*!40000 ALTER TABLE `recurso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `repeticao`
--

DROP TABLE IF EXISTS `repeticao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `repeticao` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_reserva` int(11) NOT NULL,
  `id_responsavel` int(11) NOT NULL,
  `id_destinatario` int(11) NOT NULL,
  `data_hora_reserva` datetime NOT NULL,
  `data_hora_final` datetime NOT NULL,
  `ativo` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `repeticao`
--

LOCK TABLES `repeticao` WRITE;
/*!40000 ALTER TABLE `repeticao` DISABLE KEYS */;
/*!40000 ALTER TABLE `repeticao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reserva`
--

DROP TABLE IF EXISTS `reserva`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reserva` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_responsavel` int(11) NOT NULL,
  `id_destinatario` int(11) NOT NULL,
  `id_recurso` int(11) NOT NULL,
  `data_hora_reserva` datetime NOT NULL,
  `data_hora_final` datetime NOT NULL,
  `repeticao` varchar(100) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reserva`
--

LOCK TABLES `reserva` WRITE;
/*!40000 ALTER TABLE `reserva` DISABLE KEYS */;
/*!40000 ALTER TABLE `reserva` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restricao_recurso`
--

DROP TABLE IF EXISTS `restricao_recurso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `restricao_recurso` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_tipo_recurso` int(11) NOT NULL,
  `id_funcao` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restricao_recurso`
--

LOCK TABLES `restricao_recurso` WRITE;
/*!40000 ALTER TABLE `restricao_recurso` DISABLE KEYS */;
/*!40000 ALTER TABLE `restricao_recurso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_recurso`
--

DROP TABLE IF EXISTS `tipo_recurso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_recurso` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `ativo` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_recurso`
--

LOCK TABLES `tipo_recurso` WRITE;
/*!40000 ALTER TABLE `tipo_recurso` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipo_recurso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unidade`
--

DROP TABLE IF EXISTS `unidade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `unidade` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `telefone` varchar(100) DEFAULT NULL,
  `endereco` varchar(200) DEFAULT NULL,
  `ativo` varchar(1) DEFAULT NULL,
  `id_instituicao` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unidade`
--

LOCK TABLES `unidade` WRITE;
/*!40000 ALTER TABLE `unidade` DISABLE KEYS */;
INSERT INTO `unidade` VALUES (10,'Perreimetral','aaaa@jhgjhg','123456','hrfhtrk3135','1',11),(11,'edeee','erews','wdfsr','dghdfgh','1',12);
/*!40000 ALTER TABLE `unidade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `matricula` varchar(100) DEFAULT NULL,
  `senha` varchar(45) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `telefone` varchar(100) DEFAULT NULL,
  `ativo` tinyint(1) DEFAULT NULL,
  `id_funcao` int(10) DEFAULT NULL,
  `status` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-10-26 20:15:16
