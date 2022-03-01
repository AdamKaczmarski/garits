ALTER TABLE
    Users_Roles DROP FOREIGN KEY FKUsers_Role251675;

ALTER TABLE
    Users_Roles DROP FOREIGN KEY FKUsers_Role790442;

ALTER TABLE
    Jobs_Customers DROP FOREIGN KEY FKJobs_Custo162114;

ALTER TABLE
    Jobs_Customers DROP FOREIGN KEY FKJobs_Custo598651;

ALTER TABLE
    Users_Jobs DROP FOREIGN KEY FKUsers_Jobs928384;

ALTER TABLE
    Users_Jobs DROP FOREIGN KEY FKUsers_Jobs91376;

ALTER TABLE
    Jobs_Services DROP FOREIGN KEY FKJobs_Servi182926;

ALTER TABLE
    Jobs_Services DROP FOREIGN KEY FKJobs_Servi475550;

ALTER TABLE
    Jobs_Parts DROP FOREIGN KEY FKJobs_Parts634881;

ALTER TABLE
    Jobs_Parts DROP FOREIGN KEY FKJobs_Parts55459;

ALTER TABLE
    Customers_Vehicles DROP FOREIGN KEY FKCustomers_94687;

ALTER TABLE
    Customers_Vehicles DROP FOREIGN KEY FKCustomers_547421;

ALTER TABLE
    Parts_RetailSales DROP FOREIGN KEY FKParts_Reta997959;

ALTER TABLE
    Parts_RetailSales DROP FOREIGN KEY FKParts_Reta600423;

ALTER TABLE
    Payments_Customer DROP FOREIGN KEY FKPayments_C594261;

ALTER TABLE
    Payments_Customer DROP FOREIGN KEY FKPayments_C52864;

ALTER TABLE
    Jobs DROP FOREIGN KEY FKJobs204816;

ALTER TABLE
    Parts_Orders DROP FOREIGN KEY FKParts_Orde7122;

ALTER TABLE
    Parts_Orders DROP FOREIGN KEY FKParts_Orde56004;

ALTER TABLE
    Jobs DROP FOREIGN KEY FKJobs638019;

ALTER TABLE
    Customer_Variable_Discounts_Services DROP FOREIGN KEY FKCustomer_V790173;

ALTER TABLE
    Customer_Variable_Discounts_Services DROP FOREIGN KEY FKCustomer_V539825;

DROP TABLE IF EXISTS Users;

DROP TABLE IF EXISTS Roles;

DROP TABLE IF EXISTS Users_Roles;

DROP TABLE IF EXISTS Customers;

DROP TABLE IF EXISTS Jobs;

DROP TABLE IF EXISTS Parts;

DROP TABLE IF EXISTS Vehicles;

DROP TABLE IF EXISTS Jobs_Customers;

DROP TABLE IF EXISTS Users_Jobs;

DROP TABLE IF EXISTS Services;

DROP TABLE IF EXISTS Jobs_Services;

DROP TABLE IF EXISTS Jobs_Parts;

DROP TABLE IF EXISTS Customers_Vehicles;

DROP TABLE IF EXISTS RetailSales;

DROP TABLE IF EXISTS Parts_RetailSales;

DROP TABLE IF EXISTS Payments_Customer;

DROP TABLE IF EXISTS Payments;

DROP TABLE IF EXISTS Orders;

DROP TABLE IF EXISTS Parts_Orders;

DROP TABLE IF EXISTS Customer_Variable_Discounts_Services;

CREATE TABLE Users (
    id_user int(11) NOT NULL AUTO_INCREMENT,
    email varchar(100) NOT NULL UNIQUE,
    password varchar(32) NOT NULL,
    salt varchar(32) NOT NULL,
    first_name varchar(50) NOT NULL,
    last_name varchar(100) NOT NULL,
    PRIMARY KEY (id_user),
    UNIQUE INDEX (id_user)
);

CREATE TABLE Roles (
    id_role int(11) NOT NULL AUTO_INCREMENT,
    role_name varchar(20) NOT NULL,
    PRIMARY KEY (id_role),
    UNIQUE INDEX (id_role)
);

CREATE TABLE Users_Roles (
    user_id int(11) NOT NULL,
    role_id int(11) NOT NULL
);

