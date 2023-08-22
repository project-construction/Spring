package com.construction.constructionapi.Notice.Controller;

import com.construction.constructionapi.Notice.DTO.NoticeDTO;
import com.construction.constructionapi.Notice.Domain.Notice;
import com.construction.constructionapi.Notice.Service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @GetMapping("all")
    public ResponseEntity<String> allSearchNotice(){
        try{
            List<Notice> notice = noticeService.allNotice();
            System.out.println(notice);
            if (notice.isEmpty()){
                return ResponseEntity.ok().body("empty");
            }else {
                return ResponseEntity.ok().body("success");
            }
        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.badRequest().body("failed");
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<String> noticeContent(@PathVariable int id){
        try{
            Notice notice = noticeService.noticeContent(id);
            System.out.println(notice);
            if (notice == null){
                return ResponseEntity.ok().body("empty");
            }else {
                return ResponseEntity.ok().body("success");
            }
        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.badRequest().body("failed");
        }
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<String> titleNotice(@PathVariable String title){
        try{
            List<Notice> notice = noticeService.titleNotice(title);
            System.out.println(notice);
            if (notice.isEmpty()){
                return ResponseEntity.ok().body("empty");
            }else {
                return ResponseEntity.ok().body("success");
            }
        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.badRequest().body("failed");
        }
    }

    @GetMapping("/content/{content}")
    public ResponseEntity<String> contentNotice(@PathVariable String content){
        try{
            List<Notice> notice = noticeService.contentNotice(content);
            System.out.println(notice);
            if (notice.isEmpty()){
                return ResponseEntity.ok().body("empty");
            }else {
                return ResponseEntity.ok().body("success");
            }
        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.badRequest().body("failed");
        }
    }

    @GetMapping("writer/{id}")
    public ResponseEntity<String> writerNotice(@PathVariable int id){
        try{
            List<Notice> notice = noticeService.writerNotice(id);
            System.out.println(notice);
            if (notice.isEmpty()){
                return ResponseEntity.ok().body("empty");
            }else {
                return ResponseEntity.ok().body("success");
            }
        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.badRequest().body("failed");
        }
    }

    @PostMapping("/write")
    public ResponseEntity<String> addNotice(@RequestBody NoticeDTO noticeDTO){
        try {
            noticeService.addNotice(noticeDTO);
            return ResponseEntity.ok().body("success");
        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.badRequest().body("failed");
        }

    }
}
