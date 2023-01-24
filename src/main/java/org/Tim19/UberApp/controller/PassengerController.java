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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
            return new ResponseEntity<>("User with that username already exists!", HttpStatus.BAD_REQUEST);
        }

        String name = passengerDTO.getName();

        if(name == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(name.length()>80){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        passenger.setPassword(passwordEncoder.encode(passengerDTO.getPassword()));
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
    @PreAuthorize("hasAnyAuthority('ADMIN')")
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


        try{
            Passenger passenger = passengerService.findOne(activationId);
            Activation activation = activationService.findOne(activationId);

            passenger.setActive(true);

            passengerService.save(passenger);
            activationService.save(activation);

            if(activation.getExpirationDate().isBefore(LocalDateTime.now())){
                return new ResponseEntity<>("Activation expired. Register again!", HttpStatus.BAD_REQUEST);
            }

            passenger.setActive(true);

            passengerService.save(passenger);

            return new ResponseEntity<>("Successful account activation!", HttpStatus.OK);
        }
        catch (NullPointerException ex){
            return new ResponseEntity<>("Activation with entered id does not exist!", HttpStatus.NOT_FOUND);
        }
    }


    //PASSENGER DETAILS  /api/passenger/{id}
    @PreAuthorize("hasAnyAuthority('PASSENGER', 'ADMIN', 'DRIVER')")
    @GetMapping(value = "/{id}")
    public ResponseEntity getPassenger(@PathVariable Integer id) {

        try{
            Passenger passenger = passengerService.findOne(id);
            return new ResponseEntity<>(new PassengerDTO(passenger), HttpStatus.OK);
        }
        catch (NullPointerException ex){
            return new ResponseEntity<>("Passenger does not exist!", HttpStatus.NOT_FOUND);
        }

    }

    //UPDATE EXISTING PASSENGER /api/passenger/{id}
    @PreAuthorize("hasAnyAuthority('PASSENGER')")
    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity updatePassenger(@PathVariable Integer id, @RequestBody PassengerDTO passengerDTO) {

        try{
            Passenger passenger = passengerService.findOne(id);

            String name = passengerDTO.getName();

            if(name == null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if(name.length()>80){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }


            passenger.setActive(true);
            passenger.setBlocked(false);
            passenger.setTelephoneNumber(passengerDTO.getTelephoneNumber());
            passenger.setAddress(passengerDTO.getAddress());
            passenger.setUsername(passengerDTO.getUsername());
            passenger.setName(passengerDTO.getName());
            passenger.setSurname(passengerDTO.getSurname());


            //TODO: odvojen kontroler za update slike
            //za promenu sifre v postoji u User contolleru
            passenger.setPassword(passenger.getPassword());
            passenger.setProfilePicture(passenger.getProfilePicture());

            passengerService.save(passenger);
            return new ResponseEntity<>(new PassengerDTO(passenger), HttpStatus.OK);
        }
        catch (NullPointerException ex){
            return new ResponseEntity<>("Passenger does not exist!", HttpStatus.NOT_FOUND);
        }


    }

    //PASSENGER RIDES  /api/passenger/{id}/ride
    @PreAuthorize("hasAnyAuthority('PASSENGER', 'ADMIN')")
    @GetMapping(value = "/{id}/ride")
    public ResponseEntity getAllRides(@PathVariable Integer id,
                                                           @RequestParam(defaultValue = "0") Integer page,
                                                           @RequestParam(defaultValue = "4") Integer size,
                                                           @RequestParam(required = false) String sort,
                                                           @RequestParam(required = false) String  startDate,
                                                           @RequestParam(required = false) String  endDate){

        try{
            Passenger passenger = passengerService.findOne(id);

            Pageable paging = PageRequest.of(page, size);
            Page<Ride> allRides = rideService.findByPassengerId(id, startDate, endDate, paging);

            Map<String, Object> response = new HashMap<>();
            response.put("totalCount", allRides.getTotalElements());
            response.put("results", allRides.getContent());

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (NullPointerException ex){
            return new ResponseEntity<>("Passenger does not exist!", HttpStatus.NOT_FOUND);
        }
    }

}
