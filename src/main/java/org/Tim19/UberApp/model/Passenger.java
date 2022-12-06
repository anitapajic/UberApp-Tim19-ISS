package org.Tim19.UberApp.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@DiscriminatorValue("Passenger")
public class Passenger extends User{

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Ride> rides = new HashSet<>();

    //private List<> favourite = new ArrayList<>();


    public Passenger(Integer id, String firstname, String lastname, String profilePicture, String telephoneNumber, String email, String address, String password, Boolean active, Boolean blocked, Set<Ride> rides) {
        super(id, firstname, lastname, profilePicture, telephoneNumber, email, address, password, active, blocked);
        this.rides = rides;
    }

    public void addRide(Ride ride){
        this.rides.add(ride);
    }

    public  void removeRide(Ride ride){
        this.rides.remove(ride);
    }
}

