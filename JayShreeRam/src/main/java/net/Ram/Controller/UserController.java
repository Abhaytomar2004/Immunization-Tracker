package net.Ram.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import net.Ram.Response.WeatherResponse;
import net.Ram.Scheduler.UserScheduler;
import net.Ram.Service.UserService;
import net.Ram.Service.WeatherService;
import net.Ram.entity.User;
import net.Ram.mail.EmailService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService ; 
	@Autowired
    private EmailService emailService;
	@Autowired
	private WeatherService weatherService ; 
	@Autowired
	private UserScheduler userScheduler; 
	@PostMapping
	private  boolean CreateUser(@RequestBody User user ) {
		userService.saveUser(user);
		return true ; 
	}
	@PostMapping("/signup")
    public void signup(@RequestBody User user) {
        userService.savUser1(user);
    }
	@PostMapping("/get/{username}")
	private ResponseEntity<User> getByid(@PathVariable ("username") String username) {
		User user1 = userService.findByUserName(username) ;
		if(user1!=null) {
			return ResponseEntity.status(HttpStatus.OK).body(user1) ; 
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(user1) ;
	}
	@GetMapping
	public ResponseEntity<?> gretting(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return new ResponseEntity<>("Hi" + " " + authentication.getName(), HttpStatus.OK);
	}
	  @GetMapping("/sendEmail")
	    public String sendEmail(@RequestParam String to , @RequestParam String subject , @RequestParam String text){
//	      userScheduler.fetchUsersAndSendSAMail(to, subject, text) ;
		  emailService.sendSimpleEmail(to, subject, text);
	        return "Email sent successfully";
	    }
//	  @GetMapping("/sendEmails")
//	    public String sendEmailsManually() {
//	        userScheduler.fetchUsersAndSendSAMail();
//	        return "Emails sent successfully!";
//	    }
	  @GetMapping("/hello")
	  public String hello()
	  {
		  return "hello" ; 
	  }
	  @GetMapping("/external")
	    private String externalApi(){
	    	String uri = "https://www.swiggy.com/dapi/restaurants/list/v5?lat=21.99740&lng=79.00110&is-seo-homepage-enabled=true&page_type=DESKTOP_WEB_LISTING";
	    	RestTemplate restTemplate = new  RestTemplate() ;
	    	String result = restTemplate.getForObject(uri,String.class);
	    	return result ; 
	    	
	    }
	  @GetMapping("/api")
	    public ResponseEntity<?> externalAPi(){
//	    	 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    	 WeatherResponse weatherResponse = weatherService.getWeather("Mumbai") ; 
	    	 return new ResponseEntity<>("Hi" + " " + "Weather feels like " + weatherResponse.getWeather().getMain()     , HttpStatus.ACCEPTED);
	    }
}
