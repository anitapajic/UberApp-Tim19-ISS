package org.Tim19.UberApp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.Tim19.UberApp.dto.PaginatedData.PanicPaginatedDTO;
import org.Tim19.UberApp.dto.PaginatedData.UserPanicDTO;
import org.Tim19.UberApp.dto.PaginatedData.UserPanicPaginatedDTO;
import org.Tim19.UberApp.dto.PanicSocketDTO;
import org.Tim19.UberApp.dto.RideDTO;
import org.Tim19.UberApp.dto.RideHistoryFilterDTO;
import org.Tim19.UberApp.model.*;
import org.Tim19.UberApp.security.SecurityUser;
import org.Tim19.UberApp.service.DriverService;
import org.Tim19.UberApp.service.PassengerService;
import org.Tim19.UberApp.service.RideService;
import org.Tim19.UberApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

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

    @Autowired
    private VehicleController vehicleController;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;



        //CREATING A RIDE  /api/ride
    @PreAuthorize("hasAnyAuthority('PASSENGER')")
    @PostMapping(consumes = "application/json")
    public ResponseEntity createRide(@RequestBody RideDTO rideDTO) throws InterruptedException {
        rideDTO = rideService.create(rideDTO);
        this.simpMessagingTemplate.convertAndSend("/map-updates/ask-driver", rideDTO);


        return new ResponseEntity<>(rideDTO,HttpStatus.OK);
    }

    //ACTIVE RIDE FOR DRIVER  /api/ride/driver/{driverId}/active
    @GetMapping(value="/driver/{driverId}/active")
    @PreAuthorize("hasAnyAuthority('DRIVER', 'ADMIN')")
    public ResponseEntity activeRideForDriver(@PathVariable Integer driverId) {

        Driver driver = driverService.findOne(driverId);

        if (driver == null) {
            return new ResponseEntity<>("Active ride does not exist!", HttpStatus.NOT_FOUND);
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

        try{
            Passenger passenger = passengerService.findOne(passengerId);


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
        catch (NullPointerException ex){
            return new ResponseEntity<>("Active ride does not exist!", HttpStatus.NOT_FOUND);
        }
    }

    //RIDE DETAILS  api/ride/{id}
    @PreAuthorize("hasAnyAuthority('PASSENGER', 'ADMIN', 'DRIVER')")
    @GetMapping(value = "/{id}")
    public ResponseEntity getRide(@PathVariable Integer id) {

        try{

            if(id>9999 || id<0){
                return new ResponseEntity<>("Invalid data. Bad Id format.", HttpStatus.BAD_REQUEST);
            }

            Ride ride = rideService.findOneRideById(id);

            return new ResponseEntity<>(new RideDTO(ride), HttpStatus.OK);
        }
        catch (NullPointerException ex){
            return new ResponseEntity<>("Ride does not exist!", HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping(value = "/all")
    public ResponseEntity getAllRides(@RequestBody RideHistoryFilterDTO filterDTO) {
        try{
            List<Ride> allRides = rideService.findAllFilter(filterDTO);
            Map<String, Object> response = new HashMap<>();
            response.put("totalCount", allRides.size());
            response.put("results", allRides);

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (NullPointerException ex){
            return new ResponseEntity<>("Ride does not exist!", HttpStatus.NOT_FOUND);
        }
    }

    //CANCEL EXISTING RIDE  /api/ride/{id}/withdraw
    @PreAuthorize("hasAnyAuthority('PASSENGER', 'DRIVER')")
    @PutMapping(value="/{id}/withdraw")
    public ResponseEntity cancelRide(@PathVariable Integer id) {

        try{
            Ride ride = rideService.findOneRideById(id);

            if(!(ride.getStatus().equals("ACCEPTED") || ride.getStatus().equals("PENDING"))){
                return new ResponseEntity<>("Cannot cancel a ride that is not in status PENDING or STARTED!",
                        HttpStatus.BAD_REQUEST);
            }

            this.simpMessagingTemplate.convertAndSend("/map-updates/declined-ride", new RideDTO(ride));
            Driver driver = ride.getDriver();
            driver.setHasRide(false);
            userService.save(driver);
            ride.setStatus("CANCELED");
            rideService.save(ride);

            return new ResponseEntity<>(new RideDTO(ride),HttpStatus.OK);
        }
        catch (NullPointerException ex){
            return new ResponseEntity<>("Ride does not exist!", HttpStatus.NOT_FOUND);
        }
    }

    //PANIC PROCEDURE FOR THE RIDE  /api/ride/{id}/panic
    @PreAuthorize("hasAnyAuthority('PASSENGER', 'DRIVER')")
    @PutMapping(value="/{id}/panic", consumes = "application/json")
    public ResponseEntity panicRide(@RequestBody PanicPaginatedDTO panic,
                                                       @PathVariable Integer id) {

        try{
            Ride ride = rideService.findOneRideById(id);
            if(ride == null){
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
            UserPanicDTO user3 = new UserPanicDTO(user2.getName(), user2.getSurname());

            PanicSocketDTO panicSocket = new PanicSocketDTO(panic.getId(), panic.getReason(), panic.getTime(), user3);

            this.simpMessagingTemplate.convertAndSend("/map-updates/panic", panicSocket);

            endRide(ride.getId());

            return new ResponseEntity<>(panic, HttpStatus.OK);
        }
        catch (NullPointerException ex){
            return new ResponseEntity<>("Ride does not exist!", HttpStatus.NOT_FOUND);
        }
    }

    //START RIDE  /api/ride/{id}/start
    @PreAuthorize("hasAnyAuthority('DRIVER')")
    @PutMapping(value="/{id}/start")
    public ResponseEntity startRide(@PathVariable Integer id) {

        try{
            Ride ride = rideService.findOneRideById(id);

            if(!(ride.getStatus().equals("ACCEPTED"))){
                return new ResponseEntity<>("Cannot start a ride that is not in status ACCEPTED!", HttpStatus.BAD_REQUEST);
            }

            ride.setStatus("STARTED");
            ride.setStartTime(LocalDateTime.now());
            rideService.save(ride);

            this.simpMessagingTemplate.convertAndSend("/map-updates/new-ride", new RideDTO(ride));
            return new ResponseEntity<>(new RideDTO(ride), HttpStatus.OK);
        }
        catch (NullPointerException ex){
            return new ResponseEntity<>("Ride does not exist!", HttpStatus.NOT_FOUND);
        }
    }


    //ACCEPT RIDE  /api/ride/{id}/accept
    @PreAuthorize("hasAnyAuthority('DRIVER')")
    @PutMapping(value="/{id}/accept")
    public ResponseEntity acceptRide(@PathVariable Integer id) {

        try{
            Ride ride = rideService.findOneRideById(id);

            if(!(ride.getStatus().equals("PENDING"))){
                return new ResponseEntity<>("Cannot accept a ride that is not in status PENDING!", HttpStatus.BAD_REQUEST);
            }

            ride.setStatus("ACCEPTED");
            rideService.save(ride);
            HashMap<String, Object> rideTime = new HashMap<>();
            rideTime.put("ride", new RideDTO(ride));
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime start = ride.getStartTime();

            rideTime.put("time",Math.toIntExact(ChronoUnit.MINUTES.between(now, start)));
            this.simpMessagingTemplate.convertAndSend("/map-updates/inform", rideTime);
            Driver d = ride.getDriver();
            d.setHasRide(true);
            driverService.save(d);

            this.simpMessagingTemplate.convertAndSend("/map-updates/update-activity", ride.getDriver());

            return new ResponseEntity<>(new RideDTO(ride), HttpStatus.OK);
        }
        catch (NullPointerException ex){
            return new ResponseEntity<>("Ride does not exist!", HttpStatus.NOT_FOUND);
        }
    }

    //END THE RIDE  /api/ride/{id}/end
    @PreAuthorize("hasAnyAuthority('DRIVER')")
    @PutMapping(value="/{id}/end")
    public ResponseEntity endRide(@PathVariable Integer id) {

        try{
            Ride ride = rideService.findOneRideById(id);

            if(!(ride.getStatus().equals("STARTED"))){
                return new ResponseEntity<>("Cannot end a ride that is not in status STARTED!", HttpStatus.BAD_REQUEST);
            }

            ride.setStatus("FINISHED");
            ride.setEndTime(ride.getStartTime().plusMinutes(ride.getEstimatedTimeInMinutes()));
            rideService.save(ride);
            this.simpMessagingTemplate.convertAndSend("/map-updates/end-ride", new RideDTO(ride));

            Driver d = ride.getDriver();
            d.setHasRide(false);
            driverService.save(d);

            this.simpMessagingTemplate.convertAndSend("/map-updates/update-activity", ride.getDriver());


            return new ResponseEntity<>(new RideDTO(ride), HttpStatus.OK);
        }
        catch (NullPointerException ex){
            return new ResponseEntity<>("Ride does not exist!", HttpStatus.NOT_FOUND);
        }
    }

    //CANCEL RIDE WITH AN EXPLANATION  /api/ride/{id}/cancel
    @PreAuthorize("hasAnyAuthority('PASSENGER', 'DRIVER')")
    @PutMapping(value="/{id}/cancel", consumes = "application/json")
    public ResponseEntity cancelRideWithExpl(@PathVariable Integer id,
                                                               @RequestBody Rejection rejection) {

        try
        {
            Ride ride = rideService.findOneRideById(id);

            if(!(ride.getStatus().equals("ACCEPTED") || ride.getStatus().equals("PENDING"))){
                return new ResponseEntity<>("Cannot cancel a ride that is not in status PENDING or ACCEPTED!",
                        HttpStatus.BAD_REQUEST);
            }

            rejection.setTimeOfRejection(LocalDateTime.now());
            rejection.setRide(ride);
            rejection.setUser(ride.getDriver());
            ride.addRejection(rejection);
            ride.setStatus("REJECTED");
            rideService.save(ride);

            Driver d = ride.getDriver();
            d.setHasRide(false);
            driverService.save(d);

            this.simpMessagingTemplate.convertAndSend("/map-updates/update-activity", ride.getDriver());

            RideDTO rDTO = new RideDTO(ride);
            this.simpMessagingTemplate.convertAndSend("/map-updates/declined-ride",rDTO);

            return new ResponseEntity<>(rDTO, HttpStatus.OK);
        }
        catch (NullPointerException ex){
            return new ResponseEntity<>("Ride does not exist!", HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping(
            produces = "application/json",
            value = "/active"
    )
    public ResponseEntity getAllActiveRides() {
        List<RideDTO> rides = this.rideService.getAllActiveRides();

        return new ResponseEntity<>(rides, HttpStatus.OK);
    }

    @Scheduled(initialDelay = 1000, fixedRate = 5000)
    public void simulate() throws JsonProcessingException {
        updateActiveRideVehiclePosition(this.rideService.getAllActiveRides());
        updateAcceptedRideVehiclePosition(this.rideService.getAllAcceptedRides());
        notifyPassenger();
    }

    @Transactional
    void updateActiveRideVehiclePosition(List<RideDTO> rides) throws JsonProcessingException {
        for(RideDTO ride : rides){
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(ride.getRouteJSON());
            JsonNode step = root.path("routes").get(0).path("legs").get(0).path("steps").get(ride.getStep());
            if(step == null){
                endRide(ride.getId());
            }
            rideService.saveStep(ride.getId());

            Location nextLocation = new Location();
            nextLocation.setLatitude(step.path("maneuver").path("location").get(1).floatValue());
            nextLocation.setLongitude(step.path("maneuver").path("location").get(0).floatValue());
            vehicleController.changeVehicleLocation(ride.getVehicle().getId(), nextLocation);
        }
    }

    @Transactional
    void updateAcceptedRideVehiclePosition(List<RideDTO> rides) throws JsonProcessingException {
        for(RideDTO ride : rides){
            Location nextLocation = rideService.findNextLocation(ride.getVehicle().getLocation(), ride.getDeparture());
            if(nextLocation == null){
                return;
            }
            System.out.println(nextLocation);
            vehicleController.changeVehicleLocation(ride.getVehicle().getId(), nextLocation);

        }
    }

    void notifyPassenger(){
        this.simpMessagingTemplate.convertAndSend("/map-updates/change-time", "");
    }

}
