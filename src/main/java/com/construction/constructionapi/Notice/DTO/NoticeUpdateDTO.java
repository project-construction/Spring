package com.construction.constructionapi.Notice.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticeUpdateDTO {
    private int notice_id;
    private String title;
    private String content;
}
