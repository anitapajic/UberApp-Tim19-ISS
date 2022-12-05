package org.Tim19.UberApp.dto;

import org.Tim19.UberApp.model.Driver;

public class DriverDTO extends UserDTO{

    public DriverDTO(Integer id, String firstname, String lastname, String profilePicture, String telephoneNumber, String email, String address, String password) {

    }
    public DriverDTO(Driver driver) {
        this(driver.getId(), driver.getFirstname(), driver.getLastname(), driver.getProfilePicture(), driver.getTelephoneNumber(), driver.getEmail(), driver.getAddress(), driver.getPassword());
    }
}
