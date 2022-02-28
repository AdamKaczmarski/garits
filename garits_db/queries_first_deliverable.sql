-- This file contains queries for the ER Diagram required in the first deliverable of the project. (2 Selects, 2 Inserts, 2 Updates, 2 Deletes and 2 Reporting queries based on the scenario)
-- Select 1
SELECT Parts.id_part, Parts_Orders.part_id
FROM Parts
INNER JOIN Parts_Orders ON Parts.id_part = Parts_Orders.part_id;
-- Select 2
SELECT part_name, code, manufacturer, price FROM parts;
-- Update 1

-- Update 2

-- Insert 1
INSERT INTO Parts (id_part, part_name, part_type, code, manufacturer, vehicle_type, year_s, price, stock_level, stock_level_threshold)
VALUES (00000000095, 'Airtex Water Block', 'Water Pump', '475861', 'Hyundai', 'Tucson', 2020, 36.00, 12, 4);
-- Insert 2
INSERT INTO Parts (id_part, part_name, code, manufacturer, vehicle_type, year_s, price, stock_level, stock_level_threshold)
VALUES (00000000601, 'Ford Grill', 'G781-94PQ', 'Ford', 'Fiesta', '2017', 109.99, 6, 3);
-- Delete 1

-- Delete 2

-- Report 1 (Job Sheet)

-- Report 2 (Spare Parts / Stock Level)
