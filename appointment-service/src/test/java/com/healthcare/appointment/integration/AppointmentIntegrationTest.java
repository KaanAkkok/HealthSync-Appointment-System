package com.healthcare.appointment.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.healthcare.appointment.client.DoctorServiceClient;
import com.healthcare.appointment.client.EmailServiceClient;
import com.healthcare.appointment.client.PatientServiceClient;
import com.healthcare.appointment.dto.DoctorDetailsDTO;
import com.healthcare.appointment.dto.PatientDetailsDTO;
import com.healthcare.appointment.repository.AppointmentRepository;
import com.healthcare.appointment.entity.Appointment;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@AutoConfigureMockMvc
public class AppointmentIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DoctorServiceClient doctorServiceClient;

    @MockBean
    private PatientServiceClient patientServiceClient;

    @MockBean
    private EmailServiceClient emailServiceClient;

    @MockBean
    private AppointmentRepository appointmentRepository;

    @BeforeEach
    void setup() {
        PatientDetailsDTO patientDTO = new PatientDetailsDTO();
        patientDTO.setPatientName("Test Patient");
        patientDTO.setPatientEmail("test@patient.com");

        DoctorDetailsDTO doctorDTO = new DoctorDetailsDTO();
        doctorDTO.setDoctorName("Dr. Test");
        doctorDTO.setDoctorEmail("test@doctor.com");
        doctorDTO.setDoctorAvailableTime("10:00");
        doctorDTO.setDoctorAvailableDate("2024-01-20");

        when(patientServiceClient.getPatientDetails(1L)).thenReturn(patientDTO);
        when(doctorServiceClient.getDoctorDetails(1L)).thenReturn(doctorDTO);
        when(appointmentRepository.existsByDoctorNameAndAppointmentDateAndAppointmentTime(
            anyString(), anyString(), anyString()
        )).thenReturn(false);
        
        when(appointmentRepository.save(any(Appointment.class))).thenAnswer(invocation -> {
            Appointment appointment = invocation.getArgument(0);
            appointment.setAppointmentId(1L);
            return appointment;
        });
    }

    @Test
    void createAppointment_ShouldReturnSuccess() throws Exception {
        mockMvc.perform(post("/api/appointments")
                .param("patientId", "1")
                .param("doctorId", "1")
                .param("appointmentDate", "2024-01-20")
                .param("appointmentTime", "10:00")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getAppointmentsByPatient_ShouldReturnAppointments() throws Exception {
        mockMvc.perform(get("/api/appointments/patient/{patientId}", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void getAppointmentsByDoctor_ShouldReturnAppointments() throws Exception {
        mockMvc.perform(get("/api/appointments/doctor/{doctorId}", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
} 