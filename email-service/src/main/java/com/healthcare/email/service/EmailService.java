package com.healthcare.email.service;

import com.healthcare.email.dto.AppointmentDetailsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    public void sendAppointmentConfirmation(AppointmentDetailsDTO appointment) {
        System.out.println("=== Email Notification ===");
        System.out.println("Email sent to patient: " + appointment.getPatientEmail());
        System.out.println("Subject: Appointment Confirmation");
        System.out.println("Dear " + appointment.getPatientName() + ",");
        System.out.println("Your appointment with " + appointment.getDoctorName() + 
                         " is confirmed for " + appointment.getAppointmentDate() + 
                         " at " + appointment.getAppointmentTime());
        System.out.println("======================");
    }
} 