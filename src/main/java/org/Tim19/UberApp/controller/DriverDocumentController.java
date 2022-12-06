package org.Tim19.UberApp.controller;


import org.Tim19.UberApp.dto.DriverDocumentDTO;
import org.Tim19.UberApp.model.DriverDocument;
import org.Tim19.UberApp.service.DriverDocumentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/driver")
public class DriverDocumentController {

    private DriverDocumentService driverDocumentService;

    @GetMapping(value = "/{id}/document")
    public ResponseEntity<DriverDocumentDTO> getDriverDocument(@PathVariable Integer id) {

        DriverDocument driverDocument = driverDocumentService.findOne(id);

        // user must exist
        if (driverDocument == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new DriverDocumentDTO(driverDocument), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<DriverDocumentDTO> saveDriverDocument(@RequestBody DriverDocumentDTO driverDocumentDTO) {

        DriverDocument driverDocument = new DriverDocument();
        driverDocument.setDocName(driverDocumentDTO.getDocName());
        driverDocument.setDocumentImage(driverDocumentDTO.getDocumentImage());

        //driverDocument.setDriver(driverDocumentDTO.getDriver());

        driverDocument = driverDocumentService.save(driverDocument);
        return new ResponseEntity<>(new DriverDocumentDTO(driverDocument), HttpStatus.CREATED);
    }

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
}
