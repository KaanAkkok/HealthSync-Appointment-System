package com.healthcare.doctor.integration;

import com.healthcare.doctor.entity.Doctor;
import com.healthcare.doctor.repository.DoctorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class DoctorIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DoctorRepository doctorRepository;

    @BeforeEach
    void setup() {
        Doctor doctor = new Doctor();
        doctor.setDoctorName("Dr. Test");
        doctor.setDoctorEmail("test@doctor.com");
        doctor.setSpecialty("Cardiology");
        doctor.setDoctorAvailableTime("10:00");
        doctor.setDoctorAvailableDate("2024-01-20");
        doctorRepository.save(doctor);
    }

    @Test
    void getAllDoctors_ShouldReturnDoctorList() throws Exception {
        mockMvc.perform(get("/api/doctors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].doctorName").value("Dr. Test"));
    }

    @Test
    void getDoctorDetails_ShouldReturnDoctor() throws Exception {
        mockMvc.perform(get("/api/doctors/1/details"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.doctorName").value("Dr. Test"))
                .andExpect(jsonPath("$.specialty").value("Cardiology"));
    }
} 