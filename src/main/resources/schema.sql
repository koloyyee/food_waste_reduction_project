CREATE DATABASE IF NOT EXISTS FWRP;

-- FWRP.item definition

CREATE TABLE `item` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` text,
  `expiry_date` date NOT NULL,
  `price` decimal(10,2) NOT NULL DEFAULT '0.00',
  `discount_rate` float(10,1) DEFAULT '0.0',
  `is_surplus` tinyint(1) DEFAULT '0',
  `is_donation` tinyint(1) DEFAULT '0',
  `quantity` int DEFAULT '0',
  `is_available` tinyint(1) NOT NULL DEFAULT '1',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- FWRP.`user` definition

CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` char(13) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL COMMENT 'Retailer, CharitableOrg, Consumer',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `comm_method` smallint DEFAULT '1' COMMENT '1 - email, 2 - phone, 3 - email and phone',
  `location` varchar(100) DEFAULT NULL COMMENT 'city name',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- FWRP.notification definition

CREATE TABLE `notification` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `item_id` int DEFAULT NULL,
  `comm_method` tinyint(1) DEFAULT NULL COMMENT '1 - email, 2 - phone, 3 - email & phone',
  `sent_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `message` text NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_notif_user_id` (`user_id`),
  KEY `fk_notif_item_id` (`item_id`),
  CONSTRAINT `fk_notif_item_id` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`),
  CONSTRAINT `fk_notif_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- FWRP.`order` definition

CREATE TABLE `order` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `item_id` int DEFAULT NULL,
  `transaction_type` tinyint(1) NOT NULL COMMENT '1 - purchased, 2 - donated',
  `item_price` decimal(10,2) NOT NULL DEFAULT '0.00',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `quantity` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_orders_user_id` (`user_id`),
  KEY `fk_orders_item_id` (`item_id`),
  CONSTRAINT `fk_orders_item_id` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`),
  CONSTRAINT `fk_orders_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- FWRP.subscription definition

CREATE TABLE `subscription` (
  `user_id` int NOT NULL,
  `item_id` int NOT NULL,
  `subscription_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`,`item_id`),
  KEY `item_id` (`item_id`),
  CONSTRAINT `subscription_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `subscription_ibfk_2` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;