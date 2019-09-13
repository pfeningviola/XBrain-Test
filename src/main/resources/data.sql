DROP TABLE IF EXISTS client;

CREATE TABLE client (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  email VARCHAR(250) NOT NULL,
  password VARCHAR(250) DEFAULT NULL
);

INSERT INTO client (name, email, password) VALUES
  ('Viola', 'viola@gmail.com', 'hello'),
  ('Jon Dean', 'jon@gmail.com', 'hello'),
  ('Jocky Ewing', 'jocky@gmail.com', 'hello');

DROP TABLE IF EXISTS product;

CREATE TABLE product (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  product_name VARCHAR(250) NOT NULL,
  price INTEGER NOT NULL
);

INSERT INTO product (product_name, price) VALUES
  ('mesa', 500),
  ('cadeira', 200),
  ('armário', 1000);