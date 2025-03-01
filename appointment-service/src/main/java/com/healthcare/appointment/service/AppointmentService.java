package com.healthcare.appointment.service;

import com.healthcare.appointment.client.DoctorServiceClient;
import com.healthcare.appointment.client.EmailServiceClient;
import com.healthcare.appointment.client.PatientServiceClient;
import com.healthcare.appointment.dto.AppointmentDetailsDTO;
import com.healthcare.appointment.dto.DoctorDetailsDTO;
import com.healthcare.appointment.dto.PatientDetailsDTO;
import com.healthcare.appointment.entity.Appointment;
import com.healthcare.appointment.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final DoctorServiceClient doctorServiceClient;
    private final PatientServiceClient patientServiceClient;
    private final EmailServiceClient emailServiceClient;

    private boolean isTimeSlotAvailable(String doctorName, String date, String time) {
        return !appointmentRepository.existsByDoctorNameAndAppointmentDateAndAppointmentTime(
            doctorName, 
            date, 
            time
        );
    }

    public Appointment createAppointment(Long patientId, Long doctorId, String date, String time) {
        DoctorDetailsDTO doctor = doctorServiceClient.getDoctorDetails(doctorId);
        
        if (!isTimeSlotAvailable(doctor.getDoctorName(), date, time)) {
            throw new RuntimeException("Selected time slot is not available");
        }

        PatientDetailsDTO patientDetails = patientServiceClient.getPatientDetails(patientId);

        Appointment appointment = new Appointment();
        appointment.setPatientName(patientDetails.getPatientName());
        appointment.setPatientEmail(patientDetails.getPatientEmail());
        appointment.setDoctorName(doctor.getDoctorName());
        appointment.setDoctorEmail(doctor.getDoctorEmail());
        appointment.setAppointmentDate(date);
        appointment.setAppointmentTime(time);

        appointment = appointmentRepository.save(appointment);

        AppointmentDetailsDTO appointmentDTO = new AppointmentDetailsDTO();
        appointmentDTO.setPatientName(appointment.getPatientName());
        appointmentDTO.setPatientEmail(appointment.getPatientEmail());
        appointmentDTO.setDoctorName(appointment.getDoctorName());
        appointmentDTO.setDoctorEmail(appointment.getDoctorEmail());
        appointmentDTO.setAppointmentDate(appointment.getAppointmentDate());
        appointmentDTO.setAppointmentTime(appointment.getAppointmentTime());

        emailServiceClient.sendAppointmentDetails(appointmentDTO);

        return appointment;
    }

    public List<Appointment> getAppointmentsByPatient(Long patientId) {
        PatientDetailsDTO patient = patientServiceClient.getPatientDetails(patientId);
        return appointmentRepository.findByPatientName(patient.getPatientName());
    }

    public List<Appointment> getAppointmentsByDoctor(Long doctorId) {
        DoctorDetailsDTO doctor = doctorServiceClient.getDoctorDetails(doctorId);
        return appointmentRepository.findByDoctorName(doctor.getDoctorName());
    }
} 