package com.construction.constructionapi.Test.UnityContent.DTO.TestDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestScoreDTO {
    String name;
    float score;
    int correct;
    int wrong;
}
