use library;

CREATE TABLE borrower (id varchar(32) PRIMARY KEY, name NVARCHAR(100) DEFAULT NULL, email NVARCHAR(100) DEFAULT NULL UNIQUE);

CREATE TABLE book (id varchar(32) PRIMARY KEY, isbn VARCHAR(13) DEFAULT NULL, title NVARCHAR(200) NOT NULL, author NVARCHAR(250) DEFAULT NULL);

CREATE TABLE movement (id int AUTO_INCREMENT PRIMARY KEY, item_id varchar(32) NOT NULL, borrower_id varchar(32) NOT NULL, stat int NOT NULL, movement_date datetime DEFAULT NULL);

CREATE TABLE movement_aud (id int, item_id varchar(32), borrower_id varchar(32), stat int, movement_date datetime, rev int, revtype int);

CREATE TABLE revinfo (rev INTEGER AUTO_INCREMENT PRIMARY KEY,revtstmp BIGINT);

INSERT INTO borrower (id, name, email)
VALUES (REPLACE(UUID(),'-',''),'Ali','ali@mail.com'),
(REPLACE(UUID(),'-',''),'Muthu','muthu@mail.com'),
(REPLACE(UUID(),'-',''),'Ah Hock','ahhock@mail.com');

INSERT INTO book (id, isbn, title, author)
VALUES (REPLACE(UUID(),'-',''),'9780261102736','The Silmarillion','J.R.R. Tolkien'),
(REPLACE(UUID(),'-',''),'9780261102736','The Silmarillion','J.R.R. Tolkien'),
(REPLACE(UUID(),'-',''),'9789671797112','Si Sabariah','Hamka');