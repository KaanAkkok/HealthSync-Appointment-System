package com.healthcare.email.controller;

import com.healthcare.email.dto.AppointmentDetailsDTO;
import com.healthcare.email.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;

    @PostMapping("/send")
    public void sendAppointmentConfirmation(@RequestBody AppointmentDetailsDTO appointmentDetails) {
        emailService.sendAppointmentConfirmation(appointmentDetails);
    }
} 