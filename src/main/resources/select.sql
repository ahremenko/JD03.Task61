select b.name, b.creation_year, g.name, a.name 
from book b
join author a on a.id = b.author_id
join genre g on g.id = b.genre_id;
