package org.Tim19.UberApp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class WorkingHour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime startD;
    private LocalDateTime  endD;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
    private Driver driver;

    public WorkingHour(Integer id, LocalDateTime  startD, LocalDateTime  endD, Driver driver) {
        this.id = id;
        this.startD = startD;
        this.endD = endD;
        this.driver = driver;
    }
}
