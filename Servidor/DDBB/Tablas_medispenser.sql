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
-- Table structure for table `CARGAS`
--

DROP TABLE IF EXISTS `CARGAS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CARGAS` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ID_SPD` int(11) NOT NULL,
  `ID_MEDICAMENTO` int(11) NOT NULL,
  `CANTIDAD` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `spd_id_FK_idx` (`ID_SPD`),
  KEY `medicamento_id_idx` (`ID_MEDICAMENTO`),
  CONSTRAINT `medicamento_id_FK` FOREIGN KEY (`ID_MEDICAMENTO`) REFERENCES `MEDICAMENTOS` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `spd_id_FK` FOREIGN KEY (`ID_SPD`) REFERENCES `SPDS` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `CUIDADORES`
--

DROP TABLE IF EXISTS `CUIDADORES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CUIDADORES` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ID_PERSONA` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `persona_id_idx` (`ID_PERSONA`),
  CONSTRAINT `persona_id` FOREIGN KEY (`ID_PERSONA`) REFERENCES `PERSONAS` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `DEPENDIENTES`
--

DROP TABLE IF EXISTS `DEPENDIENTES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DEPENDIENTES` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ALIAS` varchar(45) NOT NULL,
  `ID_PERSONA` int(11) NOT NULL,
  `ID_CUIDADOR` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ALIAS_UNIQUE` (`ALIAS`),
  KEY `persona_id_idx` (`ID_PERSONA`),
  KEY `cuidador_id_idx` (`ID_CUIDADOR`),
  CONSTRAINT `cuidador_id` FOREIGN KEY (`ID_CUIDADOR`) REFERENCES `CUIDADORES` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `persona_id_FK` FOREIGN KEY (`ID_PERSONA`) REFERENCES `PERSONAS` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `DOSIS`
--

DROP TABLE IF EXISTS `DOSIS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DOSIS` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `HORA_INICIO` time NOT NULL,
  `VECES_POR_DIA` int(11) NOT NULL,
  `ID_MEDICAMENTO` int(11) NOT NULL,
  `ID_TRATAMIENTO` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `medicamento_id_idx` (`ID_MEDICAMENTO`),
  KEY `tratamiento_id_idx` (`ID_TRATAMIENTO`),
  CONSTRAINT `medicamento_id` FOREIGN KEY (`ID_MEDICAMENTO`) REFERENCES `MEDICAMENTOS` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tratamiento_id` FOREIGN KEY (`ID_TRATAMIENTO`) REFERENCES `TRATAMIENTOS` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `MEDICAMENTOS`
--

DROP TABLE IF EXISTS `MEDICAMENTOS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MEDICAMENTOS` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `nombre_UNIQUE` (`NOMBRE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `PERFILES`
--

DROP TABLE IF EXISTS `PERFILES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PERFILES` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(45) CHARACTER SET latin1 NOT NULL,
  `DESCRIPCION` varchar(255) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `PERSONAS`
--

DROP TABLE IF EXISTS `PERSONAS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PERSONAS` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DNI` CHAR(9) NOT NULL,
  `NOMBRE` varchar(45) NOT NULL,
  `APELLIDOS` varchar(45) NOT NULL,
  `FECHA_NACIMIENTO` date NOT NULL,
  UNIQUE KEY `DNI_UNIQUE` (`DNI`),
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `SPDS`
--

DROP TABLE IF EXISTS `SPDS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SPDS` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `TRATAMIENTOS`
--

DROP TABLE IF EXISTS `TRATAMIENTOS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TRATAMIENTOS` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ID_DEPENDIENTE` int(11) NOT NULL,
  `ID_SPD` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `spd_id_idx` (`ID_SPD`),
  KEY `dependientes_id_idx` (`ID_DEPENDIENTE`),
  CONSTRAINT `dependientes_id` FOREIGN KEY (`ID_DEPENDIENTE`) REFERENCES `DEPENDIENTES` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `spd_id` FOREIGN KEY (`ID_SPD`) REFERENCES `SPDS` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `USUARIOS`
--

DROP TABLE IF EXISTS `USUARIOS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USUARIOS` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(45) NOT NULL,
  `PASSWORD` varchar(45) NOT NULL,
  `ID_CUIDADOR` int(11) NOT NULL,
  `ID_PERFIL` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `cuidador_id_idx` (`ID_CUIDADOR`),
  KEY `e_perfil_id_idx` (`ID_PERFIL`),
  CONSTRAINT `cuidador_id_FK` FOREIGN KEY (`ID_CUIDADOR`) REFERENCES `CUIDADORES` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `e_perfil_id` FOREIGN KEY (`ID_PERFIL`) REFERENCES `PERFILES` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
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
