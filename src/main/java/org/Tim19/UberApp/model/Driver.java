package org.Tim19.UberApp.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Driver extends Users{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @OneToMany(cascade ={CascadeType.ALL},
            fetch = FetchType.EAGER)
    private Set<Ride> rides = new HashSet<Ride>();

    @OneToMany(cascade ={CascadeType.ALL},
            fetch = FetchType.EAGER)
    private Set<DriverDocument> documents = new HashSet<DriverDocument>();

    @OneToOne(cascade ={CascadeType.ALL},
            fetch = FetchType.EAGER)
    @JoinColumn(name="vehicle_id", nullable = false)
    private Vehicle vehicle;
    public Driver(){}

    public Driver(Integer id, Set<Ride> rides, Set<DriverDocument> documents, Vehicle vehicle) {
        this.id = id;
        this.rides = rides;
        this.documents = documents;
        this.vehicle = vehicle;
    }

    public Driver(Integer id, String firstname, String lastname, String profilePicture, String telephoneNumber, String email, String address, String password, Integer id1, Set<Ride> rides, Set<DriverDocument> documents, Vehicle vehicle) {
        super(id, firstname, lastname, profilePicture, telephoneNumber, email, address, password);
        this.id = id1;
        this.rides = rides;
        this.documents = documents;
        this.vehicle = vehicle;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Ride> getRides() {
        return rides;
    }

    public void setRides(Set<Ride> rides) {
        this.rides = rides;
    }

    public Set<DriverDocument> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<DriverDocument> documents) {
        this.documents = documents;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}