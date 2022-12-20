package org.Tim19.UberApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.Tim19.UberApp.dto.NoteDTO;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Note")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "text")
    private String text;
    @OneToOne(cascade = {CascadeType.MERGE,CascadeType.DETACH})
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime date;

    public Note(Integer id, String text, User user, LocalDateTime date) {
        this.id = id;
        this.text = text;
        this.user = user;
        this.date = date;
    }

}
