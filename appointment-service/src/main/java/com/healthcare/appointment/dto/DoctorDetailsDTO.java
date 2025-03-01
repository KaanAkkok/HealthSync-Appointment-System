package com.healthcare.appointment.dto;

import lombok.Data;

@Data
public class DoctorDetailsDTO {
    private String doctorName;
    private String doctorEmail;
    private String doctorAvailableTime;
    private String doctorAvailableDate;
} 
