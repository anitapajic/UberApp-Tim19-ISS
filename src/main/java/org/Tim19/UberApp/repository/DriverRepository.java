package org.Tim19.UberApp.repository;

import org.Tim19.UberApp.model.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DriverRepository extends JpaRepository<Driver, Integer> {

    public Driver findOneByUsername(String email);
    public Driver findOneByVehicleId(Integer id);

    public Page<Driver> findAll(Pageable pageable);

    public List<Driver> findByNameAndSurnameAllIgnoringCase(String firstname, String lastname);

}
