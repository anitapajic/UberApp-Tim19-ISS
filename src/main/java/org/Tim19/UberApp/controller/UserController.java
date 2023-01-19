package org.Tim19.UberApp.controller;


import org.Tim19.UberApp.dto.MessageDTO;
import org.Tim19.UberApp.dto.NoteDTO;
import org.Tim19.UberApp.dto.ResetPasswordDTO;
import org.Tim19.UberApp.model.Note;
import org.Tim19.UberApp.model.ResetCode;
import org.Tim19.UberApp.model.Ride;
import org.Tim19.UberApp.model.User;
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
            return new ResponseEntity<>("User does not exist!", HttpStatus.NOT_FOUND);
        }
        if( resetPasswordDTO.getNewPassword() == null){
            return new ResponseEntity<>("Current password is not matching", HttpStatus.BAD_REQUEST);
        }
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!passwordEncoder.matches(resetPasswordDTO.getOldPassword(), user.getPassword())){
            Map<String, String> resp = new HashMap<>();
            resp.put("message", "Current password is not matching!");
            return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
        }
        user.setPassword(passwordEncoder.encode(resetPasswordDTO.getNewPassword()));
        userService.save(user);

        return new ResponseEntity<>("Password successfully changed!", HttpStatus.NO_CONTENT);
    }

    //RESET PASSWORD OF A USER /api/user/{id}/resetPassword
    @GetMapping(value = "/{id}/resetPassword")
    public ResponseEntity getResetPassword(@PathVariable Integer id, @RequestParam String username) throws MessagingException, UnsupportedEncodingException {
        // User user = userService.findOneById(id);
        User user= userService.findOneByUsername(username);
        if (user == null) {
            return new ResponseEntity<>("User does not exist!",HttpStatus.NOT_FOUND);
        }
        Integer code = this.randInt();
        this.sendResetCode(code, user.getUsername());

        ResetCode rc = new ResetCode(code, user.getUsername(), LocalDateTime.now());

        this.userService.save(rc);
        return new ResponseEntity<>("Code successfully sent.",HttpStatus.NO_CONTENT);
    }

    private void sendResetCode(Integer code, String username) throws MessagingException, UnsupportedEncodingException {
        String subject = "Reset password code";
        String senderName = "TAAXI";

        String mailContent = "<p>Dear,"+ username +" </p>";
        mailContent +="<p>Your code is:" + code + "</p>";
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
    public ResponseEntity resetPassword(@PathVariable Integer id, @RequestBody ResetPasswordDTO resetPasswordDTO){

        // User user = userService.findOneById(id);
        User user = userService.findOneByUsername(resetPasswordDTO.getUsername());
        if(user == null){
            return new ResponseEntity<>("User does not exist!", HttpStatus.NOT_FOUND);
        }

        ResetCode resetCode = this.userService.findCode(user.getUsername());


        if (checkCode(resetCode, resetPasswordDTO)){
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            user.setPassword(passwordEncoder.encode(resetPasswordDTO.getNewPassword()));
            userService.save(user);
            userService.delete(resetCode);
            return new ResponseEntity<>("Password successfully changed!", HttpStatus.OK);
        }
        Map<String, String> resp = new HashMap<>();
        resp.put("message", "Code is expired or not correct!");

        return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);

    }

    private Boolean checkCode(ResetCode resetCode, ResetPasswordDTO resetPassword){
        if (resetCode == null){
            return false;
        }
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
        Map<String, Object> response = new HashMap<>();

        Integer hasRides = rideService.checkAllByUserId(id);
        if (hasRides == 0){
            return new ResponseEntity<>("User does not exist!",HttpStatus.NOT_FOUND);
        }
        if(hasRides/size < page){
            response.put("results", false);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }

        Page<Ride> allRides = rideService.findByUserId(id, paging);

        if (allRides.isEmpty()){
            return new ResponseEntity<>("User does not exist!", HttpStatus.NOT_FOUND);
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
            return new ResponseEntity<>("User does not exist!", HttpStatus.NOT_FOUND);
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
        if(messageDTO.getText() == null && messageDTO.getReceiverId() == null && messageDTO.getType() == null){
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);

        }
        String text = messageDTO.getText();
        messageDTO.setSenderId(id);
        messageDTO = messageService.save(messageDTO);

        if (messageDTO.getText() != text){
            return new ResponseEntity<>(messageDTO.getText(), HttpStatus.NOT_FOUND);
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
        HashMap<String, String> resp = new HashMap<>();

        if (user == null) {
            resp.put("message", "User does not exist!");
            return new ResponseEntity<>(resp,HttpStatus.NOT_FOUND);
        }
        if (user.getBlocked()){
            resp.put("message", "User already blocked!");
            return new ResponseEntity<>(resp,HttpStatus.BAD_REQUEST);
        }

        user.setBlocked(true);

        userService.save(user);
        resp.put("message", "User is successfully blocked");

        return new ResponseEntity<>(resp,HttpStatus.NO_CONTENT);
    }

    //UNBLOCKING USER  /api/user/{id}/unblock
    @PutMapping(
            value = "/{id}/unblock",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity unblockUser(@PathVariable Integer id) {
        HashMap<String, String> resp = new HashMap<>();

        // a user must exist
        User user = userService.findOneById(id);

        if (user == null) {
            resp.put("message", "User does not exist!");
            return new ResponseEntity<>(resp,HttpStatus.NOT_FOUND);
        }
        if (!user.getBlocked()){
            resp.put("message", "User is not blocked!");
            return new ResponseEntity<>(resp,HttpStatus.BAD_REQUEST);        }

        user.setBlocked(false);

        userService.save(user);
        resp.put("message", "User is successfully unblocked");

        return new ResponseEntity<>(resp,HttpStatus.NO_CONTENT);    }

    //NOTE CREATING  /api/user/{id}/note
    @GetMapping(
            value = "/{id}/note",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity getUserNotes(@PathVariable Integer id){

        List<Note> notes = noteService.findAllByUserId(id);
        if(notes.isEmpty()){
            return new ResponseEntity<>("User does not exist!",HttpStatus.NOT_FOUND);

        }

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
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity postUserNote(@PathVariable Integer id, @RequestBody NoteDTO noteDTO, BindingResult binding){
        if (binding.hasErrors()) {
            return new ResponseEntity<>(binding.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }
        NoteDTO note = noteDTO;
        note.setUserId(id);
        note.setDate(LocalDateTime.now());
        if(note.getMessage() == null){
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);

        }

        note = noteService.save(noteDTO);

        if (note.getMessage() == null){
            return new ResponseEntity<>("User does not exist!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(note, HttpStatus.OK);
    }

}
