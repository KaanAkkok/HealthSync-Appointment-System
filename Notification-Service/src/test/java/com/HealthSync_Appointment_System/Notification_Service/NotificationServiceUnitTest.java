package com.HealthSync_Appointment_System.Notification_Service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class NotificationServiceUnitTest {

	@Mock
	private JavaMailSender emailSender;
	
	@InjectMocks
	private NotificationService notificationService;
	
	private AppointmentDetailsDTO appointmentDetailsDTO;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		
		appointmentDetailsDTO = new AppointmentDetailsDTO();
		appointmentDetailsDTO.setPatientName("Mehmet hastaTest");
		appointmentDetailsDTO.setPatientEmail("hastaTest@gmail.com");
		appointmentDetailsDTO.setDoctorName("Ali doktorTest");
		appointmentDetailsDTO.setDoctorEmail("doktorTest@gmail.com");
		appointmentDetailsDTO.setAppointmentDate("30-12-2024");
        appointmentDetailsDTO.setAppointmentTime("14:30");
	}
	
	@Test
	void whenSendEmail_thenSendsBothEmails() {
		notificationService.sendEmail(appointmentDetailsDTO);
		verify(emailSender, times(2)).send(any(SimpleMailMessage.class));
	}
	
	@Test
	void whenSendEmail_thenPatientEmailContentIsCorrect() {
		notificationService.sendEmail(appointmentDetailsDTO);
		
		verify(emailSender).send(argThat((SimpleMailMessage message) -> {
			
			if(message.getTo()[0].equals(appointmentDetailsDTO.getPatientEmail())) {
				String content = message.getText();
				return content.contains(appointmentDetailsDTO.getPatientName()) &&
	                   content.contains(appointmentDetailsDTO.getDoctorName()) &&
	                   content.contains(appointmentDetailsDTO.getAppointmentDate()) &&
	                   content.contains(appointmentDetailsDTO.getAppointmentTime());
			}
			return true;
			
		}));
	}
	
	@Test
	void whenSendEmail_thenDoctorEmailContentIsCorrect() {
		notificationService.sendEmail(appointmentDetailsDTO);
		
		verify(emailSender).send(argThat((SimpleMailMessage message) -> {
			
			if(message.getTo()[0].equals(appointmentDetailsDTO.getDoctorEmail())) {
				String content = message.getText();
				return content.contains(appointmentDetailsDTO.getPatientName()) &&
	                   content.contains(appointmentDetailsDTO.getDoctorName()) &&
	                   content.contains(appointmentDetailsDTO.getAppointmentDate()) &&
	                   content.contains(appointmentDetailsDTO.getAppointmentTime());
			}
			return true;
			
		}));
	}
}
