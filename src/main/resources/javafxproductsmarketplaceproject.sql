-- 20200307
use productsfrommarketplace_schema;

select * from user;
select * from product;
truncate product;
describe product;

alter table product drop id;
alter table product add column id int not null primary key auto_increment;


delete from user where id in(5,6,7);
insert into product(description, name, price, quantity) values ('Iphone X 128GB 2019','Iphone',2500,4);
insert into product(description, name, price, quantity) values ('Samsung Galaxy J5 32GB','Samsung',1900,2);
insert into product(description, name, price, quantity) values ('Samsung Galaxy S8 Plus 256GB 2017','Samsung',2000,1);