CREATE TABLE Customers (
    id_customer int(11) NOT NULL AUTO_INCREMENT,
    name varchar(50) NOT NULL,
    city varchar(50) NOT NULL,
    address varchar(150) NOT NULL,
    postcode varchar(10) NOT NULL,
    telephone_number int(11) NOT NULL UNIQUE,
    email varchar(100) NOT NULL UNIQUE,
    fax int(12),
    fixed_discount int(2),
    flex_discounts_json text,
    PRIMARY KEY (id_customer),
    UNIQUE INDEX (id_customer)
);

CREATE TABLE Jobs (
    id_job int(11) NOT NULL AUTO_INCREMENT,
    payment_id int(10) NOT NULL,
    no_reg_id varchar(10) NOT NULL,
    description_done varchar(2000),
    description_required varchar(2000),
    est_time_min int(4) NOT NULL,
    act_time_min int(4) NOT NULL,
    create_date timestamp NOT NULL,
    booking_date datetime NULL,
    fix_date datetime NULL,
    PRIMARY KEY (id_job),
    UNIQUE INDEX (id_job)
);

CREATE TABLE Parts (
    id_part int(11) NOT NULL AUTO_INCREMENT,
    part_name varchar(200) NOT NULL,
    part_type varchar(20),
    code varchar(25) NOT NULL,
    manufacturer varchar(20) NOT NULL,
    vehicle_type varchar(20) NOT NULL,
    year_s varchar(9) NOT NULL,
    price decimal(8, 2) NOT NULL,
    stock_level int(4) NOT NULL,
    stock_level_threshold int(4) NOT NULL,
    PRIMARY KEY (id_part),
    UNIQUE INDEX (id_part)
);

CREATE TABLE Vehicles (
    id_reg_no varchar(10) NOT NULL,
    manufacturer varchar(20) NOT NULL,
    model varchar(50) NOT NULL,
    engine_serial_number varchar(9) NOT NULL UNIQUE,
    chassis_number varchar(8) NOT NULL UNIQUE,
    colour varchar(15) NOT NULL,
    last_mot datetime NOT NULL,
    PRIMARY KEY (id_reg_no),
    UNIQUE INDEX (id_reg_no)
);

CREATE TABLE Jobs_Customers (
    job_id int(11) NOT NULL,
    customer_id int(11) NOT NULL
);

CREATE TABLE Users_Jobs (
    user_id int(11) NOT NULL,
    job_id int(11) NOT NULL
);

CREATE TABLE Services (
    id_service int(11) NOT NULL AUTO_INCREMENT,
    service_name varchar(255) NOT NULL,
    service_price decimal(8, 2) NOT NULL,
    approx_time_min int(4) NOT NULL,
    PRIMARY KEY (id_service),
    UNIQUE INDEX (id_service)
);

CREATE TABLE Jobs_Services (
    job_id int(11) NOT NULL,
    service_id int(11) NOT NULL
);

CREATE TABLE Jobs_Parts (
    part_id int(11) NOT NULL,
    job_id int(11) NOT NULL,
    quantity_used int(4)
);

CREATE TABLE Customers_Vehicles (
    customer_id int(11) NOT NULL,
    reg_no_id varchar(10) NOT NULL
);

CREATE TABLE RetailSales (
    id_sale int(11) NOT NULL AUTO_INCREMENT,
    sale_date datetime NOT NULL,
    sale_amount decimal(8, 2) NOT NULL,
    PRIMARY KEY (id_sale),
    UNIQUE INDEX (id_sale)
);

CREATE TABLE Parts_RetailSales (
    part_id int(11) NOT NULL,
    sale_id int(11) NOT NULL,
    quantity_sold int(4)
);

CREATE TABLE Payments_Customer (
    payment_id int(10) NOT NULL,
    customer_id int(11) NOT NULL
);

CREATE TABLE Payments (
    id_payment int(10) NOT NULL AUTO_INCREMENT,
    cash_or_card varchar(4) NOT NULL,
    amount decimal(8, 2) NOT NULL,
    create_date timestamp NOT NULL,
    payment_date datetime NULL,
    payment_due datetime NULL,
    PRIMARY KEY (id_payment),
    UNIQUE INDEX (id_payment)
);

CREATE TABLE Orders (
    id_order int(11) NOT NULL AUTO_INCREMENT,
    status varchar(20) DEFAULT 'pending' NOT NULL,
    order_date datetime NOT NULL,
    order_amount decimal(8, 2) NOT NULL,
    PRIMARY KEY (id_order),
    UNIQUE INDEX (id_order)
);

