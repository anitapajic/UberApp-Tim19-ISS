package org.Tim19.UberApp.dto;

import lombok.Data;

@Data
public class Driver2DTO {
    public Integer id;
    public String email;

    public Driver2DTO(Integer id, String email) {
        this.id = id;
        this.email = email;
    }

    public Driver2DTO() {
    }
}
