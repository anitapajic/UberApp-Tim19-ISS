package org.Tim19.UberApp.service;


import org.Tim19.UberApp.exceptions.NotFoundException;
import org.Tim19.UberApp.model.User;
import org.Tim19.UberApp.repository.UserRepository;
import org.Tim19.UberApp.security.UserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = this.userRepository.findOneByEmail(email).orElseThrow(() -> new NotFoundException(String.format("User with email '%s' is not found!", email)));

        return UserFactory.create(user);
    }
}
