package org.Tim19.UberApp.dto;

import lombok.Data;

import javax.persistence.criteria.CriteriaBuilder;

@Data
public class Vehicle2DTO {
    private Integer estimatedTimeInMinutes;
    private String vehicleType;
    private boolean babyTransport;
    private boolean petTransport;

    public Vehicle2DTO(Integer estimatedTimeInMinutes, String vehicleType, boolean babyTransport, boolean petTransport) {
        this.estimatedTimeInMinutes = estimatedTimeInMinutes;
        this.vehicleType = vehicleType;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
    }

    public Vehicle2DTO() {
    }
}
