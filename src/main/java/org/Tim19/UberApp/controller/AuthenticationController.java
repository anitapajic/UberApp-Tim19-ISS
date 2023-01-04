package org.Tim19.UberApp.controller;

import org.Tim19.UberApp.dto.LoginDTO;
import org.Tim19.UberApp.dto.TokenDTO;
import org.Tim19.UberApp.exceptions.BadRequestException;
import org.Tim19.UberApp.security.TokenUtils;
import org.Tim19.UberApp.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(value = "*")
@RestController
public class AuthenticationController {

    private AuthenticationManager authenticationManager;

    private UserDetailsServiceImpl userDetailsService;

    private TokenUtils tokenUtils;

    @Autowired
    public AuthenticationController(
            AuthenticationManager authenticationManager,
            UserDetailsServiceImpl userDetailsService,
            TokenUtils tokenUtils
    ) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.tokenUtils = tokenUtils;
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
            UsernamePasswordAuthenticationToken uToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword());

            //Authentication authentication = this.authenticationManager.authenticate(uToken);
            //SecurityContextHolder.getContext().setAuthentication(authentication);

            return new ResponseEntity<>(token, HttpStatus.OK);
        } catch (BadCredentialsException e) {
            throw new BadRequestException("Wrong password!");
        }
    }

    @GetMapping(
            value = "/logOut",
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    public ResponseEntity logoutUser() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (!(auth instanceof AnonymousAuthenticationToken)){
            SecurityContextHolder.clearContext();

            return new ResponseEntity<>("You successfully logged out!", HttpStatus.OK);
        } else {
            throw new BadRequestException("User is not authenticated!");
        }

    }

}
