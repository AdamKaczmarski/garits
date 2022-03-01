-- This file contains queries for the ER Diagram required in the first deliverable of the project. (2 Selects, 2 Inserts, 2 Updates, 2 Deletes and 2 Reporting queries based on the scenario)
-- Select 1
SELECT c.name AS 'Customer', CONCAT(v.manufacturer,' ',v.model) AS 'Car', v.id_reg_no AS 'Registration Number' from customers c inner join customers_vehicles cv on cv.customer_id=c.id_customer inner join vehicles v on v.id_reg_no=cv.reg_no_id order by 1,2;
-- Select 2
SELECT part_name AS 'Part',part_type AS 'Type', code 'Part code', manufacturer AS 'Car Manufacturer', price AS 'Price', stock_level AS 'Quantity', ROUND(price*stock_level) AS 'Stock cost' FROM parts order by 'Stock cost';
-- Update 1

-- Update 2

-- Insert 1
INSERT INTO Parts ( part_name, part_type, code, manufacturer, vehicle_type, year_s, price, stock_level, stock_level_threshold) VALUES ( 'Airtex Water Block', 'Water Pump', '475861', 'Hyundai', 'Tucson', 2020, 36.00, 12, 4);
-- Insert 2
INSERT INTO Customers (name, address,city,postcode,telephone_number,email,fax,fixed_discount,flex_discounts_json) VALUES ('The Car Company','Top Gear Street 17','London','E1 15ER','1234567890','car_company@gmail.com',null,7,"{{ rangeFrom: 10000, discount: 5 },{ rangeFrom: 20000, discount: 10 }}");
-- Delete 1 -  Deleting overpriced services (services that cost >1000)
DELETE FROM services where service_price>1000;
-- Delete 2 - Deleting cancelled orders
DELETE FROM orders where status='CANCELLED';
-- Report 1 (Job Sheet)

-- Report 2 (Spare Parts / Stock Level)

