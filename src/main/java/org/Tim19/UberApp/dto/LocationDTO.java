package org.Tim19.UberApp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.Tim19.UberApp.model.Location;

@Data
@NoArgsConstructor
public class LocationDTO {
    private Integer id;
    private String address;
    private Float latitude;
    private Float longitude;

    public LocationDTO(Integer id, String address, Float latitude, Float longitude) {
        this.id = id;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public LocationDTO(Location location) {
        this.id = location.getId();
        this.address = location.getAddress();
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
    }
}
