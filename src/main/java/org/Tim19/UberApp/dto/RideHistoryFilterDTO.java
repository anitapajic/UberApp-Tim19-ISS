package org.Tim19.UberApp.dto;

import org.Tim19.UberApp.model.VehicleType;

import java.time.LocalDateTime;

public class RideHistoryFilterDTO {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer driverId;
    private VehicleType vehicleType;
    private String keyword;

    public RideHistoryFilterDTO(){

    }

    public RideHistoryFilterDTO(LocalDateTime startDate, LocalDateTime endDate, Integer driverId, VehicleType vehicleType, String keyword) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.driverId = driverId;
        this.vehicleType = vehicleType;
        this.keyword = keyword;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverInt) {
        this.driverId = driverInt;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String toString() {
        return "RideHistoryFilterDTO{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", driverId=" + driverId +
                ", vehicleType=" + vehicleType +
                ", keyword='" + keyword + '\'' +
                '}';
    }
}
