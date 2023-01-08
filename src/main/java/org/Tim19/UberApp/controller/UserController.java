package org.Tim19.UberApp.controller;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.Tim19.UberApp.dto.LoginDTO;
import org.Tim19.UberApp.dto.MessageDTO;
import org.Tim19.UberApp.dto.NoteDTO;
import org.Tim19.UberApp.model.*;
import org.Tim19.UberApp.service.MessageService;
import org.Tim19.UberApp.service.NoteService;
import org.Tim19.UberApp.service.RideService;
import org.Tim19.UberApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping(value = "/api/user")
@CrossOrigin(value="*")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private NoteService noteService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private RideService rideService;


    //RIDES OF THE USER  /api/user/{id}/ride
    @GetMapping(value = "/{id}/ride")
    public ResponseEntity<Map<String, Object>> getAllRides(@PathVariable Integer id,
                                                           @RequestParam(defaultValue = "0") Integer page,
                                                           @RequestParam(defaultValue = "4") Integer size,
                                                           @RequestParam(required = false) String sort,
                                                           @RequestParam(required = false) String  from,
                                                           @RequestParam(required = false) String  to){


        Set<Ride> allRides = rideService.findByUserId(id);

        Map<String, Object> response = new HashMap<>();
        response.put("totalCount", allRides.size());
        response.put("results", allRides);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //GETTING USER DETAILS  /api/user
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllUsers(@RequestParam(defaultValue = "0") Integer page,
                                                           @RequestParam(defaultValue = "2") Integer size) {
        Pageable paging = PageRequest.of(page, size);
        Page<User> pagedResult = userService.findAll(paging);

        Map<String, Object> response = new HashMap<>();
        response.put("totalCount", pagedResult.getTotalElements());
        response.put("results", pagedResult.getContent());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    //USER MESSAGES  /api/user/{id}/message
    @GetMapping(value = "/{id}/message")
    public ResponseEntity<Map<String, Object>> getUserMessage(@PathVariable Integer id){


        List<Message> messages = messageService.findAllByUserId(id);
        Set<MessageDTO> messageDTOS = new HashSet<>();

        for (Message m: messages) {
            MessageDTO messageDTO = new MessageDTO(m);
            messageDTOS.add(messageDTO);
        }



        Map<String, Object> response = new HashMap<>();
        response.put("totalCount",messageDTOS.size());
        response.put("results", messageDTOS);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    //SEND A MESSAGE TO THE USER  /api/user/{id}/message
    @PostMapping(value = "/{id}/message", consumes = "application/json")
    public ResponseEntity<MessageDTO> postUserMessage(@PathVariable Integer id, @RequestBody MessageDTO messageDTO){
        MessageDTO message = messageDTO;
        message.setSenderId(id);

        message = messageService.save(messageDTO);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    //BLOCKING OF THE USER  /api/user/{id}/block
    @PutMapping(value =  "/{id}/block")
    public ResponseEntity<Void> updateUser(@PathVariable Integer id) {

        // a user must exist
        User user = userService.findOneById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        user.setBlocked(true);

        userService.save(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //UNBLOCKING USER  /api/user/{id}/unblock
    @PutMapping(value = "/{id}/unblock")
    public ResponseEntity<Void> unblockUser(@PathVariable Integer id) {

        // a user must exist
        User user = userService.findOneById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        user.setBlocked(false);

        userService.save(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //NOTE CREATING  /api/user/{id}/note
    @GetMapping(value = "/{id}/note")
    public ResponseEntity<Map<String, Object>> getUserNotes(@PathVariable Integer id){

        List<Note> notes = noteService.findAllByUserId(id);
        Set<NoteDTO> noteDTOS = new HashSet<>();

        for (Note n: notes) {
            NoteDTO noteDTO = new NoteDTO(n);
            noteDTOS.add(noteDTO);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("totalCount", noteDTOS.size());
        response.put("results", noteDTOS);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    //GETTING NOTES FOR THE USER  /api/user/{id}/note
    @PostMapping(value = "/{id}/note")
    public ResponseEntity<NoteDTO> postUserNote(@PathVariable Integer id, @RequestBody NoteDTO noteDTO){
        NoteDTO note = noteDTO;
        note.setUserId(id);
        note.setDate(LocalDateTime.now());
        note = noteService.save(noteDTO);

        return new ResponseEntity<>(note, HttpStatus.OK);
    }

}
