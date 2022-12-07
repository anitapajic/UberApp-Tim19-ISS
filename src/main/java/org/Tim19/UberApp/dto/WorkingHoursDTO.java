package org.Tim19.UberApp.dto;

import org.Tim19.UberApp.model.WorkingHours;

import java.time.LocalDateTime;

public class WorkingHoursDTO {
    private Integer id;
    private LocalDateTime start;
    private LocalDateTime endd;

    public WorkingHoursDTO() {
    }

    public WorkingHoursDTO(WorkingHours workingHours) {
        this(workingHours.getId(),workingHours.getStartD(), workingHours.getEndD());
    }

    public WorkingHoursDTO(Integer id, LocalDateTime start, LocalDateTime end) {
        this.id = id;
        this.start = start;
        this.endd = end;
    }

    public Integer getId() {
        return id;
    }

    public LocalDateTime getStartD() {
        return start;
    }

    public LocalDateTime getEndD() {
        return endd;
    }
}
