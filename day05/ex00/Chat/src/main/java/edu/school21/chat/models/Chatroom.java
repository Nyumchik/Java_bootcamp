package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class Chatroom {
    private Long id;
    private String name;
    private String owner;
    private List<Message> listOfChatMessage;

    public Chatroom(Long id, String name, String owner, List<Message> listOfChatMessage) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.listOfChatMessage = listOfChatMessage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<Message> getListOfChatMessage() {
        return listOfChatMessage;
    }

    public void setListOfChatMessage(List<Message> listOfChatMessage) {
        this.listOfChatMessage = listOfChatMessage;
    }

    @Override
    public String toString() {
        return "Chatroom{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", owner='" + owner + '\''
                + ", listOfChatMessage=" + listOfChatMessage
                + '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, owner, listOfChatMessage);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chatroom chatroom = (Chatroom) o;
        return id.equals(chatroom.id) && name.equals(chatroom.name) && owner.equals(chatroom.owner)
        && Objects.equals(listOfChatMessage, chatroom.listOfChatMessage);
    }
}
