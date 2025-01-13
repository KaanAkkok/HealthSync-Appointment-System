package com.HealthSync_Appointment_System.Patient_Service;

import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping
    public List<PatientDetailsDTO> getAllPatients() {
        return patientService.findAll();
    }

    @GetMapping("/{id}")
    public PatientDetailsDTO getPatientById(@PathVariable String id) {
        return patientService.findById(id);
    }

    @GetMapping("/details/{id}")
    public PatientDetailsDTO getPatientDetails(@PathVariable String id) {
        return patientService.getPatientDetails(id);
    }

    @PostMapping
    public PatientDetailsDTO createPatient(@RequestBody PatientDetailsDTO dto) {
        return patientService.save(dto);
    }

    @PutMapping("/{id}")
    public PatientDetailsDTO updatePatient(@PathVariable String id, @RequestBody PatientDetailsDTO dto) {
        return patientService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable String id) {
        patientService.delete(id);
    }
}
