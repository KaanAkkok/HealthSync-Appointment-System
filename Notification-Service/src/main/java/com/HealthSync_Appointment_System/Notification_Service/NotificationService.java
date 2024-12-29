package com.HealthSync_Appointment_System.Notification_Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendEmail(AppointmentDetailsDTO dto) {
        SimpleMailMessage patientMessage = new SimpleMailMessage();
        patientMessage.setTo(dto.getPatientEmail());
        patientMessage.setSubject("Appointment Notification");
        patientMessage.setText(createPatientEmailContent(dto));
        emailSender.send(patientMessage);

        SimpleMailMessage doctorMessage = new SimpleMailMessage();
        doctorMessage.setTo(dto.getDoctorEmail());
        doctorMessage.setSubject("Appointment Notification");
        doctorMessage.setText(createDoctorEmailContent(dto));
        emailSender.send(doctorMessage);
    }

    private String createDoctorEmailContent(AppointmentDetailsDTO dto) {
        return String.format("""
                Dear %s,

                Your appointment has been confirmed with Dr. %s.

                Appointment Details:
                Date: %s
                Time: %s

                Please arrive 15 minutes before your scheduled appointment time.

                Best regards,
                HealthSync Appointment System
                """,
                dto.getPatientName(),
                dto.getDoctorName(),
                dto.getAppointmentDate(),
                dto.getAppointmentTime()
        );
    }

    private String createPatientEmailContent(AppointmentDetailsDTO dto) {
        return String.format("""
                Dear Dr. %s,

                A new appointment has been scheduled.

                Appointment Details:
                Patient: %s
                Date: %s
                Time: %s

                Best regards,
                HealthSync Appointment System
                """,
                dto.getDoctorName(),
                dto.getPatientName(),
                dto.getAppointmentDate(),
                dto.getAppointmentTime()
        );
    }
}
