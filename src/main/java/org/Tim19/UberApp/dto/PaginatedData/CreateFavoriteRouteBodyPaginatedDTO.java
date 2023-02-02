package org.Tim19.UberApp.dto.PaginatedData;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.Tim19.UberApp.model.Passenger;
import org.Tim19.UberApp.model.Path;
import org.Tim19.UberApp.model.VehicleType;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
public class CreateFavoriteRouteBodyPaginatedDTO {
    private Integer id;
    private String favoriteName;
    private Set<Path> locations;
    private Set<Passenger> passengers = new HashSet<>();
    private VehicleType vehicleType;
    private Boolean babyTransport;
    private Boolean petTransport;

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

    @Override
    public String toString() {
        return "CreateFavoriteRouteBodyPaginatedDTO{" +
                "id=" + id +
                ", favoriteName='" + favoriteName + '\'' +
                ", locations=" + locations +
                ", passengers=" + passengers +
                ", vehicleType=" + vehicleType +
                ", babyTransport=" + babyTransport +
                ", petTransport=" + petTransport +
                '}';
    }
}
