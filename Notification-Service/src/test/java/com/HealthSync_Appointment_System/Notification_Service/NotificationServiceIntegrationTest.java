package com.HealthSync_Appointment_System.Notification_Service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.mockito.Spy;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NotificationServiceIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    @Spy
    private JavaMailSender mailSender;

    @Test
    void testSendEmailEndpoint() {

        AppointmentDetailsDTO appointmentDetailsDTO = new AppointmentDetailsDTO();
        appointmentDetailsDTO.setPatientName("Mehmet hastaTest");
        appointmentDetailsDTO.setPatientEmail("hastaTest@gmail.com");
        appointmentDetailsDTO.setDoctorName("Ali doktorTest");
        appointmentDetailsDTO.setDoctorEmail("doktorTest@gmail.com");
        appointmentDetailsDTO.setAppointmentDate("30-12-2024");
        appointmentDetailsDTO.setAppointmentTime("14:30");

        String url = "http://localhost:" + port + "/notifications/send-email";
        ResponseEntity<Void> response = restTemplate.postForEntity(url, appointmentDetailsDTO, Void.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(mailSender, times(2)).send(any(SimpleMailMessage.class));
    }
}