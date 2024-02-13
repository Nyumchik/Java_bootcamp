package edu.school21.sockets.models;

import java.time.LocalDateTime;

public class Message {
    private Long id;
    private String username;
    private String message;
    private LocalDateTime time;

    public Message(String message, LocalDateTime time) {
        this.message = message;
        this.time = time;
    }

    public Message(String username, String message) {
        this.username = username;
        this.message = message;
    }

    public Message(String message) {
        this.message = message;
    }

    public Message() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                "message='" + message + '\'' +
                ", time=" + time +
                '}';
    }
}
