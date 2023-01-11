package org.Tim19.UberApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@DiscriminatorValue("Passenger")
public class Passenger extends User{

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "passenger_ride",
            joinColumns = @JoinColumn(name = "ride_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "passenger_id", referencedColumnName = "id")
    )
    private Set<Ride> rides = new HashSet<>();

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH})
    @JoinTable(
            name = "passenger_favourites",
            joinColumns = @JoinColumn(name = "path_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "passenger_id", referencedColumnName = "id")
    )
    private Set<Path> favourite = new HashSet<>();


    public Passenger(Integer id, String name, String surname, String profilePicture, String telephoneNumber, String email, String address, String password, Boolean active, Boolean blocked, Set<Ride> rides, String authorities) {
        super(id, name, surname, profilePicture, telephoneNumber, email, address, password, active, blocked, authorities);
        this.rides = rides;
    }

    public void addRide(Ride ride){
        this.rides.add(ride);
    }

    public  void removeRide(Ride ride){
        this.rides.remove(ride);
    }
}

