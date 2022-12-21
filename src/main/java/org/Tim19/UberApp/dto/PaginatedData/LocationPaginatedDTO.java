package org.Tim19.UberApp.dto.PaginatedData;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.Tim19.UberApp.model.Location;

@Data
@NoArgsConstructor
public class LocationPaginatedDTO {

    private String address;
    private Double latitude;
    private Double longitude;

    public LocationPaginatedDTO(String address, Double latitude, Double longitude) {
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public LocationPaginatedDTO(Location location){
        this.address = location.getAddress();
        this.latitude = Double.valueOf(location.getLatitude());
        this.longitude = Double.valueOf(location.getLongitude());
    }

}
