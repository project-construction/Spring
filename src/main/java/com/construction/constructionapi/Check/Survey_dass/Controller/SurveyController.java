package com.construction.constructionapi.Check.Survey_dass.Controller;

import com.construction.constructionapi.Check.Survey_dass.DTO.SurveyRequestDTO;
import com.construction.constructionapi.Check.Survey_dass.Service.SurveyService;
import com.construction.constructionapi.SpringSecurity.Security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/survey")
public class SurveyController {

    @Autowired
    private final SurveyService surveyService;
    private final JwtTokenProvider jwtTokenProvider;

    public SurveyController(SurveyService surveyService, JwtTokenProvider jwtTokenProvider){
        this.surveyService = surveyService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping(value = "update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> survey(@RequestBody SurveyRequestDTO surveyRequestDTO){


        return ResponseEntity.ok().body("asd");
    }

    //금일 유저의 검사 유무 확인
    @GetMapping("/check")
    public ResponseEntity<String> check(HttpServletRequest request){
        String token = jwtTokenProvider.resolveToken(request);

        if(token == null || !token.startsWith("Bearer ")){
            return ResponseEntity.badRequest().body("Invalid token");
        }

        String jwtToken = token.substring(7);

        if(!jwtTokenProvider.validateToken(jwtToken)){
            return ResponseEntity.badRequest().body("Invalid token");
        }
        String userEmail = jwtTokenProvider.getUserPk(jwtToken);

        if(userEmail == null){
            return ResponseEntity.badRequest().body("Invalid token");
        }

        //유저가 검사 했는지 확인.
        if(surveyService.hasRecordToday(userEmail))
            return ResponseEntity.ok().body("true");

        return ResponseEntity.ok().body("false");
    }

}