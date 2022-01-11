INSERT INTO tenant1.users (id, user_surname, user_name, user_login, user_role) VALUES ('ac99765a88324e78bc54f9549678d0ab', 'Chandler', 'Sharat', 'user', 'USER') ON CONFLICT DO NOTHING;
INSERT INTO tenant1.users (id, user_surname, user_name, user_login, user_role) VALUES ('9c6d80c8381d446b9a241b3fbc2e9567', 'Reinhold', 'Mark', 'admin', 'ADMIN') ON CONFLICT DO NOTHING;
INSERT INTO tenant1.users (id, user_surname, user_name, user_login, user_role) VALUES ('cca76b85f24d490b8df7fd8d91743835', 'Systelab', 'Systelab', 'Systelab', 'ADMIN') ON CONFLICT DO NOTHING;


INSERT INTO tenant1.TESTS(id, code, loinc_code, short_name) VALUES('ef23a3b645454181b086ad0cd1e1624f','A00185','A00185','PT') ON CONFLICT DO NOTHING;
INSERT INTO tenant1.TESTS(id, code, loinc_code, short_name) VALUES('93280308290142fe8680d2a889e4bc1c','A00285','A00285','APTT') ON CONFLICT DO NOTHING;
INSERT INTO tenant1.TESTS(id, code, loinc_code, short_name) VALUES('afb9bdf9a9c24ca48d288a8827f4f004','A00385','A00385','DD') ON CONFLICT DO NOTHING;
INSERT INTO tenant1.TESTS(id, code, loinc_code, short_name) VALUES('cbd8d68ea02949e4a3f389d56d065675','A00685','A00685','FIBC') ON CONFLICT DO NOTHING;


INSERT INTO tenant1.patients (id, name) VALUES ('786632662d4e44b090b40340cfa83b51', 'Josh Long') ON CONFLICT DO NOTHING;
INSERT INTO tenant1.patients (id, name) VALUES ('ac99765a88324e78bc54f9549678d0ab', 'Viktor Rentea') ON CONFLICT DO NOTHING;

INSERT INTO tenant1.samples (id, barcode, fk_patient) VALUES ('bd1fa0e3f52b4b62ad7c0ca07a6df798', '000001','786632662d4e44b090b40340cfa83b51') ON CONFLICT DO NOTHING;
INSERT INTO tenant1.samples (id, barcode, fk_patient) VALUES ('cca76b85f24d490b8df7fd8d91743835', '000002','786632662d4e44b090b40340cfa83b51') ON CONFLICT DO NOTHING;
INSERT INTO tenant1.samples (id, barcode, fk_patient) VALUES ('63b8e56beb714d3f8327b89e488e0331', '000003','ac99765a88324e78bc54f9549678d0ab') ON CONFLICT DO NOTHING;
INSERT INTO tenant1.samples (id, barcode, fk_patient) VALUES ('653a7f1109574d58860ffc1af8053383', '000004','ac99765a88324e78bc54f9549678d0ab') ON CONFLICT DO NOTHING;

INSERT INTO tenant1.results (id, fk_sample, fk_test, result) VALUES ('64b5d92996264a5e85fe207b132bef5e', 'bd1fa0e3f52b4b62ad7c0ca07a6df798','ef23a3b645454181b086ad0cd1e1624f','23') ON CONFLICT DO NOTHING;



INSERT INTO tenant2.users (id, user_surname, user_name, user_login, user_role) VALUES ('ac99765a88324e78bc54f9549678d0ab', 'Chandler', 'Sharat', 'user', 'USER') ON CONFLICT DO NOTHING;
INSERT INTO tenant2.users (id, user_surname, user_name, user_login, user_role) VALUES ('9c6d80c8381d446b9a241b3fbc2e9567', 'Reinhold', 'Mark', 'admin', 'ADMIN') ON CONFLICT DO NOTHING;
INSERT INTO tenant2.users (id, user_surname, user_name, user_login, user_role) VALUES ('cca76b85f24d490b8df7fd8d91743835', 'Systelab', 'Systelab', 'Systelab','ADMIN') ON CONFLICT DO NOTHING;


INSERT INTO tenant2.TESTS(id, code, loinc_code, short_name) VALUES('ef23a3b645454181b086ad0cd1e1624f','A00185','A00185','PT') ON CONFLICT DO NOTHING;
INSERT INTO tenant2.TESTS(id, code, loinc_code, short_name) VALUES('93280308290142fe8680d2a889e4bc1c','A00285','A00285','APTT') ON CONFLICT DO NOTHING;
INSERT INTO tenant2.TESTS(id, code, loinc_code, short_name) VALUES('afb9bdf9a9c24ca48d288a8827f4f004','A00385','A00385','DD') ON CONFLICT DO NOTHING;
INSERT INTO tenant2.TESTS(id, code, loinc_code, short_name) VALUES('cbd8d68ea02949e4a3f389d56d065675','A00685','A00685','FIBC') ON CONFLICT DO NOTHING;


INSERT INTO tenant2.patients (id, name) VALUES ('786632662d4e44b090b40340cfa83b51', 'Antonio Goncalves') ON CONFLICT DO NOTHING;
INSERT INTO tenant2.patients (id, name) VALUES ('ac99765a88324e78bc54f9549678d0ab', 'Matt Raible') ON CONFLICT DO NOTHING;

INSERT INTO tenant2.samples (id, barcode, fk_patient) VALUES ('bd1fa0e3f52b4b62ad7c0ca07a6df798', '000001','786632662d4e44b090b40340cfa83b51') ON CONFLICT DO NOTHING;
INSERT INTO tenant2.samples (id, barcode, fk_patient) VALUES ('cca76b85f24d490b8df7fd8d91743835', '000002','786632662d4e44b090b40340cfa83b51') ON CONFLICT DO NOTHING;
INSERT INTO tenant2.samples (id, barcode, fk_patient) VALUES ('63b8e56beb714d3f8327b89e488e0331', '000003','ac99765a88324e78bc54f9549678d0ab') ON CONFLICT DO NOTHING;
INSERT INTO tenant2.samples (id, barcode, fk_patient) VALUES ('653a7f1109574d58860ffc1af8053383', '000004','ac99765a88324e78bc54f9549678d0ab') ON CONFLICT DO NOTHING;

INSERT INTO tenant2.results (id, fk_sample, fk_test, result) VALUES ('64b5d92996264a5e85fe207b132bef5e', 'bd1fa0e3f52b4b62ad7c0ca07a6df798','ef23a3b645454181b086ad0cd1e1624f','23') ON CONFLICT DO NOTHING;
