package org.Tim19.UberApp.dto.PaginatedData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.Tim19.UberApp.model.Passenger;
import org.Tim19.UberApp.model.Path;
import org.Tim19.UberApp.model.VehicleType;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateFavoriteRouteBodyPaginatedDTO {
    private Integer id;
    private String favoriteName;
    private Set<Path> locations;
    private Set<Passenger> passengers;
    private VehicleType vehicleType;
    private Boolean babyTransport;
    private Boolean petTransport;


}
