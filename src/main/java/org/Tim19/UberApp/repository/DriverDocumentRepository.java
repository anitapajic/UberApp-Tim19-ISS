package org.Tim19.UberApp.repository;


import org.Tim19.UberApp.model.DriverDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverDocumentRepository extends JpaRepository<DriverDocument, Integer> {

    public DriverDocument findOneById(String id);

    public Page<DriverDocument> findAll(Pageable pageable);

}
