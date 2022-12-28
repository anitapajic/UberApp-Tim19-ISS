-- Vehicle
insert into vehicle (car_model, vehicle_type, license_number, passenger_seats, baby_transport, pet_transport) values
                                                                                                                  ('audi', '0', 'NS 010 WQ', '4', 'true', 'false'),
                                                                                                                  ('audi', '1', 'NS 011 WA', '4', 'true', 'true');

-- User (Passenger, Driver)
insert into user (dtype, email, name, surname, profile_picture, telephone_number, address, password, active, blocked, vehicle_id) values
                                                                                                                                            ('Driver', 'tamara@gmail.com','tamara', 'dzambic','pic1', '22232', 'NS 12', '123', 'true', 'false', '1'),
                                                                                                                                            ('Passenger', 'anita@gmail.com','anita', 'pajic','pic2', '22232', 'NS 12', '123', 'true', 'false', null),
                                                                                                                                            ('Passenger', 'aleksandra@gmail.com','aleksandra', 'filipic','pic3', '22232', 'NS 12', '123', 'true', 'false', null),
                                                                                                                                            ('Driver', 'anja@gmail.com','anja', 'petkovic','pic1', '22232', 'NS 12', '123', 'true', 'false', '2');

-- Activations
insert into activation (creation_date, expiration_date, user_id) values
                                                                     ('2017-07-21T17:32:28Z','2023-07-21T17:32:28Z', 1),
                                                                     ('2017-07-21T17:32:28Z','2023-07-21T17:32:28Z', 2),
                                                                     ('2017-07-21T17:32:28Z','2023-07-21T17:32:28Z', 3),
                                                                     ('2017-07-21T17:32:28Z','2023-07-21T17:32:28Z', 4);

-- Location
insert into location (address, latitude, longitude) values
                                                        ('Brace Ribnikar 17', '45.2156', '85.4861'),
                                                        ('Strumicka 6', '45.2156', '85.4861'),
                                                        ('Mise Dimitrijevica 43', '45.2156', '85.4861');

-- Path
insert into path (departure_id, destination_id) values
                                                    (1,2),
                                                    (1,3),
                                                    (2,3);

-- Passenger favourite paths
insert into passenger_favourites (path_id, passenger_id) values
                                                             (1, 2),
                                                             (2, 2),
                                                             (3, 3);

-- Rides
insert into ride (start_time, end_time, total_cost, driver_id, estimated_time_in_minutes, status, panic, baby_transport, pet_transport, vehicle_type) values
                                                                                                                                                          ('2017-07-21T17:32:28Z', '2017-07-21T17:32:28Z', '250', 1, '7', 'pending', 'false', 'false', 'false', '0'),
                                                                                                                                                          ('2017-07-21T17:32:28Z', '2017-07-21T17:32:28Z', '250', 4, '7', 'pending', 'false', 'false', 'false', '0'),
                                                                                                                                                          ('2017-07-21T17:32:28Z', '2017-07-21T17:32:28Z', '250', 1, '7', 'pending', 'false', 'false', 'false', '0');

-- Ride paths
insert into ride_paths (ride_id, paths_id) values
                                               (1,1),
                                               (2,2),
                                               (3,3);

-- Passenger rides
insert into passenger_ride (ride_id, passenger_id) values(1, 2),
                                                         (2, 3),
                                                         (3, 2);

-- Rejections

insert into rejection (reason, time_of_rejection, ride_id, user_id) values
                                                                        ('Reason', '2017-07-21T17:32:28Z', 1, 2);


-- Reviews
insert into review (comment, rating, ride_id, user_id, driver_id) values
                                                                                    ('comment1', 4, 1, 2, 1),
                                                                                    ('comment2', 3, 2, 2, 4),
                                                                                    ('comment3', 5, 3, 3, 1);

-- Documents
insert into document(name, document_image, driver_id) values
                                                          ('vozacka dozvola', 'pic1', 1),
                                                          ('licna karta', 'pic1', 1),
                                                          ('vozacka dozvola', 'pic2', 4),
                                                          ('licna karta', 'pic2', 4);


-- Working hours
insert into working_hours (endd, startd, driver_id) values
                                                        ('2022-12-18T12:00:00Z', '2022-12-18T20:00:00Z', 1),
                                                        ('2022-12-18T19:00:00Z', '2022-12-19T03:00:00Z', 4);

-- Notes
insert into note (text, "DATE",  user_id) values
                                     ('Note 1', '2017-07-21T17:32:28Z', 2),
                                     ('Note 2', '2017-07-21T17:32:28Z', 3),
                                     ('Note 3', '2017-07-21T17:32:28Z', 3);

-- Messages
insert into message (text, "TIME", "TYPE", receiver_id, sender_id, ride_id) values
                                                                       ('message1', '2017-07-21T17:32:28Z', 0, 1, 2, 1),
                                                                       ('panic1', '2017-07-21T17:32:28Z', 2, 1, 2, 1),
                                                                       ('panic2', '2017-07-21T17:32:28Z', 2, 1, 2, 1),
                                                                       ('message2', '2017-07-21T17:32:28Z', 0, 1, 2, 1);