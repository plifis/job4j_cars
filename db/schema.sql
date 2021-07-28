create table cars(
    id serial primary key,
    description varchar (200),
    mark varchar (50),
    body varchar (15),
    image bytea
);

create table advt(
    id serial primary key,
    isSale boolean,
    timestamp date,
    car_id int not null references cars(id)
);