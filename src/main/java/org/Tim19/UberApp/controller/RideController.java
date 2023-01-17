package org.Tim19.UberApp.controller;

import org.Tim19.UberApp.dto.PaginatedData.CreateRideBodyPaginatedDTO;
import org.Tim19.UberApp.dto.PaginatedData.PanicPaginatedDTO;
import org.Tim19.UberApp.dto.PaginatedData.UserPanicPaginatedDTO;
import org.Tim19.UberApp.dto.RideDTO;
import org.Tim19.UberApp.model.*;
import org.Tim19.UberApp.security.SecurityUser;
import org.Tim19.UberApp.service.DriverService;
import org.Tim19.UberApp.service.PassengerService;
import org.Tim19.UberApp.service.RideService;
import org.Tim19.UberApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

@RestController
@RequestMapping(value="/api/ride")
@CrossOrigin(value = "*")
public class RideController {

    @Autowired
    private RideService rideService;
    @Autowired
    private DriverService driverService;
    @Autowired
    private UserService userService;
    @Autowired
    private PassengerService passengerService;

    //CREATING A RIDE  /api/ride
    @PreAuthorize("hasAnyAuthority('PASSENGER')")
    @PostMapping(consumes = "application/json")
    public ResponseEntity createRide(@RequestBody CreateRideBodyPaginatedDTO rideDTO) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        SecurityUser user = (SecurityUser) auth.getPrincipal();

