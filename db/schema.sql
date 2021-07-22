create table cars(
    id serial primary key,
    description varchar (200),
    mark varchar (50),
    body varchar (15)
    image blob
);

create table advt(
    id serial primary key,
    isSale boolean,
    car_id int not null references cars(id)
);