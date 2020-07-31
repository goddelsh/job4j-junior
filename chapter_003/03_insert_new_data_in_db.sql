	INSERT INTO roles (id, name) VALUES
	(0, 'administator'),
	(1, 'user'),
	(2, 'guest');


	INSERT INTO users (id, login, password, name, role) VALUES
	(0, 'admin', '09e8411f66114d22bd18b5fcebed2c05', 'Administator', 0),
	(1, 'user', '58e3582afa99040e27b92b13c8f2280', 'User', 1),
	(2, 'guest', 'd367f4699214cec412f7c2a1d513fe05', 'Guest', 2);

	INSERT INTO rules (id, name) VALUES
	(0, 'all might'),
	(1, 'create tickets'),
	(2, 'read only');

	INSERT INTO roles_rules (roleId, rulesId) VALUES
	(0, 0),
	(1, 1),
	(2, 2);

	INSERT INTO categories (id, name) VALUES
	(0, 'Managment'),
	(1, 'Sellers'),
	(2, 'Support');

	INSERT INTO statuses (id, name) VALUES
	(0, 'Opened'),
	(1, 'Closed'),
	(2, 'In process');

	INSERT INTO tickets (id, name, text, creator, category, status) VALUES
	(0, 'Отчет за месяц', 'Все отделам сделать отчет за месяц по шаблону', 0, 0, 1),
	(1, 'Письмо клиенту', 'Проверить и отправить. Письмо прикреплено', 1, 1, 2),
	(2, 'Благодарность', 'Спасибо за качественные услуги', 2, 2, 0);


	INSERT INTO atachments (filename, creator, ticketId) VALUES
	('/srv/lvs/00001.pdf', 0, 0),
	('/srv/lvs/00002.docx', 1, 1);


	INSERT INTO comments (text, creator, ticketId) VALUES
	('не тяните', 0, 1),
	('можно отправить в docx', 1, 0);

