-- FWRP.item definition
CREATE DATABASE IF NOT EXISTS FWRP;
USE FWRP;
CREATE TABLE IF NOT EXISTS `item` (
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
  `update_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- FWRP.`user` definition

CREATE TABLE IF NOT EXISTS  `user` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `name` varchar(100) NOT NULL,
                        `email` varchar(100) NOT NULL,
                        `password` varchar(255) NOT NULL,
                        `phone` char(13) DEFAULT NULL,
                        `type` varchar(50) DEFAULT NULL COMMENT 'Retailer, CharitableOrg, Consumer',
                        `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- FWRP.notification definition

CREATE TABLE IF NOT EXISTS  `notification` (
                                `id` int NOT NULL AUTO_INCREMENT,
                                `user_id` int DEFAULT NULL,
                                `item_id` int DEFAULT NULL,
                                `comm_method` tinyint(1) DEFAULT NULL COMMENT '1 - email, 2 - phone, 3 - email & phone',
                                `time_sent` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                PRIMARY KEY (`id`),
                                KEY `fk_notif_user_id` (`user_id`),
                                KEY `fk_notif_item_id` (`item_id`),
                                CONSTRAINT `fk_notif_item_id` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`),
                                CONSTRAINT `fk_notif_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- FWRP.`order` definition

CREATE TABLE IF NOT EXISTS  `order` (
                         `id` int NOT NULL AUTO_INCREMENT,
                         `user_id` int DEFAULT NULL,
                         `item_id` int DEFAULT NULL,
                         `transaction_type` tinyint(1) NOT NULL COMMENT '1 - purchased, 2 - donated',
                         `item_price` decimal(10,2) NOT NULL DEFAULT '0.00',
                         PRIMARY KEY (`id`),
                         KEY `fk_orders_user_id` (`user_id`),
                         KEY `fk_orders_item_id` (`item_id`),
                         CONSTRAINT `fk_orders_item_id` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`),
                         CONSTRAINT `fk_orders_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- FWRP.subscription definition

CREATE TABLE IF NOT EXISTS  `subscription` (
                                `id` int NOT NULL AUTO_INCREMENT,
                                `user_id` int DEFAULT NULL,
                                `item_id` int DEFAULT NULL,
                                `comm_method` tinyint(1) DEFAULT NULL COMMENT '1 - email, 2 - phone, 3 - email & phone',
                                `location` varchar(100) DEFAULT NULL COMMENT 'based on city',
                                PRIMARY KEY (`id`),
                                KEY `fk_sub_user_id` (`user_id`),
                                KEY `fk_sub_item_id` (`item_id`),
                                CONSTRAINT `fk_sub_item_id` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`),
                                CONSTRAINT `fk_sub_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;