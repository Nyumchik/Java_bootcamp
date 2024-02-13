package edu.school21.sockets.repositories;

import edu.school21.sockets.models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class UsersRepositoryImpl implements UsersRepository{
    
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public UsersRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User findById(Long id) {
        return jdbcTemplate.query("SELECT * FROM users WHERE id = " + id, new BeanPropertyRowMapper<>(User.class)).stream().findAny().orElse(null);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM users;", new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public void save(User entity) {
        jdbcTemplate.update("INSERT INTO users (username, password) VALUES (?, ?)", entity.getName(), entity.getPassword());
    }

    @Override
    public void update(User entity) {
        jdbcTemplate.update("UPDATE users SET username=? WHERE id=?", entity.getName(), entity.getId());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM users WHERE id=?", id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        List<User> list = jdbcTemplate.query("SELECT * FROM users WHERE username = ?", new BeanPropertyRowMapper<>(User.class), username);
        if (list.isEmpty()) {
            return Optional.empty();
        }
        else {
            return Optional.of(list.get(0));
        }
    }
}
