package org.Tim19.UberApp.service;


import org.Tim19.UberApp.controller.ReviewController;
import org.Tim19.UberApp.dto.ReviewDTO;
import org.Tim19.UberApp.model.*;
import org.Tim19.UberApp.repository.ReviewRepository;
import org.Tim19.UberApp.repository.RideRepository;
import org.Tim19.UberApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RideRepository rideRepository;

    public Set<Review> findByVehicleId(Integer id){return reviewRepository.findAllByVehicleId(id);}
    public Set<Review> findByDriverId(Integer id){return reviewRepository.findAllByDriverId(id);}
    public Set<Review> findByRideId(Integer id){return reviewRepository.findAllByRideId(id);}

    public ReviewDTO saveDriver(ReviewDTO reviewDTO){
        Review review;
        Optional<Review> r = reviewRepository.findOneByRideId(reviewDTO.getRide());

        if(r.isEmpty()){
            review = new Review();
            Driver driver = (Driver) userRepository.findOneById(reviewDTO.getDriver());
            Ride ride = rideRepository.findOneById(reviewDTO.getRide());
            List<Passenger> passengers = ride.getPassengers().stream().toList();
            User user = userRepository.findOneById(passengers.get(0).getId());
            review.setDriver(driver);
            review.setRide(ride);
            review.setUser(user);
            review.setComment(reviewDTO.getComment());
        }
        else {
            review = r.get();
        }
        review.setRating(reviewDTO.getRating());
        review = reviewRepository.save(review);

        return new ReviewDTO(review);
    }
    public ReviewDTO saveVehicle(ReviewDTO reviewDTO){
        Driver driver = (Driver) userRepository.findOneByVehicleId(reviewDTO.getVehicle());
        Ride ride = rideRepository.findOneById(reviewDTO.getRide());
        List<Passenger> passengers = ride.getPassengers().stream().toList();
        User user = userRepository.findOneById(passengers.get(0).getId());

        Review review = new Review();
        review.setDriver(driver);
        review.setRide(ride);
        review.setUser(user);
        review.setComment(reviewDTO.getComment());
        review.setRating(reviewDTO.getRating());
        review = reviewRepository.save(review);

        return new ReviewDTO(review);
    }
}
