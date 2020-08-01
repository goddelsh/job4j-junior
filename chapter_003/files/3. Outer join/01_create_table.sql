CREATE TABLE bodies (
	id serial primary key,
	name varchar(32)
);

CREATE TABLE engines (
	id serial primary key,
	name varchar(32)
);

CREATE TABLE gearboxes (
	id serial primary key,
	name varchar(32)
);

CREATE TABLE cars (
	id serial primary key,
	name varchar(32),
	body int references bodies(id),
	gearbox int references gearboxes(id), 
	engine int references engines(id)	
);
