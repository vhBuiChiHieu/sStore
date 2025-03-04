-- MySQL dump 10.13  Distrib 8.0.41, for Linux (x86_64)
--
-- Host: localhost    Database: gredirect2
-- ------------------------------------------------------
-- Server version	8.0.41-0ubuntu0.24.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--
DROP DATABASE IF EXISTS gredirect2;

CREATE DATABASE gredirect2;

USE gredirect2;

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` bigint DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` bigint DEFAULT NULL,
  `hash_password` varchar(255) DEFAULT NULL,
  `mail` varchar(100) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `status` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_account_mail` (`mail`),
  CONSTRAINT `account_chk_1` CHECK ((`status` between 0 and 2))
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'2025-02-28 10:00:00.000000',NULL,'2025-02-28 10:00:00.000000',NULL,'$2a$10$nKt9xZbVo1IXhaAqStC7juImMAyw4j.flpPAGWhK8Z9Zpeg7opDXK','admin@admin.com','0325978045',0),(2,'2025-02-28 10:00:00.000000',NULL,'2025-02-28 10:00:00.000000',NULL,'$2a$10$w7USNBRLKpcukgoSdGh9OOf6MO9VK7pb1dw6OM3cDD7Y0JBx4NpYG','user2@example.com',NULL,0),(3,'2025-02-28 10:00:00.000000',NULL,'2025-03-03 19:09:08.137096',1,'$2a$10$w7USNBRLKpcukgoSdGh9OOf6MO9VK7pb1dw6OM3cDD7Y0JBx4NpYG','user3@example.com',NULL,0),(4,'2025-02-28 10:00:00.000000',NULL,'2025-03-03 19:09:10.497827',1,'$2a$10$w7USNBRLKpcukgoSdGh9OOf6MO9VK7pb1dw6OM3cDD7Y0JBx4NpYG','user4@example.com',NULL,0),(5,'2025-02-28 10:00:00.000000',NULL,'2025-03-03 13:04:01.107561',1,'$2a$10$w7USNBRLKpcukgoSdGh9OOf6MO9VK7pb1dw6OM3cDD7Y0JBx4NpYG','user5@example.com',NULL,0),(8,'2025-03-03 02:28:40.152405',NULL,'2025-03-03 16:01:24.567383',1,'$2a$10$QZaSzfUcL0uCPbaYUZZGseg8EhAC3iXs6raNzDfqoXDcJOuojZ4WC','test@gmail.com',NULL,2),(9,'2025-03-03 09:58:01.373018',1,'2025-03-03 16:01:30.595081',1,'$2a$10$hcZMlxS9LolaKoqtoiI9Y.I1gHUPuz4XTBiONtFW/ecxoBiGsojdO','bmngoc2006@gmail.com',NULL,0);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account_has_role`
--

