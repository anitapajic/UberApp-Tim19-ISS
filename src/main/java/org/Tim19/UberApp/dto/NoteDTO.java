package org.Tim19.UberApp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.Tim19.UberApp.model.Note;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class NoteDTO {
    private Integer id;
    //private LocalDateTime date;
    private String message;
    private Integer userId;

    private LocalDateTime date;

    public NoteDTO(Integer id, Integer userId, String message, LocalDateTime date){
        this.id = id;
        this.userId = userId;
        this.message = message;
        this.date = date;
    }

    public  NoteDTO(Note note){
        this.id = note.getId();
        this.userId = note.getUser().getId();
        this.message = note.getText();
        this.date = note.getDate();
    }

}
