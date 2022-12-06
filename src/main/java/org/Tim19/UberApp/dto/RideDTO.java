package org.Tim19.UberApp.dto;

import org.Tim19.UberApp.model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Set;

public class RideDTO {
    private Integer id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Float totalCost;

    private Driver driver;

    private Set<Passenger> passengersDTO;
    private Integer estimatedTimeInMinutes;

    private Set<Message> reviews;
    private VehicleType vehicleType;

    private Boolean panic;
    private boolean babyTransport;
    private boolean petTransport;
    private String status;

    ///TODO: Lista putanji i odbijenica


    public RideDTO(Integer id, LocalDateTime startTime, LocalDateTime endTime, Float totalCost, Driver driver, Set<Passenger> passengersDTO, Integer estimatedTimeInMinutes, Set<Message> reviews, VehicleType vehicleType, Boolean panic, boolean babyTransport, boolean petTransport, String status) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalCost = totalCost;
        this.driver = driver;
        this.passengersDTO = passengersDTO;
        this.estimatedTimeInMinutes = estimatedTimeInMinutes;
        this.reviews = reviews;
        this.vehicleType = vehicleType;
        this.panic = panic;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
        this.status = status;
    }

    public RideDTO(){}

    public RideDTO(Ride ride) {
        this(ride.getId(), ride.getStartTime(), ride.getEndTime(), ride.getTotalCost(), ride.getDriver(), ride.getPassengers(), ride.getEstimatedTimeInMinutes(), ride.getReviews(), ride.getVehicleType(), ride.isPanic(), ride.isBabyTransport(), ride.isPetTransport(), ride.getStatus());
    }

    public Integer getId() {
        return id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public Float getTotalCost() {
        return totalCost;
    }

    public Driver getDriver() {
        return driver;
    }

    public Set<Passenger> getPassengersDTO() {
        return passengersDTO;
    }

    public Integer getEstimatedTimeInMinutes() {
        return estimatedTimeInMinutes;
    }

    public Set<Message> getReviews() {
        return reviews;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public Boolean getPanic() {
        return panic;
    }

    public boolean isBabyTransport() {
        return babyTransport;
    }

    public boolean isPetTransport() {
        return petTransport;
    }

    public String getStatus() {
        return status;
    }
}
