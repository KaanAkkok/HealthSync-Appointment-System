package com.healthcare.appointment.dto;

import lombok.Data;

@Data
public class PatientDetailsDTO {
    private String patientName;
    private String patientEmail;
    private String patientAvailableTime;
    private String patientAvailableDate;
} 
