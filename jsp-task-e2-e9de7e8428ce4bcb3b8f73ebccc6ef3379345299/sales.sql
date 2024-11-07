create table products(
pid int primary key auto_increment,
product_name varchar(255) not null,
mrp decimal(10,2) not null,
rating decimal(10,2) not null,
supplier_name varchar(255) not null,
quantity integer not null
);

create table customers(
cid int primary key auto_increment,
customer_name varchar(255) not null,
customer_mobile varchar(255) not null,
city varchar(255) not null,
pid int,
foreign key (pid) references products(pid)
);


insert into customers(customer_name,customer_mobile,city,pid) values('praveen','032902302','chennai',1);
insert into customers(customer_name,customer_mobile,city,pid) values('aliya','00923','bangalre',2);
insert into customers(customer_name,customer_mobile,city,pid) values('joseph','3434','delhi',5);
insert into customers(customer_name,customer_mobile,city,pid) values('john','032902302','avadi',6);
insert into customers(customer_name,customer_mobile,city,pid) values('nitya','254354353','arakoonam',7);
insert into customers(customer_name,customer_mobile,city,pid) values('smith','2434234','saidapet',8);
insert into customers(customer_name,customer_mobile,city,pid) values('steve','423423423','gingee',3);
insert into customers(customer_name,customer_mobile,city,pid) values('alan','34345232','thiruvanamalai',4);
insert into customers(customer_name,customer_mobile,city,pid) values('jack','4343124','thirupoprur',9);
insert into customers(customer_name,customer_mobile,city,pid) values('robin','9434123','mahabalipuram',10);


insert into products(product_name, mrp, rating, supplier_name, quantity) 
values ('shampoo', 45, 3.5, 'john supplies', 20);

insert into products(product_name, mrp, rating, supplier_name, quantity) 
values ('toothpaste', 15, 4.0, 'quick supply', 50);

insert into products(product_name, mrp, rating, supplier_name, quantity) 
values ('hand sanitizer', 60, 4.5, 'safe health', 30);

insert into products(product_name, mrp, rating, supplier_name, quantity) 
values ('face wash', 85, 4.2, 'glow supplies', 25);

insert into products(product_name, mrp, rating, supplier_name, quantity) 
values ('tissue box', 20, 3.0, 'paper world', 100);

insert into products(product_name, mrp, rating, supplier_name, quantity) 
values ('detergent', 95, 3.8, 'clean house', 40);

insert into products(product_name, mrp, rating, supplier_name, quantity) 
values ('body lotion', 120, 4.7, 'glow supplies', 15);

insert into products(product_name, mrp, rating, supplier_name, quantity) 
values ('hair gel', 75, 3.9, 'john supplies', 10);

insert into products(product_name, mrp, rating, supplier_name, quantity) 
values ('shaving cream', 35, 4.1, 'quick supply', 60);

insert into products(product_name, mrp, rating, supplier_name, quantity) 
values ('dish soap', 25, 3.6, 'clean house', 80);
