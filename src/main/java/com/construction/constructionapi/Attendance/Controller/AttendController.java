package com.construction.constructionapi.Attendance.Controller;


import com.construction.constructionapi.Attendance.DTO.CodeDTO;
import com.construction.constructionapi.Attendance.Service.AttendService;
import com.construction.constructionapi.Attendance.Service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.construction.constructionapi.Member.Security.JwtTokenProvider;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/attend")
public class AttendController {


    private final AttendService attendService;
    private final JwtTokenProvider jwtTokenProvider;
    private final CodeService codeService;

    @Autowired
    public AttendController(AttendService attendService, JwtTokenProvider jwtTokenProvider, CodeService codeService) {
        this.attendService = attendService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.codeService = codeService;
    }

    // 출석을 했는지 체크해주는 API
    @GetMapping("/check")
    public ResponseEntity<String> check(HttpServletRequest request) {

        String token = jwtTokenProvider.resolveToken(request);

        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body("Invalid token");
        }

        String jwtToken = token.substring(7);

        if (!jwtTokenProvider.validateToken(jwtToken)) {
            return ResponseEntity.badRequest().body("Invalid token");
        }
        String userEmail = jwtTokenProvider.getUserPk(jwtToken);

        if (userEmail == null) {
            return ResponseEntity.badRequest().body("Invalid token");
        }
        try {
            // 출석 체크.
            if (attendService.checkAttend(userEmail))
                return ResponseEntity.ok().body("success");

            return ResponseEntity.ok().body("failed");
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.badRequest().body("failed");
        }
    }

    // test 만들어주는 API (로그인시 생성)
    @PostMapping("/login")
    public ResponseEntity<String> makeAttend(HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);

        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body("Invalid token");
        }

        String jwtToken = token.substring(7);

        if (!jwtTokenProvider.validateToken(jwtToken)) {
            return ResponseEntity.badRequest().body("Invalid token");
        }

        String userEmail = jwtTokenProvider.getUserPk(jwtToken);

        if (userEmail == null) {
            return ResponseEntity.badRequest().body("Invalid token");
        }
        try {
            // 이미 있으면 실패
            if (attendService.checkAttend(userEmail)) return ResponseEntity.ok("failed");

            // 없으면 생성
            attendService.makeAttend(userEmail);
            return ResponseEntity.ok("success");
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.badRequest().body("failed");
        }
    }

    // int형 코드를 입력받아 같은 팀의 코드와 같을시 is_check 변수 변경
    @PostMapping("/attend")
    public ResponseEntity<String> Attend(HttpServletRequest request, @RequestBody CodeDTO codeDTO) {
        String token = jwtTokenProvider.resolveToken(request);

        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body("Invalid token");
        }

        String jwtToken = token.substring(7);

        if (!jwtTokenProvider.validateToken(jwtToken)) {
            return ResponseEntity.badRequest().body("Invalid token");
        }

        String userEmail = jwtTokenProvider.getUserPk(jwtToken);

        if (userEmail == null) {
            return ResponseEntity.badRequest().body("Invalid token");
        }

        try {
            // 출석 코드가 같은지 체크
            if (codeService.getCode(userEmail) != codeDTO.getCode()) return ResponseEntity.ok("code not same");

            // is_check 변수 변경
            if (attendService.attend(userEmail)) return ResponseEntity.ok("success");
            return ResponseEntity.ok("failed");
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.badRequest().body("failed");
        }
    }
    
    //ROLE이 USER가 아니면 자신의 팀 코드를 현재 날짜로 변경
    @PostMapping("/code")
    public ResponseEntity<String> setCode(HttpServletRequest request, @RequestBody CodeDTO codeDTO) {
        String token = jwtTokenProvider.resolveToken(request);

        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body("Invalid token");
        }

        String jwtToken = token.substring(7);

        if (!jwtTokenProvider.validateToken(jwtToken)) {
            return ResponseEntity.badRequest().body("Invalid token");
        }

        String userEmail = jwtTokenProvider.getUserPk(jwtToken);

        if (userEmail == null) {
            return ResponseEntity.badRequest().body("Invalid token");
        }

        try {
            if (codeService.setCode(userEmail, codeDTO.getCode()))
                return ResponseEntity.ok("success");
            return ResponseEntity.ok("rejected");
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.badRequest().body("failed");
        }
    }
}