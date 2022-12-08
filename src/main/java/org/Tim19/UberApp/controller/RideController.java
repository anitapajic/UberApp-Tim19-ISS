package org.Tim19.UberApp.controller;

import org.Tim19.UberApp.dto.PaginatedData.*;
import org.Tim19.UberApp.dto.RideDTO;
import org.Tim19.UberApp.dto.UserDTO;
import org.Tim19.UberApp.model.Driver;
import org.Tim19.UberApp.model.Message;
import org.Tim19.UberApp.model.Ride;
import org.Tim19.UberApp.model.VehicleType;
import org.Tim19.UberApp.service.DriverService;
import org.Tim19.UberApp.service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/api/ride")
public class RideController {

    @Autowired
    private RideService rideService;
    @Autowired
    private DriverService driverService;

    //CREATING A RIDE  /api/ride
    @PostMapping(consumes = "application/json")
    public ResponseEntity<RidePaginatedDTO> createRide(@RequestBody RidePaginatedDTO rideDTO) {

//        Ride ride = new Ride();
//        Driver driver = driverService.findOne(1);
//
//        ride.setBabyTransport(rideDTO.isBabyTransport());
//        ride.setEndTime(rideDTO.getEndTime());
//        ride.setStatus(rideDTO.getStatus());
//        ride.setPetTransport(rideDTO.isPetTransport());
//        ride.setStartTime(rideDTO.getStartTime());
//        ride.setTotalCost(rideDTO.getTotalCost());
//        ride.setVehicleType(rideDTO.getVehicleType());
//        ride.setEstimatedTimeInMinutes(rideDTO.getEstimatedTimeInMinutes());
//        ride.setDriver(driver);
//        ride.setPanic(false);
//        ride.setPaths(rideDTO.getPaths());
//
//        ride = rideService.save(ride);
        RidePaginatedDTO ride = new RidePaginatedDTO();
        UserPaginatedDTO driver = new UserPaginatedDTO(100,"user@example.com");

        ride.setPassengers(rideDTO.getPassengers());
        ride.setRejection(ride.getRejection());
        ride.setId(123);
        ride.setBabyTransport(rideDTO.getBabyTransport());
        ride.setEndTime(rideDTO.getEndTime());
        ride.setStatus(rideDTO.getStatus());
        ride.setPetTransport(rideDTO.getPetTransport());
        ride.setStartTime(rideDTO.getStartTime());
        ride.setTotalCost(rideDTO.getTotalCost());
        ride.setVehicleType(rideDTO.getVehicleType());
        ride.setEstimatedTimeInMinutes(rideDTO.getEstimatedTimeInMinutes());
        ride.setDriver(driver);
        ride.setLocations(rideDTO.getLocations());
        return new ResponseEntity<>(ride, HttpStatus.CREATED);
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
    public ResponseEntity<RidePaginatedDTO> getRide(@PathVariable Integer id) {

//        Ride ride = rideService.findOne(id);
//
//        // ride must exist
//        if (ride == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
        List<UserPaginatedDTO> passengers = new ArrayList<>();
        passengers.add(new UserPaginatedDTO(id, "user@example.com"));
        List<PathPaginatedDTO> locations = new ArrayList<>();
        LocationPaginatedDTO departure = new LocationPaginatedDTO("Bulevar oslobodjenja 46", 45.267136, 19.833549);
        LocationPaginatedDTO destination = new LocationPaginatedDTO("Bulevar oslobodjenja 46", 45.267136, 19.833549);
        locations.add(new PathPaginatedDTO(departure, destination));
        RidePaginatedDTO ride = new RidePaginatedDTO(123, LocalDateTime.of(2022,12,7,20,15,26), LocalDateTime.of(2022,12,7,20,30,15),
                1235.00, new UserPaginatedDTO(12, "user@example.com"), passengers, 5, VehicleType.STANDARD, true, true, new RejectionPaginatedDTO("Ride is canceled due to previous problems with the passenger", LocalDateTime.of(2022,12,7,21,0,0)), locations, "PENDING");



        return new ResponseEntity<>(ride, HttpStatus.OK);
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
