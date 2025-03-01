package com.healthcare.appointment.repository;

import com.healthcare.appointment.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
	List<Appointment> findByPatientName(String patientName);
	List<Appointment> findByDoctorName(String doctorName);
	boolean existsByDoctorNameAndAppointmentDateAndAppointmentTime(
		String doctorName, 
		String appointmentDate, 
		String appointmentTime
	);
} 