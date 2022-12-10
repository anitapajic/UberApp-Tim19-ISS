package org.Tim19.UberApp.dto;

import lombok.Data;
import org.Tim19.UberApp.model.User;

@Data
public class UserDTO {
    private Integer id;
    private String email;
    private String name;
    private String surname;
    private String profilePicture;
    private String telephoneNumber;
    private String address;
    private String password;

    public UserDTO(Integer id, String email, String firstname, String lastname, String profilePicture, String telephoneNumber, String address, String password, Boolean active, Boolean blocked) {
        this.id = id;
        this.email = email;
        this.name = firstname;
        this.surname = lastname;
        this.profilePicture = profilePicture;
        this.telephoneNumber = telephoneNumber;
        this.address = address;
        this.password = password;

    }

    public UserDTO() {}

    public UserDTO(User users){
        this(users.getId(), users.getEmail(), users.getFirstname(), users.getLastname(), users.getProfilePicture(), users.getTelephoneNumber(), users.getAddress(), users.getPassword(), users.getActive(), users.getBlocked());
    }


}
