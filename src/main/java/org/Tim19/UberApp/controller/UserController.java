package org.Tim19.UberApp.controller;


import org.Tim19.UberApp.dto.LoginDTO;
import org.Tim19.UberApp.dto.MessageDTO;
import org.Tim19.UberApp.dto.NoteDTO;
import org.Tim19.UberApp.dto.PaginatedData.*;
import org.Tim19.UberApp.model.MSGType;
import org.Tim19.UberApp.model.User;
import org.Tim19.UberApp.model.VehicleType;
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
public class UserController {

    @Autowired
    private UserService userService;

    //RIDES OF THE USER  /api/user/{id}/ride
    @GetMapping(value = "/{id}/ride")
    public ResponseEntity<Map<String, Object>> getAllRides(@PathVariable Integer id,
                                                           @RequestParam(defaultValue = "0") Integer page,
                                                           @RequestParam(defaultValue = "4") Integer size,
                                                           @RequestParam(required = false) String sort,
                                                           @RequestParam(required = false) String  from,
                                                           @RequestParam(required = false) String  to){
        List<UserPaginatedDTO> passengers = new ArrayList<>();
        passengers.add(new UserPaginatedDTO(id, "user@example.com"));
        List<PathPaginatedDTO> locations = new ArrayList<>();
        LocationPaginatedDTO departure = new LocationPaginatedDTO("Bulevar oslobodjenja 46", 45.267136, 19.833549);
        LocationPaginatedDTO destination = new LocationPaginatedDTO("Bulevar oslobodjenja 46", 45.267136, 19.833549);
        locations.add(new PathPaginatedDTO(departure, destination));
        RidePaginatedDTO ride = new RidePaginatedDTO(123, LocalDateTime.of(2022,12,7,20,15,26), LocalDateTime.of(2022,12,7,20,30,15),
                1235.00, new UserPaginatedDTO(12, "user@example.com"), passengers, 5, VehicleType.STANDARDNO, true, true, new RejectionPaginatedDTO("Ride is canceled due to previous problems with the passenger", LocalDateTime.of(2022,12,7,21,0,0)), locations);

        List<RidePaginatedDTO> rides = new ArrayList<>();
        rides.add(ride);
        Map<String, Object> response = new HashMap<>();
        response.put("totalCount", 243);
        response.put("results", rides);

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

    //USER MESSAGES  /api/user/login
    @PostMapping(value = "/login",consumes = "application/json")
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody LoginDTO loginDTO) {

//        User user = userService.findOneLogin(loginDTO.getEmail(), loginDTO.getPassword());
//
//        if (user == null) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }

        Map<String, Object> response = new HashMap<>();
        response.put("accessToken", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c");
        response.put("refreshToken","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c" );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //USER MESSAGES  /api/user/{id}/message
    @GetMapping(value = "/{id}/message")
    public ResponseEntity<Map<String, Object>> getUserMessage(@PathVariable Integer id){


        MessageDTO message1 = new MessageDTO(1,id, 123, "Message text", LocalDateTime.now(), MSGType.SUPPOR,465 );
        MessageDTO message2 = new MessageDTO(2,id, 256, "Message text2", LocalDateTime.now(), MSGType.SUPPOR,111 );

        Set<Object> messageDTOS = new HashSet<>();
        messageDTOS.add(message1);
        messageDTOS.add(message2);


        Map<String, Object> response = new HashMap<>();
        response.put("totalCount",243);
        response.put("results", messageDTOS);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    //SEND A MESSAGE TO THE USER  /api/user/{id}/message
    @PostMapping(value = "/{id}/message", consumes = "application/json")
    public ResponseEntity<MessageDTO> postUserMessage(@PathVariable Integer id, @RequestBody MessageDTO messageDTO){
        MessageDTO message = messageDTO;
        message.setId(666);
        message.setTimeOfSending(LocalDateTime.now());
        message.setSenderId(id);


        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    //BLOCKING OF THE USER  /api/user/{id}/block
    @PutMapping(value =  "/{id}/block")
    public ResponseEntity<Void> updateUser(@PathVariable Integer id) {

        // a user must exist
        User user = userService.findOne(id);

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
        User user = userService.findOne(id);

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

        NoteDTO note1 = new NoteDTO(1, LocalDateTime.now(), "Note text");
        NoteDTO note2 = new NoteDTO(2, LocalDateTime.now(), "Note text");
        Set<NoteDTO> noteDTOS = new HashSet<>();
        noteDTOS.add(note1);
        noteDTOS.add(note2);
        Map<String, Object> response = new HashMap<>();
        response.put("totalCount", noteDTOS.size());
        response.put("results", noteDTOS);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    //GETTING NOTES FOR THE USER  /api/user/{id}/note
    @PostMapping(value = "/{id}/note")
    public ResponseEntity<NoteDTO> postUserNote(@PathVariable Integer id, @RequestBody NoteDTO noteDTO){
        NoteDTO note = noteDTO;
        note.setId(666);
        note.setDate(LocalDateTime.now());

        return new ResponseEntity<>(note, HttpStatus.OK);
    }

}
