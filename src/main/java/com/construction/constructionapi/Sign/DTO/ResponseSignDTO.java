package com.construction.constructionapi.Sign.DTO;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class ResponseSignDTO {
    private LocalDate uploadDate;
    private String encodedImage;
}
