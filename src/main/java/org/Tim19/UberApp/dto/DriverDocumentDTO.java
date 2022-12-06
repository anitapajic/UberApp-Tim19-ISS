package org.Tim19.UberApp.dto;

import org.Tim19.UberApp.model.Driver;
import org.Tim19.UberApp.model.DriverDocument;

public class DriverDocumentDTO {

    private Integer id;
    private String name;
    private String documentImage;
    private Driver  driver;

    public DriverDocumentDTO(){}

    public DriverDocumentDTO(Integer id, String name, String documentImage, Driver driver) {
        this.id = id;
        this.name = name;
        this.documentImage = documentImage;
        this.driver = driver;
    }

    public DriverDocumentDTO(DriverDocument driverDocument){
        this(driverDocument.getId(), driverDocument.getName(), driverDocument.getDocumentImage(), driverDocument.getDriver());
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDocumentImage() {
        return documentImage;
    }

    public Driver getDriver() {
        return driver;
    }
}
