package org.Tim19.UberApp.repository;

import org.Tim19.UberApp.model.Passenger;
import org.Tim19.UberApp.model.Ride;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PassengerRepository extends JpaRepository<Passenger, Integer> {

    public Passenger findOneByEmail(String email);

    public Page<Passenger> findAll(Pageable pageable);

    @Query(value = "select * from ride r where r.id in (select ride_id from USER p join passenger_ride pr where passenger_id=?1)", nativeQuery = true)
    public Page<Integer> findAllRidesFromPassenger(Pageable pageable, Integer passengerId);

//    @Query("select p from Passenger p join fetch p.rides e where p.id =?1")
//    public Passenger findAllRidesFromPassenger(Integer id);
    public List<Passenger> findByFirstnameAndLastnameAllIgnoringCase(String firstname, String lastname);

}
