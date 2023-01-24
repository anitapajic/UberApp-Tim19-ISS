package org.Tim19.UberApp.model;

import javax.persistence.*;

@Entity
public class UpdateDriver {
    @Id
    private Integer id;

    @Column(name="username",unique = true, nullable = false)
    private String username;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name="surname", nullable = false)
    private String surname;


    @Column(name="telephone_number", nullable = false)
    private String telephoneNumber;

    @Column(name="address", nullable = true)
    private String address;

    public UpdateDriver() {
    }

    public UpdateDriver(Integer id, String username, String name, String surname, String telephoneNumber, String address) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.telephoneNumber = telephoneNumber;
        this.address = address;
    }

    public UpdateDriver(Driver driver){
        this.id = driver.getId();
        this.username = driver.getUsername();
        this.name = driver.getName();
        this.surname = driver.getSurname();
        this.telephoneNumber = driver.getTelephoneNumber();
        this.address = driver.getAddress();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
