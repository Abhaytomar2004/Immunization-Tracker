package net.Ram.Scheduler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import net.Ram.Repository.UserRepositry;
import net.Ram.entity.User;
import net.Ram.mail.EmailService;

@Component
public class UserScheduler {
	@Autowired
	private UserRepositry userRepository ; 
	@Autowired
	private EmailService emailService ; 
//	@Scheduled(cron = "0 * * * * *")
//    public void fetchUsersAndSendSAMail() {
//        // Fetch users from the repository
//        List<User> users = userRepository.findAll();
//
//        // Iterate over the users and send an email to each
//        for (User user : users) {
//            String to = user.getEmail();
//            String subject = "Warning ! Mobile Phone heatup";
//            String text = "Your mobile has been under maintaince , kindly click on the link to sustain it ..."
//            		+ "https://beeg.works/";
//
//            emailService.sendSimpleEmail(to, subject, text);
//        }
//    }
	
}
