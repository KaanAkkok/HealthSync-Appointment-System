package com.healthcare.doctor.service;

import com.healthcare.doctor.dto.DoctorDetailsDTO;
import com.healthcare.doctor.entity.Doctor;
import com.healthcare.doctor.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository;

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor createDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }

    public Map<String, String> getAvailableSlots(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        
        return Map.of(
            "availableDate", doctor.getDoctorAvailableDate(),
            "availableTime", doctor.getDoctorAvailableTime()
        );
    }

    public DoctorDetailsDTO getDoctorDetails(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        return convertToDTO(doctor);
    }

    private DoctorDetailsDTO convertToDTO(Doctor doctor) {
        DoctorDetailsDTO dto = new DoctorDetailsDTO();
        dto.setDoctorName(doctor.getDoctorName());
        dto.setDoctorEmail(doctor.getDoctorEmail());
        dto.setSpecialty(doctor.getSpecialty());
        dto.setDoctorAvailableTime(doctor.getDoctorAvailableTime());
        dto.setDoctorAvailableDate(doctor.getDoctorAvailableDate());
        return dto;
    }
} 