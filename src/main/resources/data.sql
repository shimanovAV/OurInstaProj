

CREATE TABLE users
(
  id                 INT IDENTITY PRIMARY KEY,
  username           VARCHAR(250) NOT NULL,
  password           VARCHAR(250) NOT NULL
);

CREATE TABLE board
(
  id                 INT IDENTITY PRIMARY KEY,
  user_id            INT,
  name               VARCHAR(250) NOT NULL,
  board_picture      VARCHAR(250)
);

CREATE TABLE photo
(
  id                 INT IDENTITY PRIMARY KEY,
  board_id           INT,
  description        VARCHAR(250),
  url                VARCHAR(250) NOT NULL,
  creator            VARCHAR(250) NOT NULL
);

INSERT INTO users (id, username, password)
VALUES ('1', 'yulia', '$2a$10$q73DI3Vl0xRajJJvI7La2eOHWvt5u4TYbpNztmw622pt.X1XhzWEe');

INSERT INTO board (id, user_id, name, board_picture)
VALUES ('1', '1', 'family', 'http://bsulab7shimanovichav.blob.core.windows.net/bsu/image1.jpg');

INSERT INTO photo (id, board_id, description, url, creator)
VALUES ('1', '1', 'My lovely family', 'http://bsulab7shimanovichav.blob.core.windows.net/bsu/image1.jpg', 'yulia');
