create table order_sale (
  id varchar(100) not null,
  creation_date datetime not null,
  primary key (id)
) engine=InnoDB default charset=utf8;

create table order_sale_item (
  id varchar(100) not null,
  order_sale_id varchar(100) not null,
  product_id bigint not null,
  quantity decimal(10,2) not null,
  is_gift tinyint(1) not null,
  primary key (id),
  constraint fk_order_sale_item_order_sale foreign key (order_sale_id) references order_sale (id),
  constraint fk_order_sale_item_product foreign key (product_id) references product (id)
) engine=InnoDB default charset=utf8;