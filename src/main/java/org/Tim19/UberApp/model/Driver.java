package org.Tim19.UberApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@DiscriminatorValue("Driver")
public class Driver extends User{

    @JsonIgnore
    @OneToMany(cascade ={CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH},
            fetch = FetchType.EAGER, mappedBy = "driver")
    private Set<Ride> rides = new HashSet<Ride>();

    @JsonIgnore
    @OneToMany(cascade ={CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH},
            fetch = FetchType.LAZY, mappedBy = "driver")
    private Set<DriverDocument> documents = new HashSet<DriverDocument>();

    @OneToOne
    @JoinColumn(name="vehicle_id")
    private Vehicle vehicle;

    private Boolean hasRide;

    public Driver(Integer id, String email, String name, String surname, String profilePicture, String telephoneNumber, String address, String password, Boolean active, Boolean blocked, Set<Ride> rides, Set<DriverDocument> documents, Vehicle vehicle, String authorities, Boolean hasRide) {
        super(id, email, name, surname, profilePicture, telephoneNumber, address, password, active, blocked, authorities);
        this.rides = rides;
        this.documents = documents;
        this.vehicle = vehicle;
        this.hasRide = false;
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

    public Boolean getHasRide() {
        return hasRide;
    }

    public void setHasRide(Boolean hasRide) {
        this.hasRide = hasRide;
    }
}