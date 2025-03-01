package com.healthcare.appointment.client;

import com.healthcare.appointment.dto.AppointmentDetailsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class EmailServiceClient {
    private final RestTemplate restTemplate;

    @Value("${services.email.url}")
    private String emailServiceUrl;

    public void sendAppointmentDetails(AppointmentDetailsDTO appointmentDetailsDTO) {
        restTemplate.postForObject(
                emailServiceUrl + "/api/email/send",
                appointmentDetailsDTO,
                Void.class
        );
    }
} 