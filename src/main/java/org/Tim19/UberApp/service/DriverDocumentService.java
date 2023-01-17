package org.Tim19.UberApp.service;

import org.Tim19.UberApp.dto.DriverDocumentDTO;
import org.Tim19.UberApp.model.Driver;
import org.Tim19.UberApp.model.DriverDocument;
import org.Tim19.UberApp.model.Note;
import org.Tim19.UberApp.model.Ride;
import org.Tim19.UberApp.repository.DriverDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DriverDocumentService {

    @Autowired
    private DriverDocumentRepository driverRepository;
    @Autowired
    private DriverDocumentRepository driverDocumentRepository;

    public List<DriverDocument> findAllByDriverId(Integer id){return driverDocumentRepository.findAllByDriverId(id);}


    public Page<DriverDocument> findAll(Pageable page){return driverRepository.findAll(page);}

    public DriverDocument save(DriverDocument driverDocument){return driverRepository.save(driverDocument);}

    public void remove(Integer id){driverRepository.deleteById(id);}

    public Set<DriverDocument> findDriverDocument(Integer id){
        return driverDocumentRepository.findDriverDocument(id);
        }

    public DriverDocument findOne(Integer id){return driverDocumentRepository.findById(id).orElse(null);}
}
