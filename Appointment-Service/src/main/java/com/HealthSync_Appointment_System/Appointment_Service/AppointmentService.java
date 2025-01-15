package com.HealthSync_Appointment_System.Appointment_Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private EmailServiceClient emailServiceClient;

    public Appointment createAppointment(AppointmentDetailsDTO dto) {
        Appointment appointment = new Appointment();
        appointment.setPatientName(dto.getPatientName());
        appointment.setPatientEmail(dto.getPatientEmail());
        appointment.setDoctorName(dto.getDoctorName());
        appointment.setDoctorEmail(dto.getDoctorEmail());
        appointment.setAppointmentDate(dto.getAppointmentDate());
        appointment.setAppointmentTime(dto.getAppointmentTime());

        Appointment savedAppointment = appointmentRepository.save(appointment);

        emailServiceClient.notifyAppointment(savedAppointment);

        return savedAppointment;
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }
}

