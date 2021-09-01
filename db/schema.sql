create table users(
    id serial primary key,
    name varchar (100) NOT NULL,
    email varchar (50) NOT NULL UNIQUE,
    password varchar (20) NOT NULL
);

create table cars(
                     id serial primary key,
                     description varchar (1000),
                     mark varchar (50) NOT NULL,
                     body varchar (15) NOT NULL,
                     image varchar (255)
);

create table advts(
                      id serial primary key,
                      isSale boolean,
                      created timestamp,
                      price real,
                      car_id int not null references cars(id)
);

create table authors(
    id serial primary key,
    advt_id int references advts(id),
    user_id int references users(id)
);

