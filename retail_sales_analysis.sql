/* SQL Project
Problem Statement: Online Retail Sales Analysis Database
Retail businesses generate huge sales data but lack structured insights. Design a database and write SQL queries to analyze sales performance.
Objectives
●	Create a relational database for an online store
●	Store customer, product, and order data
●	Extract meaningful insights using SQL queries
Database Tables
●	Customers (customer_id, name, city)
●	Products (product_id, name, category, price)
●	Orders (order_id, customer_id, date)
●	Order_Items (order_id, product_id, quantity)
Key Tasks
1. Find top-selling products
2. Identify most valuable customers
3. Monthly revenue calculation
4. Category-wise sales analysis
5. Detect inactive customers
*/


CREATE DATABASE retail_sales;
USE retail_sales;

-- Tables
CREATE TABLE Customers (
    customer_id INT PRIMARY KEY,
    name VARCHAR(100),
    city VARCHAR(50)
);

CREATE TABLE Products (
    product_id INT PRIMARY KEY,
    name VARCHAR(100),
    category VARCHAR(50),
    price DECIMAL(10,2)
);

CREATE TABLE Orders (
    order_id INT PRIMARY KEY,
    customer_id INT,
    order_date DATE,
    FOREIGN KEY (customer_id) REFERENCES Customers(customer_id)
);

CREATE TABLE Order_Items (
    order_id INT,
    product_id INT,
    quantity INT,
    FOREIGN KEY (order_id) REFERENCES Orders(order_id),
    FOREIGN KEY (product_id) REFERENCES Products(product_id)
);


INSERT INTO Customers VALUES
(1,'Arun','Chennai'),
(2,'Priya','Coimbatore'),
(3,'Rahul','Bangalore'),
(4,'Sneha','Hyderabad'),
(5,'Karthik','Chennai');

INSERT INTO Products VALUES
(101,'Laptop','Electronics',60000),
(102,'Phone','Electronics',20000),
(103,'Shoes','Fashion',3000),
(104,'Watch','Accessories',5000),
(105,'Headphones','Electronics',2500);

INSERT INTO Orders VALUES
(1001,1,'2024-01-10'),
(1002,2,'2024-01-15'),
(1003,1,'2024-02-10'),
(1004,3,'2024-02-20'),
(1005,4,'2024-03-05');

INSERT INTO Order_Items VALUES
(1001,101,1),
(1001,105,2),
(1002,102,1),
(1003,103,2),
(1004,101,1),
(1004,104,1),
(1005,102,2);

-- 1. Find top-selling products
SELECT p.name,SUM(oi.quantity) as total_sold FROM Products p
JOIN Order_Items oi on p.product_id = oi.product_id
GROUP BY p.name
ORDER BY total_sold DESC;

-- 2. Identify most valuable customers
SELECT c.name,SUM(p.price * oi.quantity) as total_amount FROM Customers c
JOIN Orders o on c.customer_id = o.customer_id
JOIN Order_Items oi on o.order_id = oi.order_id
JOIN Products p on oi.product_id = p.product_id
GROUP BY c.name
ORDER BY total_amount DESC;

-- 3. Monthly revenue calculation
SELECT MONTH(o.order_date) as month,SUM(p.price * oi.quantity) as revenue FROM Orders o
JOIN Order_Items oi on o.order_id = oi.order_id
JOIN Products p on oi.product_id = p.product_id
GROUP BY MONTH(o.order_date)
ORDER BY month;

-- 4. Category-wise sales analysis
SELECT p.category,SUM(p.price * oi.quantity) as total_sales FROM Products p
JOIN Order_Items oi on p.product_id = oi.product_id
GROUP BY p.category
ORDER BY total_sales DESC;

-- 5. Detect inactive customers
SELECT c.name FROM Customers c
LEFT JOIN Orders o on c.customer_id = o.customer_id
WHERE o.order_id IS NULL;
