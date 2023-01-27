package org.Tim19.UberApp.controller;

import org.Tim19.UberApp.dto.VehicleDTO;
import org.Tim19.UberApp.model.Driver;
import org.Tim19.UberApp.model.Location;
import org.Tim19.UberApp.model.Vehicle;
import org.Tim19.UberApp.service.DriverService;
import org.Tim19.UberApp.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api")
@CrossOrigin(value = "*")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private DriverService driverService;
    
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;


    //VEHICLE OF THE DRIVER  /api/driver/{id}/vehicle
    @PreAuthorize("hasAnyAuthority('ADMIN', 'DRIVER')")
    @GetMapping(value="/driver/{id}/vehicle")
    public ResponseEntity getVehicle(@PathVariable Integer id) {

        Driver driver = driverService.findOne(id);
        Integer vehicleID = driver.getVehicle().getId();
        Vehicle vehicle = vehicleService.findOne(vehicleID);

        if (vehicle == null) {
            return new ResponseEntity<>("Vehicle does not exist",HttpStatus.NOT_FOUND);
        }
        if (driver.getVehicle() == null) {
            return new ResponseEntity<>("Vehicle is not assigned!",HttpStatus.BAD_REQUEST);
        }

        VehicleDTO vehicleDTO = new VehicleDTO(vehicle);
        vehicleDTO.setDriverId(id);

        return new ResponseEntity<>(vehicleDTO, HttpStatus.OK);

    }

//    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping(value="/driver/vehicles")
    public ResponseEntity getAllVehicle() {
        
        List<VehicleDTO> vehicles = vehicleService.findAll();

        return new ResponseEntity<>(vehicles, HttpStatus.OK);

    }

    //ADD VEHICLE TO THE DRIVER  /api/driver/{id}/vehicle
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping(value="/driver/{id}/vehicle", consumes = "application/json")
    public ResponseEntity saveVehicle(@PathVariable Integer id, @RequestBody VehicleDTO vehicleDTO) {

        Driver driver = driverService.findOne(id);
        if (driver == null) {
            return new ResponseEntity<>("Driver does not exist!",HttpStatus.NOT_FOUND);
        }
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleType(vehicleDTO.getVehicleType());
        vehicle.setCarModel(vehicleDTO.getModel());
        vehicle.setLicenseNumber(vehicleDTO.getLicenseNumber());
        vehicle.setPassengerSeats(vehicleDTO.getPassengerSeats());
        vehicle.setBabyTransport(vehicleDTO.isBabyTransport());
        vehicle.setPetTransport(vehicleDTO.isBabyTransport());

        driver.setVehicle(vehicle);
        if (driver.getVehicle().getId() == null) {
            vehicle = vehicleService.save(vehicle);
            driverService.save(driver);

        }
        VehicleDTO response = new VehicleDTO(vehicle);
        response.setCurrentLocation(vehicleDTO.getCurrentLocation());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //CHANGE THE VEHICLE OF THE DRIVER  /api/driver/{id}/vehicle
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping(value="/driver/{id}/vehicle",consumes = "application/json")
    public ResponseEntity updateVehicle(@PathVariable Integer id,@RequestBody VehicleDTO vehicleDTO) {

        Driver driver = driverService.findOne(id);
        if (driver == null) {
            return new ResponseEntity<>("Driver does not exist!",HttpStatus.NOT_FOUND);
        }
        Vehicle vehicle = vehicleService.findOne(driver.getVehicle().getId());

        vehicle.setVehicleType(vehicleDTO.getVehicleType());
        vehicle.setCarModel(vehicleDTO.getModel());
        vehicle.setLicenseNumber(vehicleDTO.getLicenseNumber());
        vehicle.setPassengerSeats(vehicleDTO.getPassengerSeats());
        vehicle.setBabyTransport(vehicleDTO.isBabyTransport());
        vehicle.setPetTransport(vehicleDTO.isBabyTransport());

        vehicle = vehicleService.save(vehicle);

        VehicleDTO response = new VehicleDTO(vehicle);
        response.setCurrentLocation(vehicleDTO.getCurrentLocation());
        response.setDriverId(id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    //CHANGE LOCATION OF THE VEHICLE  /api/vehicle/{id}/location  (vehicleId)
//    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping(value = "/vehicle/location/{id}")
    public ResponseEntity changeVehicleLocation(@PathVariable Integer id, @RequestBody Location location){
        Vehicle vehicle = vehicleService.findOne(id);
        vehicle.setLocation(location);
        vehicle = vehicleService.save(vehicle);
       // this.simpMessagingTemplate.convertAndSend("/map-updates/update-vehicle-position", vehicle);

        return new ResponseEntity<>(vehicle, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping(value="/vehicle", consumes = "application/json")
    public ResponseEntity createVehicle(@RequestBody VehicleDTO vehicleDTO) {

        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleType(vehicleDTO.getVehicleType());
        vehicle.setCarModel(vehicleDTO.getModel());
        vehicle.setLicenseNumber(vehicleDTO.getLicenseNumber());
        vehicle.setPassengerSeats(vehicleDTO.getPassengerSeats());
        vehicle.setBabyTransport(vehicleDTO.isBabyTransport());
        vehicle.setPetTransport(vehicleDTO.isPetTransport());

        vehicle = vehicleService.save(vehicle);

        VehicleDTO response = new VehicleDTO(vehicle);
        response.setCurrentLocation(vehicleDTO.getCurrentLocation());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}


