package net.Ram.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTests {
	@Autowired
	   private RedisTemplate<String, String> redisTemplate;
	@Test
	void sendTestmail() {
		  redisTemplate.opsForValue().set("gmail", "abhay_tomar");
	        redisTemplate.opsForValue().set("salary", "50000"); // Setting the salary

	        String gmail = redisTemplate.opsForValue().get("gmail");
	        String salary =  redisTemplate.opsForValue().get("salary");
	        assertNotNull(gmail);
	        assertEquals("abhay_tomar", gmail);

	        assertNotNull(salary);
	        assertEquals("50000", salary);

	}

}
