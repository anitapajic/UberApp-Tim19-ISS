package org.Tim19.UberApp.dto;

import org.Tim19.UberApp.model.Passenger;
import org.Tim19.UberApp.model.Ride;

import java.util.ArrayList;
import java.util.Set;

public class PassengerDTO extends UserDTO{

    private Set<Ride> rides;

    public PassengerDTO(){}

    public PassengerDTO(Integer id, String firstname, String lastname, String profilePicture, String telephoneNumber, String email, String address, String password, Boolean active, Boolean blocked, Set<Ride> rides) {
        super(id, firstname, lastname, profilePicture, telephoneNumber, email, address, password, active, blocked);
        this.rides = rides;
    }

    public PassengerDTO(Passenger passenger){
        this(passenger.getId(), passenger.getFirstname(), passenger.getLastname(), passenger.getProfilePicture(), passenger.getTelephoneNumber(), passenger.getEmail(), passenger.getAddress(), passenger.getPassword(), passenger.getActive(), passenger.getBlocked(),passenger.getRides());
    }

    public Set<Ride> getRides() {
        return rides;
    }
}
