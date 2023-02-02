package org.Tim19.UberApp.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @JsonIgnore
//    @OneToOne
//    @JoinColumn(name = "driver_id")
//    private Driver driver;

    @Column(name = "carModel", nullable = false)
    private String carModel;

    @Column(name = "vehicleType", nullable = false)
    private VehicleType vehicleType;

    @Column(name = "licenseNumber", nullable = false)
    private String licenseNumber;

    @Column(name = "passengerSeats", nullable = false)
    private Integer passengerSeats;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH}, fetch = FetchType.EAGER)
    private Location location;

    @Column(name = "babyTransport", nullable = false)
    private boolean babyTransport;

    @Column(name = "petTransport", nullable = false)
    private boolean petTransport;

//    @JsonIgnore
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Set<Message> reviews = new HashSet<>();


//    public void addReview(Message message){
//        this.reviews.add(message);
//    }
//
//    public  void removeReview(Message message){
//        this.reviews.remove(message);
//    }

}


