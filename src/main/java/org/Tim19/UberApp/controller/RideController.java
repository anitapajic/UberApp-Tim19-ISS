package org.Tim19.UberApp.controller;

import org.Tim19.UberApp.dto.RideDTO;
import org.Tim19.UberApp.dto.UserDTO;
import org.Tim19.UberApp.model.Ride;
import org.Tim19.UberApp.model.Users;
import org.Tim19.UberApp.service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/api/ride")
public class RideController {

    @Autowired
    private RideService rideService;

    @GetMapping
    public ResponseEntity<List<RideDTO>> getAllRides() {

        List<Ride> rides = rideService.findAll();

        // convert users to DTOs
        List<RideDTO> rideDto = new ArrayList<>();
        for (Ride u : rides) {
            rideDto.add(new RideDTO(u));
        }

        return new ResponseEntity<>(rideDto, HttpStatus.OK);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<RideDTO> getRide(@PathVariable Integer id) {

        Ride ride = rideService.findOne(id);

        // ride must exist
        if (ride == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new RideDTO(ride), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<RideDTO> saveRide(@RequestBody RideDTO rideDTO) {

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

    @PutMapping(consumes = "application/json")
    public ResponseEntity<RideDTO> updateUser(@RequestBody RideDTO rideDTO) {

        // a ride must exist
        Ride ride = rideService.findOne(rideDTO.getId());

        if (ride == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        ride.setBabyTransport(rideDTO.isBabyTransport());
        ride.setEndTime(rideDTO.getEndTime());
        ride.setStatus(rideDTO.getStatus());
        ride.setPetTransport(rideDTO.isPetTransport());
        ride.setStartTime(rideDTO.getStartTime());
        ride.setTotalCost(rideDTO.getTotalCost());
        ride.setVehicleType(rideDTO.getVehicleType());
        ride.setEstimatedTimeInMinutes(rideDTO.getEstimatedTimeInMinutes());


        ride = rideService.save(ride);
        return new ResponseEntity<>(new RideDTO(ride), HttpStatus.OK);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {

        Ride ride = rideService.findOne(id);

        if (ride != null) {
            rideService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
