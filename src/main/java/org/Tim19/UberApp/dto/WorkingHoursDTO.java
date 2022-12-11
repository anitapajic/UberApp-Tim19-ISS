package org.Tim19.UberApp.dto;

import lombok.Data;
import org.Tim19.UberApp.model.WorkingHours;

import java.time.LocalDateTime;

@Data
public class WorkingHoursDTO {
    private Integer id;
    private LocalDateTime start;
    private LocalDateTime end;

    public WorkingHoursDTO() {
    }

    public WorkingHoursDTO(WorkingHours workingHours) {
        this(workingHours.getId(),workingHours.getStartD(), workingHours.getEndD());
    }

    public WorkingHoursDTO(Integer id, LocalDateTime start, LocalDateTime end) {
        this.id = id;
        this.start = start;
        this.end = end;
    }

    public Integer getId() {
        return id;
    }

   }
