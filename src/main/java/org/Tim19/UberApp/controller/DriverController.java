package org.Tim19.UberApp.controller;

import org.Tim19.UberApp.dto.*;
import org.Tim19.UberApp.dto.PaginatedData.*;
import org.Tim19.UberApp.model.*;
import org.Tim19.UberApp.service.DriverService;
import org.Tim19.UberApp.service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping(value = "/api/driver")
@CrossOrigin(value = "*")
public class DriverController {

    @Autowired
    private DriverService driverService;
    @Autowired
    private RideService rideService;

    //CREATE DRIVER  /api/driver
    //DONE
    @PostMapping(consumes = "application/json")
    public ResponseEntity<DriverDTO> createDriver(@RequestBody DriverDTO driverDTO) {

        Driver driver = new Driver();
        driver.setActive(true);
        driver.setBlocked(false);
        driver.setProfilePicture(driverDTO.getProfilePicture());
        driver.setTelephoneNumber(driverDTO.getTelephoneNumber());
        driver.setAddress(driverDTO.getAddress());
        driver.setUsername(driverDTO.getUsername());
        driver.setName(driverDTO.getName());
        driver.setSurname(driverDTO.getSurname());
        driver.setPassword(driverDTO.getPassword());

        driver = driverService.save(driver);
        return new ResponseEntity<>(new DriverDTO(driver), HttpStatus.OK);
    }

    //GETTING PAGINATED DRIVER DATA  /api/driver
    //DONE
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllDrivers(@RequestParam(defaultValue = "0") Integer page,
                                                             @RequestParam(defaultValue = "4") Integer size) {

        Pageable paging = PageRequest.of(page, size);
        Page<Driver> pagedResult = driverService.findAll(paging);

        Map<String, Object> response = new HashMap<>();
        response.put("totalCount", pagedResult.getTotalElements());
        response.put("results", pagedResult.getContent());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //DRIVER DETAILS  /api/driver/{id}
    //DONE
    @GetMapping(value = "/{id}")
    public ResponseEntity<DriverDTO> getDriver(@PathVariable Integer id) {

        Driver users = driverService.findOne(id);

        // user must exist
        if (users == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

            return new ResponseEntity<>(new DriverDTO(users), HttpStatus.OK);
        }

    //UPDATE EXISTING DRIVER  /api/driver/{id}
    //DONE
    @PutMapping(value= "/{id}" ,consumes = "application/json")
    public ResponseEntity<DriverDTO> updateDriver(@PathVariable Integer id, @RequestBody DriverDTO driverDTO) {

        // a driver must exist
        Driver driver = driverService.findOne(id);

        if (driver == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        driver.setBlocked(false);
        driver.setActive(true);
        //driver.setDocuments(driverDTO.getDocuments());
        //driver.setRides(driverDTO.getRides());
        driver.setVehicle(driverDTO.getVehicle());
        driver.setProfilePicture(driverDTO.getProfilePicture());
        driver.setTelephoneNumber(driverDTO.getTelephoneNumber());
        driver.setAddress(driverDTO.getAddress());
        driver.setUsername(driverDTO.getUsername());
        driver.setName(driverDTO.getName());
        driver.setSurname(driverDTO.getSurname());
        driver.setPassword(driverDTO.getPassword());

        return new ResponseEntity<>(new DriverDTO(driver), HttpStatus.OK);
    }


        @GetMapping(value = "/{id}/ride")
        public ResponseEntity<Map<String, Object>> getAllRidesFromDriver(@PathVariable Integer id,
                                                                         @RequestParam(defaultValue = "0") Integer page,
                                                                         @RequestParam(defaultValue = "4") Integer size,
                                                                         @RequestParam(required = false) String sort,
                                                                         @RequestParam(required = false) String  from,
                                                                         @RequestParam(required = false) String  to) {

            Pageable paging = PageRequest.of(page, size);

            Page<Ride> allRides = rideService.findByDriverId(id, paging);

            Map<String, Object> response = new HashMap<>();
            response.put("totalCount", allRides.getTotalElements());
            response.put("results", allRides.getContent());

            return new ResponseEntity<>(response, HttpStatus.OK);

        }
}

