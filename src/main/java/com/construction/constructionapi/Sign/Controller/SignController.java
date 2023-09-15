package com.construction.constructionapi.Sign.Controller;

import com.construction.constructionapi.Sign.DTO.RequestSignDTO;
import com.construction.constructionapi.Sign.Domain.SignImage;
import com.construction.constructionapi.Sign.Service.SignDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sign")
public class SignController {

    @Autowired
    private SignDataService signDataService;

    //관리자 이미지 업로드
    @PostMapping("/manager/upload")
    public ResponseEntity<String> uploadImageData(@RequestBody RequestSignDTO requestSignDTO) {

        if(requestSignDTO.getEncodeImage().isEmpty()){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("이미지 데이터 업로드 중 오류가 발생했습니다.");
        }

        try {
            signDataService.saveSignData(requestSignDTO);
            return ResponseEntity.ok("이미지 데이터가 성공적으로 업로드되었습니다. ID: ");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("이미지 데이터 업로드 중 오류가 발생했습니다.");
        }

    }

    //저장된 전체 PDF 리턴(리스트)
    @GetMapping("/manager/all")
    public ResponseEntity<List<SignImage>> allSearchNotice(){
        return ResponseEntity.ok().body(signDataService.allImages());
    }

    //근로자 싸인 저장.
/*    @PostMapping("/signs")
    public ResponseEntity<List<ResponseSignDTO>> getUserImages(@RequestBody Map<String, String> requestData) {
        String userEmail = requestData.get("email");

        if (userEmail == null || userEmail.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        List<ResponseSignDTO> response = signDataService.allImages(userEmail);

        return ResponseEntity.ok(response);
    }*/




    //pid 로 인코딩된 문자열 전송(리스트)




}
