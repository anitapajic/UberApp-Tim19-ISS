package org.Tim19.UberApp.controller;

import org.Tim19.UberApp.dto.PaginatedData.*;
import org.Tim19.UberApp.dto.PanicDTO;
import org.Tim19.UberApp.dto.PanicUserDTO;
import org.Tim19.UberApp.model.VehicleType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping(value = "/api/panic")

public class PanicController {
    @GetMapping()
    public ResponseEntity<Map<String, Object>> getAllPanics(){
        Map<String, Object> response = new HashMap<>();
        response.put("totalCount", 243);

        List<PanicDTO> results = new ArrayList<>();
        PanicUserDTO user = new PanicUserDTO("pera.peric@email.com","Pera", "Peric", "U3dhZ2dlciByb2Nrcw==", "+381123123","Bulevar Oslobodjenja 74");

        Set<UserPaginatedDTO> passengers = new HashSet<>();
        passengers.add(new UserPaginatedDTO(16, "user@example.com"));

        Set<PathPaginatedDTO> locations = new HashSet<>();
        LocationPaginatedDTO departure = new LocationPaginatedDTO("Bulevar oslobodjenja 46", 45.267136, 19.833549);
        LocationPaginatedDTO destination = new LocationPaginatedDTO("Bulevar oslobodjenja 46", 45.267136, 19.833549);
        locations.add(new PathPaginatedDTO(departure, destination));

        RidePaginatedDTO ride = new RidePaginatedDTO(123, LocalDateTime.of(2022,12,7,20,15,26), LocalDateTime.of(2022,12,7,20,30,15),
                1235.00, new UserPaginatedDTO(12, "user@example.com"), passengers, 5, VehicleType.STANDARDNO, true, true, new RejectionPaginatedDTO("Ride is canceled due to previous problems with the passenger", LocalDateTime.of(2022,12,7,21,0,0)), locations, "PENDING");


        PanicDTO panic = new PanicDTO(123,user,ride,LocalDateTime.now(),"Driver is drinking while drivig" );

        results.add(panic);

        response.put("results", results);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}