package com.construction.constructionapi.Attendance.Service;


import com.construction.constructionapi.Check.Domain.Score;
import com.construction.constructionapi.Check.Repository.ScoreRepository;
import com.construction.constructionapi.SpringSecurity.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AttendService {

    private ScoreRepository scoreRepository;
    private MemberRepository memberRepository;

    @Autowired
    public AttendService(ScoreRepository scoreRepository, MemberRepository memberRepository) {
        this.scoreRepository = scoreRepository;
        this.memberRepository = memberRepository;
    }

    public boolean checkAttend(String userEmail) {

        //userid 도출
        String userId = memberRepository.findByEmail(userEmail).getUserid();

        String today = LocalDate.now().toString();

        System.out.println(userEmail + " " + userId + " " + today);
        /*System.out.println(scoreRepository.findByUserIdAndDate(userId, today).getStress());*/

        List<Score> scores = scoreRepository.findByUserId(userId);

        for (int i = 0; i < scores.size(); i++) {
            if (scores.get(i).isCheck() && scores.get(i).getDate().substring(0, 10).equals(today)) {
                return true;
            }
        }
        return false;
    }

    public void makeAttend(String userEmail){
        String userId = memberRepository.findByEmail(userEmail).getUserid();

        Score score = new Score();
        score.setUserId(userId);
        score.setDate(LocalDateTime.now().toString());
        scoreRepository.save(score);
    }

    // ischeck update
    public boolean attend(String userEmail){
        String userId = memberRepository.findByEmail(userEmail).getUserid();
        String today = LocalDate.now().toString();
        List<Score> scores = scoreRepository.findByUserId(userId);

        for (int i = 0; i < scores.size(); i++) {
            if (scores.get(i).getDate().substring(0, 10).equals(today)) {
                Score score = scores.get(i);
                score.setCheck(true);
                score.setDate(LocalDateTime.now().toString());
                scoreRepository.save(score);
                return true;
            }
        }
        return false;
    }
}