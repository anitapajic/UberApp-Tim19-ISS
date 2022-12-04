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

    public Vehicle() {
        super();
    }

    public Vehicle(Integer id, String vehicleType, String carModel, String licenseNumber, Integer passengerSeats, boolean babyTransport, boolean petTransport) {
        super();
        this.id = id;
        this.vehicleType = vehicleType;
        this.carModel = carModel;
        this.licenseNumber = licenseNumber;
        this.passengerSeats = passengerSeats;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vehicle v = (Vehicle) o;
        if (v.licenseNumber == null || licenseNumber == null) {
            return false;
        }
        return Objects.equals(licenseNumber, v.licenseNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(licenseNumber);
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", vehicleType='" + vehicleType + '\'' +
                ", carModel='" + carModel + '\'' +
                ", licenseNumber='" + licenseNumber + '\'' +
                ", passengerSeats=" + passengerSeats +
                ", babyTransport=" + babyTransport +
                ", petTransport=" + petTransport +
                '}';
    }
}


