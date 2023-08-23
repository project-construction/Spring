package com.construction.constructionapi.Check.UnityContent.Service.DAO;

import com.construction.constructionapi.Check.Domain.Score;
import com.construction.constructionapi.Check.Repository.ScoreRepository;
import com.construction.constructionapi.Check.UnityContent.DTO.TestDTO.*;
import com.construction.constructionapi.SpringSecurity.Domain.Member;
import com.construction.constructionapi.SpringSecurity.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Component
public class TestDAO {
    ScoreRepository scoreRepository;
    MemberRepository memberRepository;
    @Autowired
    public TestDAO(ScoreRepository scoreRepository, MemberRepository memberRepository){
        this.memberRepository = memberRepository;
        this.scoreRepository = scoreRepository;
    }

    @Transactional
    public void saveDoorLock(String userEmail, TestDoorLockDTO testDoorLockDTO){

        Member member = memberRepository.findByEmail(userEmail);
        Score score = scoreRepository.findByUserId(member.getUserid());
        if(score != null){
            score.setDoorlock(testDoorLockDTO.getDoorLock());
            score.setDate(String.valueOf(LocalDateTime.now()));
        }
        else{
            Score t = new Score();
            t.setDoorlock(testDoorLockDTO.getDoorLock());
            t.setUserId(String.valueOf(member.getUserid()));
            t.setDate(String.valueOf(LocalDateTime.now()));
            scoreRepository.save(t);
        }
    }

    @Transactional
    public void saveHammering(String userEmail, TestHammeringDTO testHammeringDTO){
        Member member = memberRepository.findByEmail(userEmail);
        Score score = scoreRepository.findByUserId(String.valueOf(member.getUserid()));
        if(score != null){
            score.setHammering((float) testHammeringDTO.getHammering());
            score.setDate(String.valueOf(LocalDateTime.now()));
        }
        else{
            Score t = new Score();
            t.setHammering((float) testHammeringDTO.getHammering());
            t.setUserId(String.valueOf(member.getUserid()));
            t.setDate(String.valueOf(LocalDateTime.now()));
            scoreRepository.save(t);
        }
    }

    @Transactional
    public void saveNBack(String userEmail, TestNBackDTO testNBackDTO){
        Member member = memberRepository.findByEmail(userEmail);
        Score score = scoreRepository.findByUserId(String.valueOf(member.getUserid()));
        if(score != null){
            score.setNBack(testNBackDTO.getNBack());
            score.setDate(String.valueOf(LocalDateTime.now()));
        }
        else{
            Score t = new Score();
            t.setNBack(testNBackDTO.getNBack());
            t.setUserId(String.valueOf(member.getUserid()));
            t.setDate(String.valueOf(LocalDateTime.now()));
            scoreRepository.save(t);
        }
    }

    @Transactional
    public void saveSimon(String userEmail, TestSimonDTO testSimonDTO){
        Member member = memberRepository.findByEmail(userEmail);
        Score score = scoreRepository.findByUserId(String.valueOf(member.getUserid()));
        if(score != null){
            score.setSimon(testSimonDTO.getSimon());
            score.setDate(String.valueOf(LocalDateTime.now()));
        }
        else{
            Score t = new Score();
            t.setSimon(testSimonDTO.getSimon());
            t.setUserId(String.valueOf(member.getUserid()));
            t.setDate(String.valueOf(LocalDateTime.now()));
            scoreRepository.save(t);
        }
    }


    @Transactional
    public void saveTrafficLight(String userEmail, TestTrafficLightDTO testTrafficLightDTO){
        Member member = memberRepository.findByEmail(userEmail);
        Score score = scoreRepository.findByUserId(String.valueOf(member.getUserid()));
        if(score != null){
            score.setTrafficLight((float) testTrafficLightDTO.getTrafficLight());
            score.setDate(String.valueOf(LocalDateTime.now()));
        }
        else{
            Score t = new Score();
            t.setTrafficLight((float) testTrafficLightDTO.getTrafficLight());
            t.setUserId(String.valueOf(member.getUserid()));
            t.setDate(String.valueOf(LocalDateTime.now()));
            scoreRepository.save(t);
        }
    }

    @Transactional
    public void saveNumber_puzzle(String userEmail, TestNumberPuzzleDTO testNumberPuzzleDTO){
        Member member = memberRepository.findByEmail(userEmail);
        Score score = scoreRepository.findByUserId(String.valueOf(member.getUserid()));
        if(score != null){
            score.setNumberPuzzle(testNumberPuzzleDTO.getNumber_puzzle());
            score.setDate(String.valueOf(LocalDateTime.now()));
        }
        else{
            Score t = new Score();
            t.setNumberPuzzle(testNumberPuzzleDTO.getNumber_puzzle());
            t.setUserId(String.valueOf(member.getUserid()));
            t.setDate(String.valueOf(LocalDateTime.now()));
            scoreRepository.save(t);
        }
    }

    @Transactional
    public void saveCatchMole(String userEmail, TestCatchMoleDTO testCatchMoleDTO){
        Member member = memberRepository.findByEmail(userEmail);
        Score score = scoreRepository.findByUserId(String.valueOf(member.getUserid()));
        if(score != null){
            score.setCatchMole(testCatchMoleDTO.getCatchMole());
            score.setDate(String.valueOf(LocalDateTime.now()));
        }
        else{
            Score t = new Score();
            t.setCatchMole(testCatchMoleDTO.getCatchMole());
            t.setUserId(String.valueOf(member.getUserid()));
            t.setDate(String.valueOf(LocalDateTime.now()));
            scoreRepository.save(t);
        }
    }

    public Score findAlLByID(String userEmail){
        Member member = memberRepository.findByEmail(userEmail);
        Score score = scoreRepository.findByUserId(member.getUserid());
        return score;
    }
}
