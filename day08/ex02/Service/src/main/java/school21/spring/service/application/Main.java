package school21.spring.service.application;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import school21.spring.service.config.ApplicationConfig;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;
import school21.spring.service.repositories.UsersRepositoryJdbcTemplateImpl;
import school21.spring.service.services.UsersService;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        UsersService usersService = context.getBean("usersServiceImpl", UsersService.class);
        UsersRepository usersTemplate = context.getBean("usersRepositoryJdbcTemplateImpl", UsersRepository.class);
		UsersRepository users = context.getBean("usersRepositoryJdbcImpl", UsersRepository.class);
        
        System.out.println("----------BEFOR SIGNUP-------------");
		System.out.println(usersTemplate.findAll());
        System.out.println();
		System.out.println(users.findAll());
        System.out.println();

		System.out.println(usersService.signUp("vdfsav"));
        System.out.println(usersService.signUp("vdav"));
        System.out.println(usersService.signUp("vadrv"));
        System.out.println(usersService.signUp("vasdr"));
        System.out.println();

        System.out.println("----------AFTER SIGNUP-------------");
		System.out.println(usersTemplate.findAll());
        System.out.println();
		System.out.println(users.findAll());  
    }
}
