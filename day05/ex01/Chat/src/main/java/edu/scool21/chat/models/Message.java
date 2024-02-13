package edu.school21.chat.models;

import java.time.LocalDateTime;
import java.sql.Timestamp;
import java.util.Objects;

public class Message {
    private Long id;
    private User author;
    private Chatroom room;
    private String text;
    private Timestamp time;

    public Message(Long id, User author, Chatroom room, String text, Timestamp time) {
        this.id = id;
        this.author = author;
        this.room = room;
        this.text = text;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Chatroom getRoom() {
        return room;
    }

    public void setRoom(Chatroom room) {
        this.room = room;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Message : {\n"
                + "id=" + id
                + ",\nauthor=" + author
                + ",\nroom=" + room
                + ",\ntext='" + text + '\''
                + ",\ntime=" + time
                + "\n}";
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, room, text, time);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Message message = (Message) object;
        return id.equals(message.id) && author.equals(message.author) && room.equals(message.room)
            && text.equals(message.text) && time.equals(message.time);
    }

}
