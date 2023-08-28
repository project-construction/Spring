package com.construction.constructionapi.Member.Controller;

import com.construction.constructionapi.Member.DTO.JwtRequestDTO;
import com.construction.constructionapi.Member.DTO.JwtResponseDTO;
import com.construction.constructionapi.Member.DTO.MemberSignupRequestDTO;
import com.construction.constructionapi.Member.Service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
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

        boolean isSignup = authService.signup(request);

        if(!isSignup)
            return "failed";

        return "success";
    }


}