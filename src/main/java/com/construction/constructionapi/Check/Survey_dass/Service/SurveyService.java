package com.construction.constructionapi.Check.Survey_dass.Service;



import com.construction.constructionapi.Check.Domain.Score;
import com.construction.constructionapi.Check.Repository.ScoreRepository;
import com.construction.constructionapi.Check.Survey_dass.DTO.SurveyRequestDTO;
import com.construction.constructionapi.Member.Repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


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

        String today = LocalDate.now().toString();


        System.out.println(userEmail + " " + userId + " " + today);
        /*System.out.println(scoreRepository.findByUserIdAndDate(userId, today).getStress());*/

        Score record = scoreRepository.findByUserIdAndDate(userId, today);



        if(record == null || record.getAnxiety() == 0) {
            return true;
        }

        return false;
    }

    public void updateRecord(String userEmail, SurveyRequestDTO surveyRequestDTO){
        //userid 도출
        String userId = memberRepository.findByEmail(userEmail).getUserid();

        String today = LocalDate.now().toString();

        Score record = Score.builder()
                .userId(userId)
                .anxiety(surveyRequestDTO.getAnxiety())
                .depression(surveyRequestDTO.getDepression())
                .stress(surveyRequestDTO.getStress())
                .date(today)
                .build();

        scoreRepository.save(record);
    }

}
