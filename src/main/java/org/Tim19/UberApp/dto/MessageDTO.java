package org.Tim19.UberApp.dto;

import lombok.Data;
import org.Tim19.UberApp.model.MSGType;
import org.Tim19.UberApp.model.Message;

import java.time.LocalDateTime;

public class MessageDTO {

    private Integer id;
    private Integer senderId;
    private Integer receiverId;
    private String text;
    private LocalDateTime timeOfSending;
    private MSGType type;
    private Integer rideId;

    public MessageDTO(Integer id, Integer sender, Integer receiver, String text, LocalDateTime time, MSGType type, Integer ride) {
        this.id = id;
        this.senderId = sender;
        this.receiverId = receiver;
        this.text = text;
        this.timeOfSending = time;
        this.type = type;
        this.rideId = ride;
    }

    public MessageDTO(){}

    public MessageDTO(Message message){
        this(message.getId(), message.getSenderId().getId(), message.getReceiverId().getId(), message.getText(), message.getTime(), message.getType(), message.getRideId().getId());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getTimeOfSending() {
        return timeOfSending;
    }

    public void setTimeOfSending(LocalDateTime timeOfSending) {
        this.timeOfSending = timeOfSending;
    }

    public MSGType getType() {
        return type;
    }

    public void setType(MSGType type) {
        this.type = type;
    }

    public Integer getRideId() {
        return rideId;
    }

    public void setRideId(Integer rideId) {
        this.rideId = rideId;
    }
}
