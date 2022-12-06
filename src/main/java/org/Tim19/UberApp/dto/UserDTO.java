package org.Tim19.UberApp.dto;

import org.Tim19.UberApp.model.User;

public class UserDTO {
    private Integer id;
    private String firstname;
    private String lastname;
    private String profilePicture;
    private String telephoneNumber;
    private String email;
    private String address;
    private String password;
    private Boolean active;
    private Boolean blocked;

    public UserDTO(Integer id, String firstname, String lastname, String profilePicture, String telephoneNumber, String email, String address, String password, Boolean active, Boolean blocked) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.profilePicture = profilePicture;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.address = address;
        this.password = password;
        this.active = active;
        this.blocked = blocked;
    }

    public UserDTO() {}

    public UserDTO(User user) {
        this(user.getId(), user.getFirstname(), user.getLastname(), user.getProfilePicture(), user.getTelephoneNumber(), user.getEmail(), user.getAddress(), user.getPassword(), user.getActive(), user.getBlocked());
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
