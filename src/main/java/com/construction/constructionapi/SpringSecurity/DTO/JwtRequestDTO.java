package com.construction.constructionapi.SpringSecurity.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JwtRequestDTO {

    private String email;
    private String password;
}