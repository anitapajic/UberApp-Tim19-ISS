package org.Tim19.UberApp.dto;

import org.Tim19.UberApp.model.Driver;
import org.Tim19.UberApp.model.DriverDocument;
import org.Tim19.UberApp.model.Ride;
import org.Tim19.UberApp.model.Vehicle;

import java.util.Set;

public class DriverDTO extends UserDTO{

    private Set<Ride> rides;
    private Set<DriverDocument> documents;
    private Vehicle vehicle;

    public DriverDTO(Integer id, String firstname, String lastname, String profilePicture, String telephoneNumber, String email, String address, String password, Boolean active, Boolean blocked,Set<Ride> rides, Set<DriverDocument> documents, Vehicle vehicle) {
        super(id, firstname, lastname, profilePicture, telephoneNumber, email, address, password, active, blocked);
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
