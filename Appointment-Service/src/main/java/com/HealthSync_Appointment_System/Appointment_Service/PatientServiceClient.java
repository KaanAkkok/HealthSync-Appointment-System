package com.HealthSync_Appointment_System.Appointment_Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PatientServiceClient {

    @Value("${patient.service.url}")
    private String patientServiceUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public String getPatientDetails(String patientId) {
        String url = patientServiceUrl + "/patients/" + patientId;
        return restTemplate.getForObject(url, String.class); 
    }
}

