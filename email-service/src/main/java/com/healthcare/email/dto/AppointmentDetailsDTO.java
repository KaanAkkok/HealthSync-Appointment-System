package com.healthcare.email.dto;

import lombok.Data;

@Data
public class AppointmentDetailsDTO {
    private String patientName;
    private String patientEmail;
    private String doctorName;
    private String doctorEmail;
    private String appointmentDate;
    private String appointmentTime;
} 