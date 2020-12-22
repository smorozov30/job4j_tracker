CREATE TABLE items (
    id varchar(2000) primary key,
    name varchar(2000)
);

CREATE TABLE IF NOT EXISTS items
(
    id INT PRIMARY KEY,
    item_id VARCHAR(30),
    name VARCHAR(50)
);