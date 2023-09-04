package com.construction.constructionapi.SpringSecurity.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor // Setter를 사용하지 않아 기본으로 초기화 시켜줌
public class JwtResponseDTO {
    private String accessToken;
}