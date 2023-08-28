package com.construction.constructionapi.Check.Survey_dass.Controller;

import com.construction.constructionapi.Check.Survey_dass.DTO.SurveyRequestDTO;
import com.construction.constructionapi.Check.Survey_dass.Service.SurveyService;
import com.construction.constructionapi.Member.Security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/survey")
@CrossOrigin(origins = "https://web-template-3prof2llkxuyz4l.sel4.cloudtype.app")
public class SurveyController {

    @Autowired
    private final SurveyService surveyService;
    private final JwtTokenProvider jwtTokenProvider;

    public SurveyController(SurveyService surveyService, JwtTokenProvider jwtTokenProvider){
        this.surveyService = surveyService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    //dass-21 검사 점수 올리기
    @PostMapping(value = "update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> survey(HttpServletRequest request,
                                         @RequestBody SurveyRequestDTO surveyRequestDTO){

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

        surveyService.updateRecord(userEmail, surveyRequestDTO);

        return ResponseEntity.ok().body("success");
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
            //true 면 검사 진행 해야함.
            return ResponseEntity.ok().body("true");

        return ResponseEntity.ok().body("false");
    }
}
