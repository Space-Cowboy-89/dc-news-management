-- ******************************************************
-- 1. CREAZIONE DATABASE
-- ******************************************************
CREATE DATABASE IF NOT EXISTS `db_news` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `db_news`;

-- ******************************************************
-- 2. CREAZIONE TABELLE SENZA CHIAVI ESTERNE DIPENDENTI
-- (Tabelle fondamentali e genitori)
-- ******************************************************

-- db_news.employee definition
CREATE TABLE `employee` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `employee_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_at` datetime DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  `name` varchar(20) NOT NULL,
  `surname` varchar(20) NOT NULL,
  `email` varchar(20) NOT NULL,
  `password` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `employee_unique` (`employee_code`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- db_news.image definition
CREATE TABLE `image` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `content` mediumblob NOT NULL,
  `desc` text,
  `image_code` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_at` datetime DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `image_unique` (`image_code`),
  UNIQUE KEY `image_unique_1` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- db_news.prize_article definition
CREATE TABLE `prize_article` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `desc` text NOT NULL,
  `prize_article_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_at` datetime DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `prize_article_unique` (`name`),
  UNIQUE KEY `prize_article_unique_1` (`prize_article_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- db_news.`user` definition
CREATE TABLE `user` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `user_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_at` datetime DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  `name` varchar(20) NOT NULL,
  `surname` varchar(20) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `email` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_unique` (`user_code`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ** ATTENZIONE: La tabella `category` deve essere creata ora **
-- db_news.category definition
CREATE TABLE `category` (
  `name` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `category_code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_at` datetime DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  `id` bigint unsigned NOT NULL AUTO_INCREMENT, -- CORREZIONE 1: Aggiunto AUTO_INCREMENT
  `category_id` bigint unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `news_category_unique` (`category_code`),
  KEY `category_category_id_IDX` (`category_id`) USING BTREE,
  -- Questa FK è un'auto-referenza (categoria padre)
  CONSTRAINT `category_category_FK` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ** ATTENZIONE: La tabella `tag` deve essere creata ora **
-- db_news.tag definition
CREATE TABLE `tag` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `tag_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_at` datetime DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  `category_id` bigint unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `topic_unique` (`name`),
  UNIQUE KEY `topic_unique_1` (`tag_code`),
  KEY `topic_category_FK` (`category_id`),
  CONSTRAINT `topic_category_FK` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `tag_check` CHECK ((`category_id` > 0)) -- NOTA: Il CHECK su FK non è strettamente necessario se la colonna è NOT NULL.
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- ******************************************************
-- 3. CREAZIONE TABELLE FIGLIE
-- (Tabelle che referenziano le tabelle fondamentali)
-- ******************************************************

-- db_news.user_tag definition (Ora tag esiste)
CREATE TABLE `user_tag` (
  `user_id` bigint unsigned NOT NULL,
  `tag_id` bigint unsigned NOT NULL,
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `user_tag_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `modified_at` datetime DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_tag_unique` (`user_id`,`tag_id`),
  UNIQUE KEY `user_tag_unique_1` (`user_tag_code`),
  CONSTRAINT `user_tag_user_FK` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `user_tag_tag_FK` FOREIGN KEY (`tag_id`) REFERENCES `tag` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- db_news.contest definition
CREATE TABLE `contest` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `desc` text NOT NULL,
  `contest_code` varchar(20) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_at` datetime DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  `image_id` bigint unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `contest_unique` (`contest_code`),
  KEY `contest_image_id_IDX` (`image_id`) USING BTREE,
  CONSTRAINT `contest_image_FK` FOREIGN KEY (`image_id`) REFERENCES `image` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- db_news.image_category definition
CREATE TABLE `image_category` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `image_id` bigint unsigned NOT NULL,
  `category_id` bigint unsigned NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_at` datetime DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `image_category_category_id_IDX` (`category_id`) USING BTREE,
  KEY `image_category_image_id_IDX` (`image_id`) USING BTREE,
  CONSTRAINT `image_category_category_FK` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `image_category_image_FK` FOREIGN KEY (`image_id`) REFERENCES `image` (`id