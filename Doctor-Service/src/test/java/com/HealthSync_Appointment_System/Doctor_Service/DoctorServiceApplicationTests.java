package com.HealthSync_Appointment_System.Doctor_Service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
class DoctorServiceApplicationTests {

    @Autowired
    private DoctorService doctorService;

    @Test
    void contextLoads() {
        // Verify that the Spring context loads properly
    }

    @Test
    void testCreateAndGetDoctor() {
        // Create test data
        Doctor doctor = createTestDoctor("Test Doctor");
        
        // Save
        Doctor savedDoctor = doctorService.saveDoctor(doctor);
        assertNotNull(savedDoctor);

        // Get and verify
        Doctor foundDoctor = doctorService.findDoctorById(savedDoctor.getId());
        assertNotNull(foundDoctor);
        assertEquals(savedDoctor.getName(), foundDoctor.getName());
    }

    @Test
    void testGetAllDoctors() {
        // Create and save test data
        Doctor doctor = createTestDoctor("Test Doctor List");
        doctorService.saveDoctor(doctor);

        // Test findAll
        List<Doctor> doctors = doctorService.getAllDoctors();
        assertNotNull(doctors);
        assertFalse(doctors.isEmpty());
    }

    @Test
    void testUpdateDoctor() {
        // Create and save test data
        Doctor doctor = createTestDoctor("Update Test");
        Doctor savedDoctor = doctorService.saveDoctor(doctor);

        // Update
        savedDoctor.setSpecialization("Updated Specialization");
        Doctor updatedDoctor = doctorService.saveDoctor(savedDoctor);

        // Verify
        assertEquals("Updated Specialization", updatedDoctor.getSpecialization());
    }

    @Test
    void testDeleteDoctor() {
        // Create and save test data
        Doctor doctor = createTestDoctor("Delete Test");
        Doctor savedDoctor = doctorService.saveDoctor(doctor);

        // Delete
        doctorService.deleteDoctorById(savedDoctor.getId());

        // Verify deletion
        assertThrows(RuntimeException.class, () -> {
            doctorService.findDoctorById(savedDoctor.getId());
        });
    }

    private Doctor createTestDoctor(String name) {
        Doctor doctor = new Doctor();
        doctor.setName(name);
        doctor.setSpecialization("Cardiology");
        return doctor;
    }
}
