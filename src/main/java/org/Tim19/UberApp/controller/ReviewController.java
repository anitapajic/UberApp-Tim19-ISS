package org.Tim19.UberApp.controller;

import org.Tim19.UberApp.dto.PaginatedData.UserPaginatedDTO;
import org.Tim19.UberApp.dto.ReviewDTO;
import org.Tim19.UberApp.model.Review;
import org.Tim19.UberApp.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/api/review")
@CrossOrigin(value = "*")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping(value = "/{rideId}/vehicle/{id}")
    public ResponseEntity<ReviewDTO> postVehicleReview(@PathVariable Integer id, @PathVariable Integer rideId, @RequestBody ReviewDTO reviewDTO){

        reviewDTO.setVehicle(id);
        reviewDTO.setRide(rideId);

        reviewDTO = reviewService.saveVehicle(reviewDTO);
        return new ResponseEntity<>(reviewDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/vehicle/{id}")
    public ResponseEntity<Map<String, Object>> getVehicleReviews(@PathVariable Integer id){
        Set<Review> reviews = reviewService.findByVehicleId(id);


        //TODO: mapiraj review na reviewDTO
        Set<ReviewDTO> reviewDTOS = new HashSet<>();

        Map<String, Object> response = new HashMap<>();
        response.put("totalCount", reviews.size());
        response.put("results", reviews);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping(value ="/{rideId}/driver/{id}" )
    public ResponseEntity<ReviewDTO> postDriverReview(@PathVariable Integer rideId, @PathVariable Integer id, @RequestBody ReviewDTO reviewDTO){

        reviewDTO.setDriver(id);
        reviewDTO.setRide(rideId);

        reviewDTO = reviewService.saveDriver(reviewDTO);
        return new ResponseEntity<>(reviewDTO, HttpStatus.OK);
    }
    @GetMapping(value = "/driver/{id}")
    public ResponseEntity<Map<String, Object>> getDriverReviews(@PathVariable Integer id){
        Set<Review> reviews = reviewService.findByDriverId(id);


        //TODO: mapiraj review na reviewDTO
        Set<ReviewDTO> reviewDTOS = new HashSet<>();

        Map<String, Object> response = new HashMap<>();
        response.put("totalCount", reviews.size());
        response.put("results", reviews);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping(value = "{rideId}")
    public ResponseEntity<Map<String, Object>> getRideReviews(@PathVariable Integer rideId){
        Set<Review> reviews = reviewService.findByRideId(rideId);


        //TODO: mapiraj review na reviewDTO
        Set<ReviewDTO> reviewDTOS = new HashSet<>();

        Map<String, Object> response = new HashMap<>();
        response.put("totalCount", reviews.size());
        response.put("results", reviews);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
