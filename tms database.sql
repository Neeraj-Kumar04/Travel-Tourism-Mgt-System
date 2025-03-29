-- create database

create database tms;

-- use this database

use tms;

-- create table for signup class

CREATE TABLE account (
    username VARCHAR(30),
    name VARCHAR(40),
    password VARCHAR(30),
    question VARCHAR(100),
    answer VARCHAR(50)
);


-- create table to add new customer in new customer class

CREATE TABLE customer (
    username VARCHAR(30),
    id_type VARCHAR(20),
    number VARCHAR(20),
    name VARCHAR(30),
    gender VARCHAR(15),
    country VARCHAR(20),
    address VARCHAR(50),
    phone VARCHAR(20),
    email VARCHAR(40)
);

-- create table to add hotels

CREATE TABLE hotels (
    name VARCHAR(30),
    cost_per_day VARCHAR(20),
    food_charges VARCHAR(20),
    ac_charges VARCHAR(20)
);

-- insert value in the hotel table

insert into hotels values("JW Marriott Hotel", "2000", "2500", "3000");

insert into hotels values("Four Seasons Hotel", "1200", "1900", "2200");


-- create table for book hotel

CREATE TABLE bookHotel (
    username VARCHAR(30),
    name VARCHAR(30),
    persons VARCHAR(20),
    days VARCHAR(20),
    ac VARCHAR(10),
    food VARCHAR(10),
    id VARCHAR(30),
    number VARCHAR(20),
    phone VARCHAR(20),
    cost VARCHAR(20)
);


-- create table for package booking

CREATE TABLE bookPackage (
    username VARCHAR(30),
    package VARCHAR(40),
    persons VARCHAR(20),
    id VARCHAR(30),
    number VARCHAR(20),
    phone VARCHAR(20),
    price VARCHAR(20)
);



