package org.Tim19.UberApp.controller;

import org.Tim19.UberApp.dto.PaginatedData.UserPaginatedDTO;
import org.Tim19.UberApp.dto.ReviewDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/review")
public class ReviewController {


    @PostMapping(value = "/{rideId}/vehicle/{id}")
    public ResponseEntity<ReviewDTO> postVehicleReview(@PathVariable Integer id, @PathVariable Integer rideId, @RequestBody ReviewDTO reviewDTO){

        UserPaginatedDTO passenger = new UserPaginatedDTO(123, "user@example.com");
        ReviewDTO review = new ReviewDTO(123, reviewDTO.getRating(), reviewDTO.getComment(), passenger);

        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @GetMapping(value = "/vehicle/{id}")
    public ResponseEntity<Map<String, Object>> getVehicleReviews(@PathVariable Integer id){
        Map<String, Object> response = new HashMap<>();
        response.put("totalCount", 243);

        UserPaginatedDTO passenger = new UserPaginatedDTO(123, "user@example.com");
        ReviewDTO review = new ReviewDTO(123, 3, "The driver was driving too fast", passenger);

        Set<ReviewDTO> reviews = new HashSet<>();
        reviews.add(review);

        response.put("results", reviews);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping(value ="/{rideId}/driver/{id}" )
    public ResponseEntity<ReviewDTO> postDriverReview(@PathVariable Integer rideId, @PathVariable Integer id, @RequestBody ReviewDTO reviewDTO){

        UserPaginatedDTO passenger = new UserPaginatedDTO(123, "user@example.com");
        ReviewDTO review = new ReviewDTO(123, reviewDTO.getRating(), reviewDTO.getComment(), passenger);

        return new ResponseEntity<>(review, HttpStatus.OK);
    }
    @GetMapping(value = "/driver/{id}")
    public ResponseEntity<Map<String, Object>> getDriverReviews(){
        Map<String, Object> response = new HashMap<>();
        response.put("totalCount", 243);

        UserPaginatedDTO passenger = new UserPaginatedDTO(123, "user@example.com");
        ReviewDTO review = new ReviewDTO(123, 3, "The driver was driving too fast", passenger);

        Set<ReviewDTO> reviews = new HashSet<>();
        reviews.add(review);

        response.put("results", reviews);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping(value = "{rideId}")
    public ResponseEntity<Set<Object>> getRideReviews(){

        Set<Object> response = new HashSet<>();
        HashMap<String, Object> reviews = new HashMap<>();

        UserPaginatedDTO passenger = new UserPaginatedDTO(123, "user@example.com");
        ReviewDTO review = new ReviewDTO(123, 3, "The driver was driving too fast", passenger);

        reviews.put("vehicleReview", review);
        reviews.put("driverReview", review);

        response.add(reviews);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