        Passenger passenger = passengerService.findByEmail(user.getUsername());
        System.out.println(passenger.getUsername());
        Set<Ride> rides = rideService.findAllByPassengerId(passenger.getId());
        System.out.println(passenger.getRides());
        List<String> statuses = new ArrayList<>();
        for (Ride ride : rides){
            statuses.add(ride.getStatus());
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+ride.getId());
        }
        if (statuses.contains("PENDING")){
            return new ResponseEntity<>("Cannot create a ride while you have one already pending!",  HttpStatus.BAD_REQUEST);
        }

        Driver driver = rideService.findFreeDriver();
        Ride ride = new Ride();

        ride.setPanic(false);
        ride.setDriver(driver);
        ride.setStartTime(LocalDateTime.now());

        List<Float> coordinates = rideDTO.getCoordinates();
        Float long1 = coordinates.get(0);
        Float long2 = coordinates.get(1);
        Float lat1 = coordinates.get(2);
        Float lat2 = coordinates.get(3);

        ride.setTotalCost(rideService.calculatePrice(rideDTO.getVehicleType(), rideService.calculateKilometres(long1, long2, lat1, lat2), rideDTO.isBabyTransport(), rideDTO.isPetTransport()));
        ride.setLocations(rideDTO.getLocations());
        ride.setEstimatedTimeInMinutes(rideService.calculateTravelTime(rideService.calculateKilometres(long1, long2, lat1, lat2)));
        ride.setStatus("PENDING");
        ride.setBabyTransport(rideDTO.isBabyTransport());
        ride.setPetTransport(rideDTO.isPetTransport());
        ride.setVehicleType(rideDTO.getVehicleType());
        for (Passenger p: rideDTO.getPassengers()) {
            Passenger p2 = (Passenger) userService.findOneById(p.getId());
            ride.addPassenger(p2);
        }

        ride = rideService.save(ride);

        return new ResponseEntity<>(new RideDTO(ride),  HttpStatus.CREATED);
    }

    //ACTIVE RIDE FOR DRIVER  /api/ride/driver/{driverId}/active
    @GetMapping(value="/driver/{driverId}/active")
    @PreAuthorize("hasAnyAuthority('DRIVER', 'ADMIN')")
    public ResponseEntity activeRideForDriver(@PathVariable Integer driverId) {

        Driver driver = driverService.findOne(driverId);

        if (driver == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Set<Ride> ridesOfDriver = driver.getRides();
        Ride activeRide = null;

        for (Ride r : ridesOfDriver){
            if (r.getStatus().equals("STARTED")){
                activeRide = r;
            }
        }

        if(activeRide==null){
            return new ResponseEntity<>("Active ride does not exist!", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new RideDTO(activeRide),HttpStatus.OK);
    }

    //ACTIVE RIDE FOR PASSENGER  /api/ride/passenger/{passengerId}/active
    @PreAuthorize("hasAnyAuthority('PASSENGER', 'ADMIN')")
    @GetMapping(value="/passenger/{passengerId}/active")
    public ResponseEntity activeRideForPassenger(@PathVariable Integer passengerId) {

        Passenger passenger = passengerService.findOne(passengerId);

        if (passenger == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Set<Ride> ridesOfPassenger = rideService.findAllByPassengerId(passengerId);
        Ride activeRide = null;

        for (Ride r : ridesOfPassenger){
            if (r.getStatus().equals("STARTED")){
                activeRide = r;
            }
        }

        if(activeRide==null){
            return new ResponseEntity<>("Active ride does not exist!", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new RideDTO(activeRide),HttpStatus.OK);
    }

    //RIDE DETAILS  api/ride/{id}
    @PreAuthorize("hasAnyAuthority('PASSENGER', 'ADMIN', 'DRIVER')")
    @GetMapping(value = "/{id}")
    public ResponseEntity getRide(@PathVariable Integer id) {


        String regex = "[0-9]{0,4}";
        if(!regex.matches(String.valueOf(id))){
            return new ResponseEntity<>("Invalid data. Bad Id format.", HttpStatus.BAD_REQUEST);
        }

        Ride ride = rideService.findOneRideById(id);

        // ride must exist
        if (ride == null) {
            return new ResponseEntity<>("Ride does not exist!", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new RideDTO(ride), HttpStatus.OK);
    }

    //CANCEL EXISTING RIDE  /api/ride/{id}/withdraw
    @PreAuthorize("hasAnyAuthority('PASSENGER', 'DRIVER')")
    @PutMapping(value="/{id}/withdraw")
    public ResponseEntity cancelRide(@PathVariable Integer id) {

        Ride ride = rideService.findOneRideById(id);

        // ride must exist
        if (ride == null) {
            return new ResponseEntity<>("Ride does not exist!", HttpStatus.NOT_FOUND);
        }

        if(!(ride.getStatus().equals("STARTED") || ride.getStatus().equals("PENDING"))){
            return new ResponseEntity<>("Cannot cancel a ride that is not in status PENDING or STARTED!",
                    HttpStatus.BAD_REQUEST);
        }

        ride.setStatus("CANCELED");
        rideService.save(ride);

        return new ResponseEntity<>(new RideDTO(ride),HttpStatus.OK);
    }

    //PANIC PROCEDURE FOR THE RIDE  /api/ride/{id}/panic
    @PreAuthorize("hasAnyAuthority('PASSENGER', 'DRIVER')")
    @PutMapping(value="/{id}/panic", consumes = "application/json")
    public ResponseEntity panicRide(@RequestBody PanicPaginatedDTO panic,
                                                       @PathVariable Integer id) {

        Ride ride = rideService.findOneRideById(id);

        // ride must exist
        if (ride == null) {
            return new ResponseEntity<>("Ride does not exist!", HttpStatus.NOT_FOUND);
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        SecurityUser user = (SecurityUser) auth.getPrincipal();

        User passenger = userService.findOneUserByUsername(user.getUsername());

        UserPanicPaginatedDTO user2 = new UserPanicPaginatedDTO();
        user2.setEmail(passenger.getUsername());
        user2.setName(passenger.getName());
        user2.setAddress(passenger.getAddress());
        user2.setSurname(passenger.getSurname());
        user2.setTelephoneNumber(passenger.getTelephoneNumber());
        user2.setProfilePicture(passenger.getProfilePicture());

        int randomNumber = new Random().nextInt(9000) + 1000;
        panic.setId(randomNumber);
        panic.setRide(ride);
        panic.setUser(user2);
        panic.setTime(LocalDateTime.now());

        return new ResponseEntity<>(panic, HttpStatus.OK);
    }

    //START RIDE  /api/ride/{id}/start
    @PreAuthorize("hasAnyAuthority('DRIVER')")
    @PutMapping(value="/{id}/start")
    public ResponseEntity startRide(@PathVariable Integer id) {

        Ride ride = rideService.findOneRideById(id);

        // ride must exist
        if (ride == null) {
            return new ResponseEntity<>("Ride does not exist!", HttpStatus.NOT_FOUND);
        }

        if(!(ride.getStatus().equals("PENDING"))){
            return new ResponseEntity<>("Cannot start a ride that is not in status ACCEPTED!", HttpStatus.BAD_REQUEST);
        }

        ride.setStatus("STARTED");
        rideService.save(ride);

        return new ResponseEntity<>(new RideDTO(ride), HttpStatus.OK);
    }

    //ACCEPT RIDE  /api/ride/{id}/accept
    @PreAuthorize("hasAnyAuthority('DRIVER')")
    @PutMapping(value="/{id}/accept")
    public ResponseEntity acceptRide(@PathVariable Integer id) {

        Ride ride = rideService.findOneRideById(id);

        // ride must exist
        if (ride == null) {
            return new ResponseEntity<>("Ride does not exist!", HttpStatus.NOT_FOUND);
        }

        if(!(ride.getStatus().equals("PENDING"))){
            return new ResponseEntity<>("Cannot accept a ride that is not in status PENDING!", HttpStatus.BAD_REQUEST);
        }

        ride.setStatus("ACCEPTED");
        rideService.save(ride);

        return new ResponseEntity<>(new RideDTO(ride), HttpStatus.OK);
    }

    //END THE RIDE  /api/ride/{id}/end
    @PreAuthorize("hasAnyAuthority('DRIVER')")
    @PutMapping(value="/{id}/end")
    public ResponseEntity endRide(@PathVariable Integer id) {

        Ride ride = rideService.findOneRideById(id);

        // ride must exist
        if (ride == null) {
            return new ResponseEntity<>("Ride does not exist!", HttpStatus.NOT_FOUND);
        }

        if(!(ride.getStatus().equals("STARTED"))){
            return new ResponseEntity<>("Cannot en a ride that is not in status STARTED!", HttpStatus.BAD_REQUEST);
        }

        ride.setStatus("FINISHED");
        rideService.save(ride);

        return new ResponseEntity<>(new RideDTO(ride), HttpStatus.OK);
    }

    //CANCEL RIDE WITH AN EXPLANATION  /api/ride/{id}/cancel
    @PreAuthorize("hasAnyAuthority('PASSENGER')")
    @PutMapping(value="/{id}/cancel", consumes = "application/json")
    public ResponseEntity cancelRideWithExpl(@PathVariable Integer id,
                                                               @RequestBody Rejection rejection) {

        Ride ride = rideService.findOneRideById(id);

        // ride must exist
        if (ride == null) {
            return new ResponseEntity<>("Ride does not exist!", HttpStatus.NOT_FOUND);
        }

        if(!(ride.getStatus().equals("ACCEPTED") || ride.getStatus().equals("PENDING"))){
            return new ResponseEntity<>("Cannot cancel a ride that is not in status PENDING or ACCEPTED!",
                    HttpStatus.BAD_REQUEST);
        }

        rejection.setTimeOfRejection(LocalDateTime.now());

        rejection.setRide(ride);
        rejection.setUser((User) ride.getPassengers().toArray()[0]);

        ride.addRejection(rejection);
        ride.setStatus("REJECTED");
        rideService.save(ride);

        RideDTO rDTO = new RideDTO(ride);
        return new ResponseEntity<>(rDTO, HttpStatus.OK);
    }

}
