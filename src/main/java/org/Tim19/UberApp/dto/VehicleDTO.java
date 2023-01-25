package org.Tim19.UberApp.dto;

import lombok.Data;
import org.Tim19.UberApp.model.*;

import java.util.HashSet;
import java.util.Set;

@Data
public class VehicleDTO {

    private Integer id;
    private Integer driverId;
    private String model;
    private VehicleType vehicleType;
    private String licenseNumber;
    private Integer passengerSeats;
    private boolean babyTransport;
    private boolean petTransport;
    private LocationDTO currentLocation;
    private boolean isDriverActive;

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
        this(vehicle.getId(), vehicle.getCarModel(),vehicle.getVehicleType(), vehicle.getLicenseNumber(), vehicle.getPassengerSeats(), vehicle.isBabyTransport(), vehicle.isPetTransport(), new LocationDTO(vehicle.getLocation()));
    }


    public Integer getId() {
        return id;
    }

    public Integer getDriverId() {
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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public void setPassengerSeats(Integer passengerSeats) {
        this.passengerSeats = passengerSeats;
    }

    public void setBabyTransport(boolean babyTransport) {
        this.babyTransport = babyTransport;
    }

    public void setPetTransport(boolean petTransport) {
        this.petTransport = petTransport;
    }

    public void setCurrentLocation(LocationDTO currentLocation) {
        this.currentLocation = currentLocation;
    }

    public void setReviews(Set<Message> reviews) {
        this.reviews = reviews;
    }

    public boolean isDriverActive() {
        return isDriverActive;
    }

    public void setDriverActive(boolean driverActive) {
        isDriverActive = driverActive;
    }

    @Override
    public String toString() {
        return "VehicleDTO{" +
                "id=" + id +
                ", driverId=" + driverId +
                ", model='" + model + '\'' +
                ", vehicleType=" + vehicleType +
                ", licenseNumber='" + licenseNumber + '\'' +
                ", passengerSeats=" + passengerSeats +
                ", babyTransport=" + babyTransport +
                ", petTransport=" + petTransport +
                ", currentLocation=" + currentLocation +
                ", isDriverActive=" + isDriverActive +
                ", reviews=" + reviews +
                '}';
    }
}
