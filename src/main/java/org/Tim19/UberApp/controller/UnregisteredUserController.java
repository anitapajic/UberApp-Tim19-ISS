package org.Tim19.UberApp.controller;

import org.Tim19.UberApp.dto.RideAssumptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/unregisteredUser")
@CrossOrigin(value = "*")
public class UnregisteredUserController {
    @PostMapping(consumes = "application/json")
    public ResponseEntity<Map<String, Object>> calculateRide(@RequestBody RideAssumptionDTO rideDTO){

        Map<String, Object> response = new HashMap<>();
        response.put("estimatedTimeInMinutes", 10);
        response.put("estimatedCost", 450);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
