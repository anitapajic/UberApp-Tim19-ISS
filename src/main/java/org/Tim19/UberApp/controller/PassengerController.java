package org.Tim19.UberApp.controller;


import org.Tim19.UberApp.dto.PassengerDTO;
import org.Tim19.UberApp.model.Activation;
import org.Tim19.UberApp.model.Passenger;
import org.Tim19.UberApp.model.Ride;
import org.Tim19.UberApp.service.ActivationService;
import org.Tim19.UberApp.service.PassengerService;
import org.Tim19.UberApp.service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/passenger")
@CrossOrigin(value="*")
public class PassengerController {

    @Autowired
    private PassengerService passengerService;
    @Autowired
    private RideService rideService;
    @Autowired
    private ActivationService activationService;

    //CREATE PASSENGER  /api/passenger
    @PostMapping(consumes = "application/json")
    public ResponseEntity createPassenger(@RequestBody PassengerDTO passengerDTO) throws MessagingException, UnsupportedEncodingException {



        // if passenger already exist
        if (passengerService.findByEmail(passengerDTO.getUsername()) != null) {
            return new ResponseEntity<>("User with that email already exists!",HttpStatus.BAD_REQUEST);
        }

        Passenger passenger = new Passenger();

        passenger.setActive(false);
        passenger.setBlocked(false);
        passenger.setProfilePicture(passengerDTO.getProfilePicture());
        passenger.setTelephoneNumber(passengerDTO.getTelephoneNumber());
        passenger.setAddress(passengerDTO.getAddress());
        passenger.setUsername(passengerDTO.getUsername());
        passenger.setName(passengerDTO.getName());
        passenger.setSurname(passengerDTO.getSurname());
        passenger.setPassword(passengerDTO.getPassword());
        passenger.setAuthorities("PASSENGER");

        passenger = passengerService.save(passenger);

        Activation activation = new Activation();
        activation.setId(passenger.getId());
        activation.setUser(passenger);
        activation.setCreationDate(LocalDateTime.now());
        activation.setExpirationDate(LocalDateTime.now().plusYears(5));

        activationService.save(activation);

        this.passengerService.sendMail(passenger.getId());
        return new ResponseEntity<>(new PassengerDTO(passenger), HttpStatus.CREATED);
    }

    //GETTING PASSENGERS /api/passenger
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllPassengers(@RequestParam(defaultValue = "0") Integer page,
                                                                @RequestParam(defaultValue = "4") Integer size) {

        Pageable paging = PageRequest.of(page, size);
        Page<Passenger> pagedResult = passengerService.findAll(paging);

        Map<String, Object> response = new HashMap<>();
        response.put("totalCount", pagedResult.getTotalElements());
        response.put("results", pagedResult.getContent());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //ACTIVATE PASSENGER ACCOUNT  /api/passenger/activate/activationId
    @GetMapping(value = "/activate/{activationId}")
    public ResponseEntity activatePassengerAccount(@PathVariable Integer activationId) {

        Passenger passenger = passengerService.findOne(activationId);

        // passenger must exist
        if (passenger == null) {
            return new ResponseEntity<>("Activation with entered id does not exist!", HttpStatus.NOT_FOUND);
        }

        Activation activation = activationService.findOne(activationId);
        if(activation.getExpirationDate().isBefore(LocalDateTime.now())){
            return new ResponseEntity<>("Activation expired. Register again!", HttpStatus.BAD_REQUEST);
        }

        passenger.setActive(true);

        passengerService.save(passenger);

        return new ResponseEntity<>("Successful account activation!", HttpStatus.FOUND);
    }


    //PASSENGER DETAILS  /api/passenger/{id}
    @GetMapping(value = "/{id}")
    public ResponseEntity getPassenger(@PathVariable Integer id) {

        Passenger passenger = passengerService.findOne(id);

        // passenger must exist
        if (passenger == null) {
            return new ResponseEntity<>("Passenger does not exist!", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new PassengerDTO(passenger), HttpStatus.OK);
    }

    //UPDATE EXISTING PASSENGER /api/passenger/{id}
    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity updatePassenger(@PathVariable Integer id, @RequestBody PassengerDTO passengerDTO) {

        // a passenger must exist
        Passenger passenger = passengerService.findOne(id);

        if (passenger == null) {
            return new ResponseEntity<>("Passenger does not exist!", HttpStatus.NOT_FOUND);
        }

        passenger.setActive(true);
        passenger.setBlocked(false);
        passenger.setProfilePicture(passengerDTO.getProfilePicture());
        passenger.setTelephoneNumber(passengerDTO.getTelephoneNumber());
        passenger.setAddress(passengerDTO.getAddress());
        passenger.setUsername(passengerDTO.getUsername());
        passenger.setName(passengerDTO.getName());
        passenger.setSurname(passengerDTO.getSurname());
        passenger.setPassword(passengerDTO.getPassword());

        //passengerService.save(passenger);
        return new ResponseEntity<>(new PassengerDTO(passenger), HttpStatus.OK);
    }

    //PASSENGER RIDES  /api/passenger/{id}/ride
    @GetMapping(value = "/{id}/ride")
    public ResponseEntity getAllRides(@PathVariable Integer id,
                                                           @RequestParam(defaultValue = "0") Integer page,
                                                           @RequestParam(defaultValue = "4") Integer size,
                                                           @RequestParam(required = false) String sort,
                                                           @RequestParam(required = false) String  from,
                                                           @RequestParam(required = false) String  to){

        Passenger passenger = passengerService.findOne(id);

        if (passenger == null) {
            return new ResponseEntity<>("Passenger does not exist!", HttpStatus.NOT_FOUND);
        }

        Pageable paging = PageRequest.of(page, size);
        Page<Ride> allRides = rideService.findByPassengerId(id, paging);

        Map<String, Object> response = new HashMap<>();
        response.put("totalCount", allRides.getTotalElements());
        response.put("results", allRides.getContent());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
