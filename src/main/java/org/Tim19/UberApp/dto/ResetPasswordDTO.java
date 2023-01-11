package org.Tim19.UberApp.dto;

public class ResetPasswordDTO {

    private String username;
    private String oldPassword;
    private String newPassword;
    private Integer code;

    public ResetPasswordDTO(String username, String oldPassword, String newPassword, Integer code) {
        this.username = username;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.code = code;
    }

    public ResetPasswordDTO(String username,String newPassword, Integer code) {
        this.username = username;
        this.newPassword = newPassword;
        this.code = code;
    }

    public ResetPasswordDTO(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
