CREATE TABLE users (
                       username VARCHAR(255) PRIMARY KEY,
                       first_name VARCHAR(255) NOT NULL,
                       last_name VARCHAR(255) NOT NULL,
                       created_at TIMESTAMP NOT NULL,
                       phone_number VARCHAR(50),
                       email VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR(50),
                       is_account_non_expired BOOLEAN NOT NULL DEFAULT TRUE,
                       is_account_non_locked BOOLEAN NOT NULL DEFAULT TRUE,
                       is_credentials_non_expired BOOLEAN NOT NULL DEFAULT TRUE,
                       is_enabled BOOLEAN NOT NULL DEFAULT TRUE,
                       shopping_cart_id BIGINT UNIQUE
);


CREATE TABLE shopping_carts (
                                id SERIAL PRIMARY KEY,
                                date_created TIMESTAMP NOT NULL,
                                total_amount DOUBLE PRECISION NOT NULL
);


ALTER TABLE users
    ADD CONSTRAINT fk_user_cart FOREIGN KEY (shopping_cart_id)
        REFERENCES shopping_carts (id) ON DELETE CASCADE;


CREATE TABLE products (
                          id SERIAL PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          description TEXT,
                          image_url VARCHAR(500),
                          category VARCHAR(50) NOT NULL,
                          price DOUBLE PRECISION NOT NULL,
                          stock_quantity INT
);


CREATE TABLE shopping_carts_items (
                                      id SERIAL PRIMARY KEY,
                                      shopping_cart_id BIGINT,
                                      product_id BIGINT,
                                      quantity INT NOT NULL,
                                      price_of_product_and_quantity DOUBLE PRECISION,
                                      CONSTRAINT fk_cartitem_cart FOREIGN KEY (shopping_cart_id)
                                          REFERENCES shopping_carts (id) ON DELETE CASCADE,
                                      CONSTRAINT fk_cartitem_product FOREIGN KEY (product_id)
                                          REFERENCES products (id) ON DELETE CASCADE
);


CREATE TABLE orders (
                        id SERIAL PRIMARY KEY,
                        status VARCHAR(50) NOT NULL,
                        address VARCHAR(255) NOT NULL,
                        city VARCHAR(255) NOT NULL,
                        zipcode VARCHAR(50) NOT NULL,
                        created_at TIMESTAMP NOT NULL,
                        order_date TIMESTAMP,
                        total_amount DOUBLE PRECISION NOT NULL,
                        username VARCHAR(255),
                        CONSTRAINT fk_order_user FOREIGN KEY (username)
                            REFERENCES users (username) ON DELETE CASCADE
);


CREATE TABLE order_items (
                             id SERIAL PRIMARY KEY,
                             order_id BIGINT,
                             product_id BIGINT,
                             quantity INT NOT NULL,
                             price_of_product_and_quantity DOUBLE PRECISION,
                             CONSTRAINT fk_orderitem_order FOREIGN KEY (order_id)
                                 REFERENCES orders (id) ON DELETE CASCADE,
                             CONSTRAINT fk_orderitem_product FOREIGN KEY (product_id)
                                 REFERENCES products (id) ON DELETE CASCADE
);