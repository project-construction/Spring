package com.construction.constructionapi.Check.Survey_dass.Service;



import com.construction.constructionapi.Check.Repository.ScoreRepository;
import com.construction.constructionapi.SpringSecurity.Repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class SurveyService {

    private final ScoreRepository scoreRepository;
    private final MemberRepository memberRepository;

    public SurveyService(ScoreRepository scoreRepository,
                         MemberRepository memberRepository){
        this.scoreRepository = scoreRepository;
        this.memberRepository = memberRepository;
    }

    public boolean hasRecordToday(String userEmail){

        //userid 도출
        String userId = memberRepository.findByEmail(userEmail).getUserid();

        Date today = new Date();
        System.out.println(today);

        System.out.println(scoreRepository.existsByUserIdAndDate(userId,today));

        return scoreRepository.existsByUserIdAndDate(userId,today);
    }


}
