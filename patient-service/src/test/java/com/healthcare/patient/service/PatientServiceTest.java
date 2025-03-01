package com.healthcare.patient.service;

import com.healthcare.patient.entity.Patient;
import com.healthcare.patient.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientService patientService;

    @Test
    void getAllPatients_ShouldReturnListOfPatients() {
        Patient patient1 = new Patient();
        patient1.setPatientName("Test Patient 1");
        patient1.setPatientEmail("test1@test.com");

        Patient patient2 = new Patient();
        patient2.setPatientName("Test Patient 2");
        patient2.setPatientEmail("test2@test.com");

        when(patientRepository.findAll()).thenReturn(Arrays.asList(patient1, patient2));

        List<Patient> result = patientService.getAllPatients();

        assertEquals(2, result.size());
        assertEquals("Test Patient 1", result.get(0).getPatientName());
    }

    @Test
    void createPatient_ShouldReturnSavedPatient() {
        Patient patient = new Patient();
        patient.setPatientName("Test Patient");
        patient.setPatientEmail("test@test.com");

        when(patientRepository.save(any(Patient.class))).thenReturn(patient);

        Patient result = patientService.createPatient(patient);

        assertNotNull(result);
        assertEquals("Test Patient", result.getPatientName());
        assertEquals("test@test.com", result.getPatientEmail());
    }
} 