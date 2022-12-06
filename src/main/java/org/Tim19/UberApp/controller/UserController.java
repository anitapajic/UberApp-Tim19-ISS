package org.Tim19.UberApp.controller;


import org.Tim19.UberApp.model.User;
import org.Tim19.UberApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    //RIDES OF THE USER  /api/user/{id}/ride
    @GetMapping(value = "/{id}/ride")
    public ResponseEntity<Void> getUserRides(){



        return new ResponseEntity<>(HttpStatus.OK);
    }

    //GETTING USER DETAILS  /api/user
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllUsers(@RequestParam(defaultValue = "0") Integer page,
                                                           @RequestParam(defaultValue = "4") Integer size) {
        Pageable paging = PageRequest.of(page, size);
        Page<User> pagedResult = userService.findAll(paging);

        Map<String, Object> response = new HashMap<>();
        response.put("totalcounts", pagedResult.getTotalElements());
        response.put("results", pagedResult.getContent());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //USER MESSAGES  /api/user/{id}/message
    @GetMapping(value = "/{id}/message")
    public ResponseEntity<Void> getUserMessage(){



        return new ResponseEntity<>(HttpStatus.OK);
    }

    //SEND A MESSAGE TO THE USER  /api/user/{id}/message
    @PostMapping(value = "/{id}/message")
    public ResponseEntity<Void> postUserMessage(){



        return new ResponseEntity<>(HttpStatus.OK);
    }

    //BLOCKING OF THE USER  /api/user/{id}/block
    @PutMapping(value =  "/{id}/block")
    public ResponseEntity<Void> updateUser(@PathVariable Integer id) {

        // a user must exist
        Users user = userService.findOne(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        user.setBlocked(true);

        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //UNBLOCKING USER  /api/user/{id}/unblock
    @PutMapping(value = "/{id}/unblock")
    public ResponseEntity<Void> unblockUser(@PathVariable Integer id) {

        // a user must exist
        Users user = userService.findOne(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        user.setBlocked(false);

        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //NOTE CREATING  /api/user/{id}/note
    @GetMapping(value = "/{id}/note")
    public ResponseEntity<Void> getUserNote(){



        return new ResponseEntity<>(HttpStatus.OK);
    }

    //GETTING NOTES FOR THE USER  /api/user/{id}/note
    @PostMapping(value = "/{id}/note")
    public ResponseEntity<Void> postUserNote(){



        return new ResponseEntity<>(HttpStatus.OK);
    }
}
