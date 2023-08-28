package com.construction.constructionapi.Sign.Service;

import com.construction.constructionapi.Sign.DTO.RequestSignDTO;
import com.construction.constructionapi.Sign.DTO.ResponseSignDTO;
import com.construction.constructionapi.Sign.Domain.SignImage;
import com.construction.constructionapi.Sign.Repository.SignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SignDataService {

    @Autowired
    private SignRepository signRepository;

    public SignImage saveSignData(RequestSignDTO requestSignDTO){
        SignImage signImage = SignImage.builder()
                .email(requestSignDTO.getEmail())
                .encodedImage(requestSignDTO.getEncodeImage())
                .uploadDate(LocalDate.now())
                .build();

        return signRepository.save(signImage);
    }

    public List<ResponseSignDTO> allImages(String email) {
        List<SignImage> imageDataList = signRepository.findAllByEmail(email);

        return imageDataList.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    private ResponseSignDTO convertToResponseDTO(SignImage imageData) {
        return ResponseSignDTO.builder()
                .uploadDate(imageData.getUploadDate())
                .encodedImage(imageData.getEncodedImage())
                .build();
    }

}
