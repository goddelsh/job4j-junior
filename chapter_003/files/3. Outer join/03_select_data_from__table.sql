select cc.name, bb.name,  ee.name, gg.name from cars cc 
inner join engines ee on ee.id = cc.engine 
inner join bodies bb on bb.id = cc.body
inner join gearboxes gg on gg.id = cc.gearbox

select cv.name from engines cv
left join cars cr on cr.engine = cv.id
where cr is null;

select gg.name from gearboxes gg
left join cars cr on cr.gearbox = gg.id
where cr is null;

select bb.name from bodies bb
left join cars cr on cr.body = bb.id
where cr is null;