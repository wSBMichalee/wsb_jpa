insert into address (id, address_line1, address_line2, city, postal_code)
values (901, 'xx', 'yy', 'city', '60-400');

INSERT INTO Doctor (id, doctorNumber, email, firstName, lastName, telephoneNumber, specialization)
VALUES (1, 'D12345', 'dr.kowalski@example.com', 'Jan', 'Kowalski', '987-654-321', 'Kardiologia');

INSERT INTO Patient (id, firstName, lastName, telephoneNumber, email, patientNumber, dateOfBirth, address_id)
VALUES (1, 'Anna', 'Nowak', '123-456-789', 'anna.nowak@example.com', 'P67890', '1990-05-14', 901);

INSERT INTO Visit (id, description, time, doctor_id, patient_id, treatment_id)
VALUES (1, 'Wizyta kontrolna', '2025-03-29 10:00:00', 1, 1, NULL);

INSERT INTO MedicalTreatment (id, description, type)
VALUES (1, 'Antybiotykoterapia', 'Lekarstwo');