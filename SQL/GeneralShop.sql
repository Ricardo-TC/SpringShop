 drop database if exists OnlineShop;
 create database if not exists OnlineShop;
 use OnlineShop;

 create table `Department`(
	`dep_id` int primary key auto_increment not null,
    `dep_name` varchar(70) not null
);
 
 create table `Product`(
	`prod_id` int primary key auto_increment not null,
    `name` varchar(70) not null,
    `cost` float not null,
    `price` float not null,
    `description` varchar(80) default 'Without description',
    `dep_id` int,
	foreign key (`dep_id`) references `Department`(`dep_id`)
    );
    
create table `Discount`(
	`disc_id` int primary key not null auto_increment,
	`prod_id` int not null,
    `discount_type` varchar(1) default ('NA'),
    `discount_amount` varchar(5) default ('NA'),
    `date_begin` date not null,
	`date_expire` date not null,
    foreign key (`prod_id`) references `Product`(`prod_id`)
);

create table `Ticket`(
	`ticket_id` int primary key not null auto_increment,
    `total` float default 0,
    `date` date default(current_date)
);

create table `Cart`(
	`id` int primary key auto_increment not null,
    `ticket_id` int not null,
    `prod_id` int not null,
    `quantity` int not null,
    `subtotal` float not null,
    foreign key(`prod_id`) references `Product`(`prod_id`),
    foreign key(`ticket_id`) references `Ticket`(`ticket_id`)
);

#create table `Product_department`(
#	`id` int primary key auto_increment not null,
#	`prod_id` int not null,
#   `dep_id` int not null,
#  foreign key(`prod_id`) references `Product`(`prod_id`),
#   foreign key(`dep_id`) references `Department`(`dep_id`)
#);

insert into `department`(`dep_name`) values
("Abarrotes"),#1Groceries
("Frutas y Vegetales"),#2Fruits and Vegetables
("Panaderia"),#3Bakery
("Pescaderia"),#4Fishery
("Carniceria"),#5Butcher
("Farmacia"),#6Farmacy
("Ferreteria"),#7Hardware store
("Electronica"),#8Electronic
("Linea Blanca"),#9White Goods
("Muebles"),#10Furniture
("Ropa"),#11Cloths
("Importados");#12Imported Goods


insert into `Product`(`name`,`cost`,`price`,`description`,`dep_id`) values
('Sabritas saladas',12,14.50,'Papas fritas sabor sal',1),
('Sabritas limon',12,14.50,'Papas fritas sabor sal',1),
('Coca-Cola 1.5lts',20,25.00,'Refresco cocacola de 1.5 litros',1),
('Coca-Cola 2lts',25,32.00,'Refresco cocacola de 2 litros',1),
('Naranja',12.5,15.25,'Saco de naranjas para jugo',2),
('Dona glaceada',4.5,9.50,'Dona glaceado de sabor variado',3),
('Atun',120,155.59,'Unidad de pescado',4),
('Lomo de res',122,158.55,'Kilo de carne de res',5),
('Vitaminas',34.52,66.78,'Bote de pastillas con 50 piezas',6),
('Destornillador de cruz',23.32,31.50,'Destornillador de cruz de 5 pulgadas de largo',7),
('Pantalla Led 41 plg',7451.11,8999.99,'Pantalla LED de 21 pulgadas marca LG sin marco',8),
('Refrigerador 11 pies',6453.22,7432.99,'Refrigerador MABe de 11 pies color gris',9),
('Sillon reclinable negro',1221.12,3215.99,'Sillon individual reclinable color negro, palanca lateral',10),
('Pantalon mezclilla',66.55,225.55,'Pantalon de mezclilla generica sin marca',11),
('Vino español',82,104.22,'Vino tinto español, 750ml',12),
('Ramen Tonkotsu',17,21.60,'Sopa de fideos con sabor a cerdo',12);

#insert into `product_department`(`prod_id`,`dep_id`)values
#(1,1),(2,1),(3,1),(4,1),(5,2),(6,3),(7,4),(8,5),(9,6),(10,7),(11,8),(12,9),(13,10),(14,11),(15,12),(16,12);

#####---UPDATE COMPLETE PRODUCT 
delimiter //
create procedure `updateProduct`(in `id` int, in `nname` varchar(70), in `ncost` float, in `nprice` float, 
	in `ndescription` varchar(80), in `ndep_id` int)
begin
update `Product` 
set 
	`name` = `nname`,
    `cost` = `ncost`,
    `price` = `nprice`,
    `description` = `ndescription`,
    `dep_id` = `ndep_id`
where `prod_id` = `id`;
end //
delimiter ;

#####---UPDATE DEPARTMENT
delimiter //
create procedure `updateDepartment`(in `id` int, in `name` varchar(70))
begin
update `Department`
set
	`dep_name` = `name`
where `dep_id` = `id`;
end //
delimiter ;

#####---NEW PURCHASE
delimiter //
create procedure `newTicket`(out id int)
begin
insert into `ticket`() values();
set id = last_insert_id();
end //
delimiter ;

#####---ADDING PRODUCTS TO CART
delimiter //
create procedure `addToCart`(in `ticket` int,in `prod` int,in `qtity` int,in `stotal` float)
begin
insert into `Cart`(`ticket_id`, `prod_id`,`quantity`,`subtotal`)values(`ticket`,`prod`,`qtity`,`stotal`);
end //
delimiter ;

#####---TOTAL PURCHASE, FINISHING PURCHASE
delimiter //
create procedure `totalPurchase`(in `ticket` int, out `ttl` float)
begin
select sum(`subtotal`) into `ttl` from `cart` where `ticket_id` = `ticket`;
update `ticket` set `total` = `ttl` where `ticket_id`=`ticket`;
end //
delimiter ;

#####---UPDATE DISCOUNT PRODUCT
delimiter // 
create procedure `updateProductDiscount`(in `id` int,in `p_id` int,in `dis_type` varchar(1),in `dis_amount` varchar(5),  
	in `d_begin` date,in `d_expire` date)
begin
update `Discount`
set
	`prod_id` = `p_id`,
	`discount_type` = `dis_type`,
    `discount_amount` = `dis_amount`,
    `date_begin` = `d_begin`,
    `date_expire` = `d_expire`
where `disc_id` = `id`;
end //
delimiter ;

delimiter //
create procedure `getDiscountByProduct`(in id int)
begin
select `disc_id`,`prod_id`,`discount_type`,`discount_amount`,`date_begin`,`date_expire` from 
`discount` where `prod_id`=`id`;
end //
delimiter ;

delimiter // 
create procedure `getDiscountByDate`(in `id` int,in `datenow` date)
begin
select `disc_id`,`prod_id` ,`discount_type`,`discount_amount`,`date_begin`,`date_expire`
from `discount` where `prod_id`=`id` and `date_begin`<=`datenow` and `date_expire`>=`datenow`;
end //
delimiter ;