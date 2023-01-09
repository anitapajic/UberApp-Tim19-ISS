package org.Tim19.UberApp.service;

import org.Tim19.UberApp.dto.PaginatedData.RidePaginatedDTO;
import org.Tim19.UberApp.model.ResetCode;
import org.Tim19.UberApp.model.Ride;
import org.Tim19.UberApp.model.User;
import org.Tim19.UberApp.repository.ResetCodeRepository;
import org.Tim19.UberApp.repository.RideRepository;
import org.Tim19.UberApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private ResetCodeRepository resetCodeRepository;

    public ResetCode findCode(String username) {
        return this.resetCodeRepository.findOneByUsername(username);
    }
    public ResetCode save(ResetCode resetCode){return this.resetCodeRepository.save(resetCode);}
    public void delete(ResetCode resetCode){this.resetCodeRepository.delete(resetCode);}


    public User findOneById(Integer id){
        if (userRepository.findOneById(id) == null){
            return null;
        }
        return userRepository.findOneById(id);
    }

    public Optional<User> findOneByUsername(String username){
        return this.userRepository.findOneByUsername(username);
    }
    public User findOneLogin(String email, String password){return userRepository.findOneByUsernameAndPassword(email,password);}
    public List<User> findAll(){return userRepository.findAll();}

    public Page<User> findAll(Pageable page){
        return userRepository.findAll(page);

    }

    public User save(User user){return userRepository.save(user);}

    public void remove(Integer id){userRepository.deleteById(id);}

    public Set<RidePaginatedDTO> findAllRides(Integer id){
        //Set<String> result = userRepository.findAllRides(id);

        Set<RidePaginatedDTO> rides = new HashSet<>();

//        for (String s: result) {
//            String[] res = s.split(",");
//            RidePaginatedDTO ride = new RidePaginatedDTO();
//            //mozda neki mapper
//
//            ride.setId(Integer.valueOf(res[0]));
//            ride.setBabyTransport(Boolean.valueOf(res[1]));
//            //ride.setEndTime(LocalDateTime.parse(res[2]));
//            ride.setEstimatedTimeInMinutes(Integer.valueOf(res[3]));
//            ride.setPetTransport(Boolean.valueOf(res[5]));
//
//            rides.add(ride);
//        }

        return rides;
    }

}
