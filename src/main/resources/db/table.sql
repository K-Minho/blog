CREATE TABLE user_tb(
    id int auto_increment primary key,
    username varchar unique not null,
    password varchar not null,
    email varchar not null,
    created_at timestamp not null
);

CREATE TABLE board_tb(
    id int auto_increment primary key,
    title varchar(50) not null,
    content longtext not null,
    thumbnail longtext,
    user_id int not null,
    created_at timestamp not null
);