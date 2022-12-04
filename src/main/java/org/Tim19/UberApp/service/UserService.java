package org.Tim19.UberApp.service;

import org.Tim19.UberApp.model.Users;
import org.Tim19.UberApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Users findOne(Integer id){return userRepository.findById(id).orElseGet(null);}

    public List<Users> findAll(){return userRepository.findAll();}

    public Page<Users> findAll(Pageable page){return userRepository.findAll(page);}

    public Users save(Users users){return userRepository.save(users);}

    public void remove(Integer id){userRepository.deleteById(id);}

    public Users findByEmail(String email){return userRepository.findOneByEmail(email);}

    public List<Users> findByFirstNameAndLastName(String name, String lastname){
        return userRepository.findByFirstNameAndLastNameAllIgnoringCase(name, lastname);
    }
}
