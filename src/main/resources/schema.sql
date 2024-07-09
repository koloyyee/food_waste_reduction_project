CREATE DATABASE IF NOT EXISTS fwrp;

CREATE TABLE IF NOT EXISTS fwrp.users ( 
	id int primary key ,
	name varchar(100) not null,
	email varchar(100) not null unique,
	password vachar(255) not null,
	phone char(13) default null,
	type tinyint(1) comment '1 - retailer, 2 - charitable org, 3 - consumer'
);

CREATE TABLE IF NOT EXISTS fwrp.item (
		
	
);