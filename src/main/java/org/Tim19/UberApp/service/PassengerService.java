package org.Tim19.UberApp.service;

import org.Tim19.UberApp.model.Passenger;
import org.Tim19.UberApp.model.Ride;
import org.Tim19.UberApp.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    public Passenger findOne(Integer id){return passengerRepository.findById(id).orElseGet(null);}

    public List<Passenger> findAll(){return passengerRepository.findAll();}

    public Page<Passenger> findAll(Pageable page){return passengerRepository.findAll(page);}

    public Page<Integer> findAllRidesFromPassenger(Pageable page, Integer passengerId) {
        return passengerRepository.findAllRidesFromPassenger(page, passengerId);
    }
//    public Passenger findAllRidesFromPassenger(Integer passengerId) {
//        return passengerRepository.findAllRidesFromPassenger(passengerId);
//    }

    public Passenger save(Passenger passenger){return passengerRepository.save(passenger);}

    public void remove(Integer id){passengerRepository.deleteById(id);}

    public Passenger findByEmail(String email){return passengerRepository.findOneByEmail(email);}

    public List<Passenger> findByFirstNameAndLastName(String firstname, String lastname){
        return passengerRepository.findByFirstnameAndLastnameAllIgnoringCase(firstname, lastname);
    }


}
