use canteen_management_78150;
CREATE TABLE Vendor(
vendor_id integer PRIMARY KEY unique,
vendor_name varchar(255) not null,
vendor_shop varchar(255) not null,
vendor_phone varchar(255) not null,
vendor_spec varchar(255) not null
);
INSERT INTO Vendor VALUES
(1,'BangPD','Subway Veg','9123456789','Vegetarian'),
(2,'Jimmy','MC Donalds','9876543219','Non-Vegetarian'),
(3,'Tim','Cakes & Co.','9080706050','Coffee and Pastery'),
(4, 'Justin', 'Startbucks','9030123455', 'Coffee and Pastery');

CREATE TABLE Customer(
customer_id integer(25) Primary Key unique,
customer_name varchar(25) not null,
customer_phone varchar(25) not null,
customer_email varchar(25) not null,
customer_walletbal integer not null,
customer_password varchar(25) not null,
customer_coupon varchar(10)
);
INSERT INTO Customer VALUES
(1,'Johns_12','9080701306','Johns@gmail.com', 10000,'Johns12','1411'),
(2,'Jinny_04','9070701306','Jinny@gmail.com', 5500,'Jinny04','0412'),
(3,'Yoongi_09','9060701306','Yoongi@gmail.com', 8700,'Yoongi09','0903'),
(4,'Hobi_18','9050701306','Hobi@gmail.com',9000,'Hobi18','1802')
;

 
CREATE TABLE Menu(
food_id integer PRIMARY KEY unique,
food_name varchar(222) not null,
food_price integer not null,
vendor_id integer) ;
 
INSERT INTO Menu VALUES
(201,'Veggie cheesey burgur',320,1),
(202,'Chicken sweet corn Pizza',300,2),
(203,'Special Subway Pizza',350,1),
(204,'Cheesy Chicken Sticks',259,2),
(205,'Cold pressed Expresso',80,4),
(206,'Chocolate crust',85,4),
(207,'Cold Coffee',100,4),
(208,'Ice Americano',250,3),
(209,'Creamy butter biscuits',220,3),
(210,'Veg burger',180,1),
(211,'Veg cheesy pizza',180,1),
(212,'Chicken tender burger',180,2),
(213,'Fish chips',180,2);

CREATE TABLE OrderDetails(
order_no integer PRIMARY KEY auto_increment,
vendor_id integer,
customer_id integer(25),
food_id integer not null,
quantity integer not null,
order_date datetime default now(),
order_value integer not null,
order_status varchar(25));

 
ALTER TABLE OrderDetails ADD FOREIGN KEY(vendor_id) references Vendor(vendor_id);
ALTER TABLE OrderDetails ADD FOREIGN KEY(customer_id) references Customer(customer_id);
ALTER TABLE OrderDetails ADD FOREIGN KEY(food_id) references Menu(food_id);

INSERT INTO OrderDetails VALUES(1,1,1,201,2, '2022-04-15 01:00:00',640,'Accepted');
INSERT INTO OrderDetails VALUES(2,1,2,202,1, '2022-04-14 02:15:00',300,'Accepted');
INSERT INTO OrderDetails VALUES(3,2,3,206,1, '2022-04-21 03:00:00',85,'Accepted');
INSERT INTO OrderDetails VALUES(4,2,4,206,1, '2022-04-21 03:00:00',85,'Accepted');
INSERT INTO OrderDetails VALUES(5,3,1,209,1, '2022-04-21 05:00:12',220,'Accepted');
INSERT INTO OrderDetails VALUES(6,3,2,209,1,'2022-04-21 06:00:12',220,'Accepted');
INSERT INTO OrderDetails VALUES(7,4,1,209,1,'2022-04-21 06:00:12',220,'Rejected');

create table Login(
username varchar(50) primary key,
password varchar(50) not null,
usertype varchar(15) not null
);

insert into Login values
('Johns_12', 'Johns12', 'customer'),
('Jinny_04', 'Jinny04', 'customer'),
('Yoongi_09','Yoongi09','customer'),
('Hobi_18','Hobi18', 'customer'),
('Jimmy', 'Jimmy13', 'vendor'),
('Tim', 'Tim30', 'vendor'),
('Justin', 'Justin01', 'vendor'),
('BangPD', 'Bangpd13', 'vendor');


select * from orderDetails;
select * from menu;
select * from customer;
select * from vendor;
select * from login;
# drop database canteen_management_78150;

select * from Menu where vendor_id in (select vendor_id from vendor where vendor_spec = "Vegetarian" );
select * from Menu where vendor_id in (select vendor_id from vendor where vendor_name = "Jimmy" );
SELECT * FROM  Vendor WHERE vendor_spec = "Vegetarian" ;
# where dept_no = (select dept_no from emp where e_name = 'Smith') and e_name <> 'Smith'; # correct
