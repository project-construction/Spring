package com.construction.constructionapi.SpringSecurity.Controller;

import com.construction.constructionapi.SpringSecurity.DTO.JwtRequestDTO;
import com.construction.constructionapi.SpringSecurity.DTO.JwtResponseDTO;
import com.construction.constructionapi.SpringSecurity.DTO.MemberSignupRequestDTO;
import com.construction.constructionapi.SpringSecurity.Service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "login", produces = MediaType.APPLICATION_JSON_VALUE)
    public JwtResponseDTO login(@RequestBody JwtRequestDTO request) {

        try {
            return authService.login(request);
        } catch (Exception e) {
            return new JwtResponseDTO(e.getMessage());
        }
    }

    @PostMapping(value = "signup", produces = MediaType.APPLICATION_JSON_VALUE)
    public String signup(@RequestBody MemberSignupRequestDTO request) {
        return authService.signup(request);
    }
}