package org.Tim19.UberApp.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Passenger extends Users {

    @ManyToMany(cascade ={CascadeType.ALL},
                fetch = FetchType.EAGER)
    private Set<Ride> rides = new HashSet<Ride>();

    public Passenger() {
    }

    public Passenger(Integer id, String firstname, String lastname, String profilePicture, String telephoneNumber, String email, String address, String password) {
        super(id, firstname, lastname, profilePicture, telephoneNumber, email, address, password);
    }

    public Set<Ride> getRides() {
        return rides;
    }

    public void setRides(Set<Ride> rides) {
        this.rides = rides;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "rides=" + rides +
                '}';
    }
}
