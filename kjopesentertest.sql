DROP TABLE center;
DROP TABLE administration;
DROP TABLE store;
DROP TABLE bruker;
DROP TABLE customer_service;
DROP TABLE trade;

CREATE TABLE center(
center_name VARCHAR(30) NOT NULL,
muncipality VARCHAR(30) NOT NULL,
turnover SMALLINT,
nr_shops SMALLINT,
sqm SMALLINT,
address VARCHAR(30) NOT NULL,
mail VARCHAR(30),
tlf VARCHAR(30),
email VARCHAR(30),
car_park CHAR(1),
CONSTRAINT center_pk PRIMARY KEY(center_name,muncipality,address));

CREATE TABLE administration(
navn VARCHAR(30),
center_name VARCHAR(30) NOT NULL,
title VARCHAR(30),
tlf VARCHAR(30) NOT NULL,
email VARCHAR(30),
username VARCHAR(30) NOT NULL,
CONSTRAINT administration_pk PRIMARY KEY(username,tlf,center_name));

CREATE TABLE store(
store_name VARCHAR(30),
turnover SMALLINT,
trade VARCHAR(30) NOT NULL,
manager VARCHAR(30),
manager_username VARCHAR(30) NOT NULL,
location VARCHAR(30),
floor SMALLINT,
openinghrs VARCHAR(30),
openinghrs_weekend VARCHAR(30),
center_name VARCHAR(30) NOT NULL,
CONSTRAINT store_pk PRIMARY KEY(manager_username, center_name));

CREATE TABLE bruker(
access_lvl SMALLINT,
username VARCHAR(30) NOT NULL,
password VARCHAR(30),
CONSTRAINT bruker_pk PRIMARY KEY(username));

CREATE TABLE customer_service(
customer_case_ID INTEGER GENERATED ALWAYS AS IDENTITY,
center_name VARCHAR(30) NOT NULL,
subject VARCHAR(30),
question VARCHAR(2000),
answer VARCHAR(2000),
solved char(1),
CONSTRAINT customer_service_pk PRIMARY KEY (customer_case_ID,center_name));

CREATE TABLE trade(
trade VARCHAR(30) NOT NULL,
description VARCHAR(30) NOT NULL,
center_name VARCHAR(30) NOT NULL,
CONSTRAINT trade_pk PRIMARY KEY (trade,center_name));


ALTER TABLE administration
ADD CONSTRAINT administration_fk1 FOREIGN KEY(username) REFERENCES bruker(username);


INSERT INTO center(center_name, muncipality,turnover,nr_shops ,sqm ,address ,mail ,tlf ,email ,car_park)
VALUES('Sirkus','Trondheim',0, 3, 10000,'Veien 1', 'postboks 2012','12131415', 'Mailadresse@.com', 'y');

INSERT INTO center(center_name, muncipality,turnover,nr_shops ,sqm ,address ,mail ,tlf ,email ,car_park)
VALUES('City Syd','Trondheim',0,3 ,15000,'Gata 2', 'postboks 1337','12195415', 'Mailadr@asd.com', 'y');

INSERT INTO center(center_name, muncipality,turnover,nr_shops ,sqm ,address ,mail ,tlf ,email ,car_park)
VALUES('City Lade','Trondheim', 0,3 ,15000,'Gata 2', 'postboks 1337','12195415', 'Mailadr@asd.com', 'y');

INSERT INTO bruker(access_lvl,username,password)
VALUES(3,'petter1','password');

INSERT INTO bruker(access_lvl,username,password)
VALUES(3,'egil2','qwerty');

INSERT INTO bruker(access_lvl,username,password)
VALUES(2,'leif1','qwerty');

INSERT INTO administration(navn, center_name, title, tlf, email, username) 
VALUES('Petter','Circus','sjefen','12345678','mail@no','petter1');

INSERT INTO administration(navn, center_name, title, tlf, email, username) 
VALUES('Leif','Circus','Vaskern','1232278','mail12@no','leif1');

INSERT INTO store(store_name,turnover,trade,manager,manager_username,location,floor,openinghrs, openinghrs_weekend,center_name)
VALUES('Lefdal',13,'Elektronikk','Leif','leif1','høyre',1,'9-21','10-19','Circus');

INSERT INTO store(store_name,turnover,trade,manager,manager_username,location,floor,openinghrs, openinghrs_weekend,center_name)
VALUES('Clahs Ohlson',13,'Elektronikk','Egil','egil1','høyre',1,'9-21','10-19','Circus');

INSERT INTO store(store_name,turnover,trade,manager,manager_username,location,floor,openinghrs, openinghrs_weekend,center_name)
VALUES('Spakkel',13,'Kosmetikk','Steffen','steffen1','høyre',1,'9-21','10-19','City Syd');

INSERT INTO trade(trade, description, center_name)
VALUES('Elektronikk','Ting som går på strøm','Circus');

INSERT INTO trade(trade, description, center_name)
VALUES('Kosmetikk','Maling for fjes','City Syd');

