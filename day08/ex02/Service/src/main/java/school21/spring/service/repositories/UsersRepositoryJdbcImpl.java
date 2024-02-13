package school21.spring.service.repositories;

import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("usersRepositoryJdbcImpl")
public class UsersRepositoryJdbcImpl implements UsersRepository{
	
    DataSource dataSource;
	@Autowired
    public UsersRepositoryJdbcImpl(@Qualifier("standardDataSource") DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public User findById(Long id) {
        try (Connection connection = dataSource.getConnection()){
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE id = ?;");
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return new User(rs.getLong(1), rs.getString(2), rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
    }

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM users;");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				list.add(new User(rs.getLong(1), rs.getString(2), rs.getString(3)));
			}

			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
    }

    @Override
    public void save(User entity) {
        try (Connection connection = dataSource.getConnection()){
			PreparedStatement ps = connection.prepareStatement("INSERT INTO users (email, password) VALUES (?, ?);");
			//ps.setLong(1, entity.getId());
			ps.setString(1, entity.getEmail());
			ps.setString(2, entity.getPassword());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    @Override
    public void update(User entity) {
        try (Connection connection = dataSource.getConnection()){
			PreparedStatement ps = connection.prepareStatement("UPDATE users SET email = ? WHERE id = ?;");
			ps.setString(1, entity.getEmail());
			ps.setLong(2, entity.getId());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = dataSource.getConnection()){
			PreparedStatement ps = connection.prepareStatement("DELETE FROM users WHERE id = ?;");
			ps.setLong(1, id);
			ps.execute();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (Connection connection = dataSource.getConnection()){
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE email = ?;");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return Optional.of(new User(rs.getLong(1), rs.getString(2), rs.getString(3)));
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		return Optional.empty();
	}
}
