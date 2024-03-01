CREATE TABLE IF NOT EXISTS exchange_currency (id INT NOT NULL AUTO_INCREMENT, amount DECIMAL(18,4), currency_origin VARCHAR(255), currency_destination VARCHAR(255),field_type_amount VARCHAR(255), exchange_type DECIMAL(18,4), PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS users (
                      id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                      email VARCHAR(255) DEFAULT NULL COMMENT 'EMAIL',
                      password VARCHAR(255) DEFAULT NULL COMMENT 'PASSWORD');

