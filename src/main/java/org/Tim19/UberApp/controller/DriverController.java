package org.Tim19.UberApp.controller;

import org.Tim19.UberApp.dto.*;
import org.Tim19.UberApp.model.Driver;
import org.Tim19.UberApp.model.Passenger;
import org.Tim19.UberApp.model.Vehicle;
import org.Tim19.UberApp.service.DriverService;
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
public class DriverController {

    @Autowired
    private DriverService driverService;

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
        driver.setEmail(driverDTO.getEmail());
        driver.setFirstname(driverDTO.getFirstname());
        driver.setLastname(driverDTO.getLastname());
        driver.setPassword(driverDTO.getPassword());

        driver = driverService.save(driver);
        return new ResponseEntity<>(new DriverDTO(driver), HttpStatus.CREATED);
    }

    //GETTING PAGINATED DRIVER DATA  /api/driver
    //DONE
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllDrivers(@RequestParam(defaultValue = "0") Integer page,
                                                             @RequestParam(defaultValue = "4") Integer size) {

        Pageable paging = PageRequest.of(page, size);
        Page<Driver> pagedResult = driverService.findAll(paging);

        Map<String, Object> response = new HashMap<>();
        response.put("totalcounts", pagedResult.getTotalElements());
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
    public ResponseEntity<DriverDTO> updateDriver(@RequestBody DriverDTO driverDTO) {

        // a driver must exist
        Driver driver = driverService.findOne(driverDTO.getId());

        if (driver == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        driver.setBlocked(false);
        driver.setActive(true);
        driver.setDocuments(driverDTO.getDocuments());
        driver.setRides(driverDTO.getRides());
        driver.setVehicle(driverDTO.getVehicle());
        driver.setProfilePicture(driverDTO.getProfilePicture());
        driver.setTelephoneNumber(driverDTO.getTelephoneNumber());
        driver.setAddress(driverDTO.getAddress());
        driver.setEmail(driverDTO.getEmail());
        driver.setFirstname(driverDTO.getFirstname());
        driver.setLastname(driverDTO.getLastname());
        driver.setPassword(driverDTO.getPassword());

        driver = driverService.save(driver);
        return new ResponseEntity<>(new DriverDTO(driver), HttpStatus.OK);
    }

    //RIDES OF THE SPECIFIC DRIVER  /api/driver/{id}/ride
//    @GetMapping(value = "/{id}/ride")
//    public ResponseEntity<Map<String, Object>> getAllRidesFromDriver(@PathVariable Integer id,
//                                                           @RequestParam(defaultValue = "0") Integer page,
//                                                           @RequestParam(defaultValue = "4") Integer size) {
//
//        Driver driver = driverService.findOne(id);
//
//        if (driver == null) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//
//
//        Pageable paging = PageRequest.of(page, size);
//        //Page<Integer> pagedResult = driverService.findAllRidesFromDriver(paging, id);
//
//        Map<String, Object> response = new HashMap<>();
//        //response.put("totalcounts", pagedResult.getTotalElements());
//        //response.put("results", pagedResult.getContent());
//
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
        @GetMapping(value = "/{id}/ride")
        public ResponseEntity<Map<String, Object>> getAllRidesFromDriver(){


            Driver2DTO driver2DTO = new Driver2DTO(1,"aleks@gmail.com");
            Passenger2DTO passenger2DTO = new Passenger2DTO(1,"tamara@gmail.com");
            Vehicle2DTO vehicle2DTO =new Vehicle2DTO(5,"Standardno",true,false);

            Map<String, Object> locations = new HashMap<>();

            String lokacija1 = "fnkjaef";
            String alokacija1 = "fnkjaef";

            locations.put("departure", lokacija1);
            locations.put("destination", alokacija1);


            Map<String, Object> results2 = new HashMap<>();
            results2.put("passenger",passenger2DTO);
            results2.put("driver", driver2DTO);
            results2.put("vehicle", vehicle2DTO);


            Map<String, Object> response = new HashMap<>();
            response.put("totalcounts",2);
            response.put("results",results2);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }


}

