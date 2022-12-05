package org.Tim19.UberApp.dto;

import org.Tim19.UberApp.model.Driver;
import org.Tim19.UberApp.model.DriverDocument;

public class DriverDocumentDTO {

    private String name;
    private String documentImage;
    private Integer  driverId;

    public DriverDocumentDTO() {}

    public DriverDocumentDTO(String name, String documentImage, Integer driverId) {
        this.name = name;
        this.documentImage = documentImage;
        this.driverId = driverId;
    }
    public DriverDocumentDTO(Integer name, String documentImage, Integer driverId) {

    }
    public DriverDocumentDTO(DriverDocument driverDocument) {
        this(driverDocument.getName(), driverDocument.getDocumentImage(), driverDocument.getDriverId());
    }
    public String getName() {
        return name;
    }

    public String getDocumentImage() {
        return documentImage;
    }

    public Integer getDriverId() {
        return driverId;
    }

}
