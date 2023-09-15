package com.construction.constructionapi.Sign.Service;

import com.construction.constructionapi.Sign.DTO.RequestSignDTO;
import com.construction.constructionapi.Sign.DTO.ResponseSignDTO;
import com.construction.constructionapi.Sign.Domain.SignImage;
import com.construction.constructionapi.Sign.Repository.SignImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SignDataService {

    @Autowired
    private SignImageRepository signImageRepository;

    //관리자 DB 업로드
    public SignImage saveSignData(RequestSignDTO requestSignDTO){
        SignImage signImage = SignImage.builder()
                .PDFName(requestSignDTO.getTitle())
                .encodedImage(requestSignDTO.getEncodeImage())
                .uploadDate(requestSignDTO.getDate())
                .build();

        return signImageRepository.save(signImage);
    }

    // pdf 삭제
    public void deleteNotice(Long id) { signImageRepository.deleteById(id);}

    //관리자 리스트 출력
    public List<SignImage> allImages() {
        return signImageRepository.findAll();
    }

    //어떤 근로자의 모든 싸인 출력
/*    public List<ResponseSignDTO> allWorkersImages(String email) {
        List<SignImage> imageDataList = signImageRepository.findAllByEmail(email);

        return imageDataList.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }*/


    //관리자가 근로자에게 보여줄 PDF 파일 선택
    public String selectedPDF(Long pid){



        return "ok";
    }


    //근로자에게 선택된 PDF 파일을 보내주기





    private ResponseSignDTO convertToResponseDTO(SignImage imageData) {
        return ResponseSignDTO.builder()
                .uploadDate(imageData.getUploadDate())
                .encodedImage(imageData.getEncodedImage())
                .build();
    }




}
