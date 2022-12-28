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

    // @Query(value = "select p.id, p.email, r.*, l1.*, l2.* from USER p, user_rides pr, ride r, ride_paths rp, path path, location l1, location l2 where P.ID = ?1 and p.id = pr.passenger_id and r.id = pr.ride_id and rp.ride_id= r.id and rp.paths_id=path.id and path.departure_id = l1.id and path.destination_id = l2.id", nativeQuery = true)
//    public Page<Integer> findAllRidesFromPassenger(Pageable pageable, Integer passengerId);

    public List<Passenger> findByNameAndSurnameAllIgnoringCase(String firstname, String lastname);

}
