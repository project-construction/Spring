package com.construction.constructionapi.Test.UnityContent.Service.DAO;

import com.construction.constructionapi.Test.Domain.Reaction;
import com.construction.constructionapi.Test.Domain.Score;
import com.construction.constructionapi.Test.Repository.ReactionRepository;
import com.construction.constructionapi.Test.Repository.ScoreRepository;
import com.construction.constructionapi.Test.UnityContent.DTO.TestDTO.*;
import com.construction.constructionapi.Member.Domain.Member;
import com.construction.constructionapi.Member.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

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
    public void saveScore(String userEmail, TestScoreDTO testScoreDTO){

        Member member = memberRepository.findByEmail(userEmail);
        Score score = scoreRepository.findByUserIdAndDate(member.getUserid(), String.valueOf(LocalDate.now()));

        if(score == null){
            score = new Score();
            score.setUserId(String.valueOf(member.getUserid()));
        }
        score.setDate(LocalDate.now().toString());
        score.setScore(testScoreDTO.getName(), testScoreDTO.getScore());
        scoreRepository.save(score);

    }

    public Score findAlLByID(String userEmail){
        Member member = memberRepository.findByEmail(userEmail);
        Score score = scoreRepository.findByUserId(member.getUserid());
        return score;
    }
}
