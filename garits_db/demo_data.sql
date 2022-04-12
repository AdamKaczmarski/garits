INSERT INTO Users (email,password,first_name,last_name) VALUE
( "Penelope", "$2a$12$Hp.RX7Yi2iIoO2NR.9mjTOuHCSbgIc9GISZUcqcu4Ln6KvFXVFjUS", "Penelope", "Carr"),
( "Sunny", "$2a$12$UXEQtBGGH4IvP1g.LFMzlOG.sJIC2qV3.X9.77al7H4IgL7xgpSVe",  "Sunny", "Evans"),
( "Glynne", "$2a$12$3QclwhUhO9mmBQ/EpcJj..a0i2FSvUWVU.eOG7g1.OuDTm8QOU7n6",  "Glynne", "Lancaster"),
( "SYSDBA", "$2a$12$z7pP8c5N74qjF0XJKdSxn.XF81dCIdVoj8b8gXpYshchhwwu5tIr6",  "Admin", "Role"),
( "Gavin", "$2a$12$4ZHJdwfLewmQQkdJ14VTAu5oywiPx/SzcEqMqrMGd0Pontxwz3mrO",  "Gavin", "Ross"),
( "Anthony", "$2a$12$J3o7jF0Zdd7czHPyPA.wSO/H3PJ/s1Yi7yRoVLb4WvjTHPfqg7C1G",  "Anthony", "Wild"
);
UPDATE users set email=LOWER(email);

INSERT INTO Customers (name,city,address,postcode,telephone_number,email,fax,fixed_discount) VALUE
( "Jack Varta", "Bucks", "Transco Gas Supplies, Methane Buildings, Sulphur Lane, Stenchville", "HP19 2MT",  "01494683725", "JackVarta@gmail.com", NULL, NULL),
( "John Doherty", "Nowhereshire", "Miscellaneous House, Unknown Street, Whichville", "MT1 2UP", "01010100101", "JohnDoherty@gmail.com", NULL, NULL),
( "William Gates", "Richville", "World Domination House, Enormous Street", "NW10 4AT", "02074773333", "WilliamGates@gmail.com", NULL, NULL), 
( "Jean-Claude Laprie", "Brokenglasshire", "10 Green Bottles", "NW9 NO8", "02083334444", "JeanClaudeLaprie@gmail.com", NULL, NULL
) ;

INSERT INTO vehicles ( id_reg_no,manufacturer,model,engine_serial_number,chassis_number,colour,last_mot) VALUE
( "AA69 CPG", "Fjord Transit Van", "DONTKNOW", "DONTKNOW1","DK1", "White", "2019-12-5"),
( "CT70 DWR", "Fjord Transit Van", "DONTKNOW", "DONTKNOW2","DK2", "White", "2020-12-12"),
( "FF71 GHT", "Fjord Transit Van", "DONTKNOW", "DONTKNOW3","DK3", "White", "2021-2-3"),
( "VV71 BHN", "Fjord Transit Van", "DONTKNOW", "DONTKNOW4","DK4", "White", "2021-11-19"),
( "GG14 PUB", "Fjord Estate MK8", "DONTKNOW", "DONTKNOW5","DK5", "Green", "2014-1-1"),
( "BB19 OLE", "Rolls Royce", "Silver Shadow", "DONTKNOW6","DK6", "Red", "2019-1-1"),
( "BB67 TRU", "Fjord Estate", "Ficus", "DONTKNOW7","DK7", "Brown", "2020-11-5"
);


INSERT INTO Parts (part_name,part_type,code,manufacturer,vehicle_type,year_s,price,stock_level,stock_level_threshold) VALUE
( "heavy tread", "Tyre",485649, "Fjord", "Fjord Transit Van", "2020", 45.00, 8, 6),
( "Exhaust", "Exhaust", 456832, "Fjord", "Fjord Estate", "2020", 200.00, 3, 2),
( "Engine Mounts", "Engine Mount", 475132, "Fjord", "All", "2020", 15.00, 6, 4),
( "Spark Plugs", "Spark Plug", 436227, "Fjord", "All", "2020", 1.50, 23, 20),
( "Spark Leads", "Spark Lead", 485935, "Fjord", "All", "2020", 12.50, 16, 10),
( "Distributor Cap", "Distributor Cap", 448225, "Fjord", "Fjord vehicles", "2020", 35.00, 10, 5),
( "Arrogant Red", "Paint", 466845, "Fjord", "All", "2020", 60.00, 3, 2),
( "Interior Bulb", "Bulb", 468134, "Fjord", "Rolls Royce", "2020", 118.00, 2, 1),
( "Motor Oil", "Oil", 479915, "Fjord", "All", "2020", 25.00, 30, 25),
( "Oil Filter", "Oil Filter", 418533, "Fjord", "All", "2020", 10.00, 16, 15),
( "Air Filter", "Air Filter", 485424, "Fjord", "All", "2020", 15.00, 15, 10);

INSERT INTO Jobs (vehicle_id,status,description_done,description_required,est_time_min,act_time_min,create_date,booking_date,fix_date,bay) VALUE 
( 7, "completed", " replacement of the exhaust system, 2 new engine mounts, 4 new sparkplugs, new set of spark plug leads, new distributor cap", "Annual MoT", 120, 120, "2021-11-5", "2021-11-5",  "2021-11-5", "repair");

INSERT INTO Services (service_name,service_price,approx_time_min,short_description) VALUE
( "MoT", 50.00, 120, "Annual MoT");

INSERT INTO Payments (id_payment,cash_or_card,amount,create_date,payment_date,payment_due) VALUE
(1, "Cash", 50.00, "2021-11-5", "2021-11-5", "2021-11-5");

insert into roles (role_name,hourly_rate) values ('ROLE_MECHANIC',105),("ROLE_FRANCHISEE",null), ("ROLE_RECEPTIONIST",null), ("ROLE_FOREPERSON",125),("ROLE_ADMIN",null);

INSERT INTO Users_Roles(user_id,role_id) VALUE
(1,3),
(2,4),
(3,2),
(4,5),
(5,1),
(6,1);

INSERT INTO Users_Jobs(user_id,job_id) VALUE
(5,1);

INSERT INTO Jobs_Services(job_id,service_id) VALUE
(1,1);

INSERT INTO Jobs_Parts(part_id,job_id,quantity_used) VALUE
(2,1,1),
(3,1,2),
(4,1,4),
(5,1,1),
(6,1,1);

INSERT INTO Customers_Vehicles(customer_id,reg_no_id) VALUE
(1,"AA69 CPG"),
(1,"CT70 DWR"),
(1,"FF71 GHT"),
(1,"VV71 BHN"),
(2,"GG14 PUB"),
(3,"BB19 OLE"),(4,"BB67 TRU");

INSERT INTO Parts_Payments(part_id,quantity_sold,payment_id) VALUE
(2,1,1),
(3,2,1),
(4,4,1),
(5,1,1),
(6,1,1);

INSERT INTO Payments_Customer(payment_id,customer_id) VALUE
(1,4);

INSERT INTO Orders(status,description,order_date,order_arrival,order_amount) VALUE
("completed", "Exhaust x1, Engine Mounts x2, Spark Plugs x4","2021-11-5","2021-11-5",236.00);

INSERT INTO Parts_Orders(part_id,order_id,quantity_ordered) VALUE
(2,1,1),
(3,1,2),
(4,1,4);

INSERT INTO Jobs_Payments(job_id,payment_id) VALUE
(1,1);



