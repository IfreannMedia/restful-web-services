--create table users ()
-- user table
insert into user values(10001, sysdate(), 'Bob');
insert into user values(10002, sysdate(), 'Nolan');
insert into user values(10003, sysdate(), 'Richard');

-- Post table
insert into post values(1, 'it''s my turn partner!', 10003);
insert into post values(2, 'feeling totally fine', 10002);
insert into post values(3, 'a method should be short, even shorter than that!', 10001);