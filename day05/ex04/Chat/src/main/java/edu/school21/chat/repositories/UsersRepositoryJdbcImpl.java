package edu.school21.chat.repositories;

import edu.school21.chat.models.User;
import edu.school21.chat.models.Chatroom;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UsersRepositoryJdbcImpl implements UsersRepository
{
	private Connection connection;
	private DataSource dataSource;

	public UsersRepositoryJdbcImpl(DataSource dataSource)
	{
		this.dataSource = dataSource;
		this.connection = null;
	}

	public UsersRepositoryJdbcImpl(Connection connection)
	{
		this.dataSource = null;
		this.connection = connection;
	}

	@Override
	public List<User> findAll(int page, int size) {
		ResultSet rs = null;
		List<User> list = null;

		try {
			PreparedStatement ps = connection.prepareStatement("SELECT u.*, c.id, c.name, um.room, ct.name, us.id, us.login, us.password\n" +
            														"FROM (SELECT * FROM chat.users ORDER BY id OFFSET ? FETCH NEXT ? ROWS ONLY) u\n" +
            														"LEFT JOIN chat.chatrooms c ON u.id = c.owner\n" +
            														"LEFT JOIN chat.messages um ON u.id = um.author\n" +
            														"LEFT JOIN chat.chatrooms ct ON um.room = ct.id\n" +
            														"LEFT JOIN chat.users us ON ct.owner = us.id\n" +
            														"ORDER BY u.id, c.id, um.room;");
			ps.setLong(1, (long) page * size);
			ps.setLong(2, size);
			rs = ps.executeQuery();
			list = new ArrayList<>();

			while (rs.next()) {
                Chatroom chat;

				User user = new User(rs.getLong(1), rs.getString(2), rs.getString(3),
                            new ArrayList<>(), new ArrayList<>());
                list.add(user);

                Long createdChatId = rs.getLong(4);
                if (createdChatId != 0) {
                    chat = new Chatroom(createdChatId, rs.getString(5),
                            new User(user.getId(), user.getLogin(), null, null, null), null);
                    user.getCreatedRooms().add(chat);
                }

                Long socialChatId = rs.getLong(6);
                if (socialChatId != 0) {
                    chat = new Chatroom(socialChatId, rs.getString(7),
                            new User(rs.getLong(8), rs.getString(9), null, null, null), null);
                    user.getSocialRooms().add(chat);
                }
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
