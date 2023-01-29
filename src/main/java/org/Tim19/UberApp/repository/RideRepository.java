package org.Tim19.UberApp.repository;

import org.Tim19.UberApp.model.Ride;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface RideRepository extends JpaRepository<Ride,Integer> {

    public Ride findOneById(Integer id);

    public Ride findOneRideById(Integer id);


    @Query(value = "select * from ride r where r.driver_id = ?1", nativeQuery = true)
    public Page<Ride> findAllByDriverId(Integer id, Pageable pageable);

    @Query(value = "select * from ride r where r.driver_id = ?1 and r.start_time between ?2 AND ?3" , nativeQuery = true)
    public Page<Ride> findAllByDriverIdFilter(Integer id, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
    @Query(value = "select * from ride r where r.driver_id = ?1", nativeQuery = true)
    public Set<Ride> findAllByDriverId(Integer id);

    @Query(value = "select * from ride r where r.start_time between ?1 AND ?2", nativeQuery = true)
    public List<Ride> findAllInDateRange(LocalDateTime startDate, LocalDateTime endDate);

    List<Ride> findAllByStatus(String rideStatus);
    Ride findOneByDriverIdAndStatus(Integer id, String rideStatus);

    public Page<Ride> findAllByPassengersId(Integer id, Pageable pageable);
    @Query(value = "select * from ride r, passenger_ride pr " +
            "where r.id = pr.ride_id and pr.passenger_id =?1 " +
            "and r.start_time between ?2 AND ?3", nativeQuery = true)
    public Page<Ride> findAllByPassengersIdFilter(Integer id, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
    public Set<Ride> findAllByPassengersId(Integer id);
    public Page<Ride> findAll(Pageable pageable);

}
