use testdb;

CREATE TABLE IF NOT EXISTS genre (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
)  ENGINE=INNODB;    
 
CREATE TABLE IF NOT EXISTS author (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    start_date DATE
) ENGINE=INNODB;

drop table book;
    
CREATE TABLE IF NOT EXISTS book (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    creation_year INT,
    genre_id INT,
    author_id INT
) ENGINE=INNODB;

alter table book add constraint bookGenre_FK FOREIGN KEY (genre_id) REFERENCES genre (id) ON UPDATE CASCADE ON DELETE CASCADE;

alter table book add constraint bookAuthor_FK FOREIGN KEY (author_id) REFERENCES author (id) ON UPDATE CASCADE ON DELETE CASCADE;
        
 
 
 

