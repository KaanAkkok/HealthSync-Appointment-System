package com.healthcare.appointment.client;

import com.healthcare.appointment.dto.DoctorDetailsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class DoctorServiceClient {
    private final RestTemplate restTemplate;

    @Value("${services.doctor.url}")
    private String doctorServiceUrl;

    public DoctorDetailsDTO getDoctorDetails(Long doctorId) {
        return restTemplate.getForObject(
                doctorServiceUrl + "/api/doctors/{id}/details",
                DoctorDetailsDTO.class,
                doctorId
        );
    }
} 