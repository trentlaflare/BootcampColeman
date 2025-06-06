select name 
from dealerships;

select name
from vehicles
join inventory on vehicles.vin = inventory.vin
where inventory.DealershipID = 1;

select name, vin
from vehicles 
where vin = 10001;

select vehicles.name, vehicles.vin, dealerships.name
from dealerships
join inventory on dealerships.DealershipID = inventory.DealershipID
join vehicles on inventory.Vin = vehicles.vin
where inventory.vin = 10001;


SELECT dealerships.*
FROM dealerships
JOIN inventory ON dealerships.DealershipID = inventory.DealershipID
JOIN vehicles ON inventory.vin = vehicles.vin
WHERE vehicles.name like 'honda civic';


SELECT salescontracts.*, vehicles.*, dealerships.*
FROM salescontracts
JOIN vehicles ON salescontracts.vin = vehicles.vin
JOIN inventory ON vehicles.vin = inventory.vin
JOIN dealerships ON inventory.DealershipID = dealerships.DealershipID
WHERE dealerships.DealershipID = 1
  AND salescontracts.date BETWEEN '2024-06-01' AND '2024-06-30';
