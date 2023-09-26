package com.construction.constructionapi.Test.UnityContent.Controller;

import com.construction.constructionapi.Test.UnityContent.DTO.TestDTO.*;
import com.construction.constructionapi.Test.UnityContent.Service.ReactionService;
import com.construction.constructionapi.Test.UnityContent.Service.TestService;
import com.construction.constructionapi.Member.Security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/unityContent")
public class UnityContentTestController {
    private final TestService testService;
    private final ReactionService reactionService;
    private final JwtTokenProvider jwtTokenProvider;
    @Autowired
    public UnityContentTestController(TestService testService,ReactionService reactionService, JwtTokenProvider jwtTokenProvider){
        this.jwtTokenProvider = jwtTokenProvider;
        this.testService = testService;
        this.reactionService = reactionService;
    }


    @GetMapping("/allInfo")
    public ResponseEntity<TestAndReactionAllDTO> allTestEntity(HttpServletRequest request){
        String token = jwtTokenProvider.resolveToken(request);
        if(token == null || !token.startsWith("Bearer ")){
            return ResponseEntity.badRequest().build();
        }

        String jwtToken = token.substring(7);

        if(!jwtTokenProvider.validateToken(jwtToken)){
            return ResponseEntity.badRequest().build();
        }

        String userEmail = jwtTokenProvider.getUserPk(jwtToken);

        if(userEmail == null){
            return ResponseEntity.badRequest().build();
        }
        TestScoreAllDTO testAllDTO;
        ResponseReactionDTO responseReactionDTO;
        TestAndReactionAllDTO testAndReactionAllDTO = new TestAndReactionAllDTO();
        try{
            testAllDTO = testService.findAllScore(userEmail);
            responseReactionDTO = reactionService.getAllReaction(userEmail);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(testAndReactionAllDTO);
        }
        testAndReactionAllDTO.mergeAll(responseReactionDTO, testAllDTO);
        return ResponseEntity.ok().body(testAndReactionAllDTO);
    }


    @PostMapping("/insertContent")
    public ResponseEntity<String> insertContent(HttpServletRequest request , @RequestBody TestScoreDTO testScoreDTO){

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
        try {
            testService.fillScore(userEmail, testScoreDTO);

<<<<<<< HEAD
            return ResponseEntity.ok().body("success");
=======
            reactionService.saveReaction(userEmail, testScoreDTO);
            HttpHeaders headers = new HttpHeaders();
            headers.set("Access-Control-Allow-Origin", "https://web-template-3prof2llkxuyz4l.sel4.cloudtype.app");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body("success");
>>>>>>> 88fd9a59729592b1f5871637445f6c15b1900ac0
        } catch (Exception e) {
            return ResponseEntity.ok().body("failed");
        }

    }


}
