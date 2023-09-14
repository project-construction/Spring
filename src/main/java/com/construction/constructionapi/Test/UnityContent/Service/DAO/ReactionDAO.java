package com.construction.constructionapi.Test.UnityContent.Service.DAO;

import com.construction.constructionapi.Member.Domain.Member;
import com.construction.constructionapi.Member.Repository.MemberRepository;
import com.construction.constructionapi.Test.Domain.Reaction;
import com.construction.constructionapi.Test.Repository.ReactionRepository;
import com.construction.constructionapi.Test.UnityContent.DTO.TestDTO.ResponseReactionDTO;
import com.construction.constructionapi.Test.UnityContent.DTO.TestDTO.TestScoreDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Component
public class ReactionDAO {
    ReactionRepository reactionRepository;
    MemberRepository memberRepository;
    @Autowired
    public ReactionDAO(ReactionRepository reactionRepository, MemberRepository memberRepository){
        this.memberRepository = memberRepository;
        this.reactionRepository = reactionRepository;
    }

    @Transactional
    public void saveReaction(String email, TestScoreDTO testScoreDTO){
        Member member = memberRepository.findByEmail(email);
        Reaction reaction = reactionRepository.findByUserIdAndDate(member.getUserid(), String.valueOf(LocalDate.now()));
        if(reaction == null){
            reaction = new Reaction();
            reaction.setUserId(member.getUserid());
        }
        reaction.setDate(String.valueOf(LocalDate.now()));
        reaction.setReaction(testScoreDTO.getName(), testScoreDTO.getCorrect(), testScoreDTO.getWrong());
        reactionRepository.save(reaction);
    }

    @Transactional
    public ResponseReactionDTO getAllReaction(String email){
        Member member = memberRepository.findByEmail(email);
        Reaction reaction = reactionRepository.findByUserIdAndDate(member.getUserid(), String.valueOf(LocalDate.now()));
        ResponseReactionDTO responseReactionDTO = ResponseReactionDTO.builder()
                .date(reaction.getDate())
                .hammeringCorrect(reaction.getHammeringCorrect())
                .hammeringWrong(reaction.getHammeringWrong())
                .puzzleCorrect(reaction.getPuzzleCorrect())
                .puzzleWrong(reaction.getPuzzleWrong())
                .trafficLightWrong(reaction.getTrafficLightWrong())
                .build();
        return responseReactionDTO;
    }
}
