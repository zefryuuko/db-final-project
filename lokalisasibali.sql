-- MySQL dump 10.13  Distrib 5.7.28, for Linux (x86_64)
--
-- Host: localhost    Database: lokalisasibali
-- ------------------------------------------------------
-- Server version	5.7.28-0ubuntu0.16.04.2

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
-- Current Database: `lokalisasibali`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `lokalisasibali` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `lokalisasibali`;

--
-- Table structure for table `Auth`
--

DROP TABLE IF EXISTS `Auth`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Auth` (
  `staff_id` int(11) unsigned NOT NULL,
  `auth_password` varchar(255) NOT NULL,
  KEY `fk_auth_staff` (`staff_id`),
  CONSTRAINT `fk_auth_staff` FOREIGN KEY (`staff_id`) REFERENCES `Staff` (`staff_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Auth`
--

LOCK TABLES `Auth` WRITE;
/*!40000 ALTER TABLE `Auth` DISABLE KEYS */;
INSERT INTO `Auth` VALUES (1,'12345'),(2,'11111');
/*!40000 ALTER TABLE `Auth` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Item`
--

DROP TABLE IF EXISTS `Item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Item` (
  `item_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `item_name` varchar(100) NOT NULL,
  `item_vendor` varchar(100) NOT NULL,
  `item_type` int(11) unsigned NOT NULL,
  `item_stored` int(11) unsigned NOT NULL,
  `item_price` int(11) unsigned NOT NULL,
  `item_sellable` tinyint(1) NOT NULL,
  PRIMARY KEY (`item_id`),
  KEY `fk_item_itemtype` (`item_type`),
  CONSTRAINT `fk_item_itemtype` FOREIGN KEY (`item_type`) REFERENCES `ItemType` (`type_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Item`
--

LOCK TABLES `Item` WRITE;
/*!40000 ALTER TABLE `Item` DISABLE KEYS */;
INSERT INTO `Item` VALUES (1,'Obviously Coffee','-',1,305,20000,1);
/*!40000 ALTER TABLE `Item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ItemType`
--

DROP TABLE IF EXISTS `ItemType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ItemType` (
  `type_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `type_name` varchar(100) NOT NULL,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ItemType`
--

LOCK TABLES `ItemType` WRITE;
/*!40000 ALTER TABLE `ItemType` DISABLE KEYS */;
INSERT INTO `ItemType` VALUES (1,'Test type rename later'),(2,'electric'),(3,'Coffee Beans'),(4,'Coffee Beans');
/*!40000 ALTER TABLE `ItemType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Logistics`
--

DROP TABLE IF EXISTS `Logistics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Logistics` (
  `logistic_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `staff_id` int(11) unsigned DEFAULT NULL,
  `sales_id` int(11) unsigned NOT NULL,
  `cust_address` varchar(255) NOT NULL,
  `logistic_provider` int(11) unsigned NOT NULL,
  `tracking_number` int(11) unsigned NOT NULL,
  `date_sent` datetime NOT NULL,
  PRIMARY KEY (`logistic_id`),
  KEY `fk_logistics_staff` (`staff_id`),
  KEY `fk_logistics_sales` (`sales_id`),
  KEY `fk_logistics_logisticsprovider` (`logistic_provider`),
  CONSTRAINT `fk_logistics_logisticsprovider` FOREIGN KEY (`logistic_provider`) REFERENCES `LogisticsProvider` (`provider_id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_logistics_sales` FOREIGN KEY (`sales_id`) REFERENCES `Sales` (`sales_id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_logistics_staff` FOREIGN KEY (`staff_id`) REFERENCES `Staff` (`staff_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Logistics`
--

LOCK TABLES `Logistics` WRITE;
/*!40000 ALTER TABLE `Logistics` DISABLE KEYS */;
INSERT INTO `Logistics` VALUES (1,1,1,'God knows where',1,123456789,'2020-01-07 15:13:37'),(3,1,4,'Jl Griya Manis',1,12345678,'2020-01-08 04:44:01');
/*!40000 ALTER TABLE `Logistics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `LogisticsProvider`
--

DROP TABLE IF EXISTS `LogisticsProvider`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `LogisticsProvider` (
  `provider_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `provider_name` varchar(100) NOT NULL,
  PRIMARY KEY (`provider_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LogisticsProvider`
--

LOCK TABLES `LogisticsProvider` WRITE;
/*!40000 ALTER TABLE `LogisticsProvider` DISABLE KEYS */;
INSERT INTO `LogisticsProvider` VALUES (1,'JNE'),(2,'TIKI');
/*!40000 ALTER TABLE `LogisticsProvider` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PurchaseHistory`
--

DROP TABLE IF EXISTS `PurchaseHistory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PurchaseHistory` (
  `purchase_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `purchase_date` datetime NOT NULL,
  `staff_id` int(11) unsigned DEFAULT NULL,
  `item_id` int(11) unsigned DEFAULT NULL,
  `purchase_count` int(11) unsigned NOT NULL,
  `purchase_price_total` int(11) unsigned NOT NULL,
  PRIMARY KEY (`purchase_id`),
  KEY `fk_purchasehistory_staff` (`staff_id`),
  KEY `fk_purchasehistory_item` (`item_id`),
  CONSTRAINT `fk_purchasehistory_item` FOREIGN KEY (`item_id`) REFERENCES `Item` (`item_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_purchasehistory_staff` FOREIGN KEY (`staff_id`) REFERENCES `Staff` (`staff_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PurchaseHistory`
--

LOCK TABLES `PurchaseHistory` WRITE;
/*!40000 ALTER TABLE `PurchaseHistory` DISABLE KEYS */;
INSERT INTO `PurchaseHistory` VALUES (1,'2020-01-07 12:47:22',1,1,300,3000000),(2,'2020-01-07 14:25:10',1,1,5,100000),(3,'2020-01-07 14:27:22',1,1,5,100000),(4,'2020-01-07 15:53:44',1,NULL,1,1);
/*!40000 ALTER TABLE `PurchaseHistory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SalaryPaymentHistory`
--

DROP TABLE IF EXISTS `SalaryPaymentHistory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SalaryPaymentHistory` (
  `payment_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `staff_id` int(11) unsigned DEFAULT NULL,
  `payment_date` datetime NOT NULL,
  `payment_amount` int(11) unsigned NOT NULL,
  PRIMARY KEY (`payment_id`),
  KEY `fk_salarypaymenthistory` (`staff_id`),
  CONSTRAINT `fk_salarypaymenthistory` FOREIGN KEY (`staff_id`) REFERENCES `Staff` (`staff_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SalaryPaymentHistory`
--

LOCK TABLES `SalaryPaymentHistory` WRITE;
/*!40000 ALTER TABLE `SalaryPaymentHistory` DISABLE KEYS */;
INSERT INTO `SalaryPaymentHistory` VALUES (1,1,'2020-01-07 18:06:09',10000000),(2,5,'2020-01-08 00:39:00',5000000),(3,6,'2020-01-08 04:38:52',100000);
/*!40000 ALTER TABLE `SalaryPaymentHistory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Sales`
--

DROP TABLE IF EXISTS `Sales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Sales` (
  `sales_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `sales_datetime` datetime NOT NULL,
  `staff_id` int(11) unsigned DEFAULT NULL,
  `cust_name` varchar(100) NOT NULL,
  `sales_type` int(11) unsigned NOT NULL,
  PRIMARY KEY (`sales_id`),
  KEY `fk_sales_type` (`sales_type`),
  CONSTRAINT `fk_sales_type` FOREIGN KEY (`sales_type`) REFERENCES `SalesType` (`type_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Sales`
--

LOCK TABLES `Sales` WRITE;
/*!40000 ALTER TABLE `Sales` DISABLE KEYS */;
INSERT INTO `Sales` VALUES (1,'2020-01-07 14:13:24',1,'David',2),(2,'2020-01-07 22:49:17',2,'Test Customer',2),(3,'2020-01-08 04:40:04',1,'Livander',1),(4,'2020-01-08 04:43:07',1,'Ryo',2);
/*!40000 ALTER TABLE `Sales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SalesDetails`
--

DROP TABLE IF EXISTS `SalesDetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SalesDetails` (
  `detail_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `sales_id` int(11) unsigned NOT NULL,
  `item_id` int(11) unsigned DEFAULT NULL,
  PRIMARY KEY (`detail_id`),
  KEY `fk_salesdetails_item` (`item_id`),
  KEY `fk_salesdetails_sales` (`sales_id`),
  CONSTRAINT `fk_salesdetails_item` FOREIGN KEY (`item_id`) REFERENCES `Item` (`item_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_salesdetails_sales` FOREIGN KEY (`sales_id`) REFERENCES `Sales` (`sales_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SalesDetails`
--

LOCK TABLES `SalesDetails` WRITE;
/*!40000 ALTER TABLE `SalesDetails` DISABLE KEYS */;
INSERT INTO `SalesDetails` VALUES (1,1,1),(2,1,1),(3,2,1),(4,2,1),(5,2,1),(6,2,1),(7,3,1),(8,4,1);
/*!40000 ALTER TABLE `SalesDetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SalesType`
--

DROP TABLE IF EXISTS `SalesType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SalesType` (
  `type_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `type_name` varchar(100) NOT NULL,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SalesType`
--

LOCK TABLES `SalesType` WRITE;
/*!40000 ALTER TABLE `SalesType` DISABLE KEYS */;
INSERT INTO `SalesType` VALUES (1,'In-store'),(2,'Shipment');
/*!40000 ALTER TABLE `SalesType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Staff`
--

DROP TABLE IF EXISTS `Staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Staff` (
  `staff_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `staff_fname` varchar(100) NOT NULL,
  `staff_lname` varchar(100) NOT NULL,
  `staff_salary` int(11) NOT NULL,
  `staff_position_id` int(11) unsigned NOT NULL,
  `staff_status_id` int(11) unsigned NOT NULL,
  PRIMARY KEY (`staff_id`),
  KEY `fk_staff_staffposition` (`staff_position_id`),
  KEY `fk_staff_status` (`staff_status_id`),
  CONSTRAINT `fk_staff_staffposition` FOREIGN KEY (`staff_position_id`) REFERENCES `StaffPosition` (`position_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_staff_status` FOREIGN KEY (`staff_status_id`) REFERENCES `StaffStatus` (`status_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Staff`
--

LOCK TABLES `Staff` WRITE;
/*!40000 ALTER TABLE `Staff` DISABLE KEYS */;
INSERT INTO `Staff` VALUES (1,'David','Amadeo',10000000,1,1),(2,'Eris','Suryaputra',8000000,2,1),(5,'Ryo','Yenata',5000000,3,1),(6,'Livander','Surya',100000,4,1);
/*!40000 ALTER TABLE `Staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `StaffPosition`
--

DROP TABLE IF EXISTS `StaffPosition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `StaffPosition` (
  `position_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `position_name` varchar(100) NOT NULL,
  PRIMARY KEY (`position_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `StaffPosition`
--

LOCK TABLES `StaffPosition` WRITE;
/*!40000 ALTER TABLE `StaffPosition` DISABLE KEYS */;
INSERT INTO `StaffPosition` VALUES (1,'Operations'),(2,'Sales'),(3,'Cashier'),(4,'Barista'),(5,'Social Media'),(7,'Example');
/*!40000 ALTER TABLE `StaffPosition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `StaffStatus`
--

DROP TABLE IF EXISTS `StaffStatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `StaffStatus` (
  `status_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `status_name` varchar(100) NOT NULL,
  PRIMARY KEY (`status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `StaffStatus`
--

LOCK TABLES `StaffStatus` WRITE;
/*!40000 ALTER TABLE `StaffStatus` DISABLE KEYS */;
INSERT INTO `StaffStatus` VALUES (1,'Active'),(2,'Suspended'),(3,'Fired');
/*!40000 ALTER TABLE `StaffStatus` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-01-08  5:01:25
