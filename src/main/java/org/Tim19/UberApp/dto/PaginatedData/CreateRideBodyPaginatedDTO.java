package org.Tim19.UberApp.dto.PaginatedData;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.Tim19.UberApp.model.Passenger;
import org.Tim19.UberApp.model.Path;
import org.Tim19.UberApp.model.Ride;
import org.Tim19.UberApp.model.VehicleType;

import java.util.HashSet;
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
}
