package org.Tim19.UberApp.controller;

import org.Tim19.UberApp.dto.DriverDocumentDTO;
import org.Tim19.UberApp.model.DriverDocument;
import org.Tim19.UberApp.service.DriverDocumentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/driver/{id}/documents")
public class DriverDocumentController {

    private DriverDocumentService driverDocumentService;

    ///TODO: Treba trazenje preko driverId
    //DRIVER DOCUMENTS /api/driver/{id}/documents   (driverId)
    @GetMapping
    public ResponseEntity<DriverDocumentDTO> getDriverDocuments(@PathVariable Integer id) {

        DriverDocument driverDocument = driverDocumentService.findOne(id);

        // document must exist
        if (driverDocument == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new DriverDocumentDTO(driverDocument), HttpStatus.OK);
    }

    //DELETING DRIVERS DOCUMENTS /api/driver/{id}/documents  (documentId)
    @DeleteMapping
    public ResponseEntity<Void> deleteDriverDocument(@PathVariable Integer id) {

        DriverDocument driverDocument = driverDocumentService.findOne(id);

        if (driverDocument != null) {
            driverDocumentService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    ///TODO: Treba kreiranje preko driverId
    //ADDING NEW DRIVER DOCUMENTS  /api/driver/{id}/documents  (driverId)
    @PostMapping
    public ResponseEntity<DriverDocumentDTO> createDriverDocuments(@RequestBody DriverDocumentDTO driverDocumentDTO) {

        DriverDocument driverDocument = new DriverDocument();
        driverDocument.setName(driverDocumentDTO.getName());
        driverDocument.setDocumentImage(driverDocumentDTO.getDocumentImage());
        driverDocument.setDriver(driverDocumentDTO.getDriver());

        driverDocument = driverDocumentService.save(driverDocument);
        return new ResponseEntity<>(new DriverDocumentDTO(driverDocument), HttpStatus.CREATED);
    }

}
