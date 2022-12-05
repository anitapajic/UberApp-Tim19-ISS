package org.Tim19.UberApp.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
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

    @Column(name="profilepicture", nullable = false)
    private String profilePicture;

    @Column(name="telephonenumber", nullable = false)
    private String telephoneNumber;

    @Column(name="address", nullable = false)
    private String address;

    @Column(name="password", nullable = false)
    private String password;

    @Column(name="active", nullable = false)
    private Boolean active;

    @Column(name="blocked", nullable = false)
    private Boolean blocked;


    public User(){super();}

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() { return active; }

    public void setActive(Boolean active) { this.active = active; }

    public Boolean getBlocked() { return blocked; }

    public void setBlocked(Boolean blocked) { this.blocked = blocked; }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User s = (User) o;
        if (s.email == null || email == null) {
            return false;
        }
        return Objects.equals(email, s.email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(email);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                ", active=" + active +
                ", blocked=" + blocked +
                '}';
    }
}
