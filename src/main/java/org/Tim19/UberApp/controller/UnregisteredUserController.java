package org.Tim19.UberApp.controller;

import org.Tim19.UberApp.dto.PaginatedData.CreateRideBodyPaginatedDTO;
import org.Tim19.UberApp.dto.RideAssumptionDTO;
import org.Tim19.UberApp.dto.RideDTO;
import org.Tim19.UberApp.model.Ride;
import org.Tim19.UberApp.service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/unregisteredUser")
@CrossOrigin(value = "*")
public class UnregisteredUserController {

    @Autowired
    private RideService rideService;
    @PostMapping(consumes = "application/json")
    public ResponseEntity<Map<String, Object>> calculateRide(@RequestBody CreateRideBodyPaginatedDTO rideDTO){

        List<Float> coordinates = rideDTO.getCoordinates();
        Float long1 = coordinates.get(0);
        Float long2 = coordinates.get(1);
        Float lat1 = coordinates.get(2);
        Float lat2 = coordinates.get(3);


        Double totalCost = rideService.calculatePrice(rideDTO.getVehicleType(), rideService.calculateKilometres(long1, long2, lat1, lat2), rideDTO.isBabyTransport(), rideDTO.isPetTransport());
        Integer estimatedMinutes = rideService.calculateTravelTime(rideService.calculateKilometres(long1, long2, lat1, lat2));



        Map<String, Object> response = new HashMap<>();
        response.put("estimatedTimeInMinutes", estimatedMinutes);
        response.put("estimatedCost", totalCost);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
