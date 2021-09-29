CREATE TABLE items
(
    id               BIGINT PRIMARY KEY AUTO_INCREMENT,
    category         VARCHAR(20)         NOT NULL,
    name             VARCHAR(100) UNIQUE NOT NULL,
    size             VARCHAR(20)         NOT NULL,
    weight           INT                 NOT NULL,
    color            VARCHAR(15)         NOT NULL,
    price            INT                 NOT NULL,
    date_of_addition DATE                NOT NULL
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