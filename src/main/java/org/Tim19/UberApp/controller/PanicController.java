package org.Tim19.UberApp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/panic")

public class PanicController {
    @GetMapping()
    public ResponseEntity<Void> getAllPanics(){



        return new ResponseEntity<>(HttpStatus.OK);
    }
}