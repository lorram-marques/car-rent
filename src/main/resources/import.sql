INSERT INTO tb_client (name, email) VALUES ('Anderson', 'anderson@gmail');

INSERT INTO tb_car (manufacture_year, model) VALUES (2024, 'Bulldozer');

INSERT INTO tb_rentlog (rent_date, return_date) VALUES ('2024-08-17T10:10:00Z', '2024-08-23T10:30:00Z');

INSERT INTO tb_rentlog_car (rentlog_id, car_id) VALUES (1, 1);

INSERT INTO tb_rentlog_client (rentlog_id, client_id) VALUES (1, 1);