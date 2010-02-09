DROP TABLE IF EXISTS `baliu02`.`ae_aplicacao`;
CREATE TABLE  `baliu02`.`ae_aplicacao` (
  `id_aplicacao` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `id_processamento` int(10) unsigned DEFAULT NULL,
  `id_autenticacao` int(10) unsigned DEFAULT NULL,
  `data_cadastro` datetime DEFAULT NULL,
  PRIMARY KEY (`id_aplicacao`),
  KEY `FK_ae_aplicacao_1` (`id_processamento`),
  KEY `FK_ae_aplicacao_2` (`id_autenticacao`),
  CONSTRAINT `FK_ae_aplicacao_2` FOREIGN KEY (`id_autenticacao`) REFERENCES `ae_autenticacao` (`id_autenticacao`),
  CONSTRAINT `FK_ae_aplicacao_1` FOREIGN KEY (`id_processamento`) REFERENCES `ae_processamento` (`id_processamento`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `baliu02`.`ae_autenticacao`;
CREATE TABLE  `baliu02`.`ae_autenticacao` (
  `id_autenticacao` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `descricao` varchar(200) NOT NULL,
  PRIMARY KEY (`id_autenticacao`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `baliu02`.`ae_parametro`;
CREATE TABLE  `baliu02`.`ae_parametro` (
  `id_parametro` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_recurso` int(10) unsigned NOT NULL,
  `id_aplicacao` int(10) unsigned NOT NULL,
  `nome` varchar(100) DEFAULT NULL,
  `path` varchar(200) DEFAULT NULL,
  `style` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_parametro`),
  KEY `FK_ae_parametro_1` (`id_recurso`),
  KEY `FK_ae_parametro_2` (`id_aplicacao`),
  CONSTRAINT `FK_ae_parametro_2` FOREIGN KEY (`id_aplicacao`) REFERENCES `ae_aplicacao` (`id_aplicacao`),
  CONSTRAINT `FK_ae_parametro_1` FOREIGN KEY (`id_recurso`) REFERENCES `ae_recurso` (`id_recurso`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `baliu02`.`ae_processamento`;
CREATE TABLE  `baliu02`.`ae_processamento` (
  `id_processamento` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `descricao` varchar(200) NOT NULL,
  PRIMARY KEY (`id_processamento`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `baliu02`.`ae_recurso`;
CREATE TABLE  `baliu02`.`ae_recurso` (
  `id_recurso` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_aplicacao` int(10) unsigned NOT NULL,
  `nome` varchar(50) DEFAULT NULL,
  `base` varchar(200) DEFAULT NULL,
  `path` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id_recurso`),
  KEY `FK_ae_recurso_1` (`id_aplicacao`),
  CONSTRAINT `FK_ae_recurso_1` FOREIGN KEY (`id_aplicacao`) REFERENCES `ae_aplicacao` (`id_aplicacao`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `baliu02`.`ae_usuario_aplicacao`;
CREATE TABLE  `baliu02`.`ae_usuario_aplicacao` (
  `pk_usuario` int(10) unsigned NOT NULL,
  `id_aplicacao` int(10) unsigned NOT NULL,
  PRIMARY KEY (`pk_usuario`,`id_aplicacao`),
  KEY `FK_ae_usuario_aplicacao_2` (`id_aplicacao`),
  CONSTRAINT `FK_ae_usuario_aplicacao_2` FOREIGN KEY (`id_aplicacao`) REFERENCES `ae_aplicacao` (`id_aplicacao`),
  CONSTRAINT `FK_ae_usuario_aplicacao_1` FOREIGN KEY (`pk_usuario`) REFERENCES `usuario` (`pk_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `baliu02`.`ae_usuario_autenticacao`;
CREATE TABLE  `baliu02`.`ae_usuario_autenticacao` (
  `openid_url` varchar(255) DEFAULT NULL,
  `pk_usuario` int(10) unsigned NOT NULL,
  `login_relembrar` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`pk_usuario`) USING BTREE,
  CONSTRAINT `FK_ae_usuario_autenticacao_1` FOREIGN KEY (`pk_usuario`) REFERENCES `usuario` (`pk_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;