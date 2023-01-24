package org.Tim19.UberApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Path {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="departure_id")
    private Location departure;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="destination_id")
    private Location destination;

    public Path(Integer id, Location departure, Location destination) {
        this.id = id;
        this.departure = departure;
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "Path{" +
                "id=" + id +
                ", departure=" + departure +
                ", destination=" + destination +
                '}';
    }
}
