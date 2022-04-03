DROP DATABASE IF EXISTS garits;
CREATE DATABASE garits CHARACTER SET utf8;
USE garits;
CREATE TABLE `Users` (
    `id_user` int NOT NULL AUTO_INCREMENT,
    `email` varchar(100) NOT NULL UNIQUE,
    `password` varchar(32) NOT NULL,
    `salt` varchar(32) NOT NULL,
    `first_name` varchar(50) NOT NULL,
    `last_name` varchar(100) NOT NULL,
    PRIMARY KEY (`id_user`),
    UNIQUE INDEX (`id_user`)
);

CREATE TABLE `Roles` (
    `id_role` int NOT NULL AUTO_INCREMENT,
    `role_name` varchar(20) NOT NULL,
    PRIMARY KEY (`id_role`),
    UNIQUE INDEX (`id_role`)
);

CREATE TABLE `Users_Roles` (
    `user_id` int NOT NULL,
    `role_id` int NOT NULL
);

CREATE TABLE `Customers` (
    `id_customer` int NOT NULL AUTO_INCREMENT,
    `name` varchar(50) NOT NULL,
    `city` varchar(50) NOT NULL,
    `address` varchar(150) NOT NULL,
    `postcode` varchar(10) NOT NULL,
    `telephone_number` varchar(14) NOT NULL UNIQUE,
    `email` varchar(100) NOT NULL UNIQUE,
    `fax` varchar(20),
    `fixed_discount` int,
    PRIMARY KEY (`id_customer`),
    UNIQUE INDEX (`id_customer`)
);

CREATE TABLE `Jobs` (
    `id_job` int NOT NULL AUTO_INCREMENT,
    `vehicle_id` int NOT NULL,
    `status` varchar(50) NOT NULL,
    `description_done` varchar(2000),
    `description_required` varchar(2000),
    `est_time_min` int NOT NULL,
    `act_time_min` int NOT NULL,
    `create_date` timestamp NOT NULL,
    `booking_date` date NULL,
    `fix_date` date NULL,
    `bay` varchar(30),
    PRIMARY KEY (`id_job`),
    UNIQUE INDEX (`id_job`)
);

CREATE TABLE `Parts` (
    `id_part` int NOT NULL AUTO_INCREMENT,
    `part_name` varchar(200) NOT NULL,
    `part_type` varchar(50),
    `code` varchar(25) NOT NULL,
    `manufacturer` varchar(20) NOT NULL,
    `vehicle_type` varchar(20) NOT NULL,
    `year_s` varchar(9) NOT NULL,
    `price` decimal(8, 2) NOT NULL,
    `stock_level` int NOT NULL,
    `stock_level_threshold` int NOT NULL,
    PRIMARY KEY (`id_part`),
    UNIQUE INDEX (`id_part`)
);

CREATE TABLE `Vehicles` (
    `id_vehicle` int NOT NULL AUTO_INCREMENT,
    `id_reg_no` varchar(10) NOT NULL,
    `manufacturer` varchar(20) NOT NULL,
    `model` varchar(50) NOT NULL,
    `engine_serial_number` varchar(9) NOT NULL UNIQUE,
    `chassis_number` varchar(8) NOT NULL UNIQUE,
    `colour` varchar(15) NOT NULL,
    `last_mot` date NOT NULL,
    PRIMARY KEY (`id_vehicle`),
    UNIQUE INDEX (`id_vehicle`),
    UNIQUE INDEX (`id_reg_no`)
);

CREATE TABLE `Jobs_Customers` (
    `job_id` int NOT NULL,
    `customer_id` int NOT NULL
);

CREATE TABLE `Users_Jobs` (
    `user_id` int NOT NULL,
    `job_id` int NOT NULL
);

CREATE TABLE `Services` (
    `id_service` int NOT NULL AUTO_INCREMENT,
    `service_name` varchar(255) NOT NULL,
    `service_price` decimal(8, 2) NOT NULL,
    `approx_time_min` int NOT NULL,
    `short_description` varchar(100) NOT NULL,
    PRIMARY KEY (`id_service`),
    UNIQUE INDEX (`id_service`)
);

CREATE TABLE `Jobs_Services` (
    `job_id` int NOT NULL,
    `service_id` int NOT NULL
);

CREATE TABLE `Jobs_Parts` (
    `part_id` int NOT NULL,
    `job_id` int NOT NULL,
    `quantity_used` int
);

