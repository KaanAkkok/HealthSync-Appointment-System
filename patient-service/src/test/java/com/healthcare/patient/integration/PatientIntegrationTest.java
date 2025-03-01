package com.healthcare.patient.integration;

import com.healthcare.patient.entity.Patient;
import com.healthcare.patient.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PatientIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PatientRepository patientRepository;

    @BeforeEach
    void setup() {
        Patient patient = new Patient();
        patient.setPatientName("Test Patient");
        patient.setPatientEmail("test@test.com");
        patientRepository.save(patient);
    }

    @Test
    void createPatient_ShouldReturnSuccess() throws Exception {
        String patientJson = "{"
            + "\"patientName\": \"New Patient\","
            + "\"patientEmail\": \"new@test.com\""
            + "}";

        mockMvc.perform(post("/api/patients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(patientJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.patientName").value("New Patient"));
    }

    @Test
    void getPatientDetails_ShouldReturnPatient() throws Exception {
        mockMvc.perform(get("/api/patients/1/details"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.patientName").exists());
    }
} 