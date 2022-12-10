package org.Tim19.UberApp.dto.PaginatedData;

import java.time.LocalDateTime;

public class WorkingHoursPaginatedDTO {
    private Integer id;
    private LocalDateTime start;
    private LocalDateTime endd;

    public WorkingHoursPaginatedDTO() {
    }

    public WorkingHoursPaginatedDTO(Integer id, LocalDateTime start, LocalDateTime endd) {
        this.id = id;
        this.start = start;
        this.endd = endd;
    }

    public Integer getId() {
        return id;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEndd() {
        return endd;
    }
}
