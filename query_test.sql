select a.id , a.name , b.name parent_name
from query a
left join query b on a.parent_id = b.id;