-- MySQL dump 10.13  Distrib 5.7.23, for Linux (x86_64)
--
-- Host: localhost    Database: medispenser
-- ------------------------------------------------------
-- Server version	5.7.23-0ubuntu0.18.04.1

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
-- Table structure for table `cargas`
--

DROP TABLE IF EXISTS `cargas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cargas` (
  `idcargas` int(11) NOT NULL,
  `spd` int(11) NOT NULL,
  `medicamento` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  PRIMARY KEY (`idcargas`),
  KEY `spd_id_FK_idx` (`spd`),
  KEY `medicamento_id_idx` (`medicamento`),
  CONSTRAINT `medicamento_id_FK` FOREIGN KEY (`medicamento`) REFERENCES `medicamentos` (`idmedicamento`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `spd_id_FK` FOREIGN KEY (`spd`) REFERENCES `spds` (`idspds`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cuidadores`
--

DROP TABLE IF EXISTS `cuidadores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cuidadores` (
  `idcuidadores` int(11) NOT NULL AUTO_INCREMENT,
  `persona` int(11) DEFAULT NULL,
  PRIMARY KEY (`idcuidadores`),
  KEY `persona_id_idx` (`persona`),
  CONSTRAINT `persona_id` FOREIGN KEY (`persona`) REFERENCES `personas` (`idpersonas`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dependientes`
--

DROP TABLE IF EXISTS `dependientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dependientes` (
  `iddependientes` int(11) NOT NULL AUTO_INCREMENT,
  `alias` varchar(45) NOT NULL,
  `persona` int(11) NOT NULL,
  `cuidador` int(11) NOT NULL,
  PRIMARY KEY (`iddependientes`),
  KEY `persona_id_idx` (`persona`),
  KEY `cuidador_id_idx` (`cuidador`),
  CONSTRAINT `cuidador_id` FOREIGN KEY (`cuidador`) REFERENCES `cuidadores` (`idcuidadores`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `persona_id_FK` FOREIGN KEY (`persona`) REFERENCES `personas` (`idpersonas`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dosis`
--

DROP TABLE IF EXISTS `dosis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dosis` (
  `iddosis` int(11) NOT NULL AUTO_INCREMENT,
  `hora_inicio` time NOT NULL,
  `veces_dia` int(11) NOT NULL,
  `medicamento` int(11) NOT NULL,
  `tratamiento` int(11) NOT NULL,
  PRIMARY KEY (`iddosis`),
  KEY `medicamento_id_idx` (`medicamento`),
  KEY `tratamiento_id_idx` (`tratamiento`),
  CONSTRAINT `medicamento_id` FOREIGN KEY (`medicamento`) REFERENCES `medicamentos` (`idmedicamento`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tratamiento_id` FOREIGN KEY (`tratamiento`) REFERENCES `tratamientos` (`idtratamientos`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `medicamentos`
--

DROP TABLE IF EXISTS `medicamentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medicamentos` (
  `idmedicamento` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`idmedicamento`),
  UNIQUE KEY `nombre_UNIQUE` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `perfiles`
--

DROP TABLE IF EXISTS `perfiles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `perfiles` (
  `idperfiles` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) CHARACTER SET latin1 NOT NULL,
  `descripcion` varchar(45) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`idperfiles`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `personas`
--

DROP TABLE IF EXISTS `personas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personas` (
  `idpersonas` int(11) NOT NULL AUTO_INCREMENT,
  `dni` varchar(9) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `apellidos` varchar(45) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `personascol` varchar(45) NOT NULL,
  PRIMARY KEY (`idpersonas`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `spds`
--

DROP TABLE IF EXISTS `spds`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `spds` (
  `idspds` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`idspds`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tratamientos`
--

DROP TABLE IF EXISTS `tratamientos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tratamientos` (
  `idtratamientos` int(11) NOT NULL AUTO_INCREMENT,
  `dependiente` int(11) NOT NULL,
  `spd` int(11) NOT NULL,
  PRIMARY KEY (`idtratamientos`),
  KEY `spd_id_idx` (`spd`),
  KEY `dependientes_id_idx` (`dependiente`),
  CONSTRAINT `dependientes_id` FOREIGN KEY (`dependiente`) REFERENCES `dependientes` (`iddependientes`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `spd_id` FOREIGN KEY (`spd`) REFERENCES `spds` (`idspds`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `idusuario` int(11) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `cuidador_id` int(11) NOT NULL,
  `E_perfil` int(11) NOT NULL,
  PRIMARY KEY (`idusuario`),
  KEY `cuidador_id_idx` (`cuidador_id`),
  KEY `e_perfil_id_idx` (`E_perfil`),
  CONSTRAINT `cuidador_id_FK` FOREIGN KEY (`cuidador_id`) REFERENCES `cuidadores` (`idcuidadores`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `e_perfil_id` FOREIGN KEY (`E_perfil`) REFERENCES `perfiles` (`idperfiles`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-09-07 14:16:15
