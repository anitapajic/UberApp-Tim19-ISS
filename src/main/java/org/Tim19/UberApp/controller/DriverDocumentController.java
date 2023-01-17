package org.Tim19.UberApp.controller;

import org.Tim19.UberApp.dto.DriverDTO;
import org.Tim19.UberApp.dto.DriverDocumentDTO;
import org.Tim19.UberApp.dto.NoteDTO;
import org.Tim19.UberApp.dto.PaginatedData.DriverDocumentPaginatedDTO;
import org.Tim19.UberApp.model.Driver;
import org.Tim19.UberApp.model.DriverDocument;
import org.Tim19.UberApp.model.Note;
import org.Tim19.UberApp.model.Ride;
import org.Tim19.UberApp.service.DriverDocumentService;
import org.Tim19.UberApp.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serial;
import java.util.*;

@RestController
@RequestMapping(value = "/api/driver")
@CrossOrigin(value = "*")
public class    DriverDocumentController {

    @Autowired
    private DriverDocumentService driverDocumentService;
    @Autowired
    private DriverService driverService;

    //DRIVER DOCUMENTS /api/driver/{id}/documents   (driverId)z
    @PreAuthorize("hasAnyAuthority('ADMIN','DRIVER')")
    @GetMapping(value = "/{id}/documents")
    public ResponseEntity getDriverDocuments(@PathVariable Integer id) {

        Driver driver = driverService.findOne(id);
        List<DriverDocument> documents = driverDocumentService.findAllByDriverId(id);
        List<DriverDocumentDTO> driverDocuments = new ArrayList<>();

        if (driver == null) {
            return new ResponseEntity<>("Driver does not exist",HttpStatus.NOT_FOUND);
        }

        for (DriverDocument d: documents) {
            DriverDocumentDTO driverDocumentDTO = new DriverDocumentDTO(d);
            driverDocuments.add(driverDocumentDTO);
        }
        return new ResponseEntity<>(driverDocuments,HttpStatus.OK);

    }

    //DELETING DRIVERS DOCUMENTS /api/driver/{id}/documents  (documentId)
    @PreAuthorize("hasAnyAuthority('ADMIN','DRIVER')")
    @DeleteMapping(value = "/document/{id}")
    public ResponseEntity<Void> deleteDriverDocument(@PathVariable Integer id) {

        DriverDocument driverDocument = driverDocumentService.findOne(id);

        if (driverDocument != null) {
            driverDocumentService.remove(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //ADDING NEW DRIVER DOCUMENTS  /api/driver/{id}/documents  (driverId)
    @PreAuthorize("hasAnyAuthority('ADMIN','DRIVER')")
    @PostMapping(value = "/{id}/documents",consumes = "application/json")
    public ResponseEntity<DriverDocumentDTO> createDriverDocuments(@PathVariable Integer id, @RequestBody DriverDocumentDTO driverDocumentDTO) {

        DriverDocument driverDocument = new DriverDocument();
        Driver driver = driverService.findOne(id);
        if (driver == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        driverDocument.setName(driverDocumentDTO.getName());
        driverDocument.setDocumentImage(driverDocumentDTO.getDocumentImage());
        driverDocument.setDriver(driver);

        driverDocument = driverDocumentService.save(driverDocument);
        return new ResponseEntity<>(new DriverDocumentDTO(driverDocument), HttpStatus.OK);
    }

}
