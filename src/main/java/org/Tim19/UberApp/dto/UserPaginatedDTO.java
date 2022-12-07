package org.Tim19.UberApp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
@Data
@NoArgsConstructor
public class UserPaginatedDTO {
    private Integer id;
    private String email;

    public UserPaginatedDTO(Integer id, String email) {
        this.id = id;
        this.email = email;
    }
}
