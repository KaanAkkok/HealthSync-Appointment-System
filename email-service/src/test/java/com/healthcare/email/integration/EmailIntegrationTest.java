package com.healthcare.email.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmailIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void sendEmail_ShouldReturnSuccess() throws Exception {
        String appointmentJson = "{"
            + "\"patientName\": \"Test Patient\","
            + "\"patientEmail\": \"patient@test.com\","
            + "\"doctorName\": \"Dr. Test\","
            + "\"doctorEmail\": \"doctor@test.com\","
            + "\"appointmentDate\": \"2024-01-20\","
            + "\"appointmentTime\": \"10:00\""
            + "}";

        mockMvc.perform(post("/api/email/send")
                .contentType(MediaType.APPLICATION_JSON)
                .content(appointmentJson))
                .andExpect(status().isOk());
    }
} 