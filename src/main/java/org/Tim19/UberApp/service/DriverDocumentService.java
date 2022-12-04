package org.Tim19.UberApp.service;

import org.Tim19.UberApp.model.DriverDocument;
import org.Tim19.UberApp.repository.DriverDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class DriverDocumentService {

    @Autowired
    private DriverDocumentRepository driverRepository;

    public DriverDocument findOne(Integer id){return driverRepository.findById(id).orElseGet(null);}

    public List<DriverDocument> findAll(){return driverRepository.findAll();}

    public Page<DriverDocument> findAll(Pageable page){return driverRepository.findAll(page);}

    public DriverDocument save(DriverDocument driverDocument){return driverRepository.save(driverDocument);}

    public void remove(Integer id){
        driverRepository.deleteById(id);}

    public DriverDocument findByEmail(String driverId){return driverRepository.findOneById(driverId);}

}
