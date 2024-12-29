package com.HealthSync_Appointment_System.Notification_Service;

import com.HealthSync_Appointment_System.Notification_Service.NotificationService; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*; 

@RestController 
@RequestMapping("/notifications") 
public class NotificationController { 
	
	@Autowired 
	private NotificationService notificationService; 
	
	@PostMapping("/send-email") 
	public ResponseEntity<Void> sendEmail(@RequestBody AppointmentDetailsDTO dto) { 
		notificationService.sendEmail(dto); 
		return ResponseEntity.ok().build();
	} 
}
