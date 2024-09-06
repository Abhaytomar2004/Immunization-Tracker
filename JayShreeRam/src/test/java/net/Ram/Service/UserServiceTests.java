package net.Ram.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import net.Ram.Repository.UserRepositry;
import net.Ram.entity.User;
@SpringBootTest
public class UserServiceTests {
	@Autowired 
	private UserRepositry userRepositry ; 
	@Autowired 
	private UserService userService ; 
	@Test
	public void testAdd() {
		assertEquals(4 , 2+2);
	}
//	@Disabled
	@ParameterizedTest
	@ArgumentsSource(UserArgumentsProvider.class)
	public void testSaveUser(User user) {
		assertEquals(4 , 2+2);
		assertTrue(userService.saveUser(user));
	}
	@ParameterizedTest
	@CsvSource({
		"1 , 1 , 2 ",
		"2 , 10  , 12 " ,
		"3 ,3 ,9" 
	})
	public void test(int a , int b , int expected) {
		assertEquals(expected , a+b) ;
	}
	
	
}
