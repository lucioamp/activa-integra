CREATE DATABASE  IF NOT EXISTS `acctiva-integra` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `acctiva-integra`;
-- MySQL dump 10.13  Distrib 5.1.40, for Win32 (ia32)
--
-- Host: localhost    Database: acctiva-integra
-- ------------------------------------------------------
-- Server version	5.1.32-community

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
-- Table structure for table `forum`
--

DROP TABLE IF EXISTS `forum`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `forum` (
  `pk_forum` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fk_catForum` int(10) unsigned NOT NULL,
  `fk_tarefa` int(10) unsigned NOT NULL,
  PRIMARY KEY (`pk_forum`),
  KEY `FK_forum_2` (`fk_catForum`),
  KEY `FK_forum_3` (`fk_tarefa`),
  CONSTRAINT `FK_forum_1` FOREIGN KEY (`pk_forum`) REFERENCES `servico` (`pk_servico`),
  CONSTRAINT `FK_forum_2` FOREIGN KEY (`fk_catForum`) REFERENCES `categoriaforum` (`pk_catForum`),
  CONSTRAINT `FK_forum_3` FOREIGN KEY (`fk_tarefa`) REFERENCES `tarefa` (`pk_tarefa`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `forum`
--

LOCK TABLES `forum` WRITE;
/*!40000 ALTER TABLE `forum` DISABLE KEYS */;
INSERT INTO `forum` VALUES (1,1,1),(2,1,2),(38,2,3);
/*!40000 ALTER TABLE `forum` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `formacaoacademica`
--

DROP TABLE IF EXISTS `formacaoacademica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `formacaoacademica` (
  `pk_formacaoAcademica` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `no_formacaoAcademica` varchar(60) NOT NULL,
  PRIMARY KEY (`pk_formacaoAcademica`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `formacaoacademica`
--

LOCK TABLES `formacaoacademica` WRITE;
/*!40000 ALTER TABLE `formacaoacademica` DISABLE KEYS */;
INSERT INTO `formacaoacademica` VALUES (1,'Bacharel'),(2,'Mestrado'),(3,'Doutorado');
/*!40000 ALTER TABLE `formacaoacademica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ae_usuario_aplicacao_log`
--

DROP TABLE IF EXISTS `ae_usuario_aplicacao_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ae_usuario_aplicacao_log` (
  `id_usuario_aplicacao_log` int(10) NOT NULL AUTO_INCREMENT,
  `id_usuario_aplicacao` int(10) unsigned NOT NULL,
  `retorno` blob,
  `data` datetime DEFAULT NULL,
  PRIMARY KEY (`id_usuario_aplicacao_log`),
  KEY `ae_usuario_aplicacao_log_usuario_aplicacao_fk` (`id_usuario_aplicacao`),
  CONSTRAINT `ae_usuario_aplicacao_log_usuario_aplicacao_fk` FOREIGN KEY (`id_usuario_aplicacao`) REFERENCES `ae_usuario_aplicacao` (`id_usuario_aplicacao`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=108 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ae_usuario_aplicacao_log`
--

LOCK TABLES `ae_usuario_aplicacao_log` WRITE;
/*!40000 ALTER TABLE `ae_usuario_aplicacao_log` DISABLE KEYS */;
INSERT INTO `ae_usuario_aplicacao_log` VALUES (100,7,'Aplica&ccedil;&atilde;o [Twitter - Lista de twitts] retornou novos dados:<br/><br/><html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><title>tree.xslt output</title><link type=\"text/css\" rel=\"stylesheet\" href=\"tree-view.css\"></head><body><br><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">resultado</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">since_id</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">0</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">max_id</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">49187756370444288</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">results</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">text</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">t&eacute;cnico&nbsp;do&nbsp;vasco&nbsp;disse&nbsp;que&nbsp;&eacute;&nbsp;quase&nbsp;impossivel&nbsp;segurar&nbsp;a&nbsp;jogado&nbsp;a&eacute;rea&nbsp;do&nbsp;Botafogo&nbsp;kkk,&eacute;&nbsp;imposs&iacute;vel&nbsp;segurar&nbsp;o&nbsp;Botafogo!&nbsp;hahaha</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">from_user_id</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">147032252</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">geo</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">null</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">iso_language_code</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">pt</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">to_user_id</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">null</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">id</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">49187756370444288</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">to_user_id_str</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">null</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">source</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">&amp;lt;a&nbsp;href=&amp;quot;http://twitter.com/&amp;quot;&amp;gt;web&amp;lt;/a&amp;gt;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">from_user_id_str</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">147032252</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">from_user</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">rodrigonelly</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">created_at</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">Sat,&nbsp;19&nbsp;Mar&nbsp;2011&nbsp;19:17:29&nbsp;+0000</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">id_str</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">49187756370444288</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">profile_image_url</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">http://a1.twimg.com/profile_images/1279473050/llllllll_normal.jpg</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">metadata</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">result_type</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">recent</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">page</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">1</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">query</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">vasco</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">refresh_url</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">?since_id=49187756370444288&amp;q=vasco</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">max_id_str</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">49187756370444288</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">results_per_page</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">1</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">next_page</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">?page=2&amp;max_id=49187756370444288&amp;rpp=1&amp;q=vasco</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">since_id_str</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">0</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">completed_in</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">0.02475</span><br></body></html>','2011-03-19 16:17:53'),(101,7,'<html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><title>tree.xslt output</title><link type=\"text/css\" rel=\"stylesheet\" href=\"tree-view.css\"></head><body><br><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">resultado</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">since_id</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">0</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">max_id</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">49187906010615808</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">results</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">text</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">Obama&nbsp;seeks&nbsp;sports&nbsp;business&nbsp;from&nbsp;Brazil&nbsp;-&nbsp;USA&nbsp;Today&nbsp;http://goo.gl/fb/M9qr0</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">from_user_id</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">56649275</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">geo</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">null</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">iso_language_code</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">en</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">to_user_id</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">null</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">id</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">49187906010615808</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">to_user_id_str</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">null</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">source</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">&amp;lt;a&nbsp;href=&amp;quot;http://www.google.com/support/youtube/bin/answer.py?hl=en&amp;amp;answer=164577&amp;quot;&nbsp;rel=&amp;quot;nofollow&amp;quot;&amp;gt;Google&amp;lt;/a&amp;gt;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">from_user_id_str</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">56649275</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">from_user</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">nalcro</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">created_at</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">Sat,&nbsp;19&nbsp;Mar&nbsp;2011&nbsp;19:18:05&nbsp;+0000</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">id_str</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">49187906010615808</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">profile_image_url</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">http://a3.twimg.com/sticky/default_profile_images/default_profile_2_normal.png</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">metadata</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">result_type</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">recent</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">page</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">1</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">query</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">obama</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">refresh_url</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">?since_id=49187906010615808&amp;q=obama</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">max_id_str</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">49187906010615808</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">results_per_page</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">1</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">next_page</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">?page=2&amp;max_id=49187906010615808&amp;rpp=1&amp;q=obama</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">since_id_str</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">0</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">completed_in</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">0.022197</span><br></body></html>','2011-03-19 16:18:20'),(102,7,'Aplica&ccedil;&atilde;o [Twitter - Lista de twitts] retornou novos dados:<br/><br/><html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><title>tree.xslt output</title><link type=\"text/css\" rel=\"stylesheet\" href=\"tree-view.css\"></head><body><br><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">resultado</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">since_id</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">0</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">max_id</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">49187811940773889</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">results</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">text</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">E&nbsp;falando&nbsp;em&nbsp;futebol...&nbsp;amanh&atilde;&nbsp;tem&nbsp;fog&atilde;o.&nbsp;Contra&nbsp;o&nbsp;vasco.&nbsp;Quero&nbsp;s&oacute;&nbsp;ver&nbsp;como&nbsp;vai&nbsp;ser&nbsp;o&nbsp;joguinho&nbsp;maroto.&nbsp;(=</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">from_user_id</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">189795263</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">geo</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">null</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">iso_language_code</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">pt</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">to_user_id</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">null</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">id</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">49187811940773889</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">to_user_id_str</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">null</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">source</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">&amp;lt;a&nbsp;href=&amp;quot;http://twitter.com/&amp;quot;&amp;gt;web&amp;lt;/a&amp;gt;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">from_user_id_str</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">189795263</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">from_user</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">charles_viniciu</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">created_at</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">Sat,&nbsp;19&nbsp;Mar&nbsp;2011&nbsp;19:17:42&nbsp;+0000</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">id_str</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">49187811940773889</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">profile_image_url</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">http://a1.twimg.com/profile_images/1272528026/charles_vinicius_normal.jpg</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">metadata</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">result_type</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">recent</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">page</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">1</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">query</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">vasco</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">refresh_url</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">?since_id=49187811940773889&amp;q=vasco</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">max_id_str</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">49187811940773889</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">results_per_page</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">1</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">next_page</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">?page=2&amp;max_id=49187811940773889&amp;rpp=1&amp;q=vasco</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">since_id_str</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">0</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">completed_in</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">0.025058</span><br></body></html>','2011-03-19 16:18:23'),(103,7,'Aplica&ccedil;&atilde;o [Twitter - Lista de twitts] retornou novos dados:<br/><br/><html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><title>tree.xslt output</title><link type=\"text/css\" rel=\"stylesheet\" href=\"tree-view.css\"></head><body><br><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">resultado</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">since_id</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">0</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">max_id</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">49188003301691392</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">results</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">text</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">@Vasco_News&nbsp;me&nbsp;passa&nbsp;teu&nbsp;msn&nbsp;por&nbsp;DM&nbsp;!!&nbsp;S&oacute;&nbsp;pra&nbsp;falarmos&nbsp;de&nbsp;vasco&nbsp;!!&nbsp;Juro&nbsp;que&nbsp;n&nbsp;sai&nbsp;nada&nbsp;de&nbsp;la&nbsp;!!&nbsp;#AmoFalarDeVasco&nbsp;!</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">from_user_id</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">125356219</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">geo</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">null</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">iso_language_code</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">pt</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">to_user_id</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">148027078</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">id</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">49188003301691392</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">to_user_id_str</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">148027078</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">source</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">&amp;lt;a&nbsp;href=&amp;quot;http://twitter.com/&amp;quot;&amp;gt;web&amp;lt;/a&amp;gt;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">from_user_id_str</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">125356219</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">from_user</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">mateuscrvg</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">created_at</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">Sat,&nbsp;19&nbsp;Mar&nbsp;2011&nbsp;19:18:28&nbsp;+0000</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">to_user</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">Vasco_News</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">id_str</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">49188003301691392</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">profile_image_url</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">http://a0.twimg.com/profile_images/1238947101/ttu_normal.JPG</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">metadata</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">result_type</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">recent</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">page</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">1</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">query</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">vasco</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">refresh_url</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">?since_id=49188003301691392&amp;q=vasco</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">max_id_str</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">49188003301691392</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">results_per_page</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">1</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">next_page</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">?page=2&amp;max_id=49188003301691392&amp;rpp=1&amp;q=vasco</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">since_id_str</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">0</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">completed_in</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">0.021148</span><br></body></html>','2011-03-19 16:18:53'),(104,7,'Aplica&ccedil;&atilde;o [Twitter - Lista de twitts] retornou novos dados:<br/><br/><html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><title>tree.xslt output</title><link type=\"text/css\" rel=\"stylesheet\" href=\"tree-view.css\"></head><body><br><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">resultado</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">since_id</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">0</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">max_id</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">49188172575412224</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">results</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">text</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">I\'m&nbsp;at&nbsp;Peaje&nbsp;AP68&nbsp;Orozko&nbsp;(Autopista&nbsp;Vasco-Aragonesa,&nbsp;Orozko)&nbsp;http://4sq.com/hxFRIY</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">from_user_id</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">1320108</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">geo</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">null</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">iso_language_code</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">it</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">to_user_id</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">null</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">id</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">49188172575412224</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">to_user_id_str</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">null</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">source</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">&amp;lt;a&nbsp;href=&amp;quot;http://foursquare.com&amp;quot;&nbsp;rel=&amp;quot;nofollow&amp;quot;&amp;gt;foursquare&amp;lt;/a&amp;gt;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">from_user_id_str</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">1320108</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">from_user</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">drolano</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">created_at</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">Sat,&nbsp;19&nbsp;Mar&nbsp;2011&nbsp;19:19:08&nbsp;+0000</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">id_str</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">49188172575412224</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">profile_image_url</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">http://a2.twimg.com/profile_images/1204054069/Dublin_151_normal.jpg</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">metadata</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">result_type</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">recent</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">page</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">1</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">query</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">vasco</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">refresh_url</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">?since_id=49188172575412224&amp;q=vasco</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">max_id_str</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">49188172575412224</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">results_per_page</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">1</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">next_page</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">?page=2&amp;max_id=49188172575412224&amp;rpp=1&amp;q=vasco</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">since_id_str</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">0</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">completed_in</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">0.04412</span><br></body></html>','2011-03-19 16:19:23'),(105,7,'Aplica&ccedil;&atilde;o [Twitter - Lista de twitts] retornou novos dados:<br/><br/><html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><title>tree.xslt output</title><link type=\"text/css\" rel=\"stylesheet\" href=\"tree-view.css\"></head><body><br><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">resultado</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">since_id</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">0</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">max_id</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">49188186399834112</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">results</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">text</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">informa&ccedil;&atilde;o&nbsp;do&nbsp;@futebol_minuto&nbsp;:Eder&nbsp;Lu&iacute;s&nbsp;est&aacute;&nbsp;sendo&nbsp;tentado&nbsp;pelo&nbsp;Palmeiras.Vinculo&nbsp;com&nbsp;o&nbsp;vasco&nbsp;termina&nbsp;no&nbsp;meio&nbsp;do&nbsp;ano.&nbsp;Fernand&atilde;o&nbsp;proximo!</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">from_user_id</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">251455430</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">geo</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">null</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">iso_language_code</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">pt</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">to_user_id</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">null</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">id</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">49188186399834112</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">to_user_id_str</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">null</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">source</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">&amp;lt;a&nbsp;href=&amp;quot;http://twitter.com/&amp;quot;&amp;gt;web&amp;lt;/a&amp;gt;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">from_user_id_str</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">251455430</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">from_user</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">comprasdabola</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">created_at</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">Sat,&nbsp;19&nbsp;Mar&nbsp;2011&nbsp;19:19:12&nbsp;+0000</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">id_str</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">49188186399834112</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">profile_image_url</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">http://a0.twimg.com/profile_images/1274910301/mercado-da-bola_normal.jpg</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">metadata</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">result_type</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">recent</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">page</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">1</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">query</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">vasco</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">refresh_url</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">?since_id=49188186399834112&amp;q=vasco</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">max_id_str</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">49188186399834112</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">results_per_page</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">1</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">next_page</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">?page=2&amp;max_id=49188186399834112&amp;rpp=1&amp;q=vasco</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">since_id_str</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">0</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">completed_in</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">0.023981</span><br></body></html>','2011-03-19 16:19:53'),(106,7,'Aplica&ccedil;&atilde;o [Twitter - Lista de twitts] retornou novos dados:<br/><br/><html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><title>tree.xslt output</title><link type=\"text/css\" rel=\"stylesheet\" href=\"tree-view.css\"></head><body><br><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">resultado</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">since_id</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">0</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">max_id</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">49188492374310912</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">results</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">text</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">&amp;quot;&amp;quot;QUAL&nbsp;TIME&nbsp;&Eacute;&nbsp;MELHOR?<span class=\"escape\">\\n</span>VASCO&nbsp;,&nbsp;FALMENGO&nbsp;,&nbsp;FLUMINENSE&nbsp;&nbsp;OU&nbsp;BOTA&nbsp;FOGO??<span class=\"escape\">\\n</span>RESPOND&Atilde;O&nbsp;A&nbsp;MIM&amp;quot;&amp;quot;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">from_user_id</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">247862329</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">geo</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">null</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">iso_language_code</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">pt</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">to_user_id</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">null</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">id</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">49188492374310912</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">to_user_id_str</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">null</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">source</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">&amp;lt;a&nbsp;href=&amp;quot;http://twitter.com/&amp;quot;&amp;gt;web&amp;lt;/a&amp;gt;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">from_user_id_str</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">247862329</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">from_user</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">NandaOakSouza</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">created_at</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">Sat,&nbsp;19&nbsp;Mar&nbsp;2011&nbsp;19:20:25&nbsp;+0000</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">id_str</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">49188492374310912</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">profile_image_url</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">http://a2.twimg.com/profile_images/1275931093/hdna_normal.jpg</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">metadata</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">result_type</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">recent</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">page</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">1</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">query</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">vasco</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">refresh_url</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">?since_id=49188492374310912&amp;q=vasco</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">max_id_str</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">49188492374310912</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">results_per_page</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">1</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">next_page</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">?page=2&amp;max_id=49188492374310912&amp;rpp=1&amp;q=vasco</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">since_id_str</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">0</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">completed_in</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">0.056639</span><br></body></html>','2011-03-19 16:20:54'),(107,7,'Aplica&ccedil;&atilde;o [Twitter - Lista de twitts] retornou novos dados:<br/><br/><html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><title>tree.xslt output</title><link type=\"text/css\" rel=\"stylesheet\" href=\"tree-view.css\"></head><body><br><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">resultado</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">since_id</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">0</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">max_id</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">49188619860185088</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">results</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">text</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">E&nbsp;meu&nbsp;pai&nbsp;ainda&nbsp;falou&nbsp;q&nbsp;o&nbsp;Juninho&nbsp;Pernambucano&nbsp;ta&nbsp;mt&nbsp;velho&nbsp;pra&nbsp;jogar&nbsp;no&nbsp;#Vasco,&nbsp;isso&nbsp;q&nbsp;&eacute;&nbsp;um&nbsp;vascaino&nbsp;pessimista&nbsp;kkkk</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">from_user_id</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">213723866</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">geo</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">null</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">iso_language_code</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">pt</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">to_user_id</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">null</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">id</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">49188619860185088</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">to_user_id_str</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">null</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">source</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">&amp;lt;a&nbsp;href=&amp;quot;http://twitter.com/&amp;quot;&amp;gt;web&amp;lt;/a&amp;gt;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">from_user_id_str</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">213723866</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">from_user</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">MaatheeusSoouza</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">created_at</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">Sat,&nbsp;19&nbsp;Mar&nbsp;2011&nbsp;19:20:55&nbsp;+0000</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">id_str</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">49188619860185088</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">profile_image_url</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">http://a2.twimg.com/profile_images/1268275738/007_normal.jpg</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">metadata</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">result_type</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">recent</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">page</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">1</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">query</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">vasco</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">refresh_url</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">?since_id=49188619860185088&amp;q=vasco</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">max_id_str</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">49188619860185088</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">results_per_page</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">1</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">next_page</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">?page=2&amp;max_id=49188619860185088&amp;rpp=1&amp;q=vasco</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">since_id_str</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">0</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">completed_in</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">0.225151</span><br></body></html>','2011-03-19 16:21:24');
/*!40000 ALTER TABLE `ae_usuario_aplicacao_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ae_usuario_aplicacao_parametro`
--

DROP TABLE IF EXISTS `ae_usuario_aplicacao_parametro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ae_usuario_aplicacao_parametro` (
  `id_usuario_aplicacao` int(10) unsigned NOT NULL,
  `id_parametro` int(10) unsigned NOT NULL,
  `valor_padrao` varchar(255) DEFAULT NULL,
  `bloquear_valor` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_usuario_aplicacao`,`id_parametro`),
  KEY `ae_parametro_fk` (`id_parametro`),
  KEY `ae_usuario_aplicacao_fk` (`id_usuario_aplicacao`),
  CONSTRAINT `ae_parametro_fk` FOREIGN KEY (`id_parametro`) REFERENCES `ae_parametro` (`id_parametro`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ae_usuario_aplicacao_fk` FOREIGN KEY (`id_usuario_aplicacao`) REFERENCES `ae_usuario_aplicacao` (`id_usuario_aplicacao`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ae_usuario_aplicacao_parametro`
--

LOCK TABLES `ae_usuario_aplicacao_parametro` WRITE;
/*!40000 ALTER TABLE `ae_usuario_aplicacao_parametro` DISABLE KEYS */;
INSERT INTO `ae_usuario_aplicacao_parametro` VALUES (7,65,'vasco',0),(7,66,'1',0),(7,67,'recent',1);
/*!40000 ALTER TABLE `ae_usuario_aplicacao_parametro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tag` (
  `pk_tag` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `no_tag` varchar(60) NOT NULL,
  `ds_tag` text NOT NULL,
  PRIMARY KEY (`pk_tag`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
INSERT INTO `tag` VALUES (1,'zzzzzzzz','zfffffffffff'),(2,'afffff','aaa'),(3,'Neuro','dsadsa'),(4,'kkkk','kkk'),(5,'Neuro','adhsaldjlsajdlsjad askljdsakljdklasj dklsadsad');
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `arquivoblog`
--

DROP TABLE IF EXISTS `arquivoblog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `arquivoblog` (
  `pk_arquivoBlog` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `no_arquivoBlog` varchar(60) NOT NULL,
  `fk_blog` int(10) unsigned NOT NULL,
  PRIMARY KEY (`pk_arquivoBlog`),
  KEY `FK_arquivoBlog_1` (`fk_blog`),
  CONSTRAINT `FK_arquivoBlog_1` FOREIGN KEY (`fk_blog`) REFERENCES `blog` (`pk_blog`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `arquivoblog`
--

LOCK TABLES `arquivoblog` WRITE;
/*!40000 ALTER TABLE `arquivoblog` DISABLE KEYS */;
/*!40000 ALTER TABLE `arquivoblog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `artefato`
--

DROP TABLE IF EXISTS `artefato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `artefato` (
  `pk_artefato` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `no_autor` varchar(100) NOT NULL,
  `nu_anoPublicacao` int(10) unsigned NOT NULL,
  `fk_catArtefato` int(10) unsigned NOT NULL,
  PRIMARY KEY (`pk_artefato`),
  KEY `FK_artefato_2` (`fk_catArtefato`),
  CONSTRAINT `FK_artefato_1` FOREIGN KEY (`pk_artefato`) REFERENCES `servico` (`pk_servico`),
  CONSTRAINT `FK_artefato_2` FOREIGN KEY (`fk_catArtefato`) REFERENCES `categoriaartefato` (`pk_catArtefato`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artefato`
--

LOCK TABLES `artefato` WRITE;
/*!40000 ALTER TABLE `artefato` DISABLE KEYS */;
/*!40000 ALTER TABLE `artefato` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recomendacaomembro`
--

DROP TABLE IF EXISTS `recomendacaomembro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recomendacaomembro` (
  `fk_membroRecomendador` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fk_membroReceptor` int(10) unsigned NOT NULL,
  `fk_membroRecomendado` int(10) unsigned NOT NULL,
  PRIMARY KEY (`fk_membroRecomendador`,`fk_membroReceptor`,`fk_membroRecomendado`),
  KEY `FK_RecomendacaoMembro_2` (`fk_membroReceptor`),
  KEY `FK_RecomendacaoMembro_3` (`fk_membroRecomendado`),
  CONSTRAINT `FK_RecomendacaoMembro_1` FOREIGN KEY (`fk_membroRecomendador`) REFERENCES `membro` (`pk_membro`),
  CONSTRAINT `FK_RecomendacaoMembro_2` FOREIGN KEY (`fk_membroReceptor`) REFERENCES `membro` (`pk_membro`),
  CONSTRAINT `FK_RecomendacaoMembro_3` FOREIGN KEY (`fk_membroRecomendado`) REFERENCES `membro` (`pk_membro`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recomendacaomembro`
--

LOCK TABLES `recomendacaomembro` WRITE;
/*!40000 ALTER TABLE `recomendacaomembro` DISABLE KEYS */;
/*!40000 ALTER TABLE `recomendacaomembro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tarefa`
--

DROP TABLE IF EXISTS `tarefa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tarefa` (
  `pk_tarefa` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `no_tarefa` varchar(60) NOT NULL,
  `ds_tarefa` text NOT NULL,
  `dt_tarefa` datetime NOT NULL,
  `fk_etapa` int(10) unsigned NOT NULL,
  PRIMARY KEY (`pk_tarefa`),
  KEY `FK_tarefa_1` (`fk_etapa`),
  CONSTRAINT `FK_tarefa_1` FOREIGN KEY (`fk_etapa`) REFERENCES `etapa` (`pk_etapa`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tarefa`
--

LOCK TABLES `tarefa` WRITE;
/*!40000 ALTER TABLE `tarefa` DISABLE KEYS */;
INSERT INTO `tarefa` VALUES (1,'Entidade','O que é uma entidade.','2009-11-23 00:00:00',1),(2,'Relacionamento','O que é um relacionamento.','2009-11-23 00:00:00',1),(3,'wwww','asdasds','2010-02-28 00:00:00',2);
/*!40000 ALTER TABLE `tarefa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uf`
--

DROP TABLE IF EXISTS `uf`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `uf` (
  `pk_estado` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `no_estado` varchar(60) NOT NULL,
  `sg_estado` varchar(2) NOT NULL,
  PRIMARY KEY (`pk_estado`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uf`
--

LOCK TABLES `uf` WRITE;
/*!40000 ALTER TABLE `uf` DISABLE KEYS */;
INSERT INTO `uf` VALUES (1,'Rio de Janeiro','RJ'),(2,'São Paulo','SP'),(3,'Minas Gerais','MG'),(4,'Espírito Santo','ES'),(5,'Bahia','BA');
/*!40000 ALTER TABLE `uf` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servico`
--

DROP TABLE IF EXISTS `servico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `servico` (
  `pk_servico` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `no_servico` varchar(200) NOT NULL,
  `dt_servico` datetime NOT NULL,
  `ds_servico` text NOT NULL,
  `no_imagem` varchar(100) NOT NULL,
  `st_servico` varchar(1) NOT NULL,
  `fl_automatico` varchar(1) NOT NULL,
  `fk_ambiente` int(10) unsigned NOT NULL,
  `fk_membro` int(10) unsigned NOT NULL,
  PRIMARY KEY (`pk_servico`),
  KEY `FK_servico_1` (`fk_ambiente`),
  KEY `FK_servico_2` (`fk_membro`),
  CONSTRAINT `FK_servico_1` FOREIGN KEY (`fk_ambiente`) REFERENCES `ambiente` (`pk_ambiente`),
  CONSTRAINT `FK_servico_2` FOREIGN KEY (`fk_membro`) REFERENCES `membro` (`pk_membro`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servico`
--

LOCK TABLES `servico` WRITE;
/*!40000 ALTER TABLE `servico` DISABLE KEYS */;
INSERT INTO `servico` VALUES (1,'Entidade','2009-11-22 16:04:41','Entidade','','A','N',2,2),(2,'Relacionamento','2009-11-22 16:04:56','Relacionamento','','A','N',2,2),(6,'aafff','2010-01-19 11:54:37','asdadddddd\n\n\naff\nopiopç\npópóp\noíop´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´íouhyouiuiuioyu.tgthyt   ry dtrytryuftryutyuityi','','A','N',2,2),(9,'ddddddd','2010-01-19 13:57:26','ddasdasda','','A','N',2,2),(10,'affff','2010-01-21 11:11:22','asdadablzmklnmnm,n,','','A','N',2,2),(11,'Testando link','2010-01-21 11:17:48','<center><b>Titulo</b></center>\n<p>\nTestando descrição\n</p>\n<img src=\"http://seriesedesenhos.com/br2/images/stories/Little%20Richard.jpg\" alt=\"\" />\n<a href=\"http://jqueryui.com/\" target=\"_blank\">jquery</a>','','A','N',2,2),(12,'teste','2010-01-21 11:18:30','afffffffff','','A','N',2,2),(13,'uiuhjk','2010-01-25 11:50:50','jhkhjk\n\n\ndasdsada','','A','N',2,2),(14,'kkk','2010-01-27 16:54:57','aaaa','','A','N',2,2),(15,'zzzz','2010-01-27 16:55:03','affff','','A','N',2,2),(16,'asdsa','2010-01-27 16:55:42','aaaa','','A','N',2,2),(17,'zzz','2010-01-27 16:56:09','aaaa','','A','N',2,2),(18,'aaa','2010-01-27 16:56:23','fffff','','A','N',2,2),(19,'zzzzz','2010-01-27 16:56:27','zafffff','','A','N',2,2),(20,'kkkkkkkkkkk','2010-01-27 16:57:02','adasd','','A','N',2,2),(21,'teste','2010-01-28 11:00:48','affff','','A','N',2,2),(22,'kkk','2010-01-28 11:58:21','afff','','A','N',2,2),(23,'adasd','2010-01-28 12:08:44','afff','','A','N',2,2),(24,'ttt','2010-01-28 12:08:51','aaaaaa','','A','N',2,2),(25,'aa','2010-01-28 13:29:37','fff','','A','N',2,2),(26,'uu','2010-01-28 13:29:43','aaa','','A','N',2,2),(27,'aa','2010-01-28 13:36:40','ff','','A','N',2,2),(28,'afff','2010-01-28 13:51:46','aaa','','A','N',2,2),(29,'zz','2010-01-28 13:51:52','aaa','','A','N',2,2),(30,'uuu','2010-01-28 13:51:57','aaa','','A','N',2,2),(31,',lçmlç,','2010-01-28 14:00:39','jbjj','','A','N',2,2),(32,'afff','2010-01-28 15:35:24','aaa','','A','N',2,2),(33,'az','2010-01-28 15:35:37','aff','','A','N',2,2),(34,'aff','2010-01-28 15:43:30','aaaa','','A','N',2,2),(35,'zzz','2010-01-28 15:43:51','aa','','A','N',2,2),(36,'akkk','2010-01-28 15:44:12','asa','','A','N',2,2),(37,'dsadsad','2010-02-01 15:35:24','asdsadasdas','','A','N',14,2),(38,'asasdad','2010-02-01 15:46:32',' mdsbfdfdsbfmxcxzczxc','','A','N',14,2);
/*!40000 ALTER TABLE `servico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ambiente`
--

DROP TABLE IF EXISTS `ambiente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ambiente` (
  `pk_ambiente` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `no_ambiente` varchar(60) NOT NULL,
  `dt_ambiente` datetime NOT NULL,
  `ds_ambiente` text NOT NULL,
  `no_imagem` varchar(100) NOT NULL,
  `fk_professor` int(10) unsigned NOT NULL,
  PRIMARY KEY (`pk_ambiente`),
  KEY `FK_ambiente_1` (`fk_professor`),
  CONSTRAINT `FK_ambiente_1` FOREIGN KEY (`fk_professor`) REFERENCES `membro` (`pk_membro`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ambiente`
--

LOCK TABLES `ambiente` WRITE;
/*!40000 ALTER TABLE `ambiente` DISABLE KEYS */;
INSERT INTO `ambiente` VALUES (1,'Projeto Final','2009-11-22 15:29:00','Ambiente destinado aos alunos aptos a iniciarem seus projetos de conclusão de curso','lll',4),(2,'Banco de Dados','2009-11-22 15:31:00','Ambiente destinado aos alunos que desejam aprender a disciplina de Banco de Dados','/',2),(3,'Tópicos Especiais II','2009-11-22 15:32:00','Ambiente destinado aos alunos que desejam aprender a disciplina de Tópicos Especiais II','lll',3),(4,'aaa','2010-01-27 13:47:35','ffff','',2),(5,'xxxx','2010-01-27 13:47:43','ffff','',2),(6,'pppppppp','2010-01-27 13:47:50','aaaaaaa','',2),(7,'zzzzzz','2010-01-27 13:47:56','ffffffffffff','',2),(8,'ffasdas','2010-01-27 13:48:03','fffff','',2),(9,'xxxxxxxxxhhhh','2010-01-27 13:48:43','hhhh','',2),(10,'fff','2010-01-27 13:49:25','aaaa','',2),(11,'ttt','2010-01-27 13:49:31','aaa','',2),(12,'zzzz','2010-01-27 13:49:35','huhu','',2),(13,'pipip','2010-01-27 13:49:41','opoppo','',2),(14,'Neurociências','2010-02-01 15:22:48','Estudo de Neurociências Cognitivas','',2);
/*!40000 ALTER TABLE `ambiente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blog`
--

DROP TABLE IF EXISTS `blog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `blog` (
  `pk_blog` int(10) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`pk_blog`),
  CONSTRAINT `FK_blog_1` FOREIGN KEY (`pk_blog`) REFERENCES `servico` (`pk_servico`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog`
--

LOCK TABLES `blog` WRITE;
/*!40000 ALTER TABLE `blog` DISABLE KEYS */;
INSERT INTO `blog` VALUES (6),(11),(12),(13),(14),(15),(16),(17),(18),(19),(20),(21),(22),(23),(24),(25),(26),(27),(28),(29),(30),(31),(32),(33),(34),(35),(36);
/*!40000 ALTER TABLE `blog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cursoextensao`
--

DROP TABLE IF EXISTS `cursoextensao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cursoextensao` (
  `pk_cursoExtensao` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `no_cursoExtensao` varchar(60) NOT NULL,
  `ds_cursoExtensao` text NOT NULL,
  `fk_membro` int(10) unsigned NOT NULL,
  PRIMARY KEY (`pk_cursoExtensao`),
  KEY `FK_cursoExtensao_1` (`fk_membro`),
  CONSTRAINT `FK_cursoExtensao_1` FOREIGN KEY (`fk_membro`) REFERENCES `membro` (`pk_membro`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cursoextensao`
--

LOCK TABLES `cursoextensao` WRITE;
/*!40000 ALTER TABLE `cursoextensao` DISABLE KEYS */;
INSERT INTO `cursoextensao` VALUES (1,'Banco de Dados Espacial','Modelagem de dados usando um banco de dados espacial.',2);
/*!40000 ALTER TABLE `cursoextensao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `membroidioma`
--

DROP TABLE IF EXISTS `membroidioma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `membroidioma` (
  `fk_membro` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fk_idioma` int(10) unsigned NOT NULL,
  PRIMARY KEY (`fk_membro`,`fk_idioma`),
  KEY `FK_membroIdioma_2` (`fk_idioma`),
  CONSTRAINT `FK_membroIdioma_1` FOREIGN KEY (`fk_membro`) REFERENCES `membro` (`pk_membro`),
  CONSTRAINT `FK_membroIdioma_2` FOREIGN KEY (`fk_idioma`) REFERENCES `idioma` (`pk_idioma`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `membroidioma`
--

LOCK TABLES `membroidioma` WRITE;
/*!40000 ALTER TABLE `membroidioma` DISABLE KEYS */;
INSERT INTO `membroidioma` VALUES (2,1),(2,2);
/*!40000 ALTER TABLE `membroidioma` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comunidadeartefato`
--

DROP TABLE IF EXISTS `comunidadeartefato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comunidadeartefato` (
  `fk_comunidade` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fk_artefato` int(10) unsigned NOT NULL,
  PRIMARY KEY (`fk_comunidade`,`fk_artefato`),
  KEY `FK_comunidadeArtefato_2` (`fk_artefato`),
  CONSTRAINT `FK_comunidadeArtefato_1` FOREIGN KEY (`fk_comunidade`) REFERENCES `comunidade` (`pk_comunidade`),
  CONSTRAINT `FK_comunidadeArtefato_2` FOREIGN KEY (`fk_artefato`) REFERENCES `artefato` (`pk_artefato`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comunidadeartefato`
--

LOCK TABLES `comunidadeartefato` WRITE;
/*!40000 ALTER TABLE `comunidadeartefato` DISABLE KEYS */;
/*!40000 ALTER TABLE `comunidadeartefato` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `areatrabalho`
--

DROP TABLE IF EXISTS `areatrabalho`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `areatrabalho` (
  `pk_areaTrabalho` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `no_areaTrabalho` varchar(60) NOT NULL,
  `ds_areaTrabalho` text NOT NULL,
  `fk_membro` int(10) unsigned NOT NULL,
  PRIMARY KEY (`pk_areaTrabalho`),
  KEY `FK_areatrabalho_1` (`fk_membro`),
  CONSTRAINT `FK_areatrabalho_1` FOREIGN KEY (`fk_membro`) REFERENCES `membro` (`pk_membro`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `areatrabalho`
--

LOCK TABLES `areatrabalho` WRITE;
/*!40000 ALTER TABLE `areatrabalho` DISABLE KEYS */;
INSERT INTO `areatrabalho` VALUES (1,'Professor Universitário','Ministro aulas para ensino superior',2);
/*!40000 ALTER TABLE `areatrabalho` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `posttopico`
--

DROP TABLE IF EXISTS `posttopico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `posttopico` (
  `pk_postTopico` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ds_post` text NOT NULL,
  `dt_post` datetime NOT NULL,
  `fk_topico` int(10) unsigned NOT NULL,
  `fk_membro` int(10) unsigned NOT NULL,
  PRIMARY KEY (`pk_postTopico`),
  KEY `FK_postTopico_1` (`fk_topico`),
  KEY `FK_posttopico_2` (`fk_membro`),
  CONSTRAINT `FK_postTopico_1` FOREIGN KEY (`fk_topico`) REFERENCES `topico` (`pk_topico`),
  CONSTRAINT `FK_posttopico_2` FOREIGN KEY (`fk_membro`) REFERENCES `membro` (`pk_membro`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `posttopico`
--

LOCK TABLES `posttopico` WRITE;
/*!40000 ALTER TABLE `posttopico` DISABLE KEYS */;
INSERT INTO `posttopico` VALUES (1,'Você precisa identificar os relacionamentos e as entidades','2009-11-22 16:06:21',1,2),(2,'É simples.','2009-11-22 16:30:40',1,5),(3,'teste\n\né isso ai','2010-01-21 14:27:55',1,2),(4,'.m,çm,lç','2010-01-28 14:11:21',1,2),(5,'dsasdsadsadas','2010-02-01 15:47:57',4,2);
/*!40000 ALTER TABLE `posttopico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `municipio`
--

DROP TABLE IF EXISTS `municipio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `municipio` (
  `pk_municipio` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `no_municipio` varchar(60) NOT NULL,
  `fk_estado` int(10) unsigned NOT NULL,
  PRIMARY KEY (`pk_municipio`),
  KEY `FK_municipio_1` (`fk_estado`),
  CONSTRAINT `FK_municipio_1` FOREIGN KEY (`fk_estado`) REFERENCES `uf` (`pk_estado`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `municipio`
--

LOCK TABLES `municipio` WRITE;
/*!40000 ALTER TABLE `municipio` DISABLE KEYS */;
INSERT INTO `municipio` VALUES (1,'Rio de Janeiro',1),(3,'Niterói',1);
/*!40000 ALTER TABLE `municipio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categoriacomunidade`
--

DROP TABLE IF EXISTS `categoriacomunidade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categoriacomunidade` (
  `pk_catComunidade` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `no_catComunidade` varchar(60) NOT NULL,
  `ds_catComunidade` text NOT NULL,
  PRIMARY KEY (`pk_catComunidade`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoriacomunidade`
--

LOCK TABLES `categoriacomunidade` WRITE;
/*!40000 ALTER TABLE `categoriacomunidade` DISABLE KEYS */;
/*!40000 ALTER TABLE `categoriacomunidade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categoriaartefato`
--

DROP TABLE IF EXISTS `categoriaartefato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categoriaartefato` (
  `pk_catArtefato` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `no_catArtefato` varchar(60) NOT NULL,
  `ds_catArtefato` text NOT NULL,
  PRIMARY KEY (`pk_catArtefato`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoriaartefato`
--

LOCK TABLES `categoriaartefato` WRITE;
/*!40000 ALTER TABLE `categoriaartefato` DISABLE KEYS */;
INSERT INTO `categoriaartefato` VALUES (1,'Artigos','Artigos Científicos'),(2,'Dissertações','Dissertações de Mestrado'),(3,'Teses','Teses de Doutorado');
/*!40000 ALTER TABLE `categoriaartefato` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comunidade`
--

DROP TABLE IF EXISTS `comunidade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comunidade` (
  `pk_comunidade` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fk_catComunidade` int(10) unsigned NOT NULL,
  PRIMARY KEY (`pk_comunidade`),
  KEY `FK_comunidade_2` (`fk_catComunidade`),
  CONSTRAINT `FK_comunidade_1` FOREIGN KEY (`pk_comunidade`) REFERENCES `servico` (`pk_servico`),
  CONSTRAINT `FK_comunidade_2` FOREIGN KEY (`fk_catComunidade`) REFERENCES `categoriacomunidade` (`pk_catComunidade`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comunidade`
--

LOCK TABLES `comunidade` WRITE;
/*!40000 ALTER TABLE `comunidade` DISABLE KEYS */;
/*!40000 ALTER TABLE `comunidade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `microblog`
--

DROP TABLE IF EXISTS `microblog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `microblog` (
  `pk_microblog` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ds_microblog` text NOT NULL,
  `fk_membro` int(10) unsigned NOT NULL,
  `st_atual` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`pk_microblog`),
  KEY `FK_microblog_1` (`fk_membro`),
  CONSTRAINT `FK_microblog_1` FOREIGN KEY (`fk_membro`) REFERENCES `membro` (`pk_membro`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `microblog`
--

LOCK TABLES `microblog` WRITE;
/*!40000 ALTER TABLE `microblog` DISABLE KEYS */;
INSERT INTO `microblog` VALUES (2,'Professor Legal 2',2,0),(3,'Meu microblog.',5,1),(4,'Segundo MicroBlog',2,1);
/*!40000 ALTER TABLE `microblog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipocontato`
--

DROP TABLE IF EXISTS `tipocontato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipocontato` (
  `pk_tipoContato` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ds_tipoContato` varchar(60) NOT NULL,
  PRIMARY KEY (`pk_tipoContato`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipocontato`
--

LOCK TABLES `tipocontato` WRITE;
/*!40000 ALTER TABLE `tipocontato` DISABLE KEYS */;
INSERT INTO `tipocontato` VALUES (1,'E-mail'),(2,'Telefone Residencial'),(3,'Telefone Comercial'),(4,'Site'),(5,'Celular');
/*!40000 ALTER TABLE `tipocontato` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ae_processamento`
--

DROP TABLE IF EXISTS `ae_processamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ae_processamento` (
  `id_processamento` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `descricao` varchar(200) NOT NULL,
  PRIMARY KEY (`id_processamento`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ae_processamento`
--

LOCK TABLES `ae_processamento` WRITE;
/*!40000 ALTER TABLE `ae_processamento` DISABLE KEYS */;
INSERT INTO `ae_processamento` VALUES (1,'Arquivo WADL'),(2,'URL por Reflexão');
/*!40000 ALTER TABLE `ae_processamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `administrador`
--

DROP TABLE IF EXISTS `administrador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `administrador` (
  `pk_administrador` int(10) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`pk_administrador`),
  CONSTRAINT `FK_administrador_1` FOREIGN KEY (`pk_administrador`) REFERENCES `usuario` (`pk_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrador`
--

LOCK TABLES `administrador` WRITE;
/*!40000 ALTER TABLE `administrador` DISABLE KEYS */;
INSERT INTO `administrador` VALUES (1);
/*!40000 ALTER TABLE `administrador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `aula`
--

DROP TABLE IF EXISTS `aula`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aula` (
  `pk_aula` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ds_assunto` text NOT NULL,
  `ds_aula` text NOT NULL,
  `nu_peso` int(10) unsigned NOT NULL,
  `fk_curso` int(10) unsigned NOT NULL,
  `dt_aula` datetime NOT NULL,
  PRIMARY KEY (`pk_aula`),
  KEY `FK_aula_1` (`fk_curso`),
  CONSTRAINT `FK_aula_1` FOREIGN KEY (`fk_curso`) REFERENCES `curso` (`pk_curso`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aula`
--

LOCK TABLES `aula` WRITE;
/*!40000 ALTER TABLE `aula` DISABLE KEYS */;
INSERT INTO `aula` VALUES (1,'te','ste',123,9,'2010-01-15 00:00:00'),(2,'zzz','zzz',12,9,'2010-01-09 00:00:00'),(3,'zzzhhhhhh','aaaa',55,9,'2010-01-02 00:00:00'),(4,'cxzcxzcz','czxcxzc',1,37,'2010-02-28 00:00:00'),(5,'aaaa','aaaa',1,37,'2010-02-28 00:00:00'),(6,'fdfdsfsdf','sdfsdfsd',1,37,'2010-02-28 00:00:00');
/*!40000 ALTER TABLE `aula` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categoriaforum`
--

DROP TABLE IF EXISTS `categoriaforum`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categoriaforum` (
  `pk_catForum` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `no_catForum` varchar(60) NOT NULL,
  `ds_catForum` text NOT NULL,
  `fk_etapa` int(10) unsigned NOT NULL,
  PRIMARY KEY (`pk_catForum`),
  KEY `FK_categoriaForum_1` (`fk_etapa`),
  CONSTRAINT `FK_categoriaForum_1` FOREIGN KEY (`fk_etapa`) REFERENCES `etapa` (`pk_etapa`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoriaforum`
--

LOCK TABLES `categoriaforum` WRITE;
/*!40000 ALTER TABLE `categoriaforum` DISABLE KEYS */;
INSERT INTO `categoriaforum` VALUES (1,'DER','Como criar um der',1),(2,'Leitura','dfdsfdsf',2);
/*!40000 ALTER TABLE `categoriaforum` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `meta`
--

DROP TABLE IF EXISTS `meta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `meta` (
  `pk_meta` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ds_meta` text NOT NULL,
  `ds_duracao` text NOT NULL,
  `fk_ambiente` int(10) unsigned NOT NULL,
  PRIMARY KEY (`pk_meta`),
  KEY `FK_meta_1` (`fk_ambiente`),
  CONSTRAINT `FK_meta_1` FOREIGN KEY (`fk_ambiente`) REFERENCES `ambiente` (`pk_ambiente`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `meta`
--

LOCK TABLES `meta` WRITE;
/*!40000 ALTER TABLE `meta` DISABLE KEYS */;
INSERT INTO `meta` VALUES (1,'Modelagem de Dados','6 meses',2),(2,'SQL','6 meses',2),(3,'SQL Avançado','6 meses',2),(4,'Estudar Seminário','3',14);
/*!40000 ALTER TABLE `meta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mensagem`
--

DROP TABLE IF EXISTS `mensagem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mensagem` (
  `pk_mensagem` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ds_assunto` text NOT NULL,
  `ds_mensagem` text NOT NULL,
  `fk_membroOrigem` int(10) unsigned NOT NULL,
  `fk_membroDestino` int(10) unsigned NOT NULL,
  PRIMARY KEY (`pk_mensagem`),
  KEY `FK_mensagem_1` (`fk_membroOrigem`),
  KEY `FK_mensagem_2` (`fk_membroDestino`),
  CONSTRAINT `FK_mensagem_1` FOREIGN KEY (`fk_membroOrigem`) REFERENCES `membro` (`pk_membro`),
  CONSTRAINT `FK_mensagem_2` FOREIGN KEY (`fk_membroDestino`) REFERENCES `membro` (`pk_membro`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mensagem`
--

LOCK TABLES `mensagem` WRITE;
/*!40000 ALTER TABLE `mensagem` DISABLE KEYS */;
INSERT INTO `mensagem` VALUES (1,'Teste','Oi tudo bem?',2,2),(2,'Duvida','Não consegui criar um der',5,2),(3,'prova','o que irÃ¡ cair na prova?',5,2),(4,'hehe','heherjr',2,5),(5,'teste','afffgggg',5,2),(6,'teste','affffffff',6,2),(7,'teste','affff',2,2),(8,'herhehe','affsrgsdfgdfgf',2,6),(9,'Para mim msm','testando\n\nbreak line\n\n...\n\né é é :D',2,2),(10,'m,nm,l','555565555',2,2),(11,'klçmklmmmmmmmmmmmmmmmm',';;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;',2,2);
/*!40000 ALTER TABLE `mensagem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ae_parametro`
--

DROP TABLE IF EXISTS `ae_parametro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ae_parametro` (
  `id_parametro` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_recurso` int(10) unsigned NOT NULL,
  `id_aplicacao` int(10) unsigned DEFAULT NULL COMMENT 'EXCLUIR',
  `nome` varchar(100) DEFAULT NULL,
  `titulo` varchar(200) DEFAULT NULL,
  `obrigatorio` smallint(5) unsigned DEFAULT '0',
  PRIMARY KEY (`id_parametro`),
  KEY `FK_ae_parametro_2` (`id_aplicacao`),
  KEY `FK_ae_parametro_1` (`id_recurso`),
  CONSTRAINT `FK_ae_parametro_1` FOREIGN KEY (`id_recurso`) REFERENCES `ae_recurso` (`id_recurso`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_ae_parametro_2` FOREIGN KEY (`id_aplicacao`) REFERENCES `ae_aplicacao` (`id_aplicacao`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ae_parametro`
--

LOCK TABLES `ae_parametro` WRITE;
/*!40000 ALTER TABLE `ae_parametro` DISABLE KEYS */;
INSERT INTO `ae_parametro` VALUES (43,64,NULL,'results','Quantidade de Resultados',1),(65,72,NULL,'q','q (Sample Value: neuro)',0),(66,72,NULL,'rpp','rpp (Sample Value: 5)',0),(67,72,NULL,'result_type','result_type (Sample Value: recent)',0);
/*!40000 ALTER TABLE `ae_parametro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ae_autenticacao`
--

DROP TABLE IF EXISTS `ae_autenticacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ae_autenticacao` (
  `id_autenticacao` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `descricao` varchar(200) NOT NULL,
  PRIMARY KEY (`id_autenticacao`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ae_autenticacao`
--

LOCK TABLES `ae_autenticacao` WRITE;
/*!40000 ALTER TABLE `ae_autenticacao` DISABLE KEYS */;
INSERT INTO `ae_autenticacao` VALUES (1,'Básica'),(2,'OpenID'),(3,'GAuth (Google)');
/*!40000 ALTER TABLE `ae_autenticacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ae_usuario_autenticacao`
--

DROP TABLE IF EXISTS `ae_usuario_autenticacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ae_usuario_autenticacao` (
  `openid_url` varchar(255) DEFAULT NULL,
  `pk_usuario` int(10) unsigned NOT NULL,
  `login_relembrar` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`pk_usuario`) USING BTREE,
  CONSTRAINT `FK_ae_usuario_autenticacao_1` FOREIGN KEY (`pk_usuario`) REFERENCES `usuario` (`pk_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ae_usuario_autenticacao`
--

LOCK TABLES `ae_usuario_autenticacao` WRITE;
/*!40000 ALTER TABLE `ae_usuario_autenticacao` DISABLE KEYS */;
/*!40000 ALTER TABLE `ae_usuario_autenticacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `etapa`
--

DROP TABLE IF EXISTS `etapa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `etapa` (
  `pk_etapa` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `no_etapa` varchar(60) NOT NULL,
  `ds_etapa` text NOT NULL,
  `dt_etapa` datetime NOT NULL,
  `fk_meta` int(10) unsigned NOT NULL,
  PRIMARY KEY (`pk_etapa`),
  KEY `FK_etapa_1` (`fk_meta`),
  CONSTRAINT `FK_etapa_1` FOREIGN KEY (`fk_meta`) REFERENCES `meta` (`pk_meta`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `etapa`
--

LOCK TABLES `etapa` WRITE;
/*!40000 ALTER TABLE `etapa` DISABLE KEYS */;
INSERT INTO `etapa` VALUES (1,'DER','Diagrama de Entidade e Relacionamento','2009-11-22 00:00:00',1),(2,'dasd','asdasd','2010-02-28 00:00:00',4);
/*!40000 ALTER TABLE `etapa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topico`
--

DROP TABLE IF EXISTS `topico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `topico` (
  `pk_topico` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ds_topico` text NOT NULL,
  `dt_topico` datetime NOT NULL,
  `fk_forum` int(10) unsigned NOT NULL,
  `fk_membro` int(10) unsigned NOT NULL,
  PRIMARY KEY (`pk_topico`),
  KEY `FK_topico_1` (`fk_forum`),
  KEY `FK_topico_2` (`fk_membro`),
  CONSTRAINT `FK_topico_1` FOREIGN KEY (`fk_forum`) REFERENCES `forum` (`pk_forum`),
  CONSTRAINT `FK_topico_2` FOREIGN KEY (`fk_membro`) REFERENCES `membro` (`pk_membro`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topico`
--

LOCK TABLES `topico` WRITE;
/*!40000 ALTER TABLE `topico` DISABLE KEYS */;
INSERT INTO `topico` VALUES (1,'Como se cria um der?','2009-11-22 16:05:35',2,2),(2,'teeste','2009-12-02 14:03:10',2,5),(3,'teste','2009-12-02 14:07:44',1,5),(4,'sdasdasdsad','2010-02-01 15:47:36',38,2);
/*!40000 ALTER TABLE `topico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `postblog`
--

DROP TABLE IF EXISTS `postblog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `postblog` (
  `pk_postBlog` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ds_post` text NOT NULL,
  `dt_post` datetime NOT NULL,
  `fk_blog` int(10) unsigned NOT NULL,
  `fk_membro` int(10) unsigned NOT NULL,
  PRIMARY KEY (`pk_postBlog`),
  KEY `FK_postBlog_1` (`fk_blog`),
  KEY `FK_postBlog_2` (`fk_membro`),
  CONSTRAINT `FK_postBlog_1` FOREIGN KEY (`fk_blog`) REFERENCES `blog` (`pk_blog`),
  CONSTRAINT `FK_postBlog_2` FOREIGN KEY (`fk_membro`) REFERENCES `membro` (`pk_membro`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `postblog`
--

LOCK TABLES `postblog` WRITE;
/*!40000 ALTER TABLE `postblog` DISABLE KEYS */;
/*!40000 ALTER TABLE `postblog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `arquivoaula`
--

DROP TABLE IF EXISTS `arquivoaula`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `arquivoaula` (
  `pk_arquivoAula` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `no_arquivoAula` varchar(100) NOT NULL,
  `fk_aula` int(10) unsigned NOT NULL,
  PRIMARY KEY (`pk_arquivoAula`),
  KEY `FK_arquivoAula_1` (`fk_aula`),
  CONSTRAINT `FK_arquivoAula_1` FOREIGN KEY (`fk_aula`) REFERENCES `aula` (`pk_aula`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `arquivoaula`
--

LOCK TABLES `arquivoaula` WRITE;
/*!40000 ALTER TABLE `arquivoaula` DISABLE KEYS */;
INSERT INTO `arquivoaula` VALUES (1,'c4ca4238a0b923820dcc509a6f75849b/fundo-azul-vibrante-thumb5677811.jpg',1),(2,'c4ca4238a0b923820dcc509a6f75849b/fundo-azul-vibrante-thumb5677811.jpg',1),(3,'c4ca4238a0b923820dcc509a6f75849b/eclipse-php-galileo-sr1-win32.zip',1),(4,'c4ca4238a0b923820dcc509a6f75849b/backup_de_alterar.cdr',1),(5,'c4ca4238a0b923820dcc509a6f75849b/backup_de_incluir.cdr',1);
/*!40000 ALTER TABLE `arquivoaula` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ae_usuario_aplicacao_cache`
--

DROP TABLE IF EXISTS `ae_usuario_aplicacao_cache`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ae_usuario_aplicacao_cache` (
  `id_usuario_aplicacao` int(10) unsigned NOT NULL,
  `conteudo` blob,
  PRIMARY KEY (`id_usuario_aplicacao`),
  KEY `ae_usuario_aplicacao_cache_usuario_aplicacao_fk` (`id_usuario_aplicacao`),
  CONSTRAINT `ae_usuario_aplicacao_cache_usuario_aplicacao_fk` FOREIGN KEY (`id_usuario_aplicacao`) REFERENCES `ae_usuario_aplicacao` (`id_usuario_aplicacao`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ae_usuario_aplicacao_cache`
--

LOCK TABLES `ae_usuario_aplicacao_cache` WRITE;
/*!40000 ALTER TABLE `ae_usuario_aplicacao_cache` DISABLE KEYS */;
INSERT INTO `ae_usuario_aplicacao_cache` VALUES (7,'Aplica&ccedil;&atilde;o [Twitter - Lista de twitts] retornou novos dados:<br/><br/><html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><title>tree.xslt output</title><link type=\"text/css\" rel=\"stylesheet\" href=\"tree-view.css\"></head><body><br><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">resultado</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">since_id</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">0</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">max_id</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">49188619860185088</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">results</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">text</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">E&nbsp;meu&nbsp;pai&nbsp;ainda&nbsp;falou&nbsp;q&nbsp;o&nbsp;Juninho&nbsp;Pernambucano&nbsp;ta&nbsp;mt&nbsp;velho&nbsp;pra&nbsp;jogar&nbsp;no&nbsp;#Vasco,&nbsp;isso&nbsp;q&nbsp;&eacute;&nbsp;um&nbsp;vascaino&nbsp;pessimista&nbsp;kkkk</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">from_user_id</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">213723866</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">geo</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">null</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">iso_language_code</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">pt</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">to_user_id</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">null</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">id</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">49188619860185088</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">to_user_id_str</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">null</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">source</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">&amp;lt;a&nbsp;href=&amp;quot;http://twitter.com/&amp;quot;&amp;gt;web&amp;lt;/a&amp;gt;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">from_user_id_str</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">213723866</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">from_user</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">MaatheeusSoouza</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">created_at</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">Sat,&nbsp;19&nbsp;Mar&nbsp;2011&nbsp;19:20:55&nbsp;+0000</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">id_str</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">49188619860185088</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">profile_image_url</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">http://a2.twimg.com/profile_images/1268275738/007_normal.jpg</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">metadata</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">result_type</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">recent</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">page</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">1</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">query</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">vasco</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">refresh_url</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">?since_id=49188619860185088&amp;q=vasco</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">max_id_str</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">49188619860185088</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">results_per_page</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">1</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">next_page</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">?page=2&amp;max_id=49188619860185088&amp;rpp=1&amp;q=vasco</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">since_id_str</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span>&nbsp;<span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">0</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"name\">completed_in</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><br><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\">&nbsp;&nbsp;</span><span class=\"connector\"></span>&nbsp;<span class=\"value\">0.225151</span><br></body></html>');
/*!40000 ALTER TABLE `ae_usuario_aplicacao_cache` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `alunoaula`
--

DROP TABLE IF EXISTS `alunoaula`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `alunoaula` (
  `fk_aluno` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fk_aula` int(10) unsigned NOT NULL,
  PRIMARY KEY (`fk_aluno`,`fk_aula`),
  KEY `FK_AlunoAula_2` (`fk_aula`),
  CONSTRAINT `FK_AlunoAula_1` FOREIGN KEY (`fk_aluno`) REFERENCES `membro` (`pk_membro`),
  CONSTRAINT `FK_AlunoAula_2` FOREIGN KEY (`fk_aula`) REFERENCES `aula` (`pk_aula`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alunoaula`
--

LOCK TABLES `alunoaula` WRITE;
/*!40000 ALTER TABLE `alunoaula` DISABLE KEYS */;
/*!40000 ALTER TABLE `alunoaula` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `avaliacaoservico`
--

DROP TABLE IF EXISTS `avaliacaoservico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `avaliacaoservico` (
  `fk_membro` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fk_servico` int(10) unsigned NOT NULL,
  `nu_avaliacao` int(10) unsigned NOT NULL,
  PRIMARY KEY (`fk_membro`,`fk_servico`),
  KEY `FK_avaliacaoServico_2` (`fk_servico`),
  CONSTRAINT `FK_avaliacaoServico_1` FOREIGN KEY (`fk_membro`) REFERENCES `membro` (`pk_membro`),
  CONSTRAINT `FK_avaliacaoServico_2` FOREIGN KEY (`fk_servico`) REFERENCES `servico` (`pk_servico`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `avaliacaoservico`
--

LOCK TABLES `avaliacaoservico` WRITE;
/*!40000 ALTER TABLE `avaliacaoservico` DISABLE KEYS */;
/*!40000 ALTER TABLE `avaliacaoservico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `aluno`
--

DROP TABLE IF EXISTS `aluno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aluno` (
  `pk_aluno` int(10) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`pk_aluno`),
  CONSTRAINT `FK_aluno_1` FOREIGN KEY (`pk_aluno`) REFERENCES `membro` (`pk_membro`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aluno`
--

LOCK TABLES `aluno` WRITE;
/*!40000 ALTER TABLE `aluno` DISABLE KEYS */;
/*!40000 ALTER TABLE `aluno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `membroambiente`
--

DROP TABLE IF EXISTS `membroambiente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `membroambiente` (
  `fk_membro` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fk_ambiente` int(10) unsigned NOT NULL,
  PRIMARY KEY (`fk_membro`,`fk_ambiente`),
  KEY `FK_membroAmbiente_2` (`fk_ambiente`),
  CONSTRAINT `FK_membroAmbiente_1` FOREIGN KEY (`fk_membro`) REFERENCES `membro` (`pk_membro`),
  CONSTRAINT `FK_membroAmbiente_2` FOREIGN KEY (`fk_ambiente`) REFERENCES `ambiente` (`pk_ambiente`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `membroambiente`
--

LOCK TABLES `membroambiente` WRITE;
/*!40000 ALTER TABLE `membroambiente` DISABLE KEYS */;
INSERT INTO `membroambiente` VALUES (2,1),(5,1),(2,2),(3,2),(5,2),(6,2),(2,3),(5,3),(2,14),(7,14);
/*!40000 ALTER TABLE `membroambiente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `areainteresse`
--

DROP TABLE IF EXISTS `areainteresse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `areainteresse` (
  `pk_areaInteresse` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `no_areaInteresse` varchar(60) NOT NULL,
  `ds_areaInteresse` text NOT NULL,
  PRIMARY KEY (`pk_areaInteresse`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `areainteresse`
--

LOCK TABLES `areainteresse` WRITE;
/*!40000 ALTER TABLE `areainteresse` DISABLE KEYS */;
INSERT INTO `areainteresse` VALUES (1,'Análise de Sistemas','Desenvolvimento de sistemas'),(2,'Redes de Computadores','Infra-estrutura'),(3,'Banco de Dados','Suporte de Banco de Dados'),(4,'Interface Homem-Máquina','Interface com o usuário');
/*!40000 ALTER TABLE `areainteresse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `membroareainteresse`
--

DROP TABLE IF EXISTS `membroareainteresse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `membroareainteresse` (
  `fk_membro` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fk_areaInteresse` int(10) unsigned NOT NULL,
  PRIMARY KEY (`fk_membro`,`fk_areaInteresse`),
  KEY `FK_membroAreaInteresse_2` (`fk_areaInteresse`),
  CONSTRAINT `FK_membroAreaInteresse_1` FOREIGN KEY (`fk_membro`) REFERENCES `membro` (`pk_membro`),
  CONSTRAINT `FK_membroAreaInteresse_2` FOREIGN KEY (`fk_areaInteresse`) REFERENCES `areainteresse` (`pk_areaInteresse`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `membroareainteresse`
--

LOCK TABLES `membroareainteresse` WRITE;
/*!40000 ALTER TABLE `membroareainteresse` DISABLE KEYS */;
INSERT INTO `membroareainteresse` VALUES (2,1),(2,3);
/*!40000 ALTER TABLE `membroareainteresse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pastasfavorito`
--

DROP TABLE IF EXISTS `pastasfavorito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pastasfavorito` (
  `pk_pasta` int(11) NOT NULL,
  `no_pasta` varchar(100) NOT NULL,
  `fk_pasta` int(11) DEFAULT NULL,
  `fk_membro` int(11) NOT NULL,
  PRIMARY KEY (`pk_pasta`),
  KEY `fk_pasta` (`fk_pasta`),
  KEY `fk_membro` (`fk_membro`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pastasfavorito`
--

LOCK TABLES `pastasfavorito` WRITE;
/*!40000 ALTER TABLE `pastasfavorito` DISABLE KEYS */;
INSERT INTO `pastasfavorito` VALUES (1,'Google',NULL,2),(2,'Serviços',NULL,2),(4,'Nova Pasta',3,2),(5,'Nova Pasta',4,2);
/*!40000 ALTER TABLE `pastasfavorito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curso`
--

DROP TABLE IF EXISTS `curso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curso` (
  `pk_curso` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `dt_liberacao` datetime NOT NULL,
  PRIMARY KEY (`pk_curso`),
  CONSTRAINT `FK_curso_1` FOREIGN KEY (`pk_curso`) REFERENCES `servico` (`pk_servico`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curso`
--

LOCK TABLES `curso` WRITE;
/*!40000 ALTER TABLE `curso` DISABLE KEYS */;
INSERT INTO `curso` VALUES (9,'2010-01-19 13:57:26'),(10,'2010-01-21 11:11:22'),(37,'2010-02-01 15:35:24');
/*!40000 ALTER TABLE `curso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ambientetag`
--

DROP TABLE IF EXISTS `ambientetag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ambientetag` (
  `fk_ambiente` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fk_tag` int(10) unsigned NOT NULL,
  PRIMARY KEY (`fk_ambiente`,`fk_tag`),
  KEY `FK_AmbienteTag_2` (`fk_tag`),
  CONSTRAINT `FK_AmbienteTag_1` FOREIGN KEY (`fk_ambiente`) REFERENCES `ambiente` (`pk_ambiente`),
  CONSTRAINT `FK_AmbienteTag_2` FOREIGN KEY (`fk_tag`) REFERENCES `tag` (`pk_tag`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ambientetag`
--

LOCK TABLES `ambientetag` WRITE;
/*!40000 ALTER TABLE `ambientetag` DISABLE KEYS */;
INSERT INTO `ambientetag` VALUES (2,2),(3,3),(4,4),(14,5);
/*!40000 ALTER TABLE `ambientetag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `membrotag`
--

DROP TABLE IF EXISTS `membrotag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `membrotag` (
  `fk_membro` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fk_tag` int(10) unsigned NOT NULL,
  PRIMARY KEY (`fk_membro`,`fk_tag`),
  KEY `FK_membroTag_2` (`fk_tag`),
  CONSTRAINT `FK_membroTag_1` FOREIGN KEY (`fk_membro`) REFERENCES `membro` (`pk_membro`),
  CONSTRAINT `FK_membroTag_2` FOREIGN KEY (`fk_tag`) REFERENCES `tag` (`pk_tag`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `membrotag`
--

LOCK TABLES `membrotag` WRITE;
/*!40000 ALTER TABLE `membrotag` DISABLE KEYS */;
/*!40000 ALTER TABLE `membrotag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comunidadetag`
--

DROP TABLE IF EXISTS `comunidadetag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comunidadetag` (
  `fk_comunidade` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fk_tag` int(10) unsigned NOT NULL,
  PRIMARY KEY (`fk_comunidade`,`fk_tag`),
  KEY `FK_ComunidadeTag_2` (`fk_tag`),
  CONSTRAINT `FK_ComunidadeTag_1` FOREIGN KEY (`fk_comunidade`) REFERENCES `comunidade` (`pk_comunidade`),
  CONSTRAINT `FK_ComunidadeTag_2` FOREIGN KEY (`fk_tag`) REFERENCES `tag` (`pk_tag`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comunidadetag`
--

LOCK TABLES `comunidadetag` WRITE;
/*!40000 ALTER TABLE `comunidadetag` DISABLE KEYS */;
/*!40000 ALTER TABLE `comunidadetag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `metatag`
--

DROP TABLE IF EXISTS `metatag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `metatag` (
  `fk_meta` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fk_tag` int(10) unsigned NOT NULL,
  PRIMARY KEY (`fk_meta`,`fk_tag`),
  KEY `FK_MetaTag_2` (`fk_tag`),
  CONSTRAINT `FK_MetaTag_1` FOREIGN KEY (`fk_meta`) REFERENCES `meta` (`pk_meta`),
  CONSTRAINT `FK_MetaTag_2` FOREIGN KEY (`fk_tag`) REFERENCES `tag` (`pk_tag`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `metatag`
--

LOCK TABLES `metatag` WRITE;
/*!40000 ALTER TABLE `metatag` DISABLE KEYS */;
/*!40000 ALTER TABLE `metatag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `avaliacaomembro`
--

DROP TABLE IF EXISTS `avaliacaomembro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `avaliacaomembro` (
  `fk_membroAvaliador` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fk_membro` int(10) unsigned NOT NULL,
  `nu_avaliacao` int(10) unsigned NOT NULL,
  PRIMARY KEY (`fk_membroAvaliador`,`fk_membro`),
  KEY `FK_AvaliacaoMembro_2` (`fk_membro`),
  CONSTRAINT `FK_AvaliacaoMembro_1` FOREIGN KEY (`fk_membroAvaliador`) REFERENCES `membro` (`pk_membro`),
  CONSTRAINT `FK_AvaliacaoMembro_2` FOREIGN KEY (`fk_membro`) REFERENCES `membro` (`pk_membro`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `avaliacaomembro`
--

LOCK TABLES `avaliacaomembro` WRITE;
/*!40000 ALTER TABLE `avaliacaomembro` DISABLE KEYS */;
/*!40000 ALTER TABLE `avaliacaomembro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recomendacaoservico`
--

DROP TABLE IF EXISTS `recomendacaoservico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recomendacaoservico` (
  `fk_membroRecomendador` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fk_membroReceptor` int(10) unsigned NOT NULL,
  `fk_servico` int(10) unsigned NOT NULL,
  PRIMARY KEY (`fk_membroRecomendador`,`fk_membroReceptor`,`fk_servico`),
  KEY `FK_RecomendacaoServico_2` (`fk_membroReceptor`),
  KEY `FK_RecomendacaoServico_3` (`fk_servico`),
  CONSTRAINT `FK_RecomendacaoServico_1` FOREIGN KEY (`fk_membroRecomendador`) REFERENCES `membro` (`pk_membro`),
  CONSTRAINT `FK_RecomendacaoServico_2` FOREIGN KEY (`fk_membroReceptor`) REFERENCES `membro` (`pk_membro`),
  CONSTRAINT `FK_RecomendacaoServico_3` FOREIGN KEY (`fk_servico`) REFERENCES `servico` (`pk_servico`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recomendacaoservico`
--

LOCK TABLES `recomendacaoservico` WRITE;
/*!40000 ALTER TABLE `recomendacaoservico` DISABLE KEYS */;
/*!40000 ALTER TABLE `recomendacaoservico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contatousuario`
--

DROP TABLE IF EXISTS `contatousuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contatousuario` (
  `pk_contatoUsuario` int(10) unsigned NOT NULL,
  `fk_tipoContato` int(10) unsigned NOT NULL,
  `fk_usuario` int(10) unsigned NOT NULL,
  `ds_contato` text NOT NULL,
  PRIMARY KEY (`pk_contatoUsuario`),
  KEY `FK_contatoUsuario_1` (`fk_tipoContato`),
  KEY `FK_contatoUsuario_2` (`fk_usuario`),
  CONSTRAINT `FK_contatoUsuario_1` FOREIGN KEY (`fk_tipoContato`) REFERENCES `tipocontato` (`pk_tipoContato`),
  CONSTRAINT `FK_contatoUsuario_2` FOREIGN KEY (`fk_usuario`) REFERENCES `usuario` (`pk_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contatousuario`
--

LOCK TABLES `contatousuario` WRITE;
/*!40000 ALTER TABLE `contatousuario` DISABLE KEYS */;
INSERT INTO `contatousuario` VALUES (1,1,2,'rodrigo@gmail.com'),(2,5,2,'(21)8888-4444');
/*!40000 ALTER TABLE `contatousuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bairro`
--

DROP TABLE IF EXISTS `bairro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bairro` (
  `pk_bairro` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `no_bairro` varchar(60) NOT NULL,
  `fk_municipio` int(10) unsigned NOT NULL,
  PRIMARY KEY (`pk_bairro`),
  KEY `FK_bairro_1` (`fk_municipio`),
  CONSTRAINT `FK_bairro_1` FOREIGN KEY (`fk_municipio`) REFERENCES `municipio` (`pk_municipio`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bairro`
--

LOCK TABLES `bairro` WRITE;
/*!40000 ALTER TABLE `bairro` DISABLE KEYS */;
INSERT INTO `bairro` VALUES (1,'Centro',1),(2,'Del Castilho',1),(3,'Penha',1),(4,'Irajá',1),(5,'Abolição',1),(6,'Olaria',1);
/*!40000 ALTER TABLE `bairro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ae_aplicacao`
--

DROP TABLE IF EXISTS `ae_aplicacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ae_aplicacao` (
  `id_aplicacao` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `url` varchar(255) DEFAULT NULL,
  `id_processamento` int(10) unsigned DEFAULT NULL,
  `id_autenticacao` int(10) unsigned DEFAULT NULL COMMENT 'EXCLUIR',
  `data_cadastro` datetime DEFAULT NULL,
  `auth_basica` smallint(5) unsigned NOT NULL DEFAULT '0',
  `auth_openid` smallint(5) unsigned NOT NULL DEFAULT '0',
  `auth_gauth` smallint(5) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_aplicacao`),
  KEY `FK_ae_aplicacao_1` (`id_processamento`),
  KEY `FK_ae_aplicacao_2` (`id_autenticacao`),
  CONSTRAINT `FK_ae_aplicacao_1` FOREIGN KEY (`id_processamento`) REFERENCES `ae_processamento` (`id_processamento`),
  CONSTRAINT `FK_ae_aplicacao_2` FOREIGN KEY (`id_autenticacao`) REFERENCES `ae_autenticacao` (`id_autenticacao`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ae_aplicacao`
--

LOCK TABLES `ae_aplicacao` WRITE;
/*!40000 ALTER TABLE `ae_aplicacao` DISABLE KEYS */;
INSERT INTO `ae_aplicacao` VALUES (1,'Delicious','http://www.delicious.com',1,1,'2010-02-10 00:00:00',1,0,0),(33,'Twitter','http://localhost:8080/acctiva-integra/wadl/twitter.wadl',1,NULL,'2011-03-19 00:00:00',0,0,0);
/*!40000 ALTER TABLE `ae_aplicacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ae_usuario_aplicacao`
--

DROP TABLE IF EXISTS `ae_usuario_aplicacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ae_usuario_aplicacao` (
  `id_usuario_aplicacao` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `pk_usuario` int(10) unsigned NOT NULL,
  `id_aplicacao` int(10) unsigned NOT NULL,
  `id_recurso` int(10) unsigned NOT NULL,
  `permissao` int(10) unsigned NOT NULL,
  `mostrar_janela` varchar(45) DEFAULT NULL,
  `atualizacao_automatica` int(10) unsigned DEFAULT NULL,
  `tempo_valor` int(10) unsigned DEFAULT NULL,
  `usuario` varchar(45) DEFAULT NULL,
  `senha` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_usuario_aplicacao`),
  KEY `ae_aplicacao_fk` (`id_aplicacao`),
  KEY `ae_recurso_fk` (`id_recurso`),
  KEY `usuario_fk` (`pk_usuario`),
  CONSTRAINT `ae_aplicacao_fk` FOREIGN KEY (`id_aplicacao`) REFERENCES `ae_aplicacao` (`id_aplicacao`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ae_recurso_fk` FOREIGN KEY (`id_recurso`) REFERENCES `ae_recurso` (`id_recurso`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `usuario_fk` FOREIGN KEY (`pk_usuario`) REFERENCES `usuario` (`pk_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ae_usuario_aplicacao`
--

LOCK TABLES `ae_usuario_aplicacao` WRITE;
/*!40000 ALTER TABLE `ae_usuario_aplicacao` DISABLE KEYS */;
INSERT INTO `ae_usuario_aplicacao` VALUES (7,7,33,72,1,'',2,NULL,'','');
/*!40000 ALTER TABLE `ae_usuario_aplicacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `artefatotag`
--

DROP TABLE IF EXISTS `artefatotag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `artefatotag` (
  `fk_artefato` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fk_tag` int(10) unsigned NOT NULL,
  PRIMARY KEY (`fk_artefato`,`fk_tag`),
  KEY `FK_artefatoTag_2` (`fk_tag`),
  CONSTRAINT `FK_artefatoTag_1` FOREIGN KEY (`fk_artefato`) REFERENCES `artefato` (`pk_artefato`),
  CONSTRAINT `FK_artefatoTag_2` FOREIGN KEY (`fk_tag`) REFERENCES `tag` (`pk_tag`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artefatotag`
--

LOCK TABLES `artefatotag` WRITE;
/*!40000 ALTER TABLE `artefatotag` DISABLE KEYS */;
/*!40000 ALTER TABLE `artefatotag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ae_usuario_aplicacao_log_parametro`
--

DROP TABLE IF EXISTS `ae_usuario_aplicacao_log_parametro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ae_usuario_aplicacao_log_parametro` (
  `id_usuario_aplicacao_log` int(10) NOT NULL,
  `id_parametro` int(10) unsigned NOT NULL,
  `valor` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_usuario_aplicacao_log`,`id_parametro`),
  KEY `ae_usuario_aplicacao_log_fk` (`id_usuario_aplicacao_log`),
  KEY `ae_usuario_aplicacao_log_parametro_fk` (`id_parametro`),
  CONSTRAINT `ae_usuario_aplicacao_log_fk` FOREIGN KEY (`id_usuario_aplicacao_log`) REFERENCES `ae_usuario_aplicacao_log` (`id_usuario_aplicacao_log`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ae_usuario_aplicacao_log_parametro_fk` FOREIGN KEY (`id_parametro`) REFERENCES `ae_parametro` (`id_parametro`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ae_usuario_aplicacao_log_parametro`
--

LOCK TABLES `ae_usuario_aplicacao_log_parametro` WRITE;
/*!40000 ALTER TABLE `ae_usuario_aplicacao_log_parametro` DISABLE KEYS */;
/*!40000 ALTER TABLE `ae_usuario_aplicacao_log_parametro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `membro`
--

DROP TABLE IF EXISTS `membro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `membro` (
  `pk_membro` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fk_formacaoAcademica` int(10) unsigned DEFAULT NULL,
  `fl_permissao` varchar(1) NOT NULL,
  PRIMARY KEY (`pk_membro`),
  KEY `FK_New Table_2` (`fk_formacaoAcademica`),
  CONSTRAINT `FK_New Table_1` FOREIGN KEY (`pk_membro`) REFERENCES `usuario` (`pk_usuario`),
  CONSTRAINT `FK_New Table_2` FOREIGN KEY (`fk_formacaoAcademica`) REFERENCES `formacaoacademica` (`pk_formacaoAcademica`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `membro`
--

LOCK TABLES `membro` WRITE;
/*!40000 ALTER TABLE `membro` DISABLE KEYS */;
INSERT INTO `membro` VALUES (2,2,'P'),(3,1,'P'),(4,1,'P'),(5,1,'A'),(6,1,'A'),(7,1,'A');
/*!40000 ALTER TABLE `membro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `alunocurso`
--

DROP TABLE IF EXISTS `alunocurso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `alunocurso` (
  `fk_aluno` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fk_curso` int(10) unsigned NOT NULL,
  PRIMARY KEY (`fk_aluno`,`fk_curso`),
  KEY `FK_AlunoCurso_2` (`fk_curso`),
  CONSTRAINT `FK_AlunoCurso_1` FOREIGN KEY (`fk_aluno`) REFERENCES `membro` (`pk_membro`),
  CONSTRAINT `FK_AlunoCurso_2` FOREIGN KEY (`fk_curso`) REFERENCES `curso` (`pk_curso`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alunocurso`
--

LOCK TABLES `alunocurso` WRITE;
/*!40000 ALTER TABLE `alunocurso` DISABLE KEYS */;
/*!40000 ALTER TABLE `alunocurso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `postcomunidade`
--

DROP TABLE IF EXISTS `postcomunidade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `postcomunidade` (
  `pk_postComunidade` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ds_post` text NOT NULL,
  `dt_post` datetime NOT NULL,
  `fk_comunidade` int(10) unsigned NOT NULL,
  `fk_membro` int(10) unsigned NOT NULL,
  PRIMARY KEY (`pk_postComunidade`),
  KEY `FK_postComunidade_1` (`fk_comunidade`),
  KEY `FK_postComunidade_2` (`fk_membro`),
  CONSTRAINT `FK_postComunidade_1` FOREIGN KEY (`fk_comunidade`) REFERENCES `comunidade` (`pk_comunidade`),
  CONSTRAINT `FK_postComunidade_2` FOREIGN KEY (`fk_membro`) REFERENCES `membro` (`pk_membro`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `postcomunidade`
--

LOCK TABLES `postcomunidade` WRITE;
/*!40000 ALTER TABLE `postcomunidade` DISABLE KEYS */;
/*!40000 ALTER TABLE `postcomunidade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `idioma`
--

DROP TABLE IF EXISTS `idioma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `idioma` (
  `pk_idioma` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `no_idioma` varchar(60) NOT NULL,
  PRIMARY KEY (`pk_idioma`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `idioma`
--

LOCK TABLES `idioma` WRITE;
/*!40000 ALTER TABLE `idioma` DISABLE KEYS */;
INSERT INTO `idioma` VALUES (1,'Inglês'),(2,'Espanhol'),(3,'Francês'),(4,'Alemão'),(5,'Italiano'),(6,'Russo');
/*!40000 ALTER TABLE `idioma` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `areainteressetag`
--

DROP TABLE IF EXISTS `areainteressetag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `areainteressetag` (
  `fk_areaInteresse` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fk_tag` int(10) unsigned NOT NULL,
  PRIMARY KEY (`fk_areaInteresse`,`fk_tag`),
  KEY `FK_areaInteresseTag_2` (`fk_tag`),
  CONSTRAINT `FK_areaInteresseTag_1` FOREIGN KEY (`fk_areaInteresse`) REFERENCES `areainteresse` (`pk_areaInteresse`),
  CONSTRAINT `FK_areaInteresseTag_2` FOREIGN KEY (`fk_tag`) REFERENCES `tag` (`pk_tag`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `areainteressetag`
--

LOCK TABLES `areainteressetag` WRITE;
/*!40000 ALTER TABLE `areainteressetag` DISABLE KEYS */;
/*!40000 ALTER TABLE `areainteressetag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favorito`
--

DROP TABLE IF EXISTS `favorito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `favorito` (
  `pk_favorito` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `no_link` varchar(100) NOT NULL,
  `ds_link` text NOT NULL,
  `no_url` varchar(200) NOT NULL,
  `fk_membro` int(10) unsigned NOT NULL,
  `fk_pasta` int(11) DEFAULT NULL,
  PRIMARY KEY (`pk_favorito`),
  KEY `FK_favorito_1` (`fk_membro`),
  KEY `fk_pasta` (`fk_pasta`),
  CONSTRAINT `FK_favorito_1` FOREIGN KEY (`fk_membro`) REFERENCES `membro` (`pk_membro`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favorito`
--

LOCK TABLES `favorito` WRITE;
/*!40000 ALTER TABLE `favorito` DISABLE KEYS */;
INSERT INTO `favorito` VALUES (1,'youtube','','http://www.youtube.com',2,1),(2,'Globo','','http://www.globo.com',2,2),(3,'Novo Favorito asdasd asd asd','','http://',2,5),(4,'Novo Favorito','','http://',2,5),(5,'Novo Favorito','','http://',2,5),(6,'Novo Favorito','','http://',2,3),(10,'Gmail','','http://www.gmail.com',2,1);
/*!40000 ALTER TABLE `favorito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `pk_usuario` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nu_cpf` bigint(32) unsigned NOT NULL,
  `no_usuario` varchar(150) NOT NULL,
  `no_apelido` varchar(60) DEFAULT NULL,
  `dt_nasc` date DEFAULT NULL,
  `no_email` varchar(100) NOT NULL,
  `pw_senha` varchar(32) NOT NULL,
  `ds_perguntaChave` varchar(60) DEFAULT NULL,
  `ds_respostaChave` varchar(60) DEFAULT NULL,
  `tp_logradouro` varchar(5) DEFAULT NULL,
  `no_logradouro` varchar(100) DEFAULT NULL,
  `nu_logradouro` int(10) unsigned DEFAULT NULL,
  `ds_complemento` varchar(100) DEFAULT NULL,
  `nu_cep` varchar(9) DEFAULT NULL,
  `st_usuario` varchar(1) NOT NULL,
  `img_usuario` varchar(100) DEFAULT NULL,
  `fk_bairro` int(10) unsigned DEFAULT NULL,
  `fk_instituicao` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`pk_usuario`),
  UNIQUE KEY `nu_cpf` (`nu_cpf`),
  UNIQUE KEY `no_email` (`no_email`),
  KEY `FK_usuario_1` (`fk_bairro`),
  KEY `FK_usuario_2` (`fk_instituicao`),
  CONSTRAINT `FK_usuario_1` FOREIGN KEY (`fk_bairro`) REFERENCES `bairro` (`pk_bairro`),
  CONSTRAINT `FK_usuario_2` FOREIGN KEY (`fk_instituicao`) REFERENCES `instituicao` (`pk_instituicao`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,11111111111,'Administrador',NULL,NULL,'adm@gmail.com','123456',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'A',NULL,NULL,NULL),(2,12212212212,'Baliu','baliu','1991-10-10','raxbaliu@gmail.com','123456','Qual é o meu nome?','Raimundo','Av','Vicente de Carvalho',140,'apartamento 501','22222-222','A','6b611dc5967f37e7d696b31b541afa1a.jpg',4,1),(3,13131313131,'Adriano',NULL,NULL,'adriano@estacio.br','123456',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'A',NULL,NULL,NULL),(4,14141414141,'Valéria',NULL,NULL,'valeria@estacio.br','123456',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'A',NULL,NULL,NULL),(5,15151515151,'Priscila',NULL,NULL,'priscila.tobias@gmail.com','123456',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'A',NULL,NULL,NULL),(6,12321312312,'Teste',NULL,NULL,'teste@teste.com','123456',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'A',NULL,NULL,NULL),(7,0,'Aluno','Aluno','2011-02-10','aluno@gmail.com','123456','','','','',0,'','','A','/',1,1);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `professor`
--

DROP TABLE IF EXISTS `professor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `professor` (
  `pk_professor` int(10) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`pk_professor`),
  CONSTRAINT `FK_professor_1` FOREIGN KEY (`pk_professor`) REFERENCES `membro` (`pk_membro`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `professor`
--

LOCK TABLES `professor` WRITE;
/*!40000 ALTER TABLE `professor` DISABLE KEYS */;
/*!40000 ALTER TABLE `professor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contatoinstituicao`
--

DROP TABLE IF EXISTS `contatoinstituicao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contatoinstituicao` (
  `pk_contatoInstituicao` int(10) unsigned NOT NULL,
  `fk_tipoContato` int(10) unsigned NOT NULL,
  `fk_instituicao` int(10) unsigned NOT NULL,
  `ds_contato` varchar(60) NOT NULL,
  PRIMARY KEY (`pk_contatoInstituicao`),
  KEY `FK_contatoInstituicao_1` (`fk_tipoContato`),
  KEY `FK_contatoInstituicao_2` (`fk_instituicao`),
  CONSTRAINT `FK_contatoInstituicao_1` FOREIGN KEY (`fk_tipoContato`) REFERENCES `tipocontato` (`pk_tipoContato`),
  CONSTRAINT `FK_contatoInstituicao_2` FOREIGN KEY (`fk_instituicao`) REFERENCES `instituicao` (`pk_instituicao`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contatoinstituicao`
--

LOCK TABLES `contatoinstituicao` WRITE;
/*!40000 ALTER TABLE `contatoinstituicao` DISABLE KEYS */;
INSERT INTO `contatoinstituicao` VALUES (1,4,1,'www.estacio.br'),(2,3,1,'(21)2222-3333');
/*!40000 ALTER TABLE `contatoinstituicao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `instituicao`
--

DROP TABLE IF EXISTS `instituicao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `instituicao` (
  `pk_instituicao` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `no_instituicao` varchar(100) NOT NULL,
  `tp_logradouroInst` varchar(5) DEFAULT NULL,
  `no_logradouroInst` varchar(100) DEFAULT NULL,
  `nu_logradouroInst` int(10) unsigned DEFAULT NULL,
  `ds_complementoInst` varchar(100) DEFAULT NULL,
  `nu_cepInst` varchar(9) DEFAULT NULL,
  `fk_bairroInst` int(10) unsigned NOT NULL,
  PRIMARY KEY (`pk_instituicao`),
  KEY `FK_instituicao_1` (`fk_bairroInst`),
  CONSTRAINT `FK_instituicao_1` FOREIGN KEY (`fk_bairroInst`) REFERENCES `bairro` (`pk_bairro`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `instituicao`
--

LOCK TABLES `instituicao` WRITE;
/*!40000 ALTER TABLE `instituicao` DISABLE KEYS */;
INSERT INTO `instituicao` VALUES (1,'Universidade Estácio de Sá','Av','Presidente Lutter King',1111,'','21070-789',2);
/*!40000 ALTER TABLE `instituicao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ae_recurso`
--

DROP TABLE IF EXISTS `ae_recurso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ae_recurso` (
  `id_recurso` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_aplicacao` int(10) unsigned NOT NULL,
  `nome` varchar(200) DEFAULT NULL,
  `base` varchar(200) NOT NULL,
  `path` varchar(200) NOT NULL,
  `metodo` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id_recurso`),
  KEY `FK_ae_recurso_1` (`id_aplicacao`),
  CONSTRAINT `FK_ae_recurso_1` FOREIGN KEY (`id_aplicacao`) REFERENCES `ae_aplicacao` (`id_aplicacao`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ae_recurso`
--

LOCK TABLES `ae_recurso` WRITE;
/*!40000 ALTER TABLE `ae_recurso` DISABLE KEYS */;
INSERT INTO `ae_recurso` VALUES (1,1,'Receber Tags','http://www.delicious.com','v1/tags/get/getTags',NULL),(2,1,'Enviar Post','http://www.delicious.com','v1/tags/get/addPost',NULL),(64,1,'Listar Posts','https://api.del.icio.us/','v1/posts/all','GET'),(72,33,'Lista de twitts','http://search.twitter.com/','search.json','GET');
/*!40000 ALTER TABLE `ae_recurso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `membroareatrabalho`
--

DROP TABLE IF EXISTS `membroareatrabalho`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `membroareatrabalho` (
  `fk_membro` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fk_areaTrabalho` int(10) unsigned NOT NULL,
  PRIMARY KEY (`fk_membro`,`fk_areaTrabalho`),
  KEY `FK_membroAreaTrabalho_2` (`fk_areaTrabalho`),
  CONSTRAINT `FK_membroAreaTrabalho_1` FOREIGN KEY (`fk_membro`) REFERENCES `membro` (`pk_membro`),
  CONSTRAINT `FK_membroAreaTrabalho_2` FOREIGN KEY (`fk_areaTrabalho`) REFERENCES `areatrabalho` (`pk_areaTrabalho`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `membroareatrabalho`
--

LOCK TABLES `membroareatrabalho` WRITE;
/*!40000 ALTER TABLE `membroareatrabalho` DISABLE KEYS */;
/*!40000 ALTER TABLE `membroareatrabalho` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2011-03-19 16:21:26
