package org.Tim19.UberApp.dto;

import org.Tim19.UberApp.dto.PaginatedData.UserPanicDTO;

import java.time.LocalDateTime;

public class PanicSocketDTO {
    Integer id;
    String reason;
    LocalDateTime time;
    UserPanicDTO sender;

    public PanicSocketDTO(Integer id, String reason, LocalDateTime time, UserPanicDTO sender) {
        this.id = id;
        this.reason = reason;
        this.time = time;
        this.sender = sender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public UserPanicDTO getSender() {
        return sender;
    }

    public void setSender(UserPanicDTO sender) {
        this.sender = sender;
    }
}
