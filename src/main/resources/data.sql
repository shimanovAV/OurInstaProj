DROP TABLE IF EXISTS board;
DROP TABLE IF EXISTS photo;



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

/*CREATE TABLE game


INSERT INTO bet (name, photo_url)
VALUES ('Paper', '../../../../assets/Paper.svg'),
       ('Rock', '../../../../assets/Rock.svg'),
       ('Scissors', '../../../../assets/Scissors.svg');*/

