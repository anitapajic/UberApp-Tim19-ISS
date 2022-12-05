package org.Tim19.UberApp.dto;

import org.Tim19.UberApp.model.Ride;

import java.util.ArrayList;

public class PassengerDTO extends UserDTO{

    public PassengerDTO(Integer id, String firstname, String lastname, String profilePicture, String telephoneNumber, String email, String address, String password) {
        super(id, firstname, lastname, profilePicture, telephoneNumber, email, address, password);
    }

    public PassengerDTO(){}
}
