package edu.school21.sockets.repositories;

import edu.school21.sockets.models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

@Component
public class MessagesRepositoryImpl implements MessagesRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public MessagesRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Message findById(Long id) {
        return jdbcTemplate.query("SELECT * FROM messages WHERE id = " + id, new BeanPropertyRowMapper<>(Message.class)).stream().findAny().orElse(null);
    }

    @Override
    public List<Message> findAll() {
        return jdbcTemplate.query("SELECT * FROM messages", new BeanPropertyRowMapper<>(Message.class));
    }

    @Override
    public void save(Message entity) {
        jdbcTemplate.update("INSERT INTO messages (username, message) VALUES (?, ?)", entity.getUsername(), entity.getMessage());
    }

    @Override
    public void update(Message entity) {

    }

    @Override
    public void delete(Long id) {

    }
}
