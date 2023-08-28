package com.construction.constructionapi.Member.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtRequestDTO {

    private String email;

    private String password;

    public JwtRequestDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }
}