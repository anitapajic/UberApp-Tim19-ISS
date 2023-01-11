package org.Tim19.UberApp.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ResetCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="username",unique = true, nullable = false)
    private String username;

    @Column(name = "code",unique = true,  nullable = false)
    private Integer code;

    private LocalDateTime date;

    public ResetCode(){}

    public ResetCode(Integer code, String username, LocalDateTime date){
        this.code = code;
        this.username = username;
        this.date = date;
    }

    public ResetCode(Integer id, String username, Integer code, LocalDateTime date) {
        this.id = id;
        this.username = username;
        this.code = code;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
