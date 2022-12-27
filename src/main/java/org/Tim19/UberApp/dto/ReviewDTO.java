package org.Tim19.UberApp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.Tim19.UberApp.dto.PaginatedData.UserPaginatedDTO;
import org.Tim19.UberApp.model.Driver;
import org.Tim19.UberApp.model.Review;


@NoArgsConstructor
public class ReviewDTO {
    private Integer id;
    private Integer rating;
    private String comment;
    private UserPaginatedDTO passenger;
    private Integer driver;
    private Integer vehicle;
    private Integer ride;

    public ReviewDTO(Integer id, Integer rating, String comment, UserPaginatedDTO passenger, Integer driver, Integer ride, Integer vehicle) {
        this.id = id;
        this.rating = rating;
        this.comment = comment;
        this.passenger = passenger;
        this.driver = driver;
        this.ride = ride;
        this.vehicle = vehicle;
    }

    public ReviewDTO(Review review){
        this.id = review.getId();
        this.rating = review.getRating();
        this.comment = review.getComment();
        this.passenger = new UserPaginatedDTO(review.getUser().getId(), review.getUser().getEmail());
        this.driver = review.getDriver().getId();
        this.ride = review.getRide().getId();
        this.vehicle = review.getDriver().getVehicle().getId();
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public UserPaginatedDTO getPassenger() {
        return passenger;
    }

    public void setPassenger(UserPaginatedDTO passenger) {
        this.passenger = passenger;
    }

    public Integer getDriver() {
        return driver;
    }

    public void setDriver(Integer driver) {
        this.driver = driver;
    }

    public Integer getRide() {
        return ride;
    }

    public void setRide(Integer ride) {
        this.ride = ride;
    }

    public Integer getVehicle() {
        return vehicle;
    }

    public void setVehicle(Integer vehicle) {
        this.vehicle = vehicle;
    }
}
