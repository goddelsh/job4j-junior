CREATE TABLE meetings (
	id serial primary key,
	name varchar(32)
);

CREATE TABLE users (
	id serial primary key,
	name varchar(32)
);

CREATE TABLE participants (
	id serial primary key,
	userId int references users(id) not null,
	meetingsId int references meetings(id) not null,
	status boolean not null default false,
	unique (userId, meetingsId)
);

select m.name, count (p.userId) from participants p
left join meetings m on m.id = p.meetingsId
where p.status = true group by m.name


select m.name from meetings m 
left join participants p on p.meetingsId = m.id 
where  p.meetingsId is null
group by m.name