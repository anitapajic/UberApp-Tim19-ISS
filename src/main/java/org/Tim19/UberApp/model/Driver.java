package org.Tim19.UberApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
            fetch = FetchType.LAZY, mappedBy = "driver")
    private Set<Ride> rides = new HashSet<Ride>();

    @JsonIgnore
    @OneToMany(cascade ={CascadeType.ALL},
            fetch = FetchType.LAZY, mappedBy = "driver")
    private Set<DriverDocument> documents = new HashSet<DriverDocument>();

    @JsonIgnore
    @OneToOne
    @JoinColumn(name="vehicle_id")
    private Vehicle vehicle;

    public Driver(Integer id, String email, String name, String surname, String profilePicture, String telephoneNumber, String address, String password, Boolean active, Boolean blocked, Set<Ride> rides, Set<DriverDocument> documents, Vehicle vehicle, String authorities) {
        super(id, email, name, surname, profilePicture, telephoneNumber, address, password, active, blocked, authorities);
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