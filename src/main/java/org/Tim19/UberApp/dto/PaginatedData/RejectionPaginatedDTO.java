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

    private Set<RejectionPaginatedDTO> rejections = new HashSet<>();

    public RejectionPaginatedDTO(String reason, LocalDateTime time) {
        this.reason = reason;
        this.time = time;
    }

    public Set<RejectionPaginatedDTO> addRejections(){
        rejections.add(new RejectionPaginatedDTO("Ride is canceled due to previous problems with the passenger", LocalDateTime.of(2022,12,7,20,15,25)));
        return rejections;
    }
    public Set<RejectionPaginatedDTO> getRejections(){
        return this.rejections;
    }
}
