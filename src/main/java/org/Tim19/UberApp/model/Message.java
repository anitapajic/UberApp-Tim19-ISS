package org.Tim19.UberApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    @JoinColumn(name = "sender_id")
    private User senderId;
    @OneToOne
    @JoinColumn(name = "receiver_id")
    private User receiverId;
    @Column(name = "text")
    private String text;
    @Column(name = "time")
    private LocalDateTime time;
    @Column(name = "type")
    private MSGType type;


    @OneToOne(cascade = {CascadeType.MERGE,CascadeType.DETACH})
    @JoinColumn(name = "ride_id")
    private Ride rideId;

    public Message(Integer id, User sender, User receiver, String text, LocalDateTime  time, MSGType type, Ride ride) {
        this.id = id;
        this.senderId = sender;
        this.receiverId = receiver;
        this.text = text;
        this.time = time;
        this.type = type;
        this.rideId = ride;
    }
}


