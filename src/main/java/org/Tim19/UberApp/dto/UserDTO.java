package org.Tim19.UberApp.dto;

import org.Tim19.UberApp.model.Users;

public class UserDTO {
    private Integer id;
    private String firstname;
    private String lastname;
    private String profilePicture;
    private String telephoneNumber;
    private String email;
    private String address;
    private String password;

    public UserDTO(Integer id, String firstname, String lastname, String profilePicture, String telephoneNumber, String email, String address, String password) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.profilePicture = profilePicture;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.address = address;
        this.password = password;
    }

    public UserDTO() {}

    public UserDTO(Users users) {
        this(users.getId(), users.getFirstname(), users.getLastname(), users.getProfilePicture(), users.getTelephoneNumber(), users.getEmail(), users.getAddress(), users.getPassword());
    }

    public Integer getId() {
        return id;
    }


    public String getFirstname() {
        return firstname;
    }



    public String getLastname() {
        return lastname;
    }



    public String getProfilePicture() {
        return profilePicture;
    }



    public String getTelephoneNumber() {
        return telephoneNumber;
    }



    public String getEmail() {
        return email;
    }



    public String getAddress() {
        return address;
    }


    public String getPassword() {
        return password;
    }



}
