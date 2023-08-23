package com.construction.constructionapi.SpringSecurity.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtRequestDTO {

    private String email;
    private String password;

    public JwtRequestDTO() {
    }

    public JwtRequestDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }
}