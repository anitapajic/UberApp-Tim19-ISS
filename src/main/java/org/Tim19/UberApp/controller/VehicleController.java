package org.Tim19.UberApp.controller;

import org.Tim19.UberApp.dto.VehicleDTO;
import org.Tim19.UberApp.model.Driver;
import org.Tim19.UberApp.model.Vehicle;
import org.Tim19.UberApp.service.DriverService;
import org.Tim19.UberApp.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api")
@CrossOrigin(value = "*")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private DriverService driverService;

    //VEHICLE OF THE DRIVER  /api/driver/{id}/vehicle
    @PreAuthorize("hasAnyAuthority('ADMIN', 'DRIVER')")
    @GetMapping(value="/driver/{id}/vehicle")
    public ResponseEntity<VehicleDTO> getVehicle(@PathVariable Integer id) {

        Driver driver = driverService.findOne(id);
        Integer vehicleID = driver.getVehicle().getId();
        Vehicle vehicle = vehicleService.findOne(vehicleID);

        if (vehicle == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        VehicleDTO vehicleDTO = new VehicleDTO(vehicle);
        vehicleDTO.setDriverId(id);

        return new ResponseEntity<>(vehicleDTO, HttpStatus.OK);

    }

    //ADD VEHICLE TO THE DRIVER  /api/driver/{id}/vehicle
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping(value="/driver/{id}/vehicle", consumes = "application/json")
    public ResponseEntity<VehicleDTO> saveVehicle(@PathVariable Integer id, @RequestBody VehicleDTO vehicleDTO) {

        Driver driver = driverService.findOne(id);
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
    public ResponseEntity<VehicleDTO> updateVehicle(@PathVariable Integer id,@RequestBody VehicleDTO vehicleDTO) {

        Driver driver = driverService.findOne(id);
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
    @PutMapping(value = "/vehicle/{id}/location")
    public ResponseEntity<Void> changeVehicleLocation(){

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}


