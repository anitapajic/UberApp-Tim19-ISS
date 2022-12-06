package org.Tim19.UberApp.dto;

import org.Tim19.UberApp.model.User;

public class UserDTO {
    private Integer id;
    private String email;
    private String firstname;
    private String lastname;
    private String profilePicture;
    private String telephoneNumber;
    private String address;
    private String password;
    private Boolean active;
    private Boolean blocked;

    public UserDTO(Integer id, String email, String firstname, String lastname, String profilePicture, String telephoneNumber, String address, String password, Boolean active, Boolean blocked) {
        this.id = id;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.profilePicture = profilePicture;
        this.telephoneNumber = telephoneNumber;
        this.address = address;
        this.password = password;
        this.active = active;
        this.blocked = blocked;
    }

    public UserDTO() {}

    public UserDTO(User users){
        this(users.getId(), users.getEmail(), users.getFirstname(), users.getLastname(), users.getProfilePicture(), users.getTelephoneNumber(), users.getAddress(), users.getPassword(), users.getActive(), users.getBlocked());
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

    public Boolean getActive() { return active; }

    public Boolean getBlocked() { return blocked; }

}
