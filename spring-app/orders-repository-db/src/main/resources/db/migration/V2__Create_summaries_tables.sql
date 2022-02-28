CREATE TABLE user_summary
(
    user_id    BIGINT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name  VARCHAR(50)
);

INSERT INTO user_summary
VALUES (1, null, null);
ALTER TABLE orders
    ADD CONSTRAINT fk_user_sum FOREIGN KEY orders (user_id) REFERENCES user_summary (user_id) ON DELETE NO ACTION;

CREATE TABLE item_summary
(
    item_id BIGINT PRIMARY KEY,
    name    VARCHAR(100)
);

INSERT INTO item_summary
VALUES (1, null),
       (2, null);

ALTER TABLE ordered_items
    ADD CONSTRAINT fk_item_sum FOREIGN KEY ordered_items (item_id) REFERENCES item_summary (item_id) ON DELETE NO ACTION;