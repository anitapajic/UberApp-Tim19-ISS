package org.Tim19.UberApp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/unregisteredUser")

public class UnregisteredUserController {
    @PostMapping(consumes = "application/json")
    public ResponseEntity<Void> calculateRide(){



        return new ResponseEntity<>(HttpStatus.OK);
    }
}
