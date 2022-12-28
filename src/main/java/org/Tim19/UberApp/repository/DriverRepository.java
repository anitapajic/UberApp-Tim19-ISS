package org.Tim19.UberApp.repository;

import org.Tim19.UberApp.model.Driver;
import org.Tim19.UberApp.model.DriverDocument;
import org.Tim19.UberApp.model.Ride;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface DriverRepository extends JpaRepository<Driver, Integer> {

    public Driver findOneByEmail(String email);

    public Page<Driver> findAll(Pageable pageable);

    public List<Driver> findByNameAndSurnameAllIgnoringCase(String firstname, String lastname);

}
