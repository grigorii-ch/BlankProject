select * from products where title = 'ADAPTATION LESSON';

select * from products where price = 9.99 and category = 8 order by category, price;

select * from products where category in (8,15);

select * from products where price between 10 and 20;

select * from orders where orderdate between '2004-01-05' and '2004-02-05';

select sum(1), customerid from orders group by customerid order by 1 desc;

select sum(totalamount) from orders group by customerid, orderdate having sum(totalamount) > 100; 

select c.firstname, c.lastname, p.title, o.orderdate 
from customers c, orders o, orderlines o2, products p 
where c.customerid = o.customerid
and o.orderid = o2.orderid
and o2.prod_id = p.prod_id;