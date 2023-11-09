insert into country values (1, 'Беларусь');

insert into city values (1, 'Гомель', 1);
insert into city values (2, 'Брест', 1);
insert into city values (3, 'Могилев', 1);
insert into city values (4, 'Минск', 1);

insert into human values (1, 'Ilya', 'Moiseenko', 'IlyaMoiseenko', null, 'IlyaMoiseenko@gmail.com', 'testPassword', 1);
insert into human values (2, 'Marina', 'Maslowa', 'MarinaMaslowa', null, 'MarinaMaslowa@gmail.com', 'testPassword', 1);
insert into human values (3, 'Kolya', 'Pinchuk', 'KolyaPinchuk', null, 'KolyaPinchuk@gmail.com', 'testPassword', 1);
insert into human values (4, 'Karina', 'Mashanova', 'KarinaMashanova', null, 'KarinaMashanova@gmail.com', 'testPassword', 1);

insert into post values (1, 1, null, 'Test description by post #1', '2023-09-27 19:58:30.2964321');
insert into post values (2, 1, null, 'Test description by post #2', '2023-09-27 19:58:30.2964321');
insert into post values (3, 2, null, 'Test description by post #3', '2023-09-27 19:58:30.2964321');
insert into post values (4, 3, null, 'Test description by post #4', '2023-09-27 19:58:30.2964321');


insert into post_like values (1, 1, '2023-09-27 19:58:30.2964321');
insert into post_like values (1, 2, '2023-09-27 19:58:30.2964321');
insert into post_like values (3, 3, '2023-09-27 19:58:30.2964321');
insert into post_like values (4, 4, '2023-09-27 19:58:30.2964321');

insert into post_comment values (1, 1, 1, 'Great!');
insert into post_comment values (2, 2, 2, 'You are like a drop of water in the desert');
insert into post_comment values (3, 2, 1, 'Irresistable');
insert into post_comment values (4, 3, 3, 'Good');

INSERT INTO story VALUES (1, 1, null, 'photo', 'Test description by story #1', '2023-10-01 19:58:30.2964321');
INSERT INTO story VALUES (2, 1, null, 'video', 'Test description by story #2', '2023-10-01 19:59:30.2964321');
INSERT INTO story VALUES (3, 1, null, 'video', 'Test description by story #3', '2023-10-01 20:00:30.2964321');
INSERT INTO story VALUES (4, 2, null, 'photo', 'Test description by story #4', '2023-10-11 12:58:30.2964321');
INSERT INTO story VALUES (5, 2, null, 'video', 'Test description by story #5', '2023-10-12 13:58:30.2964321');
INSERT INTO story VALUES (6, 1, null, 'photo', 'Test description by story #6', '2023-11-06 23:58:30.2964321');
INSERT INTO story VALUES (7, 1, null, 'photo', 'Test description by story #7', '2023-11-06 23:58:30.2964321');


INSERT INTO story_comment VALUES (1, 2, 1, 'Great!');
INSERT INTO story_comment VALUES (2, 2, 2, 'Hi!!!');
INSERT INTO story_comment VALUES (3, 4, 1, 'Cool!');
INSERT INTO story_comment VALUES (4, 3, 3, 'Good');
INSERT INTO story_comment VALUES (5, 1, 4, 'Good');

INSERT INTO story_like VALUES (1, 1, '2023-09-27 19:58:30.2964321');
INSERT INTO story_like VALUES (1, 2, '2023-09-27 19:58:30.2964321');
INSERT INTO story_like VALUES (3, 3, '2023-09-27 19:58:30.2964321');
INSERT INTO story_like VALUES (4, 4, '2023-09-27 19:58:30.2964321');
INSERT INTO story_like VALUES (2, 4, '2023-09-27 19:58:30.2964321');
