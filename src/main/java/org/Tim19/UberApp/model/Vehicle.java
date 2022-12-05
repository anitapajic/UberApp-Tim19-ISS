package org.Tim19.UberApp.model;


import javax.persistence.*;
import java.util.Objects;

@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "vehicleType", unique = true, nullable = false)
    private String vehicleType;

    @Column(name = "carModel", nullable = false)
    private String carModel;

    @Column(name = "licenseNumber", nullable = false)
    private String licenseNumber;

    @Column(name = "passengerSeats", nullable = false)
    private Integer passengerSeats;

    @Column(name = "babyTransport", nullable = false)
    private boolean babyTransport;

    @Column(name = "petTransport", nullable = false)
    private boolean petTransport;



    public Vehicle(Integer id, String vehicleType, String carModel, String licenseNumber, Integer passengerSeats, boolean babyTransport, boolean petTransport) {
        this.id = id;
        this.vehicleType = vehicleType;
        this.carModel = carModel;
        this.licenseNumber = licenseNumber;
        this.passengerSeats = passengerSeats;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
    }
    public Vehicle(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public Integer getPassengerSeats() {
        return passengerSeats;
    }

    public void setPassengerSeats(Integer passengerSeats) {
        this.passengerSeats = passengerSeats;
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

}


