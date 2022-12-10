package org.Tim19.UberApp.controller;

import org.Tim19.UberApp.dto.VehicleDTO;
import org.Tim19.UberApp.model.Vehicle;
import org.Tim19.UberApp.service.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api")
public class VehicleController {

    private VehicleService vehicleService;

    ///TODO : Treba dobaviti vozilo preko driverId
    //VEHICLE OF THE DRIVER  /api/driver/{id}/vehicle
    @GetMapping(value="/driver/{id}/vehicle")
    public ResponseEntity<VehicleDTO> getVehicle(@PathVariable Integer id) {

        Vehicle vehicle = vehicleService.findOne(id);

        // vehicle must exist
        if (vehicle == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new VehicleDTO(vehicle), HttpStatus.OK);
    }

    ///TODO : Treba dodati vozilo preko driverId
    //ADD VEHICLE TO THE DRIVER  /api/driver/{id}/vehicle
    @PostMapping
    public ResponseEntity<VehicleDTO> saveVehicle(@RequestBody VehicleDTO vehicleDTO) {

        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleType(vehicleDTO.getVehicleType());
        vehicle.setCarModel(vehicleDTO.getCarModel());
        vehicle.setLicenseNumber(vehicleDTO.getLicenseNumber());
        vehicle.setPassengerSeats(vehicleDTO.getPassengerSeats());
        vehicle.setBabyTransport(vehicleDTO.isBabyTransport());
        vehicle.setPetTransport(vehicleDTO.isBabyTransport());
        vehicle.setDriver(vehicleDTO.getDriver());

        vehicle = vehicleService.save(vehicle);
        return new ResponseEntity<>(new VehicleDTO(vehicle), HttpStatus.CREATED);
    }

    ///TODO : Treba menjati vozilo preko driverId
    //CHANGE THE VEHICLE OF THE DRIVER  /api/driver/{id}/vehicle
    @PutMapping(consumes = "application/json")
    public ResponseEntity<VehicleDTO> updateVehicle(@RequestBody VehicleDTO vehicleDTO) {

        // a driver must exist
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
        vehicle.setDriver(vehicleDTO.getDriver());

        vehicle = vehicleService.save(vehicle);
        return new ResponseEntity<>(new VehicleDTO(vehicle), HttpStatus.OK);
    }

    //CHANGE LOCATION OF THE VEHICLE  /api/vehicle/{id}/location  (vehicleId)
    @PutMapping(value = "/vehicle/{id}/location")
    public ResponseEntity<Void> changeVehicleLocation(){



        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


