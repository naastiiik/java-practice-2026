create table Product (
	id serial unique not null,
	name char(20) not null,
	price integer check(price >= 0)
);

insert into Product (name, price) values ('milk', 100);
insert into Product (name, price) values ('coffee', 80);
insert into Product (name, price) values ('tea', 200);