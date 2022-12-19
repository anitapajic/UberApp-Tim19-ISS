package org.Tim19.UberApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.Tim19.UberApp.dto.NoteDTO;

import javax.persistence.*;

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

    public Note(Integer id, String text, User user) {
        this.id = id;
        this.text = text;
        this.user = user;
    }

}
