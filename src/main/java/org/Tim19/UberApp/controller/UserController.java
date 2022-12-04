package org.Tim19.UberApp.controller;


import org.Tim19.UberApp.dto.UserDTO;
import org.Tim19.UberApp.model.Users;
import org.Tim19.UberApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/passenger")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {

        List<Users> users = userService.findAll();

        // convert users to DTOs
        List<UserDTO> usersDTO = new ArrayList<>();
        for (Users u : users) {
            usersDTO.add(new UserDTO(u));
        }

        return new ResponseEntity<>(usersDTO, HttpStatus.OK);
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
