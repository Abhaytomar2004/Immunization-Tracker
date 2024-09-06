package net.Ram.Service;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.springframework.security.core.userdetails.User;



public class UserArgumentsProvider  implements ArgumentsProvider{

	@Override
	public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
		return Stream.of(
			Arguments.of(User.builder().username("anushka").password("password123").build()),
			Arguments.of(User.builder().username("uditi singh").password("").build())
					);
	}
	
	
}
