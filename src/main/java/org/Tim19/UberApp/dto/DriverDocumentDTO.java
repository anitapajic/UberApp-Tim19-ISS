package org.Tim19.UberApp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.Tim19.UberApp.model.Driver;
import org.Tim19.UberApp.model.DriverDocument;

@Data
public class DriverDocumentDTO {

    private Integer id;
    private String name;
    private String documentImage;

    private Integer  driverId;

    public DriverDocumentDTO(){}

    public DriverDocumentDTO(Integer id, String name, String documentImage, Driver driver) {
        this.id = id;
        this.name = name;
        this.documentImage = documentImage;
        this.driverId = driver.getId();
    }

    public DriverDocumentDTO(DriverDocument driverDocument){
        this(driverDocument.getId(), driverDocument.getName(), driverDocument.getDocumentImage(), driverDocument.getDriver());
    }

}
