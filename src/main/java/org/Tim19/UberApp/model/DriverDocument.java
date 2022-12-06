package org.Tim19.UberApp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
public class DriverDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name = "documentImage", nullable = false)
    private String documentImage;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "driver_id")
    private Driver driver;


    public DriverDocument(String name, String documentImage, Driver driver) {
        super();
        this.name = name;
        this.documentImage = documentImage;
        this.driver = driver;
    }

}
