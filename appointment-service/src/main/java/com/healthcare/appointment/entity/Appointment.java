package com.healthcare.appointment.entity;

import lombok.Data;

import jakarta.persistence.*;

@Entity
@Table(name = "appointments")
@Data
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;
    private String patientName;
    private String patientEmail;
    private String doctorName;
    private String doctorEmail;
    private String appointmentDate;
    private String appointmentTime;
} 