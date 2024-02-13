package school21.spring.service.repositories;

import school21.spring.service.models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public class UsersRepositoryJdbcTemplateImpl implements UsersRepository{

    private final JdbcTemplate jdbcTemplate;
    
    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource) {
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
        jdbcTemplate.update("INSERT INTO users (id, email) VALUES (?, ?)", entity.getId(), entity.getEmail());
    }

    @Override
    public void update(User entity) {
        jdbcTemplate.update("UPDATE users SET email=? WHERE id=?", entity.getEmail(), entity.getId());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM users WHERE id=?", id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        List<User> list = jdbcTemplate.query("SELECT * FROM users WHERE email = ?", new BeanPropertyRowMapper<>(User.class), email);
        if (list.isEmpty()) {
            return Optional.empty();
        }
        else {
            return Optional.of(list.get(0));
        }
    }
}