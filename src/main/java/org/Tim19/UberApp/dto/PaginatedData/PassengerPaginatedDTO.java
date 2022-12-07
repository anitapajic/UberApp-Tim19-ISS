package org.Tim19.UberApp.dto.PaginatedData;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PassengerPaginatedDTO {
    private Integer id;
    private String email;

    public PassengerPaginatedDTO(Integer id, String email) {
        this.id = id;
        this.email = email;
    }
}
