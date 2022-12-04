package org.Tim19.UberApp.repository;


import org.Tim19.UberApp.model.Driver;
import org.Tim19.UberApp.model.DriverDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DriverDocumentRepository extends JpaRepository<DriverDocument, Integer> {

    public DriverDocument findOneById(String driverId);

    public Page<DriverDocument> findAll(Pageable pageable);

}
