package com.HealthSync_Appointment_System.Patient_Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    public List<PatientDetailsDTO> findAll() {
        return patientRepository.findAll().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    public PatientDetailsDTO findById(String id) {
        Patient patient = patientRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Patient not found"));
        return convertToDTO(patient);
    }

    public PatientDetailsDTO getPatientDetails(String id) {
        return findById(id);
    }

    public PatientDetailsDTO save(PatientDetailsDTO dto) {
        Patient patient = convertToEntity(dto);
        Patient savedPatient = patientRepository.save(patient);
        return convertToDTO(savedPatient);
    }

    public PatientDetailsDTO update(String id, PatientDetailsDTO dto) {
        Patient patient = patientRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Patient not found"));
        updatePatientFromDTO(patient, dto);
        return convertToDTO(patientRepository.save(patient));
    }

    public void delete(String id) {
        patientRepository.deleteById(id);
    }

    private PatientDetailsDTO convertToDTO(Patient patient) {
        PatientDetailsDTO dto = new PatientDetailsDTO();
        dto.setPatientName(patient.getPatientName());
        dto.setPatientEmail(patient.getPatientEmail());
        dto.setPatientAvailableTime(patient.getPatientAvailableTime());
        dto.setPatientAvailableDate(patient.getPatientAvailableDate());
        return dto;
    }

    private Patient convertToEntity(PatientDetailsDTO dto) {
        Patient patient = new Patient();
        updatePatientFromDTO(patient, dto);
        return patient;
    }

    private void updatePatientFromDTO(Patient patient, PatientDetailsDTO dto) {
        patient.setPatientName(dto.getPatientName());
        patient.setPatientEmail(dto.getPatientEmail());
        patient.setPatientAvailableTime(dto.getPatientAvailableTime());
        patient.setPatientAvailableDate(dto.getPatientAvailableDate());
    }
}
