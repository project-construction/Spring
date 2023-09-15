package com.construction.constructionapi.Sign.Domain;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class workerSignedImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wid")
    private Long wid;

    @Column(name = "pid")
    private String pid;

    @Column(name = "workerName")
    private String workerName;

    @Column(name = "Encode")
    private String PDFEncode;
}
