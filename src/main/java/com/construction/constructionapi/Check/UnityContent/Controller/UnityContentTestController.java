package com.construction.constructionapi.Check.UnityContent.Controller;


import com.construction.constructionapi.Check.UnityContent.DTO.TestDTO.*;
import com.construction.constructionapi.Check.UnityContent.Service.TestService;
import com.construction.constructionapi.SpringSecurity.Security.JwtTokenProvider;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/unityContent")
@Tag(name = "UnityContents", description = "Unity 데이터 수집 컨트롤러")
@CrossOrigin(origins = "http://localhost:3000")
public class UnityContentTestController {
    private final TestService testService;
    private final JwtTokenProvider jwtTokenProvider;
    @Autowired
    public UnityContentTestController(TestService testService, JwtTokenProvider jwtTokenProvider){
        this.jwtTokenProvider = jwtTokenProvider;
        this.testService = testService;
    }


    @GetMapping("/allInfo")
    public ResponseEntity<TestAllDTO> allTestEntity(HttpServletRequest request){
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
        TestAllDTO testAllDTO;
        try{
            testAllDTO = testService.findAllById(userEmail);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(testAllDTO);
    }


    @PostMapping("/insertContent")
    public ResponseEntity<String> doorockContent(HttpServletRequest request , @RequestBody Map<String, Integer> map){
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
        for (String key:map.keySet()) {
            switch (key){
                case "catchMole":
                    TestCatchMoleDTO testCatchMoleDTO = new TestCatchMoleDTO();
                    testCatchMoleDTO.setCatchMole(map.get(key));
                    testService.fillCatchMole(userEmail, testCatchMoleDTO);
                    break;
                case "doorLock":
                    TestDoorLockDTO testDoorLockDTO = new TestDoorLockDTO();
                    testDoorLockDTO.setDoorLock(map.get(key));
                    testService.fillDoorLock(userEmail, testDoorLockDTO);
                    break;
                case "hammering":
                    TestHammeringDTO testHammeringDTO = new TestHammeringDTO();
                    testHammeringDTO.setHammering(map.get(key));
                    testService.fillHammering(userEmail, testHammeringDTO);
                    break;
                case "nBack":
                    TestNBackDTO testNBackDTO = new TestNBackDTO();
                    testNBackDTO.setNBack(map.get(key));
                    testService.fillNBack(userEmail, testNBackDTO);
                    break;
                case "numberPuzzle":
                    TestNumberPuzzleDTO testNumberPuzzleDTO = new TestNumberPuzzleDTO();
                    testNumberPuzzleDTO.setNumber_puzzle(map.get(key));
                    testService.fillNumberPuzzle(userEmail, testNumberPuzzleDTO);
                    break;
                case "simon":
                    TestSimonDTO testSimonDTO = new TestSimonDTO();
                    testSimonDTO.setSimon(map.get(key));
                    testService.fillSimon(userEmail, testSimonDTO);
                    break;
                case"trafficLight":
                    TestTrafficLightDTO testTrafficLightDTO = new TestTrafficLightDTO();
                    testTrafficLightDTO.setTrafficLight(map.get(key));
                    testService.fillTrafficLight(userEmail, testTrafficLightDTO);
                    break;
            }
        }
        return ResponseEntity.ok("정상적으로 잘 들어감");
    }


}
