package org.Tim19.UberApp.dto.PaginatedData;

import org.Tim19.UberApp.model.VehicleType;

public class Vehicle3PaginatedDTO {

    private String carModel;
    private VehicleType vehicleType;
    private String licenseNumber;
    private LocationPaginatedDTO currentLocation;
    private Integer passengerSeats;
    private boolean babyTransport;
    private boolean petTransport;

    public Vehicle3PaginatedDTO(String carModel, VehicleType vehicleType, String licenseNumber, LocationPaginatedDTO currentLocation, Integer passengerSeats, boolean babyTransport, boolean petTransport) {

        this.carModel = carModel;
        this.vehicleType = vehicleType;
        this.licenseNumber = licenseNumber;
        this.currentLocation = currentLocation;
        this.passengerSeats = passengerSeats;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
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

    public LocationPaginatedDTO getCurrentLocation() {
        return currentLocation;
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

}
