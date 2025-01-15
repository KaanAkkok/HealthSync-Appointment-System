package com.HealthSync_Appointment_System.Appointment_Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class DoctorServiceClient {

    @Value("${doctor.service.url}")
    private String doctorServiceUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public String getDoctorDetails(String doctorId) {
        String url = doctorServiceUrl + "/doctors/" + doctorId;
        return restTemplate.getForObject(url, String.class); 
    }
}

