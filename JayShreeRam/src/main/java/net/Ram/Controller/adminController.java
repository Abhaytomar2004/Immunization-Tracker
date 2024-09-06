package net.Ram.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.Ram.Service.UserService;
import net.Ram.entity.User;

@RestController
@RequestMapping("/admin")
public class adminController {
	@Autowired
	private UserService userService ; 
	@GetMapping("/all-users")
	private ResponseEntity<?> getAllusers(){
		List<User> all = userService.getAll();
		if(all!=null && !all.isEmpty()) {
			return new ResponseEntity<>(all , HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
}
