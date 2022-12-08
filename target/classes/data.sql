insert into vehicle (driver_id, car_model, vehicle_type, license_number, passenger_seats, baby_transport, pet_transport) values (null, 'audi', '0', 'NS 010 WQ', '4', 'true', 'false');

insert into user (dtype, email, firstname, lastname, profile_picture, telephone_number, address, password, active, blocked, vehicle_id) values ('Driver', 'tamara@gmail.com','tamara', 'dzambic','pic1', '22232', 'NS 12', '123', 'true', 'false', '1');
insert into user (dtype, email, firstname, lastname, profile_picture, telephone_number, address, password, active, blocked) values ('Passenger', 'anita@gmail.com','anita', 'pajic','pic2', '22232', 'NS 12', '123', 'true', 'false');
insert into user (dtype, email, firstname, lastname, profile_picture, telephone_number, address, password, active, blocked)  values ('Passenger', 'aleksandra@gmail.com','aleksandra', 'filipic','pic3', '22232', 'NS 12', '123', 'true', 'false');

insert into location (address, latitude, longitude) values ('Brace Ribnikar 17', '45.2156', '85.4861');
insert into location (address, latitude, longitude) values ('Strumicka 6', '45.2156', '85.4861');
insert into location (address, latitude, longitude) values ('Mise Dimitrijevica 43', '45.2156', '85.4861');

insert into path (departure_id, destination_id) values (1,2);
insert into path (departure_id, destination_id) values (1,3);
insert into path (departure_id, destination_id) values (2,3);

insert into ride (start_time, end_time, total_cost, driver_id, estimated_time_in_minutes, status, panic, baby_transport, pet_transport, vehicle_type) values ('2017-07-21T17:32:28Z', '2017-07-21T17:32:28Z', '250', 1, '7', 'pending', 'false', 'false', 'false', '0');
insert into ride (start_time, end_time, total_cost, driver_id, estimated_time_in_minutes, status, panic, baby_transport, pet_transport, vehicle_type) values ('2017-07-21T17:32:28Z', '2017-07-21T17:32:28Z', '250', 1, '7', 'pending', 'false', 'false', 'false', '0');
insert into ride (start_time, end_time, total_cost, driver_id, estimated_time_in_minutes, status, panic, baby_transport, pet_transport, vehicle_type) values ('2017-07-21T17:32:28Z', '2017-07-21T17:32:28Z', '250', 1, '7', 'pending', 'false', 'false', 'false', '0');

insert into ride_paths (ride_id, paths_id) values(1,1);
insert into ride_paths (ride_id, paths_id) values(2,2);
insert into ride_paths (ride_id, paths_id) values(3,3);

insert into passenger_ride (ride_id, passenger_id) values(1, 2);
insert into passenger_ride (ride_id, passenger_id) values(2, 2);
insert into passenger_ride (ride_id, passenger_id) values(3, 3);

insert into driver_document(name, document_image, driver_id) values ('vozacka dozvola', 'pic', 1);
insert into driver_document(name, document_image, driver_id) values ('licna karta', 'pic', 1);