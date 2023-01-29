package org.Tim19.UberApp.controller;

import org.Tim19.UberApp.dto.LoginDTO;
import org.Tim19.UberApp.dto.TokenDTO;
import org.Tim19.UberApp.exceptions.BadRequestException;
import org.Tim19.UberApp.model.User;
import org.Tim19.UberApp.security.SecurityUser;
import org.Tim19.UberApp.security.TokenUtils;
import org.Tim19.UberApp.service.UserDetailsServiceImpl;
import org.Tim19.UberApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(value = "*")
@RestController
public class AuthenticationController {

    private AuthenticationManager authenticationManager;

    private UserDetailsServiceImpl userDetailsService;

    private UserService userService;

    private TokenUtils tokenUtils;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    public AuthenticationController(
            AuthenticationManager authenticationManager,
            UserDetailsServiceImpl userDetailsService,
            UserService userService,
            TokenUtils tokenUtils

    ) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.tokenUtils = tokenUtils;
        this.userService = userService;
    }


    @PostMapping(
            value = "api/user/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity loginUser(@RequestBody LoginDTO login, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            throw new BadRequestException("Unauthorized!");
        }

        try {
            TokenDTO token = new TokenDTO();
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(login.getUsername());

            String tokenValue = this.tokenUtils.generateToken(userDetails);
            token.setToken(tokenValue);

            User user = this.userService.findIdByUsername(login.getUsername());
            if(user.getBlocked()){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if(user.getAuthorities().equals("DRIVER")){
                user.setActive(true);
                userService.save(user);
                this.simpMessagingTemplate.convertAndSend("/map-updates/update-activity", user);
            }
            token.setId(user.getId());
            token.setRole(user.getAuthorities());

            Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return new ResponseEntity<>(token, HttpStatus.OK);
        } catch (BadCredentialsException e) {
            Map<String, String> response = new HashMap<>(){{put("message", e.getMessage());}};
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(
            value = "api/user/logout",
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    public ResponseEntity logoutUser() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        SecurityUser sUser = (SecurityUser) auth.getPrincipal();
        User user = this.userService.findIdByUsername(sUser.getUsername());

        if(user.getAuthorities().equals("DRIVER")){
            user.setActive(false);
            userService.save(user);
            this.simpMessagingTemplate.convertAndSend("/map-updates/update-activity", user);
        }

        if (!(auth instanceof AnonymousAuthenticationToken)){
            SecurityContextHolder.clearContext();
            this.simpMessagingTemplate.convertAndSend("/map-updates/delete-all-rides", "logout");

            return new ResponseEntity<>("You successfully logged out!", HttpStatus.OK);
        } else {
            throw new BadRequestException("User is not authenticated!");
        }

    }

}
