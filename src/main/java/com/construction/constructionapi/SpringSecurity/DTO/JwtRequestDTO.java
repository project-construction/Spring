package com.construction.constructionapi.SpringSecurity.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtRequestDTO {

    private String id;
    private String password;
}