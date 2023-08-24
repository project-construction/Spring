package com.construction.constructionapi.Check.UnityContent.DTO.TestDTO;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@Builder
public class TestAllDTO {
    String userid;

    int doorLock;

    float hammering;

    int nBack;

    int simon;

    float trafficLight;

    int numberPuzzle;

    int catchMole;

    int depression;

    int anxiety;

    int stress;

    LocalDateTime date;
}
