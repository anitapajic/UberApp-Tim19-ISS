package org.Tim19.UberApp.service;

import org.Tim19.UberApp.model.DriverDocument;
import org.Tim19.UberApp.repository.DriverDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class DriverDocumentService {

    @Autowired
    private DriverDocumentRepository driverDocumentRepository;

    public DriverDocument findOne(Integer id){return driverDocumentRepository.findOneByDriverId(String.valueOf(id));}

    public List<DriverDocument> findAll(){return driverDocumentRepository.findAll();}

    public Page<DriverDocument> findAll(Pageable page){return driverDocumentRepository.findAll(page);}

    public DriverDocument save(DriverDocument driverDocument){return driverDocumentRepository.save(driverDocument);}

    public void remove(Integer id){
        driverDocumentRepository.deleteById(id);}


}
