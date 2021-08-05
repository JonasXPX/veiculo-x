CREATE TABLE brand
(
    id     bigserial,
    "name" varchar(100),
    constraint pk_id_brand primary key (id),
    constraint uq_name_brand UNIQUE ("name")
)