package school21.spring.service.application;

import school21.spring.service.repositories.UsersRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import school21.spring.service.models.User;

public class Main {
    public static void main(String[] args) {
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
            UsersRepository usersRepository = context.getBean("usersRepositoryJdbc", UsersRepository.class);
            System.out.println(usersRepository.findAll ());

            System.out.println();

            usersRepository = context.getBean("usersRepositoryJdbcTemplate", UsersRepository.class);
            System.out.println(usersRepository.findAll());
        }
        catch (IllegalThreadStateException e) {
            System.err.println("STOP");
        }
        
    }
}
