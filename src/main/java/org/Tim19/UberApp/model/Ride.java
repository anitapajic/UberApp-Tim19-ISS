package org.Tim19.UberApp.model;

import lombok.NoArgsConstructor;
import org.Tim19.UberApp.dto.RideDTO;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Entity
@NoArgsConstructor
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="startTime")
    private LocalDateTime startTime;

    @Column(name="endTime")
    private LocalDateTime endTime;

    @Column(name="totalCost", nullable = true)
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

    @Column(name="estimatedTimeInMinutes", nullable = true)
    private Integer estimatedTimeInMinutes;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "ride")
    private Set<Review> reviews = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "ride")
    private Set<Rejection> rejection = new HashSet<>();

    @Column(name="status", nullable = true)
    private String status;

    @Column(name="panic", nullable = true)
    private boolean panic;
    @Column(name="babyTransport", nullable = false)
    private boolean babyTransport;

    @Column(name="petTransport", nullable = false)
    private boolean petTransport;
    @Column(name="vehicleType", nullable = true)
    private VehicleType vehicleType;

    @Column(name = "json", columnDefinition = "text")
    @Lob
    private String routeJSON;

    @Column(name = "step")
    private Integer step;

    public Ride(Integer id, LocalDateTime startTime, LocalDateTime endTime, Double totalCost, Driver driver, Set<Passenger> passengers, Set<Path> locations, Integer estimatedTimeInMinutes, Set<Review> reviews, Set<Rejection> rejection, String status, boolean panic, boolean babyTransport, boolean petTransport, VehicleType vehicleType, String routeJSON, Integer step) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalCost = totalCost;
        this.driver = driver;
        this.passengers = passengers;
        this.locations = locations;
        this.estimatedTimeInMinutes = estimatedTimeInMinutes;
        this.reviews = reviews;
        this.rejection = rejection;
        this.status = status;
        this.panic = panic;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
        this.vehicleType = vehicleType;
        this.routeJSON = routeJSON;
        this.step = step;
    }

    public Ride(RideDTO rideDTO){
        this.locations = rideDTO.getLocations();
        this.babyTransport = rideDTO.isBabyTransport();
        this.petTransport = rideDTO.isPetTransport();
        this.routeJSON = rideDTO.getRouteJSON();
        this.step = rideDTO.getStep();
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


    public Location getDeparture(){
        Location departure = new Location();
        for (Path p: this.locations){
            departure = p.getDeparture();
        }

        return departure;
    }

    public Location getDestination(){
        Location destination = new Location();
        for (Path p: this.locations){
            destination = p.getDestination();
        }

        return destination;
    }

    public String getRouteJSON() {
        return routeJSON;
    }

    public void setRouteJSON(String routeJSON) {
        this.routeJSON = routeJSON;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    @Override
    public String toString() {
        return "Ride{" +
                "id=" + id +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", totalCost=" + totalCost +
                ", driver=" + driver.getName() + " " + driver.getSurname() +
                ", locations=" + locations +
                ", estimatedTimeInMinutes=" + estimatedTimeInMinutes +
                ", reviews=" + reviews +
                ", rejection=" + rejection +
                ", status='" + status + '\'' +
                ", panic=" + panic +
                ", babyTransport=" + babyTransport +
                ", petTransport=" + petTransport +
                ", vehicleType=" + vehicleType +
                '}';
    }


}

