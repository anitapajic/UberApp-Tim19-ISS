package org.Tim19.UberApp.controller;

import org.Tim19.UberApp.dto.DriverDTO;
import org.Tim19.UberApp.model.Driver;
import org.Tim19.UberApp.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/driver")
public class DriverController {

    @Autowired
    private DriverService driverService;

    //CREATE DRIVER  /api/driver
    @PostMapping(consumes = "application/json")
    public ResponseEntity<DriverDTO> createDriver(@RequestBody DriverDTO driverDTO) {

        Driver driver = new Driver();
        driver.setActive(true);
        driver.setBlocked(false);
        driver.setProfilePicture(driverDTO.getProfilePicture());
        driver.setTelephoneNumber(driverDTO.getTelephoneNumber());
        driver.setAddress(driverDTO.getAddress());
        driver.setEmail(driverDTO.getEmail());
        driver.setFirstname(driverDTO.getName());
        driver.setLastname(driverDTO.getSurname());
        driver.setPassword(driverDTO.getPassword());

        driver = driverService.save(driver);
        return new ResponseEntity<>(new DriverDTO(driver), HttpStatus.CREATED);
    }

    //GETTING PAGINATED DRIVER DATA  /api/driver
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
    @PutMapping(value= "/{id}" ,consumes = "application/json")
    public ResponseEntity<DriverDTO> updateDriver(@PathVariable Integer id, @RequestBody DriverDTO driverDTO) {

        // a driver must exist
        Driver driver = driverService.findOne(id);

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
        driver.setFirstname(driverDTO.getName());
        driver.setLastname(driverDTO.getSurname());
        driver.setPassword(driverDTO.getPassword());

//        driver = driverService.save(driver);
        return new ResponseEntity<>(new DriverDTO(driver), HttpStatus.OK);
    }

    //RIDES OF THE SPECIFIC DRIVER  /api/driver/{id}/ride
    @GetMapping(value = "/{id}/ride")
    public ResponseEntity<Void> getDriversRides(){



        return new ResponseEntity<>(HttpStatus.OK);
    }
}

