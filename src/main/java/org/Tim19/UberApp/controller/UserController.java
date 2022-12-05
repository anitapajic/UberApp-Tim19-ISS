package org.Tim19.UberApp.controller;


import org.Tim19.UberApp.dto.UserDTO;
import org.Tim19.UberApp.model.Users;
import org.Tim19.UberApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/passenger")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<Map<String, Object>> getAllUsers(@RequestParam(defaultValue = "0") Integer page,
                                                           @RequestParam(defaultValue = "4") Integer size) {

        Pageable paging = PageRequest.of(page, size);
        Page<Users> pagedResult = userService.findAll(paging);

        Map<String, Object> response = new HashMap<>();
        response.put("totalcounts", pagedResult.getTotalElements());
        response.put("results", pagedResult.getContent());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Integer id) {

        Users users = userService.findOne(id);

        // user must exist
        if (users == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new UserDTO(users), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO) {

        Users users = new Users();
        users.setProfilePicture(userDTO.getProfilePicture());
        users.setTelephoneNumber(userDTO.getTelephoneNumber());
        users.setAddress(userDTO.getAddress());
        users.setEmail(userDTO.getEmail());
        users.setFirstname(userDTO.getFirstname());
        users.setLastname(userDTO.getLastname());
        users.setPassword(userDTO.getPassword());

        users = userService.save(users);
        return new ResponseEntity<>(new UserDTO(users), HttpStatus.CREATED);
    }

    @PutMapping(consumes = "application/json")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) {

        // a user must exist
        Users users = userService.findOne(userDTO.getId());

        if (users == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        users.setProfilePicture(userDTO.getProfilePicture());
        users.setTelephoneNumber(userDTO.getTelephoneNumber());
        users.setAddress(userDTO.getAddress());
        users.setEmail(userDTO.getEmail());
        users.setFirstname(userDTO.getFirstname());
        users.setLastname(userDTO.getLastname());
        users.setPassword(userDTO.getPassword());

        users = userService.save(users);
        return new ResponseEntity<>(new UserDTO(users), HttpStatus.OK);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {

        Users users = userService.findOne(id);

        if (users != null) {
            userService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
