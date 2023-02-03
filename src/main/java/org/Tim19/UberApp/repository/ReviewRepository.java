package org.Tim19.UberApp.repository;

import org.Tim19.UberApp.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    public List<Review> findAll();

   // @Query( value = "select * from review r where r.ride_id = ?1", nativeQuery = true)
    public Set<Review> findAllByRideId(Integer id);
    public Optional<Review> findOneByRideId(Integer id);

    @Query( value = "select r.* from review r, \"USER\" d where r.driver_id = d.id and d.vehicle_id = ?1", nativeQuery = true)
    public Set<Review> findAllByVehicleId(Integer id);
    //@Query( value = "select r.* from review r, \"USER\" d where r.driver_id = d.id and d.vehicle_id = ?1", nativeQuery = true)
    public Set<Review> findAllByDriverId(Integer id);

    public Review save(Review review);


}
