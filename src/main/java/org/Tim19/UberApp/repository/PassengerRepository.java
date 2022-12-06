package org.Tim19.UberApp.repository;

import org.Tim19.UberApp.model.Passenger;
import org.Tim19.UberApp.model.Ride;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PassengerRepository extends JpaRepository<Passenger, Integer> {

    public Passenger findOneByEmail(String email);

    public Page<Passenger> findAll(Pageable pageable);
    //public Page<Ride> findAllRidesFromPassenger(Pageable pageable);

    public List<Passenger> findByFirstnameAndLastnameAllIgnoringCase(String firstname, String lastname);

}
