package org.Tim19.UberApp.controller;

import org.Tim19.UberApp.dto.RideDTO;
import org.Tim19.UberApp.model.Ride;
import org.Tim19.UberApp.service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(value="/api/ride")
public class RideController {

    @Autowired
    private RideService rideService;

    //CREATING A RIDE  /api/ride
    @PostMapping(consumes = "application/json")
    public ResponseEntity<RideDTO> createRide(@RequestBody RideDTO rideDTO) {

        Ride ride = new Ride();

        ride.setBabyTransport(rideDTO.isBabyTransport());
        ride.setEndTime(rideDTO.getEndTime());
        ride.setStatus(rideDTO.getStatus());
        ride.setPetTransport(rideDTO.isPetTransport());
        ride.setStartTime(rideDTO.getStartTime());
        ride.setTotalCost(rideDTO.getTotalCost());
        ride.setVehicleType(rideDTO.getVehicleType());
        ride.setEstimatedTimeInMinutes(rideDTO.getEstimatedTimeInMinutes());

        ride = rideService.save(ride);
        return new ResponseEntity<>(new RideDTO(ride), HttpStatus.CREATED);
    }

    //ACTIVE RIDE FOR DRIVER  /api/ride/driver/{driverId}/active
    @GetMapping(value="/driver/{driverId}/active")
    public ResponseEntity<List<RideDTO>> activeRideForDriver() {



        return new ResponseEntity<>(HttpStatus.OK);
    }

    //ACTIVE RIDE FOR PASSENGER  /api/ride/passenger/{passengerId}/active
    @GetMapping(value="/passenger/{passengerId}/active")
    public ResponseEntity<List<RideDTO>> activeRideForPassenger() {



        return new ResponseEntity<>(HttpStatus.OK);
    }

    //RIDE DETAILS  api/ride/{id}
    @GetMapping(value = "/{id}")
    public ResponseEntity<RideDTO> getRide(@PathVariable Integer id) {

        Ride ride = rideService.findOne(id);

        // ride must exist
        if (ride == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new RideDTO(ride), HttpStatus.OK);
    }

    //CANCEL EXISTING RIDE  /api/ride/{id}/withdraw
    @PutMapping(value="/{id}/withdraw")
    public ResponseEntity<List<RideDTO>> cancelRide() {



        return new ResponseEntity<>(HttpStatus.OK);
    }

    //PANIC PROCEDURE FOR THE RIDE  /api/ride/{id}/panic
    @PutMapping(value="/{id}/panic")
    public ResponseEntity<List<RideDTO>> panicRide() {



        return new ResponseEntity<>(HttpStatus.OK);
    }

    //ACCEPT RIDE  /api/ride/{id}/accept
    @PutMapping(value="/{id}/accept")
    public ResponseEntity<List<RideDTO>> acceptRide() {



        return new ResponseEntity<>(HttpStatus.OK);
    }

    //END THE RIDE  /api/ride/{id}/end
    @PutMapping(value="/{id}/end")
    public ResponseEntity<List<RideDTO>> endRide() {



        return new ResponseEntity<>(HttpStatus.OK);
    }

    //CANCEL RIDE WITH AN EXPLANATION  /api/ride/{id}/cancel
    @PutMapping(value="/{id}/cancel")
    public ResponseEntity<List<RideDTO>> cancelRideWithExpl() {



        return new ResponseEntity<>(HttpStatus.OK);
    }

}
