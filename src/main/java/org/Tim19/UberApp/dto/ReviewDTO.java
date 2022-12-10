package org.Tim19.UberApp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.Tim19.UberApp.dto.PaginatedData.UserPaginatedDTO;

@Data
@NoArgsConstructor
public class ReviewDTO {
    private Integer id;
    private Integer rating;
    private String comment;
    private UserPaginatedDTO passenger;

    public ReviewDTO(Integer id, Integer rating, String comment, UserPaginatedDTO passenger) {
        this.id = id;
        this.rating = rating;
        this.comment = comment;
        this.passenger = passenger;
    }
}
