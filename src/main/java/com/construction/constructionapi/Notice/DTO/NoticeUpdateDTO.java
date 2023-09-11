package com.construction.constructionapi.Notice.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticeUpdateDTO {
    private int id;
    private String title;
    private String content;
}
