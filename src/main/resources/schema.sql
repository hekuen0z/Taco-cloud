create table if not exists taco_order (
    id identity,
    delivery_Name varchar(50) not null,
    delivery_Street varchar(50) not null,
    delivery_City varchar(50) not null,
    delivery_State varchar(2) not null,
    delivery_Zip varchar(10) not null,
    cc_number varchar(16) not null,
    cc_expiration varchar(5) not null,
    cc_cvv varchar(3) not null,
    placed_at timestamp not null
);
create table if not exists taco (
    id identity,
    name varchar(50) not null,
    taco_order bigint,
    taco_order_key bigint,
    created_at timestamp not null,
    FOREIGN KEY (taco_order) REFERENCES TACO_ORDER(id)
);
create table if not exists ingredient (
    id varchar(4) unique not null,
    name varchar(25) not null,
    type varchar(10) not null,
    PRIMARY KEY (id)
);
create table if not exists ingredient_taco (
    ingredient_id varchar(4) not null,
    taco_id bigint not null,
    PRIMARY KEY (ingredient_id, taco_id),
    FOREIGN KEY (ingredient_id) REFERENCES INGREDIENT(id)
);
create table if not exists user_table (
    id identity,
    username varchar(20) not null,
    password varchar(255) not null,
    fullname varchar(20) not null,
    street varchar(20),
    city varchar(15),
    state varchar(20),
    zip varchar(20),
    phone_number varchar(20)
);
-- alter table Taco add foreign key (taco_order) references Taco_Order(id);
-- alter table Ingredient_Ref add foreign key (ingredient) references Ingredient(id);