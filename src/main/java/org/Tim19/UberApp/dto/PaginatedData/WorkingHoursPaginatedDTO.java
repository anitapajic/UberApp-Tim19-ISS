package org.Tim19.UberApp.dto.PaginatedData;

import java.time.LocalDateTime;

public class WorkingHoursPaginatedDTO {
    private Integer id;
    private LocalDateTime start;
    private LocalDateTime end;

    public WorkingHoursPaginatedDTO() {
    }

    public WorkingHoursPaginatedDTO(Integer id, LocalDateTime start, LocalDateTime endd) {
        this.id = id;
        this.start = start;
        this.end = endd;
    }

    public Integer getId() {
        return id;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }
}
