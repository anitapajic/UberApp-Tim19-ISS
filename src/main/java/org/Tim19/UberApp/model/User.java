package org.Tim19.UberApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;


@Data
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="email",unique = true, nullable = false)
    private String email;

    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Column(name="lastname", nullable = false)
    private String lastname;

    @Column(name="profile_picture", nullable = false)
    private String profilePicture;

    @Column(name="telephone_number", nullable = false)
    private String telephoneNumber;

    @Column(name="address", nullable = false)
    private String address;

    @Column(name="password", nullable = false)
    private String password;

    @Column(name="active", nullable = false)
    private Boolean active;

    @Column(name="blocked", nullable = false)
    private Boolean blocked;


    public User(Integer id, String firstname, String lastname, String profilePicture, String telephoneNumber, String email, String address, String password, Boolean active, Boolean blocked) {
        super();
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

}
