package com.construction.constructionapi.Test.UnityContent.Service;


import com.construction.constructionapi.Test.Domain.Score;
import com.construction.constructionapi.Test.UnityContent.DTO.TestDTO.*;
import com.construction.constructionapi.Test.UnityContent.Service.DAO.TestDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TestService {
    private final TestDAO testDAO;
    @Autowired
    public TestService(TestDAO testDAO){
        this.testDAO = testDAO;
    }

    public void fillScore(String userEmail,TestScoreDTO testScoreDTO){
        testDAO.saveScore(userEmail,testScoreDTO);
    }

    public TestScoreAllDTO findAllScore(String userEmail){
        Score score = testDAO.findAlLByID(userEmail);
        TestScoreAllDTO testAllDTO = TestScoreAllDTO.builder()
                .anxiety(score.getAnxiety())
                .date(LocalDate.now().toString())
                .hammering(score.getHammering())
                .catchMole(score.getCatchMole())
                .depression(score.getDepression())
                .simon(score.getSimon())
                .stress(score.getStress())
                .doorLock(score.getDoorlock())
                .nBack(score.getNBack())
                .trafficLight(score.getTrafficLight())
                .userid(score.getUserId())
                .numberPuzzle(score.getNumberPuzzle())
                .build();

        return testAllDTO;
    }


}
