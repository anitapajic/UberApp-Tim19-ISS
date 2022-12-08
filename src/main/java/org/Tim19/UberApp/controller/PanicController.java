package org.Tim19.UberApp.controller;

import org.Tim19.UberApp.dto.PaginatedData.*;
import org.Tim19.UberApp.model.VehicleType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/panic")

public class PanicController {
    @GetMapping()
    public ResponseEntity<Map<String, Object>> getAllPanics(){
//        List<UserPaginatedDTO> passengers = new ArrayList<>();
//        passengers.add(new UserPaginatedDTO(999, "user@example.com"));
//        List<PathPaginatedDTO> locations = new ArrayList<>();
//        LocationPaginatedDTO departure = new LocationPaginatedDTO("Bulevar oslobodjenja 46", 45.267136, 19.833549);
//        LocationPaginatedDTO destination = new LocationPaginatedDTO("Bulevar oslobodjenja 46", 45.267136, 19.833549);
//        locations.add(new PathPaginatedDTO(departure, destination));
//        RidePaginatedDTO ride = new RidePaginatedDTO(123, LocalDateTime.of(2022,12,7,20,15,26), LocalDateTime.of(2022,12,7,20,30,15),
//                1235.00, new UserPaginatedDTO(12, "user@example.com"), passengers, 5, VehicleType.STANDARD, true, true, new RejectionPaginatedDTO("Ride is canceled due to previous problems with the passenger", LocalDateTime.of(2022,12,7,21,0,0)), locations, "PENDING");


        Map<String, Object> response = new HashMap<>();
//        response.put("totalcounts", 243);
//        response.put("results", ride);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}