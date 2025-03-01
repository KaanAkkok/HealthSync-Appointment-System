package com.healthcare.appointment.service;

import com.healthcare.appointment.client.DoctorServiceClient;
import com.healthcare.appointment.client.EmailServiceClient;
import com.healthcare.appointment.client.PatientServiceClient;
import com.healthcare.appointment.dto.DoctorDetailsDTO;
import com.healthcare.appointment.dto.PatientDetailsDTO;
import com.healthcare.appointment.entity.Appointment;
import com.healthcare.appointment.repository.AppointmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class AppointmentServiceTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    private DoctorServiceClient doctorServiceClient;

    @Mock
    private PatientServiceClient patientServiceClient;

    @Mock
    private EmailServiceClient emailServiceClient;

    @InjectMocks
    private AppointmentService appointmentService;

    @Test
    void createAppointment_ShouldCreateAppointmentSuccessfully() {
        PatientDetailsDTO patientDTO = new PatientDetailsDTO();
        patientDTO.setPatientName("Test Patient");
        patientDTO.setPatientEmail("patient@test.com");

        DoctorDetailsDTO doctorDTO = new DoctorDetailsDTO();
        doctorDTO.setDoctorName("Dr. Test");
        doctorDTO.setDoctorEmail("doctor@test.com");
        doctorDTO.setDoctorAvailableDate("2024-01-20");
        doctorDTO.setDoctorAvailableTime("10:00");

        when(patientServiceClient.getPatientDetails(1L)).thenReturn(patientDTO);
        when(doctorServiceClient.getDoctorDetails(1L)).thenReturn(doctorDTO);
        when(appointmentRepository.save(any(Appointment.class))).thenAnswer(i -> i.getArguments()[0]);

        Appointment result = appointmentService.createAppointment(1L, 1L, "2024-01-20", "10:00");

        assertNotNull(result);
        assertEquals("Test Patient", result.getPatientName());
        assertEquals("Dr. Test", result.getDoctorName());
        verify(emailServiceClient).sendAppointmentDetails(any());
    }

    @Test
    void createAppointment_ShouldThrowException_WhenTimeSlotNotAvailable() {
        DoctorDetailsDTO doctorDTO = new DoctorDetailsDTO();
        doctorDTO.setDoctorAvailableDate("2024-01-20");
        doctorDTO.setDoctorAvailableTime("11:00");

        when(doctorServiceClient.getDoctorDetails(1L)).thenReturn(doctorDTO);

        assertThrows(RuntimeException.class, () -> 
            appointmentService.createAppointment(1L, 1L, "2024-01-20", "10:00")
        );
    }

    @Test
    void getAppointmentsByPatient_ShouldReturnPatientAppointments() {
        PatientDetailsDTO patientDTO = new PatientDetailsDTO();
        patientDTO.setPatientName("Test Patient");
        
        Appointment appointment = new Appointment();
        appointment.setPatientName("Test Patient");
        appointment.setDoctorName("Dr. Test");

        when(patientServiceClient.getPatientDetails(1L)).thenReturn(patientDTO);
        when(appointmentRepository.findByPatientName("Test Patient"))
            .thenReturn(Arrays.asList(appointment));

        List<Appointment> result = appointmentService.getAppointmentsByPatient(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test Patient", result.get(0).getPatientName());
    }

    @Test
    void getAppointmentsByDoctor_ShouldReturnDoctorAppointments() {
        DoctorDetailsDTO doctorDTO = new DoctorDetailsDTO();
        doctorDTO.setDoctorName("Dr. Test");
        
        Appointment appointment = new Appointment();
        appointment.setDoctorName("Dr. Test");
        appointment.setPatientName("Test Patient");

        when(doctorServiceClient.getDoctorDetails(1L)).thenReturn(doctorDTO);
        when(appointmentRepository.findByDoctorName("Dr. Test"))
            .thenReturn(Arrays.asList(appointment));

        List<Appointment> result = appointmentService.getAppointmentsByDoctor(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Dr. Test", result.get(0).getDoctorName());
    }
} 