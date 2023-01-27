-- Location
insert into location (address, latitude, longitude) values
                                                        ('Stajaliste stanica', '45.264980', '19.830598'),
                                                        ('Brace Ribnikar 17', ' 45.247567', '19.837351'),
                                                        ('Strumicka 6', '45.239877', '19.849011'),
                                                        ('Mise Dimitrijevica 43', '45.243911', '19.830894');

-- Vehicle
insert into vehicle (car_model, vehicle_type, license_number, passenger_seats, baby_transport, pet_transport, location_id) values
                                                                                                                  ('audi', '0', 'NS 010 WQ', '4', 'true', 'false', 2),
                                                                                                                  ('audi', '1', 'NS 010 WS', '4', 'true', 'false', 3),
                                                                                                                  ('audi', '2', 'NS 011 WA', '4', 'true', 'true', 4);

-- User (Passenger, Driver)  password = test
insert into user (dtype, username, name, surname, profile_picture, telephone_number, address, password, active, blocked, vehicle_id, authorities) values
                                                                                                                                            ('Driver', 'tamara@gmail.com','Tamara', 'Dzambic','pic1', '0645638986', 'Brace Ribnikar 17, Novi Sad', '$2a$12$DUiRc0iWMEKTgzh6jpXpQOLs14XxmvyDdVkfBoTCwUlOy1JcXFrlC', 'true', 'false', '1', 'DRIVER'),
                                                                                                                                            ('Passenger', 'anita@gmail.com','Anita', 'Pajic','pic2', '0669024480', 'Strumicka 6, Novi Sad', '$2a$12$DUiRc0iWMEKTgzh6jpXpQOLs14XxmvyDdVkfBoTCwUlOy1JcXFrlC', 'true', 'false', null, 'PASSENGER'),
                                                                                                                                            ('Passenger', 'aleksandra@gmail.com','Aleksandra', 'Filipic','pic3', '0655157604', 'Mise Dimitrijevica 41, Novi Sad', '$2a$12$DUiRc0iWMEKTgzh6jpXpQOLs14XxmvyDdVkfBoTCwUlOy1JcXFrlC', 'true', 'false', null, 'PASSENGER'),
                                                                                                                                            ('Driver', 'anja@gmail.com','Anja', 'Petkovic','pic1', '0663487556', 'Strumicka 6, Novi Sad', '$2a$12$DUiRc0iWMEKTgzh6jpXpQOLs14XxmvyDdVkfBoTCwUlOy1JcXFrlC', 'true', 'false', '2', 'DRIVER'),
                                                                                                                                            ('Driver', 'bane@gmail.com','Branislav', 'Stojkovic','pic1', '0629008875', 'Narodnog Fronta 55, Novi Sad', '$2a$12$DUiRc0iWMEKTgzh6jpXpQOLs14XxmvyDdVkfBoTCwUlOy1JcXFrlC', 'false', 'false', '3', 'DRIVER'),
                                                                                                                                            ('User', 'admin@gmail.com','admin', 'admin','pic1', '066021661', 'ADMIN 12, Novi Sad', '$2a$12$DUiRc0iWMEKTgzh6jpXpQOLs14XxmvyDdVkfBoTCwUlOy1JcXFrlC', 'true', 'false', null, 'ADMIN'),
                                                                                                                                            ('Passenger', 'aleksandra123@gmail.com','aleksandra', 'filipic','pic3', '0699945612', 'NS 12', '$2a$12$DUiRc0iWMEKTgzh6jpXpQOLs14XxmvyDdVkfBoTCwUlOy1JcXFrlC', 'false', 'false', null, 'PASSENGER');

-- Activations
insert into activation (creation_date, expiration_date, user_id) values
                                                                     ('2017-07-21T17:32:28Z','2023-07-21T17:32:28Z', 1),
                                                                     ('2017-07-21T17:32:28Z','2023-07-21T17:32:28Z', 2),
                                                                     ('2017-07-21T17:32:28Z','2022-07-21T17:32:28Z', 3),
                                                                     ('2017-07-21T17:32:28Z','2023-07-21T17:32:28Z', 7),
                                                                     ('2017-07-21T17:32:28Z','2023-07-21T17:32:28Z', 5),
                                                                     ('2017-07-21T17:32:28Z','2023-07-21T17:32:28Z', 6),
                                                                     ('2017-07-21T17:32:28Z','2023-07-21T17:32:28Z', 4);

-- Path
insert into path (departure_id, destination_id) values
                                                    (3,2),
                                                    (4,3),
                                                    (2,3),
                                                    (4,1);

insert into favorite_route(favorite_name, scheduled_time, vehicle_type, baby_transport, pet_transport) values ('Kuca-Posao', '2017-07-21T17:32:28Z', 0, false, false),
                                                                                                        ('Kuca-Faks', '2017-07-21T17:32:28Z', 0, false, false),
                                                                                                         ('Kuca-Teretana', '2017-07-21T17:32:28Z', 0, false, false);

insert into favorites_paths(favorite_id, paths_id) values (1,1),
                                                          (2,2),
                                                          (3,3);

-- Passenger favourite paths

insert into passenger_favorites (favorite_id, passenger_id) values
                                                             (1, 2),
                                                             (2, 2),
                                                             (3, 3);

-- Rides
insert into ride (start_time, end_time, total_cost, driver_id, estimated_time_in_minutes, status, panic, baby_transport, pet_transport, vehicle_type) values
                                                                                                                                                          ('2023-01-21T17:32:28Z', null, '250', 1, '7', 'STARTED', 'false', 'false', 'false', '0'),
                                                                                                                                                          ('2022-12-21T17:32:28Z', '2022-12-21T17:45:28Z', '250', 4, '7', 'PENDING', 'false', 'false', 'false', '0'),
                                                                                                                                                          ('2022-12-22T17:32:28Z', '2022-12-22T17:46:28Z', '250', 1, '7', 'PENDING', 'false', 'false', 'false', '0'),
                                                                                                                                                          ('2022-12-25T17:32:28Z', '2022-07-21T17:50:28Z', '250', 5, '7', 'PENDING', 'false', 'false', 'false', '0');

-- Ride paths
insert into ride_paths (ride_id, paths_id) values
                                               (1,1),
                                               (2,2),
                                               (3,3),
                                               (4,4);

-- Passenger rides
insert into passenger_ride (ride_id, passenger_id) values(1, 3),
                                                         (2, 3),
                                                         (3, 3),(3, 2),
                                                         (4, 2);

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
                                                        ('2022-12-18T19:00:00Z', '2022-12-19T03:00:00Z', 4),
                                                        ('2022-11-18T12:00:00Z', '2022-12-18T20:00:00Z', 1),
                                                        ('2022-10-18T19:00:00Z', '2022-12-19T03:00:00Z', 4);

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

