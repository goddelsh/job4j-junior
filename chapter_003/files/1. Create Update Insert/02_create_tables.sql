CREATE TABLE roles (
	id serial primary key,
	name varchar(128)	
);

CREATE TABLE users (
	id serial primary key,
	login varchar(32),
	password varchar(32),
	name varchar(128),
	role int references  roles(id)	
);

CREATE TABLE rules (
	id serial primary key,
	name varchar(128)	
);
CREATE TABLE roles_rules (
	id serial primary key,
	roleId int references roles(id),
	rulesId int references rules(id)	
);

CREATE TABLE categories (
	id serial primary key,
	name varchar(32)
);


CREATE TABLE statuses (
	id serial primary key,
	name varchar(32)
);

CREATE TABLE tickets (
	id serial primary key,
	name varchar(128),
	text varchar(256),
	creator int references users(id),
	category int references categories(id),
	status int references statuses(id)
);

CREATE TABLE atachments (
	id serial primary key,
	filename varchar(256) unique,
	creator int references users(id),
	ticketId  int references tickets(id)
);

CREATE TABLE comments (
	id serial primary key,
	text varchar(256),
	creator int references users(id),
	ticketId  int references tickets(id)
);
