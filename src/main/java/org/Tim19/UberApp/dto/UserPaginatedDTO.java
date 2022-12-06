package org.Tim19.UberApp.dto;

import java.util.ArrayList;

public class UserPaginatedDTO {
    private Integer totalcounts;
    private ArrayList<UserDTO> passengers;

    public UserPaginatedDTO(Integer totalcounts, ArrayList<UserDTO> passengers) {
        this.totalcounts = totalcounts;
        this.passengers = passengers;
    }
    public UserPaginatedDTO(){}

    public Integer getTotalcounts() {
        return totalcounts;
    }

    public ArrayList<UserDTO> getPassengers() {
        return passengers;
    }
}
