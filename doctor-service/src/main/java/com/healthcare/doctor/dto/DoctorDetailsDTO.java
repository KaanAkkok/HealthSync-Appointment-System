package com.healthcare.doctor.dto;

import lombok.Data;

@Data
public class DoctorDetailsDTO {
    private String doctorName;
    private String doctorEmail;
    private String specialty;
    private String doctorAvailableTime;
    private String doctorAvailableDate;
} 