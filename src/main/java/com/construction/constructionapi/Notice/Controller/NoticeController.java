package com.construction.constructionapi.Notice.Controller;

import com.construction.constructionapi.Notice.DTO.NoticeUpdateDTO;
import com.construction.constructionapi.Notice.DTO.NoticeWriteDTO;
import com.construction.constructionapi.Notice.Domain.Notice;
import com.construction.constructionapi.Notice.Service.NoticeService;
import com.construction.constructionapi.SpringSecurity.Security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/notice")
@Transactional
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    private final JwtTokenProvider jwtTokenProvider;

    public NoticeController(NoticeService noticeService, JwtTokenProvider jwtTokenProvider) {
        this.noticeService = noticeService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // 공지사항 전체 조회
    @GetMapping("all")
    public ResponseEntity<List<Notice>> allSearchNotice(){
        return ResponseEntity.ok().body(noticeService.allNotice());
    }

    // 공지사항 내용 상세 조회
    @GetMapping("{id}")
    public ResponseEntity<Notice> noticeContent(@PathVariable int id){
        return ResponseEntity.ok().body(noticeService.noticeContent(id));
    }

    // 공지사항 제목 검색
    @GetMapping("/title/{title}")
    public ResponseEntity<List<Notice>> titleNotice(@PathVariable String title){
        return ResponseEntity.ok().body(noticeService.titleNotice(title));
    }

    // 공지사항 내용 검색
    @GetMapping("/content/{content}")
    public ResponseEntity<List<Notice>> contentNotice(@PathVariable String content){
        return ResponseEntity.ok().body(noticeService.contentNotice(content));
    }

    // 공지사항 작성자 검색
    // 수정 필요
    @GetMapping("writer/{id}")
    public ResponseEntity<List<Notice>> writerNotice(@PathVariable String id){
        return ResponseEntity.ok().body(noticeService.writerNotice(id));
    }



    // 공지사항 작성
    @PostMapping(value = "/write", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addNotice(HttpServletRequest request,
                                         @RequestBody NoticeWriteDTO noticeWriteDTO){

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

        noticeService.addNotice(userEmail, noticeWriteDTO);
        return ResponseEntity.ok().body("success");
    }

    // 공지사항 수정
    @PostMapping("/update")
    public ResponseEntity<String> updateNotice(@RequestBody NoticeUpdateDTO noticeWriteDTO){
        noticeService.updateNotice(noticeWriteDTO);
        return ResponseEntity.ok().body("success");
    }

    // 공지사항 삭제
    @GetMapping("delete/{id}")
    public ResponseEntity<String> deleteNotice(@PathVariable int id){
        noticeService.deleteNotice(id);
        return ResponseEntity.ok().body("success");
    }





    @GetMapping(value = "/check/{id}")
    public ResponseEntity<String> checkUpdate(HttpServletRequest request,
                                              @PathVariable int id){

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

        String noticeEmail = noticeService.noticeContent(id).getUserID();

        if (noticeEmail.equals(userEmail)) {
            return ResponseEntity.ok().body("success");
        }else{
            return ResponseEntity.badRequest().body("failed");
        }
    }
}
