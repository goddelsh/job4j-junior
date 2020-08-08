CREATE TABLE post (
	id serial primary key,
	name varchar(256),
	text varchar(2000),
	link varchar(256) unique,
	created_date timestamp 
);
