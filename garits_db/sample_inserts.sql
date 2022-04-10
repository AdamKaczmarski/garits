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
        'Airtex Water Block1',
        'Water Pump',
        '475s61',
        'Hyundai',
        'Tucson',
        2020,
        36.00,
        4,
        12
    );


insert into users(email,password,salt,first_name,last_name) values ('test@gmaol.com','1234','1243','Bob','Tomchevsky');
insert into users_roles values (1,1);
INSERT INTO orders (status,description,order_date,order_arrival,order_amount) VALUES('completed','test','2022-03-02','2022-03-05',100);
insert into vehicles (id_reg_no,manufacturer,model,engine_serial_number,chassis_number, colour,last_mot) VALUES('1233','Hyundai','Tucson','654324','532532','red','2022-03-01');
insert into customers (name,city,address,postcode,telephone_number,email) values('tom','london','street','123ewq',8237492,'test@test.com');
insert into customers_vehicles (customer_id,reg_no_id) values(1,'1233');
insert into services (service_name,service_price,approx_time_min,short_description) values ("Oil Change",100,120,"Changing Oil"), ("Tire Change",40,30,"Changing tires");
insert into jobs(vehicle_id,status, est_time_min,act_time_min,fix_date,create_date,bay) VALUES(1,'completed',123,321,'2022-04-25',curdate(),"MOT");
insert into users_jobs values(1,1);
insert into jobs_parts (part_id,job_id,quantity_used) values(1,2,2);
insert into parts_orders (part_id,order_id,quantity_ordered) VALUES(1,1,5);

insert into jobs_payments (job_id,payment_id) values (2,2);
INSERT INTO users(email,password,salt,first_name,last_name) values("test@gmail.com","skjdfhksdjfa","ASDASVZ","Test","User");
INSERT INTO payments(cash_or_card,amount,create_date,payment_date,payment_due) values("cash","1000",'2022-03-02','2022-01-01','2022-02-06');
insert into parts_payments (quantity_sold,part_id,payment_id) values (100,1,1);
insert into payments_customer (payment_id,customer_id) values (1,1);

insert into customer_variable_discounts_services (customer_id,service_id, discount) values (1,1,10);
insert into jobs (vehicle_id, status, est_time_min,description_required) values (1,'booked',100,'NEED');
insert into jobs (vehicle_id, status, est_time_min,description_required,bay) values (1,'active',100,'NEED','regular');

SELECT id_customer, name from customers where id_customer IN (SELECT DISTINCT pc.customer_id FROM payments_customer pc INNER JOIN payments p on p.id_payment = pc.payment_id where p.payment_due<CURDATE() AND p.payment_date is NULL);

update users set password='$2a$12$4ZHJdwfLewmQQkdJ14VTAu5oywiPx/SzcEqMqrMGd0Pontxwz3mrO' where id_user=5;
update users set password='$2a$12$z7pP8c5N74qjF0XJKdSxn.XF81dCIdVoj8b8gXpYshchhwwu5tIr6' where id_user=4;
update users set password='$2a$12$3QclwhUhO9mmBQ/EpcJj..a0i2FSvUWVU.eOG7g1.OuDTm8QOU7n6' where id_user=3;
update users set password='$2a$12$UXEQtBGGH4IvP1g.LFMzlOG.sJIC2qV3.X9.77al7H4IgL7xgpSVe' where id_user=2;
update users set password='$2a$12$2EIG52Uv.3LfQs3HVq0bKO3b1qHrXJ8Wik60Z3aj3sTnkiR6aWA4y' where id_user=3;
select * from users;