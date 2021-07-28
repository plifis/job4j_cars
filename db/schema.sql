create table users(
    id serial primary key,
    name varchar (100),
    email (50),
    password (20)
);

create table authors(
    id serial primary key,
    advt_id int NOT NULL,
    user_id int NOT NULL
);

create table cars(
    id serial primary key,
    description varchar (200),
    mark varchar (50),
    body varchar (15),
    image bytea
);

create table advts(
    id serial primary key,
    isSale boolean,
    created timestamp,
    price real,
    author_id int NOT NULL,
    car_id int not null references cars(id)
);