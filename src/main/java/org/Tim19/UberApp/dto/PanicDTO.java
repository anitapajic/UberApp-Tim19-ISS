package org.Tim19.UberApp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.Tim19.UberApp.dto.PaginatedData.RidePaginatedDTO;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PanicDTO {
    private Integer id;
    private PanicUserDTO user;
    private RidePaginatedDTO ride;
    private LocalDateTime time;
    private String reason;

    public PanicDTO(Integer id, PanicUserDTO user, RidePaginatedDTO ride, LocalDateTime time, String reason) {
        this.id = id;
        this.user = user;
        this.ride = ride;
        this.time = time;
        this.reason = reason;
    }
}
