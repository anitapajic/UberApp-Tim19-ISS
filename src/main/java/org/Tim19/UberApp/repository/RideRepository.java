package org.Tim19.UberApp.repository;

import org.Tim19.UberApp.model.Ride;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface RideRepository extends JpaRepository<Ride,Integer> {

    public Ride findOneById(Integer id);

    public Ride findOneRideById(Integer id);


    @Query(value = "select * from ride r where r.driver_id = ?1", nativeQuery = true)
    public Page<Ride> findAllByDriverId(Integer id, Pageable pageable);

    public Page<Ride> findAllByPassengersId(Integer id, Pageable pageable);
    public Set<Ride> findAllByPassengersId(Integer id);
    public Page<Ride> findAll(Pageable pageable);

}
