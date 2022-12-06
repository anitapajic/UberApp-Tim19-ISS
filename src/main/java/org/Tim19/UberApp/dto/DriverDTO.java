package org.Tim19.UberApp.dto;

import org.Tim19.UberApp.model.Driver;
import org.Tim19.UberApp.model.Ride;

import java.util.ArrayList;
import java.util.Set;

public class DriverDTO extends UserDTO{


    public DriverDTO(Integer id, String firstname, String lastname, String profilePicture, String telephoneNumber, String email, String address, String password) {
//        new UserDTO(id, firstname, lastname, profilePicture, telephoneNumber, email, address, password);
        super(id, firstname, lastname, profilePicture, telephoneNumber, email, address, password);
    }

    public DriverDTO(){}
    public DriverDTO(Driver driver){
        this(driver.getId(), driver.getFirstname(), driver.getLastname(),
                driver.getProfilePicture(), driver.getTelephoneNumber(), driver.getEmail(), driver.getAddress(),
                driver.getPassword());
    }
}
