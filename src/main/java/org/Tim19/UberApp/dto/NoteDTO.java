package org.Tim19.UberApp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class NoteDTO {
    private Integer id;
    private LocalDateTime date;
    private String message;

    public NoteDTO(Integer id, LocalDateTime date, String message){
        this.id = id;
        this.date = date;
        this.message = message;
    }


}
