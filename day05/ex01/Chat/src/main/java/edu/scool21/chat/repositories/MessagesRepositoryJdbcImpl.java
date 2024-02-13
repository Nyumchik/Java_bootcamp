package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {
    private final Connection connection;

    public MessagesRepositoryJdbcImpl(Connection connection) {
        this.connection = connection;
    }
    @Override
    public Optional<Message> findById(Long id) throws SQLException {
        Message ret = null;
        ResultSet rs = null;

        try {
            PreparedStatement ps = connection.prepareStatement("SElECT * FROM chat.messages WHERE id =?");
            ps.setLong(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Long i = rs.getLong("author");
                Long j = rs.getLong("room");
                PreparedStatement ps2 = connection.prepareStatement("SElECT * FROM chat.users WHERE id = " + i);
                ResultSet rs2 = ps2.executeQuery();
                rs2.next();
                PreparedStatement ps3 = connection.prepareStatement("SElECT * FROM chat.chatrooms WHERE id = " + j);
                ResultSet rs3 = ps3.executeQuery();
                rs3.next();
                ret = new Message(rs.getLong("id"),
                new User(rs2.getLong("id"), rs2.getString("login"), rs2.getString("password"), null, null),
                new Chatroom(rs3.getLong("id"), rs3.getString("name"), null, null),
                rs.getString("text"),
                rs.getTimestamp("timestamp"));
            }
            ps.close();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        Optional<Message> mes = Optional.ofNullable(ret);
        return mes;
    }
}
