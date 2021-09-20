create table cart_items
(
    id                bigserial primary key,
    user_id           bigint,
    product_id        bigint,
    quantity          int
);

create table order_items
(
    id              bigserial primary key,
    user_id         bigint,
    product_id      bigint,
    quantity        int,
    price           float,
    amount          float,
    paid            timestamp,
    delivered       timestamp
);

