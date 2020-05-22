DROP TABLE IF EXISTS user CASCADE;
DROP TABLE IF EXISTS board CASCADE;
DROP TABLE IF EXISTS photo;


CREATE TABLE user
(
  id                 INT AUTO_INCREMENT PRIMARY KEY,
  username           VARCHAR(250) NOT NULL,
  password           VARCHAR(250) NOT NULL
);

CREATE TABLE board
(
  id                 INT AUTO_INCREMENT PRIMARY KEY,
  user_id            INT,
  name               VARCHAR(250) NOT NULL,
  board_picture      VARCHAR(250)
);

CREATE TABLE photo
(
  id                 INT AUTO_INCREMENT PRIMARY KEY,
  board_id           INT,
  description        VARCHAR(250),
  url                VARCHAR(250) NOT NULL,
  creator            VARCHAR(250) NOT NULL
);

INSERT INTO user (id, username, password)
VALUES ('1', 'yulia', '$2a$10$q73DI3Vl0xRajJJvI7La2eOHWvt5u4TYbpNztmw622pt.X1XhzWEe')
