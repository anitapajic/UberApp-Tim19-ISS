package org.Tim19.UberApp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class PanicUserDTO {
    private String email;
    private String name;
    private String surname;
    private String profilePicture;
    private String telephoneNumber;
    private String address;

    public PanicUserDTO(String email, String firstname, String lastname, String profilePicture, String telephoneNumber, String address) {
        this.email = email;
        this.name = firstname;
        this.surname = lastname;
        this.profilePicture = profilePicture;
        this.telephoneNumber = telephoneNumber;
        this.address = address;
    }

}
