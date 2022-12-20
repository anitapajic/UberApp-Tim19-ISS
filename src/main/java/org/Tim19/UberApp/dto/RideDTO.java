package org.Tim19.UberApp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.Tim19.UberApp.model.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class RideDTO {
    private Integer id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Double totalCost;

    private Driver driver;

    private Set<Passenger> passengersDTO = new HashSet<>();
    private Integer estimatedTimeInMinutes;


    private Set<Message> reviews;
    private VehicleType vehicleType;

    private Boolean panic;
    private boolean babyTransport;
    private boolean petTransport;
    private String status;
    @JsonIgnore
    private Set<Path> paths;

    private Set<Rejection> rejectons;


    public RideDTO(Integer id, LocalDateTime startTime, LocalDateTime endTime, Double totalCost, Driver driver, Set<Passenger> passengersDTO, Integer estimatedTimeInMinutes, VehicleType vehicleType, Boolean panic, boolean babyTransport, boolean petTransport, String status, Set<Path> paths) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalCost = totalCost;
        this.driver = driver;
        this.passengersDTO = passengersDTO;
        this.estimatedTimeInMinutes = estimatedTimeInMinutes;
        this.vehicleType = vehicleType;
        this.panic = panic;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
        this.status = status;
        this.paths = paths;
    }


    public RideDTO(Ride ride) {
        this(ride.getId(), ride.getStartTime(), ride.getEndTime(), ride.getTotalCost(), ride.getDriver(), ride.getPassengers(), ride.getEstimatedTimeInMinutes(), ride.getVehicleType(), ride.isPanic(), ride.isBabyTransport(), ride.isPetTransport(), ride.getStatus(), ride.getLocations());
    }


}
