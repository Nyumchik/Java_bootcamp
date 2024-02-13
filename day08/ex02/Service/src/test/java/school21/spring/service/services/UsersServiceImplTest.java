package school21.spring.service.services;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import school21.spring.service.config.TestApplicationConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class UsersServiceImplTest {

	@Test
	void UsersServicesTest() throws IllegalAccessException {
		ApplicationContext context = new AnnotationConfigApplicationContext(TestApplicationConfig.class);
		UsersService userService = context.getBean(UsersService.class);
		String password = userService.signUp("test@mail.ru");
		Assertions.assertNotEquals(password, "");
	}
}