package org.Tim19.UberApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class WorkingHours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime startD;
    private LocalDateTime  endD;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
    private Driver driver;

    public WorkingHours(Integer id, LocalDateTime  startD, LocalDateTime  endD, Driver driver) {
        this.id = id;
        this.startD = startD;
        this.endD = endD;
        this.driver = driver;
    }

    public Integer getId() {
        return id;
    }

    public LocalDateTime getStartD() {
        return startD;
    }

    public LocalDateTime getEndD() {
        return endD;
    }

    public Driver getDriver() {
        return driver;
    }
}