CREATE TABLE `Customers_Vehicles` (
    `customer_id` int NOT NULL,
    `vehicle_id` int NOT NULL
);

CREATE TABLE `Parts_Payments` (
	`id_parts_payments` int NOT NULL AUTO_INCREMENT,
    `part_id` int NOT NULL,
    `quantity_sold` int,
    `payment_id` int NOT NULL,
    PRIMARY KEY (`id_parts_payments`),
    UNIQUE INDEX (`id_parts_payments`)
);

CREATE TABLE `Payments_Customer` (
    `payment_id` int NOT NULL,
    `customer_id` int NOT NULL
);

CREATE TABLE `Payments` (
    `id_payment` int NOT NULL AUTO_INCREMENT,
    `cash_or_card` varchar(4) NOT NULL,
    `amount` decimal(8, 2) NOT NULL,
    `create_date` timestamp NOT NULL,
    `payment_date` date NULL,
    `payment_due` date NULL,
    PRIMARY KEY (`id_payment`),
    UNIQUE INDEX (`id_payment`)
);

CREATE TABLE `Orders` (
    `id_order` int NOT NULL AUTO_INCREMENT,
    `status` varchar(20) DEFAULT 'pending' NOT NULL,
    `description` varchar(200) NOT NULL,
    `order_date` date NOT NULL,
    `order_arrival` date,
    `order_amount` decimal(8, 2) NOT NULL,
    PRIMARY KEY (`id_order`),
    UNIQUE INDEX (`id_order`)
);

CREATE TABLE `Parts_Orders` (
	`id_parts_order` int NOT NULL AUTO_INCREMENT,
    `part_id` int NOT NULL,
    `order_id` int NOT NULL,
    `quantity_ordered` int NOT NULL,
    PRIMARY KEY (`id_parts_order`),
    UNIQUE INDEX (`id_parts_order`)
);

CREATE TABLE `Customer_Variable_Discounts_Services` (
    `id_var_discount` int NOT NULL AUTO_INCREMENT,
    `customer_id` int NOT NULL,
    `service_id` int NOT NULL,
    `discount` int NOT NULL,
    PRIMARY KEY (`id_var_discount`),
    UNIQUE INDEX(`id_var_discount`)
);

CREATE TABLE `Jobs_Payments` (
    `Job_ID` int NOT NULL,
    `Payment_ID` int NOT NULL
);
CREATE TABLE `customer_flex_discounts`(
    `id_flex_discount` int NOT NULL AUTO_INCREMENT,
    `customer_id` int NOT NULL,
    `range_from` int not null,
    `discount` int not null,
    PRIMARY KEY (`id_flex_discount`),
    UNIQUE INDEX (`id_flex_discount`)
);
ALTER TABLE
    `Users_Roles`
ADD
    CONSTRAINT `FKUsers_Role251675` FOREIGN KEY (`user_id`) REFERENCES `Users` (`id_user`) ON UPDATE CASCADE;

ALTER TABLE
    `Users_Roles`
ADD
    CONSTRAINT `FKUsers_Role790442` FOREIGN KEY (`role_id`) REFERENCES `Roles` (`id_role`) ON UPDATE CASCADE;

ALTER TABLE
    `Jobs_Customers`
ADD
    CONSTRAINT `FKJobs_Custo162114` FOREIGN KEY (`job_id`) REFERENCES `Jobs` (`id_job`) ON UPDATE CASCADE;

ALTER TABLE
    `Jobs_Customers`
ADD
    CONSTRAINT `FKJobs_Custo598651` FOREIGN KEY (`customer_id`) REFERENCES `Customers` (`id_customer`) ON UPDATE CASCADE;

ALTER TABLE
    `Users_Jobs`
ADD
    CONSTRAINT `FKUsers_Jobs928384` FOREIGN KEY (`user_id`) REFERENCES `Users` (`id_user`) ON UPDATE CASCADE;

ALTER TABLE
    `Users_Jobs`
ADD
    CONSTRAINT `FKUsers_Jobs91376` FOREIGN KEY (`job_id`) REFERENCES `Jobs` (`id_job`) ON UPDATE CASCADE;

ALTER TABLE
    `Jobs_Services`
ADD
    CONSTRAINT `FKJobs_Servi182926` FOREIGN KEY (`job_id`) REFERENCES `Jobs` (`id_job`) ON UPDATE CASCADE;

