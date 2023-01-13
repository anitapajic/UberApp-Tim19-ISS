package org.Tim19.UberApp.model;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Entity(name="favoriteRoute")
public class FavoriteRoute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="favoriteName", nullable = false)
    private String favoriteName;

    @Column(name="scheduledTime", nullable = true)
    private LocalDateTime  scheduledTime;

    @OneToMany(cascade ={CascadeType.ALL},
            fetch = FetchType.EAGER)
    @JoinTable(name = "favorites_paths",
            joinColumns = @JoinColumn(name = "favorite_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "paths_id", referencedColumnName = "id"))

    private Set<Path> locations = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "passenger_favorites",
            joinColumns = @JoinColumn(name = "favorite_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "passenger_id", referencedColumnName = "id")
    )
    private Set<Passenger> passengers = new HashSet<>();

    @Column(name="vehicleType", nullable = false)
    private VehicleType vehicleType;

    @Column(name="babyTransport", nullable = false)
    private Boolean babyTransport;

    @Column(name="petTransport", nullable = false)
    private Boolean petTransport;

    public FavoriteRoute(Integer id, String favoriteName, LocalDateTime scheduledTime, Set<Path> locations, Set<Passenger> passengers, VehicleType vehicleType, Boolean babyTransport, Boolean petTransport) {
        this.id = id;
        this.favoriteName = favoriteName;
        this.scheduledTime = scheduledTime;
        this.locations = locations;
        this.passengers = passengers;
        this.vehicleType = vehicleType;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
    }
    public void addPassenger(Passenger passenger){
        this.passengers.add(passenger);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFavoriteName() {
        return favoriteName;
    }

    public void setFavoriteName(String favoriteName) {
        this.favoriteName = favoriteName;
    }

    public LocalDateTime getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(LocalDateTime scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    public Set<Path> getLocations() {
        return locations;
    }

    public void setLocations(Set<Path> locations) {
        this.locations = locations;
    }

    public Set<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(Set<Passenger> passengers) {
        this.passengers = passengers;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Boolean getBabyTransport() {
        return babyTransport;
    }

    public void setBabyTransport(Boolean babyTransport) {
        this.babyTransport = babyTransport;
    }

    public Boolean getPetTransport() {
        return petTransport;
    }

    public void setPetTransport(Boolean petTransport) {
        this.petTransport = petTransport;
    }
}
