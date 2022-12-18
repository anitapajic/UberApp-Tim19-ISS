package org.Tim19.UberApp.controller;

import org.Tim19.UberApp.dto.DriverDTO;
import org.Tim19.UberApp.dto.PaginatedData.LocationPaginatedDTO;
import org.Tim19.UberApp.dto.PaginatedData.Vehicle3PaginatedDTO;
import org.Tim19.UberApp.dto.PaginatedData.VehiclePaginated2DTO;
import org.Tim19.UberApp.dto.VehicleDTO;
import org.Tim19.UberApp.model.Driver;
import org.Tim19.UberApp.model.Vehicle;
import org.Tim19.UberApp.model.VehicleType;
import org.Tim19.UberApp.service.DriverService;
import org.Tim19.UberApp.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;


@RestController
@RequestMapping(value = "/api")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private DriverService driverService;

    //VEHICLE OF THE DRIVER  /api/driver/{id}/vehicle
    @GetMapping(value="/driver/{id}/vehicle")
    public ResponseEntity<VehiclePaginated2DTO> getVehicle(@PathVariable Integer id) {

        LocationPaginatedDTO location = new LocationPaginatedDTO("Bulevar oslobodjenja 46",45.267136,19.833549);
        VehiclePaginated2DTO vehicle = new VehiclePaginated2DTO(1,id,"audi",VehicleType.STANDARDNO ,"NS 123-AB",location,4,true,true);

        return new ResponseEntity<>(vehicle, HttpStatus.OK);
    }

    //ADD VEHICLE TO THE DRIVER  /api/driver/{id}/vehicle
    @PostMapping(value="/driver/{id}/vehicle", consumes = "application/json")
    public ResponseEntity<VehicleDTO> saveVehicle(@RequestBody VehicleDTO vehicleDTO) {

        Driver driver = new Driver(111111,"tamara116@gmail.com","tamara","dzambic","ahhajhsjah","0645554454","Brace Ribnikar 17","tam123",true,false,new HashSet<>(),new HashSet<>(),null);
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleType(vehicleDTO.getVehicleType());
        vehicle.setCarModel(vehicleDTO.getModel());
        vehicle.setLicenseNumber(vehicleDTO.getLicenseNumber());
        vehicle.setPassengerSeats(vehicleDTO.getPassengerSeats());
        vehicle.setBabyTransport(vehicleDTO.isBabyTransport());
        vehicle.setPetTransport(vehicleDTO.isBabyTransport());
        vehicle.setDriver(null);

        driver.setVehicle(vehicle);
        vehicle = vehicleService.save(vehicle);
        driverService.save(driver);
        VehicleDTO response = new VehicleDTO(vehicle);
        response.setCurrentLocation(vehicleDTO.getCurrentLocation());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    ///TODO : Treba menjati vozilo preko driverId
    //CHANGE THE VEHICLE OF THE DRIVER  /api/driver/{id}/vehicle
    @PutMapping(value="/driver/{id}/vehicle",consumes = "application/json")
    public ResponseEntity<VehiclePaginated2DTO> updateVehicle(@PathVariable Integer id,@RequestBody VehiclePaginated2DTO update) {

        update.setDriverId(id);
        update.setId(1);

        return new ResponseEntity<>(update, HttpStatus.OK);
    }



//        LocationPaginatedDTO location = new LocationPaginatedDTO("Bulevar oslobodjenja 46",45.267136,19.833549);
//        VehiclePaginated2DTO vehicle = new VehiclePaginated2DTO(1,id,"audi",VehicleType.STANDARDNO ,"NS 123-AB",location,4,true,true);

//
//        // a driver must exist
//        Vehicle vehicle = vehicleService.findOne(vehicleDTO.getId());
//
//        if (vehicle == null) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//
//        vehicle.setVehicleType(vehicleDTO.getVehicleType());
//        vehicle.setCarModel(vehicleDTO.getCarModel());
//        vehicle.setLicenseNumber(vehicleDTO.getLicenseNumber());
//        vehicle.setPassengerSeats(vehicleDTO.getPassengerSeats());
//        vehicle.setBabyTransport(vehicleDTO.isBabyTransport());
//        vehicle.setPetTransport(vehicleDTO.isBabyTransport());
//        vehicle.setDriver(vehicleDTO.getDriver());
//
//        vehicle = vehicleService.save(vehicle);
//        return new ResponseEntity<>(new VehicleDTO(vehicle), HttpStatus.OK);


    //CHANGE LOCATION OF THE VEHICLE  /api/vehicle/{id}/location  (vehicleId)
    @PutMapping(value = "/vehicle/{id}/location")
    public ResponseEntity<Void> changeVehicleLocation(){

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //TODO: refactor dtos to following builder pattern
//    public void newBuilderTestClass(){
//        DriverDTO driverDTO =
//                DriverDTO.newBuilder()
//                        .withDocuments(null)
//                        .withVehicle(null)
//                        .withRides(null)
//                        .build();
//    }
}


