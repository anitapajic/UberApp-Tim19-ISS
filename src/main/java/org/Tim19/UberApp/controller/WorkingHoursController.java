package org.Tim19.UberApp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/driver")
public class WorkingHoursController {



    @GetMapping(value = "/{id}/working-hours")
    public ResponseEntity<Void> getDriversWH(){



        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping(value = "/{id}/working-hours")
    public ResponseEntity<Void> postDriversWH(){



        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/working-hours/{wh-id}")
    public ResponseEntity<Void> getWHDetails(){



        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping(value = "/working-hours/{wh-id}")
    public ResponseEntity<Void> chaneWHDetails(){



        return new ResponseEntity<>(HttpStatus.OK);
    }
}
