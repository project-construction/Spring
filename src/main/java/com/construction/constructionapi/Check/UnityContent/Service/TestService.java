package com.construction.constructionapi.Check.UnityContent.Service;


import com.construction.constructionapi.Check.Domain.Score;
import com.construction.constructionapi.Check.UnityContent.DTO.TestDTO.*;
import com.construction.constructionapi.Check.UnityContent.Service.DAO.TestDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

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
        TestAllDTO testAllDTO = new TestAllDTO();
        Score score = testDAO.findAlLByID(userEmail);

        testAllDTO.setAnxiety(score.getAnxiety());
        testAllDTO.setDate(LocalDateTime.parse(String.valueOf(score.getDate())));
        testAllDTO.setHammering(score.getHammering());
        testAllDTO.setCatchMole(score.getCatchMole());
        testAllDTO.setDepression(score.getDepression());
        testAllDTO.setSimon(score.getSimon());
        testAllDTO.setStress(score.getStress());
        testAllDTO.setDoorLock(score.getDoorlock());
        testAllDTO.setNBack(score.getNBack());
        testAllDTO.setTrafficLight(score.getTrafficLight());
        testAllDTO.setUserid(score.getUserId());
        testAllDTO.setNumberPuzzle(score.getNumberPuzzle());
        return testAllDTO;
    }
}
