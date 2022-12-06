package org.Tim19.UberApp.service;

import org.Tim19.UberApp.model.User;
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

    public User findOne(Integer id){return userRepository.findById(id).orElseGet(null);}

    public User findOneLogin (String email, String password){return userRepository.findOneByEmailAndPassword(email,password);}
    public List<User> findAll(){return userRepository.findAll();}

    public Page<User> findAll(Pageable page){return userRepository.findAll(page);}

    public User save(User user){return userRepository.save(user);}

    public void remove(Integer id){userRepository.deleteById(id);}

    public User findByEmail(String email){return userRepository.findOneByEmail(email);}

    public List<User> findByFirstNameAndLastName(String firstname, String lastname){
        return userRepository.findByFirstnameAndLastnameAllIgnoringCase(firstname, lastname);
    }
}
