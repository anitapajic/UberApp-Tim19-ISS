package org.Tim19.UberApp.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;


@Entity
@Data
@NoArgsConstructor
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="startTime", nullable = false)
    private LocalDateTime startTime;

    @Column(name="endTime", nullable = false)
    private LocalDateTime endTime;

    @Column(name="totalCost", nullable = false)
    private Double totalCost;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "passenger_ride",
            joinColumns = @JoinColumn(name = "ride_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "passenger_id", referencedColumnName = "id")
    )
    private Set<Passenger> passengers = new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade ={CascadeType.ALL},
            fetch = FetchType.LAZY)
    private Set<Path> paths = new HashSet<>();

    @Column(name="estimatedTimeInMinutes", nullable = false)
    private Integer estimatedTimeInMinutes;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Message> reviews = new HashSet<>();

    @Column(name="status", nullable = false)
    private String status;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Rejection> rejections;

    @Column(name="panic", nullable = false)
    private boolean panic;
    @Column(name="babyTransport", nullable = false)
    private boolean babyTransport;

    @Column(name="petTransport", nullable = false)
    private boolean petTransport;
    @Column(name="vehicleType", nullable = false)
    private VehicleType vehicleType;


    public Ride(Integer id, LocalDateTime startTime, LocalDateTime endTime, Double totalCost, Driver driver, Set<Passenger> passengers, Set<Path> paths, Integer estimatedTimeInMinutes, Set<Message> reviews, String status, Set<Rejection> rejections, boolean panic, boolean babyTransport, boolean petTransport, VehicleType vehicleType) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalCost = totalCost;
        this.driver = driver;
        this.passengers = passengers;
        this.paths = paths;
        this.estimatedTimeInMinutes = estimatedTimeInMinutes;
        this.reviews = reviews;
        this.status = status;
        this.rejections = rejections;
        this.panic = panic;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
        this.vehicleType = vehicleType;
    }

    public void addPassenger(Passenger passenger){
        this.passengers.add(passenger);
    }

    public  void removePassenger(Passenger passenger){
        this.passengers.remove(passenger);
    }

    public void addReview(Message message){
        this.reviews.add(message);
    }

    public  void removeReview(Message message){
        this.reviews.remove(message);
    }
}

