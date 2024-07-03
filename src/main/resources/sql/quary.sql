create table customer(
                         c_id varchar(20) primary key,
                         name varchar (20),
                         telephone varchar(20)
);


create table orders(
                       o_id varchar (20) primary key,
                       date date,
                       c_id varchar(20),
                       foreign key (c_id) references customer (c_id) on update cascade on delete cascade
);
//ALTER table orders
  modify column details varchar(100);

create table empolyee (
                          e_id varchar (20) primary key,
                          name varchar(20),
                          address varchar(20),
                          o_id varchar(20),
                          foreign key (o_id) references orders (o_id) on update cascade on delete cascade
);

create table item(
                     i_id varchar (20) primary key,
                     item_name varchar (20),
                     qty_on_hand varchar(20),
                     details varchar(20),
                     unit_price double precision
);

create table shelf(
                      sh_id varchar (20) primary key,
                      category varchar (20),
                      size varchar (20),
                      i_id varchar (20),
                      foreign key (i_id) references item (i_id) on update cascade on delete cascade
);


create table payement(
                         p_id varchar (20) primary key,
                         price varchar (20),
                         date date,
                         o_id varchar (20),
                         foreign key (o_id) references orders (o_id) on update cascade on delete cascade
);

create table supplier (
                          s_id varchar (20) primary key,
                          name varchar (20),
                          telephone varchar (20),
                          p_id varchar (20),
                          foreign key (p_id) references payement (o_id) on update cascade on delete cascade
);

create table supplierDetails(
                                i_id varchar (20),
                                s_id varchar (20),
                                qty varchar (20),
                                description varchar (20),
                                foreign key (i_id) references item (i_id) on update cascade on delete cascade,
                                foreign key (s_id) references supplier (s_id) on update cascade on delete cascade
);

create table orderDetails(
                             o_id varchar (20),
                             i_id varchar (20),
                             details varchar (20),
                             qty int,
                             unit_price double precision,
                             foreign key (o_id) references orders (o_id) on update cascade on delete cascade,
                             foreign key (i_id) references item (i_id) on update cascade on delete cascade
);



create table users(
                      u_id varchar(20)  primary key,
                      name varchar(20),
                      password varchar(20)
);

-----------------------------------------------------------------
INSERT INTO users VALUES ('U001','Ravishka','admin123');
------------------------------------------------------------------
INSERT INTO item VALUES ('I001','RTX3090','15','4GB',75000);
INSERT INTO item VALUES ('I002','RX600','12','8GB',13000);
INSERT INTO item VALUES ('I003','SSD samsung','16','500GB',7000);
INSERT INTO item VALUES ('I004','SSD kingston','22','250GB',5000);
INSERT INTO item VALUES ('I005','HDD seagate','18','1TB',6500);
INSERT INTO item VALUES ('I007','GTX560','30','6GB',6000);
INSERT INTO item VALUES ('I008','Samsung','12','24inch IPS Monitor',12000);
INSERT INTO item VALUES ('I009','Dell','15','32inch Monitor',15000);
INSERT INTO item VALUES ('I010','Corsair ','20','8GB RAM',7000);
---------------------------------------------------------------------------------

INSERT INTO customer VALUES ('C001','Kevin','0763423445');
INSERT INTO customer VALUES ('C002','Supun','0712545887');
INSERT INTO customer VALUES ('C003','Kaizer','077548977');
INSERT INTO customer VALUES ('C004','Lamar','0723545225');
INSERT INTO customer VALUES ('C005','Dasun','0775489778');
INSERT INTO customer VALUES ('C006','Alen','0785478663');
INSERT INTO customer VALUES ('C007','Henrick','0712545668');
----------------------------------------------------------------------------------

INSERT INTO orders VALUES ('O001','HDD Seagate 1TB * 2','2023-05-12','C002');
INSERT INTO orders VALUES ('O002','RX600 8GB * 1','2023-05-13','C004');
INSERT INTO orders VALUES ('O003','Samsung 24inch IPS Monitor * 1','2023-05-13','C006');
------------------------------------------------------------------------------------------

INSERT INTO supplier VALUES ('S001','Danushka','0712546665','RAM and HARD drives');
INSERT INTO supplier VALUES ('S002','Suneth','0763124556','Monitors');
INSERT INTO supplier VALUES ('S003','Sahan','077143625','VGA cards');


