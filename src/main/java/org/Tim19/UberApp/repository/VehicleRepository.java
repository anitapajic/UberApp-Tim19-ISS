package org.Tim19.UberApp.repository;

import org.Tim19.UberApp.model.DriverDocument;
import org.Tim19.UberApp.model.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

    public Vehicle findOneById(String licenseNumber);

    public Page<Vehicle> findAll(Pageable pageable);
}
