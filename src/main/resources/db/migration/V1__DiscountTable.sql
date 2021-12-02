CREATE EXTENSION "uuid-ossp";

CREATE TABLE ItemCategory(
    Id int primary key not null,
    Category varchar(50) not null
);

CREATE TABLE UserTypes(
  Id int primary key not null,
  UserType varchar(50) not null unique
);

CREATE TABLE RulesAppliesTo(
    Id INT PRIMARY KEY NOT NULL,
    ApplyTo varchar(50) not null
);

CREATE TABLE DiscountsTypes(
  Id int PRIMARY KEY not null,
  Name varchar(50) not null
);

CREATE TABLE Items (
    Id int NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223 CACHE 200 ),
    CategotId int references ItemCategory (Id) not null,
    ItemName varchar(50) not null,
    UnitPrice decimal not null DEFAULT 0.00
);

CREATE TABLE Users (
       Id int NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223 CACHE 200 ),
       Name varchar(100) not null,
       UserTypeID int references UserTypes (Id) not null,
       MembershipDate timestamp without time zone DEFAULT now()
);

CREATE TABLE Discounts (
  Id uuid primary key not null default uuid_generate_v4(),
  UserTypeId int references UserTypes (Id) not null,
  DiscountTypeId int references DiscountsTypes (Id) not null,
  DiscountValue decimal not null default 0.00,
  RuleAppliesToId int references RulesAppliesTo (Id) not null
);