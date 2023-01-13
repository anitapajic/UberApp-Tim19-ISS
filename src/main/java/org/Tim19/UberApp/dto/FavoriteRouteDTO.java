package org.Tim19.UberApp.dto;

import org.Tim19.UberApp.model.FavoriteRoute;
import org.Tim19.UberApp.model.Passenger;
import org.Tim19.UberApp.model.Path;
import org.Tim19.UberApp.model.VehicleType;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class FavoriteRouteDTO {

    private Integer id;
    private String favoriteName;
    private LocalDateTime scheduledTime;
    private Set<Path> locations = new HashSet<>();
    private Set<Passenger> passengers = new HashSet<>();
    private VehicleType vehicleType;
    private Boolean babyTransport;
    private Boolean petTransport;

    public FavoriteRouteDTO(Integer id, String favoriteName, LocalDateTime scheduledTime, Set<Path> locations, Set<Passenger> passengers, VehicleType vehicleType, Boolean babyTransport, Boolean petTransport) {
        this.id = id;
        this.favoriteName = favoriteName;
        this.scheduledTime = scheduledTime;
        this.locations = locations;
        this.passengers = passengers;
        this.vehicleType = vehicleType;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
    }

    public FavoriteRouteDTO(){}

    public FavoriteRouteDTO(FavoriteRoute favRoute){
        this(favRoute.getId(), favRoute.getFavoriteName(), favRoute.getScheduledTime(), favRoute.getLocations(), favRoute.getPassengers(), favRoute.getVehicleType(), favRoute.getBabyTransport(), favRoute.getPetTransport());
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
