package org.Tim19.UberApp.dto.PaginatedData;

import org.Tim19.UberApp.model.VehicleType;

public class VehiclePaginated2DTO {
    private Integer id;
    private Integer driver_id;
    private String carModel;
    private VehicleType vehicleType;
    private String licenseNumber;
    private LocationPaginatedDTO currentLocation;
    private Integer passengerSeats;
    private boolean babyTransport;
    private boolean petTransport;


    public VehiclePaginated2DTO(Integer id, Integer driver_id, String carModel, VehicleType vehicleType, String licenseNumber, LocationPaginatedDTO location, Integer passengerSeats, boolean babyTransport, boolean petTransport) {
        this.id = id;
        this.driver_id = driver_id;
        this.carModel = carModel;
        this.vehicleType = vehicleType;
        this.licenseNumber = licenseNumber;
        this.currentLocation = location;
        this.passengerSeats = passengerSeats;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
    }

    public VehiclePaginated2DTO() {
    }
    public Integer getId() {
        return id;
    }

    public Integer getDriver_id() {
        return driver_id;
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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDriver_id(Integer driver_id) {
        this.driver_id = driver_id;
    }
}
