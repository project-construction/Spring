package com.construction.constructionapi.Test.UnityContent.DTO.TestDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class TestAndReactionAllDTO {
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

    String date;

    Integer hammeringCorrect;
    Integer hammeringWrong;
    Integer trafficLightWrong;
    Integer puzzleCorrect;
    Integer puzzleWrong;

    public void mergeAll(ResponseReactionDTO responseReactionDTO, TestScoreAllDTO testScoreAllDTO){
        this.userid = testScoreAllDTO.getUserid();
        this.doorLock = testScoreAllDTO.getDoorLock();
        this.hammering = testScoreAllDTO.getHammering();
        this.nBack = testScoreAllDTO.getNBack();
        this.simon = testScoreAllDTO.getSimon();
        this.trafficLight = testScoreAllDTO.getTrafficLight();
        this.numberPuzzle = testScoreAllDTO.getNumberPuzzle();
        this.catchMole = testScoreAllDTO.getCatchMole();
        this.depression = testScoreAllDTO.getDepression();
        this.anxiety = testScoreAllDTO.getAnxiety();
        this.stress = testScoreAllDTO.getStress();
        this.date = testScoreAllDTO.getDate();
        this.hammeringCorrect = responseReactionDTO.getHammeringCorrect();
        this.hammeringWrong = responseReactionDTO.getHammeringWrong();
        this.trafficLightWrong = responseReactionDTO.getTrafficLightWrong();
        this.puzzleCorrect = responseReactionDTO.getPuzzleCorrect();
        this.puzzleWrong = responseReactionDTO.getPuzzleWrong();

    }

}
