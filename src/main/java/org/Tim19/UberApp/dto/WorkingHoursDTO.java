package org.Tim19.UberApp.dto;

import lombok.Data;
import org.Tim19.UberApp.model.Driver;
import org.Tim19.UberApp.model.WorkingHours;

import java.time.LocalDateTime;

@Data
public class WorkingHoursDTO {
    private Integer id;
    private LocalDateTime start;
    private LocalDateTime end;

    private Integer driverId;

    public WorkingHoursDTO() {
    }

    public WorkingHoursDTO(Integer id, LocalDateTime start, LocalDateTime end, Driver driver) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.driverId = driver.getId();
    }

    public WorkingHoursDTO(WorkingHours workingHours) {
        this(workingHours.getId(),workingHours.getStartD(), workingHours.getEndD(),workingHours.getDriver());
    }


   }
