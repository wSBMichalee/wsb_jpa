
INSERT INTO address (id, address_line1, address_line2, city, postal_code)
VALUES (901, 'xx', 'yy', 'city', '60-400');

INSERT INTO address (id, address_line1, address_line2, city, postal_code)
VALUES (1, 'Marszałkowska 126', '', 'Warszawa', '00-008');

INSERT INTO address (id, address_line1, address_line2, city, postal_code)
VALUES (2, 'Floriańska 45', '', 'Kraków', '31-019');

INSERT INTO address (id, address_line1, address_line2, city, postal_code)
VALUES (3, 'Długa 12', '', 'Gdańsk', '80-831');

INSERT INTO address (id, address_line1, address_line2, city, postal_code)
VALUES (4, 'Półwiejska 32', '', 'Poznań', '61-888');


INSERT INTO patients (id, first_name, last_name, telephone_number, email, patient_number, date_of_birth, height, address_id )
VALUES (1, 'Jan', 'Kowalski', '+48 123 456 789', 'jan.kowalski@example.com', 'P001', '1980-01-01',145, 1);

INSERT INTO patients (id, first_name, last_name, telephone_number, email, patient_number, date_of_birth, height ,address_id)
VALUES (2, 'Anna', 'Nowak', '+48 234 567 890', 'anna.nowak@example.com', 'P002', '1975-05-02', 166,2);

INSERT INTO patients (id, first_name, last_name, telephone_number, email, patient_number, date_of_birth, height, address_id)
VALUES (3, 'Piotr', 'Wiśniewski', '+48 345 678 901', 'piotr.wisniewski@example.com', 'P003', '1990-12-10', 186,3);


INSERT INTO doctors (id, first_name, last_name, telephone_number, email, doctor_number, specialization, address_id)
VALUES (1, 'Maria', 'Zielińska', '+48 456 789 012', 'maria.zielinska@example.com', 'D001', 'GP', 4);

INSERT INTO doctors (id, first_name, last_name, telephone_number, email, doctor_number, specialization, address_id)
VALUES (2, 'Tomasz', 'Lewandowski', '+48 567 890 123', 'tomasz.lewandowski@example.com', 'D002', 'OCULIST',1);

INSERT INTO doctors (id, first_name, last_name, telephone_number, email, doctor_number, specialization, address_id)
VALUES (3, 'Agnieszka', 'Dąbrowska', '+48 678 901 234', 'agnieszka.dabrowska@example.com', 'D003', 'DERMATOLOGIST',2);

INSERT INTO doctors (id, first_name, last_name, telephone_number, email, doctor_number, specialization, address_id)
VALUES (4, 'Radowsław', 'Rumian', '+48 7333 732 777', 'radosław.rumian@example.com', 'D004', 'OCULIST',4);


INSERT INTO medical_treatments (id, description, type)
VALUES (1, 'Badanie ultrasonograficzne jamy brzusznej', 'USG');

INSERT INTO medical_treatments (id, description, type)
VALUES (2, 'Badanie elektrokardiograficzne', 'EKG');

INSERT INTO medical_treatments (id, description, type)
VALUES (3, 'Zdjęcie rentgenowskie klatki piersiowej', 'RTG');



INSERT INTO visit (id, visit_date, description, patient_id, doctor_id)
VALUES (1, '2023-06-10 10:00:00', 'Kontrola ciśnienia, zalecenie diety niskosodowej', 1, 1);


INSERT INTO visit (id, visit_date, description, patient_id, doctor_id)
VALUES (2, '2023-06-11 11:30:00', 'Badanie wzroku, przepisanie nowych okularów', 2, 2);

INSERT INTO visit (id, visit_date, description, patient_id, doctor_id)
VALUES (3, '2023-06-12 14:00:00', 'Leczenie trądziku, przepisanie maści', 3, 3);

INSERT INTO visit (id, visit_date, description, patient_id, doctor_id)
VALUES (4, '2023-06-15 16:00:00', 'Kolejna kontrola', 1, 2);


INSERT INTO VISIT_TREATMENT (visit_id, treatment_id) VALUES (1, 2);
INSERT INTO VISIT_TREATMENT (visit_id, treatment_id) VALUES (1, 1);
INSERT INTO VISIT_TREATMENT (visit_id, treatment_id) VALUES (3, 1);
