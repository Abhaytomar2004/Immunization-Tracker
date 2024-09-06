package net.Ram.Service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import net.Ram.mail.EmailService;

@SpringBootTest
public class EmailServiceTests {
	@Autowired
	private EmailService emailService; 
	@Test
	void testSendMail() {
		emailService.sendSimpleEmail("abhayalpha21@gmail.com", "Regading Share Market", "Welcome to tomar Industries pvt.ltd");
	}

}
