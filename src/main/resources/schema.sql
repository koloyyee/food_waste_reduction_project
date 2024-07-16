CREATE DATABASE IF NOT EXISTS fwrp;

-- FWRP.`user` definition

CREATE TABLE IF NOT EXISTS `fwrp`.`user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` char(13) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL COMMENT 'Retailer, CharitableOrg, Consumer',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- FWRP.item definition
CREATE TABLE IF NOT EXISTS `fwrp`.`item`  (
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


-- FWRP.`order` definition
CREATE TABLE IF NOT EXISTS `fwrp`.`order` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `item_id` int DEFAULT NULL,
  `transaction_type` tinyint(1) NOT NULL COMMENT '1 - purchased, 2 - donated',
  `total_price` decimal(10,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`id`),
  KEY `fk_orders_user_id` (`user_id`),
  KEY `fk_orders_item_id` (`item_id`),
  CONSTRAINT `fk_orders_item_id` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`),
  CONSTRAINT `fk_orders_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



-- not sure about the subscription and notificatin yet.
CREATE TABLE IF NOT EXISTS fwrp.subscription (
	id int AUTO_INCREMENT not null,
	user_id int,
	item_id int,
	comm_method tinyint(1) comment '1 - email, 2 - phone, 3 - email & phone',
	latitude float not null,
	longitude float not null,
	constraint `fk_sub_user_id` foreign key(user_id) references user(id),
	constraint `fk_sub_item_id` foreign key(item_id) references item(id),
		 primary key (id)
 );
 
CREATE TABLE IF NOT EXISTS  fwrp.notification (
	id int AUTO_INCREMENT not null,
	user_id int,
	item_id int,
	comm_method tinyint(1) comment '1 - email, 2 - phone, 3 - email & phone',
	time_sent timestamp default current_timestamp,
	constraint `fk_notif_user_id` foreign key(user_id) references user(id),
	constraint `fk_notif_item_id` foreign key(item_id) references item(id),
	primary key (id)
	
); 
 