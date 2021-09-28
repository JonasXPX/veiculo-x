CREATE TABLE IF NOT EXISTS car
(
    id bigserial,
    name varchar(50) not null,
    constraint pk_car_id primary key(id)
);

CREATE TABLE IF NOT EXISTS rent
(
    id bigserial,
    id_car int8 not null,
    start_date_time timestamp not null,
    end_date_time timestamp not null,
    daily decimal(10,2),
    constraint fk_rent_id_car foreign key (id_car) references car(id)
);