use testdb;

select * from author;

insert into author (`name`) values ('Cay S. Horstmann');

insert into author (`name`) values ('Joshua Bloch');

insert into author (`name`) values ('Bruce Eckel');

insert into author (`name`) values ('Tim Marshall');

insert into genre (`name`) values ('Geography');

insert into genre (`name`) values ('Programming');

insert into book (`name`,`creation_year`,`genre_id`,`author_id`) 
 values ('Core Java Volume I – Fundamentals',2009,(select id from genre where name = 'Programming'),(select id from author where name = 'Cay S. Horstmann')); 

insert into book (`name`,`creation_year`,`genre_id`,`author_id`) 
 values ('Effective Java',2014,(select id from genre where name = 'Programming'),(select id from author where name = 'Joshua Bloch')); 

insert into book (`name`,`creation_year`,`genre_id`,`author_id`) 
 values ('Thinking in Java (4th Edition)',2013,(select id from genre where name = 'Programming'),(select id from author where name = 'Bruce Eckel')); 

insert into book (`name`,`creation_year`,`genre_id`,`author_id`) 
 values ('Ten Maps That Explain Everything About the World',2015,(select id from genre where name = 'Geography'),(select id from author where name = 'Tim Marshall')); 
 
commit;







