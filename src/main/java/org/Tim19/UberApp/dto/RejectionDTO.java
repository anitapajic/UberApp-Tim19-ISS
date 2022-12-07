package org.Tim19.UberApp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class RejectionDTO{

    private String reason;
    private LocalDateTime time;

    private Set<RejectionDTO> rejections = new HashSet<>();

    public RejectionDTO(String reason, LocalDateTime time) {
        this.reason = reason;
        this.time = time;
    }

    public Set<RejectionDTO> addRejections(){
        rejections.add(new RejectionDTO("Ride is canceled due to previous problems with the passenger", LocalDateTime.of(2022,12,7,20,15,25)));
        return rejections;
    }
    public Set<RejectionDTO> getRejections(){
        return this.rejections;
    }
}
