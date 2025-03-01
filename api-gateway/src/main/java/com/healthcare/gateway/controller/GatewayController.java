package com.healthcare.gateway.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
@RequiredArgsConstructor
public class GatewayController {
    private final RestTemplate restTemplate;

    @Value("${services.patient.url}")
    private String patientServiceUrl;

    @Value("${services.doctor.url}")
    private String doctorServiceUrl;

    @Value("${services.appointment.url}")
    private String appointmentServiceUrl;

    // Doctor Service Routes
    @GetMapping("/api/doctors")
    public ResponseEntity<Object> getAllDoctors() {
        return restTemplate.getForEntity(doctorServiceUrl + "/api/doctors", Object.class);
    }

    @GetMapping("/api/doctors/{id}")
    public ResponseEntity<Object> getDoctorById(@PathVariable Long id) {
        return restTemplate.getForEntity(doctorServiceUrl + "/api/doctors/" + id, Object.class);
    }

    @PostMapping("/api/doctors")
    public ResponseEntity<Object> createDoctor(@RequestBody Object doctorRequest) {
        return restTemplate.postForEntity(doctorServiceUrl + "/api/doctors", doctorRequest, Object.class);
    }

    // Patient Service Routes

    @GetMapping("/api/patients")
    public ResponseEntity<Object> getAllPatients() {
        return restTemplate.getForEntity(patientServiceUrl + "/api/patients", Object.class);
    }

    @GetMapping("/api/patients/{id}")
    public ResponseEntity<Object> getPatientById(@PathVariable Long id) {
        return restTemplate.getForEntity(patientServiceUrl + "/api/patients/" + id, Object.class);
    }

    @PostMapping("/api/patients")
    public ResponseEntity<Object> createPatient(@RequestBody Object patientRequest) {
        return restTemplate.postForEntity(patientServiceUrl + "/api/patients", patientRequest, Object.class);
    }

    // Appointment Service Routes
    @GetMapping("/api/appointments/patient/{patientId}")
    public ResponseEntity<Object> getPatientAppointments(@PathVariable Long patientId) {
        return restTemplate.getForEntity(
            appointmentServiceUrl + "/api/appointments/patient/" + patientId, 
            Object.class
        );
    }

    @GetMapping("/api/appointments/doctor/{doctorId}")
    public ResponseEntity<Object> getDoctorAppointments(@PathVariable Long doctorId) {
        return restTemplate.getForEntity(
            appointmentServiceUrl + "/api/appointments/doctor/" + doctorId, 
            Object.class
        );
    }

    @PostMapping("/api/appointments")
    public ResponseEntity<Object> createAppointment(@RequestBody Object appointmentRequest) {
        return restTemplate.postForEntity(
            appointmentServiceUrl + "/api/appointments",
            appointmentRequest,
            Object.class
        );
    }
} 