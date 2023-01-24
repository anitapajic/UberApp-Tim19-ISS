package org.Tim19.UberApp.repository;

import org.Tim19.UberApp.model.UpdateDriver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UpdateDriverRepository extends JpaRepository<UpdateDriver, Integer> {

    public List<UpdateDriver> findAll();

}
