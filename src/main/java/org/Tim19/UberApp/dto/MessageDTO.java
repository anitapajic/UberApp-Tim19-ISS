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
    private Integer sender;
    private Integer receiver;
    private String text;
    private LocalDateTime time;
    private MSGType type;
    private Integer ride;

    public MessageDTO(Integer id, Integer sender, Integer receiver, String text, LocalDateTime time, MSGType type, Integer ride) {
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
        this(message.getId(), message.getSender().getId(), message.getReceiver().getId(), message.getText(), message.getTime(), message.getType(), message.getRide().getId());
    }

}
