package edu.school21.chat.app;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.UsersRepository;
import edu.school21.chat.repositories.UsersRepositoryJdbcImpl;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.Statement;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class Program {
    public static void main(String[] args) throws SQLException {
        try {
            HikariDataSource ds = new HikariDataSource();
            ds.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
            ds.setUsername("postgres");
            ds.setPassword("1111");
            Connection connection;

            try {
                connection = ds.getConnection();
            }
            catch (SQLException e) {
                System.err.println("Error connection");
                return;
            }

            List<String> schema;
            List<String> data;

            try {
                schema = Files.readAllLines(Paths.get("src/main/resources/schema.sql"));
                data = Files.readAllLines(Paths.get("src/main/resources/data.sql"));
            }
            catch (IOException e) {
                return;
            }

            for (String s : schema) {
                try {
                    connection.createStatement().execute(s);
                }
                catch (SQLException e) {
                    System.err.println("Error schema");
                    return;
                }
            }

            for (String d : data) {
                try {
                    connection.createStatement().execute(d);
                }
                catch (SQLException e) {
                    System.err.println("Error data");
                    return;
                }
            }
            
            UsersRepository usersRepository = new UsersRepositoryJdbcImpl(connection);

		    List<User> list = usersRepository.findAll(0, 7);
		    for (User user:	list) {
                System.out.println(user);
                System.out.println();
            }
			    
            connection.close();
            ds.close();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

