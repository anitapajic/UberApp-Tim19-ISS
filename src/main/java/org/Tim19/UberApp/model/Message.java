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
    private User sender;
    @OneToOne
    @JoinColumn(name = "receiver_id")
    private User receiver;
    @Column(name = "text")
    private String text;
    @Column(name = "time")
    private LocalDateTime time;
    @Column(name = "type")
    private MSGType type;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ride_id")
    private Ride ride;

    public Message(Integer id, User sender, User receiver, String text, LocalDateTime  time, MSGType type, Ride ride) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.text = text;
        this.time = time;
        this.type = type;
        this.ride = ride;
    }
}


