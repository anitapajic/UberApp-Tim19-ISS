package org.Tim19.UberApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@DiscriminatorValue("Driver")
public class Driver extends User{

    @JsonIgnore
    @OneToMany(cascade ={CascadeType.ALL},
            fetch = FetchType.EAGER)
    private Set<Ride> rides = new HashSet<Ride>();

    @JsonIgnore
    @OneToMany(cascade ={CascadeType.ALL},
            fetch = FetchType.LAZY)
    private Set<DriverDocument> documents = new HashSet<DriverDocument>();

    @JsonIgnore
    @OneToOne
    @JoinColumn(name="vehicle_id")
    private Vehicle vehicle;

    public Driver(Integer id, String email, String firstname, String lastname, String profilePicture, String telephoneNumber, String address, String password, Boolean active, Boolean blocked, Set<Ride> rides, Set<DriverDocument> documents, Vehicle vehicle) {
        super(id, email, firstname, lastname, profilePicture, telephoneNumber, address, password, active, blocked);
        this.rides = rides;
        this.documents = documents;
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