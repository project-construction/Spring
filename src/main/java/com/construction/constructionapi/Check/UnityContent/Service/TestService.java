package com.construction.constructionapi.Check.UnityContent.Service;


import com.construction.constructionapi.Check.Domain.Score;
import com.construction.constructionapi.Check.UnityContent.DTO.TestDTO.*;
import com.construction.constructionapi.Check.UnityContent.Service.DAO.TestDAO;
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

    public void fillCatchMole(String userEmail,TestCatchMoleDTO testCatchMoleDTO){
        testDAO.saveCatchMole(userEmail,testCatchMoleDTO);
    }

    public void fillDoorLock(String userEmail,TestDoorLockDTO testDoorLockDTO){
        testDAO.saveDoorLock(userEmail,testDoorLockDTO);
    }

    public void fillHammering(String userEmail, TestHammeringDTO testHammeringDTO){
        testDAO.saveHammering(userEmail,testHammeringDTO);
    }

    public void fillNBack(String userEmail, TestNBackDTO testNBackDTO){
        testDAO.saveNBack(userEmail,testNBackDTO);
    }

    public void fillNumberPuzzle(String userEmail, TestNumberPuzzleDTO testNumberPuzzleDTO){
        testDAO.saveNumber_puzzle(userEmail,testNumberPuzzleDTO);
    }

    public void fillSimon(String userEmail, TestSimonDTO testSimonDTO){
        testDAO.saveSimon(userEmail,testSimonDTO);
    }

    public void fillTrafficLight(String userEmail, TestTrafficLightDTO testTrafficLightDTO){
        testDAO.saveTrafficLight(userEmail,testTrafficLightDTO);
    }

    public TestAllDTO findAllById(String userEmail){
        Score score = testDAO.findAlLByID(userEmail);
        TestAllDTO testAllDTO = TestAllDTO.builder()
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