ALTER TABLE
    `Jobs_Services`
ADD
    CONSTRAINT `FKJobs_Servi475550` FOREIGN KEY (`service_id`) REFERENCES `Services` (`id_service`) ON UPDATE CASCADE;

ALTER TABLE
    `Jobs_Parts`
ADD
    CONSTRAINT `FKJobs_Parts634881` FOREIGN KEY (`part_id`) REFERENCES `Parts` (`id_part`) ON UPDATE CASCADE;

ALTER TABLE
    `Jobs_Parts`
ADD
    CONSTRAINT `FKJobs_Parts55459` FOREIGN KEY (`job_id`) REFERENCES `Jobs` (`id_job`) ON UPDATE CASCADE;

ALTER TABLE
    `Customers_Vehicles`
ADD
    CONSTRAINT `FKCustomers_94687` FOREIGN KEY (`customer_id`) REFERENCES `Customers` (`id_customer`);

ALTER TABLE
    `Customers_Vehicles`
ADD
    CONSTRAINT `FKCustomers_547421` FOREIGN KEY (`vehicle_id`) REFERENCES `Vehicles` (`id_vehicle`) ON UPDATE CASCADE;

ALTER TABLE
    `Payments_Customer`
ADD
    CONSTRAINT `FKPayments_C594261` FOREIGN KEY (`payment_id`) REFERENCES `Payments` (`id_payment`) ON UPDATE CASCADE;

ALTER TABLE
    `Payments_Customer`
ADD
    CONSTRAINT `FKPayments_C52864` FOREIGN KEY (`customer_id`) REFERENCES `Customers` (`id_customer`) ON UPDATE CASCADE;

ALTER TABLE
    `Parts_Orders`
ADD
    CONSTRAINT `FKParts_Orde7122` FOREIGN KEY (`part_id`) REFERENCES `Parts` (`id_part`) ON UPDATE CASCADE;

ALTER TABLE
    `Parts_Orders`
ADD
    CONSTRAINT `FKParts_Orde56004` FOREIGN KEY (`order_id`) REFERENCES `Orders` (`id_order`) ON UPDATE CASCADE;

ALTER TABLE
    `Jobs`
ADD
    CONSTRAINT `FKJobs210684` FOREIGN KEY (`vehicle_id`) REFERENCES `Vehicles` (`id_vehicle`) ON UPDATE CASCADE;

ALTER TABLE
    `Customer_Variable_Discounts_Services`
ADD
    CONSTRAINT `FKCustomer_V790173` FOREIGN KEY (`customer_id`) REFERENCES `Customers` (`id_customer`) ON UPDATE CASCADE;

ALTER TABLE
    `Customer_Variable_Discounts_Services`
ADD
    CONSTRAINT `FKCustomer_V539825` FOREIGN KEY (`service_id`) REFERENCES `Services` (`id_service`) ON UPDATE CASCADE;

ALTER TABLE
    `Jobs_Payments`
ADD
    CONSTRAINT `FKJobs_Payme844591` FOREIGN KEY (`Job_ID`) REFERENCES `Jobs` (`id_job`) ON UPDATE CASCADE;

ALTER TABLE
    `Jobs_Payments`
ADD
    CONSTRAINT `FKJobs_Payme733948` FOREIGN KEY (`Payment_ID`) REFERENCES `Payments` (`id_payment`) ON UPDATE CASCADE;

ALTER TABLE
    `Parts_Payments`
ADD
    CONSTRAINT `FKParts_Paym554541` FOREIGN KEY (`part_id`) REFERENCES `Parts` (`id_part`) ON UPDATE CASCADE;

ALTER TABLE
    `Parts_Payments`
ADD
    CONSTRAINT `FKParts_Paym813726` FOREIGN KEY (`payment_id`) REFERENCES `Payments` (`id_payment`) ON UPDATE CASCADE;
<<<<<<<< HEAD:garits_db/garits_schema.sql

ALTER TABLE
    `customer_flex_discounts`
ADD
    CONSTRAINT `FKCust_CustFlex` FOREIGN KEY (`customer_id`) REFERENCES `Customers` (`id_customer`) ON UPDATE CASCADE;

========
>>>>>>>> orders:garits_db/garits_schema_first_deliverable.sql
    
insert into roles (role_name) values ('MECHANIC'),("FRANCHISEE"), ("RECEPTIONIST"), ("FOREPERSON");
