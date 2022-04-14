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


-- 2nd bullet point
-- Overall
SELECT ROUND(AVG(j.act_time_min),2) AS 'Average job time', ROUND(AVG(p.amount),2) AS 'Average price'
FROM jobs j 
INNER JOIN jobs_payments jp ON jp.job_id=j.id_job
INNER JOIN payments p on p.id_payment = jp.payment_id ;
-- Per Job Type
SELECT s.service_name AS 'Job type',ROUND(AVG(j.act_time_min),2) AS 'Average job time', ROUND(AVG(p.amount),2) AS 'Average price'
FROM jobs j 
INNER JOIN jobs_payments jp ON jp.job_id=j.id_job
INNER JOIN payments p on p.id_payment = jp.payment_id
INNER JOIN jobs_services js ON j.id_job = js.job_id
INNER JOIN services s ON s.id_service=js.service_id
GROUP BY s.service_name;
-- Per mechanic
SELECT s.service_name AS 'Job type',CONCAT(u.first_name,' ',u.last_name) AS 'Mechanic',ROUND(AVG(j.act_time_min),2) AS 'Average job time', ROUND(AVG(p.amount),2) AS 'Average price'
FROM jobs j 
INNER JOIN jobs_payments jp ON jp.job_id=j.id_job
INNER JOIN payments p on p.id_payment = jp.payment_id
INNER JOIN jobs_services js ON j.id_job = js.job_id
INNER JOIN services s ON s.id_service=js.service_id
INNER JOIN users_jobs uj ON uj.job_id = j.id_job
INNER JOIN users u ON u.id_user = uj.user_id
GROUP BY s.service_name, CONCAT(u.first_name,' ',u.last_name)
ORDER BY 2,1;

-- Per chosen mechanic
SELECT s.service_name AS 'Job type',CONCAT(u.first_name,' ',u.last_name) AS 'Mechanic',ROUND(AVG(j.act_time_min),2) AS 'Average job time', ROUND(AVG(p.amount),2) AS 'Average price'
FROM jobs j 
INNER JOIN jobs_payments jp ON jp.job_id=j.id_job
INNER JOIN payments p on p.id_payment = jp.payment_id
INNER JOIN jobs_services js ON j.id_job = js.job_id
INNER JOIN services s ON s.id_service=js.service_id
INNER JOIN users_jobs uj ON uj.job_id = j.id_job
INNER JOIN users u ON u.id_user = uj.user_id
WHERE first_name='Gavin' AND last_name='Ross'
GROUP BY s.service_name, CONCAT(u.first_name,' ',u.last_name)
ORDER BY 2,1;



select * from jobs;
select * from payments;
select * from payments_customer;
select * from customers_vehicles;
select * from vehicles;
select * from customers;


SELECT
    p.part_name AS 'Part Name',
    p.code AS 'Part Code',
    p.manufacturer AS 'Manufacturer',
    p.vehicle_type AS 'Vehicle Type',
    p.year_s AS 'Year(s)',
    ROUND(p.price, 2) AS 'Price',
    (p.stock_level-IFNULL(po.quantity_ordered,0)+IFNULL(jp.quantity_used,0)) AS 'Initial Stock level',
    ROUND(p.price * p.stock_level, 2) AS 'Initial cost, £',
    IFNULL(jp.quantity_used,0) AS 'Used',
    IFNULL(po.quantity_ordered,0) AS 'Delivery',
    (
        IFNULL(p.stock_level,0) + IFNULL(po.quantity_ordered,0) - IFNULL(jp.quantity_used,0)
    ) AS 'New Stock level',
    ROUND(
        (
            IFNULL(p.stock_level,0) + IFNULL(po.quantity_ordered,0) - IFNULL(jp.quantity_used,0)
        ) * p.price,
        2
    ) AS 'Stock cost,£',
    p.stock_level_threshold AS 'Low level Threshold'
FROM
    parts p
    LEFT JOIN parts_orders po ON po.part_id = p.id_part
    LEFT JOIN orders o ON o.id_order = po.order_id
    LEFT JOIN jobs_parts jp ON p.id_part = jp.part_id
    LEFT JOIN jobs j ON j.id_job = jp.job_id
WHERE
    (o.status='completed'
    AND DATE(o.order_date) >= '2022-04-10' AND DATE(o.order_arrival) <= '2022-04-13')
    OR (j.status='completed' AND DATE(j.create_date)>= '2022-04-10' AND DATE(j.fix_date) <= '2022-04-13');
    ;


