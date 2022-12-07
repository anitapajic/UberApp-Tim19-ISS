package org.Tim19.UberApp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.Tim19.UberApp.model.Location;
import org.Tim19.UberApp.model.Path;

@Data
@NoArgsConstructor
public class PathDTO {
    private Integer id;
    private Location departure;
    private Location destination;

    public PathDTO(Integer id, Location departure, Location destination) {
        this.id = id;
        this.departure = departure;
        this.destination = destination;
    }

    public PathDTO(Path path){
        this(path.getId(), path.getDeparture(), path.getDestination());
    }

}
