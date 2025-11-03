---

# E-Commerce Shopping Cart System

A console-based Java project that demonstrates an E-Commerce Shopping Cart system using JDBC and MySQL with a layered architecture.


---

## Project Overview

This project allows users to register, log in, view available products, add items to a shopping cart, view or remove items, and complete checkout.
It demonstrates core CRUD operations, JDBC connection handling, and modular Java development.


---

## Features

User registration and login

View product list

Add and remove items from cart

View cart with total price

Checkout and place orders

Uses MySQL for data storage



---

## Technologies Used

Language: Java

Database: MySQL

Connectivity: JDBC

IDE: Eclipse / IntelliJ / VS Code

Version Control: Git and GitHub



---

## Architecture

#### The project follows a four-layer architecture:

Controller  →  Service  →  DAO  →  Model
(MainApp)      (Business Logic)   (Database Access)   (Data Classes)

Controller: Handles user input and menu flow

Service: Contains business logic

DAO: Manages database interactions using JDBC

Model: Represents entities such as User, Product, Cart, and Order



---

## Database Setup

#### Run the following SQL commands in MySQL:

CREATE DATABASE ecommerce;
USE ecommerce;

CREATE TABLE users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(50),
  password VARCHAR(50)
);

CREATE TABLE products (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100),
  price DOUBLE
);

CREATE TABLE cart (
  id INT AUTO_INCREMENT PRIMARY KEY,
  user_id INT,
  product_id INT,
  quantity INT,
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE orders (
  id INT AUTO_INCREMENT PRIMARY KEY,
  user_id INT,
  total DOUBLE,
  order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES users(id)
);

INSERT INTO products(name, price) VALUES
('Laptop', 60000),
('Mobile', 25000),
('Headphones', 1500),
('Mouse', 700);

---

## Future Enhancements

Admin panel for managing products

Password encryption

Web-based interface using JSP or Spring Boot

Payment gateway integration

Order history for users



---

## Author

##### Barnas Munthaha A
##### GitHub: BarnasMunthaha


---

