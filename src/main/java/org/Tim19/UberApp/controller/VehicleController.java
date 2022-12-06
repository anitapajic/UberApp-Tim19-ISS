package org.Tim19.UberApp.controller;

import org.Tim19.UberApp.dto.VehicleDTO;
import org.Tim19.UberApp.model.Vehicle;
import org.Tim19.UberApp.service.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/driver/{id}/vehicle")
public class VehicleController {

    private VehicleService vehicleService;

    @GetMapping
    public ResponseEntity<VehicleDTO> getVehicle(@PathVariable Integer id) {

        Vehicle vehicle = vehicleService.findOne(id);

        // user must exist
        if (vehicle == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new VehicleDTO(vehicle), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<VehicleDTO> saveVehicle(@RequestBody VehicleDTO vehicleDTO) {

        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleType(vehicleDTO.getVehicleType());
        vehicle.setCarModel(vehicleDTO.getCarModel());
        vehicle.setLicenseNumber(vehicleDTO.getLicenseNumber());
        vehicle.setPassengerSeats(vehicleDTO.getPassengerSeats());
        vehicle.setBabyTransport(vehicleDTO.isBabyTransport());
        vehicle.setPetTransport(vehicleDTO.isBabyTransport());

        vehicle = vehicleService.save(vehicle);
        return new ResponseEntity<>(new VehicleDTO(vehicle), HttpStatus.CREATED);
    }

    @PutMapping(consumes = "application/json")
    public ResponseEntity<VehicleDTO> updateVehicle(@RequestBody VehicleDTO vehicleDTO) {

        // a user must exist
        Vehicle vehicle = vehicleService.findOne(vehicleDTO.getId());

        if (vehicle == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        vehicle.setVehicleType(vehicleDTO.getVehicleType());
        vehicle.setCarModel(vehicleDTO.getCarModel());
        vehicle.setLicenseNumber(vehicleDTO.getLicenseNumber());
        vehicle.setPassengerSeats(vehicleDTO.getPassengerSeats());
        vehicle.setBabyTransport(vehicleDTO.isBabyTransport());
        vehicle.setPetTransport(vehicleDTO.isBabyTransport());

        vehicle = vehicleService.save(vehicle);
        return new ResponseEntity<>(new VehicleDTO(vehicle), HttpStatus.OK);
    }
}


