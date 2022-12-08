package org.Tim19.UberApp.dto.PaginatedData;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class RejectionPaginatedDTO {

    private String reason;
    private LocalDateTime time;


    public RejectionPaginatedDTO(String reason, LocalDateTime time) {
        this.reason = reason;
        this.time = time;
    }

}
