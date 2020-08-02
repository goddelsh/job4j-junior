select * from product where type_id = (select id from type where name = 'СЫР') 

select * from product where name like '%мороженное%';

select * from product where expired_date >= current_date + INTERVAL '1' MONTH  and expired_date < current_date + INTERVAL '2' MONTH;

select * from product where price = (select max(price) from product);

select count(*) from product group by type_id;

select * from product where type_id in (select id from type where name = 'СЫР' or name = 'МОЛОКО');

select t.name from type t where t.id = (select p.type_id from product having group by p.type_id having count(p.id) < 10);

select * from product 
left outer join type tt on tt.id = type_id and tt.name = 'СЫР' or tt.name = 'МОЛОКО'

select t.name from type t 
left outer join product p on t.id = p.type_id
where count(p.id) < 10

where t.id = (select p.type_id from product having group by p.type_id having count(p.id) < 10);