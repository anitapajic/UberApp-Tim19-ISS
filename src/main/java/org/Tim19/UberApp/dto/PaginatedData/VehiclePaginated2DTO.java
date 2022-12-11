package org.Tim19.UberApp.dto.PaginatedData;

import lombok.Data;
import org.Tim19.UberApp.model.VehicleType;

@Data
public class VehiclePaginated2DTO {
    private Integer id;
    private Integer driverId;
    private String model;
    private VehicleType vehicleType;
    private String licenseNumber;
    private LocationPaginatedDTO currentLocation;
    private Integer passengerSeats;
    private boolean babyTransport;
    private boolean petTransport;


    public VehiclePaginated2DTO(Integer id, Integer driver_id, String carModel, VehicleType vehicleType, String licenseNumber, LocationPaginatedDTO location, Integer passengerSeats, boolean babyTransport, boolean petTransport) {
        this.id = id;
        this.driverId = driver_id;
        this.model = carModel;
        this.vehicleType = vehicleType;
        this.licenseNumber = licenseNumber;
        this.currentLocation = location;
        this.passengerSeats = passengerSeats;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
    }

    public VehiclePaginated2DTO() {
    }
}
