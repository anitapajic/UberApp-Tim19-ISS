package org.Tim19.UberApp.dto;

import org.Tim19.UberApp.model.Vehicle;
import org.Tim19.UberApp.model.WorkingHours;

public class WorkingHoursDTO {
    private Integer id;
    private String start;
    private String endd;

    public WorkingHoursDTO() {
    }

    public WorkingHoursDTO(WorkingHours workingHours) {
        this(workingHours.getId(),workingHours.getStart(), workingHours.getEnd());
    }

    public WorkingHoursDTO(Integer id, String start, String end) {
        this.id = id;
        this.start = start;
        this.endd = end;
    }

    public Integer getId() {
        return id;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return endd;
    }
}
