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

INSERT INTO orders (status,description,order_date,order_arrival,order_amount) VALUES('completed','test','2022-03-02','2022-03-05',100);
insert into jobs(reg_no_id,status, est_time_min,act_time_min,fix_date,create_date) VALUES('123','completed',123,321,'2022-03-05',curdate());
insert into vehicles (id_reg_no,manufacturer,model,engine_serial_number,chassis_number, colour,last_mot) VALUES('123','Hyundai','Tucson','654324','532532','red','2022-03-01');
insert into customers (name,city,address,postcode,telephone_number,email) values('tom','london','street','123ewq',8237492,'test@test.com');
insert into jobs_customers values(2,1);
insert into parts_orders (part_id,order_id,quantity_ordered) VALUES(1,1,3);
insert into jobs_parts (part_id,job_id,quantity_used) values(1,2,2);
insert into customers_vehicles (customer_id,reg_no_id) values(1,'123');




INSERT INTO users(email,password,salt,first_name,last_name) values("test@gmail.com","skjdfhksdjfa","ASDASVZ","Test","User");
INSERT INTO payments(cash_or_card,amount,create_date,payment_date,payment_due) values("card","500",'2022-03-02','2022-01-01','2022-02-06');
