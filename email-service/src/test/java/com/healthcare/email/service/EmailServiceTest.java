package com.healthcare.email.service;

import com.healthcare.email.dto.AppointmentDetailsDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
public class EmailServiceTest {

    @InjectMocks
    private EmailService emailService;

    @Test
    void sendAppointmentConfirmation_ShouldSendEmailSuccessfully() {
        AppointmentDetailsDTO appointmentDTO = new AppointmentDetailsDTO();
        appointmentDTO.setPatientName("Test Patient");
        appointmentDTO.setPatientEmail("patient@test.com");
        appointmentDTO.setDoctorName("Dr. Test");
        appointmentDTO.setDoctorEmail("doctor@test.com");
        appointmentDTO.setAppointmentDate("2024-01-20");
        appointmentDTO.setAppointmentTime("10:00");

        assertDoesNotThrow(() -> emailService.sendAppointmentConfirmation(appointmentDTO));
    }
} 