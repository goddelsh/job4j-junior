insert into bodies (id, name) values
(1, 'Седан'),
(2, 'Хэтчбек'),
(3, 'Универсал'),
(4, 'Лифтбэк'),
(5, 'Купе'),
(6, 'Кабриолет');

insert into engines (id, name) values
(1, 'Honda R20A'),
(2, 'Renault K4M'),
(3, 'Toyota 2AR-FE'),
(4, 'Hyundai/Kia G4FC'),
(5, 'Unknown Engine');

insert into gearboxes (id, name) values
(1, 'ручная'),
(2, 'автомат');


insert into cars (name, body, engine, gearbox) values
('Honda', 2, 1, 2),
('Renault', 3, 2, 2),
('Toyota', 1, 3, 1),
('Hyundai', 4, 4, 1),
('Kia', 5, 4, 2),
('old Honda', 2, 1, 1);