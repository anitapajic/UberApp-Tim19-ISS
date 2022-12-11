package org.Tim19.UberApp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.Tim19.UberApp.model.Driver;
import org.Tim19.UberApp.model.DriverDocument;
import org.Tim19.UberApp.model.Ride;
import org.Tim19.UberApp.model.Vehicle;

import java.util.HashSet;
import java.util.Set;

public class DriverDTO extends UserDTO{

    @JsonIgnore
    private Set<Ride> rides = new HashSet<Ride>();
    @JsonIgnore
    private Set<DriverDocument> documents = new HashSet<>();
    private Vehicle vehicle;

    public DriverDTO(Integer id, String firstname, String lastname, String profilePicture, String telephoneNumber, String email, String address, String password, Boolean active, Boolean blocked,Set<Ride> rides, Set<DriverDocument> documents, Vehicle vehicle) {
        super(id, email, firstname, lastname, profilePicture, telephoneNumber, address, password, active, blocked);
        this.rides = rides;
        this.documents = documents;
        this.vehicle = vehicle;
    }
    public DriverDTO() {}

    public DriverDTO(Driver driver){
        this(driver.getId(), driver.getFirstname(), driver.getLastname(), driver.getProfilePicture(), driver.getTelephoneNumber(), driver.getEmail(), driver.getAddress(), driver.getPassword(), driver.getActive(), driver.getBlocked(),driver.getRides(), driver.getDocuments(), driver.getVehicle());
    }

    public Set<Ride> getRides() {
        return rides;
    }

    public Set<DriverDocument> getDocuments() {
        return documents;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}
