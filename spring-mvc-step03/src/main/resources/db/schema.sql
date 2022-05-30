DROP TABLE IF exists todo Cascade ;

CREATE TABLE todo (
   id INTEGER IDENTITY PRIMARY KEY,
   user_name VARCHAR (30),
   description varchar (200),
   target_date date ,
   isDone char(1) default 'N'
);