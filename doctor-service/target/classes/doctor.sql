CREATE DATABASE IF NOT EXISTS doctor_db;

USE doctor_db;

CREATE TABLE IF NOT EXISTS doctors (
    doctor_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    doctor_name VARCHAR(100) NOT NULL,
    specialty VARCHAR(100) NOT NULL,
    doctor_email VARCHAR(100) UNIQUE NOT NULL,
    doctor_available_time TIME NOT NULL,
    doctor_available_date DATE NOT NULL
);

INSERT INTO doctors (doctor_name, specialty, doctor_email, doctor_available_time, doctor_available_date) 
VALUES 
('Ahmet', 'Kardiyoloji', 'ahmet@test.com', '09:00', '16-01-2025'),
('Ali', 'Pediatri', 'ali@test.com', '10:00', '17-01-2025'),
('Suay', 'Nöroloji', 'suay@test.com', '11:00', '18-01-2025');

