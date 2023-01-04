package org.Tim19.UberApp.controller;


import org.Tim19.UberApp.dto.PaginatedData.*;
import org.Tim19.UberApp.dto.PassengerDTO;
import org.Tim19.UberApp.model.*;
import org.Tim19.UberApp.service.PassengerService;
import org.Tim19.UberApp.service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping(value = "/api/passenger")
@CrossOrigin(value="*")
public class PassengerController {

    @Autowired
    private PassengerService passengerService;
    @Autowired
    private RideService rideService;
    @Autowired
    private JavaMailSender mailSender;

    //CREATE PASSENGER  /api/passenger
    @PostMapping(consumes = "application/json")
    public ResponseEntity<PassengerDTO> createPassenger(@RequestBody PassengerDTO passengerDTO) throws MessagingException, UnsupportedEncodingException {

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
        sendMail(passenger.getId());
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
    public ResponseEntity<Void> activatePassengerAccount(@PathVariable Integer activationId) {

        Passenger passenger = passengerService.findOne(activationId);


        // passenger must exist
        if (passenger == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        passenger.setActive(true);
        passengerService.save(passenger);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public void sendMail(Integer id) throws MessagingException, UnsupportedEncodingException {
        String subject = "Please verify your registration";
        String senderName = "TAAXI";

        String mailContent = "<p>Dear, user </p>";
        mailContent +="<p>Please click the link below to verify your registration:</p>";
        mailContent +="<h3><a href=\"" + "http://localhost:8085/api/passenger/activate/" + id + "\">VERIFY</a></h3>";
        mailContent +="<p>Thank you<br>TAAXI Team</p>";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("UberAppTim19@gmail.com", senderName);
        helper.setTo("tamara_dzambic@hotmail.com");
        helper.setSubject(subject);
        helper.setText(mailContent, true);

        mailSender.send(message);
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
    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<PassengerDTO> updatePassenger(@PathVariable Integer id, @RequestBody PassengerDTO passengerDTO) {

        // a passenger must exist
        Passenger passenger = passengerService.findOne(id);

        if (passenger == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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
    public ResponseEntity<Map<String, Object>> getAllRides(@PathVariable Integer id,
                                                           @RequestParam(defaultValue = "0") Integer page,
                                                           @RequestParam(defaultValue = "4") Integer size,
                                                           @RequestParam(required = false) String sort,
                                                           @RequestParam(required = false) String  from,
                                                           @RequestParam(required = false) String  to){


        Set<Ride> allRides = rideService.findByPassengerId(id);

        Map<String, Object> response = new HashMap<>();
        response.put("totalCount", allRides.size());
        response.put("results", allRides);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
