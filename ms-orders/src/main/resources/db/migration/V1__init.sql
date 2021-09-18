create table order_items
(
    id                bigserial primary key,
    cart_id           bigint
);

create table cart_items
(
    id                bigserial primary key,
    user_id           bigint,
    product_id        bigint,
    quantity          int
);