DROP TABLE IF EXISTS `account_has_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account_has_role` (
  `account_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  KEY `FK7gd2lkfww7ig3p2xp005xr1t9` (`role_id`),
  KEY `FKtlr2y26fej3r0fy7h8f5x777h` (`account_id`),
  CONSTRAINT `FK7gd2lkfww7ig3p2xp005xr1t9` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FKtlr2y26fej3r0fy7h8f5x777h` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_has_role`
--

LOCK TABLES `account_has_role` WRITE;
/*!40000 ALTER TABLE `account_has_role` DISABLE KEYS */;
INSERT INTO `account_has_role` VALUES (1,2),(2,1),(3,1),(4,1),(5,1),(8,1),(9,1);
/*!40000 ALTER TABLE `account_has_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `brand`
--

DROP TABLE IF EXISTS `brand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `brand` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` bigint DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` bigint DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `logo_url` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brand`
--

LOCK TABLES `brand` WRITE;
/*!40000 ALTER TABLE `brand` DISABLE KEYS */;
INSERT INTO `brand` VALUES (1,'2025-02-28 10:00:00.000000',1,'2025-02-28 10:00:00.000000',NULL,'Thương hiệu đồ thể thao',NULL,'Nike');
/*!40000 ALTER TABLE `brand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` bigint DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` bigint DEFAULT NULL,
  `account_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKoem6haiy2c42obpy8m5ye4brj` (`account_id`),
  CONSTRAINT `FK4pljlvncf45mr98etwpubxvbt` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (1,'2025-02-28 10:00:00.000000',1,'2025-02-28 10:00:00.000000',NULL,1),(2,'2025-02-28 10:00:00.000000',2,'2025-02-28 10:00:00.000000',NULL,2),(3,'2025-02-28 10:00:00.000000',3,'2025-02-28 10:00:00.000000',NULL,3),(4,'2025-02-28 10:00:00.000000',4,'2025-02-28 10:00:00.000000',NULL,4),(5,'2025-02-28 10:00:00.000000',5,'2025-02-28 10:00:00.000000',NULL,5);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart_item`
--

DROP TABLE IF EXISTS `cart_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_item` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` bigint DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` bigint DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `cart_id` bigint DEFAULT NULL,
  `product_variant_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1uobyhgl1wvgt1jpccia8xxs3` (`cart_id`),
  KEY `FKrdaiqcfk3wbovl9ctfiu8bnx2` (`product_variant_id`),
  CONSTRAINT `FK1uobyhgl1wvgt1jpccia8xxs3` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`),
  CONSTRAINT `FKrdaiqcfk3wbovl9ctfiu8bnx2` FOREIGN KEY (`product_variant_id`) REFERENCES `product_variant` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_item`
--

LOCK TABLES `cart_item` WRITE;
/*!40000 ALTER TABLE `cart_item` DISABLE KEYS */;
INSERT INTO `cart_item` VALUES (1,'2025-02-28 10:00:00.000000',1,'2025-02-28 10:00:00.000000',NULL,2,1,1),(2,'2025-02-28 10:00:00.000000',2,'2025-02-28 10:00:00.000000',NULL,1,2,2),(3,'2025-02-28 10:00:00.000000',3,'2025-02-28 10:00:00.000000',NULL,3,3,1),(4,'2025-02-28 10:00:00.000000',4,'2025-02-28 10:00:00.000000',NULL,1,4,2),(5,'2025-02-28 10:00:00.000000',5,'2025-02-28 10:00:00.000000',NULL,2,5,1),(7,NULL,NULL,NULL,NULL,10,1,2);
/*!40000 ALTER TABLE `cart_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` bigint DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` bigint DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `slug` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_category_slug` (`slug`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'2025-02-28 10:00:00.000000',1,'2025-02-28 10:00:00.000000',NULL,'Áo chuyên dụng cho việc vận động, thoải mái thoáng mát','Giày Thể Thao','giay-the-thao'),(2,'2025-03-03 14:51:12.336541',1,'2025-03-03 14:51:12.336552',NULL,'Giày chuyên dụng cho việc vận động, thoải mái thoáng mát','Giày Thể Thao Test','giay-the-thao-test'),(3,'2025-03-03 14:52:09.084150',1,'2025-03-03 14:52:09.084158',NULL,'Áo thun nam chất liệu cotton thoáng mát, phù hợp cho mùa hè','Áo Thun Nam','ao-thun-nam'),(4,'2025-03-03 14:52:14.573267',1,'2025-03-03 14:52:14.573273',NULL,'Quần jeans cao cấp, bền đẹp với nhiều kiểu dáng thời trang','Quần Jeans','quan-jeans'),(5,'2025-03-03 14:52:20.466782',1,'2025-03-03 14:52:20.466788',NULL,'Các loại phụ kiện thời trang như túi xách, dây lưng, kính mát','Phụ Kiện Thời Trang','phu-kien-thoi-trang'),(6,'2025-03-03 14:52:26.750616',1,'2025-03-03 14:52:26.750622',NULL,'Trang phục và phụ kiện dành cho các hoạt động thể thao','Đồ Thể Thao','o-the-thao'),(7,'2025-03-03 14:52:32.516127',1,'2025-03-03 14:52:32.516132',NULL,'Trang phục lịch sự, sang trọng phù hợp cho môi trường công sở','Đồ Công Sở','o-cong-so');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_item`
--

DROP TABLE IF EXISTS `order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_item` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` bigint DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` bigint DEFAULT NULL,
  `price` double NOT NULL,
  `quantity` int NOT NULL,
  `order_id` bigint DEFAULT NULL,
  `product_variant_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKt4dc2r9nbvbujrljv3e23iibt` (`order_id`),
  KEY `FKasbjwtdare2wb3anogb1oai26` (`product_variant_id`),
  CONSTRAINT `FKasbjwtdare2wb3anogb1oai26` FOREIGN KEY (`product_variant_id`) REFERENCES `product_variant` (`id`),
  CONSTRAINT `FKt4dc2r9nbvbujrljv3e23iibt` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_item`
--

LOCK TABLES `order_item` WRITE;
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` bigint DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` bigint DEFAULT NULL,
  `payment_method` varchar(255) DEFAULT NULL,
  `shipping_address` varchar(255) DEFAULT NULL,
  `status` enum('CANCELLED','DELIVERED','PAID','PENDING','SHIPPED') DEFAULT NULL,
  `total` double NOT NULL,
  `account_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3c7gbsfawn58r27cf5b2km72f` (`account_id`),
  CONSTRAINT `FK3c7gbsfawn58r27cf5b2km72f` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permission` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` bigint DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` bigint DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` enum('ACCOUNT_CREATE','ACCOUNT_DELETE','ACCOUNT_READ','ACCOUNT_READ_OWN','ACCOUNT_UPDATE','ACCOUNT_UPDATE_OWN') DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_permission_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` VALUES (1,'2025-03-01 17:12:54.586844',NULL,'2025-03-01 17:12:54.586844',NULL,'Read all account information','ACCOUNT_READ'),(2,'2025-03-01 17:12:54.586844',NULL,'2025-03-01 17:12:54.586844',NULL,'Read own account information','ACCOUNT_READ_OWN'),(3,'2025-03-01 17:12:54.586844',NULL,'2025-03-01 17:12:54.586844',NULL,'Delete accounts','ACCOUNT_DELETE'),(4,'2025-03-01 17:12:54.586844',NULL,'2025-03-01 17:12:54.586844',NULL,'Update all account information','ACCOUNT_UPDATE'),(5,'2025-03-01 17:12:54.586844',NULL,'2025-03-01 17:12:54.586844',NULL,'Update own account information','ACCOUNT_UPDATE_OWN'),(6,'2025-03-01 17:12:54.586844',NULL,'2025-03-01 17:12:54.586844',NULL,'Create new accounts','ACCOUNT_CREATE');
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` bigint DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` bigint DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `brand_id` bigint DEFAULT NULL,
  `category_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKs6cydsualtsrprvlf2bb3lcam` (`brand_id`),
  KEY `FK1mtsbur82frn64de7balymq9s` (`category_id`),
  CONSTRAINT `FK1mtsbur82frn64de7balymq9s` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `FKs6cydsualtsrprvlf2bb3lcam` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'2025-02-28 10:00:00.000000',1,'2025-02-28 10:00:00.000000',NULL,'Giày chạy bộ','Air Max',1,1);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_image`
--

DROP TABLE IF EXISTS `product_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_image` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` bigint DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` bigint DEFAULT NULL,
  `image_url` varchar(255) NOT NULL,
  `is_primary` bit(1) NOT NULL,
  `product_variant_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1hvhgw23qikjmnwflw36japbi` (`product_variant_id`),
  CONSTRAINT `FK1hvhgw23qikjmnwflw36japbi` FOREIGN KEY (`product_variant_id`) REFERENCES `product_variant` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_image`
--

LOCK TABLES `product_image` WRITE;
/*!40000 ALTER TABLE `product_image` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_variant`
--

DROP TABLE IF EXISTS `product_variant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_variant` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` bigint DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` bigint DEFAULT NULL,
  `color` varchar(255) NOT NULL,
  `price` double NOT NULL,
  `size` varchar(255) NOT NULL,
  `sku` varchar(255) DEFAULT NULL,
  `stock` int DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_product_variant_sku` (`sku`),
  KEY `FKgrbbs9t374m9gg43l6tq1xwdj` (`product_id`),
  CONSTRAINT `FKgrbbs9t374m9gg43l6tq1xwdj` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_variant`
--

LOCK TABLES `product_variant` WRITE;
/*!40000 ALTER TABLE `product_variant` DISABLE KEYS */;
INSERT INTO `product_variant` VALUES (1,'2025-02-28 10:00:00.000000',2,'2025-02-28 10:00:00.000000',NULL,'Red',100,'42','AIRMAX-RED-42',10,1),(2,'2025-02-28 10:00:00.000000',2,'2025-02-28 10:00:00.000000',NULL,'Blue',105,'43','AIRMAX-BLUE-43',5,1);
/*!40000 ALTER TABLE `product_variant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` bigint DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` bigint DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` enum('ADMIN','GUEST','TEST','USER') DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_role_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'2025-03-01 17:12:54.569609',NULL,'2025-03-01 17:12:54.569609',NULL,'Standard user role with basic privileges','USER'),(2,'2025-03-01 17:12:54.569609',NULL,'2025-03-01 17:12:54.569609',NULL,'Administrator role with full privileges','ADMIN'),(3,'2025-03-01 17:12:54.569609',NULL,'2025-03-01 17:12:54.569609',NULL,'Guest role with minimal privileges','GUEST');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_has_permission`
--

DROP TABLE IF EXISTS `role_has_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_has_permission` (
  `role_id` bigint NOT NULL,
  `permission_id` bigint NOT NULL,
  KEY `FK2h8xukv5c6o207f1iyj555146` (`permission_id`),
  KEY `FKc616yaiie179glys9ee1gwsod` (`role_id`),
  CONSTRAINT `FK2h8xukv5c6o207f1iyj555146` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`),
  CONSTRAINT `FKc616yaiie179glys9ee1gwsod` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_has_permission`
--

LOCK TABLES `role_has_permission` WRITE;
/*!40000 ALTER TABLE `role_has_permission` DISABLE KEYS */;
INSERT INTO `role_has_permission` VALUES (1,2),(1,5),(2,6),(2,3),(2,1),(2,2),(2,4),(2,5);
/*!40000 ALTER TABLE `role_has_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_info` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` bigint DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` bigint DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `account_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK7qdnfx0rdys6j766rxiqfcadf` (`account_id`),
  CONSTRAINT `FK4bvlx564gr3ii8jw1jb0fu9jh` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_info`
--

LOCK TABLES `user_info` WRITE;
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
INSERT INTO `user_info` VALUES (1,'2025-02-28 10:00:00.000000',NULL,'2025-02-28 10:00:00.000000',NULL,'3d650e19-cc32-412f-97e2-c0e1c5d15d99_avatar.jpeg','1990-01-01',NULL,'Bui','Chi Hieu',1),(2,'2025-02-28 10:00:00.000000',NULL,'2025-02-28 10:00:00.000000',NULL,NULL,'1990-01-01',NULL,'Bob','Johnson',2),(3,'2025-02-28 10:00:00.000000',NULL,'2025-02-28 10:00:00.000000',NULL,NULL,'1990-01-01',NULL,'Charlie','Brown',3),(4,'2025-02-28 10:00:00.000000',NULL,'2025-02-28 10:00:00.000000',NULL,NULL,'1990-01-01',NULL,'David','Williams',4),(5,'2025-02-28 10:00:00.000000',NULL,'2025-02-28 10:00:00.000000',NULL,NULL,'1990-01-01',NULL,'Eve','Davis',5),(7,'2025-03-03 02:28:40.179742',NULL,'2025-03-03 02:28:40.179745',NULL,NULL,'2002-05-08',NULL,'Bui','Chi Hieu',8);
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'gredirect2'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-04  1:32:28
