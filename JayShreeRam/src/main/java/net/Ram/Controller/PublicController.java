package net.Ram.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import net.Ram.entity.User;
import net.Ram.utils.JwtUtil;
import net.Ram.Repository.UserRepositry;
import net.Ram.Response.WeatherResponse;
import net.Ram.Service.UserService;
import net.Ram.Service.UserServiceImpl;
import net.Ram.Service.WeatherService;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserServiceImpl userDetailsService;
	@Autowired
	private UserService userService ; 
	@Autowired
	private WeatherService weatherService ; 
	@Autowired
	private UserRepositry userRepository ; 
	 @Autowired
	    private JwtUtil jwtUtil;
	@GetMapping("/hey")
	public String lol() {
		return " jay shree ram " ; 
	}
	@PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user   ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String username = authentication.getName();
    	User userInDb = userService.findByUserName(username);
        userInDb.setUserName(user.getUserName());
        userInDb.setPassword(user.getPassword());
        userService.saveUser(userInDb);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping
    public ResponseEntity<?> deleteUser(@RequestBody User user   ) {
    	 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	 userService.deleteByUserName(authentication.getName());
    	 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    	
    }
//    @PostMapping("/getall")
//    public void 
    @PostMapping("/signup")
    public void signup(@RequestBody User user) {
        userService.savUser1(user);
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUserName());
            String jwt = jwtUtil.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwt, HttpStatus.OK);
        }catch (Exception e){
//            log.error("Exception occurred while createAuthenticationToken ", e);
            return new ResponseEntity<>("Incorrect username or password", HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/all-users")
	private ResponseEntity<?> getAllusers(){
		List<User> all = userService.getAll();
		if(all!=null && !all.isEmpty()) {
			return new ResponseEntity<>(all , HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
    @GetMapping("/api")
    public ResponseEntity<?> externalAPi(){
    	 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	 WeatherResponse weatherResponse = weatherService.getWeather("meerut") ; 
    	 return new ResponseEntity<>("Hi" + authentication.getName() + "Weather feels like " + weatherResponse.getWeather(), HttpStatus.ACCEPTED);
    	
    }
    
}
