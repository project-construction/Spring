package com.construction.constructionapi.Member.Controller;

import com.construction.constructionapi.Member.Domain.Member;
import com.construction.constructionapi.Member.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/info")
public class InfoController {


    private final AuthService authService;

    @Autowired
    public InfoController(AuthService authService){
        this.authService = authService;
    }

    //내정보 표시(마이 페이지)
    @GetMapping("/view")
    public ResponseEntity<Member> viewMemberInfo(@RequestParam String email){

        Member info = authService.userDetailInfo(email);

        return ResponseEntity.ok().body(info);
    }

    //정보 변경
    @PostMapping("/update")
    public ResponseEntity<String> updateMemberInfo(@RequestBody Member member) {
        authService.updateMember(member);
        return ResponseEntity.ok().body("Member 정보가 업데이트되었습니다.");
    }
}
