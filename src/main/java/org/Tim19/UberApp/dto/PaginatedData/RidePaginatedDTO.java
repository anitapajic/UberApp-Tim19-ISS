package org.Tim19.UberApp.dto.PaginatedData;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.Tim19.UberApp.model.VehicleType;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class RidePaginatedDTO {

    private Integer id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Double totalCost;
    private UserPaginatedDTO driver;
    private List<UserPaginatedDTO> passengers;
    private Integer estimatedTimeInMinutes;
    private VehicleType vehicleType;
    private Boolean babyTransport;
    private Boolean petTransport;
    private RejectionPaginatedDTO rejection;
    private List<PathPaginatedDTO> locations;
    private String status;

    public RidePaginatedDTO(Integer id, LocalDateTime startTime, LocalDateTime endTime, Double totalCost, UserPaginatedDTO driver, List<UserPaginatedDTO> passengers, Integer estimatedTimeInMinutes, VehicleType vehicleType, Boolean babyTransport, Boolean petTransport, RejectionPaginatedDTO rejection, List<PathPaginatedDTO> locations, String status) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalCost = totalCost;
        this.driver = driver;
        this.passengers = passengers;
        this.estimatedTimeInMinutes = estimatedTimeInMinutes;
        this.vehicleType = vehicleType;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
        this.rejection = rejection;
        this.locations = locations;
        this.status = status;
    }

}