SELECT CONCAT(YEAR(j.create_date),'-',MONTH(j.create_date)),count(j.id_job)
FROM jobs j 
GROUP BY MONTH(j.create_date) ORDER BY 1,2;
-- Overall
SELECT CAST(COUNT(id_job) AS CHAR(50)) from jobs;
-- Per job type
SELECT s.service_name,COUNT(j.id_job)
FROM jobs j 
INNER JOIN jobs_services js ON j.id_job = js.job_id
INNER JOIN services s ON s.id_service=js.service_id
GROUP BY s.service_name;



-- 2nd bullet point
-- average time, and price, per job type (i.e. MoT/Annual service/repair). This should be done overall, and per
-- job type (MoT, annual service, repair, etc.), and / or given mechanic
-- Overall
SELECT ROUND(AVG(j.act_time_min),2) AS 'Average job time', ROUND(AVG(p.amount),2) AS 'Average price'
FROM jobs j 
INNER JOIN jobs_payments jp ON jp.job_id=j.id_job
INNER JOIN payments p on p.id_payment = jp.payment_id ;
-- Per Job Type
SELECT s.service_name AS 'Job type',ROUND(AVG(j.act_time_min),2) AS 'Average job time', ROUND(AVG(p.amount),2) AS 'Average price'
FROM jobs j 
INNER JOIN jobs_payments jp ON jp.job_id=j.id_job
INNER JOIN payments p on p.id_payment = jp.payment_id
INNER JOIN jobs_services js ON j.id_job = js.job_id
INNER JOIN services s ON s.id_service=js.service_id
GROUP BY s.service_name;
-- Per mechanic
SELECT s.service_name AS 'Job type',CONCAT(u.first_name,' ',u.last_name) AS 'Mechanic',ROUND(AVG(j.act_time_min),2) AS 'Average job time', ROUND(AVG(p.amount),2) AS 'Average price'
FROM jobs j 
INNER JOIN jobs_payments jp ON jp.job_id=j.id_job
INNER JOIN payments p on p.id_payment = jp.payment_id
INNER JOIN jobs_services js ON j.id_job = js.job_id
INNER JOIN services s ON s.id_service=js.service_id
INNER JOIN users_jobs uj ON uj.job_id = j.id_job
INNER JOIN users u ON u.id_user = uj.user_id
GROUP BY s.service_name, CONCAT(u.first_name,' ',u.last_name)
ORDER BY 2,1;

-- Per chosen mechanic
SELECT s.service_name AS 'Job type',CONCAT(u.first_name,' ',u.last_name) AS 'Mechanic',ROUND(AVG(j.act_time_min),2) AS 'Average job time', ROUND(AVG(p.amount),2) AS 'Average price'
FROM jobs j 
INNER JOIN jobs_payments jp ON jp.job_id=j.id_job
INNER JOIN payments p on p.id_payment = jp.payment_id
INNER JOIN jobs_services js ON j.id_job = js.job_id
INNER JOIN services s ON s.id_service=js.service_id
INNER JOIN users_jobs uj ON uj.job_id = j.id_job
INNER JOIN users u ON u.id_user = uj.user_id
WHERE id_user=5
GROUP BY s.service_name, CONCAT(u.first_name,' ',u.last_name)
ORDER BY 2,1;


update
    payments
set
    amount = ROUND(
        ROUND(
            amount + (
                SELECT
                    ROUND(
                        j.act_time_min *(
                            select
                                ROUND(hourly_rate / 60, 2)
                            from
                                roles
                            where
                                id_role = (
                                    select
                                        role_id
                                    from
                                        users_roles
                                    where
                                        user_id = (
                                            SELECT
                                                user_id
                                            from
                                                users_jobs
                                            where
                                                job_id = 3
                                        )
                                )
                        ),
                        2
                    )
                from
                    jobs j
                where
                    id_job = 3
            ),
            2
        ) * 1.2,
        2
    )
where
    id_payment = (
        SELECT
            payment_id
        from
            jobs_payments
        where
            job_id = 3
    )
    ;
select date_add(payment_due, INTERVAL 30 DAY) from payments;
update payments set payment_due = date_add(CURDATE(), INTERVAL 30 DAY) where id_payment = (select payment_id from jobs_payments where job_id=10);
update payments set payment_due = date_add(CURDATE(), INTERVAL -40 DAY) where id_payment = (select payment_id from jobs_payments where job_id=16);
select * from customers_vehicles;
update payments set payment_due='2022-02-12';
SELECT * from customers where id_customer IN (SELECT DISTINCT pc.customer_id FROM payments_customer pc INNER JOIN payments p on p.id_payment = pc.payment_id inner join customers c on c.id_customer = pc.customer_id where datediff(p.payment_due,curdate())<=5 AND p.payment_date is NULL AND c.is_account_holder=true)

select * from parts_orders;	