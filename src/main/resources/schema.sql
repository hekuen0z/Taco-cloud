create table if not exists taco_Order (
    id identity primary key,
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
    id identity primary key,
    name varchar(50) not null,
    taco_order bigint not null,
    taco_order_key bigint not null,
    created_at timestamp not null,
    FOREIGN KEY (taco_order) REFERENCES TACO_ORDER(id)
);
create table if not exists ingredient (
    id varchar(4) unique not null,
    name varchar(25) not null,
    type varchar(10) not null,
    PRIMARY KEY (id)
);
create table if not exists ingredient_Ref (
    ingredient varchar(4) not null,
    taco bigint not null,
    taco_key bigint not null,
    PRIMARY KEY (ingredient, taco, taco_key),
    FOREIGN KEY (ingredient) REFERENCES INGREDIENT(id)
);
create table if not exists "user" (
    "id" identity,
    "username" varchar(20) not null,
    "password" varchar(255) not null,
    "fullname" varchar(20) not null,
    "street" varchar(20),
    "city" varchar(15),
    "state" varchar(20),
    "zip" varchar(20),
    "phone_number" varchar(20)
);
-- alter table Taco add foreign key (taco_order) references Taco_Order(id);
-- alter table Ingredient_Ref add foreign key (ingredient) references Ingredient(id);