/*package com.HealthSync_Appointment_System.Patient_Service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
class PatientServiceApplicationTests {

    @Autowired
    private PatientService patientService;

    @Test
    void contextLoads() {
    }

    @Test
    void testCreateAndGetPatient() {
        // Create test data
        PatientDetailsDTO dto = createTestPatientDTO("Test Patient");
        
        // Save
        PatientDetailsDTO savedDto = patientService.save(dto);
        assertNotNull(savedDto);

        // Get and verify using saved name
        PatientDetailsDTO foundDto = patientService.getPatientDetails(savedDto.getPatientName());
        assertNotNull(foundDto);
        assertEquals(savedDto.getPatientName(), foundDto.getPatientName());
    }

    @Test
    void testGetAllPatients() {
        // Create and save test data
        PatientDetailsDTO dto = createTestPatientDTO("Test Patient List");
        patientService.save(dto);

        // Test findAll
        List<PatientDetailsDTO> patients = patientService.findAll();
        assertNotNull(patients);
        assertFalse(patients.isEmpty());
    }

    @Test
    void testUpdatePatient() {
        // Create and save test data
        PatientDetailsDTO dto = createTestPatientDTO("Update Test");
        PatientDetailsDTO savedDto = patientService.save(dto);

        // Update
        savedDto.setPatientEmail("updated@email.com");
        PatientDetailsDTO updatedDto = patientService.update(savedDto.getPatientName(), savedDto);
        
        // Verify
        assertEquals("updated@email.com", updatedDto.getPatientEmail());
    }

    @Test
    void testDeletePatient() {
        // Create and save test data
        PatientDetailsDTO dto = createTestPatientDTO("Delete Test");
        PatientDetailsDTO savedDto = patientService.save(dto);

        // Delete
        patientService.delete(savedDto.getPatientName());

        // Verify deletion
        assertThrows(RuntimeException.class, () -> {
            patientService.findById(savedDto.getPatientName());
        });
    }

    private PatientDetailsDTO createTestPatientDTO(String name) {
        PatientDetailsDTO dto = new PatientDetailsDTO();
        dto.setPatientName(name);
        dto.setPatientEmail(name.toLowerCase().replace(" ", "") + "@test.com");
        dto.setPatientAvailableTime("10:00");
        dto.setPatientAvailableDate("2024-03-20");
        return dto;
    }
}
*/