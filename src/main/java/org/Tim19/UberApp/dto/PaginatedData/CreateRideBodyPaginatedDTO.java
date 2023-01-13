package org.Tim19.UberApp.dto.PaginatedData;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.Tim19.UberApp.model.Passenger;
import org.Tim19.UberApp.model.Path;
import org.Tim19.UberApp.model.Ride;
import org.Tim19.UberApp.model.VehicleType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class CreateRideBodyPaginatedDTO {
    private Set<Path> locations;
    private Set<Passenger> passengers = new HashSet<>();
    private VehicleType vehicleType;
    private boolean babyTransport;
    private boolean petTransport;

    public CreateRideBodyPaginatedDTO(Set<Path> locations, Set<Passenger> passengers, VehicleType vehicleType, boolean babyTransport, boolean petTransport) {
        this.locations = locations;
        this.passengers = passengers;
        this.vehicleType = vehicleType;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
    }

    public CreateRideBodyPaginatedDTO(Ride ride){
        this(ride.getLocations(), ride.getPassengers(), ride.getVehicleType(), ride.isBabyTransport(), ride.isPetTransport());
    }
    public List<Float> getCoordinates(){
        List<Float> coordinates = new ArrayList<>();
        for (Path p: this.locations){
            Float latitude1 = p.getDeparture().getLatitude();
            Float longitude1 = p.getDeparture().getLongitude();
            Float longitude2 = p.getDestination().getLongitude();
            Float latitude2 = p.getDestination().getLatitude();
            coordinates.add(longitude1);
            coordinates.add(longitude2);
            coordinates.add(latitude1);
            coordinates.add(latitude2);
        }
        return coordinates;
    }
}
