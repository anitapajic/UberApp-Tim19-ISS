package org.Tim19.UberApp.controller;


import org.Tim19.UberApp.dto.DriverDTO;
import org.Tim19.UberApp.model.Driver;
import org.Tim19.UberApp.model.UpdateDriver;
import org.Tim19.UberApp.service.DriverService;
import org.Tim19.UberApp.service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/driver")
@CrossOrigin(value = "*")
public class DriverChangesController {


    @Autowired
    private DriverService driverService;


    //UPDATE EXISTING DRIVER  /api/driver/{id}
    //DONE
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping(value= "/{id}" ,consumes = "application/json")
    public ResponseEntity updateDriver(@PathVariable Integer id, @RequestBody UpdateDriver updateDriver) {

        // a driver must exist
        Driver driver = driverService.findOne(id);

        if (driver == null) {
            return new ResponseEntity<>("Driver does not exist!", HttpStatus.NOT_FOUND);
        }

        driver.setBlocked(false);
        driver.setActive(true);
        driver.setTelephoneNumber(updateDriver.getTelephoneNumber());
        driver.setAddress(updateDriver.getAddress());
        driver.setUsername(updateDriver.getUsername());
        driver.setName(updateDriver.getName());
        driver.setSurname(updateDriver.getSurname());



        //TODO: odvojen kontroler za update slike
        //za promenu sifre v postoji u User contolleru
        driver.setProfilePicture(driver.getProfilePicture());
        driver.setPassword(driver.getPassword());

        driver = driverService.save(driver);

        return new ResponseEntity<>(new DriverDTO(driver), HttpStatus.OK);
    }


    @PreAuthorize("hasAnyAuthority('DRIVER')")
    @PostMapping(value= "update/{id}")
    public ResponseEntity requestUpdateDriver(@PathVariable Integer id, @RequestBody UpdateDriver updateDriver) {

        // a driver must exist
        Driver driver = driverService.findOne(id);

        if (driver == null) {
            return new ResponseEntity<>("Driver does not exist!",HttpStatus.NOT_FOUND);
        }
        driver.setTelephoneNumber(updateDriver.getTelephoneNumber());
        driver.setAddress(updateDriver.getAddress());
        driver.setUsername(updateDriver.getUsername());
        driver.setName(updateDriver.getName());
        driver.setSurname(updateDriver.getSurname());


        UpdateDriver update = driverService.requestUpdateDriver(new UpdateDriver(driver));

        return new ResponseEntity<>(update, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping(value= "update")
    public ResponseEntity getAllRequest() {

        List<UpdateDriver> requests = driverService.getAllRequests();

        return new ResponseEntity<>(requests, HttpStatus.OK);
    }


    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping (value= "update/{id}/delete")
    public ResponseEntity deleteRequest(@PathVariable Integer id) {


        UpdateDriver request = driverService.getRequestById(id);
        if(request == null){
            return new ResponseEntity<>("Request does not exist!",HttpStatus.NOT_FOUND);
        }
        driverService.delete(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping (value= "update/{id}/approve")
    public ResponseEntity approveRequest(@PathVariable Integer id) {


        UpdateDriver request = driverService.getRequestById(id);
        if(request == null){
            return new ResponseEntity<>("Request does not exist!",HttpStatus.NOT_FOUND);
        }

        this.updateDriver(id, request);
        driverService.delete(request);

        return new ResponseEntity<>(HttpStatus.OK);
    }





}
