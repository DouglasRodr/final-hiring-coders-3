create table product (
  id bigint not null,
  name varchar(150) not null,
  active tinyint(1) not null,
  quantity_sold decimal(10,2) not null,
  primary key (id)
) engine=InnoDB default charset=utf8;