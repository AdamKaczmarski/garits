USE garits;

-- This file contains queries for the ER Diagram required in the first deliverable of the project. (2 Selects, 2 Inserts, 2 Updates, 2 Deletes and 2 Reporting queries based on the scenario)
-- Select 1
SELECT
    c.name AS 'Customer',
    CONCAT(v.manufacturer, ' ', v.model) AS 'Car',
    v.id_reg_no AS 'Registration Number'
from
    customers c
    inner join customers_vehicles cv on cv.customer_id = c.id_customer
    inner join vehicles v on v.id_reg_no = cv.reg_no_id
order by
    1,
    2;

-- Select 2
SELECT
    part_name AS 'Part',
    part_type AS 'Type',
    code 'Part code',
    manufacturer AS 'Car Manufacturer',
    price AS 'Price',
    stock_level AS 'Quantity',
    ROUND(price * stock_level) AS 'Stock cost'
FROM
    parts
order by
    'Stock cost';

-- Update 1
UPDATE
    customers
SET
    fixed_discount = 5,
    flex_discounts_json = "{{ rangeFrom: 5000, discount: 5 },{ rangeFrom: 10000, discount: 10 }}"
WHERE
    id_customer = 1;

-- Update 2
UPDATE
    parts
SET
    price = price * 1.1
WHERE
    manufacturer = 'Hyundai';

-- Insert 1
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

-- Insert 2
INSERT INTO
    customers (
        name,
        address,
        city,
        postcode,
        telephone_number,
        email,
        fax,
        fixed_discount,
        flex_discounts_json
    )
VALUES
    (
        'The Car Company',
        'Top Gear Street 17',
        'London',
        'E1 15ER',
        '1234567890',
        'car_company@gmail.com',
        null,
        7,
        "{{ rangeFrom: 10000, discount: 5 },{ rangeFrom: 20000, discount: 10 }}"
    );

-- Delete 1 -  Deleting overpriced services (services that cost >1000)
DELETE FROM
    services
where
    service_price > 1000;

-- Delete 2 - Deleting cancelled orders
DELETE FROM
    orders
where
    status = 'CANCELLED';
-- Report 1 (Job Sheet)
SELECT j.id_job AS 'Job No',j.reg_no_id AS 'Vehicle registration',j.booking_date AS 'Date Booked',v.manufacturer AS 'Make',v.model AS 'Model',c.name AS 'Customer name',c.telephone_number AS 'Customer Tel' ,j.description_required AS 'Description of work required', j.est_time_min AS 'Estimated Time',j.description_done AS 'Descprtion of work carried out',j.act_time_min AS 'Actual time',group_concat(CONCAT(p.part_name,'  ',p.code,'  ',jp.quantity_used) SEPARATOR ',') AS 'Spare parts used'
FROM customers c
INNER JOIN jobs_customers jc ON c.id_customer=jc.customer_id
INNER JOIN jobs j ON j.id_job=jc.job_id
INNER JOIN vehicles v ON j.reg_no_id=v.id_reg_no
INNER JOIN jobs_parts jp ON j.id_job=jp.job_id
INNER JOIN parts p ON p.id_part=jp.part_id
WHERE id_job=2;
-- Report 2 (Spare Parts / Stock Level)
SELECT
    p.part_name AS 'Part Name',
    p.code AS 'Part Code',
    p.manufacturer AS 'Manufacturer',
    p.vehicle_type AS 'Vehicle Type',
    p.year_s AS 'Year(s)',
    ROUND(p.price, 2) AS 'Price',
    p.stock_level AS 'Initial Stock level',
    ROUND(p.price * p.stock_level, 2) AS 'Initial cost, £',
    jp.quantity_used AS 'Used',
    po.quantity_ordered AS 'Delivery',
    (
        p.stock_level + po.quantity_ordered - jp.quantity_used
    ) AS 'New Stock level',
    ROUND(
        (
            p.stock_level + po.quantity_ordered - jp.quantity_used
        ) * p.price,
        2
    ) AS 'Stock cost,£',
    p.stock_level_threshold AS 'Low level Threshold'
FROM
    parts p
    INNER JOIN parts_orders po ON po.part_id = p.id_part
    INNER JOIN orders o ON o.id_order = po.order_id
    INNER JOIN jobs_parts jp ON p.id_part = jp.part_id
    INNER JOIN jobs j ON j.id_job = jp.job_id
WHERE
    o.order_arrival = CURDATE()
    AND j.fix_date = CURDATE()
    AND o.status='completed'
    AND j.status='completed'
UNION
SELECT 'Total','','','','','','',SUM(p.price*p.stock_level),'','','',ROUND((p.stock_level + po.quantity_ordered - jp.quantity_used) * p.price,2),''
FROM parts p
    INNER JOIN parts_orders po ON po.part_id = p.id_part
    INNER JOIN orders o ON o.id_order = po.order_id
    INNER JOIN jobs_parts jp ON p.id_part = jp.part_id
    INNER JOIN jobs j ON j.id_job = jp.job_id
WHERE
    o.order_arrival = CURDATE()
    AND j.fix_date = CURDATE()
    AND o.status='completed'
    AND j.status='completed'
    ORDER BY 'Initial cost, £' DESC;
