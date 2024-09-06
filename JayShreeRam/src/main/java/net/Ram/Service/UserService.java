package net.Ram.Service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import net.Ram.Repository.UserRepositry;
import net.Ram.entity.User;

@Service
@Slf4j
public class UserService {
	@Autowired
	private UserRepositry userRepository ;
	private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder() ;
	public boolean saveUser(User user) {
		try {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
	userRepository.save(user) ;
	return true ; 
		}
		catch (Exception e ) {
			log.error("galat h bhai");
			log.debug("dekh le bhai kuch galti toh nhi h ");
			log.trace("Pakka tune kuch galti kri h ");
			log.warn("Dhang me kr l e, kru teri guddi lal" + e);
			return false ; 
		}
	}
	public void savUser1 (User user) {
		userRepository.save(user) ;
	}
	public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public void deleteById(Long  id) {
        userRepository.deleteById(id);
    }

    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
    public void deleteByUserName(String userName) {
        userRepository.deleteByUserName(userName);
    }
}