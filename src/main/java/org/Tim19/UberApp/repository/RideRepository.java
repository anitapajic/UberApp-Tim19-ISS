package org.Tim19.UberApp.repository;

import org.Tim19.UberApp.model.Ride;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RideRepository extends JpaRepository<Ride,Integer> {
    public Ride findOneById(Integer id);
    public Page<Ride> findAll(Pageable pageable);

}
