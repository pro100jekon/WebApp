SET NAMES utf8;
DROP DATABASE IF EXISTS webapp;
CREATE DATABASE webapp CHARACTER SET utf8 COLLATE utf8_bin;
USE webapp;

CREATE TABLE roles (
    id INTEGER PRIMARY KEY,
    name VARCHAR(10) UNIQUE
);

INSERT INTO roles VALUES (0, 'admin');
INSERT INTO roles VALUES (1, 'client');

CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    first_name VARCHAR(20) NOT NULL,
    last_name VARCHAR(20) NOT NULL,
    blocked BOOLEAN NOT NULL,
    verified BOOLEAN NOT NULL,
    role_id INTEGER NOT NULL REFERENCES roles(id)
       ON DELETE CASCADE
       ON UPDATE RESTRICT
);

DELIMITER //
CREATE TRIGGER login_restriction BEFORE UPDATE ON users
    FOR EACH ROW
BEGIN
    IF OLD.email <> NEW.email THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Updating here is restricted!';
    END IF;
END;//
DELIMITER ;

-- password = admin.
INSERT INTO users VALUES (0, 'admin@gmail.com', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 'Almighty', 'Admin', FALSE, TRUE, 0);
-- password = password.
INSERT INTO users VALUES (0, 'client@gmail.com', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8', 'Useless', 'Client', FALSE, TRUE, 1);

CREATE TABLE hashes (
    id BIGINT NOT NULL REFERENCES users(id),
    hash VARCHAR(100) NOT NULL PRIMARY KEY
);

CREATE TABLE items (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    category VARCHAR(20) NOT NULL,
    name VARCHAR(100) UNIQUE NOT NULL,
    size VARCHAR(20) NOT NULL,
    weight INT NOT NULL,
    color VARCHAR(15) NOT NULL,
    price INT NOT NULL,
    date_of_addition DATE NOT NULL
);

CREATE TABLE images (
    id BIGINT NOT NULL REFERENCES items(id)
        ON DELETE CASCADE,
    path VARCHAR(100) NOT NULL PRIMARY KEY
);

INSERT INTO items VALUES (DEFAULT, 'smartphone', 'Samsung galaxy S8', '68.1 x 148.9 x 8', 155, 'blue', 13000, DATE('2017-02-15'));
INSERT INTO items VALUES (DEFAULT, 'smartphone', 'Apple iPhone 11 Pro', '144 x 71.4 x 8.1', 188, 'green', 35000, DATE('2019-09-20'));
INSERT INTO items VALUES (DEFAULT, 'smartphone', 'Huawei P30 Pro', '158 x 73.4 x 8.4', 156, 'blue', 28000, DATE('2019-03-26'));
INSERT INTO items VALUES (DEFAULT, 'gyroboard', 'Xiaomi Ninebot Mini', '260 x 595 x 548', 12800, 'white', 8400, DATE('2016-10-01'));
INSERT INTO items VALUES (DEFAULT, 'gyroboard', 'MiniRobot Black HJ-2', '546 x 262 x 595', 12800, 'black', 7900, DATE('2017-12-06'));
INSERT INTO items VALUES (DEFAULT, 'gyroboard', 'Smart Balance Premium 10.5', '700 x 350 x 335', 12000, 'pink', 5000, DATE('2019-02-09'));
INSERT INTO items VALUES (DEFAULT, 'laptop', 'Xiaomi Mi Notebook Air 13.3"', '309.6 x 14.8 x 210.9', 1280, 'gray', 22000, DATE('2018-09-24'));
INSERT INTO items VALUES (DEFAULT, 'laptop', 'ASUS ZenBook 13 UX333FA-A3126T', '309.6 x 14.8 x 210.9', 1090, 'white', 28000, DATE('2016-05-04'));
INSERT INTO items VALUES (DEFAULT, 'laptop', 'Lenovo IdeaPad S340-15IWL', '309.6 x 14.8 x 210.9', 1800, 'blue', 23250, DATE('2017-04-22'));
INSERT INTO images VALUES (1, 'Samsung1.jpg');
INSERT INTO images VALUES (1, 'Samsung2.jpg');
INSERT INTO images VALUES (1, 'Samsung3.jpg');
INSERT INTO images VALUES (2, 'iPhone1.jpg');
INSERT INTO images VALUES (2, 'iPhone2.jpg');
INSERT INTO images VALUES (2, 'iPhone3.jpg');
INSERT INTO images VALUES (3, 'Huawei1.jpg');
INSERT INTO images VALUES (3, 'Huawei2.jpg');
INSERT INTO images VALUES (3, 'Huawei3.jpg');
INSERT INTO images VALUES (4, 'XiaomiGyro1.jpg');
INSERT INTO images VALUES (4, 'XiaomiGyro2.jpg');
INSERT INTO images VALUES (4, 'XiaomiGyro3.jpg');
INSERT INTO images VALUES (5, 'MinibotGyro1.jpg');
INSERT INTO images VALUES (5, 'MinibotGyro2.jpg');
INSERT INTO images VALUES (6, 'SmartBalanceGyro1.jpg');
INSERT INTO images VALUES (6, 'SmartBalanceGyro2.jpg');
INSERT INTO images VALUES (6, 'SmartBalanceGyro3.jpg');
INSERT INTO images VALUES (7, 'XiaomiNotebook1.jpg');
INSERT INTO images VALUES (7, 'XiaomiNotebook2.jpg');
INSERT INTO images VALUES (7, 'XiaomiNotebook3.jpg');
INSERT INTO images VALUES (8, 'ASUSNotebook1.jpg');
INSERT INTO images VALUES (8, 'ASUSNotebook2.jpg');
INSERT INTO images VALUES (8, 'ASUSNotebook3.jpg');
INSERT INTO images VALUES (9, 'LenovoNotebook1.jpg');
INSERT INTO images VALUES (9, 'LenovoNotebook2.jpg');

CREATE TABLE statuses (
    id INT PRIMARY KEY,
    name VARCHAR(10) NOT NULL UNIQUE
);

DELIMITER //
CREATE TRIGGER deletion_check1 BEFORE DELETE ON statuses
    FOR EACH ROW
BEGIN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Deletion here is restricted!';
END;//
DELIMITER ;

INSERT INTO statuses VALUES (0, 'Registered');
INSERT INTO statuses VALUES (1, 'Confirmed');
INSERT INTO statuses VALUES (2, 'On its way');
INSERT INTO statuses VALUES (3, 'Delivered');
INSERT INTO statuses VALUES (4, 'Paid');
INSERT INTO statuses VALUES (5, 'Cancelled');

CREATE TABLE delivery_types (
    id INT PRIMARY KEY,
    name VARCHAR(100) UNIQUE NOT NULL
);

DELIMITER //
CREATE TRIGGER deletion_check2 BEFORE DELETE ON delivery_types
    FOR EACH ROW
BEGIN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Deletion here is restricted!';
END;//
DELIMITER ;

INSERT INTO delivery_types VALUES (0, 'NovaPoshta');
INSERT INTO delivery_types VALUES (1, 'UkrPoshta');
INSERT INTO delivery_types VALUES (2, 'MeestExpress');
INSERT INTO delivery_types VALUES (3, 'JustIn');
INSERT INTO delivery_types VALUES (4, 'CourierNovaPoshta');
INSERT INTO delivery_types VALUES (5, 'CourierMeestExpress');

CREATE TABLE payment_types (
    id INT PRIMARY KEY,
    name VARCHAR(20) UNIQUE NOT NULL
);

DELIMITER //
CREATE TRIGGER deletion_check3 BEFORE DELETE ON payment_types
    FOR EACH ROW
BEGIN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Deletion here is restricted!';
END;//
DELIMITER ;

INSERT INTO payment_types VALUES (0, 'Visa');
INSERT INTO payment_types VALUES (1, 'PayPal');
INSERT INTO payment_types VALUES (2, 'Mastercard');
INSERT INTO payment_types VALUES (3, 'WebMoney');
INSERT INTO payment_types VALUES (4, 'QIWI');
INSERT INTO payment_types VALUES (5, 'Cash');
INSERT INTO payment_types VALUES (6, 'Any card');

CREATE TABLE orders (
    id BIGINT NOT NULL,
    item_id BIGINT NOT NULL REFERENCES items(id),
    PRIMARY KEY (id, item_id),
    quantity INT NOT NULL DEFAULT 1,
    user_id BIGINT NOT NULL REFERENCES users(id),
    delivery_type INT NOT NULL REFERENCES delivery_types(id),
    payment_type INT NOT NULL REFERENCES payment_types(id),
    status_id INT NOT NULL REFERENCES statuses(id)
);

INSERT INTO orders VALUES (1, 1, 2, 2, 4, 1, 0);
INSERT INTO orders VALUES (1, 4, 23, 2, 4, 1, 0);
INSERT INTO orders VALUES (1, 2, 11, 2, 4, 1, 0);