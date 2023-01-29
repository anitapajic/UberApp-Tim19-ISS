package org.Tim19.UberApp.dto;

import java.time.LocalDateTime;

public class FilterRidesFromDatesDTO {
    LocalDateTime startDateRides;
    LocalDateTime endDateRides;

    public FilterRidesFromDatesDTO(LocalDateTime startDateRides, LocalDateTime endDateRides) {
        this.startDateRides = startDateRides;
        this.endDateRides = endDateRides;
    }

    public LocalDateTime getStartDateRides() {
        return startDateRides;
    }

    public void setStartDateRides(LocalDateTime startDateRides) {
        this.startDateRides = startDateRides;
    }

    public LocalDateTime getEndDateRides() {
        return endDateRides;
    }

    public void setEndDateRides(LocalDateTime endDateRides) {
        this.endDateRides = endDateRides;
    }
}
