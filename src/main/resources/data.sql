-- insert into users (email, firstname, lastname, profilePicture, telephoneNumber, address, password) values ('anita@gmail.com', 'Anita', 'Pajic', 'aaaaa', '0669080792', 'Strumicka 6', 'anita123');
-- insert into users (email, firstname, lastname, profilePicture, telephoneNumber, address, password) values ('anja@gmail.com', 'Anita', 'Pajic', 'aaaaa', '0669080792', 'Strumicka 6', 'anita123');
-- insert into users (email, firstname, lastname, profilePicture, telephoneNumber, address, password) values ('aleksandra@gmail.com', 'Anita', 'Pajic', 'aaaaa', '0669080792', 'Strumicka 6', 'anita123');
-- insert into users (email, firstname, lastname, profilePicture, telephoneNumber, address, password) values ('tamara@gmail.com', 'Anita', 'Pajic', 'aaaaa', '0669080792', 'Strumicka 6', 'anita123');
-- insert into users (email, firstname, lastname, profilePicture, telephoneNumber, address, password) values ('bane@gmail.com', 'Anita', 'Pajic', 'aaaaa', '0669080792', 'Strumicka 6', 'anita123');
-- insert into users (email, firstname, lastname, profilePicture, telephoneNumber, address, password) values ('anita2@gmail.com', 'Anita', 'Pajic', 'aaaaa', '0669080792', 'Strumicka 6', 'anita123');
-- insert into users (email, firstname, lastname, profilePicture, telephoneNumber, address, password) values ('anja2@gmail.com', 'Anita', 'Pajic', 'aaaaa', '0669080792', 'Strumicka 6', 'anita123');
-- insert into users (email, firstname, lastname, profilePicture, telephoneNumber, address, password) values ('aleksandra2@gmail.com', 'Anita', 'Pajic', 'aaaaa', '0669080792', 'Strumicka 6', 'anita123');
--
-- insert into vehicle (baby_transport, car_model, license_number, passenger_seats, pet_transport, vehicle_type) values ('true', 'audi', '456325', '4', 'false', 'auto1');
-- insert into vehicle (baby_transport, car_model, license_number, passenger_seats, pet_transport, vehicle_type) values ('true', 'audi', '111111', '3', 'false', 'auto2');
-- insert into vehicle (baby_transport, car_model, license_number, passenger_seats, pet_transport, vehicle_type) values ('true', 'audi', '222222', '2', 'false', 'auto3');
-- insert into vehicle (baby_transport, car_model, license_number, passenger_seats, pet_transport, vehicle_type) values ('true', 'audi', '333333', '1', 'false', 'auto4');
--
-- insert into driver (id,vehicle_id) values (1,1);
-- insert into driver (id,vehicle_id) values (2,2);
-- insert into driver (id,vehicle_id) values (3,3);
-- insert into driver (id,vehicle_id) values (4,4);
--
-- insert into passenger (id) values (5);
-- insert into passenger (id) values (6);
-- insert into passenger (id) values (7);
-- insert into passenger (id) values (8);
--
-- insert into ride (start_time, end_time, total_cost, estimated_time_in_minutes, vehicle_type, baby_transport, pet_transport, status, driver_id) values ('2017-07-21T17:32:28.000Z', '2017-07-21T17:45:14.000Z', '480', '7', 'STANDARD', 'false', 'false', 'pending', 1);
-- insert into ride (start_time, end_time, total_cost, estimated_time_in_minutes, vehicle_type, baby_transport, pet_transport, status, driver_id) values ('2017-07-21T17:32:28.000Z', '2017-07-21T17:45:14.000Z', '480', '7', 'STANDARD', 'false', 'false', 'pending', 2);
-- insert into ride (start_time, end_time, total_cost, estimated_time_in_minutes, vehicle_type, baby_transport, pet_transport, status, driver_id) values ('2017-07-21T17:32:28.000Z', '2017-07-21T17:45:14.000Z', '480', '7', 'STANDARD', 'false', 'false', 'pending', 3);
-- insert into ride (start_time, end_time, total_cost, estimated_time_in_minutes, vehicle_type, baby_transport, pet_transport, status, driver_id) values ('2017-07-21T17:32:28.000Z', '2017-07-21T17:45:14.000Z', '480', '7', 'STANDARD', 'false', 'false', 'pending', 4);
-- insert into ride (start_time, end_time, total_cost, estimated_time_in_minutes, vehicle_type, baby_transport, pet_transport, status, driver_id) values ('2017-07-21T17:32:28.000Z', '2017-07-21T17:45:14.000Z', '480', '7', 'STANDARD', 'false', 'false', 'pending', 1);
-- insert into ride (start_time, end_time, total_cost, estimated_time_in_minutes, vehicle_type, baby_transport, pet_transport, status, driver_id) values ('2017-07-21T17:32:28.000Z', '2017-07-21T17:45:14.000Z', '480', '7', 'STANDARD', 'false', 'false', 'pending', 2);
-- insert into ride (start_time, end_time, total_cost, estimated_time_in_minutes, vehicle_type, baby_transport, pet_transport, status, driver_id) values ('2017-07-21T17:32:28.000Z', '2017-07-21T17:45:14.000Z', '480', '7', 'STANDARD', 'false', 'false', 'pending', 3);
--
-- insert into passenger_rides (passenger_id, rides_id) values (5, 4);
-- insert into passenger_rides (passenger_id, rides_id) values (6, 5);
-- insert into passenger_rides (passenger_id, rides_id) values (7, 6);
-- insert into passenger_rides (passenger_id, rides_id) values (8, 7);
--
-- insert into driver_document (document_image, driver_id, name) values ('aaaa', 1, 'saobracajna');
-- insert into driver_document (document_image, driver_id, name) values ('bbbb', 2, 'vozacka');
-- insert into driver_document (document_image, driver_id, name) values ('cccc', 3, 'licna');
-- insert into driver_document (document_image, driver_id, name) values ('dddd', 4, 'licna2');
--
-- insert into driver_documents (driver_id, documents_id) values (1, 1);
-- insert into driver_documents (driver_id, documents_id) values (2, 2);
-- insert into driver_documents (driver_id, documents_id) values (3, 3);
-- insert into driver_documents (driver_id, documents_id) values (4, 4);
--
-- insert into driver_rides (driver_id, rides_id) values (1, 1);
-- insert into driver_rides (driver_id, rides_id) values (2, 2);
-- insert into driver_rides (driver_id, rides_id) values (3, 3);

