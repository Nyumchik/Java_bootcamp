package school21.spring.service.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import school21.spring.service.repositories.UsersRepositoryJdbcImpl;
import school21.spring.service.repositories.UsersRepositoryJdbcTemplateImpl;
import school21.spring.service.services.UsersService;
import school21.spring.service.services.UsersServiceImpl;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Configuration
@ComponentScan(basePackages = "school21.spring.service")
public class TestApplicationConfig {
	private EmbeddedDatabase dataSource;

	@Bean
    public DataSource dataSource() {
        		dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .build();

        return dataSource;
    }

	@Bean
	public UsersRepositoryJdbcTemplateImpl usersRepositoryJdbcTemplate() {
		return new UsersRepositoryJdbcTemplateImpl(dataSource());
	}

	@Bean
	public UsersRepositoryJdbcImpl usersRepositoryJdbc() {
		return new UsersRepositoryJdbcImpl(dataSource());
	}
}