package org.Tim19.UberApp.repository;

import org.Tim19.UberApp.model.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

    public Vehicle findOneByLicenseNumber(String licenseNumber);
    public Optional<Vehicle> findOneById(Integer id);



    public Page<Vehicle> findAll(Pageable pageable);
}
