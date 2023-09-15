package com.construction.constructionapi.Test.UnityContent.DTO.TestDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseReactionDTO {
    String userid;
    String date;

    Integer hammeringCorrect;
    Integer hammeringWrong;
    Integer trafficLightWrong;
    Integer puzzleCorrect;
    Integer puzzleWrong;
}
