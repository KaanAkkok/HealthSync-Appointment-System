package com.HealthSync_Appointment_System.Appointment_Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class EmailServiceClient {

    @Value("${notification.service.url}")
    private String notificationServiceUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public void notifyAppointment(Appointment appointment) {
        AppointmentDetailsDTO dto = new AppointmentDetailsDTO();
        dto.setPatientName(appointment.getPatientName());
        dto.setPatientEmail(appointment.getPatientEmail());
        dto.setDoctorName(appointment.getDoctorName());
        dto.setDoctorEmail(appointment.getDoctorEmail());
        dto.setAppointmentDate(appointment.getAppointmentDate());
        dto.setAppointmentTime(appointment.getAppointmentTime());

        restTemplate.postForEntity(notificationServiceUrl + "/notifications/send-email", dto, Void.class);
    }
}
