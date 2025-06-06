create table dealerships(
DealershipID int Auto_increment primary key,
name varchar(50) not null,
address varchar(50) not null,
phone varchar(12) not null
);
create table vehicles(
vin int primary key not null,
name varchar(50) not null,
type varchar(15) not null,
sold bit not null
);

create table inventory(
DealershipID int,
Vin int,
foreign key (dealershipid) references Dealerships(dealershipID),
foreign key (vin) references vehicles(vin)
);

create table salescontracts(
contractID int auto_increment primary key,
vin int,
price int not null,
financed bit not null,
foreign key (vin) references vehicles(vin)
);
 
ALTER TABLE leasecontracts
add leased bit
;
 
create table leasecontracts(
contractID int auto_increment primary key,
price int not null,
vin int,
foreign key (vin) references vehicles(vin)
);

INSERT INTO dealerships (name, address, phone) VALUES
('City Auto Mall', '123 Main St, Springfield', '555-123-4567'),
('Highway Motors', '456 Elm St, Rivertown', '555-234-5678'),
('Premier Autos', '789 Oak Ave, Lakeside', '555-345-6789'),
('Budget Wheels', '321 Pine Rd, Hillview', '555-456-7890'),
('Luxury Drive', '654 Maple Ln, Greenfield', '555-567-8901'),
('Quick Cars', '987 Cedar Blvd, Brookside', '555-678-9012'),
('Value Motors', '147 Birch St, Grandview', '555-789-0123'),
('Elite Auto Group', '258 Spruce Ct, Crestwood', '555-890-1234'),
('Reliable Rides', '369 Willow Dr, Rockport', '555-901-2345'),
('Metro Dealership', '159 Aspen Way, Fairview', '555-012-3456');

INSERT INTO vehicles (vin, name, type, sold) VALUES
(10001, 'Toyota Camry', 'Sedan', 0),
(10002, 'Ford F-150', 'Truck', 1),
(10003, 'Honda Civic', 'Sedan', 0),
(10004, 'Chevrolet Tahoe', 'SUV', 1),
(10005, 'Jeep Wrangler', 'SUV', 0),
(10006, 'Tesla Model 3', 'Sedan', 0),
(10007, 'Ram 1500', 'Truck', 1),
(10008, 'Subaru Outback', 'Wagon', 0),
(10009, 'BMW X5', 'SUV', 0),
(10010, 'Nissan Frontier', 'Truck', 0);

INSERT INTO inventory (DealershipID, Vin) VALUES
(1, 10001),
(1, 10002),
(2, 10003),
(2, 10004),
(3, 10005),
(3, 10006),
(4, 10007),
(4, 10008),
(5, 10009),
(5, 10010);


INSERT INTO salescontracts (vin, price, financed) VALUES
(10002, 28000, 1),  -- Ford F-150
(10004, 45000, 0),  -- Chevrolet Tahoe
(10007, 37000, 1);  -- Ram 1500

INSERT INTO leasecontracts (price, vin, leased) VALUES
(399, 10005, 1),  -- Jeep Wrangler
(499, 10009, 1),  -- BMW X5
(289, 10008, 0);  -- Subaru Outback (not currently leased)