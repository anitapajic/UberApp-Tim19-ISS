package org.Tim19.UberApp.dto.PaginatedData;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.Tim19.UberApp.dto.UserDTO;

@Data
@NoArgsConstructor
public class PanicPaginatedDTO {

    private Integer id;
    private UserPanicPaginatedDTO user;
    private RidePaginatedDTO ride;
    private String time;
    private String reason;

    public PanicPaginatedDTO(Integer id, UserPanicPaginatedDTO user, RidePaginatedDTO ride, String time, String reason) {
        this.id = id;
        this.user = user;
        this.ride = ride;
        this.time = time;
        this.reason = reason;
    }
}
