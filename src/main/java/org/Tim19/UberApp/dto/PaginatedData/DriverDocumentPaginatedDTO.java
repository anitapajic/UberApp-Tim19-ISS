package org.Tim19.UberApp.dto.PaginatedData;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class DriverDocumentPaginatedDTO {
    private Integer id;
    private String name;
    private String documentImage;
    private Integer driver_id;

    public DriverDocumentPaginatedDTO(Integer id, String name, String documentImage, Integer driver_id) {
        this.id = id;
        this.name = name;
        this.documentImage = documentImage;
        this.driver_id = driver_id;
    }

}
