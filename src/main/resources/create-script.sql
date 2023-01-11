-- Remove conflicting tables
DROP TABLE IF EXISTS appointment CASCADE;
DROP TABLE IF EXISTS doctor CASCADE;
DROP TABLE IF EXISTS patient CASCADE;
DROP TABLE IF EXISTS doctor_patient CASCADE;
-- End of removing

CREATE TABLE appointment (
                             id_appointment SERIAL NOT NULL,
                             id_patient INTEGER NOT NULL,
                             id_doctor INTEGER NOT NULL,
                             time_from TIMESTAMP NOT NULL,
                             time_to TIMESTAMP NOT NULL
);
ALTER TABLE appointment ADD CONSTRAINT pk_appointment PRIMARY KEY (id_appointment);

CREATE TABLE doctor (
                        id_doctor SERIAL NOT NULL,
                        name VARCHAR(256) NOT NULL,
                        email_address VARCHAR(256) NOT NULL,
                        phone_number VARCHAR(256) NOT NULL
);
ALTER TABLE doctor ADD CONSTRAINT pk_doctor PRIMARY KEY (id_doctor);

CREATE TABLE patient (
                         id_patient SERIAL NOT NULL,
                         name VARCHAR(256) NOT NULL,
                         birthdate DATE NOT NULL,
                         email_address VARCHAR(256) NOT NULL,
                         phone_number VARCHAR(256) NOT NULL
);
ALTER TABLE patient ADD CONSTRAINT pk_patient PRIMARY KEY (id_patient);

CREATE TABLE doctor_patient (
                                id_doctor INTEGER NOT NULL,
                                id_patient INTEGER NOT NULL
);
ALTER TABLE doctor_patient ADD CONSTRAINT pk_doctor_patient PRIMARY KEY (id_doctor, id_patient);

ALTER TABLE appointment ADD CONSTRAINT fk_appointment_patient FOREIGN KEY (id_patient) REFERENCES patient (id_patient) ON DELETE CASCADE;
ALTER TABLE appointment ADD CONSTRAINT fk_appointment_doctor FOREIGN KEY (id_doctor) REFERENCES doctor (id_doctor) ON DELETE CASCADE;

ALTER TABLE doctor_patient ADD CONSTRAINT fk_doctor_patient_doctor FOREIGN KEY (id_doctor) REFERENCES doctor (id_doctor) ON DELETE CASCADE;
ALTER TABLE doctor_patient ADD CONSTRAINT fk_doctor_patient_patient FOREIGN KEY (id_patient) REFERENCES patient (id_patient) ON DELETE CASCADE;