package com.epam.smyrnov.constants;

public final class SQLQueries {

    public static class Items {
        public static final String SELECT_ALL_ITEMS = "SELECT * FROM items";
        public static final String SELECT_ALL_ITEMS_BY_CATEGORY = "SELECT * FROM items WHERE category=? ORDER BY id";
        public static final String SELECT_IMAGES_OF_ITEM = "SELECT path FROM items, images WHERE items.id=? AND images.id=items.id";
        public static final String SELECT_ITEM_BY_ID = "SELECT * FROM items WHERE id=?";
        public static final String SELECT_CATEGORIES = "SELECT DISTINCT category FROM items";
        public static final String SELECT_COLORS = "SELECT DISTINCT color FROM items";

        public static final String INSERT_ITEM = "INSERT INTO items VALUES(DEFAULT, ?, ?, ?, ?, ?, ?, ?)";
        public static final String INSERT_IMAGES = "INSERT INTO images VALUES(?, ?)";
        public static final String UPDATE_ITEM = "UPDATE items SET category=?, name=?, size=?, weight=?, color=?, price=?, date_of_addition=? WHERE id=?";

        public static final String DELETE_ITEM_BY_ID = "DELETE FROM items WHERE id=?";
        public static final String DELETE_IMAGES = "DELETE FROM images WHERE id=?";
    }

    public static class Users {
        public static final String SELECT_ALL_USERS = "SELECT * FROM users ORDER BY id";
        public static final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE id=?";
        public static final String SELECT_USERS_BY_ROLE = "SELECT * FROM users WHERE role_id=?";
        public static final String SELECT_USER_BY_EMAIL = "SELECT * FROM users WHERE email=?";

        public static final String INSERT_USER = "INSERT INTO users VALUES (DEFAULT, ?, ?, ?, ?, ?, ?)";

        public static final String UPDATE_USER = "UPDATE users SET email=?, password=?, first_name=?, last_name=?, blocked=?, role_id=? WHERE id=?";

        public static final String DELETE_USER_BY_ID = "DELETE FROM users WHERE id=?";
        public static final String DELETE_USER_BY_EMAIL = "DELETE FROM users WHERE email=?";
    }

    public static class Orders {
        public static final String SELECT_ALL_ORDERS = "SELECT * FROM orders ORDER BY id";
        public static final String COUNT_ALL_ORDERS = "SELECT id FROM orders ORDER BY id DESC";
        public static final String SELECT_ORDER_BY_ID = "SELECT * FROM orders WHERE id=?";
        public static final String SELECT_ORDERS_BY_USER_ID = "SELECT * FROM orders WHERE user_id=?";

        public static final String INSERT_ORDER = "INSERT INTO orders VALUES (?, ?, ?, ?, ?, ?, ?)";

        public static final String DELETE_ORDER_BY_ID = "DELETE FROM orders WHERE id=?";
    }
}
