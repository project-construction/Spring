package com.construction.constructionapi.SpringSecurity.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class JwtRequestDTO {

    private String email;

    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public JwtRequestDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }
}