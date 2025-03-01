package com.healthcare.appointment.client;

import com.healthcare.appointment.dto.PatientDetailsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class PatientServiceClient {
    private final RestTemplate restTemplate;

    @Value("${services.patient.url}")
    private String patientServiceUrl;

    public PatientDetailsDTO getPatientDetails(Long patientId) {
        return restTemplate.getForObject(
                patientServiceUrl + "/api/patients/{id}/details",
                PatientDetailsDTO.class,
                patientId
        );
    }
} 