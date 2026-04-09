Customers
customer_id	first_name	last_name	age	country
1	John	Doe	31	USA
2	Robert	Luna	22	USA
3	David	Robinson	22	UK
4	John	Reinhardt	25	UK
5	Betty	Doe	28	UAE
Orders
order_id	item	amount	customer_id
1	Keyboard	400	4
2	Mouse	300	4
3	Monitor	12000	3
4	Keyboard	400	1
5	Mousepad	250	2
Shippings
shipping_id	status	customer
1	Pending	2
2	Pending	4
3	Delivered	3
4	Pending	5
5	Delivered	1


select first_name,age from customers group by country;


SELECT first_name, age FROM Customers  group by age order by age desc limit 3;

//Find total order amount per customer.


//List customer names along with total amount spent and their shipping status.
 SELECT c.first_name,c.customer_id, sum(amount) as spend ,status from customers c 
join orders o on c.customer_id=o.customer_id
join Shippings s on o.customer_id=s.customer group by c.first_name,c.customer_id, status  order by spend  desc limit 3 ;

SELECT c.first_name,c.customer_id, sum(amount) as spend ,status from customers c 
join orders o on c.customer_id=o.customer_id
join Shippings s on o.customer_id=s.customer group by c.first_name,c.customer_id, status  order by spend  desc limit 1 ;


  SELECT c.first_name,c.customer_id, sum(amount) as spend ,status from customers c 
join orders o on c.customer_id=o.customer_id
join Shippings s on o.customer_id=s.customer where s.status='Delivered' group by c.first_name,c.customer_id, status having sum(amount)>500   order by spend  desc  ;


  SELECT c.first_name,c.customer_id, sum(amount) as spend ,status from customers c 
join orders o on c.customer_id=o.customer_id


join Shippings s on o.customer_id=s.customer where s.status='Delivered' group by c.first_name,c.customer_id, status having sum(amount)>500   order by spend  desc  ;

SELECT c.country, AVG(o.amount) AS avg_spent
FROM Customers c
JOIN Orders o ON c.customer_id = o.customer_id
GROUP BY c.country;
