package com.construction.constructionapi.Notice.Domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "notice")
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private int id;

    @Column(name = "user_id")
    private String userID;
    private String title;
    private String content;
    private LocalDate write_date;
}