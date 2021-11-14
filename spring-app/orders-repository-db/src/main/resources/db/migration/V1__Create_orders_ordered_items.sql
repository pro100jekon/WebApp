CREATE TABLE orders
(
    id      BIGINT PRIMARY KEY AUTO_INCREMENT,
    status  INT    NOT NULL,
    user_id BIGINT NOT NULL
);

CREATE TABLE ordered_items
(
    order_id BIGINT NOT NULL,
    item_id  BIGINT NOT NULL,
    quantity INT    NOT NULL,
    price    BIGINT NOT NULL,
    CONSTRAINT fk_order_id FOREIGN KEY (order_id)
        REFERENCES orders (id),
    CONSTRAINT PRIMARY KEY (order_id, item_id)
);

INSERT INTO orders(status, user_id)
VALUES (1, 1);
INSERT INTO orders(status, user_id)
VALUES (2, 1);

INSERT INTO ordered_items
VALUES (1, 1, 10, 100),
       (1, 2, 50, 10),
       (2, 1, 1, 100);