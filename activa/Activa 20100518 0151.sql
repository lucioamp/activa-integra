-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.32-community


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema baliu02
--

CREATE DATABASE IF NOT EXISTS baliu02;
USE baliu02;

--
-- Definition of table `administrador`
--

DROP TABLE IF EXISTS `administrador`;
CREATE TABLE `administrador` (
  `pk_administrador` int(10) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`pk_administrador`),
  CONSTRAINT `FK_administrador_1` FOREIGN KEY (`pk_administrador`) REFERENCES `usuario` (`pk_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `administrador`
--

/*!40000 ALTER TABLE `administrador` DISABLE KEYS */;
INSERT INTO `administrador` (`pk_administrador`) VALUES 
 (1);
/*!40000 ALTER TABLE `administrador` ENABLE KEYS */;


--
-- Definition of table `ae_aplicacao`
--

DROP TABLE IF EXISTS `ae_aplicacao`;
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
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ae_aplicacao`
--

/*!40000 ALTER TABLE `ae_aplicacao` DISABLE KEYS */;
INSERT INTO `ae_aplicacao` (`id_aplicacao`,`nome`,`url`,`id_processamento`,`id_autenticacao`,`data_cadastro`,`auth_basica`,`auth_openid`,`auth_gauth`) VALUES 
 (1,'Delicious','http://www.delicious.com',1,1,'2010-02-10 00:00:00',0,0,0),
 (2,'Twitter','http://www.twitter.com',1,1,'2010-02-10 00:00:00',0,0,0),
 (22,'Teste','http://localhost:8080/activa/wadl/delicious.wadl',1,NULL,'2010-05-18 00:00:00',0,0,1),
 (23,'Teste','http://localhost:8080/activa/wadl/delicious.wadl',1,NULL,'2010-05-18 00:00:00',0,0,1),
 (24,'Teste','http://localhost:8080/activa/wadl/delicious.wadl',1,NULL,'2010-05-18 00:00:00',1,0,0),
 (25,'Teste','http://localhost:8080/activa/wadl/delicious.wadl',1,NULL,'2010-05-18 00:00:00',1,0,0);
/*!40000 ALTER TABLE `ae_aplicacao` ENABLE KEYS */;


--
-- Definition of table `ae_autenticacao`
--

DROP TABLE IF EXISTS `ae_autenticacao`;
CREATE TABLE `ae_autenticacao` (
  `id_autenticacao` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `descricao` varchar(200) NOT NULL,
  PRIMARY KEY (`id_autenticacao`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ae_autenticacao`
--

/*!40000 ALTER TABLE `ae_autenticacao` DISABLE KEYS */;
INSERT INTO `ae_autenticacao` (`id_autenticacao`,`descricao`) VALUES 
 (1,'Básica'),
 (2,'OpenID'),
 (3,'GAuth (Google)');
/*!40000 ALTER TABLE `ae_autenticacao` ENABLE KEYS */;


--
-- Definition of table `ae_parametro`
--

DROP TABLE IF EXISTS `ae_parametro`;
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
  CONSTRAINT `FK_ae_parametro_2` FOREIGN KEY (`id_aplicacao`) REFERENCES `ae_aplicacao` (`id_aplicacao`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ae_parametro`
--

/*!40000 ALTER TABLE `ae_parametro` DISABLE KEYS */;
INSERT INTO `ae_parametro` (`id_parametro`,`id_recurso`,`id_aplicacao`,`nome`,`titulo`,`obrigatorio`) VALUES 
 (15,48,NULL,'old','Tag to rename.',1),
 (16,48,NULL,'new','New name.',1),
 (17,49,NULL,'tag','Filter by this tag.',0),
 (18,49,NULL,'dt','Filter by this date (CCYY-MM-DDThh:mm:ssZ).',0),
 (19,49,NULL,'url','Filter by this URL.',0),
 (20,50,NULL,'tag','Filter by this tag.',0),
 (21,50,NULL,'count','Number of items to retrieve.',0),
 (22,51,NULL,'tag','Filter by this tag.',0),
 (23,54,NULL,'old','Tag to rename.',1),
 (24,54,NULL,'new','New name.',1),
 (25,55,NULL,'tag','Filter by this tag.',0),
 (26,55,NULL,'dt','Filter by this date (CCYY-MM-DDThh:mm:ssZ).',0),
 (27,55,NULL,'url','Filter by this URL.',0),
 (28,56,NULL,'tag','Filter by this tag.',0),
 (29,56,NULL,'count','Number of items to retrieve.',0),
 (30,57,NULL,'tag','Filter by this tag.',0),
 (31,58,NULL,'tag','Filter by this tag.',0),
 (32,59,NULL,'url','The URL of the item.',1),
 (33,59,NULL,'description','The description of the item.',1),
 (34,59,NULL,'extended','Notes for the item.',0),
 (35,59,NULL,'tags','Tags for the item.',0),
 (36,59,NULL,'dt','Datestamp of the item.',0),
 (37,59,NULL,'replace','Unless set to ',0),
 (38,59,NULL,'shared','Set to ',0),
 (39,60,NULL,'url','The URL of the item.',1),
 (40,62,NULL,'bundle','The bundle name.',0),
 (41,62,NULL,'tags','List of tags.',1),
 (42,63,NULL,'bundle','The bundle name.',0);
/*!40000 ALTER TABLE `ae_parametro` ENABLE KEYS */;


--
-- Definition of table `ae_processamento`
--

DROP TABLE IF EXISTS `ae_processamento`;
CREATE TABLE `ae_processamento` (
  `id_processamento` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `descricao` varchar(200) NOT NULL,
  PRIMARY KEY (`id_processamento`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ae_processamento`
--

/*!40000 ALTER TABLE `ae_processamento` DISABLE KEYS */;
INSERT INTO `ae_processamento` (`id_processamento`,`descricao`) VALUES 
 (1,'Arquivo WADL'),
 (2,'URL por Reflexão');
/*!40000 ALTER TABLE `ae_processamento` ENABLE KEYS */;


--
-- Definition of table `ae_recurso`
--

DROP TABLE IF EXISTS `ae_recurso`;
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
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ae_recurso`
--

/*!40000 ALTER TABLE `ae_recurso` DISABLE KEYS */;
INSERT INTO `ae_recurso` (`id_recurso`,`id_aplicacao`,`nome`,`base`,`path`,`metodo`) VALUES 
 (1,1,'Receber Tags','http://www.delicious.com','v1/tags/get/getTags',NULL),
 (2,1,'Enviar Post','http://www.delicious.com','v1/tags/get/addPost',NULL),
 (3,2,'Enviar Mensagem','http://www.twitter.com','v1/tags/get/sendMsg',NULL),
 (40,22,'A note on structure','https://api.del.icio.us/','v1/update','GET'),
 (41,22,'Schema description','https://api.del.icio.us/','v1/tags/get','GET'),
 (42,22,'Tag to rename.','https://api.del.icio.us/','v1/tags/rename','POST'),
 (43,23,'A note on structure','https://api.del.icio.us/','v1/update','GET'),
 (44,23,'Schema description','https://api.del.icio.us/','v1/tags/get','GET'),
 (45,23,'Tag to rename.','https://api.del.icio.us/','v1/tags/rename','POST'),
 (46,24,'A note on structure','https://api.del.icio.us/','v1/update','GET'),
 (47,24,'Schema description','https://api.del.icio.us/','v1/tags/get','GET'),
 (48,24,'Tag to rename.','https://api.del.icio.us/','v1/tags/rename','POST'),
 (49,24,'Returns posts matching the arguments.','https://api.del.icio.us/','v1/posts/get','GET'),
 (50,24,'Returns a list of the most recent posts.','https://api.del.icio.us/','v1/posts/recent','GET'),
 (51,24,'Returns all posts','https://api.del.icio.us/','v1/posts/all','GET'),
 (52,25,'A note on structure','https://api.del.icio.us/','v1/update','GET'),
 (53,25,'Schema description','https://api.del.icio.us/','v1/tags/get','GET'),
 (54,25,'Tag to rename.','https://api.del.icio.us/','v1/tags/rename','POST'),
 (55,25,'Returns posts matching the arguments.','https://api.del.icio.us/','v1/posts/get','GET'),
 (56,25,'Returns a list of the most recent posts.','https://api.del.icio.us/','v1/posts/recent','GET'),
 (57,25,'Returns all posts','https://api.del.icio.us/','v1/posts/all','GET'),
 (58,25,'Returns a list of dates with the number of posts at each date.','https://api.del.icio.us/','v1/posts/dates','GET'),
 (59,25,'Add a post to del.icio.us','https://api.del.icio.us/','v1/posts/add','GET'),
 (60,25,'Delete a post from del.icio.us','https://api.del.icio.us/','v1/posts/delete','GET'),
 (61,25,'Retrieve all of a user\'s bundles.','https://api.del.icio.us/','v1/bundles/all','GET'),
 (62,25,'Assign a set of tags to a single bundle.','https://api.del.icio.us/','v1/bundles/set','GET'),
 (63,25,'Deletes a bundle.','https://api.del.icio.us/','v1/bundles/delete','GET');
/*!40000 ALTER TABLE `ae_recurso` ENABLE KEYS */;


--
-- Definition of table `ae_usuario_aplicacao`
--

DROP TABLE IF EXISTS `ae_usuario_aplicacao`;
CREATE TABLE `ae_usuario_aplicacao` (
  `pk_usuario` int(10) unsigned NOT NULL,
  `id_aplicacao` int(10) unsigned NOT NULL,
  PRIMARY KEY (`pk_usuario`,`id_aplicacao`),
  KEY `FK_ae_usuario_aplicacao_2` (`id_aplicacao`),
  CONSTRAINT `FK_ae_usuario_aplicacao_1` FOREIGN KEY (`pk_usuario`) REFERENCES `usuario` (`pk_usuario`),
  CONSTRAINT `FK_ae_usuario_aplicacao_2` FOREIGN KEY (`id_aplicacao`) REFERENCES `ae_aplicacao` (`id_aplicacao`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ae_usuario_aplicacao`
--

/*!40000 ALTER TABLE `ae_usuario_aplicacao` DISABLE KEYS */;
/*!40000 ALTER TABLE `ae_usuario_aplicacao` ENABLE KEYS */;


--
-- Definition of table `ae_usuario_autenticacao`
--

DROP TABLE IF EXISTS `ae_usuario_autenticacao`;
CREATE TABLE `ae_usuario_autenticacao` (
  `openid_url` varchar(255) DEFAULT NULL,
  `pk_usuario` int(10) unsigned NOT NULL,
  `login_relembrar` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`pk_usuario`) USING BTREE,
  CONSTRAINT `FK_ae_usuario_autenticacao_1` FOREIGN KEY (`pk_usuario`) REFERENCES `usuario` (`pk_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ae_usuario_autenticacao`
--

/*!40000 ALTER TABLE `ae_usuario_autenticacao` DISABLE KEYS */;
/*!40000 ALTER TABLE `ae_usuario_autenticacao` ENABLE KEYS */;


--
-- Definition of table `aluno`
--

DROP TABLE IF EXISTS `aluno`;
CREATE TABLE `aluno` (
  `pk_aluno` int(10) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`pk_aluno`),
  CONSTRAINT `FK_aluno_1` FOREIGN KEY (`pk_aluno`) REFERENCES `membro` (`pk_membro`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `aluno`
--

/*!40000 ALTER TABLE `aluno` DISABLE KEYS */;
/*!40000 ALTER TABLE `aluno` ENABLE KEYS */;


--
-- Definition of table `alunoaula`
--

DROP TABLE IF EXISTS `alunoaula`;
CREATE TABLE `alunoaula` (
  `fk_aluno` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fk_aula` int(10) unsigned NOT NULL,
  PRIMARY KEY (`fk_aluno`,`fk_aula`),
  KEY `FK_AlunoAula_2` (`fk_aula`),
  CONSTRAINT `FK_AlunoAula_1` FOREIGN KEY (`fk_aluno`) REFERENCES `membro` (`pk_membro`),
  CONSTRAINT `FK_AlunoAula_2` FOREIGN KEY (`fk_aula`) REFERENCES `aula` (`pk_aula`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `alunoaula`
--

/*!40000 ALTER TABLE `alunoaula` DISABLE KEYS */;
/*!40000 ALTER TABLE `alunoaula` ENABLE KEYS */;


--
-- Definition of table `alunocurso`
--

DROP TABLE IF EXISTS `alunocurso`;
CREATE TABLE `alunocurso` (
  `fk_aluno` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fk_curso` int(10) unsigned NOT NULL,
  PRIMARY KEY (`fk_aluno`,`fk_curso`),
  KEY `FK_AlunoCurso_2` (`fk_curso`),
  CONSTRAINT `FK_AlunoCurso_1` FOREIGN KEY (`fk_aluno`) REFERENCES `membro` (`pk_membro`),
  CONSTRAINT `FK_AlunoCurso_2` FOREIGN KEY (`fk_curso`) REFERENCES `curso` (`pk_curso`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `alunocurso`
--

/*!40000 ALTER TABLE `alunocurso` DISABLE KEYS */;
/*!40000 ALTER TABLE `alunocurso` ENABLE KEYS */;


--
-- Definition of table `ambiente`
--

DROP TABLE IF EXISTS `ambiente`;
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

--
-- Dumping data for table `ambiente`
--

/*!40000 ALTER TABLE `ambiente` DISABLE KEYS */;
INSERT INTO `ambiente` (`pk_ambiente`,`no_ambiente`,`dt_ambiente`,`ds_ambiente`,`no_imagem`,`fk_professor`) VALUES 
 (1,'Projeto Final','2009-11-22 15:29:00','Ambiente destinado aos alunos aptos a iniciarem seus projetos de conclusão de curso','lll',4),
 (2,'Banco de Dados','2009-11-22 15:31:00','Ambiente destinado aos alunos que desejam aprender a disciplina de Banco de Dados','/',2),
 (3,'Tópicos Especiais II','2009-11-22 15:32:00','Ambiente destinado aos alunos que desejam aprender a disciplina de Tópicos Especiais II','lll',3),
 (4,'aaa','2010-01-27 13:47:35','ffff','',2),
 (5,'xxxx','2010-01-27 13:47:43','ffff','',2),
 (6,'pppppppp','2010-01-27 13:47:50','aaaaaaa','',2),
 (7,'zzzzzz','2010-01-27 13:47:56','ffffffffffff','',2),
 (8,'ffasdas','2010-01-27 13:48:03','fffff','',2),
 (9,'xxxxxxxxxhhhh','2010-01-27 13:48:43','hhhh','',2),
 (10,'fff','2010-01-27 13:49:25','aaaa','',2),
 (11,'ttt','2010-01-27 13:49:31','aaa','',2),
 (12,'zzzz','2010-01-27 13:49:35','huhu','',2),
 (13,'pipip','2010-01-27 13:49:41','opoppo','',2),
 (14,'Neurociências','2010-02-01 15:22:48','Estudo de Neurociências Cognitivas','',2);
/*!40000 ALTER TABLE `ambiente` ENABLE KEYS */;


--
-- Definition of table `ambientetag`
--

DROP TABLE IF EXISTS `ambientetag`;
CREATE TABLE `ambientetag` (
  `fk_ambiente` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fk_tag` int(10) unsigned NOT NULL,
  PRIMARY KEY (`fk_ambiente`,`fk_tag`),
  KEY `FK_AmbienteTag_2` (`fk_tag`),
  CONSTRAINT `FK_AmbienteTag_1` FOREIGN KEY (`fk_ambiente`) REFERENCES `ambiente` (`pk_ambiente`),
  CONSTRAINT `FK_AmbienteTag_2` FOREIGN KEY (`fk_tag`) REFERENCES `tag` (`pk_tag`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ambientetag`
--

/*!40000 ALTER TABLE `ambientetag` DISABLE KEYS */;
INSERT INTO `ambientetag` (`fk_ambiente`,`fk_tag`) VALUES 
 (2,2),
 (3,3),
 (4,4),
 (14,5);
/*!40000 ALTER TABLE `ambientetag` ENABLE KEYS */;


--
-- Definition of table `areainteresse`
--

DROP TABLE IF EXISTS `areainteresse`;
CREATE TABLE `areainteresse` (
  `pk_areaInteresse` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `no_areaInteresse` varchar(60) NOT NULL,
  `ds_areaInteresse` text NOT NULL,
  PRIMARY KEY (`pk_areaInteresse`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `areainteresse`
--

/*!40000 ALTER TABLE `areainteresse` DISABLE KEYS */;
INSERT INTO `areainteresse` (`pk_areaInteresse`,`no_areaInteresse`,`ds_areaInteresse`) VALUES 
 (1,'Análise de Sistemas','Desenvolvimento de sistemas'),
 (2,'Redes de Computadores','Infra-estrutura'),
 (3,'Banco de Dados','Suporte de Banco de Dados'),
 (4,'Interface Homem-Máquina','Interface com o usuário');
/*!40000 ALTER TABLE `areainteresse` ENABLE KEYS */;


--
-- Definition of table `areainteressetag`
--

DROP TABLE IF EXISTS `areainteressetag`;
CREATE TABLE `areainteressetag` (
  `fk_areaInteresse` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fk_tag` int(10) unsigned NOT NULL,
  PRIMARY KEY (`fk_areaInteresse`,`fk_tag`),
  KEY `FK_areaInteresseTag_2` (`fk_tag`),
  CONSTRAINT `FK_areaInteresseTag_1` FOREIGN KEY (`fk_areaInteresse`) REFERENCES `areainteresse` (`pk_areaInteresse`),
  CONSTRAINT `FK_areaInteresseTag_2` FOREIGN KEY (`fk_tag`) REFERENCES `tag` (`pk_tag`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `areainteressetag`
--

/*!40000 ALTER TABLE `areainteressetag` DISABLE KEYS */;
/*!40000 ALTER TABLE `areainteressetag` ENABLE KEYS */;


--
-- Definition of table `areatrabalho`
--

DROP TABLE IF EXISTS `areatrabalho`;
CREATE TABLE `areatrabalho` (
  `pk_areaTrabalho` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `no_areaTrabalho` varchar(60) NOT NULL,
  `ds_areaTrabalho` text NOT NULL,
  `fk_membro` int(10) unsigned NOT NULL,
  PRIMARY KEY (`pk_areaTrabalho`),
  KEY `FK_areatrabalho_1` (`fk_membro`),
  CONSTRAINT `FK_areatrabalho_1` FOREIGN KEY (`fk_membro`) REFERENCES `membro` (`pk_membro`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `areatrabalho`
--

/*!40000 ALTER TABLE `areatrabalho` DISABLE KEYS */;
INSERT INTO `areatrabalho` (`pk_areaTrabalho`,`no_areaTrabalho`,`ds_areaTrabalho`,`fk_membro`) VALUES 
 (1,'Professor Universitário','Ministro aulas para ensino superior',2);
/*!40000 ALTER TABLE `areatrabalho` ENABLE KEYS */;


--
-- Definition of table `arquivoaula`
--

DROP TABLE IF EXISTS `arquivoaula`;
CREATE TABLE `arquivoaula` (
  `pk_arquivoAula` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `no_arquivoAula` varchar(100) NOT NULL,
  `fk_aula` int(10) unsigned NOT NULL,
  PRIMARY KEY (`pk_arquivoAula`),
  KEY `FK_arquivoAula_1` (`fk_aula`),
  CONSTRAINT `FK_arquivoAula_1` FOREIGN KEY (`fk_aula`) REFERENCES `aula` (`pk_aula`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `arquivoaula`
--

/*!40000 ALTER TABLE `arquivoaula` DISABLE KEYS */;
INSERT INTO `arquivoaula` (`pk_arquivoAula`,`no_arquivoAula`,`fk_aula`) VALUES 
 (1,'c4ca4238a0b923820dcc509a6f75849b/fundo-azul-vibrante-thumb5677811.jpg',1),
 (2,'c4ca4238a0b923820dcc509a6f75849b/fundo-azul-vibrante-thumb5677811.jpg',1),
 (3,'c4ca4238a0b923820dcc509a6f75849b/eclipse-php-galileo-sr1-win32.zip',1),
 (4,'c4ca4238a0b923820dcc509a6f75849b/backup_de_alterar.cdr',1),
 (5,'c4ca4238a0b923820dcc509a6f75849b/backup_de_incluir.cdr',1);
/*!40000 ALTER TABLE `arquivoaula` ENABLE KEYS */;


--
-- Definition of table `arquivoblog`
--

DROP TABLE IF EXISTS `arquivoblog`;
CREATE TABLE `arquivoblog` (
  `pk_arquivoBlog` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `no_arquivoBlog` varchar(60) NOT NULL,
  `fk_blog` int(10) unsigned NOT NULL,
  PRIMARY KEY (`pk_arquivoBlog`),
  KEY `FK_arquivoBlog_1` (`fk_blog`),
  CONSTRAINT `FK_arquivoBlog_1` FOREIGN KEY (`fk_blog`) REFERENCES `blog` (`pk_blog`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `arquivoblog`
--

/*!40000 ALTER TABLE `arquivoblog` DISABLE KEYS */;
/*!40000 ALTER TABLE `arquivoblog` ENABLE KEYS */;


--
-- Definition of table `artefato`
--

DROP TABLE IF EXISTS `artefato`;
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

--
-- Dumping data for table `artefato`
--

/*!40000 ALTER TABLE `artefato` DISABLE KEYS */;
/*!40000 ALTER TABLE `artefato` ENABLE KEYS */;


--
-- Definition of table `artefatotag`
--

DROP TABLE IF EXISTS `artefatotag`;
CREATE TABLE `artefatotag` (
  `fk_artefato` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fk_tag` int(10) unsigned NOT NULL,
  PRIMARY KEY (`fk_artefato`,`fk_tag`),
  KEY `FK_artefatoTag_2` (`fk_tag`),
  CONSTRAINT `FK_artefatoTag_1` FOREIGN KEY (`fk_artefato`) REFERENCES `artefato` (`pk_artefato`),
  CONSTRAINT `FK_artefatoTag_2` FOREIGN KEY (`fk_tag`) REFERENCES `tag` (`pk_tag`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `artefatotag`
--

/*!40000 ALTER TABLE `artefatotag` DISABLE KEYS */;
/*!40000 ALTER TABLE `artefatotag` ENABLE KEYS */;


--
-- Definition of table `aula`
--

DROP TABLE IF EXISTS `aula`;
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

--
-- Dumping data for table `aula`
--

/*!40000 ALTER TABLE `aula` DISABLE KEYS */;
INSERT INTO `aula` (`pk_aula`,`ds_assunto`,`ds_aula`,`nu_peso`,`fk_curso`,`dt_aula`) VALUES 
 (1,'te','ste',123,9,'2010-01-15 00:00:00'),
 (2,'zzz','zzz',12,9,'2010-01-09 00:00:00'),
 (3,'zzzhhhhhh','aaaa',55,9,'2010-01-02 00:00:00'),
 (4,'cxzcxzcz','czxcxzc',1,37,'2010-02-28 00:00:00'),
 (5,'aaaa','aaaa',1,37,'2010-02-28 00:00:00'),
 (6,'fdfdsfsdf','sdfsdfsd',1,37,'2010-02-28 00:00:00');
/*!40000 ALTER TABLE `aula` ENABLE KEYS */;


--
-- Definition of table `avaliacaomembro`
--

DROP TABLE IF EXISTS `avaliacaomembro`;
CREATE TABLE `avaliacaomembro` (
  `fk_membroAvaliador` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fk_membro` int(10) unsigned NOT NULL,
  `nu_avaliacao` int(10) unsigned NOT NULL,
  PRIMARY KEY (`fk_membroAvaliador`,`fk_membro`),
  KEY `FK_AvaliacaoMembro_2` (`fk_membro`),
  CONSTRAINT `FK_AvaliacaoMembro_1` FOREIGN KEY (`fk_membroAvaliador`) REFERENCES `membro` (`pk_membro`),
  CONSTRAINT `FK_AvaliacaoMembro_2` FOREIGN KEY (`fk_membro`) REFERENCES `membro` (`pk_membro`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `avaliacaomembro`
--

/*!40000 ALTER TABLE `avaliacaomembro` DISABLE KEYS */;
/*!40000 ALTER TABLE `avaliacaomembro` ENABLE KEYS */;


--
-- Definition of table `avaliacaoservico`
--

DROP TABLE IF EXISTS `avaliacaoservico`;
CREATE TABLE `avaliacaoservico` (
  `fk_membro` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fk_servico` int(10) unsigned NOT NULL,
  `nu_avaliacao` int(10) unsigned NOT NULL,
  PRIMARY KEY (`fk_membro`,`fk_servico`),
  KEY `FK_avaliacaoServico_2` (`fk_servico`),
  CONSTRAINT `FK_avaliacaoServico_1` FOREIGN KEY (`fk_membro`) REFERENCES `membro` (`pk_membro`),
  CONSTRAINT `FK_avaliacaoServico_2` FOREIGN KEY (`fk_servico`) REFERENCES `servico` (`pk_servico`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `avaliacaoservico`
--

/*!40000 ALTER TABLE `avaliacaoservico` DISABLE KEYS */;
/*!40000 ALTER TABLE `avaliacaoservico` ENABLE KEYS */;


--
-- Definition of table `bairro`
--

DROP TABLE IF EXISTS `bairro`;
CREATE TABLE `bairro` (
  `pk_bairro` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `no_bairro` varchar(60) NOT NULL,
  `fk_municipio` int(10) unsigned NOT NULL,
  PRIMARY KEY (`pk_bairro`),
  KEY `FK_bairro_1` (`fk_municipio`),
  CONSTRAINT `FK_bairro_1` FOREIGN KEY (`fk_municipio`) REFERENCES `municipio` (`pk_municipio`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bairro`
--

/*!40000 ALTER TABLE `bairro` DISABLE KEYS */;
INSERT INTO `bairro` (`pk_bairro`,`no_bairro`,`fk_municipio`) VALUES 
 (1,'Centro',1),
 (2,'Del Castilho',1),
 (3,'Penha',1),
 (4,'Irajá',1),
 (5,'Abolição',1),
 (6,'Olaria',1);
/*!40000 ALTER TABLE `bairro` ENABLE KEYS */;


--
-- Definition of table `blog`
--

DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog` (
  `pk_blog` int(10) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`pk_blog`),
  CONSTRAINT `FK_blog_1` FOREIGN KEY (`pk_blog`) REFERENCES `servico` (`pk_servico`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `blog`
--

/*!40000 ALTER TABLE `blog` DISABLE KEYS */;
INSERT INTO `blog` (`pk_blog`) VALUES 
 (6),
 (11),
 (12),
 (13),
 (14),
 (15),
 (16),
 (17),
 (18),
 (19),
 (20),
 (21),
 (22),
 (23),
 (24),
 (25),
 (26),
 (27),
 (28),
 (29),
 (30),
 (31),
 (32),
 (33),
 (34),
 (35),
 (36);
/*!40000 ALTER TABLE `blog` ENABLE KEYS */;


--
-- Definition of table `categoriaartefato`
--

DROP TABLE IF EXISTS `categoriaartefato`;
CREATE TABLE `categoriaartefato` (
  `pk_catArtefato` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `no_catArtefato` varchar(60) NOT NULL,
  `ds_catArtefato` text NOT NULL,
  PRIMARY KEY (`pk_catArtefato`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `categoriaartefato`
--

/*!40000 ALTER TABLE `categoriaartefato` DISABLE KEYS */;
INSERT INTO `categoriaartefato` (`pk_catArtefato`,`no_catArtefato`,`ds_catArtefato`) VALUES 
 (1,'Artigos','Artigos Científicos'),
 (2,'Dissertações','Dissertações de Mestrado'),
 (3,'Teses','Teses de Doutorado');
/*!40000 ALTER TABLE `categoriaartefato` ENABLE KEYS */;


--
-- Definition of table `categoriacomunidade`
--

DROP TABLE IF EXISTS `categoriacomunidade`;
CREATE TABLE `categoriacomunidade` (
  `pk_catComunidade` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `no_catComunidade` varchar(60) NOT NULL,
  `ds_catComunidade` text NOT NULL,
  PRIMARY KEY (`pk_catComunidade`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `categoriacomunidade`
--

/*!40000 ALTER TABLE `categoriacomunidade` DISABLE KEYS */;
/*!40000 ALTER TABLE `categoriacomunidade` ENABLE KEYS */;


--
-- Definition of table `categoriaforum`
--

DROP TABLE IF EXISTS `categoriaforum`;
CREATE TABLE `categoriaforum` (
  `pk_catForum` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `no_catForum` varchar(60) NOT NULL,
  `ds_catForum` text NOT NULL,
  `fk_etapa` int(10) unsigned NOT NULL,
  PRIMARY KEY (`pk_catForum`),
  KEY `FK_categoriaForum_1` (`fk_etapa`),
  CONSTRAINT `FK_categoriaForum_1` FOREIGN KEY (`fk_etapa`) REFERENCES `etapa` (`pk_etapa`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `categoriaforum`
--

/*!40000 ALTER TABLE `categoriaforum` DISABLE KEYS */;
INSERT INTO `categoriaforum` (`pk_catForum`,`no_catForum`,`ds_catForum`,`fk_etapa`) VALUES 
 (1,'DER','Como criar um der',1),
 (2,'Leitura','dfdsfdsf',2);
/*!40000 ALTER TABLE `categoriaforum` ENABLE KEYS */;


--
-- Definition of table `comunidade`
--

DROP TABLE IF EXISTS `comunidade`;
CREATE TABLE `comunidade` (
  `pk_comunidade` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fk_catComunidade` int(10) unsigned NOT NULL,
  PRIMARY KEY (`pk_comunidade`),
  KEY `FK_comunidade_2` (`fk_catComunidade`),
  CONSTRAINT `FK_comunidade_1` FOREIGN KEY (`pk_comunidade`) REFERENCES `servico` (`pk_servico`),
  CONSTRAINT `FK_comunidade_2` FOREIGN KEY (`fk_catComunidade`) REFERENCES `categoriacomunidade` (`pk_catComunidade`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `comunidade`
--

/*!40000 ALTER TABLE `comunidade` DISABLE KEYS */;
/*!40000 ALTER TABLE `comunidade` ENABLE KEYS */;


--
-- Definition of table `comunidadeartefato`
--

DROP TABLE IF EXISTS `comunidadeartefato`;
CREATE TABLE `comunidadeartefato` (
  `fk_comunidade` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fk_artefato` int(10) unsigned NOT NULL,
  PRIMARY KEY (`fk_comunidade`,`fk_artefato`),
  KEY `FK_comunidadeArtefato_2` (`fk_artefato`),
  CONSTRAINT `FK_comunidadeArtefato_1` FOREIGN KEY (`fk_comunidade`) REFERENCES `comunidade` (`pk_comunidade`),
  CONSTRAINT `FK_comunidadeArtefato_2` FOREIGN KEY (`fk_artefato`) REFERENCES `artefato` (`pk_artefato`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `comunidadeartefato`
--

/*!40000 ALTER TABLE `comunidadeartefato` DISABLE KEYS */;
/*!40000 ALTER TABLE `comunidadeartefato` ENABLE KEYS */;


--
-- Definition of table `comunidadetag`
--

DROP TABLE IF EXISTS `comunidadetag`;
CREATE TABLE `comunidadetag` (
  `fk_comunidade` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fk_tag` int(10) unsigned NOT NULL,
  PRIMARY KEY (`fk_comunidade`,`fk_tag`),
  KEY `FK_ComunidadeTag_2` (`fk_tag`),
  CONSTRAINT `FK_ComunidadeTag_1` FOREIGN KEY (`fk_comunidade`) REFERENCES `comunidade` (`pk_comunidade`),
  CONSTRAINT `FK_ComunidadeTag_2` FOREIGN KEY (`fk_tag`) REFERENCES `tag` (`pk_tag`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `comunidadetag`
--

/*!40000 ALTER TABLE `comunidadetag` DISABLE KEYS */;
/*!40000 ALTER TABLE `comunidadetag` ENABLE KEYS */;


--
-- Definition of table `contatoinstituicao`
--

DROP TABLE IF EXISTS `contatoinstituicao`;
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

--
-- Dumping data for table `contatoinstituicao`
--

/*!40000 ALTER TABLE `contatoinstituicao` DISABLE KEYS */;
INSERT INTO `contatoinstituicao` (`pk_contatoInstituicao`,`fk_tipoContato`,`fk_instituicao`,`ds_contato`) VALUES 
 (1,4,1,'www.estacio.br'),
 (2,3,1,'(21)2222-3333');
/*!40000 ALTER TABLE `contatoinstituicao` ENABLE KEYS */;


--
-- Definition of table `contatousuario`
--

DROP TABLE IF EXISTS `contatousuario`;
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

--
-- Dumping data for table `contatousuario`
--

/*!40000 ALTER TABLE `contatousuario` DISABLE KEYS */;
INSERT INTO `contatousuario` (`pk_contatoUsuario`,`fk_tipoContato`,`fk_usuario`,`ds_contato`) VALUES 
 (1,1,2,'rodrigo@gmail.com'),
 (2,5,2,'(21)8888-4444');
/*!40000 ALTER TABLE `contatousuario` ENABLE KEYS */;


--
-- Definition of table `curso`
--

DROP TABLE IF EXISTS `curso`;
CREATE TABLE `curso` (
  `pk_curso` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `dt_liberacao` datetime NOT NULL,
  PRIMARY KEY (`pk_curso`),
  CONSTRAINT `FK_curso_1` FOREIGN KEY (`pk_curso`) REFERENCES `servico` (`pk_servico`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `curso`
--

/*!40000 ALTER TABLE `curso` DISABLE KEYS */;
INSERT INTO `curso` (`pk_curso`,`dt_liberacao`) VALUES 
 (9,'2010-01-19 13:57:26'),
 (10,'2010-01-21 11:11:22'),
 (37,'2010-02-01 15:35:24');
/*!40000 ALTER TABLE `curso` ENABLE KEYS */;


--
-- Definition of table `cursoextensao`
--

DROP TABLE IF EXISTS `cursoextensao`;
CREATE TABLE `cursoextensao` (
  `pk_cursoExtensao` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `no_cursoExtensao` varchar(60) NOT NULL,
  `ds_cursoExtensao` text NOT NULL,
  `fk_membro` int(10) unsigned NOT NULL,
  PRIMARY KEY (`pk_cursoExtensao`),
  KEY `FK_cursoExtensao_1` (`fk_membro`),
  CONSTRAINT `FK_cursoExtensao_1` FOREIGN KEY (`fk_membro`) REFERENCES `membro` (`pk_membro`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cursoextensao`
--

/*!40000 ALTER TABLE `cursoextensao` DISABLE KEYS */;
INSERT INTO `cursoextensao` (`pk_cursoExtensao`,`no_cursoExtensao`,`ds_cursoExtensao`,`fk_membro`) VALUES 
 (1,'Banco de Dados Espacial','Modelagem de dados usando um banco de dados espacial.',2);
/*!40000 ALTER TABLE `cursoextensao` ENABLE KEYS */;


--
-- Definition of table `etapa`
--

DROP TABLE IF EXISTS `etapa`;
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

--
-- Dumping data for table `etapa`
--

/*!40000 ALTER TABLE `etapa` DISABLE KEYS */;
INSERT INTO `etapa` (`pk_etapa`,`no_etapa`,`ds_etapa`,`dt_etapa`,`fk_meta`) VALUES 
 (1,'DER','Diagrama de Entidade e Relacionamento','2009-11-22 00:00:00',1),
 (2,'dasd','asdasd','2010-02-28 00:00:00',4);
/*!40000 ALTER TABLE `etapa` ENABLE KEYS */;


--
-- Definition of table `favorito`
--

DROP TABLE IF EXISTS `favorito`;
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

--
-- Dumping data for table `favorito`
--

/*!40000 ALTER TABLE `favorito` DISABLE KEYS */;
INSERT INTO `favorito` (`pk_favorito`,`no_link`,`ds_link`,`no_url`,`fk_membro`,`fk_pasta`) VALUES 
 (1,'youtube','','http://www.youtube.com',2,2),
 (2,'Globo','','http://www.globo.com',2,2),
 (3,'Novo Favorito asdasd asd asd','','http://',2,5),
 (4,'Novo Favorito','','http://',2,5),
 (5,'Novo Favorito','','http://',2,5),
 (6,'Novo Favorito','','http://',2,3),
 (10,'Gmail','','http://www.gmail.com',2,1);
/*!40000 ALTER TABLE `favorito` ENABLE KEYS */;


--
-- Definition of table `formacaoacademica`
--

DROP TABLE IF EXISTS `formacaoacademica`;
CREATE TABLE `formacaoacademica` (
  `pk_formacaoAcademica` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `no_formacaoAcademica` varchar(60) NOT NULL,
  PRIMARY KEY (`pk_formacaoAcademica`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `formacaoacademica`
--

/*!40000 ALTER TABLE `formacaoacademica` DISABLE KEYS */;
INSERT INTO `formacaoacademica` (`pk_formacaoAcademica`,`no_formacaoAcademica`) VALUES 
 (1,'Bacharel'),
 (2,'Mestrado'),
 (3,'Doutorado');
/*!40000 ALTER TABLE `formacaoacademica` ENABLE KEYS */;


--
-- Definition of table `forum`
--

DROP TABLE IF EXISTS `forum`;
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

--
-- Dumping data for table `forum`
--

/*!40000 ALTER TABLE `forum` DISABLE KEYS */;
INSERT INTO `forum` (`pk_forum`,`fk_catForum`,`fk_tarefa`) VALUES 
 (1,1,1),
 (2,1,2),
 (38,2,3);
/*!40000 ALTER TABLE `forum` ENABLE KEYS */;


--
-- Definition of table `idioma`
--

DROP TABLE IF EXISTS `idioma`;
CREATE TABLE `idioma` (
  `pk_idioma` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `no_idioma` varchar(60) NOT NULL,
  PRIMARY KEY (`pk_idioma`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `idioma`
--

/*!40000 ALTER TABLE `idioma` DISABLE KEYS */;
INSERT INTO `idioma` (`pk_idioma`,`no_idioma`) VALUES 
 (1,'Inglês'),
 (2,'Espanhol'),
 (3,'Francês'),
 (4,'Alemão'),
 (5,'Italiano'),
 (6,'Russo');
/*!40000 ALTER TABLE `idioma` ENABLE KEYS */;


--
-- Definition of table `instituicao`
--

DROP TABLE IF EXISTS `instituicao`;
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

--
-- Dumping data for table `instituicao`
--

/*!40000 ALTER TABLE `instituicao` DISABLE KEYS */;
INSERT INTO `instituicao` (`pk_instituicao`,`no_instituicao`,`tp_logradouroInst`,`no_logradouroInst`,`nu_logradouroInst`,`ds_complementoInst`,`nu_cepInst`,`fk_bairroInst`) VALUES 
 (1,'Universidade Estácio de Sá','Av','Presidente Lutter King',1111,'','21070-789',2);
/*!40000 ALTER TABLE `instituicao` ENABLE KEYS */;


--
-- Definition of table `membro`
--

DROP TABLE IF EXISTS `membro`;
CREATE TABLE `membro` (
  `pk_membro` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fk_formacaoAcademica` int(10) unsigned DEFAULT NULL,
  `fl_permissao` varchar(1) NOT NULL,
  PRIMARY KEY (`pk_membro`),
  KEY `FK_New Table_2` (`fk_formacaoAcademica`),
  CONSTRAINT `FK_New Table_1` FOREIGN KEY (`pk_membro`) REFERENCES `usuario` (`pk_usuario`),
  CONSTRAINT `FK_New Table_2` FOREIGN KEY (`fk_formacaoAcademica`) REFERENCES `formacaoacademica` (`pk_formacaoAcademica`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `membro`
--

/*!40000 ALTER TABLE `membro` DISABLE KEYS */;
INSERT INTO `membro` (`pk_membro`,`fk_formacaoAcademica`,`fl_permissao`) VALUES 
 (2,2,'P'),
 (3,1,'P'),
 (4,1,'P'),
 (5,1,'A'),
 (6,1,'A');
/*!40000 ALTER TABLE `membro` ENABLE KEYS */;


--
-- Definition of table `membroambiente`
--

DROP TABLE IF EXISTS `membroambiente`;
CREATE TABLE `membroambiente` (
  `fk_membro` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fk_ambiente` int(10) unsigned NOT NULL,
  PRIMARY KEY (`fk_membro`,`fk_ambiente`),
  KEY `FK_membroAmbiente_2` (`fk_ambiente`),
  CONSTRAINT `FK_membroAmbiente_1` FOREIGN KEY (`fk_membro`) REFERENCES `membro` (`pk_membro`),
  CONSTRAINT `FK_membroAmbiente_2` FOREIGN KEY (`fk_ambiente`) REFERENCES `ambiente` (`pk_ambiente`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `membroambiente`
--

/*!40000 ALTER TABLE `membroambiente` DISABLE KEYS */;
INSERT INTO `membroambiente` (`fk_membro`,`fk_ambiente`) VALUES 
 (2,1),
 (5,1),
 (2,2),
 (3,2),
 (5,2),
 (6,2),
 (2,3),
 (5,3),
 (2,14);
/*!40000 ALTER TABLE `membroambiente` ENABLE KEYS */;


--
-- Definition of table `membroareainteresse`
--

DROP TABLE IF EXISTS `membroareainteresse`;
CREATE TABLE `membroareainteresse` (
  `fk_membro` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fk_areaInteresse` int(10) unsigned NOT NULL,
  PRIMARY KEY (`fk_membro`,`fk_areaInteresse`),
  KEY `FK_membroAreaInteresse_2` (`fk_areaInteresse`),
  CONSTRAINT `FK_membroAreaInteresse_1` FOREIGN KEY (`fk_membro`) REFERENCES `membro` (`pk_membro`),
  CONSTRAINT `FK_membroAreaInteresse_2` FOREIGN KEY (`fk_areaInteresse`) REFERENCES `areainteresse` (`pk_areaInteresse`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `membroareainteresse`
--

/*!40000 ALTER TABLE `membroareainteresse` DISABLE KEYS */;
INSERT INTO `membroareainteresse` (`fk_membro`,`fk_areaInteresse`) VALUES 
 (2,1),
 (2,3);
/*!40000 ALTER TABLE `membroareainteresse` ENABLE KEYS */;


--
-- Definition of table `membroareatrabalho`
--

DROP TABLE IF EXISTS `membroareatrabalho`;
CREATE TABLE `membroareatrabalho` (
  `fk_membro` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fk_areaTrabalho` int(10) unsigned NOT NULL,
  PRIMARY KEY (`fk_membro`,`fk_areaTrabalho`),
  KEY `FK_membroAreaTrabalho_2` (`fk_areaTrabalho`),
  CONSTRAINT `FK_membroAreaTrabalho_1` FOREIGN KEY (`fk_membro`) REFERENCES `membro` (`pk_membro`),
  CONSTRAINT `FK_membroAreaTrabalho_2` FOREIGN KEY (`fk_areaTrabalho`) REFERENCES `areatrabalho` (`pk_areaTrabalho`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `membroareatrabalho`
--

/*!40000 ALTER TABLE `membroareatrabalho` DISABLE KEYS */;
/*!40000 ALTER TABLE `membroareatrabalho` ENABLE KEYS */;


--
-- Definition of table `membroidioma`
--

DROP TABLE IF EXISTS `membroidioma`;
CREATE TABLE `membroidioma` (
  `fk_membro` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fk_idioma` int(10) unsigned NOT NULL,
  PRIMARY KEY (`fk_membro`,`fk_idioma`),
  KEY `FK_membroIdioma_2` (`fk_idioma`),
  CONSTRAINT `FK_membroIdioma_1` FOREIGN KEY (`fk_membro`) REFERENCES `membro` (`pk_membro`),
  CONSTRAINT `FK_membroIdioma_2` FOREIGN KEY (`fk_idioma`) REFERENCES `idioma` (`pk_idioma`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `membroidioma`
--

/*!40000 ALTER TABLE `membroidioma` DISABLE KEYS */;
INSERT INTO `membroidioma` (`fk_membro`,`fk_idioma`) VALUES 
 (2,1),
 (2,2);
/*!40000 ALTER TABLE `membroidioma` ENABLE KEYS */;


--
-- Definition of table `membrotag`
--

DROP TABLE IF EXISTS `membrotag`;
CREATE TABLE `membrotag` (
  `fk_membro` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fk_tag` int(10) unsigned NOT NULL,
  PRIMARY KEY (`fk_membro`,`fk_tag`),
  KEY `FK_membroTag_2` (`fk_tag`),
  CONSTRAINT `FK_membroTag_1` FOREIGN KEY (`fk_membro`) REFERENCES `membro` (`pk_membro`),
  CONSTRAINT `FK_membroTag_2` FOREIGN KEY (`fk_tag`) REFERENCES `tag` (`pk_tag`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `membrotag`
--

/*!40000 ALTER TABLE `membrotag` DISABLE KEYS */;
/*!40000 ALTER TABLE `membrotag` ENABLE KEYS */;


--
-- Definition of table `mensagem`
--

DROP TABLE IF EXISTS `mensagem`;
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

--
-- Dumping data for table `mensagem`
--

/*!40000 ALTER TABLE `mensagem` DISABLE KEYS */;
INSERT INTO `mensagem` (`pk_mensagem`,`ds_assunto`,`ds_mensagem`,`fk_membroOrigem`,`fk_membroDestino`) VALUES 
 (1,'Teste','Oi tudo bem?',2,2),
 (2,'Duvida','Não consegui criar um der',5,2),
 (3,'prova','o que irÃ¡ cair na prova?',5,2),
 (4,'hehe','heherjr',2,5),
 (5,'teste','afffgggg',5,2),
 (6,'teste','affffffff',6,2),
 (7,'teste','affff',2,2),
 (8,'herhehe','affsrgsdfgdfgf',2,6),
 (9,'Para mim msm','testando\n\nbreak line\n\n...\n\né é é :D',2,2),
 (10,'m,nm,l','555565555',2,2),
 (11,'klçmklmmmmmmmmmmmmmmmm',';;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;',2,2);
/*!40000 ALTER TABLE `mensagem` ENABLE KEYS */;


--
-- Definition of table `meta`
--

DROP TABLE IF EXISTS `meta`;
CREATE TABLE `meta` (
  `pk_meta` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ds_meta` text NOT NULL,
  `ds_duracao` text NOT NULL,
  `fk_ambiente` int(10) unsigned NOT NULL,
  PRIMARY KEY (`pk_meta`),
  KEY `FK_meta_1` (`fk_ambiente`),
  CONSTRAINT `FK_meta_1` FOREIGN KEY (`fk_ambiente`) REFERENCES `ambiente` (`pk_ambiente`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `meta`
--

/*!40000 ALTER TABLE `meta` DISABLE KEYS */;
INSERT INTO `meta` (`pk_meta`,`ds_meta`,`ds_duracao`,`fk_ambiente`) VALUES 
 (1,'Modelagem de Dados','6 meses',2),
 (2,'SQL','6 meses',2),
 (3,'SQL Avançado','6 meses',2),
 (4,'Estudar Seminário','3',14);
/*!40000 ALTER TABLE `meta` ENABLE KEYS */;


--
-- Definition of table `metatag`
--

DROP TABLE IF EXISTS `metatag`;
CREATE TABLE `metatag` (
  `fk_meta` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fk_tag` int(10) unsigned NOT NULL,
  PRIMARY KEY (`fk_meta`,`fk_tag`),
  KEY `FK_MetaTag_2` (`fk_tag`),
  CONSTRAINT `FK_MetaTag_1` FOREIGN KEY (`fk_meta`) REFERENCES `meta` (`pk_meta`),
  CONSTRAINT `FK_MetaTag_2` FOREIGN KEY (`fk_tag`) REFERENCES `tag` (`pk_tag`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `metatag`
--

/*!40000 ALTER TABLE `metatag` DISABLE KEYS */;
/*!40000 ALTER TABLE `metatag` ENABLE KEYS */;


--
-- Definition of table `microblog`
--

DROP TABLE IF EXISTS `microblog`;
CREATE TABLE `microblog` (
  `pk_microblog` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ds_microblog` text NOT NULL,
  `fk_membro` int(10) unsigned NOT NULL,
  `st_atual` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`pk_microblog`),
  KEY `FK_microblog_1` (`fk_membro`),
  CONSTRAINT `FK_microblog_1` FOREIGN KEY (`fk_membro`) REFERENCES `membro` (`pk_membro`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `microblog`
--

/*!40000 ALTER TABLE `microblog` DISABLE KEYS */;
INSERT INTO `microblog` (`pk_microblog`,`ds_microblog`,`fk_membro`,`st_atual`) VALUES 
 (2,'Professor Legal 2',2,0),
 (3,'Meu microblog.',5,1),
 (4,'Segundo MicroBlog',2,1);
/*!40000 ALTER TABLE `microblog` ENABLE KEYS */;


--
-- Definition of table `municipio`
--

DROP TABLE IF EXISTS `municipio`;
CREATE TABLE `municipio` (
  `pk_municipio` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `no_municipio` varchar(60) NOT NULL,
  `fk_estado` int(10) unsigned NOT NULL,
  PRIMARY KEY (`pk_municipio`),
  KEY `FK_municipio_1` (`fk_estado`),
  CONSTRAINT `FK_municipio_1` FOREIGN KEY (`fk_estado`) REFERENCES `uf` (`pk_estado`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `municipio`
--

/*!40000 ALTER TABLE `municipio` DISABLE KEYS */;
INSERT INTO `municipio` (`pk_municipio`,`no_municipio`,`fk_estado`) VALUES 
 (1,'Rio de Janeiro',1),
 (3,'Niterói',1);
/*!40000 ALTER TABLE `municipio` ENABLE KEYS */;


--
-- Definition of table `pastasfavorito`
--

DROP TABLE IF EXISTS `pastasfavorito`;
CREATE TABLE `pastasfavorito` (
  `pk_pasta` int(11) NOT NULL,
  `no_pasta` varchar(100) NOT NULL,
  `fk_pasta` int(11) DEFAULT NULL,
  `fk_membro` int(11) NOT NULL,
  PRIMARY KEY (`pk_pasta`),
  KEY `fk_pasta` (`fk_pasta`),
  KEY `fk_membro` (`fk_membro`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

--
-- Dumping data for table `pastasfavorito`
--

/*!40000 ALTER TABLE `pastasfavorito` DISABLE KEYS */;
INSERT INTO `pastasfavorito` (`pk_pasta`,`no_pasta`,`fk_pasta`,`fk_membro`) VALUES 
 (1,'Google',NULL,2),
 (2,'Serviços',NULL,2),
 (4,'Nova Pasta',3,2),
 (5,'Nova Pasta',4,2);
/*!40000 ALTER TABLE `pastasfavorito` ENABLE KEYS */;


--
-- Definition of table `postblog`
--

DROP TABLE IF EXISTS `postblog`;
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

--
-- Dumping data for table `postblog`
--

/*!40000 ALTER TABLE `postblog` DISABLE KEYS */;
/*!40000 ALTER TABLE `postblog` ENABLE KEYS */;


--
-- Definition of table `postcomunidade`
--

DROP TABLE IF EXISTS `postcomunidade`;
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

--
-- Dumping data for table `postcomunidade`
--

/*!40000 ALTER TABLE `postcomunidade` DISABLE KEYS */;
/*!40000 ALTER TABLE `postcomunidade` ENABLE KEYS */;


--
-- Definition of table `posttopico`
--

DROP TABLE IF EXISTS `posttopico`;
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

--
-- Dumping data for table `posttopico`
--

/*!40000 ALTER TABLE `posttopico` DISABLE KEYS */;
INSERT INTO `posttopico` (`pk_postTopico`,`ds_post`,`dt_post`,`fk_topico`,`fk_membro`) VALUES 
 (1,'Você precisa identificar os relacionamentos e as entidades','2009-11-22 16:06:21',1,2),
 (2,'É simples.','2009-11-22 16:30:40',1,5),
 (3,'teste\n\né isso ai','2010-01-21 14:27:55',1,2),
 (4,'.m,çm,lç','2010-01-28 14:11:21',1,2),
 (5,'dsasdsadsadas','2010-02-01 15:47:57',4,2);
/*!40000 ALTER TABLE `posttopico` ENABLE KEYS */;


--
-- Definition of table `professor`
--

DROP TABLE IF EXISTS `professor`;
CREATE TABLE `professor` (
  `pk_professor` int(10) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`pk_professor`),
  CONSTRAINT `FK_professor_1` FOREIGN KEY (`pk_professor`) REFERENCES `membro` (`pk_membro`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `professor`
--

/*!40000 ALTER TABLE `professor` DISABLE KEYS */;
/*!40000 ALTER TABLE `professor` ENABLE KEYS */;


--
-- Definition of table `recomendacaomembro`
--

DROP TABLE IF EXISTS `recomendacaomembro`;
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

--
-- Dumping data for table `recomendacaomembro`
--

/*!40000 ALTER TABLE `recomendacaomembro` DISABLE KEYS */;
/*!40000 ALTER TABLE `recomendacaomembro` ENABLE KEYS */;


--
-- Definition of table `recomendacaoservico`
--

DROP TABLE IF EXISTS `recomendacaoservico`;
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

--
-- Dumping data for table `recomendacaoservico`
--

/*!40000 ALTER TABLE `recomendacaoservico` DISABLE KEYS */;
/*!40000 ALTER TABLE `recomendacaoservico` ENABLE KEYS */;


--
-- Definition of table `servico`
--

DROP TABLE IF EXISTS `servico`;
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

--
-- Dumping data for table `servico`
--

/*!40000 ALTER TABLE `servico` DISABLE KEYS */;
INSERT INTO `servico` (`pk_servico`,`no_servico`,`dt_servico`,`ds_servico`,`no_imagem`,`st_servico`,`fl_automatico`,`fk_ambiente`,`fk_membro`) VALUES 
 (1,'Entidade','2009-11-22 16:04:41','Entidade','','A','N',2,2),
 (2,'Relacionamento','2009-11-22 16:04:56','Relacionamento','','A','N',2,2),
 (6,'aafff','2010-01-19 11:54:37','asdadddddd\n\n\naff\nopiopç\npópóp\noíop´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´íouhyouiuiuioyu.tgthyt   ry dtrytryuftryutyuityi','','A','N',2,2),
 (9,'ddddddd','2010-01-19 13:57:26','ddasdasda','','A','N',2,2),
 (10,'affff','2010-01-21 11:11:22','asdadablzmklnmnm,n,','','A','N',2,2),
 (11,'Testando link','2010-01-21 11:17:48','<center><b>Titulo</b></center>\n<p>\nTestando descrição\n</p>\n<img src=\"http://seriesedesenhos.com/br2/images/stories/Little%20Richard.jpg\" alt=\"\" />\n<a href=\"http://jqueryui.com/\" target=\"_blank\">jquery</a>','','A','N',2,2),
 (12,'teste','2010-01-21 11:18:30','afffffffff','','A','N',2,2),
 (13,'uiuhjk','2010-01-25 11:50:50','jhkhjk\n\n\ndasdsada','','A','N',2,2),
 (14,'kkk','2010-01-27 16:54:57','aaaa','','A','N',2,2),
 (15,'zzzz','2010-01-27 16:55:03','affff','','A','N',2,2),
 (16,'asdsa','2010-01-27 16:55:42','aaaa','','A','N',2,2),
 (17,'zzz','2010-01-27 16:56:09','aaaa','','A','N',2,2),
 (18,'aaa','2010-01-27 16:56:23','fffff','','A','N',2,2),
 (19,'zzzzz','2010-01-27 16:56:27','zafffff','','A','N',2,2),
 (20,'kkkkkkkkkkk','2010-01-27 16:57:02','adasd','','A','N',2,2),
 (21,'teste','2010-01-28 11:00:48','affff','','A','N',2,2),
 (22,'kkk','2010-01-28 11:58:21','afff','','A','N',2,2),
 (23,'adasd','2010-01-28 12:08:44','afff','','A','N',2,2),
 (24,'ttt','2010-01-28 12:08:51','aaaaaa','','A','N',2,2),
 (25,'aa','2010-01-28 13:29:37','fff','','A','N',2,2),
 (26,'uu','2010-01-28 13:29:43','aaa','','A','N',2,2),
 (27,'aa','2010-01-28 13:36:40','ff','','A','N',2,2),
 (28,'afff','2010-01-28 13:51:46','aaa','','A','N',2,2),
 (29,'zz','2010-01-28 13:51:52','aaa','','A','N',2,2),
 (30,'uuu','2010-01-28 13:51:57','aaa','','A','N',2,2),
 (31,',lçmlç,','2010-01-28 14:00:39','jbjj','','A','N',2,2),
 (32,'afff','2010-01-28 15:35:24','aaa','','A','N',2,2),
 (33,'az','2010-01-28 15:35:37','aff','','A','N',2,2),
 (34,'aff','2010-01-28 15:43:30','aaaa','','A','N',2,2),
 (35,'zzz','2010-01-28 15:43:51','aa','','A','N',2,2),
 (36,'akkk','2010-01-28 15:44:12','asa','','A','N',2,2),
 (37,'dsadsad','2010-02-01 15:35:24','asdsadasdas','','A','N',14,2),
 (38,'asasdad','2010-02-01 15:46:32',' mdsbfdfdsbfmxcxzczxc','','A','N',14,2);
/*!40000 ALTER TABLE `servico` ENABLE KEYS */;


--
-- Definition of table `tag`
--

DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
  `pk_tag` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `no_tag` varchar(60) NOT NULL,
  `ds_tag` text NOT NULL,
  PRIMARY KEY (`pk_tag`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tag`
--

/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
INSERT INTO `tag` (`pk_tag`,`no_tag`,`ds_tag`) VALUES 
 (1,'zzzzzzzz','zfffffffffff'),
 (2,'afffff','aaa'),
 (3,'Neuro','dsadsa'),
 (4,'kkkk','kkk'),
 (5,'Neuro','adhsaldjlsajdlsjad askljdsakljdklasj dklsadsad');
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;


--
-- Definition of table `tarefa`
--

DROP TABLE IF EXISTS `tarefa`;
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

--
-- Dumping data for table `tarefa`
--

/*!40000 ALTER TABLE `tarefa` DISABLE KEYS */;
INSERT INTO `tarefa` (`pk_tarefa`,`no_tarefa`,`ds_tarefa`,`dt_tarefa`,`fk_etapa`) VALUES 
 (1,'Entidade','O que é uma entidade.','2009-11-23 00:00:00',1),
 (2,'Relacionamento','O que é um relacionamento.','2009-11-23 00:00:00',1),
 (3,'wwww','asdasds','2010-02-28 00:00:00',2);
/*!40000 ALTER TABLE `tarefa` ENABLE KEYS */;


--
-- Definition of table `tipocontato`
--

DROP TABLE IF EXISTS `tipocontato`;
CREATE TABLE `tipocontato` (
  `pk_tipoContato` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ds_tipoContato` varchar(60) NOT NULL,
  PRIMARY KEY (`pk_tipoContato`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tipocontato`
--

/*!40000 ALTER TABLE `tipocontato` DISABLE KEYS */;
INSERT INTO `tipocontato` (`pk_tipoContato`,`ds_tipoContato`) VALUES 
 (1,'E-mail'),
 (2,'Telefone Residencial'),
 (3,'Telefone Comercial'),
 (4,'Site'),
 (5,'Celular');
/*!40000 ALTER TABLE `tipocontato` ENABLE KEYS */;


--
-- Definition of table `topico`
--

DROP TABLE IF EXISTS `topico`;
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

--
-- Dumping data for table `topico`
--

/*!40000 ALTER TABLE `topico` DISABLE KEYS */;
INSERT INTO `topico` (`pk_topico`,`ds_topico`,`dt_topico`,`fk_forum`,`fk_membro`) VALUES 
 (1,'Como se cria um der?','2009-11-22 16:05:35',2,2),
 (2,'teeste','2009-12-02 14:03:10',2,5),
 (3,'teste','2009-12-02 14:07:44',1,5),
 (4,'sdasdasdsad','2010-02-01 15:47:36',38,2);
/*!40000 ALTER TABLE `topico` ENABLE KEYS */;


--
-- Definition of table `uf`
--

DROP TABLE IF EXISTS `uf`;
CREATE TABLE `uf` (
  `pk_estado` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `no_estado` varchar(60) NOT NULL,
  `sg_estado` varchar(2) NOT NULL,
  PRIMARY KEY (`pk_estado`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `uf`
--

/*!40000 ALTER TABLE `uf` DISABLE KEYS */;
INSERT INTO `uf` (`pk_estado`,`no_estado`,`sg_estado`) VALUES 
 (1,'Rio de Janeiro','RJ'),
 (2,'São Paulo','SP'),
 (3,'Minas Gerais','MG'),
 (4,'Espírito Santo','ES'),
 (5,'Bahia','BA');
/*!40000 ALTER TABLE `uf` ENABLE KEYS */;


--
-- Definition of table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `usuario`
--

/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`pk_usuario`,`nu_cpf`,`no_usuario`,`no_apelido`,`dt_nasc`,`no_email`,`pw_senha`,`ds_perguntaChave`,`ds_respostaChave`,`tp_logradouro`,`no_logradouro`,`nu_logradouro`,`ds_complemento`,`nu_cep`,`st_usuario`,`img_usuario`,`fk_bairro`,`fk_instituicao`) VALUES 
 (1,11111111111,'Administrador',NULL,NULL,'adm@gmail.com','123456',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'A',NULL,NULL,NULL),
 (2,12212212212,'Baliu','baliu','1991-10-10','raxbaliu@gmail.com','123456','Qual é o meu nome?','Raimundo','Av','Vicente de Carvalho',140,'apartamento 501','22222-222','A','6b611dc5967f37e7d696b31b541afa1a.jpg',4,1),
 (3,13131313131,'Adriano',NULL,NULL,'adriano@estacio.br','123456',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'A',NULL,NULL,NULL),
 (4,14141414141,'Valéria',NULL,NULL,'valeria@estacio.br','123456',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'A',NULL,NULL,NULL),
 (5,15151515151,'Priscila',NULL,NULL,'priscila.tobias@gmail.com','123456',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'A',NULL,NULL,NULL),
 (6,12321312312,'Teste',NULL,NULL,'teste@teste.com','123456',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'A',NULL,NULL,NULL);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
