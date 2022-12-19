package org.Tim19.UberApp.service;

import org.Tim19.UberApp.model.Ride;
import org.Tim19.UberApp.repository.RideRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import javax.annotation.Generated;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RideService {
    @Autowired
    private RideRepository rideRepository;


    public Ride findOne(Integer id){return rideRepository.findById(id).orElseGet(null);}

    public List<Ride> findAll(){return rideRepository.findAll();}

    public Page<Ride> findAll(Pageable page){return rideRepository.findAll(page);}

    public Ride save(Ride ride){return rideRepository.save(ride);}

    public void remove(Integer id){rideRepository.deleteById(id);}

    public Set<Ride> findByUserId(Integer id){
        Set<Ride> rides = new HashSet<>();

        rides.addAll(rideRepository.findAllByDriverId(id));
       // rides.addAll(rideRepository.findAll());

       rides.addAll(rideRepository.findAllByPassengersId(id));


        return rides;}

}
