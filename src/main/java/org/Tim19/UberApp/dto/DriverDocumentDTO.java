package org.Tim19.UberApp.dto;

import org.Tim19.UberApp.model.Driver;
import org.Tim19.UberApp.model.DriverDocument;

public class DriverDocumentDTO {

    private String docName;
    private String documentImage;
    private Integer  driverId;

    public DriverDocumentDTO() {}

    public DriverDocumentDTO(String docName, String documentImage, Integer driverId) {
        this.docName = docName;
        this.documentImage = documentImage;
        this.driverId = driverId;
    }

    public DriverDocumentDTO(DriverDocument driverDocument) {
        this(driverDocument.getDocName(), driverDocument.getDocumentImage(), driverDocument.getDriverId());
    }
    public String getDocName() {
        return docName;
    }

    public String getDocumentImage() {
        return documentImage;
    }

    public Integer getDriverId() {
        return driverId;
    }

}
