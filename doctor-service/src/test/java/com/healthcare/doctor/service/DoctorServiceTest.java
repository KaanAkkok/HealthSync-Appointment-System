package com.healthcare.doctor.service;

import com.healthcare.doctor.dto.DoctorDetailsDTO;
import com.healthcare.doctor.entity.Doctor;
import com.healthcare.doctor.repository.DoctorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DoctorServiceTest {

    @Mock
    private DoctorRepository doctorRepository;

    @InjectMocks
    private DoctorService doctorService;

    @Test
    void getAllDoctors_ShouldReturnListOfDoctors() {
        Doctor doctor1 = new Doctor();
        doctor1.setDoctorName("Dr. Test 1");
        doctor1.setSpecialty("Cardiology");

        Doctor doctor2 = new Doctor();
        doctor2.setDoctorName("Dr. Test 2");
        doctor2.setSpecialty("Neurology");

        when(doctorRepository.findAll()).thenReturn(Arrays.asList(doctor1, doctor2));

        List<Doctor> result = doctorService.getAllDoctors();

        assertEquals(2, result.size());
        assertEquals("Dr. Test 1", result.get(0).getDoctorName());
        assertEquals("Cardiology", result.get(0).getSpecialty());
    }

    @Test
    void getDoctorDetails_ShouldReturnDoctorDTO() {
        Doctor doctor = new Doctor();
        doctor.setDoctorName("Dr. Test");
        doctor.setDoctorEmail("doctor@test.com");
        doctor.setSpecialty("Cardiology");
        doctor.setDoctorAvailableTime("10:00");
        doctor.setDoctorAvailableDate("2024-01-20");

        when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor));

        DoctorDetailsDTO result = doctorService.getDoctorDetails(1L);

        assertNotNull(result);
        assertEquals("Dr. Test", result.getDoctorName());
        assertEquals("Cardiology", result.getSpecialty());
    }
} 