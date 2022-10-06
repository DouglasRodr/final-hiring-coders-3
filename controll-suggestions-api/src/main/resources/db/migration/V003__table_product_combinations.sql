create table product_combination (
  product_id bigint not null,
  combined_product_id bigint not null,
  count bigint not null,
  active tinyint(1) not null,
  primary key (product_id, combined_product_id),
  constraint fk_product_combination_product foreign key (product_id) references product (id),
  constraint fk_product_combination_combined_product foreign key (combined_product_id) references product (id)
) engine=InnoDB default charset=utf8;