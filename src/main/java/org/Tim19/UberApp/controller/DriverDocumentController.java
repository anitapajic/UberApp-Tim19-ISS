package org.Tim19.UberApp.controller;

import org.Tim19.UberApp.dto.DriverDocumentDTO;
import org.Tim19.UberApp.dto.PaginatedData.DriverDocumentPaginatedDTO;
import org.Tim19.UberApp.model.Driver;
import org.Tim19.UberApp.model.DriverDocument;
import org.Tim19.UberApp.service.DriverDocumentService;
import org.Tim19.UberApp.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;

@RestController
@RequestMapping(value = "/api/driver")
public class    DriverDocumentController {

    @Autowired
    private DriverDocumentService driverDocumentService;
    @Autowired
    private DriverService driverService;

    //DRIVER DOCUMENTS /api/driver/{id}/documents   (driverId)z
    @GetMapping(value = "/{id}/documents")
    public ResponseEntity<DriverDocumentPaginatedDTO> getDriverDocuments(@PathVariable Integer id) {

        DriverDocumentPaginatedDTO driverDocument = new DriverDocumentPaginatedDTO(123,"Vozačka dozvola","U3dhZ2dlciByb2Nrcw=",id);
        return new ResponseEntity<>(driverDocument, HttpStatus.OK);
    }

    //DELETING DRIVERS DOCUMENTS /api/driver/{id}/documents  (documentId)
    @DeleteMapping(value = "/document/{id}")
    public ResponseEntity<Void> deleteDriverDocument(@PathVariable Integer id) {

        DriverDocument driverDocument = driverDocumentService.findOne(id);

        if (driverDocument != null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //ADDING NEW DRIVER DOCUMENTS  /api/driver/{id}/documents  (driverId)
    @PostMapping(value = "/{id}/documents",consumes = "application/json")
    public ResponseEntity<DriverDocumentDTO> createDriverDocuments(@PathVariable Integer id, @RequestBody DriverDocumentDTO driverDocumentDTO) {

        DriverDocument driverDocument = new DriverDocument();
        Driver driver = driverService.findOne(id);
        driverDocument.setName(driverDocumentDTO.getName());
        driverDocument.setDocumentImage(driverDocumentDTO.getDocumentImage());
        driverDocument.setDriver(driver);

        driverDocument = driverDocumentService.save(driverDocument);
        return new ResponseEntity<>(new DriverDocumentDTO(driverDocument), HttpStatus.OK);
    }

}
