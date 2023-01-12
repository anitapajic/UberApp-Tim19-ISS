package org.Tim19.UberApp.dto.PaginatedData;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.Tim19.UberApp.model.Ride;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PanicPaginatedDTO {

    private Integer id;
    private UserPanicPaginatedDTO user;
    private Ride ride;
    private LocalDateTime time;
    private String reason;

    public PanicPaginatedDTO(Integer id, UserPanicPaginatedDTO user, Ride ride, LocalDateTime time, String reason) {
        this.id = id;
        this.user = user;
        this.ride = ride;
        this.time = time;
        this.reason = reason;
    }
}
