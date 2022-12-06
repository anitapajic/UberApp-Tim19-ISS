package org.Tim19.UberApp.controller;

import org.Tim19.UberApp.dto.DriverDTO;
import org.Tim19.UberApp.model.Driver;
import org.Tim19.UberApp.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/driver")
public class DriverController {

        @Autowired
        private DriverService driverService;

        @GetMapping
        public ResponseEntity<List<DriverDTO>> getAllDrivers() {

            List<Driver> driver = driverService.findAll();

            // convert users to DTOs
            List<DriverDTO> driverDTO = new ArrayList<>();
            for (Driver d : driver) {
                driverDTO.add(new DriverDTO(d));
            }

            return new ResponseEntity<>(driverDTO, HttpStatus.OK);
        }

        @GetMapping(value = "/{id}")
        public ResponseEntity<DriverDTO> getUser(@PathVariable Integer id) {

            Driver users = driverService.findOne(id);

            // user must exist
            if (users == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(new DriverDTO(users), HttpStatus.OK);
        }

        @PostMapping(consumes = "application/json")
        public ResponseEntity<DriverDTO> saveUser(@RequestBody DriverDTO driverDTO) {

            Driver driver = new Driver();
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

        @PutMapping(consumes = "application/json")
        public ResponseEntity<DriverDTO> updateUser(@RequestBody DriverDTO driverDTO) {

            // a user must exist
            Driver driver = driverService.findOne(driverDTO.getId());

            if (driver == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

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
        @DeleteMapping(value = "/{id}")
        public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {

            Driver driver = driverService.findOne(id);

            if (driver != null) {
                driverService.remove(id);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
}

