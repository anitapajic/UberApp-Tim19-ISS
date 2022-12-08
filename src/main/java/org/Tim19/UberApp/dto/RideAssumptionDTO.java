package org.Tim19.UberApp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.Tim19.UberApp.dto.PaginatedData.PathPaginatedDTO;
import org.Tim19.UberApp.model.VehicleType;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class RideAssumptionDTO {

    private Integer id;
    private VehicleType vehicleType;
    private Boolean babyTransport;
    private Boolean petTransport;
    private List<PathPaginatedDTO> locations;
    private String status;

    public RideAssumptionDTO(Integer id, VehicleType vehicleType, Boolean babyTransport, Boolean petTransport, List<PathPaginatedDTO> locations, String status) {
        this.id = id;
        this.vehicleType = vehicleType;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
        this.locations = locations;
        this.status = status;
    }
}
