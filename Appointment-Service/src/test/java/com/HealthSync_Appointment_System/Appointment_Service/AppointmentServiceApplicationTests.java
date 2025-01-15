package com.HealthSync_Appointment_System.Appointment_Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class AppointmentServiceTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    private EmailServiceClient emailServiceClient;

    @InjectMocks
    private AppointmentService appointmentService;

    private AppointmentDetailsDTO dto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        dto = new AppointmentDetailsDTO();
        dto.setPatientName("John Doe");
        dto.setPatientEmail("john.doe@example.com");
        dto.setDoctorName("Dr. Smith");
        dto.setDoctorEmail("dr.smith@example.com");
        dto.setAppointmentDate("2024-12-30");
        dto.setAppointmentTime("14:30");
    }

    @Test
    void testCreateAppointment() {
        Appointment appointment = new Appointment();
        appointment.setId(1L);

        when(appointmentRepository.save(any(Appointment.class))).thenReturn(appointment);

        appointmentService.createAppointment(dto);

        verify(appointmentRepository, times(1)).save(any(Appointment.class));
        verify(emailServiceClient, times(1)).notifyAppointment(any(Appointment.class));
    }
}

