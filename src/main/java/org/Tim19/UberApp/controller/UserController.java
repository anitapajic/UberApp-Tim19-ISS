package org.Tim19.UberApp.controller;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.Tim19.UberApp.configuration.WebSecurityConfiguration;
import org.Tim19.UberApp.dto.LoginDTO;
import org.Tim19.UberApp.dto.MessageDTO;
import org.Tim19.UberApp.dto.NoteDTO;
import org.Tim19.UberApp.dto.ResetPasswordDTO;
import org.Tim19.UberApp.exceptions.BadRequestException;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
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
    @Autowired
    private JavaMailSender mailSender;


    //CHANGE PASSWORD OF A USER /api/user/{id}/changePassword
    @PutMapping(
            value = "/{id}/changePassword",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAnyAuthority('DRIVER', 'PASSENGER', 'ADMIN')")
    public ResponseEntity changePassword(@PathVariable Integer id, @RequestBody ResetPasswordDTO resetPasswordDTO){
        User user = userService.findOneById(id);
        if( user == null){
            return new ResponseEntity<>("User does not exist", HttpStatus.NOT_FOUND);
        }

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!passwordEncoder.matches(resetPasswordDTO.getOldPassword(), user.getPassword())){
            return new ResponseEntity<>("Current password is not matching", HttpStatus.BAD_REQUEST);
        }
        user.setPassword(passwordEncoder.encode(resetPasswordDTO.getNewPassword()));
        userService.save(user);

        return new ResponseEntity<>("Password successfully changed!", HttpStatus.NO_CONTENT);
    }

    //RESET PASSWORD OF A USER /api/user/{id}/resetPassword
    @GetMapping(value = "/{id}/resetPassword")
    public ResponseEntity getResetPassword(@RequestParam String username) throws MessagingException, UnsupportedEncodingException {

        Optional<User> user = userService.findOneByUsername(username);
        if (user.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Integer code = this.randInt();
        this.sendResetCode(code, username);

        ResetCode rc = new ResetCode(code, username, LocalDateTime.now());

        this.userService.save(rc);
        return new ResponseEntity<>("Code successfully sent.",HttpStatus.NO_CONTENT);
    }

    private void sendResetCode(Integer code, String username) throws MessagingException, UnsupportedEncodingException {
        String subject = "Reset password code";
        String senderName = "TAAXI";

        String mailContent = "<p>Dear,"+ username +" </p>";
        mailContent +="<p>Your code is:" + code + "</p>";
        mailContent +="<p>Please click the link below to change your password:</p>";
        mailContent +="<h3><a href=\"" + "http://localhost:4200/reset-code\">CHANGE PASSWORD</a></h3>";
        mailContent +="<p>Thank you<br>TAAXI Team</p>";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("UberAppTim19@gmail.com", senderName);
        helper.setTo("tamara_dzambic@hotmail.com"); //ovde treba mail korisnika
        helper.setSubject(subject);
        helper.setText(mailContent, true);

        mailSender.send(message);
    }

    private Integer randInt(){
        double r = Math.random();
        int randomNum = (int)(r * (9999 - 1000)) + 1000;
        return randomNum;
    }

    //CHANGE PASSWORD OF A USER /api/user/{id}/resetPassword
    @PutMapping(value = "/{id}/resetPassword")
    public ResponseEntity resetPassword( @RequestBody ResetPasswordDTO resetPasswordDTO){

        Optional<User> u = userService.findOneByUsername(resetPasswordDTO.getUsername());
        if(u.isEmpty()){
            return new ResponseEntity<>("User does not exist!", HttpStatus.NOT_FOUND);
        }

        User user = u.get();
        ResetCode rc = this.userService.findCode(resetPasswordDTO.getUsername());


        if (checkCode(rc, resetPasswordDTO)){
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            user.setPassword(passwordEncoder.encode(resetPasswordDTO.getNewPassword()));
            userService.save(user);
            userService.delete(rc);
            return new ResponseEntity<>("Password successfully changed!", HttpStatus.OK);
        }

        return new ResponseEntity<>("Code is expired or not correct!", HttpStatus.BAD_REQUEST);

    }

    private Boolean checkCode(ResetCode resetCode, ResetPasswordDTO resetPassword){

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime codeExp = resetCode.getDate().plusMinutes(15);

        if (now.isAfter(codeExp)){
            this.userService.delete(resetCode);
            return false;
        }

        else return resetCode.getUsername().equals(resetPassword.getUsername())
                    && resetCode.getCode().equals(resetPassword.getCode());
    }

    //RIDES OF THE USER  /api/user/{id}/ride
    @GetMapping(
            value = "/{id}/ride",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity getAllRides(@PathVariable Integer id,
                                                           @RequestParam(defaultValue = "0") Integer page,
                                                           @RequestParam(defaultValue = "4") Integer size,
                                                           @RequestParam(required = false) String sort,
                                                           @RequestParam(required = false) String  from,
                                                           @RequestParam(required = false) String  to){

        Pageable paging = PageRequest.of(page, size);

        Page<Ride> allRides = rideService.findByUserId(id, paging);
        Map<String, Object> response = new HashMap<>();

        if (allRides.isEmpty()){
            return new ResponseEntity<>("User does not exist", HttpStatus.NOT_FOUND);
        }
        response.put("totalCount", allRides.getTotalElements());
        response.put("results", allRides.getContent());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //GETTING USER DETAILS  /api/user
    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity getAllUsers( @RequestParam(defaultValue = "0") Integer page,
                                       @RequestParam(defaultValue = "4") Integer size,
                                       @RequestParam(required = false) String sort,
                                       @RequestParam(required = false) String  from,
                                       @RequestParam(required = false) String  to){

        Pageable paging = PageRequest.of(page, size);
        Page<User> pagedResult = userService.findAll(paging);

        Map<String, Object> response = new HashMap<>();
        response.put("totalCount", pagedResult.getTotalElements());
        response.put("results", pagedResult.getContent());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    //USER MESSAGES  /api/user/{id}/message
    @GetMapping(
            value = "/{id}/message",
            produces = MediaType.APPLICATION_JSON_VALUE

    )
    @PreAuthorize("hasAnyAuthority('DRIVER', 'PASSENGER', 'ADMIN')")
    public ResponseEntity getUserMessage(@PathVariable Integer id){

        List<MessageDTO> messages = messageService.findAllByUserId(id);

        if (messages.isEmpty()){
            return new ResponseEntity<>("User does not exist", HttpStatus.NOT_FOUND);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("totalCount",messages.size());
        response.put("results", messages);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    //SEND A MESSAGE TO THE USER  /api/user/{id}/message
    @PostMapping(
            value = "/{id}/message",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAnyAuthority('DRIVER', 'PASSENGER', 'ADMIN')")
    public ResponseEntity postUserMessage(@PathVariable Integer id, @RequestBody MessageDTO messageDTO, BindingResult binding){
        if (binding.hasErrors()) {
            return new ResponseEntity<>(binding.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }
        messageDTO.setSenderId(id);
        messageDTO = messageService.save(messageDTO);

        if (messageDTO.getText() == null){
            return new ResponseEntity<>("User/Receiver/Ride does not exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(messageDTO, HttpStatus.OK);
    }

    //BLOCKING OF THE USER  /api/user/{id}/block
    @PutMapping(
            value =  "/{id}/block",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity blockUser(@PathVariable Integer id) {

        // a user must exist
        User user = userService.findOneById(id);

        if (user == null) {
            return new ResponseEntity<>("User does not exist!",HttpStatus.NOT_FOUND);
        }
        if (user.getBlocked()){
            return new ResponseEntity<>(" User already blocked!",HttpStatus.BAD_REQUEST);
        }

        user.setBlocked(true);

        userService.save(user);
        return new ResponseEntity<>("User is successfully blocked",HttpStatus.NO_CONTENT);
    }

    //UNBLOCKING USER  /api/user/{id}/unblock
    @PutMapping(
            value = "/{id}/unblock",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity unblockUser(@PathVariable Integer id) {

        // a user must exist
        User user = userService.findOneById(id);

        if (user == null) {
            return new ResponseEntity<>("User does not exist!",HttpStatus.NOT_FOUND);
        }
        if (!user.getBlocked()){
            return new ResponseEntity<>(" User is not blocked!",HttpStatus.BAD_REQUEST);
        }

        user.setBlocked(false);

        userService.save(user);
        return new ResponseEntity<>("User is successfully unblocked",HttpStatus.NO_CONTENT);
    }

    //NOTE CREATING  /api/user/{id}/note
    @GetMapping(
            value = "/{id}/note",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('DRIVER', 'PASSENGER', 'ADMIN')")
    public ResponseEntity getUserNotes(@PathVariable Integer id){

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
    @PostMapping(
            value = "/{id}/note",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('DRIVER', 'PASSENGER', 'ADMIN')")
    public ResponseEntity postUserNote(@PathVariable Integer id, @RequestBody NoteDTO noteDTO, BindingResult binding){
        if (binding.hasErrors()) {
            return new ResponseEntity<>(binding.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }
        NoteDTO note = noteDTO;
        note.setUserId(id);
        note.setDate(LocalDateTime.now());
        note = noteService.save(noteDTO);

        if (note.getMessage() == null){
            return new ResponseEntity<>("User does not exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(note, HttpStatus.OK);
    }

}
