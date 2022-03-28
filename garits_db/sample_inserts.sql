INSERT INTO
    parts (
        part_name,
        part_type,
        code,
        manufacturer,
        vehicle_type,
        year_s,
        price,
        stock_level,
        stock_level_threshold
    )
VALUES
    (
        'Airtex Water Block',
        'Water Pump',
        '475861',
        'Hyundai',
        'Tucson',
        2020,
        36.00,
        12,
        4
    );
INSERT INTO users (email, password, salt, first_name, last_name) values ("test_user@gmail.com","ldshfsldj","asdsadsa","Test","test");
insert into users_roles (user_id,role_id) values (24,1);
INSERT INTO orders (status,description,order_date,order_arrival,order_amount) VALUES('completed','test','2022-03-02','2022-03-05',100);
insert into jobs(reg_no_id,status, est_time_min,act_time_min,fix_date,create_date) VALUES('123','completed',123,321,'2022-03-05',curdate());
insert into vehicles (id_reg_no,manufacturer,model,engine_serial_number,chassis_number, colour,last_mot) VALUES('123','Hyundai','Tucson','654324','532532','red','2022-03-01');
insert into services (service_name,service_price,approx_time_min,short_description) values ('Oil Change',25.50,120,'Changing the oil');
insert into customers (name,city,address,postcode,telephone_number,email,fax,fixed_discount) values('tom','london','street','123ewq','123087456','test@test.com','12345',25);
insert into customer_flex_discounts(customer_id,range_from,discount) VALUES (1,20000,10);
insert into customer_flex_discounts(customer_id,range_from,discount) VALUES (1,30000,16);
insert into customer_variable_discounts_services(customer_id,service_id,discount) values (1,1,23.3);
insert into jobs_customers values(2,1);
insert into parts_orders (part_id,order_id,quantity_ordered) VALUES(1,1,3);
insert into jobs_parts (part_id,job_id,quantity_used) values(1,2,2);
insert into customers_vehicles (customer_id,reg_no_id) values(1,'123');
INSERT INTO users(email,password,salt,first_name,last_name) values("test@gmail.com","skjdfhksdjfa","ASDASVZ","Test","User");
select * from vehicles;
select * from customers;
select * from customers_vehicles;
select * from customer_variable_discounts_services;
select * from users;
insert into customers_vehicles(customer_id,reg_no_id) values(1,'123');
INSERT INTO users_roles(user_id,role_id) values(26,1),(27,1);
select * from jobs;
INSERT 
    INTO
        customer_variable_discounts_services
        (customer_id,service_id,discount) 
    VALUES
        (2,2,23);
        DELETE FROM customer_variable_discounts_services where customer_id=2;
        
        select * from customer_variable_discounts_services where customer_id=2;
        