insert into vehicle (driver_id, car_model, vehicle_type, license_number, passenger_seats, baby_transport, pet_transport) values (null, 'audi', '0', 'NS 010 WQ', '4', 'true', 'false');

insert into user (dtype, email, firstname, lastname, profile_picture, telephone_number, address, password, active, blocked, vehicle_id) values ('Driver', 'tdz@gmail.com','tamara', 'dzambic','pic1', '22232', 'NS 12', '123', 'true', 'false', '1');
insert into user (dtype, email, firstname, lastname, profile_picture, telephone_number, address, password, active, blocked) values ('Passenger', 'tdz2@gmail.com','tamara', 'dzambic','pic2', '22232', 'NS 12', '123', 'true', 'false');
insert into user (dtype, email, firstname, lastname, profile_picture, telephone_number, address, password, active, blocked)  values ('Passenger', 'tdz3@gmail.com','tamara', 'dzambic','pic3', '22232', 'NS 12', '123', 'true', 'false');

insert into ride (start_time, end_time, total_cost, driver_id, estimated_time_in_minutes, status, panic, baby_transport, pet_transport, vehicle_type) values ('2017-07-21T17:32:28Z', '2017-07-21T17:32:28Z', '250', 1, '7', 'pending', 'false', 'false', 'false', '0');
insert into ride (start_time, end_time, total_cost, driver_id, estimated_time_in_minutes, status, panic, baby_transport, pet_transport, vehicle_type) values ('2017-07-21T17:32:28Z', '2017-07-21T17:32:28Z', '250', 1, '7', 'pending', 'false', 'false', 'false', '0');
insert into ride (start_time, end_time, total_cost, driver_id, estimated_time_in_minutes, status, panic, baby_transport, pet_transport, vehicle_type) values ('2017-07-21T17:32:28Z', '2017-07-21T17:32:28Z', '250', 1, '7', 'pending', 'false', 'false', 'false', '0');


insert into passenger_ride (ride_id, passenger_id) values(1, 2);
insert into passenger_ride (ride_id, passenger_id) values(2, 2);
insert into passenger_ride (ride_id, passenger_id) values(3, 3);