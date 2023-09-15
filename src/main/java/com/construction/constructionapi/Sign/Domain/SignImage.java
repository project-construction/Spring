package com.construction.constructionapi.Sign.Domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String PDFName;

    @Lob
    private String encodedImage;
    private String uploadDate;
}
