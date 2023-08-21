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
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JwtResponseDTO> login(@RequestBody JwtRequestDTO request) {

        try {
            System.out.println(request.getEmail());
            System.out.println(request.getPassword());

            JwtResponseDTO tokenDTO = authService.login(request);
            System.out.println(tokenDTO.getAccessToken());

            return ResponseEntity.ok().body(authService.login(request));
        }catch (Exception e) {
                return ResponseEntity.badRequest().body(new JwtResponseDTO("failed"));
        }
    }


    @PostMapping(value = "signup", produces = MediaType.APPLICATION_JSON_VALUE)
    public String signup(@RequestBody MemberSignupRequestDTO request) {
        return authService.signup(request);
    }
}