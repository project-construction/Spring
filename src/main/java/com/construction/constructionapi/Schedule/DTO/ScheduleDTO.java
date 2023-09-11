package com.construction.constructionapi.Schedule.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ScheduleDTO {

    private String date;
    private List<ContentDTO> schedules;
}
