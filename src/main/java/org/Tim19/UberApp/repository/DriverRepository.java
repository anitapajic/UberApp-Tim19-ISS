package org.Tim19.UberApp.repository;

import org.Tim19.UberApp.model.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DriverRepository extends JpaRepository<Driver, Integer> {

    public Driver findOneByEmail(String email);

    public Page<Driver> findAll(Pageable pageable);

    public List<Driver> findByFirstnameAndLastnameAllIgnoringCase(String firstname, String lastname);


}
