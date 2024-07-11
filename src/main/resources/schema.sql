CREATE DATABASE IF NOT EXISTS fwrp;

CREATE TABLE IF NOT EXISTS `fwrp`.`user` ( 
	id int(5) NOT NULL AUTO_INCREMENT,
	name varchar(100) not null,
	email varchar(100) not null unique,
	password varchar(255) not null,
	phone char(13) default null,
	type tinyint(1) comment '1 - retailer, 2 - charitable org, 3 - consumer',
	created_at timestamp default CURRENT_TIMESTAMP,
	 primary key (id)
);

CREATE TABLE IF NOT EXISTS fwrp.item (
	id int(5) NOT NULL AUTO_INCREMENT,
	name varchar(100) not null,
	description text,
	expiry_date date not null,
	price decimal(10, 2) not null default '0',
	discount_rate float(10,1) default '0.0',
	is_surplus boolean default false,
	is_donation boolean default false,
	quantity int default 0,
	is_available boolean default true,
	created_at timestamp default CURRENT_TIMESTAMP,
	update_at timestamp default null,
	primary key (id)
);

CREATE TABLE IF NOT EXISTS fwrp.order (
	id int(5) NOT NULL AUTO_INCREMENT,
	user_id int,
	item_id int,
	transaction_type tinyint(1) comment '1 - purchased, 2 - donated' not null,
	total_price decimal(10,2 ) default '0.00' not null,
	constraint `fk_orders_user_id` foreign key (user_id) references user(id),
	constraint `fk_orders_item_id` foreign key (item_id) references item(id),
	primary key (id)
);

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
 