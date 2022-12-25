package org.Tim19.UberApp.repository;


import org.Tim19.UberApp.dto.DriverDocumentDTO;
import org.Tim19.UberApp.model.DriverDocument;
import org.Tim19.UberApp.model.Note;
import org.Tim19.UberApp.model.Rejection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface DriverDocumentRepository extends JpaRepository<DriverDocument, Integer> {

    public DriverDocument findOneById(String id);

    public Page<DriverDocument> findAll(Pageable pageable);

    @Query( value = "select * from document d where d.driver_id = ?1", nativeQuery = true)
    public Set<DriverDocument> findDriverDocument(Integer id);

    public List<DriverDocument> findAllByDriverId(Integer id);

}
