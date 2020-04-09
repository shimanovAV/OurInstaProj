DROP TABLE IF EXISTS board;



CREATE TABLE board
(
  id                 INT AUTO_INCREMENT PRIMARY KEY,
  name               VARCHAR(250) NOT NULL,
  board_picture      VARCHAR(250)
);

/*CREATE TABLE game


INSERT INTO bet (name, photo_url)
VALUES ('Paper', '../../../../assets/Paper.svg'),
       ('Rock', '../../../../assets/Rock.svg'),
       ('Scissors', '../../../../assets/Scissors.svg');*/

