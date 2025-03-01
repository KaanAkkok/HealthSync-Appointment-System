package com.healthcare.patient.service;

import com.healthcare.patient.dto.PatientDetailsDTO;
import com.healthcare.patient.entity.Patient;
import com.healthcare.patient.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }

    public PatientDetailsDTO getPatientDetails(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        return convertToDTO(patient);
    }

    private PatientDetailsDTO convertToDTO(Patient patient) {
        PatientDetailsDTO dto = new PatientDetailsDTO();
        dto.setPatientName(patient.getPatientName());
        dto.setPatientEmail(patient.getPatientEmail());
        return dto;
    }
} 