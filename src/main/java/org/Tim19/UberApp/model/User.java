package org.Tim19.UberApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="email",unique = true, nullable = false)
    private String email;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name="surname", nullable = false)
    private String surname;

    @Column(name="profile_picture", nullable = true)
    private String profilePicture;

    @Column(name="telephone_number", nullable = false)
    private String telephoneNumber;

    @Column(name="address", nullable = true)
    private String address;

    @Column(name="password", nullable = false)
    private String password;

    @Column(name="active", nullable = false)
    private Boolean active;

    @Column(name="blocked", nullable = false)
    private Boolean blocked;


}
