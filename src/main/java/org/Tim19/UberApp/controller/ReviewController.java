package org.Tim19.UberApp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/review")
public class ReviewController {


    @PostMapping(value = "/{rideId}/vehicle/{id}")
    public ResponseEntity<Void> postVehicleReview(){



        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/vehicle/{id}")
    public ResponseEntity<Void> getVehicleReviews(){



        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping(value ="/{rideId}/driver/{id}" )
    public ResponseEntity<Void> postDriverReview(){



        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping(value = "/driver/{id}")
    public ResponseEntity<Void> getDriverReviews(){



        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping(value = "{rideId}")
    public ResponseEntity<Void> getRideReviews(){



        return new ResponseEntity<>(HttpStatus.OK);
    }

}
