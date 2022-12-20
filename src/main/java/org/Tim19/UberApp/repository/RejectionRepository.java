package org.Tim19.UberApp.repository;

import org.Tim19.UberApp.model.Rejection;
import org.Tim19.UberApp.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface RejectionRepository extends JpaRepository<Rejection, Integer> {
    @Query( value = "select * from rejection r where r.ride_id = ?1", nativeQuery = true)
    public Set<Rejection> findAllByRideId(Integer id);
}