CREATE TABLE Parts_Orders (
    part_id int(11) NOT NULL,
    order_id int(11) NOT NULL,
    descrption varchar(50) NOT NULL,
    quantity_ordered int(4) NOT NULL,
    price decimal(8, 2)
);

CREATE TABLE Customer_Variable_Discounts_Services (
    customer_id int(11) NOT NULL,
    service_id int(11) NOT NULL,
    discount int(2) NOT NULL
);

ALTER TABLE
    Users_Roles
ADD
    CONSTRAINT FKUsers_Role251675 FOREIGN KEY (user_id) REFERENCES Users (id_user);

ALTER TABLE
    Users_Roles
ADD
    CONSTRAINT FKUsers_Role790442 FOREIGN KEY (role_id) REFERENCES Roles (id_role);

ALTER TABLE
    Jobs_Customers
ADD
    CONSTRAINT FKJobs_Custo162114 FOREIGN KEY (job_id) REFERENCES Jobs (id_job);

ALTER TABLE
    Jobs_Customers
ADD
    CONSTRAINT FKJobs_Custo598651 FOREIGN KEY (customer_id) REFERENCES Customers (id_customer);

ALTER TABLE
    Users_Jobs
ADD
    CONSTRAINT FKUsers_Jobs928384 FOREIGN KEY (user_id) REFERENCES Users (id_user);

ALTER TABLE
    Users_Jobs
ADD
    CONSTRAINT FKUsers_Jobs91376 FOREIGN KEY (job_id) REFERENCES Jobs (id_job);

ALTER TABLE
    Jobs_Services
ADD
    CONSTRAINT FKJobs_Servi182926 FOREIGN KEY (job_id) REFERENCES Jobs (id_job);

ALTER TABLE
    Jobs_Services
ADD
    CONSTRAINT FKJobs_Servi475550 FOREIGN KEY (service_id) REFERENCES Services (id_service);

ALTER TABLE
    Jobs_Parts
ADD
    CONSTRAINT FKJobs_Parts634881 FOREIGN KEY (part_id) REFERENCES Parts (id_part);

ALTER TABLE
    Jobs_Parts
ADD
    CONSTRAINT FKJobs_Parts55459 FOREIGN KEY (job_id) REFERENCES Jobs (id_job);

ALTER TABLE
    Customers_Vehicles
ADD
    CONSTRAINT FKCustomers_94687 FOREIGN KEY (customer_id) REFERENCES Customers (id_customer);

ALTER TABLE
    Customers_Vehicles
ADD
    CONSTRAINT FKCustomers_547421 FOREIGN KEY (reg_no_id) REFERENCES Vehicles (id_reg_no);

ALTER TABLE
    Parts_RetailSales
ADD
    CONSTRAINT FKParts_Reta997959 FOREIGN KEY (part_id) REFERENCES Parts (id_part);

ALTER TABLE
    Parts_RetailSales
ADD
    CONSTRAINT FKParts_Reta600423 FOREIGN KEY (sale_id) REFERENCES RetailSales (id_sale);

ALTER TABLE
    Payments_Customer
ADD
    CONSTRAINT FKPayments_C594261 FOREIGN KEY (payment_id) REFERENCES Payments (id_payment);

ALTER TABLE
    Payments_Customer
ADD
    CONSTRAINT FKPayments_C52864 FOREIGN KEY (customer_id) REFERENCES Customers (id_customer);

ALTER TABLE
    Jobs
ADD
    CONSTRAINT FKJobs204816 FOREIGN KEY (payment_id) REFERENCES Payments (id_payment);

ALTER TABLE
    Parts_Orders
ADD
    CONSTRAINT FKParts_Orde7122 FOREIGN KEY (part_id) REFERENCES Parts (id_part);

ALTER TABLE
    Parts_Orders
ADD
    CONSTRAINT FKParts_Orde56004 FOREIGN KEY (order_id) REFERENCES Orders (id_order) ON DELETE CASCADE;

ALTER TABLE
    Jobs
ADD
    CONSTRAINT FKJobs638019 FOREIGN KEY (no_reg_id) REFERENCES Vehicles (id_reg_no);

ALTER TABLE
    Customer_Variable_Discounts_Services
ADD
    CONSTRAINT FKCustomer_V790173 FOREIGN KEY (customer_id) REFERENCES Customers (id_customer);

ALTER TABLE
    Customer_Variable_Discounts_Services
ADD
    CONSTRAINT FKCustomer_V539825 FOREIGN KEY (service_id) REFERENCES Services (id_service);