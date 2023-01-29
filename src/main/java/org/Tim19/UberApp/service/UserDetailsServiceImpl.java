package org.Tim19.UberApp.service;


import org.Tim19.UberApp.exceptions.NotFoundException;
import org.Tim19.UberApp.model.User;
import org.Tim19.UberApp.repository.UserRepository;
import org.Tim19.UberApp.security.SecurityUser;
import org.Tim19.UberApp.security.UserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = this.userRepository.findOneByUsername(username)
                .orElseThrow(() -> new NotFoundException(String.format("User with username '%s' is not found!", username)));
        if(user.getBlocked()){
            return null;
        }
        if(user.getAuthorities().equals("PASSENGER")  && !user.getActive()){
            return null;
        }
        return UserFactory.create(user);
    }
}
