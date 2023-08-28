package com.construction.constructionapi.Employee.DTO;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseScoreDTO {
    private Integer doorlock;
    private Float hammering;
    private Integer nBack;
    private Integer simon;
    private Float trafficLight;
    private Integer catchMole;
    private Integer numberPuzzle;
    private Integer depression;
    private Integer anxiety;
    private Integer stress;
    private String date;
}
