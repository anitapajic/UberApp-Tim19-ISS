package org.Tim19.UberApp.dto;

import org.Tim19.UberApp.model.Ride;

import java.time.LocalDateTime;

public class RideDTO {
    private Integer id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Float totalCost;

    ///TODO:Dodati objekat vozaca, putnika i lokacija
    //private Driver driver;
    //private ArrayList<UserDTO> usersDTO;
    private Integer estimatedTimeInMinutes;
    private String vehicleType;
    private boolean babyTransport;
    private boolean petTransport;
    //private ArrayList<Location> locations;
    private String status;


    public RideDTO(Integer id, LocalDateTime startTime, LocalDateTime endTime, Float totalCost, Integer estimatedTimeInMinutes, String vehicleType, boolean babyTransport, boolean petTransport, String status) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalCost = totalCost;
        this.estimatedTimeInMinutes = estimatedTimeInMinutes;
        this.vehicleType = vehicleType;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
        this.status = status;
    }
    public RideDTO(){}

    public RideDTO(Ride ride) {
        this(ride.getId(), ride.getStartTime(), ride.getEndTime(), ride.getTotalCost(), ride.getEstimatedTimeInMinutes(), ride.getVehicleType(), ride.isBabyTransport(), ride.isPetTransport(), ride.getStatus());
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


//    public ArrayList<UserDTO> getUsersDTO() {
//        return usersDTO;
//    }


    public Integer getEstimatedTimeInMinutes() {
        return estimatedTimeInMinutes;
    }


    public String getVehicleType() {
        return vehicleType;
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
