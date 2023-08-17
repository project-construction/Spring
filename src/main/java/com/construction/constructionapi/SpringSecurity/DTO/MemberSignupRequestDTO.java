package com.construction.constructionapi.SpringSecurity.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberSignupRequestDTO {

    private String email;
    private String password;
    private String name;
}