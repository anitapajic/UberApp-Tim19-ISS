package org.Tim19.UberApp.dto;

import org.Tim19.UberApp.model.*;

import java.util.Set;

public class VehicleDTO {

    private Integer id;
    private Driver driver;
    private String carModel;
    private VehicleType vehicleType;
    private String licenseNumber;
    private Integer passengerSeats;
    private boolean babyTransport;
    private boolean petTransport;
    private Set<Message> reviews;


    public VehicleDTO(Integer id, Driver driver, String carModel, VehicleType vehicleType, String licenseNumber, Integer passengerSeats, boolean babyTransport, boolean petTransport, Set<Message> reviews) {
        this.id = id;
        this.driver = driver;
        this.carModel = carModel;
        this.vehicleType = vehicleType;
        this.licenseNumber = licenseNumber;
        this.passengerSeats = passengerSeats;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
        this.reviews = reviews;
    }

    public VehicleDTO() {}

    public VehicleDTO(Vehicle vehicle) {
        this(vehicle.getId(), vehicle.getDriver(), vehicle.getCarModel(),vehicle.getVehicleType(), vehicle.getLicenseNumber(), vehicle.getPassengerSeats(), vehicle.isBabyTransport(), vehicle.isPetTransport(), vehicle.getReviews());
    }


    public Integer getId() {
        return id;
    }

    public Driver getDriver() {
        return driver;
    }

    public String getCarModel() {
        return carModel;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public Integer getPassengerSeats() {
        return passengerSeats;
    }

    public boolean isBabyTransport() {
        return babyTransport;
    }

    public boolean isPetTransport() {
        return petTransport;
    }

    public Set<Message> getReviews() {
        return reviews;
    }
}
