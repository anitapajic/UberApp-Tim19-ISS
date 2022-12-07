package org.Tim19.UberApp.controller;


import org.Tim19.UberApp.dto.PassengerDTO;
import org.Tim19.UberApp.dto.RideDTO;
import org.Tim19.UberApp.model.Driver;
import org.Tim19.UberApp.model.Passenger;
import org.Tim19.UberApp.model.Ride;
import org.Tim19.UberApp.model.Vehicle;
import org.Tim19.UberApp.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/passenger")
public class PassengerController {

    @Autowired
    private PassengerService passengerService;


    //CREATE PASSENGER  /api/passenger
    @PostMapping(consumes = "application/json")
    public ResponseEntity<PassengerDTO> createPassenger(@RequestBody PassengerDTO passengerDTO) {

        Passenger passenger = new Passenger();

        passenger.setActive(false);
        passenger.setBlocked(false);
        passenger.setProfilePicture(passengerDTO.getProfilePicture());
        passenger.setTelephoneNumber(passengerDTO.getTelephoneNumber());
        passenger.setAddress(passengerDTO.getAddress());
        passenger.setEmail(passengerDTO.getEmail());
        passenger.setFirstname(passengerDTO.getFirstname());
        passenger.setLastname(passengerDTO.getLastname());
        passenger.setPassword(passengerDTO.getPassword());

        passenger = passengerService.save(passenger);
        return new ResponseEntity<>(new PassengerDTO(passenger), HttpStatus.CREATED);
    }

    //GETTING PASSENGERS /api/passenger
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllPassengers(@RequestParam(defaultValue = "0") Integer page,
                                                           @RequestParam(defaultValue = "4") Integer size) {

        Pageable paging = PageRequest.of(page, size);
        Page<Passenger> pagedResult = passengerService.findAll(paging);

        Map<String, Object> response = new HashMap<>();
        response.put("totalcounts", pagedResult.getTotalElements());
        response.put("results", pagedResult.getContent());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //ACTIVATE PASSENGER ACCOUNT  /api/passenger/activate/activationId
    @GetMapping(value = "/activate/{activationId}")
    public ResponseEntity<PassengerDTO> activatePassengerAccount(@PathVariable Integer id) {

        Passenger passenger = passengerService.findOne(id);

        // passenger must exist
        if (passenger == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        passenger.setActive(true);

        return new ResponseEntity<>(new PassengerDTO(passenger), HttpStatus.OK);
    }

    //PASSENGER DETAILS  /api/passenger/{id}
    @GetMapping(value = "/{id}")
    public ResponseEntity<PassengerDTO> getPassenger(@PathVariable Integer id) {

        Passenger passenger = passengerService.findOne(id);

        // passenger must exist
        if (passenger == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new PassengerDTO(passenger), HttpStatus.OK);
    }

    //UPDATE EXISTING PASSENGER /api/passenger/{id}
    @PutMapping(consumes = "application/json")
    public ResponseEntity<PassengerDTO> updatePassenger(@RequestBody PassengerDTO passengerDTO) {

        // a passenger must exist
        Passenger passenger = passengerService.findOne(passengerDTO.getId());

        if (passenger == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        passenger.setActive(false);
        passenger.setBlocked(false);
        passenger.setProfilePicture(passengerDTO.getProfilePicture());
        passenger.setTelephoneNumber(passengerDTO.getTelephoneNumber());
        passenger.setAddress(passengerDTO.getAddress());
        passenger.setEmail(passengerDTO.getEmail());
        passenger.setFirstname(passengerDTO.getFirstname());
        passenger.setLastname(passengerDTO.getLastname());
        passenger.setPassword(passengerDTO.getPassword());

        passenger = passengerService.save(passenger);
        return new ResponseEntity<>(new PassengerDTO(passenger), HttpStatus.OK);
    }

    //PASSENGER RIDES  /api/passenger/{id}/ride
    @GetMapping(value="/{id}/ride")
    public ResponseEntity<Map<String, Object>> getAllRides(@PathVariable Integer id, Pageable page,
                                                           @RequestParam(required = false) Date from,
                                                           @RequestParam(required = false) Date to){
        RideDTO rideDTO1 = new RideDTO();



        return new ResponseEntity<>(HttpStatus.CREATED);

    }

}
