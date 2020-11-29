package com.example.demo.DTO;

import com.example.demo.domain.User;

public class UserDTO {
    private Boolean status;
    private String identityId;
    private String password;
    private String name;
    private String surname;

    public UserDTO(User user , Boolean status) {
        this.status = status;
        this.identityId = user.getIdentityId();
        this.password = user.getPassword();
        this.name = user.getName();
        this.surname = user.getSurname();
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getIdentityId() {
        return identityId;
    }

    public void setIdentityId(String identityId) {
        this.identityId = identityId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
