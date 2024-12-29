package com.HealthSync_Appointment_System.Notification_Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class NotificationControllerUnitTest {

	@Mock
	private NotificationService notificationService;
	
	@InjectMocks
	private NotificationController notificationController;
	
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
	void whenValidInput_thenReturns200() {
		ResponseEntity<Void> response = notificationController.sendEmail(appointmentDetailsDTO);
		
		verify(notificationService, times(1)).sendEmail(appointmentDetailsDTO);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	void whenServiceThrowsException_thenThrowsException() {
		doThrow(new RuntimeException("Failed to send email")).when(notificationService).sendEmail(any(AppointmentDetailsDTO.class));
		assertThrows(RuntimeException.class, () -> {notificationController.sendEmail(appointmentDetailsDTO);});
	}
	
}
