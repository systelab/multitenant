create table if not exists products
(
    id        int4        not null,
    name      varchar(45) not null,
    tenant_id varchar(45) not null,
    primary key (id)
);
