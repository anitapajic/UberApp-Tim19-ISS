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
                                                                                                                                            ('Driver', 'tamara@gmail.com','Tamara', 'Dzambic','pic1', '0645638986', 'Brace Ribnikar 17, Novi Sad', '$2a$12$DUiRc0iWMEKTgzh6jpXpQOLs14XxmvyDdVkfBoTCwUlOy1JcXFrlC', 'false', 'false', '1', 'DRIVER'),
                                                                                                                                            ('Passenger', 'anita@gmail.com','Anita', 'Pajic','pic2', '0669024480', 'Strumicka 6, Novi Sad', '$2a$12$DUiRc0iWMEKTgzh6jpXpQOLs14XxmvyDdVkfBoTCwUlOy1JcXFrlC', 'true', 'false', null, 'PASSENGER'),
                                                                                                                                            ('Passenger', 'aleksandra@gmail.com','Aleksandra', 'Filipic','pic3', '0655157604', 'Mise Dimitrijevica 41, Novi Sad', '$2a$12$DUiRc0iWMEKTgzh6jpXpQOLs14XxmvyDdVkfBoTCwUlOy1JcXFrlC', 'true', 'false', null, 'PASSENGER'),
                                                                                                                                            ('Driver', 'anja@gmail.com','Anja', 'Petkovic','pic1', '0663487556', 'Strumicka 6, Novi Sad', '$2a$12$DUiRc0iWMEKTgzh6jpXpQOLs14XxmvyDdVkfBoTCwUlOy1JcXFrlC', 'true', 'false', '2', 'DRIVER'),
                                                                                                                                            ('Driver', 'bane@gmail.com','Branislav', 'Stojkovic','pic1', '0629008875', 'Narodnog Fronta 55, Novi Sad', '$2a$12$DUiRc0iWMEKTgzh6jpXpQOLs14XxmvyDdVkfBoTCwUlOy1JcXFrlC', 'true', 'true', '3', 'DRIVER'),
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
insert into ride (start_time, end_time, total_cost, driver_id, estimated_time_in_minutes, status, panic, baby_transport, pet_transport, vehicle_type, json) values
                                                                                                                                                          ('2023-01-21T17:32:28Z', null, '250', 1, '7', 'STARTED', 'false', 'false', 'false', '0', '{"code":"Ok","routes":[{"legs":[{"steps":[{"geometry":{"coordinates":[[19.849027,45.239977],[19.849367,45.239995],[19.849436,45.24001],[19.849503,45.240031]],"type":"LineString"},"maneuver":{"bearing_after":86,"bearing_before":0,"location":[19.849027,45.239977],"modifier":"right","type":"depart"},"mode":"driving","driving_side":"right","name":"","intersections":[{"out":0,"entry":[true],"bearings":[86],"location":[19.849027,45.239977]}],"weight":14.9,"duration":14.9,"distance":38.2},{"geometry":{"coordinates":[[19.849503,45.240031],[19.849454,45.240105],[19.849382,45.240216],[19.84929,45.240356],[19.849171,45.240537],[19.849153,45.240565],[19.84913,45.240612]],"type":"LineString"},"maneuver":{"bearing_after":334,"bearing_before":67,"location":[19.849503,45.240031],"modifier":"left","type":"turn"},"mode":"driving","driving_side":"right","name":"Струмичка","intersections":[{"out":2,"in":1,"entry":[true,false,true],"bearings":[165,255,330],"location":[19.849503,45.240031]}],"weight":15.1,"duration":15.1,"distance":70.9},{"geometry":{"coordinates":[[19.84913,45.240612],[19.84892,45.240559],[19.848821,45.240541],[19.848698,45.24053],[19.848676,45.240528],[19.848555,45.240521],[19.848456,45.240515],[19.848071,45.240487],[19.847901,45.240473]],"type":"LineString"},"maneuver":{"bearing_after":250,"bearing_before":334,"location":[19.84913,45.240612],"modifier":"left","type":"turn"},"mode":"driving","driving_side":"right","name":"Драге Спасић","intersections":[{"out":2,"in":1,"entry":[true,false,true],"bearings":[60,150,255],"location":[19.84913,45.240612]}],"weight":16.2,"duration":16.2,"distance":98.2},{"geometry":{"coordinates":[[19.847901,45.240473],[19.847883,45.240548],[19.847748,45.241481],[19.847654,45.242109],[19.847625,45.242311],[19.847616,45.242363],[19.847597,45.242475]],"type":"LineString"},"maneuver":{"bearing_after":352,"bearing_before":262,"location":[19.847901,45.240473],"modifier":"right","type":"turn"},"mode":"driving","driving_side":"right","name":"Фрушкогорска","intersections":[{"out":0,"in":1,"entry":[true,false,true,true],"bearings":[0,90,180,270],"location":[19.847901,45.240473]}],"weight":27.4,"duration":27.4,"distance":223.8},{"geometry":{"coordinates":[[19.847597,45.242475],[19.847378,45.24246],[19.847325,45.242455],[19.84727,45.24245],[19.846855,45.242419],[19.846388,45.242382],[19.846263,45.242373],[19.845367,45.242305],[19.845257,45.242284],[19.84462,45.242101],[19.844133,45.241964],[19.844023,45.24194],[19.843907,45.241939],[19.843848,45.241934],[19.843779,45.241921],[19.843592,45.241884],[19.843095,45.241758],[19.843071,45.241752],[19.842977,45.241728],[19.842794,45.241685]],"type":"LineString"},"maneuver":{"bearing_after":264,"bearing_before":352,"location":[19.847597,45.242475],"modifier":"left","type":"turn"},"mode":"driving","driving_side":"right","name":"Народног фронта","intersections":[{"out":3,"in":2,"entry":[true,true,false,true],"bearings":[0,90,180,270],"location":[19.847597,45.242475]},{"out":2,"in":0,"entry":[false,true,true],"bearings":[90,180,270],"location":[19.846263,45.242373]},{"out":1,"in":0,"entry":[false,true,true],"bearings":[90,255,345],"location":[19.845367,45.242305]},{"out":2,"in":0,"entry":[false,true,true],"bearings":[75,165,255],"location":[19.84462,45.242101]},{"out":1,"in":0,"entry":[false,true,true],"bearings":[75,255,345],"location":[19.843848,45.241934]}],"weight":41.099999999,"duration":41.099999999,"distance":390.4},{"geometry":{"coordinates":[[19.842794,45.241685],[19.84276,45.24175],[19.84274,45.241787],[19.842619,45.242005],[19.842596,45.242046],[19.84235,45.242489],[19.842335,45.242516],[19.842257,45.242657],[19.842232,45.242701],[19.841822,45.243438],[19.84181,45.243461],[19.841756,45.243558],[19.841685,45.243684],[19.841676,45.243701],[19.841664,45.243723],[19.841606,45.24382],[19.841551,45.243923],[19.841501,45.244016],[19.841491,45.244041],[19.840996,45.244924],[19.840878,45.245129],[19.840184,45.246385],[19.840077,45.246577],[19.840025,45.24667],[19.839997,45.246721],[19.839702,45.247249],[19.839422,45.247751],[19.839398,45.247797],[19.839382,45.247825],[19.839324,45.24793]],"type":"LineString"},"maneuver":{"bearing_after":337,"bearing_before":250,"location":[19.842794,45.241685],"modifier":"right","type":"turn"},"mode":"driving","driving_side":"right","name":"Булевар ослобођења","intersections":[{"lanes":[{"valid":false,"indications":["left"]},{"valid":true,"indications":["straight","right"]}],"out":3,"in":0,"entry":[false,false,true,true],"bearings":[75,165,255,345],"location":[19.842794,45.241685]},{"out":3,"in":1,"entry":[true,false,false,true],"bearings":[75,165,255,345],"location":[19.841606,45.24382]},{"out":3,"in":1,"entry":[false,false,true,true],"bearings":[75,165,255,345],"location":[19.841551,45.243923]},{"out":2,"in":1,"entry":[true,false,true],"bearings":[60,165,345],"location":[19.840077,45.246577]}],"weight":73.1,"duration":73.1,"distance":745.7},{"geometry":{"coordinates":[[19.839324,45.24793],[19.839267,45.248029],[19.839123,45.247991],[19.838961,45.247956],[19.838921,45.247948],[19.838686,45.247899],[19.838581,45.24788],[19.838332,45.247833],[19.838155,45.24781],[19.837934,45.247789],[19.837708,45.247778],[19.837489,45.24777],[19.837093,45.247779],[19.836656,45.247808],[19.836611,45.247811],[19.836488,45.247819],[19.83632,45.247843],[19.836276,45.247849],[19.83612,45.247871],[19.835764,45.247937],[19.835516,45.247988],[19.835259,45.24806],[19.835204,45.248076],[19.835038,45.248126],[19.835007,45.248135],[19.834972,45.248147],[19.83483,45.248199]],"type":"LineString"},"maneuver":{"bearing_after":337,"bearing_before":337,"location":[19.839324,45.24793],"modifier":"left","type":"turn"},"mode":"driving","driving_side":"right","name":"Браће Рибникар","intersections":[{"lanes":[{"valid":true,"indications":["left"]},{"valid":false,"indications":["straight"]},{"valid":false,"indications":["straight"]},{"valid":false,"indications":["straight","right"]}],"out":3,"in":1,"entry":[true,false,false,true],"bearings":[75,165,255,345],"location":[19.839324,45.24793]},{"lanes":[{"valid":true,"indications":["left"]},{"valid":false,"indications":["straight"]},{"valid":false,"indications":["straight"]},{"valid":false,"indications":["straight","right"]}],"out":2,"in":1,"entry":[false,false,true,true],"bearings":[75,165,255,345],"location":[19.839267,45.248029]},{"lanes":[{"valid":false,"indications":["left"]},{"valid":true,"indications":["straight","left"]},{"valid":true,"indications":["straight"]},{"valid":false,"indications":["right"]}],"out":2,"in":0,"entry":[false,true,true,false],"bearings":[75,165,255,345],"location":[19.839123,45.247991]},{"out":2,"in":1,"entry":[true,false,true],"bearings":[15,90,285],"location":[19.836488,45.247819]}],"weight":45.4,"duration":45.4,"distance":371.5},{"geometry":{"coordinates":[[19.83483,45.248199],[19.834775,45.24813],[19.834918,45.24808],[19.834956,45.248067],[19.834996,45.248053],[19.835157,45.248002],[19.835275,45.247968],[19.83537,45.247945],[19.835501,45.247913],[19.83577,45.247857],[19.836111,45.247795],[19.836235,45.247774],[19.836276,45.247767],[19.836446,45.24774],[19.836562,45.247729],[19.836604,45.247726],[19.836721,45.247715],[19.836913,45.247702],[19.837105,45.247698],[19.837352,45.247697]],"type":"LineString"},"maneuver":{"bearing_after":208,"bearing_before":293,"location":[19.83483,45.248199],"modifier":"uturn","type":"continue"},"mode":"driving","driving_side":"right","name":"Браће Рибникар","intersections":[{"out":1,"in":0,"entry":[false,true,true],"bearings":[120,210,300],"location":[19.83483,45.248199]},{"out":1,"in":0,"entry":[false,true,true,false],"bearings":[30,120,210,300],"location":[19.834775,45.24813]},{"out":0,"in":2,"entry":[true,true,false],"bearings":[90,195,285],"location":[19.836446,45.24774]}],"weight":26,"duration":26,"distance":218.9},{"geometry":{"coordinates":[[19.837352,45.247697],[19.837352,45.247697]],"type":"LineString"},"maneuver":{"bearing_after":0,"bearing_before":90,"location":[19.837352,45.247697],"modifier":"right","type":"arrive"},"mode":"driving","driving_side":"right","name":"Браће Рибникар","intersections":[{"in":0,"entry":[true],"bearings":[270],"location":[19.837352,45.247697]}],"weight":0,"duration":0,"distance":0}],"summary":"Булевар ослобођења, Браће Рибникар","weight":259.2,"duration":259.2,"distance":2157.6}],"weight_name":"routability","weight":259.2,"duration":259.2,"distance":2157.6}],"waypoints":[{"hint":"51xPiOpcT4gAAAAAQAAAAAAAAAAcAAAAAAAAAJI41kEAAAAAOtE2QQAAAABAAAAAAAAAABwAAACM9QAAQ98uAalOsgIz3y4BRU6yAgAAXwVq-gI_","distance":11.18443301,"name":"","location":[19.849027,45.239977]},{"hint":"7Dwag____38RAAAAGwAAAC8AAACNAAAAYzGbQecpLEGbC1BCiZ4GQxEAAAAbAAAALwAAAI0AAACM9QAAqLEuAdFssgKnsS4BT2yyAgUArwVq-gI_","distance":14.44791615,"name":"Браће Рибникар","location":[19.837352,45.247697]}]}	'),
                                                                                                                                                          ('2022-12-21T17:32:28Z', '2022-12-21T17:45:28Z', '250', 4, '7', 'PENDING', 'false', 'false', 'false', '0', null),
                                                                                                                                                          ('2022-12-22T17:32:28Z', '2022-12-22T17:46:28Z', '250', 1, '7', 'PENDING', 'false', 'false', 'false', '0', null),
                                                                                                                                                          ('2022-07-21T17:32:28Z', '2022-07-21T17:50:28Z', '250', 5, '7', 'PENDING', 'false', 'false', 'false', '0', null);

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

