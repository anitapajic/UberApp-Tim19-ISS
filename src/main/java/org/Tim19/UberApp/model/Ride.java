package org.Tim19.UberApp.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="startTime", nullable = false)
    private LocalDateTime startTime;

    @Column(name="endTime", nullable = true)
    private LocalDateTime endTime;

    @Column(name="totalCost", nullable = false)
    private Double totalCost;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "passenger_ride",
            joinColumns = @JoinColumn(name = "ride_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "passenger_id", referencedColumnName = "id")
    )
    private Set<Passenger> passengers = new HashSet<>();

    @OneToMany(cascade ={CascadeType.ALL},
            fetch = FetchType.EAGER)
    @JoinTable(name = "ride_paths",
            joinColumns = @JoinColumn(name = "ride_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "paths_id", referencedColumnName = "id"))
    private Set<Path> locations = new HashSet<>();

    @Column(name="estimatedTimeInMinutes", nullable = false)
    private Integer estimatedTimeInMinutes;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "ride")
    private Set<Review> reviews = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "ride")
    private Set<Rejection> rejection = new HashSet<>();

    @Column(name="status", nullable = false)
    private String status;

    @Column(name="panic", nullable = false)
    private boolean panic;
    @Column(name="babyTransport", nullable = false)
    private boolean babyTransport;

    @Column(name="petTransport", nullable = false)
    private boolean petTransport;
    @Column(name="vehicleType", nullable = true)
    private VehicleType vehicleType;


        public Ride(Integer id, LocalDateTime startTime, LocalDateTime endTime, Double totalCost, Driver driver, Integer estimatedTimeInMinutes, String status, boolean panic, boolean babyTransport, boolean petTransport, VehicleType vehicleType) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalCost = totalCost;
        this.driver = driver;
        this.estimatedTimeInMinutes = estimatedTimeInMinutes;
        this.status = status;
        this.panic = panic;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
        this.vehicleType = vehicleType;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Set<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(Set<Passenger> passengers) {
        this.passengers = passengers;
    }

    public Set<Path> getLocations() {
        return locations;
    }

    public void setLocations(Set<Path> paths) {
        this.locations = paths;
    }

    public Integer getEstimatedTimeInMinutes() {
        return estimatedTimeInMinutes;
    }

    public void setEstimatedTimeInMinutes(Integer estimatedTimeInMinutes) {
        this.estimatedTimeInMinutes = estimatedTimeInMinutes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isPanic() {
        return panic;
    }

    public void setPanic(boolean panic) {
        this.panic = panic;
    }

    public boolean isBabyTransport() {
        return babyTransport;
    }

    public void setBabyTransport(boolean babyTransport) {
        this.babyTransport = babyTransport;
    }

    public boolean isPetTransport() {
        return petTransport;
    }

    public void setPetTransport(boolean petTransport) {
        this.petTransport = petTransport;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public Set<Rejection> getRejection() {
        return rejection;
    }

    public void setRejection(Set<Rejection> rejection) {
        this.rejection = rejection;
    }

    public void addPassenger(Passenger passenger){
        this.passengers.add(passenger);
    }

    public  void removePassenger(Passenger passenger){
        this.passengers.remove(passenger);
    }

    public void addReview(Review review){
        this.reviews.add(review);
    }

    public void addReviews(Set<Review> reviews){
        this.reviews.addAll(reviews);
    }

    public  void removeReview(Review review){
        this.reviews.remove(review);
    }


    public void addRejection(Rejection rejection){
        this.rejection.add(rejection);
    }

    public void addRejections(Set<Rejection> rejections){
        this.rejection.addAll(rejections);
    }

    public  void removeRejection(Rejection rejection){
        this.rejection.remove(rejection);
    }
}

