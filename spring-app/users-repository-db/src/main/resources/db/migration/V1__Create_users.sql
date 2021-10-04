CREATE TABLE users (
                       id BIGINT PRIMARY KEY AUTO_INCREMENT,
                       email VARCHAR(50) NOT NULL UNIQUE,
                       password_hash VARCHAR(100) NOT NULL,
                       password_salt VARCHAR(100) NOT NULL,
                       first_name VARCHAR(20) NOT NULL,
                       last_name VARCHAR(20) NOT NULL,
                       blocked BOOLEAN NOT NULL,
                       verified BOOLEAN NOT NULL,
                       role INTEGER NOT NULL
);