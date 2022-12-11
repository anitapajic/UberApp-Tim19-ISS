package org.Tim19.UberApp.dto.PaginatedData;

import lombok.Data;

@Data
public class VehiclePaginatedDTO {

    private Integer estimatedTimeInMinutes;
    private String vehicleType;
    private boolean babyTransport;
    private boolean petTransport;

    public VehiclePaginatedDTO(Integer estimatedTimeInMinutes, String vehicleType, boolean babyTransport, boolean petTransport) {
        this.estimatedTimeInMinutes = estimatedTimeInMinutes;
        this.vehicleType = vehicleType;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
    }

    public VehiclePaginatedDTO() {
    }
}
