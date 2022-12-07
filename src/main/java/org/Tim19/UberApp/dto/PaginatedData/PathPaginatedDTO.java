package org.Tim19.UberApp.dto.PaginatedData;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PathPaginatedDTO {
    private LocationPaginatedDTO departure;
    private LocationPaginatedDTO destination;

    public PathPaginatedDTO(LocationPaginatedDTO departure, LocationPaginatedDTO destination) {
        this.departure = departure;
        this.destination = destination;
    }
}
