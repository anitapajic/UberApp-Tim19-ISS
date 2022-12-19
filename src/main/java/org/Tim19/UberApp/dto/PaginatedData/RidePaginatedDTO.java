package org.Tim19.UberApp.dto.PaginatedData;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.Tim19.UberApp.model.Passenger;
import org.Tim19.UberApp.model.Path;
import org.Tim19.UberApp.model.Ride;
import org.Tim19.UberApp.model.VehicleType;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class RidePaginatedDTO {

    private Integer id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Double totalCost;
    private UserPaginatedDTO driver;
    private Set<UserPaginatedDTO> passengers;
    private Integer estimatedTimeInMinutes;
    private VehicleType vehicleType;
    private Boolean babyTransport;
    private Boolean petTransport;
    private RejectionPaginatedDTO rejection;
    private Set<PathPaginatedDTO> locations;
    private String status;

    public RidePaginatedDTO(Integer id, LocalDateTime startTime, LocalDateTime endTime, Double totalCost, UserPaginatedDTO driver, Set<UserPaginatedDTO> passengers, Integer estimatedTimeInMinutes, VehicleType vehicleType, Boolean babyTransport, Boolean petTransport, RejectionPaginatedDTO rejection, Set<PathPaginatedDTO> locations, String status) {
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

    public RidePaginatedDTO(Ride ride){
        this.id = ride.getId();
        this.startTime = ride.getStartTime();
        this.endTime = ride.getEndTime();
        this.totalCost = ride.getTotalCost();
        this.driver = getDriver();

        Set<UserPaginatedDTO> passengers = new HashSet<>();
        for (Passenger p : ride.getPassengers()) {
            UserPaginatedDTO passenger = new UserPaginatedDTO();
            passenger.setEmail(p.getEmail());
            passenger.setId(p.getId());
            passengers.add(passenger);
        }

        this.passengers = passengers;
        this.estimatedTimeInMinutes = ride.getEstimatedTimeInMinutes();
        this.vehicleType = ride.getVehicleType();
        this.babyTransport = ride.isBabyTransport();
        this.petTransport = ride.isPetTransport();

        Set<PathPaginatedDTO> paths = new HashSet<>();
        for (Path p : ride.getPaths()) {
            PathPaginatedDTO path = new PathPaginatedDTO();
            path.setDeparture(new LocationPaginatedDTO(p.getDeparture()));
            path.setDestination(new LocationPaginatedDTO(p.getDestination()));

            paths.add(path);
        }
        this.locations = paths;
        this.status = status;
    }

}
