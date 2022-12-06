package org.Tim19.UberApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@DiscriminatorValue("Driver")
public class Driver extends User{

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<DriverDocument> documents = new HashSet<>();
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Ride> rides = new HashSet<>();
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    public Driver(Integer id, String firstname, String lastname, String profilePicture, String telephoneNumber, String email, String address, String password, Boolean active, Boolean blocked, Set<DriverDocument> documents, Set<Ride> rides, Vehicle vehicle) {
        super(id, firstname, lastname, profilePicture, telephoneNumber, email, address, password, active, blocked);
        this.documents = documents;
        this.rides = rides;
        this.vehicle = vehicle;
    }

    public void addDocument(DriverDocument document){
        this.documents.add(document);
    }

    public  void removeDocument(DriverDocument document){
        this.documents.remove(document);
    }
    public void addRide(Ride ride){
        this.rides.add(ride);
    }

    public  void removeRide(Ride ride){
        this.rides.remove(ride);
    }
}