package com.construction.constructionapi.SpringSecurity.Controller;

import com.construction.constructionapi.SpringSecurity.DTO.JwtRequestDTO;
import com.construction.constructionapi.SpringSecurity.DTO.JwtResponseDTO;
import com.construction.constructionapi.SpringSecurity.DTO.MemberSignupRequestDTO;
import com.construction.constructionapi.SpringSecurity.Service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@CrossOrigin(origins = "https://web-template-3prof2llkxuyz4l.sel4.cloudtype.app")
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JwtResponseDTO> login(@RequestBody JwtRequestDTO request) {

        try {
            return ResponseEntity.ok().body(authService.login(request));
        }catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.badRequest().body(new JwtResponseDTO("failed"));
        }

    }

    @PostMapping(value = "signup", produces = MediaType.APPLICATION_JSON_VALUE)
    public String signup(@RequestBody MemberSignupRequestDTO request) {
        return authService.signup(request);
    }
}