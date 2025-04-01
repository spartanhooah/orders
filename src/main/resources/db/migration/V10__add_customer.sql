create table customer(
    id bigint not null auto_increment primary key,
    name varchar(50),
    street_address varchar(30),
    city varchar(30),
    state varchar(30),
    zip_code varchar(30),
    phone varchar(20),
    email varchar(255),
    created_date timestamp,
    last_modified_date timestamp
) engine=InnoDB;

alter table order_header add column customer_id bigint;

alter table order_header add constraint order_customer_fk foreign key (customer_id) references customer(id);

alter table order_header drop column customer_name;

insert into customer (name, street_address, city, state, zip_code, phone, email)
values ('Big Betty', '955 Puulena Dr.', 'San Antonio', 'WI', '49085', '588-8086', 'betty@big.com');

update order_header set customer_id = (select id from customer where name = 'Big Betty');