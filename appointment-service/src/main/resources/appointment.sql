CREATE DATABASE IF NOT EXISTS appointment_db;

USE appointment_db;

CREATE TABLE IF NOT EXISTS appointments (
    appointment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    patient_name VARCHAR(100) NOT NULL,
    patient_email VARCHAR(100) NOT NULL,
    doctor_name VARCHAR(100) NOT NULL,
    doctor_email VARCHAR(100) NOT NULL,
    appointment_date DATE NOT NULL,
    appointment_time TIME NOT NULL
);