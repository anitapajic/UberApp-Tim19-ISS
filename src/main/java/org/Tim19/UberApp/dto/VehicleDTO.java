package org.Tim19.UberApp.dto;

import lombok.Data;
import org.Tim19.UberApp.model.*;

import java.util.HashSet;
import java.util.Set;

@Data
public class VehicleDTO {

    private Integer id;
    private Driver driverId;
    private String model;
    private VehicleType vehicleType;
    private String licenseNumber;
    private Integer passengerSeats;
    private boolean babyTransport;
    private boolean petTransport;

    private LocationDTO currentLocation;
    private Set<Message> reviews = new HashSet<>();


    public VehicleDTO(Integer id, String carModel, VehicleType vehicleType, String licenseNumber, Integer passengerSeats, boolean babyTransport, boolean petTransport, LocationDTO location) {
        this.id = id;
       // this.driverId = driver;
        this.model = carModel;
        this.vehicleType = vehicleType;
        this.licenseNumber = licenseNumber;
        this.passengerSeats = passengerSeats;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
       // this.reviews = reviews;
        this.currentLocation = location;
    }

    public VehicleDTO() {}

    public VehicleDTO(Vehicle vehicle) {
        this(vehicle.getId(), vehicle.getCarModel(),vehicle.getVehicleType(), vehicle.getLicenseNumber(), vehicle.getPassengerSeats(), vehicle.isBabyTransport(), vehicle.isPetTransport(), new LocationDTO());
    }


    public Integer getId() {
        return id;
    }

    public Driver getDriverId() {
        return driverId;
    }

    public String getModel() {
        return model;
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
