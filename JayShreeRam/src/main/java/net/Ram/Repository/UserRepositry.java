package net.Ram.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.Ram.entity.User;

public interface UserRepositry extends JpaRepository<User, Long> {
	 User findByUserName(String username);

	    void deleteByUserName(String username);
}