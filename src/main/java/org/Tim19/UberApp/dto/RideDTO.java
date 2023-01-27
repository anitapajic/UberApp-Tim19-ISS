package org.Tim19.UberApp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.Tim19.UberApp.model.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
public class RideDTO {
    private Integer id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Double totalCost;

    private Driver driver;

    private Set<Passenger> passengers = new HashSet<>();
    private Integer estimatedTimeInMinutes;


    private Set<Message> reviews;
    private Vehicle vehicle;

    private Boolean panic;
    private boolean babyTransport;
    private boolean petTransport;
    private String status;

    private Set<Path> locations;

    private Set<Rejection> rejections;

    private String routeJSON;


    public RideDTO(Integer id, LocalDateTime startTime, LocalDateTime endTime, Double totalCost, Driver driver, Set<Passenger> passengersDTO, Integer estimatedTimeInMinutes, Vehicle vehicle, Boolean panic, boolean babyTransport, boolean petTransport, String status, Set<Path> paths, Set<Rejection> rejections, String routeJSON) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalCost = totalCost;
        this.driver = driver;
        this.passengers = passengersDTO;
        this.estimatedTimeInMinutes = estimatedTimeInMinutes;
        this.vehicle = vehicle;
        this.panic = panic;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
        this.status = status;
        this.locations = paths;
        this.rejections = rejections;
        this.routeJSON = routeJSON;
    }


    public RideDTO(Ride ride) {
        this(ride.getId(), ride.getStartTime(), ride.getEndTime(), ride.getTotalCost(), ride.getDriver(), ride.getPassengers(), ride.getEstimatedTimeInMinutes(), ride.getDriver().getVehicle(), ride.isPanic(), ride.isBabyTransport(), ride.isPetTransport(), ride.getStatus(), ride.getLocations(), ride.getRejection(), ride.getRouteJSON());
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Set<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(Set<Passenger> passengers) {
        this.passengers = passengers;
    }
    public void addPassenger(Passenger passenger) {
        this.passengers.add(passenger);
    }
    public Integer getEstimatedTimeInMinutes() {
        return estimatedTimeInMinutes;
    }

    public void setEstimatedTimeInMinutes(Integer estimatedTimeInMinutes) {
        this.estimatedTimeInMinutes = estimatedTimeInMinutes;
    }

    public Set<Message> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Message> reviews) {
        this.reviews = reviews;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Boolean getPanic() {
        return panic;
    }

    public void setPanic(Boolean panic) {
        this.panic = panic;
    }

    public boolean isBabyTransport() {
        return babyTransport;
    }

    public void setBabyTransport(boolean babyTransport) {
        this.babyTransport = babyTransport;
    }

    public boolean isPetTransport() {
        return petTransport;
    }

    public void setPetTransport(boolean petTransport) {
        this.petTransport = petTransport;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<Path> getLocations() {
        return locations;
    }

    public void setLocations(Set<Path> locations) {
        this.locations = locations;
    }

    public Set<Rejection> getRejections() {
        return rejections;
    }

    public void setRejections(Set<Rejection> rejections) {
        this.rejections = rejections;
    }

    public String getRouteJSON() {
        return routeJSON;
    }

    public void setRouteJSON(String routeJSON) {
        this.routeJSON = routeJSON;
    }

    @Override
    public String toString() {
        return "RideDTO{" +
                "id=" + id +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", totalCost=" + totalCost +
                ", driver=" + driver +
                ", passengers=" + passengers +
                ", estimatedTimeInMinutes=" + estimatedTimeInMinutes +
                ", reviews=" + reviews +
                ", vehicle=" + vehicle +
                ", panic=" + panic +
                ", babyTransport=" + babyTransport +
                ", petTransport=" + petTransport +
                ", status='" + status + '\'' +
                ", locations=" + locations +
                ", rejections=" + rejections +
                ", routeJSON='" + routeJSON + '\'' +
                '}';
    }
}
