package org.Tim19.UberApp.dto;

import lombok.Data;
import org.Tim19.UberApp.model.MSGType;
import org.Tim19.UberApp.model.Message;
import org.Tim19.UberApp.model.Ride;
import org.Tim19.UberApp.model.User;

import java.time.LocalDateTime;

@Data
public class MessageDTO {

    private Integer id;
    private Integer senderId;
    private Integer receiverId;
    private String message;
    private LocalDateTime timeOfSending;
    private MSGType type;
    private Integer rideId;

    public MessageDTO(Integer id, Integer sender, Integer receiver, String text, LocalDateTime time, MSGType type, Integer ride) {
        this.id = id;
        this.senderId = sender;
        this.receiverId = receiver;
        this.message = text;
        this.timeOfSending = time;
        this.type = type;
        this.rideId = ride;
    }

    public MessageDTO(){}

    public MessageDTO(Message message){
        this(message.getId(), message.getSender().getId(), message.getReceiver().getId(), message.getText(), message.getTime(), message.getType(), message.getRide().getId());
    }

}
