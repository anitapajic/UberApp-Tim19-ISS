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

    @GetMapping()
    public ResponseEntity<Map<String, Object>> getAllUsers(@RequestParam(defaultValue = "0") Integer page,
                                                           @RequestParam(defaultValue = "4") Integer size) {

        Pageable paging = PageRequest.of(page, size);
        Page<User> pagedResult = userService.findAll(paging);

        Map<String, Object> response = new HashMap<>();
        response.put("totalcounts", pagedResult.getTotalElements());
        response.put("results", pagedResult.getContent());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    @GetMapping(value = "/{id}")
//    public ResponseEntity<UserDTO> getUser(@PathVariable Integer id) {
//
//        User users = userService.findOne(id);
//
//        // user must exist
//        if (users == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        return new ResponseEntity<>(new UserDTO(users), HttpStatus.OK);
//    }


    @PutMapping(value =  "/{id}/block")
    public ResponseEntity<Void> updateUser(@PathVariable Integer id) {

        // a user must exist
        User user = userService.findOne(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        user.setBlocked(true);

        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/{id}/unblock")
    public ResponseEntity<Void> unblockUser(@PathVariable Integer id) {

        // a user must exist
        User user = userService.findOne(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        user.setBlocked(false);

        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {

        User user = userService.findOne(id);

        if (user != null) {
            userService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
