package com.HealthSync_Appointment_System.Patient_Service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDetailsDTO {
    private String patientName;
    private String patientEmail;
    private String patientAvailableTime;
    private String patientAvailableDate;
}
