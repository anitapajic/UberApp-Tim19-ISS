package org.Tim19.UberApp.dto;

import org.Tim19.UberApp.model.Vehicle;

public class VehicleDTO {

    private Integer id;
    private String vehicleType;
    private String carModel;
    private String licenseNumber;
    private Integer passengerSeats;
    private boolean babyTransport;
    private boolean petTransport;



    public VehicleDTO(Integer id, String vehicleType, String carModel, String licenseNumber, Integer passengerSeats, boolean babyTransport, boolean petTransport) {
        this.id = id;
        this.vehicleType = vehicleType;
        this.carModel = carModel;
        this.licenseNumber = licenseNumber;
        this.passengerSeats = passengerSeats;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
    }

    public VehicleDTO() {}

    public VehicleDTO(Vehicle vehicle) {
        this(vehicle.getId(),vehicle.getVehicleType(), vehicle.getCarModel(), vehicle.getLicenseNumber(), vehicle.getPassengerSeats(), vehicle.isBabyTransport(), vehicle.isPetTransport());
    }

    public Integer getId(){ return id;}
    public String getVehicleType() {
        return vehicleType;
    }

    public String getCarModel() {
        return carModel;
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
}
