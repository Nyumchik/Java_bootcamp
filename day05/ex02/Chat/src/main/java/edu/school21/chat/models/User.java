package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class User {
    private Long id;
    private String login;
    private String password;
    List<Chatroom> createdRooms;
    List<Chatroom> socialRooms;

    public User(Long id, String login, String password, List<Chatroom> createdRooms, List<Chatroom> socialRooms) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.createdRooms = createdRooms;
        this.socialRooms = socialRooms;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Chatroom> getCreatedRooms() {
        return createdRooms;
    }

    public void setCreatedRooms(List<Chatroom> createdRooms) {
        this.createdRooms = createdRooms;
    }

    public List<Chatroom> getSocialRooms() {
        return socialRooms;
    }

    public void setSocialRooms(List<Chatroom> socialRooms) {
        this.socialRooms = socialRooms;
    }

    @Override
    public String toString() {
        return "{"
                + "id=" + id
                + ", login='" + login + '\''
                + ", password='" + password + '\''
                + ", createdRooms=" + createdRooms
                + ", socialRooms=" + socialRooms
                + '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, createdRooms, socialRooms);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        User user = (User) object;
        return id.equals(user.id) && login.equals(user.login) && password.equals(user.password)
            && Objects.equals(createdRooms, user.createdRooms) && Objects.equals(socialRooms, user.socialRooms);
    }
}
