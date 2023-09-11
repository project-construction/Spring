package com.construction.constructionapi.Schedule.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ContentDTO {

    private long id;
    private String time;
    private LocalDateTime dateTime;
    private String content;
}
