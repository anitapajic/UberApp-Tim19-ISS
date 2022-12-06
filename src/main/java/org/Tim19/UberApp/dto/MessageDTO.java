package org.Tim19.UberApp.dto;

import org.Tim19.UberApp.model.MSGType;
import org.Tim19.UberApp.model.Message;
import org.Tim19.UberApp.model.Ride;
import org.Tim19.UberApp.model.Users;

import java.time.LocalDateTime;

public class MessageDTO {

    private Integer id;
    private Users sender;
    private Users receiver;
    private String text;
    private LocalDateTime time;
    private MSGType type;
    private Ride ride;

    public MessageDTO(Integer id, Users sender, Users receiver, String text, LocalDateTime time, MSGType type, Ride ride) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.text = text;
        this.time = time;
        this.type = type;
        this.ride = ride;
    }

    public MessageDTO(){}

    public MessageDTO(Message message){
        this(message.getId(), message.getSender(), message.getReceiver(), message.getText(), message.getTime(), message.getType(), message.getRide());
    }

    public Integer getId() {
        return id;
    }

    public Users getSender() {
        return sender;
    }

    public Users getReceiver() {
        return receiver;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public MSGType getType() {
        return type;
    }

    public Ride getRide() {
        return ride;
    }
}
