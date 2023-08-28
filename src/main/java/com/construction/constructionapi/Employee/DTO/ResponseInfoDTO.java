package com.construction.constructionapi.Employee.DTO;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseInfoDTO {

    private String email;
    private String name;
    private String team;
    private String birth;
    private String gender;
    private String phone;
    private String address;
